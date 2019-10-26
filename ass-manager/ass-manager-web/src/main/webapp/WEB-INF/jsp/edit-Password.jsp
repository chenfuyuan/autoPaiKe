<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<title>修改密码</title>
</head>
<body>
<article class="page-container" >
		<form action="/editPassword" method="post" class="form form-horizontal"  id="form-add">
			<input type="hidden" name="id" value="${message.id }"/>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				新密码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="password" style="width:200px" class="input-text" value="" id="password" name="password">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-2">
				<span class="c-red">*</span>
				确认密码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input type="password" style="width:200px"  class="input-text" value="" id="password2" name="password2">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
			</div>
		</form>
</article>

<script type="text/javascript">
	$(function(){
	
		$('.skin-minimal input').iCheck({
			checkboxClass: 'icheckbox-blue',
			radioClass: 'iradio-blue',
			increaseArea: '20%'
		});
		//表单校验
		$("#form-add").validate({
			rules:{
				password:{
					required:true,
				},
				password2:{
					required:true,
					equalTo: "#password"
				}	
			},
			onkeyup:false,
			focusCleanup:true,
			success:"valid"
		});
		

	});
</script> 
</body>
</html>