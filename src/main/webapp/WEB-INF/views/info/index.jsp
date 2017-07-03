<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/tlds/fmt.tld" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tlds/fn.tld" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
  <!-- Support for mobile touch devices -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <c:set var="ctx" value="${pageContext.request.contextPath}"/>
  <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1, maximum-scale=1, minimal-ui">

  <!-- CSS -->

  <!-- Font awesome CSS for tool icons -->
  <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">

  <!-- Bootstrap CSS -->
  <link href="${ctx}/static/css/lib/bootstrap.min.css" rel="stylesheet">

  <!-- UI CSS -->
  <link href="${ctx}/static/css/lib/jquery-ui.min.css" rel="stylesheet">  

  <!-- Cornerstone Base CSS -->
  <link href="${ctx}/static/css/lib/cornerstone.min.css" rel="stylesheet">

  <!-- Cornerstone Demo CSS -->
  <link href="${ctx}/static/css/lib/cornerstoneDemo.css" rel="stylesheet">
  <script type="text/javascript">
var contextPath = "${pageContext.request.contextPath}";
</script>
</head>

<body>
  <div id="wrap">

    <!-- Nav bar -->
    <nav class="myNav navbar navbar-default" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <a class="navbar-brand" href="https://github.com/chafey/cornerstone">项目地址</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
          <li><a id="help" href="#" class="button hidden-xs">帮助</a></li>
          <li><a id="about" href="#" class="button hidden-xs">关于</a></li>
        </ul>
      </div>
    </nav>

    <div class='main'>

      <!-- Tabs bar -->
      <ul id="tabs" class="nav nav-tabs" >
        <li class="active"><a href="#studyList" data-toggle="tab">研究列表</a></li>
      </ul>

      <!-- Tab content -->
      <div id="tabContent" class="tab-content">
        <!-- Study list -->
        <div id="studyList" class="tab-pane active">
          <div class="row">
            <table  class="col-md-12 table table-striped">
              <thead>
                <tr>
                  <th>患者姓名</th>
                  <th>病人ID</th>
                  <th>检查日期</th>
                  <th>形态</th>
                  <th>图像描述</th>
                  <th># Images</th>
                </tr>
              </thead>
              
              <tbody id="studyListData">
                <!-- Table rows get populated from the JSON studyList manifest -->
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <!-- Study viewer tab content template -->
  </div>
</div>







<!-- Javascripts -->

<!-- Include JQuery -->
<script src="${ctx}/static/js/lib/jquery.min.js"></script>

<!-- Include JQuery UI for drag/drop -->
<script src="${ctx}/static/js/lib/jquery-ui.min.js"></script>

<!-- Include JQuery Hammerjs adapter for mobile touch-->
<script src="${ctx}/static/js/lib/hammer.min.js"></script>

<!-- Include Bootstrap js -->
<script src="${ctx}/static/js/lib/bootstrap.min.js"></script>

<!-- include the cornerstone library -->
<script src="${ctx}/static/js/lib/cornerstone.js"></script>

<!-- include the cornerstone library -->
<script src="${ctx}/static/js/lib/cornerstoneMath.js"></script>

<!-- include the cornerstone tools library -->
<script src="${ctx}/static/js/lib/cornerstoneTools.js"></script>

<!-- include the cornerstoneWADOImageLoader library -->
<script src="${ctx}/static/js/lib/cornerstoneWADOImageLoader.js"></script>

<!-- include the cornerstoneWebImageLoader library -->
<script src="${ctx}/static/js/lib/cornerstoneWebImageLoader.js"></script>

<!-- include the dicomParser library -->
<script src="${ctx}/static/js/lib/dicomParser.js"></script>

<!-- include cornerstoneDemo.js -->
<script src="${ctx}/static/js/load/setupViewport.js"></script>
<script src="${ctx}/static/js/load/displayThumbnail.js"></script>
<script src="${ctx}/static/js/load/loadStudy.js"></script>
<script src="${ctx}/static/js/load/setupButtons.js"></script>
<script src="${ctx}/static/js/load/disableAllTools.js"></script>
<script src="${ctx}/static/js/load/forEachViewport.js"></script>
<script src="${ctx}/static/js/load/imageViewer.js"></script>
<script src="${ctx}/static/js/load/loadTemplate.js"></script>
<script src="${ctx}/static/js/load/help.js"></script>
<script src="${ctx}/static/js/load/about.js"></script>
<script src="${ctx}/static/js/load/setupViewportOverlays.js"></script>
<script src="${ctx}/static/js/load/cornerstoneDemo.js"></script>


</body>
</html>