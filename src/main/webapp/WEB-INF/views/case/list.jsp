<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>患者数据查询</title>
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
<script type="text/javascript" src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script>


</head>
<body style="font-family: '微软雅黑'">
<div id="tb" style="padding:5px;height:auto">
        <div>
        	
        	<form id="searchFrom"  method="post">
		
       	        病人姓名: <input type="text" name="patientName" class="easyui-validatebox inp" maxlength="100" data-options="validType:['specialCharacters','length[0,100]'], width:150,prompt: '病人姓名'"/>       	       
       	        报告状态: <select  name="reportState"  class="select"><option value="">全部</option><option value="1">已完成</option><option value="0">等待会诊</option></select>  
       	        申请时间: <input type="text" name="startTime" class="easyui-validatebox inp" data-options="width:300,prompt: '申请时间'" onClick="WdatePicker()"/>

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
    url:'${ctx}/case/list', 
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
		{field:'patientName',title:'病人名称',sortable:true,width:'10%',formatter:function(value,row,index){   
            return '<span style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;width:98%;display:inline-block;" title='+value+'>'+value+'</span>'  
        }},
        {field:'patinetNo',title:'病人号',sortable:true,width:'10%'},
        {field:'hospital',title:'医院',sortable:true,width:'10%'},
        {field:'hospitalNo',title:'住院号',sortable:true,width:'10%'},
        {field:'checkParts',title:'检查部位',sortable:true,width:'10%'},
        {field:'checkType',title:'检查类型',sortable:true,width:'10%'},
        {field:'clinicNumber',title:'门诊号',sortable:true,width:'10%'},
        {field:'reportDoctor',title:'报告医生',sortable:true,width:'10%'},
        {field:'reportState',title:'报告状态',sortable:true,width:'10%',formatter:function(value,row,index){var isbest = row.isShelves;if('1'==isbest){return '已完成'}else{return '等待会诊'}}  },  
        {field:'sex',title:'性别',sortable:true,width:'10%',formatter:function(value,row,index){var isbest = row.isShelves;if('1'==isbest){return '男'}else{return '女'}}  },  
        {field:'age',title:'年龄',sortable:true,width:'10%'},
        {field:'checkTime',title:'检查时间',sortable:true,width:'10%',formatter: formatDatebox,editor: 'datebox'},    
        {field:'reportTime',title:'报告时间',sortable:true,width:'10%',formatter: formatDatebox,editor: 'datebox'}, 
        {field:'applicationTime',title:'申请时间',sortable:true,width:'10%',formatter: formatDatebox,editor: 'datebox'}, 
         {field:'_operate',title:'操作',sortable:false,width:'10%',align:'center',
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
	    href:'${ctx}/case/create',
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
			title : '修改病例',
			width: 65+'%',    
		    height: 90+'%',    
			href : '${ctx}/case/toEdit?id=' + id,
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
	$.messager.confirm('提示', '确定删除该病例吗？', function(data){
		if (data){
			$.ajax({
				type:'post',
				url:"${ctx}/case/delete",
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