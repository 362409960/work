/** 
 * easyui扩展/常用的方法 
 *  
 */  
// 定义全局对象  
var yxui = $.extend({}, yxui);  
var isSuccess;
$.format = function (source, params) {
    if (arguments.length == 1)
        return function () {
            var args = $.makeArray(arguments);
            args.unshift(source);
            return $.format.apply(this, args);
        };
    if (arguments.length > 2 && params.constructor != Array) {
    	params = $.makeArray(arguments).slice(1);
    }
    if (params.constructor != Array) {
        params = [params];
    }
    $.each(params, function (i, n) {
        source = source.replace(new RegExp("\\{" + i + "\\}", "g"), n);
    });
    return source;
};
$.parser.auto = false;  
$(function() {  
            $.messager.progress({  
                        text : '数据加载中....',  
                        interval : 100  
                    });  
            $.parser.parse(window.document);  
            window.setTimeout(function() {  
                        $.messager.progress('close');  
                        if (self != parent) {  
                            window.setTimeout(function() {  
                                        try {  
                                            parent.$.messager.progress('close');  
                                        } catch (e) {  
                                        }  
                                    }, 500);  
                        }  
                    }, 1);  
            $.parser.auto = true;  
        });  
$.fn.panel.defaults.loadingMessage = '数据加载中....';  
$.fn.datagrid.defaults.loadMsg = '数据加载中....';  
// 获得根路径 rootBasePath rootPath  
yxui.rootBasePath = function() {  
    var curWwwPath = window.document.location.href;  
    var pathName = window.document.location.pathname;  
    var pos = curWwwPath.indexOf(pathName);  
    var localhostPaht = curWwwPath.substring(0, pos);  
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);  
    return (localhostPaht + projectName);  
};  
yxui.rootPath = function() {  
    var pathName = window.document.location.pathname;  
    return pathName.substring(0, pathName.substr(1).indexOf('/') + 1);  
};  
// 格式化字符串 formatString  
yxui.formatString = function(str) {  
    for (var i = 0; i < arguments.length - 1; i++) {  
        str = str.replace("{" + i + "}", arguments[i + 1]);  
    }  
    return str;  
};  
// 更换主题 changeTheme  
yxui.changeTheme = function(themeName) {  
    var $yxuiTheme = $('#yxuiTheme');  
    var url = $yxuiTheme.attr('href');  
    var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';  
    $yxuiTheme.attr('href', href);  
    var $iframe = $('iframe');  
    if ($iframe.length > 0) {  
        for (var i = 0; i < $iframe.length; i++) {  
            var ifr = $iframe[i];  
            $(ifr).contents().find('#easyuiTheme').attr('href', href);  
        }  
    }  
    $.cookie('yxuiTheme', themeName, {  
                expires : 7  
            });  
};  
// 将form表单元素的值序列化成对象  
yxui.serializeObject = function(form) {  
    var o = {};  
    $.each(form.serializeArray(), function(index) {  
                if (o[this['name']]) {  
                    o[this['name']] = o[this['name']] + "," + this['value'];  
                } else {  
                    o[this['name']] = this['value'];  
                }  
            });  
    return o;  
};  
// 操作权限控制 operId  
yxui.operId = function(_this) {  
    $("#_operId").val($(_this).attr("_operId"));  
    $("#_resOperId").val($(_this).attr("_resOperId"));  
    $("#_resOperKey").val($(_this).attr("_resOperKey"));  
    // console.info("set->" + $("#_operId").val());  
};  
// form提交 formSubmit  
yxui.formSubmit = function(_datagrid, _dialog, _form, _url, _callbak) {  
    var _arg = '_menuId=' + $("#_menuId").val() + '&_operId=' + $("#_operId").val() + '&_resOperId=' + $("#_resOperId").val() + '&_resOperKey=' + $("#_resOperKey").val();  
    _url = yxui.refreshUrlLink(_url, _arg);  
    if (_form.form('validate')) {  
        _form.form('submit', {  
                    url : _url,  
                    success : function(data) {  
                        _callbak(data, _datagrid, _dialog, _form, _url);  
                    }  
                })  
    }  
    $("#_operId").val('');  
};  
// ajax提交 ajaxSubmit  
yxui.ajaxSubmit = function(_datagrid, _dialog, _form, _url, _data, _callbak) {  
    // console.info("ajax get->" + $("#_operId").val());  
    _data._operId = $("#_operId").val();  
    _data._resOperId = $("#_resOperId").val();  
    _data._resOperKey = $("#_resOperKey").val();  
    $.ajax({  
                url : _url,  
                type : 'post',  
                data : _data,  
                dataType : 'json',  
                cache : false,  
                success : function(response) {  
                    _callbak(response, _datagrid, _dialog, _form, _url, _data);  
                }  
            });  
};  
// refreshUrlLink  
yxui.refreshUrlLink = function(_url, _arg) {  
    var index = _url.indexOf('?');  
    var length = _url.length;  
    if (index < 0) {  
        _url = _url + '?' + _arg;  
    } else if (index == length - 1) {  
        _url = _url + _arg;  
    } else {  
        _url = _url.substring(0, index + 1) + _arg + '&' + _url.substring(index + 1, length);  
    }  
    return _url;  
};  
// dotoHtml  
yxui.dotoHtml = function(tos) {  
    var returnHtml = $('#rowOperation').html();  
    if (null != returnHtml) {  
        var maxArgsNumb = $('#_maxArgsNumb').val();  
        if (maxArgsNumb == 0) {  
            return returnHtml;  
        } else {  
            for (var i = 0; i < maxArgsNumb; i++) {  
                returnHtml = returnHtml.replace(new RegExp("'#arg" + i + "'", "g"), typeof(tos[i]) == 'undefined' ? 'this' : tos[i]);  
            }  
            return returnHtml;  
        }  
    } else {  
        return "";  
    }  
}  
// dotoHtmlById  
yxui.dotoHtmlById = function(id, tos) {  
    var returnHtml = $('#' + id).html();  
    if (null != returnHtml) {  
        var maxArgsNumb = tos.length;  
        for (var i = 0; i < maxArgsNumb; i++) {  
            returnHtml = returnHtml.replace(new RegExp("'#arg" + i + "'", "g"), typeof(tos[i]) == 'undefined' ? 'this' : tos[i]);  
        }  
        return returnHtml;  
    } else {  
        return "";  
    }  
}  
// dotoDiyHtml  
yxui.dotoDiyHtml = function(returnHtml, tos, maxArgsNumb) {  
    if (null != returnHtml) {  
        if (null == maxArgsNumb || maxArgsNumb == 0) {  
            return returnHtml;  
        } else {  
            for (var i = 0; i < maxArgsNumb; i++) {  
                returnHtml = returnHtml.replace(new RegExp("'#arg" + i + "'", "g"), typeof(tos[i]) == 'undefined' ? 'this' : tos[i]);  
            }  
            return returnHtml;  
        }  
    } else {  
        return "";  
    }  
}  
// replaceAll  
yxui.replaceAll = function(_str, _from, _to) {  
    if (null != _str) {  
        return _str.replace(new RegExp(_from, "g"), _to);  
    } else {  
        return "";  
    }  
}  
// getRequestArg  
yxui.getRequestArg = function() {  
    var _url = location.search;  
    var returnObject = {};  
    var index = _url.indexOf("?");  
    if (index != -1) {  
        var str = _url.substr(index + 1);  
        strs = str.split("&");  
        for (var i = 0; i < strs.length; i++) {  
            returnObject[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);  
        }  
    }  
    return returnObject;  
}  
// xui.getUrlArg  
yxui.getUrlArg = function(_url) {  
    var index = _url.indexOf("?");  
    if (index != -1) {  
        var returnObject = {};  
        var str = _url.substr(index + 1);  
        strs = str.split("&");  
        for (var i = 0; i < strs.length; i++) {  
            returnObject[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);  
        }  
        return returnObject;  
    }  
    return null;  
}  
function getrequest() {  
    var url = location.search; // 获取url中"?"符后的字串  
    // alert(url.indexOf("?"))  
    var therequest = {};  
    if (url.indexOf("?") != -1) {  
        var str = url.substr(1);  
        // alert(str)  
        strs = str.split("&");  
        for (var i = 0; i < strs.length; i++) {  
            therequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);  
        }  
    }  
    return therequest;  
}  
/** 
 * 扩展treegrid diyload treegrid查询功能 
 */  
