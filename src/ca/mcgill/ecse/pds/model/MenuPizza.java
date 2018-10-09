/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;
import java.io.Serializable;
import java.util.*;

// line 72 "../../../../../PDSPersistence.ump"
// line 24 "../../../../../pds.ump"
public class MenuPizza extends Pizza implements Serializable
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

  public MenuPizza(PDS aPDS, String aName, float aCalorieCount, float aPrice, Ingredient... allIngredients)
  {
    super(aPDS, allIngredients);
    // line 28 "../../../../../pds.ump"
    if (aName == null || aName.length() == 0) {
    	  throw new RuntimeException("The name for a pizza on the Menu cannot be empty.");
    	}
    // END OF UMPLE BEFORE INJECTION
    // line 34 "../../../../../pds.ump"
    if (aCalorieCount <= 0.0f ) {
    	  throw new RuntimeException("The calorie count for a pizza on the Menu cannot be less than zero.");
    	}
    // END OF UMPLE BEFORE INJECTION
    // line 40 "../../../../../pds.ump"
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
    // line 28 "../../../../../pds.ump"
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
    // line 34 "../../../../../pds.ump"
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
    // line 40 "../../../../../pds.ump"
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
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 75 "../../../../../PDSPersistence.ump"
  private static final long serialVersionUID = -7403802774454467836L ;

  
}