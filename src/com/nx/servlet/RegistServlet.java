package com.nx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nx.domain.User;
import com.nx.service.UserService;

import net.sf.json.JSONObject;

public class RegistServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		
		//将用户输入的数据进行封装
		User user = new User();
		
			try {
			user.setPhone(((User)request.getSession().getAttribute("sessionUser")).getPhone());
			user.setSex(Integer.valueOf(request.getParameter("sex")));
			user.setNumber(request.getParameter("number"));
			user.setName(request.getParameter("name"));
			user.setGrade(request.getParameter("grade"));
			user.setQq(request.getParameter("qq"));
			user.setDirected(request.getParameter("directed"));
			user.setStatus(1);
		} catch (NumberFormatException e) {
			JSONObject object=new JSONObject();
			object.put("msg", false);
			response.getWriter().print(object);
			return;
		}catch(Exception e1){
			JSONObject object=new JSONObject();
			object.put("msg", false);
			response.getWriter().print(object);
			return;
		}
		UserService userservice=new UserService();
		try {
			userservice.Regist(user);
		} catch (Exception e) {
			JSONObject object=new JSONObject();
			object.put("msg", false);
			response.getWriter().print(object);
		}
		JSONObject object=JSONObject.fromObject(user);
		object.put("msg", true);
		response.getWriter().print(object);
		request.getSession().setAttribute("sessionUser", user);
		response.getWriter().close();//关闭输出流
	}
	
}
