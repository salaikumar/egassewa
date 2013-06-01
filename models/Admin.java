// Admin Model.It is one of the master tables.Contains all the details of  the Admin
package models;
import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;


@Entity
@Table(name="admin")


public class Admin extends Model {

	/**
	 * @param title
	 * @param firstname
	 * @param lastname
	 * @param dob
	 * @param address
	 * @param landmark
	 * @param adminEmployeeId
	 * @param cityId
	 */
	public  Admin(String title, String firstname, String lastname, Date dob,
			String address, String landmark, Long adminEmployeeId, City cityId,Long phonenumber,Long pincode) {
		super();
		this.title = title;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.address = address;
		this.landmark = landmark;
		this.adminEmployeeId = adminEmployeeId;
		this.cityId = cityId;
		this.phonenumber=phonenumber;
		this.pincode = pincode;
	}  

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	// Title of Admin;
	public String title;
	
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
	
	//Admin employeeId;
	public Long adminEmployeeId;
	
	//Admin Location;
	@ManyToOne
	public City cityId;
	
	//Phone number
	public Long phonenumber;
	
	//Admin of the branch
	public String branchAddress;
	
	
	//Pincode
	public Long pincode;
}
