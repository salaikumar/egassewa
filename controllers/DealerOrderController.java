package controllers;
import java.util.Date;

import java.util.List;

import play.data.validation.Required;
import play.mvc.Controller;
import models.*;


public class DealerOrderController extends DealerOrder {
	
	public static void createDealerDomesticOrder(Long id, String cylinder1,Integer domesticsmallcount,String cylinder2,Integer domesticmediumcount,String cylinder3, Integer domesticlargecount){
		
		DateCreator d1  = new DateCreator();
		
		DealerOrder d = new DealerOrder();
		// Retrive the person
		
		Account dealer = Account.findById(id);
		
		d.account = dealer;
		
		d.gasCylinderType1= GasCylinder.find("byGasCylinderTypeAndGasCylinderUnit",GasCylinderType.DOMESTIC,new Float(2)).first();
		
		d.gasCylinderType1Count = domesticsmallcount;
		
		d.gasCylinderType2= GasCylinder.find("byGasCylinderTypeAndGasCylinderUnit",GasCylinderType.DOMESTIC,new Float(5)).first();
		
		d.gasCylinderType2Count = domesticmediumcount;
		
		d.gasCylinderType3= GasCylinder.find("byGasCylinderTypeAndGasCylinderUnit",GasCylinderType.DOMESTIC,new Float(14.2)).first();
		
		d.gasCylinderType3Count = domesticlargecount;
		
		d.orderAt = d1.getSystemDate();
		
		d.orderstatus = "WAITING";
		
		d.save();
		
		// Get the Gas Cylinder 
		//if 
		
		
	}
	
public static void createDealerCommercialOrder(Long id, String cylinder1,Integer commercialsmallcount,String cylinder2,Integer commercialmediumcount,String cylinder3, Integer commerciallargecount){
		
		DateCreator d1  = new DateCreator();
		
		DealerCommercialOrder d = new DealerCommercialOrder();
		// Retrieve the person
		
		Account dealer = Account.findById(id);
		
		d.account = dealer;
		
		d.gasCylinderType1= GasCylinder.find("byGasCylinderTypeAndGasCylinderUnit",GasCylinderType.COMMERCIAL,new Float(19)).first();
		
		d.gasCylinderType1Count = commercialsmallcount;
		
		d.gasCylinderType2= GasCylinder.find("byGasCylinderTypeAndGasCylinderUnit",GasCylinderType.COMMERCIAL,new Float(35)).first();
		
		d.gasCylinderType2Count = commercialmediumcount;
		
		d.gasCylinderType3= GasCylinder.find("byGasCylinderTypeAndGasCylinderUnit",GasCylinderType.COMMERCIAL,new Float(47.5)).first();
		
		d.gasCylinderType3Count = commerciallargecount;
		
		d.orderAt = d1.getSystemDate();
		
		d.orderstatus = "WAITING";
		
		d.save();
		
		// Get the Gas Cylinder 
		//if 
		
		
	}


	

}
