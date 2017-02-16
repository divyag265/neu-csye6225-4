package neu.edu.dao;

import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import neu.edu.Entity.Customer;
import neu.edu.Entity.Orderitem;
import neu.edu.Entity.Product;
import neu.edu.Entity.Seller;
import neu.edu.Entity.User;

import java.util.ArrayList;
import java.util.Date;

@Repository
public class CustomerDao extends DAO{

	public CustomerDao(){}
	
	UserDao userDao=new UserDao();

	
	User user = new User();
	
	public boolean registrationCustomer(String username, String password, String name, String email, String streetAddress, String city,
			String state, Integer cardNumber,Integer cvv,  String gender ) {

		
		
		user.setUserName(username);
		user.setPassword(password);
		user.setName(name);
		user.setEmail(email);
		user.setStreetAddress(streetAddress);
		user.setCity(city);
		user.setState(state);
		
		user.setRole("customer");

		Customer cust = new Customer();
		cust.setCardNumber(cardNumber);
		cust.setCvv(cvv);
		
		cust.setGender(gender);
		
		
		cust.setUser(user);
		user.setCustomer(cust);
	
		if(userDao.listUsers(username) == null){
			userDao.addUser(user);
			return true;
		}

	return false;
}

	public Customer listCustomer(Integer customerId){
		begin();
		Customer customer = new Customer();
		Query query = getSession().createQuery("from Customer where customerId=:id");
		query.setInteger("id", customerId);
		customer=(Customer)query.uniqueResult();
		commit();
		close();
		return customer;
	}
	
public ArrayList<Customer> ListCustomers(){
			
		ArrayList<Customer> allCusts = new ArrayList<>();
		Session session = getSession();		
		try {
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Customer");
			
			allCusts = (ArrayList<Customer>) query.list();
			tx.commit();
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			
			close();
		}
		return allCusts;
	}
	

	public Boolean saveOrderItems(Orderitem orderitem){
		
		try{
			begin();
			System.out.println("trying to add orderitem");
			getSession().save(orderitem);			
			System.out.println("orderitem saved!");		
			commit();
			
			
		}
		catch(Exception e){
			
			System.out.println("Couldn't add orderitem");
			e.printStackTrace();
		}
		finally{
			close();	
		}
		return true;
	
	}
	
	
	public void DeleteCustomer(Customer customer){
		
		begin();
		getSession().delete(customer);
		commit();
		close();
		
	}
	
}
