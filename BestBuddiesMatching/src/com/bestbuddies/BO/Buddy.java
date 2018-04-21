package com.bestbuddies.BO;

import java.util.*;

public class Buddy implements Comparable<Buddy>{
	private int id;
	private boolean gender;
	private long zipcode;
	private int phone;
	private String email;
	private int age;
	private boolean married;
	private String[] interests;
	private boolean employed;
	private String[] socialize;
	private double distance;
	private int interestsMatch;
	private List<Buddy> buddySuggestedList = new ArrayList<Buddy>();
	private double ageScore;
	
	public double getAgeScore() {
		return ageScore;
	}

	public void setAgeScore(double ageScore) {
		this.ageScore = ageScore;
	}

	public void setInterestMatcht(int match) {
		this.interestsMatch = match;
	}

	public int getInterestMatcht() {
		return this.interestsMatch;
	}
	
	public void addSuggestedBuddy(Buddy buddy) {
		this.buddySuggestedList.add(buddy);
	}

	public List<Buddy> getBuddySuggestedList() {
		return buddySuggestedList;
	}
	
	public void clearBuddySuggestedList() {
		this.buddySuggestedList.clear();
	}
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public long getZipcode() {
		return zipcode;
	}
	public void setZipcode(long zipcode) {
		this.zipcode = zipcode;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isMarried() {
		return married;
	}
	public void setMarried(boolean married) {
		this.married = married;
	}
	public String[] getInterests() {
		return interests;
	}
	public void setInterests(String[] interests) {
		this.interests = interests;
	}
	public boolean isEmployed() {
		return employed;
	}
	public void setEmployed(boolean employed) {
		this.employed = employed;
	}
	public String[] getSocialize() {
		return socialize;
	}
	public void setSocialize(String[] socialize) {
		this.socialize = socialize;
	}
	@Override
	public int compareTo(Buddy b1) {
		// TODO Auto-generated method stub
		if(distance <= b1.getDistance()){
			return 0;
			
		} else if(distance > b1.getDistance()){
			return 1;
		}
		return -1;
	}
	
}
