<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>医学影像系统</title>
<!-- <meta http-equiv="X-UA-Compatible" content="IE=11;IE=9;IE=10"> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">



<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" type="text/css" href="${ctx}/static/css/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/main.css">
<%-- <link rel="shortcut icon" href="${ctx}/static/images/icon.png"> --%>

 
</head> 
<body class="easyui-layout" id="body">
    <div data-options="region:'north',border:false" class="header clearfix">
        <div class="top clearfix">       
            <div class="top-right right">
                <ul>
                 <li>欢迎您，${username}<span class="note"></span></li>
                 <li><a href="#" class="easyui-menubutton" data-options="menu:'#set'">设置</a></li>
                </ul>
                <div id="set" style="width:100px;">
                    <div data-options="name:'edit',iconCls:'icon-edit'" onclick="updateUserPwd()">修改密码</div>
                    <div onclick="logout()">退出</div>                
                </div>
            </div>
        </div>
     
    </div>
   
    <div data-options="region:'center'">        
            <iframe id="iframe" frameborder="0" width="100%" height="99%" scrolling="no" marginheight="0" marginwidth="0" name="iframe" _onLoad="iFrameHeight()" src="${ctx}${action}"></iframe>
          
    </div>    
    <table id="dg"></table>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common.js"></script>
<script type="text/javascript" src="${ctx}/static/js/common_ex.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.loadmask.min.js"></script>   


<script type="text/javascript">

$(function(){
	var $path=$('.path').find('span');
	var $listA=$('.list').find('a');
	$listA.on('click',function(){
		$path.text($(this).text());
	});
});
//修改密码
var d;
function updateUserPwd(){		
	var url = '${ctx}/user/toUpPwd';

        d= $("#dg").dialog({
            title:'修改密码',
            width:500,
            height:400,
            href:url,
            maximizable:false,
            modal:true,
            buttons:[{
                    text:'提交',
                    handler:function(){
                        var validate = $("#pageForm").form('validate');
                        if(validate){
                            $('#pageForm').submit();    
                        }
                }
            },{
                    text:'返回',
                    handler:function(){
                        d.panel('close');
                    }
                }]
        });
    
  
}

function logout(){	
	window.location.href ='${ctx}/toLogOut';	
}
</script>
</body>
</html>
