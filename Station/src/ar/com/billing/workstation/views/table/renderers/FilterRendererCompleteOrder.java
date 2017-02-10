/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.views.table.renderers;

import ar.com.billing.workstation.model.constant.ORDER_STATUS;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Mildo
 */
public class FilterRendererCompleteOrder extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
        String status = (String) table.getValueAt(row, 3);
        // You'll need some way to supply the filter value, may via a centralised 
        // manager of some kind.
        if (status.compareToIgnoreCase(ORDER_STATUS.DONE.getLabel())==0 ) {
            setOpaque(true);
            setBackground(Color.green);
            setForeground(Color.BLACK);
        } else {
            setForeground(Color.BLACK);
            setBackground(Color.white);
            setOpaque(true);
        }
        return this;
    }
}