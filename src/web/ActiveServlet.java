package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
public class ActiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ActiveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String activeCode = request.getParameter("activeCode");
		UserService service = new UserService();
		int isActive = 0;
		try {
			isActive = service.active(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(isActive!=0){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else{
			response.getWriter().write("激活失败，请重新激活！");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
