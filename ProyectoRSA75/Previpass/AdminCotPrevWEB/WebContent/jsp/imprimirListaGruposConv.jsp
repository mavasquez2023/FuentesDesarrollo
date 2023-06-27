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

<html:form action="/ListarGruposConvenio" styleId="formulario" >
<html:hidden property="idHidden" styleId="idHidden" />
<html:hidden property="nombreHidden" styleId="nombreHidden" />
	<table width="590" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr valign="bottom"> 
						<td height="30" bgcolor="#FFFFFF" class="titulo">
							<strong>Lista de Grupos de Convenios</strong>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
					<tr>
						<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">C&oacute;digo</td>
						<td width="60%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
						<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Estado</td>
					</tr>
					<tr>
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
                                <td height="20" align="left" valign="middle" nowrap="nowrap" class="${estilo}">
                                	<div align="right">
                                		<nested:hidden property="idGrupoConvenio" write="true"/>
                                	</div>
                                </td>
                                <td class="${estilo}">
                                	<nested:hidden property="nombre" write="true"/>
                                </td>
                                <td nowrap="nowrap" class="${estilo}">
                                	<nested:hidden property="habilitado"/>
                                	<nested:notEqual property="habilitado" value="0">
                                		Habilitado
                                	</nested:notEqual>
                                	<nested:equal property="habilitado" value="0">
                                		Deshabilitado
                                	</nested:equal>
                                </td>
                            </tr>
						</nested:iterate>
					</nested:notEmpty>
					<nested:empty property="consulta">
						<tr>
							<td align="left" nowrap="nowrap" class="textos_formularios" colspan="3">
								No hay grupos de convenios que cumplan los criterios especificados
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