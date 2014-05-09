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
import static org.openmrs.module.usagestatistics668.db.hibernate.HibernateAccessVisitDAO.dfSQL;


/**
 *
 * @author Ye
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

   public AccessPatient getAccessPatient(Integer id) {
      return (AccessPatient) sessionFactory.getCurrentSession().get(AccessPatient.class, id);
   }

   public void saveAccessPatient(AccessPatient accessPatient) {
       sessionFactory.getCurrentSession().saveOrUpdate(accessPatient);
   }

   public List<Object[]> getMostViewedPatient(Date since, Date until, ActionCriteria filter,int maxResults) {
  		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		sb.append("  patient_id, ");
		sb.append("  count( * ) AS count ");
		sb.append("FROM " + TABLE_PATIENT);
                sb.append(" WHERE 1=1 ");
		if (since != null)
			sb.append("AND timestamp > '" + dfSQL.format(since) + "' ");
		if (until != null)
			sb.append("AND timestamp < '" + dfSQL.format(until) + "' ");
                System.out.println(filter);
                if (filter == ActionCriteria.CREATED)
			sb.append("  AND access_type = 'created' ");
		else if (filter == ActionCriteria.VOIDED)
			sb.append("  AND access_type = 'viewed' ");
		else if (filter == ActionCriteria.UPDATED)
			sb.append("  AND access_type = 'updated' ");
		else if (filter == ActionCriteria.VOIDED)
			sb.append("  AND access_type = 'voided' ");
                else if (filter == ActionCriteria.UNVOIDED)
			sb.append("  AND access_type = 'unvoided' ");
		sb.append(" GROUP BY patient_id ");
                sb.append("ORDER BY count DESC ");
                sb.append("LIMIT " + maxResults);
                System.out.println(sb.toString());
		return executeSQLQuery(sb.toString());
   }
   
   public List<Object[]> getDateRangeList(Date since, Date until, ActionCriteria filter, int maxResults) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT ");
        sb.append("  patient_id, ");
        sb.append("  count( * ) AS count ");
        sb.append("FROM access_patient");
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
        sb.append(" GROUP BY patient_id ");
        sb.append("ORDER BY count DESC ");
        sb.append("LIMIT " + maxResults);
        System.out.println(sb.toString());
        return executeSQLQuery(sb.toString());
    }
    
    protected List<Object[]> executeSQLQuery(String sql) {	
	Session session = sessionFactory.getCurrentSession();
	SQLQuery query = session.createSQLQuery(sql);
	return query.list();
    }
   
}