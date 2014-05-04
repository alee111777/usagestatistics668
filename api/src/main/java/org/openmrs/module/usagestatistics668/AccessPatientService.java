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
 *
 * @author Ye
 */
public interface AccessPatientService extends OpenmrsService{
    
    public AccessPatient getAccessPatient(Integer id);
    
    public void saveAccessPatient(AccessPatient accessPatient)throws APIException;   
    
    public List<Object[]> getMostViewedPatient(Date since, int maxResults) throws APIException;
}
