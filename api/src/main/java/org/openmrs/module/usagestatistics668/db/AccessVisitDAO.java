/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openmrs.module.usagestatistics668.db;

import java.util.List;
import org.openmrs.Patient;
import org.openmrs.api.db.DAOException;
//import org.openmrs.module.usagestatistics668.AccessOrder;
import org.openmrs.module.usagestatistics668.AccessVisit;

/**
 *
 * @author Ye
 */
/**
 * This is the DAO interface. This is never used by the developer, but instead
 * the {@link NoteService} calls it (and developers call the NoteService)
 */
public interface AccessVisitDAO {

   public AccessVisit getAccessVisit(Integer id);
   
   public void saveAccessVisit(AccessVisit accessVisit)throws DAOException;


}

