package ar.com.adriabe.web.model.json;

import ar.com.adriabe.model.DeliveryOrder;
import ar.com.adriabe.model.SupplierBill;
import ar.com.adriabe.model.common.IRegisterAccount;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class UnpaidElementJSON {

    public String date;
    public String number;
    public String packagesAmount;
    public String totalAmount;
    public long id;
    public DOCUMENT_TYPE documentType;

    private UnpaidElementJSON() {

    }

    public static UnpaidElementJSON createFromIRegisterAccount(IRegisterAccount iRegisterAccount) {
        UnpaidElementJSON result = new UnpaidElementJSON();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");


        result.date = format.format(iRegisterAccount.getDateAccountable());
        result.number = String.format("%08d", 0);
        result.packagesAmount = " - ";
        Locale locale = new Locale("es", "AR");
        result.totalAmount = String.format(locale, "%02.2f $", iRegisterAccount.getAmountAccountable());
        if (iRegisterAccount instanceof DeliveryOrder) {
            result.number = String.format("%08d", ((DeliveryOrder) iRegisterAccount).getId());
            result.packagesAmount = String.format("%03d", ((DeliveryOrder) iRegisterAccount).getPackagesAmount());
            result.id = ((DeliveryOrder) iRegisterAccount).getId();
            result.documentType = DOCUMENT_TYPE.DELIVERY_ORDER;
        } else if (iRegisterAccount instanceof SupplierBill) {
            result.number = String.format("%08d", ((SupplierBill) iRegisterAccount).getBillNumber());
            result.packagesAmount = " - ";
            result.id = ((SupplierBill) iRegisterAccount).getId();
            result.documentType = DOCUMENT_TYPE.BILL;
        }


        return result;
    }

    public enum DOCUMENT_TYPE {
        DELIVERY_ORDER, BILL;

    }
}
