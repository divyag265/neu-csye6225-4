package neu.edu.Model;

import java.util.Date;

import neu.edu.Entity.Order;
import neu.edu.Entity.Orderitem;
import neu.edu.Entity.Product;

public class Cart {
    
	
	private String name;
	private Date dateCreated;	
	private Float orderToal;
	private Integer quantityOrdered;
	private Order order;
	private Product product;
	
	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}
	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}
	

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Float getOrderToal() {
		return orderToal;
	}
	public void setOrderToal(Float orderToal) {
		this.orderToal = orderToal;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
