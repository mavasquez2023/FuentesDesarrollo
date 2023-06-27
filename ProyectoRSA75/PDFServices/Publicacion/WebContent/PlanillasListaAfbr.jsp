<!DOCTYPE html>
<html>
<head>
<title>PLANILLA AFBR</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/themes/ui-lightness/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="Includes/js/jquery.fileDownload.js" type="text/javascript"></script>
<script type="text/javascript" src="Includes/js/utiles.js"></script>
</head>
<body>
<div class="detalle" style="position:relative;height: 300px;width: 100%">
<table width="700px">
	<tr>
		<td align="center"><span style="font-size: 16px;">PLANILLA
		AFBR</span></td>
	</tr>
	<tr>
		<td height="10px"></td>
	</tr>
	<tr>
		<td align="center">
		<c:if test="${maxreg > 199}" >
		<span style="font-size: 14px;">Solo se
		han mostrado los primeros 200 resultados</span>
		</c:if></td>
	</tr>
</table>

<form action="ReportespdfAfbr"  name="formpdf" class="formpdf" method="post">
<input type="hidden" name="folio"> 
<input type="hidden" name="fechaProceso"> 
<input type="hidden" name="empag">
<input type="hidden" name="rutEmpresa"> 
<input type="hidden" name="_folder"> 
<input type="hidden" name="_accion">
<input type="hidden" value="" name="FechaProceso">
 
		 
<table style="border: 1px solid darkblue;border-width: 1px 1px 0 0"
	cellpadding="0" cellspacing="0" width="700px">
	<tbody>
		<tr bgcolor="darkblue" style="color: white;">
			<td width="60px"><span style="font-size: 12;"><b>Tipo
			Proceso</b></span></td>
			<td><span style="font-size: 12;"><b>Folio Proceso</b></span></td>
			<td><span style="font-size: 12;"><b>Fecha Proceso</b></span></td>
			<td><span style="font-size: 12;"><b>Rut Empresa</b></span></td>
			<td width="60px"><span style="font-size: 12;"><b>Convenio</b></span></td>
			<td align="center"><span style="font-size: 12;"><b>Sucursal</b></span></td>
			<td align="center"><span style="font-size: 12;"><b>PDF</b></span></td>
		</tr>
	</tbody>
	
	<c:forEach var="empresa" items="${empresa}">
		
			<tr bgcolor="#ffffff"
				onMouseOver="this.style.backgroundColor = 'darkblue';this.style.color='white'"
				onMouseOut="this.style.backgroundColor = '#ffffff';this.style.color='black'">

				<td align="center"
					style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span
					style="font-size: 12;">${empresa.PWCATIPRO}</span></td>
				<td style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span
					style="font-size: 12;margin-left: 5px;">${empresa.PWCAFOLIO}</span></td>
				<td style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span
					style="font-size: 12;margin-left: 5px;">${empresa.PWCACOPRO}</span></td>
				<td style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span
					style="font-size: 12;margin-left: 5px;">${empresa.fullRutEmpresa}</span></td>
				<td align="center"
					style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><span
					style="font-size: 12">${empresa.PWCACONVE}</span></td>
				<td style="border: 1px solid darkblue;border-width: 0 0 1px 1px;" align="center"><span
					style="font-size: 12;margin-left: 5px;">${empresa.PWCASUCUR}</span></td>
				<td width="80px"
					style="border: 1px solid darkblue;border-width: 0 0 1px 1px;"><input
					type="submit" value="Descargar" id="pdf"
					onclick="enviar(${empresa.PWCACOPRO },'${empresa.PWCAEMPAG }','${empresa.PWCAFOLIO}',${empresa.PWCARUTEM });"
					style="width: 70px;height: 20px;font-size: 12"></td>
			</tr>
		</c:forEach>
</table>
</form>
</div>
</body>
</html>

