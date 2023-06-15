<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/headerportal.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>
<script src="<%=sUriMedia %>/js/functions.js" type="text/javascript"></script>
<script>  
function validaForm(){ 
	if(document.forms[1].rut.value==""){
		alert("Debe ingresar el rut del Empleado")
		return false;
	}
	if(document.forms[1].dv.value==""){
		alert("Debe ingresar el digito del Empleado")
		return false;
	}
	return ChequearRutDigito(document.forms[1].rut, document.forms[1].dv);
}

function limpiaRut(){	
	document.getElementById("rut").value="";
	document.getElementById("dv").value="";
}
</script>
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%' class="sello_agua">
<tr>
<td width='160' valign='top' ><%@ include file = "/web/includes/opciones.jsp"%></td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td> 
<td valign='top'>

<!-- Begin de la pagina particular -->

<%  // Captura el Mensaje de Error 

	String rutEmpleado = "";
	String rutDVEmpleado = "";
	if(session.getAttribute("rutDelEmpleado")!=null){
		rutEmpleado = (String)session.getAttribute("rutDelEmpleado");		
		}
	if(session.getAttribute("rutDVDelEmpleado")!=null){
		rutDVEmpleado = (String)session.getAttribute("rutDVDelEmpleado");
		}	
%>
 
<div>
<span class="tituloconsultas"><h1><bean:message key='label.consulta.creditos.tituloConsultaEmpresa'/></h1></span>
<div class="texto"><bean:write name='datosUsuario' property='nombre'/><br></div>
<div class="texto">
<br/>
<bean:message key='label.consulta.creditos.ingreseRut'/>
<logic:present name="validation.message">
<div class="tituloconsultas">
<br/><bean:message key='<%=(String)session.getAttribute("validation.message") %>'/><br/>
<div>
</logic:present>
<html:form action='/getCreditos' method="POST">
<html:hidden property="validar" value="true"/>

<table border="0" cellpadding="2" cellspacing="2" width="100%">
	<tr>
		<td class="texto"><bean:message key="empleado.rut"/>:</td>
		<td class="texto">
			<html:text property="rut" styleClass="field" size='8' maxlength='8' onkeypress="return acceptNum(event);" value='<%= rutEmpleado%>' /> - 
			<html:text property="dv" styleClass="field"  size='1' maxlength='1' onchange="javascript:validaRut(document.CRECNSForm.rut,this);" value='<%= rutDVEmpleado%>'/>			
		</td>
	</tr>
	<tr>
		<td class="texto" colspan='2' align='center'>
			<input  class="boton" type="button" name="dummyProperty" value="Limpiar RUT""" onclick="javascript:limpiaRut();" onMouseOver="this.className='botonOver'" onMouseOut="this.className='boton'" >			
			<input name="aceptar"  type="submit"  class="boton" onMouseOver="this.className='botonOver'" onMouseOut="this.className='boton'" value="Aceptar" src="<%=sUriMedia %>images/aceptar.gif" >
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