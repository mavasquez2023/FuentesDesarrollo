<%@page import="java.util.Date"%>
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
  </head>
  <body >
	<main>
	<div class="container">
		<div class="row">
			<jsp:include page="banner.jsp" />
			<div class="col xs12 md8 lg10">
				<div class="row justify-center-xs" style="margin-top: -40px">
					<div class="col xs12 lg6">
						<div class="respuesta text-align-center-xs">
							<p class="respuesta__titulo color--secundario">
								Solicitud de Seguro de Vida Sana
							</p>
							<br>
							<p class="respuesta__subtitulo color--secundario" style="margin-top: -10px">
								<fmt:formatDate pattern = "dd 'de' MMMM 'de' yyyy" value="<%=new java.util.Date() %>"/>
							</p>
						</div>
					</div>
				</div>
				<div class="row justify-center-xs" style="margin-top: -40px" id="beneficios">
					<div class="col xs12 lg4">
						<div class="bloque__texto color--primario text-align-left-xs" style="background:rgb(210, 210, 210);font-weight: bold;">
							<p>
								<ul><li><img src="img/check.png" style="width:18px;height:18px;" /><font style="margin-left: 10px">Exclusivo para afiliados</font></li>
									<li><img src="img/check.png" style="width:18px;height:18px;" /><font style="margin-left: 10px">Sin costo</font></li>
									<li><img src="img/check.png" style="width:18px;height:18px;" /><font style="margin-left: 10px">Acceso por 6 meses al activar MUVU</font></li>
									<li><img src="img/check.png" style="width:18px;height:18px;" /><font style="margin-left: 10px">Desde el séptimo mes podrás mantenerlo si cumples un mínimo de actividad</font></li>
								</ul>
							</p>
							<br>
							
						</div>
						<div class="separador"></div>
					</div>
				</div>
				<form class="form" id="paso1" action="aprobar.do" method="POST" style="margin-top: -20px">
					<div id="campos">
					<div class="row justify-center-xs">
						<div class="col xs12 lg4">
							<div class="form__grupo" data-animacion="placeholder">
								<input class="text requerido tipo_email" type="text" id="email"
									name="email" value="${email}" disabled="disabled" /> <label for="email">Email</label>
							</div>
						</div>
					</div>
					<div class="row justify-center-xs" id="divrut">
						<div class="col xs12 lg4">
							<div class="form__grupo" data-animacion="placeholder" data-comentario="Sin puntos ni guión. Ej: 12345678K">
								<input class="text tipo_rut" type="text" id="rut_afiliado"
									name="rut_afiliado" value="" maxlength="12" autocomplete="off" style="text-transform: uppercase"/> <label for="rut_afiliado">Rut Afiliado</label>
							</div>
						</div>
					</div>
					<div class="row justify-center-xs">
						<div class="col xs12 lg4">
							<div class="form__grupo" data-animacion="placeholder"
								data-comentario="formato: dd/mm/aaaa">
								<input class="text requerido tipo_fecha" type="text"
									id="_fecha_nacimiento" name="_fecha_nacimiento" value="" maxlength="10"/> <label
									for="_fecha_nacimiento">Fecha Nacimiento</label>
							</div>
						</div>

					</div>
					</div>
					<div class="row justify-center-xs" id="solicitud" style="margin-bottom: -30px; display:none">
						<div class="col xs12 lg4">
							<div class="bloque__texto color--primario text-align-left-xs" style="text-align: justify;">
								<p style="font-weight: bold;">
									Yo, <font id="nombre_declara"></font>, RUT <font id="rut_declara"></font>, solicito mi incorporación al Programa de Vida Sana.
								</p>
								<div id="leermas" style="text-decoration: underline;"><a href="#" style="color:#0000CC">Leer más</a></div>
							
							</div>
							<div class="separador"></div>
						</div>
					</div>
				<div class="row justify-center-xs" id="condiciones" style="display:none">
					<div class="col xs12 lg4" style="margin-top: -20px">
						<div class="bloque__texto color--primario text-align-left-xs" style="text-align: justify;">
							
							<p style="font-weight: bold;margin-top: 10px;margin-bottom: 10px">
								Yo, <font id="nombre_declara2"></font>, RUT <font id="rut_declara2"></font>, fecha de nacimiento <font id="fecha"></font>, solicito mi incorporación al Programa de Vida Sana.
							</p>
							Coberturas:<br>
							<p style="font-size:small;margin-top: 10px;">	
							
							<jsp:include page="terminos.jsp" />
								
							</p>
							<div id="leermenos" style="text-decoration: underline;"><a href="#" style="color:#0000CC">Leer menos</a></div>
							
						</div>
						
					</div>
				</div>
				
				<div class="row justify-center-xs" >
					<div class="col xs12 lg4">
						<div class="bloque__texto color--primario text-align-left-xs" style="text-align: justify;">
							<p>	<br>
								<input type="checkbox" id="terminos" name="terminos" style="margin-right: 5px"/>
								<font style="font-size: 1.0em;">Acepto las condiciones de incorporación al Programa 
								y Seguro de Vida Sana sin costo para mi.</font>
									
							</p>
							
						</div>
					
					</div>
				</div>
				<div class="row justify-center-xs" >
					<div class="col xs12 lg4">
						<div class="alerta alerta--error" id="errores_paso1"
						style="display: none"></div>
					</div>
				</div>
				<div class="row justify-center-xs" style="margin-top: 15px;">
						<div class="col xs12 lg6">
					
							<div class="btn__grupo text-align-center-xs">
								<a id="continuar"
									class="btn btn--primario" href="<c:url value='/soporte.do?causa=salir'/>" >Salir</a>
								<a id="continuar"
									class="btn btn--secundario" href="#" onclick="validarPaso1();">Continuar</a>
								<br>
							</div>
						</div>
					</div>
					<input type="hidden" name="fecha_nacimiento" id="fecha_nacimiento" value="" />
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
    <script>
	$(document).ready(function() {
    		
    		 $("#rut_afiliado").on('paste', function(e){
    			e.preventDefault();
  			})
    		$('#rut_afiliado').change(function(){
    			if(validarRut($('#rut_afiliado').val())){
    				$("#rut_afiliado").css("border-color","LightGray"); 
        			obtieneNombre($('#rut_afiliado').val(), '<%=request.getContextPath()%>');
        		}else{
        		  $("#rut_afiliado").css("border-color","red"); 
        		  $("#rut_afiliado").focus();
        		}
    		});
    		$('#_fecha_nacimiento').change(function(){
        		$('#fecha_nacimiento').val($('#_fecha_nacimiento').val());
    		});
    		$('#leermas').click(function(){
    			if( validarPaso1_1() && $('#fecha_nacimiento').val() != ""){
        			$('#condiciones').show();
        			$('#fecha').html($('#fecha_nacimiento').val());
        			$('#beneficios').hide();
        			$('#campos').hide();
        			$('#solicitud').hide();
        		}
    		});
    		$('#leermenos').click(function(){
        		$('#condiciones').hide();
        		$('#beneficios').show();
        		$('#campos').show();
        		$('#solicitud').show();
        		
    		});
		});
	</script>
  </body>
</html>