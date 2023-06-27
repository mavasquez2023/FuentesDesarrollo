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
<html:form action="/DetalleMapeoArchivos" styleId="formulario">
<html:hidden name="accion" property="accion" styleId="accion" value="mapeo" />
<html:hidden name="subAccion" property="subAccion" styleId="subAccion" value="archivosLista" />
<html:hidden name="subSubAccion" property="subSubAccion" styleId="subSubAccion" value="archivosFicha" />
<c:set var="tipoSeparador"><%=request.getAttribute("tipoSeparador")%></c:set>
<table width="590" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td>
		<html:errors/>
	</td>
</tr>
<tr>
	<td>
		<html:messages id="msg" message="true">
			<div class="msgExito">${msg}</div>
		</html:messages>
	</td>
</tr>
<tr>
	<td valign="top">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td>
          <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
          	<tr>
          		<td width="30%"><strong>Grupo de Convenios:</strong></td>
	            <td>
	            	<nested:select property="opcGrupoConvenio" styleClass="campos" onchange="javascript:bCancel=true;submit();">
	            		<nested:optionsCollection property="gruposConvenio" />
	            	</nested:select>
               	</td>
          	</tr>
          	<tr>
	            <td><strong>Tipo de N&oacute;mina:</strong></td>
	            <td>
	            	<nested:select property="opcTipoNomina" styleClass="campos" onchange="javascript:bCancel=true;submit();">
	            		<nested:optionsCollection property="tiposNomina" />
	            	</nested:select>
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
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
        	<tr valign="bottom"> 
            	<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Mapeo de campos de archivo</strong></td>
                <td align="right" bgcolor="#FFFFFF">
					<input type="button" class="btn3" value="Imprimir" onclick="javascript:imprimir();" />&nbsp;&nbsp;&nbsp;
					<html:submit property="operacion" styleClass="btn4" value="Editar"/>
		        </td>
            </tr>
       		</table>
       		<c:choose>
       			<c:when test="${tipoSeparador == 1}">
       				<c:set var="estiloCabecera" value='class="barra_tablas"'/>
       			</c:when>
       			<c:otherwise>
       				<c:set var="estiloCabecera" value='style="display:none;"'/>
       			</c:otherwise>
       		</c:choose>
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
            	<tr> 
                    <td align="center" bgcolor="#FFFFFF">
               		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
						<tr>
		                 	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas"><%@ include file="/html/comun/flecha.jspf"%>Nº</td>
		                 	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
		                 	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Posici&oacute;n <c:if test="${tipoSeparador == 1}">Inicial</c:if></td>
		                 	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" ${estiloCabecera}>Largo</td>
	               		</tr>
	               		<nested:notEmpty property="consulta">
							<nested:iterate property="consulta" indexId="nFila">
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
		               			<tr>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}">
		                    			${nFila + 1}
		                    		</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}">
		                    			<nested:write property="nombre"/>
		                    		</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}">
		                    			<div align="right">
		                    				<nested:write property="posicion"/>
		                    			</div>
		                    		</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" ${estiloCelda}>
		                    			<div align="right">
											<nested:write property="largo"/>
		                    			</div>
		                    		</td>
		                  		</tr>
	                  		</nested:iterate>
                  		</nested:notEmpty>
	               		<nested:empty property="consulta">
	               			<tr>
		                    	<td align="right" valign="middle" nowrap="nowrap" class="textos_formularios" colspan="4">
			               			No hay mapeo de campos de archivo definidos para este grupo de convenios y tipo de proceso
	    	           			</td>
	               			</tr>
	               		</nested:empty>
					</table>
					</td>
				</tr>
            </table>
            </td>
        </tr>
	</table>
    </td>
</tr>
</table>
</html:form>
</body>
</html:html>