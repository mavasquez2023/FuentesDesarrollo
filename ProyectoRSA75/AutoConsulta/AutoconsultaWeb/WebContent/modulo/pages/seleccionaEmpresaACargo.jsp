<%@ page import = "java.io.*" %>
  
<%@ include file = "/modulo/includes/env.jsp"%>
<%@ include file = "/modulo/includes/header.jsp"%>
<% showVolverMenu = false; %>
<% showInfoUser = false; %> 
<% forceExit = true; %>
<% title = "Seleccione Modalidad Consulta"; %>
<%@ include file = "/modulo/includes/top.jsp"%>  

<% java.util.Hashtable nombres = (java.util.Hashtable)session.getAttribute("nombreEmpresas"); %>

<br>

<table border="0" cellpadding="2" cellspacing="2" width="70%">

	<tr>
        <td class="texto" colspan='2'><b><bean:message key="label.empresaacargo.seleccion.personal"/></b></td>
    </tr>


	<tr valign="middle">
        
        <td class="texto" width="5%">
        <html:link styleClass="subopcion" page='<%= "/Welcome.do?paso=4&empresaACargo="+(-1) %>'>
        <html:img page="/images/ok.gif" border="0"/>
       	</html:link>
        </td>
        <td class="texto">
        <html:link styleClass="subopcion" page='<%= "/Welcome.do?paso=4&empresaACargo="+(-1) %>'>
		<font size='3' color="#006666"><bean:write name="datosUsuario" property="fullRut"/>&nbsp;<bean:write name="datosUsuario" property="nombre"/></font>        
       	</html:link>
		</td>        
    </tr>
    
            
<logic:notEmpty name="empresasACargo">

	<tr>
        <td class="texto" colspan='2'>&nbsp;</td>
    </tr>
	<tr>
        <td class="texto" colspan='2'><b><bean:message key="label.empresaacargo.seleccion.empresas"/></b></td>
    </tr>
    
<logic:iterate id="register" name="empresasACargo" type="cl.araucana.autoconsulta.vo.EmpresaACargoVO">
	<tr valign="middle">
        <td class="texto" width="5%">
        <html:link styleClass="subopcion" page='<%= "/Welcome.do?paso=4&empresaACargo="+register.getRut() %>'>
        <html:img page="/images/ok.gif" border="0"/>
       	</html:link>
        </td>
        <td class="texto">
        <html:link styleClass="subopcion" page='<%= "/Welcome.do?paso=4&empresaACargo="+register.getRut() %>'>
		<font size='3' color="#006666"><%= register.getRut()+"-"+register.getDv()+" "+(nombres==null || nombres.get(String.valueOf(register.getRut()))==null ? " -- sin información --" : (String)nombres.get(String.valueOf(register.getRut()))) %></font>        
       	</html:link>
		</td>
    </tr>
		
		    
</logic:iterate>    
</logic:notEmpty>

</table>






<%@ include file = "/modulo/includes/footer.jsp"%>



