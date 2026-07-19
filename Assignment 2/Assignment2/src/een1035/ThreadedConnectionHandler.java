/* The Connection Handler Class - Written by Derek Molloy for the EE402 Module
 * See: ee402.eeng.dcu.ie
 */

package een1035;

import java.net.*;
import java.io.*;

public class ThreadedConnectionHandler extends Thread
{
    private Socket clientSocket = null;
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private ServerScreen serverGUI;

    public ThreadedConnectionHandler(Socket clientSocket, ServerScreen serverGUI) {
        this.clientSocket = clientSocket;
        this.serverGUI = serverGUI;
    }

    public void run() {
         try {
            this.is = new ObjectInputStream(clientSocket.getInputStream());
            this.os = new ObjectOutputStream(clientSocket.getOutputStream());
            while (this.readMessage()) {}
         } 
         catch (IOException e) 
         {
        	System.out.println("XX. There was a problem with the Input/Output Communication:");
            e.printStackTrace();
         }
    }

    private boolean readMessage() {
        Message s = null;
        try {
            s = (Message) is.readObject();
        } 
        catch (Exception e){ 
        	this.closeSocket();
            return false;
        }
        this.serverGUI.setValues(s);
        
        return true;
    }
    
    public void closeSocket() { //gracefully close the socket connection
        try {
            this.os.close();
            this.is.close();
            this.clientSocket.close();
        } 
        catch (Exception e) {
            System.out.println("XX. " + e.getStackTrace());
        }
    }
}