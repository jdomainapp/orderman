package org.jda.example.orderman.modules.handleorder.control.model;

import org.jda.example.orderman.modules.order.model.CustOrder;

import jda.modules.common.exceptions.NotPossibleException;
import jda.modules.dcsl.syntax.DClass;
import jda.modules.mbsl.model.graph.Node;
import jda.modules.mbsl.model.util.Merge;

/**
 * @overview 
 *
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */
@DClass(serialisable=false)
public class EndOrder implements Merge {

  /**
   * @effects 
   * 
   * @version 
   */
  @Override
  public Object[] evaluate(Node mergeNode, Node srcNode, Object[] args)
      throws NotPossibleException {
    
    // display the input Order (taken from args) on ModuleCustOrder. 
    // the order's status is set to either Rejected or Completed, depending on the incoming edge (processing path) 
    
    CustOrder input = (CustOrder) args[0];
    
    if (srcNode.getLabel().endsWith("AcceptOrNot")) {
      // rejected
      System.out.printf("Order rejected: %s", input);
    } else {
      // completed
      System.out.printf("Order completed: %s", input);      
    }
    
    // no out edge is found: should not happen
//    throw new NotPossibleException(DomainMessage.ERR_NO_SUITABLE_OUT_EDGE, new Object[] {inNode});
    
    return null;
  }
  
//  @DAttr(name = "id", id = true, auto = true, type = Type.Integer, length = 5, 
//      optional = false, mutable = false)
//  private int id;
//  private static int idCounter = 0;
//
//  // complete order
//  @DAttr(name="completeOrders", type=Type.Collection,filter=@Select(clazz=CompleteOrder.class),serialisable=false)
//  @DAssoc(ascName="complete-order",role="proc",
//    ascType=AssocType.One2Many,endType=AssocEndType.One,
//    associate=@Associate(
//        type=CompleteOrder.class,cardMin=0,cardMax=DCSLConstants.CARD_MORE,
//        updateLink=false
//     ))
//  private Collection<CompleteOrder> completeOrders;
//  
//  // accept-or-not
//  @DAttr(name="acceptOrNots", type=Type.Collection,filter=@Select(clazz=AcceptOrNot.class),serialisable=false)
//  @DAssoc(ascName="accept-or-not",role="proc",
//    ascType=AssocType.One2Many,endType=AssocEndType.One,
//    associate=@Associate(
//        type=AcceptOrNot.class,cardMin=0,cardMax=DCSLConstants.CARD_MORE,
//        updateLink=false
//     ))
//  private Collection<AcceptOrNot> acceptOrNots;
//  
//  
//  // virtual link to HandleOrder
//  @DAttr(name="handleOrder",type=Type.Domain,serialisable=false)
//  private HandleOrder handleOrder;
//  
//  public EndOrder(Integer id) {
//    this.id = nextID(id);
//  }
//
//  // for use by object form
//  public EndOrder() {
//    this(null);
//  }
//
//  public int getId() {
//    return id;
//  }
//
//  private static int nextID(Integer currID) {
//    if (currID == null) { // generate one
//      idCounter++;
//      return idCounter;
//    } else { // update
//      int num;
//      num = currID.intValue();
//      
//      if (num > idCounter) {
//        idCounter=num;
//      }   
//      return currID;
//    }
//  }
}
