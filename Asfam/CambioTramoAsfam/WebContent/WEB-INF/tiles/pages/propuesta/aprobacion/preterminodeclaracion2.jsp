<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />

<script>
	
</script>

<table>
	<tr>
		<td valign="top">
			<jsp:include   page="/WEB-INF/tiles/common/menu.jsp"></jsp:include>
		</td>
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
		</td>
		<td width="730px">
			<br><br><br>
						
			<br />
			
			<center>
				<span style="color:red;text-size:16px">
					Su propuesta fue aceptada correcesta correctamente, puede descargar el comprobante en el menu <b>Estado de procesamiento</b>.<br />
				</span>
			</center>
		</td>
	</tr>
</table>