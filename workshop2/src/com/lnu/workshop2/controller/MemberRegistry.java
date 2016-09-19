package com.lnu.workshop2.controller;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.lnu.workshop2.model.Boat;
import com.lnu.workshop2.model.Member;

/*
 * Handle everything in memory and save when application exists
 *
 */
public class MemberRegistry implements Serializable{

	private static final long serialVersionUID = -1384776970816710748L;
	private static MemberRegistry INSTANCE = new MemberRegistry();
	
	final static Logger logger = Logger.getLogger(MemberRegistry.class);
	private List<Member> memberList = new ArrayList<Member>();
	private PersistanceManager persistanceManager = new PersistanceManager();
	private Random rand = new Random();
	
	private MemberRegistry() {
		File f = new File(CommonProperties.serializableFilePath);
		if(f.exists()) { 
		    memberList = persistanceManager.getMemberList();
		}
	}
	
	public static MemberRegistry getInstance() {
		return INSTANCE;
	}
	
	// Add member and generate ID
	public Member addMember(String name, int personalNumber) {
		int id = rand.nextInt(50) + 1;
		Member member= new Member(id, name, personalNumber);
		memberList.add(member);
		logger.info("Member with id: " + id + " added to registry");
		return member;
	}
	
	public Boat addBoatToMember(int memberId, String type, int length) {
		int id = rand.nextInt(50) + 1;
		Boat boat = new Boat(id, type, length);
		retrieveMember(memberId).addBoat(boat);
		logger.info("Boat: " + boat.toString() + " added to member with id: " + memberId);
		return boat;
	}
	
	
	// Retrieve member
	public Member retrieveMember(int id) {
		for(int i=0;i<this.memberList.size();i++){
	        if(this.memberList.get(i).getId() == id){
	            return this.memberList.get(i);
	        }
		}
		logger.warn("No member found with id: " + id);
		return null; 
	}
	
	// Update member
	public Member updateMember(int id, String name, int personalNumber) {
		for(int i=0;i<this.memberList.size();i++){
	        if(this.memberList.get(i).getId() == id){
	            if(personalNumber != 0) {
	            	logger.info("Updating personalNumber from: " + this.memberList.get(i).getPersonalNumber() +
	            			"to: " + personalNumber + " for member with id: " + this.memberList.get(i).getId());
	            	this.memberList.get(i).setPersonalNumber(personalNumber);
	            }
	            if(name != null) {
	            	logger.info("Updating name from: " + this.memberList.get(i).getName() +
	            			"to: " + name + " for member with id: " + this.memberList.get(i).getId());
	            	this.memberList.get(i).setName(name);
	            }
	            return this.memberList.get(i);
	        }
		}
		logger.warn("No member found with id: " + id);
		return null;
	}
	
	// Update member
	public Boat updateBoatInformationForMember(int memberId, int boatId, String boatType, int length) {
		return this.retrieveMember(memberId).updateBoat(boatId, boatType, length);
	}
	
	// Delete member
	public void deleteMember(int id) {
		Iterator<Member> it = memberList.iterator();
		while (it.hasNext()) {
		  Member member = it.next();
		  if (member.getId() == id) {
		    it.remove();
		    logger.warn("Member with id: " + id + " removed from registry");
		    return;
		  }
		}
		logger.warn("Unable to delete member with id: " + id);
	}
	
	public void deleteBoatFromMember(int memberId, int boatId) {
		this.retrieveMember(memberId).removeBoat(boatId);
	}
	
	public void storeMemberList() {
		PersistanceManager persistanceManager = new PersistanceManager();
		persistanceManager.storeObjectToFile(memberList);
	}
	
	public String toString(boolean verbose) {
		StringBuilder builder = new StringBuilder();
		
		if(verbose) {
			for (Member member: memberList) {
			    builder.append("Member name: " + member.getName()); 
			    builder.append(" personal number: " + member.getPersonalNumber());
			    builder.append(" memberId: " + member.getId());
			    builder.append(System.getProperty("line.separator"));
			    List<Boat> boatList = member.getBoats();
			    for (Boat boat: boatList) {
			    	builder.append("- Boat id: " + boat.getId());
			    	builder.append("-    type: " + boat.getType());
			    	builder.append("-  length: " + boat.getLength());
			    }
			}
			return builder.toString();
		} else {
			for (Member member: memberList) {
			    builder.append("Member name: " + member.getName());
			    builder.append(" memberId: " + member.getId()); 
			    builder.append(" boats: " + member.getBoats().size());
			    builder.append(System.getProperty("line.separator"));
			}
			return builder.toString();
		}
	}
	
}
