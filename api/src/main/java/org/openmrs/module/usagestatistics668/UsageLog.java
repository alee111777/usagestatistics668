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
 * This class sets the action type of data access, call the service and saves the data access
 * to the database.
 * @author: Ye Cheng
 */
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.Visit;
import org.openmrs.api.context.Context;

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
	 * Logs a usage event for patient data
	 * @param patient the patient
	 * @param type the type of usage event
	 * @param query the search query used to find this patient
	 */
	public static void logEvent(Patient patient, Type type, String query) {
		User user = Context.getAuthenticatedUser();

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
	}
	
	/**
	 * Logs a usage event
	 * @param encounter the encounter
	 * @param type the type of usage event
	 * @param query the search query used to find this encounter
	 */
	public static void logEvent(Encounter encounter, Type type, String query) {
		User user = Context.getAuthenticatedUser();
		
                AccessEncounterService svc = (AccessEncounterService)Context.getService(AccessEncounterService.class);
		AccessEncounter ae = new AccessEncounter();
		
		// Set created / updated / voided flags
		if (type == Type.CREATED){
			ae.setAccess_type("created");
                }
		else if (type == Type.UPDATED) {
			ae.setAccess_type("updated");
                }
                else if (type == Type.VOIDED){
			ae.setAccess_type("voided");
                }
                else if (type == Type.VIEWED){
			ae.setAccess_type("viewed");
                }

		// Update the time of the recent event
		ae.setTimestamp(new Date());
                ae.setUser_id(user.getUserId());
                ae.setEncounter_id(encounter.getEncounterId());
                ae.setPatient_id(encounter.getPatient().getPatientId());
                if (encounter.getLocation()!= null){
                    ae.setLocation_id(encounter.getLocation().getLocationId());
                }
                else ae.setLocation_id(null);
	        svc.saveAccessEncounter(ae);
	}
	
	/**
	 * Logs a usage event
	 * @param visit the visit
	 * @param type the type of usage event
	 * @param query the search query used to find this visit
	 */
	public static void logEvent(Visit visit, Type type, String query) {
		User user = Context.getAuthenticatedUser();

                AccessVisitService svc = (AccessVisitService)Context.getService(AccessVisitService.class);
		AccessVisit ap = new AccessVisit();
		
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
                ap.setVisit_id(visit.getVisitId());
                ap.setLocation_id(visit.getLocation().getLocationId());
                ap.setPatient_id(visit.getPatient().getPatientId());
	        svc.saveAccessVisit(ap);

	}
}

