package com.nx.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.nx.Code.PhoneCode;

import net.sf.json.JSONObject;

public class VerifyServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		/*
		 * 1.接受前端传来的手机号和验证码
		 * 2.判断验证码是否正确
		 * 3.如果验证码正确，则返回true并发送手机验证码
		 * 4.如果验证码不正确，则返回false（验证码错误）
		 * 
		*/
		String valid=request.getParameter("valid");
		
		//获取session域的验证码文本
		String text = (String)request.getSession().getAttribute("session_vcode");
		String phone=request.getParameter("phone");
		if(text==null){
			JSONObject object=new JSONObject();
			object.put("msg", false);
			object.put("mistake", "session没有验证码");
			response.getWriter().print(object);
			return;
		}
		
		//验证码错误时
		if(!text.equalsIgnoreCase(valid)){
			JSONObject object=new JSONObject();
			object.put("msg", false);
			object.put("mistake", "图片验证码输入有误");
			response.getWriter().print(object);
			return;
		}
		
		//验证码正确时
		JSONObject object=new JSONObject();
		object.put("msg", true);
		response.getWriter().print(object);
		//发送手机验证码
			PhoneCode code=new PhoneCode();
			String phone_code=code.getCode();
			request.getSession().setAttribute("phone_code",phone_code);//将文本存入session中
			com.nx.Code.JavaSmsApi.sendSms("48809fdf8978aee119b70a4143471973", "【创享实验室】您的验证码是" + phone_code + "。如非本人操作，请忽略本短信", phone);//发送短信
			response.getWriter().close();//关闭输出流
	}

}
