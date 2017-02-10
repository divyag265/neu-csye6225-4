package neu.edu.Controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.Model.CustomerRegistrationReq;
import neu.edu.Model.ProductModel;
import neu.edu.Model.ProductRegReq;
import neu.edu.Model.RestLogicalErrorException;
import neu.edu.Service.ProductRegistrationService;

@Controller
@Path("/seller/{sellerId}")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)


public class ProductRegController {

	
ProductRegistrationService productServ = new ProductRegistrationService();
	
	@POST
	@Path("productReg")
	public Response AddProduct(@PathParam("sellerId") String sellerId,ProductRegReq productReq){
		
		try {
			if (productServ.ProductReg(productReq,Integer.parseInt(sellerId))) {
				return Response.ok().build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();

	}
	
//	
	
//	@DELETE
//	@Path("/{productId}/deleteProduct/"){
//		public Response DeleteProduct(@PathParam("sellerId") String sellerId @PathParam("productId") String productId){
//			
//			if(sellerServ.DeleteSeller( Integer.parseInt(sellerId))){
//			
//				return Response.ok().build();
//			}
//			return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();	
//			
//		} 
//	}
	
	
}
