package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Product;
import service.ProductService;
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService service = new ProductService();
		List<Product> hotProducts = service.findHotProduct();
		List<Product> newProducts = service.findNewProduct();
		request.setAttribute("hotProducts", hotProducts);
		request.setAttribute("newProducts", newProducts);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
