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
					
<%--					<br><bean:write name="proceso" property="proceso"/> --%>
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

		</tbody>
	</table>
	<table cellspacing="0" cellpadding="0" width="100%" border="0">
		<tbody>
		<tr>
			<td width="90%" align="center">
				<font class=blueText5>
				<b>
				Estado de procesamiento de propuestas
				<br/>
				</b>
				<b>

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
									<th align="left" width="16%" bgcolor="#050BD1">
										<font class=text>Rut Empresa</font>
									</th>
									<TH align="left" width="50%">
										<font class=text>Nombre archivo</font>
									</TH>
									<TH align="left" width="25%">
										<font class=text>Fecha envio</font>
									</TH>
									<TH align="left" width="25%">
										<font class=text>Hora envio</font>
									</TH>
									<TH align="left" width="25%">
										<font class=text>Cant. informada</font>
									</TH>
									<TH align="left" width="25%">
										<font class=text>Estado propues.</font>
									</TH>
									<TH align="left" width="25%">
										<font class=text>Fecha proces.</font>
									</TH>
									<TH align="left" width="25%">
										<font class=text>Cant. proc</font>
									</TH>
								</tr>
							
							    <logic:iterate name="proceso_bash" id="procBash" indexId="index">														
<%--									<bean:define id="flag" type="java.lang.Integer" name="empresa" property="flag" />  --%>
									
<%-- 								<logic:greaterThan name="empresa" property="flag" value="0"> --%>
									<tr>
										<td width="16%">
											<font class="blueText">&nbsp;<jsp:getProperty name="procBash"  property="empresa" /></font>
										</td>
										<td width="50%">
											<font class="blueText"><jsp:getProperty name="procBash" property="rutaArchivo" /></font>
										</td>
										<td width="25%">
						                    <font class="blueText"><jsp:getProperty name="procBash" property="fechaSubida" /></font>
										</td>
										<td width="25%">
						                    <font class="blueText"><jsp:getProperty name="procBash" property="horaSubida" /></font>
										</td>
										<td width="25%">
						                    <font class="blueText"><jsp:getProperty name="procBash" property="registrosInformados" /></font>
										</td>
										<td width="20%">
						                    <font class="blueText"><jsp:getProperty name="procBash" property="estado" /></font>
										</td>
										<td width="25%">
						                    <font class="blueText"><jsp:getProperty name="procBash" property="fechaProcesamiento" /></font>
										</td>
										<td width="25%">
						                    <font class="blueText"><jsp:getProperty name="procBash" property="cantidadIntento" /></font>
										</td>
										
									</tr>
									
									
<%--									 </logic:greaterThan>  --%>
								
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
									
								</tr>
								<tr>

							
							</tr>	 
							</tbody>
						</table>
					</div>
				</td>
			</tr>
		 
					 

			
			<tr>
				<td align="center" style="color:red; font-size:14px">
					<span style="font-size:16px;"><b>Para visualizar el comprobante de las propuestas ya finalizadas haga click el siguiente enlace</b></span><br />
					<b><a href="verComprobante.do">Comprobante</a></b><br />
				</td>
			</tr>
		</tbody>
	</table>
</html:form>
</td></table>