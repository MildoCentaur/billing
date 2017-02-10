package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.generic.FailFastProcessor;
import ar.com.adriabe.generic.KendoExecutionException;
import ar.com.adriabe.generic.Registrar;
import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.model.Order;
import ar.com.adriabe.model.OrderItem;
import ar.com.adriabe.processors.delivery.DeliveryOrderContext;
import ar.com.adriabe.processors.delivery.DeliveryOrderProvider;
import ar.com.adriabe.services.ClientService;
import ar.com.adriabe.services.OrderService;
import ar.com.adriabe.web.controllers.adapters.DeliveryOrderJSONAdapter;
import ar.com.adriabe.web.controllers.adapters.OrderJSONAdapter;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageModel;
import ar.com.adriabe.web.model.WebPageResponse;
import ar.com.adriabe.web.model.json.DeliveryOrderJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

/**
 * Created by AJMILD1 on 04/09/14.
 */
@Controller
public class OrderPageController extends AbstractPageController {

    @Autowired
    OrderService orderService;

    @Autowired
    ClientService clientService;

    @Autowired
    OrderJSONAdapter orderJSONAdapter;

    @Autowired
    DeliveryOrderJSONAdapter deliveryOrderJSONAdapter;

    @Autowired
    @Qualifier("deliveryOrderRegistrar")
    Registrar<DeliveryOrderContext, DeliveryOrderProvider> deliveryOrderRegistrar;

    @Autowired
    @Qualifier("deliveryOrderPrintProcessor")
    FailFastProcessor<DeliveryOrderContext> deliveryOrderPrintProcess;


    @RequestMapping(value = "/list-orders", method = RequestMethod.GET)
    public String searchClient(Model model, @RequestParam(value = "query", defaultValue = "") String query) {

        List<Order> orders = orderService.findAllOrders(query);
        WebPageModel webPageModel = createWebPageModel("Listado de Pedidos");
        model.addAttribute("page", webPageModel);
        model.addAttribute("list", orders);
        return "order/list-orders";
    }

    @RequestMapping(value = "/new-order", method = RequestMethod.GET)
    public String newOrder(Model model) {

        return renderAddOrEditOrderForm(model, "Registrar Nuevo Pedido", new Order());
    }

    @RequestMapping(value = "/edit-order", method = RequestMethod.GET)
    public String editOrder(Model model, @RequestParam("id") Long id) {
        Order order = orderService.findOrderById(id);

        return renderAddOrEditOrderForm(model, "Editar Pedido", order);
    }

    @RequestMapping(value = "/show-order", method = RequestMethod.GET)
    public String viewOrder(Model model, @RequestParam("id") Long id) {
        Order order = orderService.findOrderById(id);

        String title = "Mostrar Pedido N&deg:" + id;
        WebPageModel webPageModel = createWebPageModel(title);

        model.addAttribute("page", webPageModel);
        model.addAttribute("order", order);

        return "order/show-order";


    }

    @RequestMapping(value = "/show-delivery-order", method = RequestMethod.GET)
    public String viewDeliveryOrder(Model model, @RequestParam("id") Long id,
                                    @RequestParam(value = "print", required = false,
                                            defaultValue = "false") boolean print) {
        DeliveryOrder order = orderService.findDeliveryOrderById(id);

        WebPageModel webPageModel = createWebPageModel("Mostrar Orden de Entrega " + id);
        model.addAttribute("page", webPageModel);
        model.addAttribute("order", order);
        model.addAttribute("print", print);

        return "order/show-delivery-order";
    }

    @RequestMapping(value = "/new-delivery-order", method = RequestMethod.GET)
    public String deliverOrder(Model model, @RequestParam(value = "clientId", required = false, defaultValue = "0") Long clientId,
                               @RequestParam("orderId") Long orderId) {
        List<OrderItem> items = orderService.findItemsToDeliverByOrderOrClient(clientId, orderId);
        Integer amount = orderService.countOrdersToDeliverByOrderOrClient(clientId, orderId);
        Client client = orderService.findOrderById(orderId).getClient();
        Integer amountPackages = orderService.countPackagesToDeliverByOrderOrClient(clientId, orderId);


        WebPageModel webPageModel = createWebPageModel("Nueva orden de entrega");
        model.addAttribute("page", webPageModel);
        model.addAttribute("list", items);
        model.addAttribute("client", client);
        model.addAttribute("orderId", orderId);
        String pedidos = amount + " " + (amount == 1 ? "pedido " : "pedidos ");
        model.addAttribute("ordersToDeliver", pedidos);
        String packages = amountPackages + " " + (amountPackages == 1 ? "bulto " : "bultos ");
        model.addAttribute("packagesToDeliver", packages);


        return "order/new-delivery-order";
    }

    @RequestMapping(value = "/add-delivery", method = RequestMethod.POST, consumes = "application/json", produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    WebPageResponse processDeliverOrder(@RequestBody DeliveryOrderJSON delivery, BindingResult result, SessionStatus status) {
        WebPageResponse response = new WebPageResponse();
        DeliveryOrderContext context = null;
        try {
            DeliveryOrderProvider provider = new DeliveryOrderProvider(delivery, delivery.getOrderId());
            context = deliveryOrderRegistrar.register(provider);

        } catch (KendoExecutionException e) {
            logger.error(e.getMessage(), e);
            response.addError(e.getMessage());
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            response.addError("La operación no se pudo realizar");
            return response;
        }

        try {
            deliveryOrderPrintProcess.process(context);
        } catch (KendoExecutionException e) {
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return response;
    }

    @RequestMapping(value = "/list-delivery-orders", method = RequestMethod.GET)
    public String listDeliverOrder(Model model) {

        List<DeliveryOrder> orders = orderService.findAllDeliveryOrders();
        WebPageModel webPageModel = createWebPageModel("Listado de Ordenes de Entrega");
        model.addAttribute("page", webPageModel);
        model.addAttribute("list", orders);
        return "order/list-delivery-orders";
    }

    private String renderAddOrEditOrderForm(Model model, String title, Order order) {
        WebPageModel webPageModel = createWebPageModel(title);
        if (order == null) {
            webPageModel.addErrorMessage("Pedido no encontrado.");
        }
        model.addAttribute("page", webPageModel);
        model.addAttribute("order", order);

        return "order/new-order-old";
    }

    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_ORDER;
    }
}
