package com.cryptoclient.application.views.dashboard.components;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class Content extends JPanel {

    private JLabel contentTitle;
    private ChartPanel chartPanel;
    private XYPlot plot;
    private TimeSeries series;
    private TimeSeriesDataItem currentCursorItem;
    private Timer redrawTimer; // Champ pour le Timer

    public Content() {
        this.setLayout(new BorderLayout());

        this.contentTitle = new JLabel("Sélectionnez une crypto pour afficher son cours");
        this.contentTitle.setFont(new Font("Arial", Font.PLAIN, 20));
        this.contentTitle.setHorizontalAlignment(JLabel.CENTER);
        this.add(this.contentTitle, BorderLayout.NORTH);

        this.redrawTimer = null; // Initialiser le Timer à null
    }

    private JFreeChart createChart(String cryptocurrencyName, JSONArray data) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        series = new TimeSeries("Cours : " + cryptocurrencyName);

        for (int i = 0; i < data.length(); i++) {
            JSONObject obj = data.getJSONObject(i);
            Instant instant = Instant.parse(obj.getString("time_close"));
            Date date = Date.from(instant);
            RegularTimePeriod period = new Hour(date);
            double price = obj.getDouble("rate_close");
            series.add(period, price);
        }

        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Cours de la cryptomonnaie : " + cryptocurrencyName,
                "Temps",
                "Prix (EUR)",
                dataset,
                false,
                true,
                false
        );

        this.plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
        renderer.setSeriesPaint(0, new Color(0, 102, 204));
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);

        customizeChart(chart);
        return chart;
    }

    private void customizeChart(JFreeChart chart) {
        plot.setBackgroundPaint(new Color(240, 240, 240));
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

        chart.getTitle().setFont(new Font("Arial", Font.BOLD, 16));
    }

    public void loadChartPanel(String cryptocurrencyName, JSONArray data) {
        if (this.redrawTimer != null) {
            this.redrawTimer.stop(); // Arrêter l'ancien Timer avant de recréer le graphique
        }

        if (this.chartPanel != null) {
            this.chartPanel.setVisible(false);
            this.remove(this.chartPanel);
        }

        this.remove(this.contentTitle);

        JFreeChart chart = this.createChart(cryptocurrencyName, data);
        this.chartPanel = new ChartPanel(chart);
        this.chartPanel.setMouseWheelEnabled(true);

        if (series.getItemCount() > 0) {
            currentCursorItem = series.getDataItem(0);
        }

        this.chartPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Graphics2D g2 = (Graphics2D) chartPanel.getGraphics();
                chartPanel.repaint();

                double mouseXValue = plot.getDomainAxis().java2DToValue(e.getX(), chartPanel.getScreenDataArea(), plot.getDomainAxisEdge());

                TimeSeriesDataItem closestItem = null;
                double closestDistance = Double.MAX_VALUE;

                for (int i = 0; i < series.getItemCount(); i++) {
                    TimeSeriesDataItem item = series.getDataItem(i);
                    double xValue = item.getPeriod().getFirstMillisecond();
                    double distance = Math.abs(xValue - mouseXValue);
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestItem = item;
                    }
                }

                if (closestItem != null) {
                    currentCursorItem = closestItem;
                }

                drawCursor(g2, chartPanel);
            }
        });

        this.add(this.chartPanel, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();

        // Créer un nouveau Timer
        this.redrawTimer = new Timer(50, e -> {
            if (currentCursorItem != null) {
                Graphics2D g2 = (Graphics2D) chartPanel.getGraphics();
                if (g2 != null) {
                    drawCursor(g2, chartPanel);
                }
            }
        });
        this.redrawTimer.start();
    }

    private void drawCursor(Graphics2D g2, ChartPanel chartPanel) {
        if (currentCursorItem != null) {
            Rectangle2D dataArea = chartPanel.getScreenDataArea();
            double x = plot.getDomainAxis().valueToJava2D(currentCursorItem.getPeriod().getFirstMillisecond(), dataArea, plot.getDomainAxisEdge());
            double y = plot.getRangeAxis().valueToJava2D(currentCursorItem.getValue().doubleValue(), dataArea, plot.getRangeAxisEdge());

            g2.setColor(Color.RED);
            g2.fillOval((int) x - 5, (int) y - 5, 10, 10);

            float[] dash = {5.0f, 5.0f};
            g2.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f));
            g2.setColor(Color.GRAY);

            g2.drawLine((int) x, (int) dataArea.getMinY(), (int) x, (int) dataArea.getMaxY());
            g2.drawLine((int) dataArea.getMinX(), (int) y, (int) dataArea.getMaxX(), (int) y);

            g2.setStroke(new BasicStroke());

            drawValueBox(g2, x, y);
        }
    }

    private void drawValueBox(Graphics2D g2, double x, double y) {
        int boxWidth = 140;
        int boxHeight = 60;
        int boxX = (int) x + 15;
        int boxY = (int) y - boxHeight / 2;

        GradientPaint gradient = new GradientPaint(boxX, boxY, new Color(60, 60, 90), boxX, boxY + boxHeight, new Color(30, 30, 60));
        g2.setPaint(gradient);
        g2.fillRoundRect(boxX, boxY, boxWidth, boxHeight, 10, 10);

        g2.setColor(Color.WHITE);
        g2.drawRoundRect(boxX, boxY, boxWidth, boxHeight, 10, 10);

        String formattedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(currentCursorItem.getPeriod().getStart());
        String formattedValue = new DecimalFormat("#,##0.0000").format(currentCursorItem.getValue().doubleValue());

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 12));
        g2.drawString("Temps :", boxX + 10, boxY + 20);
        g2.drawString(formattedTime, boxX + 10, boxY + 35);
        g2.drawString("Valeur :", boxX + 10, boxY + 50);
        g2.drawString(formattedValue, boxX + 70, boxY + 50);
    }
}
