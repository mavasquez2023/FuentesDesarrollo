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
				Consulta de nómina</td>

		</tr>
	</table>

	<form action="seguimientoTEF.do" method="post" style="margin-top: 10px">
		<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
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
						<c:forEach items="${estados}" var="item">
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
					&nbsp;
				</td>
				
			</tr>
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					&nbsp;Banco:
					<select name="banco" id="banco" class="form-control">
						<option value="">BANCO</option>
						<c:forEach items="${bancos}" var="item">
							<option value="${item}"
							<c:if test="${item==bancoConsulta}">
								 selected="selected"
							</c:if>
							>${item}</option>
	
							
						</c:forEach>
					</select>
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					&nbsp;Código Nómina:
					<input type="text" value="${codigoConsulta }" id="codigoNomina" name="codigoNomina"
					class="form-control" maxlength="15" onkeypress="keyNum()" />
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
					<input type="submit" value="buscar" id="buscar"
					class="btn btn-primary" />
					<input type="reset" value="limpiar" id="limpiar"
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
	<c:if test="${nominasCabecera.size()>0}">
	<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
		<thead>
			<tr>
				<th class="certificadoCenter" style="font-size: 16px; height: 30px">Fecha carga 
					<a href="<c:url value='/ordenarLista.do?order=FechaCreacion:asc' />" ><img title="ascendente" src="resources/img/ascsort.gif"/ style="margin-bottom: 10px"></a>
					<a href="<c:url value='/ordenarLista.do?order=FechaCreacion:desc' />" ><img title="descendente" src="resources/img/descsort.gif"/ style="margin-bottom: 10px"></a>
				</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Convenio</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Producto</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Banco</th>
				<th class="certificadoCenter">Código 
					<a href="<c:url value='/ordenarLista.do?order=CodigoNomina:asc' />" ><img title="ascendente" src="resources/img/ascsort.gif"/ style="margin-bottom: 10px"></a>
					<a href="<c:url value='/ordenarLista.do?order=CodigoNomina:desc' />" ><img title="descendente" src="resources/img/descsort.gif"/ style="margin-bottom: 10px"></a>
				</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Cantidad</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Monto($)</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Estado</th>
				<th class="certificadoCenter" style="font-size: 16px; ">Ver Detalle</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${nominasCabecera}" var="cabecera">
				<tr>
					 
					<td class="certificadoCenter" ><fmt:formatDate pattern = "dd-MM-yyyy" value="${cabecera.fechaCreacion }"/>  </td>
					<td class="certificadoLeft" ><c:set var="convenio_form" value="${cabecera.convenio }"/> <%=DescripcionCodigo.getConvenio((String)pageContext.getAttribute("convenio_form"))%></td>
					<td class="certificadoLeft" ><c:set var="producto_form" value="${cabecera.producto }"/> <%=DescripcionCodigo.getProducto((String)pageContext.getAttribute("producto_form"))%></td>
					<td class="certificadoCenter">${cabecera.codigoBanco}</td>
					<td class="certificadoRight">${cabecera.codigoNomina}</td>
					<td class="certificadoRight" >${cabecera.cantidad }</td>
					<td class="certificadoRight" ><fmt:formatNumber type="number"
							maxFractionDigits="0" value="${cabecera.monto }" /></td>
					<td class="certificadoCenter" ><c:set var="estado_form" value="${cabecera.estadoNomina }"/> <%=DescripcionCodigo.getEstado(String.valueOf(pageContext.getAttribute("estado_form")))%></td>
					<td class="certificadoCenter" ><a href="<c:url value='/detalleTEF.do?id=${cabecera.idCabecera }' />">Ver</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	
	<br>
	</c:if>
	<c:if test="${nominasCabecera.size()==0}">
		<br>
		<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
			<tr>
					 
					<th class="certificadoCenter" style="font-size: 16px; " >No se han encontrado nóminas para el filtro aplicado.</th>
			</tr>
					
		</table>
	</c:if>
	</form>
	
	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	<script src="resources/js/corev2.js"></script>
	<script src="resources/js/jquery-ui-1.9.2.custom.js"></script>
	

	
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
    		
		});
	</script>
</body>
</html>