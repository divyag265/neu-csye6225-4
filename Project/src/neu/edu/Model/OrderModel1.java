package neu.edu.Model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import neu.edu.Entity.Customer;
import neu.edu.Entity.Orderitem;

public class OrderModel1 {

	private Integer customerId;
	private Integer cardNumber;
	private Date dateCreated;
	private Float orderToal;
	
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public Integer getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
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
	
	
	
}
