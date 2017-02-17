package neu.edu.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import neu.edu.Model.CustomerRegistrationReq;
import neu.edu.Model.RestLogicalErrorException;
import neu.edu.Model.SellerRegistrationReq;
import neu.edu.Service.SellerRegistrationService;

@Controller
@Path("/sellerReg")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class SellerRegController {

	@Autowired
	 SellerRegistrationService sellerServ;
	
	@POST
	public Response RegisterSeller(SellerRegistrationReq sellerRegReq){
		
		try {
			if (sellerServ.SellerRegister(sellerRegReq)) {
				return Response.ok().build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();

	}

	}
	

