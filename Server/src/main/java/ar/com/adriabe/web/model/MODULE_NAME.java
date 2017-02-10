package ar.com.adriabe.web.model;

/**
 * Created by Mildo on 6/13/14.
 */
public enum MODULE_NAME {

    MODULE_HOME("Inicio",0),
    MODULE_CLIENT("Clientes",1),
    MODULE_SUPPLIER("Proveedores",2),
    MODULE_ORDER("Pedidos",3),
    MODULE_CASHIER("Pedidos",4),
    MODULE_BILLING("Facturación",5),
    MODULE_FABRICA("Producción",6),
    MODULE_REPORTS("Reportes",7),
    MODULE_PRODUCTOS("Productos",8),
    MODULE_LOGS("Logs",9),
    MODULE_SETTINGS("Configuraciones",10),
    MODULE_LOGIN("Login",20), TAXATION("Impuestos", 11);

    private final String label;   // in kilograms
    private final int value; // in meters

    MODULE_NAME(String l, int v) {
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
