/** 
 * 
 *  File name: Message.java
 *  @author: Katherina Keogh
 *  Description: This file creates the a Message class which is used to create 
 *  			 an object which is sent from the Client to the Server.
 *  
 */

package een1035;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable {
	
    private String name, status;
    private int port, oxygen, light, pollution;

    public Message(String name, String status, int port, int oxygen, int light, int pollution) {
        this.name = name;
        this.status = status;
        this.port = port;
        this.oxygen = oxygen;
        this.light = light;
        this.pollution = pollution;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public int getOxygen() {
        return oxygen;
    }

    public int getLight() {
        return light;
    }

    public int getPollution() {
        return pollution;
    }

    public void display() {
        System.out.println("Message {" +
                "port=" + port +
                ", name=" + name +
                ", status=" + status +
                ", oxygen=" + oxygen +
                ", light=" + light +
                ", pollution=" + light +
                '}');
    }

}
