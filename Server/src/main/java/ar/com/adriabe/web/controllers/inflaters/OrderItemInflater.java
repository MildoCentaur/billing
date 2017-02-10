package ar.com.adriabe.web.controllers.inflaters;

import ar.com.adriabe.model.OrderItem;
import ar.com.adriabe.model.Product;
import ar.com.adriabe.model.constant.ORDER_ITEM_STATUS;
import ar.com.adriabe.services.ProductService;
import ar.com.adriabe.services.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemInflater implements Inflater<OrderItem> {

    ProductService productService;

    @Autowired
    public OrderItemInflater(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public OrderItem inflate(OrderItem orderItem) throws ServiceException {

        inflateOrderItem(orderItem, true);

        return orderItem;
    }

    protected void inflateOrderItem(OrderItem orderItem, boolean mainItem) throws ServiceException {
        orderItem.setStatus(ORDER_ITEM_STATUS.ORDERED);
        Product itemProduct = orderItem.getProduct();

        Product product = productService.getByFabricAndColorAndStripe(itemProduct.getFabric(), itemProduct.getColor(), itemProduct.getStripe(), itemProduct.getStripeCombination());
        orderItem.setProduct(product);

        if (orderItem.getQuantity() <= 0) {
            throw new ServiceException("Cantidad de " + product.getProductName() + " invalida.");
        }

        orderItem.setPrice(product.getPrice());
        orderItem.setMainItem(mainItem);
        orderItem.setDeleted(false);
        for (OrderItem item : orderItem.getAccesories()) {
            inflateOrderItem(item, false);
        }
    }
}
