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
<html:form action="/ListaCodigosFicha" styleId="formulario">
	<table width="590" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
					<tr>
						<td width="30%"><strong>Grupo de Convenios:</strong></td>
						<td>
							<nested:write property="nombreGrupoConvenio" />
						</td>
					</tr>
					<tr> 
						<td height="4" colspan="2" bgcolor="#85b4be"></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr valign="bottom"> 
						<td height="30" bgcolor="#FFFFFF" class="titulo">
							<strong>Lista de C&oacute;digos de Mapeo</strong>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
					<tr>
						<td width="17%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Concepto</td>
						<td width="63%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre Entidad</td>
						<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">C&oacute;digo</td>
					</tr>
					<tr> 
						<td colspan="3" nowrap="nowrap" class="textos_formularios" align="left">
							<strong>&nbsp;AFP</strong>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<nested:iterate id="fila" property="listaAFP" indexId="nFila">
									<c:choose>
					   		    		<c:when test="${nFila % 2 == 0}">
					   		    			<c:set var="estilo" value="textos-formcolor2"/>
					   		    		</c:when>
	   									<c:otherwise>
	   										<c:set var="estilo" value="textos_formularios"/>
	   									</c:otherwise>
									</c:choose>
									<tr>
										<td class="${estilo}" width="17%">&nbsp;</td>
			                    		<td align="left" valign="middle" class="${estilo}" width="63%">
			                    			<nested:write property="nombre" />&nbsp;
			                    		</td>
			                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="20%">
			                    			<div align="right"><nested:write property="idCodigo" /></div>
			                    		</td>
			                  		</tr>
								</nested:iterate>
							</table>
						</td>
					</tr>
					<tr> 
						<td colspan="3" nowrap="nowrap" class="textos_formularios" align="left">
							<strong>&nbsp;ISAPRE</strong>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<nested:iterate id="fila" property="listaISAPRE" indexId="nFila">
									<c:choose>
					   		    		<c:when test="${nFila % 2 == 0}">
					   		    			<c:set var="estilo" value="textos-formcolor2"/>
					   		    		</c:when>
	   									<c:otherwise>
	   										<c:set var="estilo" value="textos_formularios"/>
	   									</c:otherwise>
									</c:choose>
									<tr>
										<td class="${estilo}" width="17%">
											&nbsp;
										</td>
			                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="63%">
			                    			&nbsp;<nested:write property="nombre" />
			                    		</td>
			                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="20%">
			                    			&nbsp;<nested:write property="idCodigo" />
			                    		</td>
			                  		</tr>
								</nested:iterate>
							</table>
						</td>
					</tr>
					<tr> 
						<td colspan="3" nowrap="nowrap" class="textos_formularios" align="left">
							<strong>&nbsp;APV</strong>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<nested:iterate id="fila" property="listaAPV" indexId="nFila">
									<c:choose>
					   		    		<c:when test="${nFila % 2 == 0}">
					   		    			<c:set var="estilo" value="textos-formcolor2"/>
					   		    		</c:when>
	   									<c:otherwise>
	   										<c:set var="estilo" value="textos_formularios"/>
	   									</c:otherwise>
									</c:choose>
									<tr>
										<td class="${estilo}" width="17%">
											&nbsp;
										</td>
			                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="63%">
			                    			&nbsp;<nested:write property="nombre" />
			                    		</td>
			                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="20%">
			                    			&nbsp;<nested:write property="idCodigo" />
			                    		</td>
			                  		</tr>
								</nested:iterate>
							</table>
						</td>
					</tr>
					<tr> 
						<td colspan="3" nowrap="nowrap" class="textos_formularios" align="left">
							<strong>&nbsp;Movimiento Personal</strong>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<nested:iterate id="fila" property="listMvtoPer" indexId="nFila">
									<c:choose>
					   		    		<c:when test="${nFila % 2 == 0}">
					   		    			<c:set var="estilo" value="textos-formcolor2"/>
					   		    		</c:when>
	   									<c:otherwise>
	   										<c:set var="estilo" value="textos_formularios"/>
	   									</c:otherwise>
									</c:choose>
									<tr>
										<td class="${estilo}" width="17%">
											&nbsp;
										</td>
			                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="63%">
			                    			&nbsp;<nested:write property="nombre" />
			                    		</td>
			                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="20%">
			                    			&nbsp;<nested:write property="idCodigo" />
			                    		</td>
			                  		</tr>
								</nested:iterate>
							</table>
						</td>
					</tr>
					<tr> 
						<td colspan="3" nowrap="nowrap" class="textos_formularios" align="left">
							<strong>&nbsp;Movimiento Personal Afiliación Voluntaria</strong>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<nested:iterate id="fila" property="listMvtoPerAF" indexId="nFila">
									<c:choose>
					   		    		<c:when test="${nFila % 2 == 0}">
					   		    			<c:set var="estilo" value="textos-formcolor2"/>
					   		    		</c:when>
	   									<c:otherwise>
	   										<c:set var="estilo" value="textos_formularios"/>
	   									</c:otherwise>
									</c:choose>
									<tr>
										<td class="${estilo}" width="17%">
											&nbsp;
										</td>
			                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="63%">
			                    			&nbsp;<nested:write property="nombre" />
			                    		</td>
			                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="20%">
			                    			&nbsp;<nested:write property="idCodigo" />
			                    		</td>
			                  		</tr>
								</nested:iterate>
							</table>
						</td>
					</tr>
					<tr> 
						<td colspan="3" nowrap="nowrap" class="textos_formularios" align="left">
							<strong>&nbsp;G&eacute;nero</strong>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<nested:iterate id="fila" property="listaGenero" indexId="nFila">
									<c:choose>
					   		    		<c:when test="${nFila % 2 == 0}">
					   		    			<c:set var="estilo" value="textos-formcolor2"/>
					   		    		</c:when>
	   									<c:otherwise>
	   										<c:set var="estilo" value="textos_formularios"/>
	   									</c:otherwise>
									</c:choose>
									<tr>
										<td class="${estilo}" width="17%">
											&nbsp;
										</td>
			                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="63%">
			                    			&nbsp;<nested:write property="nombre" />
			                    		</td>
			                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="20%">
			                    			&nbsp;<nested:write property="idCodigo" />
			                    		</td>
			                  		</tr>
								</nested:iterate>
							</table>
						</td>
					</tr>
					<tr> 
						<td colspan="3" nowrap="nowrap" class="textos_formularios" align="left">
							<strong>&nbsp;Tramo Asignación Familiar</strong>
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<nested:iterate id="fila" property="listaTramo" indexId="nFila">
									<c:choose>
					   		    		<c:when test="${nFila % 2 == 0}">
					   		    			<c:set var="estilo" value="textos-formcolor2"/>
					   		    		</c:when>
	   									<c:otherwise>
	   										<c:set var="estilo" value="textos_formularios"/>
	   									</c:otherwise>
									</c:choose>
									<tr>
										<td class="${estilo}" width="17%">
											&nbsp;
										</td>
			                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="63%">
			                    			&nbsp;<nested:write property="nombre" />
			                    		</td>
			                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="20%">
			                    			&nbsp;<nested:write property="idCodigo" />
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
// End --> 
</script>
</html>
