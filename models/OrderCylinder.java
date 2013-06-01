
// OrderCylinder Model: this maintains the orders of Lpg cylinders;
package models;
import java.util.*;
import java.lang.*;

import javax.persistence.*;

import play.data.validation.Required;
import play.db.jpa.*;





//@SuppressWarnings("hiding")
@Entity
@Table(name="ordercylinder")


public class OrderCylinder extends Model {
	
	/**
	 * @param orderedAt
	 * @param orderstatus
	 * 
	 * @param ordernumber
	 * @param gasCylinderId
	 * @param orderCount
	 * @param orderId
	 * @param nextOrderDate 
	 */
	public OrderCylinder(Date orderedAt, String orderstatus,
			Account accountId, Long ordernumber, GasCylinder gasCylinderId,
			Integer orderCount,Date nextOrderDate) {
		this.orderedAt = orderedAt;
		this.orderstatus = orderstatus;
		gasConnectionHolder= accountId;
		this.ordernumber = ordernumber;
		this.gasCylinderId = gasCylinderId;
		this.orderCount = orderCount;
	}

	public OrderCylinder() {
		// TODO Auto-generated constructor stub
	}

	//orderedAt;
	public Date orderedAt;
	
	//Next Date on which the order can be placed;
	public Date nextOrderDate;
	
	//OrderStatus
	
	public String orderstatus;
	
	//Whom Ordered ;
	@Required
	@ManyToOne
	public Account gasConnectionHolder;
	
	//Order Number;0
	public Long ordernumber;
	
	//What is the Cylinder which is actually ordered
	@ManyToOne
	public GasCylinder gasCylinderId;
	
	//Order Count;
	public Integer orderCount;
	
	// Order Placed for dealer 
	
	@ManyToOne
	public Dealer dealerId;
	
	
}

