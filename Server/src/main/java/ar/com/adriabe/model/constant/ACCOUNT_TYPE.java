package ar.com.adriabe.model.constant;

public enum ACCOUNT_TYPE {
	ACCOUNT("Cuenta Corriente",1),
    MOVEMENT("Moviento",2);

	private final String label;   // in kilograms
    private final int value; // in meters

    ACCOUNT_TYPE(String l, int v) {
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

    public static ACCOUNT_TYPE getByOrdinal(int ordinal) {

        return (ordinal==1)? ACCOUNT_TYPE.ACCOUNT:ACCOUNT_TYPE.MOVEMENT;
    }
	

}
