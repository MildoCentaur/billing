package ar.com.adriabe.processors.delivery;

import ar.com.adriabe.daos.ClientDao;
import ar.com.adriabe.daos.OrderDao;
import ar.com.adriabe.daos.ProductDao;
import ar.com.adriabe.generic.Builder;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.model.*;
import ar.com.adriabe.model.constant.DELIVERY_ORDER_STATUS;
import ar.com.adriabe.model.constant.ORDER_ITEM_DETAIL_STATUS;
import ar.com.adriabe.web.model.json.DeliveryOrderItemJSON;
import ar.com.adriabe.web.model.json.DeliveryOrderJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by ajmild1 on 30/09/2015.
 */
@Component
public class DeliveryOrderBuilder implements Builder<DeliveryOrderContext, DeliveryOrderProvider> {

    ClientDao clientDao;

    ProductDao productDao;

    OrderDao orderDao;

    @Autowired
    public DeliveryOrderBuilder(ClientDao clientDao, ProductDao productDao, OrderDao orderDao) {
        this.clientDao = clientDao;
        this.productDao = productDao;
        this.orderDao = orderDao;
    }

    @Override
    public DeliveryOrderContext build(DeliveryOrderProvider provider) throws KendoExecutionException {

        DeliveryOrderJSON json = provider.getRawDeliveryOrder();


        Order order = populateOrder(provider);
        boolean contextPrintable = isContextPrintable(provider);
        DeliveryOrder deliveryOrder = populateDeliveryOrder(provider);
        DeliveryOrderContext context = new DeliveryOrderContext(deliveryOrder, contextPrintable, order);

        return context;

    }

    private DeliveryOrder populateDeliveryOrder(DeliveryOrderProvider provider) throws KendoExecutionException {
        DeliveryOrderJSON json = provider.getRawDeliveryOrder();
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setStatus(DELIVERY_ORDER_STATUS.WAITING);
        deliveryOrder.setClient(clientDao.findById(json.getClientId()));
        deliveryOrder.setDate(new Date());
        List<DeliveryOrderItem> items = new ArrayList<DeliveryOrderItem>();
        Map<Product, DeliveryOrderItem> mapProductToItems = new HashMap<Product, DeliveryOrderItem>();
        Double total = 0.0;
        int line = 0;
        try {
            for (DeliveryOrderItemJSON deliveryOrderItemJSON : json.getItems()) {
                line++;
                Product product = getProduct(deliveryOrderItemJSON);

                List<OrderItemDetail> packages = new ArrayList<OrderItemDetail>();
                if (!mapProductToItems.containsKey(product)) {
                    DeliveryOrderItem item = new DeliveryOrderItem();
                    item.setProduct(product);
                    items.add(item);
                    item.setPackages(packages);
                    mapProductToItems.put(product, item);
                }
                DeliveryOrderItem item = mapProductToItems.get(product);
                packages = item.getPackages();
                total = total + deliveryOrderItemJSON.getProductPrice() * deliveryOrderItemJSON.getProductWeight();
                OrderItemDetail itemPackage = orderDao.findOrderItemDetailById(deliveryOrderItemJSON.getOrderItemDetailId());
                if (itemPackage.getStatus() != ORDER_ITEM_DETAIL_STATUS.PREPEARED) {
                    throw new KendoExecutionException("El item ya fue entregado.");
                }
                packages.add(itemPackage);


            }
            deliveryOrder.setItems(items);
            deliveryOrder.setTotalAmount(total);

            return deliveryOrder;
        } catch (KendoExecutionException e) {
            e.printStackTrace();
            throw new KendoExecutionException("Error en linea: " + line + e.getMessage());
        }
    }

    private Product getProduct(DeliveryOrderItemJSON deliveryOrderItemJSON) throws KendoExecutionException {
        if (deliveryOrderItemJSON.getProductId() <= 0) {
            throw new KendoExecutionException("Producto no encontrado: " + deliveryOrderItemJSON.getProductName());
        }
        try {
            return productDao.findById(deliveryOrderItemJSON.getProductId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new KendoExecutionException("Producto no encontrado: " + deliveryOrderItemJSON.getProductName());
        }
    }

    private Order populateOrder(DeliveryOrderProvider provider) throws KendoExecutionException {
        if (provider.getOrderId() <= 0) {
            throw new KendoExecutionException("Pedido no encontrado.");
        }
        Order order = orderDao.findById(provider.getOrderId());
        if (order == null) {
            throw new KendoExecutionException("Pedido no encontrado.");
        }
        return order;
    }


    private boolean isContextPrintable(DeliveryOrderProvider provider) {
        return provider.getRawDeliveryOrder().isPrint();
    }


//
//    public List<OrderItemDetail> convertDeliveryOrderItemsJSONListToOrderItemDetailsList(ArrayList<DeliveryOrderItemJSON> json) {
//        List<OrderItemDetail> list = new ArrayList<OrderItemDetail>();
//        for (DeliveryOrderItemJSON deliveryOrderItemJSON : json) {
//            OrderItemDetail item = orderDao.findOrderItemDetailById(deliveryOrderItemJSON.getOrderItemDetailId());
//            item.setStatus(ORDER_ITEM_DETAIL_STATUS.valueOf(deliveryOrderItemJSON.getStatus()));
//            item.setBarcode(deliveryOrderItemJSON.getProductBarcode());
//            item.setWeight(deliveryOrderItemJSON.getProductWeight());
//            if(item!=null ){
//                list.add(item);
//            }
//        }
//        return list;
//    }


}
