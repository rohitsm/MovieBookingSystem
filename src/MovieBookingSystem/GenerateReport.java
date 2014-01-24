package MovieBookingSystem;

import java.io.*;
import java.util.*;

public class GenerateReport {
	
	public final static String path = "C:/Users/Stanley/Desktop/OO_Assignment/MovieBookingSystem/src/MovieBookingSystem/booking.txt";
	public final static String[] MONTH = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private static double[] totalSalesArrayDay = new double[31];
	
	//pass in the lines read from booking.txt
	private static ArrayList<String> bookingList = new ArrayList<String>();
	//pass in the list of movies
	private static ArrayList<String> movieList = new ArrayList<String>();
	//pass in the list of cineplex
	private static ArrayList<String> cineplexList = new ArrayList<String>();
	//pass in the list of totalSales
	private static ArrayList<Double> totalSalesList = new ArrayList<Double>();
	
	public GenerateReport()
	{
		
	}
	
	public static void ReadFile(String filename)
	{
		BufferedReader br = null;
		try {

			String sCurrentLine;
			br = new BufferedReader(new FileReader(filename));

			while ((sCurrentLine = br.readLine()) != null) {
				bookingList.add(sCurrentLine);
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
	
	public static void GenerateByCineplex()
	{	
		Scanner sc = new Scanner(System.in);
		ReadFile(path);
		System.out.print("Enter the month to be retrieved:");
		int month = sc.nextInt();
		System.out.print("Enter the year to be retrieved:");
		int year = sc.nextInt();
		
		for (int i = 0; i < bookingList.size(); i++)
		{
			String [] temp = bookingList.get(i).split("\t");
			String cineplex = temp[2];
			String [] temp2 = temp[4].split("/");
			int transDay = Integer.parseInt(temp2[0]);
			int transMonth = Integer.parseInt(temp2[1]);
			int transYear = Integer.parseInt(temp2[2]);
			double totalSales = Double.parseDouble(temp[5]);
						
			if (transMonth == month && transYear == year)
			{
			int index = cineplexList.indexOf(cineplex);
			if(index<0)
			{
				cineplexList.add(cineplex);
				totalSalesList.add(totalSales);	
			}
			else
			{
				totalSales = totalSalesList.get(index) + totalSales;
				totalSalesList.set(index, totalSales);
			}	
			}
		}
		System.out.println("Generate Sales Report by Cineplex");
		System.out.println("---------------------------------");
		System.out.printf( "%-15s %15s %n", "Cineplex", "Total Sales($)");
		System.out.println("---------------------------------");
		for (int z = 0; z< cineplexList.size(); z++)
		{
			System.out.printf( "%-15s %15s %n", cineplexList.get(z), String.format( "%.2f", totalSalesList.get(z) ));
		}
		System.out.println("---------------------------------");
	}

	public static void GenerateByMovie()
	{
		Scanner sc = new Scanner(System.in);
		ReadFile(path);
		System.out.print("Enter the month to be retrieved:");
		int month = sc.nextInt();
		System.out.print("Enter the year to be retrieved:");
		int year = sc.nextInt();
		
		for (int i = 0; i < bookingList.size(); i++)
		{
			String [] temp = bookingList.get(i).split("\t");

			String movie = temp[3];
			String [] temp2 = temp[4].split("/");
			int transMonth = Integer.parseInt(temp2[1]);
			int transYear = Integer.parseInt(temp2[2]);
			double sales = Double.parseDouble(temp[5]);
			double totalSales = Double.parseDouble(temp[5]);
			
			if (transMonth == month && transYear == year)
			{
				int index = movieList.indexOf(movie);
				if(index<0)
				{
					movieList.add(movie);
					totalSalesList.add(totalSales);	
				}
			else
				{
					totalSales = totalSalesList.get(index) + totalSales;
					totalSalesList.set(index, totalSales);
				}
			}
		}
			System.out.println("Generate Sales Report by Movie");
			System.out.println("---------------------------------");
			System.out.printf( "%-15s %15s %n", "Movie", "Total Sales($)");
			System.out.println("---------------------------------");
			for (int z = 0; z< movieList.size(); z++)
			{
				System.out.printf( "%-15s %15s %n", movieList.get(z), String.format( "%.2f", totalSalesList.get(z) ));
			}
			System.out.println("---------------------------------");
	}
	
	public static void GenerateByMonth() 
	{
		ReadFile(path);
		double totalSales = 0.00;
		Scanner sc = new Scanner(System.in);
		System.out.println("-------------------------------");
		System.out.println("Generate Sales Report by Month");
		System.out.println("-------------------------------");
		System.out.print("Enter the month to be retrieved:");
		int month = sc.nextInt();
		System.out.print("Enter the year to be retrieved:");
		int year = sc.nextInt();

		for (int i = 0; i < bookingList.size(); i++)
		{
			String [] temp = bookingList.get(i).split("\t");
			
			String showDate = temp[4];
			String [] temp2 = temp[4].split("/");
			int transMonth = Integer.parseInt(temp2[1]);
			int transYear = Integer.parseInt(temp2[2]);
			double sales = Double.parseDouble(temp[5]);
			
			
			if (transMonth == month && transYear == year)
			{
				totalSales += sales;
			}
			
		}
		System.out.println("---------------------------------");
		System.out.printf( "%-15s %15s %n", "Month", "Total Sales($)");
		System.out.println("---------------------------------");
		System.out.printf( "%-15s %15s %n", MONTH[month -1] , String.format( "%.2f", totalSales));
		System.out.println("---------------------------------");
	}
	
	public static void GenerateByDate()
	{
		ReadFile(path);
		double totalSales = 0.00;
		Scanner sc = new Scanner(System.in);
		System.out.println("-------------------------------");
		System.out.println("Generate Sales Report by Days");
		System.out.println("-------------------------------");
		System.out.print("Enter the month to be retrieved:");
		int month = sc.nextInt();
		System.out.print("Enter the year to be retrieved:");
		int year = sc.nextInt();
		
		for (int i = 0; i < bookingList.size(); i++)
		{
			String [] temp = bookingList.get(i).split("\t");
			String showDate = temp[4];
			String [] temp2 = temp[4].split("/");
			int transDay = Integer.parseInt(temp2[0]);
			int transMonth = Integer.parseInt(temp2[1]);
			int transYear = Integer.parseInt(temp2[2]);
			double sales = Double.parseDouble(temp[5]);
			
			if (transMonth == month && transYear == year)
			{
				totalSalesArrayDay[transDay-1] = totalSalesArrayDay[transDay-1] + sales;
			}
		}
		System.out.println("---------------------------------");
		System.out.printf( "%-15s %15s %n", "Date", "Total Sales($)");
		System.out.println("---------------------------------");
		for (int z = 0; z < 31; z++)
		{
			System.out.printf( "%-15s %15s %n", (z+1) , String.format( "%.2f", totalSalesArrayDay[z]));
		}	
		
	}
	
	public static void ReportMenu()
	{	
		Scanner sc = new Scanner(System.in);
		int choice;
		
		System.out.println("----------------------------");
		System.out.println("Please choose an operation");
		System.out.println("----------------------------");
		System.out.println("(1)Generate sales report by Cineplex");
		System.out.println("(2)Generate sales report by Movies");
		System.out.println("(3)Generate sales report by Month");
		System.out.println("(4)Generate sales report by Days");
		System.out.println("----------------------------");
		System.out.print("Enter your choice: ");
		
		choice = sc.nextInt();
		
		switch (choice)
		{
			case 1:
				GenerateByCineplex();
				break;
			case 2:
				GenerateByMovie();
				break;
			case 3: 
				GenerateByMonth();
				break;
			case 4:
				GenerateByDate();
				break;
		}//end switch
		
		sc.close();
	}
}
