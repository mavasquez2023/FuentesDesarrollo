<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>
     
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>
<tr>
<td width='160' valign='top'><%@ include file = "/web/includes/opciones.jsp"%></td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td>
<td valign='top'>

<!-- Begin de la pagina particular -->

<br>
<div class="texto" />
<br>
<div class="textobold"><bean:message key="label.asignacion.familiar.consulta.leyenda"/>:</div>
<br>
<html:form action='/getAsignacionFamiliar'>
<html:hidden property="rut"/>
<table border="0" cellpadding="2" cellspacing="2" width="50%">
	<tr>
		<td class="texto">
        	<bean:message key="label.asignacion.familiar.consulta.activas"/>
        </td>  
        <td class="texto">
        	<html:radio property="tipoConsulta" value="1"/>
		</td>
    </tr>
    <tr>
		<td class="texto">
        	<bean:message key="label.asignacion.familiar.consulta.inactivas"/>
        </td>
        <td class="texto">
        	<html:radio property="tipoConsulta" value="2"/>
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



<!-- End de la pagina particular -->

</td>
</tr>
</table>


<%@ include file = "/web/includes/footer.jsp"%>
