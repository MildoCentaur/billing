/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.billing.workstation.views;

import ar.com.billing.workstation.controllers.EmbalajeController;
import ar.com.billing.workstation.dao.ColorDao;
import ar.com.billing.workstation.dao.FabricDao;
import ar.com.billing.workstation.dao.OrderDao;
import ar.com.billing.workstation.dao.StripeDao;
import ar.com.billing.workstation.model.Color;
import ar.com.billing.workstation.model.Fabric;
import ar.com.billing.workstation.model.Order;
import ar.com.billing.workstation.model.Product;
import ar.com.billing.workstation.model.Stripe;
import ar.com.billing.workstation.model.StripeCombination;
import ar.com.billing.workstation.views.table.models.PedidosTableDataModel;
import ar.com.billing.workstation.views.table.renderers.FilterRendererCompleteOrder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Mildo
 */
public class MainWindow extends javax.swing.JFrame {

    EmbalajeController embalajeController = new EmbalajeController();
    private static MainWindow mw = null;

    /**
     * Creates new form mainWindow
     */
    private MainWindow() {
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "Inicio de pantalla");
        initComponents();
        initialization();
        embalarActionPerfomed(null);
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, "FIN Inicio de pantalla");
    }

    public static MainWindow getInstance() {
        if (mw == null) {
            mw = new MainWindow();
        }

        return mw;
    }

    public static void successMsg(String msg) {
        MainWindow mw = MainWindow.getInstance();
        mw.getValidationMsgLabel().setText(msg);
        mw.getValidationMsgLabel().setForeground(java.awt.Color.BLUE);
        mw.getValidationMsgLabel().setVisible(true);
    }

    public static void errorMsg(String msg) {
        MainWindow mw = MainWindow.getInstance();
        mw.getValidationMsgLabel().setText(msg);
        mw.getValidationMsgLabel().setForeground(java.awt.Color.RED);
        mw.getValidationMsgLabel().setVisible(true);
    }
    public void refresh() {
         ((PedidosTableDataModel) pedidosTable.getModel()).setOrders(OrderDao.getOrders());

    }
    public void refreshDataFromSynch() {
        
        if(codeComboBox.getItemCount() >0){
            return;
        }    
        final List<Color> colors = ColorDao.getColors();
        final List<Fabric> fabrics = FabricDao.getFabrics();
        final List<Stripe> stripes = StripeDao.getStripes();
        codeComboBox.setEditable(true);
        colorComboBox.setEditable(true);
        stripeComboBox.setEditable(true);
        colorComboBox.removeAllItems();
        codeComboBox.removeAllItems();
        stripeComboBox.removeAllItems();
        
        for (Color color : colors) {
            colorComboBox.addItem(color);
        }
        for (Fabric fabric : fabrics) {
            codeComboBox.addItem(fabric);
        }
        Stripe blank = new Stripe();
        blank.setId(0l);
        blank.setName("Seleccione un rayado.");
        stripeComboBox.addItem(blank);
        for (Stripe stripe:stripes) {
            stripeComboBox.addItem(stripe);
        }
        
        stripeComboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                    String a = stripeComboBox.getEditor().getItem().toString().toLowerCase();
                    stripeComboBox.removeAllItems();
                    for (Stripe stripe : stripes) {
                        if (stripe.getName().toLowerCase().matches("(.*)" + a + "(.*)")) {
                            stripeComboBox.addItem(stripe);
                        }
                    }
                    stripeComboBox.getEditor().setItem(new String(a));
                    stripeComboBox.showPopup();
                    
                    
                    
                } else if (e.getKeyCode() == 10) {
                    stripeComboBox.getEditor().setItem(stripeComboBox.getSelectedItem());
                    
                }
            }
        });
        colorComboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                    String a = colorComboBox.getEditor().getItem().toString().toLowerCase();
                    colorComboBox.removeAllItems();
                    for (Color color : colors) {
                        if (color.getName().toLowerCase().matches("(.*)" + a + "(.*)")) {
                            colorComboBox.addItem(color);
                        }
                    }
                    colorComboBox.getEditor().setItem(new String(a));
                    colorComboBox.showPopup();
                    
                } else if (e.getKeyCode() == 10) {
                    colorComboBox.getEditor().setItem(colorComboBox.getSelectedItem());
                }
            }
        });
        codeComboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != 38 && e.getKeyCode() != 40 && e.getKeyCode() != 10) {
                    String a = codeComboBox.getEditor().getItem().toString();
                    codeComboBox.removeAllItems();

                    for (Fabric fabric : fabrics) {
                        if (fabric.getCode().matches("(.*)" + a + "(.*)")) {
                            codeComboBox.addItem(fabric);
                        }
                    }
                    codeComboBox.getEditor().setItem(new String(a));
                    codeComboBox.showPopup();
                    
                } else if (e.getKeyCode() == 10) {
                    codeComboBox.getEditor().setItem(codeComboBox.getSelectedItem());
                }

            }
        });
        ((PedidosTableDataModel) pedidosTable.getModel()).setOrders(OrderDao.getOrders());
        ((PedidosTableDataModel) pedidosTable.getModel()).fireTableDataChanged();
       
    }

    private void initialization() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fechatxt.setText(format.format(new Date()));
        clearAllElements();
        
        stripeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox source = (JComboBox) event.getSource();
                Object selected = source.getSelectedItem();
                if (selected != null) {
                    stripeCombinationComboBox.setEnabled(true);
                    Stripe stripe = (Stripe) stripeComboBox.getSelectedItem();
                    stripeCombinationComboBox.removeAllItems();
                    if(stripe.getCombinations()!=null){
                        for (StripeCombination comb :  stripe.getCombinations()){
                            stripeCombinationComboBox.addItem(comb);
                        }
                    }
                    
                }
            }
        });

        
        pedidosTable.setAutoCreateColumnsFromModel(false);


        pedidosTable.setShowHorizontalLines(true);
        pedidosTable.setRowSelectionAllowed(true);
        pedidosTable.setColumnSelectionAllowed(false);

        // Change the selection colour
        pedidosTable.setSelectionForeground(java.awt.Color.WHITE);
        pedidosTable.setSelectionBackground(java.awt.Color.DARK_GRAY);
        pedidosTable.setSurrendersFocusOnKeystroke(true);
        TableColumnModel pedidosColumnModel = pedidosTable.getColumnModel();
        pedidosColumnModel.getColumn(0).setCellRenderer(new FilterRendererCompleteOrder());
        pedidosColumnModel.getColumn(1).setCellRenderer(new FilterRendererCompleteOrder());
        pedidosColumnModel.getColumn(2).setCellRenderer(new FilterRendererCompleteOrder());
        pedidosColumnModel.getColumn(3).setCellRenderer(new FilterRendererCompleteOrder());
        pedidosTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    TableModel model = pedidosTable.getModel();
                    Order aOrder = ((PedidosTableDataModel) pedidosTable.getModel()).getOrderFromRow(row);
                    OrderWindow.getInstance().setOrder(aOrder);
                    OrderWindow.getInstance().setVisible(true);
                    MainWindow.getInstance().setVisible(false);
                    
                }
            }
        });
        
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(pedidosTable.getModel());
        sorter.setComparator(0, new Comparator<Long>() {

            @Override
            public int compare(Long o1, Long o2) {
                return o1.compareTo(o2);
            }
        });
        sorter.setComparator(1, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        sorter.setComparator(2, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
         sorter.setComparator(3, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                if(o1.compareToIgnoreCase("preparado")==0){
                    return 1;
                }else if(o2.compareToIgnoreCase("preparado")==0){
                    return -1;
                }
                return 0;
            }
        });
        pedidosTable.setRowSorter(sorter);
        
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group = new javax.swing.ButtonGroup();
        tituloLbl = new javax.swing.JLabel();
        addProductPanel = new javax.swing.JPanel();
        codeLabel = new javax.swing.JLabel();
        colorLabel = new javax.swing.JLabel();
        weightLabel = new javax.swing.JLabel();
        weightTxt = new javax.swing.JTextField();
        addProductButton = new javax.swing.JButton();
        codeLabel1 = new javax.swing.JLabel();
        partida = new javax.swing.JTextField();
        codeLabel2 = new javax.swing.JLabel();
        fechatxt = new javax.swing.JTextField();
        codeComboBox = new javax.swing.JComboBox();
        colorComboBox = new javax.swing.JComboBox();
        validationMsgLabel = new javax.swing.JLabel();
        stripeComboBox = new javax.swing.JComboBox();
        colorLabel1 = new javax.swing.JLabel();
        colorLabel2 = new javax.swing.JLabel();
        stripeCombinationComboBox = new javax.swing.JComboBox();
        pedidoPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pedidosTable = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        embalajeMenu = new javax.swing.JMenu();
        jmiEmbalar = new javax.swing.JMenuItem();
        pedidosMenu = new javax.swing.JMenu();
        jmiPendientes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Preparar pedidos");
        setPreferredSize(new java.awt.Dimension(750, 550));
        setResizable(false);

        tituloLbl.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N

        addProductPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        addProductPanel.setName("addProductPanel"); // NOI18N
        addProductPanel.setPreferredSize(new java.awt.Dimension(710, 345));

        codeLabel.setText("Artículo:"); // NOI18N

        colorLabel.setText("Color:"); // NOI18N

        weightLabel.setText("Peso:"); // NOI18N

        addProductButton.setBackground(new java.awt.Color(51, 153, 0));
        addProductButton.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        addProductButton.setText("Agregar Producto");
        addProductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProductButtonActionPerformed(evt);
            }
        });

        codeLabel1.setText("Partida:"); // NOI18N

        partida.setText("000");
        partida.setName("partidatxt"); // NOI18N

        codeLabel2.setText("Fecha:"); // NOI18N

        fechatxt.setEditable(false);
        fechatxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fechatxtvalidateCode(evt);
            }
        });

        codeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                codeComboBoxActionPerformed(evt);
            }
        });

        validationMsgLabel.setForeground(new java.awt.Color(204, 0, 0));
        validationMsgLabel.setText("eqweqweqweqeqeqwe");

        stripeComboBox.setActionCommand("");
        stripeComboBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                stripeComboBoxPropertyChange(evt);
            }
        });

        colorLabel1.setText("Rayado:"); // NOI18N

        colorLabel2.setText("Combinación:"); // NOI18N

        org.jdesktop.layout.GroupLayout addProductPanelLayout = new org.jdesktop.layout.GroupLayout(addProductPanel);
        addProductPanel.setLayout(addProductPanelLayout);
        addProductPanelLayout.setHorizontalGroup(
            addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(addProductPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(addProductPanelLayout.createSequentialGroup()
                        .add(0, 0, Short.MAX_VALUE)
                        .add(addProductButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 177, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(validationMsgLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, addProductPanelLayout.createSequentialGroup()
                        .add(38, 38, 38)
                        .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, colorLabel)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, colorLabel1)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, codeLabel))
                        .add(18, 18, 18)
                        .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(colorComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(stripeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(codeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 207, Short.MAX_VALUE)
                        .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, codeLabel2)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, codeLabel1)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, weightLabel))
                        .add(18, 18, 18)
                        .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(partida, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                            .add(fechatxt)
                            .add(weightTxt)))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, addProductPanelLayout.createSequentialGroup()
                        .add(colorLabel2)
                        .add(18, 18, 18)
                        .add(stripeCombinationComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(19, 19, 19))
        );
        addProductPanelLayout.setVerticalGroup(
            addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(addProductPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, codeLabel2)
                    .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(codeLabel)
                        .add(fechatxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(codeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(codeLabel1)
                        .add(partida, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(colorComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(colorLabel)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(addProductPanelLayout.createSequentialGroup()
                        .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(stripeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(colorLabel1))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(stripeCombinationComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(colorLabel2))
                        .add(123, 123, 123))
                    .add(addProductPanelLayout.createSequentialGroup()
                        .add(addProductPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(weightTxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(weightLabel))
                        .add(0, 0, Short.MAX_VALUE)))
                .add(addProductButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 35, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(validationMsgLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(32, 32, 32))
        );

        pedidoPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pedidoPanel.setName("addProductPanel"); // NOI18N
        pedidoPanel.setPreferredSize(new java.awt.Dimension(710, 345));

        pedidosTable.setModel(new PedidosTableDataModel(OrderDao.getOrders()));
        jScrollPane1.setViewportView(pedidosTable);

        org.jdesktop.layout.GroupLayout pedidoPanelLayout = new org.jdesktop.layout.GroupLayout(pedidoPanel);
        pedidoPanel.setLayout(pedidoPanelLayout);
        pedidoPanelLayout.setHorizontalGroup(
            pedidoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pedidoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                .addContainerGap())
        );
        pedidoPanelLayout.setVerticalGroup(
            pedidoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(pedidoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("Archivo");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem1.setText("Salir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        menuBar.add(jMenu1);

        embalajeMenu.setText("Productos");

        jmiEmbalar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK));
        jmiEmbalar.setText("Embalar");
        jmiEmbalar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                embalarActionPerfomed(evt);
            }
        });
        embalajeMenu.add(jmiEmbalar);

        menuBar.add(embalajeMenu);

        pedidosMenu.setText("Pedidos");

        jmiPendientes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK));
        jmiPendientes.setText("Pendientes");
        jmiPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedidosMenuActionPerformed(evt);
            }
        });
        pedidosMenu.add(jmiPendientes);

        menuBar.add(pedidosMenu);

        setJMenuBar(menuBar);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .addContainerGap()
                        .add(addProductPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(pedidoPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 699, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .add(60, 60, 60)
                        .add(tituloLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 632, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(tituloLbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(addProductPanel, 365, 365, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(pedidoPanel, 365, 365, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void clearAllElements() {
        tituloLbl.setText("");
        addProductPanel.setVisible(false);
        getValidationMsgLabel().setText("");
        embalajeMenu.setSelected(false);
        pedidosMenu.setSelected(false);
        pedidoPanel.setVisible(false);
        
    }
    
    public void listPedidoClick(java.awt.event.ActionEvent evt) {
        pedidosMenuActionPerformed(evt);
    }
    private void pedidosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedidosMenuActionPerformed
        clearAllElements();
        tituloLbl.setText("Pedidos para preparar");
        pedidoPanel.setVisible(true);
    }//GEN-LAST:event_pedidosMenuActionPerformed
    public void embalarClick(java.awt.event.ActionEvent evt) {
        embalarActionPerfomed(evt);
    }
    private void embalarActionPerfomed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_embalarActionPerfomed
        clearAllElements();
        tituloLbl.setText("Embalar mercadería");
        tituloLbl.setAlignmentX(100);
        addProductPanel.setVisible(true);
        
    }//GEN-LAST:event_embalarActionPerfomed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    
    
    private Double validateWeight(String value) throws ValidationViewException{
        Double weight;
        try{
            value.replace(',', '.');
            weight= Double.parseDouble(value);
            if(weight<0.01 || weight>=100){
                throw new ValidationViewException("Peso invalidó, espere a que el sistema calibre.");
            }
        }catch(Exception e){
            throw new ValidationViewException("Peso invalidó, espere a que el sistema calibre.");
        }
        
        return weight;
    }
     private int validatePartida(String value) throws ValidationViewException{
        int partida;
        try{
            partida= Integer.parseInt(value);
        }catch(Exception e){
            throw new ValidationViewException("Número de partida invalidó.");
        }
        return partida;
    }
    
    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch"})
    private void addProductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProductButtonActionPerformed
        Product result = null;

        try {

            Fabric code = validateSelectedFabric(); 
            Color color = validateSelectedColor();
            Stripe stripe = validateSelectedStripe(); 
            StripeCombination stripeCombination = validateSelectedStripeCombination(); 
            Double weight = validateWeight(weightTxt.getText());
            int part = validatePartida(partida.getText());

            if(embalajeController.addProduct(code, color, stripe,stripeCombination, weight, part)){
                weightTxt.setText("0.00");
                stripeComboBox.setSelectedIndex(0);
                MainWindow.successMsg("La operación se realizó con éxito.");
            }else{
                MainWindow.errorMsg("Producto invalido.");
            }
        } catch (ValidationViewException ex) {
            MainWindow.errorMsg(ex.getMessage());
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.ALL, ex.getMessage());
        } catch (Exception ex) {
            //Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            MainWindow.errorMsg("El producto no se pudo registrar por un error de comunicación.");
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.ALL, "Error en al agregar el producto", ex);
        }
    }//GEN-LAST:event_addProductButtonActionPerformed

    private void fechatxtvalidateCode(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fechatxtvalidateCode
        // TODO add your handling code here:
    }//GEN-LAST:event_fechatxtvalidateCode

    private void stripeComboBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_stripeComboBoxPropertyChange
        Object obj = evt.getSource();

        if (obj instanceof JComboBox){
            stripeCombinationComboBox.setEnabled(true);
            stripeCombinationComboBox.setEditable(true);
            Stripe stripe = (Stripe)stripeComboBox.getSelectedItem();
            if(stripe!=null && stripe.getCombinations()!=null){
                stripeCombinationComboBox.removeAllItems();
                StripeCombination blank = new StripeCombination();
                blank.setId(-1l);
                blank.setName("Seleccione un rayado.");
                stripeCombinationComboBox.addItem(blank);
                for (StripeCombination combination:stripe.getCombinations()) {
                    stripeCombinationComboBox.addItem(combination);
                }
            }
        }

    }//GEN-LAST:event_stripeComboBoxPropertyChange

    private void codeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_codeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_codeComboBoxActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProductButton;
    private javax.swing.JPanel addProductPanel;
    private javax.swing.JComboBox codeComboBox;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JLabel codeLabel1;
    private javax.swing.JLabel codeLabel2;
    private javax.swing.JComboBox colorComboBox;
    private javax.swing.JLabel colorLabel;
    private javax.swing.JLabel colorLabel1;
    private javax.swing.JLabel colorLabel2;
    private javax.swing.JMenu embalajeMenu;
    private javax.swing.JTextField fechatxt;
    private javax.swing.ButtonGroup group;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem jmiEmbalar;
    private javax.swing.JMenuItem jmiPendientes;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField partida;
    private javax.swing.JPanel pedidoPanel;
    private javax.swing.JMenu pedidosMenu;
    private javax.swing.JTable pedidosTable;
    private javax.swing.JComboBox stripeCombinationComboBox;
    private javax.swing.JComboBox stripeComboBox;
    private javax.swing.JLabel tituloLbl;
    private javax.swing.JLabel validationMsgLabel;
    private javax.swing.JLabel weightLabel;
    private javax.swing.JTextField weightTxt;
    // End of variables declaration//GEN-END:variables

    public JTextComponent getWeightTextBox() {
        return weightTxt;
    }

    public JComboBox getColorComboBox() {
        return colorComboBox;
    }

    public JComboBox getCodeComboBox() {
        return codeComboBox;
    }


    /**
     * @return the validationMsgLabel
     */
    public javax.swing.JLabel getValidationMsgLabel() {
        return validationMsgLabel;
    }

    private Fabric validateSelectedFabric() throws ValidationViewException {
        Fabric fabric =null;
        Object obj = codeComboBox.getSelectedItem();
        if (obj instanceof Fabric){
            fabric = (Fabric) obj;
            if(fabric != null && fabric.getCode()!=null 
                    && fabric.getShortname()!=null ){
                return fabric;
            }       
        }        
        throw new ValidationViewException("Artículo seleccionado invalido.");
    }
    
    private Color validateSelectedColor() throws ValidationViewException {
        Color color =null;
        Object obj =  colorComboBox.getSelectedItem();
        if (obj instanceof Color){
            color = (Color)  obj;
            if(color != null && color.getCode()!=null 
                    && color.getName()!=null ){
                return color;
            }       
        }        
        throw new ValidationViewException("Color seleccionado invalido.");
    }

    private Stripe validateSelectedStripe() throws ValidationViewException {
        Stripe stripe =null;
        Object obj =  stripeComboBox.getSelectedItem();
        if (obj == null){
            return null;
        }
        if (obj instanceof Stripe){
            stripe = (Stripe)  obj;
            if(stripe != null && stripe.getCode()==null ){
                return null;
            }
            if(stripe != null && stripe.getCode()!=null 
                    && stripe.getName()!=null ){
                return stripe;
            }       
        }        
        throw new ValidationViewException("Rayado seleccionado invalido.");
    }
    
    private StripeCombination validateSelectedStripeCombination() throws ValidationViewException {
        StripeCombination stripeCombination =null;
        Object obj =  stripeCombinationComboBox.getSelectedItem();
        if (obj == null){
            return null;
        }
        if (obj instanceof StripeCombination){
            stripeCombination = (StripeCombination)   obj;
            if(stripeCombination != null && stripeCombination.getName()!=null ){
                return stripeCombination;
            }       
        }        
        throw new ValidationViewException("Combinación de rayado seleccionada invalido.");
    }
        
    
}
