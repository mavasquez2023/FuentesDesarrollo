/*
 * Licensed Material - Property of IBM 
 * (C) Copyright IBM Corp. 2002, 2003 - All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication or disclosure 
 * restricted by GSA ADP Schedule Contract with IBM Corp. 
 */

/*
 * Utility class to give info on runtime client browser
 *
 */
function HxBrowserManager () 
{
	this.m_browserLevel     = 3;
	this.m_browserName      = 'Netscape';
	this.m_browserVersion   = 'Unknown';
	this.m_clientPlatform   = 'Unknown';
	this.m_JSVersion       	= 1.0;

	this.m_hasStyleSheet	= false;
	this.m_hasActiveX		= true;
	this.m_hasIECacheBug	= false;
	this.m_hasDOM			= false;
	this.m_hasDOM1			= false;
	this.m_hasDOM2			= false;
	this.m_hasLayers		= false;

	this.m_isIE				= false;
	this.m_isNavigator		= false;
	this.m_isOpera			= false;
	this.m_isKonqueror		= false;
	this.m_isMozilla		= false;
	
	this.init				= hx_initClientBrowserObject; 
	this.isBrowserLevel		= hx_isBrowserLevel; 

	this.getRealAppVersion		= hx_getRealAppVersion; 

	this.hasIEZindexLimitation	= hx_hasIEZindexLimitation; 

	this.getJSVersion		= hx_getJSVersion; 

	this.hasActiveX			= hx_hasActiveX; 
	this.hasIECacheBug		= hx_hasIECacheBug; 
	this.hasStyleSheet		= hx_hasStyleSheet; 
	this.hasDOM				= hx_hasDOM; 
	this.hasDOM1			= hx_hasDOM1; 
	this.hasDOM2			= hx_hasDOM2; 

	this.isIE				= hx_isIE; 
	this.isNetscape			= hx_isNavigator; 
	this.isOpera			= hx_isOpera;
	this.isKonqueror		= hx_isKonqueror;
	this.isMozilla			= hx_isMozilla;

	this.isNetscape4		= hx_isNetscape4; 
	this.hasLayers			= hx_hasLayers; 

	this.isIE5				= hx_isIE5; 
	this.isIE5_5			= hx_isIE5_5; 

	this.isPlatformMac	= hx_isPlatformMac; 
	this.isPlatformWin	= hx_isPlatformWin; 

	this.hasCapability = hx_hasCapability;
	this.hasActiveXControl = hx_hasActiveXControl;

	this.init ();
}

function hx_initClientBrowserObject ()
{
	this.m_browserLevel	= parseInt (navigator.appVersion.charAt(0)); 

	if (typeof (navigator.appName) != "undefined") 
		this.m_browserName       = navigator.appName;
	if (typeof (navigator.userAgent) != "undefined") 
		this.m_browserVersion    = navigator.userAgent;

	if (typeof (navigator.platform) != "undefined") 
		this.m_clientPlatform    = navigator.platform;

	this.m_isIE = this.m_browserName == "Microsoft Internet Explorer";
	this.m_isNavigator = this.m_browserName == "Netscape";
	this.m_isOpera = this.mbrowserName == "Opera";
	this.m_isKonqueror = this.mbrowserName == "Konqueror";
	this.m_isMozilla  = (window.navigator != null ) ? ( window.navigator.userAgent.indexOf("ecko") != -1 ) : false;
	if(this.m_isMozilla)
		this.m_isNavigator = false;

	this.m_realAppVersion = parseFloat( this.m_isIE ? navigator.appVersion.substr(navigator.appVersion.indexOf("MSIE") + 4) : navigator.appVersion);

	this.m_hasLayers = this.m_isNavigator && this.m_browserLevel >= 4 && this.m_browserLevel < 5;

	if( window.gCookie && gCookie.bs && gCookie.bs == "" ) {
		this.m_isIE = true;
		this.m_isNavigator = false;
		this.m_browserLevel = 5;
		this.m_realAppVersion = 5.0;
	}
	if( window.gCookie && gCookie.bs && gCookie.bs == "_NN4" ) {
		this.m_isIE = false;
		this.m_isNavigator = true;
		this.m_browserLevel = 4;
		this.m_realAppVersion = 4.7;
	}

	this.m_hasIECacheBug		= this.m_isIE;
	this.m_hasStyleSheet		= this.isBrowserLevel(4);
	this.m_hasActiveX			= this.m_isIE && (navigator.appVersion.indexOf( "Win") != -1);
	this.m_hasPersistentStore	= this.m_isIE && this.m_realAppVersion > 5 && this.isPlatformWin();

	this.m_hasDOM	= this.m_isIE && this.isBrowserLevel(4);
	if ( window.document.implementation != null) {
	    this.m_hasDOM1 	= window.document.implementation.hasFeature("HTML","1.0");
	    this.m_hasDOM2 	= window.document.implementation.hasFeature("HTML","2.0") &&
	         			  window.document.implementation.hasFeature("Events","2.0") &&
	         			  window.document.implementation.hasFeature("Core","2.0") &&
	         			  window.document.implementation.hasFeature("CSS2","2.0");
	}
}

function hx_getRealAppVersion () 
{
	return this.m_realAppVersion;
}

function hx_hasIEZindexLimitation ()
{
	return this.m_isIE && this.m_realAppVersion < 5.5;
}

function hx_isBrowserLevel (neededBrowserLevel) 
{
	return (neededBrowserLevel  < (this.m_browserLevel+1));
}

function hx_hasActiveX ()
{
	return this.m_hasActiveX;
}

function hx_hasIECacheBug ()
{
	return this.m_hasIECacheBug;
}

function hx_hasStyleSheet ()
{
	return this.m_hasStyleSheet;
}

function hx_hasLayers ()
{
	return this.m_hasLayers;
}

function hx_hasDOM ()
{
	return this.m_hasDOM;
}

function hx_hasDOM1 ()
{
	return this.m_hasDOM1;
}

function hx_hasDOM2 ()
{
	return this.m_hasDOM2;
}

function hx_isIE ()
{
	return this.m_isIE; 
}

function hx_isNavigator ()
{
	return this.m_isNavigator;
}

function hx_isOpera ()
{
	return this.m_isOpera; 
}

function hx_isKonqueror ()
{
	return this.m_isKonqueror;
}

function hx_isMozilla ()
{
	return this.m_isMozilla;
}

function hx_isNetscape4 () 
{
	return (this.m_isNavigator && this.m_realAppVersion >= 4.0);
}

function hx_getJSVersion () 
{
	return this.m_JSVersion;
}

function hx_isIE5 () 
{
	return (this.m_isIE && this.m_realAppVersion >= 5.0);
}

function hx_isIE5_5()
{
	return (this.m_isIE && this.m_realAppVersion >= 5.5);
}

function hx_isPlatformMac ()
{
	return ((this.m_clientPlatform.indexOf("Mac") != -1) ? true : false);
}


function hx_isPlatformWin ()
{
	return ((this.m_clientPlatform.indexOf("Win") != -1) ? true : false);
}

function hx_hasCapability( nBrowserCapability )
{
	switch(nBrowserCapability)
	{
		case 1:
			return this.m_hasPersistentStore;

		case 2:
			return this.m_isIE && this.isPlatformWin() && (this.m_realAppVersion > 5.01 || (5.01 == this.m_realAppVersion && navigator.appMinorVersion != "0") );
			
		case 3:
			return this.m_isIE && this.isPlatformWin() && this.m_realAppVersion > 5;
			
		default:
			break;
	}

	return false;
}

function hx_hasActiveXControl(sControlName) {
	if(!window.ActiveXObject)
		return false;
	var hasCtl = false;
	//this code isn't safe for Netscape, so make it a String
	var expr = "try{ var xObj = new ActiveXObject(\"" + sControlName +  "\"); if (xObj==null) hasCtl = false; else hasCtl = true; } catch (e){hasCtl = false;}";
	eval(expr);
	return hasCtl;
}

/**
 * The paths to the rich controls resources are defined below.
 * Resources include JavaScript lib(s), gif images, CSS files, HTML
 * dialogs etc.
 * The Resource Manager derives and sets paths, loads CSS files etc
 *
 */

function HxResourceManager () {
	this.bPathSet	  = false;
	this.jsfRootToken = "/faces";
	this.protocol     = "http://";
	this.baseLibPath  = ".ibmjsfres/";
	this.baseImgPath  = this.baseLibPath + "img/";
	this.baseCssPath  = this.baseLibPath + "css/";
	this.baseDlgPath  = this.baseLibPath + "dlg/";

	this.getRelativePathPrefix = hx_getRelativePathPrefix;
	this.getAbsolutePathPrefix = hx_getAbsolutePathPrefix;
	this.setRelativePaths = hx_setRelativePaths;
	this.setAbsolutePaths = hx_setAbsolutePaths;
	
	this.getImgPath  = function() { return this.baseImgPath; }
	this.getCssPath  = function() { return this.baseCssPath; }
	this.getDlgPath  = function() { return this.baseDlgPath; }	
}

/**
 * Return the relative path from the active page to app root dir
 * e.g. for
 * http://servername/projectdir/faces/myFolder/myPage.jsp 
 * return "../"
 * 
 * @See setRelativePaths() which concats the prefix and the
 * standard resource paths which are off the root dir
 * 
 */
function hx_getRelativePathPrefix(sToken) {
	if (typeof(sToken) == "undefined" || sToken == "")
		sToken = this.jsfRootToken;
	
	var sLocation   = new String(document.location);
	var sPage2Root  = "";
	var iCursor     = sLocation.indexOf(sToken, 0);
	
	if (iCursor != -1) {
		iCursor += sToken.length;
		var sTailPath = sLocation.slice(iCursor+1);
		for (var i = 0; i < sTailPath.length; i++) {
			if (sTailPath.charAt(i) == "/") {
				sPage2Root += "../";
			}
		}
	}
	else {
		iCursor = sLocation.indexOf(this.protocol, 0);
		if (iCursor != -1) {
			iCursor += this.protocol.length;
			var i,j;
			for (i = iCursor, j= 0; i < sLocation.length; i++) {
				if (sLocation.charAt(i) == "/")
					j++;
			}
			if (j > 2) {
				while (j-- > 2)
					sPage2Root += "../";
			}	
		}	
	}

	return sPage2Root;
}

/**
 * Return the absolute path prefix for the resource files
 * e.g. for
 * http://servername/projectdir/faces/myFolder/myPage.jsp
 * return "/projectdir/faces/"
 *
 * Note that "http://servername/projectdir/faces/" or "/projectdir/"
 * seem to work just as well!
 *
 * @See setAbsolutePaths() which concats the prefix and the 
 * standard resource paths which are off the root dir
 *
 */
function hx_getAbsolutePathPrefix(sToken) {
	if (typeof(sToken) == "undefined" || sToken == "")
		sToken = this.jsfRootToken;
	
	var sLocation   = new String(document.location);
	var sPathPrefix = "";
	var iCursor     = sLocation.indexOf(sToken, 0) - 1;
	
	if (iCursor < 0) {
		/* Didn't find token - no "faces" in location */
		iCursor = sLocation.indexOf(this.protocol, 0);
		if (iCursor != -1) {
			var sTailPath = sLocation.slice(iCursor + this.protocol.length);
			for (var i = 0; i < sTailPath.length; i++) {
				if (sTailPath.charAt(i) == "/") {
					sTailPath = sTailPath.slice(i);
					for (i = 1; i < sTailPath.length; i++) {
						if (sTailPath.charAt(i) == "/") {
							sPathPrefix = sTailPath.substring(0, i + 1);
							break;
						}
					}
				}
			}
		}
	}
	else {
		/* rewind thru the location string for the start of the project dir */
		var bFoundProjectDir = false;
		for (var i = iCursor; i >= 0; i--) {
			if (sLocation.charAt(i) == "/") {
				bFoundProjectDir = true;
				break;
			}
		}
		if (bFoundProjectDir == true) {
			/* for "http://servername:port#/project_name/.ibmjsfres/..." do next line */
			// sPathPrefix = sLocation.substring(0, i + (iCursor - i) +  1 + 1);

			/* for "/project_name/.ibmjsfres/..." do next line */
			sPathPrefix = sLocation.substring(i, i + (iCursor - i) +  1 + 1);

			/* for "/project_name/faces/.ibmjsfres/..." do next line */
			// sPathPrefix = sLocation.substring(i, i + (iCursor - i) + sToken.length + 1 + 1);
		}
	}
	
	return sPathPrefix;
}

/**
 * Set the path for all resources relative to the active page
 *
 */
function hx_setRelativePaths() {
	if (this.bPathSet == false) {
		var sPage2Root = this.getRelativePathPrefix(this.jsfRootToken);
		if (sPage2Root != "") {
			this.baseImgPath = sPage2Root + this.baseImgPath;
			this.baseCssPath = sPage2Root + this.baseCssPath;
			this.baseDlgPath = sPage2Root + this.baseDlgPath;
		}
		this.bPathSet = true;
	}
}

/**
 * Set the absolute path for all resources
 *
 */
function hx_setAbsolutePaths() {
	if (this.bPathSet == false) {
		var sPathPrefix = this.getAbsolutePathPrefix(this.jsfRootToken);
		if (sPathPrefix != "") {
			this.baseImgPath = sPathPrefix + this.baseImgPath;
			this.baseCssPath = sPathPrefix + this.baseCssPath;
			this.baseDlgPath = sPathPrefix + this.baseDlgPath;
		}
		this.bPathSet = true;
	}
}
/*
 * Resource strings for rich text editor now defined in the backend
 * Ref. runtimeibm.properties
 */
D_FONTLABEL_MONOSPACE	= 0; // "Monospace";
D_FONTLABEL_SERIF		= 1; // "Serif";
D_FONTLABEL_SANSSERIF	= 2; // "Sans Serif";

L_ALTTEXT_EDITORBUTTONALIGN			=  3; //"Align";
L_ALTTEXT_EDITORBUTTONBOLD			=  4; //"Bold";
L_ALTTEXT_EDITORBUTTONHEADING		=  5; //"Heading";  ***
L_ALTTEXT_EDITORBUTTONINDENT		=  6; //"Indent";
L_ALTTEXT_EDITORBUTTONINSERTGRAPHICTEXT	= 7 //"Insert graphic text with special effects";  ***
L_ALTTEXT_EDITORBUTTONINSERTIMAGE	=  8; //"Insert image";  
L_ALTTEXT_EDITORBUTTONINSERTLINK	=  9; //"Insert link";   
L_DHTMLCONTROL_SETFORMAT_NORMAL		= 10; //"Normal";		   
L_DHTMLCONTROL_SETFORMAT_HEADING1	= 11; //"Heading 1";	   
L_ALTTEXT_EDITORBUTTONNUMBEREDLIST	= 12; //"Numbered list"; 
L_ALTTEXT_EDITORBUTTONOUTDENT		= 13; //"Outdent";	   
L_ALTTEXT_EDITORBUTTONSPELLCHECK	= 14; //"Spell check";   
L_ALTTEXT_EDITORBUTTONTEXTCOLOR		= 15; //"Text color";	   
L_ALTTEXT_EDITORBUTTONUNDERLINE		= 16; //"Underline";	   
L_ALTTEXT_EDITORBUTTONITALIC		= 17; //"Italic";		   
L_ALTTEXT_EDITORBUTTONUNORDEREDLIST	= 18; //"Unordered list";

L_ALERT_SELECTTEXTFORLINK = 19; //"Select some text before you add a link.";

/*
 * Basic Editor object
 *
 */
function HxBasicTextEditor (sId, sObjectRef, sWidth, sHeight) {	
	this.Id			= sId;
	this.objectRef	= sObjectRef;
	this.sWidth		= (typeof(sWidth) == "undefined") ? "" : sWidth;
	this.sHeight	= (typeof(sHeight) == "undefined") ? "" : sHeight;
	this.sContent	= "";

	this.init  = initHxBTE;
	this.getEditorHTML = HxBTE_getEditorHTML;
	this.getEditModeHTML = HxBTE_getEditModeHTML;
	this.setInitContent  = HxBTE_setInitContent;

	if (this.sHeight == "")
		this.sHeight = "15"; // ROWS default
	if (this.sWidth == "")
		this.sWidth = "73";  // COLS default
}

function initHxBTE() {}

/**
 * Any changes to this method should be replicated in the <noscript>
 * section of the richtext renderer code. Important to keep in synch.
 *
 */
function HxBTE_getEditorHTML()
{
	return ('<textarea wrap=virtual' +
		' rows='  + this.sHeight + 
		' cols='  + this.sWidth  + 
		' name="' + this.Id +
		'">' +
		this.sContent +	
		'</textarea>');
}

function HxBTE_getEditModeHTML ()
{
    var htmlEditor="";

    htmlEditor += this.getEditorHTML();
    return htmlEditor;
}

function HxBTE_setInitContent(sContent) {
	this.sContent = sContent;
}


// *** rte constant definitions ***
HxRTE_DECMD_BOLD		= 5000;
HxRTE_DECMD_COPY		= 5002;
HxRTE_DECMD_CUT		= 5003;
HxRTE_DECMD_DELETE		= 5004;
HxRTE_DECMD_FINDTEXT		= 5008;
HxRTE_DECMD_INDENT		= 5018;
HxRTE_DECMD_ITALIC		= 5023;
HxRTE_DECMD_JUSTIFYCENTER	= 5024;
HxRTE_DECMD_JUSTIFYLEFT	= 5025;
HxRTE_DECMD_JUSTIFYRIGHT	= 5026;
HxRTE_DECMD_ORDERLIST		= 5030;
HxRTE_DECMD_OUTDENT		= 5031;
HxRTE_DECMD_PASTE			= 5032;
HxRTE_DECMD_REDO			= 5033;
HxRTE_DECMD_SELECTALL		= 5035;
HxRTE_DECMD_SETFONTNAME	= 5044;
HxRTE_DECMD_SETFONTSIZE	= 5045;
HxRTE_DECMD_UNDERLINE		= 5048;
HxRTE_DECMD_UNDO			= 5049;
HxRTE_DECMD_UNORDERLIST	= 5051;
HxRTE_DECMD_SETFORECOLOR	= 5046;
HxRTE_DECMD_SETBLOCKFMT	= 5043;
HxRTE_DECMD_GETBLOCKFMT	= 5011;
HxRTE_DECMD_GETFONTNAME	= 5013;
HxRTE_DECMD_GETFONTSIZE	= 5014;
HxRTE_DECMD_GETBLOCKFMT	= 5011;

HxRTE_D_FONTFACE_MONOSPACE	= "Default Monospace, Courier New, Courier, monospace";
HxRTE_D_FONTFACE_SERIF		= "Default Serif, Times New Roman, Times, serif";
HxRTE_D_FONTFACE_SANSSERIF	= "Default Sans Serif, Verdana, Arial, Helvetica, sans-serif";

HxRTE_D_DHTML_STYLESHEET		= "<style>BODY { font-family: " + HxRTE_D_FONTFACE_SANSSERIF + "; font-size: 10pt}</style>";
HxRTE_DHTMLEMPTYPAGEBODYSTART	= "<div></div>";

/* font picker name/id suffix */
HxRTE_FONT_NAME_CTRL		= "FontNames";
HxRTE_FONT_SIZE_CTRL		= "FontSizes";

HxRTE_MIN_EDITOR_WIDTH	= 480;

/*
 * DHTML Rich Text Editor object
 *
 */
function HxRichTextEditor (sId, sObjectRef, sWidth, sHeight) {	
	// *** member variables  ***
	this.seoulRTE	= null;

	this.Id			= sId;
	this.objectRef	= sObjectRef;
	this.sWidth		= sWidth;
	this.sHeight		= sHeight;
	this.sFontNameCtrl	= this.Id + HxRTE_FONT_NAME_CTRL; 
	this.sFontSizeCtrl	= this.Id + HxRTE_FONT_SIZE_CTRL;
	this.sInitContent	= "";

	this.selectedElement	= "";
	this.bAlignmentMode 	= 1; // 0 = left, 1 = center, 2 = right
	this.bHTMLIsSet         = false;
	this.bDHTMLInited		= false;

	this.imgList = new Array();	  
	this.tableImgList = new Array();

	// *** methods ***
	this.init  = Hx_initRTE;
	this.setRichTextContext = Hx_setRichTextContext;
	this.setInitContent	= Hx_setInitContent;

	this.dhtmlFormat  = Hx_dhtmlFormatCmd;
	this.pickColor    = Hx_pickColorDlg;
	this.align        = Hx_alignText;

	this.undo         = Hx_undoCmd;
	this.createLink   = Hx_createLink;
	
	this.changeButton = Hx_changeButtonFace;
	this.changeFontName = Hx_changeFontNameFamily;
	this.changeButtonAlign = Hx_changeButtonAlignFace;

	this.getEditor          = Hx_getEditorObject;
	this.getEditorHTML      = Hx_getEditorHTMLCode;
	this.getEditModeHTML    = Hx_getEditModeHTMLCode;
	this.getEditorDocHTML   = Hx_getEditorDocHTML;
	this.getEditorText      = Hx_getEditorText;
	this.writeEditorButtons = Hx_writeEditorButtons;

	this.getImage          = HxRTE_getImage;
	
	this.onLoad	           = Hx_onLoadEvent;
	this.onClick           = Hx_onClickEvent;
	this.onFontNameChange  = Hx_onFontNameChangeEvent;
	this.onFontSizeChange  = Hx_onFontSizeChangeEvent;
	
	this.setDefaultFontFace = Hx_setDefaultFontFace;
	this.setDefaultFontSize = Hx_setDefaultFontSize;

	this.updateContents    = Hx_updateContents;

	this.setContents       = Hx_setContents; 
	
	// some constructor parameter validation
	if (sWidth == "")
		this.sWidth = "100%";
	else
	{
		var iWidth = parseInt(sWidth);
		if (HxRTE_MIN_EDITOR_WIDTH > iWidth)
			this.sWidth = HxRTE_MIN_EDITOR_WIDTH;
	}
}
   
function Hx_initRTE() {}

function Hx_setRichTextContext(sRichText)
{
	// alert(sRichText);
	
	if(this.bDHTMLInited == true)
		return;
	

	if (this.Id == "") {
		alert("DEBUG: the editor object has not been assigned an Id!");
		return;
	}

	if (this.seoulRTE == null)
		this.seoulRTE = this.getEditor();

	eval(this.Id).focus();
	var DHTMLStyleSheet = '<HEAD>' + HxRTE_D_DHTML_STYLESHEET + '</HEAD>';

	
	this.bAlignmentMode=1;
	this.bHTMLIsSet = true;
	
	if (sRichText != "")
		this.seoulRTE.body.innerHTML = DHTMLStyleSheet + sRichText;
	else
		this.seoulRTE.body.innerHTML = DHTMLStyleSheet + HxRTE_DHTMLEMPTYPAGEBODYSTART;
	
	this.bDHTMLInited = true;
}

function Hx_setContents(sRichText)
{
	// alert(sRichText);

	if (this.seoulRTE == null)
		this.seoulRTE = this.getEditor();

	eval(this.Id).focus();
	var DHTMLStyleSheet = '<HEAD>' + HxRTE_D_DHTML_STYLESHEET + '</HEAD>';

	if (sRichText != "")
		this.seoulRTE.body.innerHTML = DHTMLStyleSheet + sRichText;
}

function Hx_setInitContent(sInitContent) {
	this.sInitContent = sInitContent;
}

function Hx_getEditorObject() {
	var rte = document.getElementById(this.Id).contentWindow.document;
	// var rte = eval(this.Id).document;
	// var body = rte.body;

	if (rte == null)
	{
		alert("DEBUG: cannot resolve component id!");
	}
	
	this.seoulRTE = rte;
	
	return this.seoulRTE;
}

function Hx_dhtmlFormatCmd(funcNum) {

	if (this.seoulRTE == null) {
		this.getEditor();
	}

	this.seoulRTE.body.focus();
	
	switch(funcNum)
	{
		case HxRTE_DECMD_BOLD:
			this.seoulRTE.execCommand('Bold', false, null);
			break;
		
		case HxRTE_DECMD_ITALIC:
			this.seoulRTE.execCommand('Italic', false, null);
			break;

		case HxRTE_DECMD_UNDERLINE:
			this.seoulRTE.execCommand('Underline', false, null);
			break;

		case HxRTE_DECMD_INDENT:
			this.seoulRTE.execCommand('Indent', false, null);
			break;

		case HxRTE_DECMD_OUTDENT:
			this.seoulRTE.execCommand('Outdent', false, null);
			break;

		case HxRTE_DECMD_UNORDERLIST:
			this.seoulRTE.execCommand('InsertUnorderedList', false, null);
			break;

		case HxRTE_DECMD_ORDERLIST:
			this.seoulRTE.execCommand('InsertOrderedList', false, null);
			break;	

		default:
			// alert(funcNum);
			break;
	}
}

function Hx_pickColorDlg() {
	if (this.seoulRTE == null) {
		this.getEditor();
	}

	this.seoulRTE.body.focus();

	var dlgArgs = new Object();
	dlgArgs.dlgTitle = "Colors";
	var arr = showModalDialog(hx_Env.resources.getDlgPath() + "selcolor.html",
							  dlgArgs,
							  "dialogWidth: 120px; dialogHeight: 292px; help: No; status: no;" );
	
	if ( typeof(arr) != "undefined" )  {
		this.seoulRTE.execCommand("ForeColor", false, arr);
	}

	this.seoulRTE.body.focus();
}

function Hx_changeButtonFace (caller, num, isOver)
{
	var szstr="";
	if (num == 1)		szstr = "bold";
	else if (num == 2)	szstr = "italic";
	else if (num == 3)	szstr = "underline";
	else if (num == 4)	szstr = "TextColor";
	else if (num == 8)	szstr = "Indent";
	else if (num == 9)	szstr = "outdent";
	else if (num == 10)	szstr = "bullet";
	else if (num == 11)	szstr = "NumberedList";
	else if (num == 12)	szstr = "link";
	else if (num == 13)	szstr = "Graphicheadline";
	else if (num == 14)	szstr = "image";
	else if (num == 15)	szstr = "spellCheck";
	else if (num == 16)	szstr = "headline";
	else if (num == 17)	szstr = "dictionary";

	var nameStr = szstr + "Image";
	if (isOver) szstr += "M.gif";
	else szstr += ".gif";
	this.getImage(caller, 0, nameStr, hx_Env.resources.getImgPath() + szstr);
}

function HxRTE_getImage( winObj, nDirect, imgName, theURL)
{
	checkImage = new Image();
	checkImage.src = theURL;

	// if ie3 then just return the URL!
	var i;
	var theImgObj = null;

	if ( typeof(winObj) == "undefined")
	{
		return;
	}

	if ( nDirect == 1)
	{
		theImgObj = winObj;
	}
	else
	{
		theImgObj = eval( "winObj.document." + imgName);
	}

	if (( typeof( theImgObj) == "undefined") ||
		( typeof( theImgObj.src) == "undefined"))
	{
		return;
	}

	for ( i = 0; i < this.imgList.length; i++)
	{
		if ( this.imgList[i].src == checkImage.src)
		{
			theImgObj.src = this.imgList[i].src;
			return;
		}
	}

	// not found
	
	i = this.imgList.length;

	this.imgList[i] = checkImage;
	theImgObj.src = this.imgList[i].src;
	return;
}

function Hx_alignText(caller)
{
	if (this.seoulRTE == null) {
		this.getEditor();
	}
	
	if (this.selectedElement != "" && this.selectedElement.commonParentElement())
	{
		if (this.bAlignmentMode == 0)
			this.selectedElement.commonParentElement().align = "Left";
		else if (this.bAlignmentMode == 1)
			this.selectedElement.commonParentElement().align = "";
		else if (this.bAlignmentMode == 2)
			this.selectedElement.commonParentElement().align = "Right";
	}
	else
	{
		var txtAlign;

		if (this.bAlignmentMode == 0)
			txtAlign = "JustifyLeft";
		else if (this.bAlignmentMode == 1)
			txtAlign = "JustifyCenter";
		else
			txtAlign = "JustifyRight";

		this.seoulRTE.execCommand(txtAlign);
	}

	this.changeButtonAlign(caller, 2);
	this.seoulRTE.body.focus();
}

function Hx_changeButtonAlignFace (caller, isOver)
{
	if (isOver == 2)
	{
		this.bAlignmentMode++;
		if (this.bAlignmentMode > 2) this.bAlignmentMode = 0;
	}

	var szstr="";
	var szAltstr="";
	if (this.bAlignmentMode == 0) { szAltstr = "Align Left"; szstr = "AlignLeft"; }
	if (this.bAlignmentMode == 1) { szAltstr = "Align Center"; szstr = "AlignCenter"; }
	if (this.bAlignmentMode == 2) { szAltstr = "Align Right"; szstr = "AlignRight"; }
	
	if (isOver > 0)
		szstr += "M.gif";
	else
		szstr += ".gif";
	this.getImage(caller, 0, "AlignCenterImage", hx_Env.resources.getImgPath() + szstr);

	caller.title = szAltstr;
}

function Hx_undoCmd() {
	if (this.seoulRTE == null) {
		this.getEditor();
	}

	this.seoulRTE.execCommand("Undo", false, null);
	this.seoulRTE.body.focus();
}

function Hx_createLink() {
	if (this.seoulRTE == null) {
		this.getEditor();
	}
	
	if (this.seoulRTE.selection.type == "None") {
		alert(hx_Env.rte_string[L_ALERT_SELECTTEXTFORLINK]);
		return;
	}

	if(this.seoulRTE.queryCommandSupported('createLink') ){
		this.seoulRTE.execCommand('createLink',true,null);

	}
	this.seoulRTE.body.focus();
}

function Hx_onLoadEvent(frameId) {
	
	// document should always be available when IFRAME fires onload
	if (this.bDHTMLInited == false) {
		this.Id = frameId;

			// have to set event via the eval below the following will NOT work
			// 		var frameObj=document.getElementById(frameid);
			// 		frameObj.document.onselectionchange=MSHTMLRichTextArea_displayChanged;
			// <script for=IFRAME.document event="event">JSObject.method</scrpt> does not
			// work either (I confirmed with Microsoft that can't use IFRAME.document as target of event
			// in <script for= event=>.  This means these event handlers are "global" - no
			// access to the "this" object
			
		// eval(frameId + ".document.onselectionchange=MSHTMLRichTextArea_displayChanged");
		// eval(frameId + ".document.onkeydown=MSHTMLRichTextArea_keyCatch");
		//eval(frameId + ".document.body.oncontextmenu=MSHTMLRichTextArea_contextCatch");

		this.setRichTextContext(this.sInitContent);		
	}

	return;	

}

function Hx_onClickEvent() {
	if (this.seoulRTE == null) {
		this.getEditor();
	}
		
	if (this.seoulRTE != null)
	{
		this.selectedElement = this.seoulRTE.DOM.selection.createRange();
		if (this.seoulRTE.DOM.selection.type != "Control")
			this.selectedElement = "";
	}
}

function Hx_onFontNameChangeEvent() {
	if (this.seoulRTE == null) {
		this.getEditor();
	}

	if (this.selectedElement != "")
		return; // only execute this if there is no image selected

	var FontNameCtrl = document.getElementById(this.sFontNameCtrl);
	var FontSizeCtrl = document.getElementById(this.sFontSizeCtrl);
	
	this.changeFontName();
	// changes font size
	this.seoulRTE.execCommand ("FontSize", false, FontSizeCtrl.selectedIndex+1);
	this.seoulRTE.body.focus();
}

function Hx_onFontSizeChangeEvent() 
{
	if (this.seoulRTE == null) {
		this.getEditor();
	}
	
	// this.onClick(); // tmp while hooking up editorObj.onClick();

	if (this.selectedElement != "")
		return; // only execute this if there is no image selected
	
	var FontSizeCtrl = document.getElementById(this.sFontSizeCtrl);
	
	if (FontSizeCtrl != null) {
		this.seoulRTE.execCommand ("FontSize", false, FontSizeCtrl.selectedIndex+1);
		this.changeFontName();
		this.seoulRTE.body.focus();
	}
}

function Hx_changeFontNameFamily()
{
	if (this.seoulRTE == null) {
		this.getEditor();
	}

	var FontStr = "";
	var FontNameCtrl = document.getElementById(this.sFontNameCtrl);

	if (FontNameCtrl != null) {
		if (FontNameCtrl.selectedIndex == 0) // courier
			FontStr = HxRTE_D_FONTFACE_MONOSPACE;
		else if (FontNameCtrl.selectedIndex == 1) 	// times
			FontStr = HxRTE_D_FONTFACE_SERIF;
		else // verdana
			FontStr = HxRTE_D_FONTFACE_SANSSERIF;

		// alert(FontStr);
		this.seoulRTE.execCommand ("FontName", false, FontStr);
	}
}

