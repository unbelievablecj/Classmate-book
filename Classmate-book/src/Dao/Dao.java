package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class Dao {

	@SuppressWarnings("unused")
	private static Dao dao = new Dao();			//声明Dao的静态实例
    public static Statement stmt=null;          //命令集对象  
    public static PreparedStatement pStmt=null; //预编译命令集对象  
    public static ResultSet rs=null;            //结果集对象 
    public static Connection conn = null;		//定义数据库连接
	/**
	 * 获得数据库连接的方法
	 * 
	 * @return Connection
	 */
	public static Connection getConn() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=classmate_book";	//数据库的url
			String username = "sa";	//用户名
			String password = "123456";	//密码
			conn = DriverManager.getConnection(url, username, password);	//建立连接
			return conn;
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "数据库连接失败"+ e.getMessage());
			return null;
		}
	}
	//关闭所有的数据库操作对象的方法  
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
