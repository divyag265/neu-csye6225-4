package neu.edu.Service;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.Entity.Customer;
import neu.edu.Entity.Order;
import neu.edu.Entity.Orderitem;
import neu.edu.Entity.Product;
import neu.edu.Entity.Seller;
import neu.edu.Entity.User;
import neu.edu.Model.Cart;
import neu.edu.Model.Cart1;
import neu.edu.Model.Cart3;
import neu.edu.Model.CustomerModel;
import neu.edu.Model.OrderModel;
import neu.edu.Model.OrderModel1;
import neu.edu.Model.ProductModel;
import neu.edu.Model.ProductRegReq;
import neu.edu.Model.RestLogicalErrorException;
import neu.edu.dao.CustomerDao;

import neu.edu.dao.OrderDao;
import neu.edu.dao.ProductDao;
import neu.edu.dao.UserDao;

@Service
public class CustomerService {
	
	@Autowired
	CustomerDao customerDao;
	
	@Autowired
	OrderDao orderDao;
	
	
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ProductDao productDao;

	public boolean AddToCart( Cart3 cart,int customerId) throws RestLogicalErrorException{	
		CustomerDao customerdao=new CustomerDao();
		ProductDao productDao = new ProductDao();
		Customer cust=customerdao.listCustomer(customerId);
				
		Orderitem orderitem=new Orderitem();
		orderitem.setQuantityOrdered(cart.getQuantityOrdered());		
				
		Product product = productDao.ListProduct(cart.getName());
		
		System.out.println(product.getProductId());
		orderitem.setTotal(cart.getQuantityOrdered()*product.getPrice());		
		orderitem.setProduct(product);
		System.out.println(orderitem.getProduct().getProductId());
		
		product.getOrderitems().add(orderitem);
		
		Order Currentorder;
		int flag=0;
		for(Order order : cust.getOrders())
		{
			if(order.getDateCreated()==null)
				{
					Currentorder=order;
					orderitem.setOrder(Currentorder);
					Currentorder.getOrderitems().add(orderitem);
					
					flag=1;
				}
			
		}
		if(flag==0)
		{	Currentorder=new Order();
			cust.getOrders().add(Currentorder);
			Currentorder.setCustomer(cust);
			orderitem.setOrder(Currentorder);
			Currentorder.getOrderitems().add(orderitem);
			
		}
		
		customerdao.saveOrderItems(orderitem);
		
		return true;
}
	
	
	public Boolean DeleteCustomer(int customerId){
		Customer customer = customerDao.listCustomer(customerId);
	int userId=customer.getUser().getUserId();
	
		customerDao.DeleteCustomer(customer);
		User user = userDao.listUsers(userId);
		userDao.DeleteUser(user);
		return true;
	}
	
	public Boolean CheckOut( int customerId, int orderId)throws RestLogicalErrorException{
		CustomerDao customerdao = new CustomerDao();
		Customer customer=customerdao.listCustomer(customerId);
		
		Order order = orderDao.ListOrderItems(orderId);
		order.setDateCreated(new Date());
		order.setOrderToal((float)0);
		for(Orderitem orderItem: order.getOrderitems()){
			orderItem.setStatus("Ordered/Paid");
			order.setOrderToal(order.getOrderToal()+orderItem.getTotal());
		}
				
		orderDao.saveOrder(order);
		for(Orderitem orderItem: order.getOrderitems())
			{
			int productId= orderItem.getProduct().getProductId();
			Product product = productDao.ListProduct(productId);
			{
				product.setQuantityAvail(product.getQuantityAvail()-orderItem.getQuantityOrdered());
				productDao.updateProduct(product);
			}
		}
		return true;
	}
	
	
	public Boolean CheckOut( int customerId)throws RestLogicalErrorException{
		CustomerDao customerdao = new CustomerDao();
		Customer customer=customerdao.listCustomer(customerId);
		Order order = orderDao.ListCart(customerId);
		
		if(order!=null){
		order.setDateCreated(new Date());
		order.setOrderToal((float)0);
		for(Orderitem orderItem: order.getOrderitems()){
			orderItem.setStatus("Ordered/Paid");
			order.setOrderToal(order.getOrderToal()+orderItem.getTotal());
		}
			
		orderDao.saveOrder(order);
		
		for(Orderitem orderItem: order.getOrderitems())
			{
			int productId= orderItem.getProduct().getProductId();
			Product product = productDao.ListProduct(productId);
			{
				product.setQuantityAvail(product.getQuantityAvail()-orderItem.getQuantityOrdered());
				productDao.updateProduct(product);
			}
			return true;
			}
		}
		return false;
	}
	
	
	
	
	public List<OrderModel1>viewOrder(int customerId)throws RestLogicalErrorException{
		
		List<OrderModel1>orderModelList = new ArrayList<OrderModel1>();
		
		List<Order>orderList = orderDao.ListOrders(customerId);
		for(Order order:orderList){
			if(order.getDateCreated()!=null){
				OrderModel1 orderModel1 = new OrderModel1();
			orderModel1.setCustomerId(order.getCustomer().getCustomerId());
			int customerId1 = order.getCustomer().getCustomerId();
			Customer customer1 = customerDao.listCustomer(customerId1);
			
			orderModel1.setCardNumber(customer1.getCardNumber());	
				orderModel1.setDateCreated(order.getDateCreated());				
				orderModel1.setOrderToal(order.getOrderToal());			
				orderModelList.add(orderModel1);
			}	
		}
		return orderModelList;
	}
	
	public List<Cart1>viewCart(int customerId)throws RestLogicalErrorException{
		
		List<Cart1>cartList = new ArrayList<Cart1>();
		
		Order order = orderDao.ListCart(customerId);
	
		if(order==null){return null;}
		for(Orderitem orderitem:order.getOrderitems()){

				Cart1 cart = new Cart1();
			
				cart.setCustomerId(order.getCustomer().getCustomerId());
				cart.setProductId(orderitem.getProduct().getProductId());
				Product product = productDao.ListProduct(orderitem.getProduct().getProductId());
				
				cart.setProductName(product.getName());
				cart.setQuantityOrdered(orderitem.getQuantityOrdered());
				cart.setTotal(orderitem.getTotal());
				cartList.add(cart);	
		}
		return cartList;
		
	}	
	
	
	public List<CustomerModel> viewCustomers() throws RestLogicalErrorException{
		List<Customer> customerList = customerDao.ListCustomers();
		
		List<CustomerModel>custModelList = new ArrayList<CustomerModel>();
		
		if(customerList.size()!=0){
		for(Customer customer:customerList ){
			
			User user = userDao.listUsers(customer.getCustomerId());
			custModelList.add(new CustomerModel(
					customer.getCustomerId(),
					user.getUserName(),
					user.getName(),
					user.getEmail(),
					user.getStreetAddress(),
					user.getCity(),
					user.getState(),
					customer.getGender())
	
					);		
		}	}
		
		else{
			RestLogicalErrorException authResponseErr = new RestLogicalErrorException("Invalid input");
			throw authResponseErr;
	}	
		return custModelList;	
	}
	}


