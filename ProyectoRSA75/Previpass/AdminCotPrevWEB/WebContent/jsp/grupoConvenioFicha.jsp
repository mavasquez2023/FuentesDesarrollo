<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
</head>
<body>
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/DetalleGrupoConvenio" styleId="formulario">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="grupoDetalle" />
<c:set var="tipoSeparador"><%=request.getAttribute("tipoSeparador")%></c:set>
<c:set var="caracterSeparador"><%=request.getAttribute("caracterSeparador")%></c:set>
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
    	<td valign="top">
      	<table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
              <tr valign="bottom"> 
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Detalle Grupo de Convenios</strong></td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
              <tr> 
                <td width="25%" height="17" class="barratablas"><strong>C&oacute;digo:</strong></td>
                <td width="25%" height="17" class="textos_formularios">
                	<input type="hidden" name="grupoConvenio" value="<nested:write property='idGrupoConvenio'/>"/>
                	<nested:write property="idGrupoConvenio"/>
                </td>
                <td width="25%" height="17" class="barratablas"><strong>Nombre:</strong></td>
                <td width="25%" height="17" class="textos_formularios">
                	<nested:hidden property="nombreGrupoConvenio" write="true"/>
                </td>
              </tr>
              <tr>
                <td height="17" class="barratablas"><strong>Estado:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:equal value="0" property="estadoGrupoConvenio">
                		Deshabilitado
                	</nested:equal>
                	<nested:equal value="1" property="estadoGrupoConvenio">
                		Habilitado
                	</nested:equal>
                </td>
                <td height="17" class="barratablas"><strong>Creado:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:write format="dd/MM/yyyy" property="fechaCreacion"/>
                </td>
              </tr>

				<tr>
					<td height="17" class="barratablas">
						<strong>Indicador tipo archivo</strong>
					</td>
					<td height="17" class="textos_formularios" <c:if test="${tipoSeparador == 1}">colspan="3"</c:if>  >
						<c:choose>
							<c:when test="${tipoSeparador == 1}">Largo</c:when>
							<c:otherwise>Separador</c:otherwise>
						</c:choose>
					</td>
					<c:if test="${tipoSeparador == 2}">
						<td height="17" class="barratablas">
							<strong>Caracter separador</strong>
						</td>
						<td height="17" class="textos_formularios">
							<c:out value="${caracterSeparador}"/>
						</td>
					</c:if>
				</tr>

				<tr> 
            		<td height="4" colspan="4" bgcolor="#85b4be">
            		</td>
				</tr>
          		<tr>
          			<td height="17" class="barratablas" colspan="4">
          				<strong>Opciones de proceso</strong>
          			</td>
          		<tr>
				<tr>
					<td colspan="4">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
			          		<tr>
								<td width="35%" height="17" class="barratablas">
									Generar planillas INP por sucursal?:
								</td>
								<td width="15%" height="17" class="textos_formularios">
									<nested:equal property="genINPPorSucursal" value="1">S&iacute;</nested:equal>
									<nested:notEqual property="genINPPorSucursal" value="1">No</nested:notEqual>
								</td>
								<td width="35%" height="17" class="barratablas">
									Calcular monto total CCAF?:		
								</td>
								<td width="15%" height="17" class="textos_formularios">
									<nested:equal property="calcularMontoCCAF" value="1">S&iacute;</nested:equal>
									<nested:notEqual property="calcularMontoCCAF" value="1">No</nested:notEqual>
								</td>
			          		</tr>
			          		<tr>
								<td height="17" class="barratablas">
									Generar planillas Mutual por sucursal?:
								</td>
								<td height="17" class="textos_formularios">
									<nested:equal property="genMutualPorSucursal" value="1">S&iacute;</nested:equal>
									<nested:notEqual property="genMutualPorSucursal" value="1">No</nested:notEqual>
								</td>
								<td height="17" class="barratablas">
									Calcular monto total Salud?:
								</td>
								<td height="17" class="textos_formularios">
									<nested:equal property="calcularMontoTotalSalud" value="1">S&iacute;</nested:equal>
									<nested:notEqual property="calcularMontoTotalSalud" value="1">No</nested:notEqual>
								</td>
			          		</tr>
			          		<tr>
								<td height="17" class="barratablas">
									Generar planillas CCAF por sucursal?:
								</td>
								<td height="17" class="textos_formularios">
									<nested:equal property="genCCAFPorSucursal" value="1">S&iacute;</nested:equal>
									<nested:notEqual property="genCCAFPorSucursal" value="1">No</nested:notEqual>
								</td>
								<td height="17" class="barratablas">
									Calcular monto total Previsi&oacute;n?:
								</td>
								<td height="17" class="textos_formularios">
									<nested:equal property="calcularMontoTotalPrev" value="1">S&iacute;</nested:equal>
									<nested:notEqual property="calcularMontoTotalPrev" value="1">No</nested:notEqual>
								</td>
			          		</tr>
			          		<tr>
								<td height="17" class="barratablas">
									Calcular monto IPS(INP)?:
								</td>
								<td height="17" class="textos_formularios">
									<nested:equal property="calcularMontoINP" value="1">S&iacute;</nested:equal>
									<nested:notEqual property="calcularMontoINP" value="1">No</nested:notEqual>
								</td>
								<td height="17" class="barratablas">
									Imprimir planillas?:
								</td>
								<td height="17" class="textos_formularios">
									<nested:equal property="imprimirPlanillas" value="1">S&iacute;</nested:equal>
									<nested:notEqual property="imprimirPlanillas" value="1">No</nested:notEqual>
								</td>
			          		</tr>
			          		<tr>
								<td height="17" class="barratablas">
									Calcular monto total Mutual?:
								</td>
								<td height="17" class="textos_formularios">
									<nested:equal property="calcularMontoMutual" value="1">S&iacute;</nested:equal>
									<nested:notEqual property="calcularMontoMutual" value="1">No</nested:notEqual>
								</td>
								<td height="17" class="barratablas">
									Calcular Fonasa?
								</td>
								<td height="17" class="textos_formularios">
									<nested:equal property="calcularFonasa" value="1">S&iacute;</nested:equal>
									<nested:notEqual property="calcularFonasa" value="1">No</nested:notEqual>
								</td>
			          		</tr>
						</table>
					</td>
				</tr>


              <tr> 
                <td height="4" colspan="4" bgcolor="#85b4be"></td>
              </tr>

            </table>
            <br />
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
              <tr valign="bottom"> 
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Mapeo de Archivos de N&oacute;minas</strong></td>
              </tr>
            </table>
            <bean:define id="idGrupoConvenio"><nested:write property="idGrupoConvenio"/></bean:define>
            <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
              <tr> 
                <td width="23%" height="17" class="barratablas"><strong>Remuneraciones:</strong></td>
                <td height="17" class="textos_formularios"><nested:write property="descripcionR"/></td>
                <td width="13%" height="17" class="textos_formularios">
                	<div align="center">
                		<html:link action="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=R&grupoConvenio=${idGrupoConvenio}" title="Ver detalle" styleClass="links">
                			<img src="<c:url value="/img/iconos/lupa.gif" />" width="14" height="13" border="0"/>
                		</html:link>
                	</div>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Gratificaciones:</strong></td>
                <td height="17" class="textos_formularios"><nested:write property="descripcionG"/></td>
                <td height="17" class="textos_formularios">
                	<div align="center">
                		<html:link action="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=G&grupoConvenio=${idGrupoConvenio}" title="Ver detalle" styleClass="links">
                			<img src="<c:url value="/img/iconos/lupa.gif" />" width="14" height="13" border="0"/>
                		</html:link>
                	</div>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Reliquidaciones:</strong></td>
                <td height="17" class="textos_formularios"><nested:write property="descripcionA"/></td>
                <td  height="17" class="textos_formularios">
                	<div align="center">
                		<html:link action="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=A&grupoConvenio=${idGrupoConvenio}" title="Ver detalle" styleClass="links">
                			<img src="<c:url value="/img/iconos/lupa.gif" />" width="14" height="13" border="0"/>
                		</html:link>
                	</div>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Dep&oacute;sitos Convenidos e Indemnizaciones:</strong></td>
                <td height="17" class="textos_formularios"><nested:write property="descripcionD"/></td>
                <td height="17" class="textos_formularios">
                	<div align="center">
                		<html:link action="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=D&grupoConvenio=${idGrupoConvenio}" title="Ver detalle" styleClass="links">
                			<img src="<c:url value="/img/iconos/lupa.gif" />" width="14" height="13" border="0"/>
                		</html:link>
                	</div>
                </td>
              </tr>
              <tr> 
                <td height="4" colspan="4" bgcolor="#85b4be"></td>
              </tr>
            </table>
            <br />
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
              <tr valign="bottom"> 
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Mapeo de C&oacute;digos</strong></td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
              <tr> 
                <td width="23%" height="17" class="barratablas"><strong>Mapeo:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:write property="nombreMapeoCodigo"/>
                </td>
                <td width="13%" height="17" class="textos_formularios">
                	<div align="center">
                		<html:link action="/ListaCodigosFicha.do?accion=mapeo&subAccion=codigosFicha&grupoConvenio=${idGrupoConvenio}" title="Ver detalle" styleClass="links">
                			<img src="<c:url value="/img/iconos/lupa.gif" />" width="14" height="13" border="0"/>
                		</html:link>
                	</div>
                </td>
              </tr>
              <tr> 
                <td height="4" colspan="4" bgcolor="#85b4be"></td>
              </tr>
            </table>
            <br />
		                	</td>
		            	</tr>
  <tr align="center">
    <td valign="top"><br />
    	<html:submit property="operacion" value="Editar" styleClass="btn3"/>
      </td>
  </tr>
</table>
</td>
</tr>
</table>
</html:form>
</body>
</html:html>
