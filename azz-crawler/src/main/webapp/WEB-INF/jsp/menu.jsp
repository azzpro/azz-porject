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
<title>选择菜单</title>
</head>
<body>
    <center id="content">
        <h1 style="color:lightblue;">点击下方按钮进行跳转</h1>
                <table border="0">
                    <tr>
                        <td><button id="bdsh5" onclick="window.open('${basePath}/azz/crawler/bdsh5')">本地生活网</button></td>
                    </tr>
                    <tr>
                        <td><button id="baixing" onclick="window.open('${basePath}/azz/crawler/baixing')">百姓网</button></td>
                    </tr>
                    <tr>
                       	<td><button id="ganji" onclick="window.open('${basePath}/azz/crawler/citySelection')">赶集网</button></td>
                    </tr>
                     <tr>
                       	<td><button id="testLogin">测试跳转</button></td>
                    </tr>
                    <tr>
                       	<td><button id="testAddToShoppingCart">测试加入购物车</button></td>
                    </tr>
                    
                </table>
            <br>
    </center>
</body>

<script type="text/javascript">

	function setCookie(c_name,value,expiredays)
	{
	var exdate=new Date()
	exdate.setDate(exdate.getDate()+expiredays)
	document.cookie=c_name+ "=" +escape(value)+
	((expiredays==null) ? "" : ";expires="+exdate.toGMTString())
	}
	
	function getCookie(c_name)
	{
	if (document.cookie.length>0)
	  {
	  c_start=document.cookie.indexOf(c_name + "=")
	  if (c_start!=-1)
	    { 
	    c_start=c_start + c_name.length+1 
	    c_end=document.cookie.indexOf(";",c_start)
	    if (c_end==-1) c_end=document.cookie.length
	    return unescape(document.cookie.substring(c_start,c_end))
	    } 
	  }
	return ""
	}
	
	$("#testLogin").click(function(){
		$.ajax({
            type:"POST",
            url:"${basePath}"+"/azz/crawler/testLogin",
            dataType:"json",
            cache: false, //禁用缓存   
			async: false,
            success:function(result){
            	var userToken = result.data;
            	setCookie("userToken",userToken,1);
            	var cookie = getCookie("userToken")
            	var url = "http://www.baidu.com?token="+cookie;
            	//window.location.href = url;
            },
            error:function(jqXHR){
                console.log("Error: "+jqXHR.status);
            }
        });
	});
	
	$("#testAddToShoppingCart").click(function(){
		var cookie = getCookie("userToken");
		//alert(cookie);
		$.ajax({
            type:"POST",
            headers:{
            	'token':cookie
            },
            url:"http://192.168.1.175:8081/hefa/api/client/member/getUserInfo?token="+cookie,
            dataType:"json",
            cache: false, //禁用缓存   
			async: true,
            success:function(result){
            	console.log(result);
            },
            error:function(jqXHR){
                console.log("Error: "+jqXHR.status);
            }
        });
	});
	
</script>

</html>