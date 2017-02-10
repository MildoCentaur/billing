/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.synch;


import ar.com.billing.workstation.dao.ColorDao;
import ar.com.billing.workstation.dao.FabricDao;
import ar.com.billing.workstation.dao.OrderDao;
import ar.com.billing.workstation.dao.StripeDao;
import ar.com.billing.workstation.model.Color;
import ar.com.billing.workstation.model.Fabric;
import ar.com.billing.workstation.model.Order;
import ar.com.billing.workstation.model.OrderItem;
import ar.com.billing.workstation.model.OrderItemDetail;
import ar.com.billing.workstation.model.Product;
import ar.com.billing.workstation.model.Stripe;
import ar.com.billing.workstation.model.StripeCombination;
import ar.com.billing.workstation.services.CommunicationService;
import ar.com.billing.workstation.services.CommunicationServiceImpl;
import ar.com.billing.workstation.views.MainWindow;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Mildo
 */
public class SynchWorker extends SwingWorker<Integer, Map<String, JSONArray>> {

    private boolean force;
    private CommunicationService comm;

    public SynchWorker(boolean force) {
        this.force = force;
        
        this.comm = new CommunicationServiceImpl();

    }

    @Override
    protected Integer doInBackground() throws Exception  {
        try {
            setProgress(0);
            Map<String, JSONArray> map = new HashMap<String, JSONArray>();
            
            if(force){
                JSONArray fabricsJsonObject = comm.findAllFabrics();
                JSONArray colorsJsonObject = comm.findAllColors();
                JSONArray stripesJsonObject = comm.findAllStripes();
                map.put("fabrics", fabricsJsonObject);
                map.put("colors", colorsJsonObject);
                map.put("stripes", stripesJsonObject);
            }
            JSONArray ordersJsonObject = comm.findAllPendingOrders();
            
            map.put("orders",ordersJsonObject);
            publish(map);
            setProgress(100);
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(SynchWorker.class.getName()).log(Level.SEVERE, null, ex);
            MainWindow.errorMsg("Error de comunicación con el servidor.");
            throw new Exception(ex.getMessage());
        }

    }

    @Override
    protected void process(List<Map<String, JSONArray>> maps) {
        JSONArray jsonArray;
        JSONObject jsonItem;
        if (maps == null || maps.isEmpty()){
            return ;
        }
        Map<String, JSONArray> map = maps.get(0);
        JSONArray fabrics,stripes,colors,orders;
        if(force){   
           fabrics = map.get("fabrics");
           stripes= map.get("stripes");
           colors = map.get("colors");

           for(int i=0; i<fabrics.length();i++){
               parseFabric(fabrics.getJSONObject(i));
           }
           for(int i=0; i<stripes.length();i++){
               parseStripe(stripes.getJSONObject(i));
           }
           for(int i=0; i<colors.length();i++){
               parseColor(colors.getJSONObject(i));
           }
       }
        orders = map.get("orders");
        for(int i=0; i<orders.length();i++){
            parseOrder(orders.getJSONObject(i));
        }
        
        MainWindow.getInstance().refreshDataFromSynch();
        
    }

