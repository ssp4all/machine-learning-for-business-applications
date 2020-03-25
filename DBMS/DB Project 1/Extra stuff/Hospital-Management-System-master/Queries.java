	import java.sql.ResultSet;
import java.sql.*;


public class Queries {
	public static void add_staff_information(Person p){ 


		PreparedStatement ps = null;

		try{
		p.in.nextLine();
		
		System.out.println("Enter the following details: %n 1) Name %n 2) Age %n 3) Gender%n 4) Job Title %n 5) Prof Title %n 6) Department %n 7) Phone %n 8) Address %n 9) Login Credentials %n %n"); //Taking user input from the user
		
	
		String name = p.in.nextLine();
		String age = p.in.nextLine();
		String gender = p.in.nextLine();
		String jt = p.in.nextLine();
		String pt = p.in.nextLine();
		String dept = p.in.nextLine();
		String phone = p.in.nextLine();
		String addr = p.in.nextLine();
		String lic = p.in.nextLine();
		
		ps = (PreparedStatement) p.conn.prepareStatement("INSERT INTO Staff (name, age, gender, job_title, prof_title, department, phone, address, log_in_cred) VALUES (?,?,?,?,?,?,?,?,?)"); 
//Passing a query in the prepared statement
		ps.setString(1, name);
		ps.setString(2, age);
		ps.setString(3, gender);
		ps.setString(4, jt);
		ps.setString(5, pt);
		ps.setString(6, dept);
		ps.setString(7, phone);
		ps.setString(8, addr);
		ps.setString(9, lic);
		

		if(ps.executeUpdate()==1) System.out.println("Staff info added");//Executing input
		else System.out.println("Sorry the Staff info couldn't be added");
	}	
		catch(Exception whatever){
			System.out.println(whatever);
		}


		
	}
	public static void update_staff_information(Person p){

		PreparedStatement ps2 = null;
		try{
		System.out.println("Enter the record id to be updated : %n");
		p.in.nextLine();
		String r_id = p.in.nextLine();
		
		System.out.println("What do you want to update? %n 1) Name %n 2) Age %n 3) Gender%n 4) Job Title %n 5) Prof Title %n 6) Department %n 7) Phone %n 8) Address %n 9) Login Credentials %n %n"); //Taking user input from the user
		
		
		int ch = p.in.nextInt();
		if(ch==1){
			System.out.println("Enter the new Name : ");
			p.in.nextLine();
			String name1 = p.in.nextLine();
			
			ps2 = (PreparedStatement) p.conn.prepareStatement("UPDATE Staff SET name = ? WHERE staff_id = ?"); //Updating name
			ps2.setString(1, name1);
			ps2.setString(2, r_id);
			if(ps2.executeUpdate()==1) System.out.println("Staff record Updated!"); 
			// Execute query
			else System.out.println("Couldn't update the staff record");
		}
		 else if(ch==2){
            System.out.println("Enter the Age : %n");
	    p.in.nextLine();
            String age1 = p.in.nextLine();
            ps2 = (PreparedStatement) p.conn.prepareStatement("UPDATE Staff SET age = ? WHERE staff_id = ?"); //Updating Age
            ps2.setString(1, age1);
            ps2.setString(2, r_id);
            if(ps2.executeUpdate()==1) System.out.println("Staff record Updated!"); //Executing query
            else System.out.println("Couldn't update the staff record");
        }
		 else if(ch==3){
            System.out.println("Enter the Gender : ");
	    p.in.nextLine();
            String gender1 = p.in.nextLine();
            ps2 = (PreparedStatement) p.conn.prepareStatement("UPDATE Staff SET gender = ? WHERE staff_id = ?"); //Updating Gender
            ps2.setString(1, gender1);
            ps2.setString(2, r_id);
            if(ps2.executeUpdate()==1) System.out.println("Staff record Updated!"); //Executing query
            else System.out.println("Coulndn't update the staff record");
        }

		 else if(ch==4){
			             System.out.println("Enter the Job Title : ");
				     p.in.nextLine();
			             String jt1 = p.in.nextLine();
			             ps2 = (PreparedStatement) p.conn.prepareStatement("UPDATE Staff SET job_title = ? WHERE staff_id = ?"); //Updating Job Title
			             ps2.setString(1, jt1);
			             ps2.setString(2, r_id);
			             if(ps2.executeUpdate()==1) System.out.println("Staff record Updated!"); 
			//Executing query
			             else System.out.println("Coulndn't update the staff record");
				}
		
		 else if(ch==5){
			             System.out.println("Enter the Prof Title : ");
				     p.in.nextLine();
			             String pt1 = p.in.nextLine();
			             ps2 = (PreparedStatement) p.conn.prepareStatement("UPDATE Staff SET prof_title = ? WHERE staff_id = ?"); //Updating Profession Title
			             ps2.setString(1, pt1);
			             ps2.setString(2, r_id);
			             if(ps2.executeUpdate()==1) System.out.println("Staff record Updated!");
			//Execute query
			             else System.out.println("Coulndn't update the staff record");
		 		}
		 
		 else if(ch==6){
			             System.out.println("Enter the Dept : ");
				     p.in.nextLine();
			             String dept1 = p.in.nextLine();
			             ps2 = (PreparedStatement) p.conn.prepareStatement("UPDATE Staff SET department = ? WHERE staff_id = ?"); //Update Department
			             ps2.setString(1, dept1);
			             ps2.setString(2, r_id);
			             if(ps2.executeUpdate()==1) System.out.println("Staff record Updated!");
			//Executing Query
			             else System.out.println("Coulndn't update the staff record");
			     }
		 
		 else if(ch==7){
             System.out.println("Enter the Phone number : ");
	     p.in.nextLine();
             String ph_no1 = p.in.nextLine();
             ps2 = (PreparedStatement) p.conn.prepareStatement("UPDATE Staff SET phone = ? WHERE staff_id = ?"); //Update contact number
             ps2.setString(1, ph_no1);
             ps2.setString(2, r_id);
             if(ps2.executeUpdate()==1) System.out.println("Staff record Updated!"); //Execute Query
             else System.out.println("Coulndn't update the staff record");
     }
		 
		 else if(ch==8){
             System.out.println("Enter the Address : ");
	     p.in.nextLine();
             String addr1 = p.in.nextLine();
             ps2 = (PreparedStatement) p.conn.prepareStatement("UPDATE Staff SET address = ? WHERE staff_id = ?"); //Update address
             ps2.setString(1, addr1);
             ps2.setString(2, r_id);
             if(ps2.executeUpdate()==1) System.out.println("Staff record Updated!"); //Execute query
             else System.out.println("Coulndn't update the staff record");
     }
		 
		 else if(ch==9){
             System.out.println("Enter the new credentials : ");
	     p.in.nextLine();
             String lic1 = p.in.nextLine();
             ps2 = (PreparedStatement) p.conn.prepareStatement("UPDATE Staff SET log_in_cred = ? WHERE staff_id = ?"); //Update login credentials
             ps2.setString(1, lic1);
             ps2.setString(2, r_id);
             if(ps2.executeUpdate()==1) System.out.println("Staff record Updated!"); //Execute query
             else System.out.println("Coulndn't update the staff record");
     }
	}
		catch(Exception whatever){
			System.out.println(whatever);
		}

		
	}
	public static void delete_staff_information(Person p){
		PreparedStatement ps3 = null;
		try{
		System.out.println("Enter the record to be deleted %n %n");

		int sid = p.in.nextInt();
		
		ps3 = (PreparedStatement) p.conn.prepareStatement("DELETE FROM Staff where staff_id = ?");
	//Delete a staff entry
		ps3.setInt(1, sid);
	
		if(ps3.executeUpdate()==1) System.out.println("Staff info deleted"); // Execute query
		else System.out.println("Sorry the Staff info couldn't be deleted");
		}
		catch(Exception whatever){
			System.out.println(whatever);
		}

	
	}
	public static void add_patient(Person p){
		PreparedStatement ps4 = null;
		
		try{
		//scan.nextLine();
		System.out.println("Enter the following details: %n 1) SSN %n 2) Name %n 3) DOB %n 4) Gender %n 5) Age %n 6) Phone %n 7) Address %n 8) Treatment Plan %n 9) Status %n %n");
//Taking user input

		//String patid = "20";
		p.in.nextLine();
		String SSN = p.in.nextLine();
		String patname = p.in.nextLine();
		String DOB = p.in.nextLine();
		String patgender = p.in.nextLine();
		String patage = p.in.nextLine();
		String patphone = p.in.nextLine();
		String pataddr = p.in.nextLine();
		String tp = p.in.nextLine();
		String patstatus = p.in.nextLine();
		
		ps4 = (PreparedStatement) p.conn.prepareStatement("INSERT INTO Patient (SSN, name, dob, gender, age, phone, address, treatmentPlan, status) VALUES (?,?,?,?,?,?,?,?,?)");
		//ps4.setString(1, patid);
		ps4.setString(1, SSN);
		ps4.setString(2, patname);
		ps4.setString(3, DOB);
		ps4.setString(4, patgender);
		ps4.setString(5, patage);
		ps4.setString(6, patphone);
		ps4.setString(7, pataddr);
		ps4.setString(8, tp);
		ps4.setString(9, patstatus);

		if(ps4.executeUpdate()==1) System.out.println("Patient info added"); //Execute query
		else System.out.println("Sorry the Patient info couldn't be added");
		}
		catch(Exception whatever){
			System.out.println(whatever);
		}


	}	
	public static void update_patient(Person p){
		
		PreparedStatement ps5 = null;
		try{
		p.in.nextLine();
		System.out.println("Enter the record id to be updated : %n");
		String r_id1 = p.in.nextLine();
		System.out.println("What do you want to update? %n 1) SSN %n 2) Name %n 3) DOB %n 4) Gender %n 5) Age %n 6) Phone %n 7) Address %n 8) Treatment Plan %n 9) Status %n %n");
		//Asking user a value to be updated
		int ch1 = p.in.nextInt();
	    if(ch1==1){
		    System.out.println("Enter the SSN : ");
		    p.in.nextLine();
		    String ssn = p.in.nextLine();
		    ps5 = (PreparedStatement) p.conn.prepareStatement("UPDATE Patient SET SSN = ? WHERE patient_id = ?"); //Updating SSN
		    ps5.setString(1, ssn);
		    ps5.setString(2, r_id1);
		    if(ps5.executeUpdate()==1) System.out.println("Patient record Updated!"); //Execute query
		    else System.out.println("Couldn't update the patient record");
		}
		if(ch1==2){
			System.out.println("Enter the new Name : ");
			p.in.nextLine();
			String patname1 = p.in.nextLine();
			ps5 = (PreparedStatement) p.conn.prepareStatement("UPDATE Patient SET name = ? WHERE patient_id = ?"); //Execute patient name
			ps5.setString(1, patname1);
			ps5.setString(2, r_id1);
			if(ps5.executeUpdate()==1) System.out.println("Patient record Updated!"); //execute query
			else System.out.println("Couldn't update the patient record");
		}

		 if(ch1==3){
             System.out.println("Enter the Date of Birth : ");
	     p.in.nextLine();
             String dob = p.in.nextLine();
             ps5 = (PreparedStatement) p.conn.prepareStatement("UPDATE Patient SET dob = ? WHERE patient_id = ?"); //Update birthdate
             ps5.setString(1, dob);
             ps5.setString(2, r_id1);
             if(ps5.executeUpdate()==1) System.out.println("Patient record Updated!"); //Execute query
             else System.out.println("Couldn't update the patient record");
		}
		
		 if(ch1==4){
		    System.out.println("Enter the Gender : ");
		    p.in.nextLine();
		    String patgender1 = p.in.nextLine();
		    ps5 = (PreparedStatement) p.conn.prepareStatement("UPDATE Patient SET gender = ? WHERE patient_id = ?"); //Update gender
		    ps5.setString(1, patgender1);
		    ps5.setString(2, r_id1);
		    if(ps5.executeUpdate()==1) System.out.println("Patient record Updated!"); //execute query
		    else System.out.println("Couldn't update the patient record");
		}
		

		
		 if(ch1==5){
             System.out.println("Enter the Age : ");
	     p.in.nextLine();
             String patage1 = p.in.nextLine();
             ps5 = (PreparedStatement) p.conn.prepareStatement("UPDATE Patient SET age = ? WHERE patient_id = ?"); //update age
             ps5.setString(1, patage1);
             ps5.setString(2, r_id1);
             if(ps5.executeUpdate()==1) System.out.println("Patient record Updated!"); //execute query
             else System.out.println("Couldn't update the patient record");
		 }
		 
		 
		 
		 if(ch1==6){
		     System.out.println("Enter the Phone number : ");
		     p.in.nextLine();
		     String patph_no1 = p.in.nextLine();
		     ps5 = (PreparedStatement) p.conn.prepareStatement("UPDATE Patient SET phone = ? WHERE patient_id = ?"); //update contact number
		     ps5.setString(1, patph_no1);
		     ps5.setString(2, r_id1);
		     if(ps5.executeUpdate()==1) System.out.println("Patient record Updated!"); //execute query
		     else System.out.println("Couldn't update the patient record");
		}
		 
		 if(ch1==7){
		     System.out.println("Enter the Address : ");
		     p.in.nextLine();
		     String pataddr1 = p.in.nextLine();
		     ps5 = (PreparedStatement) p.conn.prepareStatement("UPDATE Patient SET address = ? WHERE patient_id = ?"); //update address
		     ps5.setString(1, pataddr1);
		     ps5.setString(2, r_id1);
		     if(ps5.executeUpdate()==1) System.out.println("Patient record Updated!"); //execute query
		     else System.out.println("Couldn't update the patient record");
		}
		
		 if(ch1==8){
		     System.out.println("Enter the Treatment Plan : ");
		     p.in.nextLine();
		     String tp1 = p.in.nextLine();
		     ps5 = (PreparedStatement) p.conn.prepareStatement("UPDATE Patient SET treatmentPlan = ? WHERE patient_id = ?"); //update treatment plan
		     ps5.setString(1, tp1);
		     ps5.setString(2, r_id1);
		     if(ps5.executeUpdate()==1) System.out.println("Patient record Updated!"); //execute query
		     else System.out.println("Couldn't update the patient record");
		}		
		
		 if(ch1==9){
             System.out.println("Enter the Status : ");
	     p.in.nextLine();
             String patstatus1 = p.in.nextLine();
             ps5 = (PreparedStatement) p.conn.prepareStatement("UPDATE Patient SET status = ? WHERE patient_id = ?"); //update status
             ps5.setString(1, patstatus1);
             ps5.setString(2, r_id1);
             if(ps5.executeUpdate()==1) System.out.println("Patient record Updated!"); //execute query
             else System.out.println("Couldn't update the patient record");
		 } 
		}
		catch(Exception whatever){
			System.out.println(whatever);
		}


	}
	public static void delete_patient(Person p){
		PreparedStatement ps6 = null;

		try {
		System.out.println("Enter the record to be deleted %n %n");

		int pid = p.in.nextInt();
		
		ps6 = (PreparedStatement) p.conn.prepareStatement("DELETE FROM Patient where patient_id = ?"); //deleting a patient entry
		ps6.setInt(1, pid);
		
		if(ps6.executeUpdate()==1) System.out.println("Patient info deleted");
		else System.out.println("Sorry the Patient info couldn't be deleted");
		}
		catch(Exception whatever){
			System.out.println(whatever);
		}
		

	}
	public static void add_ward(Person p){
		PreparedStatement ps7 = null;

		try {
		System.out.println("Enter the following details: %n 1) Ward Number %n 2) Capacity %n 3) Charges %n 4) Responsible Nurse %n %n"); //taking a user input to for the ward data

		p.in.nextLine();
		String wn = p.in.nextLine();
		String capacity = p.in.nextLine();
		String charges = p.in.nextLine();
		String rn = p.in.nextLine();
		
		ps7 = (PreparedStatement) p.conn.prepareStatement("INSERT INTO Ward VALUES (?,?,?,?)");
		ps7.setString(1, wn);
		ps7.setString(2, capacity);
		ps7.setString(3, charges);
		ps7.setString(4, rn);
		

		if(ps7.executeUpdate()==1){
			 System.out.println("Ward info added");

	       		 for(int i=0;i<Integer.parseInt(capacity);i++){
				ps7 = (PreparedStatement) p.conn.prepareStatement("INSERT INTO Beds VALUES (?,?,?,?,?)");
				ps7.setString(1, wn);
				ps7.setString(2, Integer.toString(i));
				ps7.setString(3, null);
				ps7.setString(4, null);
				ps7.setString(5, null);
				ps7.executeUpdate();
                	 }
		}

		else System.out.println("Sorry the Ward info couldn't be added");
		}
		catch(Exception whatever){
			System.out.println(whatever);
		}

	}
	public static void update_ward(Person p){
		
		PreparedStatement ps9 = null;

		try {
		System.out.println("Enter the record id to be updated : ");
		p.in.nextLine();

		String r_id2 = p.in.nextLine();
		System.out.println("What do you want to update? %n 1) Capacity %n 2) Charges %n 3) Responsible Nurse %n %n");
		
		int ch2 = p.in.nextInt();
	    		if(ch2==1){
			System.out.println("Enter the capacity : ");
			p.in.nextLine();
			String cap1 = p.in.nextLine();
			ps9 = (PreparedStatement) p.conn.prepareStatement("UPDATE Ward SET capacity = ? WHERE WardNumber = ?"); //update ward capacity
			ps9.setString(1, cap1);
			ps9.setString(2, r_id2);
			if(ps9.executeUpdate()==1) System.out.println("Ward record Updated!");
			else System.out.println("Couldn't update the Ward record");
		}

		 if(ch2==3){
             System.out.println("Enter the Charges : ");
	     p.in.nextLine();
             String ch11 = p.in.nextLine();
             ps9 = (PreparedStatement) p.conn.prepareStatement("UPDATE Ward SET charges = ? WHERE WardNumber = ?"); //update charges
             ps9.setString(1, ch11);
             ps9.setString(2, r_id2);
             if(ps9.executeUpdate()==1) System.out.println("Ward record Updated!");
             else System.out.println("Couldn't update the Ward record");
		}
		
		 if(ch2==4){
		    System.out.println("Enter the Responsible Nurse : ");
		    p.in.nextLine();
		    String rn1 = p.in.nextLine();
		    ps9 = (PreparedStatement) p.conn.prepareStatement("UPDATE Patient SET responsible_nurse = ? WHERE WardNumber = ?"); //update responsible nurse for a ward
		    ps9.setString(1, rn1);
		    ps9.setString(2, r_id2);
		    if(ps9.executeUpdate()==1) System.out.println("Ward record Updated!");
		    else System.out.println("Couldn't update the Ward record");
		}
		}
		catch(Exception whatever){
			System.out.println(whatever);
		}

	}		
	public static void delete_ward(Person p){
		PreparedStatement ps8 = null;
		
		try{
		System.out.println("Enter the record to be deleted %n %n");

		int wid = p.in.nextInt();
		
		ps8 = (PreparedStatement) p.conn.prepareStatement("DELETE FROM Ward where WardNumber = ?");
//deleting a ward from the table

		ps8.setInt(1, wid);
		
		if(ps8.executeUpdate()==1) System.out.println("Ward info deleted");
		else System.out.println("Sorry the Ward info couldn't be deleted");
		}
		catch(Exception whatever){
			System.out.println(whatever);
		}

	}
	public static void check_bed_availability(Person p){
		try{
		PreparedStatement ps = null;
		ps = (PreparedStatement) p.conn.prepareStatement("SELECT WardNumber, BedNumber FROM Beds WHERE patient_id IS NULL");
		ResultSet rs = ps.executeQuery();
		System.out.println("Ward Number | Bed Number");
		while(rs.next()){
			System.out.printf("\t%d\t %d",rs.getInt("WardNumber"),rs.getInt("BedNumber"));
			System.out.println();
		}
		}catch(Exception e){ System.out.println(e);}
	}
	public static void assign_patient_to_bed(Person p){
		try{
		p.in.nextLine();
		PreparedStatement ps = null;
		
		System.out.println("Please enter the Ward Number : ");
		String wn = p.in.nextLine();

		System.out.println("Please enter the Bed Number : ");
        	String bn = p.in.nextLine();

		System.out.println("Please enter the patient id : ");
	        String p_id = p.in.nextLine();

		System.out.println("Please enter the start date : ");
        	String sd = p.in.nextLine();

		System.out.println("Please enter the end date : ");
	        String ed = p.in.nextLine();

		
		ps = (PreparedStatement) p.conn.prepareStatement("SELECT patient_id FROM Beds WHERE WardNumber = ? AND BedNumber = ? AND patient_id IS NULL");
		ps.setString(1, wn);
		ps.setString(2, bn);

		ResultSet rs = ps.executeQuery();

		if(rs.next()){

			ps = (PreparedStatement) p.conn.prepareStatement("UPDATE Beds SET patient_id = ?,start_date = ?, end_date = ?  WHERE WardNumber = ? AND BedNumber = ?");
			ps.setString(1, p_id);
			ps.setString(2, sd);
			if(ed.equals("NULL")) ps.setString(3, null);
			else ps.setString(3,ed);
			ps.setString(4, wn);
			ps.setString(5, bn);
			if(ps.executeUpdate()==1) System.out.println("Successfully assigned the bed");
			else System.out.println("Couldn't assign bed"); 

		}
		else{
			System.out.println("Sorry this bed doesn't exists in the hospital OR is already occupied!");
		}
		}catch(Exception e){ System.out.println(e);}	
	}
	public static void release_bed(Person p){
		try{
		p.in.nextLine();
		PreparedStatement ps = null;		
		System.out.println("Please enter the Ward Number : ");
		String wn = p.in.nextLine();
		System.out.println("Please enter the Bed Number : ");
		String bn = p.in.nextLine();

		ps = (PreparedStatement) p.conn.prepareStatement("UPDATE Beds SET patient_id = null, start_date = null, end_date = null WHERE BedNumber = ? AND WardNumber = ?");
		ps.setString(1, bn);
		ps.setString(2, wn);
		if(ps.executeUpdate()==1) System.out.println("Successfully released the bed!");
		else System.out.println("Sorry couldn't release the bed!");	
		}catch(Exception e){ System.out.println(e);}
		
	}
	public static void reserve_bed(Person p){
                try{
                p.in.nextLine();
                PreparedStatement ps = null;

                System.out.println("Please enter the Ward Number : ");
                String wn = p.in.nextLine();

                System.out.println("Please enter the Bed Number : ");
                String bn = p.in.nextLine();

                System.out.println("Please enter the patient id : ");
                String p_id = p.in.nextLine();

                System.out.println("Please enter the start date : ");
                String sd = p.in.nextLine();

                System.out.println("Please enter the end date : ");
                String ed = p.in.nextLine();


                ps = (PreparedStatement) p.conn.prepareStatement("SELECT patient_id FROM Beds WHERE WardNumber = ? AND BedNumber = ? AND patient_id IS NULL");
                ps.setString(1, wn);
                ps.setString(2, bn);

                ResultSet rs = ps.executeQuery();

                if(rs.next()){

                        ps = (PreparedStatement) p.conn.prepareStatement("UPDATE Beds SET patient_id = ?,start_date = ?, end_date = ?  WHERE WardNumber = ? AND BedNumber = ?");
                        ps.setString(1, p_id);
                        ps.setString(2, sd);
                        if(ed.equals("NULL")) ps.setString(3, null);
                        else ps.setString(3,ed);
                        ps.setString(4, wn);
                        ps.setString(5, bn);
                        if(ps.executeUpdate()==1) System.out.println("Successfully assigned the bed");
                        else System.out.println("Couldn't assign bed");

                }
                else{
                        System.out.println("Sorry this bed doesn't exists in the hospital OR is already occupied!");
                }
                }catch(Exception e){ System.out.println(e);}

		
	}
	public static void add_treatment(Person p){
		try{
		p.in.nextLine();
		PreparedStatement ps = null;
		
        	System.out.println("Please enter the Record ID : ");
	        String r_id = p.in.nextLine();

        	System.out.println("Please enter the patient ID : ");
	        String p_id = p.in.nextLine();

        	System.out.println("Please enter the staff id : ");
	        String staff_id = p.in.nextLine();

        	System.out.println("Please enter the start date : ");
	        String start_date = p.in.nextLine();

        	System.out.println("Please enter the end date : ");
	        String end_date = p.in.nextLine();

        	System.out.println("Please enter the prescription : ");
	        String pres = p.in.nextLine();

        	System.out.println("Please enter the diagnosis details : ");
	        String diag_d = p.in.nextLine();
		
        	System.out.println("Please enter the treatment charge : ");
	        String tc = p.in.nextLine();
		
		ps = (PreparedStatement) p.conn.prepareStatement("INSERT INTO Medical_Record VALUES (?, ?,?,?,?,?,?,?)");
		ps.setString(1, r_id);
		ps.setString(2, p_id);
		ps.setString(3, staff_id);
		ps.setString(4, start_date);
		ps.setString(5, end_date);
		ps.setString(6, pres);
		ps.setString(7, diag_d);
		ps.setString(8, tc);
		
		if(ps.executeUpdate()==1) System.out.println("Medical Record successfully inserted!");
		else System.out.println("Sorry the medical record couldn't be added!");
		}catch(Exception e){ System.out.println(e);}
	}
	public static void add_test(Person p){
		try{
		p.in.nextLine();
		PreparedStatement ps = null;
	        System.out.println("Please enter the Staff ID: ");
        	String s_id = p.in.nextLine();

        	System.out.println("Please enter the record ID : ");
	        String r_id = p.in.nextLine();

        	System.out.println("Please enter the date : ");
	        String time = p.in.nextLine();

        	System.out.println("Please enter the name : ");
	        String name = p.in.nextLine();

        	System.out.println("Please enter the charges : ");
	        String charges = p.in.nextLine();

		
		ps = (PreparedStatement) p.conn.prepareStatement("INSERT INTO Test VALUES (?,?,?,?,?)");
		ps.setString(1, s_id);
		ps.setString(2, r_id);
		ps.setString(3, time);
		ps.setString(4, name);
		ps.setString(5, charges);

		if(ps.executeUpdate()==1) System.out.println("Test added");
		else System.out.println("Sorry the Test couldn't be added");
		}catch(Exception e){ System.out.println(e);}
	}
	public static void update_test(Person p){
		try{
		p.in.nextLine();
		System.out.println("Enter the record id to be updated : ");
	        String r_id = p.in.nextLine();

		System.out.printf("What do you want to update? %n 1) Staff_ID %n 2) Name of Test %n 3) Charges%n %n");
		String ch = p.in.nextLine();
		PreparedStatement ps = null;
		if(ch.equals("1")){
			System.out.println("Enter the new Staff_ID : ");
			String s_id = p.in.nextLine();
			ps = (PreparedStatement) p.conn.prepareStatement("UPDATE Test SET staff_id = ? WHERE record_id = ?");
			ps.setString(1, s_id);
			ps.setString(2, r_id);
			if(ps.executeUpdate()==1) System.out.println("Test Updated!");
			else System.out.println("Coulndn't update the test");
		}
		if(ch.equals("2")){
			System.out.println("Enter the new Test name : ");
			String tn = p.in.nextLine();
			ps = (PreparedStatement) p.conn.prepareStatement("UPDATE Test SET name = ? WHERE record_id = ?");
			ps.setString(1, tn);
			ps.setString(2, r_id);
			if(ps.executeUpdate()==1) System.out.println("Test Updated!");
			else System.out.println("Coulndn't update the test");
		}
		if(ch.equals("3")){
			System.out.println("Enter the new Charge : ");
			String c = p.in.nextLine();
			ps = (PreparedStatement) p.conn.prepareStatement("UPDATE Test SET charges = ? WHERE record_id = ?");
			ps.setString(1, c);
			ps.setString(2, r_id);
			if(ps.executeUpdate()==1) System.out.println("Test Updated!");
			else System.out.println("Coulndn't update the test");
		}
		}catch(Exception e){ System.out.println(e);}
	}
	public static void add_checkin(Person p){
		try{
		p.in.nextLine();
		PreparedStatement ps = null;
		
		System.out.println("Please enter the Ward Number : ");
		String wn = p.in.nextLine();

		System.out.println("Please enter the Bed Number : ");
        	String bn = p.in.nextLine();

		System.out.println("Please enter the patient id : ");
	        String p_id = p.in.nextLine();

		System.out.println("Please enter the start date : ");
        	String sd = p.in.nextLine();

		System.out.println("Please enter the end date : ");
	        String ed = p.in.nextLine();
		ps = (PreparedStatement) p.conn.prepareStatement("SELECT patient_id FROM Beds WHERE WardNumber = ? AND BedNumber = ? AND patient_id IS NULL");
		ps.setString(1, wn);
		ps.setString(2, bn);

		ResultSet rs = ps.executeQuery();

		if(rs.next()){

			ps = (PreparedStatement) p.conn.prepareStatement("UPDATE Beds SET patient_id = ?,start_date = ?, end_date = ?  WHERE WardNumber = ? AND BedNumber = ?");
			ps.setString(1, p_id);
			ps.setString(2, sd);
			if(ed.equals("NULL")) ps.setString(3, null);
			else ps.setString(3, ed);
			ps.setString(4, wn);
			ps.setString(5, bn);
			if(ps.executeUpdate()==1) System.out.println("Successfully assigned the bed");
			else System.out.println("Couldn't assign bed"); 

		}
		else{
			System.out.println("Sorry this bed doesn't exists in the hospital OR is already occupied!");
		}
		}catch(Exception e){ System.out.println(e);}
	}
	public static void update_checkin(Person p){
		try{
		p.in.nextLine();
		PreparedStatement ps = null;
		System.out.println("Please enter the patient id : ");
        	String p_id = p.in.nextLine();
		System.out.println("Please enter the end date : ");
	        String ed = p.in.nextLine();
		ps = (PreparedStatement) p.conn.prepareStatement("UPDATE Beds SET end_date = ? WHERE patient_id = ?");
		ps.setString(1, ed);
		ps.setString(2, p_id);
		if(ps.executeUpdate()==1) System.out.println("Successfully updated the check in");
		else System.out.println("Couldn't update the check-in");
		}catch(Exception e){ System.out.println(e);}
		
	}
	public static void generate_billing_account(Person p){
		PreparedStatement prepstmt = null;
		try{
			// check if the any of the beds are empty
			prepstmt = (PreparedStatement) p.conn.prepareStatement("select count(*) from Beds WHERE patient_id IS NULL");
			ResultSet rs = prepstmt.executeQuery();
			if(rs.next()){
				int capacity = rs.getInt(1);
				if(capacity <= 0){
					System.out.println("The Hospital cannot accept any patients right now because all the beds are occupied. Hence the billing account will also not be generated.");
					return;
				}
			}
			
			// insert into billing accounts a new billing account.
			prepstmt = (PreparedStatement) p.conn.prepareStatement("INSERT INTO BillingAccount (SSN, visit_date, patient_id, ward_charge, test_charge, treatment_charge, registration_fees, address, payment_method, cardNumber) values (?, ?, ?,0, 0, 0, ?, ?, ?, ?);");
			p.in.nextLine();
			System.out.println("Enter the patient id\n");
			String patient_id = p.in.nextLine();
			System.out.println("Enter the SSN\n");
			String SSN = p.in.nextLine();
			System.out.println("Enter visit date (YYYY-MM-DD)\n");
			String visit_date = p.in.nextLine();
			System.out.println("Enter registration fees\n");
			String reg_fees = p.in.nextLine();
			System.out.println("Enter address\n");
			String address = p.in.nextLine();
			System.out.println("Enter payment method\n");
			String payment_method = p.in.nextLine();
			String card_num = "NULL";
			if(payment_method.equals("Credit card")){
				System.out.println("Enter card number\n");
				card_num = p.in.nextLine();
			}
			
			prepstmt.setString(1, SSN);
			prepstmt.setString(2, visit_date);
			prepstmt.setString(3, patient_id);
			prepstmt.setString(4, reg_fees);
			prepstmt.setString(5, address);
			prepstmt.setString(6, payment_method);
			prepstmt.setString(7, card_num);
			
			// make sure that the statement is executed
			int updated_rows = prepstmt.executeUpdate();
			if(updated_rows == 1){
				System.out.println("Billing account created!");
			}
			else{
				System.out.println("Billing account was not created");
			}
		}
		catch(Exception whatever){
			System.out.println(whatever);
		}
	}
	
