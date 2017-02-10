package ar.com.adriabe.web.model.json;

import org.codehaus.jackson.map.annotate.JsonRootName;

/**
 * Created by Mildo on 1/23/15.
 */
@JsonRootName("product")
public class ProductJSON {

    private long id;
    private long stock;

    private long fabricId;
    private String fabric;
    private String fabricCode;

    private Long colorId;
    private String color;
    private String colorType;
    private String colorCode;
    private String colorCoordinate;

    private Long stripeId;
    private String stripe;
    private String stripeCode;
    private String stripeFormula;

    private Long stripeCombinationId;
    private String stripeCombinationName;
    private String[] stripeCombination;

    private String productFamilyName;

    private boolean puno;
    private boolean cuello;

    private double price;

    private Long productFamilyId;

    private int stripeCombinationIndex;
    private boolean tiras;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }

    public String getFabricCode() {
        return fabricCode;
    }

    public void setFabricCode(String fabricCode) {
        this.fabricCode = fabricCode;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorType() {
        return colorType;
    }

    public void setColorType(String colorType) {
        this.colorType = colorType;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCoordinate() {
        return colorCoordinate;
    }

    public void setColorCoordinate(String colorCoordinate) {
        this.colorCoordinate = colorCoordinate;
    }

    public String getStripeName() {
        return stripe;
    }

    public String getStripeCode() {
        return stripeCode;
    }

    public void setStripeCode(String stripeCode) {
        this.stripeCode = stripeCode;
    }

    public String getStripeFormula() {
        return stripeFormula;
    }

    public void setStripeFormula(String stripeFormula) {
        this.stripeFormula = stripeFormula;
    }

    public String getStripeCombinationName() {
        return stripeCombinationName;
    }

    public void setStripeCombinationName(String stripeCombinationName) {
        this.stripeCombinationName = stripeCombinationName;
    }

    public String[] getStripeCombination() {
        return stripeCombination;
    }

    public void setStripeCombination(String[] stripeCombination) {
        this.stripeCombination = stripeCombination;
    }

    public String getProductFamilyName() {
        return productFamilyName;
    }

    public void setProductFamilyName(String productFamilyName) {
        this.productFamilyName = productFamilyName;
    }

    public boolean isPuno() {
        return puno;
    }

    public void setPuno(boolean puno) {
        this.puno = puno;
    }

    public boolean isStripe() {
        return stripe != null;
    }

    public void setStripe(String stripe) {
        this.stripe = stripe;
    }

    public boolean isCuello() {
        return cuello;
    }

    public void setCuello(boolean cuello) {
        this.cuello = cuello;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public Long getProductFamilyId() {
        return productFamilyId;
    }

    public void setProductFamilyId(Long productFamilyId) {
        this.productFamilyId = productFamilyId;
    }

    public Long getStripeId() {
        return stripeId;
    }

    public void setStripeId(Long stripeId) {
        this.stripeId = stripeId;
    }

    public int getStripeCombinationIndex() {
        return stripeCombinationIndex;
    }

    public void setStripeCombinationIndex(int stripeCombinationIndex) {
        this.stripeCombinationIndex = stripeCombinationIndex;
    }

    public String getProductName() {
        String stripeName = "";
        if (stripe != null && stripe != "") {
            stripeName = stripe + " " + stripeCombinationName;
        }
        return fabric + " " + color + " " + stripeName;
    }


    public long getFabricId() {
        return fabricId;
    }

    public void setFabricId(long fabricId) {
        this.fabricId = fabricId;
    }

    public Long getStripeCombinationId() {
        return stripeCombinationId;
    }

    public void setStripeCombinationId(Long stripeCombinationId) {
        this.stripeCombinationId = stripeCombinationId;
    }

    public boolean isTiras() {
        return tiras;
    }

    public void setTiras(boolean tiras) {
        this.tiras = tiras;
    }
}
