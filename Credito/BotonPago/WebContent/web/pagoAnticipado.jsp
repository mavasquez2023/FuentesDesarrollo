<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/comun/tld.jsp"%>
<html>
<head>
<title>LA ARAUCANA C.C.A.F.</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include flush="true" page="/comun/inports.jsp"></jsp:include>
<link type="text/css" href="<%=request.getContextPath()%>/css/mantenedor.css" rel="stylesheet" />
<script language="JavaScript 1.2" type="text/javascript">
function init(){
	<c:if test="${error==-1}">
			mostrarWarning("Error en parámetros de ingreso, intente nuevamente");
	</c:if>
	<c:if test="${error==-2}">
			mostrarWarning("No se ha podido ingresar fecha pago anticipado, intente nuevamente ");
	</c:if>
	<c:if test="${error==-3}">
			mostrarWarning("Error del sistema, intente nuevamente");
	</c:if>
	<c:if test="${error==0}">
			mostrarInfo("Fecha pago anticipado insertado exitosamente!");
	</c:if>
}
</script>
</head>
<body onload="init();">
<script>
	var path= "<%=request.getContextPath()%>";
		$(document).ready(function() {
			$(".informacion").corner("6px");
			$(".informacion_error").corner("6px");
			$(".informacion_warning").corner("6px");
		 });

	</script>
	<jsp:include flush="true" page="/comun/header.jsp"></jsp:include>
	<fieldset class="form-fieldset">
	<div class="contenedor">
		<jsp:include flush="true" page="/comun/top.jsp"></jsp:include>
		<div class="menu">
			<jsp:include flush="true" page="/comun/menu.jsp"></jsp:include>
		</div>
		<div class="opcionmenu">
			<h1>Ingresar Fecha Pagos Anticipados</h1>
			<br> <br>
			<div id="informacion" class="informacion"></div>
			<div id="informacion_warning" class="informacion"></div>
			<div id="informacion_error" class="informacion"></div>
			<div class="textofino">
				<b>Nota:</b>&nbsp;En esta opción usted puede ingresar una fecha futura para pago anticipado de las cuotas de un crédito.
				<html:errors />
			</div><br>
			<form action="pagoAnticipado.do?op=busca" method="post" id="IngresoRutForm">
			<div class="field li">
				<label>Ingrese rut del usuario</label>
				<input class="inputCorto" type="text" name="id" id="id" maxlength="13">
				<html:errors property="rut" />
				&nbsp;&nbsp;<input class="boton" type="submit" value="Buscar">
			</div>
			<br><br>
			</form>
			<br>
			<c:if test="${not empty tipo}">
				<div class="${tipo}">
					<strong>Mensaje:</strong>&nbsp; ${mensaje}
				</div>
			</c:if>
			<c:if test="${not empty id}">
				<div>
					<form action="pagoAnticipado.do?op=guardar" method="post" id="listaCreditosPagoAnticipadoForm">
					<div class="field li">
						<label>Seleccione una Fecha Futura:</label>
						<input type="text" id="fecha" name="fecha" maxlength="10">
						<html:errors property="fecha" />
					</div>
						
					<div class="field li">
						<label>Seleccione un Crédito:</label>
						<div id="contenedor-tabla">
							<table class="tabla-principal">
								<tr>
									<th>&nbsp;</th>
									<th><b>RUT</b></th>
									<th><b>Folio</b></th>
									<th><b>Monto Crédito</b></th>
									<th><b>Total Cuotas</b></th>
								</tr>

								<c:forEach var="credito" varStatus="count" items="${listaCreditos}">
								<tr>
									<td><input type="radio" name="folio" id="folio" value="${credito.operacion}" 
									<c:if test="${count.index==0}">
									checked="checked"
									</c:if> ></td>
									<td>${id}<input type="hidden" name="id" value="${id}"></td>
									<td>${credito.operacion}</td>
									<td align="right">$<fmt:formatNumber maxFractionDigits="0">${credito.totalDeuda}</fmt:formatNumber></td>
									<td align="center">${credito.cuotasTotales}</td>
								</tr>
								</c:forEach>
								
							</table>
							
						</div>
					</div>	
						<div class="centraTabla">
							<table>
								<tr>
									<td></td>
									<td><br><input class="boton" type="submit" value="Guardar">
									</td>
								</tr>
							</table>
						</div>
					</form>
				</div>
			</c:if>
		</div>
		<br>
	</div>
	</fieldset>
</body>

<script type="text/javascript">

$(document).ready(function () {
        $("#IngresoRutForm").validate({
            rules: {
                id: {required: true, rut:true}
            }
        }); //fin jquery validate
        $("#listaCreditosPagoAnticipadoForm").validate({
            rules: {
                fecha: {required: true}
            }
        }); //fin jquery validate
        $("#id").Rut({
			format_on : 'keyup'
		});
		var currentTime = new Date();
		var inicioMes = new Date(currentTime.getFullYear(), currentTime.getMonth() + 1, 1);
		// 10 days before next month
		var finMes = new Date(currentTime.getFullYear(),
				currentTime.getMonth() + 12, 0);
		// one day before next month
		//var endDateFrom = new Date(currentTime.getFullYear(), currentTime.getMonth() +1, 3); // 3rd of next month
		//var endDateTo = new Date(currentTime.getFullYear(), currentTime.getMonth() +1, 10); // 10th of next month
		$(function() {
			$("#fecha").datepicker({
				minDate : inicioMes,
				maxDate : finMes,
				dateFormat: 'dd-mm-yy'
			});
						
		}); //fin del datepicker
    }); //fin document ready

</script>
</html>