<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	request.setCharacterEncoding("UTF-8");
	String context = request.getContextPath() ;
	request.setAttribute("basePath", context) ;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${basePath}/static/css/demo.css" rel="stylesheet" type="text/css" />

    <script src="${basePath}/static/scripts/boot.js" type="text/javascript"></script> 

    <style type="text/css">
    	#content{width: 600px;height: 300px;border: 1px solid black;margin:150px auto}
    </style>
<title>登录</title>
</head>
<body>
    <center id="content">
        <h1 style="color:red">爬虫登录</h1>
                <table border="0">
                    <tr>
                        <td>账号：</td>
                        <td><input id="username" type="text" name="username"></td>
                    </tr>
                    <tr>
                        <td>密码：</td>
                        <td><input id="password" type="password" name="password"></td>
                    </tr>
                </table>
            <br>
                <input type="button" id="loginBtn" value="登录" style="color:#BC8F8F;width: 80px">
    </center>
</body>

<script type="text/javascript">
	$("#loginBtn").click(function() {
		var username = $('#username').val();
		var password = $('#password').val();
		$.ajax({
            type:"POST",
            url:"${basePath}"+"/azz/crawler/doLogin",
            data: {'userName':username,'pwd':password},
            dataType:"json",
            cache: false, //禁用缓存   
			async: false,
            success:function(result){
            	window.location.href = "${basePath}"+"/azz/crawler/index";
            },
            error:function(jqXHR){
                console.log("Error: "+jqXHR.status);
            }
        });
	} );
	
</script>

</html>