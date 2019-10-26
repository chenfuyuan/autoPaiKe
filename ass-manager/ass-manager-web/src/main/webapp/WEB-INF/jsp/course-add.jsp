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
	<title>学生信息录入</title>
</head>
<body>
	<article class="page-container" >
		<form action="/course/add" method="post" class="form form-horizontal"  id="form-add">
			<input type="hidden" name="id" value="${message.id }"/>
			<input type="hidden" id="dId"  value="${message.departmentid }"/>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				课程名：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" style="width:250px" value="${message.name }" id="name" name="name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				每周课时：</label>
				<div class="formControls col-xs-8 col-sm-9">
					 <input type="text" class="input-text" style="width:250px" value="${message.weektime }" id="weektime" name="weektime">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				学分：</label>
				<div class="formControls col-xs-8 col-sm-9">
					 <input type="text" class="input-text" style="width:250px" value="${message.credit }" id="credit" name="credit">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				考核方式：</label>
				<div class="formControls col-xs-8 col-sm-9">
					 <select id="assess" name="assess" class="input-text" style="width:250px">
						<option value="${message.assess }">${message.assess }</option>	
						<option value="考查">考查</option>	
						<option value="考试">考试</option>	
					</select>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				开设学期：</label>
				<div class="formControls col-xs-8 col-sm-9">
					 <select id="groups" name="groups" class="input-text" style="width:250px">
						<option value="${message.groups }">${message.groups }</option>	
						<option value="大一上">大一上</option>	
						<option value="大一下">大一下</option>
						<option value="大二上">大二上</option>
						<option value="大二下">大二下</option>
						<option value="大三上">大三上</option>
						<option value="大三下">大三下</option>
						<option value="大四上">大四上</option>
						<option value="大四下">大四下</option>	
					</select>	
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				上课方式：</label>
				<div class="formControls col-xs-8 col-sm-9">
					 <select id="way" name="way" class="input-text" style="width:250px">
						<option value="${message.way }">${message.way }</option>	
						<option value="理论">理论</option>	
						<option value="理论+上机">理论+上机</option>
						<option value="理论+实验">理论+实验</option>
						<option value="室外">室外</option>
					</select>	
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				课程类型：</label>
				<div class="formControls col-xs-8 col-sm-9">
					 <select id="type" name="type" class="input-text" style="width:250px">
						<c:if test="${message.type==1 }">
							<option value="${message.type }">必修课</option>
						</c:if>
						<c:if test="${message.type==0 }">
							<option value="${message.type }">选修课</option>
						</c:if>	
						<option value="1">必修课</option>	
						<option value="0">选修课</option>
					</select>	
				</div>
			</div>
			
			<div class="row cl" id="num" style="display: none;">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				可报人数：</label>
				<div class="formControls col-xs-8 col-sm-9">
					 <input type="text" class="input-text" style="width:250px" value="${message.number }" id="number" name="number">	
				</div>
			</div>
			
			<script type="text/javascript">
				$("#type").bind("input",function(){
					var type=$("#type").val();
					if(type==0){
						$("#num").css('display','block');
					}
					if(type==1){
						$("#num").css('display','none');
					}
				})
			</script>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				所属专业：</label>
					<div class="formControls col-xs-7 col-sm-7">
						<select id="departmentId" name="departmentId" class="input-text" style="width:250px">
							
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
		var type=$("#type").val();
		if(type==0){
			$("#num").css('display','block');
		}
		if(type==1){
			$("#num").css('display','none');
		}
	
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
				weektime:{
					required:true,
				},
				credit:{
					required:true,
				},
				assess:{
					required:true,
				},
				departmentId:{
					required:true,
				},
				groups:{
					required:true,
				},
				way:{
					required:true,
				},
				type:{
					required:true,
				}
			},
			onkeyup:false,
			focusCleanup:true,
			success:"valid"
		});
	
		$("#qxbt").click(function() {
			window.location.href="/course/findAll/1";
		})
	});
</script> 
</body>
</html>
