package ar.com.adriabe.web.model.json;

/**
 * Created by Mildo on 5/10/15.
 */
public class ChequeJSON {


    public Long bankId;
    public String bank;

    public String receivedDate;
    public String expirationDate;
    public String deliveredDate;


    public String deliveredTo;

    public Long clientId;

    public String nameSigner;
    public String cuitSigner;

    public String number;
    public String account;
    public String code;

    public double value;

    public boolean others = false;
    public boolean crossed = false;
    public boolean rejected = false;
    public boolean activity;

}
