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
	
	<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
</head>
<body>
<html:form action="/ComprobSplListar" styleId="formulario">
<input type="hidden" id="accionInterna" name="accionInterna" />
	<table width="700" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<html:errors/>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr valign="bottom"> 
						<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Comprobantes de Pago Online (SPL)</strong></td>
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
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr valign="bottom"> 
						<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Filtro</strong></td>
					</tr>
				</table>
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
		           			<html:button property="operacion" styleClass="btn3" value="Buscar" onclick="javascript:buscar();"/>&nbsp;&nbsp;
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
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr valign="bottom"> 
						<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Listado</strong></td>
					</tr>
				</table>
			</td>
		</tr>		
  		<tr align="center"> 
   			<td valign="top" bgcolor="#CCCCCC">
   				<table width="100%" border="0" cellpadding="0" cellspacing="1">
		         	<tr class="subtitulos_tablas">
	             		<td width="15%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Grupo</td>
                		<td width="20%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Empresa</td>
		               	<td width="20%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Convenio</td>
		               	<td width="15%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Proceso</td>
						<td width="10%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Monto</td>
						<td width="20%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">NºComprobante</td>
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
							<nested:notEmpty property="total">
								<nested:notEmpty property="grupo">
								<tr>
			           				<td colspan="6" width="15%" height="20" align="left" valign="middle"  class="${estilo}">
										&nbsp;<nested:write property="grupo"/>
			                   		</td>
				                </tr>    	
					           </nested:notEmpty>
								<nested:notEqual property="total" value="-1">
							   		<tr>
				           				<td width="15%" height="20" align="left" valign="middle"  class="${estilo}">
											&nbsp;
				                   		</td>
					               		<td width="20%" height="20" align="left" valign="middle"  class="${estilo}">
											<div align="right">&nbsp;<nested:write property="empresa"/></div>
				                   		</td>
				                   		<td width="20%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;<nested:write property="convenio"/>
				                   		</td>
				                   		<td width="15%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;<nested:write property="proceso"/>
				                   		</td>
				                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				<div align="right">$&nbsp;<nested:write property="monto"/></div>
				                   		</td>
				                   		<td width="20%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				<div align="right">&nbsp;<nested:write property="numeroComprobante"/></div>
				                   		</td>
				                   	</tr>
							   		<tr>
				           				<td colspan="3" class="${estilo}">&nbsp;</td>
				                   		<td width="15%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;<strong>Total:</strong>
				                   		</td>
				                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				<div align="right">$&nbsp;<nested:write property="total" format="###,###,###"/></div>
				                   		</td>
				           				<td class="${estilo}">&nbsp;</td>
				                   	</tr>
				            	</nested:notEqual>
				            	<nested:equal property="total" value="-1">
					            	<tr>
				           				<td width="15%" height="20" align="left" valign="middle"  class="${estilo}">
											&nbsp;
				                   		</td>
					               		<td width="20%" height="20" align="left" valign="middle"  class="${estilo}">
											<div align="right">&nbsp;<nested:write property="empresa"/></div>
				                   		</td>
				                   		<td width="20%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;<nested:write property="convenio"/>
				                   		</td>
				                   		<td width="15%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				&nbsp;<nested:write property="proceso"/>
				                   		</td>
				                   		<td width="10%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				<div align="right">$&nbsp;<nested:write property="monto"/></div>
				                   		</td>
				                   		<td width="20%" height="20" align="left" valign="middle"  class="${estilo}">
			                   				<div align="right">&nbsp;<nested:write property="numeroComprobante"/></div>
				                   		</td>
				                   	</tr>							   		
				            	</nested:equal>
							</nested:notEmpty>
						</nested:iterate>
			         </nested:notEmpty>			         
				  	<nested:empty property="lista">
			   			<tr>
				   			<td colspan="11"  class="textos_formularios" align="left">
								No Existen datos
			         		</td>
			   			</tr>
			   		</nested:empty>			   		
		        </table>
			</td>
		</tr>
	</table>
</html:form>
<br /><br />
<script language="javaScript">
<!--
	function buscar()
	{
		var formulario=document.forms[0];
		if(formulario.grupo.value != '') 
		{
			formulario.accionInterna.value = 'GRUPO';
			formulario.submit();
		} else 
		{
			if(formulario.rutEmpresa.value != '')
				formulario.accionInterna.value = 'EMPRESA';

			var posGuion = document.getElementById("rutEmpresa").value.indexOf('-');
			var rutTMP = limpiaRutIncremental(document.getElementById("rutEmpresa").value);	
			if(formulario.rutEmpresa.value == '' && formulario.grupo.value == '')
			{
				formulario.accionInterna.value = 'TODO';
				formulario.submit();
			}else if(document.getElementById("rutEmpresa").value.length > 0 && rutTMP.length < 4)
			{
				alert(' * Debe ingresar al menos 4 dígitos de rut para la empresa a buscar');
				formulario.rutEmpresa.focus();
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
		document.formulario.rutEmpresa.value = '';
	}

	function borraGrupo()
	{
		document.formulario.grupo.value = '';
	}
// -->
</script>
</body>
</html:html>
