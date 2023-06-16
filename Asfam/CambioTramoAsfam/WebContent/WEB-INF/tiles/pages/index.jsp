<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />
 
  
 <table width="100%"><td valign="top">
<jsp:include   page="/WEB-INF/tiles/common/menu.jsp">
 
</jsp:include>	
 
 </td><td width="730px">
 <center>	<b>
			<i>
				<font color="#808080">
					Proceso Actualizaci&#243;n de Tramos
					<br><bean:write name="proceso" property="proceso"/>
				</font>
			</i>
		</b>
		</center>
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
	</tbody>
</table>
<table cellspacing="0" cellpadding="0" width="100%" border="0">
	<tbody>
		<tr>
				<td width="100%" colspan="2">
					<br>
				</td>
			</tr>
		<tr>
				<td width="90%" align="center">
					<font class=blueText5>
					<b>
					LISTA DE EMPRESAS ASIGNADAS
					</b>
					</font>
				</td>
			</tr>
			<tr>
			
		<tr>
			<td width="100%" colspan="2">
				<br>
				
			</td>
		</tr>
		<tr>
			<td width="100%">
				<div id="tabla" style="width: 730px;">
					<table cellspacing="0" cellpadding="0" width="100%" border="0">
						<tbody>
							<tr>
								<th width="30%" align="left" bgcolor="#050BD1">
									Rut Empresa
								</th>
								<th align="left" width="70%">
									Nombre Empresa
								</th>
							</tr>
							<logic:iterate name="edocs_encargado" property="empresas" id="empresa" indexId="index"> 
								<tr>
									<td width="30%">
										<font class="blueText">&nbsp;<jsp:getProperty name="empresa" property="formattedRut" /></font>
									</td>
									<td width="70%">
										<font class="blueText"><jsp:getProperty name="empresa" property="name" /></font>
									</td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
	</tbody>
</table></td>
</table>
 