package org.jda.example.orderman.software;

import org.jda.example.orderman.software.config.SCCOrderMan;

import jda.modules.jdatool.setup.DomainAppToolSetUpGen;
import jda.modules.setup.model.Cmd;
import jda.mosa.software.aio.SoftwareAio;
import jda.mosa.software.aio.SoftwareToolAio;

/**
 * @overview 
 *  Tool-based OrderMan software, i.e. software that is executed by DomainAppTool.
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
    final Class SystemCls = SCCOrderMan.class;
    final Class SetUpCls = DomainAppToolSetUpGen.class;
    
    SoftwareAio tool = new SoftwareToolAio(SetUpCls, SystemCls);
    
    try {
      tool.exec(args);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}
