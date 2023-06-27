<!-- header general -->
  
<html> 
<head>
<title><bean:message key="message.sistema.modulo"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="/AutoconsultaWeb/modulo/includes/araucana-internet.css" rel="stylesheet" type="text/css">
 
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
</SCRIPT>

<% } else { %>

<script language='javascript'>
function logout() {
	top.location.href="/AutoconsultaWeb/modulo/Login.do?accion=logoff&paso=2";
}

function menu() {
	top.location.href="/AutoconsultaWeb/modulo/Menu.do";
}

</script>

<% } %>

</head>

<body bgcolor="#ffffff" background='/AutoconsultaWeb/modulo/images/madera.jpg' <%= isPrinting ? "onload='vbPrintPage(window)'" : (isTimming ? "onload=\"setTimeout('"+backFunction+"', "+time+")\"" : "") %>>
<table border="0" cellpadding="0" cellspacing="0" width="773">
<tr><td>

<div align="center">





