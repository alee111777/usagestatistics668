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
 * for getting data from the access_visit table
 * @author Jonathan
 */
public interface AccessVisitService extends OpenmrsService {

    /**
     * Get patient by id
     * @param id
     * @return 
     */
    public AccessVisit getAccessVisit(Integer id);

    /**
     * Saves the visit that was just accessed
     * @param accessVisit
     * @throws APIException 
     */
    public void saveAccessVisit(AccessVisit accessVisit) throws APIException;

    /**
     * Gets the most recent visit, numOfVisits the the amount of results you want.
     * @param numOfVisits
     * @return 
     */
    public List<AccessVisit> getMostRecent(int numOfVisits);
    
    /**
     * Gets the most viewed visits with the parameters given, if the parameter is
     * set to null they will not be included.  maxResults is how many results you
     * want back.
     * @param since
     * @param until
     * @param filter
     * @param maxResults
     * @return
     * @throws APIException 
     */
    public List<Object[]> getMostViewedVisit(Date since, Date until, ActionCriteria filter, int maxResults) throws APIException;

    /**
     * Gets the results for the tables in the statistics page with the given parameters,
     * if the parameter is null it will not be included in the search.  maxResults
     * is the number of results you want back from the search.
     * @param since
     * @param until
     * @param patientId
     * @param filter
     * @param maxResults
     * @return 
     */
    public List<Object> getDateRangeList(Date since, Date until, Integer patientId, ActionCriteria filter, Integer maxResults);
}
