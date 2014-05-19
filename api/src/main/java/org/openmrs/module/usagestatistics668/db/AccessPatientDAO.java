/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openmrs.module.usagestatistics668.db;

import java.util.Date;
import java.util.List;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.usagestatistics668.AccessPatient;
import org.openmrs.module.usagestatistics668.ActionCriteria;

/**
 * This is the DAO interface. This is never used by the developer, but instead
 * the {@link NoteService} calls it (and developers call the NoteService)
 * @author Ye Cheng
 */

public interface AccessPatientDAO {

   /**
    * get AccessPatient object
    * see AccessPatientService
    * @param id
    * @return AccessPatient object
    */
    public AccessPatient getAccessPatient(Integer id);

    /**
     * save AccessPatient object
     * see AccessPatientService
     * @param accessPatient the AccessPatient object to save
     */
    public void saveAccessPatient(AccessPatient accessPatient)throws DAOException;

    /**
     * get the most viewed patient data
     * see AccessPatientService
     * @param since
     * @param until
     * @param filter
     * @param maxResults
     * @return 
     */
    public List<Object[]> getMostViewedPatient(Date since, Date until, ActionCriteria filter,int maxResults);
    
    /**
     * get patient data being accessed during a period of time
     * see AccessPatientService
     * @param since
     * @param until
     * @param patientId
     * @param filter
     * @param maxResults
     * @return 
     */
    public List<Object> getDateRangeList(Date since, Date until, Integer patientId, ActionCriteria filter, Integer maxResults);


}

