package com.nx.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nx.domain.Page;
import com.nx.domain.User;
import com.nx.service.AdminService;

import net.sf.json.JSONArray;

public class PageServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 1.获取当前页数
		 * 2.设置显示条数
		 * 3.根据adminservice的findbypage方法获得list
		 * 4.将list存入request域中；
		*/
		int pagenum=Integer.valueOf(request.getParameter("pagenum"));
		int pagesize=5;
		AdminService adminservice=new AdminService();
		Page<User> page=adminservice.findbypage(pagenum, pagesize);
		List<User> list=page.getList();
		JSONArray array=JSONArray.fromObject(list);
		response.getWriter().print(array);
		response.getWriter().close();//关闭输出流
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

}
