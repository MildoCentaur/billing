///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
package ar.com.billing.workstation.controllers;

import ar.com.billing.workstation.WorkstationConstants;
import ar.com.billing.workstation.model.Order;
import ar.com.billing.workstation.model.OrderItem;
import ar.com.billing.workstation.model.OrderItemDetail;
import ar.com.billing.workstation.model.constant.ErrorConstants;
import ar.com.billing.workstation.services.CommunicationService;
import ar.com.billing.workstation.services.CommunicationServiceImpl;
import ar.com.billing.workstation.views.OrderWindow;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Mildo
 */
public class PrepararPedidoController {
    private final CommunicationService communicationService = new CommunicationServiceImpl();
    
    public ErrorConstants registerProductInOrder(Order aOrder, String barcode){
        ErrorConstants result=ErrorConstants.PRODUCT_NOT_ASKED;
        
        boolean register =false;
        
        try {
            Double weight = new Double(barcode.substring(9, 13));
            weight = weight /100;
            for(OrderItem oi : aOrder.getItems()){
                if(oi.getProduct().compareWithBarcode(barcode)==0){
                    if(oi.getQuantity() > oi.getPackages().size()){
                        register = communicationService.registerProductInOrder(oi,barcode);
                        if(register){
                            oi.addOrderItemDetail(weight, barcode);
                            result = ErrorConstants.NO_ERROR;
                        }
                    }else {
                        result = ErrorConstants.ITEM_COMPLETE;
                    }    
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(PrepararPedidoController.class.getName()).log(Level.SEVERE, "No se pudo generar el pedido");
            result=ErrorConstants.REGISTER_ERROR;
        }
        return result;
    }
    
    public boolean completeOrder(Order aOrder){
        try {
            boolean result = communicationService.markOrderComplete(aOrder.getId());
            if(result){
                aOrder.setStatus("Preparado");
            }
            return result;
        } catch (Exception ex) {
            Logger.getLogger(PrepararPedidoController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public ErrorConstants removeProductFromOrder(Order aOrder,  String barcode){
        ErrorConstants result = ErrorConstants.GENERAL_ERROR;
        boolean removed=false;
        try {
            Double weight = new Double(barcode.substring(9, 13));
            weight = weight /100;
            for(OrderItem oi : aOrder.getItems()){
                if(oi.getProduct().compareWithBarcode(barcode)==0){
                    if(oi.getPackages().size()>0){
                        removed = communicationService.removeProductFromOrder(oi,barcode);
                        if(removed){
                            oi.removeOrderItemDetail(weight, barcode);
                            result = ErrorConstants.NO_ERROR;
                        }
                    }    
                }
            }
                        
            
        } catch (Exception ex) {
            Logger.getLogger(PrepararPedidoController.class.getName()).log(Level.SEVERE, null, ex);
            result=ErrorConstants.GENERAL_ERROR;
        }
        return result;
    }
 
    public void processBarcode(String barcode, Order order){
       
        OrderWindow window = OrderWindow.getInstance();
        window.errorMsg("");
        if(barcode==null || barcode.length()!=WorkstationConstants.BARCODE_LENGTH){
            window.errorMsg(ErrorConstants.BARCODE_ERROR.getLabel());
            return;
        }
        //"%03d%03d%1d%04d%05d", productFamilyId, colorId, index, (int) (weight * 100), days);
        
        ErrorConstants result =registerProductInOrder(order,barcode);
        if(result == ErrorConstants.NO_ERROR){
            window.refresh();
            //Product aProduct = ProductDao.findProductById(prodId);
            //String msg =  aProduct.getName() + " " + aProduct.getColorName();
            window.successMsg("Rollo " + barcode );
        }else{
            window.errorMsg(result.getLabel());
        }
        window.invalidate();
    }

    public void removeBarcode(String barcode, Order order) {
        OrderWindow window = OrderWindow.getInstance();
        window.errorMsg("");
        
        if(barcode==null || barcode.length()>WorkstationConstants.BARCODE_LENGTH){
            window.errorMsg(ErrorConstants.BARCODE_ERROR.getLabel());
            return;
        }
        
        ErrorConstants result =removeProductFromOrder(order,barcode);
        if(result == ErrorConstants.NO_ERROR){
            window.refresh();
            //Product aProduct = ProductDao.findProductById(prodId);
            //String msg = aProduct.getCode() + " " + aProduct.getName() + " " + aProduct.getColorName();
            window.successMsg("Rollo " + barcode );
        }else{
            window.errorMsg(result.getLabel());
        }
        window.invalidate();
    }
}