function Hx_setDefaultFontFace() {
	if (this.seoulRTE == null) {
		this.getEditor();
	}

	var FontNameCtrl = document.getElementById(this.sFontNameCtrl);

	FontNameCtrl.value = "1";
	this.onFontNameChange();	
}

function Hx_setDefaultFontSize() {
	if (this.seoulRTE == null) {
		this.getEditor();
	}
	
	var FontSizeCtrl = document.getElementById(this.sFontSizeCtrl);
	FontSizeCtrl.value = "1";
	this.onFontSizeChange();	
}


function HxRTE_genImageTag( URLString, ImgName, ImgExtra)
{
	var retString;
	retString = '<img name=' + ImgName + ' src="' + URLString + '" ' + ImgExtra + ' >';
	return retString;
}

//______________________________________________________________________________________
//
// Returns an HTML string used to display the editing controls for the rich text field
//
function Hx_writeEditorButtons( theName, bUseAlign, altTag, DHTMLThing, num)
{
	nameStr = theName + "Image";
	imageFileStr = theName + ".gif";
	var theTableEntry = '<td class="s-form-tool-cell">';
	if ( bUseAlign)
	{
		var imgExtra = ' id="rtfButton' + theName + '" width="22" height="22" align="absmiddle" ';
		
		if (num != 5)
		{
			imgExtra += ' alt="' + 
						altTag + 
						'" onMouseOver="' +
						this.objectRef + '.changeButton(this,' + 
						num + 
						',1);" onMouseOut="' +
						this.objectRef + '.changeButton(this,' + 
						num + 
						',0);" onclick="return ' + 
						DHTMLThing + 
						'"';

			theTableEntry += HxRTE_genImageTag(hx_Env.resources.getImgPath() + imageFileStr,nameStr,imgExtra);
		}
		else
		{
			imgExtra += ' TITLE="' + 
						altTag + 
						'" onMouseOver="' + this.objectRef + '.changeButtonAlign(this, 1);"'  +
						'onMouseOut="' + this.objectRef + '.changeButtonAlign(this, 0);" onclick="return ' + 
						DHTMLThing + 
						'"';
		
			theTableEntry += HxRTE_genImageTag(hx_Env.resources.getImgPath() + imageFileStr,nameStr,imgExtra);
		}
	}
	else
	{
		theTableEntry += HxRTE_genImageTag(hx_Env.resources.getImgPath() + imageFileStr,nameStr,' width="6"');
	}		
	theTableEntry += '</td>';
	return theTableEntry;
}

function Hx_getEditorHTMLCode()
{
	 return ('<iframe contenteditable="true" ' 
	 		+ 'style="width:' + this.sWidth + ';height:' + this.sHeight + ';"'
//			 + ' src="srcfile.html" '
			+ ' id=' + this.Id
			+ ' onload="' + this.objectRef + '.onLoad(' + "'" + this.Id + "');" + '"'
			+ ' onBlur="' + this.objectRef + '.updateContents();"'
			+ '></iframe>');
}

function Hx_getEditorDocHTML()
{
	var element = document.getElementById(this.Id);
	var edHTML  = new String(element.contentWindow.document.body.innerHTML);
	return edHTML;
}


function Hx_updateContents() {
	var edContentsId = this.Id + "_contents";
	var edContentsEl = document.getElementById(edContentsId);
	if (edContentsEl)
		edContentsEl.value = this.getEditorDocHTML();
}


function Hx_getEditorText()
{
	var element = document.getElementById(this.Id);
	var edText  = new String(element.contentWindow.document.body.innerText);
	return edText;
}

function Hx_getEditModeHTMLCode()
{	
	// Need to have our resources defined by the time we get here
	// If not, assign the local EN-US fallback version
	if (!hx_Env.rte_string.length)
		hx_Env.initResource(Hx_RT_COMPONENT);
		
	var htmlEditor;
	
	htmlEditor = '<A NAME="DhtmlEditor"></A>';
	htmlEditor += '<table cellpadding="0" cellspacing="0" border="" class="s-tool-bg"><tr><td nowrap="nowrap">';

	htmlEditor += '<table id="SeoulEditorMenus" height="34" cellpadding="0" cellspacing="0" border="0">';
	htmlEditor += '<td class="s-form-tool-spacer"></td>';

	htmlEditor += '<td><SELECT NAME="';
	htmlEditor += this.sFontNameCtrl + '" id="' + this.sFontNameCtrl + '" onChange="';
	htmlEditor += this.objectRef + '.onFontNameChange()" SIZE="1" TABINDEX="-1"><OPTION VALUE="1">' + 
			hx_Env.rte_string[D_FONTLABEL_MONOSPACE] + '<OPTION VALUE="2">' + hx_Env.rte_string[D_FONTLABEL_SERIF] + '<OPTION VALUE="3" SELECTED>' + hx_Env.rte_string[D_FONTLABEL_SANSSERIF] + '</SELECT></td>';
	htmlEditor += '<td><SELECT NAME="';
	htmlEditor += this.sFontSizeCtrl + '" id="' + this.sFontSizeCtrl + '" onChange="';
	htmlEditor += this.objectRef + '.onFontSizeChange()" SIZE="1" TABINDEX="-1"><OPTION VALUE="1"> 8<OPTION VALUE="2" SELECTED>10<OPTION VALUE="3">12<OPTION VALUE="4">14<OPTION VALUE="5">18<OPTION VALUE="6">24<OPTION VALUE="7">36</SELECT></td>';

	htmlEditor += '<td class="s-form-tool-spacer"></td>';

	htmlEditor += this.writeEditorButtons( "bold", true, hx_Env.rte_string[L_ALTTEXT_EDITORBUTTONBOLD], this.objectRef + ".dhtmlFormat(HxRTE_DECMD_BOLD)", 1);
	htmlEditor += this.writeEditorButtons( "italic", true, hx_Env.rte_string[L_ALTTEXT_EDITORBUTTONITALIC], this.objectRef + ".dhtmlFormat(HxRTE_DECMD_ITALIC)", 2);
	htmlEditor += this.writeEditorButtons( "underline", true, hx_Env.rte_string[L_ALTTEXT_EDITORBUTTONUNDERLINE], this.objectRef + ".dhtmlFormat(HxRTE_DECMD_UNDERLINE)", 3);
	htmlEditor += this.writeEditorButtons( "TextColor", true, hx_Env.rte_string[L_ALTTEXT_EDITORBUTTONTEXTCOLOR], this.objectRef + ".pickColor()", 4);

	htmlEditor += '<td class="s-form-tool-spacer"></td>';
	htmlEditor += this.writeEditorButtons( "AlignCenter", true, L_ALTTEXT_EDITORBUTTONALIGN, this.objectRef + ".align(this)", 5);
	htmlEditor += this.writeEditorButtons( "Indent", true, hx_Env.rte_string[L_ALTTEXT_EDITORBUTTONINDENT], this.objectRef + ".dhtmlFormat(HxRTE_DECMD_INDENT)", 8);
	htmlEditor += this.writeEditorButtons( "outdent", true, hx_Env.rte_string[L_ALTTEXT_EDITORBUTTONOUTDENT], this.objectRef + ".dhtmlFormat(HxRTE_DECMD_OUTDENT)", 9);
	htmlEditor += this.writeEditorButtons( "bullet", true, hx_Env.rte_string[L_ALTTEXT_EDITORBUTTONUNORDEREDLIST], this.objectRef + ".dhtmlFormat(HxRTE_DECMD_UNORDERLIST)", 10);
	htmlEditor += this.writeEditorButtons( "NumberedList", true, hx_Env.rte_string[L_ALTTEXT_EDITORBUTTONNUMBEREDLIST], this.objectRef + ".dhtmlFormat(HxRTE_DECMD_ORDERLIST)", 11);

	htmlEditor += '<td class="s-form-tool-spacer"></td>';
	
	htmlEditor += this.writeEditorButtons( "link", true, hx_Env.rte_string[L_ALTTEXT_EDITORBUTTONINSERTLINK], this.objectRef + ".createLink()", 12);

	htmlEditor += '<td class="s-form-tool-spacer"></td>';

//	htmlEditor += this.writeEditorButtons( "link", true, hx_Env.rte_string[L_ALTTEXT_EDITORBUTTONINSERTLINK], "DoSubmitCmd(3)", 16);
	htmlEditor += '</table></td></tr>';
	
	htmlEditor += '<tr><td nowrap="nowrap">';
	htmlEditor += this.getEditorHTML();
	htmlEditor += '</td></tr></table>';	

	return htmlEditor;
}

/*
 * Tab Control Component
 *
 * Purpose:   manages various panels associated with a tabbed dialog.
 *
 * Parameters:
 * 				doc					pass in document object for window.
 * 				sUniqueSuffix		a unique suffix for this tab control.
 *									This is important to support
 *									multiple such controls on the same
 *									page.
 *				sColor				set the background color for the
 *									tabbed dialog should be null to
 *									inherit from parent
 *				sWidth				width parameter for tabbed area
 *				sHeight				height parameter for tabbed area
 *
 *	Methods:
 *				addTab				add Tab to tab control
 *				generateHTML		emit HTML for tab control
 *
 * =====================================================================
 *  Example: Tab Control with three tabs
 *
 *  var oTabCtrl = new TabControl(doc, "ToDo", null, "100%", "100%");
 *  oTabCtrl.addTab( L_TAB_TODO, "BasicTab", sBasicHTML );
 *  oTabCtrl.addTab( L_TAB_REPEAT, "RepeatTab", sRepeatHTML );
 *
 * =====================================================================
 *
 *
 */
HX_TC_NN_TAB_OFFSET	  = 2;
HX_TC_NN_TAB_PADDING  = 2;
HX_TC_NN_TAB_HEIGHT	  = 19;

D_TABCONTROL_NOTIFY_PREHIDEPANEL	= 1;
D_TABCONTROL_NOTIFY_PRESHOWPANEL	= 2;
D_TABCONTROL_NOTIFY_POSTSHOWPANEL	= 3;

function HxIETabControl( doc, sUniqueSuffix, sColor, sWidth, sHeight, sInactiveTabColor )
{
	this.document = doc;
	this.Id = "TabCtrl_"+sUniqueSuffix;
	this.sUniqueSuffix = sUniqueSuffix;
	this.tabColor = sColor;

	this.tabInactiveColor = sInactiveTabColor;
	this.tabWidth = sWidth + '';
	this.tabHeight = sHeight + '';

	HxIECreateStyleSheets(doc, sUniqueSuffix, sColor);
	this.goCurTab = null;

	this.tabList = new Array();

	this.bDeferBuildingTabs = true;
	this.sTabTableId = this.Id + "_tabtable";
	
	this.gsCurTab = null;
	this.afnNotify = null;

	this.addTab = HxTabControlAddTab;
	this.addTabEx = HxTabControlAddTabEx;
	this.addNotifyCB = HxTabControlAddNotifyCB;
	this.removeNotifyCB = HxTabControlRemoveNotifyCB;
	this.notify = HxTabControlNotify;
}

HxIETabControl.prototype.setDeferBuildingTabs = function(bYesNo) {
	this.bDeferBuildingTabs = bYesNo;
}

HxIETabControl.prototype.getFirstHeaderRow = function() {
	var s = '';
	var bNoTabWidthsSpecified = true;
	var sInactiveBgColor = this.tabInactiveColor ? ' bgcolor="'+this.tabInactiveColor+'"' : "";

	s += '<TR ID=' + this.Id + '_hrow1' + '>';
	s += '<TD WIDTH=2>&nbsp;</TD>';
	for ( var i=0; i< this.tabList.length; i++ )
	{
		s += '<TD' + sInactiveBgColor + ' WIDTH=2 ID=L_TagH_' + this.tabList[i].tabID + 
			 ' CLASS="LeftTab_' + this.sUniqueSuffix + '">&nbsp;</TD>';

		s += '<TD' + sInactiveBgColor + ' ' + (this.tabList[i].width ? 'WIDTH=' + this.tabList[i].width : 'NOWRAP') + 
			 ' ID=TagHead_' + this.tabList[i].tabID + 
			 ' _TABTYPE=tabHead CLASS=TabText_' + this.sUniqueSuffix + 
			 ' tabID=' + this.tabList[i].tabID +
			 '>&nbsp;' + this.tabList[i].tabName + '&nbsp;</TD>';
		s += '<TD WIDTH=2 ' + 'ID=R_TagH_' + this.tabList[i].tabID +
			 ' CLASS="RightTab_' + this.sUniqueSuffix + '">&nbsp;</TD>';
		if( this.tabList[i].width )
			bNoTabWidthsSpecified = false;

		s += '<TD WIDTH=1 CLASS="OnePixel">&nbsp;</TD>';
	}
	s += '<TD' + (bNoTabWidthsSpecified ? ' WIDTH="33%"' : '') + '>&nbsp</TD>';
	s += '</TR>';
	
	return s;
}

HxIETabControl.prototype.insertTabRows = function() {
	if (document && document.getElementById) {
		var oTable = document.getElementById(this.sTabTableId); 
		if (oTable && oTable.insertRow) {
			var bNoTabWidthsSpecified = true;
			var sInactiveBgColor = this.tabInactiveColor ? this.tabInactiveColor : "";
		
			// Insert two rows which function as the clickable tabs
			oRow1=oTable.insertRow(oTable.rows.length);
			oRow2=oTable.insertRow(oTable.rows.length);
			// Retrieve the rows collection for the table.
			aRows=oTable.rows;
			
			// Retrieve the cells collection for the first row.
			var aCells=oRow1.cells;
			var oCell =aRows(oRow1.rowIndex).insertCell(aCells.length);

			/* DO THE 1ST ROW - i.e. insert all the required cells */
			/* sample output */
			// 1	<TD WIDTH=2>&nbsp;</TD>
			// 2	<TD bgcolor="#d6d6b6" WIDTH=2 ID=L_TagH_Tab0 CLASS="LeftTab_hx_tabControl1">&nbsp;</TD>
			// 3	<TD bgcolor="#d6d6b6" NOWRAP ID=TagHead_Tab0 _TABTYPE=tabHead CLASS=TabText_hx_tabControl1 tabID=Tab0>&nbsp;Tab1&nbsp;</TD>
			// 4	<TD WIDTH=2 ID=R_TagH_Tab0 CLASS="RightTab_hx_tabControl1">&nbsp;</TD>
			// 5	<TD WIDTH=1 CLASS="OnePixel">&nbsp;</TD>
			// 6	<TD bgcolor="#d6d6b6" WIDTH=2 ID=L_TagH_Tab1 CLASS="LeftTab_hx_tabControl1">&nbsp;</TD>
			// 7	<TD bgcolor="#d6d6b6" NOWRAP ID=TagHead_Tab1 _TABTYPE=tabHead CLASS=TabText_hx_tabControl1 tabID=Tab1>&nbsp;Tab2&nbsp;</TD>
			// 8	<TD WIDTH=2 ID=R_TagH_Tab1 CLASS="RightTab_hx_tabControl1">&nbsp;</TD>
			// 9	<TD WIDTH=1 CLASS="OnePixel">&nbsp;</TD>
			// 10	<TD WIDTH="33%">&nbsp</TD>
			
			// '<TD WIDTH=2>&nbsp;</TD>';
			oCell.innerHTML = '&nbsp;';
			oCell.width = 2;

			for ( var i=0; i< this.tabList.length; i++ ) {
				// eg <TD bgcolor="#d6d6b6" WIDTH=2 ID=L_TagH_Tab0 CLASS="LeftTab_hx_tabControl1">&nbsp;</TD>
				oCell =aRows(oRow1.rowIndex).insertCell(aCells.length);
				oCell.innerHTML = '&nbsp;';
				oCell.width     = 2;
				oCell.bgColor   = sInactiveBgColor;
				oCell.id        = 'L_TagH_' + this.tabList[i].tabID;
				oCell.className = 'LeftTab_' + this.sUniqueSuffix;
			 
				 // eg <TD bgcolor="#d6d6b6" NOWRAP ID=TagHead_Tab0 _TABTYPE=tabHead CLASS=TabText_hx_tabControl1 tabID=Tab0>&nbsp;Tab1&nbsp;</TD>
				oCell =aRows(oRow1.rowIndex).insertCell(aCells.length);
				oCell.innerHTML = '&nbsp;' + this.tabList[i].tabName + '&nbsp;';
				if (this.tabList[i].width)
					oCell.width = this.tabList[i].width;
				else
					oCell.noWrap = true;
				oCell.bgColor   = sInactiveBgColor;
				oCell.id        = 'TagHead_' + this.tabList[i].tabID;
				oCell._TABTYPE  = 'tabHead';
				oCell.tabID     = this.tabList[i].tabID
				oCell.className = 'TabText_' + this.sUniqueSuffix;
			 
				// eg <TD WIDTH=2 ID=R_TagH_Tab1 CLASS="RightTab_hx_tabControl1">&nbsp;</TD>
 				oCell=aRows(oRow1.rowIndex).insertCell(aCells.length);
				oCell.innerHTML = '&nbsp;';
				oCell.width     = 2;
				oCell.id        = 'R_TagH_' + this.tabList[i].tabID;
				oCell.className = 'RightTab_' + this.sUniqueSuffix;
				if( this.tabList[i].width )
					bNoTabWidthsSpecified = false;

				//  eg '<TD WIDTH=1 CLASS="OnePixel">&nbsp;</TD>';
				oCell=aRows(oRow1.rowIndex).insertCell(aCells.length);
				oCell.innerHTML = '&nbsp;';
				oCell.width     = 1;
				oCell.className = 'OnePixel';
			}
			
			// <TD WIDTH="33%">&nbsp</TD>
			oCell=aRows(oRow1.rowIndex).insertCell(aCells.length);
			oCell.innerHTML = '&nbsp;';
			if (bNoTabWidthsSpecified)
				oCell.width     =  '33%'; //(bNoTabWidthsSpecified ? '"33%"' : '');
			
			/* DO THE 2nd ROW */
			/* Sample Output */
			// A <TD CLASS="WBorder_hx_tabControl1" HEIGHT=1></TD>
			// B <TD COLSPAN=3 ID="UTab0"></TD>
			// C <TD WIDTH=1 CLASS="WBorder_hx_tabControl1"></TD>
			// D <TD COLSPAN=3 ID="UTab1"CLASS="WBorder_hx_tabControl1"></TD>
			// E <TD WIDTH=1 CLASS="WBorder_hx_tabControl1"></TD>
			// F <TD CLASS="WBorder_hx_tabControl1"></TD>
			aCells=oRow2.cells;
			
			// <TD CLASS="WBorder_hx_tabControl1" HEIGHT=1></TD>
			var oCell=aRows(oRow2.rowIndex).insertCell(aCells.length);
			oCell.className = 'WBorder_' + this.sUniqueSuffix;
			oCell.height    = 1;
			
			for ( i=0; i< this.tabList.length; i++ ) {
				// <TD COLSPAN=3 ID="UTab0"></TD>
				oCell=aRows(oRow2.rowIndex).insertCell(aCells.length);
				oCell.colSpan   =  3;
				oCell.id        =  'U' + this.tabList[i].tabID;
				if ( i!= 0 ) 
					oCell.className =  'WBorder_' + this.sUniqueSuffix;
				
				// <TD WIDTH=1 CLASS="WBorder_hx_tabControl1"></TD>
				oCell=aRows(oRow2.rowIndex).insertCell(aCells.length);
				oCell.width     = 1;
				oCell.className = 'WBorder_' + this.sUniqueSuffix;
			}
		
			oCell=aRows(oRow2.rowIndex).insertCell(aCells.length);
			oCell.className = 'WBorder_' + this.sUniqueSuffix;
		} // *end* if (oTable && oTable.insertRow)
	} // *end* if (document && document.getElementById) 
}

HxIETabControl.prototype.getSecondHeaderRow = function() {
	var s = '';
	s += '<TR ID=' + this.Id + '_hrow2' + '>';
	s += '<TD CLASS="WBorder_' + this.sUniqueSuffix + '" HEIGHT=1></TD>';
	for ( var i=0; i< this.tabList.length; i++ )
	{
		s += '<TD COLSPAN=3 ID="U' + this.tabList[i].tabID + '"';
		if ( i!= 0 )
		{
			s += 'CLASS="WBorder_' + this.sUniqueSuffix + '"';
		}
		s += '></TD>';

		s += '<TD WIDTH=1 CLASS="WBorder_' + this.sUniqueSuffix + '"></TD>';
	}
	s += '<TD CLASS="WBorder_' + this.sUniqueSuffix + '"></TD>';
	s += '</TR>';
	return s;
}

HxIETabControl.prototype.getHTMLHeader = function()
{
	var s = '';
	var sTabDiv = '';
	var tabTableWidth = this.tabWidth;
	var sBgColor = this.tabColor ? ' bgcolor="'+this.tabColor+'"' : "";
	
	s += '<DIV ID="'+ this.Id + '">';

	s += '<TABLE BORDER=0 CELLSPACING=0 CELLPADDING=0 style="width:'+this.tabWidth+'" >';
	s += '<TR>';
	s += '<TD WIDTH=2>&nbsp;</TD>';
	s += '<TD>';
	s += '<TABLE WIDTH=' + tabTableWidth + ' HEIGHT=0 BORDER=0 CELLSPACING=0 CELLPADDING=0 ID=' + this.sTabTableId + '>';
	
	if (!(this.bDeferBuildingTabs)) { 
		s += this.getFirstHeaderRow();
		s += this.getSecondHeaderRow();
	}
	
	s += '</TABLE>';

	s += '</TD>';
	s += '<TD WIDTH=2>&nbsp;</TD>';
	s += '</TR>';
	s += '<TR>';
	s += '<TD WIDTH=2>&nbsp;</TD>';
	s += '<TD>';

	s+='<TABLE WIDTH ='+ tabTableWidth +' BORDER=0'+ sBgColor +' CELLSPACING=0 CELLPADDING=0 STYLE="border-style:outset;border-width:0px 1px 1px 1px;width=100%;'+'height:'+ this.tabHeight+'">';
	s+='<TR><TD HEIGHT=4></TD></TR>';
	s+='<TR VALIGN="top">';
	s += '<TD WIDTH=2>&nbsp;</TD>';

	return s;	
};

HxIETabControl.prototype.getHTMLBody = function()
{
	var s = '';
	s+='<TD>';

	for (var i=0; i< this.tabList.length; i++)
	{
		s += '<DIV CLASS="TabContents_'+this.sUniqueSuffix + '"ID=' + this.tabList[i].tabID+ ' style="width:100%;height:100%">';
		s += this.tabList[i].contentHTML;
		s += '</DIV>';
	}

	s += '</TD>';

	return s;
}

HxIETabControl.prototype.getHTMLFooter = function()
{
	var s = '';
	s += '<TD WIDTH=2>&nbsp;</TD>';
	s += '</TR></TABLE>';
	s +='</TD>';
	s += '<TD></TD>';
	s += '</TR>';

	s += '</TABLE>';

	s += '</DIV>';

	return s;
}
	
HxIETabControl.prototype.getHTML = function()
{
	var s = '';

	s += this.getHTMLHeader();
	s += this.getHTMLBody();
	s += this.getHTMLFooter();
	
	return s;
};

HxIETabControl.prototype.registerhandler = function()
{
	
	var aAll = document.all;
	for (var i=0; i< this.tabList.length; i++)
	{
		var el = aAll.item( 'TagHead_' + this.tabList[i].tabID );
		el.onclick = this.onClick;
		el.oTabControl = this;
	}
	
	if ( this.tabList.length > 0 )
		this.setTab(this.tabList[0].tabID);
		
	/* 
	 * MWD Jan 14, 2003
	 * This function needs to have a return value or we will get
	 * an "undefined" error on the HTML/JSP page anytime we call
	 * it using document.write()
	 *
	 */
	return "";
};


HxIETabControl.prototype.generateHTML = function()
{
	this.document.write(this.getHTML());
	this.registerhandler();
};

HxIETabControl.prototype.showHTML = function()
{
	alert(this.getHTML());
};

function HxIECreateStyleSheets(doc, sUniqueSuffix, sTabColor)
{
	var StyleInfo;

	sBackgroundColor = sTabColor ? 'background-color:'+sTabColor : 'visibility:visible';

	if (! doc.body.TabStyleSheet)
	{
		doc.body.TabStyleSheet = doc.createStyleSheet();
	}
	StyleInfo = doc.body.TabStyleSheet;

	StyleInfo.addRule( 'td.TabText_' + sUniqueSuffix,
					   'font-family:Arial,Helvetica,sans-serif; font-size:x-small; font-weight:normal; color:black; border-color:white; border-width:1px 0px 0px 0px; border-style:solid; text-align:center; cursor:hand; overflow:visible' );
	StyleInfo.addRule( '.LeftTab_' + sUniqueSuffix,
					   'background-image:url('+hx_Env.resources.getImgPath()+'LeftTab.GIF'+'); background-repeat:no-repeat; height:18px; width:2px; border-width:0px; cursor:hand; font-size:1pt;' );
	StyleInfo.addRule( '.RightTab_' + sUniqueSuffix,
					   'background-image:url('+hx_Env.resources.getImgPath()+'RightTab.GIF'+'); background-repeat:no-repeat; height:18px; width:4px; border-width:0px; cursor:hand; font-size:1pt;' );
	StyleInfo.addRule( '.NoTab_' + sUniqueSuffix, 'border-width:0px 0px 1px 0px; border-color:white; border-style:solid' );
	StyleInfo.addRule( 'WBorder_' + sUniqueSuffix, 'background-color:white' );
	StyleInfo.addRule( 'td.NoBorder_' + sUniqueSuffix, sBackgroundColor );
	StyleInfo.addRule( '.TabContents_' + sUniqueSuffix, 'display:none' );
	StyleInfo.addRule( '.OnePixel', 'font-size:1pt;' );
};

HxIETabControl.prototype.setTab = function(tabID)
{
	if (tabID == this.gsCurTab) return true;
	
	for ( var i=0; i < this.tabList.length; i++ )
	{
		if ( this.tabList[i].tabID == this.gsCurTab )
		{
			if(this.tabList[i].cbOutFunction)
			{

				if (false == this.tabList[i].cbOutFunction()) return false;
			}
			break;
		}
	}

	if( !this.notify( this.gsCurTab, D_TABCONTROL_NOTIFY_PREHIDEPANEL ) )
		return false;

	if( !this.notify( tabID, D_TABCONTROL_NOTIFY_PRESHOWPANEL ) )
		return false;
	
	for ( var i=0; i < this.tabList.length; i++ )
	{
		if ( this.tabList[i].tabID == tabID )
		{
			var el;
			var uEl;
			var aAll = document.all;
			if( this.gsCurTab )
			{
				el = aAll.item(this.gsCurTab);
				el.runtimeStyle.display = "none";
				uEl = aAll.item('U'+ this.gsCurTab);
				uEl.className = 'WBorder_' + this.sUniqueSuffix;
				if (this.tabInactiveColor){
					var oTab = aAll['L_TagH_' + this.gsCurTab];
					oTab.runtimeStyle.backgroundColor = this.tabInactiveColor;
					var oTab = aAll['TagHead_' + this.gsCurTab];
					oTab.runtimeStyle.backgroundColor = this.tabInactiveColor;
				}
			}
			el = aAll.item(tabID);
			el.runtimeStyle.display = "block";
			this.gsCurTab = tabID;
			uEl = aAll.item('U'+tabID);
			uEl.className = 'NoBorder_' + this.sUniqueSuffix;
			if (this.tabColor){
				var oTab = aAll['L_TagH_' + this.gsCurTab];
				oTab.runtimeStyle.backgroundColor = this.tabColor;
				var oTab = aAll['TagHead_' + this.gsCurTab];
				oTab.runtimeStyle.backgroundColor = this.tabColor;
			}

			if(this.tabList[i].cbFunction)
				this.tabList[i].cbFunction();

			this.notify( tabID, D_TABCONTROL_NOTIFY_POSTSHOWPANEL );


			return true;
		}
	}
	return false;
};

HxIETabControl.prototype.showTab = function(tabID, show)
{
	var s;

	if (show == false)
	{
		s = "none";
	}
	else
	{
		if (show == true)
			s = "block";
		else
			return false;
	}

	for ( var i=0; i < this.tabList.length; i++ )
	{
		if ( this.tabList[i].tabID == tabID )
		{
			var el;
			var aAll = document.all;

			el = aAll.item('L_TagH_' + this.tabList[i].tabID);
			el.runtimeStyle.display = s;
			el = aAll.item('TagHead_' + this.tabList[i].tabID);
			el.runtimeStyle.display = s;
			el = aAll.item('R_TagH_' + this.tabList[i].tabID);
			el.runtimeStyle.display = s;
			el = aAll.item('U' + this.tabList[i].tabID);
			el.runtimeStyle.display = s;
			return true;
		}
	}

	return false;
};

HxIETabControl.prototype.isCurrentTab = function(tabID)
{
	if(this.gsCurTab == tabID)
		return true;

	return false;
};

HxIETabControl.prototype.onClick =  function()
{
	var el = event.srcElement;
	while ( el && ( "undefined" == el._TABTYPE || "tabHead" != el._TABTYPE ) )
		el = el.parentElement;
	if( el )
	{
		// alert("onClick: " + el._TABTYPE + " " + el.tabID );
		el.oTabControl.setTab(el.tabID);
	}
};

/*
 * TabControl for Netscape Navigator 4 (version which supports layers)
 *
 */
function HxNN4TabControl( doc, sUniqueSuffix, sColor, sWidth, sHeight, sInactiveTabColor )
{
	this.document = doc;
	this.Id = "TabCtrl_"+sUniqueSuffix;
	this.sUniqueSuffix = sUniqueSuffix;
	this.tabColor = sColor;

	this.tabInactiveColor = sInactiveTabColor;
	this.tabWidth = sWidth + '';
	this.tabHeight = sHeight + '';

	this.tabList = new Array();

	this.gsCurTab = null;
	this.afnNotify = null;

	this.addNotifyCB = HxTabControlAddNotifyCB;
	this.removeNotifyCB = HxTabControlRemoveNotifyCB;
	this.notify = HxTabControlNotify;
}

HxNN4TabControl.prototype.generateStyleSheet = function()
{
	var s = HxNN4CreateStyleSheets(this.sUniqueSuffix, this.tabColor);
	this.document.write(s);
};

HxNN4TabControl.prototype.getHTML = function()
{
	var tabBodyWidth = this.tabWidth - 2;
	var tabBodyHeight = this.tabHeight - 2;
	var sBgColor = this.tabColor ? ' BGCOLOR="' + this.tabColor + '"' : "";
	var s = '';
	var oLayerDoc;

	var sTransparentImage = hx_Env.resources.getImgPath() + "transparent.gif";
	var sLeftTabImage = hx_Env.resources.getImgPath() + "LeftTab.gif";
	var sRightTabImage = hx_Env.resources.getImgPath() + "RightTab.gif";
	
	var oBodyLayer = new Layer(this.tabWidth);
	oBodyLayer.zIndex = 50;
	oBodyLayer.visibility = "hide";

	s += '<TABLE' + sBgColor + ' BORDER=0 CELLSPACING=0 CELLPADDING=0>';
	s += '<TR>';
	s += '<TD CLASS="WBorder_' + this.sUniqueSuffix + '" COLSPAN=3 HEIGHT=1><IMG SRC="' + sTransparentImage + '"></TD>';
	s += '</TR>';
	s += '<TR VALIGN="Top">';
	s += '<TD CLASS="WBorder_' + this.sUniqueSuffix + '" WIDTH=1><IMG SRC="' + sTransparentImage + '"></TD>';
	s += '<TD WIDTH=' + tabBodyWidth + ' HEIGHT=' + tabBodyHeight + '>&nbsp;</TD>';
	s += '<TD CLASS="BBorder_' + this.sUniqueSuffix + '" WIDTH=1><IMG SRC="' + sTransparentImage + '"></TD>';
	s += '</TR>';
	s += '<TR>';
	s += '<TD><IMG SRC="' + sTransparentImage + '"></TD>';
	s += '<TD CLASS="BBorder_' + this.sUniqueSuffix + '" COLSPAN=2 HEIGHT=1><IMG SRC="' + sTransparentImage + '"></TD>';
	s += '</TR>';
	s += '</TABLE>';

	oLayerDoc = oBodyLayer.document;
	oLayerDoc.open();
	oLayerDoc.write(s);
	oLayerDoc.close();

	for (var i = 0; i < this.tabList.length; i++)
	{
		this.tabList[i].tabLayer = new Layer(this.tabList[i].width + 2*2);
		this.tabList[i].tabLayer.zIndex = i;
		this.tabList[i].tabLayer.visibility = "hide";

		s = '';
		s += '<TABLE HEIGHT=' + 19 + ' BORDER=0 CELLSPACING=0 CELLPADDING=0><TR>';
		s += '<TD WIDTH=' + 2 + ' ROWSPAN=2><IMG SRC="' + sLeftTabImage + '"></TD>';
		s += '<TD CLASS="WBorder_' + this.sUniqueSuffix + '" HEIGHT=1><IMG SRC="' + sTransparentImage + '"></TD>';
		s += '<TD WIDTH=' + 2 + ' ROWSPAN=2><IMG SRC="' + sRightTabImage + '"></TD>';
		s += '</TR><TR>';
		s += '<TD WIDTH=' + this.tabList[i].width + ' CLASS="TabText_' + this.sUniqueSuffix + '">';
		s += '<A CLASS="TabText_' + this.sUniqueSuffix + '" HREF="javascript:' + this.sUniqueSuffix + ".setTab('" + this.tabList[i].tabID + "')" + '">' + this.tabList[i].tabName + '</A></TD>';
		s += '</TR></TABLE>';

		oLayerDoc = this.tabList[i].tabLayer.document;
		oLayerDoc.open();
		oLayerDoc.write(s);
		oLayerDoc.close();
	}

	for (var i = 0; i < this.tabList.length; i++)
	{
		this.tabList[i].tabContentLayer = new Layer(tabBodyWidth - 8);
		this.tabList[i].tabContentLayer.zIndex = 100 + i;
		this.tabList[i].tabContentLayer.visibility = "hide";

		s = '';
		s += this.tabList[i].contentHTML;
		oLayerDoc = this.tabList[i].tabContentLayer.document;
		oLayerDoc.open();
		oLayerDoc.write(s);
		oLayerDoc.close();
	}

	var oPos = this.document.anchors["TabPos"];
	var tabposx = oPos.x + 2;
	for (var i = 0; i < this.tabList.length; i++) {
		this.tabList[i].tabLayer.moveToAbsolute(tabposx, oPos.y);
		this.tabList[i].tabLayer.visibility = "show";
		tabposx += this.tabList[i].width + 2*2;
	}

	oBodyLayer.moveToAbsolute(oPos.x, oPos.y+18);
	oBodyLayer.visibility = "show";

	for (var i = 0; i < this.tabList.length; i++)
		this.tabList[i].tabContentLayer.moveToAbsolute(oPos.x+4, oPos.y+18+4);

	if ( this.tabList.length > 0 )
		this.setTab(this.tabList[0].tabID);
};

