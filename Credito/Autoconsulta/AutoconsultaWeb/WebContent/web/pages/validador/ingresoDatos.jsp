<%@ include file = "/web/includes/env.jsp"%> 
<%@ include file = "/web/includes/top2.jsp"%>

     
<script language="JavaScript1.2">
	function checkForm() {
		idCertificado = document.forms[0].id.value;

		if (idCertificado == "") {
			document.forms[0].id.focus();
			return false;
		}

		if (isNaN(idCertificado)) {
			if(isCertCredito(idCertificado)){
				window.location= "http://201.238.208.61/CertificadosCredito/ValidarCertificado.do?codigo=" + idCertificado;

			}else{
				alert("Por favor ingrese el número del certificado!");
				document.forms[0].id.value = "";
				document.forms[0].id.focus();
			}
			return false;
		}

		return true;
	}

	function isCertCredito(idCertificado){
		if(idCertificado.length>3){
			tipo= idCertificado.substr(0, 3);
			tipo=tipo.toUpperCase();
			if(tipo=="CPC" || tipo=="CDV" || tipo=="CCC" || tipo=="CSD"){
				return true;
			}

		}
		return false;
	}

</script>

<table border="0" cellpadding="0" cellspacing="0" width="100%" >
<tr>
<td valign='top'>

<!-- Begin de la pagina particular -->
<font class="certificado">

<%  // Captura el Mensaje de Error 
    String message=(String)session.getAttribute("validation.message");
%>

<div align='center'>
<br>
<div class="textobold"><font color=red>
<% if (message!=null) { %>
<bean:message key='<%= message %>'/>
<% } %>
</font></div>

<br>
<br>

<html:form action='validarCertificado'>

<table border="0" cellpadding="2" cellspacing="2" width="100%" align="center">
	<tr>
		<td class="texto" align="center">
			<bean:message key="label.validador.id.consulta"/>
			<br>
			<br>
		</td>
	</tr>

	<tr>
		<td class="texto" align="center">
			<html:text property="id" styleClass="field" size="27"/>
		</td>
	</tr>
	
	<tr>
		<td class="texto" align="center">
		    <br>
			<html:image page="/images/aceptar.gif" onclick="return checkForm();"/>
		</td>
	</tr>
</table>
</html:form>

<!-- End de la pagina particular -->

</td>
</tr>
</table>

<%
/* @ include file = "/web/includes/footer.jsp"
*/
%>


