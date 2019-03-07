package web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import domain.User;
import service.UserService;
import utils.MailUtils;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");;
		
		/*String confirmpwd = request.getParameter("confirmpwd");
		String password = request.getParameter("password");
		if(!confirmpwd.equals(password)){
			request.setAttribute("codeInfo", "两次输入密码不一致！");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}*/
	
		Map<String, String[]> parameterMap = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		user.setUid(UUID.randomUUID().toString());
		String activeCode = UUID.randomUUID().toString();
		user.setCode(activeCode);
		user.setState(0);
		UserService service = new UserService();
		try {
			boolean isRe = service.register(user);
			if(isRe){
				String emailMsg = "恭喜您注册成功，点击下面链接激活账号"
						+ "<a href = 'http://localhost:8080/"+request.getContextPath()+"/active?"
						+ "activeCode="+activeCode+"'>"+activeCode+"</a>";
				try {
					MailUtils.sendMail(user.getEmail(), emailMsg);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.sendRedirect(request.getContextPath()+"/successful.jsp");
			}else{
				response.sendRedirect(request.getContextPath()+"/fail.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
