/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.usagestatistics668;

/**
 * author: Ye
 */
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.context.Context;
import org.openmrs.module.usagestatistics668.util.StatsUtils;
import org.openmrs.util.OpenmrsConstants;

/**
 * Class to handle logging of patient record usage
 */
public class UsageLog {
	
	protected static final Log log = LogFactory.getLog(UsageLog.class);

	/**
	 * The type of a usage event
	 */
	public enum Type {
		VIEWED,
		CREATED,
		UPDATED,
		VOIDED
	}
	
	/**
	 * Logs a usage event
	 * @param patient the patient
	 * @param type the type of usage event
	 * @param query the search query used to find this patient
	 */
	public static void logEvent(Patient patient, Type type, String query) {
		User user = Context.getAuthenticatedUser();
		
		//logEvent(user, patient, null, type, query);
                AccessPatientService svc = (AccessPatientService)Context.getService(AccessPatientService.class);
		AccessPatient ap = new AccessPatient();
		
		// Set created / updated / voided flags
		if (type == Type.CREATED){
			ap.setAccess_type("created");
                }
		else if (type == Type.UPDATED) {
			ap.setAccess_type("updated");
                }
                else if (type == Type.VOIDED){
			ap.setAccess_type("voided");
                }
                else if (type == Type.VIEWED){
			ap.setAccess_type("viewed");
                }

		// Update the time of the recent event
		ap.setTimestamp(new Date());
                ap.setUser_id(user.getUserId());
                ap.setPatient_id(patient.getPersonId());
	        svc.saveAccessPatient(ap);
                System.out.println("---------------access patient data saved------------");
                //testing
                Date monthAgo = StatsUtils.addDaysToDate(null, -30);
                int patient_id = (Integer)svc.getMostViewedPatient(monthAgo, 2).get(0)[0];
                System.out.println(patient_id);
                //end of testing
	}
	
	/**
	 * Logs a usage event
	 * @param encounter the encounter
	 */
	public static void logEvent(Encounter encounter, Type type) {
		// Use encounter creator as infopath forms are submitted by super user
		User user = encounter.getCreator();
		Patient patient = encounter.getPatient();
		
		//logEvent(user, patient, encounter, type, null);
	}
	
	/**
	 * Logs a usage event
	 * @param user the user
	 * @param patient the patient
	 * @param encounter the encounter
	 * @param type the type of usage event
	 * @param query the search query used to find this patient
	 */
	/*private static void logEvent(User user, Patient patient, Encounter encounter, Type type, String query) {
		if (patient == null) {
			log.warn("Attempt to log usage on null patient");
			return;
		}
		
		//Options config = Options.getInstance();
		
		// Optionally ignore system developers
		//if (user != null && user.hasRole(OpenmrsConstants.SUPERUSER_ROLE) && config.isIgnoreSystemDevelopers())
		//	return;
		
		AccessPatientService svc = (AccessPatientService)Context.getService(AccessPatientService.class);
		AccessPatient ap = new AccessPatient();

		// Update the time of the recent event
		ap.setDate(new Date());

		if (encounter != null) {
			ap.setEncounter_id(encounter.getEncounterId());
                        ap.setLocation_id(encounter.getLocation().getLocationId());
		}
                else {
                    ap.setEncounter_id(-999);
                    ap.setLocation_id(-999);
                }
		
		// Set created / updated / voided flags
		if (type == Type.CREATED){
			ap.setViewed_edited('c');
                }
		else if (type == Type.UPDATED) {
			ap.setViewed_edited('u');
                }
                else if (type == Type.VOIDED){
			ap.setViewed_edited('d');
                }
		
               
              //ap.setEncounter_id(0);
              //ap.setLocation_id(0);
              ap.setUser_id(user.getUserId());
              ap.setPatient_id(patient.getPersonId());
	      svc.saveAccessPatient(ap);
	}*/
}
