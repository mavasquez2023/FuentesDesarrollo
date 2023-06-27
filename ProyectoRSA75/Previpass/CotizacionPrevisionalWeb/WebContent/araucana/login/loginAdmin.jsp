<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/html/comun/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Cache-Control" content="no-cache"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<title>Sistema de Cotizaci&oacute;n Previsional :: cp.cl</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/web_publica.css"/>" />
</head>
<body>
<c:set var="adminCaja" scope="page"><%=request.getSession().getAttribute("isAdminCaja")%></c:set>
<c:set var="adminCPE" scope="page"><%=request.getSession().getAttribute("clAdmin")%></c:set>
<script language="javaScript">
<!-- 
	var http;
	if (window.XMLHttpRequest)
		http = new XMLHttpRequest();
	else if (window.ActiveXObject)
	{
		try
		{
			http = new ActiveXObject("MSXML2.XMLHTTP");
		} catch (e)
		{
			try
			{
				http = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {}
		}
	}
	if (!http)
	{
		alert("No ha sido posible crear una instancia de XMLHttpRequest");
	}

	logoutApp = function(contexto)
	{
		var url = "/" + contexto + "/ibm_security_logout";
		http.open("POST", url, true);

		http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		http.setRequestHeader("Content-length", 0);
		http.setRequestHeader("Connection", "close");
		http.onreadystatechange = function() {}
		http.send('');

		return true;
	}
	
	if ('<%=request.getSession().getAttribute("cierraTodo") %>' == 'si' && '${adminCaja}' == '') {
		logoutApp("AdminCotPrevWEB");
		logoutApp("CotizacionPrevisionalWeb");
		location.replace("/CotizacionPrevisionalWeb/araucana/login/usuarioNoEncontrado.jsp?error=<%= request.getAttribute("error") %>");
	} else if ('<%=request.getSession().getAttribute("cierraAdmin") %>' == 'si' && '${adminCaja}' == ''){
		//deslogea de admin e ingresa a app cliente
		logoutApp("AdminCotPrevWEB");
		location.replace("/CotizacionPrevisionalWeb/Inicio.do");
	} else if ('<%=request.getSession().getAttribute("cierraTodo") %>' == 'si' && '${adminCaja}' == 'si'){
		//deslogea de admin e ingresa directamente a adminCCAF
		location.replace("/adminCCAFWeb/");
	}

	function validaEnvio() {
		var flgRadio = 0;
		var i = 0;
		var opcion = 0;
		for (i = 0; i < document.getElementsByName('aplicacion').length; i++) {
			if (document.getElementsByName('aplicacion')[i].checked) {
				flgRadio = 1;
				opcion = document.getElementsByName('aplicacion')[i].value;
				break;
			}
		}

		if (flgRadio == 0) {
			alert("Debe seleccionar la aplicación a la que desea ingresar.");
		} else {
			//Admin CPE
			if (opcion == 1) {
				logoutApp("CotizacionPrevisionalWeb");
				location.replace("/AdminCotPrevWEB/");			
			}

			//Cliente CPE
			if (opcion == 2) {
				logoutApp("AdminCotPrevWEB");
				location.replace("/CotizacionPrevisionalWeb/Inicio.do");	
			}

			//Admin CCAF
			if (opcion == 3) {
				//Se comenta para permitir el ingreso directo en la aplicación adminCCAF
				//logoutApp("CotizacionPrevisionalWeb");
				location.replace("/adminCCAFWeb/");
			}
		}
	}
	//var adminCPE = ${adminCPE}
//-->
</script>

<form action="/CotizacionPrevisionalWeb/araucana/login/usuarioNoEncontrado.jsp" id="formulario">
	<table width="768" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="3" scope="col"><img src="<c:url value="/img/logo.jpg"/>" width="770" height="95" /></td>
		</tr>
		<tr>
			<td colspan="3" align="center" valign="middle" scope="row"><img src="<c:url value="/img/sombra.jpg"/>" width="739" height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td align="center" valign="top">
				<table width="193" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td align="left" valign="top" background="<c:url value="/img/bg_login.jpg" />" scope="col">
							<table width="188" border="0" align="left" cellpadding="0" cellspacing="0">
								<tr>
									<td height="30" colspan="3" align="center" class="titulo_login" scope="col">Aplicaciones</td>
								</tr>
								<%--c:choose>
									<c:when test="${adminCPE == 'si'}">
										<tr>
											<th width="14" height="27" scope="row">&nbsp;</th>
											<td align="left" class="btn_caja" scope="row">Admin. CPE</td>
											<td class="btn_caja">
												<input type="radio" id="aplicacion" name="aplicacion" value="1">
											</td>
										</tr>									
									</c:when>
									<c:otherwise>
										<tr><td colspan="3">&nbsp;</td></tr>
									</c:otherwise>
								</c:choose--%>
								<c:if test="${adminCPE == 'si'}">								
									<tr>
										<th width="14" height="27" scope="row">&nbsp;</th>
										<td align="left" class="btn_caja" scope="row">Admin. CPE</td>
										<td class="btn_caja">
											<input type="radio" id="aplicacion" name="aplicacion" value="1">
										</td>
									</tr>
								</c:if>
								<tr>
									<th width="14" height="27" scope="row">&nbsp;</th>
									<td align="left" class="btn_caja" scope="row">Cliente CPE</td>
									<td>
										<input type="radio" id="aplicacion" name="aplicacion" value="2">
									</td>
								</tr>
								<c:if test="${adminCaja == 'si'}">
									<tr>
										<th height="23" scope="row">&nbsp;</th>
										<td scope="row"><span class="btn_caja">Admin. CCAF</span></td>
										<td>
											<input type="radio" id="aplicacion" name="aplicacion" value="3">
										</td>
									</tr>
								</c:if>
								<c:if test="${adminCPE != 'si'}" >
									<tr><td colspan="3">&nbsp;</td></tr>
								</c:if>
								<c:if test="${adminCaja == ''}" >
									<tr><td colspan="3">&nbsp;</td></tr>
								</c:if>
								<tr>
									<td colspan="3" align="center">
										<INPUT id="button4" class="boton_login" onclick="javascript:validaEnvio();" value="Entrar" type="button">									
									</td>
								</tr>
								<tr>
									<td colspan="3">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br />
			</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</form>
</body>
</html>