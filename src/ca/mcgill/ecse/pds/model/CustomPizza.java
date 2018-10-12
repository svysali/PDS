/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;
import java.io.Serializable;
import java.util.*;

// line 83 "../../../../../PDSPersistence.ump"
// line 53 "../../../../../pds.ump"
public class CustomPizza extends Pizza implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CustomPizza Associations
  private Menu menu;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CustomPizza(float aPrice, PDS aPDS, Menu aMenu)
  {
    super(aPrice, aPDS);
    boolean didAddMenu = setMenu(aMenu);
    if (!didAddMenu)
    {
      throw new RuntimeException("Unable to create custompizza due to menu");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Menu getMenu()
  {
    return menu;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setMenu(Menu aNewMenu)
  {
    boolean wasSet = false;
    if (aNewMenu == null)
    {
      //Unable to setMenu to null, as custompizza must always be associated to a menu
      return wasSet;
    }
    
    CustomPizza existingCustompizza = aNewMenu.getCustompizza();
    if (existingCustompizza != null && !equals(existingCustompizza))
    {
      //Unable to setMenu, the current menu already has a custompizza, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Menu anOldMenu = menu;
    menu = aNewMenu;
    menu.setCustompizza(this);

    if (anOldMenu != null)
    {
      anOldMenu.setCustompizza(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Menu existingMenu = menu;
    menu = null;
    if (existingMenu != null)
    {
      existingMenu.setCustompizza(null);
    }
    super.delete();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 86 "../../../../../PDSPersistence.ump"
  private static final long serialVersionUID = 3258026461020809115L ;

  
}