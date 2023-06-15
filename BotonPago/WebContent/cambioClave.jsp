<%@ include file="layout/headerJsp.jsp" %>
<html>
	<head>
		<jsp:include flush="true" page="layout/header.jsp"></jsp:include>
		<link href="/BotonPago/css/layout.css" rel="stylesheet" type="text/css" />
		<link href="/BotonPago/css/estilos.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<meta name="Author" content="J-Factory Solutions" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<title>Pago de Crédito en Línea - La Araucana</title>
	</head>
<body class="corp">
	<jsp:include flush="true" page="comun/header.jsp"></jsp:include>
	<div id="contenedor">

	<div class="content">
		<div class="content-header">
			<p class="titulo">Servicios en línea</p>
			<div class="mensaje-gris">
				<c:choose>
					<c:when test="${obligatorio == 'true'}">
						<bean:message key="login.cambio.clave.obligatorio"/>
					</c:when>
					<c:otherwise>
						<bean:message key="login.cambio.clave"/>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div id="content-principal">
			<div id="content-left">
				<div class="caja">
					<div id="caja-left">
						<html:form action="/cambiarClave.do" styleId="form" method="post" styleClass="form">
							<div class="field">
								<label>Clave actual:</label>
								<input type="password" title="clave actual" id="claveInicial" name="claveInicial" maxlength="4" data-validate="clave" data-describedby="claveInicialMsg" data-description="clave" />
							</div>
							<div class="field">
								<label>Clave nueva:</label>
								<input type="password" title="clave nueva" id="claveNueva" name="claveNueva" maxlength="4" data-validate="claveNueva" data-describedby="claveNuevaMsg" data-description="claveNueva" />
							</div>
							<div class="field">
								<label>Confirmar clave nueva:</label>
								<input type="password" title="confirmar clave nueva" id="confirmaClave" name="confirmaClave" maxlength="4" data-validate="confirmaClave" data-describedby="confirmaClaveMsg" data-description="confirmaClave" />
							</div>
							<br>
							<div class="field centrado">
								<html:submit styleClass="boton" value="Aceptar"></html:submit>
								<c:choose>
									<c:when test="${obligatorio == 'true'}">
										<a href="<%=request.getContextPath()%>/logout.do">
											<input type="button" class="boton" value="Cancelar" />
										</a>
									</c:when>
									<c:otherwise>
										<input onclick="window.history.back();" type="button" class="boton" value="Cancelar" >
									</c:otherwise>
								</c:choose>
							</div>
						</html:form>
						<!-- Mensajes de error validación -->
						<span id="claveInicialMsg">
							<html:errors property="claveInicial" />
						</span>
						<span id="claveNuevaMsg">
							<html:errors property="claveNueva" />
						</span>
						<span id="confirmaClaveMsg">
							<html:errors property="confirmaClave" />
						</span>
					</div>	
					<div id="caja-right">
						Ingrese su clave actual y nueva clave de 4 dígitos	
					</div>
				</div>
			</div>
			<div id="content-right">
				<div class="caja">
					<a href="http://www.laaraucana.cl/irj/go/km/docs/documents/corpsite/personas/inicio/LinksCabecera/Contactenos">
						<img src="/BotonPago/img/sobre.gif" align="left" id="imgContacto" >Consultas <br> sugerencias o reclamos
					</a>
					<br>
					<div class="linea-puntos" ></div>
					<img src="/BotonPago/img/fono_contacto.gif" >
				</div>
			</div>
		</div>
		<br><br>
		<hr>
		<script type="text/javascript" src="/BotonPago/js/jquery-validate.min.js"></script>
		<script type="text/javascript" src="/BotonPago/js/script.js"></script>
	</div>
	</div>
	</body>
</html>
