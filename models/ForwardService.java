//This Model is created to maintain the Location change details of user;
/* If ChangeLocationAlone takes place , just update the database, 
 * Else cases
 * Forward the Same service to another dealer will take place
 * For that we will issue a Forward Service form 
 * If the user is not willing to continue the service the endservice process will take place*/
package models;
import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;


@Entity
@Table(name="forwardservicecertificate")
public class ForwardService extends Model{
	
	/**
	 * @param applicationnumber
	 * @param oldDealerId
	 * @param newDealerId
	 * @param oldcityId
	 * @param newcityId
	 * @param certificateId
	 * @param timestamp
	 */
	public ForwardService(ChangeLocationApplication applicationnumber,
			Dealer oldDealerId, Dealer newDealerId, City oldcityId,
			City newcityId, Long certificateId, Date timestamp) {
		this.applicationnumber = applicationnumber;
		this.oldDealerId = oldDealerId;
		this.newDealerId = newDealerId;
		this.oldcityId = oldcityId;
		this.newcityId = newcityId;
		this.certificateId = certificateId;
		this.timestamp = timestamp;
	}

	public ForwardService() {
		// TODO Auto-generated constructor stub
	}

	//Application id
	@ManyToOne
	public ChangeLocationApplication applicationnumber;
	
	//get the old dealer;
	@ManyToOne
	public Dealer oldDealerId;
	
	//get the new DealerId;
	@ManyToOne 
	public Dealer newDealerId;
	
	//get the oldLocation
	@ManyToOne
	public City oldcityId;
	
	//get the newLocation;
	@ManyToOne
	public City newcityId;
	
	//CertificateId;
	public Long certificateId;
	
	//Time Stamp;
	public Date timestamp;
}
