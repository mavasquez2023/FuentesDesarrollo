
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
	<input class="boton" type="button" value="Log" onclick="javascript:logError('LM','2013-03')">
	
	
<div id="dialog_logError_LM" title="Log de errores LM">
	<div id="info" class="bordeLimite">
			Usted esta trabajando en la Correción de Errores de Licencias Médicas para el período: <label class="lbl_info_periodo" id="periodoInfo">AAAA-MM</label>
	</div>	
	<div id="filtroLog" class="bordeLimite">
		<p>Búsqueda (Sin puntos con guión)</p>
		<form action="" id="form_filtro_Log_LM" name="form_filtro_Log_LM">
			<!-- Campos hidden -->
			<input type="hidden" id="op" name="op" value="" />
			<input type="hidden" id="fecproceso" name="fecproceso" />
			
			<div class="campoFiltro">
				<label class="lbl_nombre" >N° Licencia</label>
				<input class="txt_campo" id="folio" name="folio">
				<label class="lbl_Error" id="folio_error"></label>
			</div>	
			<div class="campoFiltro">
				<label class="lbl_nombre" >Rut </label>
				<input class="txt_campo" id="ruttrabaj" name="ruttrabaj">
				<label class="lbl_Error" id="ruttrabaj_error"></label>
			</div>
			<div class="campoFiltro">
				<label class="lbl_nombre">Agrupar Errores</label>
				<input type="checkbox" id="chk_agrupar_LM" name="chk_agrupar_LM" value="" />
			</div>
		</form>		
		<div class="campoLogs">
			<div class="campoFiltro">
					<label class="lbl_nombre" ></label>
					<input type="button" class="boton" value="Buscar" onclick="javascript:buscarLog('LM')">
			</div>
			<div class="campoFiltro">
					<label class="lbl_nombre" ></label>
					<input type="button" class="boton" value="Cargar listado completo" onclick="javascript:cargarListadoLog('LM')">
			</div>	
		</div>	
	</div>

	<div id="data" class="bordeLimite">
		<form action="">
		<div id="contenedor-tablaLog_LM">
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
				<!--END ITERATE--->
				</tbody>
			</table>
			</div>
		</div>
		</form>
	</div>
</div>
	
<div id="dialog_formL_LM" title="Formulario LM">		
	<form action="" name="form_logError_LM" id="form_logError_LM">
		<input type="hidden" id="op" name="op">
		<input type="hidden" id="fecproceso" name="fecproceso">
	<div id="contenedor-form">		
	</div>	
	</form>
</div>
	
</body>
</html>