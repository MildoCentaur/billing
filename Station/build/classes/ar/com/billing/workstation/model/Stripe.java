package ar.com.billing.workstation.model;

import java.util.List;

/**
 * Created by AJMILD1 on 03/06/14.
 */
public class Stripe {

    private String name;
    private String code;
    private int colors;  //cantidad de colores
    private Long id;
    private String listingFormula; // equation used to describe the listing 60A45B85C64A...
    private List<StripeCombination> combinations= null;

    public Stripe() {
        
    }
    
    public Stripe(Long id) {
        this.id=id;
    }
    
    public Long getId() {
        return this.id;
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

    public List<StripeCombination> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<StripeCombination> combinations) {
        this.combinations = combinations;
    }

    public int getCombinationsAmount() {
        return (this.combinations==null)? 0:this.combinations.size();
    }

    public void setId(long id) {
        this.id=id;
    }

    public Stripe clone(){
        Stripe stripe = new Stripe();
        stripe.setCode(code);
        stripe.setColors(colors);
        stripe.setCombinations(combinations);
        stripe.setId(id);
        stripe.setListingFormula(listingFormula);
        stripe.setName(name);
        return stripe;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
