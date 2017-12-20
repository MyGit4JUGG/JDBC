package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import day01.DBUtil;

/**
 * 对指定的用户修改信息
 * 输入对应的用户名，然后以及要修改的密码将其修改
 * @author 1111
 *
 */
public class PrepearedStatementDemo3 {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("请输入用户名：");
		String username=s.nextLine();
		System.out.println("请输入要修改的密码：");
		String password=s.nextLine();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="UPDATE userinfo_LLF "
					+ "SET password=? "
					+ "WHERE username=? ";
		PreparedStatement ps=conn.prepareStatement(sql);
		ps.setString(1, password);
		ps.setString(2, username);	
		int d=ps.executeUpdate();
		if(d>0){
		System.out.println("修改成功");
		}else{
			System.out.println("修改失败");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnertion(conn);
		}
	}
}
