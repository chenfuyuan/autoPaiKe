<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<title>班级信息录入</title>
</head>
<body>
	<article class="page-container" >
		<form action="/class/add" method="post" class="form form-horizontal"  id="form-add">
			<input type="hidden" name="id" value="${message.id }"/>
			<input type="hidden" id="dId"  value="${message.departmentid }"/>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				班级名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" style="width:250px" value="${message.name }" id="name" name="name">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				班级人数：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="text" class="input-text" style="width:250px" value="${message.number }" id="number" name="number">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				所属专业：</label>
					<div class="formControls col-xs-7 col-sm-7">
						<select id="departmentId" name="departmentId"  class="input-text" style="width:250px">
						
							
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
				number:{
					required:true,
				},
				departmentId:{
					required:true,
				}	
			},
			onkeyup:false,
			focusCleanup:true,
			success:"valid"
		});
	
		$("#qxbt").click(function() {
			window.location.href="/class/findAll/1";
		})
	});
</script> 
</body>
</html>
