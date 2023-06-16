<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<%
String msg="";
msg=(String)request.getAttribute("msg");

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
	<script src="jqSimat/menuOpen/ajustesMenu.js"></script>
	<script src="jqSimat/botones/ajustesDialog.js"></script>
	
	<script src="jqSimat/ajax/jsAjaxT9.js"></script>
	<script src="jqSimat/validacionesForm/validarut.js"></script>
	<script src="jqSimat/botones/ajustesBusquedas.js"></script>
<script>

      //Cargar búsqueda por id.		
		function cargarBuscar(){
			//se evalua formato rut.
				$("#filtroBuscar").find("#nombre_usuarioMarca").text("");
				$("#filtroBuscar").find("#nombre_usuario").css({'background-color' : 'white'});
				var valor= $("#filtroBuscar").find("#nombre_usuario").val();
				if (RutMod11(valor)){
					document["filtroBuscar"].submit();
	   			}else{
	   				$("#filtroBuscar").find("#nombre_usuario").css({'background-color' : '#fef1ec'});
	   				$("#filtroBuscar").find("#nombre_usuarioMarca").text("*");
	   			}
        }
        
   //Pop Up Formulario Insertar y Insertar.
        
        function openInsertarUser() {
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
                                   $("#formInsertar").find("#nombre_usuarioMarca").text("");
									$("#formInsertar").find("#nombre_usuario").css({'background-color' : 'white'});
									var valor= $("#formInsertar").find("#nombre_usuario").val();
									if (RutMod11(valor)){
										document["formInsertar"].submit();
	                                   	$( this ).dialog( "close" ); 
						   			}else{
						   				$("#formInsertar").find("#nombre_usuario").css({'background-color' : '#fef1ec'});
						   				$("#formInsertar").find("#nombre_usuarioMarca").text("*");
						   			}
                                   
                             },
                             Cancelar: function() {
                                   $( this ).dialog( "close" );
                             }
                        }
                  });
       });
                       
   //Pop Up Formulario Actualizar y Actualiza.
        
        function openActualizarUser(id) {
        		doAjaxPost(id);
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
                                   //Validaciones.
                                   $("#formActualizar").find("#nombre_usuarioMarca").text("");
									$("#formActualizar").find("#nombre_usuario").css({'background-color' : 'white'});
									
                                   var valor= $("#formActualizar").find("#nombre_usuario").val();
									if (RutMod11(valor)){
										
										$("#formActualizar").find("input[name='id']").attr("disabled", false);
										document["formActualizar"].submit();
	                                   	$( this ).dialog( "close" ); 
						   			}else{
						   				$("#formActualizar").find("#nombre_usuario").css({'background-color' : '#fef1ec'});
						   				$("#formActualizar").find("#nombre_usuarioMarca").text("*");
						   			}
                             },
                             Cancelar: function() {
                                   $( this ).dialog( "close" );
                             }
                        }
                  });
            });
            
            
            //Pop Up Borrar y  Borrar.
            
            function openBorrarUser(id) {
            	$("#formBorrar").find("input[name='id']").val(id);
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
                                   $("#formBorrar").find("input[name='id']").attr("disabled", false);
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
		<h2> Usuarios</h2>
		<hr>
		<fieldset class="form-fieldset">
				<h3> Busqueda </h3>
				<div id="tabsBusqueda">
					<ul>
					   	<li><a href="#tabs-1">Rut Usuario</a></li>
					</ul>
					<div id="tabs-1">
					    <form action="cargarBusquedaUsuariosById.do" type="cl.laaraucana.simat.forms.UsuariosForm" id="filtroBuscar" name="filtroBuscar" method="post" class="form" >
							<input type="hidden" name="metodo" id="metodo" value="buscarById"/>
							<div class="field">
								<input class="inputForm" type="text" name="nombre_usuario" id="nombre_usuario"/>
								<label class="labelForm" name="nombre_usuarioMarca" id="nombre_usuarioMarca"></label>
							</div>							
							<div align="left">
								<input type="button" class="boton" value="Buscar" id="botonBuscar" name="botonbuscar" onclick="javascript:cargarBuscar()"/>
							</div>
						</form>	
					</div>
				</div>
				<br>
				<input type="button" class="boton" value="Quitar Resultados Busquedas" id="botonRecargar" name="botonRecargar" onclick="javascript:goPag9()"/>							
		</fieldset>		
		<fieldset>	
			<%
				if(msg!=null ){
				out.println("<div align='center' id='msgRespuesta' name='msgRespuesta'>");									
				out.println(msg);									
				out.println("</div>");									
				}						
			%>
		</fieldset>
			<fieldset class="form-fieldset">
					<div name="anchoMantenedorTable" id="anchoMantenedorTable"></div>
		            <hr>
		            <form action="" method="post" class="form">
		                        <table border="1">
		                             <thead>
		                             <tr>
										<th>N°</th>
										<th>Rut Usuario</th>
										<th>acceso</th>
										<th>ultima coneccion</th>
										<th>Acciones</th>
		                             </tr>
		                             </thead>
		                             <tbody>
		                             	<logic:empty name="listaUsuarios">
												<h3>No existen usuarios precargados.</h3>
										</logic:empty>
		                             	<logic:iterate  name="listaUsuarios" id="listaUsuarios">
		                             		<tr>
			                             		<td><div id="spaceID" name="spaceID"><bean:write name="listaUsuarios" property="id" /></div></td>
			                             		<td><input readonly type='text' value="<bean:write name="listaUsuarios" property="nombre_usuario" />" /></td>
			                             		<td><input readonly type='text' value="<bean:write name="listaUsuarios" property="acceso" />" /></td>
			                             		<td><input readonly type='text' value="<bean:write name="listaUsuarios" property="ultima_coneccion" />" /></td>
			                             		
												<td>
												<input type="button" class="boton" value="Borrar" onclick="javascript:openBorrarUser('<bean:write name="listaUsuarios" property="id"/>')"/>
												
												<input type="button" class="boton" value="actualizar" onclick="javascript:openActualizarUser('<bean:write name="listaUsuarios" property="id"/>')"/>												
												</td> 
		                             		</tr>
		                             	</logic:iterate>
		                             </tbody>
		                        </table>
		                        <br>  
		                        <br>  
		                        
		                        <div align="right">
								<input type="button" class="boton" value="Agregar" onclick="javascript:openInsertarUser()"/>		            			 
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
					<form action="insertarUsuarios.do" type="cl.laaraucana.simat.forms.UsuariosForm" id="formInsertar" name="formInsertar" method="post" class="form" >
						<input type="hidden" name="metodo" id="metodo" value="insertar"/>
							<div class="campoForm">
								<label class="labelForm">Rut Usuario</label>
								<input class="inputForm" maxlength="10" type="text" name="nombre_usuario" id="nombre_usuario"/>
								<label class="labelError" name="nombre_usuarioMarca" id="nombre_usuarioMarca"></label>
							</div>
							<div class="campoForm">
								<label class="labelForm">acceso</label>
								<input readonly class="inputForm" maxlength="10" type="text" name="acceso" id="acceso" value="total"/>
							</div>
						</form> 
					</fieldset>
					</div>
								
			</div>
			<div id="borrar-dialog" title="Confirmaci&oacute;n Borrar">
				<div id="simulacion2">
					<fieldset class="form-fieldset">
						<form action="eliminarUsuarios.do" type="cl.laaraucana.simat.forms.UsuariosForm" id="formBorrar" name="formBorrar" method="post" class="form">
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
						<form action="actualizarUsuarios.do" type="cl.laaraucana.simat.forms.UsuariosForm" id="formActualizar" name="formActualizar" method="post" class="form">
							<input type="hidden" name="metodo" id="metodo" value="actualizar"/>					
							<div class="campoForm">
								<label class="labelForm">Identificador</label>							
								<input disabled="disabled" readonly="readonly" class="inputForm" type="text" name="id" id="id">
							</div>
							<div class="campoForm">
								<label class="labelForm">Rut Usuario</label>
								<input class="inputForm" maxlength="10" type="text" name="nombre_usuario" id="nombre_usuario"/>
								<label class="labelError" name="nombre_usuarioMarca" id="nombre_usuarioMarca"></label>
							</div>
							<div class=campoForm>
								<label class="labelForm">acceso</label>
								<input readonly class="inputForm" maxlength="10" type="text" name="acceso" id="acceso" value="total"/>
							</div>
						</form> 
					</fieldset>
				</div>	
			</div>
			
			
	</div>
</div>				
</body>
</html>