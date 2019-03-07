package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class CheckCodeServet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CheckCodeServet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		String check_code = (String) request.getSession().getAttribute("checkcode_session");
		boolean isRight = true;
		if (!code.equals(check_code)) {
			isRight = false;
		}
		String json = "{\"isRight\":"+isRight+"}";
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
