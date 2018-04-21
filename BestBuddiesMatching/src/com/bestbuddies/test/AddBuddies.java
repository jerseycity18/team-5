package com.bestbuddies.test;

import com.bestbuddies.BO.*;
import com.bestbuddies.match.*;

import java.util.*;

public class AddBuddies {
	public static void main(String[] args) throws Exception {
		Buddy b1 = new Buddy();
		Buddy b2 = new Buddy();
		Buddy b3 = new Buddy();
		Buddy b4 = new Buddy();
		Buddy b5 = new Buddy();
		Buddy b6 = new Buddy();
		/*b1.setZipcode(Long.valueOf("07812"));
		b2.setZipcode(Long.valueOf("08812"));
		b3.setZipcode(Long.valueOf("07307"));
		b4.setZipcode(Long.valueOf("07925"));
		b5.setZipcode(Long.valueOf("07306"));
		b6.setZipcode(Long.valueOf("07123"));*/
		b1.setDistance(10);
		b1.setDistance(20);
		b1.setDistance(30);
		b1.setDistance(40);
		b1.setDistance(50);
		b1.setDistance(60);
		List<Buddy> listOfBuddies = new ArrayList<Buddy>();
		List<Buddy> listOfVounteers = new ArrayList<Buddy>();
		listOfBuddies.add(b1);
		listOfBuddies.add(b2);
		listOfBuddies.add(b3);
		listOfVounteers.add(b4);
		listOfVounteers.add(b5);
		listOfVounteers.add(b6);
		MatchMaking.matchAlgo(listOfBuddies,listOfVounteers);
		
		
	}
}
