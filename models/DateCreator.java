package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;



public class DateCreator {

	// Get the current system date
	public Date getSystemDate(){
		
		  Calendar currentDate = Calendar.getInstance();
		  SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		  return currentDate.getTime();
	 
		  
	}
	
	// Adding 25 days to the current system date
	
	public Date addDaysToCurrentDate(int days){
		
		Calendar currentDate = Calendar.getInstance();
		 currentDate.add(Calendar.DATE, days);
		 return currentDate.getTime();
	}
	
	// A Simple method is implemented here to create the Date from the user Input
	public Date dateCreator(String date){
		
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd"); 
		    Date aDate = null;
			try {
				aDate = dateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		
		return aDate;
		
	}
	 
	// To get the locale of the user;
	/* I'm not using the method since it is not working
	public Locale getLocale(){
		 String locale = System.getProperty("user.language");
		 Locale localecode = new Locale(locale);
		 
		return localecode;
		
	}*/
	
	/*public City getCity(String city){
		City c=new City();
		
		return null;
		
	}*/
}