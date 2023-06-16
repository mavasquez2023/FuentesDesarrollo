<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="cl.araucana.cierrecpe.business.Parametros"%>
<html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Interna_Araucana.css" rel="stylesheet"
	type="text/css">
<TITLE>Concatenar Cierres</TITLE>
<script language="JavaScript1.2" src="js/core.js"></script>
<script>
var path= "<%=request.getContextPath()%>";
function Generar(){	
	nrocierres= form1.opcionCierre.length;
	var numcierreschecked=0;
	if (!isNaN(nrocierres)){
		for (var i = 0; i < nrocierres; i++) {
			if (form1.opcionCierre[i].checked==true){
				numcierreschecked++;
			}
		}
	}else{
		forma= form1.opcionCierre.value;
		form1.opcionCierre.checked=true;
		numcierreschecked=1;
	}
	if(numcierreschecked==0){
		alert("Seleccione los cierres a concatenar");
		return;
	}
	if(confirm("¿Confirma la concatenación de los cierres seleccionados?")){
			form1.submit();
		}
}

</script>
</HEAD>
<BODY topmargin="0">
<form name="form1" action= "VerCierres.do">
	<table border="0" align="left" cellpadding="0" cellspacing="0">
		<tr align="left" valign="top">
			<td><img src="images/logoshort.jpg" height="95" border="0"
				usemap="#Map" /></td>
		</tr>
		<tr align="center" valign="top">
			<td align="center">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="20" align="center">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr valign="middle">
							<td height="20" align="left" valign="middle"
								class="titulos_formularios" width="49%">&nbsp;</td>
							<td height="25" align="right" valign="bottom"
								class="titulos_formularios" width="51%">
							<table height="18" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td height="18" align="right" valign="middle"
										class="botonera_ppalactivada">&nbsp;</td>
									<td align="right" valign="middle" nowrap="nowrap"
										class="botonera_ppalactivada"><a href="#"
										onclick="window.close();"><img src="images/ico_cerrar.gif"
										width="9" height="9" hspace="2" border="0" alt="Cerrar Ventana" />Cerrar</a>
									</td>
									<td width="10" align="right" valign="middle" nowrap="nowrap"
										class="botonera_ppalactivada">&nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr valign="middle">
							<td height="22" colspan="2" align="left" valign="middle"
								class="titulos_formularios">
							<div align="center"><img src="images/sombra.jpg" width="523"
								height="7" /></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="72%" valign="top">
				<TABLE border=0 width="100%">
					<tr><td colspan="4" class="titulos_formularios" align="center">Concatenar Cierres</td></tr>
					<tr><td>&nbsp;</td></tr>
					<tr><td width="125" class="textos-formularios1">Periodo: 
						&nbsp; ${periodo}</td>
					</tr>
					<tr><td>&nbsp;</td></tr>
				</table>
				<TABLE border=0 align="center">
					<tr>
						<td align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor"><input type="checkbox" name="allSeccion" title="Todos los Cierres" checked="checked" onclick="selectAll(form1.opcionCierre, this.checked);"></td>
						<td height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">CIERRES</td>

					</tr>
					<tr>
						<td colspan=2 height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">DOMINO</td>

					</tr>
					<c:forEach var="cierre" items="${cierresDomino}">
					<tr>
						<td align="center" class="textos_formularios"><input type="checkbox" name="opcionCierre" checked="checked" value="${cierre}"  onclick="unSelect(form1.allSeccion, this.checked);"></td>
						<td align="center" class="textos_formularios">${cierre}</td>
					</tr>
					</c:forEach>
					<tr>
						
						<td colspan=2 height="22" align="center" valign="middle"
							bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">PREVIPASS</td>

					</tr>
					<c:forEach var="cierre" items="${cierresPrevipass}">
					<tr>
						<td align="center" class="textos_formularios"><input type="checkbox" name="opcionCierre" checked="checked" value="${cierre}"  onclick="unSelect(form1.allSeccion, this.checked);"></td>
						<td align="center" class="textos_formularios">${cierre}</td>
					</tr>
					</c:forEach>
					<tr><td>&nbsp;</td></tr>
					<tr><td align="center" colspan=2>
						<INPUT type="button" name="generar" class="btn2"  value="Concatenar Cierres" onclick="Generar();" >
					</td></tr>
				</TABLE>
			</td>
		</tr>
	</table>
	<INPUT type="hidden" name="periodo" value="${periodo}">
</form>
</BODY>
</html>
