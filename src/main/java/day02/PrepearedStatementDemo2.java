package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;

import day01.DBUtil;

/**
 * ����ִ��������ͬ��SQLʱ��PS��������ִ�мƻ�
 * �������ݿ⿪��
 * @author 1111
 *
 */
public class PrepearedStatementDemo2 {
	public static void main(String[] args) {
		/**
		 * ������userinfo���в���1000������
		 * ��������Ӱ�����ݿ�ִ��Ч����Ҫ�������棺
		 * 1�����紫��
		 * 2��ִ�мƻ�������
		 * 3���������
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
					ps.executeUpdate();//����ͬһ��ִ�мƻ�1000��
				}
					System.out.println("�������");
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					DBUtil.closeConnertion(conn);
				}
		
		
	}
}
