/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.usagestatistics668.db.hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.openmrs.module.usagestatistics668.AccessVisit;
import org.openmrs.module.usagestatistics668.ActionCriteria;
import org.openmrs.module.usagestatistics668.db.AccessVisitDAO;

/**
 *
 * @author Jonathan
 */
public class HibernateAccessVisitDAO implements AccessVisitDAO {

    private SessionFactory sessionFactory;

    protected static final String TABLE_VISIT = "access_visit";
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

    public AccessVisit getAccessVisit(Integer id) {
        return (AccessVisit) sessionFactory.getCurrentSession().get(AccessVisit.class, id);
    }

    public void saveAccessVisit(AccessVisit accessVisit) {
        sessionFactory.getCurrentSession().saveOrUpdate(accessVisit);
    }

    public List<AccessVisit> getMostRecent(int numOfVisits) {
        Criteria query = sessionFactory.getCurrentSession().createCriteria(AccessVisit.class);
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT SQL_CALC_FOUND_ROWS {s.*} ");
        sb.append("FROM access_encounter s ");
        sb.append("WHERE 1=1 ");
        sb.append("LIMIT 10");

        List<AccessVisit> results = sessionFactory.getCurrentSession().createSQLQuery(sb.toString())
                .addEntity("s", AccessVisit.class)
                .list();

        return results;

    }

    public List<Object[]> getMostViewedVisit(Date since, Date until, ActionCriteria filter, int maxResults) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ");
        sb.append("  visit_id, ");
        sb.append("  count( * ) AS count ");
        sb.append("FROM " + TABLE_VISIT);
        sb.append(" WHERE 1=1 ");
        if (since != null) {
            sb.append("AND timestamp > '" + dfSQL.format(since) + "' ");
        }
        if (until != null) {
            sb.append("AND timestamp < '" + dfSQL.format(until) + "' ");
        }
        System.out.println(filter);
        if (filter == ActionCriteria.CREATED) {
            sb.append("  AND access_type = 'created' ");
        } else if (filter == ActionCriteria.VOIDED) {
            sb.append("  AND access_type = 'viewed' ");
        } else if (filter == ActionCriteria.UPDATED) {
            sb.append("  AND access_type = 'updated' ");
        } else if (filter == ActionCriteria.VOIDED) {
            sb.append("  AND access_type = 'voided' ");
        } else if (filter == ActionCriteria.UNVOIDED) {
            sb.append("  AND access_type = 'unvoided' ");
        }
        sb.append(" GROUP BY visit_id ");
        sb.append("ORDER BY count DESC ");
        sb.append("LIMIT " + maxResults);
        System.out.println(sb.toString());
        return executeSQLQuery(sb.toString());
    }

    public List<Object> getDateRangeList(Date since, Date until, Integer visitId, ActionCriteria filter, Integer maxResults) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT SQL_CALC_FOUND_ROWS {s.*} ");
        sb.append("FROM " + "access_visit" + " s ");
        sb.append("WHERE 1=1 "); //this is so we can add more statements on

        if (visitId != null) {
            sb.append("  AND s.visit_id=" + visitId.toString());
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
                .addEntity("s", AccessVisit.class)
                .list();
//
//        int count = ((Number) session.createSQLQuery("SELECT FOUND_ROWS();").uniqueResult()).intValue();
//        paging.setResultsTotal(count);
        
        //List<Object> results = new ArrayList<Object>();
        return results;
    }

    protected List<Object[]> executeSQLQuery(String sql) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        return query.list();
    }

}
