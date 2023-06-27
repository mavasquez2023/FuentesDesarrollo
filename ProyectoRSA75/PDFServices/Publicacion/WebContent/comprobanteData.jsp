<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
	<link rel="stylesheet" href="theme/Master.css" type="text/css">
	<title>comprobanteData</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="GENERATOR" content="Rational Application Developer">
	<script language="javascript" src="Includes/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			var pdf=0;
			
			$("[name='bpdf']").live('click',function(){
				var code = $(this).attr("id");
				
				window.open("/Publicacion/pdfLoaderPago.jsp?code="+code,'PDF'+pdf.toString(),'width=800,height=630,left=0,top=100,screenX=0,screenY=100');
				pdf++;
			});
		});
	</script>
</head>
<body style="background-color:#ffffff">
	<form>
	<c:choose>
		<c:when test="${fn:length(comprobantes)>0}">
			<table align="center" border="1" style="font-size:12px;background:#ffffff;border-collapse: collapse;" bordercolor="darkblue">
				<tr id="trheader" style="background:darkblue;font-weight:bold;text-align:center;color:white">
					<td>Fecha</td>
					<td>Tipo Proceso</td>
					<td>Rut Empresa</td>
					<td>Razón Social</td>
					<td>Convenio</td>
					<td>Descargar</td>
				</tr>
				
				<c:set var="pos" value="0" />
				<c:forEach begin="1" end="${(fn:length(comprobantes))}">
					<tr>
						<td>${comprobantes[pos].fecha}</td>
						<td align="center">${comprobantes[pos].proceso}</td>
						<td>${comprobantes[pos].rut}-${comprobantes[pos].digito}</td>
						<td>${comprobantes[pos].rsocial}</td>
						<td align="center">${comprobantes[pos].convenio}</td>
						<td>
							<button type="button" id="${comprobantes[pos].comprobante}" name="bpdf">Generar PDF</button>
						</td>
					</tr>
					
					<c:set var="pos" value="${pos+1}" />
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<h3 style="font-family:'Times New Roman';color:#6666cc">
				No se encontraron datos para la búsqueda
			</h3>
			<hr />
		</c:otherwise>
	</c:choose>
	</form>
</body>
</html>
