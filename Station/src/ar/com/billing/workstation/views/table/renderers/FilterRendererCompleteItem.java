/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.views.table.renderers;


import ar.com.billing.workstation.model.Order;
import ar.com.billing.workstation.model.OrderItem;
import ar.com.billing.workstation.model.Product;
import ar.com.billing.workstation.views.table.models.PrepararPedidoTableDataModel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Mildo
 */
public class FilterRendererCompleteItem extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        String ratio = (String) table.getValueAt(row, 2);
        int prep = 0, order = 0;
        if (isSelected){
            return this;
        }
        if(ratio.matches("(.*)/(.*)")){
            prep = Integer.parseInt(ratio.split("/")[0]);
            order = Integer.parseInt(ratio.split("/")[1]);
            if (prep == order) {
                setOpaque(true);
                setBackground(Color.GREEN);
            } else if (prep >= order * 0.5) {
                setBackground(Color.YELLOW);
                setOpaque(true);
            } else {
                setBackground(Color.RED);
                setOpaque(true);
            }
            
        }else{
            PrepararPedidoTableDataModel model = (PrepararPedidoTableDataModel)table.getModel();
            if(ratio.charAt(0)=='0'){
                setBackground(Color.RED);
                setOpaque(true);
            }else{
                setOpaque(true);
                setBackground(Color.GREEN);
            }
        }
        
        return this;
    }
}