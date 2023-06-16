<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

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
	<script src="jqSimat/menuOpen/ajustesMenu.js"></script>
	
	<script src="jqSimat/validacionesForm/validacionHomologacion.js"></script>	
	<script src="jqSimat/ajax/jsAjaxT11.js"></script>	
	<script src="jqSimat/botones/ajustesBusquedas.js"></script>
	
<script>

        //Cargar búsqueda por mes.
		
		/*
		function cargarBuscar(){
				
				document["filtroBuscar"].submit();
        }
        */        
        //Pop Up Formulario Insertar y Insertar.
        
        function openInsertar() {
                $("#insertar-dialog").dialog("open");
            }
            $(document).ready(function(){
                  $("#insertar-dialog").dialog({
                        resizable: false,
                        width: 480,
                        modal: true,
                        autoOpen:false,
                        buttons: {
                             "Ok": function() {
                                   //Validaciones.
                                   document["formInsertar"].submit();
                                   $( this ).dialog( "close" ); 
                             },
                             Cancelar: function() {
                                   $( this ).dialog( "close" );
                             }
                        }
                  });
            });
            
            
        //Pop Up Formulario Actualizar y Actualiza.
        
       function openActualizar(id_registro) {
       			doAjaxPost(id_registro); 
                $("#actualizar-dialog").dialog("open");
                               
            }
            $(document).ready(function(){
                  $("#actualizar-dialog").dialog({
                        resizable: false,
                        width: 480,
                        modal: true,
                        autoOpen:false,
                        buttons: {
                             "Ok": function() {
                             		$("#formActualizar").find("input[name='id_registro']").attr("disabled", false);
                                   document["formActualizar"].submit();
                                   $( this ).dialog( "close" );
                             },
                             Cancelar: function() {
                                   $( this ).dialog( "close" );
                             }
                        }
                  });
            });
            
            
             //Pop Up Borrar y  Borrar.
            
            function openBorrar(id_registro) {
            	$("#formBorrar").find("input[name='id_registro']").val(id_registro);
                $("#borrar-dialog").dialog("open");
            }
            $(document).ready(function(){
                  $("#borrar-dialog").dialog({
                        resizable: false,
                        width: 480,
                        modal: true,
                        autoOpen:false,
                        buttons: {
                             "Ok": function() {
                                   //Validaciones.
                                   $("#formBorrar").find("input[name='id_registro']").attr("disabled", false);
                                   document["formBorrar"].submit();
                                   $( this ).dialog( "close" );
                             },
                             Cancelar: function() {
                                   $( this ).dialog( "close" );
                             }
                        }
                  });
            });
            
      </script>

</head>

