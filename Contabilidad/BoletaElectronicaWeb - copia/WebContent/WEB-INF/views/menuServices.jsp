<%@ include file="./comun/headerJsp.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="accordian">
	<h1
		style="color: white; font-family: Aharoni, Futura-Normal; font-size: 25px">
		<b>&nbsp;La Araucana</b>
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
				<span class="fas fa-angle-down"></span>Boleta electrónica
			</h3>
			<ul>
				<li><a href="<c:url value='/init.do'/>"
					title="Boletas Pendientes">Boletas Pendientes</a></li>

				<li><a href="<c:url value='/emitidas.do'/>"
					title="Boletas Emitidas">Boletas Emitidas</a></li>
				<c:if test="${rut =='70016160-9'}">
					<li><a href="<c:url value='/parametros.do'/>"
						title="Mantenedor parámetros">Mantenedor Parámetros</a></li>
				</c:if>


			</ul>
		</li>


	</ul>
</div>

