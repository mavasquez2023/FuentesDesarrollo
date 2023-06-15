<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comun/header.jsp" flush="true"></jsp:include>
<title>Certificado Cuotas Cr�ditos Vigentes</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/certificado.css">
</head>
<body>
	<logic:equal value="0" name="codError">
		<p class="titulo">Certificado Cuotas Cr�ditos Vigentes</p>
		<div id="datosAfiliado">
			<span><b>RUT: </b>${rut}</span><span><b>Nombre: </b>${nombre}</span>	
			<span><b>Folio Cr�dito: </b>${folio_contrato}</span>
			<span><b>Cuotas pendientes:</b>${cuotasPendientes}</span>		
		</div>
		<table>
			<tr>
				<th>Cuota</th>
				<th>Vcto.</th>
				<!-- <th>Fec. Pago</th> -->
				<!-- <th>Ofi.</th> -->
				<!-- <th>Doc. Pago</th> -->
				<th>Cuota a Descuento</th>
				<th>Estado cuota</th>
			</tr>
			<logic:equal value="1" name="opcion">
				<logic:iterate id="lista" name="listaCuotas">
					<tr>
						<td><fmt:formatNumber maxFractionDigits="0" value="${lista.nCuota}" />
						<td>${lista.fecVencimiento}</td>
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
		<div class="botones">
			<form target="blank" action="detalleCredito.do" method="POST">
				<div id="datosInsolvencia" style="display: none">
					<p align="left"><b>CLIENTE ACOGIDO A LEY DE INSOLVENCIA, ANTES DE ENTREGAR INFORMACI�N COMUNICARSE CON SU ZONAL DE COBRANZA </b><br></p>
					<p align="center"><input type="submit" value="Generar Certificado" id="imprimir" class="boton" /></p>	
				</div>
				<input type="hidden" name="accion" value="imprimirReporte">
				
				<input type="hidden" name="uc" value="${uc}">
				<input type="hidden" name="folio_contrato" value="${folio_contrato}">
				<c:choose>
				<c:when test="${insolvencia=='X' }">
					
					<input type="button" value="Generar Certificado..." id="insolvente" class="boton" />
				</c:when>
				<c:otherwise>
					<input type="submit" value="Generar Certificado" id="imprimir" class="boton" />
				</c:otherwise>
				</c:choose>
				<input id="volver" type="button" value="Volver" onClick="history.back()" / class="boton">
				<p style="height: 40px">&nbsp;</p>
			</form>
		</div>
		
				
		<div id="loading" style="position:fixed; top:25%; left:47%; display:none; z-index: auto" >
			<img src="<%=request.getContextPath() %>/img/3d-loader.gif">
		</div>
	</logic:equal>
	<script>
		
		$(document).ready(function(){
			$( "#insolvente" ).click(function() {
  				$( "#datosInsolvencia" ).show();
  				$( "#insolvente" ).hide();
			});
		});		
		/* $(document).ready(function(){

		jQuery.ajax({
	        type: "POST",
	        url: '../../getDetalleContrato.do',
	        data:{folio:"${folio_contrato}"},
	        success: function(data)
	        {
	        $("#loading").remove();
	        $("table").append(data);
	        }
		});
		
		$("#loading").show();

		}); */
		
	</script>
</body>
</html>