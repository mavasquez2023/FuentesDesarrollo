<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<jsp:useBean id="edocs_encargado"
	class="cl.araucana.ctasfam.presentation.struts.vo.Encargado"
	scope="session" />

	<!-- <h1><bean:message key="periodo.ejecucion" /> -->
	<table>
	<td valign="top">
	<jsp:include   page="/WEB-INF/tiles/common/menu.jsp">
	
	</jsp:include>
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
					Encargado de Empresa: <br> <jsp:getProperty name="edocs_encargado" property="fullName" />
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
				<logic:notEqual value="zip" name="case" property="tipo">
					<logic:notEqual value="1" name="case" property="valor1">
						<table width="100%" align="center" border="0">
							<tr style="background-color: #D7D7D7">
								<td align="left">
								<font style="margin-left: 50px;">	El Archivo <bean:write name="case" property="nombre" /> se encuentra validado, continuar con el siguiente paso para continuar con el proceso.</font>
								</td>
							</tr>
						</table>
					</logic:notEqual>
				</logic:notEqual>
				<logic:equal value="2" name="case" property="valor2">
					<logic:notEmpty name="listafiles">
						<table width="100%" align="center" border="0">
							<logic:iterate id="var" name="listafiles">
								<tr style="background-color:#D7D7D7">
									<td align="left">
										<font style="margin-left: 50px;">El Archivo <bean:write name="var" property="nombre"/> se encuentra validado, continuar con el siguiente paso para continuar con el proceso.</font>
									</td>
								</tr>
							</logic:iterate>
						</table>
					</logic:notEmpty>
				</logic:equal>
				<logic:notEmpty name="lista">
					<table align="center">
						<tr>
							<td height="10px"></td>
						</tr>
						<tr>
							<td><span class="blueText"><b>Errores Reportados</b></span></td>
						</tr>
					</table>
				</logic:notEmpty>
				<logic:notEmpty name="lista">
					<table border="0" align="center" width="100%">
						<logic:notEqual value="zip" name="case" property="tipo">
							<logic:equal value="1" name="case" property="valor1">
								<tr>
									<td colspan="3" align="left">
										<span class="blueText">
											<b>
												Se han reportado los siguientes errores en el archivo <bean:write name="case" property="nombre" />.
											</b>
										</span>
									</td>
								</tr>
								<tr style="background-color: #C0C0C0">
									<td>Fila</td>
									<td>Columna(s)</td>
									<td>Rut Trabajador</td>
									<td>Descripci&oacute;n</td>
								</tr>
							</logic:equal>
						</logic:notEqual>
						<logic:iterate id="id" name="lista">
							<logic:equal value="0" name="id" property="par">
								<tr>
									<td colspan="3" align="left">
										<span class="blueText">
											<b>
												Se han reportado los siguientes errores en el archivo <bean:write name="id" property="nombrearchivo" />.
											</b>
										</span>
									</td>
								</tr>
								<tr style="background-color: #C0C0C0">
									<td>Fila</td>
									<td>Columna(s)</td>
									<td>Rut Trabajador</td>
									<td>Descripci&oacute;n</td>
								</tr>
							</logic:equal>
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
				<logic:notEqual value="zip" name="case" property="tipo">
					<logic:notEqual value="1" name="case" property="valor1">
						<table width="100%">
						
							<tr><td  height="20px"></td></tr>
						
							<tr>
								<td  height="20px" align="center" style="color:red; font-size:14px">
									<b>
									Una vez enviado el archivo, aun le falta un paso (Paso 4 aceptar declaración jurada)<br />
									Presione el enlace "Continuar".
									</b>
								</td>
							</tr>
							
							<tr><td  height="20px"></td></tr>
							
							<tr>
							<td align="right">
					 
							 <html:link action="HomePage.do">Cancelar</html:link>&nbsp;&nbsp;&nbsp;&nbsp;
							 <html:link action="/preparedDeclaracion.do">Continuar</html:link>				
											
					 						
							 </td>
							</tr>
						</table>
					</logic:notEqual> 
				</logic:notEqual>
				
				<logic:equal value="2" name="case" property="valor2">
					<logic:notEmpty name="listafiles">
						<table width="100%">
							<tr>
							<td  height="20px"></td>
							</tr><tr>
							<td align="right">
							
							 <html:link action="HomePage.do">Cancelar</html:link>&nbsp;&nbsp;&nbsp;&nbsp;
							 <html:link action="/preparedDeclaracion.do">Continuar</html:link>				
											
					 						
							 </td>
							</tr>
						</table>
					</logic:notEmpty>
				</logic:equal>
</td>
</table>
