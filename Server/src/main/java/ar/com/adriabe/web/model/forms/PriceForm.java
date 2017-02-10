package ar.com.adriabe.web.model.forms;

/**
 * Created by Mildo on 11/24/14.
 */
public class PriceForm {

    private Long fabric;

    private Long stripe;

    private String name;

    private double priceWhite;

    private double priceLight;

    private double priceDark;

    private double priceSpecial;

    public double getPriceSpecial() {
        return priceSpecial;
    }

    public void setPriceSpecial(double priceSpecial) {
        this.priceSpecial = priceSpecial;
    }

    public double getPriceDark() {
        return priceDark;
    }

    public void setPriceDark(double priceDark) {
        this.priceDark = priceDark;
    }

    public double getPriceLight() {
        return priceLight;
    }

    public void setPriceLight(double priceLight) {
        this.priceLight = priceLight;
    }

    public double getPriceWhite() {
        return priceWhite;
    }

    public void setPriceWhite(double priceWhite) {
        this.priceWhite = priceWhite;
    }

    public Long getStripe() {
        return stripe;
    }

    public void setStripe(Long stripe) {
        this.stripe = stripe;
    }

    public Long getFabric() {
        return fabric;
    }

    public void setFabric(Long fabric) {
        this.fabric = fabric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
