<!-- header general -->
     
<html>
<head>
<title>M&oacute;dulo de Autoconsulta</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="/AutoconsultaWeb/web/includes/araucana-internet.css" rel="stylesheet" type="text/css">
<style>
h1 {
	font-size: 25px;
	font-family: Arial, Helvetica;
	font-weight: bold;
	color: #999999;
	padding: 0px;
	margin: 0px;
}
</style>
<% if (isPrinting) { %>
<OBJECT ID="WB" WIDTH="0" HEIGHT="0" CLASSID="clsid:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>

<SCRIPT LANGUAGE="VBScript"> 
Sub window_onunload
	On Error Resume Next
	Set WB = nothing   
End Sub  
Sub vbPrintPage (frame)  
	frame.focus()
	On Error Resume Next
	WB.ExecWB 6, 2, 3, 0
End Sub
Sub PrintB()
       OLECMDID_PRINT = 6
       OLECMDEXECOPT_DONTPROMPTUSER = 2
       OLECMDEXECOPT_PROMPTUSER = 1
       call WB.ExecWB(OLECMDID_PRINT, OLECMDEXECOPT_DONTPROMPTUSER,1)
End Sub
</SCRIPT>

<% } else { %>

<script language='javascript'>
function logout() {
	top.location.href="/AutoconsultaWeb/web/Login.do?accion=logoff&paso=2";
}

function welcome() {
	top.location.href="/AutoconsultaWeb/web/Welcome.do";  
}

</script>

<% } %>

</head>
  
 <body bgcolor="#ffffff" onload="window.print();return false"> 
 <!--<body bgcolor="#ffffff" onload="window.print()"> -->
  <img src='/AutoconsultaWeb/web/images/v2/logo.jpg'/><br><br>
<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr><td>






