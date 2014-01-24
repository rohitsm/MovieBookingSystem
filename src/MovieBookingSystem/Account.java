package MovieBookingSystem;

import java.io.*;
import java.util.*;

public class Account {
	
		String userID, password, role;
		static String path = "C:/Users/Stanley/Desktop/OO_Assignment/MovieBookingSystem/src/MovieBookingSystem/staff.txt";
		static ArrayList<String> staffList = new ArrayList<String>();
		
		public Account()
		{
			
		}

		public Account(String userID, String password, String role)
		{
			userID = userID;
			password = password;
			role = role;
		}
		
		public void setUserID(String userID)
		{
			userID = userID;
		}
		
		public String getUserID()
		{
			return userID;
		}
		
		public void setPassword(String password)
		{
			password = password;
		}
		
		public String getPassword()
		{
			return password;
		}
		
		public void setRole(String role)
		{
			role = role;
		}
		
		public String getRole()
		{
			return role;
		}

		public static void ReadFile(String path)
		{
			BufferedReader br = null;
			try {

				String sCurrentLine;
				br = new BufferedReader(new FileReader(path));

				while ((sCurrentLine = br.readLine()) != null) {
					staffList.add(sCurrentLine);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null)br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		public static void MainMenu()
		{
			//declare scanner
			Scanner sc = new Scanner(System.in);
			//variable for choice
			int choice;
			
			System.out.println("----------------------------");
			System.out.println("Welcome to MOBLIMA");
			System.out.println("----------------------------");
			System.out.println("(1)Login");
			System.out.println("(2)Exit Program");	
			System.out.println("----------------------------");
			System.out.print("Enter your choice: ");
			
			choice = sc.nextInt();
			
			switch (choice)
			{
				case 1:
					LoginCheck();
					break;
				case 2:
					System.out.print("System terminating..");
					break;
					
			}//end switch
			
			sc.close();
		}
		
		public static void LoginCheck()
		{
			Scanner sc = new Scanner(System.in);
			ReadFile(path);
			Account ac = new Account();
			String role = "staff";
			System.out.print("Enter your User ID: ");
			String userID = sc.nextLine();
			System.out.print("Enter your Password: ");
			String password = sc.nextLine();
					
			for (int i = 0; i < staffList.size(); i ++)
			{
			if (staffList.get(i).equals(userID + "," + password + "," + role))
			{	
				
				System.out.println("Current account: " + userID);
				System.out.println("Welcome to the system.");
				AfterLoginMenu();
				break;
			}	
			else
				{
				System.out.println("Invalid login, try again");
				break;
			}
			}
		}
		
		public static void AfterLoginMenu()
		{	
			Scanner sc = new Scanner(System.in);
			int choice;
			
			System.out.println("----------------------------");
			System.out.println("Please choose an operation");
			System.out.println("----------------------------");
			System.out.println("(1)Create user");
			System.out.println("(2)Generate sales report");
			System.out.println("(3)System Configuration");
			System.out.println("(4)Log out");
			System.out.println("----------------------------");
			System.out.print("Enter your choice: ");
			
			choice = sc.nextInt();
			
			switch (choice)
			{
				case 1:
				try {
					CreateUser();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
				case 2:
					GenerateReport.ReportMenu();
					break;
				case 3: 
					SystemConfiguration.systemconfig();
					break;
				case 4:
					MainMenu();
					break;
			}//end switch
			
			sc.close();
		}
		
		public static void CreateUser() throws IOException 
		{
			Scanner sc = new Scanner(System.in);
			Account acc;
			String role;
			role = "staff";
			acc = new Account();
			
			System.out.println("-----------------------");
			System.out.println("Create new user");
			System.out.println("-----------------------");
			System.out.print("Enter new user ID: ");
			acc.userID = sc.nextLine();
			System.out.print("Enter new Password: ");
			acc.password = sc.nextLine();
			acc.role = role;
			String inputUserData = acc.getUserID() + "," + acc.getPassword() + "," + acc.getRole();
			
			FileWriter fstream = new FileWriter(path, true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.newLine();
			out.write(inputUserData);
		
			out.close();
			System.out.println("User created successfully.");
			AfterLoginMenu();
		}
	
}



