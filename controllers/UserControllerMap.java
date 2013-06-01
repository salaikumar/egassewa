package controllers;

import play.*;
import play.cache.Cache;
import play.mvc.*;

import java.util.*;

import models.*;

public class UserControllerMap extends Controller {

    public static void index() {
        render();
    }
    /*
     * This Action will do list the Location Change Applications by the customer
     */
    public static void checkappstatus(){
    	
    	Account   a = (Account) Cache.get("authUser");
    	if(a != null){
    	List<ChangeLocationApplication> l= ChangeLocationApplication.find("byAccount", a).fetch();
    	
    	render(l);}
    	else
    	{
    		Application.pleaselogin();
    	}
    }
    
    /*
     * This Action will display the Html page with Details about Location Change Application 
     * 
     */
    public static void checklocation(Long id){
    	Account   a = (Account) Cache.get("authUser");
    	if(a != null){
    	
    		ChangeLocationApplication l = ChangeLocationApplication.findById(id);
    		Customer c= Customer.find("byGasConnectionNumber", a.uniquenumber).first();
    		render(l,a,c);
    	}
    	else{
    		Application.pleaselogin();
    	}
    }
    
    
    /*
     * This method will render the order details of the customer
     * The Id Passed by from the last page will serve as the key for accessing the OrderDetails
     */
    public static void orderdetails(Long id){
    	
    	Account  a = (Account) Cache.get("authUser");
    	if(a!= null){
    		
    		/*
    		 * Get the Order Placed by this Customer
    		 */
    		OrderCylinder l = OrderCylinder.findById(id);
    		
    		/*
    		 * By using the gasConnection Number get the customer details
    		 */
    		Customer c =Customer.find("byGasConnectionNumber", a.uniquenumber).first();
    		
    		render(l,c);
        }
    	else{
    		Application.pleaselogin();
    	}
    	}
    
    public static void locchangesuccess(){
    
    	Account  a = (Account) Cache.get("authUser");
    	
    	if (a!= null){
    	render();}
    	else{
    		Application.pleaselogin();
    	}
    	
    }
	public static void booksuccess(){
		Account  a = (Account) Cache.get("authUser");
    	
    	if (a!= null){
    	render();}
    	else{
    		Application.pleaselogin();
    	}
    	
	}
	public static void about() {
		Account  a = (Account) Cache.get("authUser");
    	
    	if (a!= null){
    	render();}
    	else{
    		Application.pleaselogin();
    	}
    	
	}
	
	public static void products(){
		Account  a = (Account) Cache.get("authUser");
    	
    	if (a!= null){
    	render();}
    	else{
    		Application.pleaselogin();
    	}
    	
	}
	
	public static void apply(){
		render();
	}
	
	public static void faq(){
		render();
	}
	
	public static void account(){
		render();
	}
	
	public static void bookhistory(){
		Account a = (Account) Cache.get("authUser");
		List<OrderCylinder> l = OrderCylinder.find("byGasConnectionHolder", a).fetch();
		if(a!= null){
		render(a,l);}
		
		else{
			Application.pleaselogin();
		}
	}
	
	public static void cancelorder(){
		Account a = (Account) Cache.get("authUser");
		List<OrderCylinder> l = OrderCylinder.find("byGasConnectionHolder", a).fetch();
		
		if(a != null)
		{
		render(a,l);
		}
		else{
			Application.pleaselogin();
		}
		
	}
	
	//This function is used to Delete Orders
	public static void deleteOrder( Long id){
		
		Account a = (Account) Cache.get("authUser");
		OrderCylinder o = OrderCylinder.findById(id);
		o._delete();
		
		if (a!= null){
		UserControllerMap.cancelorder();}
		else{
			Application.pleaselogin();
		}
	}
	
	public static void bookstatus(Long id) {
		Account a = (Account) Cache.get("authUser");
		List<OrderCylinder> l = OrderCylinder.find("byGasConnectionHolder", a).fetch();
		if( a!= null){
		render(a,l);}
		else{
			Application.pleaselogin();
		}
	}
	
	public static void security(){
		render();
	}
	
	public static void temp(Account account){
		render(account);
	}
	
	
	public static void contact(){
		render();
	}
	
	public static void rules(){
		render();
	}
	
	public static void create_acc(){
		render();
	}
	
	public static void temp(){
		render();
	}
	public static void locchange() {
		Account a = (Account) Cache.get("authUser");
		if( a != null)
		render();
		else 
			Application.pleaselogin();
	}
	
	public static void newbook(){
		Account acc = (Account) Cache.get("authUser");
		
		GasCylinder g2= GasCylinder.find("byGasCylinderUnit",new Float("2")).first();
		System.out.println("GasCylinerUnit 2"+g2);
		
		
		GasCylinder g5= GasCylinder.find("byGasCylinderUnit",new Float("5")).first();
		System.out.println("GasCylinerUnit 5"+g5);
		
		GasCylinder g14= GasCylinder.find("byGasCylinderUnit",new Float("14")).first();
		System.out.println("GasCylinerUnit 2"+g14);
		
		GasCylinder g19= GasCylinder.find("byGasCylinderUnit",new Float("19")).first();
		System.out.println("GasCylinerUnit 2"+g19);
		
		GasCylinder g35= GasCylinder.find("byGasCylinderUnit",new Float("35")).first();
		System.out.println("GasCylinerUnit 2"+35);
		
		GasCylinder g47= GasCylinder.find("byGasCylinderUnit",new Float("47.5")).first();
		System.out.println("GasCylinerUnit 2"+g47);
		if (acc !=  null)
		render(acc,g2,g5,g14,g19,g35,g47);
		else
			Application.pleaselogin();
	}
	
