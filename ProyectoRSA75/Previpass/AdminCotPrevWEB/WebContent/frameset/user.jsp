<%@ include file="/html/comun/taglibs.jsp"; %>
<%@ page import="java.util.List"; %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/frameset/adminAraucana.css" />" rel="stylesheet" type="text/css">
</HEAD>

<body>
<form method="post" action="ibm_security_logout" name="logout" id="logoutForm">
	<input type="hidden" name="logout" value="Logout">
	<input type="hidden" name="logoutExitPage" value="/login/logout.jsp">
</form>
<table width="580" border="0" cellpadding="0" cellspacing="0" align="left">
	<tr>
		<td valign="top" align="left" width="45%"><div id="pathNav"></div></td>
		<td valign="top" align="right" class="tit-usuario" width="55%"><div style="font-size: 14px;font-weight: bolder;" id="periodo"></div><div id="user"></div><div style="font-size: 9px;font-weight:">(v.04082014)</div></td>
	</tr>
</table>
<script type="text/javascript">
	function doLogout() 
	{
		document.getElementById("logoutForm").submit();
		parent.BODY.location.reload();
		parent.titulo.location.reload();
		parent.menu.location.reload();
	}

	var div = '<%=request.getSession().getAttribute("periodo")%>';
	if (div != null && div && div != 'null')
	{
		document.getElementById("periodo").innerHTML = 'Periodo: ' + div;
		document.getElementById("user").innerHTML = 'Usuario, <%=request.getSession().getAttribute("usuarioActivo")%>';
	}

	<%   List lista = (List)request.getSession().getAttribute("listaPath");
		 if (lista != null)
		 {
		 	StringBuffer sb = new StringBuffer();
			for (int i = 0; i < lista.size() - 1; i++)
			{
			String opc = (String)lista.get(i);
			opc = opc.replaceAll("\n","");
				sb.append(opc);
				if (i < lista.size() - 2)
					sb.append("<span class=\"imprimir\">&nbsp;&gt;&nbsp;</span>");	
			} %>
			document.getElementById("pathNav").innerHTML = '<%= sb %>';
	<%	
			if (lista.size() >= 2)
				lista.remove(lista.size() - 2);
			request.getSession().setAttribute("listaPath", lista);
		 }
	%>
</script>
</body>
</HTML>
