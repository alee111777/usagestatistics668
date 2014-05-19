/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.usagestatistics668.db;

import java.util.Date;
import java.util.List;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.usagestatistics668.AccessVisit;
import org.openmrs.module.usagestatistics668.ActionCriteria;

/**
 * This is the DAO interface. This is never used by the developer, but instead
 * the {@link NoteService} calls it (and developers call the NoteService)
 * @author Jonathan
 */
public interface AccessVisitDAO {

    /**
     * see AccessVisitService
     * @param id
     * @return 
     */
    public AccessVisit getAccessVisit(Integer id);

    /**
     * see AccessVisitService
     * @param accessVisit
     * @throws DAOException 
     */
    public void saveAccessVisit(AccessVisit accessVisit) throws DAOException;

    /**
     * see AccessVisitService
     * @param numOfVisits
     * @return 
     */
    public List<AccessVisit> getMostRecent(int numOfVisits);

    /**
     * see AccessVisitService
     * @param since
     * @param until
     * @param filter
     * @param maxResults
     * @return 
     */
    public List<Object[]> getMostViewedVisit(Date since, Date until, ActionCriteria filter, int maxResults);

    /**
     * see AccessVisitService
     * @param since
     * @param until
     * @param patientId
     * @param filter
     * @param maxResults
     * @return 
     */
    public List<Object> getDateRangeList(Date since, Date until, Integer patientId, ActionCriteria filter, Integer maxResults);

}
