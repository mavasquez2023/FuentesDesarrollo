<%@ taglib uri="http://pd4ml.com/tlds/pd4ml/2.6" prefix="pd4ml" %>
<%@ include file="/html/comun/taglibs.jsp" %>
<pd4ml:transform
      pageInsets="10,10,10,10,points"
      screenWidth="800"
      adjustScreenWidth="true"
      pageFormat="LETTER"
      pageOrientation="landscape"
      fileName="cargasFamiliares.pdf"
      inline="false">

<html>
	<head>
		<title>PDF Cargas Familiares</title>
		<style type="text/css">
			body {
				font-family: Arial, Tahoma, Verdana, Helvetica, sans-serif;
				text-align: left;
				font-size: 10px;
				font-style: normal;
				margin: 0px;
				width: 100%;
			}

			table .bordeExterior {
				border: 2px solid #000000;
				margin: 0 0 10px 0;
			}
			table.bordeExterior td{
				border: hidden;
			}
			table.bordeExterior tr.negrita{
				font-weight: bold;
			}
			table.bordeExterior tr.informe{
				font-size: 8px;
			}

			.texto {
				font-size:  12px;
				font-style: italic;
			}

		</style>
	</head>

	<body>
		<html:form action="/ConsultaCargas" styleId="formulario">
			<logic:present name="ConsultaCargasPDFForm" property="cargas">
				<table>
					<tr>
						<td>
							<table width="100%" class="bordeExterior">
								<tr class="negrita">
									<td align="center">AUTORIZACI&Oacute;N DE ASIGNACI&Oacute;N FAMILIAR</td>
								</tr>
								<tr class="negrita">
									<td align="center">
										<logic:iterate id="cargaFamiliar" name="cargaFamiliarList">
												<fmt:formatDate value="${cargaFamiliar[0]}" pattern="dd-MMMM-yyyy" var="newdatevar" />
												<c:out value="${newdatevar}" />												
										</logic:iterate>
								</tr>
							</table>

							<%-- CARGAS FAMILIARES POR EMPRESA--%>
							<logic:equal name="ConsultaCargasPDFForm" property="cargasPorEmp" value="true">
								<logic:iterate id="carga" name="ConsultaCargasPDFForm" property="cargas" length="1">
									<table width="100%" class="bordeExterior">
										<tr class="negrita">
											<td width="15%">Caja de Compensación: </td>
											<td width="60%">${carga.nombreCaja}</td>
											<td width="5%"></td>
											<td width="20%"></td>
										</tr>
									</table>								
									<table width="100%" class="bordeExterior">
										<tr>
											<td width="15%"><b>Empleador:</b></td>
											<td width="60%">${carga.razonSocialEmpresa}</td>
											<td width="5%"><b>RUT:</b></td>
											<td width="20%">${carga.rutEmpresaFmt}</td>
										</tr>
									</table>
									<table width="100%" class="bordeExterior">
										<tr>
											<td width="15%"><b>Casa Matriz:</b></td>
											<td width="85%">${carga.casaMatrizEmpresa}</td>
										</tr>
									</table>
								</logic:iterate>
								<table width="100%" class="bordeExterior">
									<%--tr class="negrita">
										<td width="60%" colspan="7">Nombre del Causante</td>
										<td width="40%" colspan="3" align="center">Retroactivo</td>
									</tr--%>
									<tr class="negrita">
										<td nowrap>RUT</td>
										<td nowrap>Nombre</td>
										<td nowrap>RUT Carga</td>
										<td nowrap>Nombre Carga</td>
										<td nowrap>Vencimiento</td>
										<td nowrap>Nacimiento</td>
										<td nowrap>Parentesco</td>
										<td nowrap>Tipo Carga</td>
										<%--td nowrap>Desde</td>
										<td nowrap>Hasta</td--%>
										<td nowrap>Tramo</td>
										<td nowrap>Monto</td>
									</tr>
									<logic:iterate id="cargas" name="ConsultaCargasPDFForm" property="cargas">
										<tr class="informe">
											<td nowrap>${cargas.rutTrabajadorFmt}</td>
											<td>${cargas.nombreTrabajador}</td>
											<td nowrap>${cargas.rutCargaFmt}</td>
											<td>${cargas.nombreCarga}</td>
											<td><bean:write format="dd/MM/yyyy" name="cargas" property="fecFinVigencia"/></td>
											<td><bean:write format="dd/MM/yyyy" name="cargas" property="fecNacCarga"/></td>
											<td>${cargas.parentesco}</td>
											<td>${cargas.tipo}</td>
											<%--td><bean:write format="dd/MM/yyyy" name="cargas" property="fecIniVigencia"/></td>
											<td><bean:write format="dd/MM/yyyy" name="cargas" property="fecFinVigencia"/></td--%>
											<td>${cargas.tramoAsigFam}</td>											
											<td align="right">${cargas.valorCargaFamiliar}</td>
										</tr>
									</logic:iterate>
								</table>
							</logic:equal>
	
							<%-- CARGAS FAMILIARES POR TRABAJADOR--%>
							<logic:equal name="ConsultaCargasPDFForm" property="cargasPorEmp" value="false">
								<logic:iterate id="carga" name="ConsultaCargasPDFForm" property="cargas" length="1">
									<table width="100%" class="bordeExterior">
										<tr class="negrita">
											<td width="15%">Caja de Compensación: </td>
											<td width="60%">${carga.nombreCaja}</td>
											<td width="5%"></td>
											<td width="20%"></td>
										</tr>
									</table>								
					
									<table width="100%" class="bordeExterior">
										<tr>
											<td width="15%"><b>Afiliado:</b></td>
											<td width="60%">${carga.nombreTrabajador}</td>
											<td width="5%"><b>RUT:</b></td>
											<td width="20%">${carga.rutTrabajadorFmt}</td>
										</tr>
									</table>
									<table width="100%" class="bordeExterior">
										<tr>
											<td width="15%"><b>Empleador:</b></td>
											<td width="60%">${carga.razonSocialEmpresa}</td>
											<td width="5%"><b>RUT:</b></td>
											<td width="20%">${carga.rutEmpresaFmt}</td>
										</tr>
									</table>
								</logic:iterate>								
								<table width="100%" class="bordeExterior">
									<tr>
										<td width="15%"><b>Sucursal:</b></td>
										<td width="50%"><bean:write name="ConsultaCargasPDFForm" property="nombreSucursal"/></td>
										<td width="5%"><b>Anexo:</b></td>
										<td width="30%"><bean:write name="ConsultaCargasPDFForm" property="direccSucursal"/></td>
									</tr>
								</table>
								<table width="100%" class="bordeExterior">
									<%--tr class="negrita">
										<td width="60%" colspan="5">Nombre del Causante</td>
										<td width="40%" colspan="3" align="center">Retroactivo</td>
									</tr--%>
									<tr class="negrita">
										<td align="center" nowrap>RUT</td>
										<td nowrap>Nombre</td>
										<td nowrap>Vencimiento</td>
										<td nowrap>Nacimiento</td>
										<td nowrap>Parentesco</td>
										<td nowrap>Tipo Carga</td>
										<%--td nowrap>Desde</td>
										<td nowrap>Hasta</td--%>
										<td nowrap>Tramo</td>
										<td nowrap>Monto</td>
									</tr>
									<logic:iterate id="cargas" name="ConsultaCargasPDFForm" property="cargas">
										<tr class="informe">
											<td nowrap>${cargas.rutCargaFmt}</td>
											<td>${cargas.nombreCarga}</td>
											<td><bean:write format="dd/MM/yyyy" name="cargas" property="fecFinVigencia"/></td>
											<td><bean:write format="dd/MM/yyyy" name="cargas" property="fecNacCarga"/></td>
											<td>${cargas.parentesco}</td>
											<td>${cargas.tipo}</td>
											<%--td><bean:write format="dd/MM/yyyy" name="cargas" property="fecIniVigencia"/></td>
											<td><bean:write format="dd/MM/yyyy" name="cargas" property="fecFinVigencia"/></td--%>
											<td>${cargas.tramoAsigFam}</td>
											<td align="right">${cargas.valorCargaFamiliar}</td>
										</tr>
									</logic:iterate>
								</table>
							</logic:equal>
	
					   		<table width="100%" class="bordeExterior">
						   		<%--tr>
						   			<td width="70%"><b>Monto Asignaciones Familiares Retroactivas</b></td>
						   			<td align="right">[...]</td>
						   		</tr>
						   		<tr>
							   		<td><b>Monto del mes</b></td>
							   		<td align="right">[...]</td>
						   		</tr--%>
						   		<tr>
						   			<td><b>Monto Total</b></td>
									<td align="right"><c:out value="${ ConsultaCargasPDFForm.montoTotal }"></c:out><%--bean:write name="ConsultaCargasPDFForm" property="montoTotal"/--%></td>
						   		</tr>
					   		</table>
						
					   		<table width="100%">
						   		<tr>
									<logic:iterate id="carga" name="ConsultaCargasPDFForm" property="cargas" length="1">
							   			<td class="texto">La informaci&oacute;n detallada anteriormente, se encuentra registrada en Caja de Compensaci&oacute;n ${carga.nombreCaja}.<br>Se extiende el siguiente documento para su seguridad y certificaci&oacute;n. </td>
							   		</logic:iterate>
						   		</tr>
						   		<tr>
						   			<td align="right">http://www.cp.cl</td>
						   		</tr>
					   		</table>
						</td>
				   	</tr>
				</table>			
			</logic:present>
			<logic:notPresent name="ConsultaCargasPDFForm" property="cargas">
				NO HAY CARGAS
			</logic:notPresent>
		</html:form>
	</body>
</html>
</pd4ml:transform>