$.extend($.fn.treegrid.methods, {  
            diyload : function(jq, param) {  
                return jq.each(function() {  
                            var opts = $(this).treegrid("options");  
                            diyRequest(this, param);  
                        });  
            }  
        });  
function diyRequest(jq, param) {  
    var opts = $.data(jq, "treegrid").options;  
    if (param) {  
        opts.queryParams = param;  
    }  
    if (!opts.url) {  
        return;  
    }  
    var param = $.extend({}, opts.queryParams);  
    if (opts.onBeforeLoad.call(jq, param) == false) {  
        return;  
    }  
    setTimeout(function() {  
                doRequest();  
            }, 0);  
    function doRequest() {  
        $.ajax({  
                    type : opts.method,  
                    url : opts.url,  
                    data : param,  
                    dataType : "json",  
                    success : function(data) {  
                        $(jq).treegrid('loadData', data)  
                    },  
                    error : function() {  
                        if (opts.onLoadError) {  
                            opts.onLoadError.apply(jq, arguments);  
                        }  
                    }  
                });  
    }  
}  
/** 
 * 扩展tree getCheckedExt 获得选中节点+实心节点 getSolidExt 获取实心节点 
 */  
$.extend($.fn.tree.methods, {  
            getCheckedExt : function(jq) {  
                var checked = [];  
                var checkbox2 = $(jq).find("span.tree-checkbox1,span.tree-checkbox2").parent();  
                $.each(checkbox2, function() {  
                            var thisData = {  
                                target : this,  
                                "checked" : true  
                            };  
                            var node = $.extend({}, $.data(this, "tree-node"), thisData);  
                            checked.push(node);  
                        });  
                return checked;  
            },  
            getSolidExt : function(jq) {  
                var checked = [];  
                var checkbox2 = $(jq).find("span.tree-checkbox2").parent();  
                $.each(checkbox2, function() {  
                            var node = $.extend({}, $.data(this, "tree-node"), {  
                                        target : this  
                                    });  
                            checked.push(node);  
                        });  
                return checked;  
            }  
        });  
/** 
 * 扩展datagrid，添加动态增加或删除Editor的方法 
 */  
$.extend($.fn.datagrid.methods, {  
            addEditor : function(jq, param) {  
                if (param instanceof Array) {  
                    $.each(param, function(index, item) {  
                                var e = $(jq).datagrid('getColumnOption', item.field);  
                                e.editor = item.editor;  
                            });  
                } else {  
                    var e = $(jq).datagrid('getColumnOption', param.field);  
                    e.editor = param.editor;  
                }  
            },  
            removeEditor : function(jq, param) {  
                if (param instanceof Array) {  
                    $.each(param, function(index, item) {  
                                var e = $(jq).datagrid('getColumnOption', item);  
                                e.editor = {};  
                            });  
                } else {  
                    var e = $(jq).datagrid('getColumnOption', param);  
                    e.editor = {};  
                }  
            }  
        });  
/** 
 * 扩展datagrid editor 增加带复选框的下拉树/增加日期时间组件/增加多选combobox组件 
 */  
