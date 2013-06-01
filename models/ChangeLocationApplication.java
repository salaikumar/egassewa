//This class maintains a history of the location changes made by customer

package models;
import java.util.*;

import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.*;
import java.lang.*;


@Entity
@Table(name="changelocationapplication")
public class ChangeLocationApplication extends Model {
	
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
	public ChangeLocationApplication(Date timestamp, Long applicationnumber,
			Customer customerId, String locationchangetype,
			String newAddress, City newCity, Dealer dealerId,
			String newLandmark, Long newPincode, Boolean isApproved) {
		super();
		this.timestamp = timestamp;
		this.applicationnumber = applicationnumber;
		this.customerId = customerId;
		this.locationchangetype = locationchangetype;
		this.newAddress = newAddress;
		this.newCity = newCity;
		this.dealerId = dealerId;
		this.newLandmark = newLandmark;
		this.newPincode = newPincode;
		this.isApproved = isApproved;
	}

	public ChangeLocationApplication() {
		// TODO Auto-generated constructor stub
	}
	@ManyToOne
	public Account account;
	//timestamp;
	public Date timestamp;
	
	//ApplicationNumber;
	public Long applicationnumber;
	
	//get the gas Connection Number;
	@ManyToOne
	public Customer customerId;
	
	//get the type of location change
	public String locationchangetype;
	
	//new address;
	@Required
	@Lob
	public String newAddress;
	
	//new City;
	@ManyToOne
	public City newCity;
	
	//new Dealer;
	@ManyToOne
	public Dealer dealerId;
	
	//new Landmark;
	public String newLandmark;
	
	//new Pincode;
	public Long newPincode;
	
	//Is Approved
	public Boolean isApproved;
	
	//Status
	public String status;
}
