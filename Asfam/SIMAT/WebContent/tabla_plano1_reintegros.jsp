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
	
	<script src="jqSimat/jqBlueUI/js/jquery-1.8.3.js"></script>
	<link href="jqSimat/jqBlueUI/css/redmond/jquery-ui-1.9.2.custom.css" rel="stylesheet">
	<script src="jqSimat/jqBlueUI/js/jquery-ui-1.9.2.custom.js"></script>
	
	<script src="jqSimat/calendar/ajustesCalendario.js"></script>
	<script src="jqSimat/botones/ajustesBotones.js"></script>
	<script src="jqSimat/botones/ajustesDialog.js"></script>

	<script src="jqSimat/ajax/jsAjaxT1.js"></script>
	<script src="jqSimat/validacionesForm/validarut.js"></script>
	<script src="jqSimat/validacionesForm/val_plano1_reintegros.js"></script>	
	<script src="jqSimat/botones/ajustesBusquedas.js"></script>
	
</head>
<body>
<div id="wrapper" name="wrapper">	
	<div id="header" name="header">
		<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg" width="900" height="100">
	</div>
	<div id="workSpace" name="workSpace">
	
		<div id="simulacion">
			<h2> Reintegros de subsidios y cotizaciones previsionales </h2>
			<hr>
			<fieldset class="form-fieldset">
				<h3> Busquedas </h3>
				<div id="tabsBusqueda">
					<ul>
					   	<li><a href="#tabs-1">Rut beneficiario</a></li>
					</ul>
					<div id="tabs-1">
					    <form action="buscarReintegrosByRutBenef.do" id="filtroRutBenef" name="filtroRutBenef" method="post">
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
				<br>
				<input type="button" class="boton" value="Quitar Resultados Busquedas" id="botonRecargar" name="botonRecargar" onclick="javascript:goPage1()"/>							
			</fieldset>
			
			<div id="workSpacePaginacion" name="workSpacePaginacion">
				<fieldset class="form-fieldset">				
					<form action="PaginarT1.do" method="Post" class="form" name="formAvance" id="formAvance">
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
	                                <th>Run Beneficiario</th>
	                                <th>Nombre beneficiario</th>
	                                
	                                <th>Acciones</th>
	                            </tr>
	                         </thead>	                         
							 	<tbody>
							 		<logic:empty name="listaReintegros">
										<h3>No existen datos para el periodo seleccionado.</h3>
									</logic:empty>
	                             	<logic:iterate name="listaReintegros" id="listaReintegros">
	                             		<tr>
											<td><div id="spaceID" name="spaceID"><bean:write name="listaReintegros" property="id" /></div></td>	                             		
		                             		<td><input type=text value="<bean:write name="listaReintegros" property="mes_informacion" />" /></td>
											<td><input type=text value="<bean:write name="listaReintegros" property="run_beneficiario" />" /></td> 
											<td><input type=text value="<bean:write name="listaReintegros" property="nombre_beneficiario" />" /></td>
											<td>
												<input type="button" class="boton" value="Borrar" onclick="javascript:openBorrar('<bean:write name="listaReintegros" property="id"/>')"/>												
												<input type="button" class="boton" value="actualizar" onclick="javascript:openActualizar(1,'<bean:write name="listaReintegros" property="id"/>')"/>
											</td> 
	                             		</tr>
	                             	</logic:iterate>                            
								</tbody>
	                        </table>
	                        <br>  
	                        <br>  
	                        
	                        <div align="right">
	
	            			<input type="button" class="boton" value="Agregar" onclick="javascript:openInsertar(1)" />
	
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
				<form action="insertarReintegros.do" type="cl.laaraucana.simat.forms.ReintegrosForm" name ="formInsertar" id="formInsertar" method="post" class="form">
				
				    <input type="hidden" name="metodo" id="metodo" value="insertar"/>
	
					<div class="campoForm">					
						<label class="labelForm">Mes De Informaci&oacute;n</label>
						<input onclick="javascript:cargarFecha('formInsertar','fechaPeriodo','mes_informacion')" maxlength="6" class="inputForm" type="text" name="mes_informacion" id="mes_informacion">
						<label class="labelError" id="mes_informacionMarca"></label>						
					</div>
					<div class="campoForm">
						<label class="labelForm">C&oacute;digo Entidad</label>
						<input maxlength="5" class="inputForm" TYPE="text" NAME="codigo_entidad" id="codigo_entidad">
						<label class="labelError" id="codigo_entidadMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Mes N&oacute;mina</label>
						<input onclick="javascript:cargarFecha('formInsertar','fechaPeriodo','mes_nomina')" maxlength="6" class="inputForm" TYPE="text" NAME="mes_nomina" id="mes_nomina">
						<label class="labelError" id="mes_nominaMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Run Beneficiario</label>
						<input maxlength="11" class="inputForm" TYPE="text" NAME="run_beneficiario" id="run_beneficiario">
						<label class="labelError" id="run_beneficiarioMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Nombre del Beneficiario</label>
						<input maxlength="80" class="inputForm" TYPE="text" NAME="nombre_beneficiario" id="nombre_beneficiario">
						<label class="labelError" id="nombre_beneficiarioMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Tipo subsidio</label>
						<input maxlength="1" class="inputForm" TYPE="text" NAME="tipo_subsidio" id="tipo_subsidio">
						<label class="labelError" id="tipo_subsidioMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">N&uacute;mero Licencia</label>
						<input maxlength="14" class="inputForm" TYPE="text" NAME="nro_licencia" id="nro_licencia">
						<label class="labelError" id="nro_licenciaMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Rut del Empleador</label>
						<input maxlength="11" class="inputForm" TYPE="text" NAME="rut_empleador" id="rut_empleador">
						<label class="labelError" id="rut_empleadorMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Nombre Empleador</label>
						<input maxlength="80" class="inputForm" TYPE="text" NAME="nombre_empleador"id="nombre_empleador">
						<label class="labelError" id="nombre_empleadorMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Origen del Reintegro</label>
						<input maxlength="1" class="inputForm" TYPE="text" NAME="origen_reintegro" id="origen_reintegro">
						<label class="labelError" id="origen_reintegroMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Monto Total a Reintegrar</label>
						<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_total_a_reintegrar" id="monto_total_a_reintegrar">
						<label class="labelError" id="monto_total_a_reintegrarMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Monto Recuperado</label>
						<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_recuperado" id="monto_recuperado">
						<label class="labelError" id="monto_recuperadoMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Monto Recuperado Acumulado</label>
						<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_recuperado_acum" id="monto_recuperado_acum">
						<label class="labelError" id="monto_recuperado_acumMarca"></label>
					</div> 
					<div class="campoForm">
						<label class="labelForm">Total saldo a Reintegrar</label>
						<input maxlength="15" class="inputForm" TYPE="text" NAME="total_saldo_a_reintegrar" id="total_saldo_a_reintegrar">
						<label class="labelError" id="total_saldo_a_reintegrarMarca"></label>
					</div>
			</form>
				</fieldset>
			</div>	
		</div>
		
		<div id="borrar-dialog" title="Confirmaci&oacute;n Borrar">
			<div id="simulacion2">
				<fieldset class="form-fieldset">
					<form action="eliminarReintegros.do" name ="formBorrar" id="formBorrar" method="post" class="form">
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
					<form action="actualizarReintegros.do" type="cl.laaraucana.simat.forms.ReintegrosForm" name ="formActualizar" id="formActualizar" method="post" class="form">
				    <input type="hidden" name="metodo" id="metodo" value="actualizar"/>
				    					
					<div class="campoForm">
						<div class="campoForm">
							<label class="labelForm">Identificador</label>							
							<input disabled="disabled" readonly="readonly" class="inputForm" type="text" name="id" id="id">
						</div>
					</div>
										
					<div class="campoForm">					
						<label class="labelForm">Mes De Informaci&oacute;n</label>
						<input onclick="javascript:cargarFecha('formActualizar','fechaPeriodo','mes_informacion')" maxlength="6" class="inputForm" type="text" name="mes_informacion" id="mes_informacion">
						<label class="labelError" id="mes_informacionMarca"></label>						
					</div>
					<div class="campoForm">
						<label class="labelForm">C&oacute;digo Entidad</label>
						<input maxlength="5" class="inputForm" TYPE="text" NAME="codigo_entidad" id="codigo_entidad">
						<label class="labelError" id="codigo_entidadMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Mes N&oacute;mina</label>
						<input onclick="javascript:cargarFecha('formActualizar','fechaPeriodo','mes_nomina')" maxlength="6" class="inputForm" TYPE="text" NAME="mes_nomina" id="mes_nomina">
						<label class="labelError" id="mes_nominaMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Run Beneficiario</label>
						<input maxlength="11" class="inputForm" TYPE="text" NAME="run_beneficiario" id="run_beneficiario">
						<label class="labelError" id="run_beneficiarioMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Nombre del Beneficiario</label>
						<input maxlength="80" class="inputForm" TYPE="text" NAME="nombre_beneficiario" id="nombre_beneficiario">
						<label class="labelError" id="nombre_beneficiarioMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Tipo subsidio</label>
						<input maxlength="1" class="inputForm" TYPE="text" NAME="tipo_subsidio" id="tipo_subsidio">
						<label class="labelError" id="tipo_subsidioMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">N&uacute;mero Licencia</label>
						<input maxlength="14" class="inputForm" TYPE="text" NAME="nro_licencia" id="nro_licencia">
						<label class="labelError" id="nro_licenciaMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Rut del Empleador</label>
						<input maxlength="11" class="inputForm" TYPE="text" NAME="rut_empleador" id="rut_empleador">
						<label class="labelError" id="rut_empleadorMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Nombre Empleador</label>
						<input maxlength="80" class="inputForm" TYPE="text" NAME="nombre_empleador"id="nombre_empleador">
						<label class="labelError" id="nombre_empleadorMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Origen del Reintegro</label>
						<input maxlength="1" class="inputForm" TYPE="text" NAME="origen_reintegro" id="origen_reintegro">
						<label class="labelError" id="origen_reintegroMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Monto Total a Reintegrar</label>
						<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_total_a_reintegrar" id="monto_total_a_reintegrar">
						<label class="labelError" id="monto_total_a_reintegrarMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Monto Recuperado</label>
						<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_recuperado" id="monto_recuperado">
						<label class="labelError" id="monto_recuperadoMarca"></label>
					</div>
					<div class="campoForm">
						<label class="labelForm">Monto Recuperado Acumulado</label>
						<input maxlength="15" class="inputForm" TYPE="text" NAME="monto_recuperado_acum" id="monto_recuperado_acum">
						<label class="labelError" id="monto_recuperado_acumMarca"></label>
					</div> 
					<div class="campoForm">
						<label class="labelForm">Total saldo a Reintegrar</label>
						<input maxlength="15" class="inputForm" TYPE="text" NAME="total_saldo_a_reintegrar" id="total_saldo_a_reintegrar">
						<label class="labelError" id="total_saldo_a_reintegrarMarca"></label>
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