	public static void maintain_billing_account(Person p){
		PreparedStatement prepstmt = null;
		try{
			//create a parameterised query to update the billing accounts 
			prepstmt = (PreparedStatement) p.conn.prepareStatement("UPDATE BillingAccount SET ward_charge = ?, test_charge = ?, treatment_charge = ? , payment_method = ?, cardNumber = ? , address = ? WHERE patient_id = ? AND SSN = ? AND visit_date = ?;");
			p.in.nextLine();
			//take user input
			System.out.println("Enter the patient id\n");
			String patient_id = p.in.nextLine();
			System.out.println("Enter the SSN\n");
			String SSN = p.in.nextLine();
			System.out.println("Enter visit date (YYYY-MM-DD)\n");
			String visit_date = p.in.nextLine();
			System.out.println("Enter ward charge\n");
			String ward_charge = p.in.nextLine();
			System.out.println("Enter test charge\n");
			String test_charge = p.in.nextLine();
			System.out.println("Enter treatment charge\n");
			String treatment_charge = p.in.nextLine();
			System.out.println("Enter address\n");
			String address = p.in.nextLine();
			System.out.println("Enter payment method\n");
			String payment_method = p.in.nextLine();
			String card_num = "NULL";
			if(payment_method.equals("Credit card")){
				System.out.println("Enter card number\n");
				card_num = p.in.nextLine();
			}
			//set parameters
			prepstmt.setString(1, ward_charge);
			prepstmt.setString(2, test_charge);
			prepstmt.setString(3, treatment_charge);
			prepstmt.setString(4, payment_method);
			prepstmt.setString(5, card_num);
			prepstmt.setString(6, address);
			prepstmt.setString(7, patient_id);
			prepstmt.setString(8, SSN);
			prepstmt.setString(9, visit_date);
			//execute query and check the execution
			int updated_rows = prepstmt.executeUpdate();
			if(updated_rows == 1){
				System.out.println("Billing account updated!");
			}
			else{
				System.out.println("Billing account was not updated");
			}
		}
		catch(Exception whatever){
			System.out.println(whatever);
		}
	}
	
