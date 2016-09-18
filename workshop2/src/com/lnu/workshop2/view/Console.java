package com.lnu.workshop2.view;

import java.io.IOException;
import java.util.Scanner;

import com.lnu.workshop2.controller.MemberRegistry;
import com.lnu.workshop2.model.Boat;

public class Console
{
	private static MemberRegistry memberRegistry = MemberRegistry.getInstance();
	
    public static void usage() {
        System.out.println("Welcome to the member registry");
        System.out.println("Usage: ");
        System.out.println("Press 1 to create a member");
        System.out.println("Press 2 to update a member");
        System.out.println("Press 3 to delete a member");
        System.out.println("Press 4 to add a boat to member");
        System.out.println("Press 5 to remove a boat from member");
        System.out.println("Press 6 to update information for a members boat");
        
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
	        	// Create a member
	            System.out.print("Enter member name: ");
	            String name = scanner.next();
	            System.out.print("Enter member personal number: ");
	            int personalNumber = scanner.nextInt();
	            memberRegistry.addMember(name, personalNumber);
	            break;
	        } case 2: {
	        	// Update a member
	            System.out.print("Enter id of the member to update: ");
	            int id = scanner.nextInt();
	            System.out.print("Enter new member name(leave blank for default): ");
	            String name = scanner.next();
	            System.out.print("Enter new member personal number(leave blank for default): ");
	            int personalNumber = scanner.nextInt();
	            memberRegistry.updateMember(id, name, personalNumber);
	            break;
	        } case 3 : {
	        	// Delete a member
	            System.out.print("Enter id of the member to delete: ");
	            int id = scanner.nextInt();
	            memberRegistry.deleteMember(id);
	            break;
	        } case 4 : {
	        	// Remove a boat from a member
	            System.out.print("Enter id of the member to remove a boat from: ");
	            int memberId = scanner.nextInt();
	            System.out.print("Enter id of the boat to remove from member: ");
	            int boatId = scanner.nextInt();
	            memberRegistry.deleteBoatFromMember(memberId, boatId);
	            break;
	        }case 5 : {
	        	// Update a boat registered to a member
	        	System.out.print("Enter id of the member to that owns the boat that needs updated information: ");
	            int memberId = scanner.nextInt();
	            System.out.print("Enter id of the boat to update: ");
	            int boatId = scanner.nextInt();
	            System.out.print("Enter new boat type(leave blank for default): ");
	            String boatType = scanner.next();
	            System.out.print("Enter new boat length(leave blank for default): ");
	            int length = scanner.nextInt();
	            memberRegistry.updateBoatInformationForMember(memberId, boatId, boatType, length);
	            break;
	        }case 6 : {
	        	// Register a new boat to member
	        	System.out.print("Enter id of the member to register the new boat to: ");
	            int memberId = scanner.nextInt();
	            System.out.print("Enter new boat type(" + Boat.boatType.sailboat + ", "
	            		+ Boat.boatType.Kayak + ", " + Boat.boatType.MotorSailor + " or " 
	            		+ Boat.boatType.Other);
	            String boatType = scanner.next();
	            System.out.print("Enter new boat length: ");
	            int length = scanner.nextInt();
	            memberRegistry.addBoatToMember(memberId, boatType, length);
	            break;
	        }
        }
        
        scanner.close();
       

    }
    
}