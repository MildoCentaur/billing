package ar.com.adriabe.web.model.json;

/**
 * Created by ajmild1 on 26/03/2015.
 */
public class DeliveryOrderItemJSON {
    private String productName;
    private Long productId;
    private Long orderItemDetailId;
    private Double productPrice;
    private Double productWeight;
    private String productBarcode;
    private String fabricName;
    private String colorName;
    private String status;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(Double productWeight) {
        this.productWeight = productWeight;
    }

    public Long getOrderItemDetailId() {
        return orderItemDetailId;
    }

    public void setOrderItemDetailId(Long orderItemDetailId) {
        this.orderItemDetailId = orderItemDetailId;
    }

    public void setProductBarcode(String productBarcode) {
        this.productBarcode = productBarcode;
    }

    public String getProductBarcode() {
        return productBarcode;
    }

    public void setFabricName(String fabricName) {
        this.fabricName = fabricName;
    }

    public String getFabricName() {
        return fabricName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorName() {
        return colorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
