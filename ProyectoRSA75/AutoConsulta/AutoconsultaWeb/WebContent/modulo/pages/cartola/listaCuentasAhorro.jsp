<%@ page import = "java.io.*" %>
  
<%@ include file = "/modulo/includes/env.jsp"%>
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Seleccione Cuenta"; %>
<%@ include file = "/modulo/includes/top.jsp"%>

<br>

<table border="0" cellpadding="2" cellspacing="2" width="30%">
	<tr>
        <td class="textobold" colspan='2' nowrap>
        <font size='3'><bean:message key="label.cartola.seleccion"/></font>
		</td>
    </tr>
	<tr>
        <td class="textobold" colspan='2'><br>
		</td>
    </tr>
    
<logic:notEmpty name="lista.cuentas">

<logic:iterate id="register" name="lista.cuentas" type="cl.araucana.autoconsulta.vo.CuentaAhorroVO">
	<tr valign="middle">
        <td class="textobold" width="5%">
        <html:link styleClass="subopcion" page='<%= "/getCartolaAhorro.do?cuenta="+register.getNumeroCuenta() %>'>
        <html:img page="/images/ok.gif" border="0"/>
       	</html:link>
        </td>
        <td class="textobold">
        <html:link styleClass="subopcion" page='<%= "/getCartolaAhorro.do?cuenta="+register.getNumeroCuenta() %>'>
		<font size='4' color="#006666"><b><%= register.getFullNumeroCuenta() %></b></font>        
       	</html:link>
		</td>
    </tr>

</logic:iterate>    
</logic:notEmpty>

</table>

</div>





<%@ include file = "/modulo/includes/footer.jsp"%>

