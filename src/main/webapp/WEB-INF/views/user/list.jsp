<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>用户展示</title>





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
<body style="font-family: '微软雅黑'">
<div id="tb" style="padding:5px;height:auto">
        <div>
        	
        	<form id="searchFrom"  method="post">
		
       	        用户名称: <input type="text" name="username" class="easyui-validatebox inp" maxlength="100" />       	       
       	        用户类型: <select  name="userType"  class="select"><option value="">全部</option><option value="1">上传用户</option><option value="2">查看用户</option></select>  
       	        
		        <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="cx()">查询</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove icon-remove-bg1" plain="true" onclick="qk();">重置</a>
			</form>
	 	   	
        </div> 
         &nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>		
    
</div>
 <table id="dg" style="width: 100%; height: 1000px" ></table>
	
<div id="dlg"></div>  


<script type="text/javascript">
var dg;
var d;
$(function(){   
	dg=$('#dg').datagrid({    
	method: "post",
    url:'${ctx}/user/list', 
    fit : true,
	fitColumns : true,
	border : false,
	striped : true,
	idField : 'id',
	pagination : true,
	rownumbers : true,
	pageNumber : 1,
	pageSize : 10,
	pageList : [ 10, 20, 50, 100 ],
	singleSelect : true,
	selectOnCheck : false,
	checkOnSelect : false,
	 remoteSort:false,
	 maximizable:false,
	 sortExpertMode:false,
    columns:[[    
		{field:'username',title:'用户名称',sortable:true,width:'20%',formatter:function(value,row,index){   
            return '<span style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;width:98%;display:inline-block;" title='+value+'>'+value+'</span>'  
        }},
        {field:'userType',title:'用户类型',sortable:true,width:'20%',formatter:function(value,row,index){var isbest = row.userType;if('0'==isbest){return '超级管理员'}else if('1'==isbest){return '患者用户'}else{return '医务用户'}}  },  
        {field:'createTime',title:'创建时间',sortable:true,width:'20%',formatter: formatDatebox,editor: 'datebox'},    
        {field:'updateTime',title:'更新时间',sortable:true,width:'20%',formatter: formatDatebox,editor: 'datebox'}, 
         {field:'_operate',title:'操作',sortable:false,width:'20%',align:'center',
			formatter : function(value, rowData, rowIndex) {	
			  var html ='<button  onclick="edit(\''+rowData.id+'\'\)">修改</button>&nbsp;'+
				  '<button  onclick="del(\''+rowData.id+'\'\)">删除</button>';
				     
				   return html;  
			}
		}
		  
    ]],
    enableHeaderClickMenu: false,
    enableHeaderContextMenu: false,
    enableRowContextMenu: false,
    toolbar:'#tb'
});
	
});

//弹窗增加
function add() {
	d=$("#dlg").dialog({   
	    title: '添加',    
	    width: 65+'%',    
	    height: 90+'%',    
	    href:'${ctx}/user/create',
	    closed: false,    
	    cache: false,
	    maximizable:false,
	    modal:true,	   
	    buttons:[{
			text:'确认',
			handler:function(){	
				$("#mainform").submit(); 
			}
		},{
			text:'取消',
			handler:function(){
					d.panel('close');
				}
		}]
	});
}



//弹窗修改
function edit(id) {	
		d = $("#dlg").dialog({
			title : '修改用户',
			width: 65+'%',    
		    height: 90+'%',    
			href : '${ctx}/user/toEdit?id=' + id,
			maximizable : false,
			closed: false,    
		    cache: false,
			modal : true,			
			buttons : [ {
				text : '修改',
				handler : function() {
					$('#mainform').submit(); 
				}
			}, {
				text : '取消',
				handler : function() {
					d.panel('close');
				}
			} ]
		});
}
//删除
function del(id){	
	$.messager.confirm('提示', '确定删除该用户吗？', function(data){
		if (data){
			$.ajax({
				type:'post',
				url:"${ctx}/user/delete",
				data:{id:id},
				success: function(data){					
					if(data=='failure'){						
						psmaMessageShowOne("查询数据库出错,删除失败！");		
					}else{
						$('#dg').datagrid('uncheckAll');					
						dg.datagrid('reload');
					}
				}
			});
		} 
	});
}



//清空查询条件
function qk(){
	var texts = document.getElementsByTagName("input");
	for(var i = 0;i < texts.length;i++)
	{
		texts[i].value="";
    }
	var selects = document.getElementsByTagName("select");
    for(var i = 0;i < selects.length;i++)
    {
    	selects[i].options[0].selected = true;
    }
}

//创建查询对象并查询
function cx() {
	var isValid = $("#searchFrom").form('validate');		
	if(isValid){
		var obj = $("#searchFrom").serializeObject();
		dg.datagrid('reload', obj);
	}

}
</script>
</body>
</html>