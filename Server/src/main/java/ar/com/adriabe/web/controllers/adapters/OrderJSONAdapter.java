package ar.com.adriabe.web.controllers.adapters;

import ar.com.adriabe.model.Order;
import ar.com.adriabe.model.OrderItem;
import ar.com.adriabe.model.OrderItemDetail;
import ar.com.adriabe.model.Product;
import ar.com.adriabe.model.constant.ORDER_ITEM_STATUS;
import ar.com.adriabe.model.constant.ORDER_STATUS;
import ar.com.adriabe.services.*;
import ar.com.adriabe.web.model.json.OrderItemDetailJSON;
import ar.com.adriabe.web.model.json.OrderItemJSON;
import ar.com.adriabe.web.model.json.OrderJSON;
import ar.com.adriabe.web.model.json.ProductJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 3/1/15.
 */
@Component
public class OrderJSONAdapter {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    ClientService clientService;

    @Autowired
    ProductJSONAdapter productJSONAdapter;

    public List<OrderJSON> convertOrderListToOrderJSONList(List<Order> pendings) {
        List<OrderJSON> result = new ArrayList<OrderJSON>();
        for (Order pending : pendings) {
            result.add(convertOrderToOrderJSON(pending));
        }

        return result;
    }

    public OrderJSON convertOrderToOrderJSON(Order aOrder) {
        OrderPrioritizer orderPrioritizer = new OrderPrioritizer();


        OrderJSON result = new OrderJSON();
        result.setId(aOrder.getId());
        result.setClientName(aOrder.getClient().getFullName());
        result.setClientId(aOrder.getClient().getId());
        result.setPriority(orderPrioritizer.prioritize(aOrder));
        result.setStatus(aOrder.getStatusLabel());
        List<OrderItemJSON> itemJSONs = new ArrayList<OrderItemJSON>();
        OrderItemJSON itemJSON;
        OrderItemJSON accesoryItemJSON;
        for (OrderItem orderItem : aOrder.getItems()) {
            if (orderItem.isMainItem()) {
                itemJSON = new OrderItemJSON();
                itemJSON.setStatus(orderItem.getStatus().getLabel());
                itemJSON.setId(orderItem.getId());
                itemJSON.setQuantity(orderItem.getQuantity());
                itemJSON.setProduct(productJSONAdapter.buildProductJSONFromProduct(orderItem.getProduct()));
                itemJSON.setPackages(convertOrderItemDetailsToOrderItemDetailJSON(orderItem.getPackages()));
                List<OrderItemJSON> accesoriesItemJSON = new ArrayList<OrderItemJSON>();
                for (OrderItem accesoryItem : orderItem.getAccesories()) {
                    accesoryItemJSON = new OrderItemJSON();
                    accesoryItemJSON.setAccesories(null);
                    accesoryItemJSON.setId(accesoryItem.getId());
                    accesoryItemJSON.setProduct(productJSONAdapter.buildProductJSONFromProduct(accesoryItem.getProduct()));
                    accesoryItemJSON.setQuantity(accesoryItem.getQuantity());
                    accesoryItemJSON.setStatus(accesoryItem.getStatus().getLabel());
                    accesoryItemJSON.setPackages(convertOrderItemDetailsToOrderItemDetailJSON(accesoryItem.getPackages()));
                    accesoriesItemJSON.add(accesoryItemJSON);
                    itemJSON.setAccesories(accesoriesItemJSON);
                }
                itemJSONs.add(itemJSON);
            }
        }
        result.setItems(itemJSONs);
        return result;
    }

    private List<OrderItemDetailJSON> convertOrderItemDetailsToOrderItemDetailJSON(List<OrderItemDetail> packages) {
        List<OrderItemDetailJSON> result = new ArrayList<OrderItemDetailJSON>();
        OrderItemDetailJSON itemJSON = new OrderItemDetailJSON();
        for (OrderItemDetail aPackage : packages) {
            itemJSON.setStatus(aPackage.getStatus().getLabel());
            itemJSON.setBarcode(aPackage.getBarcode());
            itemJSON.setId(aPackage.getId());
            itemJSON.setWeight(aPackage.getWeight());
            result.add(itemJSON);
        }
        return result;
    }

    public Order convertOrderJSONToOrder(OrderJSON json) throws ServiceException {
        Order order;
        List<OrderItemJSON> deleted = new ArrayList<OrderItemJSON>();
        List<OrderItem> itemsTodelete = new ArrayList<OrderItem>();
        removeUserDeletedOrderItemJSON(json, deleted);

        if (json.getId() != null && json.getId() > 0) {
            order = orderService.findOrderById(json.getId());
            boolean found;
            for (OrderItem orderItem : order.getItems()) {
                if (orderItem.isMainItem()) {
                    found = false;
                    for (OrderItemJSON itemJSON : json.getItems()) {
                        found = found || updateOrderItemWithOrderItemJSONQuantity(itemJSON, orderItem);
                    }
                    if (!found) {
                        itemsTodelete.add(orderItem);
                    }
                }
            }
            order.getItems().removeAll(itemsTodelete);
        } else {
            order = createNewOrderFromORderJSON(json);
        }

        return order;

    }

