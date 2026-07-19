/** 
 * 
 *  File name: CustomGraph.java
 *  @author: Katherina Keogh
 *  Description: This file creates the a custom Graph component for the GUI. 
 *  			 The graph displays the average of the last 10 historical readings
 *  			 from the sensors and plots a graph.
 *  Reference: https://www.javatpoint.com/java-plot
 */

package een1035;

import java.awt.*;  
import javax.swing.*;  

public class CustomGraph extends JPanel {
	
	// set the sensor values to be 0 to begin
	Integer[] oxygen = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; 
	Integer[] light = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	Integer[] pollution = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	Integer start = new Integer (0);

   public CustomGraph(){
	   
   }
   
   public void updateValues(Integer[] oxygen, Integer[] light, Integer[] pollution) {
	   this.oxygen = oxygen;
	   this.light = light;
	   this.pollution = pollution;
	   start = start + 5;
	   repaint();
   }
   
   @Override
   protected void paintComponent(Graphics g) {
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;
       
       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

       int width = getWidth();
       int height = getHeight();

       g2d.setColor(Color.BLACK);
       g2d.setStroke(new BasicStroke(5));
       g2d.drawLine(0, height - 30, 360, height - 30); // x axis line
       g2d.drawLine(30, 0, 30, height - 30 ); // y axis line
       
       drawLabels(g2d, width - 30, height - 10); // adds the time scale to x axis
       drawPoints(g2d, width - 30, height - 30, this.oxygen, Color.CYAN);
       drawPoints(g2d, width - 30, height - 30, this.light, Color.yellow);
       drawPoints(g2d, width - 30, height - 30, this.pollution, Color.green);
       
   }
   
   private void drawLabels(Graphics2D g2d, int x, int y) {
	   int step = x / 10; // gets the amount to increase by, based on teh size of the graph
	   
	   int point = 30;
	   int display = this.start;
	   
	   while(point <= x + 25 ) {
		   g2d.setFont(new Font("Arial", Font.PLAIN, 14));
	       g2d.drawString(String.valueOf(display), point, y);
	       point = point + step;
	       display += 5;
	   }
	   	
   }
   
   private void drawPoints(Graphics2D g2d, int x, int y, Integer[] points, Color color) {
	    int yStep = y / 5;
	    int xStep = x / 10;
	    int point = 30;
	    
	    for (int i = 0; i < points.length; i++) {

	    	int value = points[i];
	        int graphY = y - (value * y / 55000); // scales it down

	        g2d.setColor(color); // adds the colour based on sensor data (cyan, green or yellow)
	        g2d.fillOval(point, graphY - 10, 10, 10);
	        
	        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
	        g2d.setColor(Color.black);
	        g2d.drawString(String.valueOf(value), point - 10, graphY - 10); // adds the value as a lable

	        point += xStep;
	    }
	}
}