	public static void generate_patient_bill(Person p){
		PreparedStatement prepstmt = null;
		int result = -1;
		
		try{
			//At the start of the transaction set auto commit to false
			p.conn.setAutoCommit(false);
			p.in.nextLine();
			//Take user input for all values that are required for all three queries.
			System.out.println("Enter the patient id\n");
			String patient_id = p.in.nextLine();
			System.out.println("Enter the visit_date\n");
			String visit_date = p.in.nextLine();
	
			// Find the total test charges
			System.out.println("Sum of charges %n");
			prepstmt = (PreparedStatement) p.conn.prepareStatement("select sum(charges) from Test where record_id in (select record_id from Medical_Record where patient_id = ? and start_date >= ?)");
			prepstmt.setString(1, patient_id);
			prepstmt.setString(2, visit_date);
			ResultSet rs = prepstmt.executeQuery();
			String test_charges = "";
			if(rs.next()){
				test_charges = rs.getString(1);
			}
			//Enter test chares in billing account
			System.out.println("11");
			prepstmt = (PreparedStatement) p.conn.prepareStatement("UPDATE BillingAccount SET test_charge = ? WHERE patient_id = ? AND visit_date = ?");
			prepstmt.setString(1, test_charges);
			prepstmt.setString(2, patient_id);
			prepstmt.setString(3, visit_date);
			result = prepstmt.executeUpdate();
			if(result == -1){
				try{
					p.conn.rollback();
					p.conn.setAutoCommit(true);
		              		System.out.println("Transaction failed");
					return;
				}
				catch(Exception e2){
					System.out.println(e2);
				}
			}
			result = -1;

			// find total treatment charges
			prepstmt = (PreparedStatement) p.conn.prepareStatement("select sum(treatment_charge) from Medical_Record where patient_id = ? and ? <= start_date");
			prepstmt.setString(1, patient_id);
			prepstmt.setString(2, visit_date);
			rs = prepstmt.executeQuery();
			String treatment_charges = "";
			if(rs.next()){
				treatment_charges = rs.getString(1);
			}
			// enter treatment charges in biling account
			prepstmt = (PreparedStatement) p.conn.prepareStatement("UPDATE BillingAccount SET treatment_charge = ? WHERE patient_id = ? AND visit_date = ?");
			prepstmt.setString(1, treatment_charges);
			prepstmt.setString(2, patient_id);
			prepstmt.setString(3, visit_date);
			result = prepstmt.executeUpdate();
			if(result == -1){
				try{
					p.conn.rollback();
					p.conn.setAutoCommit(true);
					System.out.println("Transaction failed");
					return;
				}
				catch(Exception e2){
					System.out.println(e2);
				}
			}
			result = -1;

			// find total ward charges
			prepstmt = (PreparedStatement) p.conn.prepareStatement("select Ward.Charges * DATEDIFF(Beds.end_date, Beds.start_date) from Beds, Ward where Beds.WardNumber = Ward.Wardnumber AND patient_id = ?");
			prepstmt.setString(1, patient_id);
			rs = prepstmt.executeQuery();
			String ward_charges = "";
			if(rs.next()){
				ward_charges = rs.getString(1);
			}
			//enter ward charges in billing account
			if(ward_charges.equals("")){
				ward_charges="0";
			}
			prepstmt = (PreparedStatement) p.conn.prepareStatement("UPDATE BillingAccount SET ward_charge = ? WHERE patient_id = ? AND visit_date = ?");
			prepstmt.setString(1, ward_charges);
			prepstmt.setString(2, patient_id);
			prepstmt.setString(3, visit_date);
			result = prepstmt.executeUpdate();
			if(result == -1){
				try{
					p.conn.rollback();
					p.conn.setAutoCommit(true);
					System.out.println("Transaction failed");
					return;
				}
				catch(Exception e2){
					System.out.println(e2);
				}
			}
			result = -1;
			//calculate the total charges of the patient
			prepstmt = (PreparedStatement) p.conn.prepareStatement("SELECT ward_charge, test_charge, treatment_charge, registration_fees, (ward_charge + test_charge + treatment_charge + registration_fees) as Total_Charge FROM BillingAccount WHERE patient_id = ? AND visit_date = ?");
			
			prepstmt.setString(2, visit_date);
			prepstmt.setString(1, patient_id);
			rs = prepstmt.executeQuery();
			System.out.println("Ward Charge\tTest Charge\tTreatment Charge\t\tRegistration Fees\t\tTotal");
			while(rs.next()){
				System.out.println(rs.getInt(1) +"\t\t" + rs.getInt(2) +"\t\t"+ rs.getInt(3)+"\t\t" + rs.getInt(4) +"\t\t"+ rs.getInt(5));
			}
			// if we have reached here, all the queries have successfully run. Hence we can now commit the transaction
			p.conn.commit();
			p.conn.setAutoCommit(true);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void medical_record_betw_time(Person p){
		PreparedStatement prepstmt = null;
		try{
			//create a parameterised
			prepstmt = (PreparedStatement) p.conn.prepareStatement("SELECT * from Medical_Record WHERE patient_id = ?");
			p.in.nextLine();
			//take user input
			String patient_id = "";
			if(p.role == 5){
				patient_id = p.id;
			}
			else{
				System.out.println("Enter the patient id\n");
				patient_id = p.in.nextLine();
			}
			//System.out.println("Enter the start date\n");
			//String start_date = p.in.nextLine();
			//System.out.println("Enter the end date\n");
			//String end_date = p.in.nextLine();
			//set parameters
			prepstmt.setString(1, patient_id);
			//execute the query and display results
			ResultSet rs = prepstmt.executeQuery();
			System.out.println("Record id\tStaff_id\tStart Date\tEnd Date\tPrescription\t\tDiagnosis\tTreatment Charge");
			while(rs.next()){
				System.out.println(rs.getInt(1) +"\t\t" + rs.getInt(3) +"\t"+ rs.getString(4) +"\t"+ rs.getString(5) +"\t"+ rs.getString(6) +"\t"+ rs.getString(7) +"\t"+ rs.getInt(8));
			}
			// along with the medical records, the test resocrds of the patient will also be similarly created
			prepstmt = (PreparedStatement) p.conn.prepareStatement("SELECT * from Test where record_id in (SELECT record_id from Medical_Record WHERE patient_id = ?)");
	//		prepstmt.setString(1, start_date);
	//		prepstmt.setString(2, end_date);
			prepstmt.setString(1, patient_id);
			rs = prepstmt.executeQuery();
			System.out.println("staff id\trecord id\ttime\tname\tcharges");
			while(rs.next()){
				System.out.println(rs.getInt(1) +"\t" + rs.getInt(2) +"\t"+ rs.getString(3) +"\t"+ rs.getString(4) +"\t"+ rs.getInt(5));
			}
		}
		catch(Exception whatever){
			System.out.println(whatever);
		}
	}
	
	public static void generate_ward_usage(Person p){
		PreparedStatement prepstmt = null;
		try{
			//create a sql query
			prepstmt = (PreparedStatement) p.conn.prepareStatement("Select WardNumber, BedNumber, patient_id from Beds order by WardNumber, BedNumber");
			//execute the query
			ResultSet rs = prepstmt.executeQuery();
			//Print the result
			System.out.println("Ward Number\tBed Number\tPatient ID");
			while(rs.next()){
				System.out.println(rs.getInt(1) +"\t\t" + rs.getInt(2) +"\t\t"+ rs.getInt(3));
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void patients_per_month(Person p){
		PreparedStatement prepstmt = null;
		try{
			//create a sql query
			prepstmt = (PreparedStatement) p.conn.prepareStatement("SELECT Year(visit_date),Month(visit_date), count(SSN) FROM BillingAccount GROUP BY Year(visit_date), Month(visit_date)");
			//execute the query
			ResultSet rs = prepstmt.executeQuery();
			//print the results
			System.out.println("Year\tMonth\tPatients");
			while(rs.next()){
				System.out.println(rs.getInt(1) +"\t" + rs.getInt(2) +"\t"+ rs.getInt(3));
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void ward_usage_percentage(Person p){
		PreparedStatement prepstmt = null;
		try{
			// create a query
			prepstmt = (PreparedStatement) p.conn.prepareStatement("SELECT count(*) * 100 /(select count(*) FROM Beds WHERE WardNumber = ?) AS ward_usage FROM Beds WHERE patient_id is not NULL and WardNumber = ?");
			//prompt for and take user input
			p.in.nextLine();
			System.out.println("Enter the ward number");
			String ward_num = p.in.nextLine();
			// put the user input as parameters in the query
			prepstmt.setString(1, ward_num);
			prepstmt.setString(2, ward_num);
			//execute the query
			ResultSet rs = prepstmt.executeQuery();
			//print results of the query
			System.out.println("Ward Number\tUsage(Percentage)");
			while(rs.next()){
				System.out.println(ward_num + "\t" + rs.getFloat(1));
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void responsible_for_patients(Person p){
		PreparedStatement prepstmt = null;
		try{
			// create a query with parameters
			prepstmt = (PreparedStatement) p.conn.prepareStatement("SELECT * FROM Patient WHERE patient_id in (SELECT patient_id FROM Medical_Record WHERE staff_id = ? AND end_date is NULL)");
			//Prompt for and take user input
			p.in.nextLine();
			String staff_id = "";
			if(p.role == 2){
				staff_id = p.id;
			}
			else{
				System.out.println("Enter the staff id\n");
				staff_id = p.in.nextLine();
			}
			//Put user input in the query
			prepstmt.setString(1, staff_id);
			//execute the query
			ResultSet rs = prepstmt.executeQuery();
			//Print results of the query
			System.out.println("Patient id\tSSN\tName\tDate of Birth\tGender\tAge\tPhone\tAddress\tTreatment Plan\tstatus");
			while(rs.next()){
				System.out.println(rs.getInt(1) +"\t\t" + rs.getString(2) +"\t"+ rs.getString(3) +"\t"+ rs.getString(4)+"\t"+ rs.getString(5)+ "\t"+ rs.getString(6) +"\t"+ rs.getString(7) +"\t"+ rs.getString(8)+"\t"+ rs.getInt(9) +"\t"+ rs.getString(10));
			}
		}
		catch(Exception whatever){
			System.out.println(whatever);
		}
	}
	
	public static void get_rolewise_staff_info(Person p){
		PreparedStatement prepstmt = null;
		try{
			//Create a sql query.
			prepstmt = (PreparedStatement) p.conn.prepareStatement("SELECT job_title, prof_title, department, count(staff_id) FROM Staff GROUP BY job_title, prof_title, department");
			//execute the query
			ResultSet rs = prepstmt.executeQuery();
			//print results of the query
			System.out.println("Job title\tProfessional title\tDepartment\tNumber of staff");
			while(rs.next()){
				System.out.println(rs.getString(1) +"\t" + rs.getString(2) +"\t" + rs.getString(3)+"\t"+ rs.getInt(4));
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	
}

