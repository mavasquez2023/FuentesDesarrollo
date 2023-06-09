<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp" %>
<%@ include file = "/web/includes/top.jsp"%>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
<tr>
<td width='160' valign='top' ><%@ include file = "/web/includes/opciones2.jsp"%></td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td>
<td valign='top'> 

<!-- Begin de la pagina particular -->

<% java.util.Hashtable nombres = (java.util.Hashtable)session.getAttribute("nombreEmpresas"); %>

<table border="0" cellpadding="2" cellspacing="2" width="70%">
		<tr>
			<td class="texto">
				<bean:message key="label.empresaacargo.seleccion"/>
			</td>
		</tr>

		<tr>
			<td class="texto">&nbsp;</td>
		</tr>


		<tr>
			<td class="textobold">
				<bean:message key="label.empresaacargo.seleccion.personal"/>
			</td>
		</tr>
		<tr>
			<td class="texto">
	        &nbsp;&nbsp;&nbsp;&nbsp;
	        <html:link styleClass="texto" page='<%= "/Welcome.do?accion=4&empresaACargo="+(-1) %>'>
			<b><bean:write name="datosUsuario" property="fullRut"/>&nbsp;<bean:write name="datosUsuario" property="nombre"/></b> 
	       	</html:link>
			</td>
		</tr>

		<tr>
			<td class="texto">&nbsp;</td>
		</tr>
		
<logic:notEmpty name="empresasACargo">


		<tr>
			<td class="textobold">
				<bean:message key="label.empresaacargo.seleccion.empresas"/>
			</td>
		</tr>


<logic:iterate id="register" name="empresasACargo" type="cl.araucana.autoconsulta.vo.EmpresaACargoVO">
		<tr>
			<td class="texto">
	        &nbsp;&nbsp;&nbsp;&nbsp;
	        <html:link styleClass="texto" page='<%= "/Welcome.do?accion=4&empresaACargo="+register.getRut() %>'>
			<b><%= register.getRut()+"-"+register.getDv()+" "+(nombres==null || nombres.get(String.valueOf(register.getRut()))==null ? " -- sin información --" : (String)nombres.get(String.valueOf(register.getRut()))) %></b>        
	       	</html:link>
			</td>
		</tr>
</logic:iterate>  
</logic:notEmpty>
</table>


<!-- End de la pagina particular -->

</td>
</tr>
</table>


<%@ include file = "/web/includes/footer.jsp"%>
