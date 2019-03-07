package web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
public class CheckUsernameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CheckUsernameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		UserService service = new UserService();
		boolean isExsit = false;
		try {
			isExsit = service.checkUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().write("{\"isExist\":"+isExsit+"}");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
