package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "list_price")
public class ListPrice extends AuditableDomainObject {
    /**
     * 
     */
    private static final long serialVersionUID = 6933294117588836749L;

    private String name;
    private boolean active = true;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="list_price_id")
	@OrderBy("id")
    private List<ListPriceItem> prices = null;

    public ListPrice(Long listPriceId) {
        setId(listPriceId);
    }
    public ListPrice() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


	public List<ListPriceItem> getPrices() {
		return prices;
	}
	public void setPrices(List<ListPriceItem> prices) {
		this.prices = prices;
	}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
