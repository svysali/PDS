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

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MenuPizza(float aPrice, PDS aPDS, String aName, float aCalorieCount)
  {
    super(aPrice, aPDS);
    name = aName;
    calorieCount = aCalorieCount;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setCalorieCount(float aCalorieCount)
  {
    boolean wasSet = false;
    calorieCount = aCalorieCount;
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

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "calorieCount" + ":" + getCalorieCount()+ "]";
  }
}