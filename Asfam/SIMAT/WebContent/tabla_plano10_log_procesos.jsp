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
<title>Log. Procesos</title>
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
	
	<script src="jqSimat/ajax/jsAjaxT10.js"></script>
	<script src="jqSimat/ajax/jsAjaxT10Detalle.js"></script>
	
<script>
            //Cargar búsqueda por mes.
		function cargarBuscarLogPorTabla(){
				var temp = $("#opcionTabla option:selected").val();
				
				if(temp==1){
					$("#filtroBuscar").find("input[name='tabla']").val('SMF01');
				}				
				if(temp==2){
					$("#filtroBuscar").find("input[name='tabla']").val('SMF02');			
				}
				if(temp==3){
					$("#filtroBuscar").find("input[name='tabla']").val('SMF03');
				}
				if(temp==5){
					$("#filtroBuscar").find("input[name='tabla']").val('SMF05');
				}
				if(temp==6){
					$("#filtroBuscar").find("input[name='tabla']").val('SMF06');
				}
				if(temp==7){
					$("#filtroBuscar").find("input[name='tabla']").val('SMF07');
				}
				if(temp==8){
					$("#filtroBuscar").find("input[name='tabla']").val('SMF08');
				}
				document["filtroBuscar"].submit();
		}
        
           function openEditarTabla(id,tabla) {
             //alert("registro_id: "+id);
            	$("#formEditarTabla").find("input[name='registro_afectado']").val(id);
            	$("#formEditarTabla").find("input[name='tabla_afectada']").val(tabla);
                $("#editar-demo").dialog("open");
            }
            $(document).ready(function(){
                  $("#editar-demo").dialog({
                        resizable: false,
                        width: 480,
                        modal: true,
                        autoOpen:false,
                        buttons: {
                             "Ok": function() {
                                   //Validaciones.
                                   document["formEditarTabla"].submit();
                                   $( this ).dialog( "close" );
                             },
                             Cancelar: function() {
                                   $( this ).dialog( "close" );
                             }
                        }
                  });
            });
            
        //Pop Up para ver el registro del log(con todos sus campos)
        
        function openVerDetalle(id) {
        	doAjaxPost(id);
                $("#detalle-demo").dialog("open");
            }
            $(document).ready(function(){
                  $("#detalle-demo").dialog({
                        resizable: false,
                        width: 480,
                        modal: true,
                        autoOpen:false,
                        buttons: {
                             "Ir a Editar": function() {
		                            var t=$("#formDetalle").find("input[name='tabla']").val();
		            				var i=$("#formDetalle").find("input[name='registro_id']").val();
                                   openEditarTabla(i,t);                          
                                   $( this ).dialog( "close" ); 
                             },
                             Cancelar: function() {
                                   $( this ).dialog( "close" );
                             }
                        }
                  });
            });
          /*
        function avanzar(key){
            $("#botonAvanzar").click(function(){				
				$("#formAvance").find("input[name='keyAvance']").val(key);
				document["formAvance"].submit();
            });            
         }
         
         function retroceder(key){
            $("#botonRetroceder").click(function(){
				$("#formAvance").find("input[name='keyAvance']").val(key);
				document["formAvance"].submit();
            });            
         }
            */
      </script>

</head>

