package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import day01.DBUtil;

/**
 * ������
 * �������ǿ��Խ�Ҫִ�еĴ��SQL��仺���ٱ��أ�Ȼ��
 * һ���Է��͸����ݿ⣬����������ã�����ִ��Ч�ʡ�
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
//				/**ÿ�ε���executeUpdate()���ᷢ�͸����ݿ�*/
//				state.executeLargeUpdate(sql);
//				state.addBatch(sql);/**�Ȼ��浽����*/
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
			System.out.println("ִ�����");
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
