package controllers;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import play.data.validation.Required;
import play.mvc.Controller;
import models.*;

public class ProfileController extends Controller {
	
	// Create a Profile
	public static void createProfile(Long id, String profilename,Long gasConnectionNumber){
		
		//Get the Account using the Id
		Account a = Account.findById(id);
		
		//Get the Customer with the corresponding Gas Connection number
		Customer c= Customer.find("byGasConnectionNumber",gasConnectionNumber).first();
		
		
		Profile p = new Profile();
		
		Calendar c1 = Calendar.getInstance();
		p.accountId = a;
		p.customernumber = gasConnectionNumber;
		p.profilename =profilename;
		p.createdAt =  c1.getTime();
		
		p.save();
	}
	
	// Retrive Profile using Account id
	public static void retriveProfileUsingAccountId(Long id){
		
		// Get the account
		Account a = Account.findById(id);
		
		List<Profile> profile = Profile.find("byAccountId", a).fetch();
		
		if(profile != null){
			// Render in the front End
		}
	}

	// Retrive the Profiles using the gasConnectionNumbers
	public static void retriveProfileGasNumber(Long gasConnectionNumber){
		
		Customer c = Customer.find("byGasConnectionNumber", gasConnectionNumber).first();
		
		List<Profile> profile = Profile.find("byCustomernumber",c).fetch();
		
		if(profile != null){
			// Display in the front end
		}
	}

}
