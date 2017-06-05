function layerAlter(title, message) {
	layer.alert(message, {
		icon : 5,
		title : title
	})
}
function layerSuccess(title, message) {
	layer.alert(message, {
		icon : 1,
		title : title
	})
}

// 删除左右两端的空格
function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

$.fn.serializeObject=function(){  
    var serializeObj={};  
    var array=this.serializeArray();  
    var str=this.serialize();  
    $(array).each(function(){  
        if(serializeObj[this.name]){  
            if($.isArray(serializeObj[this.name])){  
                serializeObj[this.name].push(this.value);  
            }else{  
                serializeObj[this.name]=[serializeObj[this.name],this.value];  
            }  
        }else{  
            serializeObj[this.name]=this.value;   
        }  
    });  
    return serializeObj;  
};


Date.prototype.format = function(format) {  
    var o = {  
        "M+": this.getMonth() + 1, // month  
        "d+": this.getDate(), // day  
        "h+": this.getHours(), // hour  
        "m+": this.getMinutes(), // minute  
        "s+": this.getSeconds(), // second  
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter  
        "S": this.getMilliseconds()  
    }  
    if (/(y+)/.test(format))  
        format = format.replace(RegExp.$1, (this.getFullYear() + "")  
            .substr(4 - RegExp.$1.length));  
    for (var k in o)  
        if (new RegExp("(" + k + ")").test(format))  
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));  
    return format;  
}  

var date = new Date();  
// console.info(date);  
//console.info(date.format("yyyy-MM-dd hh:mm"));

function formatDatebox(value) {  
    if (value == null || value == '') {  
        return '';  
    }  
    var dt;  
    if (value instanceof Date) {  
        dt = value;  
    } else {  
        dt = new Date(value);  
    }  

    return dt.format("yyyy-MM-dd  hh:mm:ss"); //扩展的Date的format方法(上述插件实现)  
}  
//图片上传在线预览

function PreviewImage(fileObj,imgPreviewId,divPreviewId,checkPx,width,height,checkSize,size){  
var allowExtention=".jpg,.bmp,.gif,.png";//允许上传文件的后缀名document.getElementById("hfAllowPicSuffix").value;  
var extention=fileObj.value.substring(fileObj.value.lastIndexOf(".")+1).toLowerCase();              
var browserVersion= window.navigator.userAgent.toUpperCase();  
if(allowExtention.indexOf(extention)>-1){
	//预览图片地址
	var imgSrc ="";
	var fileSize;
    if(fileObj.files){//HTML5实现预览，兼容chrome、火狐7+等  
    	fileSize = fileObj.files[0].size;
        if(window.FileReader){  
            var reader = new FileReader();   
            reader.onload = function(e){  
                imgSrc =e.target.result;  
                moz_validate_img(e.target.result,fileSize,checkPx,width,height,checkSize,size,fileObj,imgPreviewId);
                return false;
            }    
            reader.readAsDataURL(fileObj.files[0]);  
        }else if(browserVersion.indexOf("SAFARI")>-1){  
            alert("不支持Safari6.0以下浏览器的图片预览!");  
        }  
    }else if (browserVersion.indexOf("MSIE")>-1){  
    	var file = fileSystem.GetFile (fileObj.value); 
    	fileSize = file.Size;  
        if(browserVersion.indexOf("MSIE 6")>-1){//ie6  
            imgSrc = fileObj.value;       
            if(validate_img(imgSrc,fileSize,checkPx,width,height,checkSize,size)){
            	 document.getElementById(imgPreviewId).setAttribute("src",fileObj.value); 
            }else{ 
            	$("#" + fileObj.id).select();    
                document.execCommand("delete");    
            	return;
            }
        }else{//ie[7-9]  
            fileObj.select();  
            if(browserVersion.indexOf("MSIE 9")>-1)  
                fileObj.blur();//不加上document.selection.createRange().text在ie9会拒绝访问  
            var newPreview = document.getElementById(divPreviewId+"New");  
            if(newPreview==null){  
                newPreview =document.createElement("div");  
                newPreview.setAttribute("id",divPreviewId+"New");  
                newPreview.style.width = document.getElementById(imgPreviewId).width+"px";  
                newPreview.style.height = document.getElementById(imgPreviewId).height+"px";  
                newPreview.style.border="solid 1px #d2e2e2";  
            }  
                                          
            imgSrc = document.selection.createRange().text;
            if(validate_img(imgSrc,fileSize,checkPx,width,height,checkSize,size)){
            	newPreview.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + document.selection.createRange().text + "')";
            }else{
            	$("#" + fileObj.id).select();    
                document.execCommand("delete");
            	return;
            }
            var tempDivPreview=document.getElementById(divPreviewId);  
            tempDivPreview.parentNode.insertBefore(newPreview,tempDivPreview);  
            tempDivPreview.style.display="none";                      
        }  
    }else if(browserVersion.indexOf("FIREFOX")>-1){//firefox  
        var firefoxVersion= parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);  
        fileSize = fileObj.files[0].size;
        if(firefoxVersion<7){//firefox7以下版本             
            imgSrc =fileObj.files[0].getAsDataURL();
            if(validate_img(imgSrc,fileSize,checkPx,width,height,checkSize,size)){
            	document.getElementById(imgPreviewId).setAttribute("src",fileObj.files[0].getAsDataURL());  
            }else{
            	$("#" + fileObj.id).val(""); 
            	return;
            }
        }else{//firefox7.0+    
            imgSrc =window.URL.createObjectURL(fileObj.files[0]);
            if(validate_img(imgSrc,fileSize,checkPx,width,height,checkSize,size)){
            	document.getElementById(imgPreviewId).setAttribute("src",window.URL.createObjectURL(fileObj.files[0]));
            }else{
            	$("#" + fileObj.id).val(""); 
            	return;
            }
        }  
    }else{       
        imgSrc =fileObj.value;
        fileSize = fileObj.files[0].size;
        if(validate_img(imgSrc,fileSize,checkPx,width,height,checkSize,size)){
        	 document.getElementById(imgPreviewId).setAttribute("src",fileObj.value);  
        }else{
        	$("#" + fileObj.id).val(""); 
        	return;
        }
    }        
 }else{  
	var str = "仅支持"+allowExtention+"为后缀名的文件!";
	layer.alert(str, {icon:5,title:'提示'});
   /*  alert("仅支持"+allowExtention+"为后缀名的文件!");  */ 
    fileObj.value="";//清空选中文件  
    if(browserVersion.indexOf("MSIE")>-1){                          
        fileObj.select();  
        document.selection.clear();  
    }                  
    fileObj.outerHTML=fileObj.outerHTML;  
}  

}

