<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>患者数据修改</title>
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
	<form id="mainform" action="${ctx}/productInfo/edit" method="post" autocomplete="off" enctype="multipart/form-data">	
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
                    <div class="form-control">	
						   <label class="labelPad">分享展示图片:</label> 
			            <div class="file clear-fix"> 
		                  <div id="divPreviewwei" class="upload" >
		                      <img id="imgweixinHeadPhoto"  class="imgBox" src="${productInfo.weixinPicUrl}">
		                  </div>    		
			             </div>
			             <a href="javascript:;" class="uploadFile aChoice"  style="margin: 10px  0 0 27px" id="weixinhr">
		              	   选择文件
		             	  <input   type="file" name="weixinfile"  onchange="preview(this,'imgweixinHeadPhoto','divPreviewwei',true,60,60,true,32);"  id="weixinfile" class="easyui-validatebox inp">
		              </a>
			           	<div class="note imgMessage">限制尺寸60*60，文件大小需在32K以内，支持.jpg,.bmp,.gif,.png格式。</div>	        									
					</div>	
                    
					<div class="form-control">
					<table class="table-01 ">
					<tr>
						<th>商品详情:</th>
						<td colspan="6">
							<textarea  name="details" id="details" class="editor" 
							 >${productInfo.details}</textarea>
							  
								<script>
								 var editor = CKEDITOR.replace( 'details' );
							     editor.on( 'change', function( event ) {    
							    	    var data = this.getData();//内容
							    	    if(data.length>15000){
							    	    	document.getElementById("detailContent").innerText="商品详情内容不可超过15000字!";
							    	    }else{
							    	    	document.getElementById("detailContent").innerText="";
							    	    }
							    	});
							 </script>
							
						</td>
					</tr>	
					<tr>
					  <th></th>
					  <td><span id="detailContent" style="color: red;"></span></td>
					</tr>				
					<tr>
						<th class="goodsDetail">商品参数：</th>						
						<td colspan="6">
							<textarea name="introduction" id="introduction" class="editor" >${productInfo.introduction}</textarea>
							<script>
							     var editor = CKEDITOR.replace( 'introduction' );
							     editor.on( 'change', function( event ) {    
							    	    var data = this.getData();//内容
							    	    if(data.length>5000){
							    	    	document.getElementById("intContent").innerText="商品参数内容不可超过5000字!";
							    	    }else{
							    	    	document.getElementById("intContent").innerText="";
							    	    }
							    	});
							 </script>
						</td>						
					</tr>
					<tr>
					  <th></th>
					  <td><span id="intContent" style="color: red;"></span></td>
					</tr>	
				</table>
				</div>
				</div>
			</div>


			<div id="send_target" title="商品图片" style="padding: 10px">
				<table id="productImageTable" class="item tabContent">
					<colgroup>
						<col />
					</colgroup>
					<tr>
						<td colspan="4"><a href="javascript:;" id="addProductImage" class="button">增加图片</a>
						 <div class="note">限制尺寸1080*610，文件大小需在2M以内，支持.jpg,.bmp,.gif,.png格式。</div>
						</td>
					</tr>
					<tr>
						<th>文件</th>						
						<!-- <th>排序</th> -->
						<th>操作</th>
					</tr>
					<c:forEach items="${productPicList}" var="it" varStatus="st">
			       	<tr>
						<td>						
							 <div class="file clear-fix"> 
	                          <div id="divPreview${st.index}" class="upload fl" >
	                              <img id="imgHeadPhoto${st.index}" src="${it.showPicUrl}"  width="143"  height="143" >
	                          </div> 
	                         </div>					
							<input type="hidden" name="productPic[${st.index}].id" value="${it.id}" />						 
						</td>						
						<%-- <td>
							<input type="text" name="productPic[${st.index}].sort"  value="${it.sort}" maxlength="9"  class="easyui-validatebox inp"  data-options="validType:'integerzszeo'" />
						</td> --%>
						<td>
							<button  class="remove button">[删除]</button>
						</td>
					</tr>
				</c:forEach>
				</table>
			</div>
			
			
		<div id="send_target" title="商品属性" style="padding: 10px">
			<div class="form-control">						
							<table id="sortPty">
							<tr>
							<c:forEach items="${sortMap}" var="item" varStatus="st">
							
							<th>${item.key.propertyName}:</th>
							<td>
							<input type="hidden" name="productPropertyArray[${st.index}].sortPropertyId" value="${item.key.id}">
							<input type="hidden" name="productPropertyArray[${st.index}].propertyId" value="${item.value.id}">
							<input type="text"  class="easyui-validatebox inp" data-options="validType:['textValidate','length[0,30]']"  name="productPropertyArray[${st.index}].propertyValue" maxlength="30" class="inp" value="${item.value.propertyValue}">
								
							</td>
						     <c:if test="${st.count%2 eq 0}"></td></tr></c:if>
						      <c:if test="${st.count%2  != 0}"></td></c:if> 
							</c:forEach>		
							</table>
						</div> 
			</div>
			
			<div id="send_target" title="商品规格" style="padding: 10px">
				<table class="table-01 mar-t50" id="spec">	
				<!-- <caption>
				    规格
				    </caption> -->
				    <c:forEach items="${mapSepc}" var="ms" varStatus="st">
					    <tr>
					        <c:choose>
					             <c:when test="${ms.key.isCheck eq 0}"><td><label class="checkbox checked"  data-name='${ms.key.id}' data-target='size-${st.index+1}'>${ms.key.specName}</label></td></c:when>
					             <c:otherwise><td><label class="checkbox" data-name='${ms.key.id}' data-target='size-${st.index+1}'>${ms.key.specName}</label></td></c:otherwise>
					        </c:choose>
					      
					    </tr>
					    
					    <tr>
					      <td>
						      <c:forEach items="${ms.value}" var="sp" >
						         <c:choose>
						             <c:when test="${sp.isCheck eq 0}"><label class="checkbox checked" data-name='${sp.id}' data-parent='size-${st.index+1}'>${sp.itemName}</label></c:when>
						             <c:otherwise><label class="checkbox" data-name='${sp.id}' data-parent='size-${st.index+1}'>${sp.itemName}</label></c:otherwise>
						         </c:choose>
						      	
						      </c:forEach>					      
					       </td>
					    </tr>
				    </c:forEach>
									
					</table>
					<div class="data-list" id="data-list">
					  <table>
					    <thead>
					    </thead>
					    <tbody>
							<tr>
							  <c:forEach items="${headList}" var="titl">
								<td>${titl}</td>								
								</c:forEach>
								<td>销售价</td>
								<td>库存</td>
							</tr>
							<c:forEach items="${productStockList}" var="item" varStatus="st">
							<tr>
							   <c:forEach items="${item.link}" var="it">
							   <td>${it}</td>
							   </c:forEach>
								<td><input type="text" name="productSkuStock[${st.index}].price" class="easyui-validatebox inp" maxlength="9" value="${item.price}" data-options= required:true,validType:['intOrFloat','gtZero'] ></td>
								<td><input type="text" name="productSkuStock[${st.index}].stock" class="easyui-validatebox inp"  maxlength="9" value="${item.stock}" data-options="required:true,validType:'integerzszeo'">
								<input type="hidden" name="productSkuStock[${st.index}].skuJson"  value="${item.specStr}">
								<input type="hidden" name="productSkuStock[${st.index}].stockId"  value="${item.id}">
								</td>
							</tr>	
							</c:forEach>	
						</tbody>
					  </table>
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
        
	//判断是否上过架
	  var isStand='${productInfo.isStand}';
	  if('1'!= isStand){
		 
		  $table.on('click','.checkbox',function(e){			 
				var $this=$(this),
					sumArr=[],
					headTr=$tableTarget.find('thead').find('tr'),
					bodyTr=$tableTarget.find('tbody').find('tr');	
					$target=$this.data('target');
				if($this.hasClass('disabled')){
					return;
				}
				if($this.hasClass('checked')){
					$this.removeClass('checked');
					$('label[data-parent^='+$target+']').addClass('disabled').removeClass('checked');
				}else{
					$this.addClass('checked');
					$('label[data-parent^='+$target+']').removeClass('disabled');
				}
				var pCheck=$('label[data-target].checked'),
					pCheckLen=pCheck.length,
					sBodyTr='',
					sHeadTr='';	
				headTr.remove();
				bodyTr.remove();
				sHeadTr+='<tr>';
				for(var i=0;i<pCheckLen;i++){
					var dataParent=pCheck.eq(i).data('target'),
						arr=[],	
						childCheck=$('label[data-parent^='+dataParent+'].checked');
					sHeadTr+='<td>'+pCheck.eq(i).text()+'</td>';
					if(i==pCheckLen-1){
						sHeadTr+='<td>销售价</td><td>库存</td>';
					}
					if(childCheck.length==0){
						arr.push('');
					}else{
						childCheck.each(function(index, element) {
							var $this=$(this);
							//拿到指定的值后，通过添加&拼接进去
							$parent=$this.data('parent'),
							$parentName=$('label[data-target^='+$parent+'].checked').data('name');
							var $value=$parentName+'#'+$this.data('name');
							if($this.text()){
								arr.push($this.text()+"&"+$value);						
							}
						});	
					}
					
					if(arr.length!=0){
						sumArr.push(arr);
					}			
				}
				var index=0;		
			    function getSKU(loc,arrayIds){	    	
					if(sumArr.length==0) return false;
					for(var i = 0;i< sumArr[loc].length; i++){	
						 if (loc < sumArr.length - 1){
							if (arrayIds != ""){
								arrayIds + ";"
							}
							getSKU(loc + 1,arrayIds + sumArr[loc][i] + ";");
						}else{
							createTable(arrayIds + sumArr[loc][i]);
						}
					}
				}
		   		getSKU(0,"");
				sHeadTr+='</tr>';
			
				function createTable(str){				
					if(!str || !str.replace(/;/g,'')) return;			
					if(str.indexOf(';')!==-1){
						 var arr=str.split(';');
						 sBodyTr+='<tr>';
						 var skuJsonValue='';
						 for(var i=0,len=arr.length;i<len;i++){
							 var parameter= arr[i].split('&');
							 sBodyTr+='<td>'+parameter[0]+'</td>';
							 skuJsonValue+=parameter[1]+","
						 }
						 //把最后的,去掉
						 skuJsonValue=skuJsonValue.substring(0,skuJsonValue.length-1);
						 sBodyTr+='<td><input type=text name=productSkuStock[' + index + '].price value=0.01 maxlength="9" class="easyui-validatebox inp" data-options= required:true,validType:[\'intOrFloat\',\'gtZero\'] ></td><td><input type=text name=productSkuStock[' + index + '].stock value=1  maxlength="9" class="easyui-validatebox inp" data-options=required:true,validType:\'integerzszeo\'><input type=hidden name=productSkuStock['+ index +'].skuJson value='+skuJsonValue+'></td><tr>';
						 index++;
					}else{	
						var parameter=str.split('&');
						sBodyTr+='<tr>';
						sBodyTr+='<td>'+parameter[0]+'</td>';
						sBodyTr+='<td><input type=text name=productSkuStock[' + index + '].price value=0.01  maxlength="9" class="easyui-validatebox inp" data-options= required:true,validType:[\'intOrFloat\',\'gtZero\'] ></td><td><input type=text name=productSkuStock[' + index + '].stock value=1 maxlength="9" class="easyui-validatebox inp" data-options=required:true,validType:\'integerzszeo\' ><input type=hidden name=productSkuStock['+ index +'].skuJson value='+parameter[1]+'></td><tr>';
						 index++;
					}	
				}		
				$tableTarget.append(sHeadTr);			
				$tableTarget.append(sBodyTr);	
				$.parser.parse('#data-list'); 
			});
		 
			return false;
	  }else{
		  $table.on('click','.checkbox',function(e){
			  psmaMessageShowOne("该产品已上架过，无法为该产品增加商品规格！");			
		  });
	  }
	  
});

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
		        '  <input  type="file" data-options="required:\'required\'" name="productPic[' + productImageIndex + '].picFile"  onchange="PreviewImage(this,\'imgHeadPhoto' +
		        productImageIndex+ '\',\'divPreview' + productImageIndex+ '\',true,1080,610,true,2);"  id="file1" class="easyui-validatebox inp">'+
		        '</a>' +
		        '</div> ';
		       
		    $productImageTable.append(
		    '<tr> <td>'+imgStr+'<\/td>   <td> <button onclick="" class="remove">删除</button><\/td> <\/tr>'        );
		    productImageIndex ++;
		    $.parser.parse('#productImageTable'); 
		
	});

	

	
	
	
	
	
	
	// 删除商品图片
	$productImageTable.on("click", "button.remove", function() {
		$(this).closest("tr").remove();
		$.parser.parse('#productImageTable');
	});
	//行业选择
	$("#sortId").combobox({
		onChange: function (n,o) {
			
			//清空图片显示div
	        $("#imgHeadPhoto").attr("src",""); 
	        //清空file域
	        var file = $("#file") ;
	        file.after(file.clone().val(""));      
	        file.remove(); 
	        $("#imgweixinHeadPhoto").attr("src",""); 
	        //清空file域	        
	        var weixinfile = $("#weixinfile") ;
	        weixinfile.after(file.clone().val(""));      
	        weixinfile.remove();  
	        
	        $.parser.parse('#weixinhr'); 
	        
	       $("#productImageTable tr:gt(1)").html("");
	       
	    	$('#data-list').find('td').remove();
			//把规格和商品属性拿出来
			$.ajax({
				type:'post',
				url:"${ctx}/productInfo/sortByData",
				data:{id:n},
				success: function(data){
					var jsonObj=data;
					//规格
					var spec=$("#spec");
					spec.html("");
					var spec_html="";
					var k=1;
					$.each(jsonObj.sortSpec, function (key,value) {
						var dataObj=key.split("@");
						var size="size"+k;
						spec_html+="<tr><td><label class=\"checkbox\" data-target=\""+size+"\"> <input type=\"checkbox\" name=\"specName\" value=\""+dataObj[1]+"\"> "+dataObj[0]+"</label></td></tr><tr><td>";
					   $.each(value, function (i,item) {						 
						   spec_html+="<label class=\"checkbox disabled\" data-parent=\""+size+"\"> <input type=\"checkbox\" name=\"itemName\" value=\""+item.id+"\">"+item.itemName+"</label>" 
					   });
					   spec_html+="</td></tr>";
					   k++;
					});
					spec.html(spec_html);
					//分类
					var sortPty=$("#sortPty");
					sortPty.html("");
					var sortPhtml="<tr>";
					$.each(jsonObj.sortProperty, function (i,item) {
						sortPhtml+='<th>'+item.propertyName+':</th><td><input type="text"   name="productPropertyArray['+ i +'].propertyValue"  maxlength="30" class="easyui-validatebox inp" data-options= validType:\'length[0,30]\'><input type="hidden" name="productPropertyArray['+ i +'].sortPropertyId" value='+item.id+'></td>';
						if((i+1)%2==0){
							sortPhtml+="</td></tr>";
						}else{
							sortPhtml+="</td>"
						}
					});					
					sortPty.html(sortPhtml);
					  $.parser.parse('#sortPty');
				}
			});
		}
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
