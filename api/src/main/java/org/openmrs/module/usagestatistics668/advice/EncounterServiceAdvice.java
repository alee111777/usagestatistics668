package org.openmrs.module.usagestatistics668.advice;

/**
 * author: Anthony Lee
 */
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Encounter;
import org.openmrs.api.context.Context;
import org.openmrs.module.usagestatistics668.AccessEncounterService;
import org.openmrs.module.usagestatistics668.UsageLog;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * AOP class used to intercept and log calls to EncounterService methods
 */
public class EncounterServiceAdvice implements MethodBeforeAdvice, AfterReturningAdvice  {

   private Log log = LogFactory.getLog(this.getClass());

   private int count = 0;

   protected UsageLog.Type usageType;
   
   /**
    * need this method to get certain parameters
    * @param method
    * @param args
    * @param target
    * @throws Throwable 
    */
   public void before(Method method, Object[] args, Object target) throws Throwable {
      System.out.println("before aop, method name: " + method.getName());

      if (method.getName().equals("saveEncounter") || method.getName().equals("updateEncounter")) {
         Encounter encounter = (Encounter) args[0];
         usageType = UsageLog.Type.UPDATED;
         if (encounter.getEncounterId() == null) {
            usageType = UsageLog.Type.CREATED;
         } else if (encounter.isVoided()) {
            AccessEncounterService svc = (AccessEncounterService) Context.getService(AccessEncounterService.class);

         }
      } else if (method.getName().equals("createEncounter") || method.getName().equals("unvoidEncounter")) {
         usageType = UsageLog.Type.CREATED;
      } else if (method.getName().equals("voidEncounter")) {
         usageType = UsageLog.Type.VOIDED;
      }
   }

   public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
      System.out.println("after aop, method name: " + method.getName());
      if (method.getName().equals("getEncounter")) {
         Encounter patient = (Encounter) returnValue;
         usageType = UsageLog.Type.VIEWED;
         UsageLog.logEvent(patient, usageType, null);
      }
   
      if (method.getName().equals("saveEncounter")
              || method.getName().equals("updateEncounter")
              || method.getName().equals("createEncounter")
              || method.getName().equals("voidEncounter")
              || method.getName().equals("unvoidEncounter")) {
         Encounter encounter = (Encounter) args[0];
         UsageLog.logEvent(encounter, usageType, null);
      }
   }

}
