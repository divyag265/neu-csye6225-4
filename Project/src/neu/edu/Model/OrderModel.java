package neu.edu.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import neu.edu.Entity.Customer;
import neu.edu.Entity.Orderitem;

public class OrderModel {

	private Customer customer;
	private Date dateCreated;
	private Date dateShipped;
	private Float orderToal;
	private Set<Orderitem> orderitems = new HashSet<Orderitem>(0);
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateShipped() {
		return dateShipped;
	}
	public void setDateShipped(Date dateShipped) {
		this.dateShipped = dateShipped;
	}
	public Float getOrderToal() {
		return orderToal;
	}
	public void setOrderToal(Float orderToal) {
		this.orderToal = orderToal;
	}
	public Set<Orderitem> getOrderitems() {
		return orderitems;
	}
	public void setOrderitems(Set<Orderitem> orderitems) {
		this.orderitems = orderitems;
	}
	
	
}
