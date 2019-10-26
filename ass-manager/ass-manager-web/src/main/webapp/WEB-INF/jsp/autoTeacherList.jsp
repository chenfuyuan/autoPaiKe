<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/jquery/jquery-1.7.1.js"></script>
	<link href="<%=request.getContextPath()%>/style/authority/basic_layout.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/style/authority/common_style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/authority/commonAll.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
	<script type="text/javascript" src="<%=request.getContextPath()%>/scripts/artDialog/artDialog.js?skin=default"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/css/style.css" />
	<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ocupload-1.1.2.js"></script>
  </head>
  
  <body>
    <div style="margin-top:20px;margin-left:20px;margin-bottom:20px;">
    	专业名称:
		<select id="departmentId" name="departmentId" class="input-text" style="width:250px">
			
		</select>	
		教师名称:
		<select id="teacherId" name="teacherId" class="input-text" style="width:250px">
			
		</select>	
		<input type="button" value="查询" class="btn btn-primary radius" id="search">
	</div>
		<table class="table" style="margin-left:15px;">
			<tr>
				<td></td>
				<td>星期一</td>
				<td>星期二</td>
				<td>星期三</td>
				<td>星期四</td>
				<td>星期五</td>
				<td>星期六</td>
				<td>星期日</td>
			</tr>
			<tr>
				<td>1</td>
				<td id="table_1" rowspan="2"></td>
				<td id="table_6" rowspan="2"></td>
				<td id="table_11" rowspan="2"></td>
				<td id="table_16" rowspan="2"></td>
				<td id="table_21" rowspan="2"></td>
				<td rowspan="2"></td>
				<td rowspan="2"></td>
			</tr>
			<tr><td>2</td></tr>
			<tr>
				<td>3</td>
				<td id="table_2" rowspan="2"></td>
				<td id="table_7" rowspan="2"></td>
				<td id="table_12" rowspan="2"></td>
				<td id="table_17" rowspan="2"></td>
				<td id="table_22" rowspan="2"></td>
				<td rowspan="2"></td>
				<td rowspan="2"></td>
			</tr>
			<tr><td>4</td></tr>
			<tr>
				<td>5</td>
				<td id="table_3" rowspan="2"></td>
				<td id="table_8" rowspan="2"></td>
				<td id="table_13" rowspan="2"></td>
				<td id="table_18" rowspan="2"></td>
				<td id="table_23" rowspan="2"></td>
				<td rowspan="2"></td>
				<td rowspan="2"></td>
			</tr>
			<tr><td>6</td></tr>
			<tr>
				<td>7</td>
				<td id="table_4" rowspan="2"></td>
				<td id="table_9" rowspan="2"></td>
				<td id="table_14" rowspan="2"></td>
				<td id="table_19" rowspan="2"></td>
				<td id="table_24" rowspan="2"></td>
				<td rowspan="2"></td>
				<td rowspan="2"></td>
			</tr>
			<tr><td>8</td></tr>
			<tr>
				<td>9</td>
				<td id="table_5" rowspan="2"></td>
				<td id="table_10" rowspan="2"></td>
				<td id="table_15" rowspan="2"></td>
				<td id="table_20" rowspan="2"></td>
				<td id="table_25" rowspan="2"></td>
				<td rowspan="2"></td>
				<td rowspan="2"></td>
			</tr>
			<tr><td>10</td></tr>
		</table>
  </body>
  <script type="text/javascript">
  	//页面加载完毕后去异步获得分类数据
	$.post(
		"/department/findAllAjax",
		function(data){
			var content="<option></option>";
			for(var i=0;i<data.length;i++){
				content+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
			}
			$("#departmentId").html(content);
		},
		"json"
	);
	$("#departmentId").bind("change",function(){
			var departmentId=$("#departmentId").val();
			$.post(
					"/teacher/findAllAjax",
					{"departmentId":departmentId},
					function(data){
					var content="<option></option>";
					for(var i=0;i<data.length;i++){
						content+="<option value='"+data[i].id+"'>"+data[i].id +"&nbsp;&nbsp;&nbsp;" +  data[i].name+"</option>";
					}
					$("#teacherId").html(content);	
				},
				"json"
			);
	})
	$("#search").click(function(){
		var teacherId=$("#teacherId").val();
		$.post(
			"/ass/findTeacherCourse",
			{"teacherId":teacherId},
			function(data){
				//清空课程表
        		for(var i=1;i<26;i++){
        			$("#table_"+i).html("");
        		}
        		//遍历课程表
        		for (var i=0;i<data.length;i++) {
	        		$("#table_"+data[i].time).html(data[i].courseName+"<br>"+data[i].className+"<br>"+data[i].classRoomName);
        		}	     
			},
			"json"
		);
	})
  </script>
</html>
