<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

	 
	<table>
		<td valign="top">
			<jsp:include   page="/WEB-INF/tiles/common/menudivision.jsp"></jsp:include>
		</td>
		<td width="730px">
		<div align="center">
			<b>
			<i>
				<font color="#808080">
					Proceso Actualizaci&#243;n de Tramos
					<br><bean:write name="proceso" property="proceso"/>
				</font>
			</i>
			</b>
			<br>
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
				<font class="blackText">Encargado División Previsional:</font>
				<br />
				<font class="blueText"> 
					<jsp:getProperty name="edocs_encargado" property="fullName" />
				</font>
			</td>
			<td align="left" width="70%" height="18">
				<font class="blackText">Rut Encargado:</font>
				<br />
				<font class="blueText">
					<jsp:getProperty name="edocs_encargado" property="formattedRut" />
				</font>
			</td>
			<td width="5%" height=18></td>
		</tr>
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
				<font class=blueText5>
				<b>
				CONSULTA DE PROPUESTAS WEB
				<br/>
				</b>
				</font>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="3">
				<br>
				<br>
			</td>
		</tr>
		<tr>
			<td width="100%" align="center" valign="top">
				<html:form action="/Consulta2.do">
					<div id="tabla" style="width: 476px;">
						<table cellspacing="1" cellpadding="1" border="0">
							<tbody>
								<tr valign="top">
									<th width="722" colspan="2" bgcolor="#050BD1">
										<font class="text">Ingrese parámetros para la búsqueda</font>
									</th>
								</tr>
								<tr>
									<td colspan="2" align="left">
										<font style="font-size: x-small;">(*) campos requeridos</font>
									</td>
								</tr>
								<tr>
									<th align="left" width="35%">
								 		<font class="text">Rut Empresa: *</font>
									</th>
									<td align="center" width="60%">
										<html:text name="divicionPrevicionalForm" property="empresa"
											onkeypress="return rut_validkey(event);" maxlength="10" />
										<font size="2" face="Arial"> (Ejemplo: 12345678-9)</font>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<br>
					<table cellspacing="0" cellpadding="0" border="0" width="100%">
						<tbody>
							<tr valign="top">
								<td width="100%">
									<p align="center">
										<html:submit styleClass="boton"
											onclick="return onSubmitConsulta2();"
											onmouseover="this.className='botonOver'"
											onmouseout="this.className='boton'">
											Buscar
										</html:submit>
										<html:cancel styleClass="boton"
											onclick="goToUrl('/CambioTramoAsfam/DivisionPrevisionalPage.do?step=homeDivisionPrevisional'); return false;"
											onmouseover="this.className='botonOver'"
											onmouseout="this.className='boton'">
											Cancelar
										</html:cancel>
									</p>
								</td>
							</tr>
						</tbody>
					</table>
				</html:form>
			</td>
		</tr>
	</tbody>
	</table>

	<logic:equal name="divicionPrevicionalForm" property="afiliados_propuesta_size" value="0">
		<h3>No se encontro informacion relacionada a los datos ingresados.</h3>
	</logic:equal>
	<!--<logic:present name="divicionPrevicionalForm" property="afiliados_propuesta">-->
		<h2 align="center">Resultado</h2>
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tbody>
				<tr>
					<td width="100%" align="center">
						<div id="tabla" style="width: 300px;">
							<table width="300" cellspacing="0" cellpadding="0" border="0">
								<tbody>
									<tr>
										<th align="center" width="120" bgcolor="#050BD1">
											<font class="text">Nombre Archivo</font>
										</th>
										<th align="center" width="250">
											<font class="text">Enlace de Descarga</font>
										</th>
									</tr>
									<logic:iterate name="divicionPrevicionalForm" property="afiliados_propuesta" 
										id="archivo" indexId="index"> 
										<tr>
											<td align="left">
												<font class="blueText">
													&nbsp;<bean:write name="archivo" property="nombreArchivo" />
												</font>
											</td>
											<td align="center">
											<logic:present name="archivo" property="urlDescarga">
												<font class="blueText">
													<a href="<bean:write name="archivo" property="urlDescarga" />">
														Clic aquí para descargar
													</a>
												</font>
											</logic:present>
											<logic:notPresent name="archivo" property="urlDescarga">
												<font class="blueText">
													Archivo No Disponible para Descarga.
												</font>
											</logic:notPresent>
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
	<!--</logic:present>-->

