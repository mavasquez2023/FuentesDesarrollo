<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/monitoreoSimulacionCreditoIngresoDatos.jsp"%>  
<%String cPath = request.getContextPath()+"/web/"; %>
<%String cPath2 = request.getContextPath(); %>
<% title = "Simulación de Crédito"; %>  
<%@ include file = "/web/includes/top.jsp"%>    
<%@ page import="cl.araucana.autoconsulta.common.Constants" %>   
<%@ page import="cl.araucana.autoconsulta.vo.UsuarioVO"%>

<script language="JavaScript">
	function popUpCredito(){ 
                return ;
		var ventana; 
		var prop="toolbar=no,scrollbars=no,location=no,statusbar=no,menubar=no,resizable=no,width=805,height=630";
		ventana=window.open('<%= "/"+fullappname+"/pages/pubCreditos.jsp"%>',"pubcredito",prop);
	}
</script> 

<%
//NUEVO 
if(session.getAttribute("montoPreAprobado") != null){
	 Long aux = (Long) session.getAttribute("montoPreAprobado");
	 long montoCredito = aux.longValue();

	 if(montoCredito > 0){
%>
<script> popUpCredito();</script>
<%	}
} 
%>

<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%' class="sello_agua">
<tr> 
<!-- menu de opciones -->  
<td width='160' valign='top'>

<%@ include file = "/web/includes/opciones.jsp"%>

</td> 
<td width='1%'>&nbsp;</td>   
<td valign='top'>     
 <div id="spiffycalendar" class="text"></div>    
<!-- Begin de la pagina particular --> 
<script>var cPath = "<%=cPath%>";</script> 
<script>var cPath2 = "<%=cPath2%>/";</script> 
<script src="/AutoconsultaWeb/web/js/calendarcode.js" type="text/javascript"></script>
<script src="/AutoconsultaWeb/web/js/simulacionCredito.js" type="text/javascript"></script>

<script> 
function fHabilitaFechaPago(){ 
	if(document.PARAMForm.docurgente.checked){
		calFechaIngEmpresa.enabled=true;
	}else{
		calFechaIngEmpresa.hide();
		calFechaIngEmpresa.enabled=false; 
		document.PARAMForm.docfechapago.value="<bean:write name='PARAMForm' property='fechaIngresoEmpresa'/>";
	}
} 

</script>
<script type="text/javascript" language="JavaScript">
 var TodaysDate=new Date(); 
 <logic:present name="afiliado">		
 	 var calFechaIngEmpresa=new ctlSpiffyCalendarBox("calFechaIngEmpresa", "PARAMForm", "fechaIngresoEmpresa","btnDate2","",2);
 	 calFechaIngEmpresa.readonly=false;
 	 calFechaIngEmpresa.useDateRange=true;
 	 calFechaIngEmpresa.dateFormat='dd-MM-yyyy';
	 calFechaIngEmpresa.setMinDate(TodaysDate.getFullYear()-100,TodaysDate.getMonth()-12, TodaysDate.getDate());
	 calFechaIngEmpresa.setMaxDate(TodaysDate.getFullYear(),TodaysDate.getMonth()+1, TodaysDate.getDate());
 	
 </logic:present>
	 var calFechaNac=new ctlSpiffyCalendarBox("calFechaNac", "PARAMForm", "fechaNacimiento","btnDate1","",2);
	 calFechaNac.useDateRange=true;
	 calFechaNac.readonly=false;
	 calFechaNac.dateFormat='dd-MM-yyyy';
	 calFechaNac.setMinDate(TodaysDate.getFullYear()-100,TodaysDate.getMonth()-12, TodaysDate.getDate());
	 calFechaNac.setMaxDate(TodaysDate.getFullYear(),TodaysDate.getMonth()+1, TodaysDate.getDate());
