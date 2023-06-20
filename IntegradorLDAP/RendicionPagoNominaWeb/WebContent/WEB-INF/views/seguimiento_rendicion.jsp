<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="cl.laaraucana.rendicionpagonomina.utils.DescripcionCodigo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="comun/header.jsp" flush="true" />
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
	<title>Seguimiento Transferencias - La Araucana</title>
<link rel="stylesheet" href="assets/css/fln.css" />
<link rel="stylesheet" href="resources/css/jquery-ui-1.9.2.custom.css" />
<script>
	
	function RendicionxFecha(){
		fln.preloader(1);
		$('#rendicionFecha').submit();
	}
	function RendicionxNomina(){
		fln.preloader(1);
		$('#rendicionNomina').submit();
	}
	
	function cancelar() {
		$('#aviso').fadeOut();
		$("#btn_ejecutar_rendicion").fadeIn();
	}
	function aprobar() {
		fln.preloader(1);
		$("#rendicionAll").submit();
	}
	function aprobarBCI() {
		fln.preloader(1);
		var selected = "";
		var options = document.getElementsByName("nominasBCISeleccionadas");
		if(options.length == 0)
			return;
		for(var i=0; i < options.length; i++){
			if(options[i].checked){
				selected = selected + options[i].value +";";
			}
		}
		document.getElementById("nominasBCISelected").value=selected;

		$("#rendicionBCI").submit();
	}
</script>
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
				Seguimiento transferencias rendición</td>

		</tr>
	</table>

	<form action="seguimientoTEF.do" method="get" style="margin-top: 10px">
		<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
		<tr><td>
		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF; width: 90%">
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;width: 40%">
					Fecha Carga:&nbsp;<input type="text" value="${fechaConsulta }" id="fechaCarga" name="fechaCarga"
					class="form-control" maxlength="10" onkeypress="keyNum()" onkeyup="formateaFecha(this)"/>
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
	
	
	<c:if test="${nominasCabecera.size()>0}">
	<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
		<thead>
			<tr>
				<th class="certificadoCenter">Fecha carga 
					<a href="<c:url value='/ordenarLista.do?order=FechaCreacion:asc' />" ><img title="ascendente" src="resources/img/ascsort.gif"/ style="margin-bottom: 10px"></a>
					<a href="<c:url value='/ordenarLista.do?order=FechaCreacion:desc' />" ><img title="descendente" src="resources/img/descsort.gif"/ style="margin-bottom: 10px"></a>
				</th>
				<th class="certificadoCenter">Convenio</th>
				<th class="certificadoCenter">Producto</th>
				<th class="certificadoCenter">Banco</th>
				<th class="certificadoCenter">Código 
					<a href="<c:url value='/ordenarLista.do?order=CodigoNomina:asc' />" ><img title="ascendente" src="resources/img/ascsort.gif"/ style="margin-bottom: 10px"></a>
					<a href="<c:url value='/ordenarLista.do?order=CodigoNomina:desc' />" ><img title="descendente" src="resources/img/descsort.gif"/ style="margin-bottom: 10px"></a>
					</th>
				<th class="certificadoCenter">N° Registros</th>
				<th class="certificadoCenter">Monto</th>
				<th class="certificadoCenter">Estado</th>
				<th class="certificadoCenter">Ver Detalle</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${nominasCabecera}" var="nom">
				<tr>
					<td class="certificadoLeft"><fmt:formatDate pattern = "dd-MM-yyyy" value="${nom.fechaCreacion}"/> </td>
					<td class="certificadoLeft"><c:set var="convenio_form" value="${nom.convenio}"/> <%=DescripcionCodigo.getConvenio((String)pageContext.getAttribute("convenio_form"))%></td>
					<td class="certificadoLeft"><c:set var="producto_form" value="${nom.producto}"/> <%=DescripcionCodigo.getProducto((String)pageContext.getAttribute("producto_form"))%></td>
					<td class="certificadoCenter">${nom.codigoBanco}</td>
					<td class="certificadoRight">${nom.codigoNomina}</td>
					<td class="certificadoRight">${nom.cantidad}</td>
					<td class="certificadoRight">$<fmt:formatNumber type="number"
							maxFractionDigits="0" value="${nom.monto}" /></td>
					<td class="certificadoCenter"><c:set var="estado_form" value="${nom.estadoNomina}"/> <%=DescripcionCodigo.getEstado(String.valueOf(pageContext.getAttribute("estado_form")))%></td>
					<td class="certificadoCenter"><a href="<c:url value='/detalleTEF.do?id=${nom.idCabecera}' />">Ver</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	</c:if>
	<c:if test="${nominasCabecera.size()==0}">
		<br>
		<table align="center" class="tabla-creditos" style="width: 800px; margin-left: 330px">
			<tr>
					 
					<th class="certificadoCenter" style="font-size: 16px; " >No se han encontrado nóminas para el filtro aplicado.</th>
			</tr>
					
		</table>
	</c:if>
	<br>
	<div id="rendicion_manual" style="display: none">
