/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;

// line 27 "../../../../../pds.ump"
public class CustomPizza extends Pizza
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CustomPizza Attributes
  private float basePrice;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CustomPizza(float aPrice, PDS aPDS, float aBasePrice)
  {
    super(aPrice, aPDS);
    basePrice = aBasePrice;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBasePrice(float aBasePrice)
  {
    boolean wasSet = false;
    basePrice = aBasePrice;
    wasSet = true;
    return wasSet;
  }

  public float getBasePrice()
  {
    return basePrice;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "basePrice" + ":" + getBasePrice()+ "]";
  }
}