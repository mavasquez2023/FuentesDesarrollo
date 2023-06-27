<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
	<title>pdfLoader</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="GENERATOR" content="Rational Application Developer">
	<script language="javascript" src="Includes/jquery.js"></script>
	<script language="javascript" src="Includes/jQuery.download.js"></script>

	<style>
		#loader{
			position: absolute;
			top: 50%; /* Buscamos el centro horizontal (relativo) del navegador */
			left: 50%; /* Buscamos el centro vertical (relativo) del navegador */
			width: 260px; /* Definimos el ancho del objeto a centrar */
			height: 66px; /* Definimos el alto del objeto a centrar */
			margin-top: -33px; /* Restamos la mitad de la altura del objeto con un margin-top */
			margin-left: -130px; /* Restamos la mitad de la anchura del objeto con un margin-left */
		}
		.load{
			background-color:blue;
			margin-left:3px;
			height:30px;
			width:10px;
			border: 1px black solid;
			float:left;
		}
	</style>
</head>
<body>
	<div id="loader">
		<div id="l1" class="load"></div>
		<div id="l2" class="load"></div>
		<div id="l3" class="load"></div>
		<div id="l4" class="load"></div>
		<div id="l5" class="load"></div>
		<div id="l6" class="load"></div>
		<div id="l7" class="load"></div>
		<div id="l8" class="load"></div>
		<div id="l9" class="load"></div>
		<div id="l10" class="load"></div>
		<div id="l11" class="load"></div>
		<div id="l12" class="load"></div>
		<div id="l13" class="load"></div>
		<div id="l14" class="load"></div>
		<div id="l15" class="load"></div>
		<div id="l16" class="load"></div>
		<div id="l17" class="load"></div>
		<% 
			String browser = request.getHeader("User-Agent");
			
			if(browser.indexOf("MSIE 8") > 0 || browser.indexOf("MSIE 7") > 0 || browser.indexOf("MSIE 6") > 0) {
				%>
					<div id="l18" class="load"></div>
					<div id="l19" class="load"></div>
					<div id="l20" class="load"></div>
				<%
			}
		%>
		<div style="clear:both" align="center">
			<font style="font-size:10pt;text-transform: uppercase;">Generando Documento Pdf... <br />el proceso tardará unos segundos.</font>
		</div>
	</div>
</body>
</html>

<script type="text/javascript">
		$(function(){
			$(document).ready(parpadear);
			function parpadear(){ 
				$('.load').fadeIn(500).delay(250).fadeOut(500, parpadear);
			}
			
			enviado=location.href.split("?");  
			igualdades=enviado[1].split("&");  
			
			var FechaProceso = igualdades[0].split("=")[1];
			var FechaProceso2 = igualdades[1].split("=")[1];
			var RutEmpresa = igualdades[2].split("=")[1];
			var NumeroComprobante = igualdades[3].split("=")[1];
			var Convenio = igualdades[4].split("=")[1];
			var holding = igualdades[5].split("=")[1];
			var TipoProceso = igualdades[6].split("=")[1];

			//window.location="/Publicacion/CertificadoAnualPDF?rut="+rut.toString()+"&year="+year.toString()+"&holding="+holding;
			$.download('/Publicacion/ComprobantePagoDataPDF',"FechaProceso="+FechaProceso.toString()+"&FechaProceso2="+FechaProceso2.toString()+"&RutEmpresa="+RutEmpresa.toString()+"&NumeroComprobante="+NumeroComprobante.toString()+"&Convenio="+Convenio.toString()+"&holding="+holding.toString()+"&TipoProceso="+TipoProceso.toString(),'get');
		});
</script>