/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.usagestatistics668.db.hibernate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.openmrs.module.usagestatistics668.AccessEncounter;
import org.openmrs.module.usagestatistics668.db.AccessEncounterDAO;

/**
 *
 * @author Anthony Lee
 */
public class HibernateAccessEncounterDAO implements AccessEncounterDAO {

   private SessionFactory sessionFactory;

   /**
    * This is a Hibernate object. It gives us metadata about the currently
    * connected database, the current session, the current db user, etc. To save
    * and get objects, calls should go through
    * sessionFactory.getCurrentSession() <br/>
    * <br/>
    * This is called by Spring. See the /metadata/moduleApplicationContext.xml
    * for the "sessionFactory" setting. See the applicationContext-service.xml
    * file in CORE openmrs for where the actual "sessionFactory" object is first
    * defined.
    *
    * @param sessionFactory
    */
   public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   public AccessEncounter getAccessEncounter(Integer id) {
      return (AccessEncounter) sessionFactory.getCurrentSession().get(AccessEncounter.class, id);
   }

   public void saveAccessEncounter(AccessEncounter accessEncounter) {
      sessionFactory.getCurrentSession().saveOrUpdate(accessEncounter);
   }

   public List<AccessEncounter> getMostRecent(int numOfEncounters) {
      Criteria query = sessionFactory.getCurrentSession().createCriteria(AccessEncounter.class);
      StringBuffer sb = new StringBuffer();	
      sb.append("SELECT SQL_CALC_FOUND_ROWS {s.*} ");
      sb.append("FROM access_encounter s ");
      sb.append("WHERE 1=1 ");
      sb.append("LIMIT 10");
      
      List<AccessEncounter> results = sessionFactory.getCurrentSession().createSQLQuery(sb.toString())
			.addEntity("s", AccessEncounter.class)
			.list();
      
      
      /**
      List<AccessEncounter> returnList = new ArrayList<AccessEncounter>();
      AccessEncounter ae = new AccessEncounter();
      ae.setAccess_type("VIEW");
      ae.setEncounter_id(1);
      ae.setLocation_id(1);
      ae.setPatient_id(1);
      ae.setTimestamp(new Date());
      ae.setUser_id(1);
      for (int i = 0; i < numOfEncounters; i++) {
         returnList.add(ae);
      }
      /**/
      
      return results;
      
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   }

}
