package com.lnu.workshop2.view;

import java.io.IOException;
import java.util.Scanner;

import com.lnu.workshop2.controller.MemberRegistry;
import com.lnu.workshop2.model.Member;

public class Console
{
	private static MemberRegistry memberRegistry = MemberRegistry.getInstance();
	
    public static void usage() {
        System.out.println("Welcome to the member registry");
        System.out.println("Usage: ");
        System.out.println("Press 1 to create a member");
        System.out.println("Press 2 to update a member");
        System.out.println("Press 3 to delete a member");
    }

    public static void main(String[] args) throws IOException {
    	// create a scanner so we can read the command-line input
        Scanner scanner = new Scanner(System.in);
        
        // promt for option
        usage();

        // get user input as a int
        int option = scanner.nextInt();

        switch(option) {
	        case 1 : {
	        	// prompt for member name and personal number
	            System.out.print("Enter member name: ");
	            String name = scanner.next();
	            System.out.print("Enter member personal number: ");
	            int personalNumber = scanner.nextInt();
	            memberRegistry.addMember(name, personalNumber);
	            break;
	        } case 2: {
	        	// prompt for member id
	            System.out.print("Enter id of the member to update: ");
	            int id = scanner.nextInt();
	            System.out.print("Enter new member name(leave blank for default): ");
	            String name = scanner.next();
	            System.out.print("Enter new member personal number(leave blank for default): ");
	            int personalNumber = scanner.nextInt();
	            memberRegistry.updateMember(id, name, personalNumber);
	            break;
	        } case 3 : {
	        	// prompt for member id
	            System.out.print("Enter id of the member to delete: ");
	            int id = scanner.nextInt();
	            memberRegistry.deleteMember(id);
	            break;
	        }
        }
       

    }
    
}