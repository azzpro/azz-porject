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
<link href="${basePath }/static/css/demo.css" rel="stylesheet" type="text/css" />

    <script src="${basePath }/static/scripts/boot.js" type="text/javascript"></script> 

    <style type="text/css">
  	table{ border-left: #ccc 3px solid; border-top: #ccc 3px solid; text-align: center;font-size: 14px;}
	table tr td{ border-bottom: #ccc 3px solid; border-right: #ccc 3px solid;padding:5px 5px; }
	hr{ border: lightblue 1px solid;}
    </style>
<title>首页</title>
</head>
<body>

	<button id="dataBtn">点击获取数据</button>
	<hr>
	<div id="content">
	</div>

</body>


<script type="text/javascript">
	$("#dataBtn").click(function(){
		$.ajax({
            type:"POST",
            url:"${basePath}"+"/azz/crawler/getBdsh5Titles",
            dataType:"json",
            success:function(result){
            	console.log(result);
            	if(result.code == 0){
            		var data = result.data;
            		var html_content = '';
            		for(var i = 0 ; i<data.length ;i++){
            			var firstTitle = data[i];
            			html_content += '<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td colspan="'+firstTitle.subTitles.length+'">'+firstTitle.name+'</td></tr>';
            			html_content += '<tr>'
            			var subTitles = firstTitle.subTitles;
            			for(var j = 0; j < subTitles.length; j++){
            				html_content+='<td>'+subTitles[j].name + '</td>'
            			}
            			html_content+='</tr></table><hr>';
            		}
            		$("#content").html(html_content);
            	}
            },
            error:function(jqXHR){
                console.log("Error: "+jqXHR.status);
            }
        });
	});
</script>



</html>