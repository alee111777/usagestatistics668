package org.openmrs.module.usagestatistics668.impl;

import java.util.List;

import org.openmrs.Patient;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.usagestatistics668.AccessEncounter;
import org.openmrs.module.usagestatistics668.AccessEncounterService;
import org.openmrs.module.usagestatistics668.Note;
import org.openmrs.module.usagestatistics668.NoteService;
import org.openmrs.module.usagestatistics668.db.AccessEncounterDAO;
import org.openmrs.module.usagestatistics668.db.NoteDAO;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of our {@link NoteService} interface. This class is set in the
 * /metadata/moduleApplicationContext.xml file to be matched to the
 * {@link NoteService} interface. <br/>
 * <br/>
 * This class extends {@link BaseOpenmrsService} so that there are empty methods
 * for onStartup and onShutdown. This allows sheilds us from changes to the
 * OpenmrsService interface from forcing us to implement the methods. <br/>
 * <br/>
 * NEVER call "new NoteServiceImpl()....". To use this class, you do: <br/>
 * <code>
 *
 *   Context.getService(NoteService.class).saveNote(note)...
 *
 * </code>
 *
 * @author Anthony Lee
 */
public class AccessEncounterServiceImpl extends BaseOpenmrsService implements AccessEncounterService {

   /**
    * This "dao" object is set by spring. See the
    * /metadata/moduleApplicationContext.xml for what value gets set here. We
    * can assume that this will never be null, even though it never gets set in
    * here. This is called Inversion of Control (IoC)
    */
   private AccessEncounterDAO dao;

   /**
    * This is the method that spring calls to set the DAO
    *
    * @param dao the dao to set
    */
   public void setDao(AccessEncounterDAO dao) {
      this.dao = dao;
   }

   /**
    * @param id
    * @return
    * @see
    * org.openmrs.module.patientnotes.NoteService#getNote(java.lang.Integer)
    */
   @Transactional(readOnly = true)
   public AccessEncounter getAccessEncounter(Integer id) {
      return dao.getAccessEncounter(id);
   }

   public void saveAccessEncounter(AccessEncounter accessEncounter) throws APIException {
      dao.saveAccessEncounter(accessEncounter);
   }

   public List<AccessEncounter> getMostRecent(int numOfEncounters) {
      return dao.getMostRecent(numOfEncounters);
   }

}
