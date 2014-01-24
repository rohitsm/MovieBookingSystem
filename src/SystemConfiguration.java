package MovieBookingSystem;

import java.util.Scanner;

public class SystemConfiguration {
		public static void systemconfig()
		{
			int choice =-1;
			while(choice!=0|choice>5){
			Scanner scan = new Scanner(System.in);
			System.out.println("\n====System Configuration====");
			System.out.println("|1. View Prices    |");
			System.out.println("|2. Edit Prices    |");
			System.out.println("|3. View Holidays  |");
			System.out.println("|4. Add Holidays   |");
			System.out.println("|5. Remove Holidays|");
			System.out.println("|0. Return         |");
			System.out.println("============================");
			System.out.print("Enter Your Choice: ");
			choice = scan.nextInt();
			
			switch(choice)
			{
			default:Account.AfterLoginMenu();
					break;
			case 5: removeholidays();
					break;
			case 4: addholidays();
					break;
			case 3:	viewholidays();
					break;
			case 2: editprices();
					break;
			case 1:	viewprices();
					break;

			}		
		}	
			
		}
	
		public static void editprices()
		{
			Price.editprices();
		}
	
		public static void viewprices()
		{
			Price.viewprices();
		}
		
		public static void removeholidays()
		{
			Holiday.removeholiday();	
		}
		
		public static void viewholidays()
		{
			Holiday.printholiday();
		}
		
		public static void addholidays()
		{
			Holiday.addholiday();
		}	
	}
	
	
	
	
	
	
	
	
	
	