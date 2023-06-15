
<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />

<title>RESULTADO NRP</title>
<script language="JavaScript 1.2" type="text/javascript">


function EjecutarProceso(proceso){	
	form1.action=proceso+".do";
	form1.submit();
}
function Volver(){
	form1.action="init.do";
	form1.submit();
}


</script>
</head>
<body>
<script>
	var path= "<%=request.getContextPath()%>";
		$(document).ready(function() {
			$(".informacion").corner("6px");
		 });

	</script>
<jsp:include page="banner.jsp" flush="true" />
<jsp:include page="menuServices.jsp" flush="true" />
	<p class="titulo">CONFIRMAR PROCESO</p>
	<form action="" name="form1" method="post">
	
	<p>	
					
	<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF;">
	<tr>
		<td style="border: 0 px;border-color: #FFFFFF;">
  				<p>Estás seguro <strong>ejecutar</strong> el proceso de estadísticas <b style="text-transform: uppercase;">${proceso}</b>?</p>

  			
			<input class="boton" id="aceptar" type="button" value="Aceptar" onclick="EjecutarProceso('${proceso}');"/>&nbsp;&nbsp;&nbsp;
			<input class="boton" id="cancelar" type="button" value="Cancelar" onclick="Volver();"/>
		</td>
		<td width="50" style="border: 0 px;border-color: #FFFFFF;">
			&nbsp;
		</td>

		
	</tr>
	</table>
	
	</p>
	<p>
		<br>
		<div id="informacion" class="informacion"></div>
		<input type="hidden" name="proceso" value="${proceso }" />
	</p>
	
	</form>
	
	<script type="text/javascript">
		$(document).ready(
				function() {
					$("#accordian h3").click(function(){
						//slide up all the link lists
						$("#accordian ul ul").slideUp();
						//slide down the link list below the h3 clicked - only if its closed
						if(!$(this).next().is(":visible")){
							$(this).next().slideDown();
						}
					});
					 

					
				}); // fin del document ready
				
	</script>
</body>
</html>