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
<body  onLoad="poneFoco('rut')">
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/?limpiaPath='/>");
//-->
</script>
<html:form action="/ListarAdministrador" styleId="formulario" onsubmit="return onFormSubmit(this);">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="usuarios" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="usuarioLista" />
<html:hidden property="operacion" styleId="operacion" value="" />
<html:hidden property="idUsuarioBorrar" styleId="idUsuarioBorrar" value="" />
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
	        <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; color: #333333; background-color: #e1ebed;">
	         	<tr><td colspan="4"><strong>Administrador</strong></td></tr>
				<tr>
	           		<td><strong>RUT:</strong></td>
	           		<td><html:text property="rut" styleId="rut" maxlength="12" size="17" styleClass="campos" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/></td>
		         	<td><strong>&nbsp;&nbsp;Nombre:</strong></td>
	           		<td><html:text property="nombre" styleId="nombre" maxlength="30" size="45" styleClass="campos" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/></td>
	         	</tr>
	         	<tr> 
	         		<td colspan="2" />
	           		<td><strong>&nbsp;&nbsp;Apellidos:</strong></td>
	           		<td><html:text property="apellidos" styleId="apellidos" maxlength="30" size="45" styleClass="campos" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/></td>
	         	</tr>
	         	<tr><td colspan="4"><hr><strong>Grupo Convenio</strong></td></tr>
	         	<tr> 
	         		<td><strong>Codigo:</strong></td>
	           		<td><html:text property="codigoGrupoConvenio" styleId="codigoGrupoConvenio" maxlength="9" size="17" styleClass="campos" onblur="javascript:soloNumero(this, '');limpiaEmpresa();" onkeyup="javascript:soloNumero(this, '');limpiaEmpresa();"/></td>
	           		<td><strong>&nbsp;&nbsp;Nombre:</strong></td>
	           		<td><html:text property="nombreGrupoConvenio" styleId="nombreGrupoConvenio" maxlength="30" size="45" styleClass="campos" onblur="javascript:soloRazonSocial(this);limpiaEmpresa();" onkeyup="javascript:soloRazonSocial(this);limpiaEmpresa();"/></td>
	         	</tr>
	         	<tr><td colspan="4"><hr><strong>Empresa</strong></td></tr>
	         	<tr> 
	           		<td><strong>Rut:</strong></td>
	           		<td><html:text property="rutEmpresa" styleId="rutEmpresa" maxlength="13" size="17" styleClass="campos" onblur="javascript:soloRut(this);limpiaGrupo()" onkeyup="javascript:soloRut(this);limpiaGrupo()"/></td>
	           		<td><strong>&nbsp;&nbsp;Razon Social:</strong></td>
	           		<td><html:text property="razonSocial" styleId="razonSocial" maxlength="30" size="45" styleClass="campos" onblur="javascript:soloRazonSocial(this);limpiaGrupo()" onkeyup="javascript:soloRazonSocial(this);limpiaGrupo()"/></td>
	         	</tr>
	         	<tr> 
	           		<td colspan="3">&nbsp;</td>
	           		<td align="right" height="30" valign="middle">
	           			<html:submit property="operacion" styleClass="btn3" value="Buscar" />&nbsp;&nbsp;&nbsp;
	           		</td>
	         	</tr>
	         	<tr> 
	           		<td height="4" colspan="5" bgcolor="#85b4be"></td>
	         	</tr>
			</table>
		</td>
	</tr>
    <logic:notEqual name="ListarAdministradorActionForm" property="mostrarLista" value="SI">
		<tr>
			<td align="right" height="30" valign="bottom">
				<html:button property="operacion" styleClass="btn2" value="Crear Administrador" onclick="javascript:creaAdministrador();" />&nbsp;&nbsp;&nbsp;
	        </td>
	    </tr>
    </logic:notEqual>
	<logic:equal name="ListarAdministradorActionForm" property="mostrarLista" value="SI">
		<tr>
			<td height="40" valign="middle">
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
		        	<tr valign="bottom"> 
		            	<td bgcolor="#FFFFFF" class="titulo"><strong>Lista de Administradores</strong></td>
		                <td align="right" bgcolor="#FFFFFF" height="30">
		                	<html:button property="operacion" styleClass="btn2" value="Crear Administrador" onclick="javascript:creaAdministrador();" />&nbsp;&nbsp;&nbsp;
							<html:button property="operacion" styleClass="btn3" value="Imprimir" onclick="javascript:abrirDocImpresion1();"/>
				        </td>
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
             		<tr class="subtitulos_tablas">
                		<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><%@ include file="/html/comun/flecha.jspf"%> RUT</td>
		               	<td width="35%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
		               	<td width="35%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Apellido</td>
		               	<td width="15%" colspan="5" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
               		</tr>
               		<nested:notEmpty property="consulta">
						<nested:iterate id="filaConsulta" property="consulta" indexId="nFila">
							<c:choose>
					   		    <c:when test="${nFila % 2 == 0}">
					   		    	<c:set var="estilo" value="textos_formularios"/>
					   		    </c:when>
		   						<c:otherwise>
		   							<c:set var="estilo" value="textos-formcolor2"/>
		   						</c:otherwise>
							</c:choose>
		               		<tr>
		                   		<td height="20" align="right" valign="middle" nowrap="nowrap" class="${estilo}">
									<div align="right"><nested:write property="rutFormat"/></div>
		                   		</td>
		                   		<td class="${estilo}"><nested:write property="nombre"/></td>
		                   		<td class="${estilo}"><nested:write property="apellidos"/></td>
		                   		<td align="center" nowrap="nowrap" class="${estilo}"><div align="center"><html:link action="/DetalleAdministrador.do?accion=admin&subAccion=usuarios&subSubAccion=usuarioFicha&idUsuario=${filaConsulta.rut}" title="Ver detalle" styleClass="links"><img src="<c:url value='/img/iconos/lupa.gif' />" width="14" height="13" border="0"/></html:link></div></td>
		                   		<td align="center" nowrap="nowrap" class="${estilo}"><div align="center"><html:link action="/DetalleAdministrador.do?accion=admin&subAccion=usuarios&subSubAccion=usuarioEditar&idUsuario=${filaConsulta.rut}"><img src="<c:url value='/img/iconos/ico_hojap.gif' />" width="14" height="13" border="0" alt="Editar" title="Editar"/></html:link></div></td>
		                   		<td align="center" nowrap="nowrap" class="${estilo}"><div align="center"><a href="/AdminCotPrevWEB/TransferirPermisos.do?rutInicio=${filaConsulta.rutFormat}&volver=1"><img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="14" height="13" border="0" alt="Transferir Permisos" title="Transferir Permisos" /></a></div></td>
		                   		<td align="center" nowrap="nowrap" class="${estilo}"><div align="center"><html:link action="/AsigPermisosAdmin.do?accion=admin&subAccion=usuarios&subSubAccion=usuarioEditar&operacion=Siguiente&idUsuario=${filaConsulta.rut}"><img src="<c:url value='/img/iconos/ico_llave.gif' />" width="14" height="13" border="0" alt="Editar" title="Asignar Permisos"/></html:link></div></td>
		                   		<td align="center" nowrap="nowrap" class="${estilo}"><div align="center"><html:link action="/DetalleAdministrador.do?accion=admin&subAccion=usuarios&subSubAccion=usuarioEditar&operacion=Password&idUsuario=${filaConsulta.rut}"><img src="<c:url value='/img/iconos/ico_candado.gif' />" width="14" height="13" border="0" alt="Editar" title="Cambiar Password"/></html:link></div></td>
		               		</tr>
	             		</nested:iterate>
             		</nested:notEmpty>
             		<nested:empty property="consulta">
             			<tr>
             				<td class="textos_formularios" height="20" colspan="8">
             					No existen usuarios que cumplan con los criterios especificados
             				</td>
             			</tr>
             		</nested:empty>
           		</table>
       			</td>
   			</tr>
   			<!-- Aqui -->
        </table>
		</td>
  	</tr>
  	<tr>
  		<td>
	  	<!-- Insertando Paginacion -->
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<bean:size id="nPags" name="ListarAdministradorActionForm" property="numeroFilas"/>
			<c:if test="${nPags > 1}">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr> 
							<td align="center" valign="middle" class="numeracion">
								<logic:iterate id="paginacion" name="ListarAdministradorActionForm" property="numeroFilas">
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
<html:hidden property="rutOculto" styleId="rutOculto"/>
<html:hidden property="nombreOculto" styleId="nombreOculto"/>
<html:hidden property="apellidosOculto" styleId="apellidosOculto"/>
<html:hidden property="nombreGrupoOculto" styleId="nombreGrupoOculto"/>
<html:hidden property="codigoGrupoOculto" styleId="codigoGrupoOculto"/>
<html:hidden property="rutEmpresaOculto" styleId="rutEmpresaOculto"/>
<html:hidden property="razonSocialOculto" styleId="razonSocialOculto"/>
</html:form>
<script language="javaScript">
<!--
	function limpiaEmpresa() 
	{
		document.getElementById("rutEmpresa").value = "";
		document.getElementById("razonSocial").value = "";
	}

	function limpiaGrupo() 
	{
		document.getElementById("codigoGrupoConvenio").value = "";
		document.getElementById("nombreGrupoConvenio").value = "";
	}
	
	function borraUsuario(idUsuario, idUsuarioFmt) 
	{		
		if (!confirm("Está seguro que desea borrar todos los permisos de administrador de empresas del usuario: " + idUsuarioFmt))
			return;
			
		document.getElementById("operacion").value = "borrar";
		document.getElementById("idUsuarioBorrar").value = idUsuario;
		document.getElementById("formulario").submit();
	}
	
	function creaAdministrador() 
	{
		document.getElementById("formulario").action="/AdminCotPrevWEB/DetalleAdministrador.do";
		document.getElementById("subSubAccion").value = "usuarioCrear";
		document.getElementById("formulario").submit();
	}

	function validaFormulario(f) 
	{
		//Valida caracteres validos en caja de busqueda
		var sMsje = "";
		
		var posGuion = document.getElementById("rut").value.indexOf('-');
		var rutTMP = limpiaRutIncremental(document.getElementById("rut").value);
		if (document.getElementById("rut").value.length > 0 && rutTMP.length < 4)
			sMsje += "* Debe ingresar al menos 4 dígitos de rut para el usuario a buscar\n";
		//else if(document.getElementById("rut").value.length > 0 && posGuion > 0 && !validaDV(rutTMP))
		else if(document.getElementById("rut").value.length > 0 && posGuion > 0 && !validaDV(document.getElementById("rut").value))
			sMsje += ' * Dígito verificador de rut para el usuario a buscar incorrecto';
		if (validaReq(document.getElementById("nombre")) && !validaNombres(document.getElementById("nombre").value))
			sMsje += "* Caracteres inválidos en el nombre del usuario a buscar\n";
		if (validaReq(document.getElementById("apellidos")) && !validaNombres(document.getElementById("apellidos").value))
			sMsje += "* Caracteres inválidos en el apellidos del usuario a buscar\n";
		posGuion = document.getElementById("rutEmpresa").value.indexOf('-');
		rutTMP = limpiaRutIncremental(document.getElementById("rutEmpresa").value);
		if (document.getElementById("rutEmpresa").value.length > 0 && rutTMP.length < 4)
			sMsje += "* Debe ingresar al menos 4 dígitos de rut para la empresa a buscar\n";
		//else if(document.getElementById("rutEmpresa").value.length > 0 && posGuion > 0 && !validaDV(rutTMP))
		else if(document.getElementById("rutEmpresa").value.length > 0 && posGuion > 0 && !validaDV(document.getElementById("rutEmpresa").value))
			sMsje += ' * Dígito verificador de Rut de empresa a buscar incorrecto';

		if (validaReq(document.getElementById("nombreGrupoConvenio")) && !validaRazonSocial(document.getElementById("nombreGrupoConvenio").value))
			sMsje += "* Caracteres inválidos en el nombre del Grupo de Convenios a buscar\n";
		else if (document.getElementById("nombreGrupoConvenio").value.length > 0 && document.getElementById("nombreGrupoConvenio").value.length < 3)
			sMsje += "* Debe ingresar al menos 3 caracteres en el nombre del Grupo de Convenios a buscar\n";
		if (validaReq(document.getElementById("razonSocial")) && !validaRazonSocial(document.getElementById("razonSocial").value))
			sMsje += "* Caracteres inválidos en el nombre de la Empresa a buscar\n";
		else if (document.getElementById("razonSocial").value.length > 0 && document.getElementById("razonSocial").value.length < 3)
			sMsje += "* Debe ingresar al menos 3 caracteres en el nombre de la Empresa a buscar\n";

		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		document.getElementById("rutOculto").value=document.getElementById("rut").value;
 		document.getElementById("nombreOculto").value=document.getElementById("nombre").value;
 		document.getElementById("apellidosOculto").value=document.getElementById("apellidos").value;
 		document.getElementById("nombreGrupoOculto").value=document.getElementById("nombreGrupoConvenio").value;
 		document.getElementById("codigoGrupoOculto").value=document.getElementById("codigoGrupoConvenio").value;
 		document.getElementById("rutEmpresaOculto").value=document.getElementById("rutEmpresa").value;
 		document.getElementById("razonSocialOculto").value=document.getElementById("razonSocial").value;
		return true;
	}

	function retornaEnlace(paginacion)
	{
		formu = document.getElementById("formulario");
		
		rut_a = document.getElementById("rutOculto");
		rut_n = document.getElementById("rut");
		if(rut_a!=rut_n)
			rut_n.value=rut_a.value;

		nom_a = document.getElementById("nombreOculto");
		nom_n = document.getElementById("nombre");
		if(nom_a!=nom_n)
			nom_n.value=nom_a.value;
		
		ap_a = document.getElementById("apellidosOculto");
		ap_n = document.getElementById("apellidos");
		if(ap_a!=ap_n)
			ap_n.value=ap_a.value;
		
		 ng_a = document.getElementById("nombreGrupoOculto");
		ng_n = document.getElementById("nombreGrupoConvenio");
		if(ng_a!=ng_n)
			ng_n.value=ng_a.value;
		
		 cg_a = document.getElementById("codigoGrupoOculto");
		cg_n = document.getElementById("codigoGrupoConvenio");
		if(cg_a!=cg_n)
			cg_n.value=cg_a.value;
		
		 re_a = document.getElementById("rutEmpresaOculto");
		re_n = document.getElementById("rutEmpresa");
		if(re_a!=re_n)
			re_n.value=re_a.value;
		
		rs_a = document.getElementById("razonSocialOculto");
		rs_n = document.getElementById("razonSocial");
		if(rs_a!=rs_n)
			rs_n.value=rs_a.value;

		formu.action = "ListarAdministrador.do?paginaNumero=" + paginacion;
		formu.submit();
	}

function abrirDocImpresion1()
{
	var rut = document.getElementById("rutOculto").value;
   	var nombre = document.getElementById("nombreOculto").value;
   	var apellidos = document.getElementById("apellidosOculto").value;
   	var nombreGrupo = document.getElementById("nombreGrupoOculto").value;
	var razonSocial = document.getElementById("razonSocialOculto").value;
   	var codigoGrupo = document.getElementById("codigoGrupoOculto").value;	
    var rutEmpresa = document.getElementById("rutEmpresaOculto").value;

   	var URL = "/AdminCotPrevWEB/ListarAdministrador.do?rut="+rut+"&nombre="+nombre+"&apellidos="+apellidos
   	URL += "&nombreGrupoConvenio="+nombreGrupo+"&razonSocial="+razonSocial+"&codigoGrupoConvenio="+codigoGrupo+"&rutEmpresa="+rutEmpresa
   	URL += "&imprimir=";
   	abrirDocImpresion(URL);
}
// -->
</script>
</body>
</html:html>