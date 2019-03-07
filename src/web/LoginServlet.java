package web;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.UserService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");;
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserService service = new UserService();
		User user =null;
		try {
			user = service.login(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(user!=null){
			
			//是否勾选自动登录
			String autologin = request.getParameter("autologin");
			if (autologin!=null) {
				String username_encode = URLEncoder.encode(username, "UTF-8");
				Cookie username_cookie = new Cookie("username_cookie",username_encode );
				Cookie password_cookie = new Cookie("password_cookie",password );
				username_cookie.setMaxAge(60*60*24);
				password_cookie.setMaxAge(60*60*24);
				username_cookie.setPath(request.getContextPath());;
				password_cookie.setPath(request.getContextPath());
				response.addCookie(username_cookie);
				response.addCookie(password_cookie);
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath());
		}else{
			request.setAttribute("loginInfo", "用户名或密码错误！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
