/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;
import java.io.Serializable;
import java.util.*;

// line 43 "../../../../../PDSPersistence.ump"
// line 99 "../../../../../pds.ump"
public class OrderItem implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OrderItem Associations
  private Pizza pizza;
  private List<Ingredient> additionalIngredients;
  private List<Ingredient> removedIngredients;
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OrderItem(Pizza aPizza, Order aOrder)
  {
    if (!setPizza(aPizza))
    {
      throw new RuntimeException("Unable to create OrderItem due to aPizza");
    }
    additionalIngredients = new ArrayList<Ingredient>();
    removedIngredients = new ArrayList<Ingredient>();
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create orderItem due to order");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetOne */
  public Pizza getPizza()
  {
    return pizza;
  }
  /* Code from template association_GetMany */
  public Ingredient getAdditionalIngredient(int index)
  {
    Ingredient aAdditionalIngredient = additionalIngredients.get(index);
    return aAdditionalIngredient;
  }

  public List<Ingredient> getAdditionalIngredients()
  {
    List<Ingredient> newAdditionalIngredients = Collections.unmodifiableList(additionalIngredients);
    return newAdditionalIngredients;
  }

  public int numberOfAdditionalIngredients()
  {
    int number = additionalIngredients.size();
    return number;
  }

  public boolean hasAdditionalIngredients()
  {
    boolean has = additionalIngredients.size() > 0;
    return has;
  }

  public int indexOfAdditionalIngredient(Ingredient aAdditionalIngredient)
  {
    int index = additionalIngredients.indexOf(aAdditionalIngredient);
    return index;
  }
  /* Code from template association_GetMany */
  public Ingredient getRemovedIngredient(int index)
  {
    Ingredient aRemovedIngredient = removedIngredients.get(index);
    return aRemovedIngredient;
  }

  public List<Ingredient> getRemovedIngredients()
  {
    List<Ingredient> newRemovedIngredients = Collections.unmodifiableList(removedIngredients);
    return newRemovedIngredients;
  }

  public int numberOfRemovedIngredients()
  {
    int number = removedIngredients.size();
    return number;
  }

  public boolean hasRemovedIngredients()
  {
    boolean has = removedIngredients.size() > 0;
    return has;
  }

  public int indexOfRemovedIngredient(Ingredient aRemovedIngredient)
  {
    int index = removedIngredients.indexOf(aRemovedIngredient);
    return index;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setPizza(Pizza aNewPizza)
  {
    boolean wasSet = false;
    if (aNewPizza != null)
    {
      pizza = aNewPizza;
      wasSet = true;
    }
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAdditionalIngredients()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addAdditionalIngredient(Ingredient aAdditionalIngredient)
  {
    boolean wasAdded = false;
    if (additionalIngredients.contains(aAdditionalIngredient)) { return false; }
    additionalIngredients.add(aAdditionalIngredient);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAdditionalIngredient(Ingredient aAdditionalIngredient)
  {
    boolean wasRemoved = false;
    if (additionalIngredients.contains(aAdditionalIngredient))
    {
      additionalIngredients.remove(aAdditionalIngredient);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAdditionalIngredientAt(Ingredient aAdditionalIngredient, int index)
  {  
    boolean wasAdded = false;
    if(addAdditionalIngredient(aAdditionalIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAdditionalIngredients()) { index = numberOfAdditionalIngredients() - 1; }
      additionalIngredients.remove(aAdditionalIngredient);
      additionalIngredients.add(index, aAdditionalIngredient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAdditionalIngredientAt(Ingredient aAdditionalIngredient, int index)
  {
    boolean wasAdded = false;
    if(additionalIngredients.contains(aAdditionalIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAdditionalIngredients()) { index = numberOfAdditionalIngredients() - 1; }
      additionalIngredients.remove(aAdditionalIngredient);
      additionalIngredients.add(index, aAdditionalIngredient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAdditionalIngredientAt(aAdditionalIngredient, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfRemovedIngredients()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addRemovedIngredient(Ingredient aRemovedIngredient)
  {
    boolean wasAdded = false;
    if (removedIngredients.contains(aRemovedIngredient)) { return false; }
    removedIngredients.add(aRemovedIngredient);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeRemovedIngredient(Ingredient aRemovedIngredient)
  {
    boolean wasRemoved = false;
    if (removedIngredients.contains(aRemovedIngredient))
    {
      removedIngredients.remove(aRemovedIngredient);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addRemovedIngredientAt(Ingredient aRemovedIngredient, int index)
  {  
    boolean wasAdded = false;
    if(addRemovedIngredient(aRemovedIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRemovedIngredients()) { index = numberOfRemovedIngredients() - 1; }
      removedIngredients.remove(aRemovedIngredient);
      removedIngredients.add(index, aRemovedIngredient);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveRemovedIngredientAt(Ingredient aRemovedIngredient, int index)
  {
    boolean wasAdded = false;
    if(removedIngredients.contains(aRemovedIngredient))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfRemovedIngredients()) { index = numberOfRemovedIngredients() - 1; }
      removedIngredients.remove(aRemovedIngredient);
      removedIngredients.add(index, aRemovedIngredient);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addRemovedIngredientAt(aRemovedIngredient, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    //Must provide order to orderItem
    if (aOrder == null)
    {
      return wasSet;
    }

    if (order != null && order.numberOfOrderItems() <= Order.minimumNumberOfOrderItems())
    {
      return wasSet;
    }

    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      boolean didRemove = existingOrder.removeOrderItem(this);
      if (!didRemove)
      {
        order = existingOrder;
        return wasSet;
      }
    }
    order.addOrderItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    pizza = null;
    additionalIngredients.clear();
    removedIngredients.clear();
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removeOrderItem(this);
    }
  }

  // line 104 "../../../../../pds.ump"
   public float getCost(){
    float cost = 0;
  	float additionalIngredientsCost = 0;
  	float removedIngredientsReductionValue = 0;
  	for(Ingredient i: this.getAdditionalIngredients()){
  		removedIngredientsReductionValue += i.getPrice(); 
  	}
  	for(Ingredient i: this.getRemovedIngredients()){
  		removedIngredientsReductionValue += i.getPrice(); 
  	}
  	cost = pizza.getPrice() + removedIngredientsReductionValue + (0.5f * removedIngredientsReductionValue);
  	return cost;
  }
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 46 "../../../../../PDSPersistence.ump"
  private static final long serialVersionUID = 386717977557499839L ;

  
}