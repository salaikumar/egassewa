 // This Model holds Details of all the classes

package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;


@Entity
@Table(name="Accessories")
public class Accessories extends Model{
	
	public Accessories(String name, Integer price) {
		super();
		this.name = name;
		this.price = price;
	}

	public Accessories() {
		// TODO Auto-generated constructor stub
	}

	// Name of the accessory
	public String name;
	
	//Price of Accessory
	public Integer price;
	

}
