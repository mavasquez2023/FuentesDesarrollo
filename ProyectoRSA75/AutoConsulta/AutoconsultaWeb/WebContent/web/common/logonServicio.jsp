<%@ page import="cl.araucana.autoconsulta.vo.UsuarioVO" %>

<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>

<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>
<tr> 
<td width='160' valign='top' ><%@ include file = "/web/includes/opciones.jsp"%></td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td>
<td valign='top'>    
<%
	UsuarioVO usuario = (UsuarioVO) session.getAttribute("datosUsuario");
	usuario.getRut();
	usuario.getRutusuarioAutenticado();
%>
 
<!-- Begin de la pagina particular -->
<div class="texto"><b><bean:write name='datosUsuario' property='nombre'/></b><br><br></div>

<h1>Acceso <bean:write name="servicioLogon.titulo"/></h1>
<logic:present name="servicioLogon.mensaje"> 
<bean:write name='servicioLogon.mensaje'/>
</logic:present>

<form action="<%= request.getContextPath() %>/web/logonServicio.do" method="POST">
  <table border="0">
  <tr>
    <td>Rut usuario</td>
    <td><input type="text" name="rutUsuarioServicio" /></td>
  </tr>
  <tr>
    <td>Clave acceso al servicio </td>
    <td><input type="password" name="claveUsuarioServicio" /></td>
  </tr>
  <tr>
    <td colspan="2"><input type="submit" name="enviar" value="Enviar"></td>
  </tr>
  </table>
</form>

<!-- End de la pagina particular GERO -->

</td>
</tr>
</table>

<%@ include file = "/web/includes/footer.jsp"%>