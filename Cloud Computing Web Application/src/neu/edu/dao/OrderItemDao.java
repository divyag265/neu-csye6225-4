package neu.edu.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import neu.edu.Entity.Orderitem;
import neu.edu.Entity.Product;

@Repository
public class OrderItemDao extends DAO{

	public Orderitem ListOrderItems(Integer orderItemId){
		begin();
		Orderitem orderItem = new Orderitem();
		Query query = getSession().createQuery("from Orderitem where orderItemId=:id");
		query.setInteger("id", orderItemId);
		orderItem=(Orderitem)query.uniqueResult();
		commit();
		close();
		return orderItem;
	}
	

public Boolean saveOrderItems(Orderitem orderitem){
		
		try{
			begin();
			System.out.println("trying to add orderitem");
			getSession().saveOrUpdate(orderitem);
			System.out.println("orderitem saved!");		
			commit();
			close();
			
		}
		catch(Exception e){
			
			System.out.println("Couldn't add orderitem");
			e.printStackTrace();
		}
		return true;
	
	}

public Boolean updateStatus(String status, Integer orderitemId){
	try{
		begin();
		Orderitem orderItem;
		Query query = getSession().createQuery("from Orderitem where orderItemId=:id");
		query.setInteger("id", orderitemId);
		orderItem=(Orderitem)query.uniqueResult();
		orderItem.setStatus("Shipped");
		getSession().update(orderItem);
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

public ArrayList<Orderitem> ListOrderitems(){
	
	
	ArrayList<Orderitem> allorderitems = new ArrayList<>();
	Session session = getSession();
	
	try {
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from Orderitem");
		allorderitems = (ArrayList<Orderitem>) query.list();
		tx.commit();

		
	} catch (Exception e) {
		System.out.println(e);
	} finally {
		
		close();

	}
	return allorderitems;
}
}
