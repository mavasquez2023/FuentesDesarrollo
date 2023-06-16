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
	<p class="titulo">BIENVENIDO</p>
	<form action="init.do" name="form1" method="post">
	</form>
	<p>	
	
	<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF;">
	<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: left;">
			Desde el menú de Fonasa/Estado Licencias, podrá consultar el estado particular de un formulario Fonasa, el resultado será entregado por pantalla.
		</td>
		
	</tr>
	<tr><td style="border: 0 px;border-color: #FFFFFF;">&nbsp;</td></tr>
	<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: left;">
			Desde el menú de Fonasa/Estado Licencias Masiva, podrá subir un archivo CSV con todas los formularios a validar su estado en Fonasa, el resultado será entregado por correo.
		</td>
		
	</tr>
	
	<tr><td style="border: 0 px;border-color: #FFFFFF;">&nbsp;</td></tr>
	<tr>
		<td class="bienvenida" style="border: 0 px;border-color: #FFFFFF; text-align: center;">
			<img alt="" src="img/formlm.jpg">
		</td>
	</tr>
	</table>
	
 	</p>

	
	
	<script>
	$(document).ready(function(){
		$("#accordian h3").click(function(){
			//slide up all the link lists
			$("#accordian ul ul").slideUp();
			//slide down the link list below the h3 clicked - only if its closed
			if(!$(this).next().is(":visible"))
			{
				$(this).next().slideDown();
			}
		});
	});
	</script>
</body>
</html>