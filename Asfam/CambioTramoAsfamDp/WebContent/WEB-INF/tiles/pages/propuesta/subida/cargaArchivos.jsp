<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />
<table>
<td valign="top">
<jsp:include flush="true" page="/WEB-INF/tiles/common/menu.jsp">

</jsp:include>
</td>
<td width="730px">
	<!-- <h1><bean:message key="periodo.ejecucion" /> -->
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
				<font class="blackText">Encargado de Empresa:</font>
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
				PASO 3: ENV&#205;O DE MODIFICACIONES
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
				<html:form action="Cargar" method="post" enctype="multipart/form-data">
					<div id="tabla" style="width: 476px;">
						<table cellspacing="1" cellpadding="1" border="0">
							<tbody>
								<tr valign="top">
									<th width="722" bgcolor="#050BD1">
										<font class="text">Haga click en el bot&#243;n 'Examinar' para seleccionar el archivo que desea enviar</font>
									</th>
								</tr>
								<tr valign="top">
									<td width="722" valign="middle" height="50">
										<font size="2" face="Arial">Ubicaci&#243;n Archivo:</font>
										<html:file property="archivo" onkeypress="return false" styleId="archivo" />
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
										<html:submit styleId="button" styleClass="boton" 
											value="Enviar Archivo" onclick="return validar();" 
											onmouseover="botonover();" onmouseout="botondown();">
											Enviar Archivo
										</html:submit>
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
					<br>
					<div id="mensaje" align="left">
						<p>
							<font class="redBackGris">
							<u>Recuerde que</u>:
							<br/><br/>
							<li>
								El nombre del archivo debe ser el Rut de la Empresa, sin gui&#243;n y sin d&#237;gito verificador, 
								y debe poseer extensi&#243;n <b>.txt</b>, <b>.csv</b> &#243; <b>.xls</b>.
							</li>
							</br></br>
							<li>
								En caso que su empresa tenga trabajadores que hayan efectuado reconocimiento de causantes por primera vez durante el mes de Junio o Julio 2013, debe agregar en la n&#243;mina a estos trabajadores. 
							</li>
							</font>
							</p>
					</div>	
					<br>
				</html:form>
			</td>
		</tr>
	</tbody>
</table>
</td>
</table>