HxNN4TabControl.prototype.generateHTML = function()
{
	this.getHTML();
};

HxNN4TabControl.prototype.showHTML = function()
{
	alert(this.getHTML());
};

function HxNN4CreateStyleSheets(sUniqueSuffix, sTabColor)
{
	sBackgroundColor = sTabColor ? ' background-color: ' + sTabColor : '';

	var sStyle = '';
	sStyle += '<STYLE TYPE="text/css">\n';
	sStyle += 'a.TabText_' + sUniqueSuffix + '{ font-family: Arial, Helvetica, sans-serif; font-size: small; font-weight: normal; color: black; text-decoration: none; }\n';
	sStyle += 'td.TabText_' + sUniqueSuffix + '{ text-align: center;' + sBackgroundColor + ' }\n';
	sStyle += 'td.WBorder_' + sUniqueSuffix + '{ background-color: white; }\n';
	sStyle += 'td.BBorder_' + sUniqueSuffix + '{ background-color: black; }\n';
	sStyle += '</STYLE>\n';

	return sStyle;
};

HxNN4TabControl.prototype.addTab = function( tabName, tabID, contentHTML, tabWidth )
{
	var pos = this.tabList.length;

	this.tabList[pos] = new Object;
	this.tabList[pos].tabName = tabName;
	this.tabList[pos].tabID = tabID;
	this.tabList[pos].contentHTML = contentHTML;
	this.tabList[pos].width = (typeof tabWidth == "undefined" ? null : tabWidth );
	this.tabList[pos].cbFunction = null;
	this.tabList[pos].cbOutFunction = null;
};

HxNN4TabControl.prototype.addTabEx = function( tabName, tabID, contentHTML, tabWidth, cbFunction, cbOutFunction )
{
	var pos = this.tabList.length;

	this.addTab( tabName, tabID, contentHTML, tabWidth );
	this.tabList[pos].cbFunction = cbFunction;
	this.tabList[pos].cbOutFunction = (typeof cbOutFunction == "undefined" ? null : cbOutFunction);
};

HxNN4TabControl.prototype.setTab = function(tabID)
{
	if (tabID == this.gsCurTab) return true;

	for ( var i=0; i < this.tabList.length; i++ )
	{
		if ( this.tabList[i].tabID == this.gsCurTab )
		{
			if(this.tabList[i].cbOutFunction)
			{

				if (false == this.tabList[i].cbOutFunction()) return false;
			}
			break;
		}
	}

	if( !this.notify( this.gsCurTab, D_TABCONTROL_NOTIFY_PREHIDEPANEL ) )
		return false;

	if( !this.notify( tabID, D_TABCONTROL_NOTIFY_PRESHOWPANEL ) )
		return false;

	for ( var i=0; i < this.tabList.length; i++ )
	{
		if ( this.tabList[i].tabID == tabID )
		{

			if( this.goCurTab ) {
				this.goCurTab.tabLayer.zIndex -= 60;
				this.goCurTab.tabContentLayer.visibility = "hide";
			}

			this.tabList[i].tabLayer.zIndex += 60;
			this.tabList[i].tabContentLayer.visibility = "show";
			this.goCurTab = this.tabList[i];
			return;
		}
	}
	return false;
};

HxNN4TabControl.prototype.showTab = function(tabID, show)
{
	var s;

	if (show == false) {
		s = "hide";
	} else if (show == true) {
		s = "show";
	} else {
		return false;
	}

	for ( var i = 0; i < this.tabList.length; i++ ) {
		if ( this.tabList[i].tabID == tabID && this.tabList[i].tabLayer.visibility != s) {
			this.tabList[i].tabLayer.visibility = s;

			var pgx;
			var pgy;
			var offset = this.tabList[i].width + 2*2;
			for ( var j = i+1; j < this.tabList.length; j++ ) {
				pgx = this.tabList[j].tabLayer.pageX;
				pgy = this.tabList[j].tabLayer.pageY;

				if (s == "hide") {
					this.tabList[j].tabLayer.moveToAbsolute(pgx-offset, pgy);
				} else {
					this.tabList[j].tabLayer.moveToAbsolute(pgx+offset, pgy);
				}
			}

			return;
		}
	}

	return false;
};

HxNN4TabControl.prototype.isCurrentTab = function(tabID)
{
	var obj = this.goCurTab;
	if(obj && obj.tabID == tabID)
		return true;

	return false;
};

/*
 * Common methods for HxIETabControl and HxNN4TabControl
 *
 */
function HxTabControlAddTab( tabName, tabID, contentHTML, tabWidth )
{
	var pos = this.tabList.length;
	this.tabList[pos] = new Object;
	this.tabList[pos].tabName = tabName;
	this.tabList[pos].tabID = tabID;
	this.tabList[pos].contentHTML = contentHTML;
	this.tabList[pos].width = tabWidth;
	this.tabList[pos].cbFunction = null;
	this.tabList[pos].cbOutFunction = null;
};

function HxTabControlAddTabEx( tabName, tabID, contentHTML, tabWidth, cbFunction, cbOutFunction )
{
	var pos = this.tabList.length;
	this.addTab(tabName, tabID, (typeof contentHTML == "undefined" ? "" : contentHTML), (typeof tabWidth == "undefined" ? null : tabWidth ));
	this.tabList[pos].cbFunction = (typeof cbFunction == "undefined" ? null : cbFunction);
	this.tabList[pos].cbOutFunction = (typeof cbOutFunction == "undefined" ? null : cbOutFunction);

};

function HxTabControlAddNotifyCB(fnNotify)
{
	if( !this.afnNotify )
		this.afnNotify = new Array( fnNotify );
};

function HxTabControlRemoveNotifyCB(fnNotify)
{
	var i;
	if( this.afnNotify ) {
		for( i=0; i<this.afnNotify.length; i++ ) {
			if( fnNotify == this.afnNotify[i] )
				this.afnNotify[i] = null;
		}
	}
};

function HxTabControlNotify( sTabId, nNotification )
{
	var i;
	if( this.afnNotify ) {
		for( i=0; i<this.afnNotify.length; i++ ) {
			if( this.afnNotify[i] )
				if( false == this.afnNotify[i]( sTabId, nNotification ) )
					return false;
		}
	}
	return true;
};


/*
 * Date Picker Component
 *
 */

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// beginning of date/time settings

// Used for D_DateFmt_ShortMonth4Yr (%1 = 1 or 2 digit date)
D_DTFMT_SHORTMONTH4YR	="MMM %1, yyyy";

// Used for D_DateFmt_Medium (%1 = current date format)
// Used in date fields
D_DTFMT_MEDIUMDATE		="EEE %1";

// Used for D_DateFmt_FullWithTime	(%1 = current full date format, %2 = current time format)
// Used as postmark
D_DTFMT_FULLWITHTIME	="%1 %2";

// Used for D_DateFmt_DayDate (%1 = 1 or 2 digit date)
D_DTFMT_DAYDATE			="EEEE %1";

// Used in scenes to represent month and 4 digit year
D_DTFMT_MONTH4YR		="MMMM yyyy";

// Used for D_DurationFmt_Default
D_DURFMT_DEFAULT		="%1h %2m";

// Used for Week View for Group Calendar
D_DTFMT_WEEKDATE			="EEE d";

// Used for Fiveday, Week and Two Week View
D_DTFMT_MONTH			="MMMM";
D_DTFMT_YEAR			="yyyy";

/**
 * The below are the date/time preferences options.  They will be
 * displayed in the order listed.  For date and time formats, there is
 * a corresponding L_ constant which is what is actually displayed to
 * the user.  The COUNT provides a way to vary the number of entries
 * from localized version to version. The 0th entry will be considered
 * the default version.
 *
 */

D_DTFMT_DATE_COUNT		=9;
D_DTFMT_DATE0	="MM-dd-yyyy";
D_DTFMT_DATE1	="M-d-yyyy";
D_DTFMT_DATE2	="dd-MM-yyyy";
D_DTFMT_DATE3	="d-M-yyyy";
D_DTFMT_DATE4	="yyyy-MM-dd";
D_DTFMT_DATE5	="MM-dd-yy";
D_DTFMT_DATE6	="M-d-yy";
D_DTFMT_DATE7	="dd-MM-yy";
D_DTFMT_DATE8	="d-M-yy";
D_DTFMT_DATE9	="";
D_DTFMT_DATE10	="";
D_DTFMT_DATE11	="";

L_DTFMT_DATE0	="mm-dd-yyyy";
L_DTFMT_DATE1	="m-d-yyyy";
L_DTFMT_DATE2	="dd-mm-yyyy";
L_DTFMT_DATE3	="d-m-yyyy";
L_DTFMT_DATE4	="yyyy-mm-dd";
L_DTFMT_DATE5	="mm-dd-yy";
L_DTFMT_DATE6	="m-d-yy";
L_DTFMT_DATE7	="dd-mm-yy";
L_DTFMT_DATE8	="d-m-yy";
L_DTFMT_DATE9	="";
L_DTFMT_DATE10	="";
L_DTFMT_DATE11	="";

D_DTFMT_DATESEP_COUNT		=3;
D_DTFMT_DATESEP0	="/";
D_DTFMT_DATESEP1	="-";
D_DTFMT_DATESEP2	=".";
D_DTFMT_DATESEP3	="";

// Used for D_DateFmt_Full (long date formats)
// Used in cal views
D_DTFMT_FULLDATE_COUNT		=4;
D_DTFMT_FULLDATE0			="EEEE, MMMM dd, yyyy";
D_DTFMT_FULLDATE1			="MMMM dd, yyyy";
D_DTFMT_FULLDATE2			="EEEE, dd MMMM, yyyy";
D_DTFMT_FULLDATE3			="dd MMMM, yyyy";
D_DTFMT_FULLDATE4			="";
D_DTFMT_FULLDATE5			="";
D_DTFMT_FULLDATE6			="";
D_DTFMT_FULLDATE7			="";
D_DTFMT_FULLDATE8			="";
D_DTFMT_FULLDATE9			="";
D_DTFMT_FULLDATE10			="";
D_DTFMT_FULLDATE11			="";

L_DTFMT_FULLDATE0			="EEEE, MMMM dd, yyyy";
L_DTFMT_FULLDATE1			="MMMM dd, yyyy";
L_DTFMT_FULLDATE2			="EEEE, dd MMMM, yyyy";
L_DTFMT_FULLDATE3			="dd MMMM, yyyy";
L_DTFMT_FULLDATE4			="";
L_DTFMT_FULLDATE5			="";
L_DTFMT_FULLDATE6			="";
L_DTFMT_FULLDATE7			="";
L_DTFMT_FULLDATE8			="";
L_DTFMT_FULLDATE9			="";
L_DTFMT_FULLDATE10			="";
L_DTFMT_FULLDATE11			="";

D_DTFMT_TIME_COUNT		=6;
D_DTFMT_TIME0	="hh:mmt";
D_DTFMT_TIME1	="hh:mm t";
D_DTFMT_TIME2	="HH:mm";
D_DTFMT_TIME3	="H:mm";
D_DTFMT_TIME4	="h:mmt";
D_DTFMT_TIME5	="h:mm t";
D_DTFMT_TIME6	="";
D_DTFMT_TIME7	="";

L_DTFMT_TIME0	="12 hour(AM/PM), show hours as 2 digits";
L_DTFMT_TIME1	="12 hour (AM/PM), show hours as 2 digits";
L_DTFMT_TIME2	="24 hour, show hours as 2 digits";
L_DTFMT_TIME3	="24 hour";
L_DTFMT_TIME4	="12 hour(AM/PM)";
L_DTFMT_TIME5	="12 hour (AM/PM)";
L_DTFMT_TIME6	="";
L_DTFMT_TIME7	="";

D_DTFMT_TIMESEP_COUNT		=2;
D_DTFMT_TIMESEP0	=":";
D_DTFMT_TIMESEP1	="h";
D_DTFMT_TIMESEP2	="";
D_DTFMT_TIMESEP3	="";

L_AM_SUFFIX  ="AM";
L_PM_SUFFIX  ="PM";

// Non-Gregorian calendar support
// D_CUSTOMCALENDAR_n_BEGINNING has to be ordered.
// EXAMPLE:
// D_CUSTOMCALENDAR_COUNT 4
// D_CUSTOMCALENDAR_0_NAME		="Meiji";
// D_CUSTOMCALENDAR_0_NAMEABB	="Mei";
// D_CUSTOMCALENDAR_0_BEGIN		="18680908";
// D_CUSTOMCALENDAR_1_NAME		="Taisho";
// D_CUSTOMCALENDAR_1_NAMEABB	="Tai";
// D_CUSTOMCALENDAR_1_BEGIN		="19120731";
// D_CUSTOMCALENDAR_2_NAME		="Showa";
// D_CUSTOMCALENDAR_2_NAMEABB	="Sho";
// D_CUSTOMCALENDAR_2_BEGIN		="19261226";
// D_CUSTOMCALENDAR_3_NAME		="Heisei";
// D_CUSTOMCALENDAR_3_NAMEABB	="Hei";
// D_CUSTOMCALENDAR_3_BEGIN		="19890108";
// D_CUSTOMCALENDAR_4_NAME		="";
// D_CUSTOMCALENDAR_4_NAMEABB	="";
// D_CUSTOMCALENDAR_4_BEGIN		="";
// D_CUSTOMCALENDAR_5_NAME		="";
// D_CUSTOMCALENDAR_5_NAMEABB	="";
// D_CUSTOMCALENDAR_5_BEGIN		="";
// D_CUSTOMCALENDAR_6_NAME		="";
// D_CUSTOMCALENDAR_6_NAMEABB	="";
// D_CUSTOMCALENDAR_6_BEGIN		="";
// D_CUSTOMCALENDAR_7_NAME		="";
// D_CUSTOMCALENDAR_7_NAMEABB	="";
// D_CUSTOMCALENDAR_7_BEGIN		="";
// D_CUSTOMCALENDAR_8_NAME		="";
// D_CUSTOMCALENDAR_8_NAMEABB	="";
// D_CUSTOMCALENDAR_8_BEGIN		="";
// D_CUSTOMCALENDAR_9_NAME		="";
// D_CUSTOMCALENDAR_9_NAMEABB	="";
// D_CUSTOMCALENDAR_9_BEGIN		="";
// (EXAMPLE END)

D_CUSTOMCALENDAR_COUNT =0;
D_CUSTOMCALENDAR_0_NAME		="";
D_CUSTOMCALENDAR_0_NAMEABB	="";
D_CUSTOMCALENDAR_0_BEGIN	="";
D_CUSTOMCALENDAR_1_NAME		="";
D_CUSTOMCALENDAR_1_NAMEABB	="";
D_CUSTOMCALENDAR_1_BEGIN	="";
D_CUSTOMCALENDAR_2_NAME		="";
D_CUSTOMCALENDAR_2_NAMEABB	="";
D_CUSTOMCALENDAR_2_BEGIN	="";
D_CUSTOMCALENDAR_3_NAME		="";
D_CUSTOMCALENDAR_3_NAMEABB	="";
D_CUSTOMCALENDAR_3_BEGIN	="";
D_CUSTOMCALENDAR_4_NAME		="";
D_CUSTOMCALENDAR_4_NAMEABB	="";
D_CUSTOMCALENDAR_4_BEGIN	="";
D_CUSTOMCALENDAR_5_NAME		="";
D_CUSTOMCALENDAR_5_NAMEABB	="";
D_CUSTOMCALENDAR_5_BEGIN	="";
D_CUSTOMCALENDAR_6_NAME		="";
D_CUSTOMCALENDAR_6_NAMEABB	="";
D_CUSTOMCALENDAR_6_BEGIN	="";
D_CUSTOMCALENDAR_7_NAME		="";
D_CUSTOMCALENDAR_7_NAMEABB	="";
D_CUSTOMCALENDAR_7_BEGIN	="";
D_CUSTOMCALENDAR_8_NAME		="";
D_CUSTOMCALENDAR_8_NAMEABB	="";
D_CUSTOMCALENDAR_8_BEGIN	="";
D_CUSTOMCALENDAR_9_NAME		="";
D_CUSTOMCALENDAR_9_NAMEABB	="";
D_CUSTOMCALENDAR_9_BEGIN	="";

// end of date/time settings
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

// Default calendar view start days (0 = Sun, 1 = Mon, ..., 6 = Sat)
D_CAL_DEFAULTFIRSTDAYMONTH		=0;

// s_datetime.h
// Full month names
L_FULLMONTH_JAN		=0;//"January";
L_FULLMONTH_FEB		=1;//"February";
L_FULLMONTH_MAR		=2;//"March";
L_FULLMONTH_APR		=3;//"April";
L_FULLMONTH_MAY		=4;//"May";
L_FULLMONTH_JUN		=5;//"June";
L_FULLMONTH_JUL		=6;//"July";
L_FULLMONTH_AUG		=7;//"August";
L_FULLMONTH_SEP		=8;//"September";
L_FULLMONTH_OCT		=9;//"October";
L_FULLMONTH_NOV		=10//"November";
L_FULLMONTH_DEC		=11//"December";
// Short month names
L_SHORTMONTH_JAN	=12;//"Jan";
L_SHORTMONTH_FEB	=13;//"Feb";
L_SHORTMONTH_MAR	=14;//"Mar";
L_SHORTMONTH_APR	=15;//"Apr";
L_SHORTMONTH_MAY	=16;//"May";
L_SHORTMONTH_JUN	=17;//"Jun";
L_SHORTMONTH_JUL	=18;//"Jul";
L_SHORTMONTH_AUG	=19;//"Aug";
L_SHORTMONTH_SEP	=20;//"Sep";
L_SHORTMONTH_OCT	=21;//"Oct";
L_SHORTMONTH_NOV	=22;//"Nov";
L_SHORTMONTH_DEC	=23;//"Dec";
// Single character month names
L_CHARMONTH_JAN		=24;//"J";
L_CHARMONTH_FEB		=25;//"F";
L_CHARMONTH_MAR		=26;//"M";
L_CHARMONTH_APR		=27;//"A";
L_CHARMONTH_MAY		=28;//"M";
L_CHARMONTH_JUN		=29;//"J";
L_CHARMONTH_JUL		=30;//"J";
L_CHARMONTH_AUG		=31;//"A";
L_CHARMONTH_SEP		=32;//"S";
L_CHARMONTH_OCT		=33;//"O";
L_CHARMONTH_NOV		=34;//"N";
L_CHARMONTH_DEC		=35;//"D";
// Full day names
L_FULLDAY_SUN		=36;//"Sunday";
L_FULLDAY_MON		=37;//"Monday";
L_FULLDAY_TUE		=38;//"Tuesday";
L_FULLDAY_WED		=39;//"Wednesday";
L_FULLDAY_THU		=40;//"Thursday";
L_FULLDAY_FRI		=41;//"Friday";
L_FULLDAY_SAT		=42;//"Saturday";
// Short day names
L_SHORTDAY_SUN		=43;//"Sun";
L_SHORTDAY_MON		=44;//"Mon";
L_SHORTDAY_TUE		=45;//"Tue";
L_SHORTDAY_WED		=46;//"Wed";
L_SHORTDAY_THU		=47;//"Thu";
L_SHORTDAY_FRI		=48;//"Fri";
L_SHORTDAY_SAT		=49;//"Sat";
// Single character day names
L_CHARDAY_SUN		=50;//"S";
L_CHARDAY_MON		=51;//"M";
L_CHARDAY_TUE		=52;//"T";
L_CHARDAY_WED		=53;//"W";
L_CHARDAY_THU		=54;//"T";
L_CHARDAY_FRI		=55;//"F";
L_CHARDAY_SAT		=56;//"S";

// from s_datepick.h
// Alt tags for Next/Previous images
L_PREVIOUS_YEAR		=57;//"Previous year";
L_NEXT_YEAR			=58;//"Next year";
L_PREVIOUS_MONTH	=59;//"Previous month";
L_NEXT_MONTH		=60;//"Next month";
L_DN_TOGGLE			=61;//"Collapse/Expand Date Navigator";
L_DN_GOTOTODAYINCALENDAR	=62;//"Go to today in calendar";

// Date, Time Duration validation error strings
L_ERR_INVALIDDURATION			=63;//"Invalid duration specified.";
L_ERR_INVALIDDURATION_MINUTES	=64;//"Invalid duration specified.  Minutes must be between 00 and 59.";
L_ERR_INVALIDDURATION_SECONDS	=65;//"Invalid duration specified.  Seconds must be between 00 and 59.";

D_DateFmt_Short				=0;
D_DateFmt_Short4Yr			=1;
D_DateFmt_ShortMonth4Yr		=2;
D_DateFmt_Medium			=3;
D_DateFmt_Full				=4;
D_DateFmt_FullWithTime		=5;
D_DateFmt_DayDate			=6;
D_DateFmt_ShortWithTime		=7;

D_TimeFmt_NoAMPM			=0;
D_TimeFmt_Default			=1;
D_TimeFmt_Hour				=2;

D_DurationFmt_Default		=0;
D_DateHlpr_DatePicked 		=false;

// Date Format definitions used by renderer (from java.text.DateFormat)
D_JavaDateFormat_Full		= 0;
D_JavaDateFormat_Long		= 1;
D_JavaDateFormat_Medium		= 2;
D_JavaDateFormat_Short		= 3;
D_JavaDateFormat_Default	= D_JavaDateFormat_Medium;

//BAF Used for DateTimePrefs initialization
/**
 *
 * generate input format string based on display format string
 *
 */
function HxBuildInputFmt( sDispDateFmt )
{
	var i, sInputFmt="";
	var nCustYearPos = sDispDateFmt.indexOf("g");
	var nYearPos = sDispDateFmt.indexOf("y");
	var nMonthPos = sDispDateFmt.search(/M{1,2}/);
	var nDatePos = sDispDateFmt.search(/d{1,2}/);

	for( i=0; i<sDispDateFmt.length; i++ )
	{
		if( i==nCustYearPos )
			sInputFmt += "G";
		if( i==nYearPos )
			sInputFmt += "Y";
		if( i==nMonthPos )
			sInputFmt += "M";
		if( i==nDatePos )
			sInputFmt += "D";
	}

	return sInputFmt;
}

//BAF Used for DateTimePrefs initialization
/**
 * generate input format string based on display format string
 *
 */
function HxBuildDurInputFmt( sDispDurFmt ) {
	var sFmt = '', aSeps = new Array(), tab = 'hms', aTokens;
	var pat = '^\\s*%([123])([^\\s]*)\\s*(%([123])([^\\s]*))?\\s*'
			  + '(%([123])([^\\s]*))?\\s*$';
	var re = new RegExp( pat );

	var cb  = null;
	if (hx_Env.browser != null)
		cb = hx_Env.browser;
	else
		cb = new HxBrowserManager ();
	var NN4 = cb.isNetscape();

	if( aTokens = re.exec( sDispDurFmt ) ){
		if (NN4){
			aTokens = initTokensNN( aTokens );
		}

		for( var i = 1; i <= 7; i += 3 ) {
			if( aTokens[ i ] != '' ){
				var p = parseInt( aTokens[ i ], 10 ) - 1;
				sFmt += tab.substr( p, 1 );
			}
			if( aTokens[ i + 1 ] != '' )
				aSeps[ aSeps.length ] = aTokens[ i + 1 ];
		}
	}

	return { sFmt: sFmt, aSeps: aSeps };
}

function HxDateTimePreferences() {
	/**
	 * Various date/time format related variables
	 *
	 */
	this.sDateFormat = D_DTFMT_DATE0;
	this.sDateInputFormat = HxBuildInputFmt(this.sDateFormat);
	this.b2DigitDay = (-1 != this.sDateFormat.indexOf("dd"));
	this.b4DigitYear = (-1 != this.sDateFormat.indexOf("yyyy"));
	this.sDateSep = D_DTFMT_DATESEP0;
	this.sDateFormatLong = D_DTFMT_FULLDATE0;
	this.sTimeFormat = D_DTFMT_TIME0;
	this.b24Hour = (-1 != this.sTimeFormat.indexOf("H"));
	this.b2DigitHour = (-1 != this.sTimeFormat.indexOf( this.b24Hour ? "HH" : "hh" ));
	this.sTimeSep = D_DTFMT_TIMESEP0;
	this.sDurInputFmt = HxBuildDurInputFmt(D_DURFMT_DEFAULT); // would it be better obtaining default duration format from preference?
	this.nFirstDayMonth = D_CAL_DEFAULTFIRSTDAYMONTH;
	this.nJavaDateFormat = D_JavaDateFormat_Default;
}

HxDateTimePreferences.prototype.setDateFormat = function ( sDateFormat ) {
/**
 * Also computes sDateInputFormat, b2DigitDay and b4DigitYear variables
 *
 */
	if (sDateFormat == null || sDateFormat == "")
		this.sDateFormat = D_DTFMT_DATE0;
	else
		this.sDateFormat = sDateFormat;

	this.sDateInputFormat = HxBuildInputFmt(this.sDateFormat);
	this.b2DigitDay = (-1 != this.sDateFormat.indexOf("dd"));
	this.b4DigitYear = (-1 != this.sDateFormat.indexOf("yyyy"));
}

HxDateTimePreferences.prototype.setDateSeparator = function ( sDateSep ) {
	if (sDateSep == null || sDateSep == "")
		this.sDateSep = D_DTFMT_DATESEP0;
	else
		this.sDateSep = sDateSep;
}

HxDateTimePreferences.prototype.setDateFormatLong = function ( sDFL ) {
	if (sDFL == null || sDFL == "")
		this.sDateFormatLong = D_DTFMT_FULLDATE0;
	else
		this.sDateFormatLong = sDFL;
}

HxDateTimePreferences.prototype.setTimeFormat = function ( sTimeFormat ) {
/**
 * Also computes b24Hour and b2DigitHour member variables
 *
 */
	if (sTimeFormat == null || sTimeFormat == "")
		this.sTimeFormat = D_DTFMT_TIME0;
	else
		this.sTimeFormat = sTimeFormat;

	this.b24Hour = (-1 != this.sTimeFormat.indexOf("H"));
	this.b2DigitHour = (-1 != this.sTimeFormat.indexOf( this.b24Hour ? "HH" : "hh" ));
}

HxDateTimePreferences.prototype.setTimeSeparator = function ( sTimeSep ) {
	if (sTimeSep == null || sTimeSep == "")
		this.sTimeSep = D_DTFMT_TIMESEP0;
	else
		this.sTimeSep = sTimeSep;
}

HxDateTimePreferences.prototype.setDefaultDurationFormat = function ( sDDF ) {
	if (sDDF == null || sDDF == "")
		this.sDurInputFmt = HxBuildDurInputFmt(D_DURFMT_DEFAULT);
	else
		this.sDurInputFmt = HxBuildDurInputFmt(sDDF);
}

HxDateTimePreferences.prototype.setDefaultFirstDayMonth = function ( nDFDM ) {
	if (nDFDM == null || typeof(nDFDM) != 'number')
		this.nFirstDayMonth = D_CAL_DEFAULTFIRSTDAYMONTH;
	else
		this.nFirstDayMonth = nDFDM;
}
HxDateTimePreferences.prototype.setJavaDateFormat = function ( nJDF ) {
	if (nJDF == null || typeof(nJDF) != 'number')
		this.nJavaDateFormat = D_JavaDateFormat_Default;
	else
		this.nJavaDateFormat = nJDF;
}

HxDateTimePreferences.prototype.setAll = function (sDF, nJDF, sDS, sDFL, sTF, sTS, sDDF, nDFDM) {
	this.setDateFormat(sDF);
	this.setJavaDateFormat(nJDF);
	this.setDateSeparator(sDS);
	this.setDateFormatLong(sDFL);
	this.setTimeFormat(sTF);
	this.setTimeSeparator(sTS);
	this.setDefaultDurationFormat(sDDF);
	this.setDefaultFirstDayMonth(nDFDM);
}

HxDateTimePreferences.prototype.getDetails = function () {
	var s = "Date Format = " + this.sDateFormat + " \r";
	s += "Date Input Format = " + this.sDateInputFormat + " \r";
	s += "Two Digit Day? = " + this.b2DigitDay + " \r";
	s += "Four Digit Year? = " + this.b4DigitYear + " \r";
	s += "Date Separator = " + this.sDateSep + " \r";
	s += "Date Long Format = " + this.sDateFormatLong + " \r";
	s += "Time Format = " +	this.sTimeFormat + " \r";
	s += "Twenty Four Hour Day? = " + this.b24Hour + " \r" ;
	s += "Two Digit Hour? = " +	this.b2DigitHour + " \r" ;
	s += "Time Separator = " + this.sTimeSep + " \r";
	s += "Duration Format = " + this.sDurInputFmt + " \r";
	s += "Default 1st Day of Month = " + this.nFirstDayMonth;

	return s;
}

HxDateTimePreferences.prototype.dumpDetails = function () {
	alert (this.getDetails());
}

/**
 * Global calendar array declarations
 *
 */
var HxDpCustCalNames = null;
var HxDpCustCalNamesAbb = null;
var HxDpCustCalBeginDates = null;
var HxDpMonths = null;
var HxDpShortMonths = null;
var HxDpDaysInMonth = null;
var HxDpDays = null;
var HxDpShortDays = null;

// Initialize arrays.

function HxInitGlobalCustomCalendarNames() {
	HxDpCustCalNames = new Array(D_CUSTOMCALENDAR_0_NAME,
							   D_CUSTOMCALENDAR_1_NAME, D_CUSTOMCALENDAR_2_NAME,
							   D_CUSTOMCALENDAR_3_NAME, D_CUSTOMCALENDAR_4_NAME,
							   D_CUSTOMCALENDAR_5_NAME, D_CUSTOMCALENDAR_6_NAME,
							   D_CUSTOMCALENDAR_7_NAME, D_CUSTOMCALENDAR_8_NAME,
							   D_CUSTOMCALENDAR_9_NAME);		
}

function HxInitGlobalCustomCalendarNamesAbbrev() {
	HxDpCustCalNamesAbb = new Array(D_CUSTOMCALENDAR_0_NAMEABB,
								  D_CUSTOMCALENDAR_1_NAMEABB, D_CUSTOMCALENDAR_2_NAMEABB,
								  D_CUSTOMCALENDAR_3_NAMEABB, D_CUSTOMCALENDAR_4_NAMEABB,
								  D_CUSTOMCALENDAR_5_NAMEABB, D_CUSTOMCALENDAR_6_NAMEABB,
								  D_CUSTOMCALENDAR_7_NAMEABB, D_CUSTOMCALENDAR_8_NAMEABB,
								  D_CUSTOMCALENDAR_9_NAMEABB);
}

