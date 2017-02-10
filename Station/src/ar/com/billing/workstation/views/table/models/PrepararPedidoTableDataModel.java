/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.views.table.models;

import ar.com.billing.workstation.model.Order;
import ar.com.billing.workstation.model.OrderItem;
import ar.com.billing.workstation.model.Product;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mildo
 */
public class PrepararPedidoTableDataModel extends AbstractTableModel{

    private Order  aOrder;
    
    public static int COLUMN_AMOUNT = 3;
    public static String COLUMN0_TITLE = "Nº";
    public static String COLUMN1_TITLE = "Producto";
    public static String COLUMN2_TITLE = "Cantidad";
    
    private String[] columnNames = {"Nº","Producto","Cantidad"}; 
   
    public PrepararPedidoTableDataModel(Order aOrder) {
        this.aOrder = aOrder;
    }
    
    public String getColumnName(int column) {
         return columnNames[column];
    }
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    public Class getColumnClass(int column) {
        return String.class;
    }
    @Override
    public int getRowCount() {
        if(aOrder==null){
            return 0;
        }
        return aOrder.getItems().size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_AMOUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(aOrder==null){
            return "";
        }
        if(rowIndex > aOrder.getItems().size()){
            return "";
        }
        if(columnIndex > COLUMN_AMOUNT){
            return "";
        }
        String aux = "";
        String fullNameAccesorio="";
        OrderItem orderItem = aOrder.getItems().get(rowIndex);
        switch (columnIndex){
            case 0:return rowIndex+1;
            case 1:return orderItem.getProduct().getProductName();
            case 2:
                
                Product p = orderItem.getProduct();
                if (p.getProductName().matches("--->>(.*)")){
                    StringBuffer buffer = new StringBuffer();
                    buffer = buffer.append(orderItem.getOrderItemDetailsSize() + " Paquetes. ");
                    buffer = buffer.append(((int)orderItem.getQuantity()));
                    buffer = buffer.append(" por rollo de tela.");
                    return  buffer.toString();
                }
                return orderItem.getOrderItemDetailsSize()+ "/" +  ((int)orderItem.getQuantity()) ;
            
        }
        return "";
    }
    
    /**
     * @return the orders
     */
    public Order getOrder() {
        return aOrder;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrder(Order aOrder) {
        
        this.aOrder = aOrder;
        fireTableDataChanged();
    }
    
}