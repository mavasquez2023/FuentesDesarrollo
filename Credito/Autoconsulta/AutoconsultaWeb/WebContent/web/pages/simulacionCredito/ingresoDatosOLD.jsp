<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>         
<% title = "Simulación de Crédito"; %>  
<%@ include file = "/web/includes/top.jsp"%>    
<%@ page import="cl.araucana.autoconsulta.common.Constants" %>   
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>    
<tr>   
<!-- menu de opciones -->  
<td width='160' valign='top'><%@ include file = "/web/includes/opciones.jsp"%></td> 
<td width='1%'>&nbsp;</td> 
<td valign='top'>     
       
<!-- Begin de la pagina particular -->
<script src="/AutoconsultaWeb/web/js/calendario.js" type="text/javascript"></script> 
<script src="/AutoconsultaWeb/web/js/simulacionCredito.js" type="text/javascript"></script>
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
                  <td width="97%" class="c11azul">Bienvenido(a) <bean:write name="nombreSimulador" scope="session" /></td>
                </tr>  
                <tr>&nbsp;</tr>  						
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
				<!--<tr>
					<td class="texto" colspan='2'  >
						<html:link styleClass="subopcion" href="javascript:seleccion('creditos')">
										<html:img page="/images/simulacionCredito/continuarSC.gif"
											border="0" />
									</html:link>					
					</td>
				</tr>-->
				 <tr>
				<td><input name="filtro" type="button" class="boton" id="filtro" onClick="javascript:seleccion('creditos');" value="Continuar &gt;&gt;"></td>			
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

<%@ include file = "/web/includes/footer.jsp"%>
