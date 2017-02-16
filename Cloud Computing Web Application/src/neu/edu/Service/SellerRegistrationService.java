package neu.edu.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.Entity.Product;
import neu.edu.Entity.Seller;
import neu.edu.Entity.User;
import neu.edu.Model.CustomerRegistrationReq;
import neu.edu.Model.ProductModel;
import neu.edu.Model.RestLogicalErrorException;
import neu.edu.Model.SellerModel;
import neu.edu.Model.SellerRegistrationReq;
import neu.edu.dao.SellerDao;
import neu.edu.dao.UserDao;

@Service
public class SellerRegistrationService {

	@Autowired
	SellerDao sellerDao;
	
	@Autowired
	UserDao userDao;
	
public boolean SellerRegister(SellerRegistrationReq sellReq) throws RestLogicalErrorException{
		
		if(sellReq.getUserName() ==null ||
				sellReq.getPassword() ==null||
				sellReq.getName()==null||
		sellReq.getEmail()==null||
			sellReq.getCompany()==null||
			sellReq.getState()==null||
			sellReq.getStreetAddress()==null||
					sellReq.getCity()==null)
						
								
{
			
			throw new RestLogicalErrorException("Input Parameters are incomplete.");
		}else{
			
			if(!sellerDao.registrationSeller(
					sellReq.getUserName(),
					sellReq.getPassword(),
					sellReq.getName(),
					sellReq.getEmail(),
					sellReq.getStreetAddress(),
					sellReq.getCity(),
					sellReq.getState(),
					sellReq.getCompany()
					)){
				throw new RestLogicalErrorException("Duplicate User! User already exists.");

			}
		}
		
		return true;
		
	}

public List<SellerModel> getSellerList() throws RestLogicalErrorException{
	
	System.out.println("in service");
	SellerDao sellerDao = new SellerDao();
	List<Seller> sellerList = sellerDao.ListAllSellers();
	System.out.println(sellerList.get(0));
	List<SellerModel>sellerModelList = new ArrayList<SellerModel>();
	
	if(sellerList.size()!=0){
	for(Seller seller:sellerList ){
		sellerModelList.add(new SellerModel(
	seller.getSellerId(),
	seller.getUser().getUserName(),
	seller.getUser().getPassword(),
	seller.getUser().getEmail(),
	seller.getUser().getStreetAddress(),
	seller.getUser().getCity(),
	seller.getUser().getState(),
	seller.getUser().getRole(),
	seller.getUser().getName(),
	seller.getCompany()
	)
				);
		
	}
	}	
	else{
		RestLogicalErrorException authResponseErr = new RestLogicalErrorException("Invalid input");
		throw authResponseErr;
}	
	return sellerModelList;	
}

public Boolean DeleteSeller( int sellerId){
	
	Seller seller = sellerDao.ListSeller(sellerId);
	
	User user=userDao.listUsers(sellerId);
	sellerDao.DeleteSeller(seller);
	userDao.DeleteUser(user);
	return true;
	
}
	
}	
	
	

