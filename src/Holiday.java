import java.util.Calendar;
import java.util.Scanner;
import java.io.*;


public class Holiday{
	static int[] last_day = {31,28,31,30,31,30,31,31,30,31,30,31};//last day of each month
	static final int numOfHoliday = 30;//A prefix number of holidays 
	//Two list below contains data
	static int[] holidays = new int[numOfHoliday];
	static String[] holidayName = new String[numOfHoliday];
	
	//constructor to populate data from text file
	public Holiday(){
		//Set the last day of February of the current year in case of FEB-29
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH,1);  
		last_day[1] = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//Populate holidays from flat file
		Scanner s=null;
		int i=0;
		try {
            s = new Scanner(new BufferedReader(new FileReader("holiday.txt")));
            while (s.hasNext()) {
            	holidayName[i] = s.nextLine();
            	holidays[i] = Integer.parseInt(s.nextLine());
            	i++;		
            }
        } 
		catch(IOException o){
			
		}
		finally {
            if (s != null) {
                s.close();
            }
        }
	}
	
	//Check if data is on public holiday or eve of holiday & return boolean
	public static boolean checkHolidayEve(int date){
	int i=0;
	date = date%10000;
	while(i<numOfHoliday){
	
	//if date is holiday
	if((date)==holidays[i]){
		return true;
	}
	
	//if date is holiday eve
	if(holidays[i]%100==1){
		int month = (holidays[i]/100) - 1; 
		if(month==0){month=12;}
		int day = last_day[month];
		if(date==(month*100+day)){
			return true;
		}
	}
	i++;
	}
	//date is not holiday
	return false;
	}
	
	//Print the listing of holidays
	public static void printholiday(){
		int i =0;
		System.out.println("\n=====Holiday========Date[MMDD]===");
		while(i<numOfHoliday){
			if(holidayName[i]!=null){
				System.out.printf("%-20s   %04d\n",holidayName[i],holidays[i]);
			}
			i++;
		}
		System.out.println("=================================");
	}

	//Functions to add a holiday
	public static void addholiday(){
		Scanner scan = new Scanner(System.in);
		System.out.println("\n========Add new Holiday=======");	
		System.out.print("Holiday Name: ");
		String name=scan.nextLine();
		System.out.print("Date in "+"MMDD"+": ");
		int date=scan.nextInt();
		if(checkholiday(date)==false){
		add(name,date);}
		else{
			System.out.println("====Holiday on this date already exist====");
		}
	}
	
	//check if date already has a holiday
	private static boolean checkholiday(int date){
		int i =0;
		while(i<numOfHoliday){
			if(holidays[i]==date){
				return true;
			}
		}
		return false;
	} 
	
	public static void add(String name, int date){
		int i=0;
		while(true){
			if(holidayName[i]==null){
				holidayName[i]= name;
				holidays[i] = date;
				updateholidayfile();
				System.out.println("=========Holiday Added=========");
				break;
			}
			i++;
		}
	}
	
	//Functions to remove a holiday
	public static void removeholiday(){
		int choice =-1;
		int i=0;
		Scanner scan = new Scanner(System.in);
		while(choice!=0){
		i=0;
		if(holidayName[i]==null){
			System.out.println("\n=======Holiday List Empty======");
			return;
		}
		System.out.println("\n=======Holiday===========Date====");
		while(i<numOfHoliday){
			if(holidayName[i]!=null){
				System.out.printf((i+1)+") %-20s   %04d\n",holidayName[i],holidays[i]);
			}
			else{
				System.out.println("0) Back");
				break;
			}
			i++;
		}
		System.out.println("===============================");
		System.out.print("Enter number to remove: ");
		choice = scan.nextInt();
		if(choice==0||choice>i){return;}
		else{remove(choice-1);}
		}
	}
	
	public static void remove(int i){
		holidayName[i]=null;
		holidays[i] = -1;
		while(holidayName[i+1]!=null && i<numOfHoliday){
			holidayName[i] =holidayName[i+1];
			holidays[i] = holidays[i+1];
			holidayName[i+1] =null;
			holidays[i+1] = -1;
			i++;
		}
		updateholidayfile();
	}
	
	//update holiday.txt
	public static void updateholidayfile(){
		int i =0;
		 	String name;
		 	int date;
		 	
		    try{
		    	BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        "holiday.txt"), false));
		    	while(i<numOfHoliday && holidayName[i]!=null){
			    	name =holidayName[i];
			        date = holidays[i];
			    	bw.write(name);
			    	bw.newLine();
			    	bw.write(String.valueOf(date));
			    	bw.newLine();
			    	i++; 
			    	}
		    	bw.close();
		    }
		    catch(Exception e){}
		    
		    	
		      
	}
	
	
	
}