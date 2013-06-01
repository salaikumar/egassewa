// State Details
package models;
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;


@Entity
@Table(name="State")

public class State extends Model {

	/**
	 * @param statename
	 * @param stateId
	 * @param c
	 */
	public State(String statename,Long stateId ,Country c) {
		this.statename = statename;
		this.stateId = stateId;
		this.countryId = c;
	}

	//State Name
	public String statename;
	
	//StateId
	public Long stateId;
	
	//Country Id;
	@ManyToOne
	public Country countryId;
}
