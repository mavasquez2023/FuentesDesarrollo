<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comunKiosco/header.jsp"></jsp:include>
<link rel="stylesheet" href="../../cssKiosco/finiquito.css">
<script type="text/javascript" src="../../js/custom-jquery-ui-es.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.Rut.min.js"></script>
<title>Certificado finiquito</title>
</head>
<body>
	<div class="bloque-explorer">
		<div id="inicioFiniquito">
			<p class="titulo">Certificado Saldo Capital por Finiquito</p>
			<form action="creditoFiniquitoList.do" method="post" class="form" id="formIniFini">
				<fieldset class="form-fieldset">
					<div class="fila">
						<div class="columna50">
							<h2>Ingreso de datos solicitados</h2>
							<hr>
							<br />
							<div class="bloque-explorer">
								<div class="divCentrado">
									<div class="field">
										<label>Rut: </label> <input type="text" name="rutEmpleado" id="rutEmpleado" maxlength="12" />
									</div>
									<div class="field espacio-med">Ej: 12345678-k</div>
									<html:errors property="rutEmpleado" />
									<div class="field">
										<label>Fecha finiquito: </label> <input type="text" name="fechaFiniquito" id="fecha" maxlength="10" />
										<html:errors property="fechaFiniquito" />
									</div>
									<div class="field espacio-med">Ej: (día-mes-año)</div>
									<div class="error2">
										<strong>${error}</strong>
									</div>
									<br>
									<div class="toolbar-imagen">
									<%-- 	<img align="right" src="<%=request.getContextPath()%>/img/btn_aceptar.jpg" id="enviarForm"> --%>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="columna50">
						<jsp:include page="../../comunKiosco/teclado.jsp" flush="true"></jsp:include>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<div id="loading" style="position: absolute; top: 25%; left: 47%; display: none; z-index: auto">
		<img src="../../img/3d-loader.gif">
	</div>
	<script type="text/javascript">
		$(document).ready(
				function() {

					$("#formIniFini").validate({
						rules : {
							rutEmpleado : {
								required : true,
								rut : true
							},
							fechaFiniquito : {
								required : true,
								customDate : true,
								//fechaValida : true,
								minlength : 10
							}
						},
						messages : {
							fechaFiniquito : {
								minlength : "Error en formato dd-mm-yyyy."
							}
						}
					}); //fin del validate

					$("#rutEmpleado").Rut({
						format : 'keyup'
					}); 

					var currentTime = new Date();
					//var inicioMes = new Date(currentTime.getFullYear(), currentTime.getMonth(), 1);
					// 10 days before next month
					var finMes = new Date(currentTime.getFullYear(),
							currentTime.getMonth() + 1, 0);
					// one day before next month
					//var endDateFrom = new Date(currentTime.getFullYear(), currentTime.getMonth() +1, 3); // 3rd of next month
					//var endDateTo = new Date(currentTime.getFullYear(), currentTime.getMonth() +1, 10); // 10th of next month
					$(function() {
						$("#fecha").datepicker({
							minDate : currentTime,
							maxDate : finMes
						});

					}); //fin del datepicker

					//comienda el show
					$(window).bind('beforeunload', function() {
						$('#loading').show();
					});

					/* function showLoading() {
						$('#loading').show();
					} */

					$(function() {
						//limpiar el formulario de consulta por history.back().
						$("#formIniFini")[0].reset();
					});

					$("#enviarForm").click(function() {
						$("#formIniFini").submit();
					});
				}); // fin del document ready

		$(document).ready(function(){
				var inputId = $('#formIniFini input').first().attr("id");
				
			 	$('#formIniFini input').click(function(event){
			 		inputId = event.target.id;	
				});	 
			 	
			 	$(".tecla").click(function(event){
					var data = event.target.name;
					if(data=="borrar"){
						$("#"+inputId).val($("#"+inputId).val().slice(0,-1));
					}else if(data=="ok"){
						$("#formIniFini").submit();//cambiar id del formulario
					}else{					
						$("#"+inputId).val($("#"+inputId).val() + data);
					}			
				});
				
		});
				
		
		
	</script>
</body>
</html>