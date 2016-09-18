package com.lnu.workshop2.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.lnu.workshop2.model.Member;


public class PersistanceController {
	
	private static String filePath = "./../resources/members.json";
	
	public void createMember(Member member) {
		
		JSONObject obj = new JSONObject();
		obj.put("name", member.getName());
		obj.put("personalNumber", member.getPersonalNumber());
		obj.put("id", member.getId());

		persistToFile(obj);

	}
	
	public Member retrieveMember(int id) {
		JSONParser parser = new JSONParser();

		try {
			
			Object obj = parser.parse(new FileReader(filePath));

			JSONArray jsonArray = (JSONArray) obj;

			Iterator<JSONObject> iterator = jsonArray.iterator();
			while (iterator.hasNext()) {
				JSONObject object = iterator.next();
				int memberId = ((Integer) object.get("id")).intValue();
				if(memberId == id) {
					String name = (String) object.get("name");
					int personalNumber = ((Integer) object.get("personalnumber")).intValue();
					return new Member(name, personalNumber, id);
				}
			}
			return null;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return new Member(null, id, id);
	}
	
	public Member updateMember(int id) {
		//TODO: updateMember
		return new Member(null, id, id);
	}
	public void deleteMember(int id) {
		//TODO: deleteMember
	}

	private void persistToFile(JSONObject obj) {
		try {
			
			FileWriter file = new FileWriter(filePath);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