<body>
<div id="wrapper" name="wrapper">	
	<div id="header" name="header">
		<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg" width="900" height="100">
	</div>
	<div id="workSpace" name="workSpace">
		
		<div id="simulacion">
		<h2> C&oacute;digos de Homologaci&oacute;n</h2>
		<hr>
			<fieldset class="form-fieldset">
				<h3> Busqueda </h3>
				<div id="tabsBusqueda">
					<ul>
					   	<li><a href="#tabs-1">Clasificaci&oacute;n</a></li>
					</ul>					
					<div id="tabs-1">
					    <form action="buscarByClasificacion.do" type="cl.laaraucana.simat.forms.TablaHomologacionForm" name="filtroBuscar" id="filtroBuscar" method="post" class="form" >
							<input type="hidden" name="metodo" id="metodo" value="buscarByClasificacion"/>
							<div class="field">
								<input type="text" name="clasificacion" id="clasificacion"/>
								<label class="labelForm" id="clasificacionMarca"></label>
							</div>							
							<div align="left">
								<input type="button" class="boton" value="Buscar" id="botonBuscar" name="botonbuscar" onclick="javascript:BuscarClasificacion()"/>
							</div>
						</form>	
					</div>
				</div>
				<br>
				<input type="button" class="boton" value="Quitar Resultados Busquedas" id="botonRecargar" name="botonRecargar" onclick="javascript:goPag11()"/>							
			</fieldset>		
			
			<fieldset class="form-fieldset">
					<div name="anchoMantenedorTable" id="anchoMantenedorTable"></div>	
		            <hr>
		            <form action="" method="post" class="form">
                        <table border="1">
                             <thead>
                             <tr>
                             	<th>Clasificaci&oacute;n</th>
								<th>C&oacute;digo La Araucana</th>
								<th>C&oacute;digo Suceso</th>
								<th>Descripci&oacute;n</th>
								<th>Acciones</th>
                             </tr>
                             </thead>
                             <tbody>
                             	<logic:iterate  name="listaHomologacion" id="listaHomologacion">
	                             		<tr>
	                             			<td><bean:write name="listaHomologacion" property="clasificacion" /></td>
		                             		<td><input readonly type=text value="<bean:write name="listaHomologacion" property="codigo_la" />" /></td>
											<td><input readonly type=text value="<bean:write name="listaHomologacion" property="codigo_suceso" />" /></td> 
											<td><input readonly type=text value="<bean:write name="listaHomologacion" property="descripcion" />" /></td>
											<td>
												<input type="button" class="boton" value="Borrar" onclick="javascript:openBorrar('<bean:write name="listaHomologacion" property="id_registro"/>')"/> 
											
												<input type="button" class="boton" value="actualizar" onclick="javascript:openActualizar('<bean:write name="listaHomologacion" property="id_registro"/>')"/>
											</td>
	                             		</tr>
	                             	</logic:iterate>
                             </tbody>
                        </table>
                        <br>  
                        <br> 	                        
                        <div align="right">
                        
            				<input type="button" class="boton" value="Agregar" onclick="javascript:openInsertar()">
            						
      					</div>
                        <br>  
                  </form>
		</fieldset>
		<fieldset class="form-fieldset">
			<form action="mostrarMenu.do" name="formVolver" id="formVolver" method="post">
				<input type="hidden" name="metodo" id="metodo" value="mostrarMenu"/>
			    <input class="boton" type="button" value='<< Volver a Menu' onclick="javascript:volverMenu()"/>
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
					<form action="insertarHomologacion.do" type="cl.laaraucana.simat.forms.TablaHomologacionForm" name ="formInsertar" id="formInsertar" method="post" class="form" >
						<input type="hidden" name="metodo" id="metodo" value="insertar"/>
					
						<div class="campoForm">
							<label class="labelForm">Clasificaci&oacute;n</label>
							<input maxlength="5" class="inputForm" type="text" name="clasificacion" id="clasificacion"/>
						</div>
						<div class="campoForm">
							<label class="labelForm">Descripci&oacute;n</label>
							<textarea onKeyUp="return maximaLongitud(this,100)" rows="4" cols="65" name="descripcion" id="descripcion"></textarea> 
						</div>
						<div class="campoForm">
							<label class="labelForm">C&oacute;digo Suceso</label><input maxlength="10" class="inputForm" type="text" name="codigo_suceso" id="codigo_suceso"/>
						</div>
						<div class="campoForm">
							<label class="labelForm">C&oacute;digo La Araucana</label><input maxlength="10" class="inputForm" type="text" name="codigo_la" id="codigo_la"/>
						</div>
						
						<br>
					</form> 
				</fieldset>
			</div>				
		</div>
			
		<div id="borrar-dialog" title="Confirmaci&oacute;n Borrar">
			<div id="simulacion2">
				<fieldset class="form-fieldset">
					<form action="eliminarHomologacion.do" name="formBorrar" id="formBorrar" method="post" type="cl.laaraucana.simat.forms.TablaHomologacionForm" class="form" >
					<input type="hidden" name="metodo" id="metodo" value="eliminar"/>
						<table>
							<tr>
								<td>
									<div class="field">
										
										<label class="labelForm">Identificador</label>							
										<input disabled="disabled" readonly="readonly" class="inputForm" type="text" name="id_registro" id="id_registro">
										
									</div>
								</td>
							</tr>
						</table>
					</form>
				</fieldset>
			</div>	
		</div>
			
		<div id="actualizar-dialog" title="Actualizar N&oacute;mina">
			<div id="simulacion2">
				<fieldset class="form-fieldset">
					<form action="actualizarHomologacion.do" type="cl.laaraucana.simat.forms.TablaHomologacionForm" name="formActualizar" 
						id="formActualizar" method="post" class="form" >
					<input type="hidden" name="metodo" id="metodo" value="actualizar"/>
						<div class="campoForm">
							<label class="labelForm">Identificador</label>
							<input disabled="disabled" readonly="readonly" class="inputForm" type="text" name="id_registro" id="id_registro">
						</div>						
						<div class="campoForm">
							<label class="labelForm">Clasificaci&oacute;n</label>
							<input maxlength="5" class="inputForm" type="text" name="clasificacion" id="clasificacion"/>
						</div>							
						<div class="campoForm">
							<label class="labelForm">C&oacute;digo Suceso</label>
							<input maxlength="10" class="inputForm" type="text" name="codigo_suceso" id="codigo_suceso"/>
						</div>
						<div class="campoForm">
							<label class="labelForm">C&oacute;digo La Araucana</label>
							<input maxlength="10" class="inputForm" type="text" name="codigo_la" id="codigo_la"/>
						</div>
						
						<div class="campoForm">
							<label class="labelForm">Descripci&oacute;n</label>
							<textarea onKeyUp="return maximaLongitud(this,100)" rows="4" cols="65" name="descripcion" id="descripcion"></textarea> 
						</div>
													
						<br>
					</form> 
				</fieldset>
			</div>	
		</div>
	</div>
</div>	
</body>
</html>