package day01;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * JDBC java��������
 * JDBC��SUN�ṩ��һ�����ڲ������ݿ�ı�׼�ӿڡ�
 * ��ͬ�����ݿ⳧�̶��ṩ��һ��JDBC�ӿڵ�ʵ������
 * ��������ṩ�����ݿ��Ʒ����һ��ʵ����ͨ�����
 * ��һ��jar���������������������
 * 
 * JDBC�ӿ����ṩ�ˣ�
 * DriverManager���������������������ݿ�����
 * Connection����ʾ�����ݿ�����ӣ��������ʵ��
 * 			����ִ��SQL���Ķ���Statement
 * Statement������ִ��SQL��䣬��ִ�е��ǲ�ѯ
 * 			��䣬��õ���ѯ�Ľ������
 * ResultSet����ʾ��ѯ�Ľ����������������ɻ�ȡ
 * 			��ѯ�ľ������ݡ�
 * 
 * @author Administrator
 *
 */
public class JDBCDemo1 {
	public static void main(String[] args) {
		Connection conn=null;
		try {
			/**
			 * 1����������
			 * ��ͬ�����ݿ⣬����ֵ��һ����
			 */
			Class.forName("oracle.jdbc.driver.OracleDriver");
			/**
			 * 2��������
			 * ���ݿ��ַ��ͬ�����ݿ��ʽ��һ��
			 */
			conn = DriverManager.getConnection(
				"jdbc:oracl:thin:@localhost:1521:xe",//���ݿ�ĵ�ַ
				"LF",//���ݿ���û���
				"317174787"//���ݿ������
			);
			System.out.println("�ѽ�������");		
			/**
			 * ͨ��Connection��������ִ��SQL����
			 * Statementʵ��
			 */
			Statement state=conn.createStatement();
			/**
			 * ����UserInfo��
			 * �ֶ�:
			 * id NUMBER(6)				//�û�ID
			 * username VARCHAR2(32)	//�û���
			 * password VARCHAR2(32)	//����
			 * nickname VARCHAR2(32)	//�ǳ�
			 * account NUMBER(10)		//�˻����
			 */
			String sql="CREATE TABLE UserInfo_LLF( "
					+ "id NUMBER(6)," 
					+ "username VARCHAR2(32)," 
					+ "password VARCHAR2(32)," 
					+ "nickname VARCHAR2(32)," 
					+ "account NUMBER(10)" 
					+ ")";
			System.out.println("sql");
			/**
			 * Statementִ��SQL������ط�����
			 * int execureUpdate(String sql)
			 * ר��������ִ��DML���ģ�����ֵΪִ�и����
			 * ��Ӱ���˱��ж�������¼��
			 * 
			 * ResultSet execureQuery(String sql)
			 * ר������ִ��DQL���ģ�����ֵΪ��ѯ�Ľ����
			 * ��ResuleSetʵ�����ء�
			 * 
			 * boolean execute(String sql)
			 * ����ִ���κ����͵�SQL��䣬������DML,DQL
			 * ����ר�ŵķ���ִ�У����Ը÷���һ������ִ��
			 * DDL��䣬����ֵΪִ�к��Ƿ��н������
			 */
			state.execute(sql);
			System.out.println("������ϣ�");
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
