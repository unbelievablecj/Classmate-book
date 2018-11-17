package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Users;
import java.util.*;

public class dUsers extends Dao
{
	/**
	 * 测试用户名密码是否正确
	 * @param Student
	 * 实例类Student的实例
	 */
	public static boolean testUsers(Users user) {
		Connection conn = null;
		try 
		{
			String sno = user.getUsno();
			String spassword = user.getUspassword();
			conn = Dao.getConn();					//获取数据库连接
			//创建PrepareStatement对象，并传递SQL语句
			PreparedStatement Ps = conn.prepareStatement("select Uspassword from Users where Usno = ? ");
			Ps.setString(1, sno);					//为参数赋值
			ResultSet rs = Ps.executeQuery();		//执行SQL语句，获得查询结果集
			if (rs.next() && (rs.getRow() > 0)) 	//查询到用户信息
			{		
				String password = rs.getString(1).trim();	//获得密码
				if (password.equals(spassword)) {
					return true;					//密码正确返回true
				} else {
					JOptionPane.showMessageDialog(null, "密码不正确");
					System.out.println("用户密码不正确");
					return false;					//密码错误返回false
				}
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "用户不存在");
				return false;
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "数据库异常！\n"+e.getMessage());
			return false;
		} 
		finally 
		{
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}

	//查询单个用户
	public static Users getUsers(String Sno){

		Users user = new Users();
		user.setUsno(Sno);
		String sql = "select * from Users where Usno = ?";
		try {
			conn = Dao.getConn();					//获取数据库连接
			//创建PrepareStatement对象，并传递SQL语句
			PreparedStatement Ps = conn.prepareStatement(sql);
			Ps.setString(1, Sno);					//为参数赋值
			ResultSet rs = Ps.executeQuery();		//执行SQL语句，获得查询结果集
			if (rs.next() && rs.getRow() > 0) {	
				
				user.setUsno(rs.getString("Usno"));
				user.setUcname(rs.getString("Ucname"));
				user.setUsaddress(rs.getString("Usaddress"));
				user.setUsdiy(rs.getString("Usdiy"));
				user.setUsname(rs.getString("Usname"));
				user.setUspassword(rs.getString("Uspassword"));
				user.setUspost(rs.getString("Uspost"));
				user.setUstele(rs.getString("Ustele"));
				user.setUsyouxiangadd(rs.getString("Usyouxiangadd"));
				user.setUsqq(rs.getString("Usqq"));
				user.setUsweixin(rs.getString("Usweixin"));
			}
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "数据库异常！\n"+e.getMessage());
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		return user;
	}
	//班长查询全部同学
		public static List<Users> getallcUsers(String Ucname){
			java.util.List<Users> list = new ArrayList<Users>();
			try {
				getConn();
				stmt = conn.createStatement();
				String sql = "select * from Users where Ucname = '" + Ucname + "'";
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					Users user = new Users();
					user.setUsno(rs.getString("Usno"));
					user.setUcname(rs.getString("Ucname"));
					user.setUsaddress(rs.getString("Usaddress"));
					user.setUsdiy(rs.getString("Usdiy"));
					user.setUsname(rs.getString("Usname"));
					user.setUspassword(rs.getString("Uspassword"));
					user.setUspost(rs.getString("Uspost"));
					user.setUstele(rs.getString("Ustele"));
					user.setUsyouxiangadd(rs.getString("Usyouxiangadd"));
					user.setUsqq(rs.getString("Usqq"));
					user.setUsweixin(rs.getString("Usweixin"));		
					list.add(user);			
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				closeAll();
			}
			return list;
		}
	//辅导员查询全部
	public static List<Users> getallUsers(){
		java.util.List<Users> list = new ArrayList<Users>();
		try {
			getConn();
			stmt = conn.createStatement();
			String sql = "select * from Users";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Users user = new Users();
				user.setUsno(rs.getString("Usno"));
				user.setUcname(rs.getString("Ucname"));
				user.setUsaddress(rs.getString("Usaddress"));
				user.setUsdiy(rs.getString("Usdiy"));
				user.setUsname(rs.getString("Usname"));
				user.setUspassword(rs.getString("Uspassword"));
				user.setUspost(rs.getString("Uspost"));
				user.setUstele(rs.getString("Ustele"));
				user.setUsyouxiangadd(rs.getString("Usyouxiangadd"));
				user.setUsqq(rs.getString("Usqq"));
				user.setUsweixin(rs.getString("Usweixin"));		
				list.add(user);			
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeAll();
		}
		return list;
	}
	//添加同学信息
	public static int addUser(Users item) {
		int iRow = 0;
		try {
			getConn();
			String sql ="insert into Users(Usno,Uspassword,Uspost,Ucname,Usname,Usaddress,Usyouxiangadd,Ustele,Usweixin,UsQQ,Usdiy)values(?,?,?,?,?,?,?,?,?,?,?)";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, item.getUsno());
			pStmt.setString(2, item.getUspassword());
			pStmt.setString(3, item.getUspost());
			pStmt.setString(4, item.getUcname());
			pStmt.setString(5, item.getUsname());
			pStmt.setString(6, item.getUsaddress());
			pStmt.setString(7, item.getUsyouxiangadd());
			pStmt.setString(8, item.getUstele());
			pStmt.setString(9, item.getUsweixin());
			pStmt.setString(10, item.getUsqq());
			pStmt.setString(11, item.getUsdiy());
			iRow = pStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try{
				closeAll();
			}
			catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return iRow;
	}
	//修改同学信息
	public static int editUser(Users item) {
		int iRow = 0;
		try {
			getConn();
			String sql = "update Users set Ucname=?,Usname=?,Usaddress=?,Usyouxiangadd=?,Ustele=?,Usweixin=?,UsQQ=?,Usdiy=? where Usno=?";
			pStmt = conn.prepareStatement(sql);	
			pStmt.setString(1, item.getUcname());
			pStmt.setString(2, item.getUsname());
			pStmt.setString(3, item.getUsaddress());
			pStmt.setString(4, item.getUsyouxiangadd());
			pStmt.setString(5, item.getUstele());
			pStmt.setString(6, item.getUsweixin());
			pStmt.setString(7, item.getUsqq());
			pStmt.setString(8, item.getUsdiy());
			iRow = pStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try{
				closeAll();
			}
			catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return iRow;
	}
	//修改密码
	public static int changepw(Users item) {
		int iRow = 0;
		try {
			getConn();
			String sql = "update Users set Uspassword=? where Usno=?";
			pStmt = conn.prepareStatement(sql);	
			pStmt.setString(1, item.getUspassword());
			pStmt.setString(2, item.getUsno());
			iRow = pStmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try{
				closeAll();
			}
			catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return iRow;
	}
	//删除
	public static int delUsers(String Usno) {
		int iRow = 0;
		try {
			getConn();
			String sql = "delete from Users where Usno = ?";
			pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, Usno);
			iRow = pStmt.executeUpdate();
			System.out.println("delete Users");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try{
				closeAll();
			}
			catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return iRow;
	}
}
