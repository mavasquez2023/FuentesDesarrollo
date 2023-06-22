 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<html:html>
<head>
<title>Consultar SIAGF</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta content="tupla version 3.3">
<meta name="GENERATOR" content="Rational Software Architect">
<link rel="stylesheet" type="text/css" href="css/cambiotramo.css" />
<link rel="stylesheet" type="text/css" href="css/main.styles.css" />
<script>
function llenaBase(opcion){
	if(opcion=="F"){
		document.getElementById("origen").value="SIAGF_FLUJ";
		document.getElementById("destino").value="SIAGF_FLUJ";
		document.getElementById("tablaorigen").value="INPUT";
	}else  if(opcion=="S"){
		document.getElementById("origen").value="SIAGF_STOK";
		document.getElementById("destino").value="SIAGF_STOK";
		document.getElementById("tablaorigen").value="INPUT";
	}else if(opcion=="M"){
		document.getElementById("origen").value="SIAGF_MES";
		document.getElementById("destino").value="SIAGF_MES";
		document.getElementById("tablaorigen").value="INPUT";
		
	}
} 
</script>
</head>
<body>
<table width="1000px" style="margin-left: 30px;"> 
<tr>



<td height="100" colspan="2">

<img  src="img/BONO.jpg" width="1000px" height="100"">



</td>
</tr>
<tr><td valign="top" width="300px" >
<jsp:include  page="/jsp/menu.jsp">
</jsp:include>
</td><td width="700px"><font class="text">
<b>Consultar SIAGF x Rut Causante o Beneficiario</b></font><hr>
<html:form action="webservice" method="post">
<table width="700px" border="0">
<tr><td><font class="blueText">Tipo Proceso</font></td><td>Flujo<input type="radio" name="proceso" value="F" checked onclick="llenaBase(this.value);">
	&nbsp;Stock<input type="radio" name="proceso" value="S" onclick="llenaBase(this.value);">
	&nbsp;Mensual<input type="radio" name="proceso" value="M" onclick="llenaBase(this.value);"></td>
</tr>
<tr><td><font class="blueText">Esquema Origen</font></td><td><input type="text" name="esquemaorigen" value="SIAGF_FLUJ" id="origen"></td>
<td><font class="blueText">Esquema Destino</font></td><td><input type="text" name="esquemadestino" value="SIAGF_FLUJ" id="destino"></td></tr>
<tr><td><font class="blueText">Tabla Entrada</font></td><td><input type="text" name="tablaorigen" value="INPUT" id="tablaorigen"></td>
<td><font class="blueText">Tabla Tuplas</font></td><td><input type="text" name="tablatuplas" value="TUPLAS"></td></tr>
<tr><td><font class="blueText">ID Mayor a</font><font class="blueText"></font></td><td><input type="text" name="maxid"  value="0" size="6" value="0"></td>
<td><font class="blueText"></font>Tabla Tramos</td><td><input type="text" name="tablatramos" value="TRAMOS"></td></tr>
<tr><td>ID Menor a</td><td><input type="text" name="minid"  value="" size="6" value="0"></td><td><font class="blueText">Tabla Causante</font></td><td><input type="text" name="tablacausante" value="CAUSANTE"></td></tr>
<tr><td>Con Thread</td><td><input type="checkbox" name="runthread"></td><td><font class="blueText">Tabla Beneficiario</font></td><td><input type="text" name="tablabeneficiario" value="BENEFICIARIO"></td></tr>
<tr><td>Grabar XML</td><td><input type="checkbox" name="recxml"></td><td></td><td></td></tr>
<!-- 
<tr><td><font class="blueText">ID Tupla Mayor a</font></td><td><input type="text" name="maxidtupla" value=""  size="6" value="0"></td><td></td><td></td></tr>
<tr><td><font class="blueText">ID Tramo Mayor a</font></td><td><input type="text" name="maxidtramo" value=""  size="6" value="0"></td><td></td><td></td></tr>
 -->
<tr><td></td><td align="center" colspan="4">
<input type="submit" value="Procesar" class="boton" onMouseOver="this.className='botonOver'" onMouseOut="this.className='boton'" />
</td><td></td><td></td></tr>
</table>
</html:form>
</td></tr>
</table>
</body>
</html:html>
 