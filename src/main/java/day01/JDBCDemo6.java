package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 每页显示5条，显示emp表中第二页的员工信息
 * 要求员工信息按照工资的降序排列后查看第二页信息
 * @author 1111
 *
 */
public class JDBCDemo6 {
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("请输入每页要显示的条目数：");
		int pageSize=Integer.parseInt(s.nextLine());
		System.out.println("请输入要显示的页数：");
		int page=Integer.parseInt(s.nextLine());
		int start=(page-1)*pageSize+1;
		int end=pageSize * page;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="SELECT * "
					+ "FROM(SELECT ROWNUM rn,t.* "
					+ "		FROM(SELECT ename,sal,job,deptno "
					+ "			FROM emp_LLF "
					+ "			ORDER BY sal DESC) t "
					+ "		WHERE ROWNUM="+end+
					"		)"+
					"WHERE rn>="+start;
			System.out.println(sql);
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery(sql);
			while(rs.next()){
				String ename=rs.getString("ename");
				int sal=rs.getInt("sal");
				String job=rs.getString("job");
				int deptno=rs.getInt("deptno");
				System.out.println(ename+","+sal+","+job+","+deptno);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
