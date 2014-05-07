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
 * @author Jonathan
 */
public interface AccessVisitService extends OpenmrsService{
    
    public AccessVisit getAccessVisit(Integer id);
    
    public void saveAccessVisit(AccessVisit accessVisit)throws APIException;   
    
    public List<Object[]> getMostViewedVisit(Date since, Date until, ActionCriteria filter,int maxResults) throws APIException; 
}
