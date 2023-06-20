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
	<title>Seguimiento Transferencias - La Araucana</title>
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
				Seguimiento transferencias.</td>

		</tr>
	</table>

	<form action="seguimientoTEF.do" method="post" style="margin-top: 10px">
	<table border="0" align="center"  style="width: 800px; border-color: #FFFFFF; margin-left: 330px">
	<tr><td class="bienvenida" style="border: 0 px; font-size: 16px; border-color: #FFFFFF; text-align: left;">
		Detalle Registro:
	</td></tr>
	</table>
	<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
		<tr><td width="65%">
		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF; width: 90%">
			<tr>
			
				<td class="bienvenida" width="50%"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Fecha Carga:&nbsp;<b><fmt:formatDate pattern = "dd-MM-yyyy" value="${cabecera_tef.fechaCreacion }"/></b>
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Estado Nómina :&nbsp;<b><c:set var="estadonom_form" value="${cabecera_tef.estadoNomina }"/> <%=DescripcionCodigo.getEstado(String.valueOf(pageContext.getAttribute("estadonom_form")))%></b> 
				</td>
				
			</tr>
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Convenio:&nbsp;<c:set var="convenio_form" value="${cabecera_tef.convenio }"/> <%=DescripcionCodigo.getConvenio((String)pageContext.getAttribute("convenio_form"))%>
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Producto:&nbsp;<c:set var="producto_form" value="${cabecera_tef.producto }"/> <%=DescripcionCodigo.getProducto((String)pageContext.getAttribute("producto_form"))%>
				</td>
				
			</tr>
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Código Nómina:&nbsp;${cabecera_tef.codigoNomina }
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Nombre Archivo:&nbsp;${cabecera_tef.nombreNomina }
				</td>
				
			</tr>
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Banco:&nbsp;${cabecera_tef.codigoBanco }
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Glosa Rechazo:&nbsp;${cabecera_tef.glosaRechazoRendicion }
				</td>
				
			</tr>
		</table>
		
		</td>
		<td>
			<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF;">
			<tr>
				<th class="certificadoCenter" style="font-size: 16px;width: 100px; height: 30px">&nbsp;
				</th>
				<th class="certificadoCenter" style="font-size: 16px;width: 100px; height: 30px">Cantidad
				</th>
				<th class="certificadoCenter" style="font-size: 16px;width: 100px; height: 30px">Monto
				</th>
			</tr>
			<tr>
				<th class="certificadoCenter" style="font-size: 16px;width: 100px; height: 30px">Totales
				</th>
				<td class="bienvenida"
					style="font-size: 14px; border-color: #cccccc; text-align: right;">
					<a href="<c:url value='/detalleEstado.do?id=${cabecera_tef.idCabecera }&estado=1' />">
					<fmt:formatNumber type="number" maxFractionDigits="0" value="${cabecera_tef.cantidad }" />
					</a>
				</td>
				<td class="bienvenida"
					style="font-size: 14px; border-color: #cccccc; text-align: right;">
					$<fmt:formatNumber type="number" maxFractionDigits="0" value="${cabecera_tef.monto }" />
				</td>
				
			</tr>
			<tr>
				<th class="certificadoCenter" style="font-size: 16px;width: 100px; height: 30px"><c:set var="estado_form" value="1"/> <%=DescripcionCodigo.getEstadoPago(String.valueOf(pageContext.getAttribute("estado_form")))%>
				</th>
				<td class="bienvenida"
					style="font-size: 14px; border-color: #cccccc; text-align: right;"><a href="<c:url value='/detalleEstado.do?id=${cabecera_tef.idCabecera }&estado=1' />">
					<fmt:formatNumber type="number" maxFractionDigits="0" value="${cabecera_tef.cantidadPagado }" /></a>
				</td>
				<td class="bienvenida"
					style="font-size: 14px; border-color: #cccccc; text-align: right;">
					$<fmt:formatNumber type="number" maxFractionDigits="0" value="${cabecera_tef.totalPagado }" />
				</td>
				
			</tr>
			<tr>
				<th class="certificadoCenter" style="font-size: 16px;width: 100px; height: 30px"><c:set var="estado_form" value="3"/> <%=DescripcionCodigo.getEstadoPago(String.valueOf(pageContext.getAttribute("estado_form")))%>
				</th>
				<td class="bienvenida"
					style="font-size: 14px; border-color: #cccccc; text-align: right;"><a href="<c:url value='/detalleEstado.do?id=${cabecera_tef.idCabecera }&estado=3' />">
					<fmt:formatNumber type="number" maxFractionDigits="0" value="${cabecera_tef.cantidad - cabecera_tef.cantidadPagado - cabecera_tef.cantidadRechazado - cabecera_tef.cantidadDevuelto }" /></a>
				</td>
				<td class="bienvenida"
					style="font-size: 14px; border-color: #cccccc; text-align: right;">
					$<fmt:formatNumber type="number" maxFractionDigits="0" value="${cabecera_tef.monto-cabecera_tef.totalPagado-cabecera_tef.totalRechazado - cabecera_tef.totalDevuelto }" />
				</td>
				
			</tr>
			<tr>
				<th class="certificadoCenter" style="font-size: 16px;width: 100px; height: 30px"><c:set var="estado_form" value="4"/> <%=DescripcionCodigo.getEstadoPago(String.valueOf(pageContext.getAttribute("estado_form")))%>
				</th>
				<td class="bienvenida"
					style="font-size: 14px; border-color: #cccccc; text-align: right;">
					<a href="<c:url value='/detalleEstado.do?id=${cabecera_tef.idCabecera }&estado=4' />">
					<fmt:formatNumber type="number" maxFractionDigits="0" value="${cabecera_tef.cantidadRechazado }" />
					</a>
				</td>
				<td class="bienvenida"
					style="font-size: 14px; border-color: #cccccc; text-align: right;">
					$<fmt:formatNumber type="number"
							maxFractionDigits="0" value="${cabecera_tef.totalRechazado }" />
				</td>
				
			</tr>
			<tr>
				<th class="certificadoCenter" style="font-size: 16px;width: 100px; height: 30px"><c:set var="estado_form" value="6"/> <%=DescripcionCodigo.getEstadoPago(String.valueOf(pageContext.getAttribute("estado_form")))%>
				</th>
				<td class="bienvenida"
					style="font-size: 14px; border-color: #cccccc; text-align: right;">
					<a href="<c:url value='/detalleEstado.do?id=${cabecera_tef.idCabecera }&estado=6' />">
					<fmt:formatNumber type="number" maxFractionDigits="0" value="${cabecera_tef.cantidadDevuelto}" />
					</a>
				</td>
				<td class="bienvenida"
					style="font-size: 14px; border-color: #cccccc; text-align: right;">
					$<fmt:formatNumber type="number" maxFractionDigits="0" value="${cabecera_tef.totalDevuelto }" />
				</td>
				
			</tr>
			
		</table>
		</td>
		</tr>
		
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
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Nombre</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.nombres }</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Correo</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.email }</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Monto</th>
				<td class="certificadoLeft" style="font-size: 14px;">$<fmt:formatNumber type="number"
							maxFractionDigits="0" value="${detalle_afiliado.monto }" /></td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Descripción del Pago</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.descripcionPago }</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Estado</th>
				<td class="certificadoLeft" style="font-size: 14px;"><c:set var="estado_form" value="${detalle_afiliado.estadoPago }"/> <%=DescripcionCodigo.getEstadoPago(String.valueOf(pageContext.getAttribute("estado_form")))%></td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Forma Pago</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.descripcionFormaPago }&nbsp;${detalle_afiliado.codigoFormaPago}</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Fecha Transacción</th>
				<td class="certificadoLeft" style="font-size: 14px;"><fmt:formatDate pattern = "dd-MM-yyyy, HH:mm:ss" value="${detalle_afiliado.fechaCambio }"/></td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Banco</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.descripcionBanco }&nbsp;${detalle_afiliado.codigoBanco }</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Número Cuenta</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.numerocuenta }</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Tipo Cuenta</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.tipoCuenta }</td>
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
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">Descripción Rechazo</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.descripcionRechazo}</td>
			</tr>
			<tr>
				<th class="certificadoLeft" style="font-size: 16px; font-weight:normal; ">IdReferencia</th>
				<td class="certificadoLeft" style="font-size: 14px;">${detalle_afiliado.idReferencia}</td>
			</tr>
		</thead>
		<tbody>
	
		</tbody>

	</table>
	</c:if>
	<c:if test="${ detalle_afiliado==null}">
		<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
		<thead>
			<tr>
				<th class="certificadoLeft" style="font-size: 14px; font-weight:normal; width: 30% ">Rut ${rutAfiliado} no encontrado en la nómina</th>
			</tr>
		</thead>
		</table>
	</c:if>
	<br>
	<c:if test="${operacion=='afiliado' }">
		<table align="center" style="border: 0 px; border-color: #FFFFFF; width: 800px; margin-left: 330px">
		<tr>
			<td style="border: 0 px; border-color: #FFFFFF;">
				<a href="javascript:history.back();"><input class="btn btn-primary" type="button" name="volver" value="Volver"/></a>
			</td>
		</tr>
		</table>
	</c:if>
	<c:if test="${operacion!='afiliado' }">
		<table align="center" style="border: 0 px; border-color: #FFFFFF; width: 800px; margin-left: 330px">
		<tr>
			<td style="border: 0 px; border-color: #FFFFFF;">
				<a href="<c:url value='detalleTEF.do?id=${cabecera_tef.idCabecera}' />"><input class="btn btn-primary" type="button" name="volver" value="Volver"/></a>
			</td>
		</tr>
		</table>
	
	</c:if>
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