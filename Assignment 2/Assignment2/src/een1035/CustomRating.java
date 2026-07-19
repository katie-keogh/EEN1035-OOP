/** 
 * 
 *  File name: CustomRating.java
 *  @author: Katherina Keogh
 *  Description: This file creates the a custom Rating component for the GUI. 
 *  			 The panel displays "Good", "Fair" or "Poor" and their corresponding colours.
 *  
 */

package een1035;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CustomRating extends JPanel {
	
	private JLabel ratingLabel;
	
	public CustomRating(){
		
        this.setLayout(new BorderLayout());

        ratingLabel = new JLabel("Rating: N/A");
        this.add(ratingLabel, BorderLayout.CENTER);
		   
        
	}
	
	public void updateRating(String rating) {
        ratingLabel.setText("Rating: " + rating);
        this.setBackground(AnalysisService.getRatingColor(rating));
    }
}
