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
<body onLoad="poneFoco('rutEmpresaBuscar')">
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/ListarEmpresas" styleId="formulario" onsubmit="return onFormSubmit(this);">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="empresaLista" />

<html:hidden property="rutOculto" styleId="rutOculto"/>
<html:hidden property="nombreOculto" styleId="nombreOculto"/>
<html:hidden property="razonOculto" styleId="razonOculto"/>
<html:hidden property="idGrupoOculto" styleId="idGrupoOculto"/>
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
	           		<td><strong>Rut Empresa:</strong></td>
	           		<td><html:text property="rutEmpresaBuscar" styleId="rutEmpresaBuscar" maxlength="13" size="18" styleClass="campos" onblur="javascript:soloRut(this);limpiaGrupo();" onkeyup="javascript:soloRut(this);javascript:limpiaGrupo();"/></td>
	           		<td><strong>Raz&oacute;n Social:</strong></td>
	           		<td><html:text property="razonSocialEmpresaBuscar" styleId="razonSocialEmpresaBuscar" maxlength="100" size="30" styleClass="campos" onblur="javascript:soloRazonSocial(this);limpiaGrupo();" onkeyup="javascript:soloRazonSocial(this);limpiaGrupo();"/></td>
	           	</tr>
	           	<tr>
	           		<td><strong>Id Grupo Convenio:</strong></td>
	           		<td><html:text property="codGrupoConvenioBuscar" styleId="codGrupoConvenioEmpresaBuscar" maxlength="9" size="18" styleClass="campos" onblur="javascript:soloNumero(this, '');limpiaEmp();" onkeyup="javascript:soloNumero(this, '');limpiaEmp();"/></td>
	         		<td><strong>Nombre Grupo Convenio:</strong></td>
	           		<td><html:text property="nombreGrupoConvBuscar" styleId="nombreGrupoConvBuscar" maxlength="30" size="30" styleClass="campos" onblur="javascript:soloRazonSocial(this);limpiaEmp();" onkeyup="javascript:soloRazonSocial(this);limpiaEmp();"/></td>
	           	</tr>
	         	<tr> 
	           		<td colspan="3">&nbsp;</td>
	           		<td align="right">
	           			<html:submit property="operacion" styleClass="btn3" value="Buscar"/>&nbsp;&nbsp;&nbsp;
	           		</td>
	         	</tr>
	         	<tr> 
	           		<td height="4" colspan="4" bgcolor="#85b4be"></td>
	         	</tr>
			</table>
		</td>
	</tr>
	<logic:notEqual name="ListarEmpresasActionForm" property="mostrarLista" value="SI"> 
	<tr>
		<td align="right" valign="bottom" bgcolor="#FFFFFF" height="30">
			<nested:empty property="consulta">
				<html:button property="operacion" styleClass="btn3" value="Crear Empresa" onclick="javascript:crea();" />&nbsp;&nbsp;&nbsp;
		    </nested:empty>
		</td>
	</tr>	
	</logic:notEqual> 
	<logic:equal name="ListarEmpresasActionForm" property="mostrarLista" value="SI">
		<tr> 
			<td align="left" valign="middle" height="40">
	        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
	            	<tr valign="bottom"> 
	              		<td height="30" bgcolor="#FFFFFF" class="titulo">
	              			<strong>Lista de Empresas</strong>
	              		</td>
	              		<td align="right" bgcolor="#FFFFFF">

	              			<html:button property="operacion" styleClass="btn3" value="Crear Empresa" onclick="javascript:crea();" />&nbsp;&nbsp;&nbsp;
	              			<html:button property="operacion" styleClass="btn3" value="Imprimir" onclick="javascript:abrirDocImpresion1();" />
		              	</td>
					</tr>
		         </table>
	        </td>
		</tr>
		<tr align="center"> 
			<td height="33" valign="top">
				<table width="590" border="0" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" bgcolor="#FFFFFF">
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
								<tr class="subtitulos_tablas">
									<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">
										<%@ include file="/html/comun/flecha.jspf"%> RUT
									</td>
	                                <td width="55%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
	                                <td width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Estado</td>
	                                <td width="20%" colspan="4" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas"><div align="center">Acci&oacute;n</div></td>
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
			                                <td class="${estilo}">
			                                	<nested:write property="razonSocial" />
			                                </td>
			                                <td nowrap="nowrap" class="${estilo}">
			                                	<nested:notEqual property="habilitada" value="0">
			                                		Habilitada
			                                	</nested:notEqual>
			                                	<nested:equal property="habilitada" value="0">
			                                		Deshabilitada
			                                	</nested:equal>
			                                </td>
			                                <td nowrap="nowrap" class="${estilo}">
			                                	<div align="center">
			                                		<html:link title="Ver detalle" action="/DetalleEmpresa.do?accion=admin&subAccion=empresas&subSubAccion=empresaDetalle&rutEmpresa=${filaConsulta.idEmpresa}"><img src="<c:url value="/img/iconos/lupa.gif" />" width="14" height="13" border="0" /></html:link>
			                                	</div>
											</td>
			                                <td nowrap="nowrap" class="${estilo}">
				                                <div align="center">
			                                		<html:link title="Listar sucursales" action="/SucursalesLista.do?accion=admin&subAccion=empresas&subSubAccion=sucursalesLista&rutEmpresa=${filaConsulta.idEmpresa}"><img alt="Listar sucursales" title="Listar sucursales" src="<c:url value="/img/iconos/btnListaSucursal.gif" />" width="14" height="13" border="0" /></html:link>
			                                	</div>
											</td>
			                                <td align="center" nowrap="nowrap" class="${estilo}">
			                                	<div align="center">
			                                		<html:link title="Listar convenios" action="/ListarConvenios.do?accion=admin&subAccion=empresas&subSubAccion=conveniosLista&rutEmpresa=${filaConsulta.idEmpresa}"><img alt="Listar convenios" title="Listar convenios" src="<c:url value="/img/iconos/ico_lista.gif" />" width="18" height="16" border="0" /></html:link>
			                                	</div>
											</td>
			                                <td align="center" nowrap="nowrap" class="${estilo}">
			                                	<div align="center">
			                                		<html:link title="Editar" action="/DetalleEmpresa.do?accion=admin&subAccion=empresas&subSubAccion=empresaEditar&rutEmpresa=${filaConsulta.idEmpresa}"><img src="<c:url value="/img/iconos/ico_hojap.gif" />" alt="Modificar Empresa" title="Modificar Empresa" width="14" height="13" border="0"/></html:link>
			                                	</div>
			                                </td>
			                            </tr>
		                            </nested:iterate>
								</nested:notEmpty>
								<nested:empty property="consulta">
									<tr>
										<td align="left" nowrap="nowrap" class="textos_formularios" colspan="7">
											No hay empresas administradas por el usuario
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
				<bean:size id="nPags" name="ListarEmpresasActionForm" property="numeroFilas"/>
				<c:if test="${nPags > 1}">
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr> 
								<td align="center" valign="middle" class="numeracion">
									<logic:iterate id="paginacion" name="ListarEmpresasActionForm" property="numeroFilas">
										${paginacion}
									</logic:iterate>
								</td>
							</tr>
						</table>
						</td>
					</tr>
				</c:if>
			</table>
			</td>
		</tr>
	</logic:equal>
	 
		 
	 
