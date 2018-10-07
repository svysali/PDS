package ca.mcgill.ecse.pds.application;

import ca.mcgill.ecse.pds.model.*;
import ca.mcgill.ecse.pds.persistence.PersistenceObjectStream;

public class PdsApplication {
	private static PDS pds;
	private static String filename = "data.pds";
	private static int counter = 1;
	public static void main(String[] args) {
		
		//TO DO:IMPLEMENT UI
		
		// Step 1
		PDS pds = getPDS();
		printPdsStatistics(pds);
		
		// Step 2
		pds.addIngredient("pepperoni",1.0f);
		save();
		printPdsStatistics(pds);
		
		
	}
	
	public static int increaseCounter() {
		return counter++;
	}
	
	private static void printPdsStatistics(PDS pds) {
		System.out.println("===== PDS: Step " + increaseCounter() +" =====");
		System.out.println("Number of Ingredients: " + pds.getIngredients().size());
		for (Ingredient ingredient : pds.getIngredients()) {
			System.out.println("Ingredient: " + ingredient.getName() + " / Price: " + ingredient.getPrice());
		}
		System.out.println("========================");
	}
	
	public static PDS getPDS() {
		if (pds == null) {
			// load model
			pds = load();
		}
 		return pds;
	}
	
	public static void save() {
		PersistenceObjectStream.serialize(pds);
	}
	
	public static PDS load() {
		PersistenceObjectStream.setFilename(filename);
		pds = (PDS) PersistenceObjectStream.deserialize();
		// model cannot be loaded - create empty PDS
		if (pds == null) {
			pds = new PDS();
		}
		else {
			pds.reinitialize();
		}
		return pds;
	}
	
	public static void setFilename(String newFilename) {
		filename = newFilename;
	}
}
