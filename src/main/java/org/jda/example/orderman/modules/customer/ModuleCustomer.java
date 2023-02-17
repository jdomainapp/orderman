package org.jda.example.orderman.modules.customer;

import java.util.List;

import org.jda.example.orderman.modules.customer.model.Customer;
import org.jda.example.orderman.modules.order.model.Order;

import jda.modules.mccl.syntax.ModuleDescriptor;
import jda.modules.mccl.syntax.controller.ControllerDesc;
import jda.modules.mccl.syntax.controller.ControllerDesc.OpenPolicy;
import jda.modules.mccl.syntax.model.ModelDesc;
import jda.modules.mccl.syntax.view.AttributeDesc;
import jda.modules.mccl.syntax.view.ViewDesc;
import jda.mosa.view.assets.datafields.JSimpleFormattedField;
import jda.mosa.view.assets.panels.DefaultPanel;

@ModuleDescriptor(
name="Customer",
modelDesc=@ModelDesc(
    model=Customer.class
),
viewDesc=@ViewDesc(
    formTitle="Customer",
    imageIcon="customer.gif"
),
isViewer=false,
isPrimary=true
)
public class ModuleCustomer {
  
  @AttributeDesc(label="Customer")
  private String title;
  
  @AttributeDesc(label="Id")
  private int customerID;

  @AttributeDesc(label="Name")
  private String customerName;
  
  @AttributeDesc(label="Customer address")
  private String customerAddress;
  
  @AttributeDesc(label="Balance",editable=false,type=JSimpleFormattedField.class)
  private double balance;
  
  @AttributeDesc(label="Orders",type=DefaultPanel.class,
      controllerDesc=@ControllerDesc(openPolicy=OpenPolicy.O_C)  // v2.6.4b
  )
  private List<Order> orders;  
}
