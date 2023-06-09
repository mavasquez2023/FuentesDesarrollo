<%@ include file = "/web/includes/env.jsp"%> 
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top2.jsp"%>
     
<script language="JavaScript1.2">
	function checkForm() {
		idCertificado = document.forms[0].id.value;

		if (idCertificado == "") {
			alert("Por favor ingrese el n�mero del certificado!");

			document.forms[0].id.focus();

			return false;
		}

		if (!isNumber(idCertificado)) {
			alert("Por favor ingrese el n�mero del certificado!");

			document.forms[0].id.value = "";
			document.forms[0].id.focus();

			return false;
		}

		return true;
	}

	function isNumber(s) {
		nmatches = 0;

	    for (i = 0; i < s.length; i++) {
	        ch = s.charAt(i);

			if ('0' <= ch && ch <= '9') {
				nmatches++;
			} else {
				return false;
			}
	    }

	    return nmatches > 0;
	}
</script>
	
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>
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

<table border="0" cellpadding="2" cellspacing="2" width="70%" align="center">
	<tr>
		<td class="texto" align="center">
			<font size='3'><bean:message key="label.validador.id.consulta"/></font>
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

<%@ include file = "/web/includes/footer.jsp"%>


