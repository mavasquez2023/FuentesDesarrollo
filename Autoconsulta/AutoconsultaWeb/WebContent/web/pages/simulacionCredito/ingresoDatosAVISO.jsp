<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>  
<%String cPath = request.getContextPath()+"/web/"; %>
<%String cPath2 = request.getContextPath(); %>
<% title = "Simulación de Crédito"; %>  
<%@ include file = "/web/includes/top.jsp"%>    
   
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>    
	<tr> 
		<!-- menu de opciones -->  
		<td width='160' valign='top'><%@ include file = "/web/includes/opciones.jsp"%></td> 
		<td width='1%'>&nbsp;</td>   
		<td valign='top'>     
   
			<table border="0" cellpadding="0" cellspacing="0" width="100%">    
				<html:form action='/prepareSimulacionCreditoWeb'>   

				<tr>    
					<td align="center">     
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr> 
							<td>    						
								<table border="0" cellpadding="0" cellspacing="0" width="100%"> 				
			                		<tr>&nbsp;</tr>  		 				
									<tr>  
										<td colspan='3'></td>
									</tr>
									<tr>
	                            		<td colspan="3">&nbsp;</td>
	                            	</tr>
									<tr>
										<td></td>
									</tr> 
								</table>				
				 
			<br>		
			
								<table align="right"> 				
									<tr>
										<td></td>			
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
						</table>
					</td>
				</tr>
                <tr>
                	<td align="center" class="c11azul">
                    	SIMULACIÓN DE CRÉDITO EN MANTENCIÓN                    	
                        <br />
                        FAVOR CONTÁCTESE CON NUESTROS EJECUTIVOS AL
                        <br />
                        TELÉFONO 600-4228100
                        <br />
			<br />
			PARA ACCEDER A OTRO SIMULADOR DE CRÉDITO
			<br />
			QUE TENEMOS A SU DISPOSICIÓN
			<br />
			POR FAVOR HACER CLIC <a href="http://portal.laaraucana.cl/wps/wcm/connect/La%20Araucana/araucana/home/simulador/">AQUÍ</a>.
			<br />
			TENGA EN CUENTA QUE AL SER UN SIMULADOR
			<br />
			LOS VALORES SERÁN REFERENCIALES
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
