<%@ page import = "java.io.*" %>
  
<%@ include file = "/modulo/includes/env.jsp"%>
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Seleccione Empleador"; %>
<%@ include file = "/modulo/includes/top.jsp"%>



<br>

<table border="0" cellpadding="2" cellspacing="2" width="30%">
	<tr>
        <td class="textobold" colspan='2' nowrap>
        <font size='3'><bean:message key="label.empleador.seleccion"/></font>
		</td>
    </tr>
	<tr>
        <td class="textobold" colspan='2'><br>
		</td>
    </tr>
    
<logic:notEmpty name="lista.empleadores">

<logic:iterate id="register" name="lista.empleadores" type="cl.araucana.autoconsulta.vo.EmpresaVO">
	<tr valign="middle">
        <td class="textobold" width="5%">
        <html:link styleClass="subopcion" page='<%= "/getAsignacionFamiliar.do?rut="+register.getRut() %>'>
        <html:img page="/images/ok.gif" border="0"/>
       	</html:link>
        </td>
        <td class="textobold">
        <html:link styleClass="subopcion" page='<%= "/getAsignacionFamiliar.do?rut="+register.getRut() %>'>
		<font size='4' color="#006666"><b><%= register.getRut()+"-"+register.getDv() %></b></font>        
       	</html:link>
		</td>
    </tr>

</logic:iterate>    
</logic:notEmpty>

</table>

</div>





<%@ include file = "/modulo/includes/footer.jsp"%>



