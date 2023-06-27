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

<table width="100%" cellspacing="0" cellpadding="0" style="vertical-align: top;">
	<tbody>
		<tr>
			<td style="width: 5%"></td>			
			<td style="width : 90%;">				
				<table cellpadding="0" cellspacing="0" border="0" style="vertical-align: top;">
					<tbody>
						<tr>
							<td colspan="3"><html:img page="/img/c.gif" alt="" style="width: 1px; height: 12px;" /></td>
						</tr>
						<tr>
							<td colspan="3" class="navleftgroup1" style="height: 10x;"></td>
						</tr>
						<tr>
							<td colspan="3" style="height: 4x; font-size: 3px;">&nbsp;</td>
						</tr>

						<tr>
							<td></td>
							<td style="width : 4px; color : #606060;; font-size : 9px;">&#149;</td>
							<td><html:link href="http://www.cp.cl" styleClass="navleftelem"><bean:message key="navLeft1.elem1" /></html:link></td>
						</tr>
						<tr>
							<td></td>
							<td style="width : 4px; color : #606060;; font-size : 9px;">&#149;</td>
							<td><html:link page="/proxy.do" styleClass="navleftelem"><bean:message key="navLeft1.elem2" /></html:link></td>
						</tr>
						<tr>
							<td></td>
							<td style="width : 4px; color : #606060;; font-size : 9px;">&#149;</td>
							<td><html:link page="/uc.jsp" styleClass="navleftelem"><bean:message key="navLeft1.elem3" /></html:link></td>
						</tr>
						<tr>
							<td></td>
							<td style="width : 4px; color : #606060;; font-size : 9px;">&#149;</td>
							<td><html:link page="/uc.jsp" styleClass="navleftelem"><bean:message key="navLeft1.elem4" /></html:link></td>
						</tr>
						<tr>
							<td></td>
							<td style="width : 4px; color : #606060;; font-size : 9px;">&#149;</td>
							<td><html:link href="http://www.laaraucana.cl/?seccion=159&nom=" styleClass="navleftelem"><bean:message key="navLeft1.elem5" /></html:link></td>
						</tr>
						
						<tr>
							<td></td>
							<td colspan="2"><html:img page="/img/horiz_gray.gif" alt="" style="border: 0; width: 142px; height: 10px" /></td>
						</tr>
					</tbody>
				</table>				
			</td>
			<td style="width : 5%;"></td>	
		</tr>
	</tbody>
</table>

<logic:notPresent name="ea_user_profile" scope="session">
<table width="175" border="0" align="left" cellpadding="1" cellspacing="1">
	<tr>
		<td>
			<form action="j_security_check" method="post">
			<table border=0 cellpadding=0 cellspacing=0 width="95%" height="100%" align="center">
				<tr>
					<td class="login">
					<table class="noframe-framing-table" border=0 cellpadding="5" cellspacing="0" width="95%" summary="Login Table">
						<tbody>
							<tr>
								<th colspan="2" class="column-head" scope="rowgroup">Ingresar&nbsp;&nbsp;</th>
							</tr>
							<tr>
								<td valign=top width="33%" class="table-text" nowrap>
									<label for="name">Id:</label>
								</td>
								<td valign=top class="table-text">
									<input type="text" name="j_username" class="short" id="name" size="14">
								</td>
							</tr>
							<tr>
								<td valign=top width="33%" class="table-text" nowrap>
									<label for="pswd">Clave:</label>
								</td>
								<td valign=top class="table-text">
									<input type="password" name="j_password" class="short" id="pswd" size="14">
								</td>
							</tr>
							<tr>
								<td valign=top colspan="2" class="login-button-section" nowrap align="center">
									<input type="submit" class="buttonbold" name="baction" class="buttons" value='Aceptar'>
								</td>
							</tr>
						</tbody>
					</table>
				</tr>
			</table>			
			</form>
		</td>
	</tr>
</table>
</logic:notPresent>

<logic:present name="ea_user_profile" scope="session">
<table bgcolor="#ffffff" border="0" cellpadding="0" cellspacing="0" width="175">
	<tr>
		<td colspan="2" style="text-align: center;">
			<input type="button" class="buttonbold" name="logout" value="Salir de Sessión" onclick="location.href='<%=request.getContextPath()%>/ibm_security_logout?logoutExitPage=/'" />&nbsp;
		</td>
	</tr>
</table>
</logic:present>