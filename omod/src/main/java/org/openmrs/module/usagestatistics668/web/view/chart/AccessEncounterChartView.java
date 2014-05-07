/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
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
import org.openmrs.module.usagestatistics668.util.ContextProvider;

/**
 * View to render hour/day usage data as a chart image
 */
public class AccessEncounterChartView extends AbstractChartView {

	@Override
	protected JFreeChart createChart(Map<String, Object> model, HttpServletRequest request) {
            System.out.println("---------create chart--------------");
		
		AccessEncounterService svc = Context.getService(AccessEncounterService.class);

                //Date monthAgo = StatsUtils.addDaysToDate(null, -30);
		List<Object[]> data = svc.getMostViewedEncounter(getFromDate(), getMaxResults());
                //List<Object[]> data = svc.getMostViewedPatient(monthAgo, 2);
		String[] categories = new String[data.size()] ;
                int[] count = new int[data.size()];
                for (int i =0;i < categories.length;i++){
                    categories[i] = String.valueOf(data.get(i)[0]);
                    count[i] = ((BigInteger)data.get(i)[1]).intValue();
                }

		String yAxisLabel = ContextProvider.getMessage("usagestatistics668.summary.count");
		String xAxisLabel = ContextProvider.getMessage("usagestatistics668.summary.encounter");
                String seriesView = ContextProvider.getMessage("usagestatistics668.summary.view");

                DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                for (int c = 0; c < data.size(); c++) {
                       dataset.addValue(count[c], seriesView, (categories != null) ? categories[c] : (c + ""));
                }
        
                JFreeChart chart = ChartFactory.createBarChart(
                        null,						// Chart title
                        xAxisLabel,					// Domain axis label
                        yAxisLabel,					// Range axis label
                        dataset,					// Data
                        PlotOrientation.VERTICAL,	// Orientation
                        true,						// Include legend
                        false,						// Tooltips?
                        false						// URLs?
                );

                return chart;
                }

}

