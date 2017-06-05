// Load in HTML templates
//图片展示界面
var viewportTemplate; // the viewport template
loadTemplate(contextPath+"/static/html/viewport.html", function(element) {
    viewportTemplate = element;
});
//数据展示界面
var studyViewerTemplate; // the study viewer template
loadTemplate(contextPath+"/static/html/studyViewer.html", function(element) {
    studyViewerTemplate = element;
});
//var url = contextPath + '/info/studyList';

var url = contextPath + '/static/json/studyList.json';

// Get study list from JSON manifest
$.getJSON(url, function(data) {
  data.studyList.forEach(function(study) {

    // Create one table row for each study in the manifest
	  
	 //页面展示数据
    var studyRow = '<tr><td>' +
    study.patientName + '</td><td>' +
    study.patientId + '</td><td>' +
    study.studyDate + '</td><td>' +
    study.modality + '</td><td>' +
    study.studyDescription + '</td><td>' +
    study.numImages + '</td><td>' +
    '</tr>';

    // Append the row to the study list
    var studyRowElement = $(studyRow).appendTo('#studyListData');

    // On study list row click
    $(studyRowElement).click(function() {

      // Add new tab for this study and switch to it
      var studyTab = '<li><a href="#x' + study.patientId + '" data-toggle="tab">' + study.patientName + '</a></li>';
      $('#tabs').append(studyTab);

      // Add tab content by making a copy of the studyViewerTemplate element
      var studyViewerCopy = studyViewerTemplate.clone();

      /*var viewportCopy = viewportTemplate.clone();
      studyViewerCopy.find('.imageViewer').append(viewportCopy);*/


      studyViewerCopy.attr("id", 'x' + study.patientId);
      // Make the viewer visible
      studyViewerCopy.removeClass('hidden');
      // Add section to the tab content
      studyViewerCopy.appendTo('#tabContent');

      // Show the new tab (which will be the last one since it was just added
      $('#tabs a:last').tab('show');

      // Toggle window resize (?)
      $('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
        $(window).trigger('resize');
      });

      // Now load the study.json
      //处理图片展示方法，json。图片可以查看
      //var jsonUrl = contextPath + '/info/mrstudyList?studyId ='+study.studyId;
      var jsonUrl = contextPath + '/static/json/' + study.studyId +'.json'
      loadStudy(studyViewerCopy, viewportTemplate, jsonUrl);
    });
  });
});


// Show tabs on click
$('#tabs a').click (function(e) {
  e.preventDefault();
  $(this).tab('show');
});

// Resize main
function resizeMain() {
  var height = $(window).height();
  $('#main').height(height - 50);
  $('#tabContent').height(height - 50 - 42);
}


// Call resize main on window resize
$(window).resize(function() {
    resizeMain();
});
resizeMain();


// Prevent scrolling on iOS
document.body.addEventListener('touchmove', function(e) {
  e.preventDefault();
});
