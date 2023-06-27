<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>
<script src="/AutoconsultaWeb/web/js/functions.js" type="text/javascript"></script>
<script>   
function validaForm(){ 
	if(document.forms[1].rut.value==""){
		alert("Debe ingresar el rut de Empresa")
		return false;
	}
	if(document.forms[1].dv.value==""){
		alert("Debe ingresar el digito de Empresa")
		return false;
	}
	return true	;
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
%>
  
<div align='center'>
<br>
<div class="textobold"><font color=red>
<% if (message!=null && message!="") { %>
<bean:message key='<%= message %>'/>
<% } %>
<html:errors/> 

</font></div>
<br>
<div class="texto">
<br>
<div class="textobold"><bean:message key="empresa.text"/></div>
<br>

<html:form action='<%= "/" + volverA %>' >

<html:hidden property="validar" value="true"/>
<table border="0" cellpadding="2" cellspacing="2" width="50%">
	<tr>
		<td class="textobold"><bean:message key="empleado.rut"/>:</td>
		<td class="texto">
			<html:text property="rut" styleClass="field" size='8' maxlength='8' onkeypress="return acceptNum(event);"/> - 
			<html:text property="dv" styleClass="field"  size='1' maxlength='1' onchange="javascript:validaRut(document.forms[1].rut,this);"/>
		</td>
	</tr>
	<tr>
		<td class="texto" colspan='2' align='center'>
			<html:image page='/images/aceptar.gif' onclick="javascript:return validaForm();" />
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


