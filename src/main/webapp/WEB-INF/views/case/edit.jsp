<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<!DOCTYPE html>

<html>
<head>
<title>患者数据修改</title>
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
	<form id="mainform" action="${ctx}/case/edit" method="post" autocomplete="off" enctype="multipart/form-data">	
		<div id="tt" class="easyui-tabs" style="height: 525px" data-options="border:false, fit:true">
		  <input type="hidden" name="id" id="id" value="${caseInfo.id}" /> 
			<div title="基本属性" id="send_datails" style="padding: 10px">


				<div class="wrap">				 
						<div class="form-control">
						<label class="labelPad">病人姓名:</label> 
					 		<input id="patientName" name="patientName" type="text" value="${caseInfo.patientName}" class="easyui-validatebox inp"    />
					</div>
					<div class="form-control">
						<label class="labelPad">病人号:</label> 
					 		<input id="patinetNo" name="patinetNo" type="text" value="${caseInfo.patinetNo}"  class="easyui-validatebox inp"   />
					</div>
					<div class="form-control">
						<label class="labelPad">医院:</label> 
					 		<input id="hospital" name="hospital" type="text" value="${caseInfo.hospital}"  class="easyui-validatebox inp"    />
					</div>
					<div class="form-control">
						<label class="labelPad">住院号:</label> 
					 		<input id="hospitalNo" name="hospitalNo" type="text" value="${caseInfo.hospitalNo}"  class="easyui-validatebox inp"    />
					</div>
					<div class="form-control">
						<label class="labelPad">检查部位:</label> 
					 		<input id="checkParts" name="checkParts" type="text" value="${caseInfo.checkParts}" class="easyui-validatebox inp"    />
					</div>
					<div class="form-control">
						<label class="labelPad">检查类型:</label> 
					 		<input id="checkType" name="checkType" type="text" value="${caseInfo.checkType}"  class="easyui-validatebox inp"    />
					</div>
					<div class="form-control">
						<label class="labelPad">门诊号:</label> 
					 		<input id="clinicNumber" name="clinicNumber" type="text" value="${caseInfo.clinicNumber}""  class="easyui-validatebox inp"    />
					</div>
					<div class="form-control">
						<label class="labelPad">报告医生:</label> 
					 		<input id="reportDoctor" name="reportDoctor" type="text" value="${caseInfo.reportDoctor}"  class="easyui-validatebox inp"   />
					</div>
					<div class="form-control">
						<label class="labelPad">年龄:</label> 
					 		<input id="age" name="age" type="text" value="${caseInfo.age}"  class="easyui-validatebox inp"   />
					</div>
					<div class="form-control">
						<label class="labelPad">报告医生:</label> 
					 		<input id="reportDoctor" name="reportDoctor" type="text" value="${caseInfo.reportDoctor}"  class="easyui-validatebox inp"    />
					</div>
					<div class="form-control">
						<label class="labelPad">报告医生:</label> 
					 		<input id="reportDoctor" name="reportDoctor" type="text" value="${caseInfo.reportDoctor}"  class="easyui-validatebox inp"    />
					</div>
					<div class="form-control">
						<label class="labelPad">性别:</label> 
						  <select name="sex" id="sex" class="easyui-validatebox select" data-options="validType:'selectRequired[\'#sex\']'" >	
						  		<c:choose>
								       <c:when test="${caseInfo.userType eq '0'}"><option value="0" selected="selected">女</option></c:when>
								       <c:when test="${caseInfo.userType eq '1'}"><option value="1" selected="selected">男</option></c:when>
								 </c:choose>						 
								
							</select>
					</div>
					<div class="form-control">	
						 <label class="labelPad">&nbsp;&nbsp;&nbsp;检查时间:
						 </label><input type="text" id="checkTime" style="width: 140px" value="${caseInfo.checkTimeIng}"  name="checkTime" class="easyui-validatebox inp"  onClick="WdatePicker()"/> 
			        </div>	
			        <div class="form-control">	
						 <label class="labelPad">&nbsp;&nbsp;&nbsp;报告时间:
						 </label><input type="text" id="reportTime" style="width: 140px"  value="${caseInfo.reportTimeIng}" name="reportTime" class="easyui-validatebox inp"  onClick="WdatePicker()"/> 
			        </div>	
			        <div class="form-control">	
						 <label class="labelPad">&nbsp;&nbsp;&nbsp;申请时间:
						 </label><input type="text" id="applicationTime" style="width: 140px" value="${caseInfo.applicationTimeIng}"  name="applicationTime" class="easyui-validatebox inp"  onClick="WdatePicker()"/> 
			        </div>	
				</div>
			</div>


			<div id="send_target" title="图片" style="padding: 10px">
				<table id="productImageTable" class="item tabContent">
					<colgroup>
						<col />
					</colgroup>
					<tr>
						<td colspan="4"><a href="javascript:;" id="addProductImage" class="button">增加图片</a>
						
						</td>
					</tr>
					<tr>
						<th>文件</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${patiList}" var="it" varStatus="st">
			       	<tr>
						<td>						
							 <div class="file clear-fix"> 
	                          <div id="divPreview${st.index}" class="upload fl" >
	                              <img id="imgHeadPhoto${st.index}" src="${it.fUrl}"  width="143"  height="143" >
	                          </div> 
	                         </div>					
							<input type="hidden" name="productPic[${st.index}].id" value="${it.id}" />						 
						</td>						
						
						<td>
							<button  class="remove button">[删除]</button>
						</td>
					</tr>
				</c:forEach>
				</table>
			</div>
			
			
		
			
			

		</div>

	</form>

