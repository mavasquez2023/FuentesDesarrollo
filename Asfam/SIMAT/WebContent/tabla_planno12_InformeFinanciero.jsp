<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html>
<head>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache"> 
<title>SIMAT</title>
	<meta http-equiv="X-UA-Compatible" content="IE=8, IE=9, IE=10" />
	<link rel="stylesheet" type="text/css" href="css/estilos.css">
	<link rel="stylesheet" type="text/css" href="css/normalize.css">
	<link rel="stylesheet" type="text/css" href="css/simular.css">
	<link rel="stylesheet" type="text/css" href="css/simular2.css">
	<link rel="stylesheet" href="css/Master.css" type="text/css">

	<link rel="stylesheet" href='./css/main.css' type="text/css" />
	<link rel="stylesheet" href='./css/screen.css' type="text/css" />
	<link rel="stylesheet" href='./css/interior.css' type="text/css" />
	
	<link rel="stylesheet" href='cssSimat/estructura.css' type="text/css" />
	
	<link href="jqSimat/jqBlueUI/css/redmond/jquery-ui-1.9.2.custom.css" rel="stylesheet">
	<script src="jqSimat/jqBlueUI/js/jquery-1.8.3.js"></script>
	<script src="jqSimat/jqBlueUI/js/jquery-ui-1.9.2.custom.js"></script>
	<script src="jqSimat/calendar/ajustesCalendario.js"></script>
	
	
	<script src="jqSimat/validacionesForm/validacionHomologacion.js"></script>
	<script src="jqSimat/botones/ajustesBotones.js"></script>
	

