/** 
 * 
 *  File name: AnalysisService.java
 *  @author: Katherina Keogh
 *  Description: This file creates the an Analysis Service which is used to calculate 
 *  			 metric or analyze the data.
 *  
 */

package een1035;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

public class AnalysisService {

	
	public static Integer calculateAverage(String type, List<History> listOfClients) {
		Integer noOfClients = new Integer(listOfClients.size());
		Integer sum = new Integer(0);
		listOfClients = listOfClients.stream()
	             .filter(client -> client.getStatus() != Constants.CONNECTED)
	             .collect(Collectors.toList());
		if(noOfClients != 0) {
			if(type == Constants.OXYGEN) {
				for(History hist : listOfClients) {
					sum = sum + hist.getMostRecentOxygen();				
				}
				return sum / noOfClients;
			} else if (type == Constants.LIGHT) {
				for(History hist : listOfClients) {
					sum = sum + hist.getMostRecentLight();				
				}
				return  sum / noOfClients;
				
			} else if (type == Constants.POLLUTION) {
				for(History hist : listOfClients) {
					sum = sum + hist.getMostRecentPollution();				
				}
				return  sum / noOfClients;
			}
		}
		
		return 0;
		
	}
	
	public static Integer getHighestReading(String type, List<History> listOfClients) {
		Integer highest = new Integer(0);
		listOfClients = listOfClients.stream()
	             .filter(client -> client.getStatus() != Constants.CONNECTED)
	             .collect(Collectors.toList());
		if(type == Constants.OXYGEN) {
			for(History hist : listOfClients) {
				if(hist.getMostRecentOxygen() > highest) {
					highest = hist.getMostRecentOxygen();
				}
			}
		} else if (type == Constants.LIGHT) {
			for(History hist : listOfClients) {
				if(hist.getMostRecentLight() > highest) {
					highest = hist.getMostRecentLight();
				}				
			}
			
		} else if (type == Constants.POLLUTION) {
			for(History hist : listOfClients) {
				if(hist.getMostRecentPollution() > highest) {
					highest = hist.getMostRecentPollution();
				}		
			}
		}
		
		return highest;
		
	}
	
	public static Integer getLowestReading(String type, List<History> listOfClients) {
		Integer lowest = new Integer(0);
		listOfClients = listOfClients.stream()
	             .filter(client -> client.getStatus() != Constants.CONNECTED)
	             .collect(Collectors.toList());
		if(listOfClients.size() > 0) {
			if(type == Constants.OXYGEN) {
				lowest = listOfClients.get(0).getMostRecentOxygen();
				for(History hist : listOfClients) {
					if(hist.getMostRecentOxygen() < lowest) {
						lowest = hist.getMostRecentOxygen();
					}
				}
			} else if (type == Constants.LIGHT) {
				lowest = listOfClients.get(0).getMostRecentLight();
				for(History hist : listOfClients) {
					if(hist.getMostRecentLight() < lowest) {
						lowest = hist.getMostRecentLight();
					}				
				}
				
			} else if (type == Constants.POLLUTION) {
				lowest = listOfClients.get(0).getMostRecentPollution();
				for(History hist : listOfClients) {
					if(hist.getMostRecentPollution() < lowest) {
						lowest = hist.getMostRecentPollution();
					}		
				}
			}
		}
		return lowest;
		
	}
	
	public static String getRating(History average) {
		String oxygen = getOxygenRating(average.getMostRecentOxygen());
		String light = getLightRating(average.getMostRecentLight());
		String pollution = getPollutionRating(average.getMostRecentPollution());
		
		int rating = 0;
		
		if (oxygen.equals(Constants.GOOD)) {
	        rating += 2;
	    } else if (oxygen.equals(Constants.FAIR)) {
	        rating += 1;
	    }

	    if (light.equals(Constants.GOOD)) {
	        rating += 2;
	    } else if (light.equals(Constants.FAIR)) {
	        rating += 1;
	    }
	

	    if (pollution.equals(Constants.GOOD)) {
	        rating += 2;
	    } else if (pollution.equals(Constants.FAIR)) {
	        rating += 1;
	    }

	    if (rating >= 5) {
	        return Constants.GOOD;
	    } else if (rating >= 3) {
	        return Constants.FAIR;
	    } else {
	        return Constants.POOR;
	    }
	}
	
	
	public static String getOxygenRating(int average) {
		if(average <= Constants.OXYGEN_GOOD) {
			return Constants.GOOD;
		} else if (average >= Constants.OXYGEN_BAD) {
			return Constants.POOR;
		} else {
			return Constants.FAIR;
		}
	}
	
	public static String getLightRating(int average) {
		if(average >= Constants.LIGHT_GOOD) {
			return Constants.GOOD;
		} else if (average <= Constants.LIGHT_BAD) {
			return Constants.POOR;
		} else {
			return Constants.FAIR;
		}
	}
	
	public static String getPollutionRating(int average) {
		if(average <= Constants.POLLUTION_NORMAL) {
			return Constants.GOOD;
		} else if (average >= Constants.POLLUTION_BAD) {
			return Constants.POOR;
		} else {
			return Constants.FAIR;
		}
	}
	
	public static Color getRatingColor(String rating) {
	       if(rating == Constants.GOOD) {
	    	   return Color.green;
	    	   
	       } else if (rating == Constants.FAIR) {
	    	   return Color.YELLOW;
	       } else {
	    	   return Color.red;
	       }
	}

}