<body>
<div id="wrapper" name="wrapper">	
	<div id="header" name="header">
		<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg" width="900" height="100">
	</div>
	<div id="workSpace" name="workSpace">
		<div id="simulacion">
		<h2>Log de Procesos</h2>
		<hr>
		<!-- 
			<fieldset class="form-fieldset">
				<form action="BuscarTablaLog.do" type="cl.laaraucana.simat.forms.RegistroAfectadoForm" id="filtroBuscar" name="filtroBuscar" method="post" 
					class="form" >
					<input type="hidden" name="metodo" id="metodo" value="buscarPorTabla"/>
					<input type="hidden" name="tabla" id="tabla"/>
					<div class="field">
						<label class="label">Identificador de tabla</label>
						<select id="opcionTabla" name="opcionTabla">
						  <option value="0">Seleccione</option>
						  <option value="1">Reintegros</option>
						  <option value="2">SubsPrepostNM</option>
						  <option value="3">SubsParental</option>
						  <option value="5">ControlDocu</option>
						  <option value="6">DocsRevalReem</option>
						  <option value="7">DatosLicCob</option>
						  <option value="8">DatosLicResol</option>
						</select>
					</div>
							
					<div align="center">
						<input type="button" class="boton" onclick="javascript:cargarBuscarLogPorTabla()" name="botonBuscar"
							id="botonBuscar" value="Buscar" />
					</div>
				</form>
				<br>
			</fieldset>
			 -->
			<div id="workSpacePaginacion" name="workSpacePaginacion">
			<fieldset class="form-fieldset">				
				<form action="PaginarSMLPR.do" method="Post" class="form" name="formAvance" id="formAvance">
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
		                                   	<th>Registro Identificador</th>
		                                   	<th>Descripcion</th>
		                                   	<th>Tabla Referenciada</th>
		                                  	<th>Acciones</th>
		                               
		                             </tr>
		                             </thead>
		                             <tbody>
			                             <logic:iterate  name="listaProcesos" id="listaProcesos">
			                             		<tr>
			                             			<td><div id="spaceID" name="spaceID"><bean:write name="listaProcesos" property="id_registro" /></div></td>
				                             		<td>
				                             			<div>
				                             				<bean:write name="listaProcesos" property="registro_id" />
														</div>
				                             		</td>
				                             		
													<!--<td><input readonly type=text value="<bean:write name="listaProcesos" property="descripcion" />" /></td>-->
													<td>
														<div>
															<bean:write name="listaProcesos" property="descripcion" /> 
														</div>
													</td>
													<td>
														<div>
															<bean:write name="listaProcesos" property="tabla" />
														</div>
													</td>
													
													
													<td>
													<input type="button" class="boton" value="detalle" onclick="javascript:openVerDetalle('<bean:write name="listaProcesos" property="id_registro"/>')"/>
													
													<input type="button" class="boton" value="ir a editar" onclick="javascript:openEditarTabla('<bean:write name="listaProcesos" property="registro_id"/>','<bean:write name="listaProcesos" property="tabla"/>')"/>
													
													</td> 
					                    		</tr>
			                             </logic:iterate>
		                             </tbody>
		                        </table>
		                        <br>  
		                        <br>
		                        <div align="right">
		<!--
		            			<a href="#" onclick="javascript:openPopUpInsertar()"><input type="button" class="boton" value="Agregar" />
		-->
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
					
			<div id="detalle-demo" title="detalle log Proceso">
				<div id="simulacion2">
					<fieldset class="form-fieldset">
						<form action="actualizarLogProcesos.do" type="cl.laaraucana.simat.forms.LogProcesosForm"  id="formDetalle" name="formDetalle" method="post" class="form" >
						<input type="hidden" name="metodo" id="metodo" value="actualizar"/>
							<div id="campoForm">
								<label class="labelForm">Identificador Registro</label>
								<input class="inputForm" readonly type="text" name="id_registro" id="id_registro"/>
							</div>
							<div id="campoForm">
								<label class="labelForm">Tipo Log</label>
								<input class="inputForm"  type="text" readonly name="tipo_log" id="tipo_log"/>
							</div>
							<div id="campoForm">
								<label class="labelForm">Fecha y hora del Log</label>
								<input class="inputForm"  readonly type="text" name="fecha_hora" id="fecha_hora"/>
							</div>
							<div id="campoForm">
								<label class="labelForm">Per&iacute;odo</label>
								<input class="inputForm"  readonly type="text" name="periodo" id="periodo"/>
							</div>
							<div id="campoForm">
								<label class="labelForm">Identificador del Usuario</label>
								<input class="inputForm"  readonly type="text" name="id_usuario" id="id_usuario"/>
							</div>
							<div id="campoForm">
								<label class="labelForm">Proceso Afectado</label>
								<input class="inputForm"  readonly type="text" name="proceso_afectado" id="proceso_afectado"/>
							</div>
							<div id="campoForm">
								<label class="labelForm">Tabla</label>
								<input class="inputForm"  readonly type="text" name="tabla" id="tabla"/>
							</div>
							<div id="campoForm">
								<label class="labelForm">Registro Identificador</label>
								<input class="inputForm" class="inputForm" readonly type="text" name="registro_id" id="registro_id"/>
							</div>
							<div id="campoForm">
								<label class="labelForm">Descripcion</label>
								<textarea readonly rows="4" cols="55" name="descripcion" id="descripcion"></textarea> 
							</div>
						</form> 
					</fieldset>
				</div>	
			</div>
			
			<div id="editar-demo" title="Confirmaci&oacute;n para acceder a mantenedor">
				<div id="simulacion2">
					<fieldset class="form-fieldset">
						<form action="editarTablaAfectada.do" type="cl.laaraucana.simat.forms.RegistroAfectadoForm"  id="formEditarTabla" name="formEditarTabla" method="post" class="form" >
						<input type="hidden" name="metodo" id="metodo" value="editarTabla"/>
							<div class="campoForm">
								<label class="labelForm">tabla Afectada</label>
								<input class="inputForm"  readonly type="text" name="tabla_afectada" id="tabla_afectada"/>
							</div>
							<div class="campoForm">
								<label class="labelForm">Registro afectado</label>
								<input class="inputForm" readonly type="text" name="registro_afectado" id="registro_afectado"/>
							</div>
						</form>
					</fieldset>
				</div>	
			</div>
			
		</div>
</div>			
</body>
</html>