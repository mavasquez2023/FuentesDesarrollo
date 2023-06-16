<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%
	String keyInicioCopy=(String)request.getAttribute("keyInicioCopy");
	String keyFinCopy=(String)request.getAttribute("keyFinCopy");
	String 	msgPaginacion=(String)request.getAttribute("msgPaginacion");
	if(msgPaginacion==null){
	msgPaginacion="";
	}
	if(keyInicioCopy==null || keyInicioCopy.equals("")){
		keyInicioCopy="0";
	}
	if(keyFinCopy==null || keyFinCopy.equals("")){
		keyFinCopy="0";
	}
 %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SIMAT</title>
	<meta http-equiv="X-UA-Compatible" content="IE=8, IE=9, IE=10" />
	<link rel="stylesheet" type="text/css" href="css/estilos.css">
	<link rel="stylesheet" type="text/css" href="css/normalize.css">
	<link rel="stylesheet" type="text/css" href="css/simular.css">
	<link rel="stylesheet" type="text/css" href="css/simular2.css">
	<link rel="stylesheet" href='./css/main.css' type="text/css" />
	<link rel="stylesheet" href='./css/screen.css' type="text/css" />
	<link rel="stylesheet" href='./css/interior.css' type="text/css" />
	
	<link rel="stylesheet" href='cssSimat/estructura.css' type="text/css" />
	
	<link href="jqSimat/jqBlueUI/css/redmond/jquery-ui-1.9.2.custom.css" rel="stylesheet">
	<script src="jqSimat/jqBlueUI/js/jquery-1.8.3.js"></script>
	<script src="jqSimat/jqBlueUI/js/jquery-ui-1.9.2.custom.js"></script>
	
	<script src="jqSimat/calendar/ajustesCalendario.js"></script>
	<script src="jqSimat/botones/ajustesBotones.js"></script>
	<script src="jqSimat/botones/ajustesDialog.js"></script>
	
	<script src="jqSimat/ajax/jsAjaxT7.js"></script>
	<script src="jqSimat/validacionesForm/val_plano7_DatosLibCob.js"></script>
	<script src="jqSimat/validacionesForm/validarut.js"></script>
	<script src="jqSimat/botones/ajustesBusquedas.js"></script>
	
</head>

