    /*
 Auther: Kirill
 */
package org.openmrs.module.usagestatistics668.web.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
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
@RequestMapping(value = "module/usagestatistics668/accessPatient.form")
public class AccessPatientController /*extends AbstractController*/ {

    private String pageViewName;
    private Date from;
    private Date until;
    private int patientId = 2;
    private ActionCriteria usageFilter;
    private int quantityFilter;
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

        AccessPatientService aeService = Context.getService(AccessPatientService.class);
        List<Object> patientList = aeService.getDateRangeList(null, null, 2, ActionCriteria.ANY, 10);
                 
                 
//      List<AccessPatient> patientList = new ArrayList<AccessPatient>();
//      AccessPatient ap = new AccessPatient();
//      ap.setAccess_type("viewed");
//      ap.setPatient_id(1);
//      ap.setId(1);
//      ap.setPatient_id(1);
//      ap.setTimestamp(new Date());
//      ap.setUser_id(1);
//      
//      for (int i = 0; i < 10; i++) {
//         patientList.add(ap);
//      }
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
    public String onSubmit(ModelMap model, HttpSession httpSession,
	                               @ModelAttribute("anyRequestObject") Object anyRequestObject, BindingResult errors, 
                                       @RequestParam("from") String from,
                                       @RequestParam("until") String until,
                                       @RequestParam("patientId") String patientId,
                                       @RequestParam("usageFilter") String usageFilter,
                                       @RequestParam("quantityFilter") String quantityFilter){
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
        if (patientId != "")
            this.patientId = Integer.parseInt(patientId);
        //catch ActionCriteria.values()?
        this.usageFilter = ActionCriteria.values()[Integer.parseInt(usageFilter)];
        this.quantityFilter = Integer.parseInt(quantityFilter);
             
        AccessPatientService aeService = Context.getService(AccessPatientService.class);
        List<Object> patientList = aeService.getDateRangeList(null, null, this.patientId, this.usageFilter, this.quantityFilter);
        model.addAttribute("patientList", patientList);
       
        //String string = "January 2, 2010";
        //Date date = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(string);
        //System.out.println(date); // Sat Jan 02 00:00:00 BOT 2010
        
        System.out.println("FROM: " + from);
        System.out.println("UNTIL: " + until);
        System.out.println("PATIENTID: " + this.patientId);
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
