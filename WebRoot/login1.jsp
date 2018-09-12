<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
	function _change(){
		var ele=document.getElementById("vcode");
		ele.src="${pageContext.request.contextPath}/VerifyCodeServlet?xxx=" + new Date().getTime();
	}
</script>
</head>
<body>
<h1>登陆</h1>
<p style="color:red; font-weight=900">${msg}</p>
<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
用户名：<input type="text" name="username"/><br/>
密    码：<input type="password" name="password"/><br/>
验证码：<input type="text" name="verifycode" size="3">
<img id="vcode" src="${pageContext.request.contextPath}/VerifyCodeServlet"/>
<a href="javascript:_change()">换一张</a><br/>
<input type="submit" value="登陆"/><br/>
<a href="${pageContext.request.contextPath}/regist.jsp">还没有账号？点击这里注册</a>
</form>
</body>
</html>