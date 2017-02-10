package ar.com.adriabe.web.controllers.adapters;

import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.model.DeliveryOrderItem;
import ar.com.adriabe.model.OrderItemDetail;
import ar.com.adriabe.model.Product;
import ar.com.adriabe.model.constant.DELIVERY_ORDER_STATUS;
import ar.com.adriabe.model.constant.ORDER_ITEM_DETAIL_STATUS;
import ar.com.adriabe.services.ClientService;
import ar.com.adriabe.services.OrderService;
import ar.com.adriabe.services.ProductService;
import ar.com.adriabe.web.model.json.DeliveryOrderItemJSON;
import ar.com.adriabe.web.model.json.DeliveryOrderJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ajmild1 on 27/03/2015.
 */
@Component("deliveryOrderJSONAdapter")
public class DeliveryOrderJSONAdapter {

    @Autowired
    ClientService clientService;

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

    public DeliveryOrder convertDeliveryOrderJSONToDeliveryOrder(DeliveryOrderJSON json) throws Exception {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setStatus(DELIVERY_ORDER_STATUS.WAITING);
        deliveryOrder.setClient(clientService.findClientById(json.getClientId()));
        deliveryOrder.setDate(new Date());
        List<DeliveryOrderItem> items = new ArrayList<DeliveryOrderItem>();
        List<OrderItemDetail> packages = new ArrayList<OrderItemDetail>();
        DeliveryOrderItem item = null;
        OrderItemDetail packageDetail = null;
        Product product = null;
        Double total = 0.0;
        for (DeliveryOrderItemJSON deliveryOrderItemJSON : json.getItems()) {
            Product currentProduct = productService.findById(deliveryOrderItemJSON.getProductId());
            if (product == null || product != currentProduct) {
                item = new DeliveryOrderItem();
                product = currentProduct;
                packages = new ArrayList<OrderItemDetail>();
                item.setProduct(product);
                items.add(item);
            }
            total = total + deliveryOrderItemJSON.getProductPrice() * deliveryOrderItemJSON.getProductWeight();
            packageDetail = orderService.findOrderItemDetailById(deliveryOrderItemJSON.getOrderItemDetailId());
            packageDetail.setStatus(ORDER_ITEM_DETAIL_STATUS.READY_TO_DELIVER);
            packages.add(packageDetail);
            item.setPackages(packages);
        }
        deliveryOrder.setItems(items);
        deliveryOrder.setTotalAmount(total);
        return deliveryOrder;
    }

    public List<DeliveryOrderJSON> convertDeliveryOrderListToDeliveryOrderJSONList(List<DeliveryOrder> list) {
        List<DeliveryOrderJSON> result = new ArrayList<DeliveryOrderJSON>();
        for (DeliveryOrder deliveryOrder : list) {
            result.add(convertDeliveryOrderToDeliveryOrderJSON(deliveryOrder));
        }
        return result;
    }

    public DeliveryOrderJSON convertDeliveryOrderToDeliveryOrderJSON(DeliveryOrder deliveryOrder) {
        DeliveryOrderJSON result = new DeliveryOrderJSON();
        DeliveryOrderItemJSON item;
        List<DeliveryOrderItemJSON> items = new ArrayList<DeliveryOrderItemJSON>();
        result.setClientId(deliveryOrder.getClient().getId());
        result.setClientName(deliveryOrder.getClient().getName());
        for (DeliveryOrderItem deliveryOrderItem : deliveryOrder.getItems()) {
            item = new DeliveryOrderItemJSON();
            item.setProductId(deliveryOrderItem.getProduct().getId());
            item.setProductName(deliveryOrderItem.getProduct().getProductName());
            item.setFabricName(deliveryOrderItem.getProduct().getFabricName());
            item.setColorName(deliveryOrderItem.getProduct().getColorName());
            for (OrderItemDetail orderItemDetail : deliveryOrderItem.getPackages()) {
                item.setOrderItemDetailId(orderItemDetail.getId());
                item.setProductWeight(orderItemDetail.getWeight());
                item.setProductBarcode(orderItemDetail.getBarcode());
            }
            items.add(item);
        }
        result.setItems(items);

        return result;
    }


    public List<OrderItemDetail> convertDeliveryOrderItemsJSONListToOrderItemDetailsList(ArrayList<DeliveryOrderItemJSON> json) {
        List<OrderItemDetail> list = new ArrayList<OrderItemDetail>();
        for (DeliveryOrderItemJSON deliveryOrderItemJSON : json) {
            OrderItemDetail item = orderService.findOrderItemDetailById(deliveryOrderItemJSON.getOrderItemDetailId());
            item.setStatus(ORDER_ITEM_DETAIL_STATUS.valueOf(deliveryOrderItemJSON.getStatus()));
            item.setBarcode(deliveryOrderItemJSON.getProductBarcode());
            item.setWeight(deliveryOrderItemJSON.getProductWeight());
            if (item != null) {
                list.add(item);
            }
        }
        return list;
    }
}
