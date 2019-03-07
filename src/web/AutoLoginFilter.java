package web;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.User;
import service.UserService;
public class AutoLoginFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String username_cookie = null;
		String password_cookie = null;
		Cookie[] cookies = req.getCookies();
		if(cookies!=null){
		for (Cookie cookie : cookies) {
			if("username_cookie".equals(cookie.getName())){
				username_cookie=cookie.getValue();
				username_cookie = URLDecoder.decode(username_cookie, "UTF-8");
			}
			if ("password_cookie".equals(cookie.getName())) {
				password_cookie = cookie.getValue();
			}
		}
	}
		if (password_cookie!=null&&username_cookie!=null) {
			UserService service = new UserService();
			User user =null;
			try {
				user = service.login(username_cookie,password_cookie);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			session.setAttribute("user", user);
		}
		
		chain.doFilter(req, resp);
	}
    public AutoLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
