package org.jda.example.orderman.modules.ship;

import org.jda.example.orderman.modules.ship.model.Shipment;
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
  name="Shipment",
  modelDesc=@ModelDesc(
      model=Shipment.class
  ),
  viewDesc=@ViewDesc(
      formTitle="Shipment",
      imageIcon="shipment.gif",
      viewType=RegionType.Data,
      view=View.class
  ),
  isPrimary=true
)
public class ModuleShip {
  @AttributeDesc(label="Shipment")
  private String title;
  
  @AttributeDesc(label="Id")
  private int id;

  @AttributeDesc(label="Status", type=JComboField.class)
  private StatusCode status;  
}
