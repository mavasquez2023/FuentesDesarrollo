<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%
	String keyInicioCopy=(String)request.getAttribute("keyInicioCopy");
	String keyFinCopy=(String)request.getAttribute("keyFinCopy");
	String 	msgPaginacion=(String)request.getAttribute("msgPaginacion");
	String keyEstadoDoc=(String)request.getAttribute("keyEstadoDoc");
	if(msgPaginacion==null){
	msgPaginacion="";
	}
	if(keyInicioCopy==null || keyInicioCopy.equals("")){
		keyInicioCopy="0";
	}
	if(keyFinCopy==null || keyFinCopy.equals("")){
		keyFinCopy="0";
	}
	if(keyEstadoDoc==null || keyEstadoDoc.equals("")){
		keyEstadoDoc="0";
	}
 %>
    
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SIMAT</title>
	<meta http-equiv="X-UA-Compatible" content="IE=8, IE=9, IE=10" />
	<link rel="stylesheet" type="text/css" href="css/estilos.css">
	<link rel="stylesheet" type="text/css" href="css/normalize.css">
	<link rel="stylesheet" type="text/css" href="css/simular.css">
	<link rel="stylesheet" type="text/css" href="css/simular2.css">
	<link rel="stylesheet" href='css/main.css' type="text/css" />
	<link rel="stylesheet" href='css/screen.css' type="text/css" />
	<link rel="stylesheet" href='css/interior.css' type="text/css" />

	<link rel="stylesheet" href='cssSimat/estructura.css' type="text/css" />

	<link href="jqSimat/jqBlueUI/css/redmond/jquery-ui-1.9.2.custom.css" rel="stylesheet">
	<script src="jqSimat/jqBlueUI/js/jquery-1.8.3.js"></script>
	<script src="jqSimat/jqBlueUI/js/jquery-ui-1.9.2.custom.js"></script>
	
	<script src="jqSimat/calendar/ajustesCalendario.js"></script>
	<script src="jqSimat/botones/ajustesBotones.js"></script>
	<script src="jqSimat/botones/ajustesDialog.js"></script>
	
	<script src="jqSimat/ajax/jsAjaxT5.js"></script>
	<script src="jqSimat/validacionesForm/val_plano5_ControlDocu.js"></script>
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
		
		<h2>N&oacute;mina de control de los documentos</h2>
		<hr>
			<fieldset class="form-fieldset">
				<h3> Busquedas </h3>
				<div id="tabsBusqueda">
				  <ul>
				    <li><a href="#tabs-1">N&uacute;mero documento</a></li>
				    <li><a href="#tabs-2">Estado Documento</a></li>
				  </ul>
				  <div id="tabs-1">
				  	<form action="buscarControlDocuByNumDoc.do" id="filtroNumDoc" name="filtroNumDoc" method="post">
						<input type="hidden" name="metodo" id="metodo" value="buscarByNumDoc"/>
						<div class="field">
							<input type="text" name="numDoc" id="numDoc"/>
						</div>							
						<div align="left">
							<input type="button" class="boton" value="Buscar" id="botonBuscar" name="botonbuscar" onclick="javascript:BuscarNumDoc()"/>
						</div>
					</form>
				  </div>
				  <div id="tabs-2">
				    <form action="buscarControlDocuByEstadoDoc.do" id="filtroEstadoDoc" name="filtroEstadoDoc" method="post">
						<input type="hidden" name="metodo" id="metodo" value="buscarByEstadoDoc"/>
						<div class="field">
							<input type="text" name="estadoDoc" id="estadoDoc"/>
						</div>							
						<div align="left">
							<input type="button" class="boton" value="Buscar" id="botonBuscar" name="botonbuscar" onclick="javascript:BuscarEstadoDoc()"/>
						</div>
					</form>	
				  </div>
				</div>
				<input type="button" class="boton" value="Quitar Resultados Busquedas" id="botonRecargar" name="botonRecargar" onclick="javascript:goPage5()"/>							
			</fieldset>	
			<div id="workSpacePaginacion" name="workSpacePaginacion">
				<fieldset class="form-fieldset">				
					<form action="PaginarT5.do" method="Post" class="form" name="formAvance" id="formAvance">
						<input type="hidden" name="metodo" id="metodo" value="mostrarPaginacion"/>
						<input type="hidden" name="keyAvance" id="keyAvance"/>
						<input type="hidden" name="keyInicio" id="keyInicio" value=<%out.print(keyInicioCopy); %> />
						<input type="hidden" name="keyFin" id="keyFin" value=<%out.print(keyFinCopy); %> />
						<input type="hidden" name="keyEstadoDoc" id="keyEstadoDoc" value=<%out.print(keyEstadoDoc); %> />
					</form>
					<label class="labelForm"><% out.print(msgPaginacion); %></label>
					<input class="boton" type="button" value='<< atras' id="botonRetroceder" onclick="javascritp:retrocederEstadoDoc('r',<%out.print(keyEstadoDoc); %>)"/>
					<input class="boton" type="button" value='adelante >>' id="botonAvanzar" onclick="javascritp:avanzarEstadoDoc('a',<%out.print(keyEstadoDoc); %>)"/>
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
	                                   <th>Run beneficiario</th>
	                                   <th>N&uacute;mero documento</th>		                                   
	                                   <th>Acciones</th>
		                             </tr>
		                             </thead>
		                             <tbody>
		                             	<logic:empty name="listaControlDocu">
											<h3>No existen datos para el periodo seleccionado.</h3>
										</logic:empty>
		                             	<logic:iterate  name="listaControlDocu" id="listaControlDocu">
		                             		<tr>
		                             			<td><div id="spaceID"><bean:write name="listaControlDocu" property="idControlDocu"/></div></td>
			                             		<td><input readonly type="text" value="<bean:write name="listaControlDocu" property="mes_informacion" />"/></td>
												<td><input readonly type="text" value="<bean:write name="listaControlDocu" property="nombre_beneficiario" />" /></td> 
												<td><input readonly type="text" value="<bean:write name="listaControlDocu" property="run_beneficiario" />" /></td>
												<td><input readonly type="text" value="<bean:write name="listaControlDocu" property="num_documento" />" /></td>
												<td>
												<input type="button" class="boton" value="Borrar" onclick="javascript:openBorrar('<bean:write name="listaControlDocu" property="idControlDocu"/>')"/>
												
												<input type="button" class="boton" value="actualizar" onclick="javascript:openActualizar(5,'<bean:write name="listaControlDocu" property="idControlDocu"/>')"/>												
												</td> 
		                             		</tr>
		                             	</logic:iterate>
		                             </tbody>
		                        </table>
		                        <br>  
		                        <br>  
		                        
		                        <div align="right">
		            			<input type="button" class="boton" value="Agregar" onclick="javascript:openInsertar(5)"/>
		
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
		
			<div id="insertar-dialog" title="Ingreso N&oacute;mina">
				<div id="simulacion2">
					<fieldset class="form-fieldset">
						
						<form action="insertarControlDocu.do" type="cl.laaraucana.simat.forms.ControlDocuForm"
								method="post" class="form" name="formInsertar" id="formInsertar">
								
							<input type="hidden" name="metodo" id="metodo" value="insertar"/>
					
							<div class="campoForm">
								<label class="labelForm">Mes Informaci&oacute;n</label>
								<input onclick="javascript:cargarFecha('formInsertar','fechaPeriodo','mes_informacion')" maxlength="6" class="inputForm" type="text" name="mes_informacion" id="mes_informacion"/>
								<label class="labelError" id="mes_informacionMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Entidad</label>
								<input maxlength="5" class="inputForm" type="text" name="codigo_entidad" id="codigo_entidad"/>
								<label class="labelError" id="codigo_entidadMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Tipo Subsidio</label>
								<input maxlength="1" class="inputForm" type="text" name="tipo_subsidio" id="tipo_subsidio"/>
								<label class="labelError" id="tipo_subsidioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Rut Empleador</label>
								<input maxlength="11" class="inputForm" type="text" name="rut_empleador" id="rut_empleador"/>
								<label class="labelError" id="rut_empleadorMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Nombre Empleador</label>
								<input maxlength="80" class="inputForm" type="text" name="nombre_empleador" id="nombre_empleador"/>
								<label class="labelError" id="nombre_empleadorMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Run Beneficiario</label>
								<input maxlength="11" class="inputForm" type="text" name="run_beneficiario" id="run_beneficiario"/>
								<label class="labelError" id="run_beneficiarioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Nombre Beneficiario</label>
								<input maxlength="80" class="inputForm" type="text" name="nombre_beneficiario" id="nombre_beneficiario"/>
								<label class="labelError" id="nombre_beneficiarioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Modo de Pago</label>
								<input maxlength="1" class="inputForm" type="text" name="mod_pago" id="mod_pago"/>
								<label class="labelError" id="mod_pagoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Serie de Documento</label>
								<input maxlength="20" class="inputForm" type="text" name="serie_documento" id="serie_documento"/>
								<label class="labelError" id="serie_documentoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">N&uacute;mero de Documento</label>
								<input maxlength="20" class="inputForm" type="text" name="num_documento" id="num_documento"/>
								<label class="labelError" id="num_documentoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha Emisi&oacute;n Documento</label>
								<input onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_emision_documento')" maxlength="10" class="inputForm" type="text" name="fecha_emision_documento" id="fecha_emision_documento"/>
								<label class="labelError" id="fecha_emision_documentoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Monto documento</label>
								<input maxlength="15" class="inputForm" type="text" name="monto_documento" id="monto_documento"/>
								<label class="labelError" id="monto_documentoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo de Banco</label>
								<input maxlength="3" class="inputForm" type="text" name="codigo_banco" id="codigo_banco"/>
								<label class="labelError" id="codigo_bancoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Estado Documento</label>
								<input maxlength="1" class="inputForm" type="text" name="estado_documento" id="estado_documento"/>
								<label class="labelError" id="estado_documentoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha cambio de estado</label>
								<input onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_cambio_estado')" maxlength="10" class="inputForm" type="text" name="fecha_cambio_estado" id="fecha_cambio_estado"/>
								<label class="labelError" id="fecha_cambio_estadoMarca"></label>
							</div>
						</form>
					</fieldset>
				</div>	
			</div>
			
			<div id="borrar-dialog" title="Confirmaci&oacute;n Borrar">
				<div id="simulacion2">
					<fieldset class="form-fieldset">
					
						<form action="eliminarControlDocu.do" method="post" class="form" name="formBorrar" id="formBorrar" type="cl.laaraucana.simat.forms.ControlDocuForm" >
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
					
						<form action="actualizarControlDocu.do" type="cl.laaraucana.simat.forms.ControlDocuForm" method="post" class="form" name="formActualizar" id="formActualizar" >
							
							<input type="hidden" name="metodo" id="metodo" value="actualizar"/>
					
							<div class="campoForm">
								<label class="labelForm">Identificador</label>							
								<input disabled="disabled" readonly="readonly" class="inputForm" type="text" name="id" id="id">
							</div>
							
							<div class="campoForm">
								<label class="labelForm">Mes Informaci&oacute;n</label>
								<input onclick="javascript:cargarFecha('formActualizar','fechaPeriodo','mes_informacion')" maxlength="6" class="inputForm" type="text" name="mes_informacion" id="mes_informacion"/>
								<label class="labelError" id="mes_informacionMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Entidad</label>
								<input maxlength="5" class="inputForm" type="text" name="codigo_entidad" id="codigo_entidad"/>
								<label class="labelError" id="codigo_entidadMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Tipo Subsidio</label>
								<input maxlength="1" class="inputForm" type="text" name="tipo_subsidio" id="tipo_subsidio"/>
								<label class="labelError" id="tipo_subsidioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Rut Empleador</label>
								<input maxlength="11" class="inputForm" type="text" name="rut_empleador" id="rut_empleador"/>
								<label class="labelError" id="rut_empleadorMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Nombre Empleador</label>
								<input maxlength="80" class="inputForm" type="text" name="nombre_empleador" id="nombre_empleador"/>
								<label class="labelError" id="nombre_empleadorMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Run Beneficiario</label>
								<input maxlength="11" class="inputForm" type="text" name="run_beneficiario" id="run_beneficiario"/>
								<label class="labelError" id="run_beneficiarioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Nombre Beneficiario</label>
								<input maxlength="80" class="inputForm" type="text" name="nombre_beneficiario" id="nombre_beneficiario"/>
								<label class="labelError" id="nombre_beneficiarioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Modo de Pago</label>
								<input maxlength="1" class="inputForm" type="text" name="mod_pago" id="mod_pago"/>
								<label class="labelError" id="mod_pagoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Serie de Documento</label>
								<input maxlength="20" class="inputForm" type="text" name="serie_documento" id="serie_documento"/>
								<label class="labelError" id="serie_documentoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">N&uacute;mero de Documento</label>
								<input maxlength="20" class="inputForm" type="text" name="num_documento" id="num_documento"/>
								<label class="labelError" id="num_documentoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha Emisi&oacute;n Documento</label>
								<input onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_emision_documento')" maxlength="10" class="inputForm" type="text" name="fecha_emision_documento" id="fecha_emision_documento"/>
								<label class="labelError" id="fecha_emision_documentoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Monto documento</label>
								<input maxlength="15" class="inputForm" type="text" name="monto_documento" id="monto_documento"/>
								<label class="labelError" id="monto_documentoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo de Banco</label>
								<input maxlength="3" class="inputForm" type="text" name="codigo_banco" id="codigo_banco"/>
								<label class="labelError" id="codigo_bancoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Estado Documento</label>
								<input maxlength="1" class="inputForm" type="text" name="estado_documento" id="estado_documento"/>
								<label class="labelError" id="estado_documentoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha cambio de estado</label>
								<input onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_cambio_estado')" maxlength="10" class="inputForm" type="text" name="fecha_cambio_estado" id="fecha_cambio_estado"/>
								<label class="labelError" id="fecha_cambio_estadoMarca"></label>
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