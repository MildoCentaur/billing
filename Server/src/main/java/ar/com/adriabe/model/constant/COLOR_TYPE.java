package ar.com.adriabe.model.constant;

public enum COLOR_TYPE {

	BLANCO(1,"Blanco"),
	CLARO(2,"Claro"),
	OSCURO(3,"Oscuro"),
	ESPECIAL(4,"Especial");
	
	private  int value; // in meters
	private  String label;   // in kilograms
    
    COLOR_TYPE(int v, String l) {
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
	
	public static COLOR_TYPE getColorType(int num){
		if(num==1){
			return BLANCO;
		}else if(num==2){
			return CLARO;
		}else if(num==3){
			return OSCURO;
		}else if(num==4){
			return ESPECIAL;
		}
		return null; 
	}
	COLOR_TYPE (int num){
		if(num==1){
			this.label = "Blanco";
	        this.value = 1;
		}else if(num==2){
			this.label = "Claro";
	        this.value = 2;
	    }else if(num==3){
			this.label = "Oscuro";
	        this.value = 3;
		}else if(num==4){ 
			this.label = "Especial";
	        this.value = 4;
		}
	}
	
}