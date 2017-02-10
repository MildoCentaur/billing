package ar.com.adriabe.web.model.json;

import org.codehaus.jackson.map.annotate.JsonRootName;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mildo on 2/19/15.
 */
@JsonRootName("account")
public class AccountJSON {


    private Long id;
    private String accountabletDate;
    private String concept;
    private String credit;
    private String debit;
    private String balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountabletDate() {
        return accountabletDate;
    }

    public void setAccountabletDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        this.accountabletDate = format.format(date);
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = String.format("%.2f", credit);
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = String.format("%.2f", debit);
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = String.format("%.2f", balance);
    }
}
