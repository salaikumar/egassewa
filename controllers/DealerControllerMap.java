package controllers;

import play.*;
import play.cache.Cache;
import play.mvc.*;
import play.mvc.Scope.Session;

import java.util.*;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import models.*;

public class DealerControllerMap extends Controller {
	public static void dealerprofile(){
		
		Account a = (Account) Cache.get("authUser");
		if(a != null)
		render(a);
		else 
			DealerControllerMap.pleaselogin();
	}
	/*
	 * This action will list the orders placed for accessories
	 */
	public static void accessoriesorderlist(){
		
		Account a = (Account) Cache.get("authUser");
		Dealer d = Dealer.find("byDealershipId", a.uniquenumber).first();
		if(a != null){
			List<OrderAccessories> l = OrderAccessories.find("byDealerId",d ).fetch();
			render(l);
		}
		else 
			DealerControllerMap.pleaselogin();

	}
	/*
	 * This action will help to set status for accessories order
	 */
	public static void accessoriesstatuspage(Long id){
		Account a = (Account) Cache.get("authUser");
		if (a != null){
			OrderAccessories o = OrderAccessories.findById(id);
			render(o);
		}
		else	
			DealerControllerMap.pleaselogin();
	}
	
	public static void checkorder(){
		Account a = (Account) Cache.get("authUser");
		if(a != null)
		render();
		else 
			DealerControllerMap.pleaselogin();
	}
	
	public static void checkapplications(){
		Account a = (Account) Cache.get("authUser");
		if(a != null)
		render();
		else 
			DealerControllerMap.pleaselogin();
	}
	
	public static void dealerlogin() {
		render();
	}

	
	public static void NewConnectionList(){
		
	// Get the dealer Who is logging in
		Account a = (Account) Cache.get("authUser");
		System.out.println("Current User"+a);
		
		Dealer d = Dealer.find("byDealershipId", a.uniquenumber).first();
		System.out.println("Current User's Dealer Account"+d);
		// Get all the requests for new Connection and display it as links in this page
		
		// As the below Query, i'm going to create a Criteria
	// SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		//   session = (Session) sessionFactory.openSession();
		 //  Property age = Property.forName("isConnectionApproved");
		//List n = (List) ((org.hibernate.Session) session).createCriteria(NewConnectionRequest.class).addOrder(Order.desc("timestamp")).add(Restrictions.eq("dealerId", d)).add(Restrictions.eq("isConnectionApproved",false));
		
		//System.out.println("Criteria query Result"+n);
		
		
	
		
		
		List<NewConnectionRequest> n = NewConnectionRequest.find("isConnectionApproved is false order by timestamp desc").fetch();
		
		  
		//System.out.println("Connection Request Applications for Dealer"+n);
		if(a != null)
			render(n);
		else 
			DealerControllerMap.pleaselogin();
			
	}
	
	public static void pleaselogin(){
		render();
	}
	public static void cylinderorderlist(){
		
		// The Current Dealer logged in
		Account a = (Account) Cache.get("authUser");
		
		
		List<OrderCylinder> o = OrderCylinder.findAll();
		
		System.out.println("The Dealers for Whom Order has beed placed "+ o);
		
		
		System.out.println("Current Dealer Logged in"+a);
		
		// Get the Dealer Details of him
		Dealer d = Dealer.find("byDealershipId",a.uniquenumber).first();
		
		System.out.println("Dealer for the Account"+d);
		// Now, get the Ordered Cylinders
		List<OrderCylinder> l = OrderCylinder.find("byDealerId", d).fetch();
		
		//Orders placed for  the Dealer are as follows
		
		System.out.println("Orders Placed for Dealer are as follows"+l);
		
		render(a,l);
		
	}
	
	public static void setorderstatus(Long id){
		
		OrderCylinder l = OrderCylinder.findById(id);
		
		System.out.println("Order cylinder passed" +l );
		render(l);
	}
	
	public static void newconnection(Long id){
		render();
	}
	public static void locchange() {
		
		render();
	}
	
	public static void newconnreq(Long id) {
		NewConnectionRequest n = NewConnectionRequest.findById(id);
		render(n);
	}
	
	public static void localareachange(Long id){
		
	 Customer c = null;
		Account   a = (Account) Cache.get("authUser");
    	if(a != null){
    	
    		ChangeLocationApplication l = ChangeLocationApplication.findById(id);
    		//Customer c= Customer.find("byGasConnectionNumber", a.uniquenumber).first();
    		 c= l.customerId;
    		render(l,a,c);
    	}
    	else{
    		DealerControllerMap.pleaselogin();
    	}
	}
	
	public static void locmigration(Long id) {
		
		 Customer c = null;
			Account   a = (Account) Cache.get("authUser");
	    	if(a != null){
	    	
	    		ChangeLocationApplication l = ChangeLocationApplication.findById(id);
	    		//Customer c= Customer.find("byGasConnectionNumber", a.uniquenumber).first();
	    		 c= l.customerId;
	    		render(l,a,c);
	    	}
	    	else{
	    		DealerControllerMap.pleaselogin();
	    	}
	}
	
	// Location Change List page
	
	public static void localchangelist(){
		 
		
		List<ChangeLocationApplication> l = ChangeLocationApplication.find("Locationchangetype =? AND isApproved is false order by  timestamp desc","CHANGELOCATIONALONE").fetch();
		
		System.out.println("Location Change Applications"+ l);
		
		render(l);
	}
	
	public static void locmigrationlist(){
		List<ChangeLocationApplication> l = ChangeLocationApplication.find("Locationchangetype =? AND isApproved is false order by  timestamp desc","CHANGELOCATIONANDDEALER").fetch();
		System.out.println("Location Change Applications"+ l);
		render(l);
		
		
	}
	/*
	 * This feature will generate reports for a particulars of him
	 */
	public static void generatereports(){
		
		Account a = (Account) Cache.get("authUser");
		
		Dealer d = Dealer.find("byDealershipId", a.uniquenumber).first();
		if(a!= null)
		{
			/*
			 * Get the list of orders placed for this dealer
			 *Classify it as Total Confirmed 
			 *Total Delivered and so on 
			 *
			 */
			List<OrderCylinder> totalorders = OrderCylinder.find("byDealerId",d).fetch();
			//List<OrderCylinder> confirmorders = OrderCylinder.f
			
		}
		else{
			DealerControllerMap.pleaselogin();
		}
		
		
	}
}