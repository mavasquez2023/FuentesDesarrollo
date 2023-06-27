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
<body onLoad="foco();">
<html:form action="/ErroresEditar" styleId="formulario" onsubmit="return onFormSubmit(this)">

<input type="hidden" id="accionInterna" name="accionInterna"/>
<input type="hidden" id="id" name="id" value="<nested:write property="id" />"/>
<input type="hidden" id="errorSeleccionado" name="errorSeleccionado" />

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
				<td class="titulo" height="30">
				<logic:equal parameter="tipoEdicion" value="ACTUALIZA">
            				<strong>Edición Error</strong>
            			</logic:equal>
						<logic:equal parameter="tipoEdicion" value="NUEVO">
            				<strong>Crear Error</strong>
            			</logic:equal>
            			
					
				</td>
			</tr>
		 	<tr> 
		   		<td>&nbsp;</td>
		 	</tr>			
			<tr>
				<td width="100" class="barratablas">
					<strong>Descripci&oacute;n:</strong>
				</td>	
				<td width="150" class="textos_formularios" colspan="3">
					<input type="text" name="descripcion" id="descripcion" value="<nested:write property="descripcion" />" class="campos" maxlength="60" size="86" onblur="javascript:soloDireccion(this);" onkeyup="javascript:soloDireccion(this);" />
				</td>
			</tr>
			<tr>
				<td width="100" class="barratablas">
					<strong>Error:</strong>
				</td>	
				<td width="150" class="textos_formularios">
					<select id="errores" name="errores" class="campos">
						<nested:equal property="error" value="0"><option value="0" selected>Error</option></nested:equal><nested:notEqual property="error" value="0"><option value="0">Error</option></nested:notEqual>
						<nested:equal property="error" value="1"><option value="1" selected>Aviso</option></nested:equal><nested:notEqual property="error" value="1"><option value="1">Aviso</option></nested:notEqual>
						<nested:equal property="error" value="2"><option value="2" selected>No Validar</option></nested:equal><nested:notEqual property="error" value="2"><option value="2">No Validar</option></nested:notEqual>
					</select>
				</td>
				<td width="100" class="barratablas">
					<strong>Corregible:</strong>
				</td>
				<td width="150" class="textos_formularios">
					<nested:equal property="corregible" value="1">
						<input type="radio" id="corregibles" name="corregibles" value="1" checked/>S&iacute;
						<input type="radio" id="corregibles" name="corregibles" value="0" />No
					</nested:equal>
					<nested:equal property="corregible" value="0">
						<input type="radio" id="corregibles" name="corregibles" value="1" />S&iacute;
						<input type="radio" id="corregibles" name="corregibles" value="0" checked/>No
					</nested:equal>
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
<td height="4" bgcolor="#85b4be" >  </td>
</tr>
<tr> 
	<td>&nbsp;</td>
</tr>	
<tr>
	<td align="right">
		<html:button property="operacion" styleClass="btn3" value="Guardar" onclick="javascript:save();"/>
		<html:button property="operacion" styleClass="btn3" value="Cancelar" onclick="javascript:cancelar();"/>
	</td>
</tr>
</table>
<script type="text/javascript">
<!--
	function modifica(id){
		document.getElementById('modificar'+id).value = '1';
	}
	function cancelar(){
		frm = document.forms['ErroresEditarActionForm'];
		
		frm.accionInterna.value = 'CANCELAR';
		frm.submit();
	}
	function save(){
		frm = document.forms['ErroresEditarActionForm'];
		
		if (validar(frm)) {
			frm.accionInterna.value = 'GUARDAR';
			for(a=0; a<frm.errores.length; a++){
				if(frm.errores[a].selected == true){
					document.getElementById("errorSeleccionado").value = frm.errores[a].value;
				}
			}		
			frm.submit();
		}
	}
	
	function validar(frm) {
		var sMsje = "";
		
		if(!validaReq(document.getElementById("descripcion"))){
			sMsje += "* Debe ingresar la descripción\n";
		}
		if (sMsje != "") {
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		return true;		
	}
	function foco()
	{
		
			 document.getElementById('descripcion').focus();
	
	}
	
//-->
</script>
</html:form>
</body>
</html:html>
