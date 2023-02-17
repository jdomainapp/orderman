package org.jda.example.orderman.modules.order.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jda.example.orderman.modules.customer.model.Customer;
import org.jda.example.orderman.util.model.StatusCode;

import jda.modules.common.cache.StateHistory;
import jda.modules.common.exceptions.ConstraintViolationException;
import jda.modules.common.types.Tuple;
import jda.modules.dcsl.syntax.DAssoc;
import jda.modules.dcsl.syntax.DAssoc.AssocEndType;
import jda.modules.dcsl.syntax.DAssoc.AssocType;
import jda.modules.dcsl.syntax.DAssoc.Associate;
import jda.modules.dcsl.syntax.DAttr;
import jda.modules.dcsl.syntax.DAttr.Format;
import jda.modules.dcsl.syntax.DAttr.Type;
import jda.modules.dcsl.syntax.DCSLConstants;
import jda.modules.dcsl.syntax.DOpt;
import jda.modules.dcsl.syntax.Select;

public class Order {
  
  /** publicly visible (but not accessible) attribute names */
  //TODO: change to AttributeName_ constant instead
  public static enum Attribute {
    orderTotal
  }

  public static final String Attribute_orderDate = "orderDate";
  
  @DAttr(name="orderID",type=Type.Integer,auto=true,id=true,mutable=false,optional=false)
  private int orderID;

  @DAttr(name=Attribute_orderDate,type=Type.Date,length=20,optional=false,
      format=Format.Date)
  private Date orderDate;

  @DAttr(name="customer",type=Type.Domain,optional=false)
  @DAssoc(ascName="customer-has-orders",role="customer",
    ascType=AssocType.One2Many,endType=AssocEndType.Many,
    associate=@Associate(type=Customer.class,cardMin=1,cardMax=1),
    dependsOn=true)  
  private Customer customer;

  @DAttr(name="lines",type=Type.Collection,
      filter=@Select(clazz=OrderLine.class),//role="order",
      serialisable=false,optional=false)
  //v2.6.4b: @Update(add="addOrderLine",delete="removeOrderLine",update="updateOrderLine")
  @DAssoc(ascName="order-has-lines",role="order",
    ascType=AssocType.One2Many,endType=AssocEndType.One,
    associate=@Associate(type=OrderLine.class,cardMin=1,cardMax=DCSLConstants.CARD_MORE))
  private List<OrderLine> lines;

  // derived
  private int orderLineCount;
  
  // derived
  @DAttr(name="orderTotal",type=Type.Double,mutable=false,
      derivedFrom={"lines"})
  private double orderTotal;
  
  /* value cache */
  private StateHistory<Attribute,Object> stateHist;
  
  private static int idCounter;
  
  @DAttr(name = "status", type = Type.Domain
      // not supported for Domain-typed attribute: auto=true
      , mutable=false
      )
  private OrderStatus status;
  
  public Order(Integer orderID, Date orderDate, 
      Customer customer,
      List<OrderLine> lines,
      Double orderTotal
      ) {
    this.orderID = nextID(orderID);
    this.orderDate = orderDate;
    this.customer = customer;
    if (lines != null) {
      this.lines =  lines;
      orderLineCount = lines.size();
    } else {
      this.lines = new ArrayList();
      orderLineCount = 0;
    }
    
    if (orderTotal != null) {
      this.orderTotal = orderTotal;
    } else {
      this.orderTotal = 0D;
    }
    
    stateHist = new StateHistory<Attribute,Object>();
  }

  public Order(Integer orderID, Date orderDate, Customer customer, Double orderTotal) {
    this(orderID, orderDate, customer, null, orderTotal);
  }

  public Order(Date orderDate, Customer customer, List<OrderLine> lines, Double orderTotal) {
    this(null, orderDate, customer, lines, orderTotal);
  }
  
  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public List<OrderLine> getLines() {
    return lines;
  }

//  public Integer getLinesCount() {
//    return lines.size();
//  }
  
  @DOpt(type=DOpt.Type.LinkCountGetter)
  public Integer getLinesCount() {
    return orderLineCount;
  }

  @DOpt(type=DOpt.Type.LinkCountSetter)
  public void setLinesCount(int count) {
    orderLineCount = count;
  }
  
