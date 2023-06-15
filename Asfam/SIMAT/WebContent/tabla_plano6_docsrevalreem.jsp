<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>


<%
	String keyInicioCopy=(String)request.getAttribute("keyInicioCopy");
	String keyFinCopy=(String)request.getAttribute("keyFinCopy");
	String keyEstadoDoc=(String)request.getAttribute("keyEstadoDoc");
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
	if(keyEstadoDoc==null || keyEstadoDoc.equals("")){
		keyEstadoDoc="0";
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
	
	<script src="jqSimat/ajax/jsAjaxT6.js"></script>
	<script src="jqSimat/validacionesForm/val_plano6_DocsRevalReem.js"></script>
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
		
		<h2>N&oacute;mina de documentos revalidados o reemitidos</h2>
		<hr>
		
		<fieldset class="form-fieldset">
				<h3> Busquedas </h3>
				<div id="tabsBusqueda">
				  <ul>
				    <li><a href="#tabs-1">N&uacute;mero documento</a></li>
				    <li><a href="#tabs-2">Estado Documento</a></li>
				  </ul>
				  <div id="tabs-1">
				  	<form action="buscarDocsRevalReemByNumDoc.do" id="filtroNumDoc" name="filtroNumDoc" method="post">
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
				    <form action="buscarDocsRevalReemByEstadoDoc.do" id="filtroEstadoDoc" name="filtroEstadoDoc" method="post">
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
				<input type="button" class="boton" value="Quitar Resultados Busquedas" id="botonRecargar" name="botonRecargar" onclick="javascript:goPage6()"/>							
			</fieldset>	
		
		<div id="workSpacePaginacion" name="workSpacePaginacion">
			<fieldset class="form-fieldset">				
				<form action="PaginarT6.do" method="Post" class="form" name="formAvance" id="formAvance">
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
										   <th>Serie documento Original</th>
										   <th>Serie Documento Nuevo</th>
										   <th>N&uacute;mero documento Original</th>		
										   <th>Acciones</th>
		                             </tr>
		                             </thead>
		                             <tbody>
		                             	<logic:empty name="listaDocsRevalReem">
											<h3>No existen datos para el periodo seleccionado.</h3>
										</logic:empty>
		                             	<logic:iterate  name="listaDocsRevalReem" id="listaDocsRevalReem">
		                             		<tr>
		                             			<td><div id="spaceID"><bean:write name="listaDocsRevalReem" property="id"/></div></td>
			                             		<td><input type="text" value="<bean:write name="listaDocsRevalReem" property="mes_informacion" />" /></td>
												<td><input type="text" value="<bean:write name="listaDocsRevalReem" property="serie_documento_original" />" /></td> 
												<td><input type="text" value="<bean:write name="listaDocsRevalReem" property="serie_documento_nuevo" />" /></td>
												<td><input type="text" value="<bean:write name="listaDocsRevalReem" property="num_documento_original" />" /></td> 
												<td>
												<input type="button" class="boton" value="Borrar" onclick="javascript:openBorrar('<bean:write name="listaDocsRevalReem" property="id"/>')"/>
												
												<input type="button" class="boton" value="actualizar" onclick="javascript:openActualizar(6,'<bean:write name="listaDocsRevalReem" property="id"/>')"/>												
												</td> 
		                             		</tr>
		                             	</logic:iterate>
		                             </tbody>
		                        </table>
		                        <br>  
		                        <br>  
		                        
		                        <div align="right">
		            			<input type="button" class="boton" value="Agregar" onclick="javascript:openInsertar(6)"/>
		
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
		
			<div id="insertar-dialog" title="Ingreso N&oacute;mina">
				<div id="simulacion2">
					<fieldset class="form-fieldset">
						<form action="insertarDocsRevalReem.do" type="cl.laaraucana.simat.forms.DocsRevalReemForm" id="formInsertar" name="formInsertar" method="post" class="form" >
							<input type="hidden" name="metodo" id="metodo" value="insertar"/>
							
							<div class="campoForm">
								<label class="labelForm">Mes Informaci&oacute;n</label>
								<input onclick="javascript:cargarFecha('formInsertar','fechaPeriodo','mes_informacion')" maxlength="6" class="inputForm" type="text" name="mes_informacion"" id="mes_informacion"/>
								<label class="labelError" id="mes_informacionMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Entidad</label>
								<input maxlength="5" class="inputForm" type="text" name="codigo_entidad" id="codigo_entidad"/>
								<label class="labelError" id="codigo_entidadMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Tipo Subsidio</label>
								<input maxlength="1" class="inputForm" type="text" name="tiposubsidio" id="tiposubsidio"/>
								<label class="labelError" id="tiposubsidioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Modo de Pago Original</label>
								<input maxlength="1" class="inputForm" type="text" name="mod_pago_original" id="mod_pago_original"/>
								<label class="labelError" id="mod_pago_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Banco Original</label>
								<input maxlength="3" class="inputForm" type="text" name="codigo_banco_original" id="codigo_banco_original"/>
								<label class="labelError" id="codigo_banco_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Serie Documento Original</label>
								<input maxlength="20" class="inputForm" type="text" name="serie_documento_original" id="serie_documento_original"/>
								<label class="labelError" id="serie_documento_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">N&uacute;mero Documento Original</label>
								<input maxlength="20" class="inputForm" type="text" name="num_documento_original" id="num_documento_original"/>
								<label class="labelError" id="num_documento_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha Emisi&oacute;n Documento Original</label>
								<input onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_emision_documento_original')" maxlength="10" class="inputForm" type="text" name="fecha_emision_documento_original" id="fecha_emision_documento_original"/>
								<label class="labelError" id="fecha_emision_documento_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Monto Documento Original</label>
								<input maxlength="15" class="inputForm" type="text" name="monto_documento_original" id="monto_documento_original"/>
								<label class="labelError" id="monto_documento_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Estado Documento Original</label>
								<input maxlength="1" class="inputForm" type="text" name="estado_documento_original" id="estado_documento_original"/>
								<label class="labelError" id="estado_documento_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Modo Pago Nuevo</label>
								<input maxlength="1" class="inputForm" type="text" name="modo_pago_nuevo" id="modo_pago_nuevo""/>
								<label class="labelError" id="modo_pago_nuevoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Banco Nuevo</label>
								<input maxlength="3" class="inputForm" type="text" name="codigo_banco_nuevo" id="codigo_banco_nuevo"/>
								<label class="labelError" id="codigo_banco_nuevoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Serie Documento Nuevo</label>
								<input maxlength="20" class="inputForm" type="text" name="serie_documento_nuevo" id="serie_documento_nuevo"/>
								<label class="labelError" id="serie_documento_nuevoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">N&uacute;mero Documento Nuevo</label>
								<input maxlength="20" class="inputForm" type="text" name="num_documento_nuevo" id="num_documento_nuevo"/>
								<label class="labelError" id="num_documento_nuevoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha Emisi&oacute;n Documento Nuevo</label>
								<input onclick="javascript:cargarFecha('formInsertar','fechaISO','fecha_emision_documento_nuevo')" maxlength="10" class="inputForm" type="text" name="fecha_emision_documento_nuevo" id="fecha_emision_documento_nuevo"/>
								<label class="labelError" id="fecha_emision_documento_nuevoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Monto Documento Nuevo</label>
								<input maxlength="15" class="inputForm" type="text" name="monto_documento_nuevo" id="monto_documento_nuevo"/>
								<label class="labelError" id="monto_documento_nuevoMarca"></label>
							</div>
						</form>
					</fieldset>
				</div>	
			</div>
			
			<div id="borrar-dialog" title="Confirmaci&oacute;n Borrar">
				<div id="simulacion2">
					<fieldset class="form-fieldset">
						<form action="eliminarDocsRevalReem.do" type="cl.laaraucana.simat.forms.DocsRevalReemForm" id="formBorrar" name="formBorrar" method="post" class="form" >
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
						<form action="actualizarDocsRevalReem.do" type="cl.laaraucana.simat.forms.DocsRevalReemForm" id="formActualizar" name="formActualizar" method="post" class="form" >
							<input type="hidden" name="metodo" id="metodo" value="actualizar"/>
							
							<div class="campoForm">
								<label class="labelForm">Identificador</label>							
							<input disabled="disabled" readonly="readonly" class="inputForm" type="text" name="id" id="id">
							</div>					
							
							<div class="campoForm">
								<label class="labelForm">Mes Informaci&oacute;n</label>
								<input onclick="javascript:cargarFecha('formActualizar','fechaPeriodo','mes_informacion')" maxlength="6" class="inputForm" type="text" name="mes_informacion"" id="mes_informacion"/>
								<label class="labelError" id="mes_informacionMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Entidad</label>
								<input maxlength="5" class="inputForm" type="text" name="codigo_entidad" id="codigo_entidad"/>
								<label class="labelError" id="codigo_entidadMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Tipo Subsidio</label>
								<input maxlength="1" class="inputForm" type="text" name="tiposubsidio" id="tiposubsidio"/>
								<label class="labelError" id="tiposubsidiotiposubsidioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Modo de Pago Original</label>
								<input maxlength="1" class="inputForm" type="text" name="mod_pago_original" id="mod_pago_original"/>
								<label class="labelError" id="mod_pago_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Banco Original</label>
								<input maxlength="3" class="inputForm" type="text" name="codigo_banco_original" id="codigo_banco_original"/>
								<label class="labelError" id="codigo_banco_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Serie Documento Original</label>
								<input maxlength="20" class="inputForm" type="text" name="serie_documento_original" id="serie_documento_original"/>
								<label class="labelError" id="serie_documento_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">N&uacute;mero Documento Original</label>
								<input maxlength="20" class="inputForm" type="text" name="num_documento_original" id="num_documento_original"/>
								<label class="labelError" id="num_documento_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha Emisi&oacute;n Documento Original</label>
								<input onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_emision_documento_original')" maxlength="10" class="inputForm" type="text" name="fecha_emision_documento_original" id="fecha_emision_documento_original"/>
								<label class="labelError" id="fecha_emision_documento_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Monto Documento Original</label>
								<input maxlength="15" class="inputForm" type="text" name="monto_documento_original" id="monto_documento_original"/>
								<label class="labelError" id="monto_documento_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Estado Documento Original</label>
								<input maxlength="1" class="inputForm" type="text" name="estado_documento_original" id="estado_documento_original"/>
								<label class="labelError" id="estado_documento_originalMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Modo Pago Nuevo</label>
								<input maxlength="1" class="inputForm" type="text" name="modo_pago_nuevo" id="modo_pago_nuevo""/>
								<label class="labelError" id="modo_pago_nuevoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">C&oacute;digo Banco Nuevo</label>
								<input maxlength="3" class="inputForm" type="text" name="codigo_banco_nuevo" id="codigo_banco_nuevo"/>
								<label class="labelError" id="codigo_banco_nuevoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Serie Documento Nuevo</label>
								<input maxlength="20" class="inputForm" type="text" name="serie_documento_nuevo" id="serie_documento_nuevo"/>
								<label class="labelError" id="serie_documento_nuevoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">N&uacute;mero Documento Nuevo</label>
								<input maxlength="20" class="inputForm" type="text" name="num_documento_nuevo" id="num_documento_nuevo"/>
								<label class="labelError" id="num_documento_nuevoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Fecha Emisi&oacute;n Documento Nuevo</label>
								<input onclick="javascript:cargarFecha('formActualizar','fechaISO','fecha_emision_documento_nuevo')" maxlength="10" class="inputForm" type="text" name="fecha_emision_documento_nuevo" id="fecha_emision_documento_nuevo"/>
								<label class="labelError" id="fecha_emision_documento_nuevoMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">Monto Documento Nuevo</label>
								<input maxlength="15" class="inputForm" type="text" name="monto_documento_nuevo" id="monto_documento_nuevo"/>
								<label class="labelError" id="monto_documento_nuevoMarca"></label>
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