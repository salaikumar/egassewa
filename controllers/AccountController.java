package controllers;


import java.util.Calendar;
import java.util.Date;

import java.util.List;

import org.apache.commons.mail.*;

import play.cache.Cache;
import play.data.validation.Email;
import play.data.validation.Error;
import play.data.validation.Required;
import play.libs.Mail;
import play.mvc.Controller;
import models.*;
import play.data.validation.*;

public class AccountController extends Controller {

	/*
	 * Creating a Account for Customer; This Method is used to create a Account
	 * for Admin, dealer , Customer The render method in this page will be
	 * replaced by the Corresponding Html page shortly
	 */
	public static void createCustomerAccount(String username, String password,
			Long uniquenumber, String accountHolder, String email,
			Long phonenumber, Date createdAt) {

		Account a = new Account();

		a.username = username;
		a.password = password;
		a.email = email;
		a.phonenumber = phonenumber;

		// Check if the user name already exists

		Account accNumber = Account.find("byUniquenumber", uniquenumber)
				.first();
		Account accMail = Account.find("byMail", email).first();

		if (accNumber != null) {
			render("Account already exists, Try to retrive the Account");
		} else if (accMail != null) {
			render("E-mail already exists");
		}

		// Validating the mail id;

		/*
		 * else{ new Account( username,password,uniquenumber, accountHolder,
		 * email, phonenumber, createdAt).save();
		 * 
		 * render("Account Created Successfully"); }
		 */
	}

	// User login Authentication;
	public static void login(
			@Required(message = "Please Enter Username") String username,
			@Required(message = "Please Enter Password") String password) {

		Account account = Account.find("byUsernameAndPassword", username,
				password).first();
		if (account != null) {

			// session.put("authUser", account);
			// UserControllerMap.temp(account);

			UserControllerMap.profile();
			// Cache.add("authUser", account);

		} else {

			Application.loginerrorpage();
		}
	}

	/*
	 * The Following Controllers are implemented for the retrival of various
	 * Accounts using Various values The Render method will be replaced with the
	 * corresponding html page
	 */

	// Retrieving account by using user name;
	public static void retriveAccountUsingUsername(String username) {

		List<Account> account = Account.find("byUsername", username).fetch();

		render(account);
	}

	// Retrieve using unique number;
	public static void retriveAccountUsingUniqueNumber(Long uniquenumber) {

		Account account = Account.find("byUniquenumber", uniquenumber).first();

		render(account);
	}

	// Retrieve Using e-mail
	public static void retriveAccountUsingMail(String mail) {

		Account account = Account.find("byEmail", mail).first();

		render(account);
	}

	// Updating the account
	public static void updateAccount(String username, String password,
			String email, Long phonenumber, String newUsername,
			String newPassword, String newEmail, Long newPhonenumber) {

		Account account = Account.find("byUsernameAndPassword", username,
				password).first();

		account.username = newUsername;
		account.password = newPassword;
		account.email = newEmail;
		account.phonenumber = newPhonenumber;

		account.save();

		render("Account Updated Successfully");
	}

	// New Controllers Start over here

	public static void onlineAccountCreation(String username, String password,
			String email, String gasConnectionNumber,String phonenumber) {

		/*
		 * Printing in Console To check for Correctness
		 */
		System.out.println("Given Username" + username);
		System.out.println("Given Password" + password);
		System.out.println("Given gasConnectionNumber" + gasConnectionNumber);
		System.out.println("Given Phonenumber" + phonenumber);
		System.out.println("Given email" + email);

		/*
		 * Validation Declaration Code goes here
		 */
		validation.required(username).message("Enter Username");
		validation.minSize(password,6);	
		validation.required(password).message("Enter Password");
		validation.required(gasConnectionNumber).message("Give GasConnectionNumber");
		
		validation.required(phonenumber).message("Enter Phone Number");
		validation.email(email).message("Give a valid E-mail");
		validation.required(email).message("Enter Email");

		/*
		 * Now Define the Error messages over here
		 */

		/*
		 * This piece of code will make errors to http request 
		 * This code should be correclty placed at the right place
		 */
		if (validation.hasErrors()) {

			for (Error error : validation.errors()) {
				System.out.println(error.message());
			}

			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			 Application.create_acc();
		}

		Account accNumber = Account.find("byUniquenumber", gasConnectionNumber)
				.first();

		System.out
				.println("Already Gas Connection Number Account " + accNumber);
		Account accMail = Account.find("byEmail", email).first();
		System.out.println("Account with same mail id " + accMail);

		Customer asCustomer = Customer.find("GasConnectionNumber =?",
				gasConnectionNumber).first();
		System.out.println("Customer for the Gas Connection Number"
				+ asCustomer);
		if (accNumber != null) {
			// flash statement goes here
			flash.error(username, "An Account for the Gas Connection Number already exists");
			Application.create_acc();

		} else if (accMail != null) {

			// flash statement goes here
			Application.emailerror();
		} else if (asCustomer != null) {
			Account a = new Account();

			Calendar currentDate = Calendar.getInstance();

			System.out.println("Customer Retrived" + asCustomer.firstname);

			a.accountHolder = "CUSTOMER";
			a.email = email;
			a.password = password;
			a.uniquenumber =Long.parseLong(gasConnectionNumber);
			a.username = username;
			a.createdAt = currentDate.getTime();
			a.phonenumber = Long.parseLong(phonenumber);

			a.save();

			// Now a new Account has been created
			// So we need to create a profile for him

			// Now the account has been created
			Application.regsuccess();

		} else if (asCustomer == null) {
			Application.invalidgas();
		}

	}

