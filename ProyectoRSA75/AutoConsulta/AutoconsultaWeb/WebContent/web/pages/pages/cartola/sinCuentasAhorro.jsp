<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>
      
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>
<tr>
<td width='160' valign='top'><%@ include file = "/web/includes/opciones.jsp"%></td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td>
<td valign='top'>

<!-- Begin de la pagina particular -->
<font class="certificado">

<br>
<br>
<div align='center'>
	<html:link page="/Menu.do">
	<html:img page="/images/volver.gif" border="0" alt="Volver"/>
	</html:link>
</div> 
<br>
<br> 

<logic:notPresent name="noActiva">
	<br>
	<br>
	<br>
	<br>
	<bean:message key="label.cartola.sin.cuentas"/>
</logic:notPresent>

<logic:present name="noActiva">
	<br>
	<br>
	<br>
	<br>
	<bean:message key="label.cartola.sin.cuentas.activas"/>
</logic:present>

</font>

<script language="Javascript">
     window.open( "/AutoconsultaWeb/web/pages/cartola/comercialAhorro2.html?"+"/AutoconsultaWeb/web/images/ahorro/comercialAhorro.jpg", "",  
     "resizable=1,HEIGHT=200,WIDTH=200");
</script> 
<!-- End de la pagina particular -->

</td>
</tr>
</table>


<%@ include file = "/web/includes/footer.jsp"%>