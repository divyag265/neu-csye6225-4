package neu.edu.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

import neu.edu.Model.ProductModel;
import neu.edu.Model.RestLogicalErrorException;
import neu.edu.Service.ProductRegistrationService;



@Controller
@Path("/customer")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TopProductController {

	ProductRegistrationService productServ = new ProductRegistrationService();
	
	
	
	@GET
	@Path("/ViewTopSoldProduct")
	public Response GetTopProduct( ){
	
		try {
			ProductModel productModel = productServ.getTopProduct() ;
			if (productModel!= null) {
				
				return Response.ok().entity(productModel).build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();
}
}
