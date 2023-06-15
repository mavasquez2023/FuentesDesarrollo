<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="cl.araucana.ctasfam.business.to.EstadoTO;" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
 
<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />
 <table>
 <td valign="top">
 <jsp:include   page="/WEB-INF/tiles/common/menu.jsp">
  
 </jsp:include> 
 </td><td width="730px">

<html:form action="/AceptaPropuesta">
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
				PASO 2: ACEPTAR PROPUESTAS
				<br/>
				</b>
				</font>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<br>
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
										<font class=text>
										<input type="checkbox" name="TODOS_BONO" id="TODOS_BONO" onclick="checkedall();"> </font>
									</TH>
								</tr>
							
									 
								<logic:iterate name="edocs_encargado" property="empresas" id="empresa" indexId="index"> 
									<bean:define id="flag" type="java.lang.Integer" name="empresa" property="flag" />
								<logic:greaterThan name="empresa" property="flag" value="0">
									<tr>
										<td width="16%">
											<font class="blueText">&nbsp;<jsp:getProperty name="empresa"  property="formattedRut" /></font>
										 
										</td>
										<td width="42%">
											<font class="blueText"><jsp:getProperty name="empresa" property="name" /></font>
										</td>
										<td width="38%">
											
										
						                        <p align="center"><input type="checkbox" name="propuesta" value="<bean:write name="empresa" property="formattedRut"/>" onclick="descheckeabono();"></p>
						                  
						                   
										</td>
										
									</tr>
									 </logic:greaterThan>
								</logic:iterate>
									<html:hidden property="rutt" />
									 
							</tbody>
						</table>
					</div>
					<table><th height="30px"></th></table>
					<div id="tabla" style="width: 730px;">
						<table cellspacing="0" cellpadding="0" width="100%" border="0">
							<tbody>
								<tr>
									<th width="16%" align="center" bgcolor="#050BD1">
										<font class=text>Declaraci&oacute;n sobre Propuestas generadas por la Araucana</font>
									</TH>
								</tr>
								<tr>
								<th width="16%" align="left" bgcolor="#050BD1">
			&nbsp;&nbsp;&nbsp;<input type="radio" id="opcion" name="opcion" value="1" onclick="habilitar();">&nbsp;&nbsp;<font class=text>ACEPTAR PROPUESTA</font>
								<br>
			&nbsp;&nbsp;&nbsp;<input type="radio" id="opcion" name="opcion" value="0" onclick="modificar();" >&nbsp;&nbsp;<font class=text>MODIFICAR PROPUESTA</font>
							<input type="hidden" name="estado"> 
								<input type="hidden" name="ruts"> 
							</th>
							</tr>	
							 
							</tbody>
						</table>
					</div>
				</td>
			</tr>
		 
					 
			<tr>
		 
				<td width="100%">
					<br>
					<p align="center">
						<input class="boton"  type="submit" onclick="return obtenerchecked();"
							value="Aceptar" name="aceptar"
							onMouseOver="this.className='botonOver'"
							onMouseOut="this.className='boton'">
						<input class="boton" onclick="goToUrl('/CambioTramoAsfam/HomePage.do'); return false;" type="button"
							value="Cancelar" name="cancelar"
							onMouseOver="this.className='botonOver'"
							onMouseOut="this.className='boton'">
					</p>
				</td>
			</tr>
		</tbody>
	</table>
</html:form>
</td></table>