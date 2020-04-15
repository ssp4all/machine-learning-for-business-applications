import java.util.Scanner; 
import java.sql.*; 

import java.sql.SQLException;
import java.util.Scanner; 
import java.sql.ResultSet;

  
public class Login {
	public static int choose_role(){
		// this function takes the user role as input. this will be stored in the person object and later will be used to display options to the user based on their role.
		int role = 0;
		do{
			System.out.println("Who would you like to log in as?\npress 1 for Admin, 2 for Doctor, 3 for Nurse, 4 for Operator, 5 for Patient\n");
			Scanner in = new Scanner(System.in); 
			role = in.nextInt();
			if(role >= 1 || role <= 5){
				System.out.println("Role chosen successfully");
			}
			else{
				System.out.println("Incorrect input. Please enter a number between 1 and 5.");
			}
			
		}while(role < 1 || role > 5);
		return role;
	}
	public static String login(int role, Connection conn){
		// this function will check the staff id and the password are correct and exist in the system.
		Boolean flag = true;
		while(flag){
			try {
				Statement s = (Statement) conn.createStatement();
				/*Console console = System.console();
				if(console == null){
					System.out.println("console instance is null");
				}
				String id = console.readLine("Enter you id: ");
				char[] password = console.readPassword("Enter you password: ");
				String pwd = new String(password);
				*/
				Scanner in = new Scanner(System.in); 
				System.out.println("Enter you id\n");
				String id = in.nextLine();
				System.out.println("Enter you password\n");
				String pwd = in.nextLine();
				PreparedStatement prepstmt = null;
				switch(role){
				case 1:case 2:case 3:case 4:
					prepstmt = (PreparedStatement) conn.prepareStatement("Select count(*) from Staff where staff_id = ? and log_in_cred = ?");
					break;
				case 5:
					
					break;
				}
				
				prepstmt.setString(1, id);
				prepstmt.setString(2, pwd);
				
				ResultSet rs = prepstmt.executeQuery();
				
				if(rs.next() && rs.getInt(1) == 1){
					flag = false;
					System.out.println("Logged in successfully!");
					return id;
				}
				else{
					System.out.println("Username and password do not match! Please try again!"); 
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "";
		
	}
	public static void logout(Person p){
		// This function will log out of the system.
		p.role = 0;
		p.id = "";
		p.operation = 0;
		System.out.println("Logged out successfully!");
	}
}
