package controllers;

import java.util.Date;
import java.util.List;
import play.mvc.Controller;
import models.*;

public class CountryController extends Controller{
	
	//Creating a country ;
	public static void createCountry(String countryname,Long countryId){
		
		
	//	if(session.contains("authUser"))
		//{

		//Testing  whether if a country already exists;
		Country checkObject = Country.find("byCountrynameAndCountryId",countryname,countryId).first();
		if(checkObject != null){
			render("Country already exist");
		}
		else{
			new Country( countryname, countryId).save();
			
			AdminControllerMap.index();
			
		}
		
	}
	//}
	
	// Retrieving a country using country name;
	public static void retriveCountryUsingName(String countryname){
		
		List<Country> country = Country.find("byCountryname", countryname).fetch();

		render(country);
	}
	
	// Retrieving a country using its id;
	public static void retriveCountryUsingId (Long countryId){
		
		Country country = Country.find("byCountryId",countryId).first();
		render(country);
	}
	
	// Updating Country information
	public static void updateCountryName(String countryname,Long countryId,String newCountryname){
		
		Country country = Country.find("byCountrynameAndCountryId", countryname,countryId).first();
		Country newcountry = Country.find("byCountryname", newCountryname).first();
		if(newcountry == null){
		country.countryname=newCountryname;
		}
		}
	
	// Updating  Country id
	public static void updateCountryId(String countryname,Long countryId,Long newCountryId){
		
		Country country = Country.find("byCountrynameAndCountryId", countryname,countryId).first();
		Country newcountry = Country.find("byCountryId", newCountryId).first();
		if(newcountry == null){
		country.countryId=countryId;
		}
		}
}
