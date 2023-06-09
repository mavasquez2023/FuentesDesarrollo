<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<%@ include file="../../comunKiosco/header.jsp"%>
<title>Certificado Cuotas Cr�ditos Vigentes</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/cssKiosco/certificado.css">
</head>
<body>
	<logic:equal value="0" name="codError">
	<div id="content">
		<p class="titulo">Certificado Cuotas Cr�ditos Vigentes</p>
		<div id="datosAfiliado">
			<span><b>RUT: </b>${rut}</span>
			<span><b>Nombre: </b>${nombre}</span>
			<span><b>N�mero de cuotas pendientes:</b>${cuotasPendientes}</span>				
		</div>
		<div >
		<table>
			<tr>
				<th>Nc</th>
				<th>Vcto.</th>
				<th>Cuota</th>
				<!-- <th>Fec. Pago</th> -->
				<!-- <th>Ofi.</th> -->
				<!-- <th>Doc. Pago</th> -->
				<th>Cuota a Descuento</th>
				<th>Estado cuota</th>
			</tr>
			<logic:equal value="1" name="opcion">
				<logic:iterate id="lista" name="listaCuotas">
					<tr>
						<td>${lista.nCuota}</td>
						<td>${lista.fecVencimiento}</td>
						<td><fmt:formatNumber maxFractionDigits="0" value="${lista.nCuota}" />
						</td>
						<%-- <td>${lista.fecPago}</td>
						<td>${lista.oficina}</td>
						<td>${lista.docPago}</td> --%>
						<td>
						
						<c:choose>
							<c:when test="${lista.tipoMoneda == 'UF'}">
								UF <fmt:formatNumber maxFractionDigits="5" value="${lista.monto}" />
							</c:when>
							<c:otherwise>
								$<fmt:formatNumber maxFractionDigits="0" value="${lista.monto}" />
							</c:otherwise>
						</c:choose>			
							
						</td>
						<td>${lista.estCuota}</td>
					</tr>
				</logic:iterate>
				
				<logic:empty name="listaCuotas">
					<c:choose>
						<c:when test="${codError=='1'}">
							<td colspan="8"> <div id="msgError">${msg}</div>
							</td>
							<script>$("#imprimir").attr("disabled","disabled");</script>
						</c:when>
						<c:otherwise>
							<td colspan="8"> <div id="msgError">El cr�dito seleccionado no tiene cuotas vigentes.</div></td>
						</c:otherwise>
					</c:choose>
				</logic:empty>
		</logic:equal>
		</table>
		<br>
		</div>
		<div class="botones">
			<form id="genCertForm" name="genCertForm" target="iframeLoad" action="detalleCredito.do" method="POST">
				<input type="hidden" name="origen" value="kiosco">
				<input type="hidden" name="accion" value="imprimirReporte">
			</form>
		</div>
	</div>
	<jsp:include page="../../comunKiosco/botoneraFull.jsp" flush="true" />	
	</logic:equal>
</body>
</html>