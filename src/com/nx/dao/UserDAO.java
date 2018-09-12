package com.nx.dao;

import java.sql.*;

import com.nx.domain.User;

public class UserDAO {
	//获取连接Connection
		private static Connection getConn(){
			String username="root";
			String password="creatshare.2018";
			String driver="com.mysql.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/nx";
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
		
		//根据手机号查找用户
		public User findbyphone(String phone){
			User user=new User();
			Connection cn=getConn();
			String sql="select * from user where phone='" + phone + "'";
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
					stmt.close();
			        cn.close();
					return user;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//增加新用户
		public void add(User user){
			Connection cn=getConn();
			String sql="insert into user(phone,name,sex,qq,number,grade,directed,status) values('" 
						 + user.getPhone() + "','" + user.getName()
						 + "'," + user.getSex() + ",'" + user.getQq()
						 + "','" + user.getNumber() + "','" + user.getGrade()
						 + "','" + user.getDirected() + "'," + user.getStatus() + ")";
			try {
				Statement st=cn.createStatement();
				st.executeUpdate(sql);
				st.close();
		        cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		public void alter(User user){
			Connection cn=getConn();
			String sql="update user set name='" + user.getName() + "',sex="
					+ user.getSex() + ",qq='" + user.getQq() + "',number='" 
					+ user.getNumber() + "',grade='" + user.getGrade()+ "',directed='"
					+ user.getDirected() + "' where phone='"
					+ user.getPhone() + "'";
			try {
				Statement st=cn.createStatement();
				st.executeUpdate(sql);
				st.close();
		        cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
}
