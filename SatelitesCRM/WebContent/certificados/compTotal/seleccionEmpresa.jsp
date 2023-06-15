<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comun/header.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/finiquito.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/js/custom-jquery-ui-es.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.Rut.min.js"></script>
<title>Carga financiera</title>
</head>
<body>
	<div class="bloque-explorer">
		<div id="inicioFiniquito">
			<p class="titulo"></p>
			<form action="<%=request.getContextPath() %>/certificados/seleccionEmpresa.do" method="post" class="form" id="formIniFini">
				<fieldset class="form-fieldset">
					<h2>Ingrese rut de empleado a consultar</h2>
					<hr>
					<br />
					<div class="bloque-explorer">
						<div class="divCentrado">
								<div class="field">
									<label>Rut: </label> 
									<input type="text" name="rutEmpleado" id="rutEmpleado" maxlength="12" />									
									<html:errors property="rutEmpleado" />
								</div>
								<div class="field espacio-med">Ej: 12345678-k</div>
								<div class="error2"><strong>${error}</strong></div>
								<br>
								
								<div class="field">
									<input type="submit" value="Consultar" class="boton">
								</div>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
	</div>
	<div id="loading" style="position:absolute; top:25%; left:47%; display:none; z-index: auto" >
		<img src="<%=request.getContextPath() %>/img/3d-loader.gif">
	</div>
	<script type="text/javascript">
		$(document).ready(
				function() {

					$("#formIniFini").validate({
						rules : {
							rutEmpleado : {
								required: true,
								rut: true
							}
						}
					}); //fin del validate
					
					$("#rutEmpleado").Rut({
						format_on : 'keyup'
					}); 

					//comienda el show
					$(window).bind('beforeunload', function(){
						$('#loading').show();
					});

				}); // fin del document ready		
	</script>
</body>
</html>