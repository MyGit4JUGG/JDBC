package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import day01.DBUtil;

/**
 * 转账功能
 * 输入转出账号的用户名：再输入转入账号的用户名
 * 最后输入要转账的金额完成转账操作
 * @author 1111
 *
 */
public class Test {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("请输入转出账号的用户名:");
		String userOut=s.nextLine();
		System.out.println("请输入转入账号的用户名:");
		String userIn=s.nextLine();
		System.out.println("请输入转账金额:");
		int money=Integer.parseInt(s.nextLine());
		Connection conn=null;
		/**
		 * UPDATE userinfo
		 * SET account=account-?
		 * WHERE username=?
		 * 
		 * psIn=
		 * UPDATE userinfo
		 * SET account=account+?
		 * WHERE username=?
		 */
		try {
			conn=DBUtil.getConnection();
			/**
			 * JDBC默认是自动提交事务的，即：每当执行
			 * 一条DML操作后都会提交事务。
			 * 若希望自行控制事务，需要将自动提交事务关闭
			 */
			conn.setAutoCommit(false);
			
			/**转出操作*/
			String sqlOut="UPDATE userinfo_LLF "
						 +"SET account=account-? "
						 + "WHERE username=? ";
			PreparedStatement psOut=conn.prepareStatement(sqlOut);
			psOut.setInt(1, money);
			psOut.setString(2, userOut);
			int d=psOut.executeUpdate();
			if(d>0){
			/**转入操作*/
			String sqlIn="UPDATE userinfo_LLF "
						+"SET account=account+ ?"
						+"WHERE username=? ";
			PreparedStatement psIn=conn.prepareStatement(sqlIn);
			psIn.setInt(1, money);
			psIn.setString(2, userIn);
			d=psIn.executeUpdate();
			if(d>0){
				System.out.println("转账完毕");
				/**提交事务*/
				conn.commit();
			}else{
				System.out.println("转账失败");
				/**回滚事务*/
				conn.rollback();
			}
		}else{
			System.out.println("转账失败");
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			if(conn!=null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally{
			DBUtil.closeConnertion(conn);
		}
		
		
		
		
	}
}
