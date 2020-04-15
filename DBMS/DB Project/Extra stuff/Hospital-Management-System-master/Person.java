import java.util.Scanner;
import java.sql.*;

import java.util.Scanner;

public class Person {
	Connection conn;//This is the connection to the database.
	int role; // this takes on values 1 for admin, 2 for Doctor, 3 for nurse, 4 for operator and 5 for patient
	String id; //if the persn is a member of the staff, this is the staff id otherwise is the patient_id
	int operation; //this shows which operation the user wants to do next. It is currently set to 0.
	Scanner in; // This is the system input.
	public Person(){
		//When a new user of a system is created, create a connection to the database and a scanner for the system.
		this.conn = (Connection) GetConnection.connection();
		this.operation = 0;
		this.in = new Scanner(System.in);
	}
	public void greet(){
		// This function greets the user according to their role.
		switch(this.role){
		case 1:System.out.println("Welcome Admin!");
			break;
		case 2:System.out.println("Welcome Doctor!");
			break;
		case 3:System.out.println("Welcome Nurse!");
			break;
		case 4:System.out.println("Welcome Operator!");
			break;
		case 5:System.out.println("Welcome Patient!");
			break;
		}
	}
	public void show_options(){
		//This function will only show operations that are accesible to a specific user based on their role.
		switch(this.role){
		case 1:
			System.out.println("");
			System.out.println("28: Logout");
			break;
		case 2: 
			System.out.println("2: Update Staff Information");
			System.out.println("14: Add Treatment for a patient");
			System.out.println("15: Add Test for a patient");
			System.out.println("16: Update Test for a patient");
			System.out.println("22: Generate Report for a patient's medical history between a certain time period");
			System.out.println("26: Get a list of patients a doctor is currently responsible for");
			System.out.println("28: Logout");
			break;
		case 3: 
			System.out.println("2: Update Staff Information");
			System.out.println("22: Generate Report for a patient's medical history between a certain time period");
			System.out.println("28: Logout");
			break;
		case 4: 
			System.out.println("2: Update Staff Information");
			System.out.println("4: Add new patient");
			System.out.println("5: Update a patient");
			System.out.println("6: Delete a patient");
			System.out.println("7: Add a ward");
			System.out.println("8: Delete a ward");
			System.out.println("9: Update a ward");
			System.out.println("10: Check Bed Availability");
			System.out.println("11: Assign a Patient to a bed");
			System.out.println("12: Release Bed");
			System.out.println("13: Reserve a bed");
			System.out.println("17: Add Checkin for a patient");
			System.out.println("18: Update Checkin for a patient");
			System.out.println("19: Generate Billing Account");
			System.out.println("20: Maintain Billing Account");
			System.out.println("21: Generate a Patient's Bill");
			System.out.println("23: Generate current usage status for all wards");
			System.out.println("24: Number of patients per month");
			System.out.println("25: Ward usage percentage");
			System.out.println("26: Get a list of patients a doctor is currently responsible for");
			System.out.println("27: Get Staff Information grouped by their role");
			System.out.println("28: Logout");
			break;
		case 5:
			System.out.println("5: Update a patient");
			System.out.println("22: Generate Report for a patient's medical history between a certain time period");
			System.out.println("28: Logout");
			break;
		}
		this.operation = in.nextInt();
	}
	
	public void call_query(){
		//This function will take the operation that was set in the previous function and call the appropriate API in the Queries class.
		switch(this.operation){
		case 1:
			Queries.add_staff_information(this);
			break;
		case 2:
			Queries.update_staff_information(this);
			break;
		case 3:
			Queries.delete_staff_information(this);
			break;
		case 4:
			Queries.add_patient(this);
			break;
		case 5:
			Queries.update_patient(this);
			break;
		case 6:
			Queries.delete_patient(this);
			break;
		case 7:
			Queries.add_ward(this);
			break;
		case 8:
			Queries.delete_ward(this);
			break;
		case 9:
			Queries.update_ward(this);
			break;
		case 10:
			Queries.check_bed_availability(this);
			break;
		case 11:
			Queries.assign_patient_to_bed(this);
			break;
		case 12:
			Queries.release_bed(this);
			break;
		case 13:
			Queries.reserve_bed(this);
			break;
		case 14:
			Queries.add_treatment(this);
			break;
		case 15:
			Queries.add_test(this);
			break;
		case 16:
			Queries.update_test(this);
			break;
		case 17:
			Queries.add_checkin(this);
			break;
		case 18:
			Queries.update_checkin(this);
			break;
		case 19: 
			Queries.generate_billing_account(this);
			break;
		case 20:
			Queries.maintain_billing_account(this);
			break;
		case 21:
			Queries.generate_patient_bill(this);
			break;
		case 22:
			Queries.medical_record_betw_time(this);
			break;		
		case 23:
			Queries.generate_ward_usage(this);
			break;
		case 24:
			Queries.patients_per_month(this);
			break;
		case 25:
			Queries.ward_usage_percentage(this);
			break;
		case 26:
			Queries.responsible_for_patients(this);
			break;
		case 27:
			Queries.get_rolewise_staff_info(this);
			break;
		case 28:
			Login.logout(this);
			break;
		default:
			System.out.println("Incorrect input operation choice. Logging out now.");
			Login.logout(this);
			break;
		}

	}
}
