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
	public boolean loggedIn = false;
	
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
		if(loggedIn) {
			int id = rand.nextInt(50) + 1;
			Member member= new Member(id, name, personalNumber);
			memberList.add(member);
			logger.info("Member with id: " + id + " added to registry");
			return member;
		} else {
			logger.warn("Log in as admin to create new members");
			return null;
		}
	}
	
	public Boat addBoatToMember(int memberId, String type, int length) {
		if(loggedIn) {
			if(validateBoatType(type)) {
				int id = rand.nextInt(50) + 1;
				Boat boat = new Boat(id, type, length);
				retrieveMember(memberId).addBoat(boat);
				logger.info("Boat: " + boat.getType() + " added to member with id: " + memberId);
				return boat;
			} else {
				return null;
			}
		} else {
			logger.warn("Log in as admin to add boats to members");
			return null;
		}
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
		if(loggedIn) {
			for(int i=0;i<this.memberList.size();i++){
		        if(this.memberList.get(i).getId() == id){
		            if(personalNumber != 0) {
		            	logger.info("Updating personalNumber from: " + this.memberList.get(i).getPersonalNumber() +
		            			" to: " + personalNumber + " for member with id: " + this.memberList.get(i).getId());
		            	this.memberList.get(i).setPersonalNumber(personalNumber);
		            }
		            if(name != null) {
		            	logger.info("Updating name from: " + this.memberList.get(i).getName() +
		            			" to: " + name + " for member with id: " + this.memberList.get(i).getId());
		            	this.memberList.get(i).setName(name);
		            }
		            return this.memberList.get(i);
		        }
			}
			logger.warn("No member found with id: " + id);
			return null;
		} else {
			logger.warn("Log in as admin to update members");
			return null;
		}
	}
	
	// Update boat information for member
	public Boat updateBoatInformationForMember(int memberId, int boatId, String boatType, int length) {
		if(loggedIn) {
			if(validateBoatType(boatType)) {
				return this.retrieveMember(memberId).updateBoat(boatId, boatType, length);
			} else {
				return null;
			}
		} else {
			logger.warn("Log in as admin to update boat information");
			return null;
		}
	}
	
	// Delete member
	public void deleteMember(int id) {
		if(loggedIn) {
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
		} else {
			logger.warn("Log in as admin to delete members from registry");
		}
	}
	
	public void deleteBoatFromMember(int memberId, int boatId) {
		if(loggedIn) {
			this.retrieveMember(memberId).removeBoat(boatId);
		} else {
			logger.warn("Log in as admin to delete boats from members");
		}
	}
	
	public void storeMemberList() {
		PersistanceManager persistanceManager = new PersistanceManager();
		persistanceManager.storeObjectToFile(memberList);
	}
	
	private boolean validateBoatType(String type) {
		if(type.equalsIgnoreCase(Boat.boatType.Sailboat.toString()) || type.equalsIgnoreCase(Boat.boatType.Kayak.toString()) || 
				type.equalsIgnoreCase(Boat.boatType.MotorSailor.toString()) || type.equalsIgnoreCase(Boat.boatType.Other.toString())) {
			return true;
		} else {
			logger.warn("Invalid boat type entered " + type + ", please add one of the types (" + 
					Boat.boatType.Sailboat + ", " + Boat.boatType.Kayak + ", " + Boat.boatType.MotorSailor + 
					" or " + Boat.boatType.Other + ")");
			return false;
		}
	}
	
	public String searchMembersBasedOnNames(String searchString) {
		List<Member> foundMembers = new ArrayList<Member>();
		for(Member member : memberList) {
	        if(member.getName() != null && member.getName().startsWith(searchString)) {
	        	foundMembers.add(member);
		    }
		}
		return this.toString(false, foundMembers);
	}
	
	public String toString(boolean verbose, List<Member> searchList) {
		StringBuilder builder = new StringBuilder();
		List<Member> localMemberList;
		if(searchList == null) {
			localMemberList = memberList;
		} else {
			localMemberList = searchList;
		}
		
		if(verbose) {
			for (Member member: localMemberList) {
			    builder.append("Member name: " + member.getName()); 
			    builder.append(" personal number: " + member.getPersonalNumber());
			    builder.append(" memberId: " + member.getId());
			    builder.append(System.getProperty("line.separator"));
			    List<Boat> boatList = member.getBoats();
			    for (Boat boat: boatList) {
			    	builder.append("- Boat id: " + boat.getId());
			    	builder.append(System.getProperty("line.separator"));
			    	builder.append("  Type: " + boat.getType());
			    	builder.append(System.getProperty("line.separator"));
			    	builder.append("  Length: " + boat.getLength());
			    	builder.append(System.getProperty("line.separator"));
			    }
			}
			return builder.toString();
		} else {
			for (Member member: localMemberList) {
			    builder.append("Member name: " + member.getName());
			    builder.append(" memberId: " + member.getId()); 
			    builder.append(" boats: " + member.getBoats().size());
			    builder.append(System.getProperty("line.separator"));
			}
			return builder.toString();
		}
	}
	
}
