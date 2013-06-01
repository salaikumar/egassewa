package controllers;
import java.util.Date;
import java.util.List;

import play.data.validation.Required;
import play.mvc.Controller;
import models.*;

public class TechSupportController extends Controller {
	
	/**
	 * @param accountId
	 * @param subject
	 * @param query
	 * @param reply
	 
	 */
	public static void createQuery(Long id, String subject, String query){
		
		// Get the AccountHolder by using the id
		Account account = Account.findById(id);
		
		TechnicalSupport t = new TechnicalSupport();
		
		t.query = query;
		t.subject = subject;
		
		t.save();
		
	}
	
	public static void replyToQuery(Long id, String reply){
		
		//Retrieve the Question by using the Id
		TechnicalSupport t = TechnicalSupport.findById(id);
		
		t.reply = reply;
		
		t.save();
		
		// Now render this object in the front end
	}
	
	// Retrieve the object using Id
	public static void retrieveTechSupport(Long id){
		
		TechnicalSupport t = TechnicalSupport.findById(id);
		
		if(t != null){
			// Render the object in the front end
		}
	}

}
