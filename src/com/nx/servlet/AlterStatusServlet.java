package com.nx.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nx.domain.User;
import com.nx.service.AdminService;

import net.sf.json.JSONObject;

public class AlterStatusServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		AdminService admin=new AdminService();
		User user = new User();
		
		try {
		user.setPhone(request.getParameter("phone"));
		user.setStatus(Integer.valueOf(request.getParameter("status")));
		admin.alterStatus(user);
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
		
		JSONObject object=new JSONObject();
		object.put("msg", true);
		response.getWriter().print(object);
		response.getWriter().close();//关闭输出流
	}
}
