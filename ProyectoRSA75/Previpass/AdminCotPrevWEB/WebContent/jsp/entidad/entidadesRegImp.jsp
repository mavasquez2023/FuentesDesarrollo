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
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
	<script src="<c:url value='/js/entidad.js'/>"></script>
	<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
</head>
<body>
<html:form action="/EdicionEntidadesRegImp" styleId="formulario" onsubmit="return onFormSubmit(this)">

<input type="hidden" id="accionInterna" name="accionInterna"/>

<input type="hidden" id="codigoEntidadAntiguo" name="codigoEntidadAntiguo" value="<nested:write property="codigoEntidadAntiguo" />" />
<input type="hidden" id="nuevaEntidad" name="nuevaEntidad" value="<nested:write property="nuevaEntidad" />" />
<input type="hidden" id="idEntidadExCajaSeleccionada" name="idEntidadExCajaSeleccionada" value="<nested:write property="idEntidadExCajaSeleccionada" />"/>
<nested:define id="origen" property="origen" />
<input type="hidden" id="origen" name="origen" value="${origen}"/>
<nested:define id="origenAfp" property="origenAfp" />
<input type="hidden" id="origenAfp" name="origenAfp" value="${origenAfp}"/>

<table width="590" border="0" cellspacing="0" cellpadding="0">
<tr valign="middle">
	<td>
		<html:errors/>
	</td>
</tr>
<tr valign="middle">
	<td>
		<html:messages id="msg" message="true">
			<div class="msgExito">${msg}</div>
		</html:messages>
	</td>
</tr>
<tr>
	<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<nested:equal property="nuevaEntidad" value="true">
				<tr height="26" valign="middle">
					<td valign="middle" colspan="4" height="30" bgcolor="#FFFFFF" class="titulo">
						<strong>Edici&oacute;n de Entidad Regimen Impositivo</strong>
					</td>
				</tr>
			</nested:equal>
			<nested:equal property="nuevaEntidad" value="false">
				<tr height="26" valign="middle">
					<td valign="middle" colspan="4" height="30" bgcolor="#FFFFFF" class="titulo">
						<strong>Creaci&oacute;n de Entidad Regimen Impositivo</strong>
					</td>
				</tr>				
			</nested:equal>
			<tr height="26" valign="middle">
				<td valign="middle" height="20" class="barratablas" colspan="4">
					<strong>Datos Entidad</strong>
				</td>
			</tr>		
			<tr height="26" valign="middle">
				<td valign="middle" width="130" class="barratablas">
					<strong>C&oacute;digo Entidad Ex-Caja *</strong>
				</td>	
				<td valign="middle" width="150" class="textos_formularios">
					<nested:define id="idEntExCaja" property="idEntExCaja"/>
					<input type="hidden" name="idEntExCaja" id="idEntExCaja" value="${idEntExCaja}"/>
						<nested:iterate id="fila" property="lista" indexId="nFila">
							<nested:equal property="id" value="${idEntExCaja}">
								<nested:write property="id"/>&nbsp;<nested:write property="nombre" />
							</nested:equal>
						</nested:iterate>
					
						
				</td>
				<td valign="middle" width="130" class="barratablas">
					<strong>Codigo Entidad Reg. Imp.*</strong>
				</td>	
				<td valign="middle" width="150" class="textos_formularios">
					<nested:text property="codigoEntidad" styleId="codigoEntidad" styleClass="campos" maxlength="4" size="30" onblur="javascript:soloNumero(this,'');" onkeyup="javascript:soloNumero(this,'');"/>
				</td>
			</tr>
			<tr height="26" valign="middle">
				<td valign="middle" width="130" class="barratablas">
					<strong>Nombre *</strong>
				</td>	
				<td valign="middle" colspan="3" width="150" class="textos_formularios">
					<input type="text" name="descripcion" id="descripcion" value="<nested:write property="descripcion" />" class="campos" maxlength="59" size="75"/>
				</td>				
			</tr>			
			<tr> 
          		<td height="4" colspan="4" bgcolor="#85b4be">
          		</td>
			</tr>
			<tr height="26" valign="middle">
				<td valign="middle" height="20" class="barratablas" colspan="4">
					<strong>Datos Tasa</strong>
				</td>
			</tr>		
			<tr height="26" valign="middle">
				<td valign="middle" width="130" class="barratablas">
					<strong>Tasa AFP con Isapre</strong>
				</td>
				<td valign="middle" width="150" colspan="3">
					<nested:text property="tasaPension" styleId="tasaPension" styleClass="campos" maxlength="5" size="9" onblur="javascript:soloDecimales(this);" onkeyup="javascript:soloDecimales(this);"/>
				</td>
			</tr>
			<tr>
				<td>
					&nbsp;
				</td>
			</tr>
			<tr>
				<td align="right" valign="top" class="leyenda">(*)Campos Obligatorios</td>
			</tr>
			<tr height="26" valign="middle">
				<td valign="middle" valign="top" colspan="4">
			    	<table width="100%" border="0" cellpadding="0" cellspacing="0">
				        <tr align="center">
			    	     	<td valign="middle" valign="top" height="20">
			    	     		<nested:equal property="nuevaEntidad" value="false">
				        	 		<html:button property="operacion" value="Guardar" styleClass="btn4" onclick="irAction('EDIT');"/>
			   	     			</nested:equal>
			    	     		<nested:equal property="nuevaEntidad" value="true">
			         			   <html:button property="operacion" styleClass="btn3" value="Guardar" onclick="irAction('NEW');"/>
								</nested:equal>
			     			    <html:button property="operacion" value="Cancelar" styleClass="btn4" onclick="cancelar();"/>
			            	</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<script type="text/javascript">
<!--
	function irAction(accion) {
		frm = document.getElementById('formulario');
		cod = document.getElementById('codigoEntidad').value;
		des = document.getElementById('descripcion').value;
		tasa = document.getElementById('tasaPension').value;
		var sMsje="";
		if(cod == ""){
			sMsje+="* Ingrese el codigo de la entidad\n";
		}
		if(des == ""){
			sMsje+="* Ingrese el nombre\n";
		}
		if(tasa == ""){
			sMsje+="* Ingrese la tasa\n";
		}
		document.getElementById("idEntidadExCajaSeleccionada").value = document.getElementById("idEntExCaja").value;
		if (accion == 'NEW') {
			frm.accionInterna.value = 'NEW';
		}
		if (accion == 'EDIT') {
			frm.accionInterna.value = 'GUARDAR';
		}
		frm.submit();
		
	}	

//-->
</script>
</html:form>
</body>
</html:html>
