<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/headerportal.jsp"%>
<%@ include file = "/web/includes/topportal.jsp"%>
<script src="/AutoconsultaWeb/web/js/functions.js" type="text/javascript"></script>
<%
%>
<script>  
function validaForm(){ 
	if(document.forms[1].rutEmpresa.value==""){
		alert("Debe ingresar el rut del Empleado")
		return false;
	}
	if(document.forms[1].dvEmpresa.value==""){
		alert("Debe ingresar el digito del Empleado")
		return false;
	}
	if(document.forms[1].rutUsuario.value==""){
		alert("Debe ingresar el rut del Empleado")
		return false;
	}
	if(document.forms[1].dvUsuario.value==""){
		alert("Debe ingresar el digito del Empleado")
		return false;
	}
	return true	;
}

function limpiaRut(){
    document.forms[2].rutEmresa.value = "";
    document.forms[2].dvEmpresa.value = "";
    document.forms[2].rutUsuario.value = "";
    document.forms[2].dvUsuario.value = "";
}
</script>
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%' class="sello_agua">
<tr>
<td width='160' valign='top' ></td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td> 
<td valign='top'>

<!-- Begin de la pagina particular -->

<div>
<span class="tituloconsultas"><h1>Empresa / Usuario a consultar</h1></span>
<div class="texto"><bean:write name='datosUsuario' property='nombre'/><br></div>
<div class="texto">
<br>
<div class="tituloconsultas"></div>
<div class="textobold">Por favor ingrese el rut del empleado y rut del usuario a consultar</div>
<br/>
<logic:present name="validation.message">
<div class="botongris">
<br/><bean:write name="validation.message"/><br/>
<div>
</logic:present>
<html:form action='/adminUsuario' method="POST">
<html:hidden property="validar" value="true"/>

<table border="0" cellpadding="2" cellspacing="2" width="50%">
	<tr>
		<td class="texto"><bean:message key="label.empleador.rut"/>:</td>
		<td class="texto">
			<html:text property="rutEmpresa" styleClass="field" size='8' maxlength='8' onkeypress="return acceptNum(event);" value='' /> - 
			<html:text property="dvEmpresa" styleClass="field"  size='1' maxlength='1' onchange="javascript:validaRut(document.forms[1].rutEmpresa,this);" value=''/>			
		</td>
	</tr>
	<tr>
		<td class="texto"><bean:message key="empleado.rut"/>:</td>
		<td class="texto">
			<html:text property="rutUsuario" styleClass="field" size='8' maxlength='8' onkeypress="return acceptNum(event);" value='' /> - 
			<html:text property="dvUsuario" styleClass="field"  size='1' maxlength='1' onchange="javascript:validaRut(document.forms[1].rutUsuario,this);" value=''/>			
		</td>
	</tr>
	<tr>
		<td class="texto" colspan='2' align='center'>
			<input  class="boton" type="button" name="dummyProperty" value="Limpiar RUT""" onclick="limpiaRut();" onMouseOver="this.className='botonOver'" onMouseOut="this.className='boton'" >			
			<input name="accion"  value="Aceptar" type="submit"  class="boton" onMouseOver="this.className='botonOver'" onMouseOut="this.className='boton'" value="Aceptar" src="images/aceptar.gif" >
		</td>

	</tr>
</table>
</html:form>
</div>
</div>

<!-- End de la pagina particular -->

</td>
</tr>
</table>

<%@ include file = "/web/includes/footer.jsp"%>


