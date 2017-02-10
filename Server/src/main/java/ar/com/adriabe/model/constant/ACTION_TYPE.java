package ar.com.adriabe.model.constant;

public enum ACTION_TYPE {

	CREATE("Crear",1),
	READ("Consultar",2),
	UPDATE("Modificar",3),
	DELETE("Borrar",4),
    PRINT("Imprimir",5);
	
	private final String label;   // in kilograms
    private final int value; // in meters
    
    ACTION_TYPE(String l, int v) {
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
	
	static public ACTION_TYPE getActionTypeFromString(String str){
		str=str.toLowerCase();
		if(str.equalsIgnoreCase("read") || str.equalsIgnoreCase("find") || str.equalsIgnoreCase("get")){
			return READ;
		}
		if(str.equalsIgnoreCase("save")){
			return CREATE;
		}
		if(str.equalsIgnoreCase("saveOrUpdate")){
			return UPDATE;
		}
		if(str.equalsIgnoreCase("DELETE")){
			return DELETE;
		}
		return null;
	}
}
