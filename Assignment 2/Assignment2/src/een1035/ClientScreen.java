/** 
 * 
 *  File: ClientScreen.java
 *  Author: Katherina Keogh
 *  Description: This file creates the GUI and it components for the user to interact with the client. 
 *  
 */

package een1035;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class ClientScreen extends Frame implements WindowListener {
	
	private int port;
	private JTextField nameField, portField;
	private CustomNumberInput oxygen, light, pollution;
	private JPanel north, south, rating;
	private Client client;
	private CustomRating customRating;
	private History average;
	
	public ClientScreen(Client client, int portNumber){
		super("Client");
		this.port = portNumber;
		this.client = client;
		
		
		this.nameField = new JTextField(String.valueOf("Name"));
		
		this.north = new JPanel();
		
		addPortDisplay();
		addNameField();
		addRating();
		
		this.south = new JPanel();
		addNumberFields();
		this.south.add(new JButton("Update"));		
		
		
	
		
		this.add(south, BorderLayout.SOUTH);
		this.add(north, BorderLayout.NORTH);
		
		this.addWindowListener(this);
		this.pack();
		this.setVisible(true);

		this.average = new History(0, "Average", "N/A", 0, 0, 0);
		int i = 0;
		while(i < 10) {
			i++;
			this.average.addOxygen(0);
			this.average.addLight(0);
			this.average.addPollution(0);
			
		}

		refresh();
	}
	
	private void refresh() {
		
		Timer timer = new Timer(5000, e -> {
			updateComponents();
			
		});
		timer.start();
		
	}

	private void updateComponents(){
		this.customRating.updateRating(AnalysisService.getRating(this.average));
	}
	
	private void addPortDisplay() {
		JPanel portPanel = new JPanel();
		this.portField = new JTextField(String.valueOf(this.port));
		portField.setEditable(false);
		portPanel.add(new JLabel("Port:"), BorderLayout.NORTH);
		portPanel.add(portField);
		
		north.add(portPanel);
		
	}
	
	private void addNameField() {
		JPanel namePanel = new JPanel();
		this.nameField = new JTextField("Senor on port " + String.valueOf(this.port));
		namePanel.add(new JLabel("Name:"), BorderLayout.NORTH);
		namePanel.add(nameField);
		
		north.add(namePanel);
		
	}
	
	private void addNumberFields() {
		this.oxygen = new CustomNumberInput(Constants.OXYGEN);
		this.light = new CustomNumberInput(Constants.LIGHT);
		this.pollution = new CustomNumberInput(Constants.POLLUTION);
		south.add(this.oxygen, BorderLayout.CENTER);
		south.add(new JLabel("ppm"));
		south.add(this.light, BorderLayout.CENTER);
		south.add(new JLabel("lux"));
		south.add(this.pollution, BorderLayout.CENTER);
		south.add(new JLabel("ppm"));
	}

	private void addRating() {
		this.rating = new JPanel();
		this.customRating = new CustomRating();
		
		this.rating.add(this.customRating);
		this.north.add(this.rating);
		
	}
	
	public Message getMessage() {
		// random number to simulate noise 
		Random rand = new Random();

		Message msg = new Message(this.nameField.getText(), Constants.CONNECTED, this.port, (this.oxygen.getValue() + rand.nextInt(100)), (this.light.getValue()  + rand.nextInt(100)), (this.pollution.getValue() + rand.nextInt(100)));
		
		// before message is sent, add the values to local history for data analysis
		this.average.addLight(msg.getLight());
		this.average.addOxygen(msg.getOxygen());
		this.average.addPollution(msg.getPollution());
		return msg;
	}

	private void sendDisconnect(){
		System.out.println("DISCONNECTNG " + this.nameField.getText() + ":" + this.port + "......");
		this.client.sendDisconnect(new Message(this.nameField.getText(), Constants.DISCONNECTED, this.port, 0, 0,0));
		System.exit(0);
	}

	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) { sendDisconnect(); }
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

}
