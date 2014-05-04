/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openmrs.module.usagestatistics668.db;

import java.util.List;
import org.openmrs.module.usagestatistics668.AccessEncounter;

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

}