<body>
<div id="wrapper" name="wrapper">	
	<div id="header" name="header">
		<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg" width="900" height="100">
	</div>
	<div id="workSpace" name="workSpace">
		<div id="simulacion">
		<h2> N&oacute;mina de datos de las licencias informadas</h2>
		<hr>
			<fieldset class="form-fieldset">
				<h3> Busquedas </h3>
				<div id="tabsBusqueda">
					<ul>
					   	<li><a href="#tabs-1">Rut beneficiario</a></li>
					</ul>
					<div id="tabs-1">
					    <form action="buscarDatosLicCobByRutBenef.do" id="filtroRutBenef" name="filtroRutBenef" method="post">
							<input type="hidden" name="metodo" id="metodo" value="buscarByRutBenef"/>
							<div class="field">
								<input type="text" name="rutBenef" id="rutBenef"/>
								<label class="labelError" id="rutBenefMarca"></label>
							</div>							
							<div align="left">
								<input type="button" class="boton" value="Buscar" id="botonBuscar" name="botonbuscar" onclick="javascript:BuscarRutBenef()"/>
							</div>
						</form>	
					</div>
				</div>
				<input type="button" class="boton" value="Quitar Resultados Busquedas" id="botonRecargar" name="botonRecargar" onclick="javascript:goPage7()"/>							
			</fieldset>
			
			 <div id="workSpacePaginacion" name="workSpacePaginacion">
				<fieldset class="form-fieldset">				
					<form action="PaginarT7.do" method="Post" class="form" name="formAvance" id="formAvance">
						<input type="hidden" name="metodo" id="metodo" value="mostrarPaginacion"/>
						<input type="hidden" name="keyAvance" id="keyAvance"/>
						<input type="hidden" name="keyInicio" id="keyInicio" value=<%out.print(keyInicioCopy); %> />
						<input type="hidden" name="keyFin" id="keyFin" value=<%out.print(keyFinCopy); %> />
					</form>
					<label class="labelForm"><% out.print(msgPaginacion); %></label>
					<input class="boton" type="button" value='<< atras' id="botonRetroceder" onclick="javascritp:retroceder('r')"/>
					<input class="boton" type="button" value='adelante >>' id="botonAvanzar" onclick="javascritp:avanzar('a')"/>
				</fieldset>
			</div>
			<fieldset class="form-fieldset">
					<div name="anchoMantenedorTable" id="anchoMantenedorTable"></div>
		            <hr>
		            <form action="" method="post" class="form">
		                        <table border="1">
		                             <thead>
		                             <tr>
		                             	<th>N°</th>
		                                <th>Mes de Informaci&oacute;n</th>
		                                <th>Nombre Beneficiario</th>
		                                <th>N&uacute;mero de Licencia</th>
		                                <th>Acciones</th>
		                               
		                             </tr>
		                             </thead>
		                             <tbody>
		                             	<logic:empty name="listaDatosLicCob">
											<h3>No existen datos para el periodo seleccionado.</h3>
										</logic:empty>
		                             	<logic:iterate  name="listaDatosLicCob" id="listaDatosLicCob">
		                             		<tr>
			                             		<td><div id="spaceID"><bean:write name="listaDatosLicCob" property="id"/></div></td>
			                             		<td><input type=text value="<bean:write name="listaDatosLicCob" property="mes_informacion" />" /></td>
												<td><input type=text value="<bean:write name="listaDatosLicCob" property="nombre_beneficiario" />" /></td> 
												<td><input type=text value="<bean:write name="listaDatosLicCob" property="nro_licencia" />" /></td>
												<td>
												<input type="button" class="boton" value="Borrar" onclick="javascript:openBorrar('<bean:write name="listaDatosLicCob" property="id"/>')"/>
												
												<input type="button" class="boton" value="actualizar" onclick="javascript:openActualizar(7,'<bean:write name="listaDatosLicCob" property="id"/>')"/>												
												</td> 
		                             		</tr>
		                             	</logic:iterate>
		                             </tbody>
		                        </table>
		                        <br>  
		                        <br>  
		                        
		                        <div align="right">
		            			<input type="button" class="boton" value="Agregar" onclick="javascript:openInsertar(7)"/>
		
		      					</div>
		                        <br>  
		                  </form>
		</fieldset>
		<fieldset class="form-fieldset">
			<form action="mostrarMenu.do" name="formVolver" id="formVolver" method="post">
				<input type="hidden" name="metodo" id="metodo" value="mostrarMenu"/>
			    <input class="boton" type="submit" value='<< Volver a Menu' onclick="javascript:volverMenu()"/>
			</form>
		</fieldset>
		</div>
		
		<div id="LoadMenu_dialog" title='Cargando...'>
			<div id="loadMenu" name="loadMenu" >
			<center><img src='./imgSimat/Loading.gif'><br>Espere un momento...<br></center>
			</div>			
		</div>
		
			<div id="insertar-dialog" title="Formulario para ingreso datos">
				<div id="simulacion2">
					<fieldset class="form-fieldset">
					<form action="insertarDatosLicCob.do" id="formInsertar" name="formInsertar" method="post" type="cl.laaraucana.simat.forms.DatosLicCobForm"
							class="form" >
						<input type="hidden" name="metodo" id="metodo" value="insertar"/>
						
							<div class="campoForm">
								<label class="labelForm">Mes Informaci&oacute;n</label>
								<input onclick="javascript:cargarFecha('formInsertar','fechaPeriodo','mes_informacion')" maxlength="6" class="inputForm" type="text" name="mes_informacion" id="mes_informacion" />
								<label class="labelError" id="mes_informacionMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Entidad</label>
								<input maxlength="5" class="inputForm" type="text" name="codigo_entidad" id="codigo_entidad" />
								<label class="labelError" id="codigo_entidadMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">N&uacute;mero de Licencia</label>
								<input maxlength="14" class="inputForm" type="text" name="nro_licencia" id="nro_licencia" />
								<label class="labelError" id="nro_licenciaMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Run Beneficiario</label>
								<input maxlength="11" class="inputForm" type="text" name="run_beneficiario" id="run_beneficiario" />
								<label class="labelError" id="run_beneficiarioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Nombre Beneficiario</label>
								<input maxlength="80" class="inputForm" type="text" name="nombre_beneficiario" id="nombre_beneficiario" />
								<label class="labelError" id="nombre_beneficiarioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Edad</label>
								<input maxlength="2" class="inputForm" type="text" name="edad" id="edad" />
								<label class="labelError" id="edadMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Sexo</label>
								<input maxlength="1" class="inputForm" type="text" name="sexo" id="sexo" />
								<label class="labelError" id="sexoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha Emisi&oacute;n Licencia</label>
								<input onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_emision_licencia')" maxlength="10" class="inputForm" type="text" name="fecha_emision_licencia" id="fecha_emision_licencia" />
								<label class="labelError" id="fecha_emision_licenciaMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha Emisi&oacute;n Reposo</label>
								<input onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_inicio_reposo')" maxlength="10" class="inputForm" type="text" name="fecha_inicio_reposo" id="fecha_inicio_reposo" />
								<label class="labelError" id="fecha_inicio_reposoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha T&eacute;rmino Reposo</label>
								<input onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_termino_reposo')" maxlength="10" class="inputForm" type="text" name="fecha_termino_reposo" id="fecha_termino_reposo" />
								<label class="labelError" id="fecha_termino_reposoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">N&uacute;mero d&iacute;as Licencia</label>
								<input maxlength="3" class="inputForm" type="text" name="nro_dias_licencia" id="nro_dias_licencia" />
								<label class="labelError" id="nro_dias_licenciaMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">N&uacute;mero d&iacute;as Licencia Autorizada</label>
								<input maxlength="3" class="inputForm" type="text" name="num_dias_licencia_autorizados" id="num_dias_licencia_autorizados"/>
								<label class="labelError" id="num_dias_licencia_autorizadosMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Run Menor Enfermo</label>
								<input maxlength="11" class="inputForm" type="text" name="run_menor_enfermo" id="run_menor_enfermo"/>
								<label class="labelError" id="run_menor_enfermoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Nombre Menor Enfermo</label>
								<input maxlength="80" class="inputForm" type="text" name="nombre_menor_enfermo" id="nombre_menor_enfermo" />
								<label class="labelError" id="nombre_menor_enfermoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha Nacimiento Menor Enfermo</label>
								<input onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_nac_menor_enfermo')" maxlength="10" class="inputForm" type="text" name="fecha_nac_menor_enfermo" id="fecha_nac_menor_enfermo" />
								<label class="labelError" id="fecha_nac_menor_enfermoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Tipo Licencia</label>
								<input maxlength="1" class="inputForm" type="text" name="cod_tipo_licencia" id="cod_tipo_licencia" />
								<label class="labelError" id="cod_tipo_licenciaMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Comuna Beneficiario</label>
								<input maxlength="5" class="inputForm" type="text" name="cod_comuna_beneficiario" id="cod_comuna_beneficiario" />
								<label class="labelError" id="cod_comuna_beneficiarioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Run Profesional</label>
								<input maxlength="11" class="inputForm" type="text" name="run_profesional" id="run_profesional" />
								<label class="labelError" id="run_profesionalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Nombre Profesional</label>
								<input maxlength="80" class="inputForm" type="text" name="nombre_profesional" id="nombre_profesional" />
								<label class="labelError" id="nombre_profesionalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Registro Colegio Profesional</label>
								<input maxlength="20" class="inputForm" type="text" name="registro_colegio_profesional" id="registro_colegio_profesional" />
								<label class="labelError" id="registro_colegio_profesionalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Diagn&oacute;stico</label>
								<input maxlength="20" class="inputForm" type="text" name="codigo_diagnostico" id="codigo_diagnostico" />
								<label class="labelError" id="codigo_diagnosticoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Rut Empleador</label>
								<input maxlength="11" class="inputForm" type="text" name="rut_empleador" id="rut_empleador" />
								<label class="labelError" id="rut_empleadorMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Nombre Empleador</label>
								<input maxlength="80" class="inputForm" type="text" name="nombre_empleador" id="nombre_empleador" />
								<label class="labelError" id="nombre_empleadorMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Calidad Trabajador</label>
								<input maxlength="1" class="inputForm" type="text" name="calidad_trabajador" id="calidad_trabajador" />
								<label class="labelError" id="calidad_trabajadorMarca"></label>
							</div>
						</form>
					
					</fieldset>
				</div>
			</div>
			
			<div id="borrar-dialog" title="Confirmaci&oacute;n Borrar">
				<div id="simulacion2">
					<fieldset class="form-fieldset">
						<form action="eliminarDatosLicCob.do" id="formBorrar" name="formBorrar" type="cl.laaraucana.simat.forms.DatosLicCobForm" method="post" class="form" >
		 					<input type="hidden" name="metodo" id="metodo" value="eliminar"/>
							<div class="campoForm">
								<label class="labelForm">Identificador</label>							
								<input disabled="disabled" readonly="readonly" class="inputForm" type="text" name="id" id="id">
							</div>							
						</form>
					</fieldset>
				</div>	
			</div>
			
			<div id="actualizar-dialog" title="Actualizar N&oacute;mina">
				<div id="simulacion2">
					<fieldset class="form-fieldset">
						<form action="actualizarDatosLicCob.do" id="formActualizar" name="formActualizar" type="cl.laaraucana.simat.forms.DatosLicCobForm" method="post" class="form" >
							<input type="hidden" name="metodo" id="metodo" value="actualizar"/>
								
							<div class="campoForm">
								<label class="labelForm">Identificador</label>							
								<input disabled="disabled" readonly="readonly" class="inputForm" type="text" name="id" id="id">
							</div>
							
							<div class="campoForm">
								<label class="labelForm">Mes Informaci&oacute;n</label>
								<input readonly onclick="javascript:cargarFecha('formActualizar','fechaPeriodo','mes_informacion')" maxlength="6" class="inputForm" type="text" name="mes_informacion" id="mes_informacion" />
								<label class="labelError" id="mes_informacionMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Entidad</label>
								<input maxlength="5" class="inputForm" type="text" name="codigo_entidad" id="codigo_entidad" />
								<label class="labelError" id="codigo_entidadMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">N&uacute;mero de Licencia</label>
								<input maxlength="14" class="inputForm" type="text" name="nro_licencia" id="nro_licencia" />
								<label class="labelError" id="nro_licenciaMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Run Beneficiario</label>
								<input maxlength="11" class="inputForm" type="text" name="run_beneficiario" id="run_beneficiario" />
								<label class="labelError" id="run_beneficiarioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Nombre Beneficiario</label>
								<input maxlength="80" class="inputForm" type="text" name="nombre_beneficiario" id="nombre_beneficiario" />
								<label class="labelError" id="nombre_beneficiarioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Edad</label>
								<input maxlength="2" class="inputForm" type="text" name="edad" id="edad" />
								<label class="labelError" id="edadMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Sexo</label>
								<input maxlength="1" class="inputForm" type="text" name="sexo" id="sexo" />
								<label class="labelError" id="sexoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha Emisi&oacute;n Licencia</label>
								<input onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_emision_licencia')" maxlength="10" class="inputForm" type="text" name="fecha_emision_licencia" id="fecha_emision_licencia" />
								<label class="labelError" id="fecha_emision_licenciaMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha Emisi&oacute;n Reposo</label>
								<input onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_inicio_reposo')" maxlength="10" class="inputForm" type="text" name="fecha_inicio_reposo" id="fecha_inicio_reposo" />
								<label class="labelError" id="fecha_inicio_reposoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha T&eacute;rmino Reposo</label>
								<input onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_termino_reposo')" maxlength="10" class="inputForm" type="text" name="fecha_termino_reposo" id="fecha_termino_reposo" />
								<label class="labelError" id="fecha_termino_reposoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">N&uacute;mero d&iacute;as Licencia</label>
								<input maxlength="3" class="inputForm" type="text" name="nro_dias_licencia" id="nro_dias_licencia" />
								<label class="labelError" id="nro_dias_licenciaMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">N&uacute;mero d&iacute;as Licencia Autorizada</label>
								<input maxlength="3" class="inputForm" type="text" name="num_dias_licencia_autorizados" id="num_dias_licencia_autorizados"/>
								<label class="labelError" id="num_dias_licencia_autorizadosMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Run Menor Enfermo</label>
								<input maxlength="11" class="inputForm" type="text" name="run_menor_enfermo" id="run_menor_enfermo"/>
								<label class="labelError" id="run_menor_enfermoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Nombre Menor Enfermo</label>
								<input maxlength="80" class="inputForm" type="text" name="nombre_menor_enfermo" id="nombre_menor_enfermo" />
								<label class="labelError" id="nombre_menor_enfermoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha Nacimiento Menor Enfermo</label>
								<input onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_nac_menor_enfermo')" maxlength="10" class="inputForm" type="text" name="fecha_nac_menor_enfermo" id="fecha_nac_menor_enfermo" />
								<label class="labelError" id="fecha_nac_menor_enfermoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Tipo Licencia</label>
								<input maxlength="1" class="inputForm" type="text" name="cod_tipo_licencia" id="cod_tipo_licencia" />
								<label class="labelError" id="cod_tipo_licenciaMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Comuna Beneficiario</label>
								<input maxlength="5" class="inputForm" type="text" name="cod_comuna_beneficiario" id="cod_comuna_beneficiario" />
								<label class="labelError" id="cod_comuna_beneficiarioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Run Profesional</label>
								<input maxlength="11" class="inputForm" type="text" name="run_profesional" id="run_profesional" />
								<label class="labelError" id="run_profesionalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Nombre Profesional</label>
								<input maxlength="80" class="inputForm" type="text" name="nombre_profesional" id="nombre_profesional" />
								<label class="labelError" id="nombre_profesionalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Registro Colegio Profesional</label>
								<input maxlength="20" class="inputForm" type="text" name="registro_colegio_profesional" id="registro_colegio_profesional" />
								<label class="labelError" id="registro_colegio_profesionalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Diagn&oacute;stico</label>
								<input maxlength="20" class="inputForm" type="text" name="codigo_diagnostico" id="codigo_diagnostico" />
								<label class="labelError" id="codigo_diagnosticoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Rut Empleador</label>
								<input maxlength="11" class="inputForm" type="text" name="rut_empleador" id="rut_empleador" />
								<label class="labelError" id="rut_empleadorMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Nombre Empleador</label>
								<input maxlength="80" class="inputForm" type="text" name="nombre_empleador" id="nombre_empleador" />
								<label class="labelError" id="nombre_empleadorMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Calidad Trabajador</label>
								<input maxlength="1" class="inputForm" type="text" name="calidad_trabajador" id="calidad_trabajador" />
								<label class="labelError" id="calidad_trabajadorMarca"></label>
							</div>
						</form>
					</fieldset>
				</div>	
			</div>
			<div id="Fecha_dialog" title='Seleccione Fecha'>
			<div id="datepicker" name="datepicker">
				<input type="text" readonly name="fechaAux" id="fechaAux"/>
				<input type="hidden" readonly name="tipoForm" id="tipoForm"/>
				<input type="hidden" readonly name="idInput" id="idInput"/>
			</div>				 
		</div>
			
		</div>
</div>			
</body>
</html>