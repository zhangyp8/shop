package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.PageBean;
import domain.Product;
import service.ProductService;

public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr==null)currentPageStr="1";
		int currentPage = Integer.parseInt(currentPageStr);
		ProductService service = new ProductService();
		PageBean<Product> pageBean = service.findProductList(cid,currentPage);
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		
		//定义一个记录历史商品信息的集合
				List<Product> historyProductList = new ArrayList<Product>();
				
				//获得客户端携带名字叫pids的cookie
				Cookie[] cookies = request.getCookies();
				if(cookies!=null){
					for(Cookie cookie:cookies){
						if("pids".equals(cookie.getName())){
							String pids = cookie.getValue();//3-2-1
							String[] split = pids.split("-");
							for(String pid : split){
								Product pro = service.finProductInfo(pid);
								historyProductList.add(pro);
							}
						}
					}
				}
				
				//将历史记录的集合放到域中
				request.setAttribute("historyProductList", historyProductList);
				
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
