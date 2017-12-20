package day01;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
 
/**
 * 数据库连接的管理类
 * @author 1111
 *
 */ 
public class DBUtil {
	
	private static BasicDataSource ds;
	static{
		try {
			Properties prop=new Properties();
			prop.load(new FileInputStream("config.properties"));
			String driverclass=prop.getProperty("driverclass");
			String url=prop.getProperty("url");
			String username=prop.getProperty("username");
			String password=prop.getProperty("password");
			int maxActive=Integer.parseInt(prop.getProperty("maxactive"));
			int maxWait=Integer.parseInt(prop.getProperty("maxwait"));
			ds=new BasicDataSource();
			ds.setDriverClassName(driverclass);
			ds.setUrl(url);
			ds.setUsername(username);
			ds.setPassword(password);
			System.out.println("初始化数据库连接...");
			System.out.println(driverclass);
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取一个数据库连接
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		try {
			/**
			 * 向连接池获取连接
			 * 若连接池中没有可用连接时，该方法会阻塞当前线程
			 * 阻塞时间由连接池设置的maxWait决定。当阻塞过程
			 * 中连接池由了可用连接时会立即将连接返回，若超时
			 * 仍然没有可用连接时，该方法会抛出异常。
			 */
			
			return ds.getConnection();
		}catch(SQLException e){
			throw e;
		}
	}
	public static void closeConnertion(Connection conn){
		try {
			if(conn!=null){
				conn.setAutoCommit(true);
				/**
				 * 连接池的连接对于close方法的处理是
				 * 将连接再连接池中的状态设置为空闲而
				 * 非真的将其关闭。
				 */
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
