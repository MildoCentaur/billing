package ar.com.adriabe.web.controllers.inflaters;


import ar.com.adriabe.daos.ClientDao;
import ar.com.adriabe.model.Client;
import ar.com.adriabe.model.Order;
import ar.com.adriabe.model.OrderItem;
import ar.com.adriabe.services.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderInflater implements Inflater<Order> {

    ClientDao clientDao;
    OrderItemInflater orderItemInflater;

    @Autowired
    public OrderInflater(ClientDao clientDao, OrderItemInflater orderItemInflater) {
        this.clientDao = clientDao;
        this.orderItemInflater = orderItemInflater;
    }

    @Override
    public Order inflate(Order order) throws ServiceException {

        Client client = clientDao.findById(order.getClient().getId());
        if (client == null) {
            throw new ServiceException("Cliente no encontrado.");
        }
        order.setClient(client);

        for (OrderItem orderItem : order.getItems()) {
            orderItemInflater.inflate(orderItem);
        }
        return order;
    }
}
