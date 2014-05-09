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
import org.openmrs.module.usagestatistics668.AccessVisit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
   public String onSubmit(HttpSession httpSession,
           @ModelAttribute("anyRequestObject") Object anyRequestObject, BindingResult errors) {
      System.out.println("POST method***************");
      if (errors.hasErrors()) {
         // return error view
      }

      return SUCCESS_FORM_VIEW;
   }

}
