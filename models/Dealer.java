// Dealer Model

package models;

import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;


@Entity
@Table(name="Dealer")

public class Dealer extends Model{
	
	/**
	 * @param title
	 * @param firstname
	 * @param lastname
	 * @param dob
	 * @param address
	 * @param landmark
	 * @param dealershipId
	 * @param cityId
	 * @param agencyname
	 * @param adminId
	 * @param godownaddress
	 * @param godownCityId 	
	 */
	public Dealer(Title title, String firstname, String lastname, Date dob,
			String address, String landmark, Long dealershipId, City cityId,
			String agencyname, Admin adminId, String godownaddress,
			City godownCityId,Long phonenumber,Long pincode) {
		super();
		this.title = title;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.address = address;
		this.landmark = landmark;
		this.dealershipId = dealershipId;
		this.cityId = cityId;
		this.agencyname = agencyname;
		this.adminId = adminId;
		this.godownaddress = godownaddress;
		this.godownCityId = godownCityId;
		this.phonenumber =phonenumber;
		this.pincode=pincode;
	//	this.email = email;
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
	
	//Dealership Id;
	public Long dealershipId;
	
	//Admin Location;
	@ManyToOne
	public City cityId;

	// Company name;
	public String agencyname;
	
	// Corresponding Admin Id;
	@ManyToOne
	public Admin adminId;
	
	// Godown Address (To send the Cylinders)
	public String godownaddress;
	
	//Godown location;
	@ManyToOne
	public City godownCityId;
	
	public Long phonenumber;
	
	public Long pincode;
}

