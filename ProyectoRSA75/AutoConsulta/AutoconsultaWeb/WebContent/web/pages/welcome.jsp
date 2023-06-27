<%@ page import="cl.araucana.autoconsulta.vo.UsuarioVO"%>
<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp "%>
<%@ include file = "/web/includes/top.jsp" %>

<table border="0" cellpadding="0" cellspacing="0"   class="sello_agua" width="934">
<tr> 
<td width='160' valign='top'>
<div class="menu">
<%@ include file = "/web/includes/opciones.jsp"%>
</div>
</td> 
<!-- menu de opciones -->
<td width='1%'>&nbsp;</td>
<td valign='top'>    

<script language="JavaScript"> 
	//function logoff(){


	//	document.logout.submit();
	//}
	function popUpCredito(){ 
		var ventana;  
		var prop="toolbar=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=no,width=805,height=630";
		ventana=window.open('<%= "/"+fullappname+"/pages/pubCreditos.jsp"%>',"pubcredito",prop);
	}
</script> 
 <%
  if(session.getAttribute("montoPreAprobado") != null){
	 Long aux = (Long) session.getAttribute("montoPreAprobado");
	 long montoCredito = aux.longValue();

	 if(montoCredito > 0){
%>
<script> //popUpCredito();</script>
<%	 
	}
} 
%>

<!-- Begin de la pagina particular -->
<%-- (Nuevos textos. REQ4369, gpavez@hotmail.com. 16/11/2007) --%>
<%
	UsuarioVO usuario = (UsuarioVO)  session.getAttribute("datosUsuario");
	if (usuario.isEsEmpresa() || usuario.isEsEmpresaPublica()) { %>
	    <div class="texto"><bean:message key="message.welcome.intro.empresa"/></div><br>
<%  } else { %>	    
		<div class="texto"><bean:message key="message.welcome.intro.persona"/></div><br>
<%  } %>

<div class="texto"><b><bean:write name='datosUsuario' property='nombre'/></b><br><br></div>
<div class="texto"><bean:message key="message.welcome"/></div><br>

<%if(usuario.isEsEncargadoEmpresa()){%>
<br>
<br>
	<div class="texto"><bean:message key="message.welcome.intro.msgEncargado1"/></div>
	<div class="texto"><bean:message key="message.welcome.intro.msgEncargado2"/></div>
<%}%>

</td>
</tr>
</table>
<%@ include file = "/web/includes/footer.jsp"%>
<!-- Comentario -->