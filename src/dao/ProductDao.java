package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.Category;
import domain.Order;
import domain.OrderItem;
import domain.Product;
import utils.C3p0Utils;

public class ProductDao {

	public List<Product> findHotProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from product where is_hot = ? limit ?,?";
		return runner.query(sql, new BeanListHandler<Product>(Product.class),1, 0,9);
	}

	public List<Product> findNewProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from product order by pdate desc limit ?,?";
		return runner.query(sql, new BeanListHandler<Product>(Product.class), 0,9);
	}

	public List<Category> findCategory() throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from category";
		return runner.query(sql, new BeanListHandler<Category>(Category.class));
	}

	public int findTotalCount(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select count(*) from product where cid = ?";
		Long query = (Long) runner.query(sql, new ScalarHandler(),cid);
		return query.intValue();
	}

	public List<Product> findProductByPage(String cid, int index, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from product where cid = ? limit ?,?";
		List<Product> query = runner.query(sql, new BeanListHandler<Product>(Product.class),cid,index,currentCount);
		return query;
	}

	public Product finProductInfo(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from product where pid = ?";
		Product product = runner.query( sql, new BeanHandler<Product>(Product.class),pid);
		return product;
	}

	public Category findCategoryByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from category where cid = ?";
		Category category = runner.query( sql, new BeanHandler<Category>(Category.class),cid);
		return category;
	}

	public void submitOrder(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		Connection connection = C3p0Utils.getCurrentConnection();
		runner.update(connection, sql, order.getOid(),order.getOrdertime(),order.getTotal(),order.getState(),
				order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid());
		
	}

	public void submitOrderItem(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		Connection connection = C3p0Utils.getCurrentConnection();
		List<OrderItem> orderItems = order.getOrderItems();
		for(OrderItem item : orderItems){
			runner.update(connection,sql,item.getItemid(),item.getCount(),item.getSubtotal(),item.getProduct().getPid(),item.getOrder().getOid());
		}
	}

	public List<Order> findOrdersByUid(String uid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from orders where uid = ?";
		List<Order> list = runner.query(sql, new BeanListHandler<Order>(Order.class), uid);
		return list;
	}

	public List<Map<String,Object>> findOrderItemsByOid(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select i.count,i.subtotal,p.pimage,p.pname,p.shop_price,p.pid from orderitem i,product p where i.pid=p.pid and i.oid=?";
		List<Map<String, Object>> list = runner.query(sql, new MapListHandler(), oid);
		return list;
	}

	public void updateOrder(String name, String address, String telephone,String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "update orders set state = 1,name = ?, address =?,telephone =? where oid = ?";
		runner.update(sql, name,address,telephone,oid);
	}

	public List<Product> findAllProduct() throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from product";
		List<Product> list = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}

	public void delProductByPid(String pid) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "delete from product where pid = ?";
		runner.update(sql, pid);
	}

	public Product findProductByName(String search) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from product where pname like ?";
		return runner.query(sql, new BeanHandler<Product>(Product.class),"%"+search+"%");
	}

	public void save(Product product) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		runner.update(sql, product.getPid(),product.getPname(),product.getMarket_price(),
				product.getShop_price(),product.getPimage(),product.getPdate(),
				product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid());
		
	}

	public void updateProduct(Product product) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "update product set pname = ?,is_hot = ?, shop_price =?,market_price =? ,pimage = ? ,cid = ? ,pdesc = ?"
				+ "where pid = ?";
		runner.update(sql, product.getPname(),product.getIs_hot(),product.getShop_price(),
				product.getMarket_price(),product.getPimage(),product.getCid(),product.getPdesc(),product.getPid());
	}
	
	
	
	
}