/*
 * 验证图片分辨率及大小
 */
function moz_validate_img(imgSre,fileSize,checkPx,width,height,checkSize,size,fileObj,imgPreviewId){ 
	var image = new Image();
	image.src = imgSre; 	
	var flag=true;
	image.onload=function(){
		document.getElementById(imgPreviewId).removeAttribute("src");  
    	if(checkPx && ((image.width != width) ||(image.height != height))){
    		psmaMessageShowOne('请上传' + width +'*' +height +'像素的图片');
    		flag=false;
    	}
    	if(checkSize && fileSize>size*1024*1024){
    		psmaMessageShowOne('请上传大小小于' + size + 'M的图片');	
    		flag=false;
    	}
    	if(flag){
    		document.getElementById(imgPreviewId).setAttribute("src",imgSre);  
    	}else{
    		$("#" + fileObj.id).val(""); 
    	}
    }
}

function validate_img(imgSre,fileSize,checkPx,width,height,checkSize,size){ 
	var image = new Image();
    image.src = imgSre; 
	if(checkPx && ((image.width != width) ||(image.height != height))){
		psmaMessageShowOne('请上传' + width +'*' +height +'像素的图片');  
		return false;
	}
	if(checkSize && fileSize>size*1024*1024){
		psmaMessageShowOne('请上传大小小于' + size + 'M的图片');
		return false;
	}
	return true;
}



