package controllers;

import java.util.Date;
import java.util.List;

import play.data.validation.Required;
import play.mvc.Controller;
import models.*;


public class AdminController extends Controller {
	
	public static void createAdmin(Long adminEmployeeID,String branchAddress,String cityId,String title, String firstname, String lastname, Date dob,
			String address, String landmark,Long pincode,Long phonenumber){
		
		
		Admin admin = new Admin();

		City c = City.find("byCityname",cityId ).first();
		admin.address=address;
		admin.adminEmployeeId=adminEmployeeID;
		admin.branchAddress =branchAddress;
		admin.cityId= c;
		admin.dob = dob;
		
		admin.firstname=firstname;
		admin.landmark=landmark;
		admin.lastname=landmark;
		admin.phonenumber=phonenumber;
		admin.pincode=pincode;
		
		
		admin.title =title;
		
		
		admin.save();
		AdminControllerMap.index();
	}

}
