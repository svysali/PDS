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
	
	public static void updateIngredient(String name, float price) throws InvalidInputException {
		String error = "";
		PDS pds = PdsApplication.getPDS();
		Ingredient foundIngredient = null;
		for (Ingredient ingredient : pds.getIngredients()) {
			if (ingredient.getName() == name) {
				foundIngredient = ingredient;
				break;
			}
		}
		if(foundIngredient == null) {
			error = error + "Could not find Ingredient:" + name + "\n";	
		}
		if(price <= 0) {
			error = error + "Price must be greater than zero. ";
		}
		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		foundIngredient.setPrice(price);
		try {
			PdsApplication.save();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	
	public static void deleteIngredient(Ingredient ingredient) throws InvalidInputException {
		ingredient.delete();
		try {
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
	public static void createMenuPizza(String name,float calorieCount,float price,Ingredient... ingredients) throws InvalidInputException {
		PDS pds = PdsApplication.getPDS();
		try {
			MenuPizza newMenuPizza = new MenuPizza(price, pds, name, calorieCount, pds.getMenu(),ingredients);
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
	
	public static List<MenuPizza> getMenuPizzas() {
		return PdsApplication.getPDS().getMenu().getMenupizzas();
	}
	
	public static void deleteMenuPizza(MenuPizza menupizza) throws InvalidInputException {
		menupizza.delete();
		try {
			PdsApplication.save();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
}
