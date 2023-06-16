<!DOCTYPE html>

<% final java.util.ResourceBundle mailProperties = java.util.ResourceBundle.getBundle("etc/config"); %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<html lang="en">

<head>
<!-- Le styles -->
<link href="dist/css/bootstrap.css" rel="stylesheet">
<link href="css/estilo.css" rel="stylesheet">
<link href="css/redmond/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.corner.js"></script>
<script type="text/javascript" src="js/ie.js"></script>
<script type="text/javascript" src="js/comun.js"></script>
<script type="text/javascript" src="js/iconos.js"></script>
<script type="text/javascript" src="js/validaciones.js"></script>
<script type="text/javascript" src="js/previpass.js"></script>
<script type="text/javascript" src="js/cargardatos.js"></script>
<script type="text/javascript" src="js/inputs.js"></script>
<script type="text/javascript" src="js/ayuda.js"></script>
</head>
<body>

	<style>
label {
	display: inline-block;
	width: 5em;
}

fieldset div {
	margin-bottom: 2em;
}

fieldset .help {
	display: inline-block;
}

.ui-tooltip {
	width: 210px;
}
a:link { text-decoration: none; }
a:visited { text-decoration: none; }
a:hover { text-decoration: none; }
a:active { text-decoration: none; }
</style>

	<script type="text/javascript">
		function preventBack() {
			window.history.forward();
		}

		setTimeout("preventBack()", 0);

		window.onunload = function() {
			null
		};
	</script>

	<script>
		
		window.history.forward();

		$(document).ready(function() {
			//cargarValores("empresa");

			$("td").corner("8px");
			$("input").corner("4px");
			$(".borde_redondeado").corner("6px");
			$(".informacion").corner("6px");
			$(".informacion_error").corner("6px");
			$(".informacion_warning").corner("6px");
		});


		
		function tipoSeleccion(){
			
			var ts= $('input:radio[name=tipo]:checked').val();
			if(ts=="D"){
				window.location= "<%= mailProperties.getString("app.url.dd")%>";
				return;
			}else if(ts=="I"){
				window.location= 'independiente.html';
			}else{
				window.location= 'empresa.html';
			}
		}
		


		function replaceAll(text, busca, reemplaza) {
			while (text.toString().indexOf(busca) != -1)
				text = text.toString().replace(busca, reemplaza);
			return text;
		}

		function mostrarAyuda(key, id, width, height) {
			$("body").append('<div id="ayuda" class="ayuda"></div>');
			$("#ayuda").html(ayuda[key]);
			$("#ayuda").fadeIn(500);
			$("#ayuda").css("width", width);
			$("#ayuda").css("height", height);
			$("#ayuda").css("left", $("#" + id).offset().left + 40)
			$("#ayuda").css("top", $("#" + id).offset().top)
		}

		function ocultarAyuda() {
			$("#ayuda").fadeOut(500);
			setTimeout(function() {
				$("#ayuda").remove();
			}, 500);
		}
		
	</script>


	<div class="container">


		<div id="header">
			<div id="logo"></div>
			<div id="bottom_header"></div>

			<div id="informacion" class="informacion"></div>
			<div id="informacion_error" class="informacion"></div>
			<div id="informacion_warning" class="informacion"></div>
		</div>

		<div id="div_seleccion">
			<h1>Selecci&oacute;n Servicio</h1>
			<table class="tabla_formulario" cellspacing="2">
				<tr class="tr_formulario">
					<td class="titulo_campo_login">Empresa</td>
					<td class="data_campo_login"><input type="radio"
						id="tipoE" name="tipo" value="E" checked="checked"></td>
				</tr>
				<tr class="tr_formulario">
					<td class="titulo_campo_login">Deudor Directo</td>
					<td class="data_campo_login" ><input type="radio"
						id="tipoD" name="tipo" value="D">
					</td>
				</tr>
				<!-- tr class="tr_formulario">
					<td class="titulo_campo_login">Trabajador Independiente</td>
					<td class="data_campo_login" ><input type="radio"
						id="tipoI" name="tipo" value="I" >
					</td>
				</tr-->
				<tr class="tr_separador">
					<td></td>
				</tr>

				<tr class="tr_formulario">
					<td colspan="5" align="center" width="100%">
						<button type="button" class="btn btn-default btn"
							onclick="javascript:tipoSeleccion();">
							<span class="glyphicon glyphicon-log-in"></span> Continuar
						</button>
					</td>
				</tr>
				<tr class="tr_separador">
					<td></td>
				</tr>
				<tr class="tr_formulario">
					<td colspan="5" align="left" class="texto_info">Seleccione de que forma ingresar&aacute; al portal.</td>
				</tr>

			</table>
		</div>	
		<div id="footer" class="texto_footer">
			Una innovaci&oacute;n tecnol&oacute;gica de Caja De
			Compensaci&oacute;n
			<div id="logo_araucana"></div>
		</div>
	</div>

		
		
		<script type="text/javascript">
			jQuery(document).ready(function($) {
				// $('#tabs').tab();
			});
		</script>

	<!-- container -->


	<script type="text/javascript" src="dist/js/bootstrap.js"></script>
	<div id="cargando">


		<div id="imagen">
			<img src="img/cargando_big.gif" width="100px"></img>
		</div>
		<div id="texto_cargando"></div>

	</div>

	<div id="confirmacion_cambio_tab" style="display: none"
		class="texto_confirmacion" title="Guardar Cambios"></div>

	<div id="confirmacion_salir_app" style="display: none"
		class="texto_confirmacion" title="Confirmaci&oacute;n Salir">
		Los cambios se han Guardado correctamente.<br />
		<br /> Una vez fuera de la aplicación Ud. podrá ingresar en cualquier
		momento para continuar con el registro de empresa, la aplicación ya ha
		guardado los datos ingresados en esta sesi&oacute;n.

	</div>

	<div class="info_validacion">Error en validacion</div>

</body>
</html>
