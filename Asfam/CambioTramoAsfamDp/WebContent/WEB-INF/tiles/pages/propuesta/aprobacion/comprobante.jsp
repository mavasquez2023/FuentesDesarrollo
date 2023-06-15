<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<table>

<jsp:useBean id="edocs_encargado" class="cl.araucana.ctasfam.presentation.struts.vo.Encargado" scope="session" />

<script>
	function printpage()
	{
	  window.print()
	}
</script>

<table>
	<tr>
		<td valign="top">
			<jsp:include   page="/WEB-INF/tiles/common/menu.jsp"></jsp:include>
		</td>
		
		<td width="730px">
			<br><br><br>
			
			<fieldset  style="border:3px solid black">
				<table border="0" align="center">
					<tr >
						<td style="text-decoration: underline;">Rut Empresa</td>
						<td>&nbsp;&nbsp;&nbsp;</td>
					  
						<td style="text-decoration: underline;">Razón Social</td>
						<td></td>
					</tr>
					<logic:iterate name="listEmpComp" id="empComp" indexId="index">
						<tr>
							<td>${empComp.rut}</td>
							<td></td>
							<td>${empComp.razonSocial}</td>
						</tr>
						<tr>
							<td></td>
							<td colspan="2">
								<table cellspacing="0" cellpadding="0" width="100%" border="0">
									<tbody>
										<tr>
											<TH align="left" width="50%"><font class=text>Nombre archivo</font></TH>
											<TH align="left" width="25%"><font class=text>Fecha envio</font></TH>
											<TH align="left" width="25%"><font class=text>Cant. informada</font></TH>
										</tr>
										<logic:iterate name="empComp" property="listProp" id="procBash" indexId="index2">
											<tr>
												<td width="50%">
													<font class="blueText"><jsp:getProperty name="procBash" property="rutaArchivo" /></font>
												</td>
												<td width="25%">
													<font class="blueText"><jsp:getProperty name="procBash" property="fechaSubida" /></font>
												</td>
												<td width="25%">
													<font class="blueText"><jsp:getProperty name="procBash" property="registrosInformados" /></font>
												</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</td>
						</tr>
					</logic:iterate>		
					
				</table>
			</fieldset>
			
			<br />
			
			<center>
				<span style="color:red;text-size:16px">
					Sus propuesta fueron cargadas exitosamente. En el siguiente enlace puede imprimir este comrpobante<br />
					<b style="text-decoration: underline;cursor:pointer;" id="linkComprobante" onclick="printpage()">
						Imprimir comprobante
					</b>
				</span>
			</center>
		</td>
	</tr>
</table>