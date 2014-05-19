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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.openmrs.api.context.Context;
import org.openmrs.module.usagestatistics668.AccessEncounterService;
import org.openmrs.module.usagestatistics668.ActionCriteria;
import org.openmrs.module.usagestatistics668.util.ContextProvider;

/**
 * View to render encounter data as a bar chart image
 * @author: Ye Cheng
 */
public class AccessEncounterChartView extends AbstractChartView {

    /**
     * create bar chart for encounter data
     * @param model model build for view
     * @param request
     * @return JFREEChart for viewing encounter data
     */
    @Override
    protected JFreeChart createChart(Map<String, Object> model, HttpServletRequest request) {

        AccessEncounterService svc = Context.getService(AccessEncounterService.class);
        List<Object[]> data = svc.getMostViewedEncounter(getFromDate(), getUntilDate(), getUsageFilter(), getMaxResults());
        String[] categories = new String[data.size()];
        int[] count = new int[data.size()];
        for (int i = 0; i < categories.length; i++) {
            categories[i] = String.valueOf(data.get(i)[0]);
            count[i] = ((BigInteger) data.get(i)[1]).intValue();
        }

        String yAxisLabel = ContextProvider.getMessage("usagestatistics668.summary.count");
        String xAxisLabel = ContextProvider.getMessage("usagestatistics668.summary.encounter");
        String seriesView = ContextProvider.getMessage("usagestatistics668.summary.any");
        if (getUsageFilter() == ActionCriteria.CREATED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.created");
        } else if (getUsageFilter() == ActionCriteria.UPDATED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.updated");
        } else if (getUsageFilter() == ActionCriteria.VIEWED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.viewed");
        } else if (getUsageFilter() == ActionCriteria.VOIDED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.voided");
        } else if (getUsageFilter() == ActionCriteria.UNVOIDED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.unvoided");
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int c = 0; c < data.size(); c++) {
            dataset.addValue(count[c], seriesView, (categories != null) ? categories[c] : (c + ""));
        }

        JFreeChart chart = ChartFactory.createBarChart(
                null, // Chart title
                xAxisLabel, // Domain axis label
                yAxisLabel, // Range axis label
                dataset, // Data
                PlotOrientation.VERTICAL, // Orientation
                true, // Include legend
                false, // Tooltips?
                false // URLs?
        );

        return chart;
    }

}
