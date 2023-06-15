<%@ include file="../comun/headerJsp.jsp" %>
<html>
	<head>
		<jsp:include flush="true" page="../comun/headerCRM.jsp"></jsp:include>
		<title>Certificados de crédito - La Araucana C.C.A.F.</title>
	</head>
	<body>
	<header>
		<div class="contenedor">
			<a href="http://www.laaraucana.cl" target="_blank"><img src="<%=request.getContextPath() %>/img/logo-final.png"></a>
		</div>
	</header>
	<div class="content">
		<div id="content-principal">
			<fieldset id="caja-login">
				<p class="titulo">Iniciar sesión</p>
				<form action="j_security_check" class="form" id="loginForm" method="post">
				<!-- form action="j_security_check" method="post" class="form" name="loginForm" id="loginForm"-->
					<div class="field">
						<label class="label">Rut:</label><input type="text" class="text" id="rut" name="j_username" maxlength="12" onKeyPress="keyRut();" />
					</div>
					<div class="field">
						<label class="label">Clave:</label> <input class="text" id="clave" type="password" name="j_password"  />
					</div>
					<br><br>
					<div class="field right">
						<input type="submit" class="boton " value="Iniciar sesión" />
					</div>
				</form>
				<div id="errores" class="field margin-15" >
					<html:errors/>
				</div>
			</fieldset>
		</div>
	</div>
	<br><br>
	
	<div class="content">
		<hr>
		<div id="content-footer">
			<img  src="<%=request.getContextPath() %>/img/footer.png">
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			 if (window!=window.top) {
				window.parent.location='<%=request.getContextPath() %>/main/login.jsp';
			}
		});
		
		$("#loginForm").validate({
			ignore : [],
			errorElement: 'div',
			rules : {
				j_password : {
					required : true
				},
				j_username : {
					required : true
				}
			},
			messages : {
					j_password : {
						required : '<bean:message key="error.password.required"/>'
					},
					j_username : {
						required : '<bean:message key="error.rut.required"/>'
					}
			},
			errorPlacement: function(error, element) {
				  $("#errores").empty();
			      error.appendTo("#errores");
			}
		});
		
		$("#rut").Rut({
			format_on : 'keyup'
		});
		
		$("#loginForm").submit(function(){
			if ($('#loginForm').validate().element("#rut"))
				$('#loginForm').validate().element("#clave");
			$('#rut').val($('#rut').val().replace(/\./g,''));
		});
	</script>
</body>
</html>