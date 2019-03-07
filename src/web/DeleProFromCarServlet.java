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
public class DeleProFromCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleProFromCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		HttpSession session = request.getSession();
		Car car = (Car) session.getAttribute("car");
		if (car!=null) {
			Map<String, CarItem> carItems = car.getCarItems();
			car.setTotalPrice(car.getTotalPrice()-carItems.get(pid).getSubTotal());
			carItems.remove(pid);
			car.setCarItems(carItems);
		}
		session.setAttribute("car", car);

		//跳转回cart.jsp
		response.sendRedirect(request.getContextPath()+"/cart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
