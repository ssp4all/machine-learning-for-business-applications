import java.sql.*;
public class Hospital {

	public static void main(String[] args) {
		System.out.println("Welcome to Wolf Hospital!");
		
		//create an object for this session
		Person p = new Person();
		Boolean go_on = true;
		
		// This loop will keep the system going
		while(go_on){
			p.role = Login.choose_role();
			p.id = Login.login(p.role, p.conn);
			p.greet();
			
			//this loop will keep going until a user is logged in
			while(p.role != 0){
				p.show_options();
				p.call_query();
				System.out.println("Press Enter to continue");
				p.in.nextLine();
				p.in.nextLine();
			}
			System.out.println("Do you want to log in? \n 1: Yes\n2: No. Exit from the system");
			int s = p.in.nextInt();
			if(s == 2){
				go_on = false;
			}
		}
		try{
			p.conn.close();
			p.in.close();
		}
		catch(Exception e){
			System.out.println("Connection could not be closed." + e);
		}
		System.out.println("Closed gracefully. Good Bye!");
	}

}