</table>
</html:form>
<script language="javaScript"> 
<!--	
	function crea() 
	{
		document.getElementById("formulario").action="/AdminCotPrevWEB/DetalleEmpresa.do";
		document.getElementById("subSubAccion").value = "empresaCrear";
		document.getElementById("formulario").submit();
	}

	function validaFormulario(f) 
	{
		//Valida caracteres validos en caja de busqueda
		var sMsje = "";
		var posGuion = document.getElementById("rutEmpresaBuscar").value.indexOf('-');
		var rutTMP = limpiaRutIncremental(document.getElementById("rutEmpresaBuscar").value);
		if (document.getElementById("rutEmpresaBuscar").value.length > 0 && rutTMP.length < 4)
			sMsje += "* Debe ingresar al menos 4 dígitos de rut para el usuario a buscar\n";
		//else if(document.getElementById("rutEmpresaBuscar").value.length > 0 && posGuion > 0 && !validaDV(rutTMP))
		else if(document.getElementById("rutEmpresaBuscar").value.length > 0 && posGuion > 0 && !validaDV(document.getElementById("rutEmpresaBuscar").value))
			sMsje += ' * Dígito verificador de Rut de empresa a buscar incorrecto';

		if (validaReq(document.getElementById("nombreGrupoConvBuscar")) && !validaRazonSocial(document.getElementById("nombreGrupoConvBuscar").value))
			sMsje += "* Caracteres inválidos en el nombre del Grupo de Convenios a buscar\n";
		else if (document.getElementById("nombreGrupoConvBuscar").value.length > 0 && document.getElementById("nombreGrupoConvBuscar").value.length < 3)
			sMsje += "* Debe ingresar al menos 3 caracteres en el nombre del Grupo de Convenios a buscar\n";
		if (validaReq(document.getElementById("razonSocialEmpresaBuscar")) && !validaRazonSocial(document.getElementById("razonSocialEmpresaBuscar").value))
			sMsje += "* Caracteres inválidos en el nombre de la Empresa a buscar\n";
		else if (document.getElementById("razonSocialEmpresaBuscar").value.length > 0 && document.getElementById("razonSocialEmpresaBuscar").value.length < 3)
			sMsje += "* Debe ingresar al menos 3 caracteres en el nombre de la Empresa a buscar\n";
		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		document.getElementById("rutOculto").value=document.getElementById("rutEmpresaBuscar").value;
		document.getElementById("nombreOculto").value=document.getElementById("nombreGrupoConvBuscar").value;
		document.getElementById("idGrupoOculto").value=document.getElementById("codGrupoConvenioEmpresaBuscar").value;
		document.getElementById("razonOculto").value=document.getElementById("razonSocialEmpresaBuscar").value;
		return true;
	}

	function retornaEnlace(paginacion)
	{
		formu = document.getElementById("formulario");
		
		rut_a = document.getElementById("rutOculto");
		  rut_n = document.getElementById("rutEmpresaBuscar");
		  if(rut_a!=rut_n){
	 			rut_n.value=rut_a.value;
	 	  }
		  nom_a = document.getElementById("nombreOculto");
		  nom_n = document.getElementById("nombreGrupoConvBuscar");
		  if(nom_a!=nom_n){
	 			nom_n.value=nom_a.value;
	 	  }
		  ap_a = document.getElementById("idGrupoOculto");
		  ap_n = document.getElementById("codGrupoConvenioEmpresaBuscar");
		  if(ap_a!=ap_n){
	 			ap_n.value=ap_a.value;
	 	  }
	 	  ng_a = document.getElementById("razonOculto");
		  ng_n = document.getElementById("razonSocialEmpresaBuscar");
		  if(ng_a!=ng_n){
	 			ng_n.value=ng_a.value;
	 	  }

		formu.action = "ListarEmpresas.do?operacion=Buscar&paginaNumero=" + paginacion;
		formu.submit();
	}

