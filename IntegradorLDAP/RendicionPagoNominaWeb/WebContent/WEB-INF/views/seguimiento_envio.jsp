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


<script type="text/javascript">
	function verDetalle(idCabecera){
	
		window.location= "<c:url value='/detalleTEF.do?id=' />" + idCabecera + "&fechaCarga=" + $("#fechaCarga").val() + "&estado=" + $("#estado").val();
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
				Seguimiento transferencias para envío
				</td>

		</tr>
	</table>

	<form action="envio.do" method="get" style="margin-top: 10px">
		<table align="center" class="tabla-creditos" style="width: 58%; margin-left: 330px">
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
					&nbsp;Estado:
					<select name="estado" id="estado" class="form-control">
						<c:forEach items="${estados}" var="item">
						<c:if test="${item.codigo==1 || item.codigo==2}">
							<option value="${item.codigo}" 
							<c:if test="${item.codigo==estadoConsulta}">
								selected="selected"
							</c:if>
							>${item.descripcion}</option>
						</c:if>
						</c:forEach>
					</select>
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 14px; border-color: #FFFFFF; text-align: left;">
					<br>
					<input type="submit" value="buscar" id="buscar" onclick="unloadEvent(true);"
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
	
	<c:if test="${codigo_respuesta=='1' || codigo_respuesta=='2'}">
		<div class="alerta alerta--exito"  align="center" id="aviso" style="width: 58%; margin-left:285px; margin-top: 10px; margin-bottom: 10px"></div>
	</c:if>
	<c:if test="${codigo_respuesta=='0' || codigo_respuesta=='-2'}">
		<div class="alerta alerta--error"  align="center" id="aviso" style="width: 58%; margin-left:285px; margin-top: 10px; margin-bottom: 10px"></div>
	</c:if>

	<form action="enviarFilesBancos.do" method="post" id="enviarFiles">
		<input type="hidden" name="nominasSelected" id="nominasSelected" value="" />
		
		<c:if test="${nominasCabecera.size()>0}">
		<table align="center"  style="width: 58%; margin-left: 330px">
			<thead>
				<tr>
					<th class="certificadoLeft">Fecha carga
						<a href="<c:url value='/ordenarLista.do?order=FechaCreacion:asc' />" ><img title="ascendente" src="resources/img/ascsort.gif"/ style="margin-bottom: 10px"></a>
						<a href="<c:url value='/ordenarLista.do?order=FechaCreacion:desc' />" ><img title="descendente" src="resources/img/descsort.gif"/ style="margin-bottom: 10px"></a>
					</th>
					<th class="certificadoLeft">Convenio</th>
					<th class="certificadoLeft">Producto</th>
					<th class="certificadoLeft">Banco</th>
					<th class="certificadoLeft">N° Registros</th>
					<th class="certificadoLeft">Monto</th>
					<th class="certificadoLeft">Estado</th>
					<th class="certificadoCenter">Detalle</th>
					<th class="certificadoCenter">Descargar</th>
					<c:if test="${estadoConsulta==1 && (rol=='operador' || rol=='admin')}">
					<th class="certificadoCenter">Autorizar</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${nominasCabecera}" var="nom">
					<tr>
						<td class="certificadoLeft"><fmt:formatDate pattern = "dd-MM-yyyy" value="${nom.fechaCreacion}"/> </td>
						<td class="certificadoLeft"><c:set var="convenio_form" value="${nom.convenio}"/> <%=DescripcionCodigo.getConvenio((String)pageContext.getAttribute("convenio_form"))%></td>
						<td class="certificadoLeft"><c:set var="producto_form" value="${nom.producto}"/> <%=DescripcionCodigo.getProducto((String)pageContext.getAttribute("producto_form"))%></td>
						<td class="certificadoLeft">${nom.codigoBanco}</td>
						<td class="certificadoRight">${nom.cantidad}</td>
						<td class="certificadoRight">$<fmt:formatNumber type="number"
								maxFractionDigits="0" value="${nom.monto}" /></td>
						<td class="certificadoLeft"><c:set var="estado_form" value="${nom.estadoNomina}"/> <%=DescripcionCodigo.getEstado(String.valueOf(pageContext.getAttribute("estado_form")))%></td>
						<td class="certificadoCenter">
								<a href="#" onclick="unloadEvent(true); verDetalle('${nom.idCabecera}');return false;"><img src="img/ver_detalle.png" width="24" height="24" alt="Ver Detalle" title="Ver Detalle" /></a>&nbsp;&nbsp;
						</td>
						<td class="certificadoCenter">
								<a href="<c:url value='/downloadPendiente.do?id=${nom.idCabecera}' />" onclick="unloadEvent(false);"><img src="img/descarga.png" width="24" height="24" alt="Descargar" title="Descargar" /></a>&nbsp;&nbsp;
						</td>
						<c:if test="${estadoConsulta==1 && (rol=='operador' || rol=='admin')}">
						<td class="certificadoCenter">
								<!-- a href="<c:url value='/detalleTEF.do?id=${nom.idCabecera}' />"><img src="img/enviar.png" width="24" height="24" alt="Enviar al Banco" title="Enviar al Banco" /></a-->
								<input type="checkbox" id="seleccion" name="seleccion" value=<c:out value="${nom.idCabecera}::${nom.producto}"/>  onclick="validarVisibilidadBoton()"/>
								
						</td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
	
		</table>
		
		<br>
		<c:if test="${estadoConsulta==1 && (rol=='operador' || rol=='admin')}">
		<p align="center">
			<!-- a href="<c:url value='/transferenciaEnvio.do' />" -->
			<a href="#">
					<input class="btn btn-primary" type="button" name="btnEnviar" id="btnEnviar" value="Ejecutar Envío de Archivos " disabled/>
			</a>
		</p>
		</c:if>
		</c:if>
		<c:if test="${nominasCabecera.size()==0}">
			<br>
			<table align="center" class="tabla-creditos" style="width: 58%; margin-left: 330px">
				<tr>
						 
						<th class="certificadoCenter" style="font-size: 16px; " >No se han encontrado nóminas para el filtro aplicado.</th>
				</tr>
						
			</table>
		</c:if>
		<br>
		<c:if test='${rol=="admin"}'>
		<p align="center">
			 <a href="<c:url value='/crontaEnvio.do' />">
					<input class="btn btn-primary" type="button" name="aceptar" value="Cargar Archivos para Envío"/>
			</a>
		</p>
		</c:if>
	</form>
	<!-- 
	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
				style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
				Nóminas BCI Disponibles para Enviar </td>

		</tr>
	</table>
	
	
	<form action="cargaBCI.do" method="post" id="cargaBCI">
		<input type="hidden" name="nominasBCISelected" id="nominasBCISelected" value="" />
		<table align="center" class="tabla-nominas-bci" style="width: 700px;">
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
					<input class="btn btn-primary" type="button" name="ejecutar_carga_BCI" id="ejecutar_carga_BCI" style="display: inline" value="Ejecutar Envio Licencias BCI"/>
			</a>
		</p>
	</form>	
	 -->
	
	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	<script src="resources/js/corev2.js"></script>
	<script src="resources/js/jquery-ui-1.9.2.custom.js"></script>
	<c:if test="${codigo_respuesta=='2'}">
			<script>
					avisar('<b><h5>Total archivos procesados: ${totalProcesados}</h5></b>'
					, 5000);
			</script>
	</c:if>
	<c:if test="${codigo_respuesta=='1'}">
			<script>
					avisar('<b><h5>Archivo ${archivo} enviado exitosamente!</h5></b>'
					, 5000);
			</script>
	</c:if>
	<c:if test="${codigo_respuesta=='0'}">
			<script>
				avisar('<b><h5>Error al enviar archivo ${archivo}, intente nuevamente.<br>Mensaje: ${mensaje}</h5></b>'
				, 5000);
			</script>
	</c:if>
	<c:if test="${codigo_respuesta=='-2'}">
			<script>
				avisar('<b><h5>Error al ejecutar Carga de archivos.<br>Mensaje: ${mensaje}</h5></b>'
				, 5000);
			</script>
	</c:if>
	
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
				
			function enviarBCI() {
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
				$("#cargaBCI").submit();
			}
				
			function validarVisibilidadBoton(){
				$("#btnEnviar").attr("disabled",true)
				var opciones = document.getElementsByName('seleccion');
				for(var i=0; i< opciones.length; i++){
					if(opciones[i].checked){
						$("#btnEnviar").attr("disabled",false)
					}
					
				}
			}	
				
			$("#btnEnviar").click(function() {
				var checked = "";
				var opciones = document.getElementsByName('seleccion');
				for(var i=0; i< opciones.length; i++){
					if(opciones[i].checked){
						checked = checked+ opciones[i].value +";";
					}
					
				}
				
				$("#nominasSelected").val(checked);
				$("#enviarFiles").submit();
			});
			
			
			$( "#ejecutar_carga_BCI" ).click(function() {
				var options = document.getElementsByName("nominasBCISeleccionadas");
				if(options.length == 0){
				
					alert("Seleccione al menos una nómina ");
					return;
				}
		  			avisar('<p align="center" style="margin-top: 30px; margin-bottom: 20px;color:#F9ED5F"><b>Envío de nóminas:  ¿Está seguro que desea ejecutar la operación?</b><br></p>'
				+ '<p align="center" style="margin-top: 10px; margin-bottom: 5px">'
				+ '	<a id="botonAceptar" style="margin-left: 20px;height:40px; color:#ffffff; background: #1A9332; border-color:#1A9332" class="btn btn--terciario" href="#" onClick="javascript:enviarBCI();" >Enviar</a>'
				+ '	&nbsp;&nbsp;<a id="botonCancelar" style="margin-left: 20px;height:40px; color:#000000; background: #cccccc; border-color:#cccccc"" class="btn btn--terciario" href="#" onClick="javascript:cancelar();">Cancelar</a>'
				+ '</p>'
			
				, 20000);
				//$( "#rendicion_manual" ).hide();
				//$( "#ejecutar_rendicion" ).hide();
				//setTimeout('$( "#btn_ejecutar_rendicion" ).fadeIn()', 20000);
				});

				
				function cargarEnvios(status){
					location.href = "envio.do?status="+status;
				}
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