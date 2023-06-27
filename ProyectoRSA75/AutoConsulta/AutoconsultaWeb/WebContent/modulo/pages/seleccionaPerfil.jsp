<%@ page import = "java.io.*" %>
  
<%@ include file = "/modulo/includes/env.jsp"%>
<%@ include file = "/modulo/includes/header.jsp"%>
<% showVolverMenu = false; %>
<% title = "Seleccione Perfil"; %>
<%@ include file = "/modulo/includes/top.jsp"%>

<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>
<tr>
<td valign='top'> 

<!-- Begin de la pagina particular -->
<div align='center'>
<br>
<div class="texto">
<br>
<div class="textobold"><bean:message key="login.usuario.empresa.persona"/></div>
<br>
<div class="textobold"><bean:message key="login.usuario.tipo.usuario.pregunta"/></div>
<br>


<table border="0" cellpadding="2" cellspacing="2" width="50%">
	<tr>
        <td class="texto">
 		<html:form action='/Welcome'>
		<html:hidden property="accion" value="1"/>
	    <html:hidden property="paso" value="3"/>
       	<html:image page='/images/entrarAfiliado.gif'/>
        <html:hidden property="tipoUsuario" value="2"/>
        </html:form>
		</td>

        <td class="texto">
		</html:form>
    	<html:form action='/Welcome'>
		<html:hidden property="accion" value="1"/>
		<html:hidden property="paso" value="3"/>
        <html:image page='/images/entrarEmpresa.gif'/>
        <html:hidden property="tipoUsuario" value="1"/>
		</html:form>
		</td>
    </tr>
</table>

</div>
</div>


<!-- End de la pagina particular -->

</td>
</tr>
</table>


<%@ include file = "/modulo/includes/footer.jsp"%>