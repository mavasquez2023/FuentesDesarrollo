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
				Seguimiento pagos con carga manual.</td>

		</tr>
	</table>

	<form action="seguimientoManual.do" method="post" style="margin-top: 10px">
		<table align="center" class="tabla-creditos" style="width: 52%; margin-left: 330px">
		<tr><td>
		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF; width: 90%">
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					Fecha Carga:&nbsp;<input type="text" value="${fechaConsulta }" id="fechaCarga" name="fechaCarga"
					class="form-control" maxlength="10" onkeypress="keyNum()" onkeyup="formateaFecha(this)"/>
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					&nbsp;Estado:
					<select name="estado" id="estado" class="form-control">
						<option value="">ESTADO</option>
						<c:forEach items="${estados_activos}" var="item">
						<option value="${item.codigo}" 
						<c:if test="${item.codigo==estadoConsulta}">
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
				
			</tr>
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					&nbsp;Convenio:
					<select name="convenio" id="convenio" class="form-control">
						<option value="">CONVENIO</option>
						<c:forEach items="${convenios}" var="item">
							<option value="${item.codigoConvenio}"
							<c:if test="${item.codigoConvenio==convenioConsulta}">
								 selected="selected"
							</c:if>
							>${item.descripcionConvenio}</option>
	
							
						</c:forEach>
					</select>
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					&nbsp;Producto:
					<select name="producto" id="producto" class="form-control">
						<option value="" >PRODUCTO</option>
					</select>
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					<br>
					<input type="submit" value="buscar" id="buscar"
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
	</form>
	
	<form action="confirmar.do" method="post" name="confirmar" id="confirmar" style="margin-top: -10px">
	<c:if test="${cabeceras_manual.size()>0}">
	<table align="center" class="tabla-creditos" style="width: 700px; margin-left: 330px">
		<thead>
			<tr>
				<th class="certificadoCenter" style="font-size: 16px; height: 30px">Fecha Carga</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Convenio</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Producto</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Cantidad</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Monto($)</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Estado</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Ver Detalle</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cabeceras_manual}" var="cabecera">
				<tr>
					 
					<td class="certificadoCenter" ><fmt:formatDate pattern = "dd-MM-yyyy" value="${cabecera.fechaCarga }"/>  </td>
					<td class="certificadoLeft" ><c:set var="convenio_form" value="${cabecera.convenio }"/> <%=DescripcionCodigo.getConvenio((String)pageContext.getAttribute("convenio_form"))%></td>
					<td class="certificadoLeft" ><c:set var="producto_form" value="${cabecera.producto }"/> <%=DescripcionCodigo.getProducto((String)pageContext.getAttribute("producto_form"))%></td>
					<td class="certificadoRight" >${cabecera.totalRegistros }</td>
					<td class="certificadoRight" ><fmt:formatNumber type="number"
							maxFractionDigits="0" value="${cabecera.totalMonto }" /></td>
					<td class="certificadoCenter" ><c:set var="estado_form" value="${cabecera.estado }"/> <%=DescripcionCodigo.getEstado(String.valueOf(pageContext.getAttribute("estado_form")))%></td>
					<td class="certificadoCenter" ><a href="<c:url value='/detalleManual.do?id=${cabecera.idCabecera }' />">Ver</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	
	<br>
	<p align="center">
		<a href="<c:url value='/transferencia.do' />">
				<input class="btn btn-primary" type="button" name="aceptar" value="Ejecutar Transferencia Manual "/>
		</a>
	</p>
	</c:if>
	<c:if test="${cabeceras_manual.size()==0}">
		<br>
		<table align="center" class="tabla-creditos" style="width: 800px;">
			<tr>
					 
					<th class="certificadoCenter" style="font-size: 16px; " >No se han encontrado nóminas para el filtro aplicado.</th>
			</tr>
					
		</table>
	</c:if>
	<c:if test="${files!=null && files.size()>0}">
						<div align="center" class="alerta alerta--exito" id="aviso" style="width: 52%;margin-left: 325px">
						<ul>
							<c:forEach items="${files}" var="item">
								<li><p class="adicional adicional--dos" style="font-size: 16px; " >Archivo:&nbsp;${item}&nbsp;generado.</p></li>
							</c:forEach>
						</ul>
						</div>

	</c:if>
	<c:if test="${files!=null && files.size()==0}">
						<div align="center" class="alerta alerta--aviso" id="aviso" style="width: 52%;margin-left: 325px">
						
							<p class="adicional adicional--dos" style="font-size: 16px; ">No existe información Pendiente para procesar.</p>
						
						</div>

	</c:if>
	</form>
	
	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	<script src="resources/js/corev2.js"></script>
	<script src="resources/js/jquery-ui-1.9.2.custom.js"></script>
	
	<c:if test="${cargado=='OK'}">
	
	<div class="alerta alerta--exito" align="center" id="aviso" style="width: 680px; margin-left: 330px; margin-top: 30px"></div>

	<script>
		avisar('<b><h5>Archivo ${archivo} cargado exitosamente!</h5></b>'
		, 5000);
	</script>
	</c:if>
	<c:if test="${cargado=='NOK'}">
	<div class="alerta alerta--error"  align="center" id="aviso" style="width: 680px; margin-left: 330px; margin-top: 30px"></div>
	<script>
		avisar('<b><h5>Error al cargar archivo ${archivo}, intente nuevamente.</h5></b>'
		, 5000);
	</script>
	</c:if>
<script type="text/javascript">
		$('#convenio').change(
				function() {
					$.getJSON('productos.do', {
						convenio : $(this).val(),
						ajax : 'true'
					}, function(data) {
						var html = '<option value="" selected="selected">PRODUCTO</option>';
						var len = data.length;
						for (var i = 0; i < len; i++) {
							
							html += '<option value="' + data[i].codigoProducto  + '">'
									+ data[i].descripcionProducto + '</option>';
									
						}
						html += '</option>';
						$('#producto').html(html);
					});
				});
			
			if($('#convenio').val()!=""){
			
					$.getJSON('productos.do', {
						convenio : $('#convenio').val(),
						ajax : 'true'
					}, function(data) {
						var html = '<option value="" >PRODUCTO</option>';
						var len = data.length;
						for (var i = 0; i < len; i++) {

							html += '<option value="' + data[i].codigoProducto  + '" '
									if (data[i].codigoProducto== '${productoConsulta}'){
								 		html += 'selected="selected"'
								 	}
							html +=	 '>'
									+ data[i].descripcionProducto + '</option>';
						}
						html += '</option>';
						$('#producto').html(html);
					});
			
			}
			
	</script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					
					var currentTime = new Date();
					//var inicioMes = new Date(currentTime.getFullYear(), currentTime.getMonth(), 1);
					// 10 days before next month
					var finMes = new Date(currentTime.getFullYear(),
							currentTime.getMonth() + 1, 0);
					// one day before next month
					//var endDateFrom = new Date(currentTime.getFullYear(), currentTime.getMonth() +1, 3); // 3rd of next month
					//var endDateTo = new Date(currentTime.getFullYear(), currentTime.getMonth() +1, 10); // 10th of next month
					$(function() {
						$("#fechaCarga").datepicker({
							maxDate : currentTime
						});
						
					}); //fin del datepicker
					
					
				}); // fin del document ready
				
	</script>
</body>
</html>