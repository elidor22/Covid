import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphicSample {
    public static void main(String[] args) throws IOException {
        JFreeChart chart = createChart(createDataset());
        SVGGraphics2D g2 = new SVGGraphics2D(600, 400);
        g2.setRenderingHint(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, true);
        Rectangle r = new Rectangle(0, 0, 600, 400);
        chart.draw(g2, r);
        File f = new File("SVGTimeSeriesChartDemo1.svg");
        SVGUtils.writeToSVG(f, g2.getSVGElement());
    }

    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "COVID alert level",
                null, "Yet to create a unit", dataset);

        String fontName = "Italic";
        chart.getTitle().setFont(new Font(fontName, Font.BOLD, 18));
        chart.addSubtitle(new TextTitle(
                "Source: NYTimes",
                new Font(fontName, Font.PLAIN, 16)));

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(false);
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);
        plot.getDomainAxis().setLowerMargin(0.0);
        plot.getDomainAxis().setLabelFont(new Font(fontName, Font.BOLD, 14));
        plot.getDomainAxis().setTickLabelFont(new Font(fontName, Font.PLAIN, 12));
        plot.getRangeAxis().setLabelFont(new Font(fontName, Font.ITALIC, 14));
        plot.getRangeAxis().setTickLabelFont(new Font(fontName, Font.PLAIN, 12));
        plot.setRangeGridlinePaint(Color.GRAY);
        plot.setDomainGridlinePaint(Color.GRAY);
        chart.getLegend().setItemFont(new Font(fontName, Font.PLAIN, 14));
        chart.getLegend().setFrame(BlockBorder.NONE);
        chart.getLegend().setHorizontalAlignment(HorizontalAlignment.CENTER);
        XYItemRenderer r = plot.getRenderer();
        if (r instanceof XYLineAndShapeRenderer) {
            XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) r;
            renderer.setDefaultShapesVisible(false);
            renderer.setDrawSeriesLineAsPath(true);
            // set the default stroke for all series
            renderer.setAutoPopulateSeriesStroke(false);
            renderer.setDefaultStroke(new BasicStroke(3.0f,
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL), false);
            renderer.setSeriesPaint(0, Color.RED);
            renderer.setSeriesPaint(1, new Color(24, 123, 58));
            renderer.setSeriesPaint(2, new Color(149, 201, 136));
            renderer.setSeriesPaint(3, new Color(1, 62, 29));
            renderer.setSeriesPaint(4, new Color(81, 176, 86));
            renderer.setSeriesPaint(5, new Color(0, 55, 122));
            renderer.setSeriesPaint(6, new Color(0, 92, 165));
        }

        return chart;

    }

    private static XYDataset createDataset(){
        //as Map<year, List>, where List contains mont and value for each year/key
        Map<Integer, List> mp = new HashMap<Integer, List>();
        List ls = new ArrayList();
        ls.add(0,1015);
        ls.add(1,500);
        ls.add(2,2015);
        mp.put(1,ls);


        TimeSeries s1 = new TimeSeries("Test1");
        s1.add(new Month(1,2016),120);
        int i=0;
        for (Map.Entry<Integer, List> entry : mp.entrySet()) {

            Integer k = entry.getKey();
            List v = entry.getValue();
            int month = entry.getKey();
            System.out.println("Key: " + k + ", Value: " + v);
            s1.add(new Month(month, k+2016),(Integer) v.get(i));
            i++;
        }


        ls.add(0,100);
        ls.add(1,400);
        ls.add(2,718);
        mp.put(2,ls);
        mp.put(3,ls);
        TimeSeries s2 = new TimeSeries("TestNY");
        //s2.add(new Month(1,2016),120);
        int j = 0;
        for (Map.Entry<Integer, List> entry2 : mp.entrySet()) {

            Integer year = entry2.getKey();
            List v = entry2.getValue();
            int month = entry2.getKey();
            System.out.println("Key: " + year + ", Value: " + v);
            s2.add(new Month(month, year+2015),(Integer) v.get(j));
            j++;
        }


        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        dataset.addSeries(s2);
        return dataset;
    }

}
