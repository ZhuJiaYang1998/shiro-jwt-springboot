<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Insert title here</title>
</head>
<body>
<h1>系统登录</h1>
<ul>
    <shiro:hasAnyRoles name="user,admin">
    <li><a href="">用户管理</a>
        <ul>
                <li><a href="">增加</a></li>
                <li><a href="">删除</a></li>
                <li><a href="">查询</a></li>
                <li><a href="">修改</a></li>
        </ul>
    </li>
    </shiro:hasAnyRoles>
    <shiro:hasRole name="admin">
        <li><a href="">商品管理</a> </li>
        <li><a href="">订单管理</a> </li>
        <li><a href="">物流管理</a> </li>
    </shiro:hasRole>
</ul>
</body>
</html>
