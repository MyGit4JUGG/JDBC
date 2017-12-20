package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import day01.DBUtil;

/**
 * Ԥ����SQL���
 * 
 * Statement�ʺ�ִ�о�̬SQL��䣬����SQL�����û��ƴ�Ӷ�̬����
 * PreparedStatement�ʺ�ִ�ж�̬SQL
 * 
 * ���ݿ����յ�SQL���ʱ��������SQL��䲢����һ����Ӧ��ִ�мƻ�
 * ������ִ�мƻ������ܴ�
 * �������ظ�����ͬ��SQL���ʱ�����ݿ���������ɵ�ִ�мƻ�������
 * ֻҪSQL������ж�̬���ݣ����¶���һ�£����ݲ�ͬ��Ҳ��������ִ��
 * �ƻ����޸����������µ�ִ�мƻ���Ϊ�ˣ������ж�̬���ݵ���������ͬ��
 * SQLҪ��ִ��ʱӦ��ʹ��Ԥ����SQL��
 * @author 1111
 *
 */
public class PrepearedStatementDemo1 {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("�������û�����");
		String username=s.nextLine();
		System.out.println("����������:");
		String password=s.nextLine();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			/**
			 * Ԥ����SQL����еĶ�̬���ݿ�����"��"����
			 */
			String sql="SELECT id,username,password,nickname,account "
					+ "FROM userinfo_LLF "
					+ "WHERE username=? "
					+ "AND password=? ";
		/**
		 * PreparedStatement�ڴ�����ʱ�����Ҫ��Ԥ�����SQL��䴫��
		 * �����͸����ݿ����ɶ�Ӧ��ִ�мƻ���	
		 */
		PreparedStatement ps=conn.prepareStatement(sql);	
		/**���ã���Ӧ��ֵ*/
		ps.setString(1,username);
		/**
		 * ���������а���SQL�����ַ���SQLע�빥����
		 * Ҳֻ�Ὣ�䵱�����뿴�����������ٸı�SQL���塣
		 */
		ps.setString(2,password);
		ResultSet rs=ps.executeQuery();
		if(rs.next()){
			System.out.println("��¼�ɹ�");
		}else{
			System.out.println("�û��������벻��ȷ");
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnertion(conn);
		}
		
		
	}
}