$.extend($.fn.datagrid.defaults.editors, {  
            combocheckboxtree : {  
                init : function(container, options) {  
                    var editor = $('<input />').appendTo(container);  
                    options.multiple = true;  
                    editor.combotree(options);  
                    return editor;  
                },  
                destroy : function(target) {  
                    $(target).combotree('destroy');  
                },  
                getValue : function(target) {  
                    return $(target).combotree('getValues').join(',');  
                },  
                setValue : function(target, value) {  
                    $(target).combotree('setValues', sy.getList(value));  
                },  
                resize : function(target, width) {  
                    $(target).combotree('resize', width);  
                }  
            },  
            datetimebox : {  
                init : function(container, options) {  
                    var editor = $('<input />').appendTo(container);  
                    editor.datetimebox(options);  
                    return editor;  
                },  
                destroy : function(target) {  
                    $(target).datetimebox('destroy');  
                },  
                getValue : function(target) {  
                    return $(target).datetimebox('getValue');  
                },  
                setValue : function(target, value) {  
                    $(target).datetimebox('setValue', value);  
                },  
                resize : function(target, width) {  
                    $(target).datetimebox('resize', width);  
                }  
            },  
            multiplecombobox : {  
                init : function(container, options) {  
                    var editor = $('<input />').appendTo(container);  
                    options.multiple = true;  
                    editor.combobox(options);  
                    return editor;  
                },  
                destroy : function(target) {  
                    $(target).combobox('destroy');  
                },  
                getValue : function(target) {  
                    return $(target).combobox('getValues').join(',');  
                },  
                setValue : function(target, value) {  
                    $(target).combobox('setValues', sy.getList(value));  
                },  
                resize : function(target, width) {  
                    $(target).combobox('resize', width);  
                }  
            }  
        });  
/** 
 * 扩展 datagrid/treegrid 增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中 
 *  
 * @param e 
 * @param field 
 */  
