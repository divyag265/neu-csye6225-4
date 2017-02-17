package neu.edu.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.Entity.Orderitem;
import neu.edu.Entity.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import neu.edu.Entity.Seller;
import neu.edu.Model.CustomerRegistrationReq;
import neu.edu.Model.ProductModel;
import neu.edu.Model.ProductRegReq;
import neu.edu.Model.RestLogicalErrorException;
import neu.edu.dao.OrderItemDao;
import neu.edu.dao.ProductDao;
import neu.edu.dao.SellerDao;
import neu.edu.dao.UserDao;

@Service
public class ProductRegistrationService {
	
UserDao userDao = new UserDao();

	SellerDao sellerDao = new SellerDao();
	ProductDao productDao = new ProductDao();
	Seller seller=new Seller();
	OrderItemDao orderitemDao=new OrderItemDao();

public boolean ProductReg(ProductRegReq productReq, int sellerId) throws RestLogicalErrorException{	
	
	if(productReq.getName() ==null ||
			productReq.getPrice() ==null ||
			productReq.getQuantityAvail() ==null ||
			productReq.getDescription() ==null 
		
			){
		
		throw new RestLogicalErrorException("Registration Parameters incomplete.");
	}else{
		//Simulation a database Request
		
				
				Product product = new Product();
				product.setName(productReq.getName());
				product.setDescription(productReq.getDescription());
				product.setPrice(productReq.getPrice());
				product.setQuantityAvail(productReq.getQuantityAvail());
				
				System.out.println(sellerId);	
				
				seller=sellerDao.ListSeller(sellerId);				
				if(seller.getProducts()==null)
				{
					seller.setProducts(new HashSet<Product>());
				}
				seller.getProducts().add(product);
				
				product.setSeller(seller); 
				
				if(sellerDao.ListProduct(product.getName())==null){					
					sellerDao.AddProduct(product,sellerId);			
					System.out.println(product.getSeller().getSellerId());
					return true;
				}
				else{
					sellerDao.updateQuantity(sellerDao.ListProduct(product.getName()),product.getQuantityAvail());
					
				}
				return true;				
			}
}

public List<ProductModel> getProductList() throws RestLogicalErrorException{
		
	List<Product> productList = productDao.ListProduct();
	List<ProductModel>productModelList = new ArrayList<ProductModel>();
	
	if(productList.size()!=0){
	for(Product product:productList ){
		productModelList.add(new ProductModel(product.getProductId(),
				product.getName(),
	product.getDescription(),
	product.getPrice(),
	product.getQuantityAvail())
				);		
	}	}	
	else{
		RestLogicalErrorException authResponseErr = new RestLogicalErrorException("Invalid input");
		throw authResponseErr;
}	
	return productModelList;	
}



public ProductModel getTopProduct() throws RestLogicalErrorException{
	List<Orderitem> orderitemlist=orderitemDao.ListOrderitems();
	ProductModel productModel = new ProductModel();
	HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
	for(Orderitem orderitem:orderitemlist)
	{
		if(map.containsKey(orderitem.getProduct().getProductId()))
		{
			map.put(orderitem.getProduct().getProductId(), map.get(orderitem.getProduct().getProductId())+orderitem.getQuantityOrdered());
		}
		else
		{
			map.put(orderitem.getProduct().getProductId(),orderitem.getQuantityOrdered());
		}
		
		int max=0;
		int maxproductid = 0;
		for(Integer i:map.keySet())
		{	
			
			if(map.get(i)>max)
			{
				max=map.get(i);
				maxproductid=i;
			}
		}
		
		
		Product product=productDao.ListProduct(maxproductid);
		productModel.setName(product.getName());
		productModel.setDescription(product.getDescription());
		productModel.setPrice(product.getPrice());
		productModel.setProductId(product.getProductId());
		
		
	}
	System.out.print("THE TOP PRODUCT IS : ");
	System.out.println(productModel.getName());
	
	return productModel;
	
}

}




