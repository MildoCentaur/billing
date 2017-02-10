package ar.com.adriabe.model.constant;

/**
 * Created by Mildo on 6/21/14.
 */
public enum SALE_CONDITION {

    Contado("Contado",1);

    private final String label;   // in kilograms
    private final int value; // in meters

    SALE_CONDITION(String l, int v) {
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
