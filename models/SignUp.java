package models;
import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;


@Entity
@Table(name="signupforms")

public class SignUp  extends Model {
	
	/**
	 * @param username
	 * @param password
	 * @param uniquenumber
	 * @param accountHolder
	 * @param email
	 * @param phonenumber
	 * @param appliedAt
	 * @param isActivated
	 */
	public SignUp(String username, String password, Long uniquenumber,
			String accountHolder, String email, String phonenumber,
			Date appliedAt, Boolean isActivated) {
		this.username = username;
		this.password = password;
		this.uniquenumber = uniquenumber;
		this.accountHolder = accountHolder;
		this.email = email;
		this.phonenumber = phonenumber;
		this.appliedAt = appliedAt;
		this.isActivated = isActivated;
	}

	public SignUp() {
		// TODO Auto-generated constructor stub
	}

	//Username;
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
	public String phonenumber;
	
	// Time of Account Creation;
	public Date appliedAt;
	
	//To verify the account status;
	public Boolean isActivated;
}

