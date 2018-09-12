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

public class FindServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		UserService userservice=new UserService();
		
		
		User user=new User();
		User _user = null;
		try {
			user.setPhone(request.getParameter("phone"));
			_user = userservice.find(user);
		} catch (Exception e) {
			JSONObject object=new JSONObject();
			object.put("msg", false);
			response.getWriter().print(object);
			return;
		}
		JSONObject object=JSONObject.fromObject(_user);
		response.getWriter().print(object);
		
		
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

}
