package utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Utils {
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
//	绑定事务
	public static Connection getCurrentConnection(){
		Connection connection = threadLocal.get();
		if(connection==null){
			connection=getConnection();
			threadLocal.set(connection);
		}
		return connection;
	}
	
//	开启事务
	public static void startThranscation() throws SQLException{
		Connection connection = getCurrentConnection();
		connection.setAutoCommit(false);
	}
	
//	回滚事务
	
	public static void rooback() throws SQLException{
		getCurrentConnection().rollback();
	}
//	提交事务
	public static void commit() throws SQLException{
		Connection connection = getCurrentConnection();
		connection.commit();
		threadLocal.remove();
		connection.close();
	}
	
	
	public static DataSource getDataSource(){
		return dataSource;
	}
	
	public static Connection getConnection(){
		try {
			Connection connection = dataSource.getConnection();
			return connection;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
