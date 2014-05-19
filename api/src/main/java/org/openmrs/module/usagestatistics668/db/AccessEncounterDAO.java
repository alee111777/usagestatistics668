
package org.openmrs.module.usagestatistics668.db;

import java.util.Date;
import java.util.List;
import org.openmrs.module.usagestatistics668.AccessEncounter;
import org.openmrs.module.usagestatistics668.ActionCriteria;

/**
 * This is the DAO interface. This is never used by the developer
 * @author Anthony Lee
 */
public interface AccessEncounterDAO {

   /**
    * see AccessEncounterService
    * @param id
    * @return 
    */
    public AccessEncounter getAccessEncounter(Integer id);

    /**
     * see AccessEncounterService
     * @param accessEncounter 
     */
    public void saveAccessEncounter(AccessEncounter accessEncounter);
    
    /**
     * see AccessEncounterService
     * @param since
     * @param until
     * @param filter
     * @param maxResults
     * @return 
     */
    public List<Object[]> getMostViewedEncounter(Date since, Date until, ActionCriteria filter, int maxResults);

    /**
     * see AccessEncounterService
     * @param since
     * @param until
     * @param patientId
     * @param filter
     * @param maxResults
     * @return 
     */
    public List<Object> getDateRangeList(Date since, Date until, Integer patientId, ActionCriteria filter, Integer maxResults);

}
