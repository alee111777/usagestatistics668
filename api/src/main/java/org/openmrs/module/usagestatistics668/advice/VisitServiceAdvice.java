/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.usagestatistics668.advice;

import java.lang.reflect.Method;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Visit;
import org.openmrs.api.context.Context;
import org.openmrs.module.usagestatistics668.AccessVisitService;
import org.openmrs.module.usagestatistics668.UsageLog;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

/**
 *
 * @author Jonathan
 */
public class VisitServiceAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

    private Log log = LogFactory.getLog(this.getClass());

    private int count = 0;

    protected UsageLog.Type usageType;

    public void before(Method method, Object[] args, Object target) throws Throwable {
        
        System.out.println("@@@@@@@@@@@#####after aop, method name: " + method.getName());

        if (method.getName().equals("saveVisit") || method.getName().equals("updateVisit")) {
            Visit visit = (Visit) args[0];
            usageType = UsageLog.Type.UPDATED;
            if (visit.getVisitId() == null) {
                usageType = UsageLog.Type.CREATED;
            } else if (visit.isVoided()) {
                AccessVisitService svc = (AccessVisitService) Context.getService(AccessVisitService.class);
				// Patient object is voided, but check database record
                //if (!svc.isPatientVoidedInDatabase(patient))
                //usageType = UsageLog.Type.VOIDED;
            }
        } else if (method.getName().equals("createVisit") || method.getName().equals("unvoidVisit")) {
            usageType = UsageLog.Type.CREATED;
        } else if (method.getName().equals("voidVisit")) {
            usageType = UsageLog.Type.VOIDED;
        }
    }

    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("after aop, method name: " + method.getName());
        if (method.getName().equals("getVisit")) {
            Visit visit = (Visit) returnValue;
            usageType = UsageLog.Type.VIEWED;
            UsageLog.logEvent(visit, usageType, null);
        }
        //log.debug("Method: " + method.getName() + ". After advice called " + (++count) + " time(s) now.");
        if (method.getName().equals("saveVisit")
                || method.getName().equals("updateVisit")
                || method.getName().equals("createVisit")
                || method.getName().equals("voidVisit")
                || method.getName().equals("unvoidVisit")) {
            Visit visit = (Visit) args[0];
            UsageLog.logEvent(visit, usageType, null);
        }
    }

}
