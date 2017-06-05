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
</head>

<body>
	<form id="mainform" action="${ctx}/user/save" method="post" autocomplete="off" enctype="multipart/form-data">	
		<div id="tt" class="easyui-tabs" style="height: 100%" data-options="border:false, fit:true">
			<div title="编辑内容" id="send_datails" style="padding: 10px" data-options="onClose:onClose">
				<div class="wrap">				
						<div class="form-control">
							<label class="labelPad">用户名称:</label> 
						 		<input id = "username" name="username" type="text" maxlength="30"  class="easyui-validatebox inp"   data-options="required:'required',validType:['specialCharacters','length[0,30]']" />

						</div>
						<div class="form-control">
							<label class="labelPad">用户类型:</label> 
							  <select name="userType" id="userType" class="easyui-validatebox select" data-options="validType:'selectRequired[\'#sortId\']'" >							 
								 <option value="">请选择用户类型</option>
								 <option value="1">上传用户</option>
								 <option value="2">查看用户</option>
							</select>
						</div>	
						
					<!-- <div class="form-control"> -->
					
				</div>
				</div>
			</div>

	</form>

<script type="text/javascript">		


$(function() {
	
});
var onSubmitFlag = false;
//提交form表单
$('#mainform').form({
				onSubmit : function() {		
					
				var isValid = $(this).form('validate');		
				var file = $("#file").val();	
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
