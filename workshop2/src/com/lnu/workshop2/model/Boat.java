package com.lnu.workshop2.model;

public class Boat {

	private int length;
	private String type;
	public enum boatType {
		sailboat, MotorSailor, Kayak, Other 
	}
	
	public Boat(String type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}