<form action="processFecha.do" method="post" id="rendicionFecha">
	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF; margin-left: 330px">
		<tr>
			<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">Ingresa Fecha Desde:</td>
			<td class="bienvenida"
				style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: right;"><input class="form-control" name="fechaDesde" id="fechaDesde" value="dd-mm-aaaa"/ onclick="this.value=''"> </td>
		</tr>
		<tr>
			<td class="bienvenida"
				style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">Ingresa Fecha Hasta:</td>
			<td class="bienvenida"
				style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: right;"><input class="form-control" name="fechaHasta" id="fechaHasta" value="dd-mm-aaaa"/ onclick="this.value=''"> </td>
			<td class="bienvenida"
				style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: right;"><a
				href="#" onclick="RendicionxFecha();"><input
					style="cursor: pointer" type="button" class="btn btn-primary"
					value="&nbsp;Bajar por Fecha&nbsp;" /></a></td>
		</tr>
	</table>
</form>
<form action="processNomina.do" method="post" id="rendicionNomina">
	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF; margin-left: 330px">
		<tr>
			<td class="bienvenida"
				style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">Ingresa Núm. Nómina:</td>
			<td class="bienvenida"
				style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: right;"><input class="form-control" name="numNomina" id="numNomina" /> </td>
			<td class="bienvenida"
				style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: right;"><a
				href="#" onclick="RendicionxNomina();"><input
					style="cursor: pointer" type="button" class="btn btn-primary"
					value="Bajar por Número" /></a></td>
		</tr>
	</table>
</form>
	</div>
<form action="processNominayFecha.do" method="post" id="rendicionAll">
	<div id="row">
	<div class="col xs12 lg8">
							<div class="alerta alerta--aviso confirmar-general" id="aviso" data-tipo="screen"
								style="display: none;position:absolute; left: 300px; right: 400px; top: -280px;"></div>
	</div>
	</div>
	<p align="center">
		<a href="#">
				<input class="btn btn-primary" type="button" name="aceptar" id="btn_ejecutar_rendicion" value="Ejecutar Rendición BES... "/>
				<input class="btn btn-primary" type="button" name="ejecutar_rendicion" id="ejecutar_rendicion" style="display: none" value="Ejecutar Rendición BES"/>
		</a>
	</p>
</form>	


<c:if test="${nominasBCI.size()>=1 }">
<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF; margin-left: 330px">
		<tr>
			<td class="bienvenida"
				style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
				Nóminas BCI Para Rendir</td>

		</tr>
	</table>
	

	<form action="rendicionBCI.do" method="post" id="rendicionBCI">
		<input type="hidden" name="nominasBCISelected" id="nominasBCISelected" value="" />
		<table align="center" class="tabla-nominas-bci" style="width: 700px; margin-left: 330px">
			<thead>
				<tr>
					<th align="left">Nombre Archivo</th>
					<th align="left">Tipo</th>
					<th align="left">Estado</th>
					<th align="center">Seleccionar</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${nominasBCI.size()==0 }">
					
						<tr> 
								<td colspan="4" align="center"> No se encontraron Nóminas.</td>
						</tr>
				</c:if>
				
				<c:if test="${nominasBCI.size()>=1 }">
					<c:forEach items="${nominasBCI}" var="nominaBCI">
						<tr>
							<td  align="center"> ${nominaBCI.file}  </td>
							<td  align="center"> ${nominaBCI.type}  </td>
							<td  align="center"> ${nominaBCI.status}  </td>
							<td>
								<c:if test="${nominaBCI.enabled}">
									<input type="checkbox" name="nominasBCISeleccionadas" value="${nominaBCI.file}::${nominaBCI.type}" />
								</c:if>
								<c:if test="${nominaBCI.enabled== false}">
									<input type="checkbox" name="nominasBCISeleccionadas" value="${nominaBCI.file}::${nominaBCI.type}" disabled="true"/>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
	
		</table>
		
		<br/>
	
	
	
	
		<div id="row">
		<div class="col xs12 lg8">
								<div class="alerta alerta--aviso confirmar-general" id="aviso" data-tipo="screen"
									style="display: none;position:absolute; left: 300px; right: 400px; top: -280px;"></div>
		</div>
		</div>
		<p align="center">
			
			<a href="#">
					<input class="btn btn-primary" type="button" name="ejecutar_rendicion_BCI" id="ejecutar_rendicion_BCI" style="display: inline" value="Ejecutar Rendición BCI"/>
			</a>
		</p>
	</form>	
	
