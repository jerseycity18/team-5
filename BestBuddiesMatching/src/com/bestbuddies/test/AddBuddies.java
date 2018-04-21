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
		b2.setDistance(20);
		b3.setDistance(30);
		b4.setDistance(40);
		b5.setDistance(50);
		b6.setDistance(60);
		b1.setInterests(new String[]{"Adventure","Baking","Basketball","Bowling","Comedy","Cooking","Honesty","Manicures","Movies","Park","Restaurants","Tennis","Theater","Yoga","Museums"});
		b2.setInterests(new String[]{"Adventure","Bowling","Comedy","Cooking","Honesty","Manicures","Movies","Park","Restaurants","Tennis","Theater","Yoga","Museums"});
		b3.setInterests(new String[]{"Adventure","Baking","Comedy","Cooking","Honesty","Manicures","Movies","Park","Restaurants","Tennis","Theater","Yoga","Museums"});
		b4.setInterests(new String[]{"Adventure","Baking","Basketball","Bowling","Honesty","Manicures","Movies","Park","Restaurants","Tennis","Theater","Yoga","Museums"});
		b5.setInterests(new String[]{"Adventure","Bowling","Comedy","Cooking","Honesty","Manicures","Movies","Park","Restaurants","Tennis","Theater","Yoga","Museums"});
		b6.setInterests(new String[]{"Adventure","Baking","Basketball","Bowling","Comedy","Manicures","Movies","Park","Restaurants"});
		
		ArrayList<Buddy> listOfBuddies = new ArrayList<Buddy>();
		ArrayList<Buddy> listOfVounteers = new ArrayList<Buddy>();
		listOfBuddies.add(b1);
		listOfBuddies.add(b2);
		listOfBuddies.add(b3);
		listOfVounteers.add(b4);
		listOfVounteers.add(b5);
		listOfVounteers.add(b6);
		MatchMaking.matchAlgo(listOfBuddies,listOfVounteers);
		
		
	}
}
