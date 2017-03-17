package neu.edu.dao;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import neu.edu.Entity.Customer;
import neu.edu.Entity.Product;
import neu.edu.Entity.User;
import neu.edu.Model.AuthRequest;


@Service
public class UserDao extends DAO{

	
	//final static Logger logger = Logger.getLogger(UserDao.class);
	
public User listUsers(String userName){
	begin();	
	User user = new User();
	begin();
	Query query = getSession().createQuery("from User where userName=:un");
	query.setString("un", userName);
	user=(User)query.uniqueResult();
	commit();
	close();
	return user;
}
public User listUsers(int userId){
	begin();	
	User user = new User();
	begin();
	Query query = getSession().createQuery("from User where userId=:id");
	query.setInteger("id", userId);
	user=(User)query.uniqueResult();
	commit();
	close();
	return user;
}

	public Boolean addUser(User user){
		
		try{
		begin();
		System.out.println("trying to register");
		getSession().save(user);		
		System.out.println("registered successfully");
	
		commit();
		close();
		return true;
		}
		catch(Exception e){
			System.out.println(" couldn't register");
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	public User validateUser(String userName, String password ){
		
   
    	begin();
    	User user = new User();
    	Query query = getSession().createQuery("from User where userName=:un and password=:pass");
    	query.setString("un",userName );
    	query.setString("pass",password);
    	user=(User)query.uniqueResult();
    	commit();
    	close();
    	return user;
    	
    }
	
	public void DeleteUser(User user){
		begin();
		getSession().delete(user);
		commit();
		close();
	}
	

}
