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
<body onLoad="poneFoco('rutEmpresa');">
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/AsigPermisosAdmin" styleId="formulario">
<input type="hidden" name="accion" id="accion" value="admin"/>
<input type="hidden" name="subAccion" id="subAccion" value="usuarios"/>
<input type="hidden" name="subSubAccion" id="subSubAccion" value="asigPermAdmin"/>
<input type="hidden" name="idAdmin" id="idAdmin" value="${AsigPermAdminActionForm.idAdmin}"/>
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
    		<table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos">
		       	<tr> 
		          	<td width="30%"><strong>RUT Administrador:</strong></td>
		          	<td width="30%">${AsigPermAdminActionForm.idAdminFmt}</td>
		            <td><strong>Nombre:</strong></td>
		          	<td>${AsigPermAdminActionForm.nombre}</td>
		       	</tr>
		      	<tr> 
		         	<td><strong>Apellidos:</strong></td>
		         	<td colspan="3">${AsigPermAdminActionForm.apellidos}</td>
		      	</tr>
		      	<tr> 
		        	<td height="4" colspan="4" bgcolor="#85b4be"></td>
		     	</tr>
		    </table>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr align="center"> 
                <td height="41" valign="top">
           		<table width="100%" border="0" cellpadding="0" cellspacing="1">
               		<tr valign="bottom"> 
                 		<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Permisos</strong></td>
               		</tr>
             	</table>
                <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos2">
	                    <tr> 
	                      	<td width="100%" class="titulos_formularios"><strong><a name="ancla" class="titulos_formularios">Permisos de administrador sobre empresas</a></strong></td>
	                    </tr>
	                </table>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
						<tr>
							<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"> <%@ include file="/html/comun/flecha.jspf"%> RUT</td>
							<td width="70%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
							<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Administrador</td>
						</tr>
						<nested:notEmpty property="permisos">
							<nested:iterate id="filaEmpresa" property="permisos" indexId="nFila">
								<c:choose>
						   		    <c:when test="${nFila % 2 == 0}">
						   		    	<c:set var="estilo" value="textos_formularios"/>
						   		    </c:when>
			   						<c:otherwise>
			   							<c:set var="estilo" value="textos-formcolor2"/>
			   						</c:otherwise>
								</c:choose>
								<tr>
									<td class="${estilo}">
										<nested:hidden property="idEmpresa"/>
										<nested:hidden property="idEmpresaFmt"/>
										<nested:write property="idEmpresaFmt"/>
									</td>
									<td class="${estilo}">
										<nested:hidden property="razonSocial"/>
										<nested:write property="razonSocial"/>
									</td>
									<td class="${estilo}">
										<div align="center">
											<nested:checkbox property="esAdmin" disabled="true"/>
										</div>
									</td>
								</tr>
							</nested:iterate>
						</nested:notEmpty>
						<nested:empty property="permisos">
							<tr>
								<td height="20" class="textos_formularios" colspan="5">
									El usuario no es administrador de ninguna empresa
								</td>
							</tr>
						</nested:empty>
						<tr>
							<td>&nbsp;
								
							</td>
						</tr>
						<tr>
							<td colspan="5" width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">
								Empresas en que las que el usuario no es administrador
							</td>
		                </tr>
					  	<tr> 
					    	<td align="left" valign="top" colspan="5">
						        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
									<tr> 
						           		<td><strong>RUT Empresa:</strong></td>
						           		<td><html:text property="rutEmpresa" styleId="rutEmpresa" maxlength="13" size="13" styleClass="campos" onblur="javascript:soloRut(this);javascript:limpiaGrupo();" onkeyup="javascript:soloRut(this);javascript:limpiaGrupo();"/></td>
						           		<td><strong>Nombre:</strong></td>
						           		<td><html:text property="razonSocial" styleId="razonSocial" maxlength="50" size="30" styleClass="campos" onblur="javascript:soloAlfanumerico(this, '');javascript:limpiaGrupo();" onkeyup="javascript:soloAlfanumerico(this, '');javascript:limpiaGrupo();"/></td>
						         	</tr>
						         	<tr> 
						           		<td><strong>Codigo Grupo Convenio:</strong></td>
						           		<td><html:text property="idGrupo" styleId="idGrupo" maxlength="13" size="13" styleClass="campos" onblur="javascript:soloNumero(this, '');javascript:limpiaEmpresa();" onkeyup="javascript:soloNumero(this, '');javascript:limpiaEmpresa();"/></td>
						           		<td><strong>Nombre grupo Convenio:</strong></td>
						           		<td><html:text property="nombreGrupo" styleId="nombreGrupo" maxlength="50" size="30" styleClass="campos" onblur="javascript:soloAlfanumerico(this, '');javascript:limpiaEmpresa();" onkeyup="javascript:soloAlfanumerico(this, '');javascript:limpiaEmpresa();"/></td>
					         		</tr>
						         	<tr> 
						           		<td colspan="3">&nbsp;</td>
						           		<td align="right">
						           			<html:submit property="operacion" styleClass="btn3" value=" Buscar " onclick="javascript:bBuscar=true;" />
						           		</td>
						         	</tr>
								</table>
							</td>
						</tr>
	
						<nested:notEmpty property="permisosPorAsignar">
							<nested:iterate id="filaEmpresa" property="permisosPorAsignar" indexId="nFila">
								<c:choose>
						   		    <c:when test="${nFila % 2 == 0}">
						   		    	<c:set var="estilo" value="textos_formularios"/>
						   		    </c:when>
			   						<c:otherwise>
			   							<c:set var="estilo" value="textos-formcolor2"/>
			   						</c:otherwise>
								</c:choose>
								<tr>
									<td class="${estilo}">
										<nested:hidden property="idEmpresa"/>
										<nested:hidden property="idEmpresaFmt"/>
										<nested:write property="idEmpresaFmt"/>
									</td>
									<td class="${estilo}">
										<nested:hidden property="razonSocial"/>
										<nested:write property="razonSocial"/>
									</td>
									<td class="${estilo}">
										<div align="center">
											<nested:checkbox property="esAdmin"/>
										</div>
									</td>
								</tr>
							</nested:iterate>
						</nested:notEmpty>
						<nested:empty property="permisosPorAsignar">
							<tr>
								<td height="20" class="textos_formularios" colspan="5">
									No hay empresas bajo los criterios espec&iacute;ficados en los que el usuario no sea administrador.
								</td>
							</tr>
						</nested:empty>
	              </table>
		       <tr align="center">
             <td height="41" valign="top"><br />
             	<html:submit property="operacion" styleClass="btn3" value="Guardar" onclick="javascript:bBuscar=false;"/>
             	<html:cancel property="operacion" styleClass="btn3" value="Cancelar"/>
             </td>
           </tr>
      </table>
    </td>
  </tr>
  <tr> 
    <td>&nbsp;</td>
  </tr>
