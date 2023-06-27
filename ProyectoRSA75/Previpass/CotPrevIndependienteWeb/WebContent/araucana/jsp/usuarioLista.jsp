<%@ include file="/html/comun/taglibs.jsp" %>
<script type="text/javascript" src="<c:url value="/js/validaciones/validacionesComun.js" />"></script>
<html:form action="/ListarUsuarios" styleId="formulario" onsubmit="return onFormSubmit(this);">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="usuarios" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="usuarioLista" />
<html:hidden property="operacion" styleId="operacion" value="" />
<html:hidden property="idUsuarioBorrar" styleId="idUsuarioBorrar" value="" />
<html:hidden property="paginaNumero" styleId="paginaNumero" value="1" />
<c:set var="FLG_Busqueda" scope="page">
	<%=request.getAttribute("FLG_Busqueda")%>
</c:set>
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr valign="bottom"> 
    	<td width="38%" height="30" bgcolor="#FFFFFF" class="titulo">
			<strong>B&uacute;squeda de Encargado</strong>
		</td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
				<tr>
					<td><strong>RUT:</strong></td>
					<td><html:text property="rut" styleId="rut" maxlength="12" size="25" styleClass="campos" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/></td>
					<td><strong>Nombre:</strong></td>
					<td><html:text property="nombre" styleId="nombre" maxlength="30" size="45" styleClass="campos" onblur="javascript:soloNomTrab(this);" onkeyup="javascript:soloNomTrab(this);"/></td>
				</tr>
				<tr>
					<td><strong>Apellidos:</strong></td>
					<td colspan="3"><html:text property="apellidos" styleId="apellidos" maxlength="40" size="60" styleClass="campos" onblur="javascript:soloNomTrab(this);" onkeyup="javascript:soloNomTrab(this);"/></td>
				</tr>
				<tr>
					<td colspan="3">&nbsp;</td>
					<td align="right">
						<html:submit property="operacion" styleClass="btn3" value="${buscar}"/>
					</td>
				</tr>
				<tr>
					<td height="4" colspan="5" bgcolor="#85b4be"></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<html:errors />
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
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
        	<tr valign="bottom"> 
            	<td width="38%" height="30" bgcolor="#FFFFFF" class="titulo"><strong>Lista de Encargados</strong></td>
                <td width="31%" align="right" bgcolor="#FFFFFF">
                	<c:set var="url"><c:url value='/ListarUsuarios.do'/>?imprimir=&accion=admin&subAccion=imprimir</c:set>
				    <html:button property="operacion" styleClass="btn3" value="${imprimir}"       onclick="javascript:abrirDocImpresion('${url}');"/>&nbsp;&nbsp;&nbsp;
					<html:button property="operacion" styleClass="btn4" value="${crearEncargado}" onclick="javascript:creaUsuario(this);" />
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
                		<td width="21%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;RUT</td>
		               	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
		               	<td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Apellidos</td>
		               	<td colspan="3" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
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
		                   		<td height="20" align="left" valign="middle" nowrap="nowrap" class="${estilo}"><nested:write property="idUsuarioFmt"/></td>
		                   		<td width="28%" nowrap="nowrap" class="${estilo}"><nested:write property="nombre"/></td>
		                   		<td width="30%" nowrap="nowrap" class="${estilo}"><nested:write property="apellido"/></td>
		                   		<td width="8%" align="center" nowrap="nowrap" class="${estilo}">
		                   			<div align="center">
			                   			<html:link action="/DetalleUsuarios.do?accion=admin&subAccion=usuarios&subSubAccion=usuarioFicha&idUsuario=${filaConsulta.idUsuario}" styleClass="links">
		                   					<img src="<c:url value='/img/lupa.gif' />" width="14" height="14" border="0" alt="Ver detalle" title="Ver detalle"/>
		                   				</html:link>
		                   			</div>
		                   		</td>
		                   		<td width="8%" align="center" nowrap="nowrap" class="${estilo}">
		                   			<div align="center">
		                   				<html:link action="/DetalleUsuarios.do?accion=admin&subAccion=usuarios&subSubAccion=usuarioEditar&idUsuario=${filaConsulta.idUsuario}">
		                   					<img src="<c:url value='/img/ico_hojap.gif' />" width="14" height="13" border="0" alt="Editar" title="Editar"/>
		                   				</html:link>
		                   			</div>
		                   		</td>
		                   		<td width="5%" align="center" nowrap="nowrap" class="${estilo}">
		                   			<div align="center">
		                   				<a href="javascript:borraUsuario('${filaConsulta.idUsuario}', '${filaConsulta.idUsuarioFmt}');">
		                   					<img src="<c:url value="/img/icono_basurero.gif" />" width="16" height="16" border="0" alt="Borrar" title="Borrar"/>
		                   				</a>
		                   			</div>
		                   		</td>
		               		</tr>
	             		</nested:iterate>
             		</nested:notEmpty>
             		<nested:empty property="consulta">
             			<tr>
             				<td class="textos_formularios" height="20" colspan="6">
             					<c:choose>
             						<c:when test="${FLG_Busqueda == '0'}">No existen encargados administrados por el encargado autenticado.</c:when>
									<c:otherwise>No existen resultados para el criterio de b&uacute;squeda ingresado.</c:otherwise>
             					</c:choose>
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
  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
  		<bean:size id="nPags" name="ListarUsuariosActionForm" property="numeroFilas"/>
		<c:if test="${nPags > 1}">
			<tr>
				<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" valign="middle" class="numeracion">
							<logic:iterate id="paginacion" name="ListarUsuariosActionForm" property="numeroFilas">
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
                	
