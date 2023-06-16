<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>SERVICIOS FONASA</title>
<script language="JavaScript 1.2" type="text/javascript">


</script>
</head>
<body>
<jsp:include page="banner.jsp" flush="true" />
<jsp:include page="menuServices.jsp" flush="true" />
	<p class="titulo">Validación desde Base de Datos</p>
	<form action="init.do" name="form1" method="post">
	</form>
	<p>	
	
	<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF;">
	<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: center;">
			Proceso se ha iniciado!!
		</td>
		
	</tr>
	<tr><td style="border: 0 px;border-color: #FFFFFF;">&nbsp;</td></tr>
	<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: center;">
			Resultado será notificado vía mail.
		</td>
		
	</tr>
	
	<tr><td style="border: 0 px;border-color: #FFFFFF;">&nbsp;</td></tr>
	
	</table>
	
 	</p>

</body>
</html>