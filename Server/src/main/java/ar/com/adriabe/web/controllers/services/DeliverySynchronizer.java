package ar.com.adriabe.web.controllers.services;


import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.model.OrderItemDetail;
import ar.com.adriabe.services.DeliveryOrderService;
import ar.com.adriabe.services.OrderService;
import ar.com.adriabe.web.controllers.adapters.DeliveryOrderJSONAdapter;
import ar.com.adriabe.web.model.json.DeliveryOrderJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ajmild1 on 11/08/2015.
 */
@Controller
public class DeliverySynchronizer {

    private OrderService orderService;

    private DeliveryOrderService deliveryOrderService;

    private DeliveryOrderJSONAdapter adapter;

    @Autowired
    public DeliverySynchronizer(OrderService orderService, DeliveryOrderService deliveryOrderService, DeliveryOrderJSONAdapter adapter) {
        this.orderService = orderService;
        this.deliveryOrderService = deliveryOrderService;
        this.adapter = adapter;
    }


    @RequestMapping(value = "/synch/delivery", method = RequestMethod.GET, consumes = "application/json")
    public
    @ResponseBody
    List<DeliveryOrderJSON> synchronizeDeliveryApp() {//(@RequestBody(required = false) ArrayList<DeliveryOrderItemJSON> json, BindingResult binding, SessionStatus status) {
        try {
            List<OrderItemDetail> delivered = adapter.convertDeliveryOrderItemsJSONListToOrderItemDetailsList(null);
            deliveryOrderService.updateDeliveredItemsStatus(delivered);
            List<DeliveryOrder> list = orderService.findAllDeliveryOrders();
            return adapter.convertDeliveryOrderListToDeliveryOrderJSONList(list);
        } catch (Exception e) {
            return new ArrayList<DeliveryOrderJSON>();
        }
    }
}
