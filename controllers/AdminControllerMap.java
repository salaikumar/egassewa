package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class AdminControllerMap extends Controller {
	public static void index(){
		render();
	}
	public static void statedelete()  {
		List<State> state = State.find("order by StateId").fetch();
		render(state);
	}
	
	public static void newadmincreation(){
		render();
	}
	
	public static void country(){
		render();
	}
	public static void state(){
		render();
	}
	public static void city(){
		render();
	}
	public static void gascylinder(){
		render();
	}
	public static void updatecylinder(){
		render();
	}
	public static void stateaction(){
		render();
	}
	public static void cityaction(){
		render();
	}
	public static void dealer(){
		render();
	}
	public static void customer(){
		render();
	}
	public static void gascylinderaction()  {
		render();
	}
	public static void accessory()  {
		render();
	}
	public static void accessoryactions()  {
		render();
	}
	
}
