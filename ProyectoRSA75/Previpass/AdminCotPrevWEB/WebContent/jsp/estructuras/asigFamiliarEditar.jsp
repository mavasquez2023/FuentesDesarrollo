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
	<script src="<c:url value='/js/funciones.js'/>"></script>
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script >
	if(top==self)
		top.location.replace("<c:url value='/' />");
	</script>
</head>
<body onLoad="foco();">
<html:form action="/AsigFamiliarEditar" styleId="formulario">
	<input type="hidden" id="accionInterna" name="accionInterna" />
	<input type="hidden" id="id" name="id" value="<nested:write property="id"/>"/>
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
			<td valign="top">
				&nbsp;
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr valign="bottom"> 
						<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Tipo Asig. Familiar</strong></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr>
						<td width="150" class="barratablas">
							<strong>Descripci&oacute;n:</strong>
						</td>	
						<td width="200" class="textos_formularios" colspan="3">
							<input type="text" id="nombre" name="nombre" value="<nested:write property="nombre" />" class="campos" maxlength="20" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);" size="30" />	
						</td>
					</tr>
					<tr>
						<td width="150" class="barratablas">
							<strong>Renta M&iacute;nima:</strong>
						</td>	
						<td width="200" class="textos_formularios">
							<input type="text" id="rentaMinima" name="rentaMinima" value="<nested:write format="####" property="rentaMinima"  />" class="campos" maxlength="10" size="20" onblur="javascript:soloMonto(this, '');" onkeyup="javascript:soloMonto(this, '');" />	
						</td>
						<td width="150" class="barratablas">
							<strong>Renta M&aacute;xima:</strong>
						</td>	
						<td width="200" class="textos_formularios">
							<input type="text" id="rentaMaxima" name="rentaMaxima" value="<nested:write format="####" property="rentaMaxima" />" class="campos" maxlength="10" size="20" onblur="javascript:soloMonto(this, '');" onkeyup="javascript:soloMonto(this, '');" />	
						</td>
					</tr>
					<tr>
						<td width="150" class="barratablas">
							<strong>Valor Carga:</strong>
						</td>	
						<td width="200" colspan="3" class="textos_formularios">
							<input type="text" id="valorCarga" name="valorCarga" value="<nested:write format="####" property="valorCarga" />" class="campos" maxlength="10" size="20" onblur="javascript:soloMonto(this, '');" onkeyup="javascript:soloMonto(this, '');" />	
						</td>
						
						 
					</tr>
					<tr>
						  <td height="4" bgcolor="#85b4be" colspan="4"/>		
					</tr>
					<tr>
						  <td colspan="4">&nbsp;</td>		
					</tr>
					<tr>
						<td   colspan="4" align="center">
							<html:button property="operacion" styleClass="btn3" value="Aceptar" onclick="javascript:guardarTipo();"/>
							<html:button property="operacion" styleClass="btn3" value="Cancelar" onclick="javascript:cancelarTipo();"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
<script>
<!--
function guardarTipo()
{
	formu=document.getElementById('formulario');
	formu.accionInterna.value = 'GUARDAR';

	if (validate(formu)) {
		formu.submit();
	}	
}

function validate(formu){
	mensaje='';
	
	if(formu.nombre.value == "" ){
		mensaje='* Ingrese la descripción del tipo \n';
	}
	
	if(formu.rentaMinima.value == "" ){
		mensaje=mensaje+ '* Ingrese la Renta mínima \n';
	}
	var rentaMinimaAux = new Number(limpiaNumero(formu.rentaMinima.value, ''));  
	if(rentaMinimaAux > 99999999 ){
		mensaje=mensaje+ '* Renta mínima debe ser menor a 99.999.999 \n';
	}
	
	if(formu.rentaMaxima.value == "" ){
		mensaje=mensaje+ '* Ingrese la Renta máxima \n';
	} 		
	var rentaMaximaAux = new Number(limpiaNumero(formu.rentaMaxima.value, '')); 
	if(rentaMaximaAux > 99999999 ){
		mensaje=mensaje+ '* Renta máxima debe ser menor a 99.999.999 \n';
	}	 

	if(rentaMinimaAux > rentaMaximaAux){
		mensaje=mensaje+ '* La renta mínima debe ser inferior a la renta máxima \n';
	} 	

	if(formu.valorCarga.value == "" ){
		mensaje=mensaje+ '* Ingrese el Valor de la Carga.\n';
	}
	var valorCargaAux = new Number(limpiaNumero(formu.valorCarga.value, '')); 
	if(valorCargaAux > 99999999 ){
		mensaje=mensaje+ 'Valor de la Carga debe ser menor a 99.999.999 \n';
	}
	
	if (mensaje != "")  {
		alert("Errores de validación:\n\n" + mensaje);
		return false;
	}
	return true;
	
}


function cancelarTipo(){
	formu=document.getElementById('formulario');
	formu.accionInterna.value = 'CANCELAR';
	formu.submit();
}
function foco()
{
	document.getElementById('nombre').focus();
}
// -->
</script>
</html:html>

