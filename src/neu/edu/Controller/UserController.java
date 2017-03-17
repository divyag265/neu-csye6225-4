package neu.edu.Controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import neu.edu.Entity.User;

import neu.edu.dao.UserDao;



@Controller
@Path("/user")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class UserController {

	@Autowired
	private UserDao userDao;

	
	@GET
	@Path("/{userName}")
	public Response ListUser(@PathParam("userName") String userName){
	
		User user = userDao.listUsers(userName);
		if(user==null){
			System.out.println("There is no such user");
		}
		else{
			return Response.ok().status(200).entity(user).build();
		}
		return null;
		
	}
	

}
	
	

