package controllers;
import java.util.Date;
import java.util.List;

import play.data.validation.Required;
import play.mvc.Controller;
import models.*;

public class GasCylinderController extends Controller{
	
	//Creating gas Cylinder
	public static void createCylinder(String gasCylinderType, Float gasCylinderUnit,
			Integer gasCylinderPrice){
		
		GasCylinder gasCylinder = GasCylinder.find("byGasCylinderTypeAndGasCylinderUnitAndGasCylinderPrice", gasCylinderType,gasCylinderUnit,gasCylinderPrice).first();
		if(gasCylinder != null){
			//render("GasCylinder of this specfications already exists");
			AdminControllerMap.gascylinder();
		}
		else {
			new GasCylinder(gasCylinderType,gasCylinderUnit,gasCylinderPrice).save();
			
			//render("GasCylinder with the required specifications is obtained");
			Application.products();
		}
	}
	//Updating the Gas Cylinder Specfications
		public static void updateCylinder ( String gasCylinderType , Float gasCylinderUnit,
				Integer gasCylinderPrice , String newgasCylinderType , Float newgasCylinderUnit,
				Integer newgasCylinderPrice) {
			
			// Get the current record;
			GasCylinder gasCylinder = GasCylinder.find("byGasCylinderTypeAndGasCylinderUnitAndGasCylinderPrice", gasCylinderType,gasCylinderUnit,gasCylinderPrice).first();
			
			gasCylinder.gasCylinderUnit = newgasCylinderUnit;
			gasCylinder.gasCylinderType = newgasCylinderType;
			gasCylinder.gasCylinderPrice =newgasCylinderPrice;
			
			gasCylinder.save();
			
			render("Data updated");
			
		}
		
		// Get the Price  using other specfications
		public static void getCylinderPrice(Integer gasCylinderType,Float gasCylinderUnit){
			
			GasCylinder gasPrice = GasCylinder.find("byGasCylinderTypeAndGasCylinderUnit", gasCylinderType, gasCylinderUnit).first();
			
			render(gasPrice.gasCylinderPrice);
			
		}
		
		//  This is the new action which will create new Cylinder type
		
		public static void createNewCylinder(String gasCylinderType, String gasCylinderUnit, Integer gasCylinderPrice ){
			
			GasCylinder g = new GasCylinder();
			
			
			
			g.gasCylinderUnit = Float.parseFloat(gasCylinderUnit);
			g.gasCylinderPrice = gasCylinderPrice;
			g.gasCylinderType = gasCylinderType;
			System.out.println("GasCylinderObject"+   g);
			System.out.println("GasCylinderType "+ g.gasCylinderType);
			System.out.println("GasCylinderPrice "+ g.gasCylinderPrice);
			//System.out.println("GasCylinderType "+ g.gasCylinderType);
			g.save();
			
			//List All the cylinders of Commercial
		    List<GasCylinder> gasCylinder = GasCylinder.find("byGasCylinderType",1).fetch();
			for(int i = 0; i<=gasCylinder.size(); i++)
			System.out.println("GasCylinders of type Commercial"+gasCylinder);
			
			
			
			System.out.println("GasCylinder Type Of Commercial"+GasCylinderType.COMMERCIAL);
			
			
			// If they process is successful , let him go to admin  profile page
			AdminControllerMap.index();
			
		}
		
		
}


