package com.lnu.workshop2.model;

public class Member {

	private String name;
	private int personalNumber;
	private int id;
	
	public Member(String name, int peronalNumber, int id) {
		this.name = name;
		this.personalNumber = personalNumber;
		this.id = id;
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
