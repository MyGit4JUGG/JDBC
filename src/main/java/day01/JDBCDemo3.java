package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthStyle;

/**
 * 向表中插入一条记录
 * @author 1111
 *
 */
public class JDBCDemo3 {
	public static void main(String[] args) {
		try {
			/**
			 * 要求用户输入用户名，密码，昵称
			 * 将该用户存入userinfo表
			 * 账户余额默认都是5000
			 */
			Scanner s=new Scanner(System.in);
			System.out.println("请输入用户名：");
			String username=s.nextLine();
			System.out.println("请输入密码:");
			String password=s.nextLine();
			System.out.println("请输入昵称:");
			String nickname=s.nextLine();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"LF",
					"317174787"
					);
			System.out.println("已建立连接！");
			Statement state=conn.createStatement();
			String sql="INSERT INTO UserInfo_LLF "
					+ "(id,username,password,nickname,account) "
					+ "VALUES "
					+ "(seq_userinfo_LLF.NEXTVAL, '"+username+"','"+password+"','"+nickname+"',5000)";
			System.out.println(sql);
						int d=state.executeUpdate(sql);
			if(d>0){
				System.out.println(d+"行已插入");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
