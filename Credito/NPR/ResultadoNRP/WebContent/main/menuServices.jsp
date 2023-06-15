
<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>RESULTADO NRP</title>
</head>
<body>
<div id="accordian">
<h1 style="background-color:#0e60b2; color: white; font-family: Aharoni, Futura-Normal; font-size: 25px; height: 30px; padding-top: 10px; margin-bottom: -14px"><b class="titulo_menu">&nbsp;<a href="<%=request.getContextPath() %>/init.do" title="Actualizar menú">La Araucana</a></b></h1>
<!-- table style="width: 980px;border: 0 px;border-color: #FFFFFF;">
  <tr style="border: 0 px;border-color: #FFFFFF;">
    <td style="text-align: left;border: 0 px;border-color: #FFFFFF;"><img src="img/logo_reducido.jpg" /></td>
    <td style="vertical-align: top;border: 0 px;border-color: #FFFFFF;"><a href="index.jsp" title="Volver a Servicios en Línea"><i class="fas fa-home"></a></i></td>
  </tr>
</table-->


	<ul>
		
			<!--  li <c:if test="${menu=='consolidacion'}">class="active" </c:if>-->
			<li class="active">
				<h3>
					<span class="fas fa-angle-down"></span>Consolidación
				</h3>
				<ul>
					<li><a href="#" onclick="Redirect('confirm', 'consolidacion');"><span class="fas fa-angle-right"></span> Generar</a></li>
					<li><a href="#" onclick="Redirect('resultConsolidacion', 'registros');"><span class="fas fa-angle-right"></span> Estadísticas</a></li>
				</ul>
			</li>
			<!-- we will keep this LI open by default -->

			<li class="active">
				<h3>
					<span class="fas fa-angle-down"></span>Generación
				</h3>
				<ul>
					<li><a href="#" onclick="Redirect('confirm', 'generacion');"><span class="fas fa-angle-right"></span> Generar</a></li>
					<li><a href="#" onclick="Redirect('resultGeneracion', 'registros');"><span class="fas fa-angle-right"></span> Estadísticas</a></li>
				</ul>
			</li>

			<li class="active">
				<h3>
					<span class="fas fa-angle-down"></span>Disponibilización
				</h3>
				<ul>
					<li><a href="#" onclick="Redirect('confirm', 'disponibilizacion');"><span class="fas fa-angle-right"></span> Generar</a></li>
					<li><a href="#" onclick="Redirect('resultDisponibilizacion', 'registros');"><span class="fas fa-angle-right"></span> Estadísticas</a></li>
					
				</ul>
			</li>
			<li class="active">
				<h3>
					<span class="fas fa-angle-down"></span>Administración
				</h3>
				<ul>
					<li><a href="#" onclick="Redirect('correos', '');"><span class="fas fa-angle-right"></span> Correos</a>
					</li>
				</ul></li>

	</ul>
	
</div>
<form action="" name="form_menu" method="post">
	</form>
<script>
$(document).ready(
	function() {
				
		
	}); // fin del document ready
function Redirect(accion, proceso){
	//var numeromeses= ""; 
	//if(cantidad!=""){
	//	numeromeses= "&numeroMeses=" + cantidad;
	//}
	form_menu.action=accion + ".do?proceso=" + proceso;
	form_menu.submit();
}

</script>
</body>
</html>