</script>
<script>
function validaForm(f){  
	
	if (f.ingresosLiquidos.value == ""){ 
			alert("Debe ingresar el ingreso líquido");
			f.ingresosLiquidos.focus();
			return false;
	}
	if (f.montoSolicitado.value == ""){
		alert("Debe ingresar el monto a solicitar"); 
		f.montoSolicitado.focus();
		return false;
	} 
	if (f.cantidadCuotas.value == ""){
		alert("Debe ingresar el número de cuotas");
		f.cantidadCuotas.focus();
		return false;
	}
	if (f.fechaNacimiento.value == ""){ 
		alert("Debe ingresar una fecha de Nacimiento");
		return false;
	}
	
	<logic:present name="afiliado">		 
	if (f.fechaIngresoEmpresa.value == ""){
		alert("Debe ingresar una fecha de ingreso a la empresa");
		return false;
	}	 
    </logic:present>  
     
	if (f.oficina.value == ""){ 
		alert("Debe seleccionar una Sucursal");
		return false;
	}    

	return true;				 	 	
		 
}
</script>
<table border="0" cellpadding="0" cellspacing="0" width="100%">    
<html:form action='/prepareSimulacionCreditoWeb'>   
<html:hidden property="campo"/>    
<html:hidden property="campoAnterior"/>               
<html:hidden property="rut"/>       
	<tr>     
		<td align="center">     
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr> 	
					<td>   
						<!-- Datos que se deben ingresar -->      
						<table border="0" cellpadding="0" cellspacing="0" width="100%"> 
                            <tr> 
                  <td width="97%" class="c11azul"> <h1>Simulador de Cr&eacute;dito</h1></td>
                </tr> 
                <!--       
				<logic:present name="nombreSimulador">
                <tr> 
                  <td width="97%" class="c11azul"> <bean:write name="nombreSimulador" scope="session" /></td>
                </tr>  
                </logic:present>
                <tr>&nbsp;</tr>  	
                -->	 				
				<tr>  
								<td colspan='3'>     
									<%  // Captura el Mensaje de Error
									    String message=(String)session.getAttribute("validation.message");
									%>
									<div align='center'>  
									<br> 
									<div class="textobold"><font color=red>
									<% if (message!=null) { %> 
									<bean:message key='<%= message %>'/> 
									<% } %>
									<html:errors/> 
								</td>
							</tr>
							<tr><td colspan="3">&nbsp;</td></tr>
							<tr>
							<td><%@ include file = "/web/pages/simulacionCredito/simulaDatos.jsp"%></td>
							</tr> 
					</table>				
		           
   			<br>
			<logic:present name="afiliado">			
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td class="c11">
							<bean:message key="label.simulacion.credito.ingreso.avisoIngresos.afiliado"/>
					</td>
				</tr>
			</table>
			</logic:present>
			
			<table align="right"> 

				 <tr>
				<td><input name="filtro" type="button" class="boton" id="filtro" onClick="javascript:seleccion('creditos');" value="Simular &gt;&gt;"></td>			
				</tr>
                <tr>
                  <td valign="top" height="8"></td>
                </tr>
                <tr>
                  <td valign="top" background="/images/divider_blue.gif"><img src="/images/block.gif" width="3" height="1"></td>
                </tr>
					
			</table>
		</td>
	</tr>
	</html:form>
</table> 
</td>
</tr>
</table>
<br>
<br>
<br>
 
<br>


</td></tr>

<tr><td colspan="3">
<br/>

<table border="0" cellpadding="0" cellspacing="0" width="100%" style="border-top: 2px solid #ccc;padding-top:10px">
  <tr valign="top">
   <td width="50%"><html:img src="/AutoconsultaWeb/web/images/footer.jpg"  height="44" border="0"/></td>
   <td align="right"><html:img src="/AutoconsultaWeb/web/images/fono.gif"  height="40" border="0"/></td>
  </tr>
</table> 

<td></tr>

</table>

</body>
</html>


