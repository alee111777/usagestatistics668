/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.usagestatistics668;

import java.sql.Date;

/**
 *
 * @author anthony
 */
public class AccessOrder {

   protected Integer id;
   protected Date date;
   protected Integer order_id;
   protected char view_edited;
   protected Integer user_id;
   protected Integer patient_id;
   protected Integer location_id;

   public AccessOrder() {}
   
   public Integer getId(){return this.id;}
   public void setId(Integer id){ this.id = id; }
   
   public Date getDatetime() { return this.date; }
   public void setDatetime( Date datetime) { this.date = datetime; }
   
   public Integer getOrder_id() { return this.order_id; }
   public void setOrder_id(Integer order_id) { this.order_id = order_id; }
   
   public Integer getUser_id() { return this.user_id; }
   public void setUser_id(Integer user_id) { this.user_id = user_id; }
   
   public Integer getPatient_id() { return this.patient_id; }
   public void setPatient_id(Integer patient_id) { this.patient_id = patient_id; }
   
   public Integer getLocation_id() { return this.location_id; }
   public void setLocation_iud(Integer location_id) { this.location_id = location_id; }
   
   public char getView_edited(){ return view_edited;}
   public void setView_edited(char view_edited){this.view_edited = view_edited;}
}
 