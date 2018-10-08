package ca.mcgill.ecse.pds.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ca.mcgill.ecse.pds.application.PdsApplication;
import ca.mcgill.ecse.pds.model.Ingredient;
import ca.mcgill.ecse.pds.model.MenuPizza;
import ca.mcgill.ecse.pds.model.PDS;
import ca.mcgill.ecse.pds.model.Pizza;

public class PdsController {

	public PdsController() {
	}
		
	private static Date cleanDate(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTimeInMillis(date.getTime());
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    java.util.Date tempCleanedDate = cal.getTime();
	    java.sql.Date cleanedDate = new java.sql.Date(tempCleanedDate.getTime());
	    return cleanedDate;
	}
	
	//All ingredient related methods
	public static void createIngredient(String name, float price) throws InvalidInputException {
		PDS pds = PdsApplication.getPDS();
		try {
			pds.addIngredient(name,price);
			PdsApplication.save();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static List<Ingredient> getIngredients() {
		return PdsApplication.getPDS().getIngredients();
	}
	
	//All menu pizza related methods
	public static void createMenuPizza(String name,float calorieCount,float price,ArrayList<Ingredient> ingredients) throws InvalidInputException {
		PDS pds = PdsApplication.getPDS();
		try {
			Pizza newMenuPizza = new MenuPizza(pds,name,calorieCount,price);
			for (Ingredient aIngredient : ingredients) {
				newMenuPizza.addIngredient(aIngredient);
			}
			pds.addPizza(newMenuPizza);
			PdsApplication.save();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
}