	public static void newLogin(String username, String password) {

		validation.required(username).message("Enter Username");
		validation.required(password).message("Enter Password");
		if (validation.hasErrors()) {

			for (Error error : validation.errors()) {
				System.out.println(error.message());
			}

			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			Application.account();
		}
		Account a = Account.find("byUsernameAndPassword", username, password)
				.first();

		if (a != null) {

			Cache.set("authUser", a);
			Account a1 = (Account) Cache.get("authUser");
			System.out.println(a1);
			UserControllerMap.profile();
		}

		else {
			Application.erroraccount();
		}

	}

	public static void newDealerLogin(String uniquenumber, String password) {

		validation.required(uniquenumber).message("Enter Dealer ID");
		validation.required(password).message("Enter Password");
		if (validation.hasErrors()) {

			for (Error error : validation.errors()) {
				System.out.println(error.message());
			}

			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			DealerControllerMap.dealerlogin();
		}
		
		Long a2 = Long.parseLong(uniquenumber);
		Account a = Account.find("byUniquenumberAndPassword", a2,
				password).first();
		System.out.println("Dealer Going to Log in" + a);
		if (a != null) {

			Cache.set("authUser", a);
			Account a1 = (Account) Cache.get("authUser");
			System.out.println(a1);
			DealerControllerMap.dealerprofile();
		}

		else {
			Application.create_acc();
		}

	}

	public static void logout() {
		Cache.delete("authUser");
		Application.account();
	}

	public static void updateUsername() {

	}

	public static void updateAccount(String username, String password,
			Long phonenumber, String email) {

		// We Let user to Update the Account except his GasConnectionNumber

		Account a = (Account) Cache.get("authUser");
		System.out.println("Logged User" + a);

		if (password != null)
			a.password = password;

		if (phonenumber != null)
			a.phonenumber = phonenumber;

		if (username != null)
			a.username = username;

		a.isPersistent();
		a.save();

		UserControllerMap.updatesuccess(a.id);
	}
	/*
	 * The below is a try to implement AJAX with play
	 */
	/*
	 * This Action will get the list of cities that belong to the choosen state
	 */
	public static String getCities(){
		
		//State s = State.findById(id);
		
		//List<City> cities = City.find("byStateId", s.id).fetch();
		//render(cities);
		
		return ("Welcome to simple AJAX in  Play");
	}
	
	/*
	 * This Action will get the list of Dealers in a given city
	 *  
	 */
	public static void getDealers(Long id){
		
		City  c  = City.findById(id);
		List<Dealer> dealers = Dealer.find("byCityId",c.id).fetch();
		render(dealers);
	}
	
	/*
	 * This Action Will help users to retrieve the account for the Customers if they loose the password
	 */
	public static void helpPassword(String gasConnectionNumber , String email){
		
		Long gasNumber = Long.parseLong(gasConnectionNumber);
		/*
		 * Declaring validation statements
		 */
		validation.required(gasConnectionNumber).message("Enter gasConnectionNumber");
		validation.required(email).message("Enter e-mail");
		validation.email(email).message("Enter an valid email id");
		
		
		/*
		 * Now using the gasConnection Number from the user i will get his account
		 *  i will send e-mail to him a random generated password
		 */
		if (validation.hasErrors()) {

			for (Error error : validation.errors()) {
				System.out.println(error.message());
			}

			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			Application.forgotpassword();
		}
		Account a= Account.find("byUniquenumber", gasNumber).first();
		
		SimpleEmail email1 = new SimpleEmail();
		try {
			email1.setFrom("gasmate.geekoders@gmail.com");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			email1.addTo(email);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		email1.setSubject("Forgot Password Request");
		if( a != null ){
			try {
				email1.setMsg("Hello "+a.username+" You request is processed!" +"Your Password at GasMate is " + a.password + "Proceed to the Website for Login");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Mail.send (email1); 
			Application.account();

		}
		else{
			try {
				email1.setMsg("Please Do Enter your Correct GasConnection Number with GasMate");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Mail.send (email1); 
			Application.account();

		}
		
	}
	
}

