package day03;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import day01.DBUtil;
/**
 * �����Զ�����
 * @author 1111
 *
 */
public class JDBCDemo2 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="INSERT INTO dept_LLF "
					+ "(deptno,dname,loc) "
					+ "VALUES "
					+ "(seq_dept_LLF.NEXTVAL,?,?) ";
			/**
			 * ����PS��ͬʱָ��ִ�и�PS��Ӧ��SQL�������õ�
			 * �����¼��ָ���ֶε�ֵ��
			 */
			PreparedStatement ps=conn.prepareStatement(sql, new String[]{"deptno"});
			ps.setString(1, "IT");
			ps.setString(2, "Beijing");
			ps.executeLargeUpdate();
			/**��ȡ�����������ָ���ֶε�ֵ*/
			ResultSet rs=ps.getGeneratedKeys();
			rs.next();
			int id=rs.getInt(1);/**��ȡ��һ���ֶε�ֵ*/
			System.out.println("����Ĳ���ID��:"+id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnertion(conn);
		}
	}
}
