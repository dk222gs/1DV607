package com.lnu.workshop2.model;

public class Member {

	private String name;
	private int personalNumber;
	private int id;
	
	public Member(int id, String name, int peronalNumber) {
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
	
	
	
}
