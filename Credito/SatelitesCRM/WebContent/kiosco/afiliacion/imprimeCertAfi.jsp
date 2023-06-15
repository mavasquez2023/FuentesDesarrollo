<%@ include file="../../comun/headerJsp.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="../../cssKiosco/kiosco.css">
</head>
<body>
	<div class="pagina">
		<div class="header">
			<div class="right">
				<img  src="<%=request.getContextPath()%>/img/logo_reducido.jpg" alt="">
			</div>
			<h1>Certificado de Afiliación</h1>
		</div>
		<div class="content">
			<p>Caja de compensación La Araucana certifica que ${hash['nombreAfiliado']},
			 RUT N° ${hash['rutAfiliado']} se mantiene como afiliado activo desde 
			 el ${hash['fechaAfiliacion']}. <br> Se extiende el presente certificado a petición del interesado, 
			 para los fines que estimen convenientes.
			</p>
		</div>
		<div class="footer">
			<span>Santiago, ${hash['fechaEmision']}.</span>
			<div class="right">
				<img src="<%=request.getContextPath()%>/img/${hash['firma']}" alt="firma">
			</div>
			<br>
			<br>
			<div class="center">
				Si requiere más información visitenos en www.laaraucana.cl
			</div>
		</div>
	</div>
<jsp:include page="../../comunKiosco/printScript.jsp" flush="true" />
</body>
</html>