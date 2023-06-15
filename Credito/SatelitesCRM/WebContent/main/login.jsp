<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<%@ include file="../comun/header.jsp"%>
<script type="text/javascript" src="../js/jquery.Rut.min.js"></script>
<style type="text/css">
	#login{
		width:400px;
	}
	#login .form-fieldset{
		padding: 6px;
	}
	.form .label{
		float: left;
		width: 100px;
	}
</style>
</head>
<body>
	<div id="login">
		<fieldset class="form-fieldset">
			<logic:empty name="usuario">
				<p class="titulo">Iniciar sesión</p>
				<html:form action="/login" styleClass="form" styleId="loginForm" method="post">
					<div class="field">
						<label class="label">Rut:</label>
						<html:text styleId="rut" property="rut" value="${noAuth.rut}"/>
					</div>
					<br />
					<div class="field">
						<label class="label">Clave:</label> 
						<html:password styleId="clave" property="clave"></html:password>
					</div>
					<br />
					<html:submit styleClass="boton">Iniciar sesión</html:submit>
				</html:form>
			</logic:empty>
			<logic:empty name="usuario">
				<html:errors />
			</logic:empty>
		</fieldset>
	</div>
		<script type="text/javascript">

			$("#loginForm").validate({
				ignore : [],
				rules : {
					rut : {
						required : true
						//rut : true
					},
					clave : "required"
				}
			}); 
			/* Formatear el rut */
			$("#rut").Rut({
				format_on : 'change'
			});
		</script>
</body>
</html>