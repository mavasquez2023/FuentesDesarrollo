<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="./comun/header.jsp" flush="true" />
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
	<title>Carga Pagos Manual - La Araucana</title>
<link rel="stylesheet" href="assets/css/fln.css" />

</head>
<body>
	<jsp:include page="banner.jsp" flush="true" />
	<jsp:include page="menuServices.jsp" flush="true" />

	<br />

	<table align="center" class="tabla-creditos"
		style="border: 1 px; border-color: #FFFFFF; margin-left: 330px">
		<tr>
			<td class="bienvenida"
				style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
				Carga pagos vía transferencia en forma manual.</td>

		</tr>
	</table>
	<form action="subir.do" id="subir_form" method="post" enctype="multipart/form-data" style="margin-top: 10px">
		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF; width: 40%; margin-left: 330px">
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 16px; border-color: #FFFFFF; text-align: left;">
					<select name="convenio" id="convenio" class="form-control">
						<option value="" selected="selected">CONVENIOS</option>
						<c:forEach items="${convenios}" var="item">
							<option value="${item.codigoConvenio}_${item.descripcionConvenio}">${item.descripcionConvenio}</option>
						</c:forEach>
					</select>
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 16px; border-color: #FFFFFF; text-align: left;">
					<select name="producto" id="producto" class="form-control">
						<option value="" selected="selected">PRODUCTO</option>
					</select>
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
			<tr>
				<td class="bienvenida" colspan="2"
					style="border: 0 px; font-size: 16px; border-color: #FFFFFF; text-align: left;">
					Subir archivo: 
					<input
							class="documento__input" type="file" id="manual" name="manual"
							data-maxsize="5000"
							accept=".csv, text/plain" />
					<input type="button" value="subir" id="subir"
					class="btn btn-primary" disabled="disabled"/>


				</td>
			</tr>
		</table>
		
	</form>
	
	<c:if test="${resumen!=null}">
	<form action="confirmar.do" method="post" name="confirmar" id="confirmar" style="margin-top: 30px">
	<table align="center" class="tabla-creditos" style="width: 400px; margin-left: 330px">
		<thead>
			<tr>
				<th class="certificadoCenter" colspan="2" style="font-size: 16px; height: 30px">Resumen de Carga</th>

			</tr>
		</thead>
		<tbody>
		
				<tr>
					<td class="certificadoCenter" colspan="2" >CONVENIO:&nbsp;${resumen.convenio} &nbsp;/&nbsp;
					PRODUCTO:&nbsp;${resumen.producto}</td>
				</tr>
				<tr>
					<td class="certificadoLeft" >Cantidad de Registros: </td>
					<td class="certificadoLeft"><fmt:formatNumber type="number"
							maxFractionDigits="0" value="${resumen.cantidadRegistros}" /></td>
				</tr>
				<tr>
					<td class="certificadoLeft">Monto Nómina:</td>
					<td class="certificadoLeft">$<fmt:formatNumber type="number"
							maxFractionDigits="0" value="${resumen.montoNomina}" /></td>
				</tr>	
				<tr>
					<td class="certificadoLeft">Sin Mandato:</td>
					<td class="certificadoLeft"><fmt:formatNumber type="number"
							maxFractionDigits="0" value="${resumen.sinmandato}" />
							<c:if test="${resumen.sinmandato>0}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<c:url value='/sinmandato.do' />" onclick="unloadEvent(false);"><input type="button" name="descargar" value="descargar"/></a>
							</c:if>
							</td>
				</tr>
				<c:if test="${resumen.listaErrores.size()>0}">
				<tr>
					<td class="certificadoLeft">Con Errores:</td>
					<td class="certificadoLeft"><fmt:formatNumber type="number"
							maxFractionDigits="0" value="${resumen.listaErrores.size()}" />
							
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<c:url value='/conerrores.do' />" onclick="unloadEvent(false);"><input type="button" name="descargar" value="descargar" /></a>
							
							</td>
				</tr>
				</c:if>
				<tr>
					<td class="certificadoCenter" height="60px"><input class="btn btn-primary" type="submit" name="aceptar" value="CONFIRMAR"/></td>
					<td class="certificadoCenter"><a href="<c:url value='/cargamanual.do' />"><input class="btn btn-primary" type="button" name="cancelar" value="CANCEL"/></a></td>
				</tr>

		</tbody>

	</table>
	</form>
	</c:if>

	
	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	<c:if test="${rol==''}">
	<div class="alerta alerta--aviso" align="center" id="aviso" style="width: 680px; margin-left: 330px; margin-top: 30px"></div>
		<script>
		avisar('<b><h5>Usuario No autorizado para cargar archivos</h5></b>'
		, 5000);
	</script>
	</c:if>	
	<c:if test="${cargado=='OK'}">
	
	<div class="alerta alerta--exito" align="center" id="aviso" style="width: 680px; margin-left: 330px; margin-top: 30px"></div>

	<script>
		avisar('<b><h5>Archivo ${archivo_manual} cargado exitosamente!</h5></b>'
		, 5000);
	</script>
	</c:if>
	<c:if test="${cargado=='NOK'}">
	<div class="alerta alerta--error"  align="center" id="aviso" style="width: 680px; margin-left: 330px; margin-top: 30px"></div>
	<script>
		avisar('<b><h5>Error al cargar archivo ${archivo_manual}, intente nuevamente.</h5></b>'
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

							html += '<option value="' + data[i].codigoProducto + '_' + data[i].descripcionProducto + '">'
									+ data[i].descripcionProducto + '</option>';
						}
						html += '</option>';
						$('#producto').html(html);
					});
				});
		$('#producto').change(
				function() {
					$('#subir').prop( "disabled", false );
		});
		$('#subir').click(
				function() {
					if($('#manual').val()!=""){
						$('#subir_form').submit();
					}
		});
		function unloadEvent(mostrar){
					$(window).bind('beforeunload', function(){
					if(mostrar== false){
						$('#loading').hide();
					}else{
						$('#loading').show();
					}
					});
					//removeEventListener("beforeunload", beforeUnloadListener, {capture: true});
				}
	</script>
</body>
</html>