/**
 * The contents of this file are subject to the OpenMRS Public License Version
 * 1.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 *
 * Copyright (C) OpenMRS, LLC. All Rights Reserved.
 */
package org.openmrs.module.usagestatistics668.web.view.chart;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.openmrs.api.context.Context;
import org.openmrs.module.usagestatistics668.AccessVisitService;
import org.openmrs.module.usagestatistics668.ActionCriteria;
import org.openmrs.module.usagestatistics668.util.ContextProvider;

/**
 * View to render encounter data as a pie chart image
 * @author: Ye Cheng
 */
public class AccessVisitPieChartView extends AbstractChartView {

    /**
     * create pie chart for visit data
     * @param model model built for view
     * @param request
     * @return JFREEChar pie chart
     */    
    @Override
    protected JFreeChart createChart(Map<String, Object> model, HttpServletRequest request) {
        AccessVisitService svc = Context.getService(AccessVisitService.class);

        List<Object[]> data = svc.getMostViewedVisit(getFromDate(), getUntilDate(), getUsageFilter(), getMaxResults());
        String[] categories = new String[data.size()];
        int[] count = new int[data.size()];
        for (int i = 0; i < categories.length; i++) {
            categories[i] = String.valueOf(data.get(i)[0]);
            count[i] = ((BigInteger) data.get(i)[1]).intValue();
        }

        String seriesView = ContextProvider.getMessage("usagestatistics668.summary.any");
        String title = "Most Accessed Visit Data";
        
        if (getUsageFilter() == ActionCriteria.CREATED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.created");
            title = "Most Created Visit Data";
        } else if (getUsageFilter() == ActionCriteria.UPDATED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.updated");
            title = "Most Updated Visit Data";
        } else if (getUsageFilter() == ActionCriteria.VIEWED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.viewed");
            title = "Most Vieweded Visit Data";
        } else if (getUsageFilter() == ActionCriteria.VOIDED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.voided");
            title = "Most Voided Visit Data";
        } else if (getUsageFilter() == ActionCriteria.UNVOIDED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.unvoided");
            title = "Most Unvoided Visit Data";
        }
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int c = 0; c < data.size(); c++) {
            dataset.setValue(String.valueOf(data.get(c)[0]), ((BigInteger) data.get(c)[1]).intValue());
        }
        
        JFreeChart chart = ChartFactory.createPieChart(
                title, // chart title
                dataset, // data
                false, // no legend
                true, // tooltips
                false // no URL generation
        );

        return chart;
    }

}


