<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/static/h-ui.admin/css/style.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery/1.9.1/jquery.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui/js/H-ui.min.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/lib/jquery.validation/1.14.0/messages_zh.js"></script>
	<title>教师信息录入</title>
</head>
<body>
	<article class="page-container" >
		<form action="/teacher/add" method="post" class="form form-horizontal"  id="form-add">
			<input type="hidden" name="id" value="${message.id }"/>
			<input type="hidden" id="dId"  value="${message.departmentid }"/>
			<input type="hidden" id="course1"  value="${course1 }"/>
			<input type="hidden" id="course2"  value="${course2 }"/>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				姓名：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" style="width:250px" value="${message.name }" id="name" name="name">
				</div>
			</div>
			<c:if test="${message.id==null }">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-2">
					<span class="c-red">*</span>
					证件号：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" style="width:250px" value="${message.code }" id="code" name="code">
					</div>
				</div>
			</c:if>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				性别：</label>
				<div class="formControls col-xs-8 col-sm-9">
					 <input name="sex" type="radio" id="six_1" value="1" checked>男&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              		 <input type="radio" name="sex" value="0" id="six_0">女
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				学位：</label>
				<div class="formControls col-xs-8 col-sm-9">
					 <select id="degree" name="degree" class="input-text" style="width:250px">
						<option value="${message.degree }">${message.degree }</option>	
						<option value="学士">学士</option>	
						<option value="硕士">硕士</option>	
						<option value="博士">博士</option>	
					</select>	
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				职称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					 <select id="title" name="title" class="input-text" style="width:250px">
						<option value="${message.title }">${message.title }</option>	
						<option value="助教">助教</option>	
						<option value="讲师">讲师</option>	
						<option value="副教授">副教授</option>	
						<option value="教授">教授</option>	
					</select>	
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				联系电话：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" style="width:250px" value="${message.phone }" id="phone" name="phone">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				家庭住址：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" style="width:250px" value="${message.address }" id="address" name="address">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				所属专业：</label>
					<div class="formControls col-xs-7 col-sm-7">
						<select id="departmentId" name="departmentId" class="input-text" style="width:250px">
							
						</select>			
					</div>
			</div>
			<script type="text/javascript">
				$("#departmentId").bind("change",function(){
					var departmentId=$("#departmentId").val();
					$.post(
							"/course/findAllAjax",
							{"departmentId":departmentId},
							function(data){
							var content="<option></option>";
							for(var i=0;i<data.length;i++){
								content+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
							}
							$("#courseId1").html(content);
							$("#courseId2").html(content);
							},
							"json"
					);
				})
			</script>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				教授课程：</label>
					<div class="formControls col-xs-7 col-sm-7">
						<select id="courseId1" name="courseId" class="input-text" style="width:250px">
							
						</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<select id="courseId2" name="courseId" class="input-text" style="width:250px">
							
						</select>			
					</div>
			</div>
			
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">&nbsp;&nbsp;&nbsp;&nbsp;
					<input class="btn btn-primary radius" type="button" id="qxbt" value="&nbsp;&nbsp;取消&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</article>

<script type="text/javascript">
	$(function(){
		//页面加载完毕后去异步获得分类数据
		$.post(
			"/department/findAllAjax",
			function(data){
				var id=$("#dId").val();
				var content="<option></option>";
				for(var i=0;i<data.length;i++){
					content+="<option id='"+data[i].id+"'value='"+data[i].id+"'>"+data[i].name+"</option>";
				}
				$("#departmentId").html(content);
				var opts = document.getElementById("departmentId");
				if(id!=""){
					for(var i=0;i<opts.options.length;i++){
						if(id==opts.options[i].value){
							opts.options[i].selected = 'selected';	
							break;
						}
					}
				}
			},
			"json"
		);
		
		var departmentId=$("#dId").val();
		var id1=$("#course1").val();
		var id2=$("#course2").val();
		if(departmentId!="")
		{
			$.post(
				"/course/findAllAjax",
				{"departmentId":departmentId},
				function(data){
					var content="<option></option>";
					for(var i=0;i<data.length;i++){
						content+="<option id='"+data[i].id+"'value='"+data[i].id+"'>"+data[i].name+"</option>";
					}
					$("#courseId1").html(content);
					$("#courseId2").html(content);
					
					var opts1 = document.getElementById("courseId1");
					if(id1!=""){
						for(var i=0;i<opts1.options.length;i++){
							if(id1==opts1.options[i].value){
								opts1.options[i].selected = 'selected';	
								break;
							}
						}
					}
					var opts2 = document.getElementById("courseId2");
					if(id2!=""){
						for(var i=0;i<opts2.options.length;i++){
							if(id==opts2.options[i].value){
								opts2.options[i].selected = 'selected';	
								break;
							}
						}
					}
				},
				"json"
			);
		}
		
		$('.skin-minimal input').iCheck({
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
	
		//表单校验
		$("#form-add").validate({
			rules:{
				name:{
					required:true,
				},
				code:{
					required:true,
					isIdCardNo:true,
					
				},
				phone:{
					required:true,
					isTel:true
				},
				address:{
					required:true,
				},
				departmentId:{
					required:true,
				},
				degree:{
					required:true,
				},
				title:{
					required:true,
				}
			},
			onkeyup:false,
			focusCleanup:true,
			success:"valid"
		});
	
		$("#qxbt").click(function() {
			window.location.href="/teacher/findAll/1";
		})
	});
</script> 
</body>
</html>
