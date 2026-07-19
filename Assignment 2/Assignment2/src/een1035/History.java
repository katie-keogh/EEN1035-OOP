/** 
 * 
 *  File name: History.java
 *  @author: Katherina Keogh
 *  Description: This file creates the a History class which is used to store 
 *  			 the historical data. 
 */

package een1035;

public class History {
	
	private static final int MAX_SIZE = 10;
	private int port;
	private String name, status;
	private Integer[] oxygen = new Integer[MAX_SIZE], light = new Integer[MAX_SIZE], pollution = new Integer[MAX_SIZE];
	
    public History(int port, String status, String name, Integer oxygen, Integer light, Integer pollution) {
        this.port = port;
        this.status = status;
        this.name = name;
        addOxygen(oxygen);
        addLight(light);
        addPollution(pollution);
    }

    public int getPort() {
        return this.port;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getStatus() {
        return this.status;
    }

    public Integer[] getAllOxygen() {
        return this.oxygen;
    }

    public Integer[] getAllLight() {
        return this.light;
    }

    public Integer[] getAllPollution() {
        return this.pollution;
    }
    
    public Integer getMostRecentOxygen() {
        return this.oxygen[this.oxygen.length -1 ];
    }

    public Integer getMostRecentLight() {
        return this.light[this.light.length - 1];
    }

    public Integer getMostRecentPollution() {
        return this.pollution[this.pollution.length - 1];
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setStatus(String status) {
    	this.status = status;
    }
    
    public void addOxygen(Integer newValue) {
    	this.oxygen = shiftList(this.oxygen);
    	this.oxygen[this.oxygen.length-1] = new Integer(newValue);
    }
    
    public void addLight(Integer newValue) {
    	this.light = shiftList(this.light);
    	this.light[this.light.length-1] = new Integer(newValue);
    }
    
    public void addPollution(Integer newValue) {
    	this.pollution = shiftList(this.pollution);
    	this.pollution[this.pollution.length-1] = new Integer(newValue);
    }
    
    private String displayList(Integer[] list) {
    	String stringOfInts = new String();
    	for (int i = 0; i < list.length - 1; i++) {
    		if(list[i] == null) {
    			i = 0;
    		}
    		stringOfInts = stringOfInts + list[i] + " ";
    	}
    	return stringOfInts;
    }
	
    private Integer[] shiftList(Integer[] intList) {
    	for (int i = 0; i < intList.length - 1; i++) {
    		intList[i] = intList[i + 1];
        }
    	 return intList;
    }
    
    public void display() {
        System.out.println("Average{" +
                "port=" + port +
                ", name=" + name +
                ", status=" + status +
                ", oxygen=" + displayList(this.oxygen) +
                ", light=" + displayList(this.light) +
                ", pollution=" + displayList(this.pollution) +
                '}');
    }
    

}
