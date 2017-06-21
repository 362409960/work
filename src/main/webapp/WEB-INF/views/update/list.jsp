<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>患者数据查询</title>
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
		
       	        商品名称: <input type="text" name="productName" class="easyui-validatebox inp" maxlength="100" data-options="validType:['specialCharacters','length[0,100]'], width:150,prompt: '商品名称'"/>       	       
       	        状态: <select  name="isShelves"  class="select"><option value="">全部</option><option value="1">已上架</option><option value="0">已下架</option></select>  
       	        创建时间: <input type="text" name="startTime" class="easyui-validatebox inp" data-options="width:300,prompt: '创建时间'" onClick="WdatePicker()"/>

		        <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="cx()">查询</a>
		        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove icon-remove-bg1" plain="true" onclick="qk();">重置</a>
			</form>
	 	   	
        </div> 
        <shiro:hasPermission name="productInfo:create">
         &nbsp;<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="add();">添加</a>		
       </shiro:hasPermission>
</div>
 <table id="dg" style="width: 100%; height: 1000px" ></table>
	
<div id="dlg"></div>  


<script type="text/javascript">
var dg;
var d;
$(function(){   
	dg=$('#dg').datagrid({    
	method: "post",
    url:'${ctx}/productInfo/list', 
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
		{field:'productName',title:'商品名称',sortable:true,width:'10%',formatter:function(value,row,index){   
            return '<span style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;width:98%;display:inline-block;" title='+value+'>'+value+'</span>'  
        }},
		{field:'miniPicUrl',title:'图片',sortable:true,width:'10%',formatter:function(value,row,index){return '<img src="'+row.miniPicUrl+'" style="vertical-align:middle;"  width="50" height="60"/>';}},
		
        {field:'price',title:'价格',sortable:true,width:'10%', formatter:function(val,rowData,rowIndex){
            if(val!=null)
                return val.toFixed(2);
       }},    
        {field:'stock',title:'库存',sortable:true,width:'10%'},
        {field:'sales',title:'销量',sortable:true,width:'10%'},
        {field:'createTime',title:'创建时间',sortable:true,width:'15%',formatter: formatDatebox,editor: 'datebox'},    
        {field:'isShelves',title:'状态',sortable:true,width:'10%',formatter:function(value,row,index){var isbest = row.isShelves;if('1'==isbest){return '已上架'}else{return '已下架'}}  },  
         {field:'_operate',title:'操作',sortable:false,width:'20%',align:'center',
			formatter : function(value, rowData, rowIndex) {	
					var html='<shiro:hasPermission name="productInfo:view"><button onclick="view(\''+rowData.id+'\'\)">查看</button></shiro:hasPermission>&nbsp;';
				      if(rowData.isShelves == 0){
				    	  html+='<shiro:hasPermission name="productInfo:upAndDownGoods"><button  onclick="up(\''+rowData.id+'\'\)">上架</button></shiro:hasPermission>&nbsp;'
				    	  +'<shiro:hasPermission name="productInfo:update"><button  onclick="edit(\''+rowData.id+'\'\)">修改</button></shiro:hasPermission>&nbsp;'+
				    	  '<shiro:hasPermission name="productInfo:delete"><button  onclick="del(\''+rowData.id+'\'\)">删除</button></shiro:hasPermission>';
				      }else{
				    	  html+='<shiro:hasPermission name="productInfo:upAndDownGoods"><button  onclick="down(\''+rowData.id+'\'\)">下架</button></shiro:hasPermission>';
				      }
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
	    href:'${ctx}/productInfo/create',
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
//弹窗查看
function view(id){	
	d=$("#dlg").dialog({   
	    title: '查看商品详情',    
	    width: 65+'%',    
	    height:90+'%',   
	    href:'${ctx}/productInfo/goView?id='+ id,
	    maximizable:false,
	    modal:true,
	    buttons:[{
			text:'关闭',
			handler:function(){
				d.panel('close');
			}
		}]
	});
}



//弹窗修改
function edit(id) {	
		d = $("#dlg").dialog({
			title : '修改商品',
			width: 65+'%',    
		    height: 90+'%',    
			href : '${ctx}/productInfo/toEdit?id=' + id,
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
	$.messager.confirm('提示', '确定删除该商品吗？', function(data){
		if (data){
			$.ajax({
				type:'post',
				url:"${ctx}/productInfo/delete",
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
//下架
function down(id){			
	$.messager.confirm('提示', '确定下架该商品吗？', function(data){
		if (data){
			$.ajax({
				type:'post',
				url:"${ctx}/productInfo/upDown",
				data:{id:id,state:'0'},
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
//上架
function up(id){		
	$.messager.confirm('提示', '确定上架该商品吗？', function(data){
		if (data){
			$.ajax({
				type:'post',
				url:"${ctx}/productInfo/upDown",
				data:{id:id,state:'1'},
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