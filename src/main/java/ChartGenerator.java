import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.ui.HorizontalAlignment;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.graphics2d.svg.SVGGraphics2D;
import Utils.*;//imports all the util package scripts
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ChartGenerator {
    public static String title ="COVID new cases";
    public static String unit = "Cases";
    public ChartGenerator() throws IOException {
        JFreeChart chart = createChart(createDataset());
        SVGGraphics2D g2 = new SVGGraphics2D(600, 400);
        g2.setRenderingHint(JFreeChart.KEY_SUPPRESS_SHADOW_GENERATION, true);
        Rectangle r = new Rectangle(0, 0, 600, 400);
        chart.draw(g2, r);
        File f = new File("SVGTimeSeriesChartDemo1.jpg");

        ChartUtils.saveChartAsJPEG(f,chart,600,400);
        //SVGUtils.writeToSVG(f, g2.getSVGElement());
    }

    private static JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                title,
                null, unit, dataset);

        String fontName = "Italic";
        chart.getTitle().setFont(new Font(fontName, Font.BOLD, 18));
        chart.addSubtitle(new TextTitle(
                "Source: ourworldindata.org/coronavirus-source-data",
                new Font(fontName, Font.PLAIN, 16)));

        /**
         * Sets the chart look*/
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

        System.out.println("Enter concern level");
        Scanner sc = new Scanner(System.in);
        boolean isConcern = false;
        if(sc.nextInt()>=1)
            isConcern =true;

        Map<Integer, List> mp = new HashMap<Integer, List>();
        List<String> day = new ArrayList();
        List<String> month = new ArrayList();
        ReadData rd = new ReadData(isConcern);
        List<String> cases = new ArrayList();

        dateElements dt = new dateElements(isConcern);


        cases=rd.getCases();
        day =dt.getDay();
        month = dt.getMonth();

        String title ="New Cases";
        if(isConcern){
            title="Concern";
            ChartGenerator.title = "Concern levels";
            ChartGenerator.unit="Concern level";
        }

        TimeSeries s1 = new TimeSeries(title);
        int i =1;

        //Continues the loop till the all the data for the given days is added
        while(i<day.size()){
           s1.add(new Day(Integer.parseInt(day.get(i)),
                 Integer.parseInt(month.get(i)),2020),Integer.parseInt(cases.get(i)));
           i++;

       }



        //Adds all the gathered information from above te the returned dataset
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        return dataset;
    }

}
