    /*
 Auther: Kirill
 */
package org.openmrs.module.usagestatistics668.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.PersonName;
import org.openmrs.User;
import org.openmrs.api.PatientService;
import org.openmrs.api.PersonService;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.usagestatistics668.AccessEncounter;
import org.openmrs.module.usagestatistics668.AccessEncounterService;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping(value = "module/usagestatistics668/encounterAccess.form")
public class EncounterAccessController /*extends AbstractController*/ {

   private String pageViewName;

   /**
    * Logger for this class and subclasses
    */
   protected final Log log = LogFactory.getLog(getClass());

   /**
    * Success form view name
    */
   private final String SUCCESS_FORM_VIEW = "/module/usagestatistics668/encounterAccess";

   @RequestMapping(method = RequestMethod.GET)
   public String showForm(ModelMap model,
           WebRequest request) {
      System.out.println("EncounterAccessController showForm method***************"
              + "\n******calling service for getmostRecent\n");

      AccessEncounterService aeService = Context.getService(AccessEncounterService.class);
      List<AccessEncounter> encounterList = aeService.getMostRecent(10);
      model.addAttribute("encounterList", encounterList);
/**/
      UserService userSvc = (UserService) Context.getUserService();
      String[] userList = new String[encounterList.size()];
      for (int i = 0; i < encounterList.size(); ++i) {
         int userId = encounterList.get(i).getUser_id();
         User user = userSvc.getUser(userId);
         String name = user.getFamilyName();
         userList[i] = name;
      }
      
      model.addAttribute("userList", userList);
/**/
      
      PatientService patientSvc = (PatientService) Context.getPatientService();
      String[] patientList = new String[encounterList.size()];
      for (int i = 0; i < encounterList.size(); ++i) {
         int patientId = encounterList.get(i).getPatient_id();
         Patient patient = patientSvc.getPatient(2);
         PersonName personName = patient.getPersonName();
         String name = personName.getFullName();
         patientList[i] = name;
      }

      model.addAttribute("patientList", patientList);
      model.addAttribute("numRows", encounterList.size());

      return SUCCESS_FORM_VIEW;
   }

   /**
    * All the parameters are optional based on the necessity
    *
    * @param httpSession
    * @param anyRequestObject
    * @param errors
    * @return
    */
   @RequestMapping(method = RequestMethod.POST)
   public String onSubmit(HttpSession httpSession,
           @ModelAttribute("anyRequestObject") Object anyRequestObject, BindingResult errors) {
      System.out.println("POST method***************");
      if (errors.hasErrors()) {
         // return error view
      }

      return SUCCESS_FORM_VIEW;
   }

   /**
    * Sets the name of the page view
    *
    * @param pageViewName the page view name
    */
   @Required
   public void setPageViewName(String pageViewName) {
      this.pageViewName = pageViewName;
   }

}
