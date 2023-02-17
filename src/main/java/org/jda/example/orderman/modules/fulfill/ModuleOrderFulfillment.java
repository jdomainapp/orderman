package org.jda.example.orderman.modules.fulfill;

import org.jda.example.orderman.modules.fulfill.model.OrderFulfillment;
import org.jda.example.orderman.util.model.StatusCode;

import jda.modules.mccl.conceptmodel.view.RegionType;
import jda.modules.mccl.syntax.ModuleDescriptor;
import jda.modules.mccl.syntax.model.ModelDesc;
import jda.modules.mccl.syntax.view.AttributeDesc;
import jda.modules.mccl.syntax.view.ViewDesc;
import jda.mosa.view.View;
import jda.mosa.view.assets.datafields.list.JComboField;

/**
 * @overview 
 *
 * @author Duc Minh Le (ducmle)
 *
 * @version 
 */

@ModuleDescriptor(
    name="OrderFulfillment",
  modelDesc=@ModelDesc(
      model=OrderFulfillment.class
  ),
  viewDesc=@ViewDesc(
      formTitle="OrderFulfillment",
      imageIcon="fulfillment.gif",
      viewType=RegionType.Data,
      view=View.class
  ),
  isPrimary=true
)
public class ModuleOrderFulfillment {
  @AttributeDesc(label="Order Fulfillment")
  private String title;
  
  @AttributeDesc(label="Id")
  private int id;

  @AttributeDesc(label="Status", type=JComboField.class)
  private StatusCode status;  
}