function HxInitGlobalCustomCalendarBeginDates() {
	HxDpCustCalBeginDates = new Array(D_CUSTOMCALENDAR_0_BEGIN,
									D_CUSTOMCALENDAR_1_BEGIN, D_CUSTOMCALENDAR_2_BEGIN,
									D_CUSTOMCALENDAR_3_BEGIN, D_CUSTOMCALENDAR_4_BEGIN,
									D_CUSTOMCALENDAR_5_BEGIN, D_CUSTOMCALENDAR_6_BEGIN,
									D_CUSTOMCALENDAR_7_BEGIN, D_CUSTOMCALENDAR_8_BEGIN,
									D_CUSTOMCALENDAR_9_BEGIN);
}

function HxInitGlobalMonths() {
	HxDpMonths = new Array(hx_Env.dtp_string[L_FULLMONTH_JAN], hx_Env.dtp_string[L_FULLMONTH_FEB], hx_Env.dtp_string[L_FULLMONTH_MAR],
						hx_Env.dtp_string[L_FULLMONTH_APR], hx_Env.dtp_string[L_FULLMONTH_MAY], hx_Env.dtp_string[L_FULLMONTH_JUN],
						hx_Env.dtp_string[L_FULLMONTH_JUL], hx_Env.dtp_string[L_FULLMONTH_AUG], hx_Env.dtp_string[L_FULLMONTH_SEP],
						hx_Env.dtp_string[L_FULLMONTH_OCT], hx_Env.dtp_string[L_FULLMONTH_NOV], hx_Env.dtp_string[L_FULLMONTH_DEC] );
}

function HxInitGlobalShortMonths() {
	HxDpShortMonths = new Array(hx_Env.dtp_string[L_SHORTMONTH_JAN], hx_Env.dtp_string[L_SHORTMONTH_FEB], hx_Env.dtp_string[L_SHORTMONTH_MAR],
							 hx_Env.dtp_string[L_SHORTMONTH_APR], hx_Env.dtp_string[L_SHORTMONTH_MAY], hx_Env.dtp_string[L_SHORTMONTH_JUN],
							 hx_Env.dtp_string[L_SHORTMONTH_JUL], hx_Env.dtp_string[L_SHORTMONTH_AUG], hx_Env.dtp_string[L_SHORTMONTH_SEP],
							 hx_Env.dtp_string[L_SHORTMONTH_OCT], hx_Env.dtp_string[L_SHORTMONTH_NOV], hx_Env.dtp_string[L_SHORTMONTH_DEC] );	
}

function HxInitGlobalDaysInMonth() {
	HxDpDaysInMonth = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
}

function HxInitGlobalDays() {
	HxDpDays = new Array(hx_Env.dtp_string[L_FULLDAY_SUN], hx_Env.dtp_string[L_FULLDAY_MON], hx_Env.dtp_string[L_FULLDAY_TUE],
					  hx_Env.dtp_string[L_FULLDAY_WED], hx_Env.dtp_string[L_FULLDAY_THU], hx_Env.dtp_string[L_FULLDAY_FRI],
					  hx_Env.dtp_string[L_FULLDAY_SAT] );
}

function HxInitGlobalShortDays() {
	HxDpShortDays = new Array(hx_Env.dtp_string[L_SHORTDAY_SUN], hx_Env.dtp_string[L_SHORTDAY_MON], hx_Env.dtp_string[L_SHORTDAY_TUE],
						   hx_Env.dtp_string[L_SHORTDAY_WED], hx_Env.dtp_string[L_SHORTDAY_THU], hx_Env.dtp_string[L_SHORTDAY_FRI],
						   hx_Env.dtp_string[L_SHORTDAY_SAT] );
}

/**
 * Init all the calendar arrays and resources in one fell swoop.
 *
 */
function HxInitGlobalCalendarArrays() {
	// Need to have our string resources defined by the time we get here!
	// If not, assign the local EN-US fallback version - MWD
	if (!hx_Env.dtp_string.length)
		hx_Env.initResource(Hx_DP_COMPONENT);
		
	HxInitGlobalCustomCalendarNames();
	HxInitGlobalCustomCalendarNamesAbbrev();
	HxInitGlobalCustomCalendarBeginDates();
	HxInitGlobalMonths();
	HxInitGlobalShortMonths();
	HxInitGlobalDaysInMonth();
	HxInitGlobalDays();
	HxInitGlobalShortDays();
}

function HxGetDays(month, year) {
	   // Test for leap year when February is selected.
	if (1 == month)
		return ((0 == year % 4) && (0 != (year % 100))) ||
				(0 == year % 400) ? 29 : 28;
	else
		return HxDpDaysInMonth[month];
}

function HxAdjustDate( oDateTime, iYearDif, iMonthDif, iDayDif, iHourDif, iMinuteDif, iSecondDif )
{
	var msDif = 0;
	var iNewMonth = 0;
	var bResetTime = false;

	// If this is an attempt to simply adjust the day or month, make
	// sure the hour, minute and seconds are left as they were.
	var iOrigHour, iOrigMinute, iOrigSecond;
	if( !iHourDif && !iMinuteDif && !iSecondDif && (iDayDif || iMonthDif) ) {
		iOrigHour = oDateTime.getHours();
		iOrigMinute = oDateTime.getMinutes();
		iOrigSecond = oDateTime.getSeconds();
		bResetTime = true;
		// We set the hours to 4AM or 8PM to try to avoid an off by day
		// problem during DST shifts. 
		oDateTime.setHours(oDateTime.getTimezoneOffset < 0 ? 4 : 20);
	}

	if( iSecondDif )
		msDif += iSecondDif * 1000;

	if( iMinuteDif )
		msDif += iMinuteDif * 60000;

	if( iHourDif )
		msDif += iHourDif * 3600000;

	if( iDayDif )
		msDif += iDayDif * 86400000;

	if( msDif )
		oDateTime.setTime( oDateTime.getTime() + msDif );

	if( iMonthDif )
	{
		var iYearAdj = parseInt( iMonthDif / 12 );
		iMonthDif = iMonthDif % 12;
		iNewMonth = oDateTime.getUTCMonth() + iMonthDif;

		if( iNewMonth < 0 )
		{
			iYearAdj--;
			iNewMonth += 12;
		}
		else if( iNewMonth > 11 )
		{
			iYearAdj++;
			iNewMonth -= 12;
		}

		iYearDif += iYearAdj;
	}

	if( iYearDif )
		oDateTime.setUTCFullYear( oDateTime.getUTCFullYear() + iYearDif );

	if( iMonthDif )
	{
		var iDays = HxGetDays( iNewMonth, oDateTime.getUTCFullYear() );
		if( oDateTime.getUTCDate() > iDays )
			oDateTime.setUTCDate( iDays );
		oDateTime.setUTCMonth(iNewMonth);
	}

	if( bResetTime ) {
		oDateTime.setHours(iOrigHour);
		oDateTime.setMinutes(iOrigMinute);
		oDateTime.setSeconds(iOrigSecond);
	}
}

//BAF In use
/**
 * Returns 0 if dates are equal, 1 if this date object > oDate,
 * -1 if this date object < oDate
 *
 */
Date.prototype.compareDate = function( oDate, bCompareTime )
{
	var	iDiff;

	iDiff = this.getFullYear() - oDate.getFullYear();

	if( iDiff == 0 )
		iDiff = this.getMonth() - oDate.getMonth();

	if( iDiff == 0 )
		iDiff = this.getDate() - oDate.getDate();

	if( bCompareTime && iDiff == 0 ) {
		iDiff = this.getHours() - oDate.getHours();
		if( iDiff == 0 )
			iDiff = this.getMinutes() - oDate.getMinutes();
		if( iDiff == 0 )
			iDiff = this.getSeconds() - oDate.getSeconds();
	}

	if( iDiff < 0 )
		iDiff = -1;
	else if ( iDiff > 0 )
		iDiff = 1;

	return iDiff;
};

//BAF In use
/**
 * Emits HTML representing a calendar grid and allows the caller to
 * provide content for each cell by specifying a function as the gridCB
 * argument
 *
 */
function HxDrawGrid( oDate, fShowOtherDays, gridCB, param )
{
	var tempDate = new Date(oDate.getFullYear(), oDate.getMonth(), 1, 12, 0, 0);

	var iDaysBefore = tempDate.getDay() - param.dtp.nFirstDayMonth;
	if( iDaysBefore < 0 )
		iDaysBefore += 7;
	var iDaysInAndBefore = HxGetDays(tempDate.getMonth(), tempDate.getFullYear()) + iDaysBefore;
	var iNoOfRows = parseInt(iDaysInAndBefore / 7);

	if( iDaysInAndBefore > iNoOfRows * 7 )
		iNoOfRows++;

	if( iDaysBefore )
		HxAdjustDate( tempDate, 0, 0, -iDaysBefore, 0, 0, 0 );

	for( y=0; y<iNoOfRows; y++ )
	{
		for( x=0; x<7; x++, HxAdjustDate(tempDate, 0, 0, 1, 0, 0, 0 ) )
		{
			if( !gridCB( tempDate, y, x, iNoOfRows, param ) )
				return false;
		}
	}

	return true;
}

//BAF In use
/*
 * returns string in YYYYMMDD format (assumes oDate is local date)
 * 
 */
function HxToLocalDateString( oDate )
{
	var s = new String("");
	var m = oDate.getMonth() + 1;
	var sMonth = '' + m;
	var sDate = '' + oDate.getDate();

	if( sMonth.length < 2 )
		sMonth = '0' + sMonth;
	if( sDate.length < 2 )
		sDate = '0' + sDate;

	s += oDate.getFullYear() + sMonth + sDate;

	return s;
}

//BAF In use
/**
 * returns JS Date object
 *
 */
function HxToDate( sDate, fLocal, fIgnoreOffset ) {

	if( typeof( fIgnoreOffset ) == 'undefined' )
		fIgnoreOffset = false;

	var pat = '^(\\d{4})[\\-\\/\\.]?(\\d{1,2})[\\-\\/\\.]?(\\d{1,2})';
	pat += '(T?(\\d{1,2})([\\-:\\.]?(\\d{1,2})([\\-:\\.]?(\\d{1,2})';
	pat += '(\\,(\\d{1,3}))?)?)?)?';
	pat += '((Z)|([\\+\\-])(\\d{1,2})([\\-:\\.]?(\\d{1,2})';
	pat += '([\\-:\\.]?(\\d{1,2}))?)?';
	pat += '(D([01N]))?)?$';

	var re = new RegExp( pat );
	var ary = re.exec( sDate );
	var cb  = null;
	if (hx_Env.browser != null)
		cb = hx_Env.browser;
	else
		cb = new HxBrowserManager ();
	var NN4 = cb.isNetscape();
	
	if (ary == null) {
		return null;
	}

	if (NN4){
		ary = initTokensNN( ary );
	}

	if( typeof( fLocal ) == 'undefined' )
		fLocal = ( ary[13] != 'Z' );

	var year = parseInt( ary[ 1 ], 10 );
	var month = parseInt( ary[ 2 ], 10 );
	var day = parseInt( ary[ 3 ], 10 );
	var hours = 0; var minutes = 0; var seconds = 0;
	
	// BAF !='' should be replaced with !=null for Navigator
	if(ary[ 5 ] && ary[ 5 ] != '' ) hours = parseInt( ary[ 5 ], 10 );
	if(ary[ 7 ] && ary[ 7 ] != '' ) minutes = parseInt( ary[ 7 ], 10 );
	if(ary[ 9 ] && ary[ 9 ] != '' ) seconds = parseInt( ary[ 9 ], 10 );

	var milliSeconds = 0;
	if(ary[ 11 ] && ary[ 11 ] != '' ){
		milliSeconds = parseInt( ary[ 11 ], 10 )
					   * Math.pow( 10, 3 - ary[ 11 ].length );
	}

	var offset = '';
	if( !fLocal )
		offset = 0;
	else if( !fIgnoreOffset && ary[ 14 ] && ary[ 14 ] != '' && ary[ 15 ] && ary[ 15 ] != '' ) {
		if( ary[ 17 ] && ary[ 17 ] == '' ) ary[ 17 ] = 0;
		if( ary[ 19 ] && ary[ 19 ] == '' ) ary[ 19 ] = 0;

		offset = ( ( ary[ 14 ] && ary[ 14 ] == '+' )? 1: -1 )
				 * ( parseInt( ary[ 15 ], 10 ) * 60 * 60
					 + parseInt( ary[ 17 ], 10 ) * 60
					 + parseInt( ary[ 19 ], 10 ) );

		if( ary[ 21 ] == '1' )
			offset += 3600;

	}

	if( typeof( offset ) == 'number' ) {
		var oDate = new Date( Date.UTC( year, month - 1, day,
										hours, minutes, seconds, milliSeconds ) );

		oDate.setTime( oDate.getTime() - offset * 1000 );
	}
	else
		var oDate = new Date( year, month - 1, day,
							  hours, minutes, seconds, milliSeconds );

	return oDate;
}

//BAF In use
/**
 * get a string representation of a number filling zero to a certain
 * point
 *
 */
function HxFillZero( theNumber, toFill ){
	var s = theNumber.toString();
	var n = toFill - s.length;

	for( var i = 0; i < n; i++ )
		s = '0' + s;

	return s;
}

//BAF In use
/**
 * return Day of week string
 *
 */
Date.prototype.getDayLabel = function()
{
	return HxDpDays[this.getDay()];
};

//BAF In use
/**
 * return Month label
 *
 */
Date.prototype.getMonthLabel = function()
{
	return HxDpMonths[this.getMonth()];
};

//BAF In use
Date.prototype.toTimeDisplay = function( iFormat, oDTP )
{
	if( arguments.length == 0 ) {
		iFormat = D_TimeFmt_Default;
		oDTP = new HxDateTimePreferences();
	}
	else if ( arguments.length == 1 ) {
		if (typeof(iFormat) == 'number')
			oDTP = new HxDateTimePreferences();
		if (typeof(iFormat) == 'object')
			iFormat = D_TimeFmt_Default;
	}

	if (iFormat == null || typeof(iFormat) != 'number')
		iFormat = D_TimeFmt_Default;
	if (oDTP == null || typeof(oDTP) != 'object')
		oDTP = new HxDateTimePreferences();

	var sFormat;
	var nDelimOffset;
	
	switch( iFormat )
	{
		case D_TimeFmt_NoAMPM:
			sFormat = oDTP.sTimeFormat.replace(/\s*t/,"");
			break;

		case D_TimeFmt_Hour:
			sFormat = oDTP.b24Hour ? "H" : "h";
			if( oDTP.b2DigitHour )
				sFormat += sFormat;
			break;

		case D_TimeFmt_Default:
		default:
			sFormat = oDTP.sTimeFormat;
			break;

	}

	return this.toFormatString(sFormat, oDTP);
};

//BAF In use
/**
 * Given a UTC date string in YYYYMMDD or YYYYMMDDHHSS format, return
 * local time string to display on screen
 *
 */
function HxToTimeDisplay( sWPDate, iFormat )
// iFormat values:
// 0 : no AM/PM
// 1 : AM/PM with no spaces between time
// 2 : AM/PM with one space between time
{
	if( sWPDate.length > 8 )
	{
		var oDate = HxToDate( sWPDate, false );
		if( arguments.length < 2 )
			var iFormat = D_TimeFmt_Default;
		return oDate.toTimeDisplay( iFormat );
	}
	else
		return new String("");
}

//BAF In use
Date.prototype.toDateDisplay = function( iFormat, oDTP )
{
	if ( arguments.length == 0 ) {
		iFormat = D_DateFmt_Medium;
		oDTP = new HxDateTimePreferences();
	}
	else if ( arguments.length == 1 ) {
		if (typeof(iFormat) == 'number')
			oDTP = new HxDateTimePreferences();
		if (typeof(iFormat) == 'object')
			iFormat = D_DateFmt_Medium;
	}

	if (iFormat == null || typeof(iFormat) != 'number')
		iFormat = D_DateFmt_Medium;
	if (oDTP == null || typeof(oDTP) != 'object')
		oDTP = new HxDateTimePreferences();
	
	var sFormat;

	switch(iFormat)
	{
		case D_DateFmt_Short:
			sFormat = oDTP.sDateFormat;
			break;

		case D_DateFmt_Short4Yr:
			sFormat = oDTP.sDateFormat;
			if( !oDTP.b4DigitYear )
				sFormat = sFormat.replace(/yy/, "yyyy");
			break;

		case D_DateFmt_ShortMonth4Yr:
			sFormat = HxFormatMessage(D_DTFMT_SHORTMONTH4YR, oDTP.b2DigitDay?"dd":"d");
			break;

		case D_DateFmt_Full:
			sFormat = oDTP.sDateFormatLong;
			break;

		case D_DateFmt_ShortWithTime:
			sFormat = HxFormatMessage(D_DTFMT_FULLWITHTIME, oDTP.sDateFormat, oDTP.sTimeFormat);
			break;

		case D_DateFmt_FullWithTime:
			sFormat = HxFormatMessage(D_DTFMT_FULLWITHTIME, oDTP.sDateFormatLong, oDTP.sTimeFormat);
			break;

		case D_DateFmt_DayDate:
			sFormat = HxFormatMessage(D_DTFMT_DAYDATE, oDTP.b2DigitDay?"dd":"d");
			break;

		case D_DateFmt_Medium:
		default:
			sFormat = HxFormatMessage(D_DTFMT_MEDIUMDATE, oDTP.sDateFormat);
			break;
	}

	return this.toFormatString(sFormat, oDTP);
};

Date.prototype.toDateDisplayEx = function( oDTP )
{
	// must have a DateTimePreferences object to work with!
	if (oDTP == null || typeof(oDTP) != 'object')
		oDTP = new HxDateTimePreferences();

	var sFormat;
	
	switch(oDTP.nJavaDateFormat)
	{
		case D_JavaDateFormat_Short:
			sFormat = oDTP.sDateFormat;
			if( !oDTP.b4DigitYear )
				sFormat = sFormat.replace(/yy/, "yyyy");
			break;

		case D_JavaDateFormat_Full:
			sFormat = oDTP.sDateFormatLong;
			break;
			
		case D_JavaDateFormat_Medium:
		case D_JavaDateFormat_Long:
		case D_JavaDateFormat_Default:
		default:
			sFormat = oDTP.sDateFormat;
			break;			
	}

	return this.toFormatString(sFormat, oDTP);
};

//BAF In use
Date.prototype.toFormatString = function (fmt, oDTP)
	//		Format strings can have sequences of the following values.
	//
	//			GGGG	-	Non-Gregorian year symbol as a full string, eg Heisei in Chinese character
	//			GGG		-	Non-Gregorian year symbol as an abreviated string, eg Hei in Chinese character
	//			gg		-	Non-Gregorian year as a zero lead number, eg 01, 12
	//			g		-	Non-Gregorian as a non-zero lead number. eg 1, 12
	//
	//			yy		-	Year as two digit string, eg 93 vs 1993
	//			yyyy	-	Year as a four digit string, eg 1993 vs 93
	//
	//			MMMM	-	Month as a full string, eg January
	//			MMM		-	Month as an abreviated string, eg Jan
	//			MM		-	Month as a zero lead number, eg 01, 12
	//			M		-	Month as a non-zero lead number. eg 1, 12
	//
	//			EEEE	-	Day of Week as full string, eg Wednesday
	//			EEE	-	Day of Week as abbreviated string, eg Wed
	//			dd		-	Day of Month as zero lead number, eg 01, 12, 31
	//			d		-	Day of Month as non-zero lead number, eg 1, 2, 12, 31
	//
	//			o		-	Ordinal of day of month, eg st, nd, rd, th
	//
	//			hh  	-	Hour in 12 hour clock with leading zero, eg 18 is 06, 0 is 12
	//			h  		-	Hour in 12 hour clock without leading zero, eg 18 is 6, 0 is 12
	//			HH		-	Hour in 24 hour clock with leading zero, eg 18 is 18, 6 is 06, 0 is 00
	//			H		-	Hour in 24 hour clock without leading zero, eg 18 is 18, 6 is 6, 0 is 0
	//
	//			mm		-	Minutes with leading zeroes, eg 08
	//			m		-   Minutes without leading zeroes, eg 8
	//
	//			ss		-	Seconds with leading zero, eg 08
	//			s		-
	//
	//			t		-   AM/PM suffix
	//
	//			-		-	Date separator. Collected from Profile
	//			:		-	Time separator. Collected from Profile
	//
	//			Other characters are inserted into output buffer verbatim. Here are some sample strings,
	//			and their outputs given a date and time of 25/01/93 18:27:36
	//
	//			EEEE, ddo mmmm yy hh:mm:ss a	- 	Monday, 25th January 93 18:27:36
	//			hh.mm.ss						-	18.27.36
	//			yy/mm/dd						-	93/01/25
	//
{
	var h = this.getHours();
	var m = this.getMinutes();
	var s = this.getSeconds();

	if (oDTP == null || typeof(oDTP) != 'object')
		oDTP = new HxDateTimePreferences();

	// do not change the order of following replacements
	fmt = fmt.replace(/MMMM/g, "@1");
	fmt = fmt.replace(/MMM/g, "@2");
	fmt = fmt.replace(/EEEE/g, "@3");
	fmt = fmt.replace(/EEE/g, "@4");

	fmt = fmt.replace(/GGGG/g, this.getNonGregorianYear().name);
	fmt = fmt.replace(/GGG/g, this.getNonGregorianYear().nameabb);
	fmt = fmt.replace(/gg/g, HxFillZero(this.getNonGregorianYear().year, 2));
	fmt = fmt.replace(/g/g, this.getNonGregorianYear().year);
	fmt = fmt.replace(/yyyy/g, this.getFullYear());
	fmt = fmt.replace(/yy/g, HxFillZero(this.getFullYear() % 100, 2));
	fmt = fmt.replace(/MM/g, HxFillZero(this.getMonth() + 1, 2));
	fmt = fmt.replace(/M/g, this.getMonth() + 1);
	fmt = fmt.replace(/dd/g, HxFillZero(this.getDate(), 2));
	fmt = fmt.replace(/d/g, this.getDate());
	// not yet implemented
	//		fmt = fmt.replace(/o/g, this.getOrdinalDay());
	// should correct to convert "0" to "12"
	fmt = fmt.replace(/hh/g, h % 12 == 0 ? 12 : HxFillZero(h % 12, 2));
//#if 1
//IMADE
	fmt = fmt.replace(/h/g, h % 12 == 0 ? 12 : (h % 12));
//#else
	fmt = fmt.replace(/h/g, h % 12);
//#endif
	fmt = fmt.replace(/HH/g, HxFillZero(h, 2));
	fmt = fmt.replace(/H/g, h);
	fmt = fmt.replace(/mm/g, HxFillZero(m, 2));
	fmt = fmt.replace(/m/g, m);
	fmt = fmt.replace(/ss/g, HxFillZero(s, 2));
	fmt = fmt.replace(/s/g, this.getSeconds());
	fmt = fmt.replace(/t/g, this.ampmSuffix());
	fmt = fmt.replace(/-/g, oDTP.sDateSep);
	fmt = fmt.replace(/:/g, oDTP.sTimeSep);
	fmt = fmt.replace(/@1/g, this.getMonthLabel());
	fmt = fmt.replace(/@2/g, this.getShortMonthLabel());
	fmt = fmt.replace(/@3/g, this.getDayLabel());
	fmt = fmt.replace(/@4/g, this.getShortDayLabel());

	return fmt;
};

//BAF In use
Date.prototype.getNonGregorianYear = function ()
{
	for (var i = D_CUSTOMCALENDAR_COUNT - 1; i >= 0; i--) {
		var oDate;
		if ((oDate = HxToDate(HxDpCustCalBeginDates[i])) && this >= oDate)
			return {
					name: HxDpCustCalNames[i],
					nameabb: HxDpCustCalNamesAbb[i],
					year: this.getFullYear() - oDate.getFullYear() + 1
			};
		ASSERT(oDate);
	}

	return { name: '', nameabb: '', year: this.getFullYear() };
};

//BAF In use
Date.prototype.ampmSuffix = function ()
{
	if (this.getHours() < 12)
		return L_AM_SUFFIX;
	else
		return L_PM_SUFFIX;
	return s;
};

//BAF In use
/*
 * return Day of week string
 *
 */
Date.prototype.getShortDayLabel = function()
{
	return HxDpShortDays[this.getDay()];
};

//BAF In use
// return Month label
Date.prototype.getShortMonthLabel = function()
{
	return HxDpShortMonths[this.getMonth()];
};


// Worldwide timezone support - Akira Sudoh@Japan 12/07/2000
function HxValidateDurExt( sDur, oDTP ) {
	// validates input duration returns the duration (-1 for failure)
	// Parameters:
	//		sDur :			Duration string to be validated
	if( !sDur )	{
		alert( hx_Env.dtp_string[L_ERR_INVALIDDURATION] );
		return -1;
	}

	if ( !oDTP )
		oDTP = new HxDateTimePreferences();
	
	var h = 0, m = 0, s = 0; var re, aParts, pat;
	var sHours = '', sMinutes = '', sSeconds = '';

	pat = '^\\s*(\\d{1,})\\s*$';
	re = new RegExp( pat ); aParts = re.exec( sDur );
	if( aParts ) return parseInt( aParts[ 1 ] ) * 60 * 1000;

	if( !aParts && oDTP.sDurInputFmt.aSeps.length > 0 ) {
		pat = '^\\s*((\\d{1,})' + oDTP.sDurInputFmt.aSeps[ 0 ];
		pat += ')?\\s*';
		if( oDTP.sDurInputFmt.aSeps.length > 1 ) {
			pat += '((\\d{1,})' + oDTP.sDurInputFmt.aSeps[ 1 ];
			pat += ')?\\s*';
		}

		if( oDTP.sDurInputFmt.aSeps.length > 2 ) {
			pat += '((\\d{1,})' + oDTP.sDurInputFmt.aSeps[ 2 ];
			pat += ')?\\s*';
		}

		pat += '$';
		re = new RegExp( pat, 'i' ); aParts = re.exec( sDur );
		if( aParts = re.exec( sDur ) ){
			if (NN4){
				aParts = initTokensNN( aParts );
			} // NN4
			for( var i = 2, j = 0;
				 i < aParts.length && i <= 6;
				 i += 2, j++ )
				switch( oDTP.sDurInputFmt.sFmt.substr( j, 1 ) ) {
					case 'h':
						sHours = aParts[ i ];
						break;
					case 'm':
						sMinutes = aParts[ i ];
						break;
					case 's':
						sSeconds = aParts[ i ];
						break;
				}
		}	
	}

	if( !aParts ){
		pat = '^\\s*(\\d{0,})?\\s*(([\\:\\.]|\\s+)\\s*(\\d{0,}))?';
		pat += '\\s*(([\\:\\.]|\\s+)\\s*(\\d{0,}))?\\s*$';
		re = new RegExp( pat );

		if( aParts = re.exec( sDur ) ){
			if (NN4){
				aParts = initTokensNN( aParts );
			} // NN4

			sHours = aParts[ 1 ];
			sMinutes = aParts[ 4 ];
			sSeconds = aParts [ 7 ];
		}
	}

	if( !aParts ){
		alert( hx_Env.dtp_string[L_ERR_INVALIDDURATION] ); return -1;
	}

	if( !sHours.length && !sMinutes.length
		  && !sSeconds.length ) {
		alert( hx_Env.dtp_string[L_ERR_INVALIDDURATION] ); return -1;
	}

	h = parseInt( sHours.length? sHours: '0', 10 );
	m = parseInt( sMinutes.length? sMinutes: '0', 10 );

	if( m > 59 ){
		alert( hx_Env.dtp_string[L_ERR_INVALIDDURATION_MINUTES] );
		return -1;
	}

	s = parseInt( sSeconds.length? sSeconds: '0', 10 );

	if( s > 59 ) {
		alert( hx_Env.dtp_string[L_ERR_INVALIDDURATION_SECONDS] );
		return -1;
	}

	return ( h * 60 * 60 + m * 60 + s ) * 1000;
}

//BAF In use
//BAF if (NN4){
function HxInitTokensNN( aTokens ) {
	for( var i = 0; i < aTokens.length; i++ ) {
		if( typeof( aTokens[ i ] ) == 'undefined' ) aTokens[ i ] = '';
	}

	return aTokens;
}
//BAF }

/*
 * Misc debug utility
 * 
 */
function ASSERT(x){
	return((x) || alert( "ASSERTION FAILED:  " + x + " : " + __FILE__ + " : " + __LINE__));
}

//BAF
/**
 * HxFormatMessage( formatString [, arg1, arg2, arg3, ....] )
 * This routine may be used to format a string with replaceable parts.
 * It provides a means of making message construction more localization
 * friendly.  It may have a variable number of string arguments the
 * first argument is the format string which may have "%n" sequences
 * (where n >= 1) to support replacing this with the nth variable
 * argument.
 * A "%%" sequence in the format string will translate to a single "%".
 *
 */
function HxFormatMessage( sFmtString /*, ...*/ )
{
	var sResult = "";
	for (var iPre = 0, i = sFmtString.indexOf('%');
		 i != -1;
		 iPre = i + 1, i = sFmtString.indexOf('%', iPre))
	{
		sResult += sFmtString.substring(iPre, i);
		//BAF ASSERT(i + 1 < sFmtString.length);
		++i; //go to next char
		if (sFmtString.charAt(i) == '%')
		{
			// %% becomes %
			sResult += sFmtString.charAt(i);
			continue;
		}
		var num = parseInt(sFmtString.substr(i),10);
		//BAF ASSERT(!isNaN(num));
		ASSERT(num >= 1);
		ASSERT(num < arguments.length);
		sResult += arguments[num];
		i += ("" + num).length - 1; // Because iPre will be set to (i + 1)
	}
	sResult += sFmtString.substr(iPre);
	return sResult;
}


/**
 * This class implements the DatePick object
 *
 * Notes:
 * We set hours to 12 in various places in the code to try to better
 * cope with date changes as a result of jumping in and out of Daylight
 * Savings Time.
 *
 */

DATEPICKIDPREFIX="SDP";
DATEPICKILAYERID="DateNavBlock";

COMMENT=true;

function HxDatePick( doc, bDateNav, sUniqueSuffix, oDTP )
{		
	this.document = doc;
	this.browser  = null;	/* new member - MWD */

	if (hx_Env.browser != null)
		this.browser = hx_Env.browser;
	else
		this.browser = new HxBrowserManager ();
		
	this.dtp = null; 		/* new member - MWD */
	this.s = "";
	this.oDate = null;			// current date in date picker
	this.oExternalDate = null;	// current date in calendar view for Date Navigator
								// current date in date field for Date Pick control
	this.elDateFld = null;		// date input field (when invoked as a input control aid)
	this.bDN = bDateNav;			// is incarnated as date navigator
	this.sUnique = sUniqueSuffix;	// way to differentiate multiple instances of datePicks
	this.id = DATEPICKIDPREFIX+sUniqueSuffix; // Navigator doesn't like "_" in ID
	this.bDNExpanded = true;   // initial state - expanded ot collapsed
	this.fnDatePicked = null;
	this.fnFocusChanged = null;
	//BAF this.oToday = this.dtp.oToday;
	this.oToday = new Date();
	this.bHasIEDOM = this.browser.hasDOM();
	this.bHasLayers = this.browser.hasLayers();
	this.bNN4 = this.browser.isNetscape4();
	this.bMoz = this.browser.isMozilla();
	this.elCapture = null;
	this.idTimer = null;
	this.nTimerInterval = 0;
	this.nRows = 0;
	this.bIgnoreOnClick = false;	// used to prevent double advances on img click
		
	if (oDTP == null || typeof(oDTP) != 'object')
		this.dtp = new HxDateTimePreferences();
	else
		this.dtp = oDTP;

	if( !this.bHasIEDOM )
	{
		if( !this.dtp.aDatePicks )
			this.dtp.aDatePicks = new Array();
		this.dtp.aDatePicks[sUniqueSuffix] = this;
	}

	//HxInitGlobalCalendarArrays();
	//BAF
	this.prototype = this;
}

HxDatePick.prototype.getBaseYear = function()
{
	var today = new Date();
	var thisYear = today.getFullYear();
	return thisYear - 30;  /* go back 30 years max - MS model */
};

/*
 * Call this function to properly create a JS Date where sDate
 * is SAID TO BE in some form of dd/mm/yy format.
 * Return null on any error.
 */
HxDatePick.prototype.getDDMMYY = function(sDate)
{
	var myDate = null;
	if (sDate == "" || sDate == null)
		return myDate;

	if( sDate.indexOf( "-" ) >= 0 || sDate.indexOf( "/" ) >= 0
		  || sDate.indexOf( "." ) >= 0 ){
		var pat = '([^\\s\\d\\-\\/\\.]*)(\\d{1,4})[\\-\\/\\.](\\d{1,2})';
		pat += '[\\-\\/\\.]([^\\s\\d\\-\\/\\.]*)(\\d{1,4})';
	}
	else
		return myDate;

	var re = new RegExp( pat );
	var aParts = re.exec( sDate );

	if (aParts && aParts.length == 6) {
		var d = null;
		var m = null;
		var y = null;
		// the slots we are interested in are: 2 (d), 3 (m), 5(y) 
		if( aParts[2].length > 0 && aParts[2].length < 3 )
			d = parseInt(aParts[2]);
		if( aParts[3].length > 0 && aParts[3].length < 3 )
			m = parseInt(aParts[3]);
		if( aParts[5].length > 0 && aParts[5].length < 5 )
			y = parseInt(aParts[5]);
		
		if (d && m && y) {
			myDate = new Date(y, m-1, d);
		}
	}
	
	return myDate;
}

