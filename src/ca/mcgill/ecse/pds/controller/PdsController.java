package ca.mcgill.ecse.pds.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ca.mcgill.ecse.pds.application.PdsApplication;
import ca.mcgill.ecse.pds.model.Customer;
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
			MenuPizza newMenuPizza = new MenuPizza(price, pds, name, calorieCount, pds.getMenu());
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
	//ALL customer related methods
	
	public static void createCustomer(String name,String phoneNumber,String emailAddress, String deliveryAddress) throws InvalidInputException {
		String error = "";
		PDS pds = PdsApplication.getPDS();
		if (name == null || deliveryAddress==null || name == "" || deliveryAddress=="" ){
			error = error + "Customer Fields cannot be left blank \n";	
		}
		try {
			pds.addCustomer(name,phoneNumber, emailAddress, deliveryAddress);
			PdsApplication.save();
			}
			catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
		}
	
	public static void deleteCustomer(Customer customer) throws InvalidInputException {
		customer.delete();
		try {
			PdsApplication.save();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	public static void updateMenuPizza(MenuPizza menupizza,float newPrice,float newCalorieCount,Ingredient[] ingredients) throws InvalidInputException {
		
			menupizza.setPrice(newPrice);
			menupizza.setCalorieCount(newCalorieCount);
			for(Ingredient i:menupizza.getIngredients()) {
				menupizza.removeIngredient(i);
			}
			for(Ingredient i:ingredients) {
				menupizza.addIngredient(i);
			}
			try {
				PdsApplication.save();
			}
			catch (RuntimeException e) {
				throw new InvalidInputException(e.getMessage());
			}
	}		
	
	public static List<Customer> getCustomers() {
		return PdsApplication.getPDS().getCustomers();
	}
	
	public static void updateCustomer(String name,String phoneNumber,String emailAddress, String deliveryAddress) throws InvalidInputException {
		String error = "";
		PDS pds = PdsApplication.getPDS();
		Customer foundCustomer = null;
		for (Customer customer : pds.getCustomers()) {
			if (customer.getName() == name) {
				foundCustomer = customer;
				break;
			}
		}
		if(foundCustomer == null) {
			error = error + "Could not find Customer:" + name + "\n";	
		}
		if(phoneNumber == ""  && emailAddress == "" || phoneNumber == null  && emailAddress == null ) {
			error = error + "Either an email address or Phone number is required as contact information";
		}
		
		if (error.length() > 0) {
			throw new InvalidInputException(error.trim());
		}
		foundCustomer.setName(name);
		foundCustomer.setPhoneNumber(phoneNumber);
		foundCustomer.setEmailAddress(emailAddress);
		foundCustomer.setDeliveryAddress(deliveryAddress);
		
		try {
			PdsApplication.save();
		}
		catch (RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
}
