package neu.edu.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import neu.edu.Entity.Orderitem;
import neu.edu.Entity.Product;
import neu.edu.Model.OrderItemModel;
import neu.edu.Model.ProductModel;
import neu.edu.Model.RestLogicalErrorException;
import neu.edu.dao.OrderItemDao;
import neu.edu.dao.ProductDao;
import neu.edu.Model.updateStatus;

@Service
public class SellerService {

	ProductDao productDao = new ProductDao();
	OrderItemDao orderitemDao = new OrderItemDao();
	
	public List<OrderItemModel> ViewOrderItems(int sellerId)throws RestLogicalErrorException{
		
		List<OrderItemModel> orderItemModelList=new ArrayList<OrderItemModel>();
		List<Product> productList = productDao.ListProducts(sellerId);
		for(Product product:productList)
		{
			for(Orderitem orderitem:product.getOrderitems())
			{	if(orderitem.getStatus()==null)
				{}
				else if(orderitem.getStatus().equals("Ordered/Paid"))
				{
					OrderItemModel orderitemModel = new OrderItemModel();
					orderitemModel.setOrderId(orderitem.getOrder().getOrderId());
					orderitemModel.setOrderitemId(orderitem.getOrderItemId());
					orderitemModel.setProductId(orderitem.getProduct().getProductId());
					orderitemModel.setQuantityOrdered(orderitem.getQuantityOrdered());
					orderitemModel.setStatus(orderitem.getStatus());
					orderitemModel.setTotal(orderitem.getTotal());
					orderitemModel.setName(orderitem.getProduct().getName());					
					orderitemModel.setPrice(orderitem.getProduct().getPrice());
					orderitemModel.setDesc(orderitem.getProduct().getDescription());				
					orderItemModelList.add(orderitemModel);
					
				}
			}
		}		
		
		
		return orderItemModelList;
	
	}
	
	public List<ProductModel> getProductListforSeller(int sellerId) throws RestLogicalErrorException{
		
		List<Product> productList = productDao.ListProducts(sellerId);
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
	
	public boolean updateStatus(updateStatus upStatus, int orderitemId)throws RestLogicalErrorException{
		
	if(!orderitemDao.updateStatus(upStatus.getStatus(), orderitemId)){
		throw new RestLogicalErrorException("Duplicate");
	}
		return true;
}
}
