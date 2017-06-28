<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>上传患者数据信息</title>
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

<body>
	<form id="mainform" action="${ctx}/case/save" method="post" autocomplete="off" enctype="multipart/form-data">	
		<div id="tt" class="easyui-tabs" style="height:600px" data-options="border:false, fit:true">
		
			<div title="编辑内容" id="datails" style="padding: 10px" data-options="fit:true">
				<div class="wrap">				
					<div class="form-control">
						<label class="labelPad">病人姓名:</label> 
					 		<input id="patientName" name="patientName" type="text" maxlength="30"  class="easyui-validatebox inp"   data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
					</div>
					<div class="form-control">
						<label class="labelPad">病人号:</label> 
					 		<input id="patinetNo" name="patinetNo" type="text" maxlength="30"  class="easyui-validatebox inp"   data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
					</div>
					<div class="form-control">
						<label class="labelPad">医院:</label> 
					 		<input id="hospital" name="hospital" type="text" maxlength="30"  class="easyui-validatebox inp"   data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
					</div>
					<div class="form-control">
						<label class="labelPad">住院号:</label> 
					 		<input id="hospitalNo" name="hospitalNo" type="text" maxlength="30"  class="easyui-validatebox inp"   data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
					</div>
					<div class="form-control">
						<label class="labelPad">检查部位:</label> 
					 		<input id="checkParts" name="checkParts" type="text" maxlength="30"  class="easyui-validatebox inp"   data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
					</div>
					<div class="form-control">
						<label class="labelPad">检查类型:</label> 
					 		<input id="checkType" name="checkType" type="text" maxlength="30"  class="easyui-validatebox inp"   data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
					</div>
					<div class="form-control">
						<label class="labelPad">门诊号:</label> 
					 		<input id="clinicNumber" name="clinicNumber" type="text" maxlength="30"  class="easyui-validatebox inp"   data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
					</div>
					<div class="form-control">
						<label class="labelPad">报告医生:</label> 
					 		<input id="reportDoctor" name="reportDoctor" type="text" maxlength="30"  class="easyui-validatebox inp"   data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
					</div>
					<div class="form-control">
						<label class="labelPad">年龄:</label> 
					 		<input id="age" name="age" type="text" maxlength="30"  class="easyui-validatebox inp"   data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
					</div>
					<div class="form-control">
						<label class="labelPad">报告医生:</label> 
					 		<input id="reportDoctor" name="reportDoctor" type="text" maxlength="30"  class="easyui-validatebox inp"   data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
					</div>
					<div class="form-control">
						<label class="labelPad">报告医生:</label> 
					 		<input id="reportDoctor" name="reportDoctor" type="text" maxlength="30"  class="easyui-validatebox inp"   data-options="required:'required',validType:['specialCharacters','length[0,30]']" />
					</div>
					<div class="form-control">
						<label class="labelPad">性别:</label> 
						  <select name="sex" id="sex" class="easyui-validatebox select" data-options="validType:'selectRequired[\'#sex\']'" >							 
								<option value="0">女</option>
							    <option value="1">男</option>
							</select>
					</div>
					<div class="form-control">	
						 <label class="labelPad">&nbsp;&nbsp;&nbsp;检查时间:
						 </label><input type="text" id="checkTime" style="width: 140px"  name="checkTime" class="easyui-validatebox inp"  onClick="WdatePicker()"/> 
			        </div>	
			        <div class="form-control">	
						 <label class="labelPad">&nbsp;&nbsp;&nbsp;报告时间:
						 </label><input type="text" id="reportTime" style="width: 140px"  name="reportTime" class="easyui-validatebox inp"  onClick="WdatePicker()"/> 
			        </div>	
			        <div class="form-control">	
						 <label class="labelPad">&nbsp;&nbsp;&nbsp;申请时间:
						 </label><input type="text" id="applicationTime" style="width: 140px"  name="applicationTime" class="easyui-validatebox inp"  onClick="WdatePicker()"/> 
			        </div>	
				</div>
			</div>


			<div id="send_target" title="图片" style="padding: 10px">
				<table id="productImageTable" class="item tabContent">
					<colgroup>
						<col />
					</colgroup>
					<tr>
					   <td colspan="4"><a href="javascript:;" id="addProductImage" class="button">增加图片</a></td>
					 
					</tr>
					<tr>
						<th>文件</th>					
					<!-- 	<th>排序</th> -->
						<th>操作</th>
					</tr>
				</table>
			</div>
			
			

		</div>

	</form>

<script type="text/javascript">		


$(function() {
	//用于新增商品图片时，空间排序，自动增加不重复	 
	var productImageIndex = 0;
	var $productImageTable = $("#productImageTable");
	var $addProductImage = $("#addProductImage");	
	//增加图片
	$addProductImage.click(function() {	 
		var imgStr = 
            '<div class="file clear-fix"> '+
            '<div id="divPreview"' + productImageIndex+ ' class="upload fl" > '+
            '    <img id="imgHeadPhoto' + productImageIndex+ '" class="imgBox">'+
            '</div>  '+
          
            '<a href="javascript:;" class="uploadFile aMarimg" > 选择文件'+
            '  <input  type="file" name="picId[' + productImageIndex + '].picFile"   id="file1" class="easyui-validatebox inp">'+
            '</a>' +
            '</div> ';
           
		$productImageTable.append(
		'<tr> <td>'+imgStr+'<\/td>   <td> <button onclick="" class="remove">删除</button><\/td> <\/tr>'		);
		productImageIndex ++;
		$.parser.parse('#productImageTable');
		 
	});
	


	// 删除商品图片
	$productImageTable.on("click", "button.remove", function() {
		$(this).closest("tr").remove();
		$.parser.parse('#productImageTable');
	});
	
});
var onSubmitFlag = false;
//提交form表单
$('#mainform').form({
				onSubmit : function() {		
					
					var isValid = $(this).form('validate');		
					
			var $picTd = $("#productImageTable tr:gt(1)");
			var p = true;
			$picTd.each(function(i, e) {
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

			if (!p) {
				psmaMessageShowOne("增加了行,就必须添加图片！");
				return false;
			}

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