/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;
import java.io.Serializable;
import java.sql.Date;
import java.util.*;

// line 26 "../../../../../PDSPersistence.ump"
// line 85 "../../../../../pds.ump"
public class Order implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextNumber = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private Date date;
  private int quantity;
  private boolean isDelivered;

  //Autounique Attributes
  private int number;

  //Order Associations
  private List<OrderItem> orderItem;
  private PDS pDS;
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(Date aDate, PDS aPDS, Customer aCustomer)
  {
    date = aDate;
    quantity = 1;
    isDelivered = false;
    number = nextNumber++;
    orderItem = new ArrayList<OrderItem>();
    boolean didAddPDS = setPDS(aPDS);
    if (!didAddPDS)
    {
      throw new RuntimeException("Unable to create order due to pDS");
    }
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create order due to customer");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setQuantity(int aQuantity)
  {
    boolean wasSet = false;
    quantity = aQuantity;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsDelivered(boolean aIsDelivered)
  {
    boolean wasSet = false;
    isDelivered = aIsDelivered;
    wasSet = true;
    return wasSet;
  }

  public Date getDate()
  {
    return date;
  }

  public int getQuantity()
  {
    return quantity;
  }

  public boolean getIsDelivered()
  {
    return isDelivered;
  }

  public int getNumber()
  {
    return number;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsDelivered()
  {
    return isDelivered;
  }
  /* Code from template association_GetMany */
  public OrderItem getOrderItem(int index)
  {
    OrderItem aOrderItem = orderItem.get(index);
    return aOrderItem;
  }

  public List<OrderItem> getOrderItem()
  {
    List<OrderItem> newOrderItem = Collections.unmodifiableList(orderItem);
    return newOrderItem;
  }

  public int numberOfOrderItem()
  {
    int number = orderItem.size();
    return number;
  }

  public boolean hasOrderItem()
  {
    boolean has = orderItem.size() > 0;
    return has;
  }

  public int indexOfOrderItem(OrderItem aOrderItem)
  {
    int index = orderItem.indexOf(aOrderItem);
    return index;
  }
  /* Code from template association_GetOne */
  public PDS getPDS()
  {
    return pDS;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrderItem()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderItem addOrderItem(Pizza aPizza)
  {
    return new OrderItem(aPizza, this);
  }

  public boolean addOrderItem(OrderItem aOrderItem)
  {
    boolean wasAdded = false;
    if (orderItem.contains(aOrderItem)) { return false; }
    Order existingOrder = aOrderItem.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aOrderItem.setOrder(this);
    }
    else
    {
      orderItem.add(aOrderItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrderItem(OrderItem aOrderItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrderItem, as it must always have a order
    if (!this.equals(aOrderItem.getOrder()))
    {
      orderItem.remove(aOrderItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderItemAt(OrderItem aOrderItem, int index)
  {  
    boolean wasAdded = false;
    if(addOrderItem(aOrderItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderItem()) { index = numberOfOrderItem() - 1; }
      orderItem.remove(aOrderItem);
      orderItem.add(index, aOrderItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderItemAt(OrderItem aOrderItem, int index)
  {
    boolean wasAdded = false;
    if(orderItem.contains(aOrderItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderItem()) { index = numberOfOrderItem() - 1; }
      orderItem.remove(aOrderItem);
      orderItem.add(index, aOrderItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderItemAt(aOrderItem, index);
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
      existingPDS.removeOrder(this);
    }
    pDS.addOrder(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeOrder(this);
    }
    customer.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (orderItem.size() > 0)
    {
      OrderItem aOrderItem = orderItem.get(orderItem.size() - 1);
      aOrderItem.delete();
      orderItem.remove(aOrderItem);
    }
    
    PDS placeholderPDS = pDS;
    this.pDS = null;
    if(placeholderPDS != null)
    {
      placeholderPDS.removeOrder(this);
    }
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeOrder(this);
    }
  }

  // line 32 "../../../../../PDSPersistence.ump"
   public static  void reinitializeAutouniqueID(List<Order> orders){
    nextNumber = 0; 
    for (Order order : orders) {
      if (order.getNumber() > nextNumber) {
        nextNumber = order.getNumber();
      }
    }
    nextNumber++;
  }

  // line 92 "../../../../../pds.ump"
   public float calculateTotal(){
    return 0.0f;
  }


  public String toString()
  {
    return super.toString() + "["+
            "number" + ":" + getNumber()+ "," +
            "quantity" + ":" + getQuantity()+ "," +
            "isDelivered" + ":" + getIsDelivered()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "pDS = "+(getPDS()!=null?Integer.toHexString(System.identityHashCode(getPDS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 29 "../../../../../PDSPersistence.ump"
  private static final long serialVersionUID = 8896099581655989380L ;

  
}