<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />
<script>
	
</script>

<table>
	<tr>
		<td valign="top"><jsp:include
				page="/WEB-INF/tiles/common/menu.jsp"></jsp:include></td>
		<td width="730px">
			<div align="center">
				<b><i> <font color="#808080"> Proceso Actualizaci&#243;n de Tramos 
							<br><bean:write name="proceso" property="proceso"/>
					</font>
				</i></b><br>
			</div>
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tbody>
					<tr>
						<td width="5%" height="21"></td>
						<td width="80%" height="21"></td>
						<td width="10%" height="21"></td>
						<td width="5%" height="21"></td>
					</tr>
					<tr>
						<td width="5%" height="18"></td>
						<td align="left" width="20%" height="18">
							<font class="blackText">Encargado de Empresa:</font> <br /> 
							<font class="blueText"> <jsp:getProperty name="edocs_encargado" property="fullName" /></font>
						</td>
						<td align="left" width="70%" height="18">
							<font class="blackText">Rut Encargado:</font> <br /> 
							<font class="blueText"> <jsp:getProperty name="edocs_encargado" property="formattedRut" /></font>
						</td>
						<td width="5%" height=18></td>
					</tr>
					<tr>
					<tr>
						<td width="5%" height=21></td>
						<td width="80%" height=21></td>
						<td width="10%" height=21></td>
						<td width="5%" height=21></td>
					</tr>
					<tr>
						<td width="5%" height=21></td>
						<td width="80%" height=21></td>
						<td width="10%" height=21></td>
						<td width="5%" height=21></td>
					</tr>
				</tbody>
			</table>
			<table cellspacing="0" cellpadding="0" width="100%" border="0">
				<tbody>
					<tr>
						<td width="90%" align="center">
							<font class=blueText5><b> ESTADO PROCESAMIENTO DE DECLARACIONES <br /></b></font>
						</td>
					</tr>
					<tr>
						<td width="100%"><br> <br></td>
					</tr>
					<tr>
						<td width="100%">
							<div id="tabla" style="width: 730px;">
								<table cellspacing="0" cellpadding="0" width="100%" border="0">
									<tbody>
										<tr>
											<th width="16%" align="left" bgcolor="#050BD1">Rut Empresa</th>
											<th align="left" width="30%">Nombre Empresa</th>
											<th align=center width="15%">Estado</th>
											<th align=center width="15%">Cantidad Declarados</th>
											<th align=center width="15%">Cantidad Propuesta</th>
											<th align=center width="15%">Cantidad No Informados</th>
											<th align=center width="15%">&nbsp;</th>
										</tr>

										<logic:iterate name="listEstadosProcesamiento" id="estPro" indexId="index">
											<tr>
												<td>
													<font class="blueText">&nbsp;<bean:write name="estPro" property="rutFormateado"/></font>
												</td>
												<td>
													<font class="blueText"><bean:write name="estPro" property="empresaNombre" /></font>
												</td>
												<td align="center">
													<font class="blueText"><bean:write name="estPro" property="estado" /></font>
												</td>
												<td align="center">
													<font class="blueText" ><bean:write name="estPro" property="totalProcesadosStr" /></font>
												</td>
												<td align="center">
													<font class="blueText" ><bean:write name="estPro" property="totalInformadosStr" /></font>
												</td>
												<td align="center">
													<logic:notEqual value="0" name="estPro" property="totalNoDeclaradosStr">
														<font class="blueText" ><a href="DescargaNoDeclaradosZip.do?rutempnd=<bean:write name="estPro" property="rut"/>" title="Descargar No Informados"><bean:write name="estPro" property="totalNoDeclaradosStr" /></a></font>
													</logic:notEqual>
													<logic:equal value="0" name="estPro" property="totalNoDeclaradosStr">
														<font class="blueText" ><bean:write name="estPro" property="totalNoDeclaradosStr" /></font>
													</logic:equal>
												</td>
												
												<td>
													<logic:equal name="estPro" property="estado" value="Procesada">
														<a href="newcomprobante.do?rutEmpresa=<bean:write name="estPro" property="rut"/>" >Ver comprobante</a>
													</logic:equal>
													
												</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
</table>