    private void parseOrder(JSONObject jsonOrder) {
        try{
            Order order = new Order(jsonOrder.getLong("id"));
            order.setClientName(jsonOrder.getString("clientName"));
            order.setPriority(jsonOrder.getInt("priority"));
            order.setStatus(jsonOrder.getString("status"));
            JSONArray jsonItems =  jsonOrder.getJSONArray("items");
            List<OrderItem> items = new ArrayList<OrderItem>();
            List<OrderItem> accesories = new ArrayList<OrderItem>();
            OrderItem item;
            JSONObject jsonItem,jsonProduct,jsonAccesoryItem;
            Product product;
            for(int i=0; i<jsonItems.length();i++){
                jsonItem = (JSONObject) jsonOrder.getJSONArray("items").getJSONObject(i);
                item = parseOrderItem(jsonItem,false);
                items.add(item);
                items.addAll(item.getAccesories());
            }
            order.setItems(items);
            OrderDao.addOrder(order);
            
        }catch(Exception e){
            e.printStackTrace();
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "No se pudo generar el pedido");
        }
        
        
    }
    private OrderItem parseOrderItem(JSONObject jsonItem, boolean isAccesory) throws JSONException {
        JSONArray jsonAccesoriesItems,jsonDetailItems;
        List<OrderItem> accesories=new ArrayList<OrderItem>();
        List<OrderItemDetail> dateils=new ArrayList<OrderItemDetail>();
        OrderItem accesory;
        OrderItem item;
        OrderItemDetail detail;
        JSONObject jsonProduct;
        Product product;
        JSONObject jsonAccesoryItem,jsonDetailItem;
        
        
        item = new OrderItem(jsonItem.getLong("id"));
        jsonProduct = jsonItem.getJSONObject("product");
        product = new Product(jsonProduct.getLong("id"));
        if(isAccesory){
            product.setProductName("--->>" + jsonProduct.getString("productName"));
        }else{
            product.setProductName(jsonProduct.getString("productName"));
        }
        
        product.setProductFamilyId(jsonProduct.getLong("productFamilyId"));
        product.setColorId(jsonProduct.getLong("colorId"));
        product.setStripeId(jsonProduct.getLong("stripeId"));
        product.setAccesory(isAccesory);
        
        product.setStripeCombinationIndex(jsonProduct.getLong("stripeCombinationIndex"));
        item.setProduct(product);
        item.setQuantity(jsonItem.getDouble("quantity"));
        item.setStatus(jsonItem.getString("status"));
        if(!jsonItem.isNull("packages")){
            jsonDetailItems = jsonItem.getJSONArray("packages");
            for(int h=0; h<jsonDetailItems.length();h++){
                jsonDetailItem = (JSONObject) jsonItem.getJSONArray("packages").getJSONObject(h);
                detail = new OrderItemDetail();
                detail.setId(jsonDetailItem.getLong("id"));
                detail.setBarcode(jsonDetailItem.getString("barcode"));
                detail.setStatus(jsonDetailItem.getString("status"));
                detail.setWeight(jsonDetailItem.getDouble("weight"));
                dateils.add(detail);
            }
            item.setPackages(dateils);
        }
        if(!jsonItem.isNull("accesories")){
            jsonAccesoriesItems = jsonItem.getJSONArray("accesories");
            for(int j=0; j<jsonAccesoriesItems.length();j++){
                jsonAccesoryItem = (JSONObject) jsonItem.getJSONArray("accesories").getJSONObject(j);
                accesory = parseOrderItem(jsonAccesoryItem,true);
                accesories.add(accesory);
            }
        }
        
        item.setAccesories(accesories);
        return item;
    }
    private Color parseColor(JSONObject jsonColor) {
        try{
            Color color = new Color(jsonColor.getLong("id"));
            color.setCode(jsonColor.getString("code"));
            color.setName(jsonColor.getString("name"));
            color.setMethod(jsonColor.getString("method"));
            color.setCoordinate(jsonColor.getString("coordinate"));
            color.setType(jsonColor.getString("type"));
            ColorDao.addColor(color);
            return color;
        }catch(Exception e){
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "No se pudo generar el color");
        }
        return null;
        
    }

    private void parseFabric(JSONObject jsonFabric) {
        try{
            Fabric fabric = new Fabric(jsonFabric.getLong("id"));
            fabric.setCode(jsonFabric.getString("code"));
            fabric.setCuello(jsonFabric.getBoolean("cuello"));
            fabric.setMercerizado(jsonFabric.getBoolean("mercerizado"));
            fabric.setPuno(jsonFabric.getBoolean("puno"));
            fabric.setShortname(jsonFabric.getString("shortname"));
            FabricDao.addFabric(fabric);
        }catch(Exception e){
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "No se pudo generar el tejido");
        }
        
    }
    
    private void parseStripe(JSONObject jsonStripe) {
        try{
            Stripe stripe = new Stripe(jsonStripe.getLong("id"));
            stripe.setCode(jsonStripe.getString("code"));
            stripe.setColors(jsonStripe.getInt("colors"));
            stripe.setListingFormula(jsonStripe.getString("listingFormula"));
            stripe.setName(jsonStripe.getString("name"));
            StripeCombination comb;
            JSONObject obj;
            List<StripeCombination> combinations= new ArrayList<StripeCombination>();
            for(int i=0; i<jsonStripe.getJSONArray("combinations").length();i++){
                obj = (JSONObject) jsonStripe.getJSONArray("combinations").getJSONObject(i);
                comb = new StripeCombination();
                comb.setId(obj.getLong("id"));
                comb.setName(obj.getString("name"));
                comb.setColorFingerA(obj.getString("colorFingerA"));
                comb.setColorFingerB(obj.getString("colorFingerB"));
                comb.setColorFingerC(obj.getString("colorFingerC"));
                comb.setColorFingerD(obj.getString("colorFingerD"));
                comb.setColorFingerE(obj.getString("colorFingerE"));
                comb.setColorFingerF(obj.getString("colorFingerF"));
                combinations.add(comb);
                
            }

            stripe.setCombinations(combinations);
            StripeDao.addStripe(stripe);
        }catch(Exception e){
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "No se pudo generar el patron de rayado");
        }
    }

    
    
    
}
