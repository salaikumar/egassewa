// Account Model - Holds the Online CustomerAccount Details
package models;
import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;
import java.lang.*;


/**
 * @author Salaikumar
 *
 */
@Entity
@Table(name="Account")

public class Account extends Model {
	
	/**
	 * @param username
	 * @param password
	 */
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Account(String username, String password, Long uniquenumber,
			String accountHolder, String email, Long phonenumber,
			Date createdAt) {
		super();
		this.username = username;
		this.password = password;
		this.uniquenumber = uniquenumber;
		this.accountHolder = accountHolder;
		this.email = email;
		this.phonenumber = phonenumber;
		this.createdAt = createdAt;
	}

	public Account() {
		// TODO Auto-generated constructor stub
	}

	//User name;
	public String username;
	
	//password;
	public String password;
	
	//UniqueNumber;
	public Long uniquenumber;
	
	//Account Holder
	public String accountHolder;
	
	//e-mail;
	public String email;
	
	//Phone Number;
	public Long phonenumber;
	
	// Time of Account Creation;
	public Date createdAt;
	
	
}
