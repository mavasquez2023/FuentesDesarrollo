<%@ include file = "/modulo/includes/env.jsp"%> 
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Simulación de Crédito"; %>
<%@ include file = "/modulo/includes/top.jsp"%>
  
<html:form action='/prepareSimulacionCredito'>
 
<html:hidden property="campo"/>
<html:hidden property="ingresosLiquidos"/>
<html:hidden property="montoSolicitado"/>
<html:hidden property="cantidadCuotas"/>
<html:hidden property="fechaNacimiento"/>
<html:hidden property="fechaIngresoEmpresa"/>
<html:hidden property="seguroDesgravamen"/>
<html:hidden property="seguroVida"/>
<html:hidden property="seguroCesantia"/>
<html:hidden property="rut"/>
 
<script>
function seleccion(opcion){

	document.PARAMForm.campo.value = opcion;
	document.PARAMForm.submit();

}

</script>

<br>
 
<table border="0" cellpadding="0" cellspacing="0" width="90%">
	<tr>
		<td align="center">
			<%@ include file = "/modulo/pages/simulacionCredito/cuerpoSimulacion.jsp"%>
		</td>
	</tr>
</table>
<div align='center'>
	<table>
		<tr>
			<td class="texto" colspan='2' align='center'>
				<html:link styleClass="subopcion" href='<%="javascript:seleccion(" + Constants.DATOS_SC_INGRESOS_LIQUIDOS + ")" %>'>
				<html:img page="/images/simulacionCredito/volverIngresoSC.gif" border="0"/>
				</html:link>					
			</td> 
			<td>
				&nbsp;
				&nbsp;
				&nbsp;												
				&nbsp;
				&nbsp;
			</td> 
			<td class="texto" colspan='2' align='center'>
				<html:link page='/showSimulacion.do'>
				<html:img page="/images/imprimir.gif" border="0" alt="Imprimir este resultado"/>
				</html:link>
			</td>
		</tr>
	</table>
</div>

</html:form>

<%@ include file = "/modulo/includes/footer.jsp"%>