HxDatePick.prototype.NetscapeA = function( sHTML, nMonthAdj )
{
	if( this.bNN4 )
		return '<A HREF="javascript:HxDatePickAdjust(' + "'" + this.sUnique + "'," + nMonthAdj + "," + this.dtp + ')">' + sHTML + '</A>';
	else
		return sHTML;
};

HxDatePick.prototype.getHTML = function( bNoDivBlock ) {
	var sCharDays = new Array(hx_Env.dtp_string[L_CHARDAY_SUN], hx_Env.dtp_string[L_CHARDAY_MON], hx_Env.dtp_string[L_CHARDAY_TUE],
							  hx_Env.dtp_string[L_CHARDAY_WED], hx_Env.dtp_string[L_CHARDAY_THU], hx_Env.dtp_string[L_CHARDAY_FRI],
							  hx_Env.dtp_string[L_CHARDAY_SAT] );
	var i;
	var s = '';
	var bNoOuterDiv = arguments.length == 0 ? false : bNoDivBlock;

	var sPrevImg = hx_Env.resources.getImgPath() + "DNPrev.gif";
	var sNextImg = hx_Env.resources.getImgPath() + "DNNext.gif";
	var sTransparentImg = hx_Env.resources.getImgPath() + "transparent.gif";

	// make sure we have a date before trying to display the grid
	if( this.oDate == null )
		this.oDate = new Date( this.oToday.getFullYear(), this.oToday.getMonth(), this.oToday.getDate(), 12, 0, 0 );


	if( !bNoOuterDiv )
	{
		// for Netscape this is initially hidden and appears on first timer message.
		/*#if 0
		if(this.bDN)
			s += this.bHasLayers ? '' : '<div class="s-datePick-NavFrame">';
		#endif*/

		s += this.bHasLayers ? '<layer' : '<div';
		s += ' class="' + (this.bDN ? 's-datePick-DateNav' : 's-datePick-DateCtl') + '"';
		if(this.bDN && this.bHasLayers)
			s += ' VISIBILITY="hidden"';
			// dg: visibility:hidden is causing white space at the bottom
			//  of ContactEdit and PreferencesEdit
		if (!this.bDN)
			s+=' style="display:none;"';
		s += ' id="' + this.id + '">';
	}

	if( this.bDN )
	{
		sDNDownImg = hx_Env.resources.getImgPath() + "DNDown.gif";
		var sTodayId = HxToLocalDateString(this.oToday);
		s += '<table border="0" cellspacing="0" cellpadding="0" width="100%">';
		s += '<tr><td colspan="3" height="1" class="s-pagetitlebar-bg"><img src=' + sTransparentImg + ' height=1 width=1 border="0"></td></tr>';
		s += '<tr><td valign="center" class="s-dateNav-Day" id="DateNav_Day" title="' + hx_Env.dtp_string[L_DN_GOTOTODAYINCALENDAR] + '" DAYID="' + sTodayId +'">' 
			 + '&nbsp;' + this.oToday.getDate() + '</td>';
		s += '<td width="10"></td>';
		s += '<td valign="center" class="s-dateNav-Desc" id="DateNav_DayDesc" DAYID="' + sTodayId +'" title="'+ hx_Env.dtp_string[L_DN_GOTOTODAYINCALENDAR] + '">' + 
			 this.oToday.getDayLabel() + '<br>' + this.oToday.toDateDisplayEx(this.dtp) + '<br>' + this.oToday.toTimeDisplay(null, this.dtp) + '</td></tr>';
		s += '</table>';
		s += '<table border="0" cellspacing="0" cellpadding="0" width="100%">';

		if (!this.bNN4){
			s += '<tr><td width="85%" valign="MIDDLE"><hr align="center" class="s-dateNav-hr" size="1" width="95%"></td>';
			s += '<td height="20" colspan="1" align="center" valign="top"><img src=' + /*BAF ResourcesURLQuoted(DNDown.gif)*/ sDNDownImg + ' width="13" height="13" alt="' + hx_Env.dtp_string[L_DN_TOGGLE] + '" title="' + hx_Env.dtp_string[L_DN_TOGGLE] + '" border="0" style="cursor:pointer;cursor:hand"></td></tr>';
		}else{
			s += '<tr><td width="100%" valign="MIDDLE"><hr align="center" class="s-dateNav-hr" size="1" width="95%"></td></tr>';
		}

		s += '<tr><td colspan="2" height="6"></td></tr>';
		s += '</table>';

		if (!this.bNN4) {
			s += '<div id="DateNav_Cal"' + (this.bDNExpanded ? '' : ' style="display:none"') + '>';
		}else{
			if( !this.bDNExpanded )
				s += '<layer visibility="hidden">';
		}
	}

	s += '<table border="0" cellspacing="0" cellpadding="2" width="100%">';
	s += '<tr><td width="20%" align="center">' + this.NetscapeA( '<img class="s-datePick-Arrow" src="' + sPrevImg + '" alt="' + hx_Env.dtp_string[L_PREVIOUS_YEAR] +'" title="' + hx_Env.dtp_string[L_PREVIOUS_YEAR] +'" border=0 YEARADJ="-1" />', -12)  + '</td>';
	s += '<td width="60%" class="s-datePick-Hdg-text" ID="' + DATEPICKIDPREFIX + 'Year' + this.sUnique + '">' + this.oDate.toFormatString(D_DTFMT_YEAR, this.dtp) + '</td>';
	s += '<td width="20%" align="center">' + this.NetscapeA( '<img class="s-datePick-Arrow" src="' + sNextImg + '" alt="' + hx_Env.dtp_string[L_NEXT_YEAR] + '" title="' + hx_Env.dtp_string[L_NEXT_YEAR] + '" border=0 YEARADJ="1"/>', 12) + '</td></tr>';

	if(this.bHasIEDOM){
		s += '<tr><td colspan="3" height="1" bgcolor="black"></td></tr>';
	}else{
		s += '</table>';
		s += '<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="0" WIDTH="100%" HEIGHT="1">';
		s += '<TR><TD HEIGHT="1" BGCOLOR="black"><img src="' + sTransparentImg + '" height=1 width=1 border="0"></TD></TR>';
		s += '</TABLE>';
		s += '<table border="0" cellspacing="0" cellpadding="2" width="100%">';
	}	

	s += '<tr><td align="center">' + this.NetscapeA( '<img class="s-datePick-Arrow" SRC="' + sPrevImg + '" alt="' + hx_Env.dtp_string[L_PREVIOUS_MONTH] + '"  title="' + hx_Env.dtp_string[L_PREVIOUS_MONTH] + '"border=0 MONTHADJ="-1"/>', -1)  + '</td>';
	s += '<td class="s-datePick-Hdg-text" ID="' + DATEPICKIDPREFIX + 'Month' + this.sUnique + '">' + this.oDate.getMonthLabel() + '</td>';
	s += '<td align="center">' + this.NetscapeA( '<img class="s-datePick-Arrow" SRC="' + sNextImg + '" alt="' + hx_Env.dtp_string[L_NEXT_MONTH] + '" title="' + hx_Env.dtp_string[L_NEXT_MONTH] + '" border=0 MONTHADJ="1"/>', 1)  + '</td></tr>';
	s += '</table>';
	s += '<table border="0" cellspacing="0" cellpadding="0" width="100%"><tr>';

	for( i=this.dtp.nFirstDayMonth; i<this.dtp.nFirstDayMonth+7; i++ )
		s += '<td class="s-datePick-WeekDay-text">' + sCharDays[i%7] + '</td>';

	s += '</tr><tr><td colspan="7" bgcolor="black" height="1"><img src="' + sTransparentImg + '" height=1 width=1 border="0"></td></tr>';
	s += '</table>';

	// Generate HTML within date grid (within this.s)
	this.s = "";
	this.nRows = 0;
	HxDrawGrid( this.oDate, true, this.monthGrid, this );

	for( i=this.nRows; i<6; i++ )
		this.s += '<tr><td class="s-datePick-OtherMonth" colspan="7">&nbsp;</td></tr>';

	s += '<span id="' + DATEPICKIDPREFIX + 'Grid' + this.sUnique + '" width="100%">';
	s += '<table border="0" cellspacing="0" cellpadding="0" width="100%">' +
		 this.s + '</table></span>';

	// Close up DateNav_Cal
	if (!this.bNN4){
		if( this.bDN )
			s += '</div>';
	}else{
		if( this.bDN && !this.bDNExpanded )
			s += '</layer>';
	}


	if( !bNoOuterDiv )
	{
		s += this.bHasLayers ? '</layer>' : '</div>';
		if( this.bDN )
			if (!this.bNN4){
				s += this.bHasLayers ? '<ilayer id="' + DATEPICKILAYERID + '" width="140" height="180" above="' + this.id + '"></ilayer>' : '';
			}else{
				if( this.bDNExpanded )
					s += this.bHasLayers ? '<ilayer id="' + DATEPICKILAYERID + '" width="140" height="180" above="' + this.id + '"></ilayer>' : '';
				else
					s += this.bHasLayers ? '<ilayer id="' + DATEPICKILAYERID + '" width="140" height="50" above="' + this.id + '"></ilayer>' : '';
			}
	}

	return s;
}   /* end of getHTML() */

HxDatePick.prototype.generateHTML = function( bNoDivBlock )
{
	this.document.write( this.getHTML(bNoDivBlock) );

	if( this.bHasIEDOM )
	{
		var el = this.document.all.item( this.id );
		el.oDatePick = this;
		el.onclick = HxDatePickOnClick;
		el.onkeydown = HxDatePickOnKeyDown;
		el.onmousedown = HxDatePickOnMouseDown;
		el.onmouseup = HxDatePickOnMouseUp;
		el.onlosecapture = HxDatePickOnLoseCapture;
	}
	if( this.bMoz )
	{
		var el = this.document.getElementById( this.id );
		el.oDatePick = this;
		el.onclick = HxDatePickOnClick;
		el.onkeydown = HxDatePickOnKeyDown;
		el.onmousedown = HxDatePickOnMouseDown;
		el.onmouseup = HxDatePickOnMouseUp;
		el.onlosecapture = HxDatePickOnLoseCapture;
	}
	if (this.bNN4){
		if( !bNoDivBlock && this.bDN && this.bHasLayers )
			//BAF this.dtp.oOnloadChain.add( "HxDatePickShowLayer( '" + this.sUnique + "')" );
			HxDatePickShowLayer(this.sUnique, this.bNN4, this.dtp);
	}
};

/**
 * param below is actually the this pointer (as this cannot be used as
 * this is a callback function from outside this class)
 *
 */
HxDatePick.prototype.monthGrid= function( oDate, r, c, nRows, param )
{
	var s = "";
	var sCellTableId = HxToLocalDateString(oDate);
	var iDate = oDate.getDate();

	if( c == 0 )
	{
		s += '<tr>';
		param.nRows = nRows;
	}

	s += '<td valign="center" height="8" tabindex="1" class="s-datePick-';
	if( 0 == oDate.compareDate( param.oToday ) )
		s += 'Today';
	else if ( param.oExternalDate != null && 0 == oDate.compareDate( param.oExternalDate ) )
		s += 'CurrDay';
	else
	{
		s += ( (r == 0 && iDate > 7) || (r == nRows-1 && iDate < 7 ) ? 'OtherMonth' : 'CurrMonth');
		if( !param.bHasIEDOM && 0 == oDate.compareDate( param.oDate ) )
			s += 'Curr';
	}
	s += '" DAYID="'+ sCellTableId;
	if( param.bHasIEDOM || param.bMoz)
	{
		s += '" id="' + DATEPICKIDPREFIX + 'Day' + param.sUnique + sCellTableId;
		s += '">' + iDate + '</TD>';
	}
	else
	{
		s += '"><a href="javascript:HxDatePickOnClick(\'' + param.sUnique + '\',\'' + sCellTableId + '\')" CLASS="s-datePick-';
		if( 0 == oDate.compareDate( param.oToday ) )
			s += 'Today';
		else if ( param.oExternalDate != null && 0 == oDate.compareDate( param.oExternalDate ) )
			s += 'CurrDay';
		else
		{
			s += ( (r == 0 && iDate > 7) || (r == nRows-1 && iDate < 7 ) ? 'OtherMonth' : 'CurrMonth');
			// if( !param.bHasIEDOM && 0 == oDate.compareDate( param.oDate ) )
			//	s += 'Curr';
		}

		s += '">' + iDate + '</a></TD>';
	}

	if( c == 6 )
		s += '</tr>';

	param.s += s;
	return true;
};

HxDatePick.prototype.update = function( )
{
	var el;
	var i;

	this.s = "";
	HxDrawGrid( this.oDate, true, this.monthGrid, this );
	for( i=this.nRows; i<6; i++ )
		this.s += '<tr><td class="s-datePick-OtherMonth" colspan="7">&nbsp;</td></tr>';
	if(document.getElementById)
		el = document.getElementById( DATEPICKIDPREFIX + 'Year' + this.sUnique );
	else 
		el = document.all.item( DATEPICKIDPREFIX + 'Year' + this.sUnique );
	el.innerHTML = this.oDate.toFormatString(D_DTFMT_YEAR, this.dtp);
	if(document.getElementById)
		el = document.getElementById( DATEPICKIDPREFIX + 'Month' + this.sUnique );
	else
		el = document.all.item( DATEPICKIDPREFIX + 'Month' + this.sUnique );
	el.innerHTML = this.oDate.getMonthLabel();
	if(document.getElementById)
		el = document.getElementById( DATEPICKIDPREFIX + 'Grid' + this.sUnique );
	else
		el = document.all.item( DATEPICKIDPREFIX + 'Grid' + this.sUnique );
	el.innerHTML = '<table border="0" cellspacing="0" cellpadding="0" width="100%">' +
				   this.s + '</table>';
	if( !this.bDN && this.elDateFld && this.D_DateHlpr_DatePicked ) {
		this.elDateFld.value = this.oDate.toDateDisplayEx(this.dtp);
		D_DateHlpr_DatePicked = false;
	}
};

HxDatePick.prototype.updateDNTime = function()
{		
	this.oToday = this.dtp.oToday;
	if( this.bHasLayers )
	{
		var oLayer = this.document[this.id];
		if( oLayer.visibility == "show" )
			this.updateLayer(this.bNN4);
	}	

	if( !this.bHasIEDOM )
		return;

	document.all.DateNav_Day.innerHTML = '&nbsp;' + this.oToday.getDate();
	document.all.DateNav_DayDesc.innerHTML = this.oToday.getDayLabel() + '<BR>' + 
											 this.oToday.toDateDisplayEx(this.dtp) + '<BR>' + this.oToday.toTimeDisplay(null, this.dtp);
};

HxDatePick.prototype.setDate = function( oDate )
{
	this.oDate = new Date( oDate.getFullYear(), oDate.getMonth(), oDate.getDate(), 12, 0, 0 );
};

HxDatePick.prototype.getDate = function()
{
	return this.oDate;
};

// Sets the date displayed within the calendar view (used only for navigator)
HxDatePick.prototype.setExternalDate = function( oDate )
{
	this.oExternalDate = new Date( oDate.getFullYear(), oDate.getMonth(), oDate.getDate(), 12, 0, 0 );
};

HxDatePick.prototype.getExternalDate = function()
{
	return this.oExternalDate;
};

HxDatePick.prototype.setDatePickedCB = function( fn )
{
	this.fnDatePicked = fn;
};

HxDatePick.prototype.setFocusChangedCB = function( fn )
{
	this.fnFocusChanged = fn;
};

HxDatePick.prototype.yearMonthAdj = function( ev, y, m )
{
	HxAdjustDate( this.oDate, y, m, 0, 0, 0, 0 );
	this.oDate.setHours(12);
	this.update();
	if( ev ) {
		ev.cancelBubble = true;
		if (ev.stopPropagation) ev.stopPropagation();
	}
		
	this.showFocus();
};

HxDatePick.prototype.clickedDate = function( sDate, ddMgr )
{
	var newDate = HxToDate( sDate, true );
	this.oDate.setTime( newDate.getTime() );
	this.oDate.setHours(12);
	this.elDateFld.value = this.oDate.toDateDisplayEx(this.dtp);
	ddMgr.hide();
	// ev.cancelBubble = true;
};

HxDatePick.prototype.showFocus = function()
{
	if( !this.oDate )
		return;
	var sCurrDayId = HxToLocalDateString(this.oDate);
	if(document.getElementById)
		elFocus = document.getElementById( DATEPICKIDPREFIX + "Day" + this.sUnique + sCurrDayId );
	else
		elFocus = document.all.item( DATEPICKIDPREFIX + "Day" + this.sUnique + sCurrDayId );
	if( this.bDNExpanded && elFocus && elFocus.focus )
		elFocus.focus();
};


HxDatePick.prototype.pickDate = function( elDay, sDayId )
{
	this.D_DateHlpr_DatePicked = true;
	if (this.bNN4) {
		elDay = null; // not relevant to Netscape - MWD
		
		if( null == sDayId )
			return;

		// In IE 5.5, could possibly create an event object and fire it
		var sCurrDayId = HxToLocalDateString(this.oDate);
		var oSelDate = HxToDate(sDayId,true);

		// Set the current date as represented in the view
		this.setExternalDate( oSelDate );
		if( sCurrDayId != sDayId )
		{
			this.setDate( oSelDate );
		}

		if( this.fnDatePicked )
			this.fnDatePicked( sDayId );
	} else {
		/*
		 * Internet Explorer
		 *
		 */
		if( null == sDayId )
			sDayId = elDay.getAttribute("DAYID");

		// In IE 5.5, could possibly create an event object and fire it
		var sCurrDayId = HxToLocalDateString(this.oDate);
		var oSelDate = HxToDate(sDayId,true);

		// If not the hanging calendar portion of date navigator
//		if( 0 != elDay.id.indexOf( "DateNav_Day" ) )
//			elDay.focus();

		// Set the current date as represented in the view
		this.setExternalDate( oSelDate );
		if( sCurrDayId != sDayId )
			this.setDate( oSelDate );
		this.update();
		if( this.fnDatePicked )
			this.fnDatePicked( sDayId );
	}
}

HxDatePick.prototype.clickedImg = function( elImg, ev )
{
	var sAttrib;
	var nYearAdj=0, nMonthAdj=0;
	this.D_DateHlpr_DatePicked = false;

	sAttrib = elImg.getAttribute("YEARADJ");
	if( sAttrib )
		nYearAdj = parseInt( sAttrib, 10 );
	else
	{
		sAttrib = elImg.getAttribute("MONTHADJ");
		if( sAttrib )
			nMonthAdj = parseInt( sAttrib, 10 );
		else
		{
			// clicked on expand/collapse img
			this.show(!this.bDNExpanded);
			return;
		}
	}

	this.yearMonthAdj(ev, nYearAdj, nMonthAdj);
	if( this.fnFocusChanged )
		this.fnFocusChanged( this.oDate );
};	

HxDatePick.prototype.show = function(bShow) {
	this.bDNExpanded = bShow? true:false;
	document.all.tags("div").DateNav_Cal.style.display = this.bDNExpanded? "inline" : "none";
};


HxDatePick.prototype.updateLayer = function(bNN4)
{
	/* Netscape only */
	if (bNN4) {
		var oLayerDoc = document[this.id].document;
		oLayerDoc.open();
		oLayerDoc.clear();
		oLayerDoc.writeln("");
		this.generateHTML( true );
		oLayerDoc.close();
	}
}

HxDatePick.prototype.on_layer_toggled = function(bVisible, x, y)
{
	// When becoming visible, check the value in the correspnding field
	// and update DatePicker to highlight that date as selected
	if(bVisible) {
		if( this.elDateFld.value.length ) {
			var ddmmyy = null;
			// DatePicker says the date should be short format dd/mm/yy?
			if ( this.dtp.sDateFormat.indexOf("d") == 0 ) {
				ddmmyy = this.getDDMMYY(this.elDateFld.value);
			}
			if (ddmmyy == null)	
				this.oDate = new Date(this.elDateFld.value);
			else
				this.oDate = ddmmyy;
			
			// check if this is a short year and convert to a 21st
			// century value in that case, i.e. '03' = 2003 (!1903)
			var nYear = this.oDate.getFullYear();
			var sYear = new String(nYear);
			var baseYear = this.getBaseYear();
			if (nYear < baseYear && this.elDateFld.value.indexOf(sYear) == -1) {
				nYear += 100;
				this.oDate = new Date(nYear, this.oDate.getMonth(), this.oDate.getDate(), 12, 0, 0);
			}
			
			this.oExternalDate = new Date(this.oDate.getTime());
			this.update();
		}
	}
}

function HxDatePickOnClick(e)
{	
	if(!e) e = window.event;
	
	if (!this.bNN4){	
		if(e.srcElement) el = e.srcElement; else el = e.target;
		var sAttrib;
		var bHandled = false;

		if (el.tagName == "IMG") 
		{
			if( !this.oDatePick.bIgnoreOnClick )
				this.oDatePick.clickedImg( el, e );
			else
				this.oDatePick.bIgnoreOnClick = false; 
			bHandled = true;
		}
		else if (el.tagName == "TD")
		{
			sAttrib = el.getAttribute("DAYID");
			if( sAttrib )
			{
				this.oDatePick.pickDate(el, sAttrib);
				bHandled = true;
				// YK if this is a Ctrl, close it once a date is selected
				if(!this.bDN) {
					hx_toggle_layer(this.id);
				}
				
			}
		}

	//BAF if (COMMENT){
	// Added the below two lines to try to fix problem with datepick
	// dropdown control disappearing as soon as next/prev arrow is
	// clicked within repeat panel. This seems to do the trick (VRS:
	// 10/12/00) 
	//}

	//BAF cancelEvent();
	/*#if 0
	// many scenes do this ... 
	// cancelEvent() is in s_Utils.h
	window.event.cancelBubble = true;
	window.event.returnValue = false;
	#endif*/

	if( !bHandled )
		this.oDatePick.showFocus();
	}
	else
	{
		/* this is the Netscape clause */
		
		//BAF function HxDatePickOnClick(sUniqueSuffix,DayID)
		var oDatePick = this.oDatePick.dtp.aDatePicks[sUniqueSuffix];
		oDatePick.pickDate(null, DayID);
	}
}

function HxDatePickOnKeyDown()
{
	var ev = window.event;
	var nKeyCode = ev.keyCode;
	var nDayAdj = 0;
	var oDatePick = this.oDatePick;

	// Catch arrow keys and adjust focus within the date grid,
	// traversing months as necessary 
	switch( nKeyCode )
	{
		case 32: case 13: case 18: //Space, Enter[10key], Return
			this.oDatePick.pickDate(window.event.srcElement, null);
			//BAF cancelEvent(ev);
			break;
			
		case 37: //LeftArrow
			nDayAdj = -1;
			break;
			
		case 38: //UpArrow
			nDayAdj = -7;
			break;
			
		case 39: //RightArrow
			nDayAdj = 1;
			break;
			
		case 40: //DownArrow
			nDayAdj = 7;
			break;
			
		case 9: //Tab, Shift+Tab
			if (!this.bNN4){
				nDayAdj = ev.shiftKey ? -1 : 1;
			}else{
				nDayAdj = (ev.modifiers & SHIFT_MASK) ? -1 : 1;
			}
			break;
			
		case 27: //Esc
			hideDropDownMgr();
			window.setTimeout("document.all.item('" + oDatePick.id + "').oDatePick.elDateFld.select()", 0);
			//BAF cancelEvent(ev);
			break;
			
		default:
			break;
		}

	if( nDayAdj != 0 )
	{
		HxAdjustDate( oDatePick.oDate, 0, 0, nDayAdj, 0, 0, 0 );
		oDatePick.oDate.setHours(12);
		oDatePick.update();
		oDatePick.showFocus();
		//BAF cancelEvent(ev);
		if( oDatePick.fnFocusChanged )
			oDatePick.fnFocusChanged( oDatePick.oDate );
	}
}

function HxDatePickOnMouseDown(e)
{
	if(!e) e = window.event;

	if( e.button != 1 )
		return;
	
	var el = e.srcElement;
	var oDatePick = this.oDatePick;
	var bArrow = false;
	
	if (el.tagName == "IMG") 
	{
		if( el.getAttribute("YEARADJ") || el.getAttribute("MONTHADJ") )
			bArrow = true;
	}
	if( bArrow )
	{
		// I've experienced problems getting a subsequent onclick event
		// if I capture the first mouse down on a page.  I believe this
		// is an IE bug, but have not yet reported this.  For now, am
		// doing my own click on mouse down. 
		oDatePick.clickedImg( el, window.event );
		oDatePick.bIgnoreOnClick = true;
		oDatePick.elCapture = el;
		this.setCapture();
		oDatePick.nTimerInterval = 700;
		oDatePick.idTimer = window.setTimeout( "HxDatePickContinuousClick('" + this.id + "')", oDatePick.nTimerInterval );
	}
}

function HxDatePickOnMouseUp(e)
{
	if(!e) e = window.event;

	if( e.button != 1 )
		return;

	if( this.oDatePick.elCapture )
	{
		this.releaseCapture();
		e.cancelBubble = true;
		if (e.stopPropagation) e.stopPropagation();
	}
}

function HxDatePickOnLoseCapture()
{
	this.oDatePick.elCapture = null;
	if( this.oDatePick.idTimer )
	{
		window.clearTimeout( this.oDatePick.idTimer );
		this.oDatePick.idTimer = null;
	}
}

function HxDatePickContinuousClick( sDatePickId )
{
	var elDP = document.all.item( sDatePickId );
	var oDatePick = (elDP ? elDP.oDatePick : null);
	if( oDatePick && oDatePick.elCapture )
	{
		oDatePick.clickedImg( oDatePick.elCapture, null );
		oDatePick.nTimerInterval = Math.max( oDatePick.nTimerInterval - 50, 100 );
		oDatePick.idTimer = window.setTimeout( "HxDatePickContinuousClick('" + sDatePickId + "')", oDatePick.nTimerInterval );
	}
}

function HxDatePickShowLayer( sUnique, bNN4, oDTP )
{
	/* Netscape only - MWD */
	if (bNN4) {
		var oDatePick = oDTP.aDatePicks[sUnique];
		var oLayer = document[oDatePick.id];
		var oILayer = document[DATEPICKILAYERID];
		oLayer.moveToAbsolute(oILayer.pageX, oILayer.pageY );
		oILayer.bgColor = "#d6d6b6";
		oLayer.visibility = "show";
		oDatePick.updateLayer(bNN4);
	}
}

function HxDatePickAdjust( sUnique, nMonths, oDTP )
{
	var oDatePick = oDTP.aDatePicks[sUnique];
	var s = document.location.href;
	HxAdjustDate( oDatePick.oDate, 0, nMonths, 0, 0, 0, 0 );
	oDatePick.oDate.setHours(12);
	//oDatePick.update();//BAF
	if( oDatePick.fnFocusChanged )
	{
		oDatePick.fnFocusChanged( oDatePick.oDate );
	}
	if( oDatePick.bHasLayers )
		oDatePick.updateLayer(true); /* NN4 only */
	else
		document.location.replace(document.location.href);//BAF was "window.location...."
}

// ===========================================================================================
//
// Slider Start
//
// ===========================================================================================

// ---------------------------------------------------------------------
// Beware testing this on Netscape 4.x.
// 1. get the latest 4.x  sub-version (currently 4.79)
// 2. if pages seems to render unreliably, load a different page then
//    reload the page under test.
// 3. why Netscape 4.x requires double-nested SPANs we don't know.
//
// ---------------------------------------------------------------------

var hx_sliding = false;			// state for user drag'n'drop
var hx_sliders = new Array();		// keeps track of all created sliders
var hx_nn4 = false;				
var hx_ie = false;
var hx_moz = false;	// Mozilla, NS 6+, Konqueror, etc
var hx_dom2 = false;	// fully supports (important bits of) DOM2

// ---------------------------------------------------------------------
// create the slider
function hx_render_slider()
{
    with (window.document)
    {
    	// create a wrapping SPAN container to show/hide the slider when clicked on the image
        write("<span id=\"" + this.span_id + "_mainspan\" style=\"display:none\">");
        
		this.render_pane();
		this.render_scale();
		
		if(this.tick_tabs == null) {	// calculate tabs between ticks
		    this.tick_tabs = (this.scale_width-this.tick_width) / (this.ticks-1.0);
		}
	
		for (i=0; i<this.ticks; i++) {	// draw ticks and labels
			if(this.show_labels || i == 0 || i == this.ticks - 1 ) {
		    	this.render_tick(i);
		    	this.render_label(i);
			}
		}
		this.render_stylus();

		write("</span>");
	
		// Install event handlers
	
		if (this.interactive == true) {
			span = hx_getObject(this.span_id);
		    if (hx_moz || hx_dom2) {
				span.addEventListener("mousedown",hx_slider_stylus_mousedown, 0);
				span.addEventListener("mouseup",hx_slider_stylus_mouseup, 0);
				span.addEventListener("mousemove",hx_slider_stylus_mousemove, 0);
		    }
		    else if ( hx_ie ) {
				span.onmousedown = hx_slider_stylus_mousedown;
				span.onmouseup = hx_slider_stylus_mouseup;
				span.onmousemove = hx_slider_stylus_mousemove;
		    }
		    else if ( hx_nn4 ) {
				span.captureEvents(Event.MOUSEDOWN|Event.MOUSEUP|Event.MOUSEMOVE);
				span.onMouseDown = hx_slider_stylus_mousedown;
				span.onMouseUp = hx_slider_stylus_mouseup;
				span.onMouseMove = hx_slider_stylus_mousemove;
				window.document.onMouseMove = null;
		    }
		}
    }
}

// ---------------------------------------------------------------------
// render the scale "slot" or line
function hx_render_scale_slider()
{
    with (window.document) {
    write("<SPAN><SPAN " +
    	"ID = \"" + this.span_id + "_scale\" " +
		"STYLE=\"" +
		    "z-index:1; margins:0; padding:0; " +
		    "position: absolute; " +
		"\" >");

    write("<IMG " +
		"SRC=\""+this.scale_image+"\" " +
		"HEIGHT=\"" + this.scale_height + "\" " +
		"WIDTH=\"" + this.scale_width + "\" " +
		"border=0>");
    write("</SPAN></SPAN>");
    }
}

// ---------------------------------------------------------------------
// Background clickable span used for all mouse input
// There is a hack here for NS 4.x - neither ID nor NAME
// are accessable from event handlers, so we tack the ID onto
// the end of the SRC attribute. Still a valid URL and doesn't break NN 4.
// If we don't do this, we can't tell which slider it is.
function hx_render_pane_slider()
{
    with (window.document) {
	write("<SPAN><SPAN " +
		    "ID=\"" + this.span_id + "\" " +
		    "STYLE=\"" +
    			"z-index:10; margins:0; padding:0; " +
		        "position: absolute; " +
	    "\" >" );

	write("<IMG " +
		    "ID=\"" + this.span_id + "_pane\" " +
		    ( hx_nn4 ? "SRC=\""+this.pane_image+"#"+this.span_id+"\" "
		          : "SRC=\""+this.pane_image+"\" "
		    ) +
		    "HEIGHT=\"" + (this.stylus_height + this.tick_height + this.label_size) + "\" " +
		    "WIDTH=\"" + (this.scale_width+this.stylus_width) + "\" " +
		    "border=0>");
	write("</SPAN></SPAN>");
	
	// background
	write("<SPAN id=\"" + this.span_id + "_background\" class=\"s-slider-Ctl\" style=\"" +
		    "height:" + (this.stylus_height + this.tick_height + this.label_size + 5) + ";" +
		    "width:" + (this.scale_width+this.stylus_width + 5) + ";\"></SPAN>");
	
    }
    
}

// ---------------------------------------------------------------------
// a mark along the scale
function hx_render_tick_slider(i)
{
    with (window.document) {
	write("<SPAN><SPAN " +
		    "ID=\"" + this.span_id + "_tick_" + i + "\" " +	
		    "STYLE=\"" +
			"z-index:1;" +
			"position: absolute; " +
			"font-size:" + this.tick_width + "px; " +
		    "\">" );

	write("<IMG " +
		    "SRC=\"" + this.tick_image + "\" " +
		    "HEIGHT=\"" + this.tick_height + "\" " +
		    "WIDTH=\"" + this.tick_width + "\" " +
		    "border=0>");
	write("</SPAN></SPAN>");
    }
}

