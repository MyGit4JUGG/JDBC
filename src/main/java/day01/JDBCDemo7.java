package day01;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
/**
 * 完成登录功能
 * 要求用户输入用户名和密码
 * 若userinfo表中有对应数据，则显示登陆成功
 * 否则显示登录失败
 * @author 1111
 *
 */
public class JDBCDemo7 {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("请输入用户名：");
		String username=s.nextLine();
		System.out.println("请输入密码:");
		String password=s.nextLine();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			Statement state=conn.createStatement();
			String sql="SELECT ID,username,password,nickname "
					+ "FROM userinfo_LLF "
					+ "WHERE username='"+username+"'"
							+ "AND password='"+password+"'";
			System.out.println(sql);
			ResultSet rs=state.executeQuery(sql);
			if(rs.next()){
				System.out.println("登录成功");
			}else{
				System.out.println("用户名和密码不正确");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnertion(conn);
		}
	}
}
