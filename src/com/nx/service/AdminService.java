package com.nx.service;

import java.util.List;

import com.nx.dao.AdminDAO;
import com.nx.domain.Page;
import com.nx.domain.User;

public class AdminService {
	public List<User> findAll(){
		AdminDAO admin=new AdminDAO();
		try {
			List<User> list=admin.find();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void alterStatus(User user){
		AdminDAO admin=new AdminDAO();
		try {
			admin.alterStatusbyphone(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Page<User> findbypage(int pagenum,int pagesize){
		
		/*
		 * 获得分页数据
		 * 1.依赖damindao对象的fingpage方法获得所有数据
		 * 2.获得所有数据的条数（amount）
		 * 3.使用三个参数构造page对象
		 * 4.调用admindao的findpage方法获得当前分页的数据并返回
		 */	
		AdminDAO admin=new AdminDAO();
		List<User> allmovie=admin.find();
		int amount=allmovie.size();
		
		Page<User> page=new Page<User>(pagenum,pagesize,amount);
		int startindex=page.getStartindex();
		
		List<User> list=admin.fingpage(startindex, pagesize);
		page.setList(admin.fingpage(startindex, pagesize)); 
		return page;
	}
	
	public void delete(String phone){
		AdminDAO admin=new AdminDAO();
			admin.delete(phone);
	}
}
