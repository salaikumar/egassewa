// This Controller implements the method required to LocationChange
package controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import play.cache.Cache;
import play.data.validation.Error;
import play.data.validation.Required;
import play.libs.Mail;
import play.mvc.Controller;
import models.*;

//import javax

public class LocationChangeController extends Controller {

	// Counter variable for ApplicationNumber
	public static Long applicationNumber = 1L;

	// This method let us to create a Location change request

	// A simple method that will search for the Dealer in a city

	public static void searchDealer(String cityname) {

		// Get the city Id
		City city = City.find("byCityname", cityname).first();

		// Get the Dealers in the City
		// List<Dealer> dealer = Dealer.find("byCityId",city).fetch();

		// Now render the object at the front end
		// Which means call the corresponding Front page and pass the parameter
		// as argument

	}

	/**
	 * @param timestamp
	 * @param applicationnumber
	 * @param customerId
	 * @param locationchangetype
	 * @param newAddress
	 * @param newCity
	 * @param dealerId
	 * @param newLandmark
	 * @param newPincode
	 * @param isApproved
	 */
	/*
	 * Once the Customer had chosen the Dealer at the new place, -> Get the New
	 * Cityname -> Get the new Address -> Get the new Landmark -> Get the new
	 * DealerId
	 */
	/*
	 * public static void applyLocationChange(Account accountId , String
	 * newCity, String newAddress, String newLandmark, String newdealer){
	 * 
	 * // Now using the AccountId we are able to get the GasConnectionNumber
	 * Account account = Account.findById(accountId);
	 * 
	 * ChangeLocationApplication c= new ChangeLocationApplication();
	 * 
	 * 
	 * 
	 * //Now get the Customer ID Customer customer =
	 * Customer.find("byGasConnectionNumber", account.uniquenumber).first();
	 * 
	 * //Get the Selected Dealer Id; Dealer newDealer =
	 * Dealer.find("byAgencyname", newdealer).first();
	 * 
	 * //Get the City Id City city = City.find("byCityname", newCity).first();
	 * 
	 * // To find the location change type if(customer.dealerId == newDealer) {
	 * /*The Dealer number remains the same So no need to generate any type of
	 * forms * c.locationchangetype = LocationChangeType.CHANGELOCATIONALONE;
	 * c.dealerId = customer.dealerId; } else if(customer.dealerId !=
	 * newDealer){
	 * 
	 * c.locationchangetype = LocationChangeType.CHANGEDEALERALONE; // We need
	 * to Generate ForwardConnectionRequest form for this c.dealerId =
	 * newDealer; } else { c.locationchangetype
	 * =LocationChangeType.CHANGELOCATIONANDDEALER; // We need to generate
	 * ForwardConnectionRequest form for this c.dealerId = newDealer; }
	 * 
	 * 
	 * // Get the Current System Date DateCreator d= new DateCreator(); //Place
	 * it in the Model c.timestamp = d.getSystemDate();
	 * 
	 * // Generate an applicationNumber for this c.applicationnumber =
	 * ++applicationNumber;
	 * 
	 * // Place the Customer Id to the class c.customerId = customer;
	 * 
	 * //Get the new Address c.newAddress = newAddress;
	 * 
	 * //Get the new Landmark c.newLandmark = newLandmark;
	 * 
	 * //newPincode c.newPincode=city.pincode;
	 * 
	 * //Get the new City c.newCity= city;
	 * 
	 * //As of now, the application is not yet approved c.isApproved=false;
	 * 
	 * // Save it to the Database c.save(); }
	 */
	public static void setApprovalStatus(Long Id) {

		// Set the Status for it
		ChangeLocationApplication c = ChangeLocationApplication.findById(Id);

		// Retrieve the Customer to make corresponding changes
		Customer customer = Customer.findById(c.customerId.id);

		c.isApproved = true;

		// Commit
		c.save();
		/*
		 * This code snippet will send automail for the Customers.
		 */

		// Once it is Approved We need to do Some task based on the
		// LocationChangeType

		// ChangeLocationAlone
		if (c.locationchangetype == "CHANGELOCATIONALONE") {
			// Update the user Location
			customer.address = c.newAddress;
			customer.landmark = c.newLandmark;
			// customer.cityId = c.newCity;

			customer.save();

		}

		// ChangeDealerAlone
		else if (c.locationchangetype == "CHANGEDEALERALONE") {

			// Now we need to generate a ForwardServiceRequest here
			// Once that request is Approved, Simple Change the DealerId of the
			// Customer
			/**
			 * @param applicationnumber
			 * @param oldDealerId
			 * @param newDealerId
			 * @param oldcityId
			 * @param newcityId
			 * @param certificateId
			 * @param timestamp
			 */
			ForwardService f = new ForwardService();

			ChangeLocationApplication app = ChangeLocationApplication.find(
					"byApplicationnumber", c.applicationnumber).first();

			// Get the Application number for the LocationChange
			// Let it be as it is . because it is an object
			f.applicationnumber = app;

			Customer customer1 = Customer.findById(c.customerId);

			// Get the Old Dealer ID
			f.oldDealerId = customer1.dealerId;

			// Get the new Dealer Id
			f.newDealerId = c.dealerId;

			// Get the Old City of the Customer
			f.oldcityId = customer.cityId;

			// Get the New City of the Customer
			f.newcityId = c.newCity;

			// Now store the CurrentDate on which the application got Approved
			Calendar c1 = Calendar.getInstance();
			f.timestamp = c1.getTime();

			// Now save the Object
			f.save();

			// Update it to the database of Customer
			customer1.dealerId = f.newDealerId;
			customer1.save();

			// I need to include the code to Generate a Pdf for this
			// Certificate;
		} else {

			// Change Location and Dealer
			// ChangeAddress
			// Update the user Location
			customer.address = c.newAddress;
			// customer.landmark = c.newLandmark;
			customer.cityId = c.newCity;

			// Change the Dealer

			// Generate ForwardConnectionRequest
			ForwardService f = new ForwardService();

			ChangeLocationApplication app = ChangeLocationApplication.find(
					"byApplicationnumber", c.applicationnumber).first();

			// Get the Application number for the LocationChange
			f.applicationnumber = app;

			// Customer customer1 = Customer.findById(c.customerId);

			// Get the Old Dealer ID
			f.oldDealerId = customer.dealerId;

			// Get the new Dealer Id
			f.newDealerId = c.dealerId;

			// Get the Old City of the Customer
			f.oldcityId = customer.cityId;

			// Get the New City of the Customer
			f.newcityId = c.newCity;

			// Now store the CurrentDate on which the application got Approved
			DateCreator d = new DateCreator();
			f.timestamp = d.getSystemDate();

			// Now save the Object
			f.save();

			// Update it to the database of Customer
			customer.dealerId = f.newDealerId;
			customer.save();

			SimpleEmail email = new SimpleEmail();
			try {
				email.setFrom("gasmate.geekoders@gmail.com");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				email.addTo(c.account.email);
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			email.setSubject("GasMate-Location Change application  Details");
			try {
				email.setMsg("Hello"
						+ " "
						+ c.account.username
						+ "Here is the Status of the Loaction Change Application placed by you"
						+ " "
						+ " Order Id :"
						+ c.id
						+ " "
						+ "Status"
						+ c.status
						+ " "
						+ ""
						+ " Please Check your profile in our site and check for the update of the Location Change");
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Mail.send(email);

			DealerControllerMap.locchange();
			// I need to include the Jasper Tool Work Code here
		}
	}

	public static Long application = 1L;

	public static void changeLocationAlone(String name,
			Long gasConnectionNumber, String newAddress) {

		/*
		 * Validation Definition statement goes here
		 */
		validation.required(name).message("Enter name");
		validation.required(gasConnectionNumber).message(
				"Enter Gas Connection Number");
		validation.required(newAddress).message("Enter new Address");

		/*
		 * Display the errors over cmd prompt and in view layer
		 */
		if (validation.hasErrors()) {

			for (Error error : validation.errors()) {
				System.out.println(error.message());
			}

			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			UserControllerMap.localareachange();
		}

		ChangeLocationApplication c = new ChangeLocationApplication();

		c.newAddress = newAddress;

		Account a = (Account) Cache.get("authUser");

		c.account = a;

		Calendar c1 = Calendar.getInstance();
		c.timestamp = c1.getTime();
		c.applicationnumber = ++application;

		c.locationchangetype = "CHANGELOCATIONALONE";
		// Get the customer
		Customer cus = Customer.find("byGasConnectionNumber",
				gasConnectionNumber).first();

		c.customerId = cus;

		c.isApproved = false;
		c.status = "WAITING";
		c.save();
		SimpleEmail email = new SimpleEmail();
		try {
			email.setFrom("gasmate.geekoders@gmail.com");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			email.addTo(a.email);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		email.setSubject("GasMate-Location Change Application Details");
		try {
			email.setMsg("Hello"
					+ " "
					+ a.username
					+ "Here is the Details of the Location Change Application placed by you"
					+ " " + " Application Number :" + c.id + " "
					+ "Application Status" + c.status + " "
					+ "Address of Change" + c.newAddress
					+ " Please Check your complete Location details on Site");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mail.send(email);
		UserControllerMap.locchangesuccess();

	}

	public static void discard(Long id) {

		ChangeLocationApplication c = ChangeLocationApplication.findById(id);

		c.delete();
		SimpleEmail email = new SimpleEmail();
		try {
			email.setFrom("gasmate.geekoders@gmail.com");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			email.addTo(c.account.email);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		email.setSubject("GasMate-Location Change application  Details");
		try {
			email.setMsg("Hello"
					+ " "
					+ c.account.username
					+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "
					
					+ "Here is the Status of the Loaction Change Application placed by you"
					+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "
					+ " Order Id :"
					+ c.id
					+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "
					+ "Status"
					+ c.status
					+ " "
					+ " "
					
					+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " "+ " Please contact your Dealer in person for more details since your Application has beed discarded");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mail.send(email);
		DealerControllerMap.locchange();
	}

	public static void changeDealerAndLocation(String name, String newDealer,
			String gasConnectionNumber, String newCity, String newAddress) {

		/*
		 * Validation Definitions
		 */
		validation.required(name).message("Enter username");
		validation.required(newDealer).message("Enter Dealer Agency name");
		validation.required(gasConnectionNumber).message(
				"Enter Gas connection Number");
		validation.required(newAddress).message("Enter new Address");
		validation.required(newCity).message("Enter City");
		if (validation.hasErrors()) {

			for (Error error : validation.errors()) {
				System.out.println(error.message());
			}

			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			UserControllerMap.locmigration();
		}

		// Local Variable to hold gas ConnectionNumber Temporarily
		Long gasConnectionNumber1 = Long.parseLong(gasConnectionNumber);
		ChangeLocationApplication c = new ChangeLocationApplication();

		Account a = (Account) Cache.get("authUser");
		c.account = a;

		Customer c1 = Customer.find("byGasConnectionNumber",
				gasConnectionNumber1).first();

		c.customerId = c1;
		c.locationchangetype = "CHANGELOCATIONANDDEALER";
		Calendar c2 = Calendar.getInstance();
		c.timestamp = c2.getTime();

		City c3 = City.find("byCityname", newCity).first();

		c.newCity = c3;

		c.newAddress = newAddress;

		c.applicationnumber = ++application;

		c.isApproved = false;

		Dealer d = Dealer.find("byAgencyname", newDealer).first();
		c.dealerId = d;

		c.save();

		SimpleEmail email = new SimpleEmail();
		try {
			email.setFrom("gasmate.geekoders@gmail.com");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			email.addTo(a.email);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		email.setSubject("GasMate-Order Details");
		try {
			email.setMsg("Hello"
					+ " "
					+ a.username
					+ "Here is the Details of the Location Change Application placed by you"
					+ " "
					+ " Application Number :"
					+ c.id
					+ " "
					+ "Application Status"
					+ c.status
					+ " "
					+ " "
					+ " Please Check your complete Location Change details on Site");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mail.send(email);
		UserControllerMap.locchangesuccess();

	}

}
