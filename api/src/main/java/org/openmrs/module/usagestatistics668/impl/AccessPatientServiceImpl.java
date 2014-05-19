package org.openmrs.module.usagestatistics668.impl;

import java.util.Date;
import java.util.List;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.usagestatistics668.AccessPatient;
import org.openmrs.module.usagestatistics668.AccessPatientService;
import org.openmrs.module.usagestatistics668.ActionCriteria;
import org.openmrs.module.usagestatistics668.db.AccessPatientDAO;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of our {@link AccessPatientService} interface. This class is
 * set in the /metadata/moduleApplicationContext.xml file to be matched to the
 * {@link AccessPatientService} interface. <br/>
 * <br/>
 * This class extends {@link BaseOpenmrsService} so that there are empty methods
 * for onStartup and onShutdown. This allows shields us from changes to the
 * OpenmrsService interface from forcing us to implement the methods. <br/>
 * <br/>
 * NEVER call "new AccessPatientServiceImpl()....". To use this class, you do:
 * <br/>
 * <code>
 *
 *   Context.getService(AccessPatientService.class).saveAccessPatient(accessPatient)...
 *
 * </code>
 *
 * @author Ye Cheng
 */
public class AccessPatientServiceImpl extends BaseOpenmrsService implements AccessPatientService {

    /**
     * This "dao" object is set by spring. See the
     * /metadata/moduleApplicationContext.xml for what value gets set here. We
     * can assume that this will never be null, even though it never gets set in
     * here. This is called Inversion of Control (IoC)
     */
    private AccessPatientDAO dao;

    /**
     * This is the method that spring calls to set the DAO
     *
     * @param dao the dao to set
     */
    public void setDao(AccessPatientDAO dao) {
        this.dao = dao;
    }

    /**
     * see AccessPatientSerivce
     * @param id
     * @return accessPatient
     */
    @Transactional(readOnly = true)
    public AccessPatient getAccessPatient(Integer id) {
        return dao.getAccessPatient(id);
    }

    /**
     * see AccessPatientService
     * @param accessPatient
     * @throws APIException 
     */
    @Override
    public void saveAccessPatient(AccessPatient accessPatient) throws APIException {
        dao.saveAccessPatient(accessPatient);
    }

    /**
     * see AccessPatientService
     * @param since
     * @param until
     * @param filter
     * @param maxResults
     * @return List of AccessPatient id's with number of access
     * @throws APIException 
     */
    @Override
    public List<Object[]> getMostViewedPatient(Date since, Date until, ActionCriteria filter, int maxResults) throws APIException {
        return dao.getMostViewedPatient(since, until, filter, maxResults);
    }
    
    /**
     * see AccessPatientService
     * @param since
     * @param until
     * @param patientId
     * @param filter
     * @param maxResults
     * @return List of AccessPatient
     */
    public List<Object> getDateRangeList(Date since, Date until, Integer patientId, ActionCriteria filter, Integer maxResults){
        return dao.getDateRangeList(since, until, patientId,  filter, maxResults);
    }

}
