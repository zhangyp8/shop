package web;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Category;
import domain.Product;
import service.ProductService;
public class ProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ProductInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		String cid = request.getParameter("cid");
		String currentPage = request.getParameter("currentPage");
		ProductService service = new ProductService();
		Product productInfo = service.finProductInfo(pid);
//		String cid = productInfo.getCid();
		Category categoryInfo = service.findCategoryByCid(cid);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("categoryInfo", categoryInfo);
		request.setAttribute("productInfo", productInfo);
//		历史纪录
		String pids = pid;
		Cookie[] cookies = request.getCookies();
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if ("pids".equals(cookie.getName())) {
					pids=cookie.getValue();
					String[] split = pids.split("-");
					List<String> asList = Arrays.asList(split);
					LinkedList<String> pidsList = new LinkedList<String>(asList);
					if (pidsList.contains(pid)) {
						pidsList.remove(pid);
					}
					pidsList.addFirst(pid);
					//将[3,1,2]转成3-1-2字符串
					StringBuffer sb = new StringBuffer();
					for(int i=0;i<pidsList.size()&&i<7;i++){
						sb.append(pidsList.get(i));
						sb.append("-");//3-1-2-
					}
					//去掉3-1-2-后的-
					pids = sb.substring(0, sb.length()-1);
				}
				
			}
			
		}
		
		Cookie cookie_pids = new Cookie("pids",pids);
		cookie_pids.setMaxAge(60*60*24);
		response.addCookie(cookie_pids);
		
		request.getRequestDispatcher("/product_info.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
