// City Details
package models;
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;


@Entity
@Table(name="City")

public class City extends Model{
	
	/**
	 * @param cityname
	 * @param pincode
	 * @param stateId
	 */
	public City(String cityname, Long pincode, State stateId) {
		super();
		this.cityname = cityname;
		this.pincode = pincode;
		this.stateId = stateId;
	}

	public City() {
		// TODO Auto-generated constructor stub
	}


	// City name
	public String cityname;
	
	// pin code;
	public Long pincode;
	
	// StateId
	@ManyToOne
	public State stateId;

}
