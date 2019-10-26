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
	<title>专业信息列表</title>
</head>
<body>
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 专业信息 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
	<div class="page-container">
		<div class="cl pd-5 bg-1 bk-gray ">
			<div style="float:left;margin-right:9px"><input type="button" value="批量删除" class="btn btn-danger radius" onclick="batchDel();" /> </div>
			<div style="float:left;margin-right:9px"><input type="button" value="新增" class="btn btn-primary radius" id="addBtn" /></div>
			<div style="float:left;margin-right:9px"><input type="button"  value="导入" class="btn btn-primary radius" id="importBtn" /></div>
			<div style="float:left;margin-right:9px"><input type="button" value="导出" class="btn btn-primary radius" onclick="exportExcel();" /> </div>
			<span class="ui_frt">
				<input type="text" name="search" id="search" style="width:250px" class="input-text" onkeyup="searchWord(this)" placeholder="请输入专业名称查询"/>
				<div id="showDiv" style="display:none; position:absolute;z-index:1000;background:#fff; width:250px;border:1px solid #CCCCCC;">
							
				</div>
				<input type="button" value="查询"  class="btn btn-success "  onclick="search();" /> 
			</span>
		</div>
		
		<script type="text/javascript">
			//导入
			$(function(){
				//页面加载完成后，调用插件的upload方法，动态修改了HTML页面元素
				$("#importBtn").upload({
					action:'/department/import',
					name:'departmentFile'
				});
			})
				
			//导出
			function exportExcel(){
				$.messager.confirm("提示信息","你确定要导出文件吗？",function(r){
					if(r){
						window.location.href="/department/export";
					}
				})
			}
		
			//查询
			function search(){
				var words=$("#search").val();
				window.location.href="/department/search/${currentPage}?words="+words;
			}
			
			//鼠标移入，改变被移入位置的背景色
			function overFn(obj){
				$(obj).css("background","#DBEAF9");
			}
			
			//鼠标移出，还原被移出位置的背景色	
			function outFn(obj){
				$(obj).css("background","#fff");
			}
			
			//点击鼠标，获取该位置的内容写入搜索框并将showDiv隐藏		
			function clickFn(obj){
				$("#search").val($(obj).html());
				$("#showDiv").css("display","none");
			}
			
 			function searchWord(obj){
 				var word=$(obj).val();			//获取搜索框输入的内容
 				//删除输入框文字后将shoeDiv隐藏并不执行下面的代码
 				if(word==""){
 					$("#showDiv").css("display","none");
 					return;
 				}
 				//根据输入框的内容去数据库中模糊查询
 				var content="";
 				$.post(
 						"/department/search",
 						{"word":word},
 						function(data){
 							//将返回的商品的名称 显现在showDiv中
 							if(data.length>0){
 								for(var i=0;i<data.length;i++){
 									content+="<div style='padding:5px;cursor:pointer' onclick='clickFn(this)' onmouseover='overFn(this)' onmouseout='outFn(this)'>"+data[i]+"</div>";
 								}
 								$("#showDiv").html(content);
 								$("#showDiv").css("display","block");
 							}
 						},
 						"json"
 				);		
 			}
		
			/** 新增 **/
			$(function(){
				$("#addBtn").click(function(){
					window.location.href="/department-add"
				})
			})
			/** 批量删除 **/
			function batchDel(){
				if($("input[name='IDCheck']:checked").size()<=0){
					art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'至少选择一条', ok:true,});
					return;
				}
				$.messager.confirm("提示信息","你确定要删除这些记录吗？",function(r){
					if(r){
						//1）取出用户选中的checkbox放入字符串传给后台,form提交
						var allIDCheck = "";
						$("input[name='IDCheck']:checked").each(function(index, domEle){
							allIDCheck += $(domEle).val() + ",";					
						});
						window.location.href="/department/deleteMore/"+allIDCheck;
					}
				})
			}
		</script>
		 
		<div class="mt-20">
			<table class="table">
				<tr>
					<th width="30" ><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);" />
					</th>
					<th>专业代码</th>
					<th>专业名称</th>
					<th>负责人</th>
					<th>开设时间</th>
					<th>专业说明</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${result.rows}" var="department">
					<tr>
						<td><input type="checkbox" name="IDCheck" value="${department.id}" class="acb" /></td>
						<td>${department.id}</td>
						<td>${department.name}</td>
						<td>${department.dean}</td>
						<td>
							<fmt:formatDate value="${department.created}" pattern="yyyy-MM-dd"/>
						</td>
						<td>${department.explains}</td>
						<td>
							<a title="编辑" href="javascript:;" onclick="toEdit(${department.id})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
							<a title="删除" href="javascript:;" onclick="deletes(${department.id})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<script type="text/javascript">
			//前往信息修改页面
			function toEdit(id){
				window.location.href="/department/edit/"+id;
			}
			//删除单条记录
			function deletes(id){
				$.messager.confirm("提示信息","你确定要删除当前记录吗？",function(r){
					if(r){
						location.href ="/department/delete/"+id;
					}
				})
			}
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
				window.location.href="/department/findAll/1";
			}
			//跳往前一页
			function beforePage(){
				window.location.href="/department/findAll/${currentPage-1}";
			}
			//跳往后一页
			function afterPage(){
				window.location.href="/department/findAll/${currentPage+1}";
			}
			//跳往尾页
			function last(){
				window.location.href="/department/findAll/${totalPage}";
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
				location.href="/department/findAll/"+pageNum;
			}
		</script>
	</div>
</body>
</html>