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
	 * �����û��������Ƿ���ȷ
	 * @param Student
	 * ʵ����Student��ʵ��
	 */
	public static boolean testUsers(Users user) {
		Connection conn = null;
		try 
		{
			String sno = user.getUsno();
			String spassword = user.getUspassword();
			conn = Dao.getConn();					//��ȡ���ݿ�����
			//����PrepareStatement���󣬲�����SQL���
			PreparedStatement Ps = conn.prepareStatement("select Uspassword from Users where Usno = ? ");
			Ps.setString(1, sno);					//Ϊ������ֵ
			ResultSet rs = Ps.executeQuery();		//ִ��SQL��䣬��ò�ѯ�����
			if (rs.next() && (rs.getRow() > 0)) 	//��ѯ���û���Ϣ
			{		
				String password = rs.getString(1).trim();	//�������
				if (password.equals(spassword)) {
					return true;					//������ȷ����true
				} else {
					JOptionPane.showMessageDialog(null, "���벻��ȷ");
					System.out.println("�û����벻��ȷ");
					return false;					//������󷵻�false
				}
			} 
			else 
			{
				JOptionPane.showMessageDialog(null, "�û�������");
				return false;
			}
		} 
		catch (Exception e) 
		{
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "���ݿ��쳣��\n"+e.getMessage());
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

	//��ѯ�����û�
	public static Users getUsers(String Sno){

		Users user = new Users();
		user.setUsno(Sno);
		String sql = "select * from Users where Usno = ?";
		try {
			conn = Dao.getConn();					//��ȡ���ݿ�����
			//����PrepareStatement���󣬲�����SQL���
			PreparedStatement Ps = conn.prepareStatement(sql);
			Ps.setString(1, Sno);					//Ϊ������ֵ
			ResultSet rs = Ps.executeQuery();		//ִ��SQL��䣬��ò�ѯ�����
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
			JOptionPane.showMessageDialog(null, "���ݿ��쳣��\n"+e.getMessage());
			
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
	//�೤��ѯȫ��ͬѧ
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
	//����Ա��ѯȫ��
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
	//���ͬѧ��Ϣ
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
	//�޸�ͬѧ��Ϣ
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
	//�޸�����
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
	//ɾ��
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
