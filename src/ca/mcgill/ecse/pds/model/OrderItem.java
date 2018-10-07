/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;
import java.io.Serializable;
import java.util.*;

// line 38 "../../../../../PDSPersistence.ump"
// line 83 "../../../../../pds.ump"
public class OrderItem implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OrderItem Attributes
  private float price;

  //OrderItem Associations
  private Pizza pizza;
  private List<Customization> customizations;
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OrderItem(float aPrice, Pizza aPizza, Order aOrder)
  {
    price = aPrice;
    if (!setPizza(aPizza))
    {
      throw new RuntimeException("Unable to create OrderItem due to aPizza");
    }
    customizations = new ArrayList<Customization>();
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create orderItem due to order");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPrice(float aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public float getPrice()
  {
    return price;
  }
  /* Code from template association_GetOne */
  public Pizza getPizza()
  {
    return pizza;
  }
  /* Code from template association_GetMany */
  public Customization getCustomization(int index)
  {
    Customization aCustomization = customizations.get(index);
    return aCustomization;
  }

  public List<Customization> getCustomizations()
  {
    List<Customization> newCustomizations = Collections.unmodifiableList(customizations);
    return newCustomizations;
  }

  public int numberOfCustomizations()
  {
    int number = customizations.size();
    return number;
  }

  public boolean hasCustomizations()
  {
    boolean has = customizations.size() > 0;
    return has;
  }

  public int indexOfCustomization(Customization aCustomization)
  {
    int index = customizations.indexOf(aCustomization);
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
  public static int minimumNumberOfCustomizations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Customization addCustomization(Customization.Type aType)
  {
    return new Customization(aType, this);
  }

  public boolean addCustomization(Customization aCustomization)
  {
    boolean wasAdded = false;
    if (customizations.contains(aCustomization)) { return false; }
    OrderItem existingOrderItem = aCustomization.getOrderItem();
    boolean isNewOrderItem = existingOrderItem != null && !this.equals(existingOrderItem);
    if (isNewOrderItem)
    {
      aCustomization.setOrderItem(this);
    }
    else
    {
      customizations.add(aCustomization);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCustomization(Customization aCustomization)
  {
    boolean wasRemoved = false;
    //Unable to remove aCustomization, as it must always have a orderItem
    if (!this.equals(aCustomization.getOrderItem()))
    {
      customizations.remove(aCustomization);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCustomizationAt(Customization aCustomization, int index)
  {  
    boolean wasAdded = false;
    if(addCustomization(aCustomization))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomizations()) { index = numberOfCustomizations() - 1; }
      customizations.remove(aCustomization);
      customizations.add(index, aCustomization);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCustomizationAt(Customization aCustomization, int index)
  {
    boolean wasAdded = false;
    if(customizations.contains(aCustomization))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomizations()) { index = numberOfCustomizations() - 1; }
      customizations.remove(aCustomization);
      customizations.add(index, aCustomization);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCustomizationAt(aCustomization, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    if (aOrder == null)
    {
      return wasSet;
    }

    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      existingOrder.removeOrderItem(this);
    }
    order.addOrderItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    pizza = null;
    while (customizations.size() > 0)
    {
      Customization aCustomization = customizations.get(customizations.size() - 1);
      aCustomization.delete();
      customizations.remove(aCustomization);
    }
    
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removeOrderItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "pizza = "+(getPizza()!=null?Integer.toHexString(System.identityHashCode(getPizza())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 41 "../../../../../PDSPersistence.ump"
  private static final long serialVersionUID = 386717977557499839L ;

  
}