<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>用户添加</title>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<link rel="stylesheet" type="text/css" href="${ctx}/static/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/color.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css">




<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common_ex.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.loadmask.min.js"></script>   
</head>

<body>
	<form id="mainform" action="${ctx}/user/save" method="post" autocomplete="off" enctype="multipart/form-data">	
		<div id="tt" class="easyui-tabs" style="height: 525px" data-options="border:false, fit:true">
		 
			<div title="基本属性" id="send_datails" style="padding: 10px">
				<div class="wrap">				 
						<div class="form-control">
							<label class="labelPad">用户名称:</label> 
						 		<input id="username" name="username"  maxlength="30"  type="text" class="easyui-validatebox inp" data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
						</div>
						<div class="form-control">
							<label class="labelPad">用户分类:</label> 
							 	<select id="userType" name="userType"	class="easyui-validatebox select" data-options="validType:'selectRequired[\'#userType\']'"  >
								   <option value="">请选择用户分类</option>
								   <option value="1">患者用户</option>
								   <option value="2">医务用户</option>
								</select>
						</div>	 							
						<div class="form-control"  id = "labelsq">    
                             
                    	</div>	
				</div>
			</div>
		</div>

	</form>
<script type="text/javascript">		


$(function() {
	$("#userType").combobox({
		onChange: function (n,o) {
			if("1" == n){
				$.ajax({
					type:'post',
					url:"${ctx}/user/checkName",
					data:{id:2},
					success: function(data){
						if (data.user == false){
							$("#userType").empty(); 
							psmaMessageShowOne("没有医务用户");
						} else{
							var html = "<label class=\"labelPad\">医务用户:</label> ";
							var labelsq = $("#labelsq");
							labelsq.html("");
							html += "<select id=\"userUId\" name=\"userUId\"	class=\"easyui-validatebox select\" data-options=\"validType:\'selectRequired[\\'#userUId\\']'\"  >";
							
							$.each(data, function (i,item) {
								html += "<option value="+item[0].id+">"+item[0].username+"</option>";
							});
							labelsq.html(html);
						}
						
					}
				});
			}
			$.parser.parse('#labelsq');
		}
	});
});
var onSubmitFlag = false;
//提交form表单
$('#mainform').form({
				onSubmit : function() {		
				var isValid = $(this).form('validate');		
				
				if (isValid) {
					if (onSubmitFlag == true) {
						return false;
					}
					onSubmitFlag = true;
					$(d).mask("正在提交数据...");
				}
				return isValid; // 返回false终止表单提交

		},
		success : function(data) {
			if (data == 'success') {

				psmaMessageShowOne("操作成功！");
				$(d).unmask();
				d.panel('close');
				dg.datagrid('reload');
			} else {
				onSubmitFlag = false;
				psmaMessageShowOne("操作失败！");
			}
			$(d).unmask();
		}

	});
</script>
</body>
</html>
