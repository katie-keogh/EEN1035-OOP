/** 
 * 
 *  File name: CustomGauge.java
 *  @author: Katherina Keogh
 *  Description: This file creates the a custom Gauge component for the GUI. 
 *  			 The gauge displays the lowest, average and highest readings
 *  			 from the sensors and plots them on a bar.
 *  
 *  Reference: https://docs.oracle.com/javase/1.5.0/docs/guide/awt/demos/lightweight/GaugeSwing/Gauge.java
 *  
 */


package een1035;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.*;

public class CustomGauge extends JPanel {

    private int lowValue = 0;
    private int avgValue = 0;
    private int highValue = 0;    
    int current = 0;
    int total = 50000;

    int Height = 18;
    int Width  = 250;

    
    public void setValues(int low, int avg, int high) {
        this.lowValue = low;
        this.avgValue = avg;
        this.highValue = high;
        repaint();
    }


	public CustomGauge(int min, int avg, int max) {
	      this(Color.lightGray);
	      setValues(min, avg, max);
	      this.setPreferredSize(new Dimension(200, 120));
	  }
	
	  public CustomGauge(Color gaugeColor) {
	      setBackground(gaugeColor);
	  }

	  public void paint(Graphics g) {
	      int barWidth = (int) (((float)this.highValue/(float)total) * getSize().width);
	      g.setColor(new java.awt.Color(74, 176, 240));
	      g.fill3DRect(0, 0, barWidth, getSize().height-2, true);
	      
	      barWidth = (int) (((float)this.avgValue/(float)total) * getSize().width);
	      g.setColor(new java.awt.Color(141, 206, 247));
	      g.fill3DRect(0, 0, barWidth, getSize().height-2, true);
	      
	      barWidth = (int) (((float)this.lowValue/(float)total) * getSize().width);
	      g.setColor(new java.awt.Color(187, 226, 250));
	      g.fill3DRect(0, 0, barWidth, getSize().height-2, true);
	  }
	  
	  public Dimension getPreferredSize() {
	      return new Dimension(Width, Height);
	  }

}
