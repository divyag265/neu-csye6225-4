package neu.edu.Controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.Model.AuthRequest;
import neu.edu.Model.CustomerModel;
import neu.edu.Model.ProductModel;
import neu.edu.Model.RestLogicalErrorException;
import neu.edu.Model.SellerModel;
import neu.edu.Model.UserSession;
import neu.edu.Service.CustomerService;
import neu.edu.Service.ProductRegistrationService;
import neu.edu.Service.SellerRegistrationService;

@Path("/Admin")
@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class AdminController {
	
	@Autowired
	SellerRegistrationService sellerServ;
	
	@Autowired
	CustomerService custServ;
	
	@Autowired
	ProductRegistrationService prodServ;

	@GET
	@Path("/{adminId}/ViewAllSellers")
	public Response getSellers(){
	
		try {
			List<SellerModel>sellerList = sellerServ.getSellerList();
			//if (sellerList!= null) {				
				return Response.ok().entity(sellerList).build();
			//}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		//return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();

	}
	
	@GET
	@Path("/ViewAllProducts")
	public Response getProducts(){	
		try {
			List<ProductModel>productList = prodServ.getProductList();
			if (productList!= null) {
				
				return Response.ok().entity(productList).build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();

	}
	
	
			
	@DELETE
	@Path("/{sellerId}/deleteSeller")
	public Response DeleteSeller(@PathParam("sellerId") String sellerId){
		
			if(sellerServ.DeleteSeller( Integer.parseInt(sellerId))){
			
				return Response.ok().build();
			}
			return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();	
			
		}
	
	@DELETE
	@Path("/{customerId}/deleteCustomer")
	public Response DeleteCustomer(@PathParam("customerId") String customerId){
		
		if(custServ.DeleteCustomer( Integer.parseInt(customerId))){
		
			return Response.ok().build();
		}
		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();	
		
	}
	
	@GET
	@Path("/ViewAllCustomers")
	public Response getCustomers(){	
		try {
			List<CustomerModel>custList = custServ.viewCustomers();
			if (custList!= null) {
				
				return Response.ok().entity(custList).build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();

	}
}

