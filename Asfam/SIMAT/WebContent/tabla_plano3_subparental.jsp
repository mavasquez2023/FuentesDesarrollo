<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>


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


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
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
	 
 	<script src="jqSimat/ajax/jsAjaxT3.js"></script>
	<script src="jqSimat/validacionesForm/val_plano3_SubsParental.js"></script>
	<script src="jqSimat/validacionesForm/validarut.js"></script>
	<script src="jqSimat/botones/ajustesBusquedas.js"></script>
	
	
</head>

<body>
<div id="wrapper" name="wrapper">	
	<div id="header" name="header">
		<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg" width="900px" height="100px">
	</div>
	<div id="workSpace" name="workSpace">
		
		<div id="simulacion">
		<h2>Subsidios y cotizaciones previsionales por permiso postnatal Parental</h2>
		<hr>
			<fieldset class="form-fieldset">
				<h3> Busquedas </h3>
				<div id="tabsBusqueda">
				  <ul>
				    <li><a href="#tabs-1">Rut beneficiario Parental</a></li>
				    <li><a href="#tabs-2">N&uacute;mero documento</a></li>
				  </ul>
				  <div id="tabs-1">
				    <form action="buscarSubsParentalByRutBenef.do" id="filtroRutBenef" name="filtroRutBenef" method="post">
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
				  <div id="tabs-2">
				    <form action="buscarSubsParentalByNumDoc.do" id="filtroNumDoc" name="filtroNumDoc" method="post">
						<input type="hidden" name="metodo" id="metodo" value="buscarByNumDoc"/>
						<div class="field">
							<input type="text" name="numDoc" id="numDoc"/>
						</div>							
						<div align="left">
							<input type="button" class="boton" value="Buscar" id="botonBuscar" name="botonbuscar" onclick="javascript:BuscarNumDoc()"/>
						</div>
					</form>	
				  </div>
				</div>
				<input type="button" class="boton" value="Quitar Resultados Busquedas" id="botonRecargar" name="botonRecargar" onclick="javascript:goPage3()"/>							
			</fieldset>	
				
			<div id="workSpacePaginacion" name="workSpacePaginacion">
				<fieldset class="form-fieldset">				
					<form action="PaginarT3.do" method="Post" class="form" name="formAvance" id="formAvance">
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
		                                <th>Nombre beneficiario</th>
		                                <th>Run beneficiario </th>
		                                <th>N&uacute;mero de documento</th>
		                                <th>Acciones</th>		                               
		                             </tr>
		                             </thead>
		                             <tbody>
		                            <logic:empty name="listaSubsParental">
										<h3>No existen datos para el periodo seleccionado.</h3>
									</logic:empty>
		                             <logic:iterate  name="listaSubsParental" id="listaSubsParental">
		                             		<tr>
												<td><div id="spaceID"><bean:write name="listaSubsParental" property="idParental"/></div></td>                         		
			                             		<td><input readonly name="mes_informacion" id="mes_informacion" type=text value="<bean:write name="listaSubsParental" property="mes_informacion" />"/></td>
												<td><input readonly type=text value="<bean:write name="listaSubsParental" property="nombre_beneficiario_parental"/>" /></td> 
												<td><input readonly type=text value="<bean:write name="listaSubsParental" property="run_beneficiario_parental" />" /></td>
												<td><input readonly type=text value="<bean:write name="listaSubsParental" property="num_documento" />" /></td>
												<td>
													<input readonly type="button" class="boton" value="Borrar" onclick="javascript:openBorrar('<bean:write name="listaSubsParental" property="idParental"/>')"/>
												
													<input type="button" class="boton" value="actualizar" onclick="javascript:openActualizar(3,'<bean:write name="listaSubsParental" property="idParental"/>')"/>												
												</td> 
		                             		</tr>
		                             </logic:iterate>
		                             
		                             </tbody>
		                        </table>
		                        <br>  
		                        <br>  
		                        
		                        <div align="right">
		            				<input type="button" class="boton" value="Agregar" onclick="javascript:openInsertar(3)"/>		
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
			<div id="loadMenu" name="loadMenu" ></div>
			<center><img src='./imgSimat/Loading.gif'><br>Espere un momento...<br></center>			
		</div>
		
			<div id="insertar-dialog" title="Formulario para ingreso datos">
				<div id="simulacion2">
					<fieldset class="form-fieldset">
					<form action="insertarSubsParental.do" type="cl.laaraucana.simat.forms.SubsParentalForm" method="post" class="form" name="formInsertar" id="formInsertar">
					<input type="hidden" name="metodo" id="metodo" value="insertar"/>
							
							<div id="campoForm">
								<label class="labelForm">Mes Informaci&oacute;n</label>
								<input onclick="javascript:cargarFecha('formInsertar','fechaPeriodo','mes_informacion')" maxlength="6" class="inputForm" type="text" name="mes_informacion" id="mes_informacion"/>
								<label class="labelError" id="mes_informacionMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">C&oacute;digo Entidad</label>
								<input class="inputForm" maxlength="5" type="text" name="codigo_entidad" id="codigo_entidad"/>
								<label class="labelError" id="codigo_entidadMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Agencia Entidad</label>
								<input class="inputForm" maxlength="20" type="text" name="agencia_entidad" id="agencia_entidad"/>
								<label class="labelError" id="agencia_entidadMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Rut Empleador</label>
								<input class="inputForm" maxlength="11" type="text" name="rut_empleador" id="rut_empleador"/>
								<label class="labelError" id="rut_empleadorMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Nombre Empleador</label>
								<input class="inputForm" maxlength="80" type="text" name="nombre_empleador" id="nombre_empleador"/>
								<label class="labelError" id="nombre_empleadorMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Run Beneficiario Parental</label>
								<input class="inputForm" maxlength="11" type="text" name="run_beneficiario_parental" id="run_beneficiario_parental"/>
								<label class="labelError" id="run_beneficiario_parentalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Nombre Beneficiario Parental</label>
								<input class="inputForm" maxlength="80" type="text" name="nombre_beneficiario_parental" id="nombre_beneficiario_parental"/>
								<label class="labelError" id="nombre_beneficiario_parentalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Edad</label>
								<input class="inputForm" maxlength="2" type="text" name="edad" id="edad"/>
								<label class="labelError" id="edadMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Sexo</label>
								<input class="inputForm" maxlength="1" type="text" name="sexo" id="sexo"/>
								<label class="labelError" id="sexoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Calidad Trabajador</label>
								<input class="inputForm" maxlength="1" type="text" name="calidad_trabajador" id="calidad_trabajador"/>
								<label class="labelError" id="calidad_trabajadorMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Actividad Laboral Trabajador</label>
								<input class="inputForm" maxlength="2" type="text" name="actividad_laboral_trabajador" id="actividad_laboral_trabajador"/>
								<label class="labelError" id="actividad_laboral_trabajadorMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">C&oacute;digo Comuna Beneficiario</label>
								<input class="inputForm" maxlength="5" type="text" name="cod_comuna_beneficiario" id="cod_comuna_beneficiario"/>
								<label class="labelError" id="cod_comuna_beneficiarioMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Run Beneficiario Postnatal</label>
								<input class="inputForm" maxlength="11" type="text" name="run_beneficiario_postnatal" id="run_beneficiario_postnatal"/>
								<label class="labelError" id="run_beneficiario_postnatalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Nombre Beneficiario Postnatal</label>
								<input class="inputForm" maxlength="80" type="text" name="nombre_beneficiario_postnatal" id="nombre_beneficiario_postnatal"/>
								<label class="labelError" id="nombre_beneficiario_postnatalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">N&uacute;mero de Licencia</label>
								<input class="inputForm" maxlength="14" type="text" name="nro_licencia" id="nro_licencia"/>
								<label class="labelError" id="nro_licenciaMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">V&iacute;nculo Beneficiario Menor</label>
								<input class="inputForm" maxlength="1" type="text" name="vinculo_beneficiario_menor" id="vinculo_beneficiario_menor"/>
								<label class="labelError" id="vinculo_beneficiario_menorMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">N&uacute;mero Resoluci&oacute;n</label>
								<input class="inputForm" maxlength="20" type="text" name="nro_resolucion" id="nro_resolucion"/>
								<label class="labelError" id="nro_resolucionMarca"></label>								
							</div>
							<div id="campoForm">
								<label class="labelForm">Tipo Extension postnatal Parental</label>
								<input class="inputForm" maxlength="1" type="text" name="tipo_extension_postnatal_parental" id="tipo_extension_postnatal_parental"/>
								<label class="labelError" id="tipo_extension_postnatal_parentalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Fecha Inicio Postnatal Parental</label>
								<input class="inputForm" onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_inicio_postnatal_parental')" maxlength="10" type="text" name="fecha_inicio_postnatal_parental" id="fecha_inicio_postnatal_parental"/>
								<label class="labelError" id="fecha_inicio_postnatal_parentalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Fecha Termino Postnatal Parental</label>
								<input class="inputForm" onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_termino_postnatal_parental')" maxlength="10" type="text" name="fecha_termino_postnatal_parental" id="fecha_termino_postnatal_parental"/>
								<label class="labelError" id="fecha_termino_postnatal_parentalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">N&uacute;mero Dias Permiso Pagado</label>
								<input class="inputForm" maxlength="3" type="text" name="num_dias_permiso_pagado" id="num_dias_permiso_pagado"/>
								<label class="labelError" id="num_dias_permiso_pagadoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Tipo de Pago</label>
								<input class="inputForm" maxlength="1" type="text" name="tipo_de_pago" id="tipo_de_pago"/>
								<label class="labelError" id="tipo_de_pagoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Subsidio DFL44_1978</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_sub_dfl44_1978" id="monto_sub_dfl44_1978"/>
								<label class="labelError" id="monto_sub_dfl44_1978Marca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Subsidio Pagado</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_sub_pagado" id="monto_sub_pagado"/>
								<label class="labelError" id="monto_sub_pagadoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Tipo Emisi&oacute;n</label>
								<input class="inputForm" maxlength="2" type="text" name="tipo_emision" id="tipo_emision"/>
								<label class="labelError" id="tipo_emisionMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Mes N&oacute;mina</label>
								<input class="inputForm" onclick="javascript:cargarFecha('formInsertar','fechaPeriodo','mes_nomina')" maxlength="6" type="text" name="mes_nomina" id="mes_nomina"/>
								<label class="labelError" id="mes_nominaMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Modalidad De Pago</label>
								<input class="inputForm" maxlength="1" type="text" name="mod_pago" id="mod_pago"/>
								<label class="labelError" id="mod_pagoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Serie Documento</label>
								<input class="inputForm" maxlength="20" type="text" name="serie_documento" id="serie_documento"/>
								<label class="labelError" id="serie_documentoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">N&uacute;mero Documento</label>
								<input class="inputForm" maxlength="20" type="text" name="num_documento" id="num_documento"/>
								<label class="labelError" id="num_documentoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Fecha Emisi&oacute;n Documento</label>
								<input class="inputForm" onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_emision_documento')" maxlength="10" type="text" name="fecha_emision_documento" id="fecha_emision_documento"/>
								<label class="labelError" id="fecha_emision_documentoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Del Documento</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_documento" id="monto_documento"/>
								<label class="labelError" id="monto_documentoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">C&oacute;digo del Banco</label>
								<input class="inputForm" maxlength="3" type="text" name="codigo_banco" id="codigo_banco"/>
								<label class="labelError" id="codigo_bancoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Cuenta Corriente</label>
								<input class="inputForm" maxlength="20" type="text" name="cta_cte" id="cta_cte"/>
								<label class="labelError" id="cta_cteMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Causal Emisi&oacute;n</label>
								<input class="inputForm" maxlength="1" type="text" name="causal_emision" id="causal_emision"/>
								<label class="labelError" id="causal_emisionMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">N&uacute;mero Dias Cotizados Pagados</label>
								<input class="inputForm" maxlength="3" type="text" name="num_dias_cotiz_pagados" id="num_dias_cotiz_pagados"/>
								<label class="labelError" id="num_dias_cotiz_pagadosMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Remuneraci&oacute;n imponible</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_remun_imponible" id="monto_remun_imponible"/>
								<label class="labelError" id="monto_remun_imponibleMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto FP</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_fp" id="monto_fp"/>
								<label class="labelError" id="monto_fpMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Salud</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_salud" id="monto_salud"/>
								<label class="labelError" id="monto_saludMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Salud AD</label>
								<input class="inputForm" maxlength="15" type="text" name=monto_salud_ad id="monto_salud_ad"/>
								<label class="labelError" id="monto_salud_adMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Desahucio</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_desahucio" id="monto_desahucio"/>
								<label class="labelError" id="monto_desahucioMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Cotizaci&oacute;n FU</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_cotiz_fu" id="monto_cotiz_fu"/>
								<label class="labelError" id="monto_cotiz_fuMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Cotizaci&oacute;n SC</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_cotiz_sc" id="monto_cotiz_sc"/>
								<label class="labelError" id="monto_cotiz_scMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Entidad Previsional</label>
								<input class="inputForm" maxlength="5" type="text" name="entidad_previsional" id="entidad_previsional"/>
								<label class="labelError" id="entidad_previsionalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Traspaso</label>
								<input class="inputForm" maxlength="1" type="text" name="traspaso" id="traspaso"/>
								<label class="labelError" id="traspasoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Subsidio Iniciado</label>
								<input class="inputForm" maxlength="1" type="text" name="subsidio_iniciado" id="subsidio_iniciado"/>
								<label class="labelError" id="subsidio_iniciadoMarca"></label>
							</div>	
							<div class="campoForm">
								<label class="labelForm"> Indicador convenio</label>
								<input maxlength="1" class="inputForm" TYPE="text" NAME="indicador_convenio" id="indicador_convenio" />
								<label class="labelError" id="indicador_convenioMarca"></label>
							</div>							
						</form>
					</fieldset>
					</div>
			</div>
			
			
			<div id="borrar-dialog" title="Confirmaci&oacute;n Borrar">
				<div id="simulacion2">
					<fieldset class="form-fieldset">
						<form action="eliminarSubsParental.do"  type="cl.laaraucana.simat.forms.SubsParentalForm" method="post" class="form" name="formBorrar" id="formBorrar">
							<input type="hidden" name="metodo" id="metodo" value="eliminarByID"/>
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
					<form action="actualizarSubsParental.do" type="cl.laaraucana.simat.forms.SubsParentalForm" method="post" class="form" name="formActualizar" id="formActualizar">
						<input type="hidden" name="metodo" id="metodo" value="actualizarByID"/>
							
							<div class="campoForm">
								<label class="labelForm">Identificador</label>							
							<input disabled="disabled" readonly="readonly" class="inputForm" type="text" name="id" id="id">
							</div>
							
							<div id="campoForm">
								<label class="labelForm">Mes Informaci&oacute;n</label>
								<input onclick="javascript:cargarFecha('formActualizar','fechaPeriodo','mes_informacion')" maxlength="6" class="inputForm" type="text" name="mes_informacion" id="mes_informacion"/>
								<label class="labelError" id="mes_informacionMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">C&oacute;digo Entidad</label>
								<input class="inputForm" maxlength="5" type="text" name="codigo_entidad" id="codigo_entidad"/>
								<label class="labelError" id="codigo_entidadMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Agencia Entidad</label>
								<input class="inputForm" maxlength="20" type="text" name="agencia_entidad" id="agencia_entidad"/>
								<label class="labelError" id="agencia_entidadMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Rut Empleador</label>
								<input class="inputForm" maxlength="11" type="text" name="rut_empleador" id="rut_empleador"/>
								<label class="labelError" id="rut_empleadorMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Nombre Empleador</label>
								<input class="inputForm" maxlength="80" type="text" name="nombre_empleador" id="nombre_empleador"/>
								<label class="labelError" id="nombre_empleadorMarca"></label>
							</div>
							
							<div id="campoForm">
								<label class="labelForm">Run Beneficiario Parental</label>
								<input class="inputForm" maxlength="11" type="text" name="run_beneficiario_parental" id="run_beneficiario_parental"/>
								<label class="labelError" id="run_beneficiario_parentalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Nombre Beneficiario Parental</label>
								<input class="inputForm" maxlength="80" type="text" name="nombre_beneficiario_parental" id="nombre_beneficiario_parental"/>
								<label class="labelError" id="nombre_beneficiario_parentalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Edad</label>
								<input class="inputForm" maxlength="2" type="text" name="edad" id="edad"/>
								<label class="labelError" id="edadMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Sexo</label>
								<input class="inputForm" maxlength="1" type="text" name="sexo" id="sexo"/>
								<label class="labelError" id="sexoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Calidad Trabajador</label>
								<input class="inputForm" maxlength="1" type="text" name="calidad_trabajador" id="calidad_trabajador"/>
								<label class="labelError" id="calidad_trabajadorMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Actividad Laboral Trabajador</label>
								<input class="inputForm" maxlength="2" type="text" name="actividad_laboral_trabajador" id="actividad_laboral_trabajador"/>
								<label class="labelError" id="actividad_laboral_trabajadorMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">C&oacute;digo Comuna Beneficiario</label>
								<input class="inputForm" maxlength="5" type="text" name="cod_comuna_beneficiario" id="cod_comuna_beneficiario"/>
								<label class="labelError" id="cod_comuna_beneficiarioMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Run Beneficiario Postnatal</label>
								<input class="inputForm" maxlength="11" type="text" name="run_beneficiario_postnatal" id="run_beneficiario_postnatal"/>
								<label class="labelError" id="run_beneficiario_postnatalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Nombre Beneficiario Postnatal</label>
								<input class="inputForm" maxlength="80" type="text" name="nombre_beneficiario_postnatal" id="nombre_beneficiario_postnatal"/>
								<label class="labelError" id="nombre_beneficiario_postnatalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">N&uacute;mero de Licencia</label>
								<input class="inputForm" maxlength="14" type="text" name="nro_licencia" id="nro_licencia"/>
								<label class="labelError" id="nro_licenciaMarca"></label>
							</div>
							
							<div id="campoForm">
								<label class="labelForm">V&iacute;nculo Beneficiario Menor</label>
								<input class="inputForm" maxlength="1" type="text" name="vinculo_beneficiario_menor" id="vinculo_beneficiario_menor"/>
								<label class="labelError" id="vinculo_beneficiario_menorMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">N&uacute;mero Resoluci&oacute;n</label>
								<input class="inputForm" maxlength="20" type="text" name="nro_resolucion" id="nro_resolucion"/>
								<label class="labelError" id="nro_resolucionMarca"></label>								
							</div>
							<div id="campoForm">
								<label class="labelForm">Tipo Extension postnatal Parental</label>
								<input class="inputForm" maxlength="1" type="text" name="tipo_extension_postnatal_parental" id="tipo_extension_postnatal_parental"/>
								<label class="labelError" id="tipo_extension_postnatal_parentalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Fecha Inicio Postnatal Parental</label>
								<input class="inputForm" onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_inicio_postnatal_parental')" maxlength="10" type="text" name="fecha_inicio_postnatal_parental" id="fecha_inicio_postnatal_parental"/>
								<label class="labelError" id="fecha_inicio_postnatal_parentalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Fecha Termino Postnatal Parental</label>
								<input class="inputForm" onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_termino_postnatal_parental')" maxlength="10" type="text" name="fecha_termino_postnatal_parental" id="fecha_termino_postnatal_parental"/>
								<label class="labelError" id="fecha_termino_postnatal_parentalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">N&uacute;mero Dias Permiso Pagado</label>
								<input class="inputForm" maxlength="3" type="text" name="num_dias_permiso_pagado" id="num_dias_permiso_pagado"/>
								<label class="labelError" id="num_dias_permiso_pagadoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Tipo de Pago</label>
								<input class="inputForm" maxlength="1" type="text" name="tipo_de_pago" id="tipo_de_pago"/>
								<label class="labelError" id="tipo_de_pagoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Subsidio DFL44_1978</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_sub_dfl44_1978" id="monto_sub_dfl44_1978"/>
								<label class="labelError" id="monto_sub_dfl44_1978Marca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Subsidio Pagado</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_sub_pagado" id="monto_sub_pagado"/>
								<label class="labelError" id="monto_sub_pagadoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Tipo Emisi&oacute;n</label>
								<input class="inputForm" maxlength="2" type="text" name="tipo_emision" id="tipo_emision"/>
								<label class="labelError" id="tipo_emisionMarca"></label>
							</div>
							
							<div id="campoForm">
								<label class="labelForm">Mes N&oacute;mina</label>
								<input class="inputForm" onclick="javascript:cargarFecha('formActualizar','fechaPeriodo','mes_nomina')" maxlength="6" type="text" name="mes_nomina" id="mes_nomina"/>
								<label class="labelError" id="mes_nominaMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Modalidad De Pago</label>
								<input class="inputForm" maxlength="1" type="text" name="mod_pago" id="mod_pago"/>
								<label class="labelError" id="mod_pagoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Serie Documento</label>
								<input class="inputForm" maxlength="20" type="text" name="serie_documento" id="serie_documento"/>
								<label class="labelError" id="serie_documentoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">N&uacute;mero Documento</label>
								<input class="inputForm" maxlength="20" type="text" name="num_documento" id="num_documento"/>
								<label class="labelError" id="num_documentoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Fecha Emisi&oacute;n Documento</label>
								<input class="inputForm" onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_emision_documento')" maxlength="10" type="text" name="fecha_emision_documento" id="fecha_emision_documento"/>
								<label class="labelError" id="fecha_emision_documentoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Del Documento</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_documento" id="monto_documento"/>
								<label class="labelError" id="monto_documentoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">C&oacute;digo del Banco</label>
								<input class="inputForm" maxlength="3" type="text" name="codigo_banco" id="codigo_banco"/>
								<label class="labelError" id="codigo_bancoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Cuenta Corriente</label>
								<input class="inputForm" maxlength="20" type="text" name="cta_cte" id="cta_cte"/>
								<label class="labelError" id="cta_cteMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Causal Emisi&oacute;n</label>
								<input class="inputForm" maxlength="1" type="text" name="causal_emision" id="causal_emision"/>
								<label class="labelError" id="causal_emisionMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">N&uacute;mero Dias Cotizados Pagados</label>
								<input class="inputForm" maxlength="3" type="text" name="num_dias_cotiz_pagados" id="num_dias_cotiz_pagados"/>
								<label class="labelError" id="num_dias_cotiz_pagadosMarca"></label>
							</div>
							
							<div id="campoForm">
								<label class="labelForm">Monto Remuneraci&oacute;n imponible</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_remun_imponible" id="monto_remun_imponible"/>
								<label class="labelError" id="monto_remun_imponibleMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto FP</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_fp" id="monto_fp"/>
								<label class="labelError" id="monto_fpMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Salud</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_salud" id="monto_salud"/>
								<label class="labelError" id="monto_saludMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Salud AD</label>
								<input class="inputForm" maxlength="15" type="text" name=monto_salud_ad id="monto_salud_ad"/>
								<label class="labelError" id="monto_salud_adMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Desahucio</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_desahucio" id="monto_desahucio"/>
								<label class="labelError" id="monto_desahucioMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Cotizaci&oacute;n FU</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_cotiz_fu" id="monto_cotiz_fu"/>
								<label class="labelError" id="monto_cotiz_fuMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Monto Cotizaci&oacute;n SC</label>
								<input class="inputForm" maxlength="15" type="text" name="monto_cotiz_sc" id="monto_cotiz_sc"/>
								<label class="labelError" id="monto_cotiz_scMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Entidad Previsional</label>
								<input class="inputForm" maxlength="5" type="text" name="entidad_previsional" id="entidad_previsional"/>
								<label class="labelError" id="entidad_previsionalMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Traspaso</label>
								<input class="inputForm" maxlength="1" type="text" name="traspaso" id="traspaso"/>
								<label class="labelError" id="traspasoMarca"></label>
							</div>
							<div id="campoForm">
								<label class="labelForm">Subsidio Iniciado</label>
								<input class="inputForm" maxlength="1" type="text" name="subsidio_iniciado" id="subsidio_iniciado"/>
								<label class="labelError" id="subsidio_iniciadoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm"> Indicador convenio</label>
								<input maxlength="1" class="inputForm" TYPE="text" NAME="indicador_convenio" id="indicador_convenio" />
								<label class="labelError" id="indicador_convenioMarca"></label>
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