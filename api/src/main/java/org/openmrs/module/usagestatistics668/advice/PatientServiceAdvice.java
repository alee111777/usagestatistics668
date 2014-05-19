package org.openmrs.module.usagestatistics668.advice;

import java.lang.reflect.Method;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.Patient;
import org.openmrs.module.usagestatistics668.AccessPatientService;
import org.openmrs.module.usagestatistics668.UsageLog;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;


/**
 * AOP class used to intercept and log calls to PatientService methods
 * @author Ye
 */
public class PatientServiceAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

    private Log log = LogFactory.getLog(this.getClass());

    private int count = 0;
    
    protected UsageLog.Type usageType;
    
   /**
    * this method is used to log method name and parameters before the method is used
    * @param method method to log
    * @param args arguments passed to this method
    * @param target the target of this method
    * @throws Throwable 
    */
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before aop, method name: " + method.getName());


	if (method.getName().equals("savePatient") || method.getName().equals("updatePatient")) {
            Patient patient = (Patient)args[0];
            usageType = UsageLog.Type.UPDATED;
            if (patient.getPatientId() == null)
                usageType = UsageLog.Type.CREATED;
            else if (patient.isVoided()) {
		AccessPatientService svc = (AccessPatientService)Context.getService(AccessPatientService.class);
            }			
	}
	else if (method.getName().equals("createPatient")||method.getName().equals("unvoidPatient"))
            usageType = UsageLog.Type.CREATED;
       else if (method.getName().equals("voidPatient"))
                usageType = UsageLog.Type.VOIDED;
    }

    /**
    * this method is used to log method name, parameters and return values 
    * after the method is used
    * @param method method to log
    * @param args arguments passed to this method
    * @param target the target of this method
    * @param returnValue the return value of this method
    * @throws Throwable 
    */
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("after aop, method name: " + method.getName());
        if (method.getName().equals("getPatient")){
            Patient patient = (Patient) returnValue;
            usageType = UsageLog.Type.VIEWED;
            UsageLog.logEvent(patient, usageType, null);
        }
        if (method.getName().equals("savePatient")
            || method.getName().equals("updatePatient")
            || method.getName().equals("createPatient")
            || method.getName().equals("voidPatient")
            || method.getName().equals("unvoidPatient")) {
                Patient patient = (Patient)args[0];
                UsageLog.logEvent(patient, usageType, null);
            }
     }

}


