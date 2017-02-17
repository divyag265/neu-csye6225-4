package neu.edu.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import neu.edu.Entity.Orderitem;
import neu.edu.Entity.Product;

@Repository
public class ProductDao extends DAO {

	public ArrayList<Product> ListProduct(){
		
		
		ArrayList<Product> allProducts = new ArrayList<>();
		Session session = getSession();
		
		try {
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Product");
			allProducts = (ArrayList<Product>) query.list();
			tx.commit();

			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			
			close();

		}
		return allProducts;
	}
	
	public ArrayList<Product> ListProducts(Integer sellerId){
		ArrayList<Product> allProducts = new ArrayList<>();
		Session session = getSession();
		
		try {
			Transaction tx = session.beginTransaction();
			Query query = session.createQuery("from Product where sellerId=:id");
			query.setInteger("id", sellerId);
			allProducts = (ArrayList<Product>) query.list();
			System.out.println(allProducts.get(0));
			tx.commit();

			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			close();

		}
		return allProducts;
		
	}
		
	public Product ListProduct(Integer productId){
		begin();
		Product product = new Product();
		
		Query query = getSession().createQuery("from Product where productId=:id");
		query.setInteger("id", productId);
		product=(Product)query.uniqueResult();
		commit();
		close();
		return product;
		
	}
	
	public Product ListProduct(String name){
		begin();
		Product product = new Product();
		
		Query query = getSession().createQuery("from Product where name=:name");
		query.setString("name", name);
		product=(Product)query.uniqueResult();
		commit();
		close();
		return product;
		
	}
	
	public Boolean updateProduct(Product product){
		
		try{
			begin();			
			getSession().update(product);					
			commit();
			
		}
		catch(Exception e){
			
			System.out.println("Couldn't update product");
			e.printStackTrace();
		}
		finally{

			close();
		}
		return true;
	
	}
	}

