package com.nx.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nx.domain.User;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class AdminDAO {
	private static Connection getConn(){
		String username="root";
		String password="creatshare.2018";
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/nx";
		/*String username="root";
		String password="123456789";
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/demo?useSSL=false";*/
		Connection conn=null;
		
		try{
			Class.forName(driver);
			conn=(Connection)DriverManager.getConnection(url,username,password);
			
		}catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return conn;
	}
	
	//获得全部用户信息
	public List<User> find(){
		User user=new User();
		Connection cn=getConn();
		String sql="select * from user ";
		List<User> list=new ArrayList<User>();
		try {
			Statement stmt = cn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()){
				user.setPhone(rs.getString("phone"));
				user.setName(rs.getString("name"));
				user.setNumber(rs.getString("number"));
				user.setQq(rs.getString("qq"));
				user.setSex(Integer.valueOf(rs.getString("sex")));
				user.setGrade(rs.getString("grade"));
				user.setDirected(rs.getString("directed"));
				user.setStatus(Integer.valueOf(rs.getString("status")));
				list.add(user);
			}
			stmt.close();
	        cn.close();
	        return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//分页
	public List<User> fingpage(int startindex,int pagesize){
		Connection cn=getConn();
		String sql="select * from user limit " + startindex + "," + pagesize;
		List<User> list = new ArrayList<User>();  
		try {
			Statement stmt = cn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()) {  
				User user=new User();
				user.setPhone(rs.getString("phone"));
				user.setName(rs.getString("name"));
				user.setNumber(rs.getString("number"));
				user.setQq(rs.getString("qq"));
				user.setSex(Integer.valueOf(rs.getString("sex")));
				user.setGrade(rs.getString("grade"));
				user.setDirected(rs.getString("directed"));
				user.setStatus(Integer.valueOf(rs.getString("status")));
				list.add(user);
	        }
			stmt.close();
	        cn.close();
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//修改面试状态
	public void alterStatusbyphone(User user){
		Connection cn=getConn();
		String sql="update user set status='" + user.getStatus()
					+ "' where phone='" + user.getPhone() + "'";
		try {
			Statement st = cn.createStatement();
			st.executeUpdate(sql);
			st.close();
	        cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//删除用户（未通过笔试）
	public void delete(String phone){
		Connection cn=getConn();
		String sql="delete from user where phone='" + phone + "'";
		try {
			Statement st = cn.createStatement();
			st.executeUpdate(sql);
			st.close();
	        cn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
