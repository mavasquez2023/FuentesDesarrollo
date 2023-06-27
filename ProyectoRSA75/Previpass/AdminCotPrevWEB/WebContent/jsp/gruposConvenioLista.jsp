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
	
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
</head>
<body onLoad="poneFoco('idGrupoConvenio')" >


<html:form action="/ListarGruposConvenio" styleId="formulario" onsubmit="return onFormSubmit(this);" >
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="grupoLista" />
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
    	<td align="left" valign="top">
	        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
				<tr> 
	           		<td><strong>C&oacute;digo</strong></td>
	           		<td><html:text property="idGrupoConvenio" styleId="idGrupoConvenio" maxlength="9" size="16" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/></td>
	           		<td><strong>Nombre</strong></td>
	           		<td><html:text property="nombreGrupoConvenio" styleId="nombreGrupoConvenio" maxlength="30" size="50" styleClass="campos" onblur="javascript:soloRazonSocial(this);" onkeyup="javascript:soloRazonSocial(this);"/></td>
	         	</tr>
	         	<tr> 
	           		<td colspan="3">&nbsp;</td>
	           		<td align="right">
	           			<html:submit property="operacion" styleClass="btn3" value="Buscar" />&nbsp;&nbsp;&nbsp;
	           		</td>
	         	</tr>
	         	<tr> 
	           		<td height="4" colspan="5" bgcolor="#85b4be"></td>
	         	</tr>
			</table>
		</td>
	</tr>
	<c:if test="${param['idGrupoConvenio'] != null}">
	<tr> 
		<td align="left" valign="middle" height="40">
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
            	<tr valign="bottom"> 
              		<td height="30" bgcolor="#FFFFFF" class="titulo">
              			<strong>Lista de Grupos de Convenios</strong>
              		</td>
              		<td align="right" bgcolor="#FFFFFF">
              			<html:cancel property="operacion" styleClass="btn3" value="Crear grupo" />&nbsp;&nbsp;&nbsp;
              			<html:button property="operacion" styleClass="btn3" value="Imprimir" onclick="javascript:abrirDocImpresion1();" />
	              	</td>
				</tr>
	         </table>
        </td>
	</tr>
	<tr align="center"> 
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr> 
					<td align="center" bgcolor="#FFFFFF">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
							<tr class="subtitulos_tablas">
								<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">
								<%@ include file="/html/comun/flecha.jspf"%> C&oacute;digo
								</td>
                                <td width="55%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">
                               		Nombre
                               	</td>
                                <td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">
                                	Estado
                                </td>
                                <td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas" colspan="2">
                               		Acci&oacute;n
                                </td>
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
		                                <td align="center" nowrap="nowrap" class="${estilo}">
		                                	<div align="center">
		                                		<html:link action="/DetalleGrupoConvenio.do?accion=admin&subAccion=empresas&subSubAccion=grupoDetalle&grupoConvenio=${filaConsulta.idGrupoConvenio}" title="Ver detalle"><img src="<c:url value="/img/iconos/lupa.gif" />" width="14" height="13" border="0"/></html:link>
		                                	</div>
		                                </td>
		                                <td align="center" nowrap="nowrap" class="${estilo}">
		                                	<div align="center">
		                                		<html:link action="/DetalleGrupoConvenio.do?accion=admin&subAccion=empresas&subSubAccion=grupoEditar&grupoConvenio=${filaConsulta.idGrupoConvenio}" title="Editar"><img src="<c:url value="/img/iconos/ico_hojap.gif" />" alt="Editar" title="Editar" width="14" height="13" border="0"/></html:link>
			                            	</div>
		                                </td>
		                            </tr>
	                            </nested:iterate>
							</nested:notEmpty>
							<nested:empty property="consulta">
								<tr>
									<td align="left" nowrap="nowrap" class="textos_formularios" colspan="5">
										No hay grupos de convenios que cumplan los criterios especificados
									</td>
								</tr>
							</nested:empty>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
		<!-- Insertando Paginacion -->
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr> 
			<bean:size id="nPags" name="GruposConvenioActionForm" property="numeroFilas"/>
				<c:if test="${nPags > 1}">
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr> 
								<td align="center" valign="middle" class="numeracion">
									<logic:iterate id="paginacion" name="GruposConvenioActionForm" property="numeroFilas">
										${paginacion}
									</logic:iterate>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</c:if>
			</tr>
		</table>
		</td>
	</tr>
	</c:if>
	<c:if test="${param['idGrupoConvenio'] == null}">
	<tr> 
		<td align="right" valign="bottom" bgcolor="#FFFFFF" height="30">
        	<html:cancel property="operacion" styleClass="btn3" value="Crear grupo" />&nbsp;&nbsp;&nbsp;
        </td>
	</tr>
	</c:if>
</table>
<html:hidden property="idHidden" styleId="idHidden" />
<html:hidden property="nombreHidden" styleId="nombreHidden" />
</html:form>

</body>
<script language="javaScript">
<!--
	function validaFormulario(f) 
	{
		//Valida caracteres validos en caja de busqueda
		var sMsje = "";
		if (validaReq(document.getElementById("idGrupoConvenio")) &&
			!validaUInt(document.getElementById("idGrupoConvenio").value))
			sMsje += "* No es un número válido el ID del grupo de convenios\n";
		if (validaReq(document.getElementById("nombreGrupoConvenio")) &&
			!validaRazonSocial(document.getElementById("nombreGrupoConvenio").value))
			sMsje += "* Caracteres inválidos en el nombre del grupo de convenios\n";

		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		
 		document.getElementById("nombreHidden").value=document.getElementById("nombreGrupoConvenio").value;
 		document.getElementById("idHidden").value=document.getElementById("idGrupoConvenio").value;
		return true;
	}

	function retornaEnlace(paginacion)
	{
			nom_n=document.getElementById("nombreGrupoConvenio");
	 		id_n=document.getElementById("idGrupoConvenio");
	 		
	 		nom_a=document.getElementById("nombreHidden");
	 		id_a=document.getElementById("idHidden");	
	 		if(nom_a!=nom_n){
	 			nom_n.value=nom_a.value;
	 		}
	 		if(id_a!=id_n){
	 			id_n.value=id_a.value;
	 		}
	 		formu = document.getElementById("formulario");
			formu.action = "ListarGruposConvenio.do?paginaNumero=" + paginacion;
			formu.submit();
	}
	
	function abrirDocImpresion1()
	{
	   var nombre=document.getElementById("nombreHidden").value;
	   var idgrupo=document.getElementById("idHidden").value;
	   var URL="/AdminCotPrevWEB/ListarGruposConvenio.do?nombreHidden="+nombre+"&idHidden="+idgrupo+"&imprimir=";
	   abrirDocImpresion(URL);
 	}
// -->
</script>
</html:html>
