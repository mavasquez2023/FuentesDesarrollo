<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<style type="text/css">
.noframe-framing-table {background-color: #767776; background-image: none; border-right: 1px solid #000000;	border-bottom: 1px solid #000000; border-top: 1px solid #000000; border-left: 1px solid #000000;}
.column-head { padding-left: .35em; font-family: Arial, Helvetica, sans-serif; font-size: 77.0%; font-weight: bold; text-align: left; color: #FFFFFF; background-color: #8691C7; background-image: none;}
.table-text { font-family: Arial, Helvetica, sans-serif; font-size: 70.0%; background-color: #F7F7F7; background-image: none;}
.login-button-section {padding-left: 0px; font-family: Arial, Helvetica, sans-serif; font-weight: normal; color: #000000; background-color: #CCCCCC; background-image: none;}
.buttonbold { font-family: Arial, Helvetica, sans-serif; font-size: 10px; }

/* FORMS */
FIELDSET {border: 0px}
INPUT {font-family: sans-serif; font-size: 95.0%}
FORM {margin-bottom: 0px; margin-top: 0px; padding-top: 0px; padding-bottom: 0px;}
</style>

<table width="160px" cellspacing="0" cellpadding="0" >
	<tbody>
		<tr>
			<td style="width: 5%"></td>
			
			<td style="width : 90%;">			
				
				<table cellpadding="0" cellspacing="0" border="0" width="164px">
					<tbody>
						<tr>
							<td colspan="3"><html:img page="/img/c.gif" alt="" style="width: 1px; height: 12px;" /></td>
						</tr>
						<tr>
							<td colspan="3" class="navleftgroup1" style="height: 10x;">Consultas</td>
						</tr>
						<tr>
							<td colspan="3" style="height: 4x; font-size: 3px;">&nbsp;</td>
						</tr>
						<tr>
							<td><html:img page="/img/c.gif" alt="" style="width: 10px; height: 10px;" /></td>
							<td style="width : 4px; color : #606060;; font-size : 9px;">&#149;</td>
							<td><html:link page="/ctaCteN1.do?idChanged=EMPRESA" styleClass="navleftelem"><bean:message key="navLeft.elem1" /></html:link></td>
						</tr>
						<tr>
							<td></td>
							<td style="width : 4px; color : #606060;; font-size : 9px;">&#149;</td>
							<td><html:link page="/liquidacionN1.do?idChanged=EMPRESA" styleClass="navleftelem"><bean:message key="navLeft.elem2" /></html:link></td>
						</tr>
						<tr>
							<td></td>
							<td style="width: 4px; color: #9a9a9a; font-size: 9px">&#149;</td>
							<td><html:link page="/deudaCreditoN1.do?idChanged=EMPRESA" styleClass="navleftelem"><bean:message key="navLeft.elem3" /></html:link></td>
						</tr>
						<!--
						<tr>
							<td></td>
							<td style="width: 4px; color: #9a9a9a; font-size: 9px">&#149;</td>
							<td><html:link page="/prepagoFiniquitoN1.do?idChanged=EMPRESA" styleClass="navleftelem">Prepago por Finiquito</html:link></td>
						</tr>		
						-->

						<tr>
							<td colspan="3" style="height: 4x; font-size: 3px;">&nbsp;</td>
						</tr>				
						<tr>
							<td></td>
							<td colspan="2"><html:img page="/img/horiz_gray.gif" alt="" style="border: 0; width: 142px; height: 10px" /></td>
						</tr>
						
						<tr>
							<td colspan="3" style="height: 4x; font-size: 3px;">&nbsp;</td>
						</tr>				
					</tbody>
				</table>
				
			</td>
			<td style="width : 5%;"></td>	
		</tr>
	</tbody>
</table>

<logic:present name="ea_user_profile" scope="session">
<table bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="175">
	<% cl.araucana.common.Profile profile = (cl.araucana.common.UserProfile) session.getAttribute("ea_user_profile");%>
	<% String rol = (String) session.getAttribute("rol");%>
	<% java.util.Collection empresas = (java.util.Collection) profile.getAttribute("empresas");%>
	<% if(empresas.size() > 1 || rol == "Ejecutivo") {%>
	<tr>
		<td style="text-align: center;">
			<input type="button" class="buttonbold" name="logout" value="Elegir otra empresa" onclick="location.href='<%=request.getContextPath()%>/proxy.do?empresaElegida=&status=r'" />&nbsp;
			<!--
			<html:link page="/proxy.do?empresaElegida=&status=r" styleClass="navleftelem">Elegir otra empresa</html:link>
			-->
		</td>
	</tr>
	</logic:greaterThan>
	<% } %>
</table>
</logic:present>
