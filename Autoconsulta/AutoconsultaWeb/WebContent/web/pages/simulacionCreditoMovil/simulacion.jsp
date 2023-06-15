<%@ include file = "/web/includes/env.jsp"%>  
<%@ include file = "/web/includes/header.jsp"%>
<% title = "Simulación de Crédito"; %> 
<%@ include file = "/web/pages/simulacionCreditoMovil/cabeceraSimulacion.jsp"%> 

<script src="http://code.jquery.com/jquery-latest.js"></script>


<script>
function seleccion(opcion){
 
	document.PARAMForm.campo.value = opcion;
	document.PARAMForm.submit();
  
}  

</script>  


<table border="0" cellpadding="0" cellspacing="0" width="90%" class="sello_agua">
<html:form action='/simuladorCreditoMovil'>

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
			<%@ include file = "/web/pages/simulacionCreditoMovil/cuerpoSimulacion.jsp"%> 
		</td>
	</tr> 
</table>  
<div align='center'>
	<table>    
		<tr>
			
			<td>
			   <input name="volver" type="button" class="boton" id="volver" onClick="javascript:seleccion('<%=Constants.DATOS_SC_INGRESOS_LIQUIDOS%>');" value="&lt;&lt; Volver a Simular"></td>															
			<td>

			<td>    
				&nbsp;   
				&nbsp;
				&nbsp;		 										
				&nbsp;
				&nbsp; 
			</td>
			<td class="texto" colspan='2' align='center'>			 
				<img onclick="window.print();return false;" src="/AutoconsultaWeb/web/images/imprimir.gif" border="0" alt="Imprimir este resultado"/>
			</td>
			
			
		</tr>
	</table>
	</html:form>
</div>


<%@ include file = "/web/includes/footer.jsp"%>