</table>
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
		document.getElementById("idGrupo").value = "";
		document.getElementById("nombreGrupo").value = "";
	}
	
	function validaCajasBusqueda(f) 
	{
		var sMsje = "";
		if(!validaReq(document.getElementById("idGrupo")) &&  !validaReq(document.getElementById("nombreGrupo")) && !validaReq(document.getElementById("rutEmpresa")) && !validaReq(document.getElementById("razonSocial")))
		{
			sMsje = "* Debe ingresar datos de Empresa o Grupo de Convenio para realizar la busqueda";
		}
		if (validaReq(document.getElementById("rutEmpresa")) || validaReq(document.getElementById("razonSocial")))
		{
			if(validaReq(document.getElementById("rutEmpresa")))
			{
				if(!validaRut(document.getElementById("rutEmpresa").value))
					sMsje = "* El formato del rut de la empresa es incorrecto";
				else
				{
					if(!validaDV(document.getElementById("rutEmpresa").value))
						sMsje = "* Digito verificador del rut de la empresa incorrecto";
				}
			}else
			{
				if(!validaReq(document.getElementById("razonSocial")))
					sMsje = "* Caracteres invalidos para el nombre de la empresa";
			}
		}
				
		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		var inputs = document.getElementsByTagName("input");
	    for (var i = 0; i < inputs.length; i++) 
	    {
	    	if (inputs[i].type == "checkbox" && inputs[i].checked == true)
				return true;
		}
		alert("Para este usuario, debe asignarle al menos una Empresa a Administrar.\n\n");
		return false;
	}
// End --> 
</script>
</body>
</html:html>