var createGridHeaderContextMenu = function(e, field) {  
    e.preventDefault();  
    var grid = $(this);/* grid本身 */  
    var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */  
    if (!headerContextMenu) {  
        var tmenu = $('<div style="width:150px;"></div>').appendTo('body');  
        var fields = grid.datagrid('getColumnFields');  
        for (var i = 0; i < fields.length; i++) {  
            var fildOption = grid.datagrid('getColumnOption', fields[i]);  
            if (!fildOption.hidden) {  
                $('<div iconCls="icon-ok" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);  
            } else {  
                $('<div iconCls="icon-empty" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);  
            }  
        }  
        headerContextMenu = this.headerContextMenu = tmenu.menu({  
                    onClick : function(item) {  
                        var field = $(item.target).attr('field');  
                        if (item.iconCls == 'icon-ok') {  
                            grid.datagrid('hideColumn', field);  
                            $(this).menu('setIcon', {  
                                        target : item.target,  
                                        iconCls : 'icon-empty'  
                                    });  
                        } else {  
                            grid.datagrid('showColumn', field);  
                            $(this).menu('setIcon', {  
                                        target : item.target,  
                                        iconCls : 'icon-ok'  
                                    });  
                        }  
                    }  
                });  
    }  
    headerContextMenu.menu('show', {  
                left : e.pageX,  
                top : e.pageY  
            });  
};  
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;  
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;  
/** 
 * 扩展 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作 
 *  
 * @param XMLHttpRequest 
 */  
var easyuiErrorFunction = function(XMLHttpRequest) {  
    $.messager.progress('close');  
    $.messager.alert('错误', XMLHttpRequest.responseText);  
};  
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;  
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;  
$.fn.tree.defaults.onLoadError = easyuiErrorFunction;  
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;  
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;  
$.fn.form.defaults.onLoadError = easyuiErrorFunction;  
/** 
 * 防止panel/window/dialog组件超出浏览器边界 
 *  
 * @param left 
 * @param top 
 */  
var easyuiPanelOnMove = function(left, top) {  
    var l = left;  
    var t = top;  
    if (l < 1) {  
        l = 1;  
    }  
    if (t < 1) {  
        t = 1;  
    }  
    var width = parseInt($(this).parent().css('width')) + 14;  
    var height = parseInt($(this).parent().css('height')) + 14;  
    var right = l + width;  
    var buttom = t + height;  
    var browserWidth = $(window).width();  
    var browserHeight = $(window).height();  
    if (right > browserWidth) {  
        l = browserWidth - width;  
    }  
    if (buttom > browserHeight) {  
        t = browserHeight - height;  
    }  
    $(this).parent().css({/* 修正面板位置 */  
        left : l,  
        top : t  
    });  
};  
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;  
$.fn.window.defaults.onMove = easyuiPanelOnMove;  
$.fn.panel.defaults.onMove = easyuiPanelOnMove;  
/** 
 * 扩展easyui的validator插件rules，支持更多类型验证 
 */  
$.extend($.fn.validatebox.defaults.rules, {  
            minLength : { // 判断最小长度  
                validator : function(value, param) {  
                    return value.length >= param[0];  
                },  
                message : '最少输入 {0} 个字符'  
            },  
            length : { // 长度  
                validator : function(value, param) {  
                    var len = $.trim(value).length;  
                    return len >= param[0] && len <= param[1];  
                },  
                message : "输入内容长度必须介于{0}和{1}之间"  
            },  
            phone : {// 验证电话号码  
                validator : function(value) {  
                    return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);  
                },  
                message : '格式不正确,请使用下面格式:020-88888888'  
            },  
            mobile : {// 验证手机号码  
                validator : function(value) {  
                    return /^(88888888888)|^1\d{10}$/i.test(value);
                },  
                message : '手机号码格式不正确'  
            },  
            phoneAndMobile : {// 电话号码或手机号码  
                validator : function(value) {  
                    return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value) || /^1\d{10}$/i.test(value);  
                },  
                message : '电话号码或手机号码格式不正确'  
            },
            oneOrManyForPhoneAndMobile : {// 一个或者多个电话号码或手机号码  
                validator : function(value) {  
                	if(value==null||value==''){
                		return false;
                	}
                	
            		var split= value.split(",");
            		if(split.length>=2){
            			for(var i = 0;i < split.length; i++) 
              		  {
              		 	if(!((/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(split[i]) || /^1\d{10}$/i.test(split[i])))){
              		 		return false;
              		 	}
              		  }
            		}else{
              		 	if(!(/^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(split[0]) || /^1\d{10}$/i.test(split[0]))){
              		 		return false;
              		 	}
            		}

            		return true;
                	
                },  
                message : '电话号码或手机号码格式不正确'  
            },
            /*checkLoginName : {// 检查登陆名是否重名
                validator : function(loginName) {
                	$.ajax({
            			data:{loginName:loginName},
            			async:false,
            			url:localCtx+'/sys/users/checkLoginName',
            			success: function(data){
            				if(data == '0'){
            					//成功
            					cr=true;
            					isSuccess=true;
            				}else if(data == '10002'){
            					$.fn.validatebox.defaults.rules.checkLoginName.message ="该用户名系统中已存在，不能添加用户";
            					cr = false;
            					isSuccess=false;
            				}else{
            					$.fn.validatebox.defaults.rules.checkLoginName.message ="校验用户名失败";
            					cr = false;
            					isSuccess=false;            					
            				}
            			}});
                	return cr;
                },
                message : '默认信息'  
            },*/
          //验证密码  字段相等
            passwordEqualsTo: {
            	validator: function (value, param) {
            		return $(param[0]).val() == value;
            	}, 
            	message: '两次输入密码不匹配' 
            },
            dateASmallerThanDateB: {//检测开始日期必须小于等于结束日期
            	validator: function (value, param) {
            		
            		var beginDate=new Date($(param[0]).datetimebox('getValue'));
            		 var endDate=new Date((value));
            		 
            		return endDate!=null&&endDate>=beginDate;
            	}, 
            	message: '开始日期必须小于等于结束日期' 
            },
            idcard : {// 验证身份证  
                validator : function(value) {  
                	return IdentityCodeValid(value);
                },  
                message : '身份证号码格式不正确'  
            },  
            intAndFloat : {// 验证整数或小数  
                validator : function(value) {  
                    //return /^[1-9]+(\.\d)$/i.test(value);  
                    return /^([1-9])(\.\d)?$/i.test(value);
                },  
                message : '请输入1到9的正数，或保留一位小数'  
            },
            intOrFloat : {// 验证整数或小数  
                validator : function(value) {  
                    return /^\d{1,6}(\.\d{1,2})?$/i.test(value);  
                },  
                message : '请输入正数,范围【0.1~999999.99】，小数保留二位'  
            },
            gtZero : { //大于0
            	validator : function(value){
            		return /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/i.test(value);
            	},
            	message : '价格必须大于0'
            },
            specialCharacters : {// 验证特殊字符
                validator : function(value) {  
                	 return /^[\u4E00-\u9FA5A-Za-z0-9_]+$/gi.test(value);  
                },  
                message : '不能输入特殊字符'  
            },
            textValidate : {
            	validator : function(value) {  
            		
               	 	return /^([ ，。、；\[\]……‘’“”?？!！\~\-【】、·,./;{}\\`\'\'\"\"]|[\u4E00-\u9FA5A-Za-z0-9_])+$/gi.test(value);  
               },  
               message : '不能输入特殊字符' 
            },
            currency : {// 验证货币  
                validator : function(value) {  
                    return /^\d+(\.\d+)?$/i.test(value);  
                },  
                message : '货币格式不正确'  
            },  
            acreage : {// 验证面积  
                validator : function(value) {  
                    return /^\d+(\.\d+)?$/i.test(value);  
                },  
                message : '面积格式不正确'  
            }, 
            
            acreage1 : {// 验证面积  
                validator : function(value) {  
                    return /^\d+(\.\d+)?$/i.test(value);  
                },  
                message : '请输入正数，小数保留小数点后面保留二位。'  
            }, 
            qq : {// 验证QQ,从10000开始  
                validator : function(value) {  
                    return /^[1-9]\d{4,9}$/i.test(value);  
                },  
                message : 'QQ号码格式不正确'  
            },  
            integer : {// 验证整数  
                validator : function(value) {  
                    return /^[+]?[1-9]+\d*$/i.test(value);  
                },  
                message : '请输入整数'  
            }, 
            integerzs : {// 验证整数  
                validator : function(value) {  
                    return /^[+]?[1-9]+\d*$/i.test(value);  
                },  
                message : '请输入正整数'  
            },  
            integerzszeo : {// 验证整数  
                validator : function(value) {  
                    return /^([1-9]\d*|[0]{1,1})$/.test(value);  
                },  
                message : '请输入正整数和零'  
            },  
            chinese : {// 验证中文  
                validator : function(value) {  
                    return /^[\u0391-\uFFE5]+$/i.test(value);  
                },  
                message : '请输入中文'  
            },  
            chineseAndLength : {// 验证中文及长度  
                validator : function(value) {  
                    var len = $.trim(value).length;  
                    if (len >= param[0] && len <= param[1]) {  
                        return /^[\u0391-\uFFE5]+$/i.test(value);  
                    }  
                },  
                message : '请输入中文'  
            },  
            english : {// 验证英语  
                validator : function(value) {  
                    return /^[A-Za-z]+$/i.test(value);  
                },  
                message : '请输入英文'  
            },  
            englishAndLength : {// 验证英语及长度  
                validator : function(value, param) {  
                    var len = $.trim(value).length;  
                    if (len >= param[0] && len <= param[1]) {  
                        return /^[A-Za-z]+$/i.test(value);  
                    }  
                },  
                message : '请输入英文'  
            },  
            englishUpperCase : {// 验证英语大写  
                validator : function(value) {  
                    return /^[A-Z]+$/.test(value);  
                },  
                message : '请输入大写英文'  
            },  
            unnormal : {// 验证是否包含空格和非法字符  
                validator : function(value) {  
                    return /.+/i.test(value);  
                },  
                message : '输入值不能为空和包含其他非法字符'  
            }, 
            username : {// 验证用户名  
                validator : function(value) {  
                    return /^[a-zA-Z0-9_@]{6,16}$/i.test(value);  
                },  
                message : '由字母、数字、下划线、@符号组成的6-16位字符'  
            },
            onlychina : {// 验证用户名  
                validator : function(value) {  
                    return  /^[\u4e00-\u9fa5]*$/i.test(value);  
                },  
                message : '真实姓名只能是中文'  
            },
            onlyCEN : {// 验证只能是中文，数字，字母 
                validator : function(value) {  
                    return  /^[\u4e00-\u9fa5a-zA-Z0-9]*$/i.test(value);  
                },  
                message : '只能输入中文、数字、字母'  
            },
            oneNumber : {// 验证只能是一个1到10的数字
                validator : function(value) {
                    return  /^([1-9]|10)$/i.test(value);  
                },  
                message : '只能输入一个1到10之间的数字'  
            },
            noCE:{//验证不能包含空格和中文
            	validator:function(value){
            		return /^([^ \u4e00-\u9fa5])+$/i.test(value);
            	},
            message : '不能包含空格和中文'  
            },
            onlyCNAX : {// 中文、数字、字母、下划线
                validator : function(value) {  
                	
                    return /^([\u4a00-\u9fa50-9a-zA-Z_])+$/i.test(value);  
                },  
                message : '只能输入中文、数字、字母、下划线'  
            },
            onlyEN : {// 验证只能是数字，字母 
                validator : function(value) {  
                    return  /^[a-zA-Z0-9]*$/i.test(value);  
                },  
                message : '只能输入数字、字母'  
            },
            onlyNumber : {// 验证只能是中文，数字，字母 
                validator : function(value) {
                    return  /^[0-9]*$/i.test(value);  
                },  
                message : '只能输入数字'  
            },
            loginname : {// 验证登录名 
                validator : function(value) {  
                    return /^[a-zA-Z0-9][a-zA-Z0-9_]{5,15}$/i.test(value);  
                },  
                message : '登录名不合法（字母或数字开头，允许6-16字符，允许字母数字下划线）'  
            },
            code : {// 不能有中文 
            	validator : function(value) {  
                    return /^[\w-]+$/i.test(value);
                },  
                message : '输入不合法（允许字母数字下划线）' 
            },
            faxno : {// 验证传真  
                validator : function(value) {  
                    return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);  
                },  
                message : '传真号码不正确'  
            },  
            zip : {// 验证邮政编码  
                validator : function(value) {  
                    return /^[1-9]\d{5}$/i.test(value);  
                },  
                message : '邮政编码格式不正确'  
            },  
            ip : {// 验证IP地址  
                validator : function(value) {  
                    return /d+.d+.d+.d+/i.test(value);  
                },  
                message : 'IP地址格式不正确'  
            },  
            name : {// 验证姓名，可以是中文或英文  
                validator : function(value) {  
                    return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);  
                },  
                message : '请输入姓名'  
            },  
            engOrChinese : {// 可以是中文或英文  
                validator : function(value) {  
                    return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);  
                },  
                message : '请输入中文'  
            },  
            engOrChineseAndLength : {// 可以是中文或英文  
                validator : function(value) {  
                    var len = $.trim(value).length;  
                    if (len >= param[0] && len <= param[1]) {  
                        return /^[\u0391-\uFFE5]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);  
                    }  
                },  
                message : '请输入中文或英文'  
            },  
            carNo : {  
                validator : function(value) {  
                    return /^[\u4E00-\u9FA5][\da-zA-Z]{6}$/.test(value);  
                },  
                message : '车牌号码无效（例：粤B12350）'  
            },  
            carenergin : {  
                validator : function(value) {  
                    return /^[a-zA-Z0-9]{16}$/.test(value);  
                },  
                message : '发动机型号无效(例：FG6H012345654584)'  
            },  
            selectRequired : {
            	validator : function(value,param){ 
            		return $(param[0]).find("option:contains('"+value+"')").val() != ''; 
            	},
            	message : '必选项'
            },
            same : {  
                validator : function(value, param) {  
                    if ($("#" + param[0]).val() != "" && value != "") {  
                        return $("#" + param[0]).val() == value;  
                    } else {  
                        return true;  
                    }  
                },  
                message : '两次输入的密码不一致!'  
            },
            selectValueRequired : {
            	validator : function(value){
            		if("0" == value){
            			return false;
            		} else {
            			return true;
            		}
            	},
            	message : '必选项'
            }
        });  
/** 
 * 扩展easyui validatebox的两个方法.移除验证和还原验证 
 */  
$.extend($.fn.validatebox.methods, {  
            remove : function(jq, newposition) {  
                return jq.each(function() {  
                    $(this).removeClass("validatebox-text validatebox-invalid").unbind('focus.validatebox').unbind('blur.validatebox');  
                        // remove tip  
                        // $(this).validatebox('hideTip', this);  
                    });  
            },  
            reduce : function(jq, newposition) {  
                return jq.each(function() {  
                    var opt = $(this).data().validatebox.options;  
                    $(this).addClass("validatebox-text").validatebox(opt);  
                        // $(this).validatebox('validateTip', this);  
                    });  
            },  
            validateTip : function(jq) {  
                jq = jq[0];  
                var opts = $.data(jq, "validatebox").options;  
                var tip = $.data(jq, "validatebox").tip;  
                var box = $(jq);  
                var value = box.val();  
                function setTipMessage(msg) {  
                    $.data(jq, "validatebox").message = msg;  
                };  
                var disabled = box.attr("disabled");  
                if (disabled == true || disabled == "true") {  
                    return true;  
                }  
                if (opts.required) {  
                    if (value == "") {  
                        box.addClass("validatebox-invalid");  
                        setTipMessage(opts.missingMessage);  
                        $(jq).validatebox('showTip', jq);  
                        return false;  
                    }  
                }  
                if (opts.validType) {  
                    var result = /([a-zA-Z_]+)(.*)/.exec(opts.validType);  
                    var rule = opts.rules[result[1]];  
                    if (value && rule) {  
                        var param = eval(result[2]);  
                        if (!rule["validator"](value, param)) {  
                            box.addClass("validatebox-invalid");  
                            var message = rule["message"];  
                            if (param) {  
                                for (var i = 0; i < param.length; i++) {  
                                    message = message.replace(new RegExp("\\{" + i + "\\}", "g"), param[i]);  
                                }  
                            }  
                            setTipMessage(opts.invalidMessage || message);  
                            $(jq).validatebox('showTip', jq);  
                            return false;  
                        }  
                    }  
                }  
                box.removeClass("validatebox-invalid");  
                $(jq).validatebox('hideTip', jq);  
                return true;  
            },  
            showTip : function(jq) {  
                jq = jq[0];  
                var box = $(jq);  
                var msg = $.data(jq, "validatebox").message  
                var tip = $.data(jq, "validatebox").tip;  
                if (!tip) {  
                    tip = $("<div class=\"validatebox-tip\">" + "<span class=\"validatebox-tip-content\">" + "</span>" + "<span class=\"validatebox-tip-pointer\">" + "</span>" + "</div>").appendTo("body");  
                    $.data(jq, "validatebox").tip = tip;  
                }  
                tip.find(".validatebox-tip-content").html(msg);  
                tip.css({  
                            display : "block",  
                            left : box.offset().left + box.outerWidth(),  
                            top : box.offset().top  
                        });  
            },  
            hideTip : function(jq) {  
                jq = jq[0];  
                var tip = $.data(jq, "validatebox").tip;  
                if (tip) {  
                    tip.remove;  
                    $.data(jq, "validatebox").tip = null;  
                }  
            }  
        });  
/** 
 * 对easyui dialog 封装 
 */  
yxui.dialog = function(options) {  
    var opts = $.extend({  
                modal : true,  
                onClose : function() {  
                    $(this).dialog('destroy');  
                }  
            }, options);  
    return $('<div/>').dialog(opts);  
};  
  
/** 
 * 相同连续列合并扩展 
 */  
$.extend($.fn.datagrid.methods, {  
            autoMergeCells : function(jq, fields) {  
                return jq.each(function() {  
                            var target = $(this);  
                            if (!fields) {  
                                fields = target.datagrid("getColumnFields");  
                            }  
                            var rows = target.datagrid("getRows");  
                            var i = 0, j = 0, temp = {};  
                            for (i; i < rows.length; i++) {  
                                var row = rows[i];  
                                j = 0;  
                                for (j; j < fields.length; j++) {  
                                    var field = fields[j];  
                                    var tf = temp[field];  
                                    if (!tf) {  
                                        tf = temp[field] = {};  
                                        tf[row[field]] = [i];  
                                    } else {  
                                        var tfv = tf[row[field]];  
                                        if (tfv) {  
                                            tfv.push(i);  
                                        } else {  
                                            tfv = tf[row[field]] = [i];  
                                        }  
                                    }  
                                }  
                            }  
                            $.each(temp, function(field, colunm) {  
                                        $.each(colunm, function() {  
                                                    var group = this;  
                                                    if (group.length > 1) {  
                                                        var before, after, megerIndex = group[0];  
                                                        for (var i = 0; i < group.length; i++) {  
                                                            before = group[i];  
                                                            after = group[i + 1];  
                                                            if (after && (after - before) == 1) {  
                                                                continue;  
                                                            }  
                                                            var rowspan = before - megerIndex + 1;  
                                                            if (rowspan > 1) {  
                                                                target.datagrid('mergeCells', {  
                                                                            index : megerIndex,  
                                                                            field : field,  
                                                                            rowspan : rowspan  
                                                                        });  
                                                            }  
                                                            if (after && (after - before) != 1) {  
                                                                megerIndex = after;  
                                                            }  
                                                        }  
                                                    }  
                                                });  
                                    });  
                        });  
            }  
        });  
/** 
 * 左到右 
 */  
yxui.left2right = function(but) {  
    var $layout = $($(but).parents('.easyui-layout')[0]);  
    var left = $layout.find('select')[0];  
    var rigth = $layout.find('select')[1];  
    if ($.browser.msie) {// IE 单独处理  
        for (var i = 0; i < left.options.length; i++) {  
            var option = left.options[i];  
            if (option.selected) {  
                var opt = new Option(option.text, option.value);  
                rigth.options.add(opt);  
                left.remove(i);  
            }  
        }  
    } else {  
        $(left.options).each(function(i, n) {  
                    if (n.selected) {  
                        n.selected = false;  
                        rigth.options.add(n);  
                    }  
                });  
    }  
};  
/** 
 * 右到左 
 */  
yxui.right2left = function(but) {  
    var $layout = $($(but).parents('.easyui-layout')[0]);  
    var left = $layout.find('select')[0];  
    var rigth = $layout.find('select')[1];  
    if ($.browser.msie) {// IE 单独处理  
        for (var i = 0; i < rigth.options.length; i++) {  
            var option = rigth.options[i];  
            if (option.selected) {  
                var opt = new Option(option.text, option.value);  
                left.options.add(opt);  
                rigth.remove(i);  
            }  
        }  
    } else {  
        $(rigth.options).each(function(i, n) {  
                    if (n.selected) {  
                        n.selected = false;  
                        left.options.add(n);  
                    }  
                });  
    }  
}  
/** 
 * 左全到右 
 */  
yxui.leftall2right = function(but) {  
    var $layout = $($(but).parents('.easyui-layout')[0]);  
    var left = $layout.find('select')[0];  
    var rigth = $layout.find('select')[1];  
    if ($.browser.msie) {// IE 单独处理  
        for (var i = 0; i < left.options.length; i++) {  
            var option = left.options[i];  
            var opt = new Option(option.text, option.value);  
            rigth.options.add(opt);  
        }  
        $(left).empty();  
    } else {  
        $(left.options).each(function(i, n) {  
                    rigth.options.add(n);  
                });  
    }  
};  
/** 
 * 右全到左 
 */  
yxui.rightall2left = function(but) {  
    var $layout = $($(but).parents('.easyui-layout')[0]);  
    var left = $layout.find('select')[0];  
    var rigth = $layout.find('select')[1];  
    if ($.browser.msie) {// IE 单独处理  
        for (var i = 0; i < rigth.options.length; i++) {  
            var option = rigth.options[i];  
            var opt = new Option(option.text, option.value);  
            left.options.add(opt);  
        }  
        $(rigth).empty();  
    } else {  
        $(rigth.options).each(function(i, n) {  
                    left.options.add(n);  
                });  
    }  
};  
/** 
 * select 选择框数据采集 
 *  
 * @param options 
 * @return 数组 
 */  
yxui.findSelectMultipleValue = function(options) {  
    var returnArr = [], ids = [], texts = [];  
    if ($.browser.msie) {// IE 单独处理  
        for (var i = 0; i < options.length; i++) {  
            ids.push(options[i].value);  
            texts.push(options[i].text);  
        }  
    } else {  
        $(options).each(function(i, n) {  
                    ids.push($(n).val());  
                    texts.push($(n).html());  
                });  
    }  
    returnArr.push(ids);  
    returnArr.push(texts);  
    return returnArr;  
}  

/**
 * 用户重名检测
 * @param id 用户名对应的input框中的id
 */
function checkLoginName(id){
	$('#'+id).validatebox({
	    validType: 'username',
	});
	var v=$('#'+id).validatebox('isValid');
	if(v){
		//用户重名检测
		$('#'+id).validatebox({
		    validType: 'checkLoginName',
		});

		//恢复用户名账号规范检测
		$('#'+id).onpropertychange = function() {
				$('#'+id).validatebox({
				    validType: 'username',
				});
		}
		//恢复用户名账号规范检测
		if (window.addEventListener) {
			document.getElementById(id).addEventListener('input', function() {
				$('#'+id).validatebox({
				    validType: 'username',
				});
			}, false); 
		}
		
		//取消focus验证，减少用户中心验证次数
		$('#'+id).unbind('focus.validatebox');
	}
}

/**
 * 用户重名检测,用户名可以数据
 * @param id 用户名对应的input框中的id
 */
function checkLoginNameByPhone(id){
	$('#'+id).validatebox({
	    validType: 'loginname',
	});
	var v=$('#'+id).validatebox('isValid');
	if(v){
		//用户重名检测
		$('#'+id).validatebox({
		    validType: 'checkLoginName',
		});

		//恢复用户名账号规范检测
		$('#'+id).onpropertychange = function() {
				$('#'+id).validatebox({
				    validType: 'loginname',
				});
		}
		//恢复用户名账号规范检测
		if (window.addEventListener) {
			document.getElementById(id).addEventListener('input', function() {
				$('#'+id).validatebox({
				    validType: 'loginname',
				});
			}, false); 
		}
		
		//取消focus验证，减少用户中心验证次数
		$('#'+id).unbind('focus.validatebox');
	}
}

 
//判断两个日期的月份差是否小于等于12个月
function checkMonth(startDate,endDate){ 
	 // 拆分年月日
	startDate = startDate.split('-');
	 // 得到月数
	startDate = parseInt(startDate[0]) * 12 + parseInt(startDate[1]);
	 // 拆分年月日
	endDate = endDate.split('-');
	 // 得到月数
	endDate = parseInt(endDate[0]) * 12 + parseInt(endDate[1]);
	devMonth =   Math.abs(endDate - startDate); 
	//月份绝对差小于等于12返回true，否则返回false
	return (devMonth < 12)?true:false;
	}


/**
 * 获取月份差
 * @param beginTime 开始时间 -- 日期为字符串类型
 * @param endTime 结束时间 
 * @returns {String}
 */
function getTime(beginTime,endTime){
	var beginyear = beginTime.substring(0,4);
	var beginmonth = beginTime.substring(5,7);
	var endyear = endTime.substring(0,4);
	var endmonth = endTime.substring(5,7);
	var str ;
	if ((beginyear+beginmonth) == (endyear+endmonth)) {
		str = beginyear+"年"+beginmonth+"月";
	}else{
		str = beginyear+"年"+beginmonth+"月"+" - "+ endyear+"年"+endmonth+"月";
	}
	
	/*//开始时间
	 var startDate = new Date(beginTime);
	//结束时间
	 var endDate = new Date(endTime);
	 //月数 差
	 var num=0;
	 //年差
	 var year = endDate.getFullYear()-startDate.getFullYear();
	 num += year*12;
	 //月数差
	 var month=endDate.getMonth()-startDate.getMonth();
	 num += month;
	 
	 var str="";
	 //当前开始月份数
	 var startMonth = startDate.getMonth()+1;
	 var startYear = startDate.getFullYear();
	 //获取当前选择开始月份
	 if(startMonth<10){
		 str = startYear+"-0"+startMonth; 
	 }else{
		 str = startYear+"-"+startMonth; 
	 }
	 if(num >0){
		 for(var i = 0 ;i<num;i++){
				//如果大于12表示 一年循环完
				if(startMonth >= 12){
					startMonth = 1;
					startYear = startYear+1;
				}else{
					startMonth = startMonth + 1;
				}
				//拼上年份
				if(startMonth<10){
					str += ","+startYear+"-0"+ startMonth  ;
				}else{
					str += ","+startYear+"-"+ startMonth ;
				}
			}
	 }*/
	 return str;
}

/**
 * 获取到期缴费
 * @param beginTime  开始日期
 * @param endTime    结束日期
 * @param duePaymentDateType 规则类型
 * @param increaseMonths     月份
 * @param increaseDay        天
 * @returns 到期缴费日期
 */
function getPayTime(beginTime, endTime, duePaymentDateType, increaseMonths, increaseDay){
	var payTime = new Date().format('yyyy-MM-dd');
	if((duePaymentDateType==1 || duePaymentDateType==2) && typeof(beginTime)!="undefined"){
		beginTime = new Date(beginTime);
		var days = getDaysInMonth(beginTime.getFullYear(), beginTime.getMonth()+increaseMonths);
		if(beginTime.getDate() > days){
			beginTime.setDate(days);
		}
		beginTime.setMonth(beginTime.getMonth()+increaseMonths);
		if(duePaymentDateType==2 && increaseDay == 29){
			beginTime.setMonth(beginTime.getMonth()+1);
			beginTime.setDate(0);
		}else if(duePaymentDateType==2){
			beginTime.setDate(increaseDay);
		}else{
			beginTime.setDate(beginTime.getDate()+increaseDay);
		}
		payTime = new Date(beginTime).format('yyyy-MM-dd');
	}else if((duePaymentDateType==3 || duePaymentDateType==4) && typeof(endTime)!="undefined"){
		endTime = new Date(endTime);
		var days = getDaysInMonth(endTime.getFullYear(), endTime.getMonth()+increaseMonths);
		if(endTime.getDate() > days){
			endTime.setDate(days);
		}
		endTime.setMonth(endTime.getMonth()+increaseMonths);
		if(duePaymentDateType==4 && increaseDay == 29){
			endTime.setMonth(endTime.getMonth()+1);
			endTime.setDate(0);
		}else if(duePaymentDateType==4){
			endTime.setDate(increaseDay);
		}else{
			endTime.setDate(endTime.getDate()+increaseDay);
		}
		payTime = new Date(endTime).format('yyyy-MM-dd');
	}
	return payTime;
}

function getDaysInMonth(year,month){
   return new Date(year, month+1,0).getDate();
}

/**
 * 自动选择菜单
 * @param menu 菜单名称
 */
function autoSelectMenu(menu){
	
	//菜单是否存在
	var isExist=false;
	
	//顶端页面的所有菜单
	var linkBtn=top.$("span.l-btn-text");
	
	for (var i=0;i<linkBtn.length;i++)
	{
		var spanDom=linkBtn[i];
		
		if(spanDom.innerHTML==menu){
			//菜单存在
			isExist=true;
			
			//获取到展示菜单的div
			var expandMenu=spanDom.parentNode.parentNode.parentNode.parentNode;
			var expandMenuBtn=expandMenu.parentNode.firstChild.childNodes[2].firstChild;

			if(expandMenu.style.display=='none'){
			expandMenuBtn.click();
			}
			
			linkBtn[i].click(); 
			
			//解决IE出现弹窗确认的不兼容问题
			top.window.onbeforeunload = null;
			setTimeout(function(){
				top.window.onbeforeunload = function () { return "您确定要退出本程序？"; };
				}, 1000);
			
			break;
		}
	}
	if(menu == '主页'){
		isExist = true;
	}
	
	if(!isExist){
		psmaMessageShowOne("该菜单不存在，请联系管理员添加该功能!");
	}
}


	//身份证号合法性验证 
	//支持15位和18位身份证号
	//支持地址编码、出生日期、校验位验证
      function IdentityCodeValid(code) { 
          var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
          var tip = "";
          var pass= true;
          
          if(!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)){
              tip = "身份证号格式错误";
              pass = false;
          }
          
         else if(!city[code.substr(0,2)]){
              tip = "地址编码错误";
              pass = false;
          }
          else{
              //18位身份证需要验证最后一位校验位
              if(code.length == 18){
                  code = code.split('');
                  //∑(ai×Wi)(mod 11)
                  //加权因子
                  var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
                  //校验位
                  var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
                  var sum = 0;
                  var ai = 0;
                  var wi = 0;
                  for (var i = 0; i < 17; i++)
                  {
                      ai = code[i];
                      wi = factor[i];
                      sum += ai * wi;
                  }
                  var last = parity[sum % 11];
                  if(parity[sum % 11] != code[17]){
                      tip = "校验位错误";
                      pass =false;
                  }
              }
          }
          
          return pass;
      }
      
      
      /**
       * 将数值四舍五入(保留2位小数)后格式化成金额形式
       *
       * @param num 数值(Number或者String)
       * @return 金额格式的字符串,如'1,234,567.45'
       * @type String
       */
      function formatCurrency(num) {
          num = num.toString().replace(/\$|\,/g,'');
          if(isNaN(num))
          num = "0";
          sign = (num == (num = Math.abs(num)));
          num = Math.floor(num*100+0.50000000001);
          cents = num%100;
          num = Math.floor(num/100).toString();
          if(cents<10)
          cents = "0" + cents;
          for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
          num = num.substring(0,num.length-(4*i+3))+','+
          num.substring(num.length-(4*i+3));
          return (((sign)?'':'-') + num + '.' + cents);
      }
	   // 对Date的扩展，将 Date 转化为指定格式的String
	   // 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
	   // 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
	   // 例子： 
	   // (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
	   // (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
      Date.prototype.Format = function (fmt) { 
    	    var o = {
    	        "M+": this.getMonth() + 1, //月份 
    	        "d+": this.getDate(), //日 
    	        "h+": this.getHours(), //小时 
    	        "m+": this.getMinutes(), //分 
    	        "s+": this.getSeconds(), //秒 
    	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
    	        "S": this.getMilliseconds() //毫秒 
    	    };
    	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    	    for (var k in o)
    	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    	    return fmt;
    	}
      
      /**
       * 获得本月最后一天最后的时间
       */
      Date.prototype.getLastDay = function () {   
    	  var year= this.getFullYear();
    	  var month=this.getMonth()+1;
          var new_year = year;    //取当前的年份          
          var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）          
          if(month>12) {         
           new_month -=12;        //月份减          
           new_year++;            //年份增          
          }         
          var new_date = new Date(new_year,new_month,1);                //取当年当月中的第一天          
          return new Date(new_date.getTime()-1000);//获取当月最后一天日期          
     }