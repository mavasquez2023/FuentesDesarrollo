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

	<script src="jqSimat/ajax/jsAjaxT2.js"></script>
	<script src="jqSimat/validacionesForm/val_plano2_SubsPrepostNM.js"></script>
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
			<h2> Subsidios prenatales, postnatales y por enfermedad grave del niño menor de un año</h2>
			<hr>
			<fieldset class="form-fieldset">
				<h3> Busquedas </h3>
				<div id="tabsBusqueda">
				  <ul>
				    <li><a href="#tabs-1">Rut beneficiario</a></li>
				    <li><a href="#tabs-2">N&uacute;mero Documento</a></li>
				  </ul>
				  <div id="tabs-1">
				    <form action="buscarByRutBenefSubsPrePostNm.do" id="filtroRutBenef" name="filtroRutBenef" method="post">
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
				    <form action="buscarByNumDocSubsPrePostNm.do" id="filtroNumDoc" name="filtroNumDoc" method="post">
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
				<input type="button" class="boton" value="Quitar Resultados Busquedas" id="botonRecargar" name="botonRecargar" onclick="javascript:goPage2()"/>							
			</fieldset>
			
			<div id="workSpacePaginacion" name="workSpacePaginacion">
				<fieldset class="form-fieldset">				
					<form action="PaginarT2.do" method="Post" class="form" name="formAvance" id="formAvance">
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
	                                <th>Run Beneficiario</th>
	                                <th>N&uacute;mero de documento</th>
	                                <th>Acciones</th>		                               
								</tr>
							</thead>
							<tbody>
								<logic:empty name="listaSubsPrePostNM">
									<h3>No existen datos para el periodo seleccionado.</h3>
								</logic:empty>
								<logic:iterate  name="listaSubsPrePostNM" id="listaSubsPrePostNM">
									<tr>
	                             		<td><div id="spaceID"><bean:write name="listaSubsPrePostNM" property="id"/></div></td>
										<td><input type="text" value="<bean:write name="listaSubsPrePostNM" property="mes_informacion" />" /></td> 
										<td><input type="text" value="<bean:write name="listaSubsPrePostNM" property="nombre_beneficiario" />" /></td>
										<td><input type="text" value="<bean:write name="listaSubsPrePostNM" property="run_beneficiario" />" /></td>
										<td><input type="text" value="<bean:write name="listaSubsPrePostNM" property="num_documento" />" /></td>
										<td>
											<input type="button" class="boton" value="Borrar" onclick="javascript:openBorrar('<bean:write name="listaSubsPrePostNM" property="id"/>')"/>
										
											<input type="button" class="boton" value="actualizar" onclick="javascript:openActualizar(2,'<bean:write name="listaSubsPrePostNM" property="id"/>')"/>
										</td> 
									</tr>
								</logic:iterate>                             	
							</tbody>
                        </table>
                        <br>  
                        <br>  
                        
                        <div align="right">

            			<input type="button" class="boton" value="Agregar" onclick="javascript:openInsertar(2)"/>

      					</div>
                        <br>  
			</form>
		</fieldset>
		
		<fieldset class="form-fieldset">
			<div name="vm" id="vm">
				<input class="boton" type="button" value='<< Volver a Menu' onclick="javascript:volverMenu()"/>
			</div>
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
					<form action="insertarSubsPrePostNm.do" type="cl.laaraucana.simat.forms.SubsPrePostNMForm" name="formInsertar" id="formInsertar" method="post" class="form" >
					
						<input type="hidden" name="metodo" id="metodo" value="insertar"/> 
		
						<div class="campoForm">
							<label class="labelForm">Mes De Informaci&oacute;n</label>
							<input onclick="javascript:cargarFecha('formInsertar','fechaPeriodo','mes_informacion')" maxlength="6" class="inputForm" TYPE="text" NAME="mes_informacion" id="mes_informacion" />
							<label class="labelError" id="mes_informacionMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">C&oacute;digo Entidad</label>
							<input maxlength="5" class="inputForm" TYPE="text" NAME="codigo_entidad" id="codigo_entidad" />
							<label class="labelError" id="codigo_entidadMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">Agencia Entidad</label>
							<input maxlength="20" class="inputForm" TYPE="text" NAME="agencia_entidad" id="agencia_entidad" />
							<label class="labelError" id="agencia_entidadMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">Rut Empleador</label>
							<input maxlength="11" class="inputForm" TYPE="text" NAME="rut_empleador" id="rut_empleador" />
							<label class="labelError" id="rut_empleadorMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">Nombre Empleador</label>
							<input maxlength="80" class="inputForm" TYPE="text" NAME="nombre_empleador" id="nombre_empleador" />
							<label class="labelError" id="nombre_empleadorMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">Run Beneficiario</label>
							<input maxlength="11" class="inputForm" TYPE="text" NAME="run_beneficiario" id="run_beneficiario" />
							<label class="labelError" id="run_beneficiarioMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">Nombre Beneficiario</label>
							<input maxlength="80" class="inputForm" TYPE="text" NAME="nombre_beneficiario" id="nombre_beneficiario" />
							<label class="labelError" id="nombre_beneficiarioMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">Nro Licencia</label>
							<input maxlength="14" class="inputForm" TYPE="text" NAME="nro_licencia" id="nro_licencia" />
							<label class="labelError" id="nro_licenciaMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">C&oacute;digo Diagnostico</label>
							<input maxlength="20" class="inputForm" TYPE="text" NAME="codigo_diagnostico" id="codigo_diagnostico" />
							<label class="labelError" id="codigo_diagnosticoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">V&iacute;nculo Beneficiario Menor</label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="vinculo_beneficiario_menor" id="vinculo_beneficiario_menor" />
							<label class="labelError" id="vinculo_beneficiario_menorMarca"></label>
						</div>
						
						<div class="campoForm">
							<label class="labelForm">Actividad Laboral Trabajador</label>
							<input maxlength="2" class="inputForm" TYPE="text" NAME="actividad_laboral_trabajador" id="actividad_laboral_trabajador" />
							<label class="labelError" id="actividad_laboral_trabajadorMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> C&oacute;digo Comuna Beneficiario</label>
							<input maxlength="5" class="inputForm" TYPE="text" NAME="cod_comuna_beneficiario" id="cod_comuna_beneficiario" />
							<label class="labelError" id="cod_comuna_beneficiarioMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">N&uacute;mero Resoluci&oacute;n</label>
							<input maxlength="20" class="inputForm" TYPE="text" NAME="nro_resolucion" id="nro_resolucion" />
							<label class="labelError" id="nro_resolucionMarca"></label>
						</div> 
						<div class="campoForm">
							<label class="labelForm"> Extensi&oacute;n Postnatal</label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="extension_postnatal" id="extension_postnatal" />
							<label class="labelError" id="extension_postnatalMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> N&uacute;mero Nacimientos</label>
							<input maxlength="2" class="inputForm" TYPE="text" NAME="nro_nacimientos" id="nro_nacimientos" />
							<label class="labelError" id="nro_nacimientosMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> N&uacute;mero D&iacute;as Licencia Autorizados</label>
							<input maxlength="3" class="inputForm" TYPE="text" NAME="num_dias_licencia_autorizados" id="num_dias_licencia_autorizados" />
							<label class="labelError" id="num_dias_licencia_autorizadosMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Fecha Inicio Reposo</label>
							<input onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_inicio_reposo')" maxlength="10" class="inputForm" TYPE="text" NAME="fecha_inicio_reposo" id="fecha_inicio_reposo" />
							<label class="labelError" id="fecha_inicio_reposoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Fecha T&eacute;rmino Reposo</label>
							<input onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_termino_reposo')" maxlength="10" class="inputForm" TYPE="text" NAME="fecha_termino_reposo" id="fecha_termino_reposo" />
							<label class="labelError" id="fecha_termino_reposoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> N&uacute;mero d&iacute;as Subsidio Pagados</label>
							<input maxlength="3" class="inputForm" TYPE="text" NAME="num_dias_sub_pagadados" id="num_dias_sub_pagadados" />
							<label class="labelError" id="num_dias_sub_pagadadosMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Tipo de Pago</label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="tipo_de_pago" id="tipo_de_pago" />
							<label class="labelError" id="tipo_de_pagoMarca"></label>
						</div>				
						<div class="campoForm">
							<label class="labelForm"> Monto Subsidio DFL44_1978</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_sub_dfl44_1978" id="monto_sub_dfl44_1978" />
							<label class="labelError" id="monto_sub_dfl44_1978Marca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Monto Subisidio Pagado</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_sub_pagado" id="monto_sub_pagado" />
							<label class="labelError" id="monto_sub_pagadoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Tipo Emisi&oacute;n</label>
							<input maxlength="2" class="inputForm" TYPE="text" NAME="tipo_emision" id="tipo_emision" />
							<label class="labelError" id="tipo_emisionMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Mes N&oacute;mina</label>
							<input onclick="javascript:cargarFecha('formInsertar','fechaPeriodo','mes_nomina')" maxlength="6" class="inputForm" TYPE="text" NAME="mes_nomina" id="mes_nomina" />
							<label class="labelError" id="mes_nominaMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Modalidad de Pago</label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="mod_pago" id="mod_pago" />
							<label class="labelError" id="mod_pagoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Serie Documento</label>
							<input maxlength="20" class="inputForm" TYPE="text" NAME="serie_documento" id="serie_documento" />
							<label class="labelError" id="serie_documentoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> N&uacute;mero Documento</label>
							<input maxlength="20" class="inputForm" TYPE="text" NAME="num_documento" id="num_documento" />
							<label class="labelError" id="num_documentoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Fecha Emisi&oacute;n Documento</label>
							<input onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_emision_documento')" maxlength="10" class="inputForm" TYPE="text" NAME="fecha_emision_documento" id="fecha_emision_documento" />
							<label class="labelError" id="fecha_emision_documentoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Monto Documento</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_documento" id="monto_documento" />
							<label class="labelError" id="monto_documentoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> C&oacute;digo de Banco</label>
							<input maxlength="3" class="inputForm" TYPE="text" NAME="codigo_banco" id="codigo_banco" />
							<label class="labelError" id="codigo_bancoMarca"></label>
						</div>						
						<div class="campoForm">
							<label class="labelForm">  Cuenta Corriente</label>
							<input maxlength="20" class="inputForm" TYPE="text" NAME="cta_cte" id="cta_cte" />
							<label class="labelError" id="cta_cteMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Causal Emisi&oacute;n </label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="causal_emision" id="causal_emision" />
							<label class="labelError" id="causal_emisionMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> N&uacute;mero D&iacute;as Cotizados Pagados</label>
							<input maxlength="3" class="inputForm" TYPE="text" NAME="num_dias_cotiz_pagados" id="num_dias_cotiz_pagados" />
							<label class="labelError" id="num_dias_cotiz_pagadosMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">  Monto Remuneraci&oacute;n Imponible</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_renum_imponible" id="monto_renum_imponible" />
							<label class="labelError" id="monto_renum_imponibleMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">  Monto FP</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_fp" id="monto_fp" />
							<label class="labelError" id="monto_fpMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Monto Salud</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_salud" id="monto_salud" />
							<label class="labelError" id="monto_saludMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">  Monto Salud AD</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_salud_ad" id="monto_salud_ad" />
							<label class="labelError" id="monto_salud_adMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">  Monto Desahucio</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_desahucio" id="monto_desahucio" />
							<label class="labelError" id="monto_desahucioMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Monto Cotizaci&oacute;n FU</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_cotiz_fu" id="monto_cotiz_fu" />
							<label class="labelError" id="monto_cotiz_fuMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">  Monto Cotizaci&oacute;n SC</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_cotiz_sc" id="monto_cotiz_sc" />
							<label class="labelError" id="monto_cotiz_scMarca"></label>
						</div>
						
						<div class="campoForm">
							<label class="labelForm"> Entidad Previsional</label>
							<input maxlength="5" class="inputForm" TYPE="text" NAME="entidad_previsional" id="entidad_previsional" />
							<label class="labelError" id="entidad_previsionalMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Subsidio Iniciado</label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="subsidio_iniciado" id="subsidio_iniciado" />
							<label class="labelError" id="subsidio_iniciadoMarca"></label>
						</div>	
						<div class="campoForm">
							<label class="labelForm"> Indicador convenio</label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="indicador_convenio" id="indicador_convenio" />
							<label class="labelError" id="indicador_convenioMarca"></label>
						</div>				
					</FORM> 
				</fieldset>
			</div>
				
		</div>
			<div id="borrar-dialog" title="Confirmaci&oacute;n Borrar">
				<div id="simulacion2">
					<fieldset class="form-fieldset">
						<form action="eliminarSubsPrePostNm.do" type="cl.laaraucana.simat.forms.SubsPrepostNMForm" name="formBorrar" id="formBorrar" method="post" class="form" >
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
						<form action="actualizarSubsPrePostNm.do" type="cl.laaraucana.simat.forms.SubsPrePostNMForm" name="formActualizar" id="formActualizar" method="post" 
							class="form" >
						<input type="hidden" name="metodo" id="metodo" value="actualizar"/> 
						
						<div class="campoForm">
							<label class="labelForm">Identificador</label>							
							<input disabled="disabled" readonly="readonly" class="inputForm" type="text" name="id" id="id">
						</div>
								
						<div class="campoForm">
							<label class="labelForm">Mes De Informaci&oacute;n</label>
							<input onclick="javascript:cargarFecha('formActualizar','fechaPeriodo','mes_informacion')" maxlength="6" class="inputForm" TYPE="text" NAME="mes_informacion" id="mes_informacion" />
							<label class="labelError" id="mes_informacionMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">C&oacute;digo Entidad</label>
							<input maxlength="5" class="inputForm" TYPE="text" NAME="codigo_entidad" id="codigo_entidad" />
							<label class="labelError" id="codigo_entidadMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">Agencia Entidad</label>
							<input maxlength="20" class="inputForm" TYPE="text" NAME="agencia_entidad" id="agencia_entidad" />
							<label class="labelError" id="agencia_entidadMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">Rut Empleador</label>
							<input maxlength="11" class="inputForm" TYPE="text" NAME="rut_empleador" id="rut_empleador" />
							<label class="labelError" id="rut_empleadorMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">Nombre Empleador</label>
							<input maxlength="80" class="inputForm" TYPE="text" NAME="nombre_empleador" id="nombre_empleador" />
							<label class="labelError" id="nombre_empleadorMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">Run Beneficiario</label>
							<input maxlength="11" class="inputForm" TYPE="text" NAME="run_beneficiario" id="run_beneficiario" />
							<label class="labelError" id="run_beneficiarioMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">Nombre Beneficiario</label>
							<input maxlength="80" class="inputForm" TYPE="text" NAME="nombre_beneficiario" id="nombre_beneficiario" />
							<label class="labelError" id="nombre_beneficiarioMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">Nro Licencia</label>
							<input maxlength="14" class="inputForm" TYPE="text" NAME="nro_licencia" id="nro_licencia" />
							<label class="labelError" id="nro_licenciaMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">C&oacute;digo Diagnostico</label>
							<input maxlength="20" class="inputForm" TYPE="text" NAME="codigo_diagnostico" id="codigo_diagnostico" />
							<label class="labelError" id="codigo_diagnosticoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">V&iacute;nculo Beneficiario Menor</label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="vinculo_beneficiario_menor" id="vinculo_beneficiario_menor" />
							<label class="labelError" id="vinculo_beneficiario_menorMarca"></label>
						</div>
						
						<div class="campoForm">
							<label class="labelForm">Actividad Laboral Trabajador</label>
							<input maxlength="2" class="inputForm" TYPE="text" NAME="actividad_laboral_trabajador" id="actividad_laboral_trabajador" />
							<label class="labelError" id="actividad_laboral_trabajadorMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> C&oacute;digo Comuna Beneficiario</label>
							<input maxlength="5" class="inputForm" TYPE="text" NAME="cod_comuna_beneficiario" id="cod_comuna_beneficiario" />
							<label class="labelError" id="cod_comuna_beneficiarioMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">N&uacute;mero Resoluci&oacute;n</label>
							<input maxlength="20" class="inputForm" TYPE="text" NAME="nro_resolucion" id="nro_resolucion" />
							<label class="labelError" id="nro_resolucionMarca"></label>
						</div> 
						<div class="campoForm">
							<label class="labelForm"> Extensi&oacute;n Postnatal</label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="extension_postnatal" id="extension_postnatal" />
							<label class="labelError" id="extension_postnatalMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> N&uacute;mero Nacimientos</label>
							<input maxlength="2" class="inputForm" TYPE="text" NAME="nro_nacimientos" id="nro_nacimientos" />
							<label class="labelError" id="nro_nacimientosMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> N&uacute;mero D&iacute;as Licencia Autorizados</label>
							<input maxlength="3" class="inputForm" TYPE="text" NAME="num_dias_licencia_autorizados" id="num_dias_licencia_autorizados" />
							<label class="labelError" id="num_dias_licencia_autorizadosMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Fecha Inicio Reposo</label>
							<input onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_inicio_reposo')" maxlength="10" class="inputForm" TYPE="text" NAME="fecha_inicio_reposo" id="fecha_inicio_reposo" />
							<label class="labelError" id="fecha_inicio_reposoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Fecha T&eacute;rmino Reposo</label>
							<input onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_termino_reposo')" maxlength="10" class="inputForm" TYPE="text" NAME="fecha_termino_reposo" id="fecha_termino_reposo" />
							<label class="labelError" id="fecha_termino_reposoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> N&uacute;mero d&iacute;as Subsidio Pagados</label>
							<input maxlength="3" class="inputForm" TYPE="text" NAME="num_dias_sub_pagadados" id="num_dias_sub_pagadados" />
							<label class="labelError" id="num_dias_sub_pagadadosMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Tipo de Pago</label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="tipo_de_pago" id="tipo_de_pago" />
							<label class="labelError" id="tipo_de_pagoMarca"></label>
						</div>				
						<div class="campoForm">
							<label class="labelForm"> Monto Subsidio DFL44_1978</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_sub_dfl44_1978" id="monto_sub_dfl44_1978" />
							<label class="labelError" id="monto_sub_dfl44_1978Marca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Monto Subisidio Pagado</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_sub_pagado" id="monto_sub_pagado" />
							<label class="labelError" id="monto_sub_pagadoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Tipo Emisi&oacute;n</label>
							<input maxlength="2" class="inputForm" TYPE="text" NAME="tipo_emision" id="tipo_emision" />
							<label class="labelError" id="tipo_emisionMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Mes N&oacute;mina</label>
							<input onclick="javascript:cargarFecha('formActualizar','fechaPeriodo','mes_nomina')" maxlength="6" class="inputForm" TYPE="text" NAME="mes_nomina" id="mes_nomina" />
							<label class="labelError" id="mes_nominaMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Modalidad de Pago</label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="mod_pago" id="mod_pago" />
							<label class="labelError" id="mod_pagoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Serie Documento</label>
							<input maxlength="20" class="inputForm" TYPE="text" NAME="serie_documento" id="serie_documento" />
							<label class="labelError" id="serie_documentoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> N&uacute;mero Documento</label>
							<input maxlength="20" class="inputForm" TYPE="text" NAME="num_documento" id="num_documento" />
							<label class="labelError" id="num_documentoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Fecha Emisi&oacute;n Documento</label>
							<input onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_emision_documento')" maxlength="10" class="inputForm" TYPE="text" NAME="fecha_emision_documento" id="fecha_emision_documento" />
							<label class="labelError" id="fecha_emision_documentoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Monto Documento</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_documento" id="monto_documento" />
							<label class="labelError" id="monto_documentoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> C&oacute;digo de Banco</label>
							<input maxlength="3" class="inputForm" TYPE="text" NAME="codigo_banco" id="codigo_banco" />
							<label class="labelError" id="codigo_bancoMarca"></label>
						</div>						
						<div class="campoForm">
							<label class="labelForm">  Cuenta Corriente</label>
							<input maxlength="20" class="inputForm" TYPE="text" NAME="cta_cte" id="cta_cte" />
							<label class="labelError" id="cta_cteMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Causal Emisi&oacute;n </label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="causal_emision" id="causal_emision" />
							<label class="labelError" id="causal_emisionMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> N&uacute;mero D&iacute;as Cotizados Pagados</label>
							<input maxlength="3" class="inputForm" TYPE="text" NAME="num_dias_cotiz_pagados" id="num_dias_cotiz_pagados" />
							<label class="labelError" id="num_dias_cotiz_pagadosMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">  Monto Remuneraci&oacute;n Imponible</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_renum_imponible" id="monto_renum_imponible" />
							<label class="labelError" id="monto_renum_imponibleMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">  Monto FP</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_fp" id="monto_fp" />
							<label class="labelError" id="monto_fpMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Monto Salud</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_salud" id="monto_salud" />
							<label class="labelError" id="monto_saludMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">  Monto Salud AD</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_salud_ad" id="monto_salud_ad" />
							<label class="labelError" id="monto_salud_adMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">  Monto Desahucio</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_desahucio" id="monto_desahucio" />
							<label class="labelError" id="monto_desahucioMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Monto Cotizaci&oacute;n FU</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_cotiz_fu" id="monto_cotiz_fu" />
							<label class="labelError" id="monto_cotiz_fuMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm">  Monto Cotizaci&oacute;n SC</label>
							<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_cotiz_sc" id="monto_cotiz_sc" />
							<label class="labelError" id="monto_cotiz_scMarca"></label>
						</div>
						
						<div class="campoForm">
							<label class="labelForm"> Entidad Previsional</label>
							<input maxlength="5" class="inputForm" TYPE="text" NAME="entidad_previsional" id="entidad_previsional" />
							<label class="labelError" id="entidad_previsionalMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Subsidio Iniciado</label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="subsidio_iniciado" id="subsidio_iniciado" />
							<label class="labelError" id="subsidio_iniciadoMarca"></label>
						</div>
						<div class="campoForm">
							<label class="labelForm"> Indicador convenio</label>
							<input maxlength="1" class="inputForm" TYPE="text" NAME="indicador_convenio" id="indicador_convenio" />
							<label class="labelError" id="indicador_convenioMarca"></label>
						</div>						
					</FORM> 
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