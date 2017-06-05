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

</head>

<body>
	<form id="mainform" action="${ctx}/user/edit" method="post" autocomplete="off" enctype="multipart/form-data">	
		<div id="tt" class="easyui-tabs" style="height: 525px" data-options="border:false, fit:true">
		  <input type="hidden" name="id" id="id" value="${productInfo.id}" /> 
		  <input type="hidden" name="shopId" id="shopId" value="${productInfo.shopId}" /> 
		  <input type="hidden" name="isStand" id="isStand" value="${productInfo.isStand}" /> 
			<div title="基本属性" id="send_datails" style="padding: 10px">


				<div class="wrap">				 
						<div class="form-control">
							<label class="labelPad">商品名称:</label> 
						 		<input id=productName name="productName"  value="${productInfo.productName}" maxlength="30"  type="text" class="easyui-validatebox inp" data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
						</div>
						<div class="form-control">
							<label class="labelPad">行业分类:</label> 
							 	<select id="sortId" name="sortId"	class="easyui-validatebox select" data-options="validType:'selectRequired[\'#sortId\']'" disabled="disabled" >
								<option value="">请选择行业分类</option>
								<c:forEach items="${productSortList}" var="it" varStatus="st">
								    <c:choose>
								       <c:when test="${productInfo.sortId eq it.id}"><option value="${it.id}" selected="selected">${it.sortName}</option></c:when>
								       <c:otherwise><option value="${it.id}">${it.sortName}</option></c:otherwise> 
								    </c:choose>
								     
								  </c:forEach>
								</select>
						</div>	 							
						<div class="form-control">    
                            <label class="labelPad">展示图片:</label>    
	                        <div class="file clear-fix"> 
	                          <div id="divPreview" class="upload" >
	                              <img id="imgHeadPhoto" class="imgBox" src="${productInfo.picPath}"  >
	                         
	                          </div>
	             
	                       </div>
	                       <a href="javascript:;" class="uploadFile aChoice"  style="margin: 10px  0 0 27px"> 选择文件
	                          <input  type="file" name="file"  onchange="PreviewImage(this,'imgHeadPhoto','divPreview',true,300,300,true,2);"  id="file" class="easyui-validatebox inp">
	                       </a>    
	                       <div class="note imgMessage">限制尺寸300*300，文件大小需在2M以内，支持.jpg,.bmp,.gif,.png格式。</div>                                
                    </div>	
                   
				</div>
			</div>


		</div>

	</form>

<script type="text/javascript">		


$(function(){
	var disLabel=$('label[data-parent]'),
		$table=$('.table-01'),
		pLen=$('label[data-target]').length,
		$tableList=$('.data-list'),		
		$dataname=$('label[data-name]'),    
		$tableTarget=$tableList.find('table');
	
	
	//如果规格没有选中，规格明细为不可选择的
	var parentLabel = $('label[data-target]');
	$.each(parentLabel, function (key,value) {
		var $that = $(this);
		if(!$that.hasClass("checked")){
			var $target=$that.data('target');
			$('label[data-parent^='+$target+']').addClass('disabled')
		}
		
	});
        
	
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
//添加样式
function addFieldClass(obj){		
	//$(obj).addClass("easyui-validatebox");
	//$(obj).attr('data-options','required:true,validType:\'integerzs\'')
	var text=trim($(obj).val());
	if(!/^[+]?[1-9]+\d*$/i.test(text)){
		$(obj).val('');
		psmaMessageShowOne("请输入正整数");
	}
	
}	

function addPriceClass(obj){
	var text=trim($(obj).val());
	if(!/^\d+(\.\d+)?$/i.test(text)){
		$(obj).val('0.01');
		psmaMessageShowOne("请输入数字，并确保格式正确");
	}
}

function addStockClass(obj){
	var text=trim($(obj).val());
	if(!/^([1-9]\d*|[0]{1,1})$/.test(text)){
		$(obj).val('0');
		psmaMessageShowOne("请输入正整数");
	}
}
</script>
</body>
</html>
