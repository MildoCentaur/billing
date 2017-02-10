package ar.com.adriabe.model.constant;

/**
 * Created by AJMILD1 on 25/06/14.
 */
public enum TRANSFER_TYPE {

    INCOMING("Ingreso",1),
    OUTCOMING("Egreso",-1);

    private final String label;
    private final int value;
    TRANSFER_TYPE (String l, int v) {
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
