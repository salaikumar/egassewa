package models;

import play.data.validation.Required;
import play.db.jpa.Model;
import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;
import java.lang.*;

/**
 * @author Salaikumar
 *
 */
@Entity
@Table(name = "NewApplicationRequest")
public class NewConnectionRequest extends Model {

	// Here is the List of Variables 
	
//	@Required
	public Long applicationnumber;
	@Required
	@ManyToOne
	public State stateId;
	@Required
	@ManyToOne
	public City cityId;
	@Required
	@ManyToOne
	public Dealer dealerId;

	public Title title;
	@Required
	public String firstname;
	
	public String lastname;
	@Required
	public Date dob;
	@Required
	@Column(columnDefinition="Text")
	public String address;
	@Required
	public String landmark;
	@Required
	public Long pincode;
	@Required
	public Long phonenumber;
	@Required
	public String id1;
	@Required
	public Long idnumber;
	@Required
	public String email;
	
	public boolean isConditionsAccepted;
	
	public boolean isConnectionApproved;
	
	public Date timestamp;
	
	public NewConnectionRequest() {
	
	}

	/**
	 * @param applicationnumber
	 * @param stateId
	 * @param cityId
	 * @param dealerId
	 * @param title
	 * @param firstname
	 * @param lastname
	 * @param dob
	 * @param address
	 * @param landmark
	 * @param pincode
	 * @param phonenumber
	 * @param id
	 * @param idnumber
	 * @param email
	 * @param isConditionsAccepted
	 * @param isConnectionApproved
	 * @param timestamp
	 */
	public NewConnectionRequest(Long applicationnumber, State stateId,
			City cityId, Dealer dealerId, Title title, String firstname,
			String lastname, Date dob, String address, String landmark,
			Long pincode, Long phonenumber, String id1,
			Long idnumber, String email, boolean isConditionsAccepted,
			boolean isConnectionApproved, Date timestamp) {
		super();
		this.applicationnumber = applicationnumber;
		this.stateId = stateId;
		this.cityId = cityId;
		this.dealerId = dealerId;
		this.title = title;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.address = address;
		this.landmark = landmark;
		this.pincode = pincode;
		this.phonenumber = phonenumber;
		this.id1 = id1;
		this.idnumber = idnumber;
		this.email = email;
		this.isConditionsAccepted = isConditionsAccepted;
		this.isConnectionApproved = isConnectionApproved;
		this.timestamp = timestamp;
	}

}
