
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
	<input class="boton" type="button" value="mantencion" onclick="javascript:openMantenedor('SIL','2014-05')">
	
	
<div id="dialog_mantenedor_SIL" title="Mantenedor SIL">
	<div id="info" class="bordeLimite">
			Usted esta trabajando en la Mantención Licencias Medicas para el período: <label class="lbl_info_periodo" id="periodoInfo">AAAA-MM</label>
	</div>	
	<div id="filtro" class="bordeLimite">
		<p>Búsqueda (Sin puntos con guión)</p>
		<form action="" id="form_filtro_SIL" name="form_filtro_SIL">
			<input type="hidden" id="op" name="op">
			<input type="hidden" id="perpag" name="perpag">
			<div class="campoFiltro">
				<label class="lbl_nombre" >N° Licencia</label>
				<input class="txt_campo" id="nrofol" name="nrofol">
				<label class="lbl_Error" id="nrofol_error"></label>
			</div>
			<div class="campoFiltro">
				<label class="lbl_nombre" >Rut </label>
				<input class="txt_campo" id="ruttrabaj" name="ruttrabaj">
				<label class="lbl_Error" id="ruttrabaj_error"></label>
			</div>
		
			<div class="campoFiltro">
					<label class="lbl_nombre" ></label>
					<input type="button" class="boton" value="buscar" onclick="javascript:buscar('SIL')">
			</div>
			<div class="campoFiltro">
					<label class="lbl_nombre" ></label>
					<input type="button" class="boton" value="cargar listado completo" onclick="javascript:cargarListado('SIL')">
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
				<input type="button" class="boton" id="btnIngresar" value="ingresar" onclick="javascript:openInsertar('SIL')">
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
	   				<th>Rut Trabajador</th>
	   				<th>N° Licencia</th>   				
	   				<th>Folio de pago PAGFOL</th>
	   				<th>Fecha término reposo</th>
					<th>Opciones</th>
				</tr>
			</thead>	                         
	 		<tbody>
	 			<!--Empty y msg-->
				<!-- Iterate-->
				<c:forEach items="${silList}" var="silList">
					<tr>
						<td>${lmList.correlativ}</td>
						<td class="var_r">${silList.ruttrabaj}</td>
						<td class="var_f">${silList.nrofolio}</td>
						<td class="var_pf">${silList.pagfol}</td>
						<td class="var_ftr">${silList.lichasfec}</td>
						<td>
							<input type="button" class="boton up_SIL"  value="Editar"/>												
							<input type="button" class="boton del_SIL"  value="Eliminar"/>
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
	
<div id="dialog_form_SIL" title="Formulario SIL">		
		<div id="contenedor-form">
			<form action="" name="form_mantenedor_SIL" id="form_mantenedor_SIL">
				<input type="hidden" id="op" name="op">
				<input type="hidden" id="perpag" name="perpag">					
				<input type="hidden" id="pagfol" name="pagfol">
				<input type="hidden" id="nrofol" name="nrofol">
				<input type="hidden" id="ruttrabaj" name="ruttrabaj">
				<input type="hidden" id="lichasfec" name="lichasfec">
			</form>
		</div>
</div>
	
</body>
</html>