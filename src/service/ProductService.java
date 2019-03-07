package service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import dao.ProductDao;
import domain.Category;
import domain.Order;
import domain.PageBean;
import domain.Product;
import utils.C3p0Utils;

public class ProductService {

	public List<Product> findHotProduct() {
		ProductDao dao = new ProductDao();
		try {
			return dao.findHotProduct();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Product> findNewProduct() {
		ProductDao dao = new ProductDao();
		try {
			return dao.findNewProduct();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Category> findCategory() {
		ProductDao dao = new ProductDao();
		try {
			return dao.findCategory();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public PageBean<Product> findProductList(String cid, int currentPage) {
		ProductDao dao = new ProductDao();
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setCurrentPage(currentPage);
		int currentCount = 12;
		pageBean.setCurrentCount(currentCount);
		int totalCount = 0;
		try {
			totalCount = dao.findTotalCount(cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		// 当前页与起始索引index的关系
		int index = (currentPage-1)*currentCount;
		List<Product> list=null;
		try {
			list = dao.findProductByPage(cid,index,currentCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pageBean.setList(list);
		return pageBean;
	}

	public Product finProductInfo(String pid) {
		ProductDao dao = new ProductDao();
		try {
			return dao.finProductInfo( pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public Category findCategoryByCid(String cid) {
		ProductDao dao = new ProductDao();
		try {
			return dao.findCategoryByCid(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void submitOrder(Order order) {
		ProductDao dao = new ProductDao();
		try {
			C3p0Utils.startThranscation();
			dao.submitOrder(order);
			dao.submitOrderItem(order);
		} catch (SQLException e) {
			try {
				C3p0Utils.rooback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				C3p0Utils.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public List<Order> findOrdersByUid(String uid) {
		ProductDao dao = new ProductDao();
		List<Order> orders = null ;
		try {
			orders = dao.findOrdersByUid(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	public List<Map<String, Object>> findOrderItemsByOid(String oid) {
		ProductDao dao = new ProductDao();
		List<Map<String, Object>> orderItems = null ;
		try {
			orderItems = dao.findOrderItemsByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderItems;
	}

	public void updateOrder(String name, String address, String telephone,String oid) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrder(name,address,telephone,oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public List<Product> findAllProduct() {
		ProductDao dao = new ProductDao();
		
		try {
			return dao.findAllProduct();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delProductByPid(String pid) {
		ProductDao dao = new ProductDao();
			try {
				dao.delProductByPid(pid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public Product findProductByName(String search) {
		try {
			return new ProductDao().findProductByName(search);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void saveProduct(Product product) {
		// TODO Auto-generated method stub
		try {
			new ProductDao().save(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateProduct(Product product) {
		try {
			new ProductDao().updateProduct(product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
