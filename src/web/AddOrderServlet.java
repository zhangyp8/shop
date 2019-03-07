package web;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Car;
import domain.CarItem;
import domain.Order;
import domain.OrderItem;
import domain.User;
import service.ProductService;

public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Order order = new Order();
		//判断是否登录
		User user = (User) session.getAttribute("user");
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
//		封装对象
		ProductService service = new ProductService();
		String oid = UUID.randomUUID().toString();
		order.setOid(oid);
		order.setOrdertime(new Date());
		Car car = (Car) session.getAttribute("car");
		double total = car.getTotalPrice();
		order.setTotal(total);
		order.setState(0);
		order.setUser(user);
		Map<String, CarItem> carItems = car.getCarItems();
		for (Map.Entry<String, CarItem> entry : carItems.entrySet()) {
			//取出每一个购物项
			CarItem carItem = entry.getValue();
			//创建新的订单项
			OrderItem orderItem = new OrderItem();
			//1)private String itemid;//订单项的id
			orderItem.setItemid(UUID.randomUUID().toString());
			//2)private int count;//订单项内商品的购买数量
			orderItem.setCount(carItem.getBuyNum());
			//3)private double subtotal;//订单项小计
			orderItem.setSubtotal(carItem.getSubTotal());
			//4)private Product product;//订单项内部的商品
			orderItem.setProduct(carItem.getProduct());
			//5)private Order order;//该订单项属于哪个订单
			orderItem.setOrder(order);

			//将该订单项添加到订单的订单项集合中
			order.getOrderItems().add(orderItem);
		}
				//order对象封装完毕
				//传递数据到service层
				
				service.submitOrder(order);

				session.setAttribute("order", order);
				session.removeAttribute("car");
				//页面跳转
				response.sendRedirect(request.getContextPath()+"/order_info.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
