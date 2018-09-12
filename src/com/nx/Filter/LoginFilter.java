package com.nx.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nx.Code.JWT;
import com.nx.domain.User;

import net.sf.json.JSONObject;

public class LoginFilter implements Filter {
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httprequest=(HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse) response;
		HttpSession session=httprequest.getSession();	
		httpresponse.setCharacterEncoding("utf-8");  
        httpresponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        httpresponse.setHeader("Access-Control-Allow-Methods", "*");
        httpresponse.setHeader("Access-Control-Max-Age", "3600");
        httpresponse.setHeader("Access-Control-Allow-Headers", "Content-Type,Access-Token,Authorization");
        httpresponse.setHeader("Access-Control-Allow-Credentials","true");
        httpresponse.setHeader("Access-Control-Expose-Headers", "*");
        String Authorization=httprequest.getHeader("Authorization");
        String token = (String) JWT.unsign(Authorization, String.class);

        if(httprequest.getMethod().equals("OPTIONS")){
        	chain.doFilter(request,response);
        	return;
        }
        User user=(User)session.getAttribute("sessionUser");
        String phone= user.getPhone();
        if(phone!=null&&token!=null){
        if(phone.equals(token)){
        	chain.doFilter(request,response);
        }
        }
	}
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
