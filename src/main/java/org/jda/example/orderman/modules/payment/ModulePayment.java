package org.jda.example.orderman.modules.payment;

import org.jda.example.orderman.modules.payment.model.CustomerPaymentProcess;
import org.jda.example.orderman.modules.payment.model.Payment;

import jda.modules.mccl.conceptmodel.view.RegionType;
import jda.modules.mccl.syntax.MCCLConstants.AlignmentX;
import jda.modules.mccl.syntax.ModuleDescriptor;
import jda.modules.mccl.syntax.SetUpDesc;
import jda.modules.mccl.syntax.controller.ControllerDesc;
import jda.modules.mccl.syntax.model.ModelDesc;
import jda.modules.mccl.syntax.view.AttributeDesc;
import jda.modules.mccl.syntax.view.ViewDesc;
import jda.modules.setup.commands.CopyResourceFilesCommand;
import jda.mosa.controller.Controller;
import jda.mosa.view.View;
import jda.mosa.view.assets.datafields.JTextField;

/**
 * @Overview
 *  Module for {@link Payment}
 * 
 * @author dmle
 */
@ModuleDescriptor(name="ModulePayment",
modelDesc=@ModelDesc(
    model=Payment.class
),
viewDesc=@ViewDesc(
    formTitle="Payment",
    domainClassLabel="Payment"
    //,imageIcon="-"
    ,imageIcon="payment.jpg",
    viewType=RegionType.Data,
    view=View.class
),
controllerDesc=@ControllerDesc(
    controller=Controller.class,
    isDataFieldStateListener=true
),
isPrimary=true
//,childModules={ModuleStudent.class}
,setUpDesc=@SetUpDesc(postSetUp=CopyResourceFilesCommand.class)
)
public class ModulePayment  {

  @AttributeDesc(label="Payment")
  private String title;
  
  // attributes
  @AttributeDesc(label="Id", alignX=AlignmentX.Center)
  private int id;
  
  @AttributeDesc(label="Payment details", alignX=AlignmentX.Center)
  private String payDetails;
  
  @AttributeDesc(label="Description")
  private String description;
  
  @AttributeDesc(label="Status", alignX=AlignmentX.Center)
  private String statusStr;
//  @AttributeDesc(label="Tình trạng"
//      ,type=JComboField.class
//      )
//  private PaymentStatus status;
  
  // not shown (only used to set input value from the join activity)
  @AttributeDesc(label="~"
      ,type=JTextField.class, editable=false
      ,isVisible=false)
  private CustomerPaymentProcess paymentProc;
}
