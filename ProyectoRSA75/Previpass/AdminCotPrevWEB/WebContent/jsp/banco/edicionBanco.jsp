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
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script >
	if(top==self)
		top.location.replace("<c:url value='/' />");
	</script>
</head>
<body>
<html:form action="/EdicionBanco" styleId="formulario">
	<input type="hidden" id="accionInterna" name="accionInterna" />
	<input type="hidden" id="idBanco" name="idBanco" value="<nested:write property="idBanco"/>"/>
	<input type="hidden" id="codSplSeleccionado" name="codSplSeleccionado" />
	
	<table width="590" border="0" cellspacing="0" cellpadding="0">
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
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr valign="bottom">
						<c:choose>
							<c:when test="${EdicionBancoActionForm.idBanco eq -1}">
								<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Ingresar banco</strong></td>
								<c:set var="readOnly" value=""/>
							</c:when>
							<c:otherwise>
								<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Modificar banco</strong></td>
								<c:set var="readOnly" value="readonly"/>
							</c:otherwise>
						</c:choose>	
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr>
						<td width="20%" class="barratablas">
							<strong>RUT</strong>
						</td>	
						<td width="80%" class="textos_formularios" colspan="3">
							<input type="text" id="rutBanco" name="rutBanco" value="<nested:write property="rutBanco" /><nested:write property="digitoRut" />" class="campos" maxlength="12" size="15" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);" ${readOnly}/>	
						</td>
					</tr>
					<tr>
						<td class="barratablas">
							<strong>Nombre</strong>
						</td>	
						<td class="textos_formularios" colspan="3">
							<input type="text" id="nombre" name="nombre" value="<nested:write property="nombre" />" class="campos" maxlength="30" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);" size="46" />	
							<nested:define id="nombre" property="nombre" />
						</td>

					</tr>
					<tr>
						<td width="20%" class="barratablas">
							<strong>C&oacute;digo SPL</strong>
						</td>	
						<td width="40%" class="textos_formularios">
							<html:select property="codSpl" styleId="codSpl" styleClass="campos">
								<html:option value="-1">Sin banco SPL</html:option>
								<html:optionsCollection property="listaSpl" label="descripcion" value="codigo" />
							</html:select>
						</td>					
						<td width="15%" class="barratablas">
							<strong>Estado</strong>
						</td>	
						<td width="20%" class="textos_formularios">
							<nested:equal property="estado" value="1">
								<input type="radio" id="estado" name="estado" value="1" checked/>ON
								<input type="radio" id="estado" name="estado" value="0" />OFF
							</nested:equal>
							<nested:equal property="estado" value="0">
								<input type="radio" id="estado" name="estado" value="1" />ON
								<input type="radio" id="estado" name="estado" value="0" checked/>OFF
							</nested:equal>
						</td>
						 
					</tr>
					<tr>
					  <td height="4" bgcolor="#85b4be" colspan="4"/>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td  colspan="4" align="right" >
							<html:button property="operacion" styleClass="btn3" value="Aceptar" onclick="javascript:guardarBanco();"/>
							<html:button property="operacion" styleClass="btn3" value="Cancelar" onclick="javascript:cancelarBanco();"/>
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
    function guardarBanco()
    {
        formu=document.getElementById('formulario');
        formu.accionInterna.value = 'GUARDAR';

        if (validate(formu))
        {
            for(a=0; a<formu.codSpl.length; a++)
            {
                if(formu.codSpl[a].selected == true)
                    formu.codSplSeleccionado.value = formu.codSpl[a].value;
            }
            formu.submit();
        }
    }

    function cancelarBanco()
    {
        formu=document.getElementById('formulario');
        formu.accionInterna.value = 'CANCELAR';
        formu.submit();
    }

    function validate(frm)
    {
        mensaje='';
        if(trim(document.getElementById("nombre").value) == "" )
            mensaje += '* Ingrese el nombre del banco\n';
        if(trim(document.getElementById("rutBanco").value) == "" )
            mensaje += '* Ingrese el rut del banco\n';

        if (!valida_Rut(document.getElementById("rutBanco")))
            mensaje += " * RUT inválido\n";

        if (mensaje != "") 
        {
            alert("Errores de validación:\n\n" + mensaje);
            return false;
        }       
        return true;   
    }
// End --> 
</script>
</html:html>
