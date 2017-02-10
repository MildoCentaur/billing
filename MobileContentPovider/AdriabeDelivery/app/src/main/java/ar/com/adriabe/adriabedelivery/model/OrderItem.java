package ar.com.adriabe.adriabedelivery.model;

import java.util.ArrayList;
import java.util.List;

public class OrderItem {

    protected double askedRolls;
    protected double deliveredRolls;
    protected double preparedRolls;
    protected String product;
    private long id;
    protected List<OrderItemDetail> rolls;

    /**
     * Method 'OrderDetalle'
     *
     */
    public OrderItem() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }


    /**
     * Method 'getKilo'
     *
     * @return double
     */
    public double getWeight() {
        double rta = 0;
        for (OrderItemDetail oid : rolls) {
            rta = rta + oid.getWeight();
        }
        return rta;
    }
    public int getRemaining() {
        int rta = 0;
        for (OrderItemDetail oid : rolls) {
            rta = rta + (oid.isDelivered() ? 0:1);
        }
        return rta;
    }
    
    /**
     * Method 'getProducts'
     *
     * @return Product
     */
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * @return the items
     */
    public List<OrderItemDetail> getRolls() {
        return rolls;
    }

    /**
     * @param items the items to set
     */
    public void setRolls(List<OrderItemDetail> items) {
        this.rolls = items;
    }

    /**
     * @return the askedRolls
     */
    public double getAskedRolls() {
        return askedRolls;
    }

    /**
     * @param askedRolls the askedRolls to set
     */
    public void setAskedRolls(double askedRolls) {
        this.askedRolls = askedRolls;
    }

    /**
     * @return the deliveredRolls
     */
    public double getDeliveredRolls() {
        return deliveredRolls;
    }

    /**
     * @param deliveredRolls the deliveredRolls to set
     */
    public void setDeliveredRolls(double deliveredRolls) {
        this.deliveredRolls = deliveredRolls;
    }

    /**
     * @return the preparedRolls
     */
    public double getPreparedRolls() {
        return preparedRolls;
    }

    /**
     * @param preparedRolls the preparedRolls to set
     */
    public void setPreparedRolls(double preparedRolls) {
        this.preparedRolls = preparedRolls;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OrderItem other = (OrderItem) obj;
        if (Double.doubleToLongBits(askedRolls) != Double.doubleToLongBits(other.askedRolls)) {
            return false;
        }
        if (product == null) {
            if (other.product != null) {
                return false;
            }
        } else if (!product.equals(other.product)) {
            return false;
        } else if (this.getId() != other.getId()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderItem [product=" + product.toString() + ", askedRolls=" + askedRolls +" ]";
    }

    public void addOrderItemDetail(long id, boolean deliver,Double weight, String barcode) {
       if(rolls==null ){
           rolls = new ArrayList<OrderItemDetail>();
       }
       rolls.add(new OrderItemDetail(id,deliver,weight,barcode));
       preparedRolls++;
    }
    
    public String orderItemDeliveryLabel(){
    	String result="";
    	int cant=rolls.size();
    	String paquetes = (cant==1) ? " Paquete " : " Paquetes ";
    	result = cant+ paquetes + product;

    	return result;
    }

	public CharSequence orderItemDeliveryRatio() {
		String result="";
		int remaining = getRemaining();
		
		String paquetes = (remaining==1) ? " paquete " : " paquetes ";
    	String queda = "Pendiente de entrega ";
    	result = queda + remaining + paquetes+ "de " + rolls.size();
    	
    	return result;
	}
}
