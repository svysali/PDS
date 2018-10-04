/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.29.0.4181.a593105a9 modeling language!*/

package ca.mcgill.ecse.pds.model;

// line 48 "../../../../../pds.ump"
public class Ingredient
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Ingredient Attributes
  private String name;
  private float price;

  //Ingredient Associations
  private PDS pDS;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Ingredient(String aName, float aPrice, PDS aPDS)
  {
    // line 51 "../../../../../pds.ump"
    if (aName == null || aName.length() == 0) {
    	  throw new RuntimeException("The name of an ingredient cannot be empty.");
    	}
    // END OF UMPLE BEFORE INJECTION
    // line 57 "../../../../../pds.ump"
    if (aPrice <= 0.0f) {
    	  throw new RuntimeException("The price cannot be less than zero");
    	}
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    price = aPrice;
    boolean didAddPDS = setPDS(aPDS);
    if (!didAddPDS)
    {
      throw new RuntimeException("Unable to create ingredient due to pDS");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    // line 51 "../../../../../pds.ump"
    if (aName == null || aName.length() == 0) {
    	  throw new RuntimeException("The name of an ingredient cannot be empty.");
    	}
    // END OF UMPLE BEFORE INJECTION
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(float aPrice)
  {
    boolean wasSet = false;
    // line 57 "../../../../../pds.ump"
    if (aPrice <= 0.0f) {
    	  throw new RuntimeException("The price cannot be less than zero");
    	}
    // END OF UMPLE BEFORE INJECTION
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public float getPrice()
  {
    return price;
  }
  /* Code from template association_GetOne */
  public PDS getPDS()
  {
    return pDS;
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
      existingPDS.removeIngredient(this);
    }
    pDS.addIngredient(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    PDS placeholderPDS = pDS;
    this.pDS = null;
    if(placeholderPDS != null)
    {
      placeholderPDS.removeIngredient(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "pDS = "+(getPDS()!=null?Integer.toHexString(System.identityHashCode(getPDS())):"null");
  }
}