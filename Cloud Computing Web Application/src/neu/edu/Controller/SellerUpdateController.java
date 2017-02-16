package neu.edu.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;

import neu.edu.Model.RestLogicalErrorException;
import neu.edu.Model.updateStatus;
import neu.edu.Service.SellerService;

@Controller
@Path("/seller/{orderitemId}")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SellerUpdateController {
	
	
	SellerService sellerServ = new SellerService();

	@PUT
	@Path("/ship")
	public Response MarkAsShipped(@PathParam("orderitemId")String orderitemId, updateStatus upstatus){
		
		try {
			if (sellerServ.updateStatus(upstatus, Integer.parseInt(orderitemId)) ){
				return Response.ok().build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();


	}
	
}
