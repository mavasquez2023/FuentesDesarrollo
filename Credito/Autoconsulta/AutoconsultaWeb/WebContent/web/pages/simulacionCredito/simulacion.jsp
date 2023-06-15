<%@ include file = "/web/includes/env.jsp"%>  
<%@ include file = "/web/includes/monitoreoSimulacionCreditoResultado.jsp"%>
<% title = "Simulación de Crédito"; %> 
<%@ include file = "/web/includes/top.jsp"%>
<script>
function seleccion(opcion){
 
	document.PARAMForm.campo.value = opcion;
	document.PARAMForm.submit();
  
}  

</script>  
<table border="0" cellpadding="0" cellspacing="0" width="90%" class="sello_agua">
<html:form action='/prepareSimulacionCreditoWeb'>

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
	<tr>
		<td align="center">
			<%@ include file = "/web/pages/simulacionCredito/cuerpoSimulacion.jsp "%> 
		</td>
	</tr> 
</table>  
<div align='center'>
	<table>    
		<tr>
			<td>
			   <input name="volver" type="button" class="boton" id="volver" onClick="javascript:seleccion('<%=Constants.DATOS_SC_INGRESOS_LIQUIDOS%>');" value="&lt;&lt; Volver a Simular"></td>															
			<td>
			<!--<td class="texto" colspan='2' align='center'> 
				<html:link styleClass="subopcion" href='<%="javascript:seleccion(" + Constants.DATOS_SC_INGRESOS_LIQUIDOS + ")" %>'>
				<html:img page="/images/simulacionCredito/volverIngresoSC.gif" border="0"/>
				</html:link>					
			</td>--> 
			<td>    
				&nbsp;   
				&nbsp;
				&nbsp;		 										
				&nbsp;
				&nbsp; 
			</td>
			<td class="texto" colspan='2' align='center'>			 
				<img onclick="javascript:window.open('/AutoconsultaWeb/web/showSimulacionWeb.do','','status=1,toolbar=0,menubar=0,resizable=1,scrollbars=1,left=20,top=20,width=700,height=250');" src="/AutoconsultaWeb/web/images/imprimir.gif" border="0" alt="Imprimir este resultado"/>
			</td>
		</tr>
	</table>
	</html:form>
</div>
 
<%@ include file = "/web/includes/footer.jsp"%>
