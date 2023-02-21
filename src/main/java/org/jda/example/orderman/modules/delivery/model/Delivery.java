package org.jda.example.orderman.modules.delivery.model;

import java.util.Objects;

import org.jda.example.orderman.modules.fillorder.model.FillOrder;
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
public class Delivery {
  @DAttr(name="id",type=Type.Integer,id=true,auto=true,mutable=false,optional=false,min=1)
  private int id;
  
  @DAttr(name = "status", type = Type.Domain
      // not supported for Domain-typed attribute: auto=true
      , mutable=false
      )
  private StatusCode status;

  
  // virtual link to FillOrder
  @DAttr(name="fillOrder",type=Type.Domain,serialisable=false,virtual=true)
  private FillOrder fillOrder;
  
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
    return "Delivery (" + id + ", " + status + ")";
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
    Delivery other = (Delivery) obj;
    return id == other.id;
  }
  
  
}
