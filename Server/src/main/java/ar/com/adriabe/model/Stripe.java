package ar.com.adriabe.model;

import ar.com.adriabe.model.common.AuditableDomainObject;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AJMILD1 on 03/06/14.
 */
@Entity
@Table(name = "stripes")
public class Stripe extends AuditableDomainObject {

    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String code;

    private int colors;  //cantidad de colores

    private String listingFormula; // equation used to describe the listing 60A45B85C64A...

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "stripe_id")
    @OrderBy("id")
    private List<StripeCombination> combinations = null;


    public Stripe() {
        setId(0l);
    }

    public Stripe(Long id) {
        setId(id);
    }

    public String getListingFormula() {
        return listingFormula;
    }

    public void setListingFormula(String listingFormula) {
        this.listingFormula = listingFormula;
    }

    public int getColors() {
        return colors;
    }

    public void setColors(int colors) {
        this.colors = colors;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public List<StripeCombination> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<StripeCombination> combinations) {
        this.combinations = combinations;
    }

    public int getCombinationsAmount() {
        return (this.combinations == null) ? 0 : this.combinations.size();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stripe)) return false;
        if (!super.equals(o)) return false;

        Stripe stripe = (Stripe) o;

        if (!getId().equals(stripe.getId())) return false;

        if (!code.equals(stripe.code)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getId().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", colors=" + colors +
                ", listingFormula='" + listingFormula + '\'' +
                '}';
    }

    public int getCombinationIndex(StripeCombination stripeCombination) {
        int i = 0, result = 0;

        for (StripeCombination combination : combinations) {
            if (combination.getId() == stripeCombination.getId()) {
                result = i;
            }
            i++;
        }
        return result;
    }

    public void addCombinations(StripeCombination combination) {
        if (combinations == null) {
            combinations = new ArrayList<StripeCombination>();
        }
        combinations.add(combination);
    }

    public Long getCombinationIdByIndex(int index) {
        return combinations.get(index).getId();
    }
}
