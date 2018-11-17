package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class Dao {

	@SuppressWarnings("unused")
	private static Dao dao = new Dao();			//����Dao�ľ�̬ʵ��
    public static Statement stmt=null;          //�������  
    public static PreparedStatement pStmt=null; //Ԥ�����������  
    public static ResultSet rs=null;            //��������� 
    public static Connection conn = null;		//�������ݿ�����
	/**
	 * ������ݿ����ӵķ���
	 * 
	 * @return Connection
	 */
	public static Connection getConn() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=classmate_book";	//���ݿ��url
			String username = "sa";	//�û���
			String password = "123456";	//����
			conn = DriverManager.getConnection(url, username, password);	//��������
			return conn;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "���ݿ�����ʧ��"+ e.getMessage());
			return null;
		}
	}
	//�ر����е����ݿ��������ķ���  
    public static void closeAll() {  
        try {  
            if (rs!=null){  
                rs.close();  
                rs=null;  
            }  
            if (stmt!=null){  
                stmt.close();  
                stmt=null;  
            }  
            if (pStmt!=null){  
                pStmt.close();  
                pStmt=null;  
            }  
            if (conn!=null){  
                conn.close();  
                conn=null;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
