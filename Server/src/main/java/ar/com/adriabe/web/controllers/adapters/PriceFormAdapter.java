package ar.com.adriabe.web.controllers.adapters;

import ar.com.adriabe.model.*;
import ar.com.adriabe.model.constant.COLOR_TYPE;
import ar.com.adriabe.web.model.forms.ListPriceForm;
import ar.com.adriabe.web.model.forms.PriceForm;

import java.util.*;

/**
 * Created by Mildo on 12/3/14.
 */
public class PriceFormAdapter {

    public List<PriceForm> buildPriceFormListFromItemPriceList(List<ListPriceItem> prices) throws KendoAdapterException {
        ArrayList<PriceForm> result = new ArrayList<PriceForm>();
        PriceForm priceForm;
        List<Double> list;
        Long stripeId=0l;
        String nameAux;
        Map<String, List<Double>> priceMap = new HashMap<String, List<Double>>();
        Map<String, Map<String, Long>> productFamilyMap = new HashMap<String, Map<String, Long>>();
        Map<String, Long> familyMap;
        Fabric fabric = new Fabric();
        Stripe stripe = new Stripe();
        ProductFamily productFamily;
        try {
            if (prices == null || prices.isEmpty()) {
                return result;
            }

            for (ListPriceItem price : prices) {
                nameAux = price.getProductFamily().getName();

                if (priceMap.containsKey(nameAux)) {
                    list = priceMap.get(nameAux);
                    list.add(price.getPrice());
                } else {
                    list = new ArrayList<Double>();
                    list.add(price.getPrice());
                    priceMap.put(nameAux, list);
                    productFamily = price.getProductFamily();
                    if (productFamily != null ) {
                        fabric = productFamily.getFabric();
                        stripe = productFamily.getStripe();
                    }
                    familyMap = new HashMap<String, Long>();
                    familyMap.put("fabric-id", fabric.getId());
                    stripeId = (stripe == null ) ? 0:stripe.getId();
                    familyMap.put("stripe-id", stripeId );

                    productFamilyMap.put(nameAux, familyMap);
                }
            }

            for (String name : priceMap.keySet()) {
                priceForm = new PriceForm();
                priceForm.setName(name);
                list = priceMap.get(name);
                familyMap = productFamilyMap.get(name);
                Collections.sort(list);
                priceForm.setPriceWhite(list.get(0));
                priceForm.setPriceLight(list.get(1));
                priceForm.setPriceDark(list.get(2));
                priceForm.setPriceSpecial(list.get(3));
                priceForm.setFabric(familyMap.get("fabric-id"));
                priceForm.setStripe(familyMap.get("stripe-id"));
                result.add(priceForm);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new KendoAdapterException("Error al generar el listado.");
        }
    }

    public void  buildListPriceItemsListFromListPriceForm(ListPriceForm newList, ListPrice list) throws KendoAdapterException {

        if (newList == null || newList.getList() == null || newList.getList().isEmpty()) {
            return ;
        }

        Stripe stripe;
        Fabric fabric;
        List<String> violations = new ArrayList<String>();
        for (PriceForm priceForm : newList.getList()) {
            for (ListPriceItem listPriceItem : list.getPrices()) {
                stripe = listPriceItem.getProductFamily().getStripe();
                fabric = listPriceItem.getProductFamily().getFabric();
                if (sameFabric(fabric, priceForm) && sameStripe(stripe, priceForm)) {
                     updatePriceListItem(listPriceItem, priceForm,violations);
                }
            }
        }

        if(!violations.isEmpty()){
            throw new KendoAdapterException(violations);
        }
    }

    private ListPriceItem updatePriceListItem(ListPriceItem listPriceItem, PriceForm priceForm,List<String> violations) {
        COLOR_TYPE colorType = listPriceItem.getProductFamily().getColorType();
        boolean isValidPrice=true;
        switch (colorType) {
            case BLANCO:
                isValidPrice = isValidPrice && priceForm.getPriceWhite() <= priceForm.getPriceLight();
                isValidPrice = isValidPrice && priceForm.getPriceWhite() <= priceForm.getPriceDark();
                isValidPrice = isValidPrice && priceForm.getPriceWhite() <= priceForm.getPriceSpecial();

                listPriceItem.setPrice(priceForm.getPriceWhite());
                break;
            case CLARO:
                isValidPrice = isValidPrice && priceForm.getPriceLight() <= priceForm.getPriceDark();
                isValidPrice = isValidPrice && priceForm.getPriceLight() <= priceForm.getPriceSpecial();
                listPriceItem.setPrice(priceForm.getPriceLight());
                break;
            case OSCURO:
                isValidPrice = isValidPrice && priceForm.getPriceDark() <= priceForm.getPriceSpecial();
                listPriceItem.setPrice(priceForm.getPriceDark());
                break;
            case ESPECIAL:
                listPriceItem.setPrice(priceForm.getPriceSpecial());
                break;
        }
        if (!isValidPrice){
            String str = listPriceItem.getProductFamily().toString();
            violations.add("Precio de " + str + " es invalido.");
        }

        return listPriceItem;
    }

    /**
     * �
     * To make code more clear the condition was extracted to a private method  to make it more readable.
     */
    private boolean sameStripe(Stripe stripe, PriceForm priceForm) {
        boolean nonStripeFamily = stripe == null && priceForm.getStripe() == 0;
        boolean stripeFamily = true;
        if(stripe!=null){
            stripeFamily = stripe.getId() == priceForm.getStripe();
        }

        return nonStripeFamily || stripeFamily;
    }

    /**
     * �
     * To make code more clear the condition was extracted to a private method  to make it more readable.
     */
    private boolean sameFabric(Fabric fabric, PriceForm priceForm) {
        return fabric.getId() == priceForm.getFabric();
    }


}
