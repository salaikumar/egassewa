package models;
import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;


@Entity
@Table(name="Country")
public class Country extends Model {
	
		/**
	 * @param countryname
	 * @param countryId;
	 */
	public Country(String countryname,Long countryId) {
		super();
		this.countryname = countryname;
		this.countryId = countryId;
	}

		//Country name;
		public String countryname;
		
		//Country Id;
		public Long countryId;

}
