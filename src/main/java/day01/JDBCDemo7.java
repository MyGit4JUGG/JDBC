package day01;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
/**
 * ��ɵ�¼����
 * Ҫ���û������û���������
 * ��userinfo�����ж�Ӧ���ݣ�����ʾ��½�ɹ�
 * ������ʾ��¼ʧ��
 * @author 1111
 *
 */
public class JDBCDemo7 {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("�������û�����");
		String username=s.nextLine();
		System.out.println("����������:");
		String password=s.nextLine();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			Statement state=conn.createStatement();
			String sql="SELECT ID,username,password,nickname "
					+ "FROM userinfo_LLF "
					+ "WHERE username='"+username+"'"
							+ "AND password='"+password+"'";
			System.out.println(sql);
			ResultSet rs=state.executeQuery(sql);
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
