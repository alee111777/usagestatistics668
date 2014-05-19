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
 * for getting data from the access_patient table
 * @author Ye Cheng
 */
public interface AccessPatientService extends OpenmrsService{
    
    /**
    * get an access patient by id
    * @param id patient id
    * @return AccessPatient with id 
    */
    public AccessPatient getAccessPatient(Integer id);
    
    /**
     * save an access patient by id
     * @param accessPatient AccessPatient object
     * @throws APIException 
     */
    public void saveAccessPatient(AccessPatient accessPatient)throws APIException;   
    
    /**
     * this function will retrieve the most viewed patients and pair
     * them with then number of accesses that they had
     * @param since starting time of accessing data
     * @param until ending time of accessing data
     * @param filter action type of accessing data
     * @param maxResults the number of records it returns
     * @return List of objects which are the patient id and the number
     *         of access that they have.
     * @throws APIException 
     */
    public List<Object[]> getMostViewedPatient(Date since, Date until, ActionCriteria filter,int maxResults) throws APIException;
    
    /**
     * this function matches up with the search parameters for the
     * table pages. Setting parameters to null will take them out of the
     * filter
     * @param since starting time of accessing data
     * @param until ending time of accessing data
     * @param patientId id of the patient
     * @param filter action type of accessing data
     * @param maxResults number of records it returns
     * @return List of AccessPatient
     */
    public List<Object> getDateRangeList(Date since, Date until, Integer patientId, ActionCriteria filter, Integer maxResults);
}
