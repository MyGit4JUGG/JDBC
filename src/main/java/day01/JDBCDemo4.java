package day01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 * ����һ���û�����Ȼ�󽫸��û�ɾ��
 * @author 1111
 *
 */
public class JDBCDemo4 {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("������Ҫɾ�����û�����");
		String username=s.nextLine();
		Connection conn=null;
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe",
				"LF",
				"317174787"
				);
		Statement state=conn.createStatement();
		String sql="DELETE FROM userinfo_LLF "
				+ "WHERE username='"+username+"'";
		int d=state.executeUpdate(sql);
		if(d>0){
			System.out.println("�û�:"+username+"��ɾ��");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}