    private void removeUserDeletedOrderItemJSON(OrderJSON json, List<OrderItemJSON> deleted) {
        for (OrderItemJSON itemJSON : json.getItems()) {
            if (itemJSON.isDeleted()) {
                deleted.add(itemJSON);
            }
        }
        if (deleted.size() > 0) {
            json.getItems().removeAll(deleted);
        }
    }

    private boolean updateOrderItemWithOrderItemJSONQuantity(OrderItemJSON itemJSON, OrderItem orderItem) {
        if (itemJSON == null || orderItem == null) {
            return false;
        }
        if (orderItem.getAccesories() != null && itemJSON.getAccesories() != null
                && orderItem.getAccesories().size() != itemJSON.getAccesories().size()) {
            return false;
        }

        if (isSameProductInItem(itemJSON, orderItem)) {
            boolean isSameAccessory;
            //la primera es para validar que los accesorios son los mismos
            for (OrderItem accesory : orderItem.getAccesories()) {
                isSameAccessory = false;
                for (OrderItemJSON accesoryJSON : itemJSON.getAccesories()) {
                    if (isSameProductInItem(accesoryJSON, accesory)) {
                        isSameAccessory = true;
                    }
                }
                if (!isSameAccessory) {
                    return false; // el accesorio no se encontro el item no es el mismo
                }
            }
            //la segunsda pasada es para actualizar los valores
            for (OrderItem accesory : orderItem.getAccesories()) {
                for (OrderItemJSON accesoryJSON : itemJSON.getAccesories()) {
                    if (isSameProductInItem(accesoryJSON, accesory)) {
                        accesory.setQuantity(accesoryJSON.getQuantity());
                    }
                }
            }
            orderItem.setQuantity(itemJSON.getQuantity());

            return true; // Todos los accesorios fueron encontrados y actualizados
        }
        return false; // no poseen el mismo producto
    }

    private boolean isSameProductInItem(OrderItemJSON itemJSON, OrderItem orderItem) {
        Product product = orderItem.getProduct();
        ProductJSON productJSON = itemJSON.getProduct();
        boolean mainItemIsTheSame = false;
        if (product.getFabric().getId() == productJSON.getFabricId()) {
            if (product.getColor().getId() == productJSON.getColorId()) {
                if (product.getStripe() == null && (productJSON.getStripeId() == null || productJSON.getStripeId() == 0)) {
                    mainItemIsTheSame = true;
                }
                if (product.getStripe() != null) {
                    if (product.getStripe().getId() == productJSON.getStripeId()) {
                        if (product.getStripeCombination().getId() == productJSON.getStripeCombinationId()) {
                            mainItemIsTheSame = true;
                        }
                    }
                }
            }
        }
        return mainItemIsTheSame;
    }

    private Order createNewOrderFromORderJSON(OrderJSON json) throws ServiceException {
        Order order;
        order = new Order();
        order.setClient(clientService.findClientById(json.getClientId()));
        order.setStatus(ORDER_STATUS.WAITING);
        order.setOrderedDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 15);
        order.setEstimatedDate(calendar.getTime());
        List<OrderItem> items = new ArrayList<OrderItem>();
        List<OrderItem> accesories;
        OrderItem item, accesory;

        for (OrderItemJSON itemJSON : json.getItems()) {
            item = convertOrderItemJSONToOrderItem(itemJSON);
            accesories = new ArrayList<OrderItem>();
            for (OrderItemJSON orderItemJSON : itemJSON.getAccesories()) {
                accesory = convertOrderItemJSONToOrderItem(orderItemJSON);
                accesories.add(accesory);
            }
            item.setAccesories(accesories);
            items.add(item);
        }
        order.setItems(items);
        return order;
    }

    private OrderItem convertOrderItemJSONToOrderItem(OrderItemJSON itemJSON) throws ServiceException {
        OrderItem item;
        ProductJSON productJSON;
        Product product;
        item = new OrderItem();
        item.setMainItem(true);
        item.setQuantity(itemJSON.getQuantity());
        productJSON = itemJSON.getProduct();
        product = productService.getByFabricAndColorAndStripeId(productJSON.getFabricId(), productJSON.getColorId(), productJSON.getStripeId(), productJSON.getStripeCombinationId());
        item.setPrice(product.getPrice());
        item.setProduct(product);
        item.setStatus(ORDER_ITEM_STATUS.ORDERED);
        return item;
    }


}
