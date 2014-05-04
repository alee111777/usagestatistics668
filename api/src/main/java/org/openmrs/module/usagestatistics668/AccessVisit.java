/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openmrs.module.usagestatistics668;

import java.util.Date;

/**
 *
 * @author jonathan
 */
public class AccessVisit {
    
    protected Integer id;
    protected Date timestamp;
    protected String access_type;
    protected Integer visit_id;
    protected Integer user_id;
    protected Integer patient_id;
    protected Integer location_id;
    
    //Default constructor
    public AccessVisit(){    }

    public Integer getId(){return id;}
    
    public void setId(Integer id){this.id = id;}
    
    public Date getTimestamp(){return timestamp;}
    
    public void setTimestamp(Date timestamp){this.timestamp = timestamp;}
    
    public String getAccess_type(){return access_type;}
    
    public void setAccess_type(String access_type){this.access_type = access_type;}
    
    public Integer getVisit_id(){return visit_id;}
    
    public void setVisit_id(Integer visit_id){this.visit_id = visit_id;}
    
    public Integer getUser_id(){return user_id;}
    
    public void setUser_id(Integer user_id){this.user_id = user_id;}
    
    public Integer getPatient_id(){return patient_id;}
    
    public void setPatient_id(Integer patient_id){this.patient_id = patient_id;}
    
    public Integer getLocation_id(){return location_id;}
    
    public void setLocation_id(Integer location_id){this.location_id = location_id;}
    
}
