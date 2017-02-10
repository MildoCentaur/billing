package ar.com.adriabe.model.constant;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "status")
public enum DELIVERY_ORDER_STATUS {

    WAITING("Pendiente", 1), // cola de preparacion
    DELIVERED("Entregado", 2),
    CANCELLED("Cancelado", 3);;

    private final String label;
    private final int value;

    DELIVERY_ORDER_STATUS(String l, int v) {
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