  public void setLines(List<OrderLine> lines) {
    this.lines = lines;
    orderLineCount = lines.size();
  }

  @DOpt(type=DOpt.Type.LinkAdder)
  public boolean addOrderLine(OrderLine line) {
    if (!lines.contains(line)) {  // v2.7.3
      lines.add(line);
      
//      orderLineCount++;
//          
//      // cache old value
//      stateHist.put(Attribute.orderTotal, orderTotal);
//      
//      orderTotal = orderTotal + line.getLineTotal();
//      
//      // orderTotal is changed
//      return true;
//    } else {
//      return false;
    }
    return false;
  }

  @DOpt(type=DOpt.Type.LinkAdderNew)
  public boolean addNewOrderLine(OrderLine line) {
    lines.add(line);
    
    orderLineCount++;
        
    // cache old value
    stateHist.put(Attribute.orderTotal, orderTotal);
    
    orderTotal = orderTotal + line.getLineTotal();
    
    // orderTotal is changed
    return true;
  }
  
  @DOpt(type=DOpt.Type.LinkRemover)
  public boolean removeOrderLine(OrderLine line) {
    lines.remove(line);
    
    orderLineCount--;
    
    // cache old value
    stateHist.put(Attribute.orderTotal, orderTotal);

    orderTotal = orderTotal - line.getLineTotal();
    
    // orderTotal is changed
    return true;
  }

  @DOpt(type=DOpt.Type.LinkUpdater)
  public boolean updateOrderLine(OrderLine line) {
    // update orderTotal based on the change in line total
    double oldLineTotal = line.getLineTotal(true);
    
    double diff = line.getLineTotal() - oldLineTotal;
    
    // cache old value
    stateHist.put(Attribute.orderTotal, orderTotal);

    orderTotal += diff;
    
    // orderTotal is changed
    return true;
  }

  
  public int getOrderID() {
    return orderID;
  }
  
//  private void updateOrderTotal() {
//    if (lines != null) {
//      double total = 0;
//      for (OrderLine line : lines) {
//        total += line.getLineTotal();
//      }
//      
//      orderTotal = total;
//    } else {
//      orderTotal = 0;
//    }  
//  }

  /**
   * @effects 
   *  if lines != null
   *    return the total of all order lines
   *  else
   *    return 0
   */
  public double getOrderTotal() {
    return getOrderTotal(false);
  }
  
  public double getOrderTotal(boolean cached) throws IllegalStateException {
    if (cached) {
      Object val = stateHist.get(Attribute.orderTotal);
      
      if (val == null)
        throw new IllegalStateException("Order.getOrderTotal: cached value is null");
      
      return (Double) val;
    } else {
      // clear cache entry (if any)
      //valueCache.remove(Attribute.orderTotal);

      return orderTotal;
    }
  }
  
  
//  public Object deriveOrderTotal(List vals) {
//    updateOrderTotal();
//    
//    return orderTotal;
//  }
  
  /**
   * @effects return status
   */
  public OrderStatus getStatus() {
    return status;
  }

  /**
   * @effects set status = status
   */
  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Order (" + orderID + ", " + customer + ")";
  } 

  private static int nextID(Integer currID) {
    if (currID == null) { // generate one
      idCounter++;
      return idCounter;
    } else { // update
      int num;
      num = currID.intValue();
      
      if (num > idCounter) 
        idCounter=num;
      
      return currID;
    }
  }

  /**
   * @requires 
   *  minVal != null /\ maxVal != null
   * @effects 
   *  update the auto-generated value of attribute <tt>attrib</tt>, specified for <tt>derivingValue</tt>, using <tt>minVal, maxVal</tt>
   */
  @DOpt(type=DOpt.Type.AutoAttributeValueSynchroniser)
  public static void updateAutoGeneratedValue(
      DAttr attrib,
      Tuple derivingValue, 
      Object minVal, 
      Object maxVal) throws ConstraintViolationException {
    
    if (minVal != null && maxVal != null) {
      if (attrib.name().equals("orderID")) {
        int maxIdNum = (Integer) maxVal;
        
        if (maxIdNum > idCounter) // extra check
          idCounter = maxIdNum;
      }
    }
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + orderID;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Order other = (Order) obj;
    if (orderID != other.orderID)
      return false;
    return true;
  }
  
  
}
