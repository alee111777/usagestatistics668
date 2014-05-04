/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.openmrs.module.usagestatistics668.advice;

/**
 *
 * @author Ye
 */

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.usagestatistics668.AccessVisit;
import org.openmrs.module.usagestatistics668.AccessVisitService;
import org.springframework.aop.MethodBeforeAdvice;


/*
Refer to https://wiki.openmrs.org/display/docs/OpenMRS+AOP
*/
public class CountingBeforeAdvice implements MethodBeforeAdvice {

    private Log log = LogFactory.getLog(this.getClass());

    private int count = 0;


    public void before(Method method, Object[] os, Object o) throws Throwable {
        
        AccessVisitService svc = (AccessVisitService)Context.getService(AccessVisitService.class);
	AccessVisit av = new AccessVisit();
//        av.setAccess_type("viewed");
//        av.setLocation_id(1);
//        av.setPatient_id(1);
//        av.setTimestamp(new Date());
//        av.setUser_id(1);
//        av.setVisit_id(1);
//        svc.saveAccessVisit(av);
//        System.out.println("@@@@@@@@@@@@@@Saved the stuff@@@@@@@@@@@@@@@@");
//        System.out.println("@@@@@@@@@@@@@@Herro, I accessed " + svc.getAccessVisit(1).getAccess_type() + "@@@@@@@@@@@@@@@@@");

        log.debug("Method: " + method.getName() + ". Before advice called " + (++count) + " time(s) now.");
        String userName = Context.getUserContext().getAuthenticatedUser().getFamilyName();
        System.out.println("AOP - by: " + userName + "  Method: " + method.getName() + ". Before advice called " + (++count) + " time(s) now.");
        if (method.getName() != null) {
            if (method.getName().equals("createPerson")) {
                System.out.println(" Person created");
            }
        }
    }

}
