
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
	<input class="boton" type="button" value="mantencion" onclick="javascript:openMantenedor('LM','2013-04')">
	
	
<div id="dialog_mantenedor_LM" title="Mantenedor LM">
	<div id="info" class="bordeLimite">
			Usted esta trabajando en la Mantención Licencias Medicas para el período: <label class="lbl_info_periodo" id="periodoInfo">AAAA-MM</label>
	</div>	
	<div id="filtro" class="bordeLimite">
		<p>Búsqueda (Sin puntos con guión)</p>
		<form action="" id="form_filtro_LM" name="form_filtro_LM">
			<input type="hidden" id="op" name="op">
			<input type="hidden" id="fecproceso" name="fecproceso">
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
					<label class="lbl_nombre" ></label>
					<input type="button" class="boton" value="buscar" onclick="javascript:buscar('LM')">
			</div>
			<div class="campoFiltro">
					<label class="lbl_nombre" ></label>
					<input type="button" class="boton" value="cargar listado completo" onclick="javascript:cargarListado('LM')">
			</div>
			<label id="lbl_msgBusqueda"></label>
		</form>
	</div>
	
	<div id="filtro" class="bordeLimite">
		Ingreso nuevo Registro
		<br>
		<br>
		<br>
		<div class="campoFiltro">
				<label class="lbl_nombre" ></label>				
				<input type="button" class="boton" id="btnIngresar" value="ingresar" onclick="javascript:openInsertar('LM')">
		</div>				
	</div>
	
	<div id="data" class="bordeLimite">
		<form action="">
		<div id="contenedor-tabla">
			<div class="datagrid">
			<table class="tablaSinBordes">
				<thead>
					<tr>
						<th>N°</th>
						<th>N° Licencia</th>
		   				<th>Rut Trabajador</th>
		   				<th>Fecha inicio reposo</th>
		   				<th>Fecha termino reposo</th>
						<th>Opciones</th>
					</tr>
				</thead>	                         
		 		<tbody>
		 			<!--Empty y msg-->
					<!-- Iterate-->
					<c:forEach items="${lmList}" var="lmList">
						<tr>
							<td>${lmList.correlativ}</td>                             		
							<td class="var_f">${lmList.folio}</td>
							<td class="var_r">${lmList.ruttrabaj}</td> 
							<td>${lmList.fecinirepo}</td>
							<td>${lmList.fecterrepo}</td>
							<td>
								<input type="button" class="boton up_LM"  value="Editar"/>												
								<input type="button" class="boton del_LM"  value="Eliminar"/>
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
	
<div id="dialog_form_LM" title="Formulario LM">		
	<div id="contenedor-form">
		<form action="" name="form_mantenedor_LM" id="form_mantenedor_LM">
			<input type="hidden" id="op" name="op">
			<input type="hidden" id="fecproceso" name="fecproceso">
			<input type="hidden" id="fecterrepo" name="fecterrepo">
			<input type="hidden" id="folio" name="folio">
			<input type="hidden" id="ruttrabaj" name="ruttrabaj">
		</form>
	</div>
</div>
	
</body>
</html>