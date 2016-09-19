package com.lnu.workshop2.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.lnu.workshop2.model.Member;

public class PersistanceManager {
	
	final static Logger logger = Logger.getLogger(PersistanceManager.class);
	
	public void storeObjectToFile(Object object) {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(CommonProperties.serializableFilePath);
		 
			ObjectOutputStream oos = new ObjectOutputStream(fileOut);
			oos.writeObject(object);
			oos.close();
			logger.info("Memberlist saved to file");
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public List<Member> getMemberList() {
		FileInputStream fileOut;
		try {
			fileOut = new FileInputStream(CommonProperties.serializableFilePath);
		 
			ObjectInputStream ois = new ObjectInputStream(fileOut);
			List<Member> memberList = (List<Member>)ois.readObject();
			ois.close();
			logger.info("Memberlist retrieved from file");
			return memberList;
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
}
