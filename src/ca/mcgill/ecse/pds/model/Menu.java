/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;
import java.io.Serializable;
import java.util.*;

// line 15 "../../../../../PDSPersistence.ump"
// line 32 "../../../../../pds.ump"
public class Menu implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Menu Associations
  private List<MenuPizza> menupizzas;
  private PDS pDS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Menu(PDS aPDS)
  {
    menupizzas = new ArrayList<MenuPizza>();
    if (aPDS == null || aPDS.getMenu() != null)
    {
      throw new RuntimeException("Unable to create Menu due to aPDS");
    }
    pDS = aPDS;
  }

  public Menu()
  {
    menupizzas = new ArrayList<MenuPizza>();
    pDS = new PDS(this);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public MenuPizza getMenupizza(int index)
  {
    MenuPizza aMenupizza = menupizzas.get(index);
    return aMenupizza;
  }

  public List<MenuPizza> getMenupizzas()
  {
    List<MenuPizza> newMenupizzas = Collections.unmodifiableList(menupizzas);
    return newMenupizzas;
  }

  public int numberOfMenupizzas()
  {
    int number = menupizzas.size();
    return number;
  }

  public boolean hasMenupizzas()
  {
    boolean has = menupizzas.size() > 0;
    return has;
  }

  public int indexOfMenupizza(MenuPizza aMenupizza)
  {
    int index = menupizzas.indexOf(aMenupizza);
    return index;
  }
  /* Code from template association_GetOne */
  public PDS getPDS()
  {
    return pDS;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMenupizzas()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public MenuPizza addMenupizza(float aPrice, PDS aPDS, String aName, float aCalorieCount, Ingredient... allIngredients)
  {
    return new MenuPizza(aPrice, aPDS, aName, aCalorieCount, this, allIngredients);
  }

  public boolean addMenupizza(MenuPizza aMenupizza)
  {
    boolean wasAdded = false;
    if (menupizzas.contains(aMenupizza)) { return false; }
    Menu existingMenu = aMenupizza.getMenu();
    boolean isNewMenu = existingMenu != null && !this.equals(existingMenu);
    if (isNewMenu)
    {
      aMenupizza.setMenu(this);
    }
    else
    {
      menupizzas.add(aMenupizza);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMenupizza(MenuPizza aMenupizza)
  {
    boolean wasRemoved = false;
    //Unable to remove aMenupizza, as it must always have a menu
    if (!this.equals(aMenupizza.getMenu()))
    {
      menupizzas.remove(aMenupizza);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMenupizzaAt(MenuPizza aMenupizza, int index)
  {  
    boolean wasAdded = false;
    if(addMenupizza(aMenupizza))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenupizzas()) { index = numberOfMenupizzas() - 1; }
      menupizzas.remove(aMenupizza);
      menupizzas.add(index, aMenupizza);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMenupizzaAt(MenuPizza aMenupizza, int index)
  {
    boolean wasAdded = false;
    if(menupizzas.contains(aMenupizza))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenupizzas()) { index = numberOfMenupizzas() - 1; }
      menupizzas.remove(aMenupizza);
      menupizzas.add(index, aMenupizza);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMenupizzaAt(aMenupizza, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (menupizzas.size() > 0)
    {
      MenuPizza aMenupizza = menupizzas.get(menupizzas.size() - 1);
      aMenupizza.delete();
      menupizzas.remove(aMenupizza);
    }
    
    PDS existingPDS = pDS;
    pDS = null;
    if (existingPDS != null)
    {
      existingPDS.delete();
    }
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 18 "../../../../../PDSPersistence.ump"
  private static final long serialVersionUID = -2367786829324099582L ;

  
}