</c:if>
	
	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	<script src="resources/js/corev2.js"></script>
	<script src="resources/js/jquery-ui-1.9.2.custom.js"></script>
	
	
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
					$(function() {
						$("#fechaDesde").datepicker({
							maxDate : currentTime
						});
						
					}); //fin del datepicker
					$(function() {
						$("#fechaHasta").datepicker({
							maxDate : currentTime
						});
						
					}); //fin del datepicker
					$( "#btn_ejecutar_rendicion" ).click(function() {
  			
						$( "#rendicion_manual" ).fadeIn();
						$( "#btn_ejecutar_rendicion" ).hide();
						$( "#ejecutar_rendicion" ).show();
					});
				}); // fin del document ready
				
	</script>
	<script>
		$( "#ejecutar_rendicion" ).click(function() {
  			avisar('<p align="center" style="margin-top: 30px; margin-bottom: 20px;color:#F9ED5F"><b>Rendición nóminas "En Proceso" más rendición del día <br><br> ¿Está seguro que desea ejecutar la operación?</b><br></p>'
		+ '<p align="center" style="margin-top: 10px; margin-bottom: 5px">'
		+ '	<a id="botonAceptar" style="margin-left: 20px;height:40px; color:#ffffff; background: #1A9332; border-color:#1A9332" class="btn btn--terciario" href="#" onClick="javascript:aprobar();" >Aprobar</a>'
		+ '	&nbsp;&nbsp;<a id="botonCancelar" style="margin-left: 20px;height:40px; color:#000000; background: #cccccc; border-color:#cccccc"" class="btn btn--terciario" href="#" onClick="javascript:cancelar();">Cancelar</a>'
		+ '</p>'
	
		, 20000);
		$( "#rendicion_manual" ).hide();
		$( "#ejecutar_rendicion" ).hide();
		setTimeout('$( "#btn_ejecutar_rendicion" ).fadeIn()', 20000);
		});

		
		$( "#ejecutar_rendicion_BCI" ).click(function() {
  			avisar('<p align="center" style="margin-top: 30px; margin-bottom: 20px;color:#F9ED5F"><b>Rendición nóminas BCI <br><br> ¿Está seguro que desea ejecutar la operación?</b><br></p>'
		+ '<p align="center" style="margin-top: 10px; margin-bottom: 5px">'
		+ '	<a id="botonAceptar" style="margin-left: 20px;height:40px; color:#ffffff; background: #1A9332; border-color:#1A9332" class="btn btn--terciario" href="#" onClick="javascript:aprobarBCI();" >Aprobar</a>'
		+ '	&nbsp;&nbsp;<a id="botonCancelar" style="margin-left: 20px;height:40px; color:#000000; background: #cccccc; border-color:#cccccc"" class="btn btn--terciario" href="#" onClick="javascript:cancelar();">Cancelar</a>'
		+ '</p>'
	
		, 20000);
		//$( "#rendicion_manual" ).hide();
		//$( "#ejecutar_rendicion" ).hide();
		//setTimeout('$( "#btn_ejecutar_rendicion" ).fadeIn()', 20000);
		});

		
		
	</script>
</body>
</html>