package models;
import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;


@Entity
@Table(name="technicalsupport")

public class TechnicalSupport extends Model{
	
	/**
	 * @param accountId
	 * @param subject
	 * @param query
	 * @param reply
	 *
	 */
	public TechnicalSupport(Account accountId, String subject, String query,
			String reply) {
		this.accountId = accountId;
		this.subject = subject;
		this.query = query;
		this.reply = reply;
	//	this.techpeopleId = techpeopleId;
	}

	public TechnicalSupport() {
		// TODO Auto-generated constructor stub
	}

	//Profile Id;
	@ManyToOne
	public Account accountId;
	
	//subject;
	public String subject;
	
	//query;
	public String query;
	
	//reply;
	public String reply;
	
	
	
	

}
