<%@page import="cl.araucana.core.registry.User"%>

<div class="top">
	<a href="/BotonPago/web/welcome.do"><img align="left" title="p�gina de inicio"
		alt="home" src="<%=request.getContextPath()%>/img/home.png">
	</a>
	<p>
	<b><%=(String) session.getAttribute("nombreUsuario")%></b>&nbsp;
	
	<a href="<%=request.getContextPath()%>/web/logout.do"> Cerrar Sesi�n</a>
	</p>
</div>
