package ar.com.adriabe.web.controllers.adapters;

import ar.com.adriabe.model.Bill;
import ar.com.adriabe.model.BillItem;
import ar.com.adriabe.model.Color;
import ar.com.adriabe.services.ClientService;
import ar.com.adriabe.services.ColorService;
import ar.com.adriabe.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mildo on 1/30/15.
 */
@Component
public class BillFormAdapter {

    @Autowired
    ProductService productService;

    @Autowired
    ClientService clientService;

    @Autowired
    ColorService colorService;

    public Bill adapter(Bill bill) throws KendoAdapterException {


        Bill newBill = buildBill(bill);

        newBill.setItems(createBillItem(bill));

        return newBill;

    }

    private List<BillItem> createBillItem(Bill bill) throws KendoAdapterException {
        Color color = null;
        List<BillItem> result = new ArrayList<BillItem>();
        BillItem newBillItem =null;
        try {
            for (BillItem billItem : bill.getItems()) {
                newBillItem =new BillItem();
                newBillItem.setTotal(billItem.getTotal());
                newBillItem.setAmount(billItem.getAmount());
                newBillItem.setPackages(billItem.getPackages());
                newBillItem.setPrice(billItem.getPrice());
                newBillItem.setSubtotal(billItem.getSubtotal());
                newBillItem.setTax(billItem.getTax());
                newBillItem.setProductFamily(productService.findProductFamilyByProductFamilyId(billItem.getProductFamily().getId()));
                color = colorService.findColorById(billItem.getColor().getId());
                newBillItem.setColor(color);
                result.add(newBillItem);
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            throw new KendoAdapterException("Producto no encontrado.");
        }
    }

    private Bill buildBill(Bill bill) throws KendoAdapterException {
        try {
            Bill newBill = new Bill();
            newBill.setSubTotal(bill.getSubTotal());
            newBill.setTotal(bill.getTotal());
            newBill.setSaleCondition(bill.getSaleCondition());
            newBill.setOrderNumber(bill.getOrderNumber());
            newBill.setPackages(bill.getPackages());
            newBill.setAddress(bill.getAddress());
            newBill.setBillNumber(bill.getBillNumber());
            newBill.setDate(bill.getDate());
            newBill.setIvaCategory(bill.getIvaCategory());
            newBill.setIvaTax(bill.getIvaTax());
            newBill.setClient(clientService.findClientById(bill.getClientId()));
            newBill.setCuit(bill.getCuit());
            newBill.setBillType(bill.getBillType());
            return newBill;
        }catch (Exception e){
            e.printStackTrace();
            throw new KendoAdapterException("Cliente inextistente");
        }

    }

}
