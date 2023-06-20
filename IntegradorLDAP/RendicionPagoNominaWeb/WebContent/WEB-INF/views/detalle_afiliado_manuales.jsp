<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="cl.laaraucana.rendicionpagonomina.utils.DescripcionCodigo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="./comun/header.jsp" flush="true" />
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
	<title>Seguimiento Pagos Manual - La Araucana</title>
<link rel="stylesheet" href="assets/css/fln.css" />
</head>
<body>
	<jsp:include page="banner.jsp" flush="true" />
	<jsp:include page="menuServices.jsp" flush="true" />

	<br />

	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF; margin-left: 330px">
		<tr>
			<td class="bienvenida"
				style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
				Seguimiento pagos con carga manual.</td>

		</tr>
	</table>

	<form action="seguimientoManual.do" method="post" style="margin-top: 10px">
	<table border="0" align="center"  style="width: 800px; border-color: #FFFFFF; margin-left: 330px">
	<tr><td class="bienvenida" style="border: 0 px; font-size: 16px; border-color: #FFFFFF; text-align: left;">
		Detalle Registro:
	</td></tr>
	</table>
	<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
		<tr><td>
		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF; width: 90%">
			<tr>
				<td class="bienvenida" width="50%"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Fecha Carga:&nbsp;<fmt:formatDate pattern = "dd-MM-yyyy" value="${cabecera.fechaCarga }"/>
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					&nbsp;
				</td>
				
			</tr>
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Convenio:&nbsp;<c:set var="convenio_form" value="${cabecera.convenio }"/> <%=DescripcionCodigo.getConvenio((String)pageContext.getAttribute("convenio_form"))%>
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Producto:&nbsp;<c:set var="producto_form" value="${cabecera.producto }"/> <%=DescripcionCodigo.getProducto((String)pageContext.getAttribute("producto_form"))%>
				</td>
				
			</tr>
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Cant.Registros:&nbsp;<fmt:formatNumber type="number"
							maxFractionDigits="0" value="${cabecera.totalRegistros }" />
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Monto:&nbsp;$<fmt:formatNumber type="number"
							maxFractionDigits="0" value="${cabecera.totalMonto }" />
				</td>
				
			</tr>
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Cant.Registros Pendientes:&nbsp;<fmt:formatNumber type="number"
							maxFractionDigits="0" value="${cabecera.totalPendientes }" />
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Monto Pendiente:&nbsp;$<fmt:formatNumber type="number"
							maxFractionDigits="0" value="${cabecera.montoPendiente }" />
				</td>
				
			</tr>
			<tr>

				<td class="bienvenida"
					style="border: 0 px; font-size: 10px; border-color: #FFFFFF; text-align: left;">
				&nbsp;</td>
			</tr>
		</table>
		</td></tr>
	</table>
	</form>
	
	<form action="confirmar.do" method="post" name="confirmar" id="confirmar" style="margin-top: -10px">
	<table border="0" align="center"  style="width: 800px; border-color: #FFFFFF; margin-left: 330px">
	<tr><td class="bienvenida" style="border: 0 px; font-size: 16px; border-color: #FFFFFF; text-align: left;">
		Detalle Afliado:
	</td></tr>
	</table>
	<c:if test="${detalle_afiliado!=null }">
	<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
		<thead>
			<tr>
				<th class="certificadoLeft" style="font-size: 14px; font-weight:normal; width: 30% ">Rut</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.rutAfiliado }-${detalle_afiliado.dvAfiliado }&nbsp;</td>
				<td class="certificadoLeft" style="width: 30%;" rowspan="13">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Estado:
					<table align="center" class="tabla-creditos" style="width: 80%;">
						<tr>
						<td class="certificadoLeft">
						<c:forEach items="${estadospago}" var="item">
							<input type="radio" name="estado" value="${item.codigo }" 
							<c:if test="${item.codigo==detalle_afiliado.estado}">
								checked="checked"
							</c:if>
							>${item.descripcion }
							<br>
						</c:forEach>
						</td>
						</tr>
					</table>
					<c:if test="${detalle_afiliado.estado=='2' || detalle_afiliado.estado=='4' }">
						<br>
						<p align="center"><a href="<c:url value='anularPago.do?id=${detalle_afiliado.idDetalle}' />"><input class="btn btn-primary" type="button" name="anular" value="Anular Pago"/></a></p>
					</c:if>
					<c:if test="${detalle_afiliado.estado=='5'}">
						<br>
						<p align="center"><a href="<c:url value='pendientePago.do?id=${detalle_afiliado.idDetalle}' />"><input class="btn btn-primary" type="button" name="anular" value="Dejar Pendiente"/></a></p>
					</c:if>
				</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Nombre</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.nombreAfiliado }</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Beneficio</th>
				<td class="certificadoLeft" style="font-size: 14px;"><c:set var="beneficio_form" value="${detalle_afiliado.beneficio }"/> <%=DescripcionCodigo.getBeneficio((String)(pageContext.getAttribute("beneficio_form")))%></td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Descripción del pago</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.descripcionPago }</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Referencia 1</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.referencia1}</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Referencia 2</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.referencia2}</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Estado</th>
				<td class="certificadoLeft" style="font-size: 14px;"><c:set var="estado_form" value="${detalle_afiliado.estado }"/> <%=DescripcionCodigo.getEstadoPago(String.valueOf(pageContext.getAttribute("estado_form")))%></td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Monto($)</th>
				<td class="certificadoLeft" style="font-size: 14px;"><fmt:formatNumber type="number"
							maxFractionDigits="0" value="${detalle_afiliado.montoPago }" /></td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Banco</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.bancoAfiliado }</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Número Cuenta</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.numeroCuenta }</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Tipo Cuenta</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.tipoCuenta }</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Correo</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.email }</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Descripción último rechazo banco</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.descripcionRechazo}</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Fecha transferencia banco</th>
				<td class="certificadoLeft" style="font-size: 14px;"><fmt:formatDate pattern = "dd-MM-yyyy, HH:mm:ss" value="${detalle_afiliado.fechaTransferencia }"/></td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Folio interno del banco</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.folioNomina}</td>
			</tr>
			
		</thead>
		<tbody>
	
		</tbody>

	</table>
	</c:if>
	<c:if test="${ detalle_afiliado==null}">
		<table align="center" class="tabla-creditos" style="width: 700px; margin-left: 330px">
		<thead>
			<tr>
				<th class="certificadoLeft" style="font-size: 14px; font-weight:normal; width: 30% ">Rut ${rutAfiliado} no encontrado en la nómina</th>
			</tr>
		</thead>
		</table>
	</c:if>
	<br>
	<table align="center" style="border: 0 px; border-color: #FFFFFF; width: 800px; margin-left: 330px">
	<tr>
	<td style="border: 0 px; border-color: #FFFFFF;">
		<a href="<c:url value='detalleManual.do?id=${cabecera.idCabecera}' />"><input class="btn btn-primary" type="button" name="volver" value="Volver"/></a>
	</td>
	</tr>
	</table>
	</form>
	
	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	
	<c:if test="${cargado=='OK'}">
	
	<div class="alerta alerta--exito" align="center" id="aviso" style="width: 680px; margin-left: 330px; margin-top: 30px"></div>

	<script>
		avisar('<b><h5>Pago RUT ${detalle.rutAfiliado }-${detalle.dvAfiliado } eliminado exitosamente!</h5></b>'
		, 5000);
	</script>
	</c:if>
	<c:if test="${cargado=='NOK'}">
	<div class="alerta alerta--error"  align="center" id="aviso" style="width: 680px; margin-left: 330px; margin-top: 30px"></div>
	<script>
		avisar('<b><h5>Error al eliminar pago RUT ${detalle.rutAfiliado }-${detalle.dvAfiliado } ,intente nuevamente.</h5></b>'
		, 5000);
	</script>
	</c:if>

</body>
</html>