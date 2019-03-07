package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductService;
public class AdminDelProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDelProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String pid = request.getParameter("pid");
			ProductService service  =new ProductService();
			service.delProductByPid(pid);
			response.sendRedirect(request.getContextPath()+"/adminProduct");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
