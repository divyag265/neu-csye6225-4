package neu.edu.Model;

import neu.edu.Entity.Order;
import neu.edu.Entity.Product;

public class OrderItemModel {

	private Integer orderId;
	private Integer orderitemId;
	private Integer productId;
	private String name;
	private Integer quantityOrdered;
	private Float total;
	private String status;
	private String desc;
	private float price;
	private String address;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Integer getOrder() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getProductId() {
		return productId;
	}
	
	
	public Integer getOrderitemId() {
		return orderitemId;
	}
	public void setOrderitemId(Integer orderitemId) {
		this.orderitemId = orderitemId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}
	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
