package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;

import day01.DBUtil;

/**
 * 批量执行语义相同的SQL时，PS可以重用执行计划
 * 减少数据库开销
 * @author 1111
 *
 */
public class PrepearedStatementDemo2 {
	public static void main(String[] args) {
		/**
		 * 批量向userinfo表中插入1000条数据
		 * 批量操作影响数据库执行效率主要三个方面：
		 * 1：网络传输
		 * 2：执行计划的生成
		 * 3：事物管理
		 */
		Connection conn=null;
				try {
					conn=DBUtil.getConnection();
					String sql="INSERT INTO userinfo_LLF "
							+ "(id,username,password,nickname,account) "
							+ "VALUES "
							+ "(seq_userinfo_LLF.NEXTVAL,?,'123456',?,?) ";
				PreparedStatement ps=conn.prepareStatement(sql);
				for(int i=0;i<1000;i++){
					ps.setString(1, "text"+i);
					ps.setString(2, "text"+i);
					ps.setInt(3, 5000);
					ps.executeUpdate();//重用同一个执行计划1000次
				}
					System.out.println("插入完毕");
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					DBUtil.closeConnertion(conn);
				}
		
		
	}
}
