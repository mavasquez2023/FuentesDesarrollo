<%@ page import = "java.io.*"%>
     
<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp" %> 
<%@ include file = "/web/includes/top.jsp"%>



<table border="0" cellpadding="0" cellspacing="0" height='100%'>
<tr> 
<td width='160' valign='top' ><%@ include file = "/web/includes/opciones2.jsp"%></td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td>
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
<html:form action='/Welcome'>
<html:hidden property="accion" value="1"/>
<table border="0" cellpadding="2" cellspacing="2" width="50%">
	<logic:present name="isEsEmpresa">
	<tr>
		<td class="texto">
        	<bean:message key="login.usuario.empresa"/>
        </td>
        <td class="texto">
        	<html:radio property="tipoUsuario" value="1"/>
		</td>
    </tr>
    </logic:present>
    <logic:present name="isEsEmpresaPublica">
	<tr>
		<td class="texto">
        	<bean:message key="login.usuario.empresaPublica"/>
        </td>
        <td class="texto">
        	<html:radio property="tipoUsuario" value="3"/>
		</td>
    </tr>
    </logic:present>        
    <tr>
		<td class="texto">
        	<bean:message key="login.usuario.persona"/>
        </td>
        <td class="texto">
        	<html:radio property="tipoUsuario" value="2"/>
        </td>
	</tr>
	<tr>
		<td class="texto" colspan='2' align='center'>
			<html:image page='/images/aceptar.gif'/>
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