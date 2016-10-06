package com.lnu.workshop2.model;

import java.io.Serializable;

public class Boat implements Serializable{

	private static final long serialVersionUID = -257796160740329210L;
	private int id;
	private int length;
	private String type;
	public static enum boatType {
		Sailboat, MotorSailor, Kayak, Other 
	}
	
	public Boat(int id, String type, int length) {
		this.id = id;
		this.type = type;
		this.length = length;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