// ---------------------------------------------------------------------
// a label for one of the tick marks
function hx_render_label_slider(i)
{
   var label_style = "z-index:1; padding:0; margins:0;";

   with (window.document)
   {
	write("<SPAN><SPAN " +
		    "ID=\"" + this.span_id + "_label_" + i + "\" " +	
		    "STYLE=\"" +
			"position: absolute; "+
			"font-size:" + this.label_size + "px; " +
			((!hx_nn4) ? "font-family:"+this.label_font+"; " : "") +
			label_style + "\" " +
	       ">" );
	label = this.min_value + (i * this.inc_value);
	if(label > this.max_value) {
		label = this.max_value
	}
	write(label + "</SPAN></SPAN>");
    }
}

// ---------------------------------------------------------------------
// the stylus on the scale line (the "claw" or "pointer")
function hx_render_stylus_slider()
{
  with (window.document) {
	write("<SPAN><SPAN " +
		    "ID=\"" + this.span_id + "_stylus" + "\" " +
		    "STYLE=\"" +
			"z-index:2; " +
			"position: absolute; margins:0; padding:0; "+
		"\" >");
	write("<IMG " +
		( hx_nn4 ? "SRC=\""+this.stylus_image+"#"+this.span_id+"_stylus\" "
		      : "SRC=\""+this.stylus_image+"\" "
		) +
		"HEIGHT=\"" + this.stylus_height + "\" " +
		"WIDTH=\"" + this.stylus_width + "\" " +
		"border=0>");
    write("</SPAN></SPAN>");
    }
}

// ---------------------------------------------------------------------
// event handler for 3 classes of browser
function hx_slider_stylus_mousedown(obj)
{
    var i;
    var slider_name;

    if (hx_moz || hx_dom2) {
		obj.stopPropagation();
		obj.preventDefault();
    }
    else if(hx_ie) {
		window.event.cancelBubble = true;
		window.event.returnValue = false;
    }
    else if(hx_nn4) {
    }

    hx_sliding = true;

    return false;	// for nn4
}

// ---------------------------------------------------------------------
// event handler for 3 classes of browser
function hx_slider_stylus_mousemove(obj)
{
    var slider_name = "";
    var config = null;
    var x = 0;
    var i = 0;

    if(!hx_sliding)
    	return;

    // find current location and slider data

    if(hx_moz || hx_dom2) {
        slider_name = obj.currentTarget.id;
		x = obj.clientX;
		obj.stopPropagation();
		obj.preventDefault();
    }
    else if(hx_ie) {
		// picks up the <IMG> tag as src.
		// picks up the z-index:0 tag if the <IMG> edge is reached
		// picks up <html> if user move is very fast
	
		with(window.event.srcElement) {
		    if(tagName != "IMG") {
		    	hx_sliding = false;
		        return;		// very fast mouse swipe by user - do nothing
		    }
		    else if(id == parentElement.id + "_pane") {
		    	slider_name = parentElement.id;
		    }
		    else {
		    	slider_name = window.document.activeElement.id;
		    }
		}
	
		x = window.event.clientX;
		window.event.cancelBubble = true;
		window.event.returnValue = false;
    }
    else if(hx_nn4) {
		slider_name = obj.target.src;
		i = slider_name.indexOf("#",0);	// the  NN 4.x hack
		slider_name = slider_name.substring(i+1,slider_name.length);
		x = obj.pageX;
    }

    for(i=0; i<hx_sliders.length; i++) {
		if(hx_sliders[i].span_id == slider_name) {
		    config = hx_sliders[i];
		}
    }

    x = hx_normalise_slider(x, config);
    x -= config.stylus_width/2;		// left edge of stylus, not center

    // set new location

    if(hx_moz || hx_dom2) {
		document.getElementById(slider_name+"_stylus").style.left = x + "px";
    }
    else if(hx_ie) {
        document.all(slider_name+"_stylus").style.left = x + "px";
    }
    else if(hx_nn4) {
		window.document.layers[slider_name+"_stylus"].left = x;
    }

	config.get_value();

    return false;	// for nn4
}

// ---------------------------------------------------------------------
// event handler for 3 classes of browser
function hx_slider_stylus_mouseup(obj)
{
    var slider_name;
    var x;

    if(!hx_sliding)
    	return;

    // find current location and slider data
    if(hx_moz || hx_dom2) {
        slider_name = obj.currentTarget.id;
		obj.stopPropagation();
		obj.preventDefault();
		x = obj.clientX;
    }
    else if(hx_ie) {
		// picks up the <IMG> tag as src.
		// picks up the z-index:0 tag if the <IMG> edge is reached
	
		with(window.event.srcElement) {
		    if(id == parentElement.id + "_pane") {
		    	slider_name = parentElement.id;
		    }
		    else {
		    	slider_name = window.document.activeElement.id;
		    }
		}
	
		x = window.event.clientX;
		window.event.cancelBubble = true;
		window.event.returnValue = false;
    }
    else if(hx_nn4) {
		slider_name = obj.target.src;
		i = slider_name.indexOf("#",0);	// the NN 4.x hack
		slider_name = slider_name.substring(i+1,slider_name.length);
		x = obj.pageX;
    }

    for(i=0; i<hx_sliders.length; i++) {
		if(hx_sliders[i].span_id == slider_name ) {
		    config = hx_sliders[i];
		}
    }

    // calculate final slider position

    x = hx_normalise_slider(x, config);
    x = hx_align_slider(x, config);

    x -= config.stylus_width/2;		// center, not edge of stylus

    // do final update to position
    if(hx_moz || hx_dom2) {
		document.getElementById(slider_name+"_stylus").style.left = x + "px";
    }
    else if(hx_ie) {
		document.all(slider_name+"_stylus").style.left = x + "px";
    }
    else if(hx_nn4) {
		window.document.layers[slider_name+"_stylus"].left = x;
    }

    window.sliding = false;

	config.get_value();

	hx_toggle_layer(config.span_id + "_mainspan");
		
    return false;
}

// ----------------------------------------------------
// Show/Hide when clicked on the image.
function hx_on_layer_toggled_slider(bVisible, x, y)
{
	if(bVisible)
		this.set_value();
		
	// update the coordinates
	hx_reposition_slider(this, x, y);
}

// ---------------------------------------------------------------------
// make sure position x is never beyond the allowed bounds of the slider
function hx_normalise_slider(x, obj)
{
    xmin = obj.left + (obj.stylus_width/2);
    xmax = obj.left + obj.scale_width + (obj.stylus_width/2);

    x = ( x <= xmin ) ? xmin : x;
    x = ( x >= xmax ) ? xmax : x;

    return x;
}

// ---------------------------------------------------------------------
// if the slider isn't continuous==true , align x with a tick mark.
function hx_align_slider(x, obj)
{
    var tab = 0;
    if(!obj.continuous) {		// must align with a tick
		while((x -= obj.tick_tabs) > obj.left - obj.tick_tabs/2) {
		    tab++;
		}
		x = obj.left + tab * obj.tick_tabs + (obj.stylus_width/2);
    }
    return x;
}

// ---------------------------------------------------------------------
// copy the slider setting into a form field.
function hx_get_value_slider()
{
    var i = 0, x = 0, data = null;

    if(hx_moz || hx_dom2) {
		x = window.document.getElementById(this.span_id+"_stylus").style.left;
		x = x.substring(0,x.length-2) - 0;
    }
    else if(hx_ie) {
        x = document.all(this.span_id+"_stylus").style.left;
		x = x.substring(0,x.length-2) - 0;
    }
    else if(hx_nn4) {
		x = window.document.layers[this.span_id+"_stylus"].left;
    }

    x += this.stylus_width/2;

    if(!this.continuous) {	// must be aligned with a tick
        x -= this.left;
		while((x-=this.tick_tabs) >= 0) {
	  	  i++;
		}
		// data = this.values[i];
		data = this.min_value + (i * this.inc_value);
    }
    else {
//		i = (x*1.0 - this.left) / this.stylus_width;
//    	data = i * (this.values[object.ticks-1] - this.values[0]);

    }
	if(data > this.max_value) {
		data = this.max_value;
	}
	if(window.document.forms[0] != null)
	    window.document.forms[0][this.form_field_id].value = data + "";

    return data;
}

// ---------------------------------------------------------------------
// set slider position accroding to a value of a form field
function hx_set_value_slider()
{
	this.value = window.document.forms[0][this.form_field_id].value;
	if(isNaN(this.value)) {
		this.value = this.min_value;
	}
	if(this.value < this.min_value) {
		this.value = this.min_value;
	}
	if(this.value > this.max_value) {
		this.value = this.max_value;
	}
	this.start_tick = (this.value - this.min_value) / this.inc_value;

}
// ---------------------------------------------------------------------
// Slider Object c'tor
function HxSlider(sID) {		
	    this.interactive = true;		// User modifiable on 'true'
	    this.continuous = false;		// Any position allowed if 'true'
	    this.span_id = sID;
	    this.left = 0;					// all in 'px' pixels
	    this.top = 0;
	    this.pane_image= hx_Env.resources.getImgPath() + "1x1.gif";
	    this.scale_width = 300;
	    this.scale_height = 2;		
	    this.scale_image = hx_Env.resources.getImgPath() + "scale.gif";
	    this.stylus_width = 10;
	    this.stylus_height = 20;
	    this.stylus_image   = hx_Env.resources.getImgPath() + "slider.gif";
	    this.tick_height = 5;
	    this.tick_width = 2;
	    this.tick_image = hx_Env.resources.getImgPath() + "tick.gif";
	    this.tick_tabs = null;			// auto-calc'ed if set to null
	    this.label_size = 10;			// in 'px' not in 'pt'
	    this.label_font = "\"Courier\"";
	    this.min_value = 1;
	    this.max_value = 10;
	    this.inc_value = 1;
	    this.value = 3;
	    this.show_labels = true;
	    this.form_field_id = sID + "_hidden";
	    this.bVisible = false;			// visibility status for toggeling
	    
	    this.render = hx_render_slider;
	    this.render_pane = hx_render_pane_slider;
	    this.render_scale = hx_render_scale_slider;
	    this.render_tick = hx_render_tick_slider;
	    this.render_label = hx_render_label_slider;
	    this.render_stylus = hx_render_stylus_slider;
	    this.get_value = hx_get_value_slider;
	    this.set_value = hx_set_value_slider;
	    this.align = hx_align_slider;
	    this.normalise = hx_normalise_slider;
	    this.init = hx_init_slider;
	    this.on_layer_toggled = hx_on_layer_toggled_slider;
	    
	    hx_sliders[hx_sliders.length] = this;
}

function hx_init_slider()
{
	this.browser = null;
	if (hx_Env.browser != null)
		this.browser = hx_Env.browser;
	else
		this.browser = new HxBrowserManager();
		
	hx_nn4 = this.browser.isNetscape4();
	hx_ie = this.browser.isIE();
	hx_moz = this.browser.isMozilla();
	hx_dom2 = this.browser.hasDOM2();

	if(this.min_value > this.max_value) {	
		temp = this.max_value;
		this.max_value = this.min_value;
		this.min_value= temp;
	}
	if(this.value < this.min_value) {
		this.value = this.min_value;
	}
	if(this.value > this.max_value) {
		this.value = this.max_value;
	}
    this.ticks = (this.max_value - this.min_value ) / this.inc_value + 1;
    this.start_tick = (this.value - this.min_value) / this.inc_value;
}

function hx_reposition_slider(obj, x, y) 
{
	var id = obj.span_id;
	if(id == null)
		return;

	var pane = hx_getObject(id+"_pane");
	var span = hx_getObject(obj.span_id);
	var back = hx_getObject(id+"_background");
	  
	pane.style.left = obj.left = span.style.left = back.style.left = x;
	pane.style.top = obj.top = span.style.top = back.style.top = y;
	
	var scale = hx_getObject(id+"_scale");
	var slider = hx_getObject(id+"_stylus");

    if ( hx_ie || hx_moz || hx_dom2) {
		scale.style.left = (obj.left + (obj.stylus_width/2)) + "px";
		scale.style.top = (obj.top + (obj.stylus_height/2) - (obj.scale_height/2)) + "px";
		slider.style.left = (obj.left + obj.start_tick*obj.tick_tabs) + "px";
		slider.style.top = obj.top + "px";
		
		for(i=0; i<obj.ticks; i++)	{
			if(obj.show_labels || i == 0 || i == obj.ticks - 1 ) {
				var tick = hx_getObject(id+"_tick_"+i);
				var label = hx_getObject(id+"_label_"+i);

				temp = (obj.left + i*obj.tick_tabs + (obj.stylus_width/2));
				if(temp > obj.left + obj.scale_width) {
					temp = obj.left + obj.scale_width;
				}
				tick.style.left = temp + "px";
				tick.style.top = (obj.top + obj.stylus_height) + "px";
				label.style.left = temp + "px";
				label.style.top = (obj.top + obj.stylus_height + obj.tick_height) + "px";
			}
		}
    }
    else if(hx_nn4) {
		window.document.layers[id+"_scale"].left = obj.left + (obj.stylus_width/2);
		window.document.layers[id+"_scale"].top = obj.top + (obj.stylus_height/2) - (obj.scale_height/2);
		window.document.layers[id+"_stylus"].left = obj.left + obj.start_tick*obj.tick_tabs;
		window.document.layers[id+"_stylus"].top = obj.top;

		for (i=0; i<obj.ticks; i++) {
			if(obj.show_labels || i == 0 || i == obj.ticks - 1 ) {
				window.document.layers[id+"_tick_"+i].left = (obj.left + i*obj.tick_tabs + (obj.stylus_width/2)) + "px";
				window.document.layers[id+"_tick_"+i].top = (obj.top + obj.stylus_height) + "px";
				window.document.layers[id+"_label_"+i].left = (obj.left + i*obj.tick_tabs + (obj.stylus_width/2)) + "px";
				window.document.layers[id+"_label_"+i].top = (obj.top + obj.stylus_height + obj.tick_height) + "px";
			}
		}
    }
}

// ===========================================================================================
//
// Slider End
//
// ===========================================================================================

// ===========================================================================================
//
// Spinner Start
//
// ===========================================================================================

function hx_spinner_onclick(id, delta) {
    if(id != "") {
        value = window.document.forms[0][id].value;
        if(value == "") {
            value = "0";
        }
        if(!isNaN(value)) {
            window.document.forms[0][id].value = parseInt(value) + parseInt(delta);
        }
    }
}

// ===========================================================================================
//
// Spinner End
//
// ===========================================================================================

// ===========================================================================================
//
// Global functions
//
// ===========================================================================================
function hx_getObject(id)
{
    if(window.document.getElementById != null) 		// moz || dom2
		return window.document.getElementById(id);
    else if(window.document.all != null)			// ie
		return window.document.all(id);
    else if(window.document.layers != null)			// nn4
		return window.document.layers[id];
	
	return null;
}

function hx_findPosX(obj)
{
	var curleft = 0;
	if(obj.offsetParent) {
		while(obj.offsetParent) {
			curleft += obj.offsetLeft
			obj = obj.offsetParent;
		}
	}
	else if(obj.x)
		curleft += obj.x;
	return curleft;
}

function hx_findPosY(obj)
{
	var curtop = 0;
	if(obj.offsetParent) {
		while(obj.offsetParent)	{
			curtop += obj.offsetTop
			obj = obj.offsetParent;
		}
	}
	else if(obj.y)
		curtop += obj.y;
	return curtop;
}

function hx_set_image(id, img) {
	obj = hx_getObject(id);
	
	if(obj == null) {
		return;
	}
	
	obj.src = img;
}

function hx_toggle_layer(layer_str, obj_to_notify, bMove, pos_ref_obj_str, x, y)  // (string, string, string, bool, int, int)
{
	  var layer = hx_getObject(layer_str);
	  
	  if(layer == null) {
	      return;
	  }
	
	  bVisible = (layer.style.display == "none") ? false : true ;
	  bVisible = !bVisible;
	
	  layer.style.display = bVisible ? "inline" : "none"; 
	  
	  if(bMove) {
       	  var pos_ref_obj = hx_getObject(pos_ref_obj_str);
	  	  if(pos_ref_obj != null) {
		      x = hx_findPosX(pos_ref_obj) + x; 
	  	      y = hx_findPosY(pos_ref_obj) + y;
	  	  }
	      layer.style.left = x;
	      layer.style.top = y;
	  } 

	  if(obj_to_notify != null && obj_to_notify.on_layer_toggled != null) {
	  	  obj_to_notify.on_layer_toggled(bVisible, x, y);
	  }
}

// ===========================================================================================
//
// Actionbar Start
//
// ===========================================================================================

function hx_actionbar_onclick(id, id1)
{
	var obj = hx_getObject(id);
	if(obj == null)
		return;

	var bHidden = (obj.style.display == "none");
	
	if(id1 != null) {
		var table = hx_getObject(id1);
	   	for(var r = 0; r < table.rows.length; r++) {
	  		if(r != 0) {
	  			var child = hx_actionbar_find_nested_object(table.rows[r], "TABLE");
	  			if(child != null) {
		        	if(child.nodeName == "TABLE") {
			         	child.style.display = "none";
			        }
	            }
		  	}
		}	
	}
		
	if(bHidden) {
		obj.style.display ="inline";
	}
	else {
		obj.style.display ="none";
	}
}

function hx_actionbar_find_nested_object(obj, tag)
{
	if(obj == null || obj.childNodes == null || obj.childNodes.length == 0)
		return null;
		
	children = obj.childNodes;
	for(var i = 0; i < children.length; i++) {
		child = children[i];
		if(child.nodeName == tag)
			return child;
	}
	for(var i = 0; i < children.length; i++) {
		child = children[i];
		nested_child = hx_actionbar_find_nested_object(child, tag);
		if(nested_child != null) {
			return nested_child;
		}
	}
	return null;
}
// ===========================================================================================
//
// Actionbar End
//
// ===========================================================================================

////////////////////////////////////////////////////////////////////////////
// DATAGRID SELECTION CLASSES - START - A McDowell
////////////////////////////////////////////////////////////////////////////

////class HxSelection////

/**
*Class for holding selection information and notifying selection listeners of events.
*/
function HxSelection(bSelected)
{
	this.bSelected = bSelected;

	this.arrSelectionListeners = new Array();
}

/**
*Set selected true/false
*/
HxSelection.prototype.setSelected = function (bSelected)
{
	if(this.bSelected==bSelected)
	{
		return;
	}

	this.bSelected = bSelected;

	//notify listeners of change
	this.fireSelectionListeners();
}

/**
*Set selected true/false
*/
HxSelection.prototype.isSelected = function ()
{
	return this.bSelected;
}

/**
*Add a listener
*Must provide a method selectionChanged(objHxSelection)
*/
HxSelection.prototype.addSelectionListener = function (objListener)
{
	if(!objListener)
	{
		alert("RUNTIME ERROR: null SelectionListener added to HxSelection");
	}
	if(!objListener.selectionChanged)
	{
		alert("RUNTIME ERROR: SelectionListener does not proide selectionChanged method");
	}
	
	this.arrSelectionListeners[this.arrSelectionListeners.length] = objListener;
}

/**
*Fires any listeners listening to object changes
*/
HxSelection.prototype.fireSelectionListeners = function ()
{
	var len = this.arrSelectionListeners.length;
	for(var i=0; i<len; i++)
	{
		this.arrSelectionListeners[i].selectionChanged(this);
	}
}

////class HxStatisticsListener////

/**
*Class for listening to a HxSelection.
*Will update div tag contents with statistic info.
*/
function HxStatisticsListener(strDivId, nSelCount)
{
	if(!document.getElementById)
	{
		//then don't attempt to make live
		return;
	}

	this.nSelCount = nSelCount;

	var objDiv = document.getElementById(strDivId);
	if(!objDiv)
	{
		alert("RUNTIME ERROR: "+strDivId+" not found.");
		return;
	}

	this.objText = objDiv.firstChild;
}

HxStatisticsListener.prototype.selectionChanged = function (objHxSelection)
{
	if(objHxSelection.isSelected())
	{
		this.nSelCount++;
	}
	else
	{
		this.nSelCount--;
	}

	this.objText.data = this.nSelCount;
}

////class HxCheckboxListener////

/**
*Class for listening to a check box.
*Will notify the HxSelection object of changes.
*Will add itself as a listener for changes in the HxSelection; DO NOT REGISTER AS LISTENER MANUALLY.
*/
function HxCheckboxListener(strCheckboxId, objSelection)
{
	if(!document.getElementById)
	{
		//then this isn't going to work
		return;
	}

	this.objCheckbox = document.getElementById(strCheckboxId);
	if(!this.objCheckbox)
	{
		alert("RUNTIME ERROR: checkbox "+strCheckboxId+" cannot be found");
		return;
	}

	this.objSelection = objSelection;

	this.bindToDom();
	
	//remember the selection object + add this as a Listener
	this.objSelection = objSelection;
	this.objSelection.addSelectionListener(this);
}

HxCheckboxListener.prototype.bindToDom = function ()
{
	var thisObj = this;
	this.objCheckbox.onclick = function()
	{
		thisObj.onclick();
	}
}

HxCheckboxListener.prototype.isSelected = function ()
{
	return this.objCheckbox.checked;
}

HxCheckboxListener.prototype.setSelected = function (bSelected)
{
	this.objCheckbox.checked = bSelected;
}

HxCheckboxListener.prototype.onclick = function ()
{
	this.objSelection.setSelected(this.isSelected());
}

HxCheckboxListener.prototype.selectionChanged = function (objHxSelection)
{
	if(objHxSelection.isSelected() != this.isSelected())
	{
		this.setSelected( objHxSelection.isSelected() );
	}
}

////class HxStyleListener////

/**
*Class for listening to a HxSelection + changing CSS styles according to changes
*/
function HxStyleListener(strElementId, strUnselectedCss, strSelectedCss)
{
	if(!document.getElementById)
	{
		return;
	}

	this.objElement = document.getElementById(strElementId);
	if(!this.objElement)
	{
		alert("RUNTIME ERROR: element id="+strElementId+" cannot be found");
	}
	this.strUnselectedCss = strUnselectedCss;
	this.strSelectedCss = strSelectedCss;
}

HxStyleListener.prototype.setStyle = function (strStyleCss)
{
	this.objElement.className = strStyleCss;
}

HxStyleListener.prototype.selectionChanged = function (objHxSelection)
{
	if(objHxSelection.isSelected())
	{
		this.setStyle( this.strSelectedCss );
	}
	else
	{
		this.setStyle( this.strUnselectedCss );
	}
}

////class HxCheckImageListener////

function HxCheckImageListener(strImgId, strUnselectedUrl, strSelectedUrl, strUnselected, strSelected)
{
	if(!document.getElementById)
	{
		return;
	}

	this.objImg = document.getElementById(strImgId);
	if(this.objImg==null)
	{
		alert("RUNTIME ERROR: cannot find item to set background on: "+strImgId);
	}
	this.strUnselectedUrl = strUnselectedUrl;
	this.strSelectedUrl = strSelectedUrl;
	this.strUnselected = strUnselected;
	this.strSelected = strSelected;
}

HxCheckImageListener.prototype.selectionChanged = function (objHxSelection)
{
	if(objHxSelection.isSelected())
	{
		//this.objImg.style.backgroundImage = "url(\""+this.strSelectedUrl+"\")";
		this.objImg.src = this.strSelectedUrl;
		this.objImg.alt = this.strSelected;
	}
	else
	{
		//this.objImg.style.backgroundImage = "url(\""+this.strUnselectedUrl+"\")";
		this.objImg.src = this.strUnselectedUrl;
		this.objImg.alt = this.strUnselected;
	}
}

////class HxCheckCellListener/////

/**
*Class for handling Notes-style select (drag-select).
*/
function HxCheckCellListener(strTdId, strInputId, objGroup, objHxSelection){
	if(!document.getElementById)
	{
		return;
	}

	this.objTd = document.getElementById(strTdId);
	if(!this.objTd)
	{
		alert("RUNTIME ERROR: TD element "+strTdId+" cannot be found");
	}
	
	this.objInput = document.getElementById(strInputId);
	if(!this.objInput)
	{
		alert("RUNTIME ERROR: INPUT element "+strInputId+" cannot be found");
	}
	
	this.objGroup = objGroup;
	this.objHxSelection = objHxSelection;

	this.bindToDom();

	this.objGroup.addMember(this);
}

/**
*Binds to DOM object.
*/
HxCheckCellListener.prototype.bindToDom = function()
{
	var thisObj = this;
	this.objTd.onmousedown = function (evt)
	{
		if(!evt)
		{
			var evt = window.event;
		}
		thisObj.onMouseDown(evt);

		return false;
	}

	this.objTd.onclick = function (evt)
	{
		return false;
	}

	this.objTd.onkeypress = function (evt)
	{
		if(!evt)
		{
			var evt = window.event;
		}
		thisObj.onKey(evt);
		//return false;
	}
}

/**
*User pressed button on cell.
*/
HxCheckCellListener.prototype.onMouseDown = function(evt)
{
	this.objHxSelection.setSelected( !this.objHxSelection.isSelected() );

	this.setSelected(this.objHxSelection.isSelected());
	this.objGroup.fireMouseDown(evt, this.objHxSelection.isSelected());
}

/**
*User pressed key.
*/
HxCheckCellListener.prototype.onKey = function(evt)
{
	var nKey = evt.which | evt.keyCode;
	if(nKey!=32) //space
	{
		return;
	}
	this.objHxSelection.setSelected( !this.objHxSelection.isSelected() );
	this.setSelected(this.objHxSelection.isSelected());
}

/**
*Set selected/unselected on click (toggle).
*/
HxCheckCellListener.prototype.setSelected = function(bSelected)
{
	this.objHxSelection.setSelected(bSelected);
	this.objInput.value = bSelected;
}

/**
*Is selected?
*/
HxCheckCellListener.prototype.isSelected = function()
{
	return this.objHxSelection.isSelected();
}

/**
*Is the mouse inside this element?
*/
HxCheckCellListener.prototype.isInElementBoundary = function(mousex, mousey)
{
	var x = this.findPosX(this.objTd);
	if(mousex<x)
	{
		return false;
	}

	var y = this.findPosY(this.objTd);
	if(mousey<y)
	{
		return false;
	}

	y += this.objTd.offsetHeight;
	if(mousey>y)
	{
		return false;
	}
	
	x += this.objTd.offsetWidth;
	if(mousex>x)
	{
		return false;
	}
	
	return true;
}

/**
*Find the x position of given element.
*/
HxCheckCellListener.prototype.findPosX = function (obj)
{
	var curleft = 0;
	if (obj.offsetParent)
	{
		while (obj.offsetParent)
		{
			curleft += obj.offsetLeft
			obj = obj.offsetParent;
		}
	}
	else if (obj.x)
	{
		curleft += obj.x;
	}
	return curleft;
}

/**
*Find the y position of given element.
*/
HxCheckCellListener.prototype.findPosY = function (obj)
{
	var curtop = 0;
	if (obj.offsetParent)
	{
		while (obj.offsetParent)
		{
			curtop += obj.offsetTop
			obj = obj.offsetParent;
		}
	}
	else if (obj.y)
	{
		curtop += obj.y;
	}
	return curtop;
}

////class HxCheckCellGroup////

/**
*Class for holding a group of HxCheckCellListener objects + methods for their interaction.
*/
function HxCheckCellGroup()
{
	this.arrayMembers = new Array();
	this.bSelected = true;

	if(!document.getElementById)
	{
		return;
	}

	/*this.objPointer = document.getElementById(strPointerId)
	if(!this.objPointer)
	{
		alert("RUNTIME ERROR: pointer image id="+strPointerId+" could not be found");
	}*/
}

/**Static variable*/
HxCheckCellGroup.prototype.GROUP = null;

/**
*Adds a child to the group.
*/
HxCheckCellGroup.prototype.addMember = function (objHxCheckCellListener)
{
	this.arrayMembers[this.arrayMembers.length] = objHxCheckCellListener;
}

/**
*Returns the mode we're setting (selected/unselected).
*/
HxCheckCellGroup.prototype.isSelected = function()
{
	return this.bSelected;
}

/**
*Sets the mode we're going to pass on the drag.
*/
HxCheckCellGroup.prototype.setSelected = function(bSelected)
{
	this.bSelected = bSelected;
}

/**
*Fired by HxCheckCellListener child.
*/
HxCheckCellGroup.prototype.fireMouseDown = function(evt, bSelected)
{
	this.setSelected(bSelected);
	HxCheckCellGroup.prototype.GROUP = this;
	this.registerWindowListeners(evt);
}

/**
*Adds some global listeners to the document.
*/
HxCheckCellGroup.prototype.registerWindowListeners = function(evt)
{
	if(document.attachEvent)
	{
		document.attachEvent("onmousemove", HxCheckCellGroup.prototype.onMouseEnterStatic);
		document.attachEvent("onmouseup",   HxCheckCellGroup.prototype.onMouseUpStatic);
		document.attachEvent("onselectstart",   HxCheckCellGroup.prototype.onSelectStartStatic);
		window.event.cancelBubble = true;
		window.event.returnValue = false;
	}
	else if(document.addEventListener)
	{
		document.addEventListener("mousemove", HxCheckCellGroup.prototype.onMouseEnterStatic, true);
		document.addEventListener("mouseup",   HxCheckCellGroup.prototype.onMouseUpStatic, true);
		evt.preventDefault();
	}
}

/**
*Method for preventing ugly selection of text + other unwanted effects.
*/
HxCheckCellGroup.prototype.onSelectStartStatic = function(evt)
{
	return false;
}

/**
*Static method for handling drag events (mousemove/onmousemove).
*/
HxCheckCellGroup.prototype.onMouseEnterStatic = function(evt)
{
	if(!evt)
	{
		var evt = window.event;
	}

	HxCheckCellGroup.prototype.GROUP.onMouseEnter(evt);
}

/**
*Instance handling of mousemove/onmousemove events.
*/
HxCheckCellGroup.prototype.onMouseEnter = function(evt)
{
	var len = this.arrayMembers.length;
	for(var i=0; i<len; i++)
	{
		var mousex = 0;
		var mousey = 0;
		if(evt.pageX)
		{
			mousex = evt.pageX;
			mousey = evt.pageY;
		}
		else if(evt.clientX)
		{
			mousex = evt.clientX + document.body.scrollLeft;
			mousey = evt.clientY + document.body.scrollTop;
		}
		if(this.arrayMembers[i].isInElementBoundary(mousex, mousey))
		{
			this.arrayMembers[i].setSelected( this.isSelected() );
			return;
		}
	}
}

/**
*Mouse button lifted, kill the events.
*/
HxCheckCellGroup.prototype.onMouseUpStatic = function(evt)
{
	if(!evt)
	{
		var evt = window.event;
	}

	if(document.detachEvent)
	{
		document.detachEvent("onmousemove", HxCheckCellGroup.prototype.onMouseEnterStatic);
		document.detachEvent("onmouseup", HxCheckCellGroup.prototype.onMouseUpStatic);
		document.detachEvent("onselectstart",   HxCheckCellGroup.prototype.onSelectStartStatic);
	}
	else if(document.removeEventListener)
	{
		document.removeEventListener("mousemove", HxCheckCellGroup.prototype.onMouseEnterStatic, true);
		document.removeEventListener("mouseup", HxCheckCellGroup.prototype.onMouseUpStatic, true);
	}

	HxCheckCellGroup.prototype.GROUP = null;
}

/**
*Select all method
*/
HxCheckCellGroup.prototype.selectAll = function()
{
	var len = this.arrayMembers.length;
	for(var i=0; i<len; i++)
	{
		this.arrayMembers[i].setSelected( true );
	}
}

/**
*Select none method
*/
HxCheckCellGroup.prototype.selectNone = function()
{
	var len = this.arrayMembers.length;
	for(var i=0; i<len; i++)
	{
		this.arrayMembers[i].setSelected( false );
	}
}

/**
*Select inverse method
*/
HxCheckCellGroup.prototype.selectInverse = function()
{
	var len = this.arrayMembers.length;
	for(var i=0; i<len; i++)
	{
		this.arrayMembers[i].setSelected( !this.arrayMembers[i].isSelected() );
	}
}

//////////////class HxFocusChanger/////////////

/**
*Class for adding focus traversal to an element.
*/
function HxFocusChanger(element, nPrev, nNext)
{
	if(!document.getElementById)
	{
		//failsafe
		return;
	}

	this.objThis = this.findFocusableElement(element);
	this.nNext = nNext;
	this.nPrev = nPrev;

	if(this.objThis==null)
	{
		return;
	}

	this.bindToDom(this.objThis);
}

