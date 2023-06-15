<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

                          <table cellspacing="0" cellpadding="0" width="100%" border="1">
							<tbody>
								<tr>
									<th align="left" width="62%" >
										<font class=text>Estado</font>
									</th>
									<TH align="left" width="60%">
										<font class=text>Total procesados</font>
									</TH>
									<TH align="left" width="60%">
										<font class=text>Cantidad empresas</font>
									</TH>

								</tr>
							
							    <logic:iterate name="resumen_xls" id="resumenXLS" indexId="index">														

									<tr>
										<td width="62%">
											<font class="blueText">&nbsp;<jsp:getProperty name="resumenXLS"  property="estado" /></font>
										</td>
										<td width="60%">
											<font class="blueText"><jsp:getProperty name="resumenXLS" property="totalProcesados" /></font>
										</td>
										<td width="60%">
						                    <font class="blueText"><jsp:getProperty name="resumenXLS" property="cantidadEmpresas" /></font>
										</td>
									</tr>
																	
								    </logic:iterate>

									 	 
							</tbody>
						</table>
						
						<table>
						</table>
						
						<table cellspacing="0" cellpadding="0" width="100%" border="1">
							<tbody>
								<tr>
									<th align="left" width="16%" >
										<font class=text>Rut</font>
									</th>
									<TH align="left" width="50%">
										<font class=text>Nombre</font>
									</TH>
									<TH align="left" width="25%">
										<font class=text>Oficina</font>
									</TH>
									<th align="left" width="16%" >
										<font class=text>Sucursal</font>
									</th>
									<TH align="left" width="50%">
										<font class=text>Total registros Proc.</font>
									</TH>
									<TH align="left" width="25%">
										<font class=text>Estado</font>
									</TH>

								</tr>
							
							    <logic:iterate name="detalle_xls" id="detalleXLS" indexId="index">														

									<tr>
										<td width="16%">
											<font class="blueText">&nbsp;<jsp:getProperty name="detalleXLS"  property="rut" /></font>
										</td>
										<td width="50%">
											<font class="blueText"><jsp:getProperty name="detalleXLS" property="nombre" /></font>
										</td>
										<td width="25%">
						                    <font class="blueText"><jsp:getProperty name="detalleXLS" property="oficina" /></font>
										</td>
										<td width="16%">
											<font class="blueText">&nbsp;<jsp:getProperty name="detalleXLS"  property="sucursal" /></font>
										</td>
										<td width="50%">
											<font class="blueText"><jsp:getProperty name="detalleXLS" property="total" /></font>
										</td>
										<td width="25%">
						                    <font class="blueText"><jsp:getProperty name="detalleXLS" property="descEstado" /></font>
										</td>
									</tr>
																	
								    </logic:iterate>

									 	 
							</tbody>
						</table>

</body>
</html>