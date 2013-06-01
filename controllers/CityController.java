package controllers;
import java.util.Date;

import java.util.List;

import play.data.validation.Required;
import play.mvc.Controller;
import models.*;
public class CityController extends Controller{

	//Create City
	public static void createCity(String cityname, Long pincode, String statename){
		
		State s = State.find("byStatename", "TAMILNADU").first();
		
		City c = City.find("byPincode",pincode).first();
		
		if(c != null){
			render("City already exists");
		}
		else{
			new City(cityname,pincode,s).save();
			AdminControllerMap.index();
		}
	}
	
	//Try Retrieving city using city name
	public static void retriveCityUsingCityname(String cityname){
		List<City> city = City.find("byCityname",cityname).fetch();
		render(city);
		
	}
	
	//Retrieve city using pin code
	public static void retriveCityUsingPincode(Integer pincode){
		
		City city = City.find("byPincode", pincode).first();
		
		render(city);
	}
	
	//List all the cities using the state name
	public static void retriveCityUsingState(String statename){
		
		State state= State.find("byStatename",statename).first();
		
		List<City> city = City.find("byStateId",state).fetch();
		
		render(city);
	}
	
	// Update the city information
	public static void updateCity(String cityname, Long pincode, String statename,String newCityname, Long newPincode, String newStatename){
		
		State state = State.find("byStatename", statename).first();
		City city = City.find("byCitynameAndPincodeAndStateId",cityname,pincode,state).first();
		
		State newstate = State.find("byStatename",newStatename).first();
		
		// Check if the pin code already exists in the database
		City cityPincode = City.find("byPincode", newPincode).first();
		
		if(cityPincode == null){
			
		
		city.cityname = newCityname;
		city.pincode =newPincode;
		city.stateId =newstate;
		
		city.save();
		
		render("City updated");
		}
		else{
			// Display that another city is having the same pincode
		}
	}
}
