/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.usagestatistics668;

import java.util.Date;
import java.util.List;
import org.openmrs.Location;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.api.db.DAOException;

/**
 *
 * @author Anthony Lee
 */
public interface AccessEncounterService extends OpenmrsService {

    public AccessEncounter getAccessEncounter(Integer id);

    public void saveAccessEncounter(AccessEncounter accessEncounter) throws APIException;

    public List<AccessEncounter> getMostRecent(int numOfEncounters);

    public List<Object[]> getMostViewedEncounter(Date since, Date until, ActionCriteria filter, int maxResults) throws APIException;

    public List<Object[]> getDateRangeStats(Date from, Date until, Location location) throws DAOException;

}
