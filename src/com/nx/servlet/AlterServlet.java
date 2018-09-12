package com.nx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nx.domain.User;
import com.nx.service.UserService;

import net.sf.json.JSONObject;

public class AlterServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		request.setCharacterEncoding("utf-8");

		User user = new User();
		
		try {
		user.setPhone(((User)request.getSession().getAttribute("sessionUser")).getPhone());
		user.setSex(Integer.valueOf(request.getParameter("sex")));
		user.setNumber(request.getParameter("number"));
		user.setName(request.getParameter("name"));
		user.setGrade(request.getParameter("grade"));
		user.setQq(request.getParameter("qq"));
		user.setDirected(request.getParameter("directed"));
		
		UserService userservice=new UserService();
		userservice.Alter(user);
		} catch (NumberFormatException e) {
			JSONObject object=new JSONObject();
			object.put("msg", "AAA false");
			response.getWriter().print(object);
			return;
		}catch(Exception e1){
			JSONObject object=new JSONObject();
			object.put("msg", "BBB false");
			response.getWriter().print(object);
			return;
		}
		
		JSONObject object=new JSONObject();
		object.put("msg", true);
		response.getWriter().print(object);
		response.getWriter().close();//关闭输出流
	}
	public void doGET(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
}
