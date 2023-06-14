<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>
     
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%' class="sello_agua">
<tr>
<td width='160' valign='top' >
	<%@ include file = "/web/includes/opciones.jsp"%>
</td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td>
<td valign='top'>

 
<!-- Begin de la pagina particular -->
  
<html:form action='/getAsignacionFamiliar'>
<logic:notEmpty name="lista.empleadores">
<table border="0" cellpadding="2" cellspacing="2" width="50%">
		<tr>
			<td nowrap class="textobold">
				<bean:message key="label.empleador.seleccion"/>: 
			</td>
			<td class="texto"> 
			  	<html:select property="rut" styleClass="combo">
    			    <html:options collection="lista.empleadores" labelProperty="nombreRut" property="rut"/>
		        </html:select>
			</td>
		</tr>
		<tr>
			<td class="texto" colspan='2' align='center'>
                  <input type="submit"  class="boton" name="dummyProperty" value="Aceptar" onclick="window.location='Menu.do'" onMouseOver="this.className='botonOver'" onMouseOut="this.className='boton'" />	



			</td>
		</tr>
</table>
</logic:notEmpty>
</html:form>

<!-- End de la pagina particular -->

</td>
</tr>
</table>

<%@ include file = "/web/includes/footer.jsp"%>