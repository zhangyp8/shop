package web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import domain.Order;
import domain.OrderItem;
import domain.Product;
import domain.User;
import service.ProductService;

public class MyOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyOrdersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//判断是否登录
		User user = (User) session.getAttribute("user");
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		ProductService service = new ProductService();
		List<Order> orders = service.findOrdersByUid(user.getUid());
		if(orders!=null){
			for (Order order : orders) {
				String oid = order.getOid();
				List<Map<String,Object>> orderItems = service.findOrderItemsByOid(oid);
				for (Map<String, Object> map : orderItems) {
					try {
						OrderItem orderItem = new OrderItem();
						BeanUtils.populate(orderItem, map);
						Product product = new Product();
						BeanUtils.populate(product, map);
						orderItem.setProduct(product);
						order.getOrderItems().add(orderItem);
					} catch (IllegalAccessException | InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		request.setAttribute("orders", orders);
		
		request.getRequestDispatcher("/order_list.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
