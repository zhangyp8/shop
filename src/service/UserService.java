package service;

import java.sql.SQLException;

import dao.UserDao;
import domain.User;

public class UserService {

	public boolean register(User user) throws SQLException {
		UserDao dao = new UserDao();
		int isRe = 0;
		isRe = dao.register(user);
		
		return isRe>0?true:false;
	}

	public User login(String username, String password) throws SQLException {
		UserDao dao = new UserDao();
		return dao.login(username,password);
	}

	public boolean checkUsername(String username) throws SQLException {
		UserDao dao = new UserDao();
		Long count = dao.checkUsername(username);
		if (count>0) {
			return true;
		}else{
			
			return false;
		}
	}

	public int active(String activeCode) throws SQLException {
		UserDao dao = new UserDao();
		return dao.active( activeCode);
		
	}

}
