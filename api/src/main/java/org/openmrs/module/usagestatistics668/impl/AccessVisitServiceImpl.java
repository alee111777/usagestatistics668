package org.openmrs.module.usagestatistics668.impl;

import java.util.Date;
import java.util.List;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.usagestatistics668.AccessVisit;
import org.openmrs.module.usagestatistics668.AccessVisitService;
import org.openmrs.module.usagestatistics668.ActionCriteria;
import org.openmrs.module.usagestatistics668.db.AccessVisitDAO;
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
 * @author Jonathan
 */

public class AccessVisitServiceImpl extends BaseOpenmrsService implements AccessVisitService {

    /**
     * This "dao" object is set by spring. See the
     * /metadata/moduleApplicationContext.xml for what value gets set here. We
     * can assume that this will never be null, even though it never gets set in
     * here. This is called Inversion of Control (IoC)
     */
    private AccessVisitDAO dao;

    /**
     * This is the method that spring calls to set the DAO
     *
     * @param dao the dao to set
     */
    public void setDao(AccessVisitDAO dao) {
        this.dao = dao;
    }

    /**
     * see AccessVisitService
     * @param id
     * @return 
     */
    @Transactional(readOnly = true)
    public AccessVisit getAccessVisit(Integer id) {
        return dao.getAccessVisit(id);
    }

    /**
     * see AccessVisitService
     * @param accessVisit
     * @throws APIException 
     */
    @Override
    public void saveAccessVisit(AccessVisit accessVisit) throws APIException {
        dao.saveAccessVisit(accessVisit);
    }

    /**
     * see AccessVisitService
     * @param since
     * @param until
     * @param filter
     * @param maxResults
     * @return
     * @throws APIException 
     */
    @Override
    public List<Object[]> getMostViewedVisit(Date since, Date until, ActionCriteria filter, int maxResults) throws APIException {
        return dao.getMostViewedVisit(since, until, filter, maxResults);
    }

    /**
     * see AccessVisitService
     * @param since
     * @param until
     * @param patientId
     * @param filter
     * @param maxResults
     * @return 
     */
    public List<Object> getDateRangeList(Date since, Date until, Integer patientId, ActionCriteria filter, Integer maxResults) {
        return dao.getDateRangeList(since, until, patientId, filter, maxResults);    
    }

    /**
     * see AccessVisitService
     * @param numOfVisits
     * @return 
     */
    public List<AccessVisit> getMostRecent(int numOfVisits) {
        return dao.getMostRecent(numOfVisits);  
    }
}
