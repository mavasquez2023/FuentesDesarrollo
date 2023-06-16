<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

	<!-- <h1><bean:message key="periodo.ejecucion" /> -->
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
<html:form action="/Consulta2.do">
<div align="left">
	<h2>Consulta:</h2>
	<div id="tabla" style="width: 320px; padding: 5px;">
		<table cellspacing="0" cellpadding="0" width="320" border="0">
			<tbody>
				<tr>
					<td colspan="2" align="left">
						<font style="font-size: x-small;">* Todos los campos son requeridos:</font>
					</td>
				</tr>
				<tr>
					<th align="left" width="35%">
						<font class="text">Empresa:</font>
					</th>
					<td align="center" width="60%">
						<html:text name="divicionPrevicionalForm" property="empresa"
							onkeypress="return rut_validkey(event);" maxlength="10" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="left" style="padding-left: 160px;">
						<html:submit styleClass="boton"
							onclick="return onSubmitConsulta2();"
							onmouseover="this.className='botonOver'"
							onmouseout="this.className='boton'">
							Buscar
						</html:submit>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<logic:equal name="divicionPrevicionalForm" property="afiliados_propuesta_size" value="0">
		<h3>No se encontro informacion relacionada a los datos ingresados.</h3>
	</logic:equal>
	<!--<logic:present name="divicionPrevicionalForm" property="afiliados_propuesta">-->
		<h2>Resultado:</h2>
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tbody>
				<tr>
					<td width="100%">
						<div id="tabla" style="width: 260px;">
							<table width="260" cellspacing="0" cellpadding="0" border="0">
								<tbody>
									<tr>
										<th align="center" width="120" bgcolor="#050BD1">
											<font class="text">Nombre Archivo</font>
										</th>
										<th align="center" width="140">
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
														Descarge Aqui!!!
													</a>
												</font>
											</logic:present>
											<logic:notPresent name="archivo" property="urlDescarga">
												<font class="blueText">
													Archivo no Disponible para Descarga.
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
</html:form>
</td>
</table>