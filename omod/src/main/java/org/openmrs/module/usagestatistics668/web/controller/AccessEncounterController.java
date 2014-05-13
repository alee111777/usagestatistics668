    /*
 Auther: Kirill
 */
package org.openmrs.module.usagestatistics668.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.usagestatistics668.AccessEncounter;
import org.openmrs.module.usagestatistics668.AccessEncounterService;
import org.openmrs.module.usagestatistics668.AccessPatientService;
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
      System.out.println("EncounterAccessController showForm method***************\n");

      AccessEncounterService aeService = Context.getService(AccessEncounterService.class);
      List<Object> encounterList = aeService.getDateRangeList(null, null, null, ActionCriteria.ANY, 20);

      model.addAttribute("encounterList", encounterList);
      return SUCCESS_FORM_VIEW;
   }

   /**
    * 
    * @param model
    * @param httpSession
    * @param anyRequestObject
    * @param errors
    * @param from
    * @param until
    * @param encounterId
    * @param usageFilter
    * @param quantityFilter
    * @return
    * @throws ParseException 
    */
   @RequestMapping(method = RequestMethod.POST)
   public String onSubmit(ModelMap model, HttpSession httpSession,
	                               @ModelAttribute("anyRequestObject") Object anyRequestObject, BindingResult errors, 
                                       @RequestParam("from") String from,
                                       @RequestParam("until") String until,
                                       @RequestParam("encounterId") String encounterId,
                                       @RequestParam("usageFilter") String usageFilter,
                                       @RequestParam("quantityFilter") String quantityFilter) throws ParseException{
     System.out.println("POST method***************");
      if (errors.hasErrors()) {
         // return error view
      }
      //String patientId = "2";
      /*
       try {
       DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
       this.from = df.parse(from);
       this.until = df.parse(until);
       } catch (ParseException e) {
       e.printStackTrace();
       }
       */
      //this.from = (Date)from;
      //this.until = (Date)until;
      Integer encounter_id = null;
      if (!"".equals(encounterId)) {
         encounter_id = Integer.parseInt(encounterId);
      }

      SimpleDateFormat newDate = new SimpleDateFormat("dd/MM/yyyy");
      Date since = null;
      if (!"".equals(from)) {
         since = newDate.parse(from);
      }

      Date til = null;
      if (!"".equals(until)) {
         til = newDate.parse(until);
      }

      ActionCriteria criteria;

      criteria = ActionCriteria.values()[Integer.parseInt(usageFilter)];

      Integer numRows = null;
      if (!"".equals(quantityFilter)) {
         numRows = Integer.parseInt(quantityFilter);
      }

      AccessEncounterService aeService = Context.getService(AccessEncounterService.class);
      List<Object> patientList = aeService.getDateRangeList(since, til, encounter_id, criteria, numRows);
      model.addAttribute("patientList", patientList);
      
      
      //String string = "January 2, 2010";
      //Date date = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(string);
      //System.out.println(date); // Sat Jan 02 00:00:00 BOT 2010
      System.out.println("FROM: " + since);
      System.out.println("UNTIL: " + til);
      System.out.println("ENCOUNTERID: " + encounter_id);
      System.out.println("USAGEFILTER: " + criteria);
      System.out.println("QUANTITYFILTER: " + numRows);
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
