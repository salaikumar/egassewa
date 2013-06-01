package models;
import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;


@Entity
@Table(name="profile")

public class Profile extends Model {
	
	/**
	 * @param profilename
	 * @param customernumber
	 * @param createdAt
	 */
	public Profile(String profilename, Long customernumber, Date createdAt , Long id) {
		this.profilename = profilename;
		this.customernumber = customernumber;
		this.createdAt = createdAt;
		
		Account a = Account.findById(id);
		accountId = a;
		
	}

	public Profile() {
		// TODO Auto-generated constructor stub
	}

	//Profile name
	public String profilename;
	
	//Consumernumber
	
	public Long customernumber;
	
	//Created Date;
	public Date createdAt;
	
	//Account Id
	/**
	 * 
	 */
	public Account accountId;
	
}
