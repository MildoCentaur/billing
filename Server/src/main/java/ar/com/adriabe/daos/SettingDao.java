package ar.com.adriabe.daos;

import ar.com.adriabe.model.Setting;

import java.util.List;


public interface SettingDao {

    public static final String NRO_FACTURA_A = "nro-factura-a";
    public static final String NRO_FACTURA_B = "nro-factura-b";
    public static final String NRO_REMITO_A = "nro-remito-a";
    public static final String NRO_REMITO_B = "nro-remito-b";
    public static final String NRO_RECIBO = "nro-recibo";
    public static final String PRINTER_NAME_BILL_A = "impresora-factura-a";
    public static final String PRINTER_NAME_BILL_B = "impresora-factura-b";
    public static final String PRINTER_NAME_RECEIPT = "impresora-recibo";

    public static final String PRINTER_TICKET_URL = "impresora-ticket-url";
    public static final String PRINTER_TICKET_PORT = "impresora-ticket-port";

    Setting findByName(String settingName) throws DaoException;

    String getNextBillANumber() throws DaoException;

    String getNextBillBNumber() throws DaoException;

    String getNextOrderANumber() throws DaoException;

    String getNextOrderBNumber() throws DaoException;

    void setSetting(String name, String value) throws DaoException;

    String getPrinterNameBillA() throws DaoException;

    String getPrinterNameBillB() throws DaoException;

    String getPrinterNameReceipt() throws DaoException;

    List<Setting> findAll();

    void save(Setting setting);

    String getNextReceiptNumber() throws DaoException;

    String getTicketPrinterUrl() throws DaoException;

    int getTicketPrinterPort() throws DaoException;
}
