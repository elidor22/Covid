package Utils;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class DisplayImage {


    public DisplayImage() throws IOException
    {
        //Returns the absolute path of the file
        /**
         * If wanted, this can be used to display a newly created graphic*/
        //FileChooser fc = new FileChooser();
        //String path = fc.choose();
        BufferedImage img=ImageIO.read(new File("/home/elidor/IdeaProjects/Covid/SVGTimeSeriesChartDemo1.jpg"));
        BufferedImage img2=ImageIO.read(new File("/home/elidor/IdeaProjects/Covid/SVGTimeSeriesChartDemo1.jpg"));
        BufferedImage img3=ImageIO.read(new File("/home/elidor/IdeaProjects/Covid/SVGTimeSeriesChartDemo1.jpg"));

        ImageIcon icon=new ImageIcon(img);
        ImageIcon icon2=new ImageIcon(img2);
        ImageIcon icon3=new ImageIcon(img3);
        JFrame frame=new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(1600,1000);
        JLabel lbl=new JLabel();
        lbl.setBounds(0,0,600,400);

        JLabel lbl2=new JLabel();
        lbl2.setBounds(600,500,600,400);

        JLabel lbl3=new JLabel();
        lbl3.setBounds(600,0,600,400);

        lbl.setIcon(icon);
        lbl2.setIcon(icon3);
        lbl3.setIcon(icon2);
        frame.add(lbl);
        frame.add(lbl2);
        frame.add(lbl3);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
