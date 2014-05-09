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
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.usagestatistics668.AccessEncounter;
import org.openmrs.module.usagestatistics668.AccessEncounterService;
import org.openmrs.module.usagestatistics668.AccessPatient;
import org.openmrs.module.usagestatistics668.AccessPatientService;
import org.openmrs.module.usagestatistics668.AccessVisit;
import org.openmrs.module.usagestatistics668.AccessVisitService;
import org.openmrs.module.usagestatistics668.ActionCriteria;
import org.springframework.aop.MethodBeforeAdvice;


/*
Refer to https://wiki.openmrs.org/display/docs/OpenMRS+AOP
*/
public class CountingBeforeAdvice implements MethodBeforeAdvice {

    private Log log = LogFactory.getLog(this.getClass());

    private int count = 0;


    public void before(Method method, Object[] os, Object o) throws Throwable {
        
//        AccessPatientService svc = (AccessPatientService)Context.getService(AccessPatientService.class);
//	AccessPatient av = new AccessPatient();
//        List<Object[]> something = svc.getDateRangeList(null, null, ActionCriteria.ANY, 4);
//        
//        System.out.println("@@@@@@@@@@@@OVER HERE@@@@@@");
//        for(int i = 0; i < something.size(); i++){
//            System.out.println("In the loops");
//            System.out.println(something.get(i)[0]);
//        }
//        System.out.println("@@@@@@@@@@@@END HERE@@@@@@");
        
        
        

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
