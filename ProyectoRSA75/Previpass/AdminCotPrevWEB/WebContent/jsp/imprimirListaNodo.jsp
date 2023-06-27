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
<html:form action="/listarNodo" styleId="formulario">

	<table width="590" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr valign="bottom"> 
						<td height="30" bgcolor="#FFFFFF" class="titulo">
							<strong>Lista de Nodos</strong>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
	     			<tr>
	     				<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">
	     				<%@ include file="/html/comun/flecha.jspf"%>   Nodo</td>
	     				<td width="25%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Descripci&oacute;n</td>
	     				<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Distribuidor</td>
	     				<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Habilitado</td>
	     			</tr>
	               		<nested:notEmpty property="consulta" name="NodoForm" >
							<nested:iterate id="filaConsulta" name="NodoForm"  property="consulta" indexId="nFila">
							<c:choose>
					   		    <c:when test="${nFila % 2 == 0}">
					   		    	<c:set var="estilo" value="textos_formularios"/>
					   		    </c:when>
		   						<c:otherwise>
		   							<c:set var="estilo" value="textos-formcolor2"/>
		   						</c:otherwise>
							</c:choose>
								<tr>
			                   		<td width="15%" align="right" nowrap="nowrap" class="${estilo}" >
			                   			<div align="right">${filaConsulta.idNodo}</div>
			                   		</td>
			                   		<td width="25%" align="left" nowrap="nowrap" class="${estilo}" >
			                   			${filaConsulta.descripcion}
			                   		</td>
			                   		<td width="15%" align="left" nowrap="nowrap" class="${estilo}" >
			                   			<c:if test="${filaConsulta.distribuidor == 1}">
			                   				Habilitado
			                   			</c:if>
				                   		<c:if test="${filaConsulta.distribuidor == 0}">
			                   				No Habilitado
			                   			</c:if>
			                   		</td>
			                   		<td width="15%" align="left" nowrap="nowrap" class="${estilo}" >
			                   			<c:if test="${filaConsulta.habilitado == 1}">
			                   				Habilitado
			                   			</c:if>
				                   		<c:if test="${filaConsulta.habilitado == 0}">
			                   				No Habilitado
			                   			</c:if>
			                   		</td>
			               		</tr>			               		
							</nested:iterate>
	             		</nested:notEmpty>
	             		<nested:empty property="consulta" name="NodoForm" >
	             			<tr>
	             				<td class="textos_formularios" height="20" colspan="4">
	             					No existen Nodos
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