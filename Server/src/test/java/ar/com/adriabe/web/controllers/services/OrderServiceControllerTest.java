package ar.com.adriabe.web.controllers.services;

import ar.com.adriabe.model.*;
import ar.com.adriabe.services.OrderService;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/application-context.xml"})
public class OrderServiceControllerTest {


    @Autowired
    OrderService orderService;


    @Test
    public void testfdsfs() throws IOException {
        Order order = orderService.findOrderById(1l);
        ObjectMapper mapper = new ObjectMapper();
        Hibernate.initialize(order);


        System.out.println(mapper.writeValueAsString(order));
    }


    public void testSaveOrderFromOrderForm() throws Exception {
        Order aOrder = new Order();
        aOrder.setClient(new Client(1l));

        List<OrderItem> items = new ArrayList<OrderItem>();
        OrderItem oi = new OrderItem();
        Product product = new Product();
        Fabric fabric = new Fabric(1l);
        product.setFabric(fabric);
        Color color = new Color(2l);
        product.setColor(color);
        Stripe stripe = new Stripe();
        stripe.setId(1l);
        product.setStripe(stripe);
        StripeCombination stripeCombination = new StripeCombination();
        stripeCombination.setId(53l);
        product.setStripeCombination(stripeCombination);
        oi.setProduct(product);
        items.add(oi);

        oi = new OrderItem();
        product = new Product();
        fabric = new Fabric(1l);
        product.setFabric(fabric);
        color = new Color(2l);
        product.setColor(color);
        oi.setProduct(product);
        items.add(oi);

        aOrder.setItems(items);


        orderService.save(aOrder);

    }

}