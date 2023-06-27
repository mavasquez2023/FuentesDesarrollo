<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>


<style type="text/css">

.cabezal1 {
	text-align:center; 
	text-decoration: underline; 
	font-family: ariel, verdana; 
	font-size: 14px; 
	font-weight: bold;  
	letter-spacing: 3px;
}

.cabezal2 {
	color: #006666;
	font-family: ariel, verdana; 
	font-size: 12px; 
	font-weight: bold; 
}

.destacado {
	text-align: justify;
	color: 6666ff#; 
	font-family: ariel, verdana; 
	font-size: 10px;
	text-decoration: none;
}

.normal {
	text-align: justify;
	color: #003333; 
	font-family: ariel, verdana; 
	font-size: 10px;
}

a.normal:link {
	text-decoration: none; 
	color: aqua; 
	font-family: ariel, verdana; 
	font-size: 10px;
}
a.normal:visited { 
	text-decoration: none; 
	color: #0000ff; 
	font-family: Arial, sans-serif, verdana; 
	font-size: 8px; 
	font-weight: normal; 
}
a.normal:hover { 
	text-decoration: blink; 
	color: #0000ff; 
	font-family: Arial, sans-serif, verdana; 
	font-size: 8px; 
	font-weight: normal; 
}

.boton { 
	font-family: Arial, sans-serif, verdana; 
	font-size: 10px; 
	font-weight: normal; 
}

b {
	font-weight: 700
}</style>

<SCRIPT language=javascript>
	function createWindow(name) {
		var newWindow;
		var details = "left=0,screenX=0,top=0,screenY=0";
		
		if (window.screen) {
			var ah = screen.availHeight - 170;
			var aw = screen.availWidth - 10;
			
			details += ",height=" + ah;
			details += ",innerHeight=" + (ah - 100);
			details += ",width=" + aw;
			details += ",innerWidth=" + aw;
			details += ",resizable";
			details += ",menubar=yes";
			details += ",toolbar=yes";
			details += ",location=yes";
			details += ",status=yes";									
			details += ",scrollbars=yes";									
		}
		
		newWindow = window.open('', name, details);
	}
</SCRIPT>

				<table width="100%" border="0">
					<tbody>
						<tr>
							<td style="width: 100%; vertical-align: top;">
								<table border="0">
									<tbody>
										<tr>
											<td colspan="4"></td>											
										</tr>
										<tr>
											<td class="cabezal1" colspan="4">Consultas</td>
										</tr>
										<tr>
											<td colspan="4"></td>											
										</tr>
										<tr>
											<td style="width: 15px;"></td>
											<td class="destacado" colspan="2">
												<span style = "font-size: 12px;"><br>A través del Servicio de <b>Consultas</b> usted podrá conocer los montos que la Caja  o su Empresa se adeudan mutuamente.</span> <br />
												<br />
												<span style = "font-size: 10px;">
												      <u>Nota</u>: Indicando la <b>Oficina de la Caja</b> en que usted se atiende y
												                   la <b>Sucursal de su Empresa</b> en las consultas que se presentan
												                   a continuaci&oacute;n, usted podr&aacute; obtener la información requerida:</span>
											</td>
											<td style="width: 20px;"></td>											
										</tr>
										<tr>
											<td colspan="4">&nbsp;</td>											
										</tr>
										<tr>
											<td></td>											
											<td class="cabezal2" colspan="2">&#149;&nbsp;Cuenta Corriente</td>
											<td></td>											
										</tr>
										<tr>
											<td></td>											
											<td style="width: 20px;"></td>											
											<td class="normal">
											    <span style = "font-size: 10px;">
											          <br>
											          Esta consulta le permitir&aacute;
											          conocer los valores que la Caja o su Empresa se adeudan
													  mutuamente, por diferentes conceptos.
													  <b>Valores vigentes en la fecha de su consulta.</b>
												</span>
									        </td>
											<td></td>											
										</tr>					
										<tr>
											<td colspan="4" style="height: 3px; font-size: 3px;"></td>											
										</tr>
											<tr>
											<td></td>											
											<td></td>											
											<td align="center"><br><input class="boton" type="button" value="Consultar Cuenta Corriente Empresa" onclick="location.href='<%=request.getContextPath()%>/ctaCteN1.do?idChanged=EMPRESA'; return false;"/><br></td>
											<td></td>											
										</tr>					
										<tr>
											<td colspan="4" style="height: 5px; font-size: 4px;">&nbsp;</td>											
										</tr>
										<tr>
											<td></td>											
											<td class="cabezal2" colspan="2">&#149;&nbsp;Liquidaci&oacute;n Cuenta Corriente</td>											
											<td></td>											
										</tr>
										<tr>
											<td></td>
											<td></td>
											<td class="normal">
											    <span style = "font-size: 10px;">
											          <br>
											          Esta consulta le permitir&aacute; conocer <b>los valores
											          liquidados en un per&iacute;odo determinado.</b>
											    </span>
											</td>
											<td></td>											
										</tr>						
										<tr>
											<td colspan="4" style="height: 3px; font-size: 3px;"></td>											
										</tr>
											<tr>
											<td></td>											
											<td></td>											
											<td align="center"><br><input class="boton" type="button" value="Consultar Liquidación Cuenta Corriente" onclick="location.href='<%=request.getContextPath()%>/liquidacionN1.do?idChanged=EMPRESA'; return false;"/><br></td>
											<td></td>											
										</tr>
										<tr>
											<td colspan="4" style="height: 5px; font-size: 4px;">&nbsp;</td>											
										</tr>
										<tr>
											<td></td>
											<td class="cabezal2" colspan="2">&#149;&nbsp;Cr&eacute;dito</td>
											<td></td>											
										</tr>
										<tr>
											<td></td>											
											<td></td>											
											<td class="normal">
											    <span style = "font-size: 10px;">
											          <br>
											          Esta consulta le permitir&aacute; conocer los
											          <b>Cr&eacute;ditos de sus Trabajadores</b> e imprimir
											          <b>Certificados de Saldo Capital para Finiquitos</b> y
											          <b>Tablas de Desarrollo de un Cr&eacute;dito.</b>
											    </span>
											</td>
											<td></td>											
										</tr>
										<tr>
											<td colspan="4" style="height: 3px; font-size: 3px;"></td>											
										</tr>
											<tr>
											<td></td>											
											<td></td>											
											<td align="center"><input class="boton" type="button" value="Consultar Créditos" onclick="location.href='<%=request.getContextPath()%>/deudaCreditoN1.do?idChanged=EMPRESA'; return false;"/></td>
											<td></td>											
										</tr>												
										<tr>
											<td colspan="4" style="height: 5px; font-size: 4px;">&nbsp;</td>											
										</tr>
										<tr>
											<td></td>											
											<td class="cabezal2" colspan="2"></td>											
											<td></td>											
										</tr>
										<tr>
											<td></td>											
											<td></td>											
											<td class="normal"></td>											
											<td></td>											
										</tr>	
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
