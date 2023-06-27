<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>
<script src="/AutoconsultaWeb/web/js/functions.js" type="text/javascript"></script>
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
	return true	;
}

function limpiaRut(){	
	document.getElementById("rut").value="";
	document.getElementById("dv").value="";
}
</script>
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>
<tr>
<td width='160' valign='top' ><%@ include file = "/web/includes/opciones.jsp"%></td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td> 
<td valign='top'>

<!-- Begin de la pagina particular -->

<%  // Captura el Mensaje de Error 
    String message=(String)session.getAttribute("validation.message");
   // Captura a que action debe reenviar
	String volverA= (String)session.getAttribute("volverA");

	String rutEmpleado = "";
	String rutDVEmpleado = "";
	if(session.getAttribute("rutDelEmpleado")!=null){
		rutEmpleado = (String)session.getAttribute("rutDelEmpleado");		
		}
	if(session.getAttribute("rutDVDelEmpleado")!=null){
		rutDVEmpleado = (String)session.getAttribute("rutDVDelEmpleado");
		}	
%>
 
<div align='center'>
<br>
<div class="textobold"><font color=red>
<% if (message!=null) { %>
<bean:message key='<%= message %>'/>
<% } %>
<html:errors/>
</font></div>
<br>
<div class="texto">
<br>
<div class="tituloconsultas"><bean:message key='<%="consulta."+volverA%>'/></div>
<br>
<div class="textobold"><bean:message key="empleado.text"/></div>
<br>

<html:form action='<%= "/" + volverA %>' >

<html:hidden property="validar" value="true"/>
<table border="0" cellpadding="2" cellspacing="2" width="50%">
	<tr>
		<td class="textobold"><bean:message key="empleado.rut"/>:</td>
		<td class="texto">
			<html:text property="rut" styleClass="field" size='8' maxlength='8' onkeypress="return acceptNum(event);" value='<%= rutEmpleado%>' /> - 
			<html:text property="dv" styleClass="field"  size='1' maxlength='1' onchange="javascript:validaRut(document.forms[1].rut,this);" value='<%= rutDVEmpleado%>'/>			
		</td>
	</tr>
	<tr>
		<td class="texto" colspan='2' align='center'>
			<html:button onclick="javascript:limpiaRut();" property="dummyProperty" style="margin-bottom:5px; background-repeat: no-repeat; width=81; height=20; background-position: center; background: transparent; background-image: url(/AutoconsultaWeb/web/images/limpiarRut.gif); border: 0; ">""</html:button>
			
			<html:image page='/images/aceptar.gif' onclick="javascript:return validaForm();"  />
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


