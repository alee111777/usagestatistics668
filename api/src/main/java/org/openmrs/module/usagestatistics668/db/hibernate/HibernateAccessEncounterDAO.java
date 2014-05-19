/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.usagestatistics668.db.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.module.usagestatistics668.AccessEncounter;
import org.openmrs.module.usagestatistics668.ActionCriteria;
import org.openmrs.module.usagestatistics668.db.AccessEncounterDAO;

/**
 *
 * @author Anthony Lee
 */
public class HibernateAccessEncounterDAO implements AccessEncounterDAO {

    private SessionFactory sessionFactory;
    protected static final String TABLE_ENCOUNTER = "access_encounter";
    protected static final SimpleDateFormat dfSQL = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * This is a Hibernate object. It gives us metadata about the currently
     * connected database, the current session, the current db user, etc. To
     * save and get objects, calls should go through
     * sessionFactory.getCurrentSession() <br/>
     * <br/>
     * This is called by Spring. See the /metadata/moduleApplicationContext.xml
     * for the "sessionFactory" setting. See the applicationContext-service.xml
     * file in CORE openmrs for where the actual "sessionFactory" object is
     * first defined.
     *
     * @param sessionFactory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * see AccessEncounterService
     * @param id
     * @return AccessEncounter
     */
    public AccessEncounter getAccessEncounter(Integer id) {
        return (AccessEncounter) sessionFactory.getCurrentSession().get(AccessEncounter.class, id);
    }

    /**
     * see AccessEncounterService
     * @param accessEncounter 
     */
    public void saveAccessEncounter(AccessEncounter accessEncounter) {
        sessionFactory.getCurrentSession().saveOrUpdate(accessEncounter);
    }

    /**
     * this function is called by the AccessEncounter service
     * see AccessEncounterService
     * @param since
     * @param until
     * @param filter
     * @param maxResults
     * @return List of AccessEncounter id's with their number of access
     */
    public List<Object[]> getMostViewedEncounter(Date since, Date until, ActionCriteria filter, int maxResults) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ");
        sb.append("  encounter_id, ");
        sb.append("  count( * ) AS count ");
        sb.append("FROM access_encounter");
        sb.append(" WHERE 1=1 ");
        if (since != null) {
            sb.append("AND timestamp > '" + dfSQL.format(since) + "' ");
        }
        if (until != null) {
            sb.append("AND timestamp < '" + dfSQL.format(until) + "' ");
        }
        //System.out.println(filter);
        if (filter == ActionCriteria.CREATED) {
            sb.append("  AND access_type = 'created' ");
        } else if (filter == ActionCriteria.VIEWED) {
            sb.append("  AND access_type = 'viewed' ");
        } else if (filter == ActionCriteria.UPDATED) {
            sb.append("  AND access_type = 'updated' ");
        } else if (filter == ActionCriteria.VOIDED) {
            sb.append("  AND access_type = 'voided' ");
        } else if (filter == ActionCriteria.UNVOIDED) {
            sb.append("  AND access_type = 'unvoided' ");
        }
        sb.append(" GROUP BY encounter_id ");
        sb.append("ORDER BY count DESC ");
        sb.append("LIMIT " + maxResults);
        System.out.println(sb.toString());
        return executeSQLQuery(sb.toString());
    }
    
    /**
     * this function is called by AccessEncounterService
     * @param since
     * @param until
     * @param encounterId
     * @param filter
     * @param maxResults
     * @return List of AccessEncounters
     */
    public List<Object> getDateRangeList(Date since, Date until, Integer encounterId, ActionCriteria filter, Integer maxResults) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT SQL_CALC_FOUND_ROWS {s.*} ");
        sb.append("FROM " + "access_encounter" + " s ");
        sb.append("WHERE 1=1 "); //this is so we can add more statements on

        if (encounterId != null) {
            sb.append("  AND s.encounter_id=" + encounterId.toString());
        }
        if (since != null) {
            sb.append("  AND timestamp > '" + dfSQL.format(since) + "' ");
        }
        if (until != null) {
            sb.append("  AND timestamp < '" + dfSQL.format(until) + "' ");
        }

        if (filter == ActionCriteria.CREATED) {
            sb.append("  AND access_type = 'created' ");
        } else if (filter == ActionCriteria.VIEWED) {
            sb.append("  AND access_type = 'viewed' ");
        } else if (filter == ActionCriteria.UPDATED) {
            sb.append("  AND access_type = 'updated' ");
        } else if (filter == ActionCriteria.VOIDED) {
            sb.append("  AND access_type = 'voided' ");
        } else if (filter == ActionCriteria.UNVOIDED) {
            sb.append("  AND access_type = 'unvoided' ");
        }

        sb.append("  ORDER BY s.timestamp ");

        if (maxResults == null) {
            maxResults = 20;
        }

        sb.append("LIMIT " + maxResults + ";");
        
        System.out.println("**************  " + sb.toString());

        Session session = sessionFactory.getCurrentSession();

        List<Object> results = sessionFactory.getCurrentSession().createSQLQuery(sb.toString())
                .addEntity("s", AccessEncounter.class)
                .list();
        
        return results;
    }

    /**
     * this function is for sending a sql query to the DB
     * @param sql
     * @return List<Object>
     */
    protected List<Object[]> executeSQLQuery(String sql) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        return query.list();
    }
}
