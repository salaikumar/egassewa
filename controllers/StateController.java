package controllers;

import java.util.List;

import models.Country;
import models.State;
import play.mvc.Controller;

public class StateController extends Controller{
	
	// Create a state and save it
	public static void createState(String statename,Long stateId ){
		
		
		Country c = Country.find("byCountryname", "INDIA").first();
		//Checks if the state already exists
		
		
		State state = State.find("byStatenameAndCountryId",statename,c).first();
		
		if(state != null){
			render("not created");
			
		}
		else {
			
			new State(statename,stateId,c).save();
			
			AdminControllerMap.index();
		}
	}
	
	//Retrieve state using the state name 
	public static void retriveStateUsingStatename(String statename){
		State state = State.find("byStatename", statename).first();
		
		render(state);
	}
	
	//Retrieve using State id;
	public static void retriveStateUsingId(Long stateId){
		
		State state = State.find("byStateId",stateId).first();
		
		render(state);
	}
public static void retriveStates(){
		
		List<State> state = State.find("orderby StateId").fetch();
		
		// AdminControllerMap.statedelete(state);
	}
public static void deleteState(State s){
	// State s = State.findById(id);
	s.delete();
	AdminControllerMap.statedelete();
}
	
	//Update state details
	

	public static void updateState(Long stateId,String statename,String countryname,Long newStateId,String newStatename,String newCountryname){

		Country c= Country.find("byCountryname", countryname).first();
		
		Country c1=Country.find("byCountryname", newCountryname).first();
		
		State statePinCheck = State.find("byStateId", newStateId).first();
		
		//First i'm trying to retrieve the state using all these
		State state = State.find("byStatenameAndStateIdAndCountryId", statename,stateId,c).first();
		
		if(statePinCheck == null){
			
		state.countryId = c1;
		state.stateId = newStateId;
		state.statename = newStatename;
		
		state.save();
		render(state);
		}
	}
	
	public static void deleteState(String statename , Long stateid){
		
		// Get the corressponding object
		State s  = State.find("byStatenameAndStateId", statename,stateid).first();
		
		// Delete the state
		s.delete();
		
	}

}
