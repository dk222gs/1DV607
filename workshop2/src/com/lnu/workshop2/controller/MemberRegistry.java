package com.lnu.workshop2.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import com.lnu.workshop2.model.Member;

/*
 * Handle everything in memory and save when application exists
 *
 */
public class MemberRegistry {

	final static Logger logger = Logger.getLogger(MemberRegistry.class);
	private List<Member> memberList = new ArrayList<Member>();
	
	public void addMember(Member member) {
		memberList.add(member);
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
	
	// Delete member
	public void deleteMember(int id) {
		Iterator<Member> it = memberList.iterator();
		while (it.hasNext()) {
		  Member member = it.next();
		  if (member.getId() == id) {
		    it.remove();
		    return;
		  }
		}
		logger.warn("Unable to delete member with id: " + id);
	}
	
}