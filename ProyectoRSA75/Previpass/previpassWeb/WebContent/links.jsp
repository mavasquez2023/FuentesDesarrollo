<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	javax.naming.Context init = new javax.naming.InitialContext();
	javax.naming.Context env = (javax.naming.Context)init.lookup("java:comp/env");
	String urlPortalPublico = (String) env.lookup("urlPortalPublico");
%>	
<div style="margin-top:100px; margin-right:10px;" align="right">
<img src="img/ico_home.gif" width="11" height="12" hspace="3" /><a href="../portal"><font color="#FFFFFF">Home</font></a>
<c:if test="${perfil.pagoCotizaciones}">
    <img src="img/ico_clave.gif" width="11" height="12" hspace="3" /><a href="/CotizacionPrevisionalWeb/PrepareChangePassword.do" target="contenido"><font color="#FFFFFF">Cambiar Clave</font></a>
</c:if>
<img src="img/ico_contacto.gif" width="11" height="12" hspace="3" /><a href="${perfil.mailContactenos}"><font color="#FFFFFF">Cont&aacute;ctenos</font></a>
<img src="img/ico_cerrar.gif" width="11" height="12" hspace="3" /><a href="javascript:document.getElementById('logoutForm').submit();"><font color="#FFFFFF">Cerrar Sesi&oacute;n</font></a>
</div>
<form method="post" action="ibm_security_logout" name="logout" id="logoutForm">
<input type="hidden" name="logout" value="Logout">
<input type="hidden" name="logoutExitPage" value="/publico.jsp">
</form>
		