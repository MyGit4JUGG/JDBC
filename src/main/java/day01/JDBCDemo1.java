package day01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * JDBC java数据连接
 * JDBC是SUN提供的一套用于操作数据库的标准接口。
 * 不同的数据库厂商都提供了一套JDBC接口的实现类用
 * 与操作其提供的数据库产品。这一套实现类通常会打
 * 成一个jar包发布，这个包叫驱动包
 * 
 * JDBC接口中提供了：
 * DriverManager：加载驱动并负责与数据库连接
 * Connection：表示与数据库的连接，负责管理实务，
 * 			创建执行SQL语句的对象Statement
 * Statement：用来执行SQL语句，若执行的是查询
 * 			语句，会得到查询的结果集。
 * ResultSet：表示查询的结果集，遍历结果集可获取
 * 			查询的具体数据。
 * 
 * @author Administrator
 *
 */
public class JDBCDemo1 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			/**
			 * 1加载驱动包
			 * 不同的数据库，参数值不一样。
			 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			/**
			 * 2建立连接
			 * 数据库地址不同的数据库格式不一样
			 */
			conn = DriverManager.getConnection(
				"jdbc:oracl:thin:@localhost:1521:xe",//数据库的地址
				"LF",//数据库的用户名
				"317174787"//数据库的密码
			);
			System.out.println("已建立连接");		
			/**
			 * 通过Connection创建用于执行SQL语句的
			 * Statement实例
			 */
			Statement state=conn.createStatement();
			/**
			 * 创建UserInfo表
			 * 字段:
			 * id NUMBER(6)				//用户ID
			 * username VARCHAR2(32)	//用户名
			 * password VARCHAR2(32)	//密码
			 * nickname VARCHAR2(32)	//昵称
			 * account NUMBER(10)		//账户余额
			 */
			String sql="CREATE TABLE UserInfo_LLF( "
					+ "id NUMBER(6)," 
					+ "username VARCHAR2(32)," 
					+ "password VARCHAR2(32)," 
					+ "nickname VARCHAR2(32)," 
					+ "account NUMBER(10)" 
					+ ")";
			System.out.println("sql");
			/**
			 * Statement执行SQL语句的相关方法：
			 * int execureUpdate(String sql)
			 * 专门用来长执行DML语句的，返回值为执行该语句
			 * 后影响了表中多少条记录。
			 * 
			 * ResultSet execureQuery(String sql)
			 * 专门用来执行DQL语句的，返回值为查询的结果集
			 * 用ResuleSet实例返回。
			 * 
			 * boolean execute(String sql)
			 * 可以执行任何类型的SQL语句，但由于DML,DQL
			 * 都有专门的方法执行，所以该方法一般用来执行
			 * DDL语句，返回值为执行后是否有结果集。
			 */
			state.execute(sql);
			System.out.println("表创建完毕！");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
