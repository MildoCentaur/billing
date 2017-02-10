package ar.com.adriabe.web.controllers.services;

import ar.com.adriabe.components.OrderUpdater;
import ar.com.adriabe.model.Order;
import ar.com.adriabe.model.OrderAdjustment;
import ar.com.adriabe.model.constant.ORDER_STATUS;
import ar.com.adriabe.services.InvalidDataException;
import ar.com.adriabe.services.OrderService;
import ar.com.adriabe.services.ServiceException;
import ar.com.adriabe.web.controllers.adapters.OrderJSONAdapter;
import ar.com.adriabe.web.controllers.inflaters.OrderInflater;
import ar.com.adriabe.web.model.WebPageResponse;
import ar.com.adriabe.web.model.json.OrderJSON;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mildo on 11/16/14.
 */
@Controller
public class OrderServiceController extends AbstractServiceController {

    @Autowired
    OrderJSONAdapter orderJSONAdapter;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderInflater orderInflater;

    @Autowired
    OrderUpdater orderUpdater;

    @RequestMapping(value = "/add-order", method = RequestMethod.POST, headers = {"Accept=application/json"})
    public
    @ResponseBody
    WebPageResponse processNewOrder(@RequestBody String orderJsonString, BindingResult result, SessionStatus status) {

        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Save Order process initiated");
            ObjectMapper mapper = new ObjectMapper();
            // read from file, convert it to user class
            Order order = mapper.readValue(orderJsonString, Order.class);
            orderInflater.inflate(order);
            orderService.save(order);
        } catch (ServiceException se) {
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("No se pudo realizar la operación.");
        }


        return webPageResponse;
    }

    @RequestMapping(value = "/edit-process-order", method = RequestMethod.POST, headers = {"Accept=application/json"})
    public
    @ResponseBody
    WebPageResponse processEditOrder(@RequestBody String adjustmentsJsonString, BindingResult result, SessionStatus status) {
        logger.info("Save Order process initiated");
        WebPageResponse webPageResponse = new WebPageResponse();
        try {

            ObjectMapper mapper = new ObjectMapper();
            // read from file, convert it to user class
            OrderAdjustment[] adjustments = mapper.readValue(adjustmentsJsonString, OrderAdjustment[].class);
            orderUpdater.update(adjustments);

        } catch (ServiceException se) {
            logger.error(se.getMessage(), se);
            webPageResponse.addError(se.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("No se pudo realizar la operación.");
        }


        return webPageResponse;
    }


    @RequestMapping(value = "/delete/order", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse deleteOrder(Model model, @RequestParam("id-order") Long id) {

        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Save Order process initiated");

            orderService.deleteById(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("No se pudo realizar la operación.");
        }


        return webPageResponse;
    }

    @RequestMapping(value = "/delete/delivery-order", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse deleteDeliveryOrder(Model model, @RequestParam("id-order") Long id) {

        WebPageResponse webPageResponse = new WebPageResponse();
        try {
            logger.info("Save Order process initiated");

            orderService.deleteDeliveryOrderById(id);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            webPageResponse.addError("No se pudo realizar la operación.");
        }


        return webPageResponse;
    }

    @RequestMapping(value = "/data/orders/pending", method = RequestMethod.GET)
    public
    @ResponseBody
    List<OrderJSON> findAllPendingOrders(Model model) {
        List<OrderJSON> result;
        try {

            List<Order> pending = orderService.findAllPendingOrders();
            result = orderJSONAdapter.convertOrderListToOrderJSONList(pending);
        } catch (Exception e) {
            e.printStackTrace();
            result = new ArrayList<OrderJSON>();
        }
        return result;
    }


    @RequestMapping(value = "/data/order/register-product", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse registerOrderItemDetail(@RequestParam("order-item-id") Long orderItemId,
                                            @RequestParam("barcode") String barcode) {
        WebPageResponse response = new WebPageResponse();
        response.setAjaxResponse(Boolean.FALSE);
        if (orderItemId < 1 || barcode == null || barcode.isEmpty()) {
            response.addError("Datos invalidos.");
            return response;
        }


        try {
            orderService.registerOrderItemDetail(orderItemId, barcode);
            response.setAjaxResponse(Boolean.TRUE);
        } catch (ServiceException e) {
            response.addError(e.getMessage());
        } catch (InvalidDataException e) {
            e.printStackTrace();
            response.addError("Datos invalidos.");
        } catch (Exception e) {
            e.printStackTrace();
            response.addError("No se pudo ingresar el producto.");
        }
        return response;
    }


    @RequestMapping(value = "/data/order/remove-product", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse removeProductInOrderItem(@RequestParam("id-order-item") Long orderItemId,
                                             @RequestParam("barcode") String barcode) {
        WebPageResponse response = new WebPageResponse();

        if (orderItemId < 1 || barcode == null || barcode.isEmpty()) {
            response.addError("Datos invalidos.");
            return response;
        }


        try {
            orderService.removeProductInOrder(orderItemId, barcode);
            response.setAjaxResponse(Boolean.TRUE);
        } catch (ServiceException e) {
            response.addError(e.getMessage());
        } catch (InvalidDataException e) {
            e.printStackTrace();
            response.addError("Datos invalidos.");
        } catch (Exception e) {
            e.printStackTrace();
            response.addError("No se pudo quitar el producto.");
        }
        return response;
    }


    @RequestMapping(value = "/data/order/mark-complete", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded")
    public
    @ResponseBody
    WebPageResponse markOrderComplete(@RequestParam("order-id") Long orderId) {
        WebPageResponse response = new WebPageResponse();

        if (orderId < 1) {
            response.addError("Datos invalidos.");
            return response;
        }


        try {
            orderService.updateOrderStatus(orderId, ORDER_STATUS.DONE);
            response.setAjaxResponse(Boolean.TRUE);
        } catch (Exception e) {
            e.printStackTrace();
            response.addError("No se pudo marcar el pedido como completo.");
        }
        return response;
    }


}