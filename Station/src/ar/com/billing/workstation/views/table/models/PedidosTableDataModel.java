/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.views.table.models;


import ar.com.billing.workstation.model.Order;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mildo
 */
public class PedidosTableDataModel extends AbstractTableModel{

    private List<Order> orders;
    
    public static int COLUMN_AMOUNT = 4;
    public static String COLUMN0_TITLE = "Nº";
    public static String COLUMN1_TITLE = "Cliente";
    public static String COLUMN2_TITLE = "Rollos";
    public static String COLUMN3_TITLE = "Estado";
    public static String COLUMN4_TITLE = "Observaciones";
    private String[] columnNames = {"Nº","Cliente","Rollos","Estado","Observaciones"}; 
   
    public PedidosTableDataModel(List<Order> orders) {
        this.orders = orders;
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
        if(orders==null){
            return 0;
        }
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_AMOUNT;
    }
    public Order getOrderFromRow(int rowIndex){
        return orders.get(rowIndex);
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex > orders.size()){
            return "";
        }
        if(columnIndex > COLUMN_AMOUNT){
            return "";
        }
        Order order = orders.get(rowIndex);
        switch (columnIndex){
            case 0:return order.getId();
            case 1:return order.getClientName();
            case 2:return order.getTotalPrepearedPackages()+ "/" + order.getTotalOrderedPackages();
            case 3:
                return order.getStatus();
        }
        return "";
    }
    
    /**
     * @return the orders
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * @param orders the orders to set
     */
    public void setOrders(List<Order> orders) {
//        this.orders.clear();
        this.orders = orders;
        
        fireTableDataChanged();
    }
    
}
