/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.usagestatistics668.db;

import java.util.Date;
import java.util.List;
import org.openmrs.Location;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.usagestatistics668.AccessEncounter;
import org.openmrs.module.usagestatistics668.ActionCriteria;

/**
 *
 * @author Anthony Lee
 */
/**
 * This is the DAO interface. This is never used by the developer, but instead
 * the {@link NoteService} calls it (and developers call the NoteService)
 */
public interface AccessEncounterDAO {

    public AccessEncounter getAccessEncounter(Integer id);

    public void saveAccessEncounter(AccessEncounter accessEncounter);

    public List<AccessEncounter> getMostRecent(int numOfEncounters);

    public List<Object[]> getMostViewedEncounter(Date since, Date until, ActionCriteria filter, int maxResults);

    public List<Object> getDateRangeList(Date since, Date until, Integer patientId, ActionCriteria filter, Integer maxResults);

}
