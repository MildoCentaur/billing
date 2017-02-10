package ar.com.adriabe.model;

import ar.com.adriabe.model.constant.EurekaConstants;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Entity
@Table(name = "cheque")
public class Cheque extends AbstractAuditable<User, Long> {

    private static final long serialVersionUID = 8609609585743951206L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    protected Bank bank;
    protected double value;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    protected Date received;
    protected boolean others = false;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    protected Date dateHanded;
    protected boolean crossed = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    protected Client client;
    protected String number;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date expirationDate;
    private String handedTo;
    private String firmante;
    private String cuitFirmante;
    private String account;
    private String code;

    private boolean rejected = false;

    private boolean activity;

    /**
     * Method 'Cheque'
     */
    public Cheque() {
    }

    public Cheque(Long idCheque) {
        setId(idCheque);
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getReceived() {
        return received;
    }


    public void setReceived(Date received) {
        this.received = received;
    }

    public boolean isOthers() {
        return others;
    }

    public void setOthers(boolean others) {
        this.others = others;
    }

    public Date getDateHanded() {
        return dateHanded;
    }

    public void setDateHanded(Date dateHanded) {
        this.dateHanded = dateHanded;
    }

    public boolean isCrossed() {
        return crossed;
    }

    public void setCrossed(boolean crosses) {
        this.crossed = crosses;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    public String getCuitFirmante() {
        return cuitFirmante;
    }

    public void setCuitFirmante(String cuitFirmante) {
        this.cuitFirmante = cuitFirmante;
    }

    public String getFirmante() {
        return firmante;
    }

    public void setFirmante(String firmante) {
        this.firmante = firmante;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Transient
    public String getBankShortName() {
        return bank.getShortName();
    }

    @Transient
    public String getClientName() {
        return client.getName();
    }

    @Transient
    public String getReceivedDateStr() {
        if (received == null) {
            return "--";
        }
        SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE_SHORT);
        return format.format(received);
    }

    @Transient
    public String getExpirationDateStr() {
        if (expirationDate == null) {
            return "--";
        }
        SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE_SHORT);
        return format.format(expirationDate);
    }

    @Transient
    public String getValueStr() {
        return String.format(new Locale("es", "ES"), "%1$,.2f $", value);
    }

    @Transient
    public String getHandedDateStr() {
        if (dateHanded == null) {
            return "--";
        }
        SimpleDateFormat format = new SimpleDateFormat(EurekaConstants.PATTERN_READABLE_SHORT);
        return format.format(dateHanded);
    }

    public String getHandedTo() {
        return handedTo;
    }

    public void setHandedTo(String handedTo) {
        this.handedTo = handedTo;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }
}
