package ar.com.adriabe.model.constant;

public enum ACCOUNTABLE_DOCUMENT {
    // 0 < significa que restan
// 0 > significa que aumentan
    CLIENT_PAYMENT("Su pago", -1),
    RECEIPT("Su pago Nº", -2),
    SUPPLIER_PAYMENT("Pago a proveedores", -3),
    CREDIT_NOTE("Nota de credíto", -4),
    ORDER_DELIVERY("Orden de entrega Nº:", 1),
    BILL("Factura Nº:", 2),
    DEBIT_NOTE("Nota de debíto", 3),
    EMPTY("", 0);

    private final String label;   // in kilograms
    private final int value; // in meters

    ACCOUNTABLE_DOCUMENT(String l, int v) {
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
