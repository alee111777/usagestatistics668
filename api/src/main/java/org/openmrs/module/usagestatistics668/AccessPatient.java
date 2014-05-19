/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openmrs.module.usagestatistics668;

import java.util.Date;

/**
 * This is the POJO class for access_patient table
 * @author Ye Cheng
 */
public class AccessPatient{
    
    protected Integer id;
    protected Date timestamp;
    protected String access_type;
    protected Integer user_id;
    protected Integer patient_id;

    public AccessPatient(){}
    
   public Integer getId() { return this.id; }
   public void setId(Integer id) { this.id = id; }
   
   public Date getTimestamp() { return this.timestamp; }
   public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
   
   public Integer getPatient_id() { return this.patient_id; }
   public void setPatient_id(Integer patient_id) { this.patient_id = patient_id; }
   
   public String getAccess_type() { return this.access_type; }
   public void setAccess_type(String type) { this.access_type = type; }
   
   public Integer getUser_id() { return this.user_id; }
   public void setUser_id(Integer user_id) { this.user_id = user_id; }
}

