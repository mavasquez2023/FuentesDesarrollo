<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>CARGAS FAMILIARES NO CANCELADAS</title>
<script language="JavaScript 1.2" type="text/javascript">
function init(){
	<c:if test="${error==0}">
			mostrarInfo("Trabajador desvinculado exitosamente. ");
	</c:if>
	<c:if test="${error==-1}">
			mostrarWarning("No se han encontrado datos para el trabajador ${mensaje} ");
	</c:if>
	<c:if test="${error==1}">
			mostrarError("No es posible realizar la desvinculación en este momento. Intente mas tarde <br>${errorMsg}");
	</c:if>
}
function buscarTrabajador(){
	if(validaRut(form1.rutTrabajador.value)){
		document.getElementById("informacion").innerHTML = "";
		form1.accion.value="buscarTrabajador";
		form1.submit();
	}else{
		alertDialog('-- Ingrese un RUT válido --');
	}
	
}

function Volver(){	
	form1.accion.value="volver";
	form1.submit();
	
}

function confirmDialog(message) {
    $('<div></div>').appendTo('body')
    .html('<div><h5>'+message+'?</h5></div>')
    .dialog({
        modal: true, title: 'Cesación', zIndex: 10000, autoOpen: true,
        width: 'auto', resizable: false,
        buttons: {
            Si: function () {
            	form1.accion.value="cesarTrabajador";
				form1.submit();
                $(this).dialog("close");
            },
            No: function () {                                                               
                $(this).dialog("close");
            }
        }
    });
};


function Desvincular(){	
	if(validaFecha(form1.desvinculacion.value)){
		confirmDialog('Confirma cesará al trabajador con fecha ' + form1.desvinculacion.value);
	}else{
		alertDialog("Ingrese una fecha válida");
	}
}


</script>
</head>
<body onLoad="init();">
<script>
	var path= "<%=request.getContextPath()%>";
		$(document).ready(function() {
			$(".informacion").corner("6px");
			$(".informacion_error").corner("6px");
			$(".informacion_warning").corner("6px");
		 });

	</script>
