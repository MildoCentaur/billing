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
import org.json.JSONArray;

/**
 *
 * @author Mildo
 */
public interface CommunicationService {
    
    public final String FIND_ALL_FABRICS_URL = "/data/fabrics.json";
    public final String FIND_ALL_STRIPES_URL = "/data/stripes.json";
    public final String FIND_ALL_COLORS_URL =  "/data/colors.json";
    public final String FIND_PENDING_ORDERS_URL =  "/data/orders/pending.json";
    public final String INCREMENT_PRODUCT_URL = "/data/product/increment.json";
    
    public final String REGISTER_PRODUCT_ORDER_URL = "/data/order/register-product.json";
    public final String MARK_COMPLETE_ORDER_URL = "/data/order/mark-complete.json";
    public final String REMOVE_PRODUCT_ORDER_URL = "/data/order/remove-product.json";
  

    public JSONArray findAllFabrics() throws Exception;

    public JSONArray findAllColors() throws Exception;

    public JSONArray findAllStripes() throws Exception;

    public String incrementProductStock(Fabric fabric, Color color, Stripe stripe, StripeCombination stripeCombination, Double weight, int part) throws Exception;

    public JSONArray findAllPendingOrders() throws Exception;
    
    public boolean registerProductInOrder(OrderItem orderItem, String barcode) throws Exception;

    public boolean markOrderComplete(long id) throws Exception;

    public boolean removeProductFromOrder(OrderItem orderItem, String barcode) throws Exception;
}
