package neu.edu.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import neu.edu.Entity.Order;
import neu.edu.Entity.Orderitem;
import neu.edu.Entity.Product;


;

@Repository
public class OrderDao extends DAO {
	
	

	public Order ListOrderItems(Integer orderId){
		begin();
		Order order = new Order();
		Query query = getSession().createQuery("from Order where orderId=:id");
		query.setInteger("id", orderId);
		order=(Order)query.uniqueResult();
		commit();
		close();
		return order;
	}
	
public Order ListOrder(Integer customerId){
	begin();
	Order order = new Order();
	Query query = getSession().createQuery("from Order where customerId=:id");
	query.setInteger("id", customerId);
	order=(Order)query.uniqueResult();
	commit();
	close();
	return order;
}
	
public ArrayList<Order> ListOrders(Integer customerId){
	
		begin();
		ArrayList<Order> allOrders = new ArrayList<Order>();
		Query query = getSession().createQuery("from Order where customerId=:id");
		query.setInteger("id", customerId);
		allOrders = (ArrayList<Order>) query.list();
		commit();
       close();
	
	return allOrders;

}

public Order ListCart(Integer customerId)
{
	begin();
	
	ArrayList<Order> allOrders = new ArrayList<Order>();
	
	Query query = getSession().createQuery("from Order where customerId=:id");
	query.setInteger("id", customerId);
	allOrders = (ArrayList<Order>) query.list();

	for(Order O:allOrders)
	{
		if(O.getDateCreated()==null)
			{
			return O;}
		
		
	}
return null;
}

	public Boolean saveOrder(Order order){
		
		try{
			begin();
			System.out.println("trying to add order");
			getSession().update(order);
			System.out.println("order saved!");		
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
}