<script type="text/javascript">		


$(function() {
	var productImageIndex = '${picLength}';
	var $productImageTable = $("#productImageTable");
	var $addProductImage = $("#addProductImage");	
	//增加图片
	$addProductImage.click(function() {	
		  var imgStr = 
		        '<div class="file clear-fix"> '+
		        '<div id="divPreview"' + productImageIndex+ ' class="fileupload fl" > '+
		        '    <img class="imgBox" id="imgHeadPhoto' + productImageIndex+ '"    width="143"  height="143" >'+
		        '</div>  '+
		        '<a href="javascript:;" class="uploadFile aMarimg" > 选择文件'+
		        '  <input  type="file" data-options="required:\'required\'" name="productPic[' + productImageIndex + '].picFile"   id="file1" class="easyui-validatebox inp">'+
		        '</a>' +
		        '</div> ';
		       
		    $productImageTable.append(
		    '<tr> <td>'+imgStr+'<\/td>   <td> <button onclick="" class="remove">删除</button><\/td> <\/tr>'        );
		    productImageIndex ++;
		    $.parser.parse('#productImageTable'); 
		
	});

	
	// 删除图片
	$productImageTable.on("click", "button.remove", function() {
		$(this).closest("tr").remove();
		$.parser.parse('#productImageTable');
	});
	
});
//提交form表单
$('#mainform').form({
				onSubmit : function() {
					var isValid = $(this).form('validate');		
					var imgHeadPhoto =  $("#imgHeadPhoto").attr('src');    
					
					if (undefined == imgHeadPhoto || ''== imgHeadPhoto) {
						var file = $("#file").val();
						if('' == file){
							psmaMessageShowOne("展示图片不可为空！");
							return false;
						}
					}
					var weixinfile = $("#weixinfile").val();
					
					var imgweixinHeadPhoto = $("#imgweixinHeadPhoto").attr('src');  
					
					if (undefined == imgweixinHeadPhoto || '' == imgweixinHeadPhoto) {
						if ('' == weixinfile) {
							psmaMessageShowOne("缩微图片不可为空！");
							return false;
						}
					}
					var details=$.trim(CKEDITOR.instances.details.getData());				
					if (details == '') {
						psmaMessageShowOne("请填写商品详情！");
						return false;
					}
					if(details.length > 15000){
						psmaMessageShowOne("商品详情的内容不可超过15000字!");
						return false;
					}
			
					var details=$.trim(CKEDITOR.instances.introduction.getData());				
					if (details == '') {
						psmaMessageShowOne("请填写商品参数！");
						return false;
					}
					if(details.length > 5000){
						psmaMessageShowOne("商品参数的内容不可超过5000字!");
						return false;
					}
					 var table = $("#productImageTable tr:gt(1) ").html();
					if(undefined == table){
						psmaMessageShowOne("请添加商品图片！");
						return false;
					}
					var stock= $("input[name='productSkuStock[0].price']").val();
					if(undefined == stock){
						psmaMessageShowOne("请按照规格添加数据！");
						return false;
					}
					
					var $td=$('.data-list').find('td');
					var flag=true;
					$td.each(function(index, element) {
			            var $this=$(this),
							$text=$.trim($this.html());
						if($text==''){
							flag=false;	
							return false;
						}
			        });

					if(!flag){
						psmaMessageShowOne("如果选择了规格，就必须选择规则明细！");
						return false;
					}
					
					var $picTd = $("#productImageTable tr:gt(1)");
					var p = true;
					$picTd.each(function(i,e){
						var $this = $(this);
						if(undefined == $this.eq(0).find("img")[0]){
							return true;
						}else{
							var $img = $.trim($this.eq(0).find("img")[0].src);
							if ("" == $img) {
								p = false;
								return false;
							}
						}	
					});
					
					if(!p){
						psmaMessageShowOne("增加了商品行,就必须添加图片！");
						return false;
					}
					
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
