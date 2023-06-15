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
		<td width="730px"><br>
			<div align="center">
				<b><i> <font color="#808080"> Proceso Actualizaci&#243;n de Tramos <br> 
<%-- 							<bean:write name="proceso" property="proceso" /> --%>
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
							<span style="color:red;text-size:16px">
								El comprobante de la empresa no esta disponible<br />
							</span>
						</td>
					</tr>
					<tr>
						<td width="100%"><br> <br></td>
					</tr>
					<tr>
						<td width="100%"></td>
					</tr>
				</tbody>
			</table>
		</td>
	</tr>
</table>