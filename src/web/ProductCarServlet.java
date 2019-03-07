package web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Car;
import domain.CarItem;
import domain.Product;
import service.ProductService;
public class ProductCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		获取数据
		String pid = request.getParameter("pid");
		String buyNumStr = request.getParameter("buyNum");
		int buyNum = Integer.parseInt(buyNumStr);
		ProductService service = new ProductService();
		Product product = service.finProductInfo(pid);
		double subTotal = buyNum*product.getShop_price();
//		封装数据
		CarItem item = new CarItem();
		item.setBuyNum(buyNum);
		item.setProduct(product);
		item.setSubTotal(subTotal);
//		将数据存到session域中
		HttpSession session = request.getSession();
		Car car = (Car) session.getAttribute("car");
		if(car==null){
			car = new Car();
		}
		Map<String, CarItem> carItems = car.getCarItems();
		//重复添加同一件商品
		double newSubTotal = 0.0;
		if (carItems.containsKey(pid)) {
			CarItem carItem = carItems.get(pid);
			int oldBuyNum = carItem.getBuyNum();
			oldBuyNum+=buyNum;
			carItem.setBuyNum(oldBuyNum);
			double oldSubTotal = carItem.getSubTotal();
			newSubTotal = buyNum*product.getShop_price();
			carItem.setSubTotal(oldSubTotal+newSubTotal);
			car.setCarItems(carItems);
		}else{
			//如果车中没有该商品
			car.getCarItems().put(product.getPid(), item);
			newSubTotal = buyNum*product.getShop_price();
		}

		//计算总计
		double total = car.getTotalPrice()+newSubTotal;
		car.setTotalPrice(total);


		//将车再次访问session
		session.setAttribute("car", car);

		//直接跳转到购物车页面
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
