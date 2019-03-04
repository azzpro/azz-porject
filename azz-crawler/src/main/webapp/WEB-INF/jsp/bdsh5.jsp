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
<title>本地生活网</title>
</head>
<body>
	<div id="content">
	</div>
	<button id="submitTitles">提交爬虫的标题关键字</button>
	
	<button id="downloadBtn" onclick="window.open('${basePath}/azz/crawler/exportBdsh5Data')" style="display: none;">导出爬取数据</button>
	
	<div class="layer" style="display: none;"></div>
	<img alt="加载中" width="20px" height="20px" src="/static/images/loading.gif" class="pic_center" style="display: none;">
</body>


<script type="text/javascript">

		$.ajax({
            type:"POST",
            url:"${basePath}"+"/azz/crawler/getBdsh5Titles",
            dataType:"json",
            cache: false, //禁用缓存   
			async: false,
            success:function(result){
            	//console.log(result);
            	if(result.code == 0){
            		var data = result.data;
            		var html_content = '';
            		for(var i = 0 ; i<data.length ;i++){
            			var firstTitle = data[i];
            			html_content += '<table width="100%" border="0" cellspacing="0" cellpadding="0"><tr><td colspan="'+firstTitle.subTitles.length+'"><input class="cb" type="checkbox" value="'+firstTitle.name+'_'+firstTitle.url+'">'+firstTitle.name+'</td></tr>';
            			html_content += '<tr>'
            			var subTitles = firstTitle.subTitles;
            			for(var j = 0; j < subTitles.length; j++){
            				html_content+='<td><input class="cb" type="checkbox" value="'+subTitles[j].name+'_'+subTitles[j].url+'">'+subTitles[j].name + '</td>'
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
		$("#submitTitles").click(function(){
			var selectedCheckboxes = $('input[type=checkbox]:checked');
			var selectedTitles = '';
			for (var i = 0; i < selectedCheckboxes.length; i++) {
				if(i == 0){
					selectedTitles = selectedCheckboxes[i].value;
				}else{
					selectedTitles += ','+selectedCheckboxes[i].value;
				}
			}
			if(selectedTitles.length == 0){//未选中任何标题
				alert("请选中需要爬取数据的标题");
				return;
			}else{
				$("#submitTitles").attr("disabled","disabled");
				$(".pic_center").css("display","block");
				$(".layer").css("display","block");
				var titles = selectedTitles.split(',');
				var postData = [];
				for(var i = 0; i < titles.length; i++){
					var splitedTitle = titles[i].split('_');
					var eachData ={
						"name" : splitedTitle[0],
						"url" : splitedTitle[1]
					}
					postData.push(eachData);
				}
				$.ajax({
		            type:"POST",
		            url:"${basePath}"+"/azz/crawler/getBdsh5SearchInfoByTitle",
		            dataType:"json",
		            contentType: "application/json; charset=utf-8",
		            data:JSON.stringify(postData),
		            success:function(result){
		            	//console.log(result);
		            },
		            error:function(jqXHR){
		                console.log("Error: "+jqXHR.status);
		                $("#downloadBtn").hide();
		                alert("爬取出错，请重试");
		            },
		            complete:function(XMLHttpRequest,textStatus){
		            	if(XMLHttpRequest.status == 200){
			                $("#downloadBtn").css("display","inline");
		            	}
			            $(".layer").css("display","none");
				        $(".pic_center").css("display","none");
			            $("#submitTitles").removeAttr("disabled");
		            },
		        });
			}
		});
		
		$("#downloadBtn").click(function(){
			$(this).hide();
		});
		
</script>



</html>