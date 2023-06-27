<%@ page import = "java.io.*" %>
  
<%@ include file = "/modulo/includes/env.jsp"%>
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Seleccione Tipo"; %>
<%@ include file = "/modulo/includes/top.jsp"%>



<br>

<table border="0" cellpadding="2" cellspacing="2" width="50%">
	<tr>
        <td class="textobold" colspan='2'>
        <bean:message key="label.asignacion.familiar.consulta.leyenda"/>
		</td>
    </tr>
	<tr>
        <td class="texto">
 		<html:form action='/getAsignacionFamiliar'>
		<html:hidden property="rut"/>
       	<html:image page='/images/cargaActiva.gif'/>
        <html:hidden property="tipoConsulta" value="1"/>
        </html:form>
		</td>

        <td class="texto">
		</html:form>
    	<html:form action='/getAsignacionFamiliar'>
		<html:hidden property="rut"/>
        <html:image page='/images/cargaInactiva.gif'/>
        <html:hidden property="tipoConsulta" value="2"/>
		</html:form>
		</td>
    </tr>
</table>

</div>





<%@ include file = "/modulo/includes/footer.jsp"%>












