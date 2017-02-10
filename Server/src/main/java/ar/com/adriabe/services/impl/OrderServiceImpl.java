package ar.com.adriabe.services.impl;

import ar.com.adriabe.components.BarcodeAnalyzer;
import ar.com.adriabe.daos.DaoException;
import ar.com.adriabe.daos.DeliveryOrderDao;
import ar.com.adriabe.daos.OrderDao;
import ar.com.adriabe.daos.ProductDao;
import ar.com.adriabe.model.*;
import ar.com.adriabe.model.common.annotation.ILogableOperation;
import ar.com.adriabe.model.constant.ACTION_TYPE;
import ar.com.adriabe.model.constant.ORDER_STATUS;
import ar.com.adriabe.services.*;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Mildo on 10/14/14.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    public static final int FIFTY_PERCENTAGE = 50;
    public static final int HOUNDRED_PERCENTAGE = 100;


    private OrderDao orderDao;

    private ProductDao productDao;

    private DeliveryOrderDao deliveryOrderDao;

    private BillingService billingService;

    private BarcodeAnalyzer barcodeAnalyzer;

    private DeliveryOrderService deliveryOrderService;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ProductDao productDao, DeliveryOrderDao deliveryOrderDao,
                            BillingService billingService, @Qualifier("barcodeAnalyzer") BarcodeAnalyzer barcodeAnalyzer,
                            DeliveryOrderService deliveryOrderService) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.deliveryOrderDao = deliveryOrderDao;
        this.billingService = billingService;
        this.barcodeAnalyzer = barcodeAnalyzer;
        this.deliveryOrderService = deliveryOrderService;
    }

    @Override
    public List<Order> findAllOrders(String query) {
        if (query == null || query.isEmpty()) {
            return orderDao.findAll();
        }
        return orderDao.findLikeName(query);
    }

    @Override
    public Order findOrderById(Long id) {
        if (id == null || id < 0) {
            return null;
        }
        return orderDao.findById(id);
    }

    @Override
    @ILogableOperation(desc = "Registra un nuevo pedido", type = ACTION_TYPE.CREATE)
    @Transactional
    public void save(Order order) throws ServiceException {
        orderDao.saveOrUpdate(order);
    }

    @Override
    @ILogableOperation(desc = "Elimina un pedido", type = ACTION_TYPE.DELETE)
    @Transactional
    public void deleteById(Long id) {
        Order order = orderDao.findById(id);
        if (order != null) {
            orderDao.delete(order);
        }

    }

    @Override
    public OrderItem findOrderItemById(Long id) {
        if (id == 0) {
            return new OrderItem();
        }
        return orderDao.findOrderItemById(id);

    }

    @Override
    public List<Order> findAllPendingOrders() {
        List<Order> orders = orderDao.findByStatus(Lists.newArrayList(ORDER_STATUS.WAITING, ORDER_STATUS.WORKING));
        return orders;
    }

    @Override
    @ILogableOperation(desc = "Registra un paquete a un item de un pedido", type = ACTION_TYPE.UPDATE)
    public void registerOrderItemDetail(Long orderItemId, String code) throws InvalidDataException, ServiceException {
        OrderItem orderItem = orderDao.findOrderItemById(orderItemId);
        Barcode barcode = barcodeAnalyzer.scanBarcode(code);


        Double weight = barcode.getWeight();

        if (!orderItem.getProduct().getId().equals(barcode.getProduct().getId())) {
            throw new InvalidDataException();
        }


        if (orderItem.isMainProductInItem() && orderItem.getRequestedPackages() <= orderItem.getPrepearedPackages()) {
            throw new ServiceException("Ya se ingresaron todos los paquetes pedidos.");
        }

        OrderItemDetail detail = new OrderItemDetail();
        detail.setBarcode(code);
        detail.setWeight(weight);
        orderItem.add(detail);
        orderDao.save(orderItem);

        Order aOrder = orderDao.findOrderContainingOrderItem(orderItem);
        aOrder.setStatus(ORDER_STATUS.WORKING);
        aOrder.recalculateValues();
        orderDao.saveOrUpdate(aOrder);
    }

    @Override
    @ILogableOperation(desc = "Remueve un paquete de un item de un pedido", type = ACTION_TYPE.UPDATE)
    public void removeProductInOrder(Long orderItemId, String barcode) throws InvalidDataException, ServiceException {
        OrderItem orderItem = orderDao.findOrderItemById(orderItemId);
        Long familyId = Long.parseLong(barcode.substring(0, 3));
        Long colorId = Long.parseLong(barcode.substring(3, 6));
        Double weight = Long.parseLong(barcode.substring(7, 11)) / 100.0;
        OrderItemDetail selected = null;

        if (orderItem.getProduct().getProductFamily().getId() != familyId) {
            throw new InvalidDataException();
        }

        if (orderItem.getProduct().getColor().getId() != colorId) {
            throw new InvalidDataException();
        }


        if (orderItem.isMainProductInItem() && orderItem.getPrepearedPackages() < 1) {
            return;
        }


        for (OrderItemDetail detail : orderItem.getPackages()) {
            if (detail.getBarcode().compareTo(barcode) == 0) {
                selected = detail;
            }
        }
        if (selected != null) {
            orderItem.getPackages().remove(selected);
            orderDao.removeOrderItemDetail(selected);

            Order order = orderDao.findOrderContainingOrderItem(orderItem);
            order.setStatus(ORDER_STATUS.WORKING);
            order.recalculateValues();
            orderDao.saveOrUpdate(order);
        }
    }

    @Override
    public void updateOrderStatus(Long orderId, ORDER_STATUS status) throws ServiceException {
        Order order = findOrderById(orderId);
        updateOrderStatus(order, status);
    }

    @Override
    @ILogableOperation(desc = "Cambia el estado del pedido", type = ACTION_TYPE.UPDATE)
    public void updateOrderStatus(Order order, ORDER_STATUS status) throws ServiceException {
        order.setStatus(status);
        save(order);

    }

    @Override
    public List<Order> findNewOrders() {
        Calendar calendar = Calendar.getInstance(new Locale("es", "AR"));
        calendar.set(Calendar.HOUR, 23);
        calendar.add(Calendar.DATE, -1);
        Date from = calendar.getTime();
        Calendar calendarTo = Calendar.getInstance(new Locale("es", "AR"));
        Date to = calendarTo.getTime();

        try {
            return findOrdersInBetweenDates(from, to);
        } catch (ServiceException e) {
            e.printStackTrace();
            return new ArrayList<Order>();
        }

    }

    @Override
    public List<Order> findOrdersInBetweenDates(Date from, Date to) throws ServiceException {
        if (from == null || to == null || from.compareTo(to) > -1) {
            throw new ServiceException("Parametros invalidos");
        }

        return orderDao.findOrdersInBetweenDates(from, to);
    }

    @Override
    public List<Order> findOrderByStatus(ORDER_STATUS production) {
        return findOrderByStatus(Lists.newArrayList(production));
    }

    @Override
    public List<Order> findOrderByStatus(List<ORDER_STATUS> statusList) {
        return orderDao.findByStatus(statusList);
    }

    @Override
    public List<Order> findTodaysDeliveredOrders() {
        return orderDao.findTodaysDeliveredOrders();
    }

    @Override
    public List<DeliveryOrder> findAllDeliveryOrders() {
        return deliveryOrderDao.findAllDeliveryOrders();
    }

    @Override
    public List<OrderItem> findItemsToDeliverByOrderOrClient(Long clientId, Long orderId) {
        return orderDao.findItemsToDeliverByOrderOrClient(clientId, orderId);

    }

    @Override
    public Integer countOrdersToDeliverByOrderOrClient(Long clientId, Long orderId) {
        return orderDao.countOrdersToDeliverByOrderOrClient(clientId, orderId);
    }

    @Override
    public OrderItemDetail findOrderItemDetailById(Long id) {
        return orderDao.findOrderItemDetailById(id);
    }

    @Override
    public DeliveryOrder findDeliveryOrderById(Long id) {
        return deliveryOrderDao.findById(id);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void deliverOrderPackages(DeliveryOrder deliveryOrder, boolean print) throws Exception {
        //always save the complete deliveryOrder as the second CC is the one with all the data
        Client client = deliveryOrder.getClient();
        client.setBalanceActivity(client.getBalanceActivity() + deliveryOrder.getTotalAmount());
        deliveryOrderService.saveDeliveryOrder(deliveryOrder, true);
        Order order = orderDao.findOrderFromDeliveryOrderId(deliveryOrder.getId());

        if (order.getStatus() == ORDER_STATUS.DONE) {
            ORDER_STATUS status = order.isFullyDelivered() ? ORDER_STATUS.DELIVERED : ORDER_STATUS.PARTIALLY_DELIVERED;
            updateOrderStatus(order, status);
        }
        Bill bill = generateBillFromDeliveryOrder(deliveryOrder);
        if (bill != null) {
            billingService.save(bill);
        }

    }

    @Override
    @ILogableOperation(desc = "Elimina una orden de entrega", type = ACTION_TYPE.DELETE)
    @Transactional
    public void deleteDeliveryOrderById(Long id) throws ServiceException {


        DeliveryOrder deliveryOrder = deliveryOrderDao.findById(id);
        if (deliveryOrder != null) {
            deliveryOrderDao.delete(deliveryOrder);
            Order order = orderDao.findOrderFromDeliveryOrderId(deliveryOrder.getId());
            ORDER_STATUS status = ORDER_STATUS.DONE;
            if (order.getUncancelledDeliveries().size() > 0) {
                status = ORDER_STATUS.PARTIALLY_DELIVERED;

            }
            updateOrderStatus(order, status);
        }

    }

    @Override
    public Integer countPackagesToDeliverByOrderOrClient(Long clientId, Long orderId) {
        int prepearedPackages = 0;
        List<OrderItem> itemsToDeliverByOrderOrClient = orderDao.findItemsToDeliverByOrderOrClient(clientId, orderId);
        for (OrderItem orderItem : itemsToDeliverByOrderOrClient) {
            prepearedPackages = prepearedPackages + orderItem.getPrepearedPackages();
        }
        return prepearedPackages;
    }


    @ILogableOperation(desc = "Genera la factura asociada a una entrega si coresponde", type = ACTION_TYPE.CREATE)
    private Bill generateBillFromDeliveryOrder(DeliveryOrder deliveryOrder) throws DaoException {
        Bill bill = null;
        Client client = deliveryOrder.getClient();
        int percentage = client.getClientType() == 1 ? FIFTY_PERCENTAGE : (client.getClientType() == 2 ? HOUNDRED_PERCENTAGE : 0);

        bill = deliveryOrder.createBillForPercentage(percentage, billingService.getNextBillANumber(), billingService.getNextOrderANumber(), billingService.getNextBillBNumber(), billingService.getNextOrderBNumber());

        return bill;
    }


    private OrderItem getOrderItem(Order order, Product product) throws InvalidDataException, ServiceException {
        OrderItem item = null;
        if (order == null || product == null) {
            throw new InvalidDataException();
        }

        for (OrderItem orderItem : order.getItems()) {
            if (orderItem.getProduct().equals(product)) {
                item = orderItem;
            }
        }

        if (item == null) {
            throw new ServiceException("Producto no solicitado.");
        }
        return item;
    }

}
