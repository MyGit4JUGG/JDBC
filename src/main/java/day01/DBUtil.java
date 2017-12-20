package day01;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
 
/**
 * ���ݿ����ӵĹ�����
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
			System.out.println("��ʼ�����ݿ�����...");
			System.out.println(driverclass);
			System.out.println(url);
			System.out.println(username);
			System.out.println(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡһ�����ݿ�����
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		try {
			/**
			 * �����ӳػ�ȡ����
			 * �����ӳ���û�п�������ʱ���÷�����������ǰ�߳�
			 * ����ʱ�������ӳ����õ�maxWait����������������
			 * �����ӳ����˿�������ʱ�����������ӷ��أ�����ʱ
			 * ��Ȼû�п�������ʱ���÷������׳��쳣��
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
				 * ���ӳص����Ӷ���close�����Ĵ�����
				 * �����������ӳ��е�״̬����Ϊ���ж�
				 * ����Ľ���رա�
				 */
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