/**
*Add our listener to the DOM element
*/
HxFocusChanger.prototype.bindToDom = function(objThis)
{
	if(objThis.onkeyup != null)
	{
		this.anotherKeyListener = objThis.onkeyup;
	}

	var thisObject = this;

	objThis.onkeyup = function(evt)
	{
		if(!evt)
		{
			var evt = window.event;
		}

		thisObject.onkeyup(evt);

		if(thisObject.anotherKeyListener!=null)
		{
			var fn = objThis.onkeyup;
			objThis.onkeyup = thisObject.anotherKeyListener;
			objThis.onkeyup(evt);
			objThis.onkeyup = fn;
		}
	}
}

HxFocusChanger.prototype.onkeyup = function(evt)
{
	var nKey = evt.which | evt.keyCode;
	var objGoto = null;

	if(nKey == this.nNext)
	{
		objGoto = this.findPeer(this.objThis, true);
	}
	else if(nKey == this.nPrev)
	{
		objGoto = this.findPeer(this.objThis, false);
	}
	else
	{
		return;
	}

	if(objGoto!=null)
	{
		objGoto.focus();
	}
}

/**
*Finds the element that is going to take focus
*/
HxFocusChanger.prototype.findFocusableElement = function(el)
{
	if(el==null)
	{
		return null;
	}

	var obj = el;
	if( typeof(el) == "string" )
	{
		obj = document.getElementById(el);
	}

	if(obj==null)
	{
		alert("RUNTIME ERROR: cannot find: "+strId);
	}

	if(!obj.focus)
	{
		//then this isn't going to work; fail
		return null;
	}

	//TODO - more checks?

	return obj;
}

HxFocusChanger.prototype.findPeer = function(objElement, bNext)
{
	var tdObj = this.getParentOfType(objElement, "TD");
	var trObj = this.getParentOfType(objElement, "TR");
	var objRowContainer = trObj.parentNode;
	
	if( (tdObj==null) || (trObj==null) || (objRowContainer==null) )
	{
		return null;
	}

	var nIdx = this.getIndexInParent(trObj);
	while(true)
	{
		if(bNext)
		{
			nIdx++;
		}
		else
		{
			nIdx--;
		}

		var objSibling = this.getChildAtIndex(objRowContainer, nIdx);
		if(objSibling==null)
		{
			return null;
		}

		if( !this.isVisible(objSibling) )
		{
			continue;
		}

		var objTdPeer = this.findTdPeer(tdObj, objSibling);
		if(objTdPeer==null)
		{
			continue;
		}

		var objLike = this.findLike(objElement, objTdPeer);
		if(objLike!=null)
		{
			return objLike;
		}
	}

	return null;
}

HxFocusChanger.prototype.findTdPeer = function(tdFindPeer, trIn)
{
	var nIdx = this.getIndexInParent(tdFindPeer);
	return this.getChildAtIndex(trIn, nIdx);
}

HxFocusChanger.prototype.findLike = function(objFindLike, objIn)
{
	var nIdx = this.getIndexInParent(objFindLike);
	var objPotentialPeer = this.getChildAtIndex(objIn, nIdx);
	if(objPotentialPeer!=null)
	{
		if( this.isViableTarget(objPotentialPeer) )
		{
			return objPotentialPeer;
		}
	}

	var iterator = new  HxDomIterator(objIn);
	while(iterator.hasNext())
	{
		var objNext = iterator.next();
		if( this.isViableTarget(objNext) )
		{
			return objNext;
		}
	}
	return null;
}

HxFocusChanger.prototype.getChildAtIndex = function(objElement, nIdx)
{
	if(nIdx<0)
	{
		return null;
	}
	if(objElement==null)
	{
		return null;
	}
	if(nIdx >= objElement.childNodes.length)
	{
		return null;
	}
	return objElement.childNodes[nIdx];
}

HxFocusChanger.prototype.getIndexInParent = function(objElement)
{
	var objParent = objElement.parentNode;
	for(var i=0; i<objParent.childNodes.length; i++)
	{
		if(objElement==objParent.childNodes[i])
		{
			return i;
		}
	}
	return -1;
}

HxFocusChanger.prototype.getParentOfType = function(node, strType)
{
	if((node == null) || (strType==null))
	{
		return null;
	}

	var parent = node.parentNode;
	while(parent!=null)
	{
		if(parent.nodeName==strType)
		{
			return parent;
		}
		parent = parent.parentNode;
	}
	
	return null;
}

HxFocusChanger.prototype.isViableTarget = function(objElement)
{
	return this.isFocusable(objElement) && this.isVisible(objElement);
}

HxFocusChanger.prototype.isFocusable = function(objElement)
{
	if(objElement==null)
	{
		return false;
	}

	if(!objElement.focus)
	{
		return false;
	}

	var nodeName = objElement.nodeName;
	if(nodeName == "INPUT")
		return true;
	if(nodeName == "BUTTON")
		return true;
	if(nodeName == "A")
		return true;

	return false;
}

HxFocusChanger.prototype.isVisible = function(objElement)
{
	if(objElement.style)
	{
		if(objElement.style.visibility=="hidden")
		{
			return false;
		}
		if(objElement.style.display=="none")
		{
			return false;
		}
	}
	var parent = objElement.parentNode;
	if(parent==null)
	{
		return true;
	}
	return this.isVisible(parent);
}

//////class HxDomIterator/////////

/**
*Class for iterating throw an element and its children.
*Elements are returned in order:
*1. element
*2. children
*3. siblings
*@param	element	the DOM element or its String id attribute
*/
function HxDomIterator(element)
{
	this.index = 0;
	this.root = this.findElement(element);
	if(this.root==null)
	{
		//failsafe
		return;
	}

	this.stack = new Array();
	
	this.pushToStack(this.root);
}

HxDomIterator.prototype.pushToStack = function(obj)
{
	//push not supported in IE pre 5.5
	//this.stack.push(this.root);
	this.stack[this.index++] = obj;
}

HxDomIterator.prototype.popFromStack = function()
{
	//return this.stack.pop();
	return this.stack[this.index--];
}

/**
*Init method for locating the element.
*/
HxDomIterator.prototype.findElement = function(element)
{
	var ret = element;

	if(typeof(element) == "string" )
	{
		if(!document.getElementById)
		{
			return null;
		}
		ret = document.getElementById(element);
		if(!ret)
		{
			alert("RUNTIMER ERROR: cannot find "+element);
		}
	}

	return ret;
}

/**
*Has next.
*/
HxDomIterator.prototype.hasNext = function()
{
	//return (this.stack.length!=0);
	return (this.index>0);
}

/**
*The next DOM element.
*/
HxDomIterator.prototype.next = function()
{
	var obj = this.popFromStack();

	if(obj==null)
	{
		return null;
	}

	if(obj.nextSibling)
	{
		this.pushToStack(obj.nextSibling);
	}
	if(obj.firstChild)
	{
		this.pushToStack(obj.firstChild);
	}

	return obj;
}

////////////class HxFocusAdder////////////////

function HxFocusAdder(strTableId, nPrev, nNext)
{
	if(!document.getElementById)
	{
		//failsafe
		return;
	}
	
	var objTable = document.getElementById(strTableId);
	if(objTable==null)
	{
		return null;
	}
	
	var arrElements = objTable.getElementsByTagName("TD");
	for(var i=0; i<arrElements.length; i++)
	{
		var iterator = new HxDomIterator(arrElements[i]);
		while(iterator.hasNext())
		{
			var element = iterator.next();
			if(this.isFocusable(element))
			{
				new HxFocusChanger(element, nPrev, nNext);
			}
		}
	}
}

HxFocusAdder.prototype.isFocusable = HxFocusChanger.prototype.isFocusable;




/**static cursor up keycode*/
HxFocusAdder.prototype.UP = 38;
/**static cursor down keycode*/
HxFocusAdder.prototype.DOWN = 40;

//////////////////class HxRowActionListener////////////////

function HxRowActionListener(strHiddenId, strTrId, nIdx)
{
	if(!document.getElementById)
	{
		return;
	}

	this.objHidden = this.findElement(strHiddenId);
	this.objTr = this.findElement(strTrId);
	this.objForm = this.getParentOfType(this.objTr, "FORM");
	if(this.objForm==null) return;
	this.nIdx = nIdx;

	this.bindToDOM();
	//this.setCursor();
}

HxRowActionListener.prototype.findElement = function(strId)
{
	var obj = document.getElementById(strId);
	if(obj==null)
	{
		alert("RUNTIME ERROR: cannot find element "+strId);
	}
	return obj;
}

HxRowActionListener.prototype.bindToDOM = function()
{
	var thisObj = this;

	this.objTr.onclick = function(evt)
	{
		if(!evt)
		{
			var evt = window.evt;
		}
		thisObj.onClick(evt);
	}
}

HxRowActionListener.prototype.getParentOfType = function(node, strType)
{
	if((node == null) || (strType==null))
	{
		return null;
	}

	var parent = node.parentNode;
	while(parent!=null)
	{
		if(parent.nodeName==strType)
		{
			return parent;
		}
		parent = parent.parentNode;
	}
	
	return null;
}

HxRowActionListener.prototype.onClick = function(evt)
{
	this.objHidden.value = this.nIdx;
	this.objForm.submit();
}

//HxRowActionListener.prototype.setCursor = function()
//{
//	return;
//
//	if( (this.objTr.style) && (this.objTr.style.cursor) )
//	{
//		if(!this.isBrowserIePre6())
//		{
//			//then W3C
//			this.objTr.style.cursor = "pointer";
//		}
//	}
//}

//HxRowActionListener.prototype.isBrowserIePre6 = function()
//{
//	if(!hx_Env.browser.isIE())
//		return false;
//	if(!hx_Env.browser.isBrowserLevel(6))
//		return false;
//	return true;
//}

///////////class HxGridShow///////////////
			
/*function HxGridShow(strShowId, strHideId, strAId)
{
	if(!document.getElementById)
	{
		return;
	}
	this.objShow = this.findElement(strShowId);
	this.objHide = this.findElement(strHideId);
	this.objA = this.findElement(strAId);

	this.bindToDOM();
}

HxGridShow.prototype.findElement = function(strId)
{
	var obj = document.getElementById(strId);
	if(obj==null)
	{
		alert("RUNTIME ERROR: cannot find element "+strId);
	}
	return obj;
}

HxGridShow.prototype.bindToDOM = function()
{
	var thisObj = this;

	this.objA.onclick = function(evt)
	{
		if(!evt)
		{
			var evt = window.event;
		}
		thisObj.onClick(evt);
		return false;
	}
}

HxGridShow.prototype.onClick = function(evt)
{
	this.objShow.style.display = "inline";
	this.objHide.style.display = "none";
}*/

//////////class HxEditSave////////////////

function HxEditSave(strHidId, strAId, nIdx)
{
	if(!document.getElementById)
	{
		return;
	}

	this.objHidden = this.findElement(strHidId);
	this.objA = this.findElement(strAId);
	this.nIdx = nIdx;
	this.objForm = this.getParentOfType(this.objA, "FORM");
	if(this.objForm==null)
	{
		return;
	}

	this.bindToDOM();
}

HxEditSave.prototype.findElement = function(strId)
{
	var obj = document.getElementById(strId);
	if(obj==null)
	{
		alert("RUNTIME ERROR: cannot find element "+strId);
	}
	return obj;
}

HxEditSave.prototype.bindToDOM = function()
{
	var thisObj = this;

	this.objA.onclick = function(evt)
	{
		if(!evt)
		{
			var evt = window.evt;
		}
		thisObj.onClick(evt);
	}
}

HxEditSave.prototype.getParentOfType = HxRowActionListener.prototype.getParentOfType;

HxEditSave.prototype.onClick = function(evt)
{
	this.objHidden.value = this.nIdx;
	this.objForm.submit();
}

///////////////class HxGridEdit/////////////////////

function HxGridEdit()
{
	this.editRows = new Array();
	this.viewRows = new Array();
}

HxGridEdit.prototype.addRow = function(strTrId, strTrEditId, strAEditId, strACancelId)
{
	if(!document.getElementById)
	{
		return;
	}

	this.bindToCancel(strACancelId);
	this.bindToEdit(strTrId, strTrEditId, strAEditId);

	this.viewRows[this.viewRows.length] = strTrId;
	this.editRows[this.editRows.length] = strTrEditId;
}

HxGridEdit.prototype.bindToCancel = function(strACancelId)
{
	var thisObj = this;
	var objA = this.findElement(strACancelId);

	objA.onclick = function(evt)
	{
		thisObj.resetRows();
	}
}

HxGridEdit.prototype.bindToEdit = function(strTrId, strTrEditId, strAEditId)
{
	var thisObj = this;
	var objA = this.findElement(strAEditId);

	objA.onclick = function(evt)
	{
		thisObj.editRow(strTrId, strTrEditId);
	}
}

HxGridEdit.prototype.editRow = function(strTrId, strTrEditId)
{
	var objTr = this.findElement(strTrId);
	var objTrEditId = this.findElement(strTrEditId);

	this.resetRows();
	
	objTr.style.display = "none";
	//objTrEditId.style.display = "inline";
	objTrEditId.style.display = "";
}

HxGridEdit.prototype.findElement = function(strId)
{
	var obj = document.getElementById(strId);
	if(obj==null)
	{
		alert("RUNTIME ERROR: cannot find element "+strId);
	}
	return obj;
}

HxGridEdit.prototype.resetRows = function()
{
	for(var row in this.editRows)
	{
		var obj = this.findElement(this.editRows[row]);
		obj.style.display = "none";
	}
	for(var row in this.viewRows)
	{
		var obj = this.findElement(this.viewRows[row]);
		//obj.style.display = "inline";
		obj.style.display = "";
	}
}

////////////////////////////////////////////////////////////////////////////
// DATAGRID SELECTION CLASSES - END
////////////////////////////////////////////////////////////////////////////


function HxGlobalObject () {
	this.browser   = new HxBrowserManager ();
	this.resources = new HxResourceManager();

	this.resources.setAbsolutePaths();

	this.component = new Array();
		
	this.rte_string = new Array();	/* rte resource strings */
	this.dtp_string = new Array();	/* date picker strings  */
}

var hx_Env = new HxGlobalObject();

Hx_RT_COMPONENT = 0;
Hx_DP_COMPONENT = 1;
Hx_TC_COMPONENT = 2;

HxGlobalObject.prototype.registerComponent = function (oComponent, aResourceStrings, nId)
{
	if (oComponent != null)
		this.component[this.component.length] = oComponent;
	
	switch(nId)
	{
		case Hx_RT_COMPONENT:
			if (aResourceStrings != null && this.rte_string != null)
				this.rte_string = aResourceStrings;
			break;
			
		case Hx_DP_COMPONENT:
			if (aResourceStrings != null && this.dtp_string != null) {
				this.dtp_string = aResourceStrings;
			}
			HxInitGlobalCalendarArrays();
			break;
			
		case Hx_TC_COMPONENT:
			break; // no resources required as yet	
			
		default:
			break;
	}
}

/*
 * NB! This function assigns a snapshot of the resource string arrays to the various components.
 * It is the job of the component renderer to set up these resources arrays automatically.  This
 * function should only be called if the resource arrays cannot be found, e.g. bug in the renderer.
 * Also this allows us to use this library outside of JSF, say for standalone testing/debugging.
 * So... DO NOT TRANSLATE THESE STRINGS.  They are a default EN-US fallback.  They may also be
 * stale if not periodically synched with the backend runtimeibm.properties definitions - MWD.
 *
 */
HxGlobalObject.prototype.initResource = function (nId) {
	switch(nId)
	{
		case Hx_RT_COMPONENT:
			this.rte_string = new Array("Monospace","Serif","Sans Serif","Align","Bold","Heading","Indent","Insert graphic text with special effects","Insert image","Insert link","Normal","Heading 1","Numbered list","Outdent","Spell check","Text color","Underline","Italic","Unordered list","Select some text before you add a link.");
			break;
			
		case Hx_DP_COMPONENT:
			this.dtp_string = new Array("January","February","March","April","May","June","July","August","September","October","November","December","Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","J","F","M","A","M","J","J","A","S","O","N","D","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sun","Mon","Tue","Wed","Thu","Fri","Sat","S","M","T","W","T","F","S","Previous year","Next year","Previous month","Next month","Collapse/Expand Date Navigator","Go to today in calendar","Invalid duration specified.","Invalid duration specified.  Minutes must be between 00 and 59.","Invalid duration specified.  Seconds must be between 00 and 59.");
			break;
			
		case Hx_TC_COMPONENT:
			break; // no resources required as yet	
			
		default:
			break;
	}
}

HxGlobalObject.prototype.getComponentByOrdinal = function (nOrdinal)
{
	var component = null;
	if (typeof nOrdinal == "number" &&
		nOrdinal < this.component.length &&
	    nOrdinal >= 0
	   )
		component = this.component[nOrdinal]
	return component;
}

HxGlobalObject.prototype.getComponentById = function (sId)
{
	var i, j = this.component.length;
	var component = null;

	if (sId != null && sId != "") {
		for (i = 0; i < j; i++) {
			var cmp = this.component[i];

			if (typeof cmp.Id == "undefined") {
				continue;
			}
			else if (cmp.Id == sId) {
				component = cmp;
				break;
			}
			else
				alert("DEBUG: getComponentById() bad code path!");
		}
	}
	
	return component;
}

// ========================= Bill's libs ============================
// JSFBundle.js

// Instantiate a JSF bundle of strings and other language-specific resources
// Note that only one bundle will exist on a page so in the portal case all portlets will
// use the same bundle
if (!(document.JSFbundle)){
	JSFbundle = new Object();
	JSFbundle["increment"] = "increment";
	JSFbundle["decrement"] = "decrement";
}

// JSFComponents.js

if (!(document.JSF)) JSF = new _JSF();

// -- BEGIN-- DESIRED/MISSING FEATURES LIST 
// 1.  Add (and test) Bidi handling
// -- END-- FEATURES LIST

// --------------------------
// BEGIN: the JSF object 
// --------------------------

// State maintained in the JSF object
_JSF.prototype.Components = new Object();
_JSF.prototype.Images = new Object();
_JSF.prototype.Popups = new Object();
_JSF.prototype.activecomponent = null;
_JSF.prototype.activecomponentkind = "";


// Public: Constructor
// Use:	   For portal safe code, have the page only construct one JSF object, e.g.,
//				if (!(document.JSF)) {	JSF = new _JSF();  }
//		   Also, calling JSF.init() in the onload handler for the page is required
function _JSF() {
}

// Public:  Call the initializer on the page body's onLoad event
// Use:		Can be called more than once (e.g. the onLoad can list it multiple times)
//			however, it must be called at least once after all JSF components are on the page
 _JSF.prototype.onPageLoad = function () {
 	// Initialize components
	for (varobj in this.Components) {
		if (this.Components[varobj].onPageLoad) {
			this.Components[varobj].onPageLoad();
		}
	}
	// Initialize popups
	for (varobj in this.Popups) {
		if (this.Popups[varobj].onPageLoad) {
			this.Popups[varobj].onPageLoad();
		}
	}
	// Images currently don't need initializing
	return true;
}

// Public:  Resize all JSF controls and their attendant buttons, ... 
// Use:		Can be called more than once, normally this routine only needs to be called
//			if the JSF controls have percentage based sizes (e.g., 80% of the page width)
//			in which case it should be added to the page's onResize handler
 _JSF.prototype.onResize = function () {
 	// Initialize components
	for (varobj in this.Components) {
		if (this.Components[varobj].uirelease) {
			this.Components[varobj].uirelease();
		}
		if (this.Components[varobj].onRedraw) {
			this.Components[varobj].onRedraw();
		}
	}
	for (varobj in this.Popups) {
		if (this.Popups[varobj].uirelease) {
			this.Popups[varobj].uirelease();
		}
	}
	return true;
	// Images currently don't need initializing
	return true;
}

// Public: Release any popups that are visible or any other UI'isms that we want to clear out
_JSF.prototype.uirelease = function () {
	for (varobj in this.Components) {
		if (this.Components[varobj].uirelease) {
			this.Components[varobj].uirelease();
		}
	}
	for (varobj in this.Popups) {
		if (this.Popups[varobj].uirelease) {
			this.Popups[varobj].uirelease();
		}
	}
	return true;
}

// Public:  Compute what level of DOM/CSS is available
_JSF.prototype.isBrowserType = function(CSStype) {
	// Input:  CSStype = "CSS"	 - means Javascript, moderately modern DOM
	//		   			 "W3C"   - means full W3C DOM and script, may not be compliant
	//					 "W3C-STD" - means fully compliant W3C DOM (that is W3C is true and it's Netscape or IE6CSS)
	//		   			 "Netscape" - means any version of Netscape
	//					 "IE"		- means any version of IE
	//					 "IE6CSS"   - means IE6 running W3C Standard compliant mode (not the normal case)
	// Returns: true or false
	if (document.images) {
		var JSFisCSS = (document.body && document.body.style) ? true : false;
		if (CSStype == "CSS") {
			return JSFisCSS;
		} else if (CSStype == "W3C" || CSStype == "W3C-STD") {
			var W3C = ((JSFisCSS && document.getElementById)  ? true : false);
			if (W3C && CSStype == "W3C-STD") {
				if (navigator.appName.indexOf("Netscape") != -1) {
					W3C = true;
				} else {
					W3C = ((document.compatMode && document.compatMode.indexOf("CSS1") >= 0) ? true : false);
				}
			}
			return W3C;
		} else if (CSStype == "Netscape") {
			return ((navigator.appName.indexOf("Netscape") != -1) ? true: false);
		} else if (CSStype == "IE") {
			return ((navigator.appName.indexOf("Microsoft") != -1) ? true: false);
		} else if (CSStype == "IE6CSS") {
			return ((document.compatMode && document.compatMode.indexOf("CSS1") >= 0) ? true : false);
		}
	}
	return false;
}

// Public:  Given an object's DOM id (a string from "id=") look up the DOM object.
// (If the translation has already happened, return the passed in object)
_JSF.prototype.getElementById = function(obj) {
	// If the input obj isn't already an object, it's a tag (an id name)
	// look up the tag id in the DOM tree using an appropriate (by browser) lookup
	var theObj;
	if (typeof obj == "string") {
		if (document.getElementById != null) {
			theObj = document.getElementById(obj);
		} else {
			theObj = document.all(obj);
		}
	} else {
		theObj = obj;
	}
	return theObj;
}

// Public:  Add a component to the JSF object 
_JSF.prototype.addComponent = function(name, obj) {
	if (name && obj && this.isBrowserType("W3C") ) {
		obj.id = name;
		this.Components[name] = obj;
		return true;
	}
	return false;	
}

// Public:  Add an image to the JSF object
_JSF.prototype.addImage = function(name, normal, moused, depressed, width, height) {
	if (name && normal && this.isBrowserType("CSS")) {
		this.Images[name] = new this.JSFImage(normal, moused, depressed, width, height);
		return true;
	}
	return false;	
}

_JSF.prototype.JSFImage = function (normal, moused, depressed, width, height) {
	if (width && height) {
		if (normal) {
			this.imageNormal = new Image(width, height);
			this.imageNormal.src = normal;
		}
		if (moused) {
			this.imageMoused = new Image(width, height);
			this.imageMoused.src = moused;
		}
		if (depressed) {
			this.imageDepressed = new Image(width, height);
			this.imageDepressed.src = depressed;
		}
	} else {
		if (normal) {
			this.imageNormal = new Image();
			this.imageNormal.src = normal;
		}
		if (moused) {
			this.imageMoused = new Image();
			this.imageMoused.src = moused;
		}
		if (depressed) {
			this.imageDepressed = new Image();
			this.imageDepressed.src = depressed;
		}
	}
}

// Public:  Add an popup to the JSF object
_JSF.prototype.addPopup = function(name, defwidth, defheight, srcvalue) {
	if (name && this.isBrowserType("W3C")) {
		// Only add the popup if it doesn't exist
		if (this.Popups[name]) {
			// currently don't need to re-init the popup if it exists
		} else {
			// Construct a new popup object
			var obj = new this.JSFPopup(name, defwidth, defheight, srcvalue);
			this.Popups[name] = obj;
		}
		return true;
	}
	return false;	
}

// Public:  Given an object's DOM id (a string from "id=") look up the data component object.
// (If the translation has already happened, return the passed in object)
_JSF.prototype.getComponentById = function(obj) {
	if (typeof obj == "string")  {
		return(this.Components[obj]);
	}
	return (obj);
}

// Public:  Given an image's DOM id (a string from "id=") look up the image object.
// (If the translation has already happened, return the passed in object)
_JSF.prototype.getImageById = function(obj) {
	if (typeof obj == "string")  {
		return(this.Images[obj]);
	}
	return (obj);
}

// Public:  Swap an image in the document with the image in a different state.
// 			States are "normal", "moused", "depressed"
_JSF.prototype.swapImage = function(obj, state) {
	var theDOMObj = this.getElementById (obj);
	var theJSFObj = this.getImageById (obj);
	if (document.images && theDOMObj && theJSFObj)  {
		var theImage = theJSFObj.imageNormal;
		if (state == "depressed") {
			theImage = theJSFObj.imageDepressed;
		} else if (state == "moused") {
			theImage = theJSFObj.imageMoused;
		}
		if (theImage) {
			document.images[obj].src = theImage.src;
			return true;
		}
	}
	return false;
}

// Public:  Given a popup's DOM id, look up the object.
// (If the translation has already happened, return the passed in object)
_JSF.prototype.getPopupById = function(obj) {
	if (typeof obj == "string")  {
		return(this.Popups[obj]);
	}
	return (obj);
}

// Public:  Get the absolute Top corner of an element (in pixels)
//           (Absolute means relative to the position 0,0 on the page)
_JSF.prototype.getElementAbsPosTop = function(obj) {
	var elem = this.getElementById(obj);
	rValue = elem.offsetTop;
	while (elem.offsetParent) {
		elem = elem.offsetParent;
		rValue += elem.offsetTop;
	}
	return rValue;
}	

// Public:  Get the absolute Left corner of an element (in pixels)
_JSF.prototype.getElementAbsPosLeft = function(obj) {
	var elem = this.getElementById(obj);
	rValue = elem.offsetLeft;
	while (elem.offsetParent) {
		elem = elem.offsetParent;
		rValue += elem.offsetLeft;
	}
	return rValue;
}
	

// BEGIN: JSF object: private methods

// Private: Dispatch a (DOM) event to a button (or any other tag that makes up a component object)
_JSF.prototype.dispatchAction = function(evt) {
	evt = (evt) ? evt : event;
	if (evt) {
		var item = "";
		var keycode = 0;
		var src = (evt.srcElement) ? evt.srcElement : evt.target;
		var typ = evt.type;
		if (src && src.tagName == "INPUT") {
			item = "input";
		} else if (src && src.tagName == "SPAN") {
			item = "span";
		} else {
			var sibling = src.previousSibling;
			src = src.parentNode;
			if (src && src.tagName == "SPAN") {
				item = (sibling && sibling.tagName == "BUTTON") ? "btn2" : "btn1";
				src = src.previousSibling;
			}
		}
		// Return values are returned by the dispatched action
		// normally they'll be "false" to stop event bubbling
		if (src && src.id) {
			// N.B. because of indirection, we can't use "this" here -- must use "JSF."
			var obj = JSF.getComponentById(src.id);
			if (obj) {
				return (obj.dispatchAction (item, typ, evt.keyCode));
			}
		}
	}
	// return true to let an event bubble if no dispatch is found
	return true;
}

// Private: Dispatch a timer to a component object
_JSF.prototype.dispatchTimer = function(obj, item, action) {
	elem = this.getComponentById(obj);
	if (elem) {
		elem.dispatchTimer(item, action);	
		return true;		
	}
	return false;
}

// Private:  Set which component is UI active.  Used the UI can draw
// in a state that needs to be "undone" based on a later event.  For example,
// if a button draws "depressed" on a mouse-down event, then it's active until the
// mouse-up event happens or the UI active state is cancelled
_JSF.prototype.setActiveComponent = function(obj, kind) {
	if (obj) {
		this.activecomponent = obj;
		this.activecomponentkind = kind;
	} else {
		this.activecomponent = "";
		this.activecomponentkind = "";
	}
	return true;
}

// Private: Get which component is UI active
_JSF.prototype.getActiveComponent = function(kind) {
	if (this.activecomponent && typeof(this.activecomponent) != "string") {
		if (this.activecomponentkind == kind) {
			return this.activecomponent;
		}
	}
	return;
}

// Private: Get the actual (effective) style value, that is, style after CSS is applied to the object
_JSF.prototype.getEffectiveStyle = function (obj, stylething1, stylething2) {
// Provide the CSS name for a style (e.g., border-width) as stylething1 and omit stylething2
// If the DOM name for a style cannot be computed from the CSS name (e.g., border-width = borderWidth)
// then provide the DOM name as stylething2
	var rvalue;
	if (obj) {
		// IE
		if (obj.currentStyle) {
			var cstyle = (stylething2) ? stylething2 : this.CSStagToDOMtag (stylething1);
			rvalue = obj.currentStyle.getAttribute(cstyle);
		// W3C
		} else if (window.getComputedStyle) {
			var compStyle = window.getComputedStyle(obj, "");
			rvalue = compStyle.getPropertyValue(stylething1);
		}
	}
	return (rvalue);
}

// Helper
_JSF.prototype.CSStagToDOMtag = function(value) {
	var work = value;
	var q = work.indexOf("-");
	while (q >= 0) {
		work = work.substring(0, q) + (work.substring(q+1, q+2)).toUpperCase() + work.substring(q+2);
		q = work.indexOf("-");
	}
	return work;
}

// BEGIN: JSF object: private routines used to manage an input field with one/two buttons next to it

// Private:  A number of the JSF "complex" controls are rendered as a "box" on the left
//			 with one or more "buttons" to the right of the box.  These controls are built
//		     out of an <input> tag with a margin on the right in which we position
//			 buttons. 

