<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="fecha_tramo" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />

	<!-- <h1><bean:message key="periodo.ejecucion" /> -->
	<table>
	<td valign="top">
	
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
			<td align="left" width="70%" height="18">
				<font class="blackText">
					Rut Encargado: <jsp:getProperty name="edocs_encargado" property="formattedRut" />
				</font>
			</td>
			<td width="5%" height=18></td>
		</tr>
	</tbody>
</table>
<table cellspacing="0" cellpadding="0" width="100%" border="0">
	 
		<tr>
			<td width="100%" colspan="3"><br>
			<br>
			</td>
		</tr>
		<tr>
			<td width="100%" valign="top" align="center">
				<logic:empty name="lista">
				
						<table width="100%" align="center" border="0">
							<tr style="background-color: #D7D7D7">
								<td align="center">
								<font style="margin-left: 50px;">	El Archivo <bean:write name="case" property="nombre" /> se encuentra validado. <br/><br/>Continuar con el siguiente paso para avanzar en el proceso.</font>
								</td>
							</tr>
						</table>

			</logic:empty>	
				<logic:notEmpty name="lista">
					<table align="center">
						<tr>
							<td height="10px"></td>
						</tr>
						<tr>
							<td align="left"><span class="blueText"><b>Se han encontrado los siguientes errores en el archivo:</b></span></td>
						</tr>
					</table>
				</logic:notEmpty>
				<logic:notEmpty name="lista">
					<table border="0" align="center" width="100%">
						
								
								<tr style="background-color: #C0C0C0">
									<td>Fila</td>
									<td>Columna(s)</td>
									<td>Rut Trabajador</td>
									<td>Descripci&oacute;n</td>
								</tr>

						<logic:iterate id="id" name="lista">
						
							<tr style="background-color: #D7D7D7">
							
								<td align="center">
								<bean:write name="id" property="numerolinea"></bean:write></td>
							    <td><bean:write name="id" property="numeroColumna"></bean:write></td>
								<td><bean:write name="id" property="ruttrabajador"></bean:write></td>
								<td><bean:write name="id" property="descripcionerror"></bean:write></td>
							</tr>

						</logic:iterate>
					</table>	
				</logic:notEmpty>
				
				<logic:notEmpty name="lista">

						<table width="100%">
						
							<tr><td  height="20px"></td></tr>
						
							<tr>
								<td  height="20px" align="center" style="color:red; font-size:14px">
									<b>
									Corrija los errores y vuelva a enviar el archivo<br />
									Presione el botón "Cerrar" para volver al Envío.
									</b>
								</td>
							</tr>
							<tr><td height="20px">&nbsp;</td></tr>
							<tr><td  height="20px" align="center">
										<html:button styleId="button" styleClass="boton" property="close"
											value="Cerrar" onmouseover="botonover();" onmouseout="botondown();" onclick="window.close();">
										</html:button></td></tr>
						</table>
				</logic:notEmpty>

				<logic:empty name="lista">
						<table width="100%">
							<tr>
							<td align="right">
							 <table width="100%">
							 <tr><td  height="20px" align="center" >
							<html:button styleId="button" styleClass="boton" property="close" 
							value="Cancelar" onmouseover="botonover();" onmouseout="botondown();" onclick="window.close();">
							</html:button></td>
							<td  height="20px" align="center" >
							<html:button styleId="button" styleClass="boton" property="close"
							value="Continuar" onmouseover="botonover();" onmouseout="botondown();" onclick="goToUrl('/CambioTramoAsfam/preparedDeclaracion.do');">
							</html:button></td>
							</tr>
							 
							 </table>
													
							 </td>
							</tr>
						</table>
				</logic:empty>
			</td>
		</tr>
</table>
</table>
