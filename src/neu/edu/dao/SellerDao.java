package neu.edu.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import neu.edu.Entity.Customer;
import neu.edu.Entity.Product;
import neu.edu.Entity.Seller;
import neu.edu.Entity.User;

@Repository
public class SellerDao extends DAO {

	
UserDao userDao = new UserDao();
	

	
	public boolean registrationSeller(String userName, String password, String name, String email, String streetAddress, String city,
			String state, String company) {

		User user = new User();
		
		user.setUserName(userName);
		user.setPassword(password);
		user.setName(name);
		user.setEmail(email);
		user.setStreetAddress(streetAddress);
		user.setCity(city);
		user.setState(state);		
		user.setRole("seller");

		Seller seller = new Seller();		
		seller.setCompany(company);
		
		seller.setUser(user);
		user.setSeller(seller);
		
	
		if(userDao.listUsers(userName) == null){
			userDao.addUser(user);
			return true;
		}

	return false;
}

	public Seller ListSeller(Integer sellerId){
		begin();
		Seller seller = new Seller();
		Query query = getSession().createQuery("from Seller where sellerId=:id");
		query.setInteger("id", sellerId);
		seller=(Seller)query.uniqueResult();
		commit();
		close();
		return seller;
	}
	
	public Boolean AddProduct(Product product, int sellerId){
		
		try{
			begin();
			System.out.println("trying to add product");
			if(product.getSeller().getSellerId() == sellerId){
			getSession().save(product);
			System.out.println("product saved!");
			}
			System.out.println(product.getSeller().getSellerId());
			commit();
			
			
		}
		catch(Exception e){
			System.out.println(product.getSeller().getSellerId());
			System.out.println("Couldn't add product");
			e.printStackTrace();
		}
		finally{
			close();
		}
		return true;
	}
		
	public Product ListProduct(String name){
		begin();
		Product product = new Product();
		
		Query query = getSession().createQuery("from Product where name=:un");
		query.setString("un", name);
		product=(Product)query.uniqueResult();
		commit();
		close();
		return product;
		
	}
	
public List<Seller> ListAllSellers(){
	
			Query query = getSession().createQuery("from Seller");
			ArrayList<Seller> allSellers = (ArrayList<Seller>) query.list();
			
			System.out.println("in sellerDao " +allSellers.get(0));
		
		return allSellers;
	}

public void DeleteSeller(Seller seller){
	
	begin();
	getSession().delete(seller);
	commit();
	close();
	
}

public void updateQuantity(Product product, Integer quantityAvail) {
	// TODO Auto-generated method stub
	product.setQuantityAvail(product.getQuantityAvail()+quantityAvail);
	begin();
	System.out.println("Trying to update");
	getSession().update(product);
	commit();
	System.out.println("UPDATED SUCCESFULLY");
	close();
	
}
}
