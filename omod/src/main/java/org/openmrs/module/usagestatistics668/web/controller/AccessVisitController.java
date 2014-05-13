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
import org.openmrs.module.usagestatistics668.AccessPatientService;
import org.openmrs.module.usagestatistics668.AccessVisit;
import org.openmrs.module.usagestatistics668.AccessVisitService;
import org.openmrs.module.usagestatistics668.ActionCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping(value = "module/usagestatistics668/accessVisit.form")
public class AccessVisitController {

   /**
    * Logger for this class and subclasses
    */
   protected final Log log = LogFactory.getLog(getClass());

   /**
    * Success form view name
    */
   private final String SUCCESS_FORM_VIEW = "/module/usagestatistics668/accessVisit";

   private String pageViewName;
   private Date from;
   private Date until;
   private int visitId;
   private ActionCriteria usageFilter;
   private int quantityFilter;

   public void setPageViewName(String pageViewName) {
      this.pageViewName = pageViewName;
   }

   public String getPageViewName() {
      return pageViewName;
   }

   @RequestMapping(method = RequestMethod.GET)
   public String showForm(ModelMap model,
           WebRequest request) {
      System.out.println("VisitAccessController showForm method***************"
              + "\n******calling service for getmostRecent\n");

      //AccessVisitService aeService = Context.getService(AccessVisitService.class);
      //List<AccessVisit> encounterList = Service.getMostRecent(10);
      List<AccessVisit> visitList = new ArrayList<AccessVisit>();
      AccessVisit av = new AccessVisit();
      av.setAccess_type("viewed");
      av.setVisit_id(1);
      av.setId(1);
      av.setLocation_id(1);
      av.setPatient_id(1);
      av.setTimestamp(new Date());
      av.setUser_id(1);

      for (int i = 0; i < 10; i++) {
         visitList.add(av);
      }

      model.addAttribute("visitList", visitList);

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
                                       @RequestParam("visitId") String visitId,
                                       @RequestParam("usageFilter") String usageFilter,
                                       @RequestParam("quantityFilter") String quantityFilter) throws ParseException{
      System.out.println("POST method***************");
      if (errors.hasErrors()) {
         // return error view
      }

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
      Integer patient_id = null;
      if (!"".equals(visitId)) {
         patient_id = Integer.parseInt(visitId);
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

      AccessVisitService avService = Context.getService(AccessVisitService.class);
      List<Object> visitList = avService.getDateRangeList(since, til, patient_id, criteria, numRows);
      model.addAttribute("visitList", visitList);
      
      
      //String string = "January 2, 2010";
      //Date date = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(string);
      //System.out.println(date); // Sat Jan 02 00:00:00 BOT 2010
      System.out.println("FROM: " + since);
      System.out.println("UNTIL: " + til);
      System.out.println("PATIENTID: " + patient_id);
      System.out.println("USAGEFILTER: " + criteria);
      System.out.println("QUANTITYFILTER: " + numRows);
      return SUCCESS_FORM_VIEW;
   }

}
