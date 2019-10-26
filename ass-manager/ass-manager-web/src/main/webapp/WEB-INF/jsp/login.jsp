<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="no-js">
    <head>
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/reset.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/supersized.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css">
    	
    </head>

    <body>
        <div class="page-container">
            <h1>Login</h1>
            <c:if test="${error!=null }">
            	<div style="position:relative; top:15px;color:red;">${error}</div>
            </c:if>
            <form action="/login" method="post">
                <input type="text" name="name" class="username" placeholder="Username">
                <input type="password" name="password" class="password" placeholder="Password">
                <input id="loginform:codeInput" style="width:180px" type="text" name="checkcode" title="请输入验证码" />
				<img style="position:relative; top:10px; left:13px;" id="loginform:vCode" src="/validatecode" onclick="javascript:document.getElementById('loginform:vCode').src='${pageContext.request.contextPath }/validatecode?'+Math.random();" />
                <button type="submit">Sign me in</button>
                
            </form>
        </div>
        <!-- Javascript -->
        <script src="<%=request.getContextPath()%>/assets/js/jquery-1.8.2.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/supersized.3.2.7.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/supersized-init.js"></script>
        <script src="<%=request.getContextPath()%>/assets/js/scripts.js"></script>
    </body>
</html>
