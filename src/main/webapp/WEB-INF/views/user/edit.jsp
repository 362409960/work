<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>用户修改</title>
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
	<form id="mainform" action="${ctx}/user/edit" method="post" autocomplete="off" enctype="multipart/form-data">	
		<div id="tt" class="easyui-tabs" style="height: 525px" data-options="border:false, fit:true">
		  <input type="hidden" name="id" id="id" value="${user.id}" /> 
			<div title="基本属性" id="send_datails" style="padding: 10px">
				<div class="wrap">				 
						<div class="form-control">
							<label class="labelPad">用户名称:</label> 
						 		<input id="username" name="username"  value="${uservo.username}" maxlength="30"  type="text" class="easyui-validatebox inp" data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
						</div>
						<div class="form-control">
							<label class="labelPad">用户分类:</label> 
							 	<select id="userType" name="userType"	class="easyui-validatebox select" data-options="validType:'selectRequired[\'#userType\']'" disabled="disabled" >
								
								    <c:choose>
								       <c:when test="${userType.userType eq '1'}"><option value="1" selected="selected">患者用户</option></c:when>
								       <c:when test="${userType.userType eq '2'}"><option value="2" selected="selected">医务用户</option></c:when>
								    </c:choose>
								</select>
						</div>	 							
						<div class="form-control">    
                             
                    </div>	
                   
				</div>
			</div>


		</div>

	</form>

<script type="text/javascript">		


$(function(){
	
        
	
});


//提交form表单
$('#mainform').form({
				onSubmit : function() {
					var isValid = $(this).form('validate');		
					
					
			    	if(isValid){
			    		$(d).mask("正在提交数据...");
			    	}
					return isValid;	// 返回false终止表单提交
				},
				success : function(data) {
					if (data == 'success') {
						psmaMessageShowOne("操作成功！");						
						$(d).unmask();
						d.panel('close');
						dg.datagrid('reload');
					} else  {
						psmaMessageShowOne("操作失败！");					
					}
					$(d).unmask();
				}

			});

</script>
</body>
</html>
