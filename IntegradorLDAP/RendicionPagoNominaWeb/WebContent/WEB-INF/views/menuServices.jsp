<%@ include file="./comun/headerJsp.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="accordian" style="width: 230px">
	<h1
		style="color: white; font-family: Aharoni, Futura-Normal; font-size: 25px">
		<b>TEF&nbsp;-&nbsp;La Araucana</b>
	</h1>
	<!-- table style="width: 980px;border: 0 px;border-color: #FFFFFF;">
  <tr style="border: 0 px;border-color: #FFFFFF;">
    <td style="text-align: left;border: 0 px;border-color: #FFFFFF;"><img src="img/logo_reducido.jpg" /></td>
    <td style="vertical-align: top;border: 0 px;border-color: #FFFFFF;"><a href="index.jsp" title="Inicio"><i class="fas fa-home"></a></i></td>
  </tr>
</table-->


	<ul>

		<li class="active">
			<h3>
				<span class="fas fa-angle-down"></span>Transferencias
			</h3>
			<ul>
				<li><a href="<c:url value='/envio.do?status=1'/>" title="Seguimiento Envío">Seguimiento Envío</a></li>
				<c:if test='${rol=="operador" ||  rol=="admin" }'>
					<li><a href="<c:url value='/rendicion.do'/>" title="Seguimiento Rendición">Seguimiento Rendición</a></li>
				</c:if>
				<li><a href="<c:url value='/consulta.do'/>" title="Consulta de Nóminas">Consulta de Nómina</a></li>
				<li><a href="<c:url value='/seguimientoAfiliado.do'/>" title="Búsqueda de Afiliado">Búsqueda de Afiliado</a></li>
				<c:if test='${rol=="admin" }'>
					<li><a href="<c:url value='/autorizar.do'/>" title="Autorizar Usuarios">Autorizar Usuarios</a></li>
				</c:if>
 
			</ul>
		</li>
		
		<li class="active">
			<h3>
				<span class="fas fa-angle-down"></span>Pagos Manuales
			</h3>
			<ul>
				<c:if test='${rol=="cargaManual" || rol=="admin"}'>
					<li><a href="<c:url value='/cargamanual.do'/>" title="Cargar Pagos Manuales">Cargar Pagos</a></li>
				</c:if>
				<c:if test='${rol=="operador" || rol=="admin"}'>
					<li><a href="<c:url value='/seguimientoManual.do'/>" title="Seguimiento Pagos Manuales">Seguimiento</a></li>
 				</c:if>
			</ul>
		</li>

	</ul>
</div>
<div id="loading" style="position:absolute; top:41%; left:47%; display:none; z-index: auto" >
		<img src="<%=request.getContextPath() %>/img/3d-loader.gif">
</div>
<script>
	$(document).ready(function() {
		//comienda el show
		$(window).bind('beforeunload', function(){
			$('#loading').show();
		});    	
	});
	
</script>