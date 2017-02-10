package ar.com.adriabe.utilities;


import ar.com.adriabe.model.*;
import ar.com.adriabe.model.constant.EurekaConstants;

import java.text.SimpleDateFormat;
import java.util.*;


public class TicketPrintable extends CustomPrintable implements Comparator<DeliveryOrderItem> {

    private static final String PCIO_UNIT_STRING = "Pcio. Unit:";

    private static final int COLUMN_CODE_WIDTH = 5;
    private static final int COLUMN_COLOR_WIDTH = 25;
    private static final int COLUMN_WEIGHT_WIDTH = 9;

    private static final int TOTAL_WIDTH = 38;

    private static final int COLUMN_PCIO_STR_WIDTH = 13;
    private static final int COLUMN_PCIO_WIDTH = 12;
    private static final int COLUMN_PCIO_PRODUCT_WIDTH = 13;

    private static final int TOTAL_WEIGHT_WIDTH = 35;

    List<String> lines = null;

    DeliveryOrder deliveryOrder;

    public TicketPrintable(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public List<String> getPrintableTicket() {
        List<DeliveryOrderItem> list = deliveryOrder.getItems();
        Collections.sort(list, this);

        return getTicketLines(list);
    }

    private List<String> getTicketLines(List<DeliveryOrderItem> list) {
        List<String> result = new ArrayList<String>();
        String aux;
        String line;
        SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE_SHORT);

        //Ticket Header
        line = preAppendWithSpaces("Fecha: " + format.format(new Date()), TicketPrinter.PRINTER_LINE_WIDTH);
        result.add(line + TicketPrinter.NEW_LINE);
        result.add(TicketPrinter.NEW_LINE);

        //Ticket body
        ProductFamily family = null, oldFamily = null;
        DeliveryOrderItem oldItem = null;
        double acum = 0;
        int amount = 0, families = 0;
        for (DeliveryOrderItem deliveryOrderItem : list) {
            family = deliveryOrderItem.getProduct().getProductFamily();
            if (oldFamily == null) {
                oldFamily = family;
                oldItem = deliveryOrderItem;
                generateHeaderFamily(result, family, deliveryOrderItem.getProduct());
            }

            if (isDifferentFamilly(family, oldFamily)) {
                generateFooterOldFamily(result, oldFamily, acum, amount, oldItem);
                families++;
                result.add(TicketPrinter.NEW_LINE);
                result.add(TicketPrinter.NEW_LINE);
                generateHeaderFamily(result, family, deliveryOrderItem.getProduct());
                oldFamily = family;
                oldItem = deliveryOrderItem;


            }
            if (isSameFamily(family, oldFamily)) {
                generateBodyFamily(result, deliveryOrderItem);
                acum += deliveryOrderItem.getWeight();
                amount += deliveryOrderItem.getPackages().size();
            }
        }
        generateFooterOldFamily(result, oldFamily, acum, amount, oldItem);
        families++;
        result.add(TicketPrinter.NEW_LINE);
        result.add(TicketPrinter.NEW_LINE);


        //Ticket footer
        result.add(TicketPrinter.PRINT_RED);

        line = completeWithSpaces(String.format("%03d", families), 5);
        line = line + completeWithSpaces("Total:", 17);
        aux = String.format(new Locale("es", "ES"), "%1$,.2f $", deliveryOrder.getAmountAccountable());
        aux = preAppendWithSpaces(aux, 16);
        line = line + aux;
        result.add(line + TicketPrinter.NEW_LINE);

        result.add(TicketPrinter.PRINT_BLACK);

        //Extra feed para poder cortar el ticket
        result.add(TicketPrinter.NEW_LINE);
        result.add(TicketPrinter.NEW_LINE);
        result.add(TicketPrinter.NEW_LINE);
        result.add(TicketPrinter.NEW_LINE);


        return result;
    }

    private boolean isSameFamily(ProductFamily family, ProductFamily oldFamily) {
        long stripeFamilyId = family.getStripe() == null ? 0 : family.getStripe().getId();
        long stripeOldFamilyId = oldFamily.getStripe() == null ? 0 : oldFamily.getStripe().getId();
        return family.getId() == oldFamily.getId() && stripeFamilyId == stripeOldFamilyId;
    }

    private boolean isDifferentFamilly(ProductFamily family, ProductFamily oldFamily) {
        return !isSameFamily(family, oldFamily);
    }

    private void generateFooterOldFamily(List<String> result, ProductFamily oldFamily, double acum, int amount, DeliveryOrderItem oldItem) {
        //line sum all weight
        String line = String.format("%03d", amount);
        String aux = String.format(new Locale("es", "ES"), "%1$,.2f Kg", acum);
        line = line + preAppendWithSpaces(aux, TOTAL_WEIGHT_WIDTH);
        result.add(line + TicketPrinter.NEW_LINE);


        //line price per product-family
        double price = oldFamily.getPrice();
        double totalFamily = price * acum;
        line = completeWithSpaces(PCIO_UNIT_STRING, COLUMN_PCIO_STR_WIDTH);

        aux = String.format(new Locale("es", "ES"), "%1$,.2f $", price);
        line = line + completeWithSpaces(aux, COLUMN_PCIO_WIDTH);

        aux = String.format(new Locale("es", "ES"), "%1$,.2f $", totalFamily);
        line = line + preAppendWithSpaces(aux, COLUMN_PCIO_PRODUCT_WIDTH);
        result.add(line + TicketPrinter.NEW_LINE);

    }

    private void generateHeaderFamily(List<String> result, ProductFamily family, Product product) {
        String line = product.getFabric().getCode() + " " + family.getName();
        line = line + ((family.getStripe() == null) ? "" : family.getStripe().getName());
        result.add(completeWithSpaces(line, TOTAL_WIDTH));
        result.add(TicketPrinter.NEW_LINE);
    }


    private void generateBodyFamily(List<String> result, DeliveryOrderItem deliveryOrderItem) {
        String aux;
        String line;
        String productSpecifics = "";
        StripeCombination combination = null;
        for (OrderItemDetail detail : deliveryOrderItem.getPackages()) {
            aux = String.format(new Locale("es", "ES"), "%1$,.2f Kg.", detail.getWeight());
            line = completeWithSpaces(" ", COLUMN_CODE_WIDTH);
            productSpecifics = deliveryOrderItem.getProduct().getColorName();
            combination = deliveryOrderItem.getProduct().getStripeCombination();
            productSpecifics = productSpecifics + ((combination == null) ? "" : combination.getName());
            line = line + completeWithSpaces(productSpecifics, COLUMN_COLOR_WIDTH);
            line = line + preAppendWithSpaces(aux, COLUMN_WEIGHT_WIDTH);
            result.add(line + TicketPrinter.NEW_LINE);
        }
    }

    @Override
    public String getDocuemntType() {
        return "POSTicket";
    }

    @Override
    public Boolean isDocuementReady() {
        return lines != null;
    }

    @Override
    public int compare(DeliveryOrderItem item1, DeliveryOrderItem item2) {
        long family1 = item1.getProduct().getProductFamily().getId();
        long family2 = item2.getProduct().getProductFamily().getId();
        Stripe stripe1 = item1.getProduct().getProductFamily().getStripe();
        Stripe stripe2 = item2.getProduct().getProductFamily().getStripe();
        long stripeId1 = (stripe1 == null) ? 0 : stripe1.getId();
        long stripeId2 = (stripe2 == null) ? 0 : stripe2.getId();

        return (int) ((family1 - family2 == 0) ? stripeId1 - stripeId2 : family1 - family2);
    }
}