<jsp:include page="banner.jsp" flush="true" />
<jsp:include page="menuServices.jsp" flush="true" />
<div style="position: relative; left: 100px; width: 1024px;">
	<p class="titulo">CESACIÓN TRABAJADOR</p>
	<form action="cesacion.do" name="form1" method="post">
	<p>	
				<table align="center" class="tabla-creditos">
					<tr>
						<th class="certificadoLeft">RUT Empresa</th>
						<td class="certificadoLeft">${rutEmpresa} &nbsp; ${razonSocial}</td>
					</tr>
					<tr>
						<th class="certificadoLeft">RUT Trabajador</th>
						<td class="certificadoLeft">
						<c:if test="${trabajador.rutTrabajador!=0}">
							${trabajador.rutTrabajador}-${trabajador.dvTrabajador} <input type="hidden" name="rutTrabajador" value="${ trabajador.rutTrabajador}-${trabajador.dvTrabajador}">
						</c:if>
						<c:if test="${trabajador.rutTrabajador==0}">
							<input id="rutTrabajador" name="rutTrabajador" type="text" value="" />&nbsp;<input class="boton" id="buscar" type="button" value="Buscar"  onclick="buscarTrabajador();"/></td>
						</c:if>
						</td>
					</tr>
					
					<tr>
						<th class="certificadoLeft">Nombre</th>
						<td class="certificadoLeft">${trabajador.nombre }</td>
					</tr>
					<tr>
						<th class="certificadoLeft">Apellido Paterno</th>
						<td class="certificadoLeft">${trabajador.apellidoPaterno }</td>
					</tr>
					<tr>
						<th class="certificadoLeft">Apellido Materno</th>
						<td class="certificadoLeft">${trabajador.apellidoMaterno }</td>
					</tr>
					<tr>
					<c:choose>
						<c:when test="${trabajador.estado=='C' && trabajador.rutTrabajador!=0}">
							<th class="certificadoLeft">Fecha Desvinculación</th>
						</c:when>
						<c:otherwise>
							<th class="certificadoLeft">Fecha Afiliación</th>
						</c:otherwise>
					</c:choose>
						<td class="certificadoLeft">${trabajador.fechaAfiliacion }</td>
					</tr>
					<tr>
						<c:choose>
							<c:when test="${trabajador.estado!='C' && trabajador.rutTrabajador!=0 && error!=0}">
								<th class="certificadoLeft">Fecha Desvinculación</th>
								<td class="certificadoLeft"><input id="fecha" name="desvinculacion" type="text" maxlength="10" />&nbsp;</td>
							</c:when>
							<c:when test="${trabajador.estado=='C' && trabajador.rutTrabajador!=0}">
								<th class="certificadoLeft">Aviso</th>
								<td class="certificadoLeft">Trabajador no se encuentra afiliado a la empresa</td>
							</c:when>
							<c:when test="${trabajador.rutTrabajador==0}">
								<th class="certificadoLeft">Fecha Desvinculación</th>
								<td class="certificadoLeft">&nbsp;</td>
							</c:when>
							<c:otherwise >
								<td class="certificadoLeft">&nbsp;</td>
								<td class="certificadoLeft">&nbsp;</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</table>
				<input type="hidden" name="accion" value="" />
	
	</p>
	<p><br><br>
	</p>
	<div id="informacion" class="informacion"></div>
	<div id="informacion_warning" class="informacion"></div>
	<div id="informacion_error" class="informacion"></div>
	
	<c:if test="${trabajador.estado!='C' && trabajador.rutTrabajador!=0 && error!=0}">
	<p>
	
	<div class="certificadoCenter">
	<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF;">
	<tr>
		<td style="border: 0 px;border-color: #FFFFFF;">
			<input class="boton" id="volver" type="button" value="Volver" onclick="Volver();"/>
		</td>
		<td width="50" style="border: 0 px;border-color: #FFFFFF;">
			&nbsp;
		</td>
		<td style="border: 0 px;border-color: #FFFFFF;">
			<input class="boton" id="desvincular" type="button" value="Desvincular" onclick="Desvincular();"/>
		</td>
		
	</tr>
	</table>
	</div>
	</p>
	</c:if>
	</form>
	
	<c:if test="${trabajador.estado=='C' && trabajador.rutTrabajador!=0 || error==0}">
	<p>
	<form action="comprobanteMP.do" name="form2" method="post">	
	<div class="certificadoCenter">
	<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF;">
	<tr>
		<td style="border: 0 px;border-color: #FFFFFF;">
			<input class="boton" id="volver" type="button" value="Volver" onclick="Volver();"/>
		</td>
		<c:if test="${error==0}">
		<td style="border: 0 px;border-color: #FFFFFF;">
			
				<input class="boton" id="verCertificado" type="submit" value="Descargar PDF" />
		</td>
		</c:if>
	</tr>
	</table>
		<input type="hidden" name="tipo" value="I" />
	</div>	
	</form>
	</p>
	</c:if>
</div>	
	<script type="text/javascript">
		$(document).ready(
				function() {
					$("#accordian h3").click(function(){
						//slide up all the link lists
						$("#accordian ul ul").slideUp();
						//slide down the link list below the h3 clicked - only if its closed
						if(!$(this).next().is(":visible")){
							$(this).next().slideDown();
						}
					});
					
					
					$("#rutTrabajador").Rut({
						format_on : 'keyup'
					}); 

					var currentTime = new Date();
					//var inicioMes = new Date(currentTime.getFullYear(), currentTime.getMonth(), 1);
					// 10 days before next month
					var finMes = new Date(currentTime.getFullYear(),
							currentTime.getMonth() + 1, 0);
					// one day before next month
					//var endDateFrom = new Date(currentTime.getFullYear(), currentTime.getMonth() +1, 3); // 3rd of next month
					//var endDateTo = new Date(currentTime.getFullYear(), currentTime.getMonth() +1, 10); // 10th of next month
					$(function() {
						$("#fecha").datepicker({
							maxDate : finMes
						});
						
					}); //fin del datepicker
					
					
				}); // fin del document ready
				
	</script>
</body>
</html>
