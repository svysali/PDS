/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;
import java.io.Serializable;
import java.util.*;

// line 44 "../../../../../PDSPersistence.ump"
// line 89 "../../../../../pds.ump"
public class Customization implements Serializable
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum Type { Add, Remove, Replace }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customization Attributes
  private Type type;

  //Customization Associations
  private List<Ingredient> ingredients;
  private OrderItem orderItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customization(Type aType, OrderItem aOrderItem)
  {
    type = aType;
    ingredients = new ArrayList<Ingredient>();
    boolean didAddOrderItem = setOrderItem(aOrderItem);
    if (!didAddOrderItem)
    {
      throw new RuntimeException("Unable to create customization due to orderItem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(Type aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public Type getType()
  {
    return type;
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
  public OrderItem getOrderItem()
  {
    return orderItem;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfIngredients()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
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
    if (ingredients.contains(aIngredient))
    {
      ingredients.remove(aIngredient);
      wasRemoved = true;
    }
    return wasRemoved;
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
  public boolean setOrderItem(OrderItem aOrderItem)
  {
    boolean wasSet = false;
    if (aOrderItem == null)
    {
      return wasSet;
    }

    OrderItem existingOrderItem = orderItem;
    orderItem = aOrderItem;
    if (existingOrderItem != null && !existingOrderItem.equals(aOrderItem))
    {
      existingOrderItem.removeCustomization(this);
    }
    orderItem.addCustomization(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ingredients.clear();
    OrderItem placeholderOrderItem = orderItem;
    this.orderItem = null;
    if(placeholderOrderItem != null)
    {
      placeholderOrderItem.removeCustomization(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "type" + "=" + (getType() != null ? !getType().equals(this)  ? getType().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderItem = "+(getOrderItem()!=null?Integer.toHexString(System.identityHashCode(getOrderItem())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 47 "../../../../../PDSPersistence.ump"
  private static final long serialVersionUID = -3900912597282882073L ;

  
}