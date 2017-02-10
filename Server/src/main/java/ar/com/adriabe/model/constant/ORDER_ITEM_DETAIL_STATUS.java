package ar.com.adriabe.model.constant;

/**
 * Created by Mildo on 11/14/14.
 */
public enum ORDER_ITEM_DETAIL_STATUS {
    PREPEARED("Preparado", 1), DELIVERED("Entregado", 2), READY_TO_DELIVER("Listo para Entregar", 3), RETURNED("Devuelto", 4);

    private final String label;
    private final int value;

    ORDER_ITEM_DETAIL_STATUS(String l, int v) {
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
