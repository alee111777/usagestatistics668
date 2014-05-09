    /*
 Auther: Kirill
 */
package org.openmrs.module.usagestatistics668.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.usagestatistics668.AccessPatient;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping(value = "module/usagestatistics668/accessPatient.form")
public class AccessPatientController /*extends AbstractController*/ {

   private String pageViewName;

   /**
    * Logger for this class and subclasses
    */
   protected final Log log = LogFactory.getLog(getClass());

   /**
    * Success form view name
    */
   private final String SUCCESS_FORM_VIEW = "/module/usagestatistics668/accessPatient";
   

   @RequestMapping(method = RequestMethod.GET)
   public String showForm(ModelMap model,
           WebRequest request) {
      System.out.println("PatientAccessController showForm method***************"
              + "\n******calling service for getmostRecent\n");

      //AccessPatientService aeService = Context.getService(AccessPatientService.class);
      //List<AccessPatient> encounterList = aeService.getMostRecent(10);
      
      List<AccessPatient> patientList = new ArrayList<AccessPatient>();
      AccessPatient ap = new AccessPatient();
      ap.setAccess_type("viewed");
      ap.setPatient_id(1);
      ap.setId(1);
      ap.setPatient_id(1);
      ap.setTimestamp(new Date());
      ap.setUser_id(1);
      
      for (int i = 0; i < 10; i++) {
         patientList.add(ap);
      }
      
      model.addAttribute("patientList", patientList);


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
