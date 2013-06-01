package controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import play.data.validation.Error;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;
import models.*;

public class NewConnectionRequestController extends Controller {

	public static Long gasNumber = 000000L;
	public static Long applicationnumber = 1L;

	// Creating a connection Request
	public static void applyConnection(Long stateid, Long cityid,
			Long dealerid, String title, String firstname, String lastname,
			String dob, String address, String landmark, String pincode,
			Long phonenumber, String id1, String idnumber,
			String email,
			boolean isConditionsAccepted ) {

		/*
		 * Validation definition goes here
		 */
		validation.required(stateid).message("Select any State");
		validation.required(cityid).message("Select any State");
		validation.required(dealerid).message("Select any Dealer");
		validation.required(firstname).message("Firstname Required");
		validation.required(dob).message("Enter Date of birth");
		validation.past(dob).message("Enter a valid date of birth");
		validation.required(address).message("Enter Address");
		validation.required(landmark).message("Enter Landmark");
		validation.required(pincode).message("Enter Pincode");
		validation.required(idnumber).message("Enter IndenficationProofNumber");
		validation.required(id1).message("Choose IndentificationProof");
		validation.isTrue(isConditionsAccepted).message("Conditions should be Accepted");
		
		/*
		 * Display the Errors if any over here
		*/
		if (validation.hasErrors()) {

			for (Error error : validation.errors()) {
				System.out.println(error.message());
			}

			params.flash(); // add http parameters to the flash scope
			validation.keep(); // keep the errors for the next request
			Application.apply();
		}
		
		System.out.println("Date of Birth" + dob);
		NewConnectionRequest n = new NewConnectionRequest();

		n.applicationnumber = ++applicationnumber;
		System.out.println("Application Number:" + n.applicationnumber);
		// Get the state
		State s = State.findById(stateid);
		n.stateId = s;
		System.out.println("Statename" + s.statename);
		// Get the City
		City c = City.findById(cityid);
		n.cityId = c;
		System.out.println("Cityname" + c.cityname);
		// Get the choosen dealer
		Dealer d = Dealer.findById(dealerid);
		n.dealerId = d;
		System.out.println("Agencyname" + d.agencyname);

		if (title == "Mr") {

			n.title = Title.Mr;
		} else {
			n.title = Title.Ms;
		}

		// Get the date of birth
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		Date aDate = null;
		System.out.println("Date of Birth" + dob);
		try {
			aDate = dateFormat.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		n.dob = aDate;
		System.out.println("Date of Birth after parsed" + aDate);
		System.out.println("Date Stored in database" + n.dob);

		n.firstname = firstname;
		System.out.println("Firstname" + n.firstname);
		n.landmark = landmark;
		System.out.println("landmark" + n.landmark);
		n.lastname = lastname;
		System.out.println("lastname" + n.lastname);
		n.email = email;
		System.out.println("email" + n.email);
		n.isConditionsAccepted = true;
		n.isConnectionApproved = false;
		n.address = address;
		System.out.println("address" + n.address);
		n.phonenumber = phonenumber;
		System.out.println("phonenumber" + n.phonenumber);
		n.pincode =Long.parseLong(pincode);
		System.out.println("pincode" + n.pincode);

		Calendar c1 = Calendar.getInstance();
		n.timestamp = c1.getTime();
		System.out.println("pincode" + n.timestamp);
		n.idnumber =Long.parseLong (idnumber);
		System.out.println("idnumber" + n.idnumber);
		n.id1 = id1;
		System.out.println("id1" + n.id1);

		// Once the new Connection Application has been completed, the page will
		// be redirected to home page

		n.save();

		Application.index();
	}

	// This Variable is used to generate gasConnectionNumber

	public static void approveGasConnection(Long id) {

		NewConnectionRequest n = NewConnectionRequest.findById(id);

		if (n != null) {
			n.isConnectionApproved = true;

			// Creating a Customer with the following details
			Customer c = new Customer();

			c.title = n.title;
			c.firstname = n.firstname;
			c.lastname = n.lastname;
			c.address = n.address;
			c.dob = n.dob;
			c.phonenumber = n.phonenumber;
			c.gasConnectionNumber = ++gasNumber;
			c.cityId = n.cityId;
			c.dealerId = n.dealerId;
			c.Document = n.id1;
			c.documentId = n.idnumber;
			Calendar c1 = Calendar.getInstance();
			c.timestamp = c1.getTime();

			// Now save the customer
			c.save();

			System.out.println("Saved Customer " + c.firstname);

			// Creating an Online Account for the Customer
			Account acc = new Account();

			acc.accountHolder = "CUSTOMER";
			acc.createdAt = c1.getTime();
			acc.email = n.email;
			acc.accountHolder = "CUSTOMER";
			acc.createdAt = c1.getTime();
			acc.phonenumber = n.phonenumber;
			acc.uniquenumber = c.gasConnectionNumber;

			// Username and Password for the Newly created User
			acc.username = n.firstname;
			acc.password = n.phonenumber.toString();

			acc.save();

			DealerControllerMap.NewConnectionList();
			// Display it
		}

	}

	// Discard the Applications

	public static void discard(Long id) {

		// Retrieve the Application

		NewConnectionRequest n = NewConnectionRequest.findById(id);

		n.delete();

		DealerControllerMap.NewConnectionList();
	}

}
