package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductService;
public class SubmitOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SubmitOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		String oid = request.getParameter("oid");
		ProductService service = new ProductService();
		service.updateOrder(name,address,telephone,oid);
		response.sendRedirect(request.getContextPath()+"/pay.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
