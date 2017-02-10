package neu.edu.Model;

public class ProductModel {
    
	private Integer productId;
	private String name;
	private String description;
	private Float price;
	private Integer quantityAvail;
	
	public ProductModel(){
		
	}
	
	public ProductModel(Integer productId,String name, String description, Float price, Integer quantityAvail) {
		super();
		this.productId=productId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantityAvail = quantityAvail;
	}

	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
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
	
	
	
}
