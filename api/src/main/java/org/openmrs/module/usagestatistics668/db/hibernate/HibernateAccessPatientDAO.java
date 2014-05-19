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
import org.openmrs.module.usagestatistics668.AccessPatient;
import org.openmrs.module.usagestatistics668.ActionCriteria;
import org.openmrs.module.usagestatistics668.db.AccessPatientDAO;

/**
 *
 * @author Ye Cheng
 */
public class HibernateAccessPatientDAO implements AccessPatientDAO {

    private SessionFactory sessionFactory;
    protected static final String TABLE_PATIENT = "access_patient";
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
     * see AccessPatientService
     * @param id
     * @return AccessPatient
     */
    public AccessPatient getAccessPatient(Integer id) {
        return (AccessPatient) sessionFactory.getCurrentSession().get(AccessPatient.class, id);
    }

    /**
     * see AccessPatientService
     * @param accessPatient 
     */
    public void saveAccessPatient(AccessPatient accessPatient) {
        sessionFactory.getCurrentSession().saveOrUpdate(accessPatient);
    }

    /**
     * This function is called by the AccessPatient service
     * This function uses SQL query to get the most viewed patients
     * see AccessPatientService
     * @param since starting time of data access
     * @param until ending time of data access
     * @param filter action type of the data access
     * @param maxResults number of record it returns
     * @return List of AccessPatient id's with their number of access
     */
    public List<Object[]> getMostViewedPatient(Date since, Date until, ActionCriteria filter, int maxResults) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ");
        sb.append("  patient_id, ");
        sb.append("  count( * ) AS count ");
        sb.append("FROM " + TABLE_PATIENT);
        sb.append(" WHERE 1=1 ");
        if (since != null) {
            sb.append("AND timestamp > '" + dfSQL.format(since) + "' ");
        }
        if (until != null) {
            sb.append("AND timestamp < '" + dfSQL.format(until) + "' ");
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
        sb.append(" GROUP BY patient_id ");
        sb.append("ORDER BY count DESC ");
        sb.append("LIMIT " + maxResults);
        return executeSQLQuery(sb.toString());
    }

    /**
     * This function is called by the AccessPatient service
     * This function uses SQL query to get patient data being accessed
     * during a period of time
     * see AccessPatientService
     * @param since starting time of data access
     * @param until ending time of data access
     * @param patientId patient id
     * @param filter action type of data access
     * @param maxResults number of records it returns
     * @return List of AccessPatients
     */
    public List<Object> getDateRangeList(Date since, Date until, Integer patientId, ActionCriteria filter, Integer maxResults) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT SQL_CALC_FOUND_ROWS {s.*} ");
        sb.append("FROM " + "access_patient" + " s ");
        sb.append("WHERE 1=1 "); //this is so we can add more statements on

        if (patientId != null) {
            sb.append("  AND s.patient_id=" + patientId.toString());
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

        Session session = sessionFactory.getCurrentSession();

        List<Object> results = sessionFactory.getCurrentSession().createSQLQuery(sb.toString())
                .addEntity("s", AccessPatient.class)
                .list();

        return results;
    }

    protected List<Object[]> executeSQLQuery(String sql) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        return query.list();
    }

}
