package day01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * Ö¸¶¨DQLÓï¾ä
 * @author 1111
 *
 */
public class JDBCDemo5 {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe",
					"LF",
					"317174787");
			Statement state=conn.createStatement();
			String sql="SELECT id,username,password,nickname,account "
					+ "FROM userinfo_LLF";
			ResultSet rs=state.executeQuery(sql);
			while(rs.next()){
				int id=rs.getInt("id");
				String username=rs.getString("username");
				String password=rs.getString("password");
				String nickname=rs.getString("nickname");
				int account=rs.getInt("account");
				System.out.println(id+","+username+","+password+","+nickname);
			}
			
			
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
