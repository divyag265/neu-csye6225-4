package neu.edu.Model;

import neu.edu.Entity.Seller;

public class ProductRegReq {

	private Integer productId;
	private Seller seller;
	private String name;
	private String description;
	private Float price;
	private Integer quantityAvail;
	
	public ProductRegReq(){
		
	}
	
	public ProductRegReq( Integer productId, Seller seller, String name, String description, Float price,
			Integer quantityAvail) {
		super();
		
		this.productId= productId;
		this.seller = seller;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantityAvail = quantityAvail;
	}
	
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getQuantityAvail() {
		return quantityAvail;
	}
	public void setQuantityAvail(Integer quantityAvail) {
		this.quantityAvail = quantityAvail;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
}
