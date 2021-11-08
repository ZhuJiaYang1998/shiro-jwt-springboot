<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
</head>
<body>
<h1>用户登录</h1>
<form action="user/login" method="post">
    用户名:<input type="text" name="username"> <br/>
    密码:<input type="text" name="password"> <br/>
    验证码：<input type="text" name="code" > <br/>
    <img src="<%=request.getContextPath()%>/user/getImge" alt=""><br/>
    <input type="submit" value="登录">
</form>
</body>
</html>
