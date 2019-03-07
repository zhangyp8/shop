package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ExitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		Cookie username_cookie = new Cookie("username_cookie","");
		Cookie password_cookie = new Cookie("password_cookie","");
		username_cookie.setMaxAge(0);
		password_cookie.setMaxAge(0);
		username_cookie.setPath(request.getContextPath());;
		password_cookie.setPath(request.getContextPath());
		response.addCookie(username_cookie);
		response.addCookie(password_cookie);
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
