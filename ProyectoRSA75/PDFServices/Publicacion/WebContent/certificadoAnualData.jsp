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
				var rut = $(this).attr("id");
				var year = $(this).attr("year");
				var holding = $("#holding").val().replace(/\s/g,",");
				//window.location="/Publicacion/CertificadoAnualPDF?rut="+rut.toString()+"&year="+year.toString()+"&holding="+holding;
				window.open("/Publicacion/pdfLoader.jsp?rut="+rut.toString()+"&year="+year.toString()+"&holding="+holding,'PDF'+pdf.toString(),'width=800,height=630,left=0,top=100,screenX=0,screenY=100');
				pdf++;
			});
		});
	</script>
</head>
<body style="background-color:white">
	<form>
		<input type="hidden" id="holding" value="${holding}" />
		<c:choose>
			<c:when test="${fn:length(empresas)>0}">
				<table align="center" border="1" style="font-size:12px;background:#ffffff;border-collapse: collapse;" bordercolor="darkblue">
					<tr id="trheader" style="background:darkblue;font-weight:bold;text-align:center;color:white">
						<td>Año</td>
						<td>Rut Empresa</td>
						<td>Razón Social</td>
						<td>Descargar</td>
					</tr>
					
					<c:set var="pos" value="0" />
					<c:forEach begin="1" end="${(fn:length(empresas))}">
						<tr>
							<td>${year}</td>
							<td>${fn:trim(empresas[pos].rut)}-${empresas[pos].digito}</td>
							<td>${empresas[pos].rsocial}</td>
							<td>
								<button type="button" year="${year}" id="${fn:trim(empresas[pos].rut)}" name="bpdf">Generar PDF</button>
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
