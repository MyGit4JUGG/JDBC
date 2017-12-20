package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import day01.DBUtil;

/**
 * ��ָ�����û��޸���Ϣ
 * �����Ӧ���û�����Ȼ���Լ�Ҫ�޸ĵ����뽫���޸�
 * @author 1111
 *
 */
public class PrepearedStatementDemo3 {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("�������û�����");
		String username=s.nextLine();
		System.out.println("������Ҫ�޸ĵ����룺");
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
		System.out.println("�޸ĳɹ�");
		}else{
			System.out.println("�޸�ʧ��");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnertion(conn);
		}
	}
}
