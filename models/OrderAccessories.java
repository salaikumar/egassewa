
// OrderAccessories Model: this maintains the orders of Lpg Accessories;
package models;
import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;
import java.lang.*;


@Entity
@Table(name="orderaccessories")


public class OrderAccessories extends Model {
	
	/**
	 * @param ordertime
	 * @param orderstatuss
	 * @param profileid
	 * @param ordernumber
	 * @param accessoriesId
	 * @param orderCount
	 */
	public OrderAccessories(Date ordertime, String orderstatus,
			Account profileid, Long ordernumber, Accessories accessoriesId,
			Integer orderCount) {
		this.ordertime = ordertime;
		this.orderstatus = orderstatus;
		this.accountId = profileid;
	//	this.ordernumber = ordernumber;
		this.accessoriesId = accessoriesId;
		this.orderCount = orderCount;
	}

	public OrderAccessories() {
		// TODO Auto-generated constructor stub
	}

	//orderedAt;
	public Date ordertime;
	
	//OrderStatus
	public String orderstatus;
	
	//Whom Ordered;
	@ManyToOne
	public Account accountId;
	
	//Order Number;
//	public Long ordernumber;
	
	//What is the Cylinder which is actually ordered
	@ManyToOne
	public Accessories accessoriesId;
	
	//Order Count: 
	public Integer orderCount;
	
	@ManyToOne
	public Dealer dealerId;
	
}
