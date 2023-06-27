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
<c:set var="tipoSeparador"><%=request.getAttribute("tipoSeparador")%></c:set>
<c:choose>
	<c:when test="${tipoSeparador == 1}">
		<c:set var="estiloCabecera" value='class="barra_tablas"'/>
	</c:when>
	<c:otherwise>
		<c:set var="estiloCabecera" value='style="display:none;"'/>
	</c:otherwise>
</c:choose>
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
<html:form action="/DetalleMapeoArchivos" styleId="formulario">
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
						<td width="30%"><strong>Tipo de N&oacute;mina:</strong></td>
						<td>
							<nested:write property="nombreTipoNomina" />
						</td>
					</tr>
		          	<c:if test="${tipoSeparador == 2}">
			          	<tr>
				            <td><strong>Caracter Separador:</strong></td>
				            <td>
				            	<c:out value="${caracterSeparador}"/>
			               	</td>
			          	</tr>
		          	</c:if>					
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
							<strong>Mapeo de campos de archivo</strong>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
					<tr>
						<td width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">N&ordm;</td>
						<td width="50%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre <nested:write property="tienePrevired"/></td>
						<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Posici&oacute;n <c:if test="${tipoSeparador == 1}">Inicial</c:if></td>
						<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" ${estiloCabecera}>Largo</td>
					</tr>
					<tr>
						<td colspan="4">
		               		<nested:notEmpty property="consulta">
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

										<c:choose>
											<c:when test="${tipoSeparador == 1}">
												<c:set var="estiloCelda" value='class="${estilo}"'/>
											</c:when>
											<c:otherwise>
												<c:set var="estiloCelda" value='style="display:none;"'/>
											</c:otherwise>
										</c:choose>										

										<c:set var="concepto"><nested:write property="idConcepto"/></c:set>

										<c:choose>
											<c:when test="${concepto < 134 || concepto > 136}">
												<tr>
						                    		<td width="10%" align="right" valign="middle" nowrap="nowrap" class="${estilo}">
						                    			<div align="right">${nFila + 1}</div>
						                    		</td>
						                    		<td width="50%" align="left" valign="middle" nowrap="nowrap" class="${estilo}">
						                    			<nested:write property="nombre"/>
						                    		</td>
						                    		<td width="20%" align="right" valign="middle" nowrap="nowrap" class="${estilo}">
						                    			<div align="right">
							                    			<nested:write property="posicion"/>
							                    		</div>
						                    		</td>
						                    		<td width="20%" align="right" valign="middle" nowrap="nowrap" ${estiloCelda}>
						                    			<div align="right">
							                    			<nested:write property="largo"/>
							                    		</div>
						                    		</td>
						                  		</tr>
					                  		</c:when>											
											<c:otherwise>
												<logic:equal name="tienePrevired" value="true">
													<tr>
							                    		<td width="10%" align="right" valign="middle" nowrap="nowrap" class="${estilo}">
							                    			<div align="right">${nFila + 1}</div>
							                    		</td>
							                    		<td width="50%" align="left" valign="middle" nowrap="nowrap" class="${estilo}">
							                    			<nested:write property="nombre"/>
							                    		</td>
							                    		<td width="20%" align="right" valign="middle" nowrap="nowrap" class="${estilo}">
							                    			<div align="right">
								                    			<nested:write property="posicion"/>
								                    		</div>
							                    		</td>
							                    		<td width="20%" align="right" valign="middle" nowrap="nowrap" ${estiloCelda}>
							                    			<div align="right">
								                    			<nested:write property="largo"/>
								                    		</div>
							                    		</td>
							                  		</tr>
												</logic:equal>
											</c:otherwise>
										</c:choose>

										<%--tr>
				                    		<td width="10%" align="right" valign="middle" nowrap="nowrap" class="${estilo}">
				                    			<div align="right">${nFila + 1}</div>
				                    		</td>
				                    		<td width="50%" align="left" valign="middle" nowrap="nowrap" class="${estilo}">
				                    			<nested:write property="nombre"/>
				                    		</td>
				                    		<td width="20%" align="right" valign="middle" nowrap="nowrap" class="${estilo}">
				                    			<div align="right">
					                    			<nested:write property="posicion"/>
					                    		</div>
				                    		</td>
				                    		<td width="20%" align="right" valign="middle" nowrap="nowrap" ${estiloCelda}>
				                    			<div align="right">
					                    			<nested:write property="largo"/>
					                    		</div>
				                    		</td>
				                  		</tr--%>
									</nested:iterate>
								</table>
							</nested:notEmpty>
							<nested:empty property="consulta">
								<table border="0" cellpadding="0" cellspacing="0" width="100%">
			               			<tr>
				                    	<td align="right" valign="middle" nowrap="nowrap" class="textos_formularios" colspan="4">
					               			No hay mapeo de campos de archivo definidos para este grupo de convenios y Tipo de N&oacute;mina
			    	           			</td>
			               			</tr>
			               		</table>
							</nested:empty>
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
