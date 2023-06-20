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
	<title>Seguimiento TEF - La Araucana</title>
<link rel="stylesheet" href="assets/css/fln.css" />
<link rel="stylesheet" href="resources/css/jquery-ui-1.9.2.custom.css" />
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
				Búsqueda de afiliado</td>

		</tr>
	</table>

	<form action="seguimientoAfiliado.do" id="formSA" method="post" style="margin-top: 10px">
		<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
		<tr><td>
		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF; width: 90%">
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					&nbsp;Estado registro:
					<select name="estado" id="estado" class="form-control">
						<option value="">ESTADO PAGO</option>
						<c:forEach items="${estadosPago}" var="item">
						<option value="${item.codigo}" 
						<c:if test="${item.codigo==estadoPagoConsulta}">
							selected="selected"
						</c:if>
						>${item.descripcion}</option>
						
						</c:forEach>
					</select>
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					&nbsp;
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					&nbsp;
				</td>
				
			</tr>
			<tr>
				
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					&nbsp;Rut Afiliado:
					<input type="text" value="${rutConsulta }" id="rutAfiliado" name="rutAfiliado"
					class="form-control" maxlength="12" 
					onchange="validarRut();" 
					onKeyPress="keyRut();" 
					onKeyUp="formateaRut(this);" />
				</td>
				
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					&nbsp;Nombre Afiliado:
					<input type="text" value="${nombreConsulta }" id="nombreAfiliado" name="nombreAfiliado"
					class="form-control" maxlength="50"   />
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: right;">
					<br>
					<input type="button" value="buscar" id="buscar"
					class="btn btn-primary" />
					<input type="button" value="limpiar" id="limpiar"
					class="btn btn-primary" />
				</td>
			</tr>
			<tr>

				<td class="bienvenida"
					style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
				&nbsp;</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">

				</td>
			</tr>
		</table>
		</td></tr>
	</table>
	<c:if test="${nominasDetalle.size()>0}">
	<table border="0" align="center"  style="width: 800px; border-color: #FFFFFF; margin-left: 330px">
	<tr><td class="bienvenida" style="border: 0 px; font-size: 16px; border-color: #FFFFFF; text-align: left;">
		Cantidad de Registros encontrados: <b><fmt:formatNumber type="number"
							maxFractionDigits="0" value="${cantidadRegistros }" /></b>
	</td></tr>
	</table>
	
	<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
		<thead>
			<tr>
				<th class="certificadoCenter" style="font-size: 16px;width: 100px; height: 30px">Rut</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Nombre</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Fecha</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Monto($)</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Forma Pago</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Banco</th>
				<c:if test="${estadoPago==4 }">
				<th class="certificadoCenter" style="font-size: 16px; ">Desc. Rechazo</th>
				</c:if>
				<th class="certificadoCenter" style="font-size: 16px; ">Estado</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Ref1/Ref2</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Ver Detalle</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${nominasDetalle}" var="detalle">
				<tr>
					 
					<td class="certificadoRight" >${detalle.rutAfiliado }-${detalle.dvAfiliado }&nbsp;</td>
					<td class="certificadoLeft" >${detalle.nombres }</td>
					<td class="certificadoCenter" ><fmt:formatDate pattern = "dd-MM-yyyy" value="${detalle.fechaCambio }"/></td>
					<td class="certificadoRight" ><fmt:formatNumber type="number"
							maxFractionDigits="0" value="${detalle.monto }" /></td>
					<td class="certificadoCenter" >${detalle.descripcionFormaPago }</td>
					<td class="certificadoCenter" >${detalle.descripcionBanco }</td>
					<c:if test="${estadoPago==4 }">
					<td class="certificadoCenter" >${detalle.descripcionRechazo }</td>
					</c:if>
					<td class="certificadoCenter" ><c:set var="estado_form" value="${detalle.estadoPago }"/> <%=DescripcionCodigo.getEstadoPago(String.valueOf(pageContext.getAttribute("estado_form")))%></td>
					<td class="certificadoCenter" >${detalle.referencia1 }/${detalle.referencia2}</td>
					<td class="certificadoCenter" ><a href="<c:url value='/detalleAfiliado.do?id=${detalle.idDetalle }' />">Ver</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	
	<br>
	</c:if>
	<c:if test="${nominasDetalle.size()==0}">
		<br>
		<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
			<tr>
					 
					<th class="certificadoCenter" style="font-size: 16px; " >No se han encontrado registros para el filtro aplicado.</th>
			</tr>
					
		</table>
	</c:if>
	</form>
	
	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	<script src="resources/js/corev2.js"></script>
	<script src="resources/js/jquery-ui-1.9.2.custom.js"></script>
	
	<script>
	$(document).ready(function() {
			$('#rutAfiliado').keyup(function(){
        		$(this).val($(this).val().toUpperCase());
        		$('#nombreAfiliado').val("");
    		});
    		$('#nombreAfiliado').keyup(function(){
        		$(this).val($(this).val().toUpperCase());
        		$('#rutAfiliado').val("");
    		});
    		$('#limpiar').click(function(){
        		$('#nombreAfiliado').val("");
        		$('#rutAfiliado').val("");
        		$('#estado').val("");
    		});
    		$('#buscar').click(function(){
    			if($('#rutAfiliado').val() != "" || $('#nombreAfiliado').val()!= ""){
        			$('#formSA').submit();
        		}
        		
    		});
		});
	</script>
</body>
</html>