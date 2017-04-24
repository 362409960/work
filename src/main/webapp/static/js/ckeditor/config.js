/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.removeDialogTabs = 'image:advanced;link:advanced';


	
	 config.filebrowserUploadUrl = '/cloudring-shop-mall-web/ck/upload';

    config.image_previewText = ' '; 
    config.baseFloatZIndex = 10000;
 
  /*  config.height = '350';
    config.width = '100%';*/
    
    config.toolbar = 'Full';
    config.toolbar_Full = [	//['Source','-','Preview','-'],
	                         ['Cut','Copy','Paste','PasteText','PasteFromWord'],
							['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
							//['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
							['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],'/',
							['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
							['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
							['Link','Unlink','Anchor'],
							['Image','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
							['Styles','Format','Font','FontSize'],
							['TextColor','BGColor']
						   ];
};
