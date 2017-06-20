<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
    String path = request.getContextPath();
%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
<title>修改密码</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" type="text/css" href="${ctx}/static/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css">
<%-- <link rel="shortcut icon" href="${ctx}/static/images/icon.png"> --%>

<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common_ex.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.loadmask.min.js"></script>   
</head>

<body >
<div id="dlg" >

<form id="pageForm" class="easyui-form" method="post"  action="<%=path%>/user/updatPwd" >

			<table class="detailTable">
	    <input name="t" type="hidden" id="t"/>
        <tr>
        	<td>输入新密码:</td>
            <td>
            	<input type="hidden" id="id" name="id" value="${user.id}" />
            	<input type="password"  class="inp" id="password" name="password"  autocomplete="off"/>
            </td>
        </tr>
        <tr>
            <td>确认新密码:</td>
            <td>
            	<input type="password"  class="inp" id="newsPwd" name="newsPwd"  autocomplete="off"/>
            </td>
        </tr>
     </table>


     <script type="text/javascript">
     
    
     $('#pageForm').form({
         onSubmit : function() {
                     var isValid = $(this).form('validate');
                     if (isValid) {
                  	   
                         var id = $("[name='id']").val();
                         var password = $("[name='password']").val();
                         var newsPwd = $("[name='newsPwd']").val();
                         var r = /^[\w\_\,\.]{6,20}$/;
                         if(password==""){
                        	   psmaMessageShowOne("请输入新密码！");
                               return false;
                           }
                         if (password.length < 6 || password.length > 20) {
                      	   psmaMessageShowOne("密码长度为6到20位字母数字下划线等组成！");
                             return false;
                         }
                         if (!r.test(password)) {
                      	   psmaMessageShowOne("密码格式输入不正确！");
                             return false;
                         }
                         if (newsPwd.length < 6 || newsPwd.length > 20) {
                      	   psmaMessageShowOne("新密码长度为6到20位字母数字下划线等组成！");
                             return false;
                         }
                         if (!r.test(newsPwd)) {
                      	   psmaMessageShowOne("新密码格式输入不正确！");
                             return false;
                         }
                         
                         if(password!=newsPwd){
                      	   psmaMessageShowOne("两次输入的密码不一致！");
                             return false;
                         }
                         $("#t").val( Math.random());
                         $(d).mask("正在提交数据...");
                     }
                     return isValid; // 返回false终止表单提交
         },
         success : function(data) {
      	   var data = eval(data); 
                     if (data[0] == true) {
                  	   psmaMessageShowOne("修改密码成功！");                 
                             $(d).unmask();
                             d.panel('close');
                             //dg.datagrid('reload');
                     } else {
                  	   psmaMessageShowOne("修改密码失败！"); 
                     }
                 $(d).unmask();
     }
 });
				    
</script>
     </form>
     </div>
</body>
</html>