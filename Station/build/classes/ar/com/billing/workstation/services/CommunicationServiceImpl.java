/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.services;

import ar.com.billing.workstation.model.Color;
import ar.com.billing.workstation.model.Fabric;
import ar.com.billing.workstation.model.Order;
import ar.com.billing.workstation.model.OrderItem;
import ar.com.billing.workstation.model.Stripe;
import ar.com.billing.workstation.model.StripeCombination;
import static ar.com.billing.workstation.services.CommunicationService.FIND_ALL_COLORS_URL;
import static ar.com.billing.workstation.services.CommunicationService.FIND_ALL_STRIPES_URL;
import static ar.com.billing.workstation.services.CommunicationService.MARK_COMPLETE_ORDER_URL;
import static ar.com.billing.workstation.services.CommunicationService.REGISTER_PRODUCT_ORDER_URL;
import static ar.com.billing.workstation.services.CommunicationService.REMOVE_PRODUCT_ORDER_URL;
import ar.com.billing.workstation.utilities.Configurator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Mildo
 */
public class CommunicationServiceImpl implements CommunicationService{
    
    private String baseUrl="";
            
    
    public CommunicationServiceImpl(){
        try {
            baseUrl = (String) Configurator.getInstance().findProperty(Configurator.APP_URL);
        } catch (Exception ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Error al traer la url base"); 
                    
            baseUrl = "http://localhost:8080/Adriabe/data";
        }
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, baseUrl); 
            
    }
    
    @Override
    public JSONArray findAllFabrics() throws Exception{
        try {
            JSONObject obj = NetworkService.doGet(baseUrl+FIND_ALL_FABRICS_URL, null);
            return obj.getJSONArray("array");
        } catch (Exception ex) {
            Logger.getLogger(CommunicationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error de comunicación con el servidor");
        }
    }

    @Override
    public JSONArray findAllColors() throws Exception{
        try {
            JSONObject obj = NetworkService.doGet(baseUrl+FIND_ALL_COLORS_URL, null);
            return obj.getJSONArray("array");
        } catch (Exception ex) {
            Logger.getLogger(CommunicationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error de comunicación con el servidor");
        }
    }

    @Override
    public JSONArray findAllStripes() throws Exception{
        try {
            JSONObject obj = NetworkService.doGet(baseUrl+FIND_ALL_STRIPES_URL, null);
            return obj.getJSONArray("array");
        } catch (Exception ex) {
            Logger.getLogger(CommunicationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error de comunicación con el servidor");
        }
    }

    @Override
    public String incrementProductStock(Fabric fabric, Color color, Stripe stripe, StripeCombination stripeCombination, Double weight, int part) throws Exception{
        try {
            JSONObject params = new JSONObject();
            params.put("fabricId", String.format("%d", fabric.getId()));
            params.put("colorId", String.format("%d", color.getId()));
            
            if(stripe!=null){
                params.put("stripeId", String.format("%d", stripe.getId()));
            }
            if(stripeCombination!=null){
                params.put("stripeCombinationId", String.format("%d", stripeCombination.getId()));
            }
            params.put("weight", String.format("%2.2f", weight));
            params.put("partida",String.format("%d", part));
            JSONObject obj = NetworkService.doPost(baseUrl+INCREMENT_PRODUCT_URL, params);
            if(obj==null){
                return null;
            }
            return obj.getString("barcode");
        } catch (Exception ex) {
            Logger.getLogger(CommunicationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error de comunicación con el servidor");
        }
    }

    @Override
    public JSONArray findAllPendingOrders() throws Exception{
        try {
            JSONObject obj = NetworkService.doGet(baseUrl+FIND_PENDING_ORDERS_URL, null);
            return obj.getJSONArray("array");
        } catch (Exception ex) {
            Logger.getLogger(CommunicationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception("Error de comunicación con el servidor");
        }
    }

    @Override
    public boolean registerProductInOrder(OrderItem item, String barcode) throws Exception{
        JSONObject params = new JSONObject();
        params.put("order-item-id", String.valueOf(item.getId()));
        params.put("barcode", barcode);
        
        JSONObject obj = NetworkService.doPost(baseUrl+REGISTER_PRODUCT_ORDER_URL, params);
        
        return obj.getBoolean("result");
        
    }

    @Override
    public boolean markOrderComplete(long id) throws Exception{
        JSONObject params = new JSONObject();
        params.put("order-id", String.valueOf(id));
        
        JSONObject obj = NetworkService.doPost(baseUrl+MARK_COMPLETE_ORDER_URL, params);
        
        return obj.getBoolean("result");
        
    }

    /**
     *
     * @param aOrder
     * @param prodId
     * @param weight
     * @param barcode
     * @return
     * @throws Exception
     */
    @Override
    public boolean removeProductFromOrder(OrderItem orderItem, String barcode)throws Exception{
         JSONObject params = new JSONObject();
        params.put("id-order-item", String.valueOf(orderItem.getId()));
        params.put("barcode", barcode);
        
        JSONObject obj = NetworkService.doPost(baseUrl+REMOVE_PRODUCT_ORDER_URL, params);
        
        return obj.getBoolean("result");
    }
}
