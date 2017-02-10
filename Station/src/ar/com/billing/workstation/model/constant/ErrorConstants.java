/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ar.com.billing.workstation.model.constant;

/**
 *
 * @author Administrador
 */
public enum ErrorConstants {
    NO_ERROR("La operación se realizó con éxito.",0),
    ITEM_COMPLETE("El item esta completo",1), // cola de preparacion
    REGISTER_ERROR("Error al registrar el rollo.",2),
    PRODUCT_NOT_ASKED("Producto no pedido.",3),
    GENERAL_ERROR("Error de comunicación.",4), 
    BARCODE_ERROR("Error de lectura del código de barras",5), 
    DUPLICATE_BARCODE("El rollo pertenece a otro pedido.",6);
	
	private final String label;    
    private final int value; 
    
    ErrorConstants(String l, int v) {
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
        public static ErrorConstants getFromValue(int value){
            switch(value){
                case 1:
                       return ITEM_COMPLETE;
                case 2:
                        return REGISTER_ERROR;
                case 3:
                        return PRODUCT_NOT_ASKED;
                case 0: 
                        return NO_ERROR;
                case 4: 
                        return GENERAL_ERROR;
            }
            return GENERAL_ERROR;
        } 

	
}
