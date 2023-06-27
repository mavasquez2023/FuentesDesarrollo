<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>

<%-- Error Messages --%>
<logic:messagesPresent property="<%= org.apache.struts.action.ActionMessages.GLOBAL_MESSAGE %>">
<table width="100%" height="36" border="0" cellpadding="0" cellspacing="0">
	<html:messages id="error" property="<%= org.apache.struts.action.ActionMessages.GLOBAL_MESSAGE %>">
	<tr>
    	<td width="36" align="center">&nbsp;</td>
		<td width="96%" class="mensaje">
            <c:out value="${error}" escapeXml="false"/>
		</td>
	</tr>
	</html:messages>
</table>
</logic:messagesPresent>



<%-- Success Messages --%>
<logic:messagesPresent message="true" property="<%= org.apache.struts.action.ActionMessages.GLOBAL_MESSAGE %>">
<table width="100%" height="36" border="0" cellpadding="0" cellspacing="0">
	<html:messages id="message" message="true" property="<%= org.apache.struts.action.ActionMessages.GLOBAL_MESSAGE %>">
	<tr>
    	<td width="36" align="center">&nbsp;</td>
		<td width="96%" class="mensaje">
            <c:out value="${message}" escapeXml="false"/>
		</td>
	</tr>
	</html:messages>
</table>
</logic:messagesPresent>
