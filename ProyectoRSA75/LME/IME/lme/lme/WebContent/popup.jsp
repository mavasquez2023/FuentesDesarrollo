<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page errorPage="error.jsp" %>
<html>
<HEAD>
<%@ page language="java" contentType="text/html;"	pageEncoding="UTF-8"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath() %>/css/Interna_Araucana.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath() %>/css/web_publica.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath() %>/css/XMLDisplay.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath() %>/css/menu.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath() %>/css/screen.css" rel="stylesheet" type="text/css">
<TITLE>Licencia M&#233;dica Electr&#243;nica</TITLE>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/lme.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/utils.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/XMLDisplay.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/menu.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/calendar_new.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/calendar_setup_new.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/calendar_lang.js"></SCRIPT>

<script type="text/javascript">

function Dv() {  
	var rut = '<%=request.getParameter("n") %>'  
	var ag=rut.split('').reverse()     
	for(total=0,n=2,i=0;i<ag.length;((n==7) ? n=2 : n++),i++)     
	{         
		total+=ag[i]*n     
	}     
	var resto=11-(total%11)     
	document.getElementById("dv").value = (resto<10)?resto:((resto>10)?0:'k') 
}

function Dv(rut) {  
	var rut = document.getElementById("numimpreNew").value 
	var ag=rut.split('').reverse()     
	for(total=0,n=2,i=0;i<ag.length;((n==7) ? n=2 : n++),i++)     
	{         
		total+=ag[i]*n     
	}     
	var resto=11-(total%11)     
	document.getElementById("dv").value = (resto<10)?resto:((resto>10)?0:'k') 
}

</script>

</HEAD>
<BODY onload="cargarOperador();Dv();" bgcolor="white">
<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg"
	width="767" height="81">
<input type="hidden" id="_ideOpe" value="<%out.print(request.getParameter("i")); %>"/>
<input type="hidden" id="_estLicen" value="<%out.print(request.getParameter("e")); %>"/>
<input type="hidden" id="_afiRut" value="<%out.print(request.getParameter("a")); %>"/>
<input type="hidden" id="_numimpre" value="<%out.print(request.getParameter("n")); %>"/>
<input type="hidden" id="_numimprela" value="<%out.print(request.getParameter("imp")); %>"/>

<table width="100%" border="0" style="border:#999 1px solid;" class="texto">
  <tr>
    <td>Operador:</td>
    <td><select id="ideOpeNew" name="ideOpeNew" style="border: 1px solid #6C7D8D; color: #666666; /*width: 240px;*/ height: 20px; font-size: 11px; padding: 0px; z-index:12;"></select></td>
  </tr>
  <tr>
    <td>N° Licencia </td>
    <td><input type="text" id="numimpreNew" onblur="Dv(1);" size= "11" value="<%out.print(request.getParameter("n")); %>"/> - <input type="text" id="dv" value="" size="2" disabled="disabled" /></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input type="button" id="actualizar" onclick="Actualizar();" value="Actualizar" style="background-image: url(/lme/img/buttom.JPG);border: 0;width: 87px;height: 21px; color: white;"></td>
  <td>&nbsp;</td>
 	<td><input type="button" id="Salir" onclick="CerrarConsumo();" value="Salir" style="background-image: url(/lme/img/buttom.JPG);border: 0;width: 87px;height: 21px; color: white;"></td>
  </tr>
</table>
		
<div id="loadingmsg" style="display: none; position:absolute; z-index:3; left:240; top:200; width:200; height:100; border-width:1; border-style:ridge; background-color:#E6F0F1">
	<center><br><br>
		<font face="Arial" Size="3"><b>Procesando.....</b></font><br>
		<!-- include gif image if u want --> <br><br>
	</center>
</div>
 
		

</body>
</html>
