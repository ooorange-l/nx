package com.nx.service;

import com.nx.dao.UserDAO;
import com.nx.domain.User;

public class UserService {
	//查找
	public User find(User user){
		UserDAO userdao=new UserDAO();
		User _user = null;
		try {
			_user = userdao.findbyphone(user.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return _user;
	}
	//登陆
	public User login(User user) throws UserException{
		/*
		 * 1.检测该手机号是否存在，若存在，则直接返回该用户信息
		 * 2.若该手机号不存在，返回错误
		*/
		UserDAO userdao=new UserDAO();
		User _user = null;
		try {
			_user = userdao.findbyphone(user.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(_user==null) throw new UserException("用户不存在");
		return _user;
	}
	//注册
	public void Regist(User user){
		UserDAO userdao=new UserDAO();
		try {
			userdao.add(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//修改
	public void Alter(User user){
		UserDAO userdao=new UserDAO();
		try {
			userdao.alter(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
