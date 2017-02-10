package ar.com.billing.workstation.model;

public class Product {

    private static final long serialVersionUID = -2644487083142153574L;
    private Long id;
    private String productName;
    private Long productFamilyId;
    private Long colorId;
    private Long stripeId;
    private Long stripeCombinationIndex;

    public Long getStripeCombinationIndex() {
        return stripeCombinationIndex;
    }

    public void setStripeCombinationIndex(Long stripeCombinationIndex) {
        this.stripeCombinationIndex = stripeCombinationIndex;
    }

    public Product(long id) {
        this.id=id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductFamilyId(long aLong) {
        this.productFamilyId=aLong;
    }

    public void setColorId(long aLong) {
        this.colorId=aLong;
    }

    public Long getProductFamilyId() {
        return productFamilyId;
    }

    public void setProductFamilyId(Long productFamilyId) {
        this.productFamilyId = productFamilyId;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public Long getStripeId() {
        return stripeId;
    }

    public void setStripeId(Long stripeId) {
        this.stripeId = stripeId;
    }

    public int compareWithBarcode(String barcode) {
        Long prodFamilyId=Long.parseLong(barcode.substring(0, 3));
        if(this.productFamilyId != prodFamilyId){
            return 1;
        }
        Long colorId=Long.parseLong(barcode.substring(3, 6));
        if(this.colorId != colorId){
            return 1;
        }
        Long stripeCombinationIndex=Long.parseLong(barcode.substring(6, 7));
        if(this.stripeCombinationIndex  != stripeCombinationIndex){
            return 1;
        }
        return 0;
    }
 
}
