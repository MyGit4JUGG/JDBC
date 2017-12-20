package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import day01.DBUtil;

/**
 * 批操作
 * 批操作是可以将要执行的大概SQL语句缓存再本地，然后
 * 一次性发送给数据库，减少网络调用，调高执行效率。
 * @author 1111
 *
 */
public class JDBCDEMO_batch {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
//			Statement state=conn.createStatement();
//			for(int i=0;i<1000;i++){
//				String sql="INSERT INTO userinfo_LLF "
//						+ "(id,username,password,nickname,account) "
//						+ "VALUES "
//						+ "(seq_userinfo_LLF.NEXTVAL,'text'+i,'123456','text'+i,5000) ";
//				/**每次调用executeUpdate()都会发送给数据库*/
//				state.executeLargeUpdate(sql);
//				state.addBatch(sql);/**先缓存到批中*/
//			}
//			int[] data=state.executeBatch();
			conn.setAutoCommit(false);
			String sql="INSERT INTO userinfo_LLF "
					+ "(id,username,password,nickname,account) "
					+ "VALUES "
					+ "(seq_userinfo_LLF.NEXTVAL,?,'123456','text',5000) ";
			PreparedStatement ps=conn.prepareStatement(sql);
			int count=0;
			for(int i=0;i<1000;i++){
				ps.setString(1, "text"+i);
				//ps.execute();
				ps.addBatch();
				count++;
				if(count%50==0){
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			int[] data=ps.executeBatch();
			conn.commit();
			System.out.println("执行完毕");
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
