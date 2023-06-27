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
	<link href="<c:url value="/css/Interna_Araucana.css" />" rel="stylesheet" type="text/css">
</head>
<body>
<table width="590" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="590" height="95" valign="top">
			<img src="<c:url value="/img/logo_imp.jpg" />" border="0" width="590" height="95" />
		</td>
	</tr>
	<tr>
		<td width="590" height="7" valign="top">
			<img src="<c:url value="/img/sombra_imp.jpg" />" border="0" width="590" height="7" />
		</td>
	</tr>
</table>
<html:form action="/ListarConvenios" styleId="formulario">
	<table width="590" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
					<tr>
						<td width="15%"><strong>RUT:</strong></td>
						<td width="20%">
							<nested:write property="rutEmpresaFmt"/>
						</td>
						<td><strong>Nombre:</strong></td>
						<td>
							<nested:write property="nombreEmpresa"/>
						</td>
					</tr>
					<tr>
						<td height="4" colspan="4" bgcolor="#85b4be"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr valign="bottom"> 
						<td height="30" bgcolor="#FFFFFF" class="titulo">
							<strong>Lista de Convenios</strong>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
					<tr>
						<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Convenio</td>
						<td width="60%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Descripci&oacute;n</td>
						<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Estado</td>
					</tr>
					<tr>
						<td colspan="3">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<nested:iterate id="fila" property="consulta" indexId="nFila">
									<c:choose>
					   		    		<c:when test="${nFila % 2 == 0}">
					   		    			<c:set var="estilo" value="textos-formcolor2"/>
					   		    		</c:when>
	   									<c:otherwise>
	   										<c:set var="estilo" value="textos_formularios"/>
	   									</c:otherwise>
									</c:choose>
									<tr>
			                    		<td width="20%" align="right" valign="middle" nowrap="nowrap" class="${estilo}">
			                    			<div align="right">
					                 			<nested:write format="00" property="idConvenio" />
				                 			</div>
			                    		</td>
			                    		<td width="60%" align="left" valign="middle" class="${estilo}">
											<nested:write property="descripcion"/>
			                    		</td>
			                    		<td width="20%" align="left" valign="middle" nowrap="nowrap" class="${estilo}">
					                 		<nested:notEqual property="habilitado" value="0">
					                 			Habilitado
					                 		</nested:notEqual>
					                 		<nested:equal property="habilitado" value="0">
					                 			Deshabilitado
					                 		</nested:equal>
			                    		</td>
			                  		</tr>
								</nested:iterate>
							</table>
						</td>
					</tr>
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