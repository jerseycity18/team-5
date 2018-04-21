package com.bestbuddies.match;

import com.bestbuddies.BO.Buddy;

import java.util.*;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Document;


public class MatchMaking {
	List<Buddy> buddyList = new ArrayList<Buddy>();
	List<Buddy> volunteerList = new ArrayList<Buddy>();
	
	
	public static void matchAlgo(List<Buddy> buddyList, List<Buddy> volunteerList) throws Exception{
		int n = buddyList.size();
		int m = volunteerList.size();
		//Map<Buddy, List<Buddy>> listOfMatches = new HashMap<Buddy, List<Buddy>>();
		for(int i=0;i<m;i++){
			ArrayList<Buddy> orderedBuddyList = new ArrayList<Buddy>(); 
			for(int j=0;j<n;j++){
				Buddy volunteer = volunteerList.get(i);
				Buddy buddy = buddyList.get(j);
				//double distance= getDistance(volunteer.getZipcode(), buddy.getZipcode() );
				//buddy.setDistance(distance);
				orderedBuddyList.add(buddy);
			}
			Collections.sort(orderedBuddyList);
			for(Buddy b: orderedBuddyList){
				volunteerList.get(i).addSuggestedBuddy(b);
			}
			
		}
		volunteerList = scoreAge(volunteerList);
		volunteerList = getInterestMatches(volunteerList);
		volunteerList = calculateRank(volunteerList);
		volunteerList = showBuddieswithlowestScore(volunteerList);
		
		System.out.println(volunteerList);
	}
	
	static List<Buddy> showBuddieswithlowestScore(List<Buddy> volunteerList){
		for(Buddy b: volunteerList){
			List<Buddy> sortedBuddyList = b.getBuddySuggestedList();
			b.clearBuddySuggestedList();
			//public static Comparator<Buddy>
			Collections.sort(sortedBuddyList, new Comparator<Buddy>() {
			    @Override
			    public int compare(Buddy o1, Buddy o2) {
			    	double d1 = o1.getTotalRank();
			    	double d2 = o2.getTotalRank();
			        return Double.compare(d1, d2);
			    }
			});
			for(Buddy bx: sortedBuddyList){
				b.addSuggestedBuddy(bx);
			}
		}
			return volunteerList;
	}
	
	static List<Buddy> scoreAge(List<Buddy> listOfMatches){
		int range120 = 1;
		int range2140 = 2;
		int range4160 = 3;
		int range60above = 4;
		List<Buddy> scoreByAgeList = new ArrayList<Buddy>();
		for(Buddy b: listOfMatches){
			int age = b.getAge();
			List<Buddy> blist = b.getBuddySuggestedList();
			b.clearBuddySuggestedList();
			for(Buddy b1: blist){
				int age2 = b1.getAge();
				if(age<= 20 && age2 <=20){
					b1.setAgeScore(range120);
				} else if((age>20 && age <=40) && (age2>20 && age2 <=40)){
					b1.setAgeScore(range2140);
				} else if((age>40 && age <=60) && (age2>40 && age2 <=60)){
					b1.setAgeScore(range4160);
				} else {
					b1.setAgeScore(range60above);
				}
				b.addSuggestedBuddy(b1);
			}
			scoreByAgeList.add(b);
		}
		return scoreByAgeList;
		
	}
	
	
	static double getDistance(long volunteerZip, long buddyZip) throws Exception{
		String[] volunteerlatlong = getLatLong(volunteerZip);
		String[] buddylatlong = getLatLong(buddyZip);
		int r = 6371; // average radius of the earth in km
	    double dLat = Math.toRadians(Double.valueOf(volunteerlatlong[0]) - Double.valueOf(buddylatlong[0]));
	    double dLon = Math.toRadians(Double.valueOf(volunteerlatlong[1]) - Double.valueOf(buddylatlong[1]));
	    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
	       Math.cos(Math.toRadians(Double.valueOf(volunteerlatlong[0]))) * Math.cos(Math.toRadians(Double.valueOf(buddylatlong[0]))) 
	      * Math.sin(dLon / 2) * Math.sin(dLon / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double d = r * c;
	    return d;
	}

	
	public static String[] getLatLong(long address) throws Exception
	{
	  int responseCode = 0;
	  String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder. encode(String.valueOf(address), "UTF-8") + "&sensor=true";
	  URL url = new URL(api);
	  HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
	  httpConnection.connect();
	  responseCode = httpConnection.getResponseCode();
	  if(responseCode == 200)
	  {
	    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();;
	    Document document = builder.parse(httpConnection.getInputStream());
	    XPathFactory xPathfactory = XPathFactory.newInstance();
	    XPath xpath = xPathfactory.newXPath();
	    XPathExpression expr = xpath.compile("/GeocodeResponse/status");
	    String status = (String)expr.evaluate(document, XPathConstants.STRING);
	    if(status.equals("OK"))
	    {
	       expr = xpath.compile("//geometry/location/lat");
	       String latitude = (String)expr.evaluate(document, XPathConstants.STRING);
	       expr = xpath.compile("//geometry/location/lng");
	       String longitude = (String)expr.evaluate(document, XPathConstants.STRING);
	       return new String[] {latitude, longitude};
	    }
	    else
	    {
	       throw new Exception("Error from the API - response status: "+status);
	    }
	  }
	  return null;
	}
	
	public static List<Buddy> getInterestMatches(List<Buddy> list) {
		List<Buddy> matchedList = new ArrayList<Buddy>();
		
		//ListIterator<Buddy> listIter = (ListIterator<Buddy>) list.iterator();
		for(Buddy buddy: list) {
			//Buddy buddy = listIter.next();
			
			List<Buddy> volunteerList = buddy.getBuddySuggestedList();
			
			buddy.clearBuddySuggestedList();
			
			//ListIterator<Buddy> litr = (ListIterator<Buddy>) volunteerList.iterator();
			for(Buddy volunteer: volunteerList){
				//Buddy volunteer = litr.next();
				Collection<String> volunteerInterests = new ArrayList<String>(Arrays.asList(volunteer.getInterests()));
				int v = volunteer.getInterests().length;
				volunteerInterests.removeAll(new ArrayList<String>(Arrays.asList(buddy.getInterests())));
				v = v - volunteerInterests.size();
				volunteer.setInterestMatcht(v);
				buddy.addSuggestedBuddy(volunteer);
			}
			matchedList.add(buddy);
		}
		return matchedList;
	}
	
	public static List<Buddy> calculateRank(List<Buddy> listWithSocres){
		for(Buddy b: listWithSocres){
			double totalRank = 0;
			totalRank += b.getDistance()*1;
			totalRank += b.getAgeScore()*2;
			double interestMatch = (100- (b.getInterestMatcht()/b.getInterests().length *100));
			totalRank += interestMatch*3;
			b.setTotalRank(totalRank);
		}
		return listWithSocres; 
	}

}

