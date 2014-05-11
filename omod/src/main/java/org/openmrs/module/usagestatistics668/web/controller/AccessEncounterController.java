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
import org.openmrs.module.usagestatistics668.AccessEncounter;
import org.openmrs.module.usagestatistics668.ActionCriteria;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping(value = "module/usagestatistics668/accessEncounter.form")
public class AccessEncounterController /*extends AbstractController*/ {

   private String pageViewName;
   private Date from;
   private Date until;
   private int encounterId;
   private ActionCriteria usageFilter;
   private int quantityFilter;
   /**
    * Logger for this class and subclasses
    */
   protected final Log log = LogFactory.getLog(getClass());

   /**
    * Success form view name
    */
   private final String SUCCESS_FORM_VIEW = "/module/usagestatistics668/accessEncounter";
   

   @RequestMapping(method = RequestMethod.GET)
   public String showForm(ModelMap model,
           WebRequest request) {
      System.out.println("EncounterAccessController showForm method***************"
              + "\n******calling service for getmostRecent\n");

      //AccessEncounterService aeService = Context.getService(AccessEncounterService.class);
      //List<AccessEncounter> encounterList = aeService.getMostRecent(10);
      
      List<AccessEncounter> encounterList = new ArrayList<AccessEncounter>();
      AccessEncounter ae = new AccessEncounter();
      ae.setAccess_type("viewed");
      ae.setEncounter_id(1);
      ae.setId(1);
      ae.setLocation_id(1);
      ae.setPatient_id(1);
      ae.setTimestamp(new Date());
      ae.setUser_id(1);
      
      for (int i = 0; i < 10; i++) {
         encounterList.add(ae);
      }
      
      model.addAttribute("encounterList", encounterList);


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
   public String onSubmit(ModelMap model, HttpSession httpSession,
	                               @ModelAttribute("anyRequestObject") Object anyRequestObject, BindingResult errors, 
                                       @RequestParam("from") String from,
                                       @RequestParam("until") String until,
                                       @RequestParam("encounterId") String encounterId,
                                       @RequestParam("usageFilter") String usageFilter,
                                       @RequestParam("quantityFilter") String quantityFilter){
      System.out.println("POST method***************");
      if (errors.hasErrors()) {
         // return error view
      }
      
        //this.from = (Date)from;
        //this.until = (Date)until;
        if (encounterId != "")
            this.encounterId = Integer.parseInt(encounterId);
        //catch ActionCriteria.values()?
        this.usageFilter = ActionCriteria.values()[Integer.parseInt(usageFilter)];
        this.quantityFilter = Integer.parseInt(quantityFilter);
      
      System.out.println("FROM: " + from);
      System.out.println("UNTIL: " + until);
      System.out.println("ENCOUNTERID: " + this.encounterId);
      System.out.println("USAGEFILTER: " +  this.usageFilter);
      System.out.println("QUANTITYFILTER: " + this.quantityFilter);
        
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
