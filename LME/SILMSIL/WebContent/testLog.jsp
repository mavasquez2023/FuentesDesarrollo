
<%@ include file="layout/headerJsp.jsp"%>

<html>
<head>
	<jsp:include flush="true" page="layout/header.jsp"></jsp:include>
	<title>SILMSIL</title>
	<%
	String cantidadPaginas="";
	String paginaActual="";
	
		Paginacion_VO pag;
		HttpSession sesion = request.getSession();
		pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
		if(pag!=null){
			cantidadPaginas=String.valueOf(pag.getCantidadPaginas());
			paginaActual=String.valueOf(pag.getPaginaActual());
		}
	%>	
</head>

<body>

	<h1>test</h1>
	<br>
	<br>
	<br>
	<input class="boton" type="button" value="Log" onclick="javascript:logError()">
	
	
<div id="dialog_logError_SIL" title="Log de errores SIL">
	<div id="info" class="bordeLimite">
			Usted esta trabajando en la Correci&oacute;n de Errores de Licencias M&eacute;dicas para el per&iacute;odo: <label class="lbl_info_periodo" id="periodoInfo">AAAA-MM</label>
	</div>	
	<div id="filtroLog" class="bordeLimite">
		<p>B&uacute;squeda (Sin puntos con gui&oacute;n)</p>
		<form action="" id="form_filtro_Log_SIL" name="form_filtro_Log_SIL">
			<!-- Campos hidden -->
			<input type="hidden" id="op" name="op" value="" />
			<input type="hidden" id="perpag" name="perpag" />
			
			<div class="campoFiltro">
				<label class="lbl_nombre" >N° Licencia</label>
				<input class="txt_campo" id="nrofol" name="nrofol">
				<label class="lbl_Error" id="folio_error"></label>
			</div>	
			<div class="campoFiltro">
				<label class="lbl_nombre" >Rut </label>
				<input class="txt_campo" id="ruttrabaj" name="ruttrabaj">
				<label class="lbl_Error" id="ruttrabaj_error"></label>
			</div>
			<div class="campoFiltro">
				<label class="lbl_nombre">Agrupar Errores</label>
				<input type="checkbox" id="chk_agrupar_SIL" name="chk_agrupar_SIL" value="" />
			</div>
		</form>
		
	<div class="campoLogs">
		<div class="campoFiltro">
				<label class="lbl_nombre" ></label>
				<input type="button" class="boton" value="Buscar" onclick="javascript:buscarLog('SIL')">
		</div>
		<div class="campoFiltro">
				<label class="lbl_nombre" ></label>
				<input type="button" class="boton" value="Cargar listado completo" onclick="javascript:cargarListadoLog('SIL')">
		</div>	
	</div>	
	</div>

	<div id="data" class="bordeLimite">
		<form action="">
		<div id="contenedor-tablaLog_SIL">
			<div class="datagrid">
			<table class="tablaSinBordes">
				<thead>
					<tr>
						<th>N°</th>
						<th>N° Licencia</th>
		   				<th>Rut Trabajador</th>
		   				<th>Emisi&oacute;n</th>
		   				<th>Descripci&oacute;n Error</th>
						<th>Opci&oacute;n</th>
					</tr>
				</thead>	                         
		 		<tbody>
		 			<!--Empty y msg-->
					<!-- Iterate-->
					<c:forEach items="${silList}" var="silList">
						<tr>
							<td>${silList.correlab}</td>                             		
							<td class="var_rut">${silList.ruttrabaj}</td>
							<td class="var_nrofol">${silList.nrofol}</td>
							<td>${silList.fecemi}</td>
							<td>${silList.descripcion}</td>
							<td>
								<input type="button" class="boton up_LM"  value="Corregir Error"/>	
							</td> 
						</tr>
					</c:forEach>
				<!--END ITERATE--->
				</tbody>
			</table>
			</div>
		</div>
		</form>
	</div>
</div>

<div id="dialog_formL_SIL" title="Formulario SIL">		
	<form action="" name="form_filtro_Log_SIL" id="form_filtro_Log_SIL">
		<input type="hidden" id="op" name="op">
		<input type="hidden" id="fecproceso" name="fecproceso">
	<div id="contenedor-form">		
	</div>	
	</form>
</div>

<div id="dialog_form_LogSIL" title="Formulario Log Errores SIL">		
	<div id="contenedor-form_SIL">
		<form action="" name="form_mantenedor_SIL" id="form_mantenedor_SIL">
			<input type="hidden" id="op" name="op">
			<input type="hidden" id="fecproceso" name="fecproceso">
			<input type="hidden" id="nrofol" name="nrofol">
			<input type="hidden" id="ruttrabaj" name="ruttrabaj">
		</form>
	</div>
</div>

</body>
</html>