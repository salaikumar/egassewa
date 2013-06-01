/*package controllers;
import java.util.Date;
import java.util.List;

import play.data.validation.Required;
import play.db.jpa.GenericModel;
import play.mvc.Controller;
import models.*;
public class EndServiceController extends Controller {
	
	public static Long application = 00000L;
	public static void endService(Long id){
		
		// Let us retrive the account using the id
		DateCreator d = new DateCreator();
		Account a = Account.findById(id);
		
		if(a != null)
		{
			Customer c =Customer.find("byGasConnectionNumber",a.uniquenumber).first();
			
		
	EndService e = new EndService();
		e.applicationId = ++application;
		e.cerificatenumber = e.applicationId + 2;
		e.consumerNumber = c;
		e.timestamp = d.getSystemDate();
		e.isApproved = false;
		e.save();
		}
	}
	
	// Approving Connection Termination request
	public static void approveEndService(Long id)
	{
		EndService e = EndService.findById(id);
		e.isApproved = true;
		
		// Once it is approved i need to delete 
		// 1.Customer
		// 2.Account 
		// 3.Profiles for that Gas Connection number	
		
		Customer c =Customer.findById(e.consumerNumber.id);
		
		
		if(c != null){
			 
			// Delete the profiles corresponding to the Customer
			
			List <Profile> profile = Profile.find("byCustomernnumber", c).fetch();
			
			//deleting all the profiles 
			profile = null;
			
			((GenericModel) profile).save();
			
			//((GenericModel) profile).delete();
		
			// Deleting all the online Account of  the customer
			
			Account a = Account.find("byUniquenumber", c.gasConnectionNumber).first();
			
			// Delete the Account
			a = null;
			
			a.save();
			
			// Delete the customer
			c = null;
			c.save();
		}
		
		
		
	}
}
*/