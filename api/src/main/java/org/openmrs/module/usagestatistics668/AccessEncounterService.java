/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.usagestatistics668;

import java.util.Date;
import java.util.List;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;

/**
 * This is the abstract class which represents the service
 * for getting data from the access encounter table
 * @author Anthony Lee
 */
public interface AccessEncounterService extends OpenmrsService {

   /**
    * get an access encounter by id
    * @param id
    * @return AccessEncounter with id 
    */
    public AccessEncounter getAccessEncounter(Integer id);

    /**
     * save an accessEncounter into the database
     * @param accessEncounter
     * @throws APIException 
     */
    public void saveAccessEncounter(AccessEncounter accessEncounter) throws APIException;

    /**
     * this function will retrieve the most viewed encounters and pair
     * them with then number of accesses that they had
     * @param since
     * @param until
     * @param filter
     * @param maxResults
     * @return List of objects which are the encounter id and the number
     *         of access that they have.
     * @throws APIException 
     */
    public List<Object[]> getMostViewedEncounter(Date since, Date until, ActionCriteria filter, int maxResults) throws APIException;

    /**
     * this function matches up with the search parameters for the
     * table pages. Setting parameters to null will take them out of the
     * filter
     * @param since
     * @param until
     * @param patientId
     * @param filter
     * @param maxResults
     * @return List of AccessEncounters 
     */
    public List<Object> getDateRangeList(Date since, Date until, Integer patientId, ActionCriteria filter, Integer maxResults);

}