function limpiaEmp()
{
	document.getElementById("rutEmpresaBuscar").value = "";
	document.getElementById("razonSocialEmpresaBuscar").value = "";
	//alert("limpiaEmp:" + document.getElementById("rutEmpresaBuscar").value + "::" + document.getElementById("razonSocialEmpresaBuscar").value + "::");
}

function limpiaGrupo()
{
	document.getElementById("nombreGrupoConvBuscar").value = "";
	document.getElementById("codGrupoConvenioEmpresaBuscar").value = "";
	//alert("limpiaGrupo:" + document.getElementById("nombreGrupoConvBuscar").value + "::" + document.getElementById("codGrupoConvenioEmpresaBuscar").value + "::");
}
function abrirDocImpresion1()
{
   var rut=document.getElementById("rutOculto").value;
   var rSocial=document.getElementById("razonOculto").value;
   var idGrupo=document.getElementById("idGrupoOculto").value;
   var nombre=document.getElementById("nombreOculto").value;
  
		
   var URL="/AdminCotPrevWEB/ListarEmpresas.do?rutOculto="+rut+"&razonOculto="+rSocial+"&idGrupoOculto="+idGrupo+"&nombreOculto="+nombre+"&imprimir=";
   abrirDocImpresion(URL);
}
// -->
</script>
</body>
</html:html>