</table>
</html:form>
<script language="javaScript">

	function validaFormulario(f) 
	{
		//Valida caracteres válidos en caja de búsqueda
		var sMsje = "";
		if (validaReq(document.getElementById("rut")) &&
			!validaRut(document.getElementById("rut").value))
			sMsje += "* Formato de rut inválido para el trabajador a buscar\n";
		if (validaReq(document.getElementById("nombre")) && !validaChrs(document.getElementById("nombre").value))
			sMsje += "* Caracteres inválidos en el nombre del trabajador a buscar\n";
		else if (document.getElementById("nombre").value.length > 0 && document.getElementById("nombre").value.length < 3)
			sMsje += "* Debe ingresar al menos 3 caracteres en el nombre del trabajador a buscar\n";
		if (validaReq(document.getElementById("apellidos")) &&
			!validaChrs(document.getElementById("apellidos").value))
			sMsje += "* Caracteres inválidos en los apellidos del trabajador a buscar\n";
		else if (document.getElementById("apellidos").value.length > 0 && document.getElementById("apellidos").value.length < 3)
			sMsje += "* Debe ingresar al menos 3 caracteres en los apellidos del trabajador a buscar\n";

		if (sMsje == "") {
			if (validaReq(document.getElementById("rut")) &&
				!validaDV(document.getElementById("rut").value))
				sMsje += "* Dígito verificador inválido para el rut del trabajador a buscar\n";
		}

		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}

		return true;
	}


	function borraUsuario(idUsuario, idUsuarioFmt) {
		
		if (!confirm("Está seguro que desea borrar al encargado: " + idUsuarioFmt))
			return;
			
		document.getElementById("operacion").value = "borrar";
		document.getElementById("idUsuarioBorrar").value = idUsuario;
		document.getElementById("formulario").submit();
	}
	
	function creaUsuario(obj) {
		
		document.getElementById("operacion").value = obj.value;
		document.getElementById("formulario").submit();
	}
	
	function retornaEnlace(paginacion)
	{
		document.getElementById("paginaNumero").value = paginacion;
		document.getElementById("formulario").submit();
	}
</script>