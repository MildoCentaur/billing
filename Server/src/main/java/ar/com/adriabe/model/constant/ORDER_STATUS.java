package ar.com.adriabe.model.constant;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "status")
public enum ORDER_STATUS {

    WAITING("Pendiente", 1), // cola de preparacion
    PRODUCTION("En Producción", 2),
    WORKING("Preparandose", 3),
    DONE("Preparado", 4),
    PRINTED("Ticket Impreso", 5),
    DEBITED("Debitado", 6),
    PARTIALLY_DELIVERED("Listo para Entregar", 7),
    DELIVERED("Entregado", 8);

    private final String label;
    private final int value;

    ORDER_STATUS(String l, int v) {
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
