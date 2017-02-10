/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.controllers;

import ar.com.billing.workstation.model.Color;
import ar.com.billing.workstation.model.Fabric;
import ar.com.billing.workstation.model.Stripe;
import ar.com.billing.workstation.model.StripeCombination;
import ar.com.billing.workstation.printer.PrinterController;
import ar.com.billing.workstation.services.CommunicationService;
import ar.com.billing.workstation.services.CommunicationServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author Mildo
 */
public class EmbalajeController {
    CommunicationService communicationService = new CommunicationServiceImpl();
    PrinterController printer = PrinterController.getInstance();
    

    public  boolean addProduct(Fabric fabric, Color color,Stripe stripe,StripeCombination stripeCombination, Double weight, int part) throws Exception {
        
        String barcode = communicationService.incrementProductStock(fabric, color,stripe, stripeCombination, weight, part);
        if(barcode==null){
            return false;
        }
        String fabricLabelName= fabric.getCode() + " " + fabric.getShortname();
        String colorName = color.getCode() + " " + color.getName();
        String stripeName = "";
        
        if(stripe!=null){
            stripeName = stripe.getCode() + " " + stripe.getName();
        }
        String weigthStr = String.format("%2.2f",weight);
        String partida = String.format("%3d",part);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String date = format.format(new Date());
        printer.print(fabricLabelName, colorName, stripeName,weigthStr,partida,date,barcode);
        return true;
    }
    
   
}