</head>
<body>
<a name="keyArriba"></a> 
<div id="wrapper" name="wrapper">
	<div id="header" name="header">
		<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg" width="900" height="100">
	</div>
	<div id="workSpace" name="workSpace">
		<div id="simulacion">
			<h2> Informe Financiero</h2>
			<hr>
			<fieldset class="form-fieldset">
				<div align='right'>
					<input name="generarIF" id="generarIF" class="boton" type="button" value='Generar Informe Financiero' onclick="javascript:cargarIF()"/>
				</div>				
			</fieldset>
			<div id="load">
				<center><img src='./imgSimat/Loading.gif'><br>Espere un momento...<br></center>
			</div>
			<fieldset class="form-fieldset">		
			<div id="workSpaceVisorIF">
				<div id="workSpaceTitle">
					<table border="1">
						<thead>
							<tr>
								<th>Campos</th>
							</tr>
							</thead>
							<tbody>                    	
							<tr>
								<td>
									<label class="labelTableTitle">A. INGRESOS</label>
								</td>																							
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">A.1 Provisión de subsidios líquidos del mes</label>
								</td>																						
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">A.2 Provisión de cotizaciones de subsidios del mes, enterada en el mes siguiente</label>
								</td>																						
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">A.3 Provisión complementaria</label>
								</td>																						
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">A.3.1)  Subsidios líquidos del mes</label>
								</td>																					
							</tr>
							<tr style="height: 37px">
								<td>
									<label class="labelTableTitle">A.3.2) Cotizaciones Previsionales de subsidios del mes, enterada en el mes siguiente</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">A.4 Reintegro por cobro indebido</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">A.4.1)  Producto de fiscalización SUSESO </label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">A.4.2) otros reintegros</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">B. EGRESOS (C+D)</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C. SUBTOTAL GASTO EN SUBSIDIOS</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">	C.1 Subsidios por reposo Prenatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.2 Subsidios por reposo Postnatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.3 Subsidios por reposo Postnatal Parental</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.4 Subsidios por permiso por enfermedad grave del niño menor de un año</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.5 Subsidios a mujeres no cubiertas en regimen SIL</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.6 Documentos Caducados (-)</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.6.1)  Subsidios por reposo Prenatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.6.2) Subsidios por reposo Postnatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.6.3)  Subsidios por reposo Postnatal Parental</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.6.4) Subsidios por permiso por enfermedad grave del niño menor de un año</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.6.5) Subsidios a mujeres no cubiertas en regimen SIL</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.7 Documentos Anulados (-)	</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.7.1)  Subsidios por reposo Prenatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.7.2) Subsidios por reposo Postnatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.7.3)  Subsidios por reposo Postnatal Parental	</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.7.4) Subsidios por permiso por enfermedad grave del niño menor de un año	</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.7.5) Subsidios a mujeres no cubiertas en regimen SIL	</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.8 Documentos Reemitidos</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.8.1)  Subsidios por reposo Prenatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.8.2) Subsidios por reposo Postnatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.8.3)  Subsidios por reposo Postnatal Parental</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.8.4) Subsidios por permiso por enfermedad grave del niño menor de un año</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.8.5) Subsidios a mujeres no cubiertas en regimen SIL</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.9 Documentos Revalidados</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.9.1)  Subsidios por reposo Prenatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.9.2) Subsidios por reposo Postnatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.9.3)  Subsidios por reposo Postnatal Parental</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.9.4) Subsidios por permiso por enfermedad grave del niño menor de un año</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">C.9.5) Subsidios a mujeres no cubiertas en regimen SIL</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">D. SUBTOTAL GASTO EN COTIZACIONES (E+F+G)</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">E. COTIZACIONES A FONDOS DE PENSIONES</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">E.1  Subsidios por reposo Prenatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">E.2 Subsidios por reposo Postnatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">E.3  Subsidios por reposo Postnatal Parental</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">E.4 Subsidios por permiso por enfermedad grave del niño menor de un año</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">E.5 Subsidios a mujeres sin contrato vigente</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">F. COTIZACIONES DE SALUD</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">F.1  Subsidios por reposo Prenatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">F.2 Subsidios por reposo Postnatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">F.3  Subsidios por reposo Postnatal Parental</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">F.4 Subsidios por permiso por enfermedad grave del niño menor de un año</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">F.5 Subsidios a mujeres sin contrato vigente</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">G. COTIZACIONES DESAHUCIO E INDEMNIZACIÓN</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">G.1  Subsidios por reposo Prenatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">G.2 Subsidios por reposo Postnatal</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">G.3  Subsidios por reposo Postnatal Parental</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">G.4 Subsidios por permiso por enfermedad grave del niño menor de un año</label>
								</td>																								
							</tr>
							<tr>
								<td>
									<label class="labelTableTitle">H. EXCEDENTE O DÉFICIT (A-B)</label>
								</td>																								
							</tr>
						</tbody>
					</table>
				</div>
				<div id="workSpaceTable">
					<table border="1">
	                      <thead>
	                      <tr>
							<th>Balance General</th>
	                      </tr>
	                      </thead>
	                     	 <tbody>
		                     	 <logic:iterate  name="listaIF_balanceGeneral" id="listaIF_balanceGeneral">                      	
		                       		<tr>
		                        		<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='a'/> </label>
										</td>									
		                       		</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='a_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='a_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='a_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='a_3_1'/> </label>
										</td>									
									</tr>
									<tr style="height: 37px">
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='a_3_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='a_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='a_4_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='a_4_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='b'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_6'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_6_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_6_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_6_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_6_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_6_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_7'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_7_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_7_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_7_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_7_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_7_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_8'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_8_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_8_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_8_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_8_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_8_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_9'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_9_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_9_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_9_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_9_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='c_9_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='d'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='e'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='e_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='e_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='e_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='e_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='e_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='f'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='f_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='f_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='f_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='f_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='f_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='g'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='g_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='g_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='g_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='g_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_balanceGeneral' property='h'/> </label>
										</td>									
									</tr>
		                       	</logic:iterate>
	                      </tbody>
					</table>
				</div>
				<div id="workSpaceTable">
					<table border="1">
	                      <thead>
	                      <tr>
							<th>Informacion de Planos</th>
	                      </tr>
	                      </thead>
	                     	 <tbody>                      	
		                     	<logic:iterate  name="listaIF_cuadratura" id="listaIF_cuadratura">
									<tr>
		                        		<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='a'/> </label>
										</td>									
		                       		</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='a_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='a_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='a_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='a_3_1'/> </label>
										</td>									
									</tr>
									<tr style="height: 37px">
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='a_3_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='a_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='a_4_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='a_4_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='b'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_6'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_6_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_6_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_6_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_6_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_6_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_7'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_7_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_7_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_7_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_7_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_7_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_8'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_8_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_8_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_8_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_8_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_8_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_9'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_9_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_9_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_9_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_9_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='c_9_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='d'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='e'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='e_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='e_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='e_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='e_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='e_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='f'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='f_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='f_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='f_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='f_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='f_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='g'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='g_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='g_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='g_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='g_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_cuadratura' property='h'/> </label>
										</td>									
									</tr>
		                       	</logic:iterate>
	                      </tbody>
					</table>
				</div>
				<div id="workSpaceTable">
					<table border="1">
						<thead>
							<tr>
								<th>Diferencia</th>
							</tr>
						</thead>
						<tbody>                      	
							<logic:iterate  name="listaIF_diferencia" id="listaIF_diferencia">
								<tr>
		                        		<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='a'/> </label>
										</td>									
		                       		</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='a_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='a_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='a_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='a_3_1'/> </label>
										</td>									
									</tr>
									<tr style="height: 37px">
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='a_3_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='a_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='a_4_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='a_4_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='b'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_6'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_6_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_6_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_6_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_6_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_6_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_7'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_7_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_7_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_7_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_7_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_7_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_8'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_8_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_8_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_8_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_8_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_8_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_9'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_9_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_9_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_9_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_9_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='c_9_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='d'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='e'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='e_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='e_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='e_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='e_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='e_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='f'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='f_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='f_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='f_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='f_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='f_5'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='g'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='g_1'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='g_2'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='g_3'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='g_4'/> </label>
										</td>									
									</tr>
									<tr>
										<td>
											<label class="labelTableValor"><bean:write name='listaIF_diferencia' property='h'/> </label>
										</td>									
									</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			</fieldset>
				<div align='right'>
					<fieldset class="form-fieldset">
						    <input name="nav_down" id="nav_down" class="boton" type="button" value='Exportar Informe Financiero' onclick="javascript:generarInformeFinanciero()"/>
					</fieldset>
				</div>
			<fieldset class="form-fieldset">
				<div name="vm" id="vm">
				    <input class="boton" type="button" value='<< Volver a Menu' onclick="javascript:volverMenu()"/>
				</div>
			</fieldset>			
		</div>		
	</div>
	<div id="LoadMenu_dialog" title='Cargando...'>
			<div id="loadMenu" name="loadMenu" >
			<center><img src='./imgSimat/Loading.gif'><br>Espere un momento...<br></center>
			</div>			
		</div>
</div>
</body>
</html>
