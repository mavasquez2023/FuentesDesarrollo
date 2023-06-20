<%@page import="cl.laaraucana.muvu.util.Configuraciones"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, viewport-fit=cover"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>MUVU - La Araucana</title>
    <link rel="stylesheet" href="assets/css/fln.css"/>
    <link rel="stylesheet" href="fonts/fln-icons.css"/>
    <link rel="stylesheet" href="assets/css/estilos.css"/>
    <script>
      WebFontConfig = {
          google: {
              families: ['Open Sans:300,400,500,700']
          }
      };
      (function () {
          var wf = document.createElement('script');
          wf.type = 'text/javascript';
          wf.async = 'true';
          var s = document.getElementsByTagName('script')[0];
          s.parentNode.insertBefore(wf, s);
      })();
    </script>
    <script>
	function login() {
			//validarRut();
			if($("#password").val()!=""){
				rut= $("#rut").val();
				rut= rut.replace(/\./g, "");
				$("#rut").val(rut);
				$("#paso0").submit();
			}else{
				//marcarCampoError("j_password");
			}
		}
	function validarEnter(){
			if(validaEnter() == false){
				$("#login").focus();
				login();
			}
			return;
		}
</script>
  </head>
  <body >
	<main>
	<div class="container">
		<div class="row">
			<jsp:include page="banner.jsp" />
			<div class="col xs12 md8 lg10" style="margin-top: -40px">
				<div class="row justify-center-xs">
					<div class="col xs12 lg6">
						<div class="respuesta text-align-center-xs">
							<p class="respuesta__titulo color--secundario">
								Bienvenido al Programa de Vida Sana de La Araucana
							</p>
							<br>
							<p class="respuesta__subtitulo color--secundario">Ingresa tu
								RUT y tu clave como afiliado</p>
						</div>
					</div>
				</div>
				<form class="form" id="paso0" action="solicitud.do" method="POST" style="margin-top: -55px">
					<div class="row justify-center-xs">
						<div class="col xs12 lg4">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="Sin puntos ni guión. Ej: 12345678K">
								<input class="text requerido tipo_rut" type="text" id="rut"
									name="rutAfiliado" value="" maxlength="12" autocomplete="off" />
								<label for="rut">RUT</label>
							</div>
						</div>
					</div>
					<div class="row justify-center-xs">
						<div class="col xs12 lg4">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="Clave numérica">
								<input class="text requerido tipo_numerico" type="password"
									id="password" name="password" value="" maxlength="10"
									onkeypress="validarEnter();" autocomplete="new-password" /> <label
									for="password">Contraseña</label>
							</div>
						</div>

					</div>
					<div class="row justify-center-xs">
						<div class="col xs12 lg4">
							<div class="alerta alerta--error" id="aviso" style="display:none"></div>
						</div>

					</div>
					
					<div class="row justify-center-xs">
						<div class="col xs12 lg6">
							<div class="btn__grupo text-align-center-xs">
								<a id="login" style="margin-left: 20px"
									class="btn btn--primario" href="#" onclick="login();">Ingresar</a>
								<br>
								<p class="adicional"
									style="margin-top: 10px; margin-left: -15px; font-size: medium; text-align: center">
									Solicita clave &nbsp; <a style="color: #2072BC"
										href="<c:url value='<%=Configuraciones.getConfig("url.clave.persona")%>' />"
										target="_blank">aquí</a> &nbsp;|&nbsp;Recupera clave &nbsp; <a
										style="color: #2072BC"
										href="<c:url value='<%=Configuraciones.getConfig("url.recuperar.clave.persona")%>' />"
										target="_blank">aquí</a>
								</p>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	</main>
	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
    <script src="assets/js/polyfill.js"></script>
    <script src="assets/js/jquery-3.3.1.js"></script>
    <script src="assets/js/jquery-ui.js"></script>
    <script src="assets/js/automatizar.js"></script>
    <script src="assets/js/fln.js"></script>
    <script src="assets/js/funciones.js"></script>
    <script src="assets/js/corev2.js"></script>
    <c:if test='${errorMsg=="ldap_error" }'>
    <script>
		avisar('El RUT o contraseña no es válido, intenta nuevamente.', 5000);
	</script>
	</c:if>
  </body>
</html>