package ar.com.adriabe.web.model.json;

import java.util.List;

/**
 * Created by Mildo on 5/10/15.
 */
public class PaymentJSON {

    private Long entityId;
    private String entityType;
    private List<TransferJSON> deposits;
    private List<ChequeJSON> cheques;
    private Double cash;
    private List<UnpaidElementJSON> paying;

    private Double total;

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public List<TransferJSON> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<TransferJSON> deposits) {
        this.deposits = deposits;
    }

    public List<ChequeJSON> getCheques() {
        return cheques;
    }

    public void setCheques(List<ChequeJSON> cheques) {
        this.cheques = cheques;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }


    public List<UnpaidElementJSON> getPaying() {
        return paying;
    }

    public void setPaying(List<UnpaidElementJSON> paying) {
        this.paying = paying;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
