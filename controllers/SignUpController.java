package controllers;

import java.util.Date;

import models.Account;
import models.AccountHolder;
import models.Admin;
import models.Customer;
import models.DateCreator;
import models.Dealer;
import play.mvc.Controller;

public class SignUpController extends Controller {
	
	
	/*All the Applications for online Account Creation goes here
	 * Once a signUp form is submitted, it will get the unique number and check ths details of it in the database
	 * If it exists, the Account will be Created :)
	 * Otherwise online Account can't be created
	 * 
	 * */
	
	//OnlineAccountCreationApplication Verification is done here
	public static void signUpForm(String username, String password, Long uniquenumber,
			 String email, Long phonenumber){
		
		
		// Variable to set the Account type
		String accountHolder ;
		
		Account a = Account.find("byUsername", username).first();
		DateCreator d = new DateCreator();
		
		// Variable to set the Current date
		//String currentDate =d.getSystemDate();
		
		// Converting String date to Date type
		Date date = new Date();
		date = d.getSystemDate();
		
		
		// Check if the username already exist
		Account usernameCheck = Account.find("byUsername",username).first();
		
		// Checks whether he is related to the Company by any means 
		Customer asCustomer= Customer.find("byGasConnectionNumber",uniquenumber).first();
		Dealer asDealer = Dealer.find("byDealershipId", uniquenumber).first();
		Admin asAdmin = Admin.find("byAdminEmployeeId", uniquenumber).first();
		
		//Checks if there an account exists for the same number
		Account accountCheck = Account.find("byUniquenumber", uniquenumber).first();
		
		// Checks if the mail id already exists
		Account accMail = Account.find("byEmail", email).first();
		
		if(usernameCheck != null){
			
			Application.create_acc();
		
		}
		else if( accMail != null){
			
			Application.create_acc();
		}
		
		else if ( asCustomer != null  ){
			
				if(accountCheck != null){
				
				Application.create_acc();
				}
				else{
				
				accountHolder = "CUSTOMER";
				// Call the Account creation method over here
				new Account( username,  password,  uniquenumber,
						accountHolder, email, phonenumber,
				date).save();
				UserControllerMap.profile();
				}
		}
		else if ( asDealer != null){
			
			if(accountCheck != null){
				
				Application.create_acc();
				}
				
				else{
				
				accountHolder = "DEALER";
			//	 Call the Account creation method over here
				new Account( username,  password,  uniquenumber,
						accountHolder, email, phonenumber,
						date).save();
				UserControllerMap.profile();
				}
		}
		else if ( asAdmin != null){
			
			if(accountCheck != null){
				Application.create_acc();
			// Display Account already exists for the number
			}
			
			
			else{
			
			accountHolder = "ADMIN";
			// Call the Account creation method over here
			new Account( username,  password,  uniquenumber,
					accountHolder, email, phonenumber,
					date).save();
			UserControllerMap.profile();
			}
		}
		
		
		
	}
	
	public static void newOnlineAccount(){
		
	}

	

}