function preview(fileObj,imgPreviewId,divPreviewId,checkPx,width,height,checkSize,size){  
	var allowExtention=".jpg,.bmp,.gif,.png";//允许上传文件的后缀名document.getElementById("hfAllowPicSuffix").value;  
	var extention=fileObj.value.substring(fileObj.value.lastIndexOf(".")+1).toLowerCase();              
	var browserVersion= window.navigator.userAgent.toUpperCase();  
	if(allowExtention.indexOf(extention)>-1){
		//预览图片地址
		var imgSrc ="";
		var fileSize;
	    if(fileObj.files){//HTML5实现预览，兼容chrome、火狐7+等  
	    	fileSize = fileObj.files[0].size;
	        if(window.FileReader){  
	            var reader = new FileReader();   
	            reader.onload = function(e){  
	                imgSrc =e.target.result;  
	                moz_validate_imgwei(e.target.result,fileSize,checkPx,width,height,checkSize,size,fileObj,imgPreviewId);
	                return false;
	            }    
	            reader.readAsDataURL(fileObj.files[0]);  
	        }else if(browserVersion.indexOf("SAFARI")>-1){  
	            alert("不支持Safari6.0以下浏览器的图片预览!");  
	        }  
	    }else if (browserVersion.indexOf("MSIE")>-1){  
	    	var file = fileSystem.GetFile (fileObj.value); 
	    	fileSize = file.Size;  
	        if(browserVersion.indexOf("MSIE 6")>-1){//ie6  
	            imgSrc = fileObj.value;       
	            if(validate_imgwei(imgSrc,fileSize,checkPx,width,height,checkSize,size)){
	            	 document.getElementById(imgPreviewId).setAttribute("src",fileObj.value); 
	            }else{ 
	            	$("#" + fileObj.id).select();    
	                document.execCommand("delete");    
	            	return;
	            }
	        }else{//ie[7-9]  
	            fileObj.select();  
	            if(browserVersion.indexOf("MSIE 9")>-1)  
	                fileObj.blur();//不加上document.selection.createRange().text在ie9会拒绝访问  
	            var newPreview = document.getElementById(divPreviewId+"New");  
	            if(newPreview==null){  
	                newPreview =document.createElement("div");  
	                newPreview.setAttribute("id",divPreviewId+"New");  
	                newPreview.style.width = document.getElementById(imgPreviewId).width+"px";  
	                newPreview.style.height = document.getElementById(imgPreviewId).height+"px";  
	                newPreview.style.border="solid 1px #d2e2e2";  
	            }  
	                                          
	            imgSrc = document.selection.createRange().text;
	            if(validate_imgwei(imgSrc,fileSize,checkPx,width,height,checkSize,size)){
	            	newPreview.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='scale',src='" + document.selection.createRange().text + "')";
	            }else{
	            	$("#" + fileObj.id).select();    
	                document.execCommand("delete");
	            	return;
	            }
	            var tempDivPreview=document.getElementById(divPreviewId);  
	            tempDivPreview.parentNode.insertBefore(newPreview,tempDivPreview);  
	            tempDivPreview.style.display="none";                      
	        }  
	    }else if(browserVersion.indexOf("FIREFOX")>-1){//firefox  
	        var firefoxVersion= parseFloat(browserVersion.toLowerCase().match(/firefox\/([\d.]+)/)[1]);  
	        fileSize = fileObj.files[0].size;
	        if(firefoxVersion<7){//firefox7以下版本             
	            imgSrc =fileObj.files[0].getAsDataURL();
	            if(validate_imgwei(imgSrc,fileSize,checkPx,width,height,checkSize,size)){
	            	document.getElementById(imgPreviewId).setAttribute("src",fileObj.files[0].getAsDataURL());  
	            }else{
	            	$("#" + fileObj.id).val(""); 
	            	return;
	            }
	        }else{//firefox7.0+    
	            imgSrc =window.URL.createObjectURL(fileObj.files[0]);
	            if(validate_imgwei(imgSrc,fileSize,checkPx,width,height,checkSize,size)){
	            	document.getElementById(imgPreviewId).setAttribute("src",window.URL.createObjectURL(fileObj.files[0]));
	            }else{
	            	$("#" + fileObj.id).val(""); 
	            	return;
	            }
	        }  
	    }else{       
	        imgSrc =fileObj.value;
	        fileSize = fileObj.files[0].size;
	        if(validate_imgwei(imgSrc,fileSize,checkPx,width,height,checkSize,size)){
	        	 document.getElementById(imgPreviewId).setAttribute("src",fileObj.value);  
	        }else{
	        	$("#" + fileObj.id).val(""); 
	        	return;
	        }
	    }        
	 }else{  
		var str = "仅支持"+allowExtention+"为后缀名的文件!";
		layer.alert(str, {icon:5,title:'提示'});
	   /*  alert("仅支持"+allowExtention+"为后缀名的文件!");  */ 
	    fileObj.value="";//清空选中文件  
	    if(browserVersion.indexOf("MSIE")>-1){                          
	        fileObj.select();  
	        document.selection.clear();  
	    }                  
	    fileObj.outerHTML=fileObj.outerHTML;  
	}  

	}
/*
 * 验证图片分辨率及大小
 */
function moz_validate_imgwei(imgSre,fileSize,checkPx,width,height,checkSize,size,fileObj,imgPreviewId){ 
	var image = new Image();
	image.src = imgSre; 	
	var flag=true;
	image.onload=function(){
		document.getElementById(imgPreviewId).removeAttribute("src");  
    	if(checkPx && ((image.width != width) ||(image.height != height))){
    		psmaMessageShowOne('请上传' + width +'*' +height +'像素的图片');
    		flag=false;
    	}
    	if(checkSize && fileSize>size*1024){
    		psmaMessageShowOne('请上传大小小于' + size + 'k的图片');	
    		flag=false;
    	}
    	if(flag){
    		document.getElementById(imgPreviewId).setAttribute("src",imgSre);  
    	}else{
    		$("#" + fileObj.id).val(""); 
    	}
    }
}

function validate_imgwei(imgSre,fileSize,checkPx,width,height,checkSize,size){ 
	var image = new Image();
    image.src = imgSre; 
	if(checkPx && ((image.width != width) ||(image.height != height))){
		psmaMessageShowOne('请上传' + width +'*' +height +'像素的图片');  
		return false;
	}
	if(checkSize && fileSize>size*1024){
		psmaMessageShowOne('请上传大小小于' + size + 'k的图片');
		return false;
	}
	return true;
}

