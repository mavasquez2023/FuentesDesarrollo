<%@ include file="layout/headerJsp.jsp"%><%@ page 
import="cl.laaraucana.simat.entidades.CuentaPaseContableVO"  %>
<c:set var="tipoDebito" value="<%=CuentaPaseContableVO.TIPO_IMPORTE_DEBITO%>"></c:set>
<c:set var="tipoCredito" value="<%=CuentaPaseContableVO.TIPO_IMPORTE_CREDITO%>"></c:set>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="layout/header.jsp" flush="true"></jsp:include>
	<script src="jqSimat/spin.min.js"></script>
	<title>Pase contable</title>
</head>
<body>
<div id="wrapper" name="wrapper">	
	<div id="header" name="header">
		<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg" width="900" height="100">
	</div>
	<div id="workSpace" name="workSpace">
		<div id="simulacion">
		<h2>Propuesta Pase contable ${periodoText}</h2>
		<hr>
		<div id="cajaEstadoProcesos">
		<fieldset class="form-fieldset">
			<div class="field full-width">
				<strong>Periodo seleccionado: ${periodo}</strong><br><br>
				<strong>Estado proceso de Carga: ${estCarga.desEstado}</strong><br><br>
				<strong>Estado proceso de Validaci�n: ${estValid.desEstado}</strong><br><br>
				<strong>Estado de creaci�n de pase contable: ${estPase.desEstado}</strong><br><br>
				<input class="boton" type="button" value='Consultar Estado Procesos' onclick="recargarEstadosProcesos();"/>									
			</div>
			<br>
		</fieldset><br>
		</div>
				<fieldset class="form-fieldset">
					<div class="contenido">
					<c:if test="${not empty paseContable}">
						<table align="center">
							<thead>
								<tr>
									<th>Cuenta</th>
									<th>Concepto</th>
									<th>D�bito</th>
									<th>Cr�dito</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${paseContable.cuentasPaseContable}" var="cuenta">
									<tr class="margin">
										<td>${cuenta.nroCuenta}</td>
										<td>${cuenta.descConcepto}</td>
										<c:if test="${cuenta.tipoImporte == tipoDebito}">
											<td><fmt:formatNumber currencySymbol="$" value="${cuenta.monto}" type="currency" /></td>
											<td>$0</td>
										</c:if>
										<c:if test="${cuenta.tipoImporte == tipoCredito}">
											<td>$0</td>
											<td><fmt:formatNumber currencySymbol="$" value="${cuenta.monto}" type="currency" /></td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<th colspan="2">TOTALES</th>
									<th><fmt:formatNumber currencySymbol="$" value="${paseContable.totalDebito}" type="currency" /></th>
									<th><fmt:formatNumber currencySymbol="$" value="${paseContable.totalCredito}" type="currency" /></th>
								</tr>
							</tfoot>
						</table>
					</c:if>
					<div class="right">
						<div id="loadingJs"></div>
						<br>
						<input type="button" class="boton" value="Volver"onclick="volverAGenerar();">
					<c:if test="${tipoMensaje=='3'}">
							<input type="button" id="procesar" class="boton" value="Procesar"onclick="procesarPaseContable();">
					</c:if>
					</div>
					</div>
					<br>
					<div id="respuestaProceso">
						<c:choose>
							<c:when test="${tipoMensaje=='3'}">
								<div class="alert alert-success">${mensaje}</div>
							</c:when>
							<c:otherwise>
								<div class="alert alert-danger">${mensaje}</div>
							</c:otherwise>
						</c:choose>
					</div>
					<br>
				</fieldset>
				<br>
				<fieldset class="form-fieldset">
			<form action="mostrarMenu.do" name="formVolver" id="formVolver" method="post">
				<input type="hidden" name="metodo" id="metodo" value="mostrarMenu"/>
			    <input class="boton" type="submit" value='<< Volver a Menu' onclick="javascript:volverMenu()"/>
			</form>
		</fieldset>
		</div>
		<div id="loadMenu" name="loadMenu" >
			<img src='./imgSimat/Loading.gif' align="absmiddle"><br>Espere un momento...<br>
		</div>			
	</div>	
</div>
<script type="text/javascript">
var opts = {
		  lines: 11, // The number of lines to draw
		  length: 0, // The length of each line
		  width: 15, // The line thickness
		  radius: 30, // The radius of the inner circle
		  corners: 1, // Corner roundness (0..1)
		  rotate: 0, // The rotation offset
		  direction: 1, // 1: clockwise, -1: counterclockwise
		  color: '#000', // #rgb or #rrggbb or array of colors
		  speed: 1, // Rounds per second
		  trail: 60, // Afterglow percentage
		  shadow: false, // Whether to render a shadow
		  hwaccel: false, // Whether to use hardware acceleration
		  className: 'spinner', // The CSS class to assign to the spinner
		  zIndex: 2e9, // The z-index (defaults to 2000000000)
		  top: '50%', // Top position relative to parent
		  left: '50%' // Left position relative to parent
		};
		var target = document.getElementById('loadingJs');
		var spinner = new Spinner(opts);
		
	var procesoActivo = 0;
	function recargarPagina(){
		if(procesoActivo==0){
		 window.location = "<%=request.getContextPath()%>/obtenerPaseContable.do";
		}else{
			alert('Existe un proceso en ejecuci�n');
		}
	}
	
	window.onbeforeunload = preguntarAntesDeSalir;
	function preguntarAntesDeSalir(){
	    if(procesoActivo==1){
			return "Existe un proceso en ejecuci�n:";
	    }
	}
	
	function procesarPaseContable(){
		procesoActivo=1;
		//$("#loadMenu").show();
		spinner.spin(target);
		$.ajax({
 			url : '<%=request.getContextPath()%>/procesarPaseContable.do',
 			type : 'POST',
			success : function(response) {
				$("#respuestaProceso").empty();
 				$("#respuestaProceso").append(response);
 			},complete: function(response){
 				procesoActivo=0;
 				//$("#loadMenu").hide();
 				spinner.stop();
 			}
 		});
	}
	
	function volverAGenerar(){
		window.location.href='<%=request.getContextPath()%>/validarPreBalance.do';
	}
	
	var target2 = document.getElementById('cajaEstadoProcesos');
	var spinnerEstado = new Spinner(opts);
	procesoEstadoActivo=0;
	function recargarEstadosProcesos(){
		if(procesoEstadoActivo==0){
		procesoEstadoActivo=1;
		//$("#loadMenu").show();
		spinnerEstado.spin(target2);
			$.ajax({
	 			url : '<%=request.getContextPath()%>/consultaEstadoProcesos.do',
	 			type : 'POST',
				success : function(response) {
					$("#cajaEstadoProcesos").empty();
	 				$("#cajaEstadoProcesos").append(response);
	 			},complete: function(response){
	 				procesoEstadoActivo=0;
	 				//$("#loadMenu").hide();
	 				spinnerEstado.stop();
	 			}
	 		});
		}
	}
	
</script>
</body>
</html>