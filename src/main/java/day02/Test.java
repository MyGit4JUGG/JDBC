package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import day01.DBUtil;

/**
 * ת�˹���
 * ����ת���˺ŵ��û�����������ת���˺ŵ��û���
 * �������Ҫת�˵Ľ�����ת�˲���
 * @author 1111
 *
 */
public class Test {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("������ת���˺ŵ��û���:");
		String userOut=s.nextLine();
		System.out.println("������ת���˺ŵ��û���:");
		String userIn=s.nextLine();
		System.out.println("������ת�˽��:");
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
			 * JDBCĬ�����Զ��ύ����ģ�����ÿ��ִ��
			 * һ��DML�����󶼻��ύ����
			 * ��ϣ�����п���������Ҫ���Զ��ύ����ر�
			 */
			conn.setAutoCommit(false);
			
			/**ת������*/
			String sqlOut="UPDATE userinfo_LLF "
						 +"SET account=account-? "
						 + "WHERE username=? ";
			PreparedStatement psOut=conn.prepareStatement(sqlOut);
			psOut.setInt(1, money);
			psOut.setString(2, userOut);
			int d=psOut.executeUpdate();
			if(d>0){
			/**ת�����*/
			String sqlIn="UPDATE userinfo_LLF "
						+"SET account=account+ ?"
						+"WHERE username=? ";
			PreparedStatement psIn=conn.prepareStatement(sqlIn);
			psIn.setInt(1, money);
			psIn.setString(2, userIn);
			d=psIn.executeUpdate();
			if(d>0){
				System.out.println("ת�����");
				/**�ύ����*/
				conn.commit();
			}else{
				System.out.println("ת��ʧ��");
				/**�ع�����*/
				conn.rollback();
			}
		}else{
			System.out.println("ת��ʧ��");
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
