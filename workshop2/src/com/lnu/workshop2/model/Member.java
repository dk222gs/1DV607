package com.lnu.workshop2.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

public class Member {

	private String name;
	private int personalNumber;
	private int id;
	private List<Boat> boatList = new ArrayList<Boat>();
	
	final static Logger logger = Logger.getLogger(Member.class);
	
	public Member(int id, String name, int personalNumber) {
		this.id = id;
		this.name = name;
		this.personalNumber = personalNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPersonalNumber() {
		return personalNumber;
	}

	public void setPersonalNumber(int personalNumber) {
		this.personalNumber = personalNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void addBoat(Boat boat) {
		this.boatList.add(boat);
	}
	
	public List<Boat> getBoats() {
		return this.boatList;
	}
	
	public Boat updateBoat(int boatId, String type, int length) {
		for(int i=0;i<this.boatList.size();i++){
	        if(this.boatList.get(i).getId() == boatId){
	            if(type != null) {
	            	this.boatList.get(i).setType(type);
	            }
	            if(length != 0) {
	            	this.boatList.get(i).setLength(length);
	            }
	            return this.boatList.get(i);
	        }
		}
		logger.warn("No boat found with id: " + id);
		return null;
	}
	
	// Move to memberRegistry
	public void removeBoat(int boatId) {
		Iterator<Boat> it = boatList.iterator();
		while (it.hasNext()) {
		  Boat boat = it.next();
		  if (boat.getId() == boatId) {
		    it.remove();
		    logger.warn("Boat with id: " + id + " removed from member with name: " + this.getName());
		    return;
		  }
		}
		logger.warn("Unable to delete boat with id: " + boatId);
	}
	
}
