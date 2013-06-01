/* The following is the test file for the Customer Model*/

package controllers;

import java.util.Date;

import java.util.List;

import models.City;
import models.Dealer;
import models.IdentificationProof;
import models.Title;
import play.mvc.Controller;
import models.*;

 


public class CustomerController extends Controller{				
	
	public static void createCustomer(Title title, String firstname, String lastname, Date dob,
									 	String address, String landmark, Long gasConnectionNumber,
									 	City cityId,Dealer dealerId, Long phonenumber,
									 	String document, Long documentId){

		
		//Checking if a gas connection is already exists with the same name;
		Customer addressCheck = Customer.find("byAddress", address).first();
		if(addressCheck != null){
			render("A Gas Connection already exists for the given address");
		}
		else{
		
		
		// Creating a new Customer with the given details	
		new Customer(title, firstname, lastname,  dob,
				 address,  landmark,  gasConnectionNumber,
				 cityId, dealerId,  phonenumber,
				 document,  documentId).save();
		
		render("Customer Created Successfully");
		
		// Once a Customer is created, we are automatically creating an online account for him
		//SignUpController.signUpForm(firstname, phonennumber, gasConnectionNumber, email, phonenumber)
	}
	}
	
	// Retrive Customer using firstname
	public static void retriveCustomer(String firstname){
		
		List<Customer> firstName = Customer.find("byFirstname",firstname).fetch();
		
		render(firstname);
		
	}
	
	// Retrive using gasConnectionNumber
	public static void retriveCustomerUsingGasConnectionNumber(Long gasConnectionNumber){
		
		Customer customer=Customer.find("byGasConnectionNumber", gasConnectionNumber).first();
		
		render(customer);
	}
	
	//Retrive using address
	public static void retriveCustomerUsingAddress(String address){
		 
		Customer addrCustomer = Customer.find("byAddress",address ).first();
		
		render(addrCustomer);
		
	}
	
	//Retrive using cityId
	public static void retriveCustomerUsingCity(City city){
		
		List<Customer> cityCustomer = Customer.find("byCityId",city).fetch();
		render(cityCustomer);
	}
	
	// Retrive using Dealer
	public static void retriveCustomerUsingDealer(Dealer dealer){
		List<Customer> dealerCustomer = Customer.find("byDealerId",dealer).fetch();
		render(dealerCustomer);
	}
	
	//Retrive using landmark
	public static void retriveCustomerUsingLandmark(String landmark){
		
		List<Customer> landmarkCustomer = Customer.find("byLandmark",landmark).fetch();
		render(landmarkCustomer);
	}
	
	//Retrive using PhoneNumber
	public static void retriveCustomerUsingPhoneNumber(Long phonenumber){
		
		List<Customer> phoneCustomer = Customer.find("byPhonenumber", phonenumber).fetch();
		render(phoneCustomer);
	}
	
	/* Here Follows the update Function*/
	
	
	// Update the Customer Details
	
	//This Controller is currently disabled due to design issues
	
	/*public static void updateCustomerDetails(Title title, String firstname, String lastname, Date dob,
		 	String address, String landmark, Long gasConnectionNumber,
		 	City cityId,Dealer dealerId, Long phonenumber,
		 	IdentificationProof document, Integer documentId){
		Customer updateCustomer = Customer.find("byGasConnectionNumber",gasConnectionNumber).first();
		
		updateCustomer.address = address;
		updateCustomer.cityId = cityId;
		updateCustomer.dealerId =dealerId;
		updateCustomer.dob = dob;
		updateCustomer.Document = document;
		updateCustomer.documentId =documentId;
	}*/
	
}