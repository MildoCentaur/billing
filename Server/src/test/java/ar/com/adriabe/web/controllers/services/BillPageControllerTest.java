package ar.com.adriabe.web.controllers.services;

import ar.com.adriabe.model.*;
import ar.com.adriabe.model.constant.IVA_TYPE;
import ar.com.adriabe.model.constant.SALE_CONDITION;
import ar.com.adriabe.services.BillingService;
import ar.com.adriabe.web.controllers.adapters.BillFormAdapter;
import ar.com.adriabe.web.controllers.page.BillingPageController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mildo on 1/31/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/application-context.xml" })
public class BillPageControllerTest {

        @Autowired
        BillFormAdapter billFormAdapter;

        @Autowired
        BillingService billingService;

        @Autowired
        BillingPageController billingPageController;


        @Test
        public void testSaveBillFromBillForm() throws Exception {
            Bill aBill = new Bill();
            aBill.setClient(new Client(1l));

            aBill.setBillType("A");
            aBill.setAddress("DUMMY_ADDRESS");
            aBill.setBillNumber(10l);
            aBill.setDate(new Date());
            aBill.setIvaCategory(IVA_TYPE.MNT);
            aBill.setIvaTax(100.0);
            aBill.setOrderNumber(10l);
            aBill.setCuit("5432142354323432");
            aBill.setSaleCondition(SALE_CONDITION.Contado);
            aBill.setTotal(3123.0);
            aBill.setSubTotal(3456.0);
            List<BillItem> items = new ArrayList<BillItem>();
            BillItem bi = new BillItem();
            ProductFamily productFamily = new ProductFamily();
            productFamily.setId(5l);
            Color color= new Color(1l);
            bi.setColor(color);
            bi.setProductFamily(productFamily);
            bi.setAmount(10.0);
            bi.setPrice(100.0);
            bi.setSubtotal(10000.0);
            items.add(bi);

            aBill.setItems(items);

            aBill = billFormAdapter.adapter(aBill);
//
            //billingService.save(aBill);

            //billingPageController.processNewOrder(aBill,null,null);

            billingService.printBill(aBill);

        }

    }