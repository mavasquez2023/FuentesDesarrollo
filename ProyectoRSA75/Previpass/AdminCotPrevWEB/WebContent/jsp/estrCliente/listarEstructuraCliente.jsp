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
	<TITLE>Entidades</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
	<script src="<c:url value='/js/validaciones.js'/>"></script>
	<script src="<c:url value='/js/comun.js'/>"></script>
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
</head>

<body onLoad="poneFoco('empresa')">
<html:form action="/ListarEstrCliente" styleId="formulario">
	<input type="hidden" id="accionInterna" name="accionInterna" />
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
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom"> 
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Estructura de Clientes</strong></td>
				</tr>
			</table>
		</td>
	</tr>
  	<tr> 
    	<td align="left" valign="top">
	        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
				<tr> 
	           		<td><strong>RUT Empresa:</strong></td>
           			<td>
           				<html:text property="busquedaEmpresa" maxlength="13" size="18" styleId="empresa" styleClass="campos" onblur="javascript:soloRut(this);borraGrupo();" onkeyup="javascript:soloRut(this);borraGrupo();"/>
           			</td>
	           		<td><strong>C&oacute;d. Grupo Convenio:</strong></td>
	           		<td>
	           			<html:text property="busquedaGrupo" maxlength="9" size="12" styleId="grupo" styleClass="campos" onblur="javascript:soloNumero(this);borraEmpresa();" onkeyup="javascript:soloNumero(this);borraEmpresa();"/>
	           		</td>
	         	</tr>
	        	<tr> 
	           		<td colspan="3">&nbsp;</td>
	           		<td align="right">
	           			<html:button property="operacion" styleClass="btn3" value="Buscar" onclick="javascript:buscar();"/>
	           		</td>
	         	</tr>
	         	<tr> 
	           		<td height="4" colspan="5" bgcolor="#85b4be"></td>
	         	</tr>
			</table>
		</td>
	</tr>
 	<tr> 
   		<td>&nbsp;</td>
 	</tr>
 	<nested:empty property="resultadoGrupo">	
    <tr align="center"> 
   		<td valign="top" bgcolor="#CCCCCC">
   		<table width="100%" border="0" cellpadding="0" cellspacing="1">
   			<tr> 
     			<td align="center" bgcolor="#FFFFFF">
     			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
             		<tr class="subtitulos_tablas">
                		<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><%@ include file="/html/comun/flecha.jspf"%> Cargo</td>
		               	<td width="50%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre de Cliente</td>
		               	<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">RUT Cliente</td>
		               	<td width="35%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nivel de Acceso</td>
		         	</tr>
	         		<nested:notEmpty property="filas">
					<nested:iterate id="filaConsulta" property="filas" indexId="nFila">
						<c:choose>
						    <c:when test="${nFila % 2 == 0}">
					   		   	<c:set var="estilo" value="textos_formularios"/>
					   	    </c:when>
		   					<c:otherwise>
		   						<c:set var="estilo" value="textos-formcolor2"/>
		   					</c:otherwise>
						</c:choose>
		           		<tr>
			           		 <nested:notEqual value="nueva" property="tipo">
			           		 	<nested:notEqual value="no" property="tipo">
				               		<td height="20"  width="15%"align="left" valign="middle" nowrap="nowrap" class="${estilo}">
										&nbsp;<nested:write property="cargo"/>
			                   		</td>
			                   		<td height="20" width="50%" align="left" valign="middle" nowrap="nowrap" class="${estilo}">
		                   				&nbsp;<nested:write property="nombre"/>
			                   		</td>
			                   		<td height="20" width="20%" align="left" valign="middle" nowrap="nowrap" class="${estilo}">
		                   				<div align="right">&nbsp;<nested:write property="rut"/></div>
			                   		</td>
			                   		<td height="20" width="35%" align="left" valign="middle" nowrap="nowrap" class="${estilo}">
		                   				&nbsp;<nested:write property="acceso"/>
			                   		</td>
		                   		</nested:notEqual>
		                   		<nested:equal value="no" property="tipo">
				               		<td height="20"  width="15%"align="left" valign="middle" nowrap="nowrap" class="${estilo}">
										&nbsp;<nested:write property="cargo"/>
			                   		</td>
			                   		<td height="20" colspan="3" align="left" valign="middle" nowrap="nowrap" class="${estilo}">
		                   				&nbsp;<nested:write property="nombre"/>
			                   		</td>
		                   		</nested:equal>
		                   	</nested:notEqual>
	                   		<nested:equal value="nueva" property="tipo">
		                   		<td height="20" width="100%" colspan="4" align="left" valign="middle" nowrap="nowrap" class="barra_tablas">
	                   				&nbsp;<nested:write property="nombre"/>
		                   		</td>
	                   		</nested:equal>
	               		</tr>
	             	</nested:iterate>
				  	</nested:notEmpty>
	         		<nested:empty property="filas">
	         			<tr><td colspan="4" class="textos_formularios">No Existen datos </td></tr>
	         		</nested:empty>
				</table>
			  </td>	
			</tr>
	   </table>
	  </td>
	 </tr>
	</nested:empty>
	</table>
</html:form>
<br /><br />
<script language="javaScript">
<!--
	function buscar()
	{
		form=document.getElementById('formulario');
		if(form.empresa.value != '')
			form.accionInterna.value = 'EMPRESA';
		if(form.grupo.value != '') 
			form.accionInterna.value = 'GRUPO';

		if (validate(form)) 
			form.submit();
	}
	
	function validate(form) 
	{
		mensaje='';
		if(form.empresa.value == '' && form.grupo.value == '')
			mensaje += '* Debe ingresar un parámetro de búsqueda\n';

		var posGuion = document.getElementById("empresa").value.indexOf('-');
		var rutTMP = limpiaRutIncremental(document.getElementById("empresa").value);
		if (document.getElementById("empresa").value.length > 0 && rutTMP.length < 4)
			mensaje += " * Debe ingresar al menos 4 dígitos de rut para el usuario a buscar\n"; 
		//else if(document.getElementById("empresa").value.length > 0 && posGuion > 0 && !validaDV(rutTMP))
		else if(document.getElementById("empresa").value.length > 0 && posGuion > 0 && !validaDV(document.getElementById("empresa").value))
			mensaje += ' * Dígito verificador de Rut de empresa a buscar incorrecto';
		if (mensaje != "")
		{
			alert("Errores de validación:\n\n" + mensaje);
			return false;
		}		
		return true;
	}

	function borraEmpresa()
	{
		document.getElementById('formulario').empresa.value = '';
	}

	function borraGrupo()
	{
		document.getElementById('formulario').grupo.value = '';
	}
// -->
</script>
</body>
</html:html>

