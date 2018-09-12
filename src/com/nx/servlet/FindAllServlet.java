package com.nx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nx.Code.JWT;
import com.nx.domain.User;
import com.nx.service.AdminService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FindAllServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		AdminService admin=new AdminService();
		List<User> list=admin.findAll();
		JSONArray array=JSONArray.fromObject(list);
		response.getWriter().print(array);
		response.getWriter().close();//关闭输出流
		
	}

}
