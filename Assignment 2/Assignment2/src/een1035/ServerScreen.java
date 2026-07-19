/** 
 * 
 *  File: ServerScreen.java
 *  Author: Katherina Keogh
 *  Description: This file creates the GUI and it components for the user to interact with the server. 
 *  
 */



package een1035;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ServerScreen extends Frame implements WindowListener {
	
	private JPanel oxygenPanel, lightPanel, pollutionPanel, graph, rating, list, connectionsPanel;
	private CustomGraph customGraph;
	private CustomGauge oxygenGauge, lightGauge, pollutionGauge ;
	private JLabel connectionsLabel;
	private List<History> listOfClients = new ArrayList<History>();
//	private JTable clientTable;
//	private DefaultTableModel tableModel;
	private History averageHist; 
	private CustomRating customRating;
	
	public ServerScreen(){
		super("Server");
		
		// initialise averageHist with all 0s since no data has been recieved
		this.averageHist = new History(0, "Average", "N/A", 0, 0, 0);
		int i = 0;
		while(i < 10) {
			i++;
			this.averageHist.addOxygen(0);
			this.averageHist.addLight(0);
			this.averageHist.addPollution(0);		
		}
		
		// creating the components
		createOxygenPanel();
		createLightPanel();
		createPollutionPanel();
		createConnectionsPanel();
		createCustomGraphPanel();
		createRatingPanel();				
		
		// creating the north panel
		JPanel north = new JPanel();
		north.add(this.oxygenPanel);
		north.add(this.lightPanel);
		north.add(this.pollutionPanel);
		
		// creating the south pane;
		JPanel south = new JPanel();
		south.add(this.graph);
		
		//creating the centre panel
		JPanel centre = new JPanel();
		centre.setLayout(new BoxLayout(centre, BoxLayout.Y_AXIS)); 
		centre.add(this.rating);
		centre.add(this.connectionsPanel);
		
		// adding panels to frame
		this.add(south, BorderLayout.SOUTH);
		this.add(north, BorderLayout.NORTH);
		this.add(centre, BorderLayout.CENTER);
		
		this.addWindowListener(this);
		this.pack();
		this.setVisible(true);

		// loop to update values every 5 seconds
		Thread taskThread = new Thread(() -> {
			while (true) {
				try {
					updateComponents();
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					System.err.println("Task thread interrupted: " + e.getMessage());
					break; 
				}
			}
		});
		taskThread.start();
	}
	
	
	public void createCustomGraphPanel() {
		this.graph = new JPanel();
		this.customGraph = new CustomGraph();
        this.graph.setPreferredSize(new Dimension(380, 300));
		this.graph.add(this.customGraph, BorderLayout.NORTH);
		this.graph.setLayout(new BoxLayout(this.graph, BoxLayout.Y_AXIS)); 

    }
	
	private void createOxygenPanel() {
		this.oxygenPanel = new JPanel();
		this.oxygenPanel.add(new JLabel(Constants.OXYGEN));
		this.oxygenGauge = new CustomGauge(AnalysisService.getLowestReading(Constants.OXYGEN, this.listOfClients), AnalysisService.calculateAverage(Constants.OXYGEN, this.listOfClients), AnalysisService.getHighestReading(Constants.OXYGEN, this.listOfClients));
		this.oxygenPanel.add(this.oxygenGauge, BorderLayout.CENTER);
	}
	
	private void createLightPanel() {
		this.lightPanel = new JPanel();
		this.lightPanel.add(new JLabel(Constants.LIGHT));
		this.lightGauge = new CustomGauge(AnalysisService.getLowestReading(Constants.LIGHT, this.listOfClients), AnalysisService.calculateAverage(Constants.LIGHT, this.listOfClients), AnalysisService.getHighestReading(Constants.LIGHT, this.listOfClients));
		this.lightPanel.add(this.lightGauge);
	}
	
	private void createPollutionPanel() {
		this.pollutionPanel = new JPanel();
		this.pollutionPanel.add(new JLabel(Constants.POLLUTION));
		this.pollutionGauge = new CustomGauge(AnalysisService.getLowestReading(Constants.POLLUTION, this.listOfClients), AnalysisService.calculateAverage(Constants.POLLUTION, this.listOfClients), AnalysisService.getHighestReading(Constants.POLLUTION, this.listOfClients));
		this.pollutionPanel.add(this.pollutionGauge);
	}
	
	private void createRatingPanel() {
		this.rating = new JPanel();
		this.customRating = new CustomRating();
		this.customRating.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.rating.add(this.customRating);
	}
	
	private void createConnectionsPanel() {
		this.connectionsPanel = new JPanel();
		
//		String[] columnNames = {"Name", "Port Number"};		
//		
//		this.tableModel = new DefaultTableModel(columnNames, 0);
//			
//	  	this.clientTable = new JTable(tableModel);
//	  	this.connectionsPanel.add(clientTable);
		
		this.connectionsPanel = new JPanel();
		this.connectionsLabel = new JLabel("No devices have connected yet.");
		this.connectionsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		this.connectionsPanel.add(this.connectionsLabel);
		
	}
	
	// sets the new values when the server recieves a message
	public void setValues(Message message) {

		// first checks if the list of clients contains the client that sent the message.
		// using the port kind of like an ID since it changed be chnaged while running
		if(checkIfListContains(message.getPort())) {
			// if it is in the list then add values to the existing object
			History currentAvg = this.listOfClients.stream()
					.filter(client -> client.getPort() == message.getPort())
					.findFirst()
					.orElse(null);
			currentAvg.setName(message.getName());
			currentAvg.setStatus(message.getStatus());
			currentAvg.addOxygen(message.getOxygen());
			currentAvg.addLight(message.getLight());
			currentAvg.addPollution(message.getPollution());
		} else {
			// if its not in the list then create a new object 
			this.listOfClients.add(new History(message.getPort(), message.getStatus(), message.getName(), new Integer(message.getOxygen()), new Integer(message.getLight()), new Integer(message.getPollution())));
		}
	}
	
	private void updateComponents() {
		this.averageHist.display();
		this.customRating.updateRating(AnalysisService.getRating(this.averageHist));
		displayAverage();
		displayConnections();
		this.oxygenGauge.setValues(AnalysisService.getLowestReading(Constants.OXYGEN, this.listOfClients), AnalysisService.calculateAverage(Constants.OXYGEN, this.listOfClients), AnalysisService.getHighestReading(Constants.OXYGEN, this.listOfClients));
		this.lightGauge.setValues(AnalysisService.getLowestReading(Constants.LIGHT, this.listOfClients), AnalysisService.calculateAverage(Constants.LIGHT, this.listOfClients), AnalysisService.getHighestReading(Constants.LIGHT, this.listOfClients));
		this.pollutionGauge.setValues(AnalysisService.getLowestReading(Constants.POLLUTION, this.listOfClients), AnalysisService.calculateAverage(Constants.POLLUTION, this.listOfClients), AnalysisService.getHighestReading(Constants.POLLUTION, this.listOfClients));
		
	};
	
	private Boolean checkIfListContains(Integer port) {
		for (History avg : this.listOfClients) {
			if (avg.getPort() == port) {
				return true;
	         }
	     }
		return false;
	}
	
	
	private void displayAverage() {
		if(this.listOfClients.size() > 0) {
			this.averageHist.addOxygen(AnalysisService.calculateAverage(Constants.OXYGEN, this.listOfClients));
			this.averageHist.addLight(AnalysisService.calculateAverage(Constants.LIGHT, this.listOfClients));
			this.averageHist.addPollution(AnalysisService.calculateAverage(Constants.POLLUTION, this.listOfClients));	
			this.customGraph.updateValues(this.averageHist.getAllOxygen(),this.averageHist.getAllLight(), this.averageHist.getAllPollution() );
		}
		 
	}
	
	private void displayConnections() {
		if(this.listOfClients.size() > 0) {
			String listOfCons = "<html>";
			for(History client : listOfClients) {
				
				listOfCons = listOfCons + client.getName() + ":" + client.getPort() + " " + client.getStatus().toLowerCase() +  "<br/>";
			}
			listOfCons = listOfCons + "</html>";
			
			this.connectionsLabel.setText(listOfCons);
		}
		// I tried to implement a table but it was quite challanging to make it dynamic
//
//		for(History client : listOfClients) {
//			this.tableModel.addRow(new Object[]{client.getName(), client.getPort()});
//		}
	}
	
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) { System.exit(0); }
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

}
