//This class maintains a history of the location changes made by customer

package models;
import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;


@Entity
@Table(name="changelocation")
public class ChangeLocation extends Model {
	
	/**
	 * @param timestamp
	 * @param customerId
	 * @param locationchangetype
	 * @param newAddress
	 * @param newCity
	 * @param dealerId
	 * @param newLandmark
	 * @param newPincode
	 */
	public ChangeLocation(Date timestamp, Account customerId,
			String locationchangetype, String newAddress,
			City newCity, Dealer dealerId, String newLandmark, Long newPincode) {
		super();
		this.timestamp = timestamp;
		this.customerId = customerId;
		this.locationchangetype = locationchangetype;
		this.newAddress = newAddress;
		this.newCity = newCity;
		this.dealerId = dealerId;
		this.newLandmark = newLandmark;
		this.newPincode = newPincode;
	}

	public ChangeLocation() {
		// TODO Auto-generated constructor stub
	}

	//timestamp;
	public Date timestamp;
	
	//get the gas Connection Number;
	@ManyToOne
	public Account customerId;
	
	//get the type of location change
	public String locationchangetype;
	
	//new address;
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
}
