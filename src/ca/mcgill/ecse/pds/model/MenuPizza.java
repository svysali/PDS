/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;
import java.util.*;

// line 21 "../../../../../pds.ump"
public class MenuPizza extends Pizza
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MenuPizza Attributes
  private String name;
  private float calorieCount;
  private float price;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MenuPizza(PDS aPDS, String aName, float aCalorieCount, float aPrice)
  {
    super(aPDS);
    // line 25 "../../../../../pds.ump"
    if (aName == null || aName.length() == 0) {
    	  throw new RuntimeException("The name for a pizza on the Menu cannot be empty.");
    	}
    // END OF UMPLE BEFORE INJECTION
    // line 31 "../../../../../pds.ump"
    if (aCalorieCount <= 0.0f ) {
    	  throw new RuntimeException("The calorie count for a pizza on the Menu cannot be less than zero.");
    	}
    // END OF UMPLE BEFORE INJECTION
    // line 37 "../../../../../pds.ump"
    if (aPrice <= 0.0f ) {
    	  throw new RuntimeException("The price for a pizza on the Menu cannot be less than zero.");
    	}
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    calorieCount = aCalorieCount;
    price = aPrice;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 25 "../../../../../pds.ump"
    if (aName == null || aName.length() == 0) {
    	  throw new RuntimeException("The name for a pizza on the Menu cannot be empty.");
    	}
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setCalorieCount(float aCalorieCount)
  {
    boolean wasSet = false;
    // line 31 "../../../../../pds.ump"
    if (aCalorieCount <= 0.0f ) {
    	  throw new RuntimeException("The calorie count for a pizza on the Menu cannot be less than zero.");
    	}
    // END OF UMPLE BEFORE INJECTION
    calorieCount = aCalorieCount;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(float aPrice)
  {
    boolean wasSet = false;
    // line 37 "../../../../../pds.ump"
    if (aPrice <= 0.0f ) {
    	  throw new RuntimeException("The price for a pizza on the Menu cannot be less than zero.");
    	}
    // END OF UMPLE BEFORE INJECTION
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public float getCalorieCount()
  {
    return calorieCount;
  }

  public float getPrice()
  {
    return price;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "calorieCount" + ":" + getCalorieCount()+ "," +
            "price" + ":" + getPrice()+ "]";
  }
}