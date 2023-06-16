<%@page import="cl.araucana.core.registry.User"%>

<div class="top">
	<a href="/BotonPago/web/welcome.do"><img align="left" title="página de inicio"
		alt="home" src="<%=request.getContextPath()%>/img/home.png">
	</a>
	<p>
	<b><%=(String) session.getAttribute("nombreUsuario")%></b>&nbsp;
	
	<a href="<%=request.getContextPath()%>/web/logout.do"> Cerrar Sesión</a>
	</p>
</div>
