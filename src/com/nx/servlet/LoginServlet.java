package com.nx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nx.Code.JWT;
import com.nx.domain.User;
import com.nx.service.UserException;
import com.nx.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			/*
			 * 1.接受前端传来的数据
			 * 2.比对手机验证码是否正确
			 * 3.如果正确，判断数据库中是否有这个信息（是否第一次登陆）
			 * 4.如果是第一次，则直接跳转填写信息界面
			 * 5.如果不是第一次，直接进入个人界面
			 * 
			 */
			String phone=request.getParameter("phone");
			String code=request.getParameter("code");//用户输入的手机验证码
			
			String phone_code = (String)request.getSession().getAttribute("phone_code");
			UserService userservice=new UserService();
			User user=new User();
			if(phone_code==null){
				JSONObject object=new JSONObject();
				object.put("msg", 0);//0代表错误
				object.put("mistake", "没有找到验证码");
				response.getWriter().print(object);
				return;
			}
			if(!phone_code.equalsIgnoreCase(code)){
				JSONObject object=new JSONObject();
				object.put("msg", 0);//0代表错误
				object.put("mistake", "手机验证码输入有误");
				response.getWriter().print(object);
				return;
			}
			user.setPhone(phone);
			try {
				User _user=userservice.login(user);
				request.getSession().setAttribute("sessionUser", user);
				JSONObject object=JSONObject.fromObject(_user);
				object.put("msg", 2);//2代表成功登陆，用户存在
				String token = JWT.sign(phone, 7L * 24L * 3600L * 1000L);//加密token
				if (token != null) {
					object.put("token", token);
				}
				response.getWriter().print(object);
			} catch (UserException e) {
				request.getSession().setAttribute("sessionUser", user);
				JSONObject object=new JSONObject();
				object.put("msg", 1);//1代表成功登陆，用户不存在
				object.put("mistake", "用户不存在");
				String token = JWT.sign(phone, 7L * 24L * 3600L * 1000L);
				if (token != null) {
					object.put("token", token);
				}
				response.getWriter().print(object);
			}	
			response.getWriter().close();//关闭输出流
	}
}
