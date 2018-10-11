/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;
import java.io.Serializable;
import java.util.*;
import java.sql.Date;

// line 55 "../../../../../PDSPersistence.ump"
// line 70 "../../../../../pds.ump"
public class Customer implements Serializable
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private String name;
  private String phoneNumber;
  private String emailAddress;
  private String deliveryAddress;

  //Autounique Attributes
  private int id;

  //Customer Associations
  private List<Order> orders;
  private PDS pDS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aName, String aPhoneNumber, String aEmailAddress, String aDeliveryAddress, PDS aPDS)
  {
    // line 77 "../../../../../pds.ump"
    if ((aPhoneNumber == null || aPhoneNumber.length() == 0 ) && (aEmailAddress == null || aEmailAddress.length() == 0)) {
    	  throw new RuntimeException("PhoneNumber or Email Address must be provided");
    	}
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    phoneNumber = aPhoneNumber;
    emailAddress = aEmailAddress;
    deliveryAddress = aDeliveryAddress;
    id = nextId++;
    orders = new ArrayList<Order>();
    boolean didAddPDS = setPDS(aPDS);
    if (!didAddPDS)
    {
      throw new RuntimeException("Unable to create customer due to pDS");
    }
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

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setEmailAddress(String aEmailAddress)
  {
    boolean wasSet = false;
    emailAddress = aEmailAddress;
    wasSet = true;
    return wasSet;
  }

  public boolean setDeliveryAddress(String aDeliveryAddress)
  {
    boolean wasSet = false;
    deliveryAddress = aDeliveryAddress;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmailAddress()
  {
    return emailAddress;
  }

  public String getDeliveryAddress()
  {
    return deliveryAddress;
  }

  public int getId()
  {
    return id;
  }
  /* Code from template association_GetMany */
  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }
  /* Code from template association_GetOne */
  public PDS getPDS()
  {
    return pDS;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder(Date aDate, PDS aPDS)
  {
    return new Order(aDate, aPDS, this);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    Customer existingCustomer = aOrder.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aOrder.setCustomer(this);
    }
    else
    {
      orders.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a customer
    if (!this.equals(aOrder.getCustomer()))
    {
      orders.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
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
      existingPDS.removeCustomer(this);
    }
    pDS.addCustomer(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=orders.size(); i > 0; i--)
    {
      Order aOrder = orders.get(i - 1);
      aOrder.delete();
    }
    PDS placeholderPDS = pDS;
    this.pDS = null;
    if(placeholderPDS != null)
    {
      placeholderPDS.removeCustomer(this);
    }
  }

  // line 61 "../../../../../PDSPersistence.ump"
   public static  void reinitializeAutouniqueID(List<Customer> customers){
    nextId = 0; 
    for (Customer customer : customers) {
      if (customer.getId() > nextId) {
        nextId = customer.getId();
      }
    }
    nextId++;
  }


  public String toString()
  {
    return super.toString() + "["+
            "id" + ":" + getId()+ "," +
            "name" + ":" + getName()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "emailAddress" + ":" + getEmailAddress()+ "," +
            "deliveryAddress" + ":" + getDeliveryAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "pDS = "+(getPDS()!=null?Integer.toHexString(System.identityHashCode(getPDS())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 58 "../../../../../PDSPersistence.ump"
  private static final long serialVersionUID = 2045406856025012133L ;

  
}