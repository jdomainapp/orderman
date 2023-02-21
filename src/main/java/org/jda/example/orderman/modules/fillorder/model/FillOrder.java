package org.jda.example.orderman.modules.fillorder.model;

import java.util.Collection;
import java.util.Objects;

import org.jda.example.orderman.modules.delivery.model.Delivery;
import org.jda.example.orderman.modules.handleorder.model.HandleOrder;
import org.jda.example.orderman.modules.order.model.CustOrder;

import jda.modules.dcsl.syntax.DAssoc;
import jda.modules.dcsl.syntax.DAssoc.AssocEndType;
import jda.modules.dcsl.syntax.DAssoc.AssocType;
import jda.modules.dcsl.syntax.DAssoc.Associate;
import jda.modules.dcsl.syntax.DAttr;
import jda.modules.dcsl.syntax.DAttr.Type;
import jda.modules.dcsl.syntax.DCSLConstants;
import jda.modules.dcsl.syntax.DClass;
import jda.modules.dcsl.syntax.Select;

/**
 * @overview 
 *
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */
@DClass(serialisable=false)
public class FillOrder {
  @DAttr(name = "id", id = true, auto = true, type = Type.Integer, length = 5, 
      optional = false, mutable = false)
  private int id;
  private static int idCounter = 0;
  
  @DAttr(name="receivedOrder", type=Type.Domain, mutable=false,serialisable=false)
  private CustOrder receivedOrder;
  
  // order 
  @DAttr(name="orders", type=Type.Collection,filter=@Select(clazz=CustOrder.class
//      ,attributes = {"orderID", "orderDate", "status", "fillOrder"}
  )
  ,serialisable=false)
  @DAssoc(ascName="fill-order",role="mgmt",
    ascType=AssocType.One2Many,endType=AssocEndType.One,
    associate=@Associate(
        type=CustOrder.class,cardMin=0,cardMax=DCSLConstants.CARD_MORE,
        updateLink=false
     ))
  private Collection<CustOrder> orders;

  // delivery 
  @DAttr(name="deliveries", type=Type.Collection,filter=@Select(clazz=Delivery.class),serialisable=false)
  @DAssoc(ascName="delivery",role="mgmt",
    ascType=AssocType.One2Many,endType=AssocEndType.One,
    associate=@Associate(
        type=Delivery.class,cardMin=0,cardMax=DCSLConstants.CARD_MORE,
        updateLink=false
     ))
  private Collection<Delivery> deliveries;
  
  // virtual link to HandleOrder
  @DAttr(name="handleOrder",type=Type.Domain,serialisable=false,virtual=true)
  private HandleOrder handleOrder;
  
  
  public FillOrder(Integer id, CustOrder receivedOrder) {
    this.id = nextID(id);
    this.receivedOrder = receivedOrder;
  }

  public FillOrder(CustOrder receivedOrder) {
    this(null,receivedOrder);
  }
  
  /**
   * @effects return id
   */
  public int getId() {
    return id;
  }

  
  /**
   * @effects return receivedOrder
   */
  public CustOrder getReceivedOrder() {
    return receivedOrder;
  }
  
  /**
   * @effects set receivedOrder = receivedOrder
   */
  public void setReceivedOrder(CustOrder receivedOrder) {
    this.receivedOrder = receivedOrder;
  }
  
  
  // proxy methods for CustOrder (needed to pass details along to the outgoing CustOrder node
//  public int getOrderID() {
//    return receivedOrder != null ? receivedOrder.getOrderID() : null;
//  }
//
//  public Date getOrderDate() {
//    return receivedOrder != null ? receivedOrder.getOrderDate() : null;
//  }
//
//  public OrderStatus getStatus() {
//    return receivedOrder != null ? receivedOrder.getStatus() : null;
//  }

  /**
   * @effects 
   * 
   * @version 
   */
  @Override
  public String toString() {
    return "FillOrder (" + id + ")";
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
    FillOrder other = (FillOrder) obj;
    return id == other.id;
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
  
  
}
