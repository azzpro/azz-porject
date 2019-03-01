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
                        <td><button id="ganji" onclick="window.open('${basePath}/azz/crawler/ganji')">赶集网</button></td>
                    </tr>
                    <tr>
                        <td><button id="baixing" onclick="window.open('${basePath}/azz/crawler/baixing')">百姓网</button></td>
                    </tr>
                </table>
            <br>
    </center>
</body>

<script type="text/javascript">
	
</script>

</html>