// Private:  Add in a pattern of "buttons" after the input field (after "widening" a margin on the field)
//			 This routine can be iteratively called, e.g., on every redraw
//			 to ensure the buttons are correctly positioned relative to any changes
//			 made on the page
_JSF.prototype.addButtonsToInput = function (obj) {

	var elem = this.getElementById(obj);
	var component = this.getComponentById(obj);
	if (elem && component) {

		// If the button hasn't been added to the HTML stream, add it
		if (!component.HTMLrendered) {
			var parent = elem.parentNode;
			elem.onkeydown = this.dispatchAction;	// Handler for keystrokes on the input field
			var next = elem.nextSibling;
			// Wrap the Input tag in a span so that we can position things relative to it
			var span1 = document.createElement("span");
			span1.style.position = "relative";
			span1.style.display = "inline-block";
			var newspan = parent.insertBefore(span1, elem);
			newspan.appendChild(elem);
			// Following the Input tag, add a <span> element
			// this will "continue" the border around the input field to 
			// surround the button(s)			
			var span2 = document.createElement("span");
			span2.style.position = "absolute";
			span2.style.left = "0px";
			span2.style.top = "0px";
			span2.style.height = "1px";
			span2.style.width = "1px";
			span2.style.margin = "0px";
			span2.style.padding = "0px";
			span2.style.overflow = "hidden";
			span2.style.verticalAlign = "top";
			var newspan2 = newspan.appendChild(span2);	
			// Add a button in the span (button and enclosing span are sized in separate call)
			var btn1 = document.createElement("button");
			btn1.setAttribute("type", "button");
			btn1.style.position = "absolute";
			btn1.style.margin = "0px";
			btn1.style.padding = "0px";
			btn1.style.verticalAlign = "top";
			btn1.style.overflow = "hidden";
			btn1.onclick = this.dispatchAction;
			btn1.ondblclick = this.dispatchAction;
			btn1.onmousedown = this.dispatchAction;
			btn1.onmouseup = this.dispatchAction;
			btn1.onmouseout = this.dispatchAction;
			btn1.onkeydown = this.dispatchAction;
			var newbtn1 = newspan2.appendChild(btn1);
			// The button needs "content" to behave right, give it an image
			// Image is provisional -- a smaller one may be substituted later
			var img1 = document.createElement("img");
			img1.setAttribute("src", hx_Env.resources.getImgPath() + "spin-down-sm-black.gif");
			img1.setAttribute("width", 4);
			img1.setAttribute("height", 2);
			img1.setAttribute("border", 0);
			img1.setAttribute("align", "top");
			img1.style.margin = "0px";
			img1.style.padding = "0px";
			img1.style.position = "relative";
			img1.style.overflow = "hidden";
			img1.setAttribute("alt", JSFbundle["increment"]);
			newbtn1.appendChild(img1);
			// If this is a "two button" control, then add another button and image
			if (component.buttonOrganization == 2) {
				var btn2 = document.createElement("button");
				btn2.setAttribute("type", "button");
				btn2.style.position = "absolute";
				btn2.style.margin = "0px";
				btn2.style.padding = "0px";
				btn2.style.verticalAlign = "top";
				btn2.style.overflow = "hidden";
				btn2.onclick = this.dispatchAction;
				btn2.ondblclick = this.dispatchAction;
				btn2.onmousedown = this.dispatchAction;
				btn2.onmouseup = this.dispatchAction;
				btn2.onmouseout = this.dispatchAction;
				btn2.onkeydown = this.dispatchAction;
				newbtn2 = newspan2.appendChild(btn2);
				var img2 = document.createElement("img");
				img2.setAttribute("src", hx_Env.resources.getImgPath() + "spin-down-sm-black.gif");
				img2.setAttribute("width", 4);
				img2.setAttribute("height", 2);
				img2.setAttribute("border", 0);
				img2.setAttribute("align", "top");
				img2.style.margin = "0px";
				img2.style.padding = "0px";
				img2.style.position = "relative";
				img2.style.overflow = "hidden";
				img2.setAttribute("alt", JSFbundle["decrement"]);
				newbtn2.appendChild(img2);
			}
			component.HTMLrendered = true;
		}
	}		
}
// Private:  Given a configuration of input field, span and button(s) 
//			 Size and position the span/buttons and set their style based on the input field
_JSF.prototype.sizeButtonsToInput = function (obj) {
	var s, span, btn1, btn2, img1, img2;
	var elem = this.getElementById(obj);
	if(elem == null)
		return;
	var component = this.getComponentById(obj);
	var outerspan = elem.parentNode;
	if (elem) span  = elem.nextSibling;
	if (span) btn1  = span.firstChild;
	if (btn1) img1  = btn1.firstChild;
	var verify = (component && elem && span && btn1 && img1 && elem.tagName == "INPUT" && span.tagName == "SPAN" && btn1.tagName == "BUTTON" && img1.tagName == "IMG");
	if (component && component.buttonOrganization == 2) {
		if (btn1) btn2 = btn1.nextSibling;
		if (btn2) img2 = btn2.firstChild;
		verify = verify && (btn2 && img2 && btn2.tagName == "BUTTON" && img2.tagName == "IMG");
	}
	if (verify) {
		// Copy over the relevant positioning information from the input to the enclosing span and then
		// remove it from the input tag
		var ppos = this.getEffectiveStyle(elem, "position");
		if (!component.HTMLcopied) {
			this.copyStyle (elem, outerspan, "margin-left", "margin-right", "margin-top", "margin-bottom");
			this.copyStyle (elem, outerspan, "left", "right", "top", "bottom");
			component.position = "relative";
			if (ppos == "absolute") {
				outerspan.style.position = "absolute";
				component.position = "absolute";
			}
			component.HTMLcopied = true;
		} else {
			// If we've already done the copy, then only copy something if it's no longer "clear"
			this.copyStyleZ (component, elem, outerspan, "margin-left", "margin-right", "margin-top", "margin-bottom");
			this.copyStyleZ (component, elem, outerspan, "left", "right", "top", "bottom");
			if (ppos != component.position) {
				component.position = "relative";
				if (ppos == "absolute") {
					outerspan.style.position = "absolute";
					component.position =  "absolute";
				}
			}
		}
		elem.style.margin = elem.style.marginLeft = elem.style.marginRight = elem.style.marginTop = elem.style.marginBottom = "0px";
		if (elem.clientHeight) {
			elem.style.left = elem.style.top = "0px";
		} else {
			elem.style.left = elem.style.top = elem.style.bottom = elem.style.right = "0px";
		}
		elem.style.position = "relative";

		// Copy over all relevant style properties from the input to the span
		this.copyStyle (elem, span, "cursor", "direction", "display", "overflow", "visibility");
		this.copyStyle (elem, span, "font-family", "font-size", "font-variant", "font-weight", "line-height");
		span.disabled = (elem.disabled || elem.readOnly);
		span.hideFocus = elem.hideFocus;
		
		// Manage the borders on the style
		if (component.buttonBorder > 0) {
			this.copyStyle (elem, span, "border-left-width", "border-left-style", "border-right-width", "border-right-style", "border-top-width", "border-top-style", "border-bottom-width", "border-bottom-style");			
			span.style.borderLeftWidth = "0px";
		} else {
			span.style.borderWidth = "0px"
		}
		// Note that we don't copy "effective" styles for color -- they're sorta inherited based on the "color" setting
		// If the border color has only one color and it's the same as the "color", this is the way
		// IE constructs an "inherited" border color
		var bordercolor = this.getEffectiveStyle (elem, "border-color");
		var colorcolor  = this.getEffectiveStyle (elem, "color");
		if (bordercolor.indexOf(" ") < 0 && bordercolor==colorcolor) {
			span.style.borderColor = "";
			bordercolor = "";
		} else {
			span.style.borderRightColor = this.getEffectiveStyle(elem,"border-right-color");
			span.style.borderLeftColor  = this.getEffectiveStyle(elem,"border-left-color");
			span.style.borderTopColor   = this.getEffectiveStyle(elem,"border-top-color");
			span.style.borderBottomColor= this.getEffectiveStyle(elem,"border-bottom-color");
			bordercolor = this.getEffectiveStyle (elem, "border-right-color");
		}
		var borderstyle = this.getEffectiveStyle(elem,"border-right-style");
		var borderR = this.getEffectiveStyle(elem, "border-right-width");
		var borderT = this.getEffectiveStyle(elem, "border-top-width");				
		var borderB = this.getEffectiveStyle(elem, "border-bottom-width");
		borderstyle = (borderstyle == "solid")   ? borderstyle : "";
		borderR = this.parseBorder(borderR);
		borderT = this.parseBorder(borderT);
		borderB = this.parseBorder(borderB);

		// Style the button(s)
		// It can have a border of 0, 1 or 2 pixels.  If the border is > 3, it will deform so we'll cap it at 3
		// Use the right border of the input tag to define the style of the button
		// One special case -- if the border is solid and 1 pixel wide we eliminate some extra lines
		var btnBorderWidth = (borderR > 3) ? 3 : borderR;
		btn1.style.backgroundColor = (btnBorderWidth > 0) ? component.buttonColor : "transparent";
		btn1.style.borderColor = bordercolor;
		btn1.style.borderStyle = borderstyle;
		btn1.style.borderWidth = btnBorderWidth + "px";
		btn1.disabled = (elem.disabled || elem.readOnly);
		btn1.hideFocus = elem.hideFocus;
		if (btn2) {
			btn2.style.backgroundColor = btn1.style.backgroundColor;
			btn2.style.borderColor = btn1.style.borderColor;
			btn2.style.borderStyle = btn1.style.borderStyle;
			btn2.style.borderWidth = btn1.style.borderWidth;
			btn2.disabled = (elem.disabled || elem.readOnly); 
			btn2.hideFocus = elem.hideFocus;
		}
		var leftline = false;
		var leftlinewidth = 1;
		var xtrainterior = 0;
		var NSOffset = 0;
		span.style.backgroundColor = (component.buttonBorder > 0) ? component.buttonColor : "transparent";
		if (borderstyle == "solid" && borderR == 1) {
			if (this.isBrowserType("Netscape")) {
				NSOffset = 1;
				xtrainterior = 1;
			} else {
				xtrainterior = 3;
			}
			leftline = true;
			span.style.backgroundColor = bordercolor;
			btn1.style.borderWidth = "0px";
			if (btn2) {
				btn2.style.borderWidth = "0px";
				btn2.style.marginTop = "1px";
			}
		}
		var xMargin = parseInt(this.getEffectiveStyle(elem, "padding-right"));
		if (component.paddingRight < 0 || xMargin > 1) {
			component.paddingRight = xMargin;
		}
		if (component.paddingRight > 1) {
			elem.style.paddingRight = "1px";
		}
		if (((borderstyle == "" || borderstyle == "inset") && borderR > 1) || 
			(component.paddingRight > 1) || (component.buttonBorder > 0) ) {
			leftline = true;
 		}
 		leftlinewidth = (!leftline) ? 0 : ((component.paddingRight <= 1) ? 1 : component.paddingRight);
		
		// Position the buttons
		var fullheight = elem.offsetHeight;
		if (component.buttonBorder > 0) {		
			fullheight -= (borderT + borderB);
		}
		fullheight = (fullheight < 0) ? 0 : fullheight;
		var halfheight = Math.floor(fullheight/2);
		var btnHeight  = (btn2) ? halfheight : fullheight;
		var btnHeightX = (btnHeight-(btnBorderWidth*2));
		var imgheight = 4;
		var imgwidth = 8;
		var imgname = "";
		var btnWidth = 16;
		if (btnHeightX < 5) {
			btnWidth = 12;  imgname = "sm-";
			imgheight = 2;	imgwidth = 4;
		} else if (btnHeightX < 6) {
			btnWidth = 14;  imgname = "md-";
			imgheight = 3;	imgwidth = 6;
		}
		btn1.style.top = "0px";
		btn1.style.left = leftlinewidth + "px";
		btn1.style.width = btnWidth + "px";
		btn1.style.height = btnHeight + "px";
		if (btn2) {
			btn2.style.top = btnHeight + "px";
			btn2.style.left = btn1.style.left;
			btn2.style.width = btnWidth + "px";
			btn2.style.height = (fullheight - halfheight - NSOffset) + "px";
		}
		// Scale the hinky image to fit inside the button
		imgname = (span.disabled) ? (imgname + "gray.gif") : (imgname = imgname + "black.gif");
		img1.width = imgwidth;  img1.height = imgheight;
		if (btn2) {
			img2.width = imgwidth;	img2.height = imgheight;
			img1.src = hx_Env.resources.getImgPath() + "spin-up-" + imgname;
			img2.src = hx_Env.resources.getImgPath() + "spin-down-" + imgname;
		} else {
			img1.src = hx_Env.resources.getImgPath() + "spin-down-" + imgname;
		}
		var voffset = Math.floor(((btnHeightX - imgheight + xtrainterior + 1)/2)) - 1;
		img1.style.top = voffset + "px";
		if (btn2) {
			img2.style.top = voffset + "px";
		}
		btnWidth = (this.isBrowserType("W3C-STD")) ? btnWidth : btn1.offsetWidth;
		btnWidth = (btnWidth < 0) ? 12 : btnWidth;
		
		// Widen the input field to include the buttons by widening the margin
		elem.style.marginRight = (btnWidth+1 + leftlinewidth) + "px";
		component.marginRight = elem.style.marginRight;

		// Position the span.
		borderR = (component.buttonBorder > 0) ? borderR : 0;
		var elemHeight = elem.offsetHeight;
		var elemWidth  = elem.offsetWidth;
		var elemOffset = 0;
		if (elem.offsetLeft > 0) {
			// There's an odd bug in IE where if the Input field is the 
			// first element on a line, offsetLeft is > 0 even if there is
			// no padding and no margining
			var lmargin = this.getEffectiveStyle(outerspan, "margin-left");
			var lleft = this.getEffectiveStyle(outerspan, "left");
			lmargin = isNaN(parseInt(lmargin)) ? 0: parseInt(lmargin);
			lleft = isNaN(parseInt(lleft)) ? 0: paraseInt(lleft);
			if (lmargin > 0 || lleft > 0) {
				elemOffset = elem.offsetLeft;
			}
		}
		var W3CadjustH = 0;
		if (component.buttonBorder > 0) {
			if (this.isBrowserType("W3C-STD")) {
				elemHeight -= (borderT + borderB);
				W3CadjustH = borderR;
			}
		}
		elemHeight = (elemHeight < 0) ?  4 : elemHeight;
		elemWidth  = (elemWidth  < 0) ? 10 : elemWidth;
		span.style.width = (btnWidth + borderR + leftlinewidth - W3CadjustH) + "px";
		span.style.height = elemHeight + "px";
		span.style.top = "1px";
		span.style.left = (elemOffset + elemWidth - borderR) + "px";
		this.adjustTop(elem, span);
	}
}

// Private:  copy a style attribute from one element to another
_JSF.prototype.adjustTop = function (elem, span) {
	var elemtop = parseInt(this.getElementAbsPosTop(elem));
	var spantop = parseInt(this.getElementAbsPosTop(span));
	if (elemtop != spantop) {
		span.style.top = parseInt(span.style.top) + (elemtop-spantop) + "px";
	}
	// NS doesn't get it right sometimes, try again (though this may not help much)
	spantop = parseInt(this.getElementAbsPosTop(span));
	if (elemtop != spantop) {
		span.style.top = parseInt(span.style.top) + (elemtop-spantop) + "px";
	}
}

_JSF.prototype.copyStyle = function (src, dst) {
	var s, name;
	for (var x = 2; x < arguments.length; x++) {
		s = this.getEffectiveStyle(src, arguments[x]);
		if (s) {
			// IE
			if (src.currentStyle) {
				name = this.CSStagToDOMtag(arguments[x]);
				dst.style.setAttribute(name, s);
			} else {
				dst.style.setProperty(arguments[x], s, "");
			}
		}
	}	
}

_JSF.prototype.copyStyleZ = function (component, src, dst) {
	var s, name;
	for (var x = 3; x < arguments.length; x++) {
		s = this.getEffectiveStyle(src, arguments[x]);
		name = this.CSStagToDOMtag(arguments[x]);
		if (name == "marginRight") {
			if (s && s != component.marginRight) {
				if (src.currentStyle) {
					dst.style.setAttribute(name, s);
				} else {
					dst.style.setProperty(arguments[x], s, "");
				}
			}
		} else {
			if (s && s != "0px") {
				if (src.currentStyle) {
					dst.style.setAttribute(name, s);
				} else {
					dst.style.setProperty(arguments[x], s, "");
				}
			}
		}
	}	
}

_JSF.prototype.parseBorder = function (value) {
	return (value == "thin") ? 2 : ((value == "medium") ? 3 : ((value == "thick") ? 4 : parseInt(value)));
	
}

// BEGIN: JSF object:  Manage get/set attribute encoded arguments
_JSF.prototype.JSFargument = function (instring) {
	this.attribute = "";
	this.value = "";
	if (instring && (typeof instring == "string")) {
		var q = instring.indexOf(":");
		q = (q < 0) ? instring.indexOf("=") : q;
		if (q > 0) {
			this.attribute = instring.substring(0,q);
			this.value = instring.substring(q+1);
		}
	}
}
_JSF.prototype.JSFargument.prototype.attribute;
_JSF.prototype.JSFargument.prototype.value;

// BEGIN: JSF object:  Iframe support

// Private: Get the rendered size of an element.  Note that these routines return
// the browser's idea of a rendered size.  In IE (non-CSS) the size excludes padding and
// borders.  On CSS-conformant browsers (IE in standards mode, Netscape), the sizes do
// include borders and padding for most BUT NOT ALL elements.  So use this only in
// cases where you're sure you are handling the browsers correctly.
_JSF.prototype.getElementRenderedHeight = function (obj) {
	var elem = this.getElementById(obj);
	return parseInt(elem.offsetHeight);
}
_JSF.prototype.getElementRenderedWidth = function (obj) {
	var elem = this.getElementById(obj);
	return parseInt(elem.offsetWidth);
}

// Private:  Get the width of the browser window (interior) in pixels
_JSF.prototype.getWindowClientWidth = function () {
	if (window.innerWidth) {
		return window.innerWidth;
	} else if (this.isBrowserType("IE6CSS")) {
		return document.body.parentElement.clientWidth;
	} else if (document.body && document.body.clientWidth) {
		return document.body.clientWidth;
	}
	return 0;
}

// Private:  Get the height of the browser window (interior) in pixels
_JSF.prototype.getWindowClientHeight = function () {
	if (window.innerHeight) {
		return window.innerHeight;
	} else if (this.isBrowserType("IE6CSS")) {
		return document.body.parentElement.clientHeight;
	} else if (document.body && document.body.clientHeight) {
		return document.body.clientHeight;
	}
	return 0;
}

// Private: From the containing window, get the window object of the iframe
_JSF.prototype.getIframeWindowObject = function (inobj, type) {
	if (inobj) {
		var obj;
		if (typeof inobj == "string") {
			obj = this.getElementById (inobj);
		} else {
			obj = inobj;
		}
		if (obj) {
			if (type == "window") {
				if (obj.contentDocument && obj.contentDocument.defaultView) {
					return (obj.contentDocument.defaultView);
				} else if (obj.Document) {
					return (obj.Document.parentWindow);
				}
			} else if (type == "body") {
				if (obj.contentDocument) {
					return (obj.contentDocument.body);
				} else if (obj.Document) {
					return (obj.Document.body);
				}
			}
		}
	}
	return;
}

// Private: Similar but get the body object of the iframe
_JSF.prototype.getIframeBodyObject = function (inobj) {
	if (inobj) {
		var obj;
		if (typeof inobj == "string") {
			obj = this.getElementById (inobj);
		} else {
			obj = inobj;
		}
		if (obj) {
			if (obj.contentDocument) {
				return (obj.contentDocument.body);
			} else if (obj.Document) {
				return (obj.Document.body);
			}
		}
	}	
	return;
}

// --------------------------
// BEGIN: the POPUP object 
// --------------------------


// Public:  Constructor
_JSF.prototype.JSFPopup = function (name, defWidth, defHeight, srcValue) {
	this.id = name;
	this.defaultWidth = defWidth;
	this.defaultHeight = defHeight;
	this.srcValue = srcValue;
}

// Prototype defined state
_JSF.prototype.JSFPopup.prototype.id = "";
_JSF.prototype.JSFPopup.prototype.emitted = false;
_JSF.prototype.JSFPopup.prototype.defaultWidth = 1;
_JSF.prototype.JSFPopup.prototype.defaultHeight = 1;
_JSF.prototype.JSFPopup.prototype.srcValue = "";

_JSF.prototype.JSFPopup.prototype.onPageLoad = function() {
	// If we haven't emitted the HTML that makes the iframe, do so now 
	if (!this.emitted) {
		var elem = document.createElement("iframe");
		elem.setAttribute("id", this.id);
		elem.setAttribute("frameBorder", 0);
		elem.setAttribute("marginWidth", 0);
		elem.setAttribute("marginHeight", 0);
		elem.setAttribute("width", this.defaultWidth);
		elem.setAttribute("height", 1);
		elem.setAttribute("scrolling", "no");
		elem.setAttribute("src", this.srcValue);
		elem.style.overflow = "visible";
		elem.style.position = "absolute";
		elem.style.left = "0px";
		elem.style.top = "0px";
		elem.style.fontSize = "1pt";
		elem.style.zIndex = "9999";
		elem.style.borderWidth = "0px";
		elem.style.borderStyle = "solid";
		elem.style.borderColor = "ThreeDDarkShadow";
		elem.style.visible = "none";
		document.body.appendChild(elem);
		this.emitted = true;
	}
}

// Public:  Put the popup ondisplay
_JSF.prototype.JSFPopup.prototype.uivisible = function (position, parenttag, srcvalue) {
	var popuptag  = JSF.getElementById(this.id);
	if (popuptag) {
		// First, correct the popup's height.  The popup is initially displayed using a fixed
		// set of dimensions.  If a popup has content which is font-size dependent (notably height), 
		// then it may be too small.  
		// Note that Netscape is flakey here.
		var scrollheight = 0;
		var iframebody = JSF.getIframeBodyObject(popuptag);
		if (iframebody) {
			if (JSF.isBrowserType("Netscape")) {
				// In Netscape we have some problems.  First, even in NS 7.1 (which claims to have fixed this)
				// the iframe is essentially without content if it isn't visible (even if the content has been
				// loaded -- which it is in 7.1 -- it's incorrectly sized.  So we must make it visible
				// to size it (this makes things a bit jumpy but it's the only way).
				popuptag.style.display = "block";
				// Second, "scrollheight" isn't reliable in NS 6/7.0, so use offsetHeight which normally is
				scrollheight = iframebody.offsetHeight;
			} else {
				// IE handles iframe content correctly, but offsetHeight isn't reliably set, 
				// so use scrollHeight which is
				scrollheight = iframebody.scrollHeight;
			}
		}
		// Now set the height of the iframe popup
		if (scrollheight <= 0) scrollheight = popuptag.height;
		popuptag.setAttribute("height", scrollheight); 

		// position the iframe popup
		var xtagtop = JSF.getElementAbsPosTop(parenttag);	
		var xtagleft = JSF.getElementAbsPosLeft(parenttag);
		var winwidth  = JSF.getWindowClientWidth();
		var winheight = JSF.getWindowClientHeight();
		var popperheight= JSF.getElementRenderedHeight(parenttag);
		var popupheight = JSF.getElementRenderedHeight(popuptag);
		var popupwidth  = JSF.getElementRenderedWidth(popuptag);
		var xtop  = xtagtop + popperheight - 1;
		var xleft = xtagleft - 1;
		// Make sure we don't go "outside the window"
		if ((xtop + popupheight > winheight) && (winheight > popupheight)) {
			xtop = xtagtop - popupheight + 1;
		}
		if ((xleft + popupwidth > winwidth) && (winwidth > popupwidth)) {
			xleft = winwidth - popupwidth - 1;
		}

		// Now make it display
		popuptag.style.top = xtop + "px";
		popuptag.style.left= xleft + "px";
		popuptag.style.display = "block";
		return true;
	}
	return false;
}


// Public:  Take the popup off display
_JSF.prototype.JSFPopup.prototype.uirelease = function () {
	var popuptag  = JSF.getElementById(this.id);
	if (popuptag) {
		popuptag.style.display = "none";
		return true;
	}
	return false;
}

// --------------------------
// BEGIN: the SPINNER object 
// --------------------------

// Pattern for emitted HTML
// <SCRIPT language="JavaScript" type="text/javascript">
//	JSF.addComponent ("Input1", new JSFSpinner(#attributes));
// </SCRIPT>
// <INPUT type="text" id="Input1" value="1" size="20"> 
//	  The "input" tag is the normal tag emitted for an INPUT of type number JSF component.
//	  The "JSFSpinner" class supports the following attributes:
//	
//							Attributes have the format "name:value", e.g., "max-bound:100"
//							Multiple attributes are separated by commas, e.g., "min-bound:1","max-bound:10"
//	
//		min-bound			Minimum value the spinner will spin to.  Default: infinity
//		max-bound			Maximum value the spinner will spin to.  Default: -infinity
//		increment			Each "click" of the spinner in/decreases this amount:  Default: 1
//		button-color		Color of the spinner buttons.			 Default: buttonface
//		button-border		Spinner buttons are "within" the 
//							input field's border					 Default: 1
//

// PROTOTYPE defined state: following are required of all JSF complex components

// Public:  Constructor
_JSF.prototype.JSFSpinner = function (arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9) {

	this.buttonOrganization = 2;
	this.HTMLrendered = false;
	this.HTMLcopied = false;
	this.marginRight = -1;
	this.paddingRight = -1;
	this.position = "";
	this.buttonBorder = 1;
	this.buttonColor = "buttonface"
	this.minBound = 0;
	this.maxBound = 1;
	this.minUnbounded = true;
	this.maxUnbounded = true;
	this.interval = 1;

	this.setAttributeRedraw (false, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);	
}

// PROTOTYPE defined state: following are required of all JSF complex components
_JSF.prototype.JSFSpinner.prototype.id;
_JSF.prototype.JSFSpinner.prototype.buttonOrganization = 2;
_JSF.prototype.JSFSpinner.prototype.HTMLrendered = false;
_JSF.prototype.JSFSpinner.prototype.HTMLcopied = false;
_JSF.prototype.JSFSpinner.prototype.marginRight = -1;
_JSF.prototype.JSFSpinner.prototype.paddingRight = -1;
_JSF.prototype.JSFSpinner.prototype.position = "";
_JSF.prototype.JSFSpinner.prototype.buttonBorder = 1;
_JSF.prototype.JSFSpinner.prototype.buttonColor = "buttonface"
// PROTOTYPE defined state: following are custom to the control
_JSF.prototype.JSFSpinner.prototype.minBound = 0;
_JSF.prototype.JSFSpinner.prototype.maxBound = 1;
_JSF.prototype.JSFSpinner.prototype.minUnbounded = true;
_JSF.prototype.JSFSpinner.prototype.maxUnbounded = true;
_JSF.prototype.JSFSpinner.prototype.interval = 1;
_JSF.prototype.JSFSpinner.prototype.intervalID;
_JSF.prototype.JSFSpinner.prototype.timerID;

// Public:  Page level onLoad handler
_JSF.prototype.JSFSpinner.prototype.onPageLoad = function () {
	// Add the spinner buttons to the HTML on the page
	JSF.addButtonsToInput(this.id);
	// Force the value to be "reset" so it's properly bounded
	this.setValue(this.getValue());
	// Redraw to make it all happen
	this.onRedraw();
	return true;
}

// Public:  Redraw the spinner incorporating any changes to the HTML of the input or spinner fields
_JSF.prototype.JSFSpinner.prototype.onRedraw = function () {
// Public: Given the current style of the DOM object and the current value for
//		   the size (height/width) of the DOM Object, reposition/restyle any spinner buttons.
	JSF.sizeButtonsToInput(this.id);
	return true;
}

// Public:  Release the ui state of the spinner
_JSF.prototype.JSFSpinner.prototype.uirelease = function (local) {
	JSF.setActiveComponent ("");
	// The key thing is to make sure all timers are clear
	window.clearInterval(this.intervalID);
	window.clearTimeout(this.timerID);
	this.intervalID = 0;
	this.timerID = 0;
	return true;
}

// Public:  Set a value (hex string only) into a spinner
_JSF.prototype.JSFSpinner.prototype.setValue = function (value) {
	var tagobj = JSF.getElementById(this.id);
	if (tagobj) {
		// make sure we're dealing with a number
		var ivalue = (typeof value == "string") ? parseInt(value) : value;
		// make sure we're above the minimum
		if (!isNaN(ivalue)) {
			if (!this.minUnbounded) {
				ivalue = (ivalue < this.minBound) ? this.minBound : ivalue;
			}
			// make sure we're below the maximum
			if (!this.maxUnbounded) {
				ivalue = (ivalue > this.maxBound) ? this.maxBound : ivalue;
			}
			tagobj.value = ivalue;
		} else {
			tagobj.value = "";
		}
		return true;
		}
	return false;
}
	
// Public:  Get a value from a spinner
_JSF.prototype.JSFSpinner.prototype.getValue = function() {
	var tagobjinput = JSF.getElementById(this.id);
	if (tagobjinput) {
		return (tagobjinput.value);
	}
	return;
}

// Public:  Set an "attribute" on the spinner.  Attributes are passed as strings of the form "name:value"
//			Attributes are "min-bound" "max-bound" "increment" "button-color" "button-border"
_JSF.prototype.JSFSpinner.prototype.setAttribute = function(arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9) {
	this.setAttributeRedraw (true, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
}

// Public:  Same as above but first parameter controls if we do a redraw or not after
//			the attributes are set
_JSF.prototype.JSFSpinner.prototype.setAttributeRedraw = function(redraw, arg1, arg2, arg3, arg4, arg5) {
	for (var x = 1; x < arguments.length; x++) {
		var arg = new JSF.JSFargument(arguments[x]);
		if (arg.attribute.length > 0 && arg.value.length > 0) {
			if (arg.attribute == "minimum-bound" || arg.attribute == "min-bound") {
				var unbounded = (typeof arg.value == "string" && arg.value == "") ? true : false;
				var num = 0;
				if (unbounded) {
					this.minUnbounded = true;
				} else {
					this.minUnbounded = false;
					num = (typeof arg.value == "string") ? parseInt(arg.value) : arg.value;
				}
				this.minBound = num;
			} else if (arg.attribute == "maximum-bound" || arg.attribute == "max-bound") {
				var unbounded = (typeof arg.value == "string" && arg.value == "") ? true : false;
				var num = 0;
				if (unbounded) {
					this.maxUnbounded = true;
				} else {
					this.maxUnbounded = false;
					num = (typeof arg.value == "string") ? parseInt(arg.value) : arg.value;
				}
				this.maxBound = num;
			} else if (arg.attribute == "increment") {
				var num = (typeof arg.value == "string") ? parseInt(arg.value) : arg.value;
				num = (num < 2) ? 1 : num;
				this.interval = num;
			} else if (arg.attribute == "button-color"  || arg.attribute == "btn-color") {
				this.buttonColor = arg.value;
			} else if (arg.attribute == "button-border" || arg.attribute == "btn-border") {
				if (arg.value == "false" || arg.value == 0 || arg.value == "0") {
					this.buttonBorder = 0;
				} else if (arg.value == "true" || arg.value == 1 || arg.value == "1") {
					this.buttonBorder = 1;
				}
			}
		}
	}
	// Make sure bounds are in bounds
	if (!this.maxUnbounded && !this.minUnbounded) {
		if (this.minBound >= this.maxBound) {
			this.minUnbounded = true;
			this.minBound = 0;
		} else if (this.maxBound <= this.minBound) {
			this.maxUnbounded = true;
			this.maxBound = 0;
		} else if ((this.maxBound - this.minBound) < this.increment) {
			this.increment = 1;
		}
	}
	
	// Reset the value to ensure it's bounded and redraw
	if (redraw) {
		this.setValue(this.getValue());
		this.onRedraw();
	}
	return true;
}

// Public: Retrieve the value of an attribute
_JSF.prototype.JSFSpinner.prototype.getAttribute = function (attribute) {
	if (attribute == "minimum-bound" || attribute == "min-bound") {
		return (this.minUnbounded ? -9999 : this.minBound);
	} else if (attribute == "maximum-bound" || attribute == "max-bound") {
		return (this.maxUnbounded ? +9999 : this.maxBound);
	} else if (attribute == "increment") {
		return this.interval;
	} else if (attribute == "button-color"  || attribute == "btn-color") {
		return this.buttonColor;
	} else if (attribute == "button-border" || attribute == "btn-border") {
		return this.buttonBorder;
	}
	return;
}

// BEGIN: SPINNER object, private methods

// Private: Increment or decrement the current value (within the bound, using the current increment
_JSF.prototype.JSFSpinner.prototype.reValue = function (buttonitem) {
	var tagobj  = JSF.getElementById(this.id);
	if (tagobj) {
		var value = parseInt(tagobj.value);
		if (isNaN(value)){
			value = this.minBound;
		} else {
			var delta = parseInt((buttonitem == "btn1") ? this.interval : -(this.interval));
			var absdelta = (delta < 0) ? -(delta) : delta;
			// Normally we just "add" the delta to the current value and then bound it
			// But if say the delta is 10 and the current value is 2, most people
			// would expect us to first spin to go to "10" (not to "12") and then spin on
			// in units of "10" (so spinning would go 2, 10, 20, 30 not 2,12,22,32)
			// This is a judgment call, so we "round" to the a multiple of the delta if delta is >= 5.
			if (absdelta < 5) {
				value = value + delta;
			} else {
				if (delta >= 0) {
					value = ((Math.floor(value/absdelta))* absdelta) + delta;
				} else {
					value = ((Math.ceil(value/absdelta))* absdelta) + delta;
				}
			}
		}
		this.setValue (value);
	}
}
// Private:  Handle an DOM event dispatched by the JSF object event handler
_JSF.prototype.JSFSpinner.prototype.dispatchAction = function (item, action, keycode) {
	// Return values are important so that event bubbling works
	JSF.uirelease();
	if (item == "btn1" || item == "btn2") {
	// Handle the mouse events on the buttons
		if (action == "click") {
			this.reValue(item); 
			return false;
		} else if (action == "dblclick") {
			if (JSF.isBrowserType("Netscape")) {
				// In netscape's case, we seem to only get clicks 
				// the double clicks which are sporadic will cause us to inc too much
			} else {
				this.reValue(item);
			}
			return false;
		} else if (action == "mousedown") {
			// N.B. Timers can only evaluate "arguments" to functions if those arguments have a
			// global or well known scope, e.g., "this" doesn't work because the timer is executing
			// script in a different context than where it's set.  So we evaluate the id of this
			// object and let the JSF object refind "this" so we can execute an event.
			var executionstring = "JSF.dispatchTimer('" + this.id + "', '" + item + "', 'start')";		
			this.timerID = window.setTimeout(executionstring, 500);
			return true;
		} else if (action == "keydown") {
			if (keycode == 38) {
				this.reValue("btn1");
				return false;
			} else if (keycode == 40) {
				this.reValue("btn2");
				return false;
			}
		} else {
			// release (above) is all we need to do 
			return false;
		}
	} else if (item == "input") {
		if (action == "keydown") {
			if (keycode == 38) {
				this.reValue("btn1");
				return false;
			} else if (keycode == 40) {
				this.reValue("btn2");
				return false;
			}
		}
	}
	return true;
}

// Private:  Handle a timer event dispatched by the JSF object event handler
_JSF.prototype.JSFSpinner.prototype.dispatchTimer = function (item, action) {
	if (item == "btn1" || item == "btn2") {
		if (action == "spin") {
			this.reValue(item); 
		} else if (action == "start") {
			JSF.uirelease();
			var executionstring = "JSF.dispatchTimer('" + this.id + "', '" + item + "', 'spin')";		
			this.intervalID = window.setInterval(executionstring, 75);
		}
	}
}


// ======================================= End of Bill's libs =====================================
