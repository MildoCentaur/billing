package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by AJMILD1 on 13/06/14.
 */
@Entity
@Table(name = "payments")
public class Payment extends AuditableDomainObject {

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReviewed;
    private Double value; // total amount
    private Long number;
    private boolean activity; // si es una actividad entra en el listado de actividades sino entra en ambos[movimientos y actividades]
    private String place;


    private Double cash;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "payment_cheques",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "cheque_id"))
    @OrderBy("id")
    private List<Cheque> cheques;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "payment_transfers",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "transfers_id"))
    @OrderBy("id")
    private List<Transfer> transfers;

    public Payment() {
        this.transfers = new ArrayList<Transfer>();
        this.cheques = new ArrayList<Cheque>();
        this.date = new Date();
        this.value = 0.0;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateReviewed() {
        return dateReviewed;
    }

    public void setDateReviewed(Date dateReviewed) {
        this.dateReviewed = dateReviewed;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<Cheque> getCheques() {
        return cheques;
    }

    public void setCheques(List<Cheque> cheques) {
        this.cheques = cheques;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }


    public void addTransfer(Transfer transfer) {

        if (transfers == null) {
            transfers = new ArrayList<Transfer>();
        }
        transfers.add(transfer);
    }

    public void addCheque(Cheque cheque) {
        if (cheques == null) {
            cheques = new ArrayList<Cheque>();
        }
        cheques.add(cheque);
    }
}
