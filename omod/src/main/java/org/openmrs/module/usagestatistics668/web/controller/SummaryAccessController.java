    /*
Author: Michael
 */
package org.openmrs.module.usagestatistics668.web.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "module/usagestatistics668/summaryAccess.form")
public class SummaryAccessController {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	/** Success form view name */
	private final String SUCCESS_FORM_VIEW = "/module/usagestatistics668/summaryAccess";  
        
	@RequestMapping(method = RequestMethod.GET)
	public String showForm(){
            System.out.println("SummaryAccessController showForm method***************");
            
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
