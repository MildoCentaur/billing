package ar.com.adriabe.model.constant;

/**
 * Created by Mildo on 11/14/14.
 */
public enum ORDER_ITEM_STATUS {
    ORDERED("Pedido", 1), PARTIALLY_PREPARED("Parcialmente Preparado", 2), PREPARED("Preparado", 3), PARTIALLY_DELIVERED("Parcialmente entregado", 4), DELIVERED("Entregado", 5);


    private final String label;
    private final int value;

    ORDER_ITEM_STATUS(String l, int v) {
        this.label = l;
        this.value = v;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }
}
