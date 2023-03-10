package org.jda.example.orderman.modules.handleorder.control.model;

import java.util.List;

import org.jda.example.orderman.modules.fillorder.model.FillOrder;
import org.jda.example.orderman.modules.order.model.CustOrder;

import jda.modules.common.exceptions.NotPossibleException;
import jda.modules.dcsl.syntax.DClass;
import jda.modules.mbsl.exceptions.DomainMessage;
import jda.modules.mbsl.model.graph.Edge;
import jda.modules.mbsl.model.graph.Node;
import jda.modules.mbsl.model.util.Decision;

/**
 * @overview 
 *
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */
@DClass(serialisable=false)
public class AcceptOrNot implements Decision {

  /* (non-Javadoc)
   * @see domainapp.modules.activity.model.util.Decision#evaluate(java.lang.Object[], java.util.List)
   */
  /**
   * @requires <tt>args</tt> contains a {@link Student} <tt>s</tt>
   * 
   * @effects 
   *  implements the following decision logic specific to the referenced
   *    domain classes:
   *  <pre>
   *    let {@link Student} s = args[0]
   *    if s.helpRequested = true
   *      return Edge e in decisionNode.out s.t. e.n2.refCls = HelpDesk
   *    else
   *      return Edge e in decisionNode.out s.t. e.n2.refCls = SClass
   *  </pre>
   */
  @Override
  public Edge evaluate(Node decisionNode, Object[] args) throws NotPossibleException {
    CustOrder input = (CustOrder) args[0];
    
    Class outCls;
    if (input.isRejected()) {
      outCls = EndOrder.class;
    } else {
      outCls = FillOrder.class;
    }

    List<Edge> outEdges = decisionNode.getOut();
    for (Edge e : outEdges) {
      if (e.getTarget().getRefCls().equals(outCls)) {
        return e;
      }
    }
    
    // no out edge is found: should not happen
    throw new NotPossibleException(DomainMessage.ERR_NO_SUITABLE_OUT_EDGE, new Object[] {decisionNode});
  }

}
