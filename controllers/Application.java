package controllers;

import play.*;
import play.cache.Cache;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller {

	/*
	 * This Action will render the home page of the application
	 */
    public static void index() {
    	/*
    	 * Retrieve the GasCylinders 
    	 */
    	GasCylinder g19= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("19")).first();
    	GasCylinder g14= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("14")).first();
    	/*
    	 * Retrieive the Accessories
    	 */
    	Accessories pro  = Accessories.find("byName","BURNER PRO").first();
    	Accessories stove  = Accessories.find("byName","PORTABLE STOVE").first();
    	Accessories gas  = Accessories.find("byName","GAS STOVE").first();
    	Accessories reg  = Accessories.find("byName","REGULATOR").first();
    	Accessories pet  = Accessories.find("byName","PETROMAX CYLINDER").first();
    	Accessories auto  = Accessories.find("byName","AUTOMATIC GAS STOVE").first();
    	Accessories trad  = Accessories.find("byName","TRADITIONAL STOVE").first();
    	Accessories bun  = Accessories.find("byName","BUNSEN BURNER").first();
    	Accessories hose  = Accessories.find("byName","LPG HOSE").first();
        render(g19,g14,pro,stove,gas,reg,pet,auto,trad,bun,hose);
    }
    
    /*
     * This action will display the forgot password page
     */
    public static void forgotpassword(){
    	render();
    }
    
    /*
     * This Action will render the Search Dealers Page
     */
    public static void searchdealers(){
    	List<State> s = State.findAll();	
    	City c = City.find("byCityname","KUMBAKONAM").first();
    	List<City> c1 = City.findAll();
    	System.out.println("City name"+ c);
    	
    	
    	render(s,c1);
    }
    /*
     * This Action will render the result page of the searchdealers
     * 
     */
    public static void searchdealerslist(Long stateId,Long cityId){
    	
    	
    	System.out.println("Id of State Sent"+ stateId);
    	System.out.println("Id of City Sent"+ cityId);
    	/*
    	 * Get the State;
    	 */
    	State s= State.findById(stateId);
    	/*
    	 * Get the List of Cities in the given state
    	 */
    	City city = City.findById(cityId);
    	
    	System.out.println("City chosen by the Dealer"+ city.cityname);
    	List<Dealer> l = Dealer.find("byCityId", city).fetch();
    	
    	System.out.println("List of the dealers in the chosen city"+ l);
    	
    	render(l);
    	
    	
    	/*
    	 * Get the Dealers in the given city
    	 */
    }
    /*
     * This Action Will List the Details of single Detail
     */
    public static void dealerdetails(Long id){
    	
    	Dealer a =Dealer.findById(id);
    	
    	Account l = Account.find("byUniquenumber", a.dealershipId).first();
    	
    	render(a,l);
    }
    
	public static void add() {
        render();
    }
	public static void pleaselogin(){
		render();
	}
	public static void about() {
		render();
	}
	
	public static void products(){
		
		render();
	}
	
	public static void apply(){
	List<State> s = State.findAll();	
	City c = City.find("byCityname","KUMBAKONAM").first();
	List<City> c1 = City.findAll();
	System.out.println("City name"+ c);
	
	List<Dealer> d = Dealer.findAll();
		render(s,c,d,c1);
	}
	
	/*The following function is implemented to load the cities based on the corresponding state*/
	public static void changeCity(){
		
	}
	
	public static void faq(){
		render();
	}
	
	public static void loginerrorpage(){
		render();
	}
	
	public static void account(){
		render();
	}
	
	public static void security(){
		render();
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
	
	public static void accessories(){
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
	
	public static void book(){
		Account acc = (Account) Cache.get("authUser");
		System.out.println(acc);
		render(acc);
	}
	
	public static void history(){
		render();
	}
	
	public static void tariff(){
		render();
	}
	
	public static void dom(){
		GasCylinder g2= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("2")).first();
		GasCylinder g5= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("5")).first();
		GasCylinder g14= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("14")).first();
		render(g2,g5,g14);
	}
	
	public static void nondom(){
		GasCylinder g19= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("19")).first();
		GasCylinder g35= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("35")).first();
		GasCylinder g47= GasCylinder.find("byGasCylinderUnit",Float.parseFloat("47.5")).first();
		render(g19,g35,g47);
	}
	
	public static void smalldom(){
		render();	
	}
	
	public static void underconstruct(){
		render();
	}
	
	public static void accessoriesbook(){
		render();
	}
	
	public static void emailerror(){
		render();
	}
	public static void invalidgas(){
		render();
	}
	public static void erroraccount()
	{
		render();
	}
	
	public static void regsuccess(){
		render();
	}
	
	public static void accexisterror(){
		render();
	}
}