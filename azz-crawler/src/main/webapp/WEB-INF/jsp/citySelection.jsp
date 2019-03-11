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
	
	.pic_center {
		position:absolute;
		left:50%;
		top:50%;
		margin-left:-10px;
		margin-top:-10px;
		
	}
	
	.layer{
		position:absolute;
		left:0%;
		top:0%;
		width:100%;
		height:100%;
		background: white;
		opacity: 0.2;
	}
	
    </style>
<title>选择城市</title>
</head>
<body>
	<div id="content">
	</div>
	<button id="submitCityBtn">选择城市</button>
</body>


<script type="text/javascript">

		$.ajax({
            type:"POST",
            url:"${basePath}"+"/azz/crawler/getGanjiCities",
            dataType:"json",
            cache: false, //禁用缓存   
			async: false,
            success:function(result){
            	if(result.code == 0){
            		var data = result.data;
            		var html_content = '<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr>';
            		for(var i = 0 ; i<data.length ;i++){
            			var eachCity = data[i];
            			if(i%12 == 0){ // 每12个换一次行
            				html_content+='</tr><tr>';
            			}else{
	            			html_content += '<td><input name="city" type="radio" value="'+eachCity.cityName+'_'+eachCity.cityUrl+'">'+eachCity.cityName+'</td>';
            			}
            		}
            		html_content+='</tr></table><hr>';
            		$("#content").html(html_content);
            	}
            },
            error:function(jqXHR){
                console.log("Error: "+jqXHR.status);
            }
        });
		
		
		$("#submitCityBtn").click(function(){
			var selectedRadio = $('input:radio:checked').val();
			if(!selectedRadio){//未选中任何城市
				alert("请选择城市");
				return;
			}else{
				var splitedCity = selectedRadio.split('_');
				var selectedCity ={
					"cityName" : splitedCity[0],
					"cityUrl" : splitedCity[1]
				}
				$.ajax({
		            type:"POST",
		            url:"${basePath}"+"/azz/crawler/selectCity",
		            dataType:"json",
		            contentType: "application/json; charset=utf-8",
		            data:JSON.stringify(selectedCity),
		            success:function(result){
		            	location.href = "${basePath}"+"/azz/crawler/ganji"
		            },
		            error:function(jqXHR){
		                console.log("Error: "+jqXHR.status);
		                alert("程序出错，请重试");
		            },
		        });
			}
		});
		
		
</script>



</html>