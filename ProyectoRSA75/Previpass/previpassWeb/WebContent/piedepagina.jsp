<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	javax.naming.Context init = new javax.naming.InitialContext();
	javax.naming.Context env = (javax.naming.Context)init.lookup("java:comp/env");
	String urlPortalPublico = (String) env.lookup("urlPortalPublico");
%>	
<div class="grid_4">
    <div id="footer">
    	<div class="grid_1 alpha">
    	  <div class="txt_foot">Una Innovación tecnológica<br />
        de la Caja de Compensación</div>
    	</div>
        <div class="grid_3 omega"><a href="http://www.laaraucana.cl" target="_blank"><img src="img/araucana_footer.jpg" alt="La Araucana" width="283" height="47" vspace="5" border="0" /></a></div>
    </div>
</div>
		