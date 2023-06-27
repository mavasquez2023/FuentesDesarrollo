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
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
</head>
<body>
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/ListaMapeoArchivos" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden name="accion" property="accion" styleId="accion" value="mapeo" />
<html:hidden name="subAccion" property="subAccion" styleId="subAccion" value="archivosLista" />
<table width="590" border="0" cellspacing="0" cellpadding="0">
  	<tr> 
    	<td align="left" valign="top">
    	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
          	<tr> 
	            <td width="30%"><strong>Grupo de Convenios:</strong></td>
	            <td>
					<nested:select property="opcGrupoConvenio" styleClass="campos" onchange="javascript:submit();">
						<nested:optionsCollection property="gruposConvenio" />
						<nested:define id="opcGrupoConvenio" property="opcGrupoConvenio"/>
					</nested:select>
               	</td>
          	</tr>
          	<tr> 
            	<td height="4" colspan="2" bgcolor="#85b4be"></td>
         	</tr>
        </table>
        <br />
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
        	<tr valign="bottom"> 
            	<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Mapeos de Archivos</strong></td>
            </tr>
        </table>
        </td>
    </tr>
    <tr align="center"> 
   		<td valign="top" bgcolor="#CCCCCC">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
            	<tr> 
                    <td align="center" bgcolor="#FFFFFF">
               		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
						<tr>
		                 	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Identificador</td>
		                 	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Descripci&oacute;n</td>
		                 	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
	               		</tr>
               			<tr> 
		                 	<td align="center" valign="middle" nowrap="nowrap" class="textos_formularios"><div align="center"><a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=R&grupoConvenio=${opcGrupoConvenio}" />" title="Remuneraci&oacute;n" class="links">1 Remuneraci&oacute;n</a></div></td>
		                 	<td nowrap="nowrap" class="textos_formularios" align="left"><nested:write property="descripcionR"/></td>
                   			<td align="center" nowrap="nowrap" class="textos_formularios"><div align="center"><a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosEditar&tipoNomina=R&grupoConvenio=${opcGrupoConvenio}" />"><img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" height="13" border="0"/></a></div></td>
               			</tr>
               			<tr> 
		                 	<td align="center" valign="middle" nowrap="nowrap" class="textos-formcolor2"><div align="center"><a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=A&grupoConvenio=${opcGrupoConvenio}" />" title="Reliquidaci&oacute;n" class="links">2 Reliquidaci&oacute;n</a></div></td>
		                 	<td nowrap="nowrap" class="textos-formcolor2" align="left"><nested:write property="descripcionA"/></td>
                   			<td align="center" nowrap="nowrap" class="textos-formcolor2"><div align="center"><a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosEditar&tipoNomina=A&grupoConvenio=${opcGrupoConvenio}" />"><img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" height="13" border="0"/></a></div></td>
               			</tr>
               			<tr> 
		                 	<td align="center" valign="middle" nowrap="nowrap" class="textos_formularios"><div align="center"><a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=G&grupoConvenio=${opcGrupoConvenio}" />" title="Gratificaci&oacute;n" class="links">3 Gratificaci&oacute;n</a></div></td>
		                 	<td nowrap="nowrap" class="textos_formularios" align="left"><nested:write property="descripcionG"/></td>
                   			<td align="center" nowrap="nowrap" class="textos_formularios"><div align="center"><a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosEditar&tipoNomina=G&grupoConvenio=${opcGrupoConvenio}" />"><img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" height="13" border="0"/></a></div></td>
               			</tr>
               			<tr> 
		                 	<td align="center" valign="middle" nowrap="nowrap" class="textos-formcolor2"><div align="center"><a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=D&grupoConvenio=${opcGrupoConvenio}" />" title="Dep&oacute;sito Convenido" class="links">4 Dep&oacute;sito Convenido</a></div></td>
		                 	<td nowrap="nowrap" class="textos-formcolor2" align="left"><nested:write property="descripcionD"/></td>
                   			<td align="center" nowrap="nowrap" class="textos-formcolor2"><div align="center"><a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosEditar&tipoNomina=D&grupoConvenio=${opcGrupoConvenio}" />"><img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" height="13" border="0"/></a></div></td>
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
