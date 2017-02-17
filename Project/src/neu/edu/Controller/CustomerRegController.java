package neu.edu.Controller;

import javax.ws.rs.PathParam;

import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



import javax.ws.rs.core.Response;


import neu.edu.Entity.Customer;
import neu.edu.Entity.Order;
import neu.edu.Entity.Orderitem;
import neu.edu.Entity.Product;
import neu.edu.Model.Cart;
import neu.edu.Model.Cart1;
import neu.edu.Model.Cart3;
import neu.edu.Model.CustomerRegistrationReq;
import neu.edu.Model.OrderModel;
import neu.edu.Model.OrderModel1;
import neu.edu.Model.ProductModel;
import neu.edu.Service.CustomerRegistrationService;
import neu.edu.Service.CustomerService;
import neu.edu.Service.ProductRegistrationService;
import neu.edu.Model.RestLogicalErrorException;
import neu.edu.dao.CustomerDao;
import neu.edu.dao.UserDao;

@Controller
@Path("/customer")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CustomerRegController {

	@Autowired
	private CustomerRegistrationService custRegServ;
	
	@Autowired
	private CustomerService customerServ;
	
	@Autowired
	ProductRegistrationService prodServ;

	private Logger LOG=Logger.getLogger(CustomerRegController.class.getName());
	
	@POST
	@Path("CustomerRegistration")
	public Response RegisterCust(CustomerRegistrationReq custRegReq){
		
		try {
			if (custRegServ.CustomerRegister(custRegReq)) {
				return Response.ok().build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();

	}
	
	@GET
	@Path("/{customerId}/ViewAllProducts")
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
	@GET
	@Path("/{customerId}/ViewOrders")
	public Response ViewOrders(@PathParam("customerId")String customerId){
		try{
			List<OrderModel1>orderList = customerServ.viewOrder(Integer.parseInt(customerId));
			if(orderList!=null){
				return Response.ok().entity(orderList).build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();
		
	}
		
	@GET
	@Path("/{customerId}/ViewCart")
	public Response ViewCart(@PathParam("customerId")String customerId){
		
		try{
			List<Cart1>cartList = customerServ.viewCart(Integer.parseInt(customerId));
			if(cartList!=null){
				return Response.ok().entity(cartList).build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();
				
			}
		
	

	@POST	
	@Path("/{customerId}/AddToCart")
	public Response AddtoCart(@PathParam("customerId") String customerId, Cart3 cart){
		
		try {
			if (customerServ.AddToCart(cart, Integer.parseInt(customerId)) ){
				return Response.ok().build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();

	
	}
	
	@PUT
	@Path("/{customerId}/UpdateDetails")
	public Response UpdateDetails(@PathParam("customerId") String customerId){
		return null;
		
	}

	
	
	@POST
	@Path("/{customerId}/ViewOrders/{orderId}/Checkout")
	public Response CheckOut(@PathParam("customerId")String customerId,@PathParam("orderId")String orderId){
		try {
			if (customerServ.CheckOut( Integer.parseInt(customerId), Integer.parseInt(orderId)) ){
				return Response.ok().build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();

	}
	
	

	@POST
	@Path("/{customerId}/Checkout")
	public Response CheckOut(@PathParam("customerId")String customerId){
			
		try {
			if (customerServ.CheckOut( Integer.parseInt(customerId)) ){
				return Response.ok().build();
			}
		} catch (RestLogicalErrorException re) {
			return Response.ok().status(422).entity(re.getResponseError()).build();
		}

		return Response.ok().status(422).entity(new RestLogicalErrorException("Unknown Error. Please try again")).build();

	}
	
	@GET
	@Path("/ViewAllProducts")
	public Response getProductsHome(){	
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
	
	

	}
		