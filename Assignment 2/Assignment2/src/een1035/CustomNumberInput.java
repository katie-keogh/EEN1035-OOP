/** 
 * 
 *  File name: CustomNumberInput.java
 *  @author: Katherina Keogh
 *  Description: This file creates the a custom Number Input component for the GUI. 
 *  			 The number input creates an editable field for users to input the
 *  			 sensor value using either the keyboard or the buttons displayed
 *  			 on screen.
 *  
 */


package een1035;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;


@SuppressWarnings("serial")
public class CustomNumberInput extends JPanel {
	
	private JSpinner numberInput;

    public CustomNumberInput(String label) {
    	
        this.setLayout(new FlowLayout()); 
        numberInput = new JSpinner(new SpinnerNumberModel(0, 0, 100000, 1));
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(this.numberInput, "#");
        numberInput.setEditor(editor);
        this.add(new JLabel(label + ":"), BorderLayout.NORTH);
        this.setBackground(getBackgroundColour(label));
        this.add(numberInput);

        numberInput.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setBackground(getBackgroundColour(label));
            }
        });
    }

	public int getValue() {
		return (int) numberInput.getValue();
	}

    private Color getBackgroundColour(String label){
        if(label == Constants.OXYGEN){
            return AnalysisService.getRatingColor(AnalysisService.getOxygenRating((int) numberInput.getValue()));
        } else if (label == Constants.LIGHT) {
            return AnalysisService.getRatingColor(AnalysisService.getLightRating((int) numberInput.getValue()));
        } else if (label == Constants.POLLUTION){
            return AnalysisService.getRatingColor(AnalysisService.getPollutionRating((int) numberInput.getValue()));
        }
        return Color.lightGray;
    }

}
