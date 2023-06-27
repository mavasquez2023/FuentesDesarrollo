<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>

<center>
	<table width="90%" border="0" cellpadding="0" cellspacing="0" class="table_transparente">
			<tr> 
		        <td align="left" valign="top">
		        
					<div class="error">
						<!--  caso normal -->
						<bean:message key="message.cliente"/><br/><bean:message key="message.error"/><br/><br/>
						
						<logic:present name="error.code">
							<bean:message key='<%= (String)request.getAttribute("error.code") %>'/>
							<logic:present name="error.information">
							<br/><i><bean:write name="error.information"/></i></logic:present>
						</logic:present>
						
						<!--  crítico -->
						<logic:notPresent name="error.code">
							<bean:message key="exceptions.critical"/>
						</logic:notPresent>
					</div>
		
			</td>
		</tr>
	</table>
</center>

<!-- *********************** ERROR INFORMATION **************************** -->
<!-- USER:  <%= request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : null %> -->
<!-- DATE:  <%= new java.util.Date() %> -->
<%
		java.util.Enumeration lista = request.getAttributeNames();
		while ( lista.hasMoreElements() ) {
			String key = (String) lista.nextElement();
			if ( key.startsWith("error.") ) { %>
				<!-- <%= key %>:  <%= request.getAttribute( key ) + "\n" %> -->
<%			}
		}
%>


