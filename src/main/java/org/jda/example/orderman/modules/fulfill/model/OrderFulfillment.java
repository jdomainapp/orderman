package org.jda.example.orderman.modules.fulfill.model;

import java.util.Objects;

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
public class OrderFulfillment {
  @DAttr(name="id",type=Type.Integer,id=true,auto=true,mutable=false,optional=false,min=1)
  private int id;
  
  @DAttr(name = "status", type = Type.Domain
      // not supported for Domain-typed attribute: auto=true
      , mutable=false
      )
  private StatusCode status;

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

  /**
   * @effects 
   * 
   * @version 
   */
  @Override
  public String toString() {
    return "OrderFulfillment (" + id + ", " + status + ")";
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
    OrderFulfillment other = (OrderFulfillment) obj;
    return id == other.id;
  }
  
  
}
