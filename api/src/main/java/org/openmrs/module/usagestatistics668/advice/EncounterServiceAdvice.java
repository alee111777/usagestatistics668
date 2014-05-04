package org.openmrs.module.usagestatistics668.advice;

/**
 * author: Ye
 */
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.api.context.Context;
import org.openmrs.module.usagestatistics668.AccessEncounter;
import org.openmrs.module.usagestatistics668.AccessEncounterService;
import org.openmrs.module.usagestatistics668.UsageLog;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * AOP class used to intercept and log calls to EncounterService methods
 */
public class EncounterServiceAdvice implements MethodBeforeAdvice {

   protected static final Log log = LogFactory.getLog(EncounterServiceAdvice.class);

   protected UsageLog.Type usageType;

   public void before(Method method, Object[] args, Object target) throws Throwable {
      
      
      if (method.getName().equals("saveEncounter")) {
         Encounter encounter = (Encounter) args[0];
         usageType = UsageLog.Type.UPDATED;

         if (encounter.getEncounterId() == null) {
            usageType = UsageLog.Type.CREATED;
         } else if (encounter.isVoided()) {
            AccessEncounterService svc = (AccessEncounterService) Context.getService(AccessEncounterService.class);
	    // Patient object is voided, but check database record
            //if (!svc.isPatientVoidedInDatabase(encounter))
            //usageType = UsageLog.Type.VOIDED;
         }
      } else if (method.getName().equals("voidEncounter")) {
        // usageType = UsageLog.Type.VOIDED;
      }
      /**/
   }

   /**
    * @param returnVal
    * @param method
    * @param args
    * @param target
    * @throws java.lang.Throwable
    * @see org.springframework.aop.AfterReturningAdvice#afterReturning(Object,
    * Method, Object[], Object)
    */
   public void afterReturning(Object returnVal, Method method, Object[] args, Object target) throws Throwable {
      
      /**
      if (method.getName().equals("saveEncounter")) {

         Encounter encounter = (Encounter) args[0];

         UsageLog.logEvent(encounter, usageType);
      }
      /**/
   }
}
