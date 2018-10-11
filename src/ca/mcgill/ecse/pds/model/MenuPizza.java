/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;
import java.io.Serializable;
import java.util.*;

// line 77 "../../../../../PDSPersistence.ump"
// line 36 "../../../../../pds.ump"
public class MenuPizza extends Pizza implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MenuPizza Attributes
  private String name;
  private float calorieCount;

  //MenuPizza Associations
  private Menu menu;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MenuPizza(float aPrice, PDS aPDS, String aName, float aCalorieCount, Menu aMenu, Ingredient... allIngredients)
  {
    super(aPrice, aPDS, allIngredients);
    // line 40 "../../../../../pds.ump"
    if (aName == null || aName.length() == 0) {
    	  throw new RuntimeException("The name for a pizza on the Menu cannot be empty.");
    	}
    // END OF UMPLE BEFORE INJECTION
    // line 46 "../../../../../pds.ump"
    if (aCalorieCount <= 0.0f ) {
    	  throw new RuntimeException("The calorie count for a pizza on the Menu cannot be less than zero.");
    	}
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    calorieCount = aCalorieCount;
    boolean didAddMenu = setMenu(aMenu);
    if (!didAddMenu)
    {
      throw new RuntimeException("Unable to create menupizza due to menu");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 40 "../../../../../pds.ump"
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
    // line 46 "../../../../../pds.ump"
    if (aCalorieCount <= 0.0f ) {
    	  throw new RuntimeException("The calorie count for a pizza on the Menu cannot be less than zero.");
    	}
    // END OF UMPLE BEFORE INJECTION
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
  /* Code from template association_GetOne */
  public Menu getMenu()
  {
    return menu;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMenu(Menu aMenu)
  {
    boolean wasSet = false;
    if (aMenu == null)
    {
      return wasSet;
    }

    Menu existingMenu = menu;
    menu = aMenu;
    if (existingMenu != null && !existingMenu.equals(aMenu))
    {
      existingMenu.removeMenupizza(this);
    }
    menu.addMenupizza(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Menu placeholderMenu = menu;
    this.menu = null;
    if(placeholderMenu != null)
    {
      placeholderMenu.removeMenupizza(this);
    }
    super.delete();
  }

  // line 52 "../../../../../pds.ump"
  public float getPrice(){
    return super.getPrice();
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "calorieCount" + ":" + getCalorieCount()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "menu = "+(getMenu()!=null?Integer.toHexString(System.identityHashCode(getMenu())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 80 "../../../../../PDSPersistence.ump"
  private static final long serialVersionUID = -7403802774454467836L ;

  
}