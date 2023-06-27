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
	<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
</head>
<body  onLoad="foco();">
<html:form action="/ConceptoTesoEditar" styleId="formulario" onsubmit="return onFormSubmit(this)">

	<input type="hidden" id="accionInterna" name="accionInterna"/>
	
	<input type="hidden" id="idActual" name="idActual" value="<nested:write property="idActual" />" />
	<input type="hidden" id="descripcionActual" name="descripcionActual" value="<nested:write property="descripcionActual" />" />
	
	<input type="hidden" id="accion" name="accion" value="<nested:write property="accion" />" />
	
	<input type="hidden" id="idSeleccionado" name="idSeleccionado" />
	<input type="hidden" id="descripcionSeleccionado" name="descripcionSeleccionado" />
	
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
					<tr>
						<td colspan="4" height="30" bgcolor="#FFFFFF" class="titulo">
							<strong>Concepto Tesorer&iacute;a</strong>
						</td>
					</tr>
					<tr>
						<td width="60"  class="textos_formcolor"">
							<strong>Id Concepto:</strong>
						</td>	
						<nested:define id="id" property="id" />
						<td width="150"class="textos_formularios">
							<input type="text" class="campos" name="id" id="id" value='<nested:write property="id"/>' maxlength="5" size="10" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
						</td>
					</tr>
					<tr>
						<td width="60"  class="textos_formcolor">
							<strong>Descripci&oacute;n:</strong>
						</td>	
						<nested:define id="id" property="id" />
						<td width="200"class="textos_formularios">
							<input type="text" class="campos" name="descripcion" id="descripcion" value='<nested:write property="descripcion"/>' maxlength="50" size="75" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);"/>
						</td>
					</tr>
					<tr>
					<td height="4" bgcolor="#85b4be" colspan="4"> </td>
					</tr>
					<tr>
					<td colspan="4"> &nbsp; </td>
					</tr>
					<tr>
						<td  align="center" colspan="4">
							<div align="right">
								<html:button property="operacion" styleClass="btn3" value="Guardar" onclick="javascript:save();"/>
								<html:button property="operacion" styleClass="btn3" value="Cancelar" onclick="javascript:cancelar();"/>
							</div>
						</td>
		       		</tr>
				</table>
			</td>
		</tr>
	</table>
</html:form>
<script type="text/javascript">
<!--	
	function cancelar(){
		formu = document.getElementById("formulario");
		
		formu.accionInterna.value = 'CANCELAR';
		formu.submit();
	}
	function save(){
		formu = document.getElementById("formulario");
		
		if (validate(formu)) {
			formu.accionInterna.value = 'GUARDAR';
			document.getElementById("idSeleccionado").value = formu.id.value;
			document.getElementById("descripcionSeleccionado").value = formu.descripcion.value;
	
			formu.submit();
		}
	}
	function foco()
	{
		document.getElementById('id').focus();
	}
	
	function validate(frm) {
		mensaje='';
		
		if(document.getElementById("id").value == "" || document.getElementById("id").value == "0"){
			mensaje += '* Ingrese el Id de Concepto.';
		} 
		if(document.getElementById("descripcion").value == "" ){
			mensaje += '* Ingrese la descripcion del concepto.';
		} 		

		if (mensaje != "")  {
			alert("Errores de validación:\n\n" + mensaje);
			return false;
		}		
		return true;
	
	}	
//-->
</script>
</body>
</html:html>

