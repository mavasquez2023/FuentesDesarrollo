<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>

	<c:if test="${param.errorLoginFlag != null && param.errorLoginFlag == 'on'}">
		<center>
		<div  align="center" class="error" id="loginErrorBox" style="display:none;width: 70%;" >
			<b><bean:message key="message.error"/><br/></b>
			<bean:message key='message.login.error'/><br/>
		</div>
		</center>
		
		<script language="javascript">
			$(document).ready(function(){
				 desplegar( "loginErrorBox" );
				}
			);
		</script>
	</c:if>
						
	<form name="login" method="post" action="j_security_check">
		<table width="100%" class="table_transparente">
			<tr>
				<td width="20%">&nbsp;</td>
				<td width="60%">
				    <table width="100%">
				    	<tr>
							<th colspan="2" nowrap="nowrap"><bean:message key="seccion.login"/></th>
						</tr>
						<tr> 
							<td width="40%"><strong><bean:message key="label.user"/>:</strong></td>
							<td nowrap="nowrap">
								<input type="text" name="j_username" maxlength="20" size="15"/>
								<a class="addToolTip" title='<bean:message key="help.login.descripcion"/>'><html:img page='/images${internetPath}/help.jpg' /></a>
							</td>
						</tr>
						<tr> 
							<td width="40%"><strong><bean:message key="label.password"/>:</strong></td>
							<td><input type="password" name="j_password" maxlength="20" size="15"/></td>
						</tr>
						
						<tr> 
							<td colspan="2" class="botonera"><input type="submit" class="button" value="<bean:message key='button.aceptar'/>"/></td>
						</tr>
					</table>
				</td>
				<td width="20%">&nbsp;</td>
			</tr>
		</table>

	</form>

