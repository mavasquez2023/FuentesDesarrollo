<%@ include file="../comun/headerJsp.jsp"%>
<html:html>
<head>
<link type="text/css" href="../css/integracionCRM.css" rel="stylesheet" />
<%@ include file="../comun/header.jsp"%>
<script>
	$(document).ready(function() {
		$('a').click(function() {
			var url = $(this).attr('rel');
			$('#mainIframe').attr('src', url);
	
		});
	});
</script>
</head>
<body>
	<div class="contieneIframe">
	<div id="navegador">
		<ul>
			<li><a rel="../certificados/afiliacion/generaCertificado.do">Certificado de Afiliaci&oacute;n</a></li>
			<li><a rel="../certificados/creditosvigentes/listarCreditosVigentes.do">Certificado de Cr&eacute;ditos Vigentes</a></li>
			<li><a rel="../certificados/creditoscancelados/listarCreditosCancelados.do">Certificado de Cr&eacute;ditos Cancelados</a></li>
					
 			<li><a rel="../certificados/finiquito/finiquitoInicioView.do">Certificado de Finiquito</a></li>
			<li><a rel="../certificados/prepago/getCreditosPrepago.do">Certificado de Prepago</a></li>
			<li><a rel="../certificados/deuda/deudaInicioView.do">Deuda Ley 20.720 Renegociación SIR</a></li>
			<li><a rel="../certificados/deuda/getCreditosDeuda.do">Deuda Ley 20.720 Liquidación</a></li>
		</ul>
	</div>
	<br>
		<iframe src="" id="mainIframe" name="mainIframe" style="width: 99%; height: 100%;" frameborder="0"></iframe>
	</div>
	<form action="" method="post" target="mainIframe" id="formid">
	<input type="hidden" name="rut" id="rut" value="${rut}" />
	</form>
</body>
</html:html>