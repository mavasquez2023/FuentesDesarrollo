<!DOCTYPE html>
<%@page	language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<% response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
 response.setHeader("Pragma","no-cache"); //HTTP 1.0 
 response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
%>
<%@ include file="../comun/tld.jsp"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<title>Carga Financiera</title>
<%@ include file="../comun/header.jsp"%>
</head>
<body>
	<div class="logo">
		<img width="210px" alt="logo" src="../img/logo_reducido.jpg">
	</div>
	<div class="contenedor">

		<h1 class="titulo">Carga Financiera</h1>

		<h2 class="subTitulo pullLeft">
			<b>RUT Afiliado:</b>
			<bean:write name="rut" />
		</h2>
		<h3 class="subTitulo pullLeft">
			<b>ID Contrato:</b>
			<bean:write name="idContrato" />
		</h3>
		<h3 class="fechaEmision margenBottom">
			<b>Fecha Emisión: </b>
			<bean:write name="fechaEmision" format="dd-MM-yyy" />
		</h3>

		<div class="containerTablas">
			<table style="width:400px" >
				<tbody>
					<tr>
						<th>% Condonación <br>Gravámenes</th>
						<th>% Condonación <br>Gastos de Cobranza</th>
						<th>% Condonación <br>Honorarios</th>
					</tr>
					<tr>
						<td><bean:write name="condonacion" property="cond_gravamenes" /></td>
						<td><bean:write name="condonacion" property="cond_gasto_cobranza" /></td>
						<td><bean:write name="condonacion" property="cond_honorarios" /></td>
					</tr>
				</tbody>
			</table>
			<table>
				<caption>Detalle de Contrato</caption>
				<tbody>
					<tr>
						<th>N° de Cuota</th>
						<th>Fecha de <br>Vencimiento</th>
						<th>Monto Capital <br>($)</th>
						<th>Monto Interés<br>($)</th>
						<th>Monto Gravamen<br>($)</th>
						<th>Monto Seguro<br>($)</th>
						<th>Monto Pagado/<br>Abonado ($)</th>
						<th>Monto a Dcto <br>($)</th>
						<th>Estado de Cuota</th>
						<th>Fecha Pago <br>Recaudación</th>
						<th>Método Pago</th>
					</tr>
					<logic:equal name="error" value="ok">
						<logic:iterate id="detalleBanking" name="detalle">
							<tr>
								<td><bean:write name="detalleBanking" property="nroCuota" />
								</td>
								<td><bean:write name="detalleBanking"
										property="fechaVencCuota" format="dd-MM-yyy" />
								</td>
								<td>
<%-- 								<fmt:formatNumber maxFractionDigits="0"> --%>
										<bean:write name="detalleBanking" property="montoCapital" />
<%-- 								</fmt:formatNumber> --%>
								</td>
								<td>
<%-- 								<fmt:formatNumber maxFractionDigits="0"> --%>
										<bean:write name="detalleBanking" property="montoInteres" />
<%-- 									</fmt:formatNumber> --%>
								</td>
								<td>
<%-- 								<fmt:formatNumber maxFractionDigits="0"> --%>
										<bean:write name="detalleBanking" property="montoGravamenes" />
<%-- 									</fmt:formatNumber> --%>
								</td>
								<td>
<%-- 								<fmt:formatNumber maxFractionDigits="0"> --%>
										<bean:write name="detalleBanking" property="montoSeguros" />
<%-- 									</fmt:formatNumber> --%>
								</td>
								<td>
<%-- 								<fmt:formatNumber maxFractionDigits="0"> --%>
										<bean:write name="detalleBanking" property="montoAbono" />
<%-- 									</fmt:formatNumber> --%>
								</td>
								<td>
<%-- 								<fmt:formatNumber maxFractionDigits="0"> --%>
										<bean:write name="detalleBanking" property="totalCuota" />
<%-- 									</fmt:formatNumber> --%>
								</td>
								<td><bean:write name="detalleBanking"
										property="estadoCuota" />
								</td>
								<td><bean:write name="detalleBanking"
										property="ultFechaPago" format="dd-MM-yyy" />
								</td>
								<td><bean:write name="detalleBanking"
										property="viaCotizDescripcion" />
								</td>
							</tr>
						</logic:iterate>
					</logic:equal>
					<logic:equal name="error" value="vacio">
						<tr>
							<td colspan="10">
								<div class="notice_message">
									<bean:message key="error.sin.cuotas" />
								</div>
							</td>
						</tr>

					</logic:equal>
					<logic:equal name="error" value="error">
						<tr>
							<td colspan="10">
								<div class="error_message">
									<bean:write name="mensaje" />
								</div>
							</td>
						</tr>

					</logic:equal>
				</tbody>
			</table>


			<div class="pullRight">
				<div id="datosInsolvencia" style="display: none">
					<p align="center"><b>CLIENTE ACOGIDO A LEY DE INSOLVENCIA, ANTES DE ENTREGAR INFORMACIÓN COMUNICARSE CON SU ZONAL DE COBRANZA </b><br></p>
					<p align="center"><br><input class="boton" type="button" value="Imprimir" id="imprimir" onClick="window.print()" />	</p>
				</div>
				
				<c:choose>
				<c:when test="${insolvencia=='X' }">
					<input class="boton" type="button" value="Imprimir..." id="insolvente" />
				</c:when>
				<c:otherwise>
					<input class="boton" type="button" value="Imprimir" id="imprimir" onClick="window.print()" />
				</c:otherwise>
				</c:choose>
				<input class="boton" type="button"
					value="Volver" onclick="window.history.back()" />
				<p style="height: 40px">&nbsp;</p>
			</div>
		</div>
	</div>
	<script>
		
		$(document).ready(function(){
			$( "#insolvente" ).click(function() {
  				$( "#datosInsolvencia" ).show();
  				$( "#insolvente" ).hide();
			});
		});	
	</script>
</body>
</html:html>