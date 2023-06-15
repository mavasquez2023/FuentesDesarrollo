<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<table>
	<td valign="top">
	<jsp:include flush="true" page="/WEB-INF/tiles/common/menudivision.jsp"></jsp:include>
	</td>
	<td width="730px">

<div align="center">
	<b>
		<i>Envio informe ingreso de trabajadores para actualizacion de tramos </i>
	</b>
	<br>
	<b>
		<i>
			<font color="#808080"><bean:message key="periodo.ejecucion" /></font>
		</i>
	</b>
	<br>
</div>
<br>
<div align="right">
	<font size="2" color="#808080">
		(Usuario: <jsp:getProperty name="edocs_encargado" property="fullName" /> )
	</font>
	<br>
	<font size="1" face="Verdana">Rut Encargado: 
		<b><i><jsp:getProperty name="edocs_encargado" property="formattedRut" /></i></b>
	</font>
</div>
<br>
<div id="tabla">
	<table width="100%" cellspacing="1" cellpadding="0" border="0">
		<tbody>
			<tr valign="top">
				<th width="16%" bgcolor="#050BD1">
					<b>
						<font class="text"> Rut Empresa </font>
					</b>
				</th>
				<th width="42%" bgcolor="#050BD1">
					<b>
						<font class="text"> Razon Social </font>
					</b>
				</th>
				<th width="38%" bgcolor="#050BD1">&nbsp;</th>
			</tr>
			<logic:greaterThan name="validaTerminosForm" property="aprobar_empresas_size" value="0">
				<logic:iterate name="validaTerminosForm" property="aprobar_empresas" id="empresa" indexId="index"> 
					<tr>
						<td width="16%">
							<font style="text-align: center;" class="blueText">&nbsp;<jsp:getProperty name="empresa" property="formattedRut" /></font>
						</td>
						<td width="42%">
							<font class="blueText"><jsp:getProperty name="empresa" property="name" /></font>
						</td>
						<td width="38%">
	                        <p align="center">Se aprobo exitosamente!!!</p>
						</td>
					</tr>
				</logic:iterate>
			</logic:greaterThan>
			<logic:equal name="validaTerminosForm" property="aprobar_empresas_size" value="0">
				<tr>
					<td colspan="3">
						<p align="center">
							<font class="blueText" style="font-weight: bold;">
								&nbsp; Usted no posee empresas que requieran aprobacion.
							</font>
						</p>
					</td>
				</tr>
			</logic:equal>
		</tbody>
	</table>
</div>
</td>
</table>