package ar.com.adriabe.model.constant;

public enum ACCOUNTABLE_ENTITY {
	CLIENT("client",1),
    SUPPLIER("supplier",2);

	private final String label;   // in kilograms
    private final int value; // in meters

    ACCOUNTABLE_ENTITY(String l, int v) {
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


    public static ACCOUNTABLE_ENTITY getFromLabel(String accountableEntity) {
        ACCOUNTABLE_ENTITY[] entities = ACCOUNTABLE_ENTITY.values();
        for (int i = 0; i < entities.length; i++) {
            if(entities[i].getLabel().compareTo(accountableEntity)==0){
                return entities[i];
            }
        }
        return null;
    }
}
