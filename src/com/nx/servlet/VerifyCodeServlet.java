package com.nx.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nx.Code.VerifyCode;

public class VerifyCodeServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		VerifyCode vc=new VerifyCode();
		BufferedImage image=vc.getImage();
		//保存验证码到session域(name=session_vcode)
		request.getSession().setAttribute("session_vcode",vc.getText());
		VerifyCode.output(image, response.getOutputStream());
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
}
