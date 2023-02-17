package org.jda.example.orderman.modules.order;

import org.jda.example.orderman.modules.order.model.Order;
import org.jda.example.orderman.modules.order.model.OrderLine;
import org.jda.example.orderman.modules.product.model.Product;

import jda.modules.dcsl.syntax.Select;
import jda.modules.mccl.conceptmodel.view.RegionType;
import jda.modules.mccl.syntax.ModuleDescriptor;
import jda.modules.mccl.syntax.model.ModelDesc;
import jda.modules.mccl.syntax.view.AttributeDesc;
import jda.modules.mccl.syntax.view.ViewDesc;
import jda.mosa.view.View;
import jda.mosa.view.assets.datafields.JSimpleFormattedField;

@ModuleDescriptor(
name="OrderLine",
modelDesc=@ModelDesc(
    model=OrderLine.class
),
viewDesc=@ViewDesc(
    formTitle="Order Line",
    imageIcon="orderline.gif",
    viewType=RegionType.Data,
    view=View.class
),
isPrimary=true)
public class ModuleOrderLine {
  @AttributeDesc(label="Order Line")
  private String title;
  
  @AttributeDesc(label="Id")
  private int id;
  
  @AttributeDesc(label="Order")
  private Order order;
  
  @AttributeDesc(label="Product",
      ref=@Select(clazz=Product.class,attributes={"productDescr"}))
  private Product product;

  @AttributeDesc(label="Unit price",editable=false,type=JSimpleFormattedField.class)
  private double unitPrice;

  @AttributeDesc(label="Quantity",type=JSimpleFormattedField.class)
  private int quantity;

  @AttributeDesc(label="Tổng dòng",editable=false,type=JSimpleFormattedField.class)
  private int lineTotal;
}
