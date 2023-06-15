<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />

	 
	<table>
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
				CARGAR ARCHIVO RECIBIDO
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
				<html:form action="Holding" method="post" enctype="multipart/form-data">
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
										<html:file property="archivoholding" onkeypress="return false" styleId="archivo" />
									</td>
								</tr>
								<tr valign="top">
									<td width="722" valign="middle" height="50">
										<font size="2" face="Arial">Tipo de Archivo:</font>
										&nbsp;&nbsp;
										<html:radio property="tipo" styleId="holding" value="holding"  onclick="holdings();" ><font size="2" face="Arial">HOLDING</font></html:radio>
										&nbsp;&nbsp;&nbsp;
										<html:radio property="tipo" styleId="empresa" value="empresa"  onclick="empresas();" ><font size="2" face="Arial">EMPRESA</font></html:radio>
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
											value="Enviar archivo" styleId="button" 
											styleClass="boton" value="Subir archivo" 
											onclick="return validarHolding();" onmouseover="botonover();" 
											onmouseout="botondown();">
											Enviar Archivo
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
								La informaci&#243;n contenida en el archivo ser&#225; procesada s&#243;lo si no ha sido enviada anteriormente por la empresa, en cuyo caso dicha informaci&#243;n prevalecer&#225;. 
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