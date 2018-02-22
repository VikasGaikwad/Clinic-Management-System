package com.bridgelabz;

import java.io.IOException;
import java.util.Scanner;

import org.json.simple.parser.ParseException;

/**purpose: This program is used to manage a list of Doctors associated with the Clinic.
 * @author bridgeit
 * @since - 16-feb-2018
 */
public class Main {
	static String patientPath="/home/bridgeit/Documents/Programs/Clinic Management System/src/jsonFiles/patient.json";
	static String doctorPath="/home/bridgeit/Documents/Programs/Clinic Management System/src/jsonFiles/doctor.json";
	public static void main(String[] args) throws IOException, ParseException {
		Scanner scanner=new Scanner(System.in);
		char ch;
		do {
			System.out.println("\n\n\t*********************************");
			System.out.println("\t****Clinic Management System*****");
			System.out.println("\t*********************************\n\n");
			System.out.println("\t1. Add Patient.\n\t2. Read Doctors List.\n\t3. Search Doctors And Patient."
					+ "\n\t4. Take Appointment.\n\t5. Appointment List\n\t6. Exit. ");
			System.out.println("enter your choice : ");
			int choice=scanner.nextInt();
			switch(choice) {
			case 1:
				System.out.println("\tAdding new Patient..");
				Utility.addPatient(patientPath);
				break;
			case 2:
				System.out.println("\tDisplaying All Doctors list..");
				Utility.doctorsList(doctorPath);
			break;
			case 3:
				System.out.println("\t Search Doctors & Patients..");
				Utility.search();
				break;
			case 4:
				System.out.println("\tTake Appointment.. -");
				Utility.takeOppointment(doctorPath);
					break;
			case 5:
				System.out.println("\tAppointment List..");
				Utility.displayAppointment();
				break;
			case 6:
				System.exit(0);
			}
				

			
			
			
			System.out.println("do you want to continue : Y/N");
			ch=scanner.next().charAt(0);
		}while(ch=='Y' ||ch=='y'); 
		scanner.close();
	}

}
