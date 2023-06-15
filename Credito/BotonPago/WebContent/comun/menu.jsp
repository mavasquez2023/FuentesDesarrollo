
<%@ include file="/comun/tld.jsp"%>
<b class="textobold">OPCIONES</b>
<br>
<br>
<c:forEach var="item" items="${permisos}">
	<c:if test="${item eq 'simulaDeudor'}">
		<p class="texto">
			<b>Rut cargado:</b>&nbsp; ${rutDeudor}
		</p>
		<form action="<c:out value="${pageContext.request.contextPath}"/>/web/cargaRut.do" method="post" id="cargaRutForm">
			<label>Simular deudor</label><br>
			<input class="inputMenu formatoRut" name="rut" maxlength="13" type="text"> <input class="boton" name="cargaRut" type="submit"
				value="ok">
		</form>
		<div id="mensajesError"></div>
		<br>
	</c:if>
	<c:if test="${item eq 'gestionPago'}">
		<input class="botonmenu" name="gestionPago" type="button" value="Gestión de Pago"
			onclick="window.location.href='<c:out value="${pageContext.request.contextPath}"/>/web/listaCreditos.do'">
	</c:if>
	<c:if test="${item eq 'historialPagos'}">
		<input class="botonmenu" type="button" value="Historial de Pagos" 
		onclick="window.location.href='<c:out value="${pageContext.request.contextPath}"/>/web/historialDePago.do'">
	</c:if>
	<c:if test="${item eq 'ingresarUsuario'}">
		<input class="botonmenu" type="button" value="Crear Usuario" 
		onclick="window.location.href='<c:out value="${pageContext.request.contextPath}"/>/web/nuevoUsuario.do'">
	</c:if>
	<c:if test="${item eq 'asignaRol'}">
		<input class="botonmenu" type="button" value="Modificar Permisos"
			onclick="window.location.href='<c:out value="${pageContext.request.contextPath}"/>/web/otorgaPermiso.do?op=ini'">
	</c:if>
	<c:if test="${item eq 'sinat03'}">
		<input class="botonmenu" type="button" value="Condonación Gravámenes" 
		onclick="window.location.href='<c:out value="${pageContext.request.contextPath}"/>/web/mantenedorSinat03.do'">
	</c:if>
	<c:if test="${item eq 'sinat04'}">
		<input class="botonmenu" type="button" value="Condonación Gastos de Cobranza" 
		onclick="window.location.href='<c:out value="${pageContext.request.contextPath}"/>/web/mantenedorSinat04.do'">
	</c:if>
	<c:if test="${item eq 'recuperar'}">
		<input class="botonmenu" type="button" value="Recuperar SAP" 
		onclick="window.location.href='<c:out value="${pageContext.request.contextPath}"/>/web/regularizaPago.do?op=ini'">
	</c:if>
	<c:if test="${item eq 'pagoAnticipado'}">
		<input class="botonmenu" type="button" value="Pago Anticipado" 
		onclick="window.location.href='<c:out value="${pageContext.request.contextPath}"/>/web/pagoAnticipado.do?op=ini'">
	</c:if>
	<c:if test="${item eq 'cartolaCredito'}">
		<input class="botonmenu" type="button" value="Cartola de Créditos"
			onclick="window.location.href='<c:out value="${pageContext.request.contextPath}"/>/web/redireccionaSatelites.do'">
	</c:if>
	<c:if test="${item eq 'parametrosSistema'}">
		<input class="botonmenu" type="button" value="Parametros Sistema"
			onclick="window.location.href='8'">
	</c:if>
</c:forEach>
<br>
<br>
<br>
<br>

<script type="text/javascript">
	$(document).ready(function() {
		$("#cargaRutForm").validate({
			rules : {
				rut : {
					required : true,
					rut : true
				}
			},
			errorPlacement : function(error, element) {
				$("#mensajesError").empty();
				error.appendTo("#mensajesError");
			},
			messages : {
				"rut" : {
					required : "* Rut requerido."
				}
			}
		}); //fin jquery validate

		$(".formatoRut").Rut({
			format_on : 'keyup'
		});
		$(".formatoRut").Rut({
			format_on : 'load'
		});
	}); //fin document ready
</script>


