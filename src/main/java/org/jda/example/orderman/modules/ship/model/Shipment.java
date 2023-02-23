package org.jda.example.orderman.modules.ship.model;

import java.util.Objects;

import org.jda.example.orderman.modules.customer.model.Customer;
import org.jda.example.orderman.modules.order.model.CustOrder;
import org.jda.example.orderman.util.model.StatusCode;

import jda.modules.dcsl.syntax.DAttr;
import jda.modules.dcsl.syntax.DAttr.Type;

/**
 * @overview 
 *
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */
public class Shipment {
  @DAttr(name="id",type=Type.Integer,id=true,auto=true,mutable=false,optional=false,min=1)
  private int id;
  private static int idCounter;
  
  @DAttr(name="order", type=Type.Domain, mutable=false, auto=true)
  private CustOrder order;

  // derived from order
  @DAttr(name="customer", type=Type.Domain, mutable=false, auto=true)
  private Customer customer;
  
  @DAttr(name = "status", type = Type.Domain
      // not supported for Domain-typed attribute: auto=true
      , mutable=false
      )
  private StatusCode status;

  //  virtual link
  @DAttr(name="shipOrder",type=Type.Domain,serialisable=false,virtual=true)
  private ShipOrder shipOrder;
  
  // data source
  public Shipment(Integer id, CustOrder order, Customer customer, StatusCode status) {
    this.id = nextID(id);
    this.order = order;
    this.customer = customer; 
    this.status = status;
  }
  
  // object form
  public Shipment(CustOrder order, Customer customer, StatusCode status) {
    this(null, order, customer, status);
  }
  
  /**
   * @effects return status
   */
  public StatusCode getStatus() {
    return status;
  }

  /**
   * @effects set status = status
   */
  public void setStatus(StatusCode status) {
    this.status = status;
  }

  /**
   * @effects return id
   */
  public int getId() {
    return id;
  }

  
  private static int nextID(Integer currID) {
    if (currID == null) { // generate one
      idCounter++;
      return idCounter;
    } else { // update
      int num;
      num = currID.intValue();
      
      if (num > idCounter) {
        idCounter=num;
      }   
      return currID;
    }
  }
  
  /**
   * @effects return receivedOrder
   */
  public CustOrder getOrder() {
    return order;
  }
  
  /**
   * @effects set this.order = order
   */
  public boolean setOrder(CustOrder order) {
    this.order = order;
    this.customer = order.getCustomer();
    
    return true;
  }
  
  /**
   * @effects 
   * 
   */
  public boolean isCompleted() {
    return status.equals(StatusCode.Done);
  }
  
  /**
   * @effects 
   * 
   * @version 
   */
  @Override
  public String toString() {
    return "Shipment (" + id + ", " + status + ")";
  }

  /**
   * @effects 
   * 
   * @version 
   */
  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  /**
   * @effects 
   * 
   * @version 
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Shipment other = (Shipment) obj;
    return id == other.id;
  }
  
}
