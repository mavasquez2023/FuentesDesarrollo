<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<title>Cotizaci&oacute;n Previsional Electr&oacute;nica :: cp.cl</title>
	<link href="<c:url value="css/adminAraucana.css" />" rel="stylesheet" type="text/css">
</head>
<body>
<table width="590" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="590" height="95" valign="top">
			<img src="<c:url value="img/logo_imp.jpg" />" border="0" width="590" height="95" />
		</td>
	</tr>
	<tr>
		<td width="590" height="7" valign="top">
			<img src="<c:url value="img/sombra_imp.jpg" />" border="0" width="590" height="7" />
		</td>
	</tr>
</table>

<html:form action="/ListarEmpresas" styleId="formulario" >
<html:hidden property="rutOculto" styleId="rutOculto"/>
<html:hidden property="nombreOculto" styleId="nombreOculto"/>
<html:hidden property="razonOculto" styleId="razonOculto"/>
<html:hidden property="idGrupoOculto" styleId="idGrupoOculto"/>
	<table width="590" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr valign="bottom"> 
						<td height="30" bgcolor="#FFFFFF" class="titulo">
							<strong>Lista de Empresas</strong>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
					<tr>
						<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">RUT</td>
						<td width="60%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
						<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Estado</td>
					</tr>
					<nested:notEmpty property="consulta">
						<nested:iterate id="filaConsulta" property="consulta" indexId="nFila">
							<c:choose>
					   		    <c:when test="${nFila % 2 == 0}">
					   		    	<c:set var="estilo" value="textos_formularios"/>
					   		    	<c:set var="estilo2" value="tablaClaroBordes"/>
					   		    </c:when>
		   						<c:otherwise>
		   							<c:set var="estilo" value="textos-formcolor2"/>
		   							<c:set var="estilo2" value="tablaOscuroBordes"/>
		   						</c:otherwise>
							</c:choose>
                            <tr>                 
                                <td height="20" align="right" valign="middle" nowrap="nowrap" class="${estilo}">
                               		<div align="right"><nested:write property="idEmpresaFmt" /></div>
                                </td>
                                <td align="left" class="${estilo}">
                                	<nested:write property="razonSocial" />
                                </td>
                                <td class="${estilo}">
                                	<nested:notEqual property="habilitada" value="0">
                                		Habilitada
                                	</nested:notEqual>
                                	<nested:equal property="habilitada" value="0">
                                		Deshabilitada
                                	</nested:equal>
                                </td>
                            </tr>
                       </nested:iterate>
					</nested:notEmpty>
				<nested:empty property="consulta">
					<tr>
						<td align="left" nowrap="nowrap" class="textos_formularios" colspan="3">
							No hay empresas administradas por el usuario
						</td>
					</tr>
				</nested:empty>
			</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
<script language="javaScript"> 
<!-- 
	window.print();
	//window.close();
// End --> 
</script>
</html>