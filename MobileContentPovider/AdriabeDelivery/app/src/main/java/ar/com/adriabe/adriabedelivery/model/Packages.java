/**
 * 
 */
package ar.com.adriabe.adriabedelivery.model;


/**
 * @author Mildo
 *
 */
public class Packages {

	private String productName;
	private Long productId;
	private Long orderItemDetailId;
	private Double productPrice;
	private Double productWeight;
	private String productBarcode;
	private String fabricName;
	private String colorName;
	private String status;

	public Packages(Long orderItemDetailId, Double productWeight, String productBarcode, String fabricName, String colorName, String status) {
		this.orderItemDetailId = orderItemDetailId;
		this.productWeight = productWeight;
		this.productBarcode = productBarcode;
		this.fabricName = fabricName;
		this.colorName = colorName;
		this.status = status;
	}

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

	public Long getOrderItemDetailId() {
		return orderItemDetailId;
	}

	public void setOrderItemDetailId(Long orderItemDetailId) {
		this.orderItemDetailId = orderItemDetailId;
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

	public String getProductBarcode() {
		return productBarcode;
	}

	public void setProductBarcode(String productBarcode) {
		this.productBarcode = productBarcode;
	}

	public String getFabricName() {
		return fabricName;
	}

	public void setFabricName(String fabricName) {
		this.fabricName = fabricName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
