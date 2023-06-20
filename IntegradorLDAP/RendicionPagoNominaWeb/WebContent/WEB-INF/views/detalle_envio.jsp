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
	<title>Seguimiento Transferencias- La Araucana</title>
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
				Seguimiento transferencias para envío</td>

		</tr>
	</table>

	<form action="seguimiento.do" method="post" style="margin-top: 10px">
	<table border="0" align="center"  style="width: 800px; border-color: #FFFFFF;margin-left: 330px">
	<tr><td class="bienvenida" style="border: 0 px; font-size: 16px; border-color: #FFFFFF; text-align: left;">
		Detalle Registros:
	</td></tr>
	</table>
	<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
		<tr><td>
		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF; width: 90%">
			<tr>
			
				<td class="bienvenida" width="50%"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					<c:if test="${estadoNomina==1}">
						Fecha Carga:&nbsp;<fmt:formatDate pattern = "dd-MM-yyyy" value="${cabecera_tef.fechaCreacion }"/>
					</c:if>
					<c:if test="${estadoNomina==2}">
						Fecha Envío:&nbsp;<fmt:formatDate pattern = "dd-MM-yyyy" value="${cabecera_tef.fechaEnvio }"/>
					</c:if>
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					&nbsp;
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
					Cant.Registros:&nbsp;<fmt:formatNumber type="number"
							maxFractionDigits="0" value="${cabecera_tef.cantidad }" />
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Monto:&nbsp;$<fmt:formatNumber type="number"
							maxFractionDigits="0" value="${cabecera_tef.monto }" />
				</td>
				
			</tr>
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Estado Nómina :&nbsp;<c:set var="estadonom_form" value="${cabecera_tef.estadoNomina }"/> <%=DescripcionCodigo.getEstado(String.valueOf(pageContext.getAttribute("estadonom_form")))%> 
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Nombre Nómina:&nbsp;${cabecera_tef.nombreNomina }
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
	<form action="detalleAfiliado.do" method="post" name="buscar" id="buscar" style="margin-top: 0px;">
	<table align="center" style="width: 800px; border:hidden; margin-left: 330px">
	<tr><td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;width: 30%;">
					Rut Afiliado:&nbsp;<input type="text" value="" id="rutAfiliado" name="rutAfiliado"
					class="form-control" maxlength="12" onkeypress="keyRut();" onkeyup="formateaRut(this);"/>
					
		</td>
		<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					<br><input type="submit" value="buscar" id="buscar"
					class="btn btn-primary" />
		</td>
		<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: right;">
					<br><a href="<c:url value='/seguimientoTEF.do?accion=volver' />"><input class="btn btn-primary" type="button" name="volver" value="Volver"/></a>
		</td>
	</tr>
	</table>
	</form>
	<form action="confirmar.do" method="post" name="confirmar" id="confirmar" style="margin-top: -10px">
	
	<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
		<thead>
			<tr>
				
				<th class="certificadoCenter" style="font-size: 16px;width: 100px; height: 30px">Rut</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Nombre</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Monto($)</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Tipo Pago</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Banco</th>
				<th class="certificadoCenter" style="font-size: 16px; ">N.Cuenta</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Estado</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Ref1/Ref2</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Ver Detalle</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${detalles_tef}" var="detalle">
				<tr>
					
					<td class="certificadoRight" >${detalle.rutAfiliado }-${detalle.dvAfiliado }&nbsp;</td>
					<td class="certificadoLeft" >${detalle.nombres }</td>
					<td class="certificadoRight" ><fmt:formatNumber type="number"
							maxFractionDigits="0" value="${detalle.monto }" /></td>
					<td class="certificadoCenter" >${detalle.codigoFormaPago }</td>
					<td class="certificadoCenter" >${detalle.codigoBanco }</td>
					<td class="certificadoCenter" >${detalle.numerocuenta }</td>
					<td class="certificadoCenter" ><c:set var="estado_form" value="${detalle.estadoPago }"/> <%=DescripcionCodigo.getEstadoPago(String.valueOf(pageContext.getAttribute("estado_form")))%></td>
					<td class="certificadoCenter" >${detalle.referencia1 }/${detalle.referencia2}</td>
					<td class="certificadoCenter" ><a href="<c:url value='/detalleAfiliado.do?id=${detalle.idDetalle }' />">Ver</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	<br>
	<table align="center" style="border: 0 px; border-color: #FFFFFF; width: 800px; margin-left: 330px">
		<tr>
			<td style="border: 0 px; border-color: #FFFFFF;">
				<a href="<c:url value='/seguimientoTEF.do?accion=volver' />"><input class="btn btn-primary" type="button" name="volver" value="Volver"/></a>
			</td>
		</tr>
	</table>
	</form>
	
	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	<script src="resources/js/corev2.js"></script>
	
</body>
</html>