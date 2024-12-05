package com.cryptoclient.application.views.dashboard.components;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import java.awt.*;

public class ChartStyle {

    public static void applyStyle(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(240, 240, 240));
        plot.setDomainGridlinePaint(new Color(85, 107, 255)); // Grille bleu
        plot.setRangeGridlinePaint(new Color(85, 107, 255));

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(85, 107, 255)); // Ligne bleu
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);

        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 18));
        chart.getTitle().setPaint(new Color(85, 107, 255)); // Titre bleu
    }
}
