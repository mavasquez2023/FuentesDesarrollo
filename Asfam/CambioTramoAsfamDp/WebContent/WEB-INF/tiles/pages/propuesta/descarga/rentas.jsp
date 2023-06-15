<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<% 
List lista=(List)request.getSession().getAttribute("lista");
 %>
 
<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />
<table><td valign="top">
<jsp:include  page="/WEB-INF/tiles/common/menu.jsp">
  
</jsp:include>
</td>
<td width="730px">
<html:form action="DescargaPropuestasZip">
	<html:hidden property="serviceName" />
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
					PASO 1: DESCARGA DE PROPUESTAS
					</b>
					</font>
				</td>
			</tr>
			<tr>
			<tr>
				<td width="100%" colspan="3">
					<br>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<div id="tabla" style="width: 730px;">
						<table cellspacing="0" cellpadding="0" width="100%" border="0">
							<tbody>
								<tr>
									<th width="16%" align="left" bgcolor="#050BD1">
										<font class=text>Rut Empresa</font>
									</th>
									<TH align="left" width="42%">
										<font class=text>Nombre Empresa</font>
									</TH>
									<tH align=middle width="38%">
									 
										<font class=text><input type="checkbox" name="TODOS_BONO" id="TODOS_BONO" onclick="checkedall();" ></font>
									</TH>
								</tr>									 
								<logic:iterate name="edocs_encargado" property="empresas" id="empresa" indexId="index"> 
									<bean:define id="flag" type="java.lang.Integer" name="empresa" property="flag" />
									<tr>
										<td width="16%">
											<font class="blueText">&nbsp;<jsp:getProperty name="empresa" property="formattedRut" /></font>										 
										</td>
										<td width="42%">
											<font class="blueText"><jsp:getProperty name="empresa" property="name" /></font>
										</td>
										<td width="38%">
											<logic:greaterThan name="empresa" property="flag" value="0">		
						                        <p align="center"><input type="checkbox" name="propuestas" value="<bean:write name="empresa" property="formattedRut"/>" onclick="descheckeabono();"></p>
						                   </logic:greaterThan>
						                   <logic:equal name="empresa" property="flag" value="0">
						                        <p align="center"><font class="blueText">- Sin Propuesta -</font></p>
						                   </logic:equal>
										</td>
									</tr>
								</logic:iterate>
								<html:hidden property="rutt" />
							</tbody>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td width="100%">
					<br>
					<p align="center">
						<input class="boton"  type="submit" onclick="return obtenercheckeddescarga();"
							value="Descargar Propuestas" name="aceptar"
							onMouseOver="this.className='botonOver'"
							onMouseOut="this.className='boton'">
						<input class="boton" onclick="goToUrl('/CambioTramoAsfam/HomePage.do'); return false;" type="button"
							value="Cancelar" name="cancelar"
							onMouseOver="this.className='botonOver'"
							onMouseOut="this.className='boton'">
					</p>
				</td>
			</tr>
			<tr>
				<td width="100%" colspan="3">
					<br>
					<br>
				</td>
			</tr>
			<tr>
				<td  width="100%" colspan=3 align=right >
					<font class=blackText>
						<a href="http://download.winzip.com/winzip90.exe">
							Download Winzip
						</a>
					</font>
				</td>
			</tr>
		</tbody>
	</table>
</html:form>
</td>
</table>