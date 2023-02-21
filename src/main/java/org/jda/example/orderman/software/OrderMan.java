package org.jda.example.orderman.software;

import org.jda.example.orderman.software.config.SCCOrderMan;

import jda.modules.common.Toolkit;
import jda.modules.mbsl.model.graph.ActivityGraph;
import jda.modules.setup.model.Cmd;
import jda.mosa.software.SoftwareFactory;
import jda.mosa.software.aio.SoftwareAio;

/**
 * @overview 
 *  OrderMan software.
 *  
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */
public class OrderMan {
  
  /**
   * @requires 
   *  args.length > 0 /\ args[0] = name of a {@link Cmd}.
   */
  public static void main(String[] args) {
    final Class SwCfgCls = SCCOrderMan.class;
    SoftwareAio sw = SoftwareFactory.createSoftwareAioWithMemoryBasedConfig(SwCfgCls);
    
    // debug
    Toolkit.addDebug(ActivityGraph.class.getSimpleName());
    
    try {
      sw.exec(args);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
    
    // old code
//    final Class SystemCls = SCCOrderMan.class;
//    final Class SetUpCls = DomainAppToolSetUpGen.class;
//    
//    SoftwareAio tool = new SoftwareToolAio(SetUpCls, SystemCls);
//    
//    try {
//      tool.exec(args);
//    } catch (Exception e) {
//      e.printStackTrace();
//      System.exit(1);
//    }
  }
}
