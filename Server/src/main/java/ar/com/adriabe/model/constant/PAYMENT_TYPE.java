package ar.com.adriabe.model.constant;

public enum PAYMENT_TYPE {
	
	MOVIMIENTO("Movimiento",1),
	PAGO("Pago",2);
	
	private final String label;   
    private final int value; 
    PAYMENT_TYPE (String l, int v) {
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
