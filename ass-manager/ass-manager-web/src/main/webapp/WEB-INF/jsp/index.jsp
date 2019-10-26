<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
	<title>排课系统</title>
	<script type="text/javascript">
			function eP(){
				layer.open({
				type: 2,
				area: ['500px','200px'],
				fix: false, //不固定
				maxmin: true,
				shade:0.4,
				title: '修改密码',
				content: '/edit-Password',
				})
			}	
	</script>
  </head>
  <body>
    	
<div>
	<header class="navbar-wrapper">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl"> 
				<a class="logo navbar-logo f-l mr-10 hidden-xs" href="#">自动排课系统</a>  
				<span class="logo navbar-slogan f-l mr-10 hidden-xs">v1.0</span> 
				<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
					<ul class="cl">
						<li>超级管理员</li>
						
						<li class="dropDown dropDown_hover">
							<a href="#" class="dropDown_A">${user.name}<i class="Hui-iconfont">&#xe6d5;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" onClick="eP()">修改密码</a></li>
								<li><a href="/logout">退出</a></li>
							</ul>
						</li>
						
						<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
								<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
								<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
								<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
								<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
								<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	
	<aside class="Hui-aside">
		<div class="menu_dropdown bk_2">
			<dl id="menu-department">
				<dt><i class="Hui-iconfont">&#xe616;</i>  专业信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/department/findAll/1" data-title="专业信息" href="javascript:void(0)">专业信息</a></li>
					</ul>
				</dd>
			</dl>
			
			<dl id="menu-class">
				<dt><i class="Hui-iconfont">&#xe613;</i>  班级信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/class/findAll/1" data-title="班级信息" href="javascript:void(0)">班级信息</a></li>
					</ul>
				</dd>
			</dl>
			
			<dl id="menu-classroom">
				<dt><i class="Hui-iconfont">&#xe620;</i>  教室信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/classroom/findAll/1" data-title="教室信息" href="javascript:void(0)">教室信息</a></li>
					</ul>
				</dd>
			</dl>
			
			<dl id="menu-course">
				<dt><i class="Hui-iconfont">&#xe622;</i>  课程信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/course/findAll/1" data-title="课程信息" href="javascript:void(0)">课程信息</a></li>
					</ul>
				</dd>
			</dl>
			
			<dl id="menu-teacher">
				<dt><i class="Hui-iconfont">&#xe60d;</i>  教师信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/teacher/findAll/1" data-title="教师信息" href="javascript:void(0)">教师信息</a></li>
					</ul>
				</dd>
			</dl>
			
			<dl id="menu-student">
				<dt><i class="Hui-iconfont">&#xe62d;</i>  学生信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/student/findAll/1" data-title="学生信息" href="javascript:void(0)">学生信息</a></li>
					</ul>
				</dd>
			</dl>
			
			<shiro:hasPermission name="super">
			<dl id="menu-admin">
				<dt><i class="Hui-iconfont">&#xe61a;</i>  管理员信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/admin/findAll/1" data-title="管理员信息" href="javascript:void(0)">管理员信息</a></li>
					</ul>
				</dd>
			</dl>
			</shiro:hasPermission>
			<dl id="menu-ass">
				<dt><i class="Hui-iconfont">&#xe62e;</i>  排课管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
				<dd>
					<ul>
						<li><a data-href="/findAutoCourse/1" data-title="排课信息列表" href="javascript:void(0)">自动排课</a></li>
						<li><a data-href="/autoList" data-title="班级课表查询" href="javascript:void(0)">班级课表查询</a></li>
						<li><a data-href="/autoTeacherList" data-title="教师课表查询" href="javascript:void(0)">教师课表查询</a></li>
						<li><a data-href="/autoRoomList" data-title="教室课表查询" href="javascript:void(0)">教室课表查询</a></li>
					</ul>
				</dd>
			</dl>
		</div>
	</aside>
	
	<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
		<section class="Hui-article-box">
			<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
				<div class="Hui-tabNav-wp">
					<ul id="min_title_list" class="acrossTab cl">
						<li class="active">
							<span title="我的桌面" data-options="attributes:{'url':'welcome'}">我的桌面</span>
							<em></em></li>
					</ul>
				</div>
				<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
			</div>
			<div id="iframe_box" class="Hui-article">
				<div class="show_iframe">
					<div style="display:none" class="loading"></div>
					<iframe scrolling="yes" frameborder="0" src="/welcome"></iframe>
				</div>
			</div>
		</section>

		<div class="contextMenu" id="Huiadminmenu">
			<ul>
				<li id="closethis">关闭当前 </li>
				<li id="closeall">关闭全部 </li>
			</ul>
		</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> 
<script type="text/javascript" src="lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
</body>
</html>