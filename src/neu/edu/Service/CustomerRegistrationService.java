package neu.edu.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.Model.CustomerRegistrationReq;
import neu.edu.Model.RestLogicalErrorException;
import neu.edu.dao.CustomerDao;
import neu.edu.dao.UserDao;

@Service
public class CustomerRegistrationService {

	@Autowired
	UserDao userDao;
	
	@Autowired
	CustomerDao custDao;
	
public boolean CustomerRegister(CustomerRegistrationReq custReq) throws RestLogicalErrorException{
		
		if(custReq.getUsername() ==null ||
				custReq.getPassword() ==null||
				custReq.getCardNumber()==null||
				custReq.getCity()==null||
				custReq.getState()==null||
				custReq.getStreetAddress()==null||
				custReq.getEmail()==null||
				custReq.getGender()==null
			
				){
			
			throw new RestLogicalErrorException("Registration Parameters incomplete.");
		}else{
			//Simulation a database Request
			if(!custDao.registrationCustomer(
					custReq.getUsername(),
					custReq.getPassword(),
					custReq.getName(),
					custReq.getEmail(),
					custReq.getStreetAddress(),
					custReq.getCity(),
					custReq.getState(),
					custReq.getCardNumber(),
					custReq.getCvv(),
					
					custReq.getGender()
					
					)){
				throw new RestLogicalErrorException("Duplicate User.");

			}
		}
		
		
		
		return true;
		
	}
}
