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
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.openmrs.api.context.Context;
import org.springframework.web.bind.ServletRequestUtils;
import org.openmrs.module.usagestatistics668.AccessPatientService;
import org.openmrs.module.usagestatistics668.ActionCriteria;
import org.openmrs.module.usagestatistics668.util.ContextProvider;
import org.openmrs.module.usagestatistics668.util.StatsUtils;

/**
 * View to render hour/day usage data as a chart image
 */
public class AccessPatientPieChartView extends AbstractChartView {

    @Override
    protected JFreeChart createChart(Map<String, Object> model, HttpServletRequest request) {
        System.out.println("---------create pie chart--------------");

        AccessPatientService svc = Context.getService(AccessPatientService.class);

        //Date monthAgo = StatsUtils.addDaysToDate(null, -30);
        List<Object[]> data = svc.getMostViewedPatient(getFromDate(), getUntilDate(), getUsageFilter(), getMaxResults());
        //List<Object[]> data = svc.getMostViewedPatient(monthAgo, 2);
        
        
        System.out.println(getFromDate());
        
        String[] categories = new String[data.size()];
        int[] count = new int[data.size()];
        for (int i = 0; i < categories.length; i++) {
            categories[i] = String.valueOf(data.get(i)[0]);
            count[i] = ((BigInteger) data.get(i)[1]).intValue();
        }

        String seriesView = ContextProvider.getMessage("usagestatistics668.summary.any");
        String title = "Most Accessed Patient Data";
        
        if (getUsageFilter() == ActionCriteria.CREATED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.created");
            title = "Most Created Patient Data";
        } else if (getUsageFilter() == ActionCriteria.UPDATED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.updated");
            title = "Most Updated Patient Data";
        } else if (getUsageFilter() == ActionCriteria.VIEWED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.viewed");
            title = "Most Vieweded Patient Data";
        } else if (getUsageFilter() == ActionCriteria.VOIDED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.voided");
            title = "Most Voided Patient Data";
        } else if (getUsageFilter() == ActionCriteria.UNVOIDED) {
            seriesView = ContextProvider.getMessage("usagestatistics668.summary.unvoided");
            title = "Most Unvoided Patient Data";
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

        /*
        // set a custom background for the chart
        chart.setBackgroundPaint(new GradientPaint(new Point(0, 0),
                new Color(20, 20, 20), new Point(400, 200), Color.DARK_GRAY));

        // customise the title position and font
        TextTitle t = chart.getTitle();
        t.setHorizontalAlignment(HorizontalAlignment.LEFT);
        t.setPaint(new Color(240, 240, 240));
        t.setFont(new Font("Arial", Font.BOLD, 26));

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        plot.setInteriorGap(0.04);
        plot.setOutlineVisible(false);

        // use gradients and white borders for the section colours
        plot.setSectionPaint("Others", createGradientPaint(new Color(200, 200, 255), Color.BLUE));
        plot.setSectionPaint("Samsung", createGradientPaint(new Color(255, 200, 200), Color.RED));
        plot.setSectionPaint("Apple", createGradientPaint(new Color(200, 255, 200), Color.GREEN));
        plot.setSectionPaint("Nokia", createGradientPaint(new Color(200, 255, 200), Color.YELLOW));
        plot.setBaseSectionOutlinePaint(Color.WHITE);
        plot.setSectionOutlinesVisible(true);
        plot.setBaseSectionOutlineStroke(new BasicStroke(2.0f));

        // customise the section label appearance
        plot.setLabelFont(new Font("Courier New", Font.BOLD, 20));
        plot.setLabelLinkPaint(Color.WHITE);
        plot.setLabelLinkStroke(new BasicStroke(2.0f));
        plot.setLabelOutlineStroke(null);
        plot.setLabelPaint(Color.WHITE);
        plot.setLabelBackgroundPaint(null);

        // add a subtitle giving the data source
        TextTitle source = new TextTitle("Source: http://www.bbc.co.uk/news/business-15489523",
                new Font("Courier New", Font.PLAIN, 12));
        source.setPaint(Color.WHITE);
        source.setPosition(RectangleEdge.BOTTOM);
        source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(source);*/
        return chart;
    }

}

