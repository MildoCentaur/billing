package ar.com.adriabe.web.controllers.page;

import ar.com.adriabe.model.*;
import ar.com.adriabe.model.constant.ORDER_STATUS;
import ar.com.adriabe.services.*;
import ar.com.adriabe.web.model.MODULE_NAME;
import ar.com.adriabe.web.model.WebPageModel;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mildo on 8/21/14.
 */
@Controller
public class HomePageController extends AbstractPageController {

    @Autowired
    ClientService clientService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    ProductService productService;

    @Autowired
    ColorService colorService;

    @Autowired
    OrderService orderService;

    @Autowired
    EventService eventService;


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(Model model) {
        WebPageModel webPageModel = createWebPageModel("Inicio");
        model.addAttribute("page", webPageModel);

        model.addAttribute("missingProducts", productService.countProductsWithEmptyStock());
        model.addAttribute("newSales", orderService.findNewOrders().size());
        model.addAttribute("ordersInProduction", orderService.findOrderByStatus(ORDER_STATUS.PRODUCTION).size());
        model.addAttribute("ordersDelivered", orderService.findTodaysDeliveredOrders().size());
        model.addAttribute("events", eventService.findAllPendingEvents());
        List<ORDER_STATUS> nonDeliveredStatus = Lists.newArrayList(ORDER_STATUS.WAITING, ORDER_STATUS.WORKING,
                ORDER_STATUS.DONE, ORDER_STATUS.PRODUCTION, ORDER_STATUS.PARTIALLY_DELIVERED);
        model.addAttribute("orders", orderService.findOrderByStatus(nonDeliveredStatus));

        /*
        *
        * select pf.name, c.name, sum(oi.quantity) as ordered, p.`stock`
						 from orders o inner join `order_item` oi on oi.`order_id`= o.id
						 inner join products p on oi.`product_id`=p.id
						 inner join product_families pf on pf.id=p.family_id
						 inner join colors c on p.`color_id`= c.id
						 where oi.`status`='ORDERED'
						 GROUP BY pf.name,p.stock,c.name;
        * */
        model.addAttribute("productsOrdered", productService.findAll(""));


        return "home/home";
    }

    @RequestMapping(value = "/search-entities", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, List<Object>> search(Model model, @RequestParam(value = "query", defaultValue = "") String query) {
        Map<String, List<Object>> result = new HashMap<String, List<Object>>();


        List<Client> clients = clientService.findAllClients(query);
        result.put("Clientes", new ArrayList<Object>(clients));

        List<Supplier> suppliers = supplierService.findAllSuppliers(query);
        result.put("Proveedores", new ArrayList<Object>(suppliers));

        List<Fabric> fabrics = productService.findAllFabrics(query);
        result.put("Tejidos", new ArrayList<Object>(fabrics));

        List<Stripe> stripes = productService.findAllStripes(query);
        result.put("Rayados", new ArrayList<Object>(stripes));

        List<Color> colors = colorService.findAllColors(query);
        result.put("Colores", new ArrayList<Object>(colors));


        return result;
    }

    @Override
    public MODULE_NAME getModuleName() {
        return MODULE_NAME.MODULE_HOME;
    }
}
