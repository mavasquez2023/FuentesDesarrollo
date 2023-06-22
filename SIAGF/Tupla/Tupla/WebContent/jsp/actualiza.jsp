 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html"%>
<%@taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean"%>
<html:html>
<head>
<title>Actualizar Causante</title>
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
		document.getElementById("tablaorigen").value="LIQREVISA";
		document.getElementById("actualizarM").checked=true;
	}else  if(opcion=="S"){
		document.getElementById("origen").value="SIAGF_STOK";
		document.getElementById("destino").value="SIAGF_STOK";
		document.getElementById("tablaorigen").value="LIQREVISA";
		document.getElementById("actualizarM").checked=true;
	}else if(opcion=="M"){
		document.getElementById("origen").value="SIAGF_MES";
		document.getElementById("destino").value="SIAGF_MES";
		document.getElementById("tablaorigen").value="LIQREVISA";
		document.getElementById("actualizarM").checked=false;
		
	}else if(opcion=="SV"){
		if(document.getElementById("accionSV").checked){
			document.getElementById("tabla011").value="ASF011";
			document.getElementById("tabla012").value="ASF012";
		}else{
			document.getElementById("tabla011").value="";
			document.getElementById("tabla012").value="";
		}
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
<b>Actualizacion de Causantes</b><br>
</font><hr>
<html:form action="actualizacion" method="post" >
<html:hidden property="isForm" value="true" name="isForm"/>
<table width="700px" border="0">

<tr><td><font class="blueText">Tipo Proceso</font></td><td>Flujo<input type="radio" name="proceso" value="F" checked onclick="llenaBase(this.value);">
	&nbsp;Stock<input type="radio" name="proceso" value="S" onclick="llenaBase(this.value);">
	&nbsp;Mensual<input type="radio" name="proceso" value="M" onclick="llenaBase(this.value);"></td>
</tr>
<tr><td><font class="blueText">Esquema </font>Rechazo</td><td><input type="text" name="esquemaorigen" value="SIAGF_FLUJ" id="origen">
</td><td><font class="blueText">Esquema </font>Tuplas</td><td><input type="text" name="esquemadestino" value="SIAGF_FLUJ" id="destino"></td></tr>
<tr><td><font class="blueText">Tabla Rechazo</font></td><td><input type="text" name="tablaorigen" value="TOTRECHAZT" id="tablaorigen"></td><td>Tablas Tuplas</td><td><input type="text" name="tabladestino" value="TUPLAS"></td></tr>
<tr><td><font class="blueText"></font>Tabla Marca Rechazo</td><td><input type="text" name="tablamarcarechazo" value="MARCARECHAZO" id="tablamarcarechazo"></td><td>Tablas Tramos</td><td><input type="text" name="tablatramo" value="TRAMOS"></td></tr>
<tr><td><font class="blueText"></font>Tabla 11</td><td><input type="text" name="tabla011" id="tabla011" value=""></td><td></td><td></td></tr>
<tr><td><font class="blueText"></font>Tabla 12</td><td><input type="text" name="tabla012" id="tabla012" value=""></td><td></td><td></td></tr>
<tr><td><font class="blueText"></font>Periodo</td><td><input type="text" name="minperiodo" value="2012" size="6" maxlength="6">&nbsp;a&nbsp;<input type="text" name="maxperiodo" value="201702" size="6" maxlength="6"></td><td></td><td></td></tr>
<tr><td><font class="blueText"></font>Actualizar Sistemas:</td><td>SIAGF<input type="checkbox" name="accion" value="SF" checked>SIVEGAM<input type="checkbox" name="accion" id="accionSV" value="SV" onclick="llenaBase(this.value);"></td>
<td>Opciones</td>
<td>Período<input type="checkbox" name="actualizar" value="P" checked="checked">
<br>Tramo<input type="checkbox" name="actualizar" value="T" checked="checked">
<br>Monto<input type="checkbox" name="actualizar" id="actualizarM" value="M" checked="checked">
<!-- 
<br>Causante<input type="checkbox" name="actualizar" value="C" checked="checked">
<br>Beneficiario<input type="checkbox" name="actualizar" value="B">
<br>Empleador<input type="checkbox" name="actualizar" value="E">
<br>Entidad<input type="checkbox" name="actualizar" value="D">
 -->							
							</td></tr>
<tr><td></td><td align="center" colspan="4">
<input type="submit" value="Enviar" class="boton" onMouseOver="this.className='botonOver'" onMouseOut="this.className='boton'" />
</td><td></td><td></td></tr>
</table>
</html:form>
</td></tr>
</table>
</body>
</html:html>
 