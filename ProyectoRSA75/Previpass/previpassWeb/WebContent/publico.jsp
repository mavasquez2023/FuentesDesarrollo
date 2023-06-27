<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	javax.naming.Context init = new javax.naming.InitialContext();
	javax.naming.Context env = (javax.naming.Context)init.lookup("java:comp/env");
	String urlPortalPublico = (String) env.lookup("urlPortalPublico");
%>	

<html>
	<body>
		<script type="text/javascript">
			document.location="<%=urlPortalPublico%>";
		</script>

	</body>
</html>



