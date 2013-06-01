// Customer Module
package models;
import java.util.*;
import models.Title;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;

import play.db.jpa.*;


@Entity
@Table(name="Customer")
//@OnDelete(action=OnDeleteAction.CASCADE)

public class Customer extends Model {

	/**
	 * @param title
	 * @param firstname
	 * @param lastname
	 * @param dob
	 * @param address
	 * @param landmark
	 * @param gasConnectionNumber
	 * @param cityId
	 * @param dealerId
	 * @param phonenumber
	 * @param document
	 * @param documentId
	 * @param timestamp
	 */
	public Customer(Title title, String firstname, String lastname, Date dob,
			String address, String landmark, Long gasConnectionNumber,
			City cityId,Dealer dealerId, Long phonenumber,
		String document, Long documentId) {
		super();
		this.title = title;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.address = address;
		this.landmark = landmark;
		this.gasConnectionNumber = gasConnectionNumber;
		this.cityId = cityId;
	//	this.agencyname = agencyname;
		this.dealerId = dealerId;
		this.phonenumber = phonenumber;
		Document = document;
		this.documentId = documentId;
		
		DateCreator d = new DateCreator();
		timestamp = d.getSystemDate();
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	// title;
	public Title title;
	
	//Firstname;
	public String firstname;
	
	//lastname;
	public String lastname;
	
	//date of Birth;
	public Date dob;
	
	//Address;
	@Lob
	public String address;
	
	//landmark;
	public String landmark ;
	
	//GasConnection Number Id;
	public Long gasConnectionNumber;
	
	//Admin Location;
	/**
	 * 
	 */
	@ManyToOne
	public City cityId;

	// Company name;
//	public String agencyname;
	
	// Corresponding Dealer Id;
	@ManyToOne
	public  Dealer dealerId;

	// Phone Number;
	public Long phonenumber;
	
	//Identification Document
	public String Document;
	
	//Document Details;
	public Long documentId;
	
	// Time on which the customer was created
	public Date timestamp;
}
