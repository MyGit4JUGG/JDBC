package day03;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import day01.DBUtil;
/**
 * 返回自动主键
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
			 * 创建PS的同时指定执行该PS对应的SQL语句后腰得到
			 * 插入记录中指定字段的值。
			 */
			PreparedStatement ps=conn.prepareStatement(sql, new String[]{"deptno"});
			ps.setString(1, "IT");
			ps.setString(2, "Beijing");
			ps.executeLargeUpdate();
			/**获取插入的数据中指定字段的值*/
			ResultSet rs=ps.getGeneratedKeys();
			rs.next();
			int id=rs.getInt(1);/**获取第一个字段的值*/
			System.out.println("插入的部门ID是:"+id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnertion(conn);
		}
	}
}