	public static void updatesuccess(Long id){
		Account a = Account.findById(id);
		if (a!= null)
		render();
		else
			Application.pleaselogin();
	}
	
	public static void updateaccount(){
		
		Account a = (Account) Cache.get("authUser");
		if( a != null)
		render(a);	
		else
			Application.pleaselogin();
	}
	
	public static void updatefail(){
		
		render();	
	}
	
	public static void profile(){
		Account acc = (Account) Cache.get("authUser");
		System.out.print(acc);
		if (acc != null)
			render(acc);
		else{
			Application.pleaselogin();
		}
	}
	public static void localareachange(){
		Account acc = (Account) Cache.get("authUser");
		if(acc != null)
		render();
		else 
			Application.pleaselogin();
	}
	
	public static void locmigration() {
		Account acc = (Account) Cache.get("authUser");
		if(acc != null)
		render();
		else
			Application.pleaselogin();
			
	}
	
	public static void accessories(){
		Account acc = (Account) Cache.get("authUser");
		
		if(acc != null)
		{
			Accessories pro  = Accessories.find("byName","BURNER PRO").first();
	    	Accessories stove  = Accessories.find("byName","PORTABLE STOVE").first();
	    	Accessories gas  = Accessories.find("byName","GAS STOVE").first();
	    	Accessories reg  = Accessories.find("byName","REGULATOR").first();
	    	Accessories pet  = Accessories.find("byName","PETROMAX CYLINDER").first();
	    	Accessories auto  = Accessories.find("byName","AUTOMATIC GAS STOVE").first();
	    	Accessories trad  = Accessories.find("byName","TRADITIONAL STOVE").first();
	    	Accessories bun  = Accessories.find("byName","BUNSEN BURNER").first();
	    	Accessories hose  = Accessories.find("byName","LPG HOSE").first();
	        
			render(pro,stove,gas,reg,pet,auto,trad,bun,hose);
		}
		else
			Application.pleaselogin();
	}
	
	// This Page will be replaced by Book Page
	public static void book(){
		
		//Use Cache memory to see the list of profiles of the current Account
		
		Account acc = (Account) Cache.get("authUser");
	
		GasCylinder g2= GasCylinder.find("byGasCylinderUnit",new Float("2")).first();
		System.out.println("GasCylinerUnit 2"+g2);
		
		
		GasCylinder g5= GasCylinder.find("byGasCylinderUnit",new Float("5")).first();
		System.out.println("GasCylinerUnit 5"+g5);
		
		GasCylinder g14= GasCylinder.find("byGasCylinderUnit",new Float("14")).first();
		System.out.println("GasCylinerUnit 2"+g14);
		
		GasCylinder g19= GasCylinder.find("byGasCylinderUnit",new Float("19")).first();
		System.out.println("GasCylinerUnit 2"+g19);
		
		GasCylinder g35= GasCylinder.find("byGasCylinderUnit",new Float("35")).first();
		System.out.println("GasCylinerUnit 2"+35);
		
		GasCylinder g47= GasCylinder.find("byGasCylinderUnit",new Float("47.5")).first();
		System.out.println("GasCylinerUnit 2"+g47);
		
		
		
		if(acc != null)
		render(acc,g2,g5,g14,g19,g35,g47);
		else
			Application.pleaselogin();
	}
	public static void bookfail(){
		Account acc = (Account) Cache.get("authUser");
		
		if(acc != null)
		render();
		else
			Application.pleaselogin();
	}
	
	public static void history(){
		render();
	}
	
	public static void tariff(){
		render();
	}
	
	public static void dom(){
		Account acc = (Account) Cache.get("authUser");
		GasCylinder g2= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("2")).first();
		GasCylinder g5= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("5")).first();
		GasCylinder g14= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("14")).first();
		if(acc != null)
			render(g2,g5,g14);
		else
			Application.pleaselogin();
	}
	
	public static void nondom(){
		Account acc = (Account) Cache.get("authUser");
		GasCylinder g19= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("19")).first();
		GasCylinder g35= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("35")).first();
		GasCylinder g47= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("47.5")).first();
		if(acc != null)
		render(g19,g35,g47);
		else
			Application.pleaselogin();
	}
	
	public static void smalldom(){
		Account acc = (Account) Cache.get("authUser");

		GasCylinder g19= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("19")).first();
		GasCylinder g35= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("35")).first();
		GasCylinder g47= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("47.5")).first();
		
		if(acc != null){
		render(g19,g35,g47);}
		else
			Application.pleaselogin();
	}	
	
	
	public static void underconstruct(){
		render();
	}
	
	public static void accessoriesbook(){
		Account a = (Account) Cache.get("authUser");
		if(a != null)
		render();
		else
			Application.pleaselogin();
	}
	
	/*
	 * This Action will list the Accessories Order Placed by customer
	 */
	public static void accessoriesorderlist(){
		
		Account a = (Account) Cache.get("authUser");
		if(a != null){
		/*
		 * Get the list of orders placed by this is account holder in controller
		 */
			List<OrderAccessories> o = OrderAccessories.find("byAccountId", a).fetch(); 
			render(o);}
		else
			Application.pleaselogin();
	}
	public static void individualaccesoriesorder(Long id){
		Account a = (Account) Cache.get("authUser");
		if(a != null){
		
			OrderAccessories o = OrderAccessories.findById(id);
			
			/*
			 * Get the customer details
			 * 
			 */
			Customer c= Customer.find("byGasConnectionNumber", a.uniquenumber).first();
			render(o,c);
		}
		else
			Application.pleaselogin();

		
	}
	
	public static void statuscheckoptions(){
		Account a = (Account) Cache.get("authUser");
		if(a != null)
		render();
		else
			Application.pleaselogin();
		
	}
}