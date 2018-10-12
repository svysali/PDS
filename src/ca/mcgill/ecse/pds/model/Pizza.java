/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;
import java.io.Serializable;
import java.util.*;

/**
 * Pizza abstract
 */
// line 71 "../../../../../PDSPersistence.ump"
// line 20 "../../../../../pds.ump"
public class Pizza implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Pizza Attributes
  private int size;
  private float price;

  //Pizza Associations
  private List<Ingredient> ingredients;
  private PDS pDS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Pizza(float aPrice, PDS aPDS, Ingredient... allIngredients)
  {
    // line 25 "../../../../../pds.ump"
    if (aPrice <= 0.0f ) {
    	  throw new RuntimeException("The price for a pizza cannot be less than zero.");
    	}
    // END OF UMPLE BEFORE INJECTION
    size = 12;
    price = aPrice;
    ingredients = new ArrayList<Ingredient>();
    boolean didAddIngredients = setIngredients(allIngredients);
    if (!didAddIngredients)
    {
      throw new RuntimeException("Unable to create Pizza, must have at least 1 ingredients");
    }
    boolean didAddPDS = setPDS(aPDS);
    if (!didAddPDS)
    {
      throw new RuntimeException("Unable to create pizza due to pDS");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSize(int aSize)
  {
    boolean wasSet = false;
    size = aSize;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(float aPrice)
  {
    boolean wasSet = false;
    // line 25 "../../../../../pds.ump"
    if (aPrice <= 0.0f ) {
    	  throw new RuntimeException("The price for a pizza cannot be less than zero.");
    	}
    // END OF UMPLE BEFORE INJECTION
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public int getSize()
  {
    return size;
  }

  public float getPrice()
  {
    return price;
  }
  /* Code from template association_GetMany */
  public Ingredient getIngredient(int index)
  {
    Ingredient aIngredient = ingredients.get(index);
    return aIngredient;
  }

  public List<Ingredient> getIngredients()
  {
    List<Ingredient> newIngredients = Collections.unmodifiableList(ingredients);
    return newIngredients;
  }

  public int numberOfIngredients()
  {
    int number = ingredients.size();
    return number;
  }

  public boolean hasIngredients()
  {
    boolean has = ingredients.size() > 0;
    return has;
  }

  public int indexOfIngredient(Ingredient aIngredient)
  {
    int index = ingredients.indexOf(aIngredient);
    return index;
  }
  /* Code from template association_GetOne */
  public PDS getPDS()
  {
    return pDS;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfIngredients()
  {
    return 1;
  }
  /* Code from template association_AddUnidirectionalMStar */
  public boolean addIngredient(Ingredient aIngredient)
  {
    boolean wasAdded = false;
    if (ingredients.contains(aIngredient)) { return false; }
    ingredients.add(aIngredient);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeIngredient(Ingredient aIngredient)
  {
    boolean wasRemoved = false;
    if (!ingredients.contains(aIngredient))
    {
      return wasRemoved;
    }

    if (numberOfIngredients() <= minimumNumberOfIngredients())
    {
      return wasRemoved;
    }

    ingredients.remove(aIngredient);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_SetUnidirectionalMStar */
  public boolean setIngredients(Ingredient... newIngredients)
  {
    boolean wasSet = false;
    ArrayList<Ingredient> verifiedIngredients = new ArrayList<Ingredient>();
    for (Ingredient aIngredient : newIngredients)
    {
      if (verifiedIngredients.contains(aIngredient))
      {
        continue;
      }
      verifiedIngredients.add(aIngredient);
    }

    if (verifiedIngredients.size() != newIngredients.length || verifiedIngredients.size() < minimumNumberOfIngredients())
    {
      return wasSet;
    }

    ingredients.clear();
    ingredients.addAll(verifiedIngredients);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addIngredientAt(Ingredient aIngredient, int index)
  {  
    boolean wasAdded = false;
    if(addIngredient(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveIngredientAt(Ingredient aIngredient, int index)
  {
    boolean wasAdded = false;
    if(ingredients.contains(aIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIngredients()) { index = numberOfIngredients() - 1; }
      ingredients.remove(aIngredient);
      ingredients.add(index, aIngredient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addIngredientAt(aIngredient, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setPDS(PDS aPDS)
  {
    boolean wasSet = false;
    if (aPDS == null)
    {
      return wasSet;
    }

    PDS existingPDS = pDS;
    pDS = aPDS;
    if (existingPDS != null && !existingPDS.equals(aPDS))
    {
      existingPDS.removePizza(this);
    }
    pDS.addPizza(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ingredients.clear();
    PDS placeholderPDS = pDS;
    this.pDS = null;
    if(placeholderPDS != null)
    {
      placeholderPDS.removePizza(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "size" + ":" + getSize()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "pDS = "+(getPDS()!=null?Integer.toHexString(System.identityHashCode(getPDS())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 74 "../../../../../PDSPersistence.ump"
  private static final long serialVersionUID = 1529112079821173014L ;

  
}