package ar.com.billing.workstation.model.constant;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "status")
public enum ORDER_STATUS {

	WAITING("Pendiente",1), // cola de preparacion
	WORKING("Preparandose",2),
	DONE("Preparado",3),
	PRINTED("Ticket Impreso",4),
    DEBITED("Debitado",5),
    DELIVERED("Entregado",6);
	
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
