package dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import domain.User;
import utils.C3p0Utils;

public class UserDao {

	public int register(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		int update = runner.update(sql, user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),
				user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode());
		return update;
	}

	public User login(String username, String password) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select * from user where username=? and password=?";
		User user = runner.query(sql, new BeanHandler<User>(User.class), username,password);
		return user;
	}

	public Long checkUsername(String username) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql = "select count(*) from user where username=?";
		Long query = (Long) runner.query(sql, new ScalarHandler(), username);
		return query;
	}

	public int active(String activeCode) throws SQLException {
		QueryRunner runner = new QueryRunner(C3p0Utils.getDataSource());
		String sql ="update user set state = 1 where code =?";
		int update = runner.update(sql, activeCode);
		return update;
		
	}

}
