/* The Client Class - Written by Derek Molloy for the EE402 Module
 * See: ee402.eeng.dcu.ie
 * 
 * 
 */

package een1035;

import java.net.*;
import java.io.*;

public class Client {
	
	private static int portNumber = 5050;
    private Socket socket = null;
	private String serverIP;
    private ObjectOutputStream os = null;
    private ObjectInputStream is = null;
    private ClientScreen clientGUI;
	
    public Client(String serverIP) {
		this.serverIP = serverIP;
    	if (!connectToServer(serverIP)) {
    		System.out.println("XX. Failed to open socket connection to: " + serverIP);            
    	}
    }

    private boolean connectToServer(String serverIP) {
    	try {
    		this.socket = new Socket(serverIP, portNumber);
    		this.os = new ObjectOutputStream(this.socket.getOutputStream());
    		this.is = new ObjectInputStream(this.socket.getInputStream());
    		System.out.println("00. -> Connected to Server:" + this.socket.getInetAddress() 
    				+ " on port: " + this.socket.getPort());
    		System.out.println("    -> from local address: " + this.socket.getLocalAddress() 
    				+ " and port: " + this.socket.getLocalPort());
    	} 
        catch (Exception e) {
        	System.out.println("XX. Failed to Connect to the Server at port: " + portNumber);
        	System.out.println("    Exception: " + e.toString());	
        	return false;
        }
		return true;
    }

    private void sendData() {
    	Message sensorData = this.clientGUI.getMessage();
    	System.out.println("01. -> Sending Message object to the server...");
    	this.send(sensorData);
    }

	public void sendDisconnect(Message message) {
    	System.out.println("01. -> Sending Disconnect message to the server...");
    	this.send(message);
		try{
			this.socket.close();
		} catch (Exception e) {
			System.out.println("XX. Failed to Disconnect to the Server at port: " + portNumber);
        	System.out.println("    Exception: " + e.toString());	
		}
		
    	System.out.println("04. -- Disconnected from Server.");
    }
	
    private void send(Message message) {
		try {
		    System.out.println("02. -> Sending a Message...");
			message.display();
		    os.writeObject(message);
		    os.flush();
			System.out.println("03. -> Message sent.");
		} 
	    catch (Exception e) {
		    System.out.println("XX. Exception Occurred on Sending:" +  e.toString());
			System.out.println("XX. Attempting to reconnect to:" + this.serverIP);
			connectToServer(this.serverIP);
		}
    }
    
    private void getGUI(Client client) {
    	try {
    		this.clientGUI = new ClientScreen(client, this.socket.getLocalPort());
    	} catch (Exception e) {
    		 System.out.println("XX. Exception Occurred on opening GUI:" + e.toString());
    	}
		
    }

    public static void main(String args[]) 
    {
    	System.out.println("**. Java Client Application - EE402 OOP Module, DCU");
    	if(args.length==1){
    		Client theApp = new Client(args[0]);
    		theApp.getGUI(theApp);
			Thread taskThread = new Thread(() -> {
				while (true) {
					try {
						theApp.sendData();
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						System.err.println("Task thread interrupted: " + e.getMessage());
						break;
					}
				}
			});
			taskThread.start();
    		
		}
    	else
    	{
    		System.out.println("Error: you must provide the address of the server");
    		System.out.println("Usage is:  java Client x.x.x.x  (e.g. java Client 192.168.7.2)");
    		System.out.println("      or:  java Client hostname (e.g. java Client localhost)");
    	}    
    	System.out.println("**. End of Application.");
    }
}