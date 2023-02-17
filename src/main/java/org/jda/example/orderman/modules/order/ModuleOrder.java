package org.jda.example.orderman.modules.order;

import java.util.Date;
import java.util.List;

import org.jda.example.orderman.modules.customer.model.Customer;
import org.jda.example.orderman.modules.order.model.Order;
import org.jda.example.orderman.modules.order.model.OrderLine;

import jda.modules.mccl.conceptmodel.view.RegionType;
import jda.modules.mccl.syntax.ModuleDescriptor;
import jda.modules.mccl.syntax.controller.ControllerDesc;
import jda.modules.mccl.syntax.controller.ControllerDesc.OpenPolicy;
import jda.modules.mccl.syntax.model.ModelDesc;
import jda.modules.mccl.syntax.view.AttributeDesc;
import jda.modules.mccl.syntax.view.ViewDesc;
import jda.mosa.view.View;
import jda.mosa.view.assets.datafields.JSimpleFormattedField;
import jda.mosa.view.assets.tables.JObjectTable;

@ModuleDescriptor(
name="Order",
modelDesc=@ModelDesc(
    model=Order.class
),
viewDesc=@ViewDesc(
    formTitle="Đơn hàng",
    imageIcon="order.gif",
    viewType=RegionType.Data,
    view=View.class
),
isPrimary=true)
public class ModuleOrder {
  
  @AttributeDesc(label="Order")
  private String title;
  
  @AttributeDesc(label="Order ID")
  private int orderID;

  @AttributeDesc(label="Order date",type=JSimpleFormattedField.class)
  private Date orderDate;

  @AttributeDesc(label="Customer")
  private Customer customer;

  @AttributeDesc(label="Order lines",type=JObjectTable.class,
      controllerDesc=@ControllerDesc(openPolicy=OpenPolicy.O)  // v2.6.4b
  )
  private List<OrderLine> lines;

  @AttributeDesc(label="Total",type=JSimpleFormattedField.class)
  private double orderTotal;
}
