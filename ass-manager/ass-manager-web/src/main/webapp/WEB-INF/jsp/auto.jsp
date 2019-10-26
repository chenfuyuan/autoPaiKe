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
  	<style type="text/css">
  		select{
  			width:150px;
  		}
  	</style>
  </head>
  
  
  <body style="text-align: center">
    <div style="margin-top:15px;">
		<select id="groups" name="groups" class="input-text" style="width:250px">
			<option value="" >请选择要排课的年级</option>	
			<option value="大一上">大一上</option>	
			<option value="大一下">大一下</option>
			<option value="大二上">大二上</option>
			<option value="大二下">大二下</option>
			<option value="大三上">大三上</option>
			<option value="大三下">大三下</option>
			<option value="大四上">大四上</option>
			<option value="大四下">大四下</option>	
		</select>	
		<input type="button" value="排课" class="btn btn-primary radius" onclick="Auto()">
	</div>
	<script type="text/javascript">
		function Auto(){
			var groups=$("#groups").val();
			if(groups!=""){
				$.messager.confirm("提示信息","你确定要排课吗？",function(r){
					if(r){
						$.post(
							"/ass/auto",
							{"groups":groups},
							function(data){	
								if(data==1){
									$.messager.alert("友情提示","排课成功","succeed");
									location.href ="/findAutoCourse/1";
								}		
							},
							"json"
						);
					}
				})
			}else{
				$.messager.alert("友情提示","请选择要排课的年级","error");
			}
		}
	</script>
	
	<div class="mt-20" style="margin-top:20px;">
		<table class="table">
			<tr>
				<th>排课代码</th>
				<th>课程名称</th>
				<th>授课老师</th>
				<th>授课班级</th>
				<th>授课教室</th>
				<th>授课时间</th>				
				<th>操作</th>
			</tr>
			
			<c:forEach items="${result.rows}" var="data" varStatus="idxStatus">
			<tr>	
				<td>
					<input type="hidden" name="id"  id="id${idxStatus.index+1 }" value="${data.id}"/>
					${data.id}
				</td>
				<td>
					<input type="hidden" id="countPage"   value="${currentPageCount}"/>
					<input type="hidden" id="coId${idxStatus.index+1 }" name="coId"  value="${data.courseid }"/>
					<select id="courseId${idxStatus.index+1 }" name="courseId" onchange="course(this.options[this.options.selectedIndex].value,${data.id})">
						
					</select>
				</td>
				
				<td>
					<input type="hidden" id="teId${idxStatus.index+1 }" name="teId" value="${data.teacherid }"/>
					<select id="teacherId${idxStatus.index+1 }" name="teacherId" onchange="teacher(this.options[this.options.selectedIndex].value,${data.id})">
				
					</select>
				</td>
				<td>					
					<input type="hidden" id="clId${idxStatus.index+1 }" name="clId" value="${data.classid }"/>
					<select id="classId${idxStatus.index+1 }" name="classId" onchange="classs(this.options[this.options.selectedIndex].value,${data.id})">
				
					</select>
				</td>
				<td>
					<input type="hidden" id="clrId${idxStatus.index+1 }" name="clrId" value="${data.classroomid }"/>
					<select id="classroomId${idxStatus.index+1 }" name="classroomId" onchange="classroom(this.options[this.options.selectedIndex].value,${data.id})">
				
					</select>
				</td>
				<td>
					<select id="week${idxStatus.index+1 }" name="week" onchange="week(this.options[this.options.selectedIndex].value,${data.id})">
					<c:if test="${data.time<=5 }">
						<option value="1" selected="selected">星期一</option>
						<option value="2">星期二</option>
						<option value="3">星期三</option>
						<option value="4">星期四</option>
						<option value="5">星期五</option>
					</c:if>
					<c:if test="${data.time<=10&&data.time>5 }">
						<option value="1">星期一</option>
						<option value="2" selected="selected">星期二</option>
						<option value="3">星期三</option>
						<option value="4">星期四</option>
						<option value="5">星期五</option>
					</c:if>
					<c:if test="${data.time<=15&&data.time>10 }">
						<option value="1">星期一</option>
						<option value="2">星期二</option>
						<option value="3" selected="selected">星期三</option>
						<option value="4">星期四</option>
						<option value="5">星期五</option>
					</c:if>
					<c:if test="${data.time<=20&&data.time>15 }">
						<option value="1">星期一</option>
						<option value="2">星期二</option>
						<option value="3">星期三</option>
						<option value="4" selected="selected">星期四</option>
						<option value="5">星期五</option>
					</c:if>
					<c:if test="${data.time<25&&data.time>20 }">
						<option value="1">星期一</option>
						<option value="2">星期二</option>
						<option value="3">星期三</option>
						<option value="4">星期四</option>
						<option value="5" selected="selected">星期五</option>
					</c:if>
					</select>
					<select id="knob" name="knob" onchange="time(this.options[this.options.selectedIndex].value,${data.id})">
					<c:if test="${data.time==1 || data.time==6 || data.time==11 || data.time==16 ||data.time==21 }">	
						<option value="1" selected="selected">1-2</option>
						<option value="2">3-4</option>
						<option value="3">5-6</option>
						<option value="4">7-8</option>
						<option value="5">9-10</option>
					</c:if>
					<c:if test="${data.time==2 || data.time==7 || data.time==12 || data.time==17 ||data.time==22 }">	
						<option value="1">1-2</option>
						<option value="2" selected="selected">3-4</option>
						<option value="3">5-6</option>
						<option value="4">7-8</option>
						<option value="5">9-10</option>
					</c:if>
					<c:if test="${data.time==3 || data.time==8 || data.time==13 || data.time==18 ||data.time==23 }">	
						<option value="1">1-2</option>
						<option value="2">3-4</option>
						<option value="3" selected="selected">5-6</option>
						<option value="4">7-8</option>
						<option value="5">9-10</option>
					</c:if>
					<c:if test="${data.time==4 || data.time==9 || data.time==14 || data.time==19 ||data.time==24 }">	
						<option value="1">1-2</option>
						<option value="2">3-4</option>
						<option value="3">5-6</option>
						<option value="4" selected="selected">7-8</option>
						<option value="5">9-10</option>
					</c:if>
					<c:if test="${data.time==5 || data.time==10 || data.time==15 || data.time==20 ||data.time==25 }">	
						<option value="1">1-2</option>
						<option value="2">3-4</option>
						<option value="3">5-6</option>
						<option value="4">7-8</option>
						<option value="5" selected="selected">9-10</option>
					</c:if>
						
					</select>
				</td>
				<td>
					<a title="删除" href="javascript:;" onclick="deletes(${data.id})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
				</td>
			</tr>	
		</c:forEach>
		</table>
	</div>
	
	<script type="text/javascript">
		function course(v,id){
			$.post(
				"/auto/changeCourseId",
				{
    				"id": id, "courseId":v
				},
				function(data){}
			);
		}
		
		function teacher(v,id){
			$.post(
				"/auto/changeTeacherId",
				{
    				"id": id, "teacherId":v
				},
				function(data){}
			);
		}
		
		function classs(v,id){
			$.post(
				"/auto/changeClassId",
				{
    				"id": id, "classId":v
				},
				function(data){}
			);
		}
		
		function classroom(v,id){
			$.post(
				"/auto/changeClassRoomId",
				{
    				"id": id, "classroomId":v
				},
				function(data){}
			);
		}
		
		function week(v,id){
			$.post(
				"/auto/changeWeek",
				{
    				"id": id, "week":v
				},
				function(data){}
			);
		}
		
		function time(v,id){
			$.post(
				"/auto/changeTime",
				{
    				"id": id, "time":v
				},
				function(data){}
			);
		}
		
		function deletes(id){
			$.messager.confirm("提示信息","你确定要删除当前记录吗？",function(r){
				if(r){
					location.href ="/auto/delete/"+id;
				}
			})
		}
	
		$(function(){
		
		var page=$("#countPage").val();
		
		//页面加载完毕后去异步获得分类数据
		$.post(
			"/course/findAllAjaxs",
			function(data){
				var content="";
				for(var i=0;i<data.length;i++){
					content+="<option id='"+data[i].id+"'value='"+data[i].id+"'>"+data[i].name+"</option>";
				}
				$("select[name='courseId']").html(content);
				for(var j=1;j<=page;j++){
					var id=$("#coId"+j).val();
					var opts = document.getElementById("courseId"+j);
					if(id!=""){
						for(var i=0;i<opts.options.length;i++){
							if(id==opts.options[i].value){
								opts.options[i].selected = 'selected';	
								break;
							}
						}
					}
				}
			},
			"json"
		);
	
		//页面加载完毕后去异步获得分类数据
		$.post(
			"/teacher/findAllAjaxs",
			function(data){
				var content="";
				for(var i=0;i<data.length;i++){
					content+="<option id='"+data[i].id+"'value='"+data[i].id+"'>"+data[i].name+"</option>";
				}
				$("select[name='teacherId']").html(content);
				for(var j=1;j<=page;j++){
					var id=$("#teId"+j).val();
					var opts = document.getElementById("teacherId"+j);
					if(id!=""){
						for(var i=0;i<opts.options.length;i++){
							if(id==opts.options[i].value){
								opts.options[i].selected = 'selected';	
								break;
							}
						}
					}
				}
			},
			"json"
		);
		
		//页面加载完毕后去异步获得分类数据
		$.post(
			"/class/findAllAjaxs",
			function(data){
				var content="";
				for(var i=0;i<data.length;i++){
					content+="<option id='"+data[i].id+"'value='"+data[i].id+"'>"+data[i].name+"</option>";
				}
				$("select[name='classId']").html(content);
				for(var j=1;j<=page;j++){
					var id=$("#clId"+j).val();
					var opts = document.getElementById("classId"+j);
					if(id!=""){
						for(var i=0;i<opts.options.length;i++){
							if(id==opts.options[i].value){
								opts.options[i].selected = 'selected';	
								break;
							}
						}
					}
				}
			},
			"json"
		);
		
		//页面加载完毕后去异步获得分类数据
		$.post(
			"/classroom/findAllAjaxs",
			function(data){
				var content="";
				for(var i=0;i<data.length;i++){
					content+="<option id='"+data[i].id+"'value='"+data[i].id+"'>"+data[i].name+"</option>";
				}
				$("select[name='classroomId']").html(content);
				for(var j=1;j<=page;j++){
					var id=$("#clrId"+j).val();
					var opts = document.getElementById("classroomId"+j);
					if(id!=""){
						for(var i=0;i<opts.options.length;i++){
							if(id==opts.options[i].value){
								opts.options[i].selected = 'selected';	
								break;
							}
						}
					}
				}
			},
			"json"
		);
		
		
	})
	</script>
	<!-- 分页 -->
	<div class="ui_tb_h30 mt-20">
			<div class="ui_flt" style="height: 30px; line-height: 30px;">
				共有<span class="ui_txt_bold04">${result.total }</span>条记录，当前第<span class="ui_txt_bold04">${currentPage}/${totalPage }</span>页
			</div>
			<div class="ui_frt">
				<!--    如果是第一页，则只显示下一页、尾页 -->
				<c:if test="${currentPage!=1 }">
					<input type="button" value="首页" class="ui_input_btn01" onclick="indexPage()"/>
					<input type="button" value="上一页" class="ui_input_btn01" onclick="beforePage()"/>
				</c:if>
				<c:if test="${currentPage!=totalPage }">
					<input type="button" value="下一页" class="ui_input_btn01" onclick="afterPage()" />
					<input type="button" value="尾页" class="ui_input_btn01" onclick="last();" />
				</c:if>				
				<!--     如果是最后一页，则只显示首页、上一页 -->
				转到第<input type="text" id="jumpNumTxt" class="ui_input_txt01" />页
				<input type="button" class="ui_input_btn01" value="跳转" onclick="jumpInputPage(${totalPage });" />
			</div>
		</div>
		<script type="text/javascript">
			//跳往首页
			function indexPage(){
				window.location.href="/findAutoCourse/1";
			}
			//跳往前一页
			function beforePage(){
				window.location.href="/findAutoCourse/${currentPage-1}";
			}
			//跳往后一页
			function afterPage(){
				window.location.href="/findAutoCourse/${currentPage+1}";
			}
			//跳往尾页
			function last(){
				window.location.href="/findAutoCourse/${totalPage}";
			}
			//输入页数进行跳转
			function jumpInputPage(totalPage){
				//如果“跳转页数”不为空
				if($("#jumpNumTxt").val() != ''){
					var pageNum = parseInt($("#jumpNumTxt").val());
					//如果跳转页数在不合理范围内，则置为1
					if(pageNum<1 | pageNum>totalPage){
						$.messager.alert("友情提示","请输入合适的页数，\n自动为您跳到首页","error");
						pageNum = 1;
					}
				}else{
					//“跳转页数”为空
					$.messager.alert("友情提示","请输入合适的页数，\n自动为您跳到首页","error");
					pageNum = 1;
				}
				location.href="/findAutoCourse/"+pageNum;
			}
		</script>
  </body>
</html>
