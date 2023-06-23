<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">

	<body>
		<div align="center">
			<div class="error">
				<bean:message key="message.cliente"/> <bean:message key="message.error"/><br/>
				<bean:message key="message.403"/>
			</div>
		</div>
		
		
		<!-- *********************** ERROR INFORMATION **************************** -->
		<!-- USER:  <%= request.getUserPrincipal() != null ? request.getUserPrincipal().getName() : null %> -->
		<!-- DATE:  <%= new java.util.Date() %> -->	
	</body>
	
</html>

