package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * �������� seq_userinfo_id
 * ��1��ʼ������Ϊ1
 * @author 1111
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection(
					"jdbc:oracl:thin:@localhost:1521:xe",
					"LF",
					"317174787"
					);
			System.out.println("�ѽ������ӣ�");
			Statement state=conn.createStatement();
			String sql="CREATE SEQUENCE seq_userinfo_LLF " 
					+ "START WITH 1 "  
					+ "INCREMENT BY 1 " ;
			System.out.println("���д�����ϣ�");
			state.execute(sql);
			
			
			
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
