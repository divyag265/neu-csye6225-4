package neu.edu.Controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

import neu.edu.Model.Cart;
import neu.edu.Model.OrderItemModel;
import neu.edu.Model.ProductModel;
import neu.edu.Model.RestLogicalErrorException;
import neu.edu.Model.updateStatus;
import neu.edu.Service.CustomerService;
import neu.edu.Service.ProductRegistrationService;
import neu.edu.Service.SellerService;


@Controller
@Path("/seller/{sellerId}")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SellerController {
	
	ProductRegistrationService productServ = new ProductRegistrationService();
	
	CustomerService custServ = new CustomerService();
	SellerService sellerServ = new SellerService();

	@GET
	@Path("/ViewProducts")
	public Response GetProducts(@PathParam("sellerId")String sellerId ){
	
		try {
			List<ProductModel>productList = sellerServ.getProductListforSeller(Integer.parseInt(sellerId));
			if (productList!= null) {
				
				return Response.ok().entity(productList).build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();
}
	@GET
	@Path("/ViewOrderitems")
	public Response GetOrderItems(@PathParam ("sellerId") String sellerId){
		
		try {
			
			List<OrderItemModel>orderItemModel = sellerServ.ViewOrderItems(Integer.parseInt(sellerId));
			
			if (orderItemModel!= null) {
				
				return Response.ok().entity(orderItemModel).build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();
		
	}
	

}
	

