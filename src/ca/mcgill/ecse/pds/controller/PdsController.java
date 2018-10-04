package ca.mcgill.ecse.pds.controller;

import java.sql.Date;
import java.util.Calendar;

import ca.mcgill.ecse.pds.application.PdsApplication;
import ca.mcgill.ecse.pds.model.PDS;

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

}
