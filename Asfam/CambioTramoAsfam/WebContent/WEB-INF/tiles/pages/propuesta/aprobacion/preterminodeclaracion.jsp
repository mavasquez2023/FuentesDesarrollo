<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />
<!-- 
<script>
	window.location.href = "estadoprocesamiento.do";
</script>
-->
<table>
	<tr><td colspan="4">
		<div align="center" >
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
	</td>
	</tr>
	<tr>
			<td width="5%" height="18"></td>
			<td align="left" width="50%" height="18">
				<font class="blackText">
					<c:choose>
						<c:when test='${rol=="Ejecutivo"}'>
							Encargado División Previsional:
						</c:when>
						<c:otherwise>
							Encargado de Empresa:
						</c:otherwise>
					</c:choose>
					<br> <jsp:getProperty name="edocs_encargado" property="fullName" />
				</font>
			</td>
			<td align="right" width="40%" height="18">
				<font class="blackText">
					Rut Encargado: <br><jsp:getProperty name="edocs_encargado" property="formattedRut" />
				</font>
			</td>
			<td width="5%" height=18></td>
	</tr>
	<tr>
		<td width="730px" height="120px" colspan="4" align="center" valign="middle">			
				<span style="color:red;text-size:16px">
					Su propuesta esta siendo procesada. Revise el estado en la opción "Estado de procesamiento"<br />
				</span>
		</td>
	</tr>
	<tr><td  height="20px" align="center" colspan="4">
			<html:button styleId="button" styleClass="boton" property="close"
			value="Cerrar" onmouseover="botonover();" onmouseout="botondown();" onclick="window.close();">
			</html:button></td>
	</tr>
</table>