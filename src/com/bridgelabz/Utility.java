package com.bridgelabz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utility {
	static Scanner scanner=new Scanner(System.in);
	static String patientPath="/home/bridgeit/Documents/Programs/Clinic Management System/src/jsonFiles/patient.json";
	static String doctorPath="/home/bridgeit/Documents/Programs/Clinic Management System/src/jsonFiles/doctor.json";
	static String appointmentPath="/home/bridgeit/Documents/Programs/Clinic Management System/src/jsonFiles/appointment.json";



	/**function to add the patients information in JSON file.
	 * @param patientPath-the path to which object is to store
	 * @throws IOException-exception while writing to file
	 * @throws ParseException-exception while reading from file.
	 */
	@SuppressWarnings({ "unchecked" })
	public static void addPatient(String patientPath) throws IOException, ParseException {

		File file = new File(patientPath);

		if(file.length()==0) {

			JSONObject parentJsonObject=new JSONObject();
			JSONObject jsonObject=new JSONObject();
			JSONArray jsonArray=new JSONArray();

			System.out.println("enter name of patient -");
			String name_of_patient=scanner.next();
			jsonObject.put("name_of_patient", name_of_patient);

			System.out.println("enter patient ID -");
			String patient_id=scanner.next();
			jsonObject.put("patient_id", patient_id);

			System.out.println("Enter patients mobile number -");
			String patient_mobile_number=scanner.next();
			jsonObject.put("patient_mobile_number", patient_mobile_number);

			System.out.println("Age : ");
			String patient_age=scanner.next();
			jsonObject.put("patient_age",patient_age);

			jsonArray.add(jsonObject);
			parentJsonObject.put("clinic_data", jsonArray);
			writeToJson( parentJsonObject,patientPath);

		}
		else{
			JSONParser parse = new JSONParser();
			Object obj=parse.parse(new FileReader(file));
			JSONObject outer = (JSONObject) obj;
			JSONObject jsonObject = new JSONObject();
			JSONArray jrr =(JSONArray) outer.get("clinic_data");

			System.out.println("enter name of patient -");
			String name_of_patient=scanner.next();
			jsonObject.put("name_of_patient", name_of_patient);

			System.out.println("enter patient ID -");
			String patient_id=scanner.next();
			jsonObject.put("patient_id", patient_id);

			System.out.println("Enter patients mobile number -");
			String patient_mobile_number=scanner.next();
			jsonObject.put("patient_mobile_number", patient_mobile_number);

			System.out.println("Age : ");
			String patient_age=scanner.next();
			jsonObject.put("patient_age",patient_age);

			jrr.add(jsonObject);
			JSONObject jsonNew = new JSONObject();
			jsonNew.put("clinic_data", jrr);
			writeToJson(jsonNew,patientPath);
		}

	}


	/**Function is used to write data to JSON file.
	 * @param parentJsonObject - json object to store in the file
	 * @param path - the path to which object is to store
	 * @throws IOException- exception while writing to file
	 */
	private static void writeToJson(JSONObject parentJsonObject,String path) throws IOException {
		File file=new File(path);
		if(file.exists()) {
			FileWriter fileWriter=new FileWriter(file);
			fileWriter.write(parentJsonObject.toJSONString());
			fileWriter.flush();
			fileWriter.close();

		}

	}


	/**
	 * @param doctorPath-the path to which object is to store.
	 * @throws FileNotFoundException-exception while file is missing.
	 * @throws IOException-exception while writing to file
	 * @throws ParseException-exception while reading  the file.
	 */
	public static void doctorsList(String doctorPath) throws FileNotFoundException, IOException, ParseException {
		File file=new File(doctorPath);
		if(file.length()==0) {
			System.out.println("File is Empty...");
		}
		else {
			JSONParser jsonParser=new JSONParser();
			Object obJ=jsonParser.parse(new FileReader(file));
			JSONObject  object = (JSONObject) obJ;
			JSONArray arr=(JSONArray)object.get("doctor_list");
			JSONObject temp=null;
			for(int i=0;i<arr.size();i++) {
				temp=(JSONObject) arr.get(i);

				System.out.println("\t\tDoctor Name : "+temp.get("doctor_name"));
				System.out.println("\t\tDoctor ID : "+temp.get("doctor_id"));
				System.out.println("\t\tSpecialization : "+temp.get("specialization"));
				System.out.println("\t\tAvailability : "+temp.get("availability"));		
				System.out.println("\t\tAppointments : "+temp.get("total_appointment"));		
				System.out.println("\t************************************");

			}			
		}
	}
	public static void search() throws FileNotFoundException, IOException, ParseException {
		System.out.println("Enter the search choice : ");
		System.out.println("\t1: Doctor \t2: Patient");		
		int a = scanner.nextInt();
		switch( a) {
		case 1:
			System.out.println("\t1. By Name.\t2. By Id.\t3. By Specialization.");
			int b=scanner.nextInt();
			switch(b) {
			case 1:
				System.out.println("enter the name of doctor : ");
				String doctor_name=scanner.next();
				docSearch(doctor_name);
				break;
			case 2:
				System.out.println("Enter the ID Number : ");
				String id_number=scanner.next();
				docSearch(id_number);
				break;
			case 3:
				System.out.println("Enter by Specialization : ");
				String specialization=scanner.next();
				docSearch(specialization);
				break;
			default:	
				System.out.println("Enter correct choice...");
			}
			break;
		case 2:	System.out.println("\t1: By Name.\t2 By Id.\t3: By Mobile Number.");
		int c=scanner.nextInt();
		switch(c) {

		case 1:System.out.println("Enter the Pateient Name : ");
		String patient_name = scanner.next();
		patientSearch(patient_name);
		break;
		case 2:System.out.println("Enter Patient Id : ");
		String patient_id = scanner.next();
		patientSearch(patient_id);
		break;
		case 3:System.out.println("Enter Patient Mobile Number : ");
		String patient_mob = scanner.next();
		patientSearch(patient_mob);
		break;
		default:	
			System.out.println("Enter correct choice...");
		}
		break;
		default:
			System.out.println("Enter correct choice please...");
		}
	}


	private static void patientSearch(String patient_info) throws FileNotFoundException, IOException, ParseException {

		File file  = new File(patientPath);
		JSONParser parse = new JSONParser();
		Object obj = parse.parse(new FileReader(file));
		JSONObject outer = (JSONObject) obj;
		JSONArray jArr = (JSONArray) outer.get("clinic_data");
		JSONObject compareObj;
		for(int i=0;i<jArr.size();i++) {

			compareObj=(JSONObject) jArr.get(i);
			String name = (String) compareObj.get("name_of_patient");
			String id = (String) compareObj.get("patient_id");
			String mob = (String) compareObj.get("patient_mobile_number");

			if((name.equals(patient_info))||(id.equals(patient_info))||(mob.equals(patient_info))){
				System.out.println(compareObj);
			}
		}
	}




	/**
	 * @param doctor_info-used to search the doctor's information from JSON.
	 * @throws FileNotFoundException-exception while file is missing.
	 * @throws IOException-exception while writing to file
	 * @throws ParseException-exception while reading  the file.
	 */
	private static void docSearch(String doctor_info) throws FileNotFoundException, IOException, ParseException {
		File file  = new File(doctorPath);
		JSONParser parse = new JSONParser();
		Object obj = parse.parse(new FileReader(file));
		//System.out.println("one");
		JSONObject outer = (JSONObject) obj;
		JSONArray jArr = (JSONArray) outer.get("doctor_list");
		JSONObject compareObj=new JSONObject();
		//System.out.println("two");
		for(int i=0;i<jArr.size();i++) {

			compareObj=(JSONObject) jArr.get(i);
			String name = (String) compareObj.get("doctor_name");
			String id = (String) compareObj.get("doctor_id");
			String specification = (String) compareObj.get("specialization");

			if((name.equals(doctor_info))||(id.equals(doctor_info))||(specification.equals(doctor_info))){
				//System.out.println("three");
				System.out.println(compareObj);
				break;
			}
		}


	}
	public static String searchPatientName(String patientName, String patientPath2) throws FileNotFoundException, IOException, ParseException {
		String foundPatient = null;
		File file=new File(patientPath2);
		JSONParser parser = new JSONParser();
		Object patientObj=parser.parse(new FileReader(file));

		JSONObject Jobject =  (JSONObject) patientObj;

		JSONArray jsonArr=(JSONArray) Jobject.get("clinic_data");

		for (int i = 0; i < jsonArr.size(); i++) 
		{
			JSONObject temp_object = (JSONObject) jsonArr.get(i);
			String pName = (String) temp_object.get("name_of_patient");

			if (pName.equals(patientName)) {
				System.out.println("Patient name "+patientName);
				foundPatient = pName;
				break;
			} 
		}
		if(foundPatient==null) {
			System.out.println("patient not found on this name"+patientName);
			System.exit(0);
		}
		return foundPatient;
	}
	/**
	 * @param stringDate-it returns the availability of doctor(timing), if it is present.
	 * @param doctorPath2-path of json file.
	 * @return-it returns the doctor name if it is present.
	 * @throws FileNotFoundException-exception while file not found.
	 * @throws IOException-exception while writing to file.
	 * @throws ParseException--exception while reading  the file.
	 */
	@SuppressWarnings("unused")
	public static String searchDoctorAvailable(String stringDate, String doctorPath2) throws FileNotFoundException, IOException, ParseException {
		String foundDate=null;
		File file=new File(doctorPath2);
		JSONParser dateparser = new JSONParser();
		Object dateObj=dateparser.parse(new FileReader(file));
		JSONObject dateObject =  (JSONObject) dateObj;

		JSONArray jsonDateArray=(JSONArray) dateObject.get("doctor_list");

		for (int i = 0; i < jsonDateArray.size(); i++) 
		{
			JSONObject temp_objectDate = (JSONObject) jsonDateArray.get(i);
			String available = (String) temp_objectDate.get("availability");

			if (available.equals(stringDate)){
				System.out.println("Available date : "+stringDate);
				foundDate=available;
				break;
			} 
			return foundDate;
		}

		if(foundDate==null) {
			System.out.println("incorrect date.."+stringDate);
			System.exit(0);
		}
		return foundDate;
	}
	/**
	 * @param docterName
	 * @param doctorPath2
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings("unused")
	public static String searchDoctorName(String docterName, String doctorPath2) throws FileNotFoundException, IOException, ParseException {
		String foundDoc = null;

		JSONParser parser = new JSONParser();
		File file=new File(doctorPath2);
		Object obj=parser.parse(new FileReader(file));
		JSONObject object =  (JSONObject) obj;
		JSONArray jsonArray=(JSONArray) object.get("doctor_list");
		for (int i = 0; i < jsonArray.size(); i++) 
		{
			JSONObject temp_object = (JSONObject) jsonArray.get(i);
			String doctorname = (String) temp_object.get("doctor_name");
			String appointment = (String) temp_object.get("total_appointment");
			long appointment_id=Integer.parseInt((String) temp_object.get(appointment).toString());
			if(appointment_id==5) {
				System.out.println("appointment is full, try another day..");
				
			}
			if ((doctorname.equals(docterName))){
				System.out.println("Doctor name "+docterName);
				foundDoc = doctorname;
				break;
			} 
		}
		if(foundDoc==null) {
			System.out.println("doctor not found : "+docterName);
			System.exit(0);
		}
		return foundDoc;
		
		
	}


	/**
	 * @param doctorPath-path of json file.
	 * @throws FileNotFoundException-exception while file not found.
	 * @throws IOException-exception while writing to file.
	 * @throws ParseException--exception while reading  the file.
	 */
	@SuppressWarnings({ "unchecked" })
	public static void takeOppointment(String doctorPath) throws FileNotFoundException, IOException, ParseException {
		File appointmentFile=new File(appointmentPath);

		System.out.println("Enter Patient Name");
		String patientName =scanner.next();
		System.out.println("Enter Doctor Name to take an Appointment");
		String docterName =scanner.next();
		System.out.println("Enter the date..for example -16/02/2018");
		String stringDate = scanner.next();

		String foundDoc =searchDoctorName(docterName,doctorPath);
		String foundDate=searchDoctorAvailable(stringDate,doctorPath);
		String foundPatient=searchPatientName(patientName,patientPath);

		JSONObject appoint=new JSONObject();

		appoint.put("Doctor_Name", foundDoc);
		appoint.put("Patient_Name", foundPatient);
		appoint.put("Booking_Date ",foundDate );
		JSONObject obj2 = new JSONObject();
		
		JSONArray appointmentFileArray = null;	

		if(appointmentFile.length()==0) {
			appointmentFileArray = new JSONArray();	
		}
		else 
		{
			JSONParser appointmentparser = new JSONParser();
			File file=new File(appointmentPath);
			Object appointmentObj=appointmentparser.parse(new FileReader(file));
			JSONObject outer = (JSONObject) appointmentObj;			
			appointmentFileArray =(JSONArray) outer.get("appointment");			

		}
		appointmentFileArray.add(appoint);
		obj2.put("appointment", appointmentFileArray);
		writeToJson( obj2,appointmentPath);
	}
	/**
	 * @throws FileNotFoundException-exception while file not found.
	 * @throws IOException-exception while reading to file.
	 * @throws ParseException--exception while reading  the file.
	 */
	public static void displayAppointment() throws FileNotFoundException, IOException, ParseException {

		File file=new File(appointmentPath);
		if(file.length()==0) {
			System.out.println("File is Empty...");
		}
		else {
			JSONParser jsonParser=new JSONParser();
			Object displayObJ=jsonParser.parse(new FileReader(file));
			JSONObject  object = (JSONObject) displayObJ;
			JSONArray arr=(JSONArray)object.get("appointment");
			JSONObject temp=null;
			for(int i=0;i<arr.size();i++) {
				temp=(JSONObject) arr.get(i);

				System.out.println("\t\tDoctor Name : "+temp.get("Doctor_Name"));
				System.out.println("\t\tBooking_Date : "+temp.get("Booking_Date"));			
				System.out.println("\t\tPatient_Name : "+temp.get("Patient_Name"));

				System.out.println("\t*********************************");

			}

			//scanner.close();Booking_Date
		}
	}

}



