<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	javax.naming.Context init = new javax.naming.InitialContext();
	javax.naming.Context env = (javax.naming.Context)init.lookup("java:comp/env");
	String urlPortalPublico = (String) env.lookup("urlPortalPublico");
%>	

<html>
	<body onload="javascript:document.getElementById('logoutForm').submit();">
		<form method="post" action="ibm_security_logout" name="logout" id="logoutForm" target="_top">
			<input type="hidden" name="logout" value="Logout">
			<input type="hidden" name="logoutExitPage" value="<%=urlPortalPublico%>">
</form>
</body>
</html>
