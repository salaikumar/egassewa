
package controllers;
import java.util.Date;
import java.util.List;

import play.data.validation.Required;
import play.mvc.Controller;
import models.*;
public class DealerController extends Controller{
	
	// Creating a new Dealer 
	
	/*public static void createDealer(String title, String firstname, String lastname, Date dob,
			String address, String landmark, Long dealershipId, String cityId,
			String agencyname,  String godownaddress,
			String godownCityId,Long phonenumber,Long pincode){
		
		Title t ;
		if(title =="Mr"){
			t=Title.Mr;
		}
		else{
			t=Title.Ms;
		}
		Admin a = Admin.find("byAdminEmployeeId",1L).first();
		City c = City.find("byCityname", godownCityId).first();
		City c1 = City.find("byCityname", cityId).first();
		
		//Check if the dealer already exits
		Dealer checkDealer = Dealer.find("byDealershipId",dealershipId).first();
		if(checkDealer != null){
			DealerControllerMap.newconnreq();
			
		}
		else {
			new Dealer( t,  firstname,  lastname,  dob,
					 address,  landmark,  dealershipId,  c,
					 agencyname,  a, godownaddress,
					 c,phonenumber,pincode).save();
			
			AdminControllerMap.index();
		//	render("Dealer Already Delaar exists.");
		}
	}*/
	
	// Trying to retrive the Dealer using his city id
	
	public static void retriveDealerUsingCityId(String city){
		
		//Get the city;
		City cityname = City.find("byCityname",city).first();
		
	//	List<Dealer> dealer = Dealer.find("byCityId",cityname).fetch();
		
		render();
	}
	
	//Retrive the Dealer using the DealerFirstname
	public static void retriveDealerUsingFirstname(String firstname){
		
		List<Dealer> dealer = Dealer.find("byFirstname",firstname).fetch();
		
		render(dealer);
	}
	
	//Retrive the Dealer using his agency name
	public static void retriveDealerUsingAgencyname(String agencyname){
		
		Dealer dealer = Dealer.find("byAgencyname",agencyname).first();
		render(dealer);
	}
	
	//Retrive Dealer using and gas Agency name
	public static void retriveDealerUsingAgencyAndCity(String cityname, String agencyname){
		
		City city = City.find("byCityname", cityname).first();
		Dealer dealer = Dealer.find("byCityIdAndAgencyname",city,agencyname).first();
		
		render(dealer);
	}

	// Retrive dealer  by using dealership id;
	public static void retriveDealerUsingDealershipId(Long dealershipId){
		
		Dealer dealer = Dealer.find("byDealershipId",dealershipId).first();
		
		render(dealer);
	}
}
