package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthStyle;

/**
 * ����в���һ����¼
 * @author 1111
 *
 */
public class JDBCDemo3 {
	public static void main(String[] args) {
		try {
			/**
			 * Ҫ���û������û��������룬�ǳ�
			 * �����û�����userinfo��
			 * �˻����Ĭ�϶���5000
			 */
			Scanner s=new Scanner(System.in);
			System.out.println("�������û�����");
			String username=s.nextLine();
			System.out.println("����������:");
			String password=s.nextLine();
			System.out.println("�������ǳ�:");
			String nickname=s.nextLine();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"LF",
					"317174787"
					);
			System.out.println("�ѽ������ӣ�");
			Statement state=conn.createStatement();
			String sql="INSERT INTO UserInfo_LLF "
					+ "(id,username,password,nickname,account) "
					+ "VALUES "
					+ "(seq_userinfo_LLF.NEXTVAL, '"+username+"','"+password+"','"+nickname+"',5000)";
			System.out.println(sql);
						int d=state.executeUpdate(sql);
			if(d>0){
				System.out.println(d+"���Ѳ���");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
