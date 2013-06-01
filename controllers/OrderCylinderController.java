package controllers;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.City;
import models.Dealer;
import models.GasCylinder;
import models.IdentificationProof;
import models.OrderStatuss;
import models.Profile;
import models.Title;
import play.mvc.Controller;
import models.*;

public class OrderCylinderController extends Controller{
	

	// Sequence Generator
	static Long autoIncrement=1000L;
	
	// Create an Order for Customer
	public static void createCylinderOrder(Profile profileId,Float cylinderUnit, GasCylinderType gasCylinderType,Integer orderCount){
		
		
		// First the id of the cylinder Ordered
		GasCylinder gasCylinderId = GasCylinder.find("byGasCylinderUnitAndGasCylinderType",cylinderUnit,gasCylinderType).first();
		
		
		
		OrderStatuss orderStatus =OrderStatuss.WAITING;
					
		
		// Now let us check if he had already placed any order of the same Cylindertype within the past 20 days
		// Oops i need to include the past data of booking
		OrderCylinder checkOrder = OrderCylinder.find("byProfileIdAndGasCylinderId", profileId,gasCylinderId).first();
		
		
		
	}
	
	//Retrieve the order using order number;
	public static void retriveOrderByOrderNumber(Long orderNumber){
		
		OrderCylinder retriveOrder = OrderCylinder.find("byOrderNumber", orderNumber).first();
		
		if(retriveOrder != null){
			// Render the object
		}
		else{
			// Display no such Order exists
		}
	}
	
	// Retrieve orders by date
	public static void retriveOrderByDate(String orderDate){
		
		// Convert the String into Date
		DateCreator d = new DateCreator();
		Date date = d.dateCreator(orderDate);
		
		List<OrderCylinder> order = OrderCylinder.find("byorderedAt",date).fetch();
		
		// now display the object
	}
	public static void setOrderStatus(Long Id , String orderstatus){
		
		OrderCylinder order = OrderCylinder.findById(Id);
		order.orderstatus = orderstatus;
		order.save();
		
		//display status changed
	}
	
	// Cancel order
	// For cancelling order, u should do it within 24 hr of booking;
	
	
	

}
