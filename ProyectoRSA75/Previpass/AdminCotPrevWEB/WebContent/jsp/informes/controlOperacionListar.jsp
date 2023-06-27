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
	<TITLE>Control de Operaciones</TITLE>
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
<body onLoad="poneFoco('grupo')">
<html:form action="/ControlOperacionListar" styleId="formulario">
<input type="hidden" id="accionInterna" name="accionInterna" />
	<table width="1000" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<html:errors/>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr valign="bottom"> 
						<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Control de Operaciones</strong></td>
					</tr>
				</table>
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
						<td><strong>Grupo Convenio:</strong>
		           		<input type="text" id="grupo" name="grupo" maxlength="9" size="12" class="campos" onblur="javascript:soloNumero(this,'');borraEmpresa();" onkeyup="javascript:soloNumero(this,'');borraEmpresa();"/>
		           		<strong>RUT Empresa:</strong>
		           		<input type="text" id="rutEmpresa" name="rutEmpresa" maxlength="13" size="20" class="campos" onblur="javascript:soloRut(this);borraGrupo();" onkeyup="javascript:soloRut(this);borraGrupo();"/></td>
		           	</tr>
		        	<tr> 
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
	   		<td height="4" colspan="5" ></td>
       	</tr>
		   <tr align="center"> 
   			<td valign="top" bgcolor="#CCCCCC">
   				<table width="100%" border="0" cellpadding="0" cellspacing="1">
             		<tr class="subtitulos_tablas">
	             		<td width="10%" rowspan="2" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Grupo Convenio</td>
                		<td width="10%" rowspan="2" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Rut Empresa</td>
		               	<td width="10%" rowspan="2" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
		               	<td width="6%" rowspan="2" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Cantidad de Env&iacute;os</td>
						<td width="60%" colspan="8" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Informaci&oacute;n &uacute;ltimo env&iacute;o</td>
		         	</tr>
		         	<tr class="subtitulos_tablas">
	             		<td width="7%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Fecha</td>
                		<td width="9%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Hora inicio</td>
		               	<td width="9%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Hora t&eacute;rmino</td>
		               	<td width="7%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Tiempo Total</td>
						<td width="7%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Nº registros</td>
						<td width="7%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Factor Tipos</td>
						<td width="11" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Nodo</td>
						<td width="7%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Usuario</td>
		         	</tr>
	         		<nested:notEmpty property="lista">
		         		<nested:iterate id="listaOperacion" property="lista" indexId="nFila">
							<c:choose>
						    	<c:when test="${nFila % 2 == 0}">
						   		   	<c:set var="estilo" value="textos_formularios"/>
						   	    </c:when>
			   					<c:otherwise>
			   						<c:set var="estilo" value="textos-formcolor2"/>
			   					</c:otherwise>
							</c:choose>
		           			<tr>
				           		<td width="10%" height="20" align="left" valign="middle" class="${estilo}">
									&nbsp;<nested:write property="grupoConvenio"/>
		                   		</td>
			               		<td width="10%" height="20" align="left" valign="middle" class="${estilo}">
									<div align="right">&nbsp;<nested:write property="rutEmpresa"/></div>
		                   		</td>
		                   		<td width="10%" height="20" align="left" valign="middle" class="${estilo}">
	                   				&nbsp;<nested:write property="razonSocial"/>
		                   		</td>
		                   		<td width="6%" height="20" align="left" valign="middle" class="${estilo}">
	                   				<div align="right">&nbsp;<nested:write property="cantidadEnvios"/></div>
		                   		</td>
		                   		<td width="7%" height="20" align="left" valign="middle" class="${estilo}">
	                   				<div align="right">&nbsp;<nested:write property="fecha"/></div>
		                   		</td>
		                   		<td width="9%" height="20" align="left" valign="middle" class="${estilo}">
	                   				<div align="right">&nbsp;<nested:write property="horaInicio"/></div>
		                   		</td>
		                   		<td width="9%" height="20" align="left" valign="middle" class="${estilo}">
	                   				<div align="right">&nbsp;<nested:write property="horaTermino"/></div>
		                   		</td>
		                   		<td width="7%" height="20" align="left" valign="middle"  class="${estilo}">
	                   				<div align="right">&nbsp;<nested:write property="tiempoTotal"/></div>
		                   		</td>
		                   		<td width="7%" height="20" align="left" valign="middle"  class="${estilo}">
	                   				<div align="right">&nbsp;<nested:write property="numeroRegistros"/></div>
		                   		</td>
		                   		<td width="7%" height="20" align="left" valign="middle"  class="${estilo}">
	                   				<div align="right">&nbsp;<nested:write property="factorTipo"/></div>
		                   		</td>
		                   		<td width="11%" height="20" align="left" valign="middle"  class="${estilo}">
	                   				&nbsp;<nested:write property="nodo"/>
		                   		</td>
		                   		<td width="7%" height="20" align="left" valign="middle"  class="${estilo}">
	                   				&nbsp;<nested:write property="usuario"/>
		                   		</td>
		               		</tr>		               		
			             </nested:iterate>
			         </nested:notEmpty>
				  	<nested:empty property="lista">
			   			<tr>
				   			<td colspan="12"  class="textos_formularios" align="left">
								No Existen datos
			         		</td>
			   			</tr>
			   		</nested:empty>			   		
		        </table>
			</td>
		</tr>
	</table>
</html:form>
<script language="javaScript">
<!--
	function buscar()
	{
		formu=document.getElementById('formulario');
		if(formu.grupo.value != '') 
		{
			formu.accionInterna.value = 'GRUPO';
			formu.submit();
		} else 
		{
			if(formu.rutEmpresa.value != '')
				formu.accionInterna.value = 'EMPRESA';

			var posGuion = document.getElementById("rutEmpresa").value.indexOf('-');
			var rutTMP = limpiaRutIncremental(document.getElementById("rutEmpresa").value);			
			if(formu.rutEmpresa.value == '' && formu.grupo.value == '')
				alert('Debe ingresar el parámetro de búsqueda');
			else if(document.getElementById("rutEmpresa").value.length > 0 && rutTMP.length < 4)
			{
				alert(' * Debe ingresar al menos 4 dígitos de rut para la empresa a buscar');
				formu.rutEmpresa.focus();
			//} else if(document.getElementById("rutEmpresa").value.length > 0 && posGuion > 0 && !validaDV(rutTMP))
			} else if(document.getElementById("rutEmpresa").value.length > 0 && posGuion > 0 && !validaDV(document.getElementById("rutEmpresa").value))
			{
				alert(' * Dígito verificador de Rut de empresa a buscar incorrecto');				
				formulario.rutEmpresa.focus();
			} else
				formulario.submit();
		}
	}
	
	function borraEmpresa()
	{
		formu=document.getElementById('formulario');
		formu.rutEmpresa.value = '';
	}
	function borraGrupo()
	{
		formu=document.getElementById('formulario');
		formu.grupo.value = '';
	}
// -->
</script>
</body>
</html:html>
