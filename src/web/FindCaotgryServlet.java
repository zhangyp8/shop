package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.Category;
import redis.clients.jedis.Jedis;
import service.ProductService;
import utils.JedisPoolUtils;
public class FindCaotgryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FindCaotgryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		ProductService service = new ProductService();
		Jedis jedis = JedisPoolUtils.getJedis();
		
		String categoriesJson = jedis.get("categories");
		if (categoriesJson==null) {
			List<Category> categories = service.findCategory();
			Gson gson = new Gson();
			categoriesJson = gson.toJson(categories);
			jedis.set("categoriesJson", categoriesJson);
		}
		response.getWriter().write(categoriesJson);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
