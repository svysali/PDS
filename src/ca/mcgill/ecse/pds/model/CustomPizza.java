/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;
import java.io.Serializable;
import java.util.*;

// line 83 "../../../../../PDSPersistence.ump"
// line 56 "../../../../../pds.ump"
public class CustomPizza extends Pizza implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CustomPizza(float aPrice, PDS aPDS, Ingredient... allIngredients)
  {
    super(aPrice, aPDS, allIngredients);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

  // line 59 "../../../../../pds.ump"
  public float getPrice(){
    return super.getPrice();
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 86 "../../../../../PDSPersistence.ump"
  private static final long serialVersionUID = 3258026461020809115L ;

  
}