<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />

	<!-- <h1><bean:message key="periodo.ejecucion" /> -->
	<table>
	<tr>
	<td valign="top">
	<jsp:include flush="true" page="/WEB-INF/tiles/common/menudivision.jsp"></jsp:include>
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
	</tbody>
</table>
<table cellspacing="0" cellpadding="0" width="100%" border="0">
	<tbody>
		<tr>
			<td width="100%" colspan="3">
				<br>
				<br>
			</td>
		</tr>
		<tr>
			<td width="100%" valign="top" align="center">
				<html:form action="Cargar" method="post" enctype="multipart/form-data" styleId="formCarga" target="ENVIAR"> 
					<font size="2" face="Arial">
						Haga click en el bot&#243;n 'Seleccionar archivo' para buscar el archivo que desea enviar
					</font>
					<br>
					<br>
					
					<div id="tabla" style="width: 476px;">
					
						<table cellspacing="1" cellpadding="0" border="0" cellpading="1">
							<tbody>
								<tr valign="top">
									<th width="468" bgcolor="#050BD1">
										<font class="text">Enviar Archivos</font>
									</th>
								</tr>
								
								<tr valign="top">
									<td width="468" valign="middle" height="50">
										<font size="2" face="Arial">Archivo: </font>
										<html:file property="archivo" onkeypress="return false" styleId="archivo" />
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<br>
					<font size="2" face="Arial">
						<i>
							* Favor Recordar que el nombre de los archivo debe ser el rut de la empresa sin guion y sin digito verificador
							con extensi&#243;n .txt o .csv.
						</i>
					</font>
					<br>
					<br>
					<table cellspacing="0" cellpadding="0" border="0" width="100%">
						<tbody>
							<tr valign="top">
								<td width="100%">
									<p align="center">
										<html:button styleId="button" styleClass="boton" 
											value="Subir archivo" styleId="button"
											property="subir"
											styleClass="boton" value="Subir archivo" 
											onmouseover="botonover();" 
											onmouseout="botondown();">
										</html:button>
										<script>
										$(function(){
											$("#button").click(function(e){
												e.preventDefault();
												
												var result = validar('Ejecutivo');
												
												if(result == true){
													enviarPropuestaEjecutivo();
													$("#formCarga").submit();
												}
											});
										});
										</script>
										
										<html:cancel styleClass="boton"
											onclick="goToUrl('/CambioTramoAsfam/HomePage.do'); return false;"
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
</td>
</tr>
</table>