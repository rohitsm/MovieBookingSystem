package MovieBookingSystem;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.io.*;

public class Price {
	static final int size = 100;
		Scanner scan = new Scanner(System.in);
		//holiday price set to day of the week eg. holiday=sunday price=7;
		static int hdayprice = 7;
		//block buster price	
		static double blockbuster=1;
		
		static int[] priceid = new int[size];
		static double[] price =  new double[size];
		static String view ="View Prices";
		static String edit="Edit Prices";
		//use for displaying prices & retrieving correct priceid
		static int id;
		
		//Arrays containing movie details
		static String[] cinematype ={"Normal Suite","Platinum Suite"};
		static String[] movietype ={"Digital","3D"};
		static String[] agegroup ={"Normal","Students","Seniors"};
		static String[] day ={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		static String[] beforeafter ={"Before 6PM","From 6PM"};
		
		public Price(){	
			
			int i=0;
			Scanner s=null;
			try {
				s = new Scanner(new BufferedReader(new FileReader("price.txt")));
            	while (s.hasNext()) {
            	priceid[i] = s.nextInt();
            	price[i] = s.nextDouble();
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

		//////////////////////////////////////////////////
		
		//Get price from price list with a given id
		public static double getPrice(int group){
		int i=0; 
		id += group*1000;
		while(i<priceid.length){
			//check 2msb== then 3lsb==
		if(priceid[i]==id/10){
			if(id%10==2){
				return price[i]+blockbuster;
			}
			return price[i];
		}
		i++;
		}
		return -1;
		}
		
		///////////////////////////METHODS TO GENERATE A PRICE ID/////////////////////////////////////////////////////////////////////////////
		//ctype is either "platinum" or "normal"//type=string//time=int XXXX//int month//int day
		
		public static int[] generatePriceId(String ctype,String type,int time,int date){
			int id=0; 
			String bb = "Blockbuster";
			String mtype = "3D";
			if(type.toLowerCase().contains(bb.toLowerCase())==false){
				bb="";
			}
			if(type.toLowerCase().contains(mtype.toLowerCase())==false){
				mtype="";
			}
			id += 100000*cinematype(ctype);
			id += 10000*movietype(mtype);////
			if(holidayeve(date)){
				id += 100*hdayprice;
			}
			else{ 
			id += 100*dayofweek(date);
			}
			id += 10*timeofshow(time);
			id += priceplus(bb);
			return (choicelist());
			//thousand placeing is left open for group availbility check;
		}
		
		//If date is on holiday or holiday even
		public static boolean holidayeve(int date){
		if(Holiday.checkHolidayEve(date)){
		return true;
		}
		else{return false;}
		}
		
		//If movie is Blockbuster or any other special genre
		public static int priceplus(String bb){
			if(bb.equals("Blockbuster")){
				return 2;
			}
			return 1;
		}
		
		//Time of show-before6/from6 //10
		public static int timeofshow(int time){
			//before time<6pm = 1800hr
			if(time<1800){return 1;}
			//from time >= 6p = 1800hr
			else{return 2;}
		}
		
		//Day of the week-1~7 //100
		public static int dayofweek(int date){
			int d = date%100;
			int m = (date%10000) - d ;
			int y = date/10000;
			Calendar cal = Calendar.getInstance();
			//Month starts from January =0 
		    cal.set(Calendar.MONTH,m -1);  
		    cal.set(Calendar.DATE,d);  
		    cal.set(Calendar.YEAR,y);
		    //Sunday is first day of the week according to Calendar
		    if(cal.get(Calendar.DAY_OF_WEEK)-1==0){return 7;}
		    return cal.get(Calendar.DAY_OF_WEEK)-1;
		}
		
		//Type of movie-3D/2D-Digital //10000
		public static int movietype(String mtype){
		if(mtype.equals("3D")){
		return 2;
		}
		return 1;
		}
		
		//Type of cinema-normal/Platinum  //100000
		public static int cinematype(String ctype){
		if(ctype.equals("Platinum")){
		return 2;
		}
		return 1;
		}
	
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////edit prices////////
		public static void editprices(){
			viewbysuite(1);
		}
		
		/////view prices////////
		public static void viewprices(){
			viewbysuite(0);
		}
		
		public static void viewbysuite(int ve){			
			int choice =-1;
			Scanner scan = new Scanner(System.in);
			while(true){
			id=0;
			String either = view;
			if(ve==1){
				either = edit;
			}
			System.out.printf("\n==========%11s============\n",either);
			System.out.printf("|1. %-15s   |\n",cinematype[0]);
			System.out.printf("|2. %-15s   |\n",cinematype[1]);
			System.out.println("|0. Back              |");
			System.out.println("=================================");	
			System.out.print("Enter Your Choice: ");
			choice = scan.nextInt();
			if(choice==0||choice>cinematype.length){
				break;
			}
			else{
				id+=choice*100000;
				System.out.println(id);
				viewbytype(ve);
			}}
		}
		
		public static void viewbytype(int ve){
			int choice =-1;
			Scanner scan = new Scanner(System.in);
			while(true){
			id= (id/100000)*100000;
			String either = view;
			if(ve==1){
				either = edit;
			}
			System.out.printf("\n==========%11s============\n",either);			System.out.printf("->%s\n",cinematype[(id/100000)-1]);
			System.out.printf("|1. %-15s   |\n",movietype[0]);
			System.out.printf("|2. %-15s   |\n",movietype[1]);
			System.out.println("|0. Back              |");
			System.out.println("=================================");	
			System.out.print("Enter Your Choice: ");
			choice = scan.nextInt();
			if(choice==0||choice>movietype.length){
				break;
			}
			else{
			id+= choice*10000;
			System.out.println(id);
			viewbygroup(ve);}
			}
		}
		
		public static void viewbygroup(int ve){
			int[]list=null; 
			Scanner scan = new Scanner(System.in);
			while(true){
			list = choicelist(); 
			id= (id/10000)*10000;
			String either = view;
			if(ve==1){
				either = edit;
			}
			int range = list.length;
			int i=0; int choice=-1;
			System.out.printf("\n==========%11s============\n",either);			System.out.printf("->%s/%s\n",cinematype[(id/100000)-1],movietype[((id/10000)%10)-1]);
			while(list[i]!=range){
				System.out.printf("|%d. %-15s   |\n",list[i],agegroup[list[i]-1]);
				i++;
			}
			System.out.println("|0. Back              |");
			System.out.println("=================================");
			System.out.print("Enter Your Choice: ");
			choice = scan.nextInt();
			if(choice==0||choice>i){
				break;
			}
			else{
			id+= choice*1000;
			System.out.println(id);
			viewbydaytime(ve);
			}
			}////
		}
		 
		public static void viewbydaytime(int ve){
			 int i=0;
			 Scanner scan = new Scanner(System.in);
			 String either = view;
			 double[][] dtprice = new double[day.length][beforeafter.length];
			 //For editing view 
			 if(ve==1){
				 while(true){
					 i =0;
					 while(i<size){
						 if(priceid[i]/100==id/1000){
							 dtprice[((priceid[i]%100)/10)-1][(priceid[i]%10)-1] = price[i]; 
						 }
						 i++;}
					 int insert;
					 int choice=-1;
					 either = edit;
					 System.out.printf("\n==========%11s============\n",either);			 System.out.printf("->%s/%s/%s\n",cinematype[(id/100000)-1],movietype[((id/10000)%10)-1],agegroup[((id/1000)%10)-1]);
					 System.out.printf("=DAY=========ID====%s======ID====%s=====\n",beforeafter[0],beforeafter[1]);	
			 
					 i=0;
					 while(i<day.length){
						 int temp =(i+1)*10;
						 System.out.printf("%-10s   %2s      %4s          %2s      %4s\n",day[i],temp,String.valueOf(dtprice[i][0]),temp+1,String.valueOf(dtprice[i][1]));	
						 i++;
					 }
					 System.out.println("=======Enter "+"0"+" to return=======");
					 System.out.print("Enter ID to edit price: ");
					 choice = scan.nextInt();
					 if(choice==0){
						 return;
					 }
					 System.out.print("Enter new Price: ");
					 double newprice=scan.nextDouble();
					 insert=(id/10)+choice+1;
					 switch(choice){
					 case 10:	priceset(insert,newprice);
				 				break;
					 case 11:	priceset(insert,newprice);
		 						break;
					 case 20:	priceset(insert,newprice);
		 						break;
					 case 21:	priceset(insert,newprice);
		 						break;
					 case 30:	priceset(insert,newprice);
		 						break;
					 case 31:	priceset(insert,newprice);
		 						break;
					 case 40:	priceset(insert,newprice);
		 						break;
					 case 41:	priceset(insert,newprice);
		 						break;
					 case 50:	priceset(insert,newprice);
		 						break;
					 case 51:	priceset(insert,newprice);
		 						break;
					 case 60:	priceset(insert,newprice);
		 						break;
					 case 61:	priceset(insert,newprice);
		 						break;
					 case 70:	priceset(insert,newprice);
		 						break;
					 case 71:	priceset(insert,newprice);
		 						break;
					 default:	System.out.println("==========Invalid ID===========");
		 						break;	 
				 }}}
			 //For normal view only
			 else{
				 while(i<size){
					 if(priceid[i]/100==id/1000){
						 dtprice[((priceid[i]%100)/10)-1][(priceid[i]%10)-1] = price[i]; 
					 }
					 i++;}
				 System.out.printf("\n==========%11s============\n",either);			 System.out.printf("->%s/%s/%s\n",cinematype[(id/100000)-1],movietype[((id/10000)%10)-1],agegroup[((id/1000)%10)-1]);
				 System.out.printf("=DAY=======%s======%s=====\n",beforeafter[0],beforeafter[1]);	
				 i=0;
				 while(i<day.length){
					 System.out.printf("%-10s   %4s           %4s\n",day[i],String.valueOf(dtprice[i][0]),String.valueOf(dtprice[i][1]));	
					 
					 i++;
				 }
				 System.out.println("=================================");
			 }
		
		}
		 
		//edit price for particular price id
		public static void priceset(int id, double newprice){
			int i=0;
			while(i<size){	
				if(priceid[i]==id){
					price[i]= newprice;
					System.out.println("========Price editted successfully========");
					updatepricefile();
					return;
				}i++;}
			System.out.println("======ID currently not available======");
		} 
		
		//update price file
		public static void updatepricefile(){
			int i =0;
		 	int idtag;
		 	double cost;
		 	
		    try{
		    	BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
                        "price.txt"), false));
		    	while(i<size && priceid[i]!=0){
			    	idtag =	priceid[i];
			        cost = price[i];
			    	bw.write(String.valueOf(idtag));
			    	bw.newLine();
			    	bw.write(String.valueOf(cost));
			    	bw.newLine();
			    	i++; 
			    	}
		    	bw.close();
		    }
		    catch(Exception e){}
		} 
		
		//check for available age-group
		public static int[] choicelist(){
			int listsize=10;
			int[] list = new int[listsize];
			int i=0;
			while(i<listsize){
				list[i]=listsize;
				i++;}
			i=0;
			while(priceid[i]!=0){
				if(priceid[i]/1000==id/10000){
					int k=0;
					int group = (priceid[i]/100)%10;
					while(k<listsize){
						if(list[k]==group){
							break;
						}
						else if(list[k]==listsize){
							list[k]=group;
							break;
						}
						k++;
						}}
				i++;}
			Arrays.sort(list);
			return list;
		}
	
}
