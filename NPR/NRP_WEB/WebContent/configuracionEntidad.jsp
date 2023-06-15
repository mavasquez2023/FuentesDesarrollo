<%@include file="header_session.jsp" %>
<%@page import="java.lang.Math"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="Author" content="J-Factory Solutions" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>Gestor de Nóminas - La Araucana</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="bootstrap-3.3.7-dist/js/jquery.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/comun.js?<%=Math.random()%>"></script>
<script src="js/validaciones.js?<%=Math.random()%>"></script>

<style>

#content_liv{
width:1000px;
height:600px;
background-color:#FFF;
}

.menu{
width:180px;
height:600px;
background-color:#FFF;
padding-left: 5px;

float:left;
color:#005D8B;
}

#main_content{
float:left;
margin-left:20px;
}

.descripcion_etapa{
margin-left:10px;
}

.titulo_menu{
color:#005D8B;
font-weight:bold;
height:30px;
}

.titulo_proceso{
width:100%;
color:#005D8B;
}

.hide{
display:none;
}

.cursor{
cursor:hand;
cursor:pointer;
}

.loading{
width:400px;
text-align:center;
color:#005D8B;
}
.loading img{
padding-top:100px;
padding-bottom:20px;
width:50px;
}
.clickeable{

cursor:hand;
cursor:pointer;
}

.header_tabla{
background-color:#005D8B;
font-weight:none;
color:#FFF;
}

.tabla_con_header{
border: 1px solid #005D8B;

}



</style>

</head>
<body class="corp" id="body">


<div id="content" class="container_12">
	<div class="grid_12">
		<img src="images/cabecera_claves.jpg" />
		<%@include file="menu.jsp" %>	
		<div id="content_liv">		
			<div id="main_content" > 
				<div id="configurador">
					<h2>Configuraci&oacute;n de Nóminas</h2>
				

					<div id="div_busqueda">
						<font>Buscar Configuraci&oacute;n Empresa o Holding</font><br/><br/>
						<div class="form-group">
						  <input type="texto" class="form-control" id="codigo_entidad" placeholder="Ingrese Rut Empresa o Código Holding" value="" onkeyPress="validaSiEsHolding()" onChange="validaSiEsHolding()">

							<button type="button" class="btn btn-primary btn-sm" style="margin-top:10px" id="btn_seleccionar_entidad">
								<span class="glyphicon glyphicon-search"></span> Seleccionar Entidad
							</button>
							
							<button type="button" class="btn btn-info btn-sm" style="margin-top:10px;display:none" id="btn_ver_empresas_asociadas">
								<span class="glyphicon glyphicon-search"></span> Ver Empresas Asociadas
							</button>
							<button type="button" class="btn btn-success btn-sm" style="margin-top:10px" id="btn_buscar_info">
								<span class="glyphicon glyphicon-search"></span> Buscar Resultados
							</button>
						</div>

						<!-- Formatos -->
						<div class="container" style="width:800px">
						  <h4>Formatos Encontrados</h4>         
						  <table class="table table-bordered tabla_con_header">
							<thead  class="header_tabla">
							  <tr>
								<th width="150px">C&oacute;digo Entidad</th>
								<th width="150px">Nombre Formato</th>
								<th width="500px">Descripci&oacute;n</th>
								<th width="100px">Opciones</th>
							  </tr>
							</thead>
							<tbody id="body_formatos">
							  
							  <tr class="sin_resultados">
								<td colspan="3" style="padding-left:20px;">Sin Resultados</td>
							  </tr>
							</tbody>
						  </table>
							<button type="button" class="btn btn-primary btn-sm" style="margin-top:10px;display;none" id="btn_copy_formato">
								<span class="glyphicon glyphicon-duplicate"></span> Copiar Formato de N&oacute;mina
							</button>
							<button type="button" class="btn btn-success btn-sm" style="margin-top:10px;display;none" id="btn_add_formato">
								<span class="glyphicon glyphicon-plus"></span> Crear Formato N&oacute;mina
							</button>
						</div>

						<br/>


						<!-- N&oacute;minas -->
						<div class="container" style="width:800px">
						  <h4>N&oacute;minas Encontradas</h4>         
						  <table class="table table-bordered">
							<thead  class="header_tabla">
							  <tr>
								<th width="100px">C&oacute;digo Entidad</th>
								<th width="100px">Tipo</th>
								<th width="100px">Data Adicional</th>
								<th width="100px">Formato Salida</th>
								<th width="100px">Nombre Salida</th>
								<th width="100px">Formato N&oacute;mina</th>
								<th width="100px">Opciones</th>
							  </tr>
							</thead>
							<tbody id="body_nominas">
							  
							  <tr class="sin_resultados">
								<td colspan="3" style="padding-left:20px;">Sin Resultados</td>
							  </tr>
							</tbody>
						  </table>
							<button type="button" class="btn btn-info btn-sm" style="margin-top:10px;display;none" id="btn_generar_nomina">
								<span class="glyphicon glyphicon-play"></span> Generar N&oacute;mina
							</button>
						
							<button type="button" class="btn btn-primary btn-sm" style="margin-top:10px;display;none" id="btn_ver_nominas_periodo">
								<span class="glyphicon glyphicon-list-alt"></span> Ver N&oacute;minas del Per&iacute;odo
							</button>
							<button type="button" class="btn btn-success btn-sm" style="margin-top:10px;display;none" id="btn_add_nomina">
								<span class="glyphicon glyphicon-plus"></span> Crear N&oacute;mina
							</button>
						</div>


						<br/>
						<br/>

						<!-- Envios -->
						<div class="container" style="width:800px">
						  <h4>Env&iacute;os Encontrados</h4>         
						  <table class="table table-bordered">
							<thead  class="header_tabla">
							  <tr>
								<th width="100px">C&oacute;digo Entidad</th>
								<th width="100px">Tipo Env&iacute;o</th>
								<th width="100px">Data Adicional</th>
								<th width="100px">Opciones</th>
							  </tr>
							</thead>
							<tbody id="body_envios">
							  
							  <tr class="sin_resultados">
								<td colspan="3" style="padding-left:20px;">Sin Resultados</td>
							  </tr>
							</tbody>
						  </table>

							
							<button type="button" class="btn btn-success btn-sm" style="margin-top:10px;display;none" id="btn_add_envio">
								<span class="glyphicon glyphicon-plus"></span> Configurar Env&iacute;o
							</button>
							<br/><br/>
						</div>



					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
  <div class="modal fade" id="modalAgregarFormato" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title">Agregar Formato Especial</h4>
        </div>
        <div class="modal-body">
          
			<div class="form-group">
				<label for="col_formato_sufijo">Sufijo</label>
				<input type="text" class="form-control" id="col_formato_sufijo" placeholder="Ingrese Sufijo" value="" onchange="agregarSufijo();" onKeyPress="return validateString(event)">
			</div>

			<div class="form-group">
				<label for="col_formato_nombre">Nombre Configuraci&oacute;n</label>
				<input type="text" class="form-control" id="col_formato_nombre" placeholder="Nombre" value="" readonly>
			</div>
			
			<div class="form-group">
				<label for="col_formato_descripcion">Descripci&oacute;n</label>
				<input type="text" class="form-control" id="col_formato_descripcion" placeholder="Ingrese descripción" value="" onchange="">
			</div>


			<div style="width:100%;float:left;">
				<font id="informacion_formato"></font>
			</div>
<br/>
        </div>
        <div class="modal-footer">
			

          	<button type='button' class='btn btn-default btn-sm' title='Guardar Cambios' onclick="ocultarModalFormato()">
				<span class='glyphicon glyphicon-back'></span> Cancelar
			</button> 
			
          	<button type='button' class='btn btn-primary btn-sm' title='Validar Nombre' onclick="validarNombreFormato()">
				<span class='glyphicon glyphicon-refresh'></span> Validar Nombre
			</button> 

          	<button id="btnAgregarFormato" type='button' class='btn btn-success btn-sm' style="display:none" title='Guardar Cambios' onclick="agregarFormatoNomina()">
				<span class='glyphicon glyphicon-plus'></span> Crear Formato
			</button> 

        </div>
      </div>
      
    </div>
  </div>

 <div class="modal fade" id="modalCopiarFormato" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title">Copiar Formato Existente</h4>
        </div>
        <div class="modal-body">
          
			<div class="form-group">
				<label for="col_formato_sufijo">Sufijo</label>
				<input type="text" class="form-control" id="col_copia_formato_sufijo" placeholder="Ingrese Sufijo" value="" onchange="agregarSufijoCopiado();" onKeyPress="return validateString(event)">
			</div>

			<div class="form-group">
				<label for="col_formato_nombre">Nombre Configuraci&oacute;n</label>
				<input type="text" class="form-control" id="col_copia_formato_nombre" placeholder="Nombre" value="" readonly>
			</div>
			
			<div class="form-group">
				<label for="col_formato_descripcion">Formatos Disponibles</label>
				<select id="sel_copia_formatos_disponibles" class="form-control" ><option>Seleccione Formato</option></select>
			</div>


			<div style="width:100%;float:left;">
				<font id="informacion_formato_copiado"></font>
			</div>
<br/>
        </div>
        <div class="modal-footer">
			

          	<button type='button' class='btn btn-default btn-sm' title='Guardar Cambios' onclick="ocultarModalFormatoCopiado()">
				<span class='glyphicon glyphicon-back'></span> Cancelar
			</button> 
			
          	<button type='button' class='btn btn-primary btn-sm' title='Validar Nombre' onclick="validarNombreFormatoCopiado()">
				<span class='glyphicon glyphicon-refresh'></span> Validar Nombre
			</button> 

          	<button id="btnAgregarFormatoCopiado" type='button' class='btn btn-success btn-sm' style="display:none" title='Guardar Cambios' onclick="agregarFormatoNominaCopiado()">
				<span class='glyphicon glyphicon-duplicate'></span> Copiar Formato
			</button> 

        </div>
      </div>
      
    </div>
  </div>
  

<!-- Modal -->
  <div class="modal fade" id="modalAgregarNomina" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title">Agregar N&oacute;mina</h4>
        </div>
        <div class="modal-body">
          

			<div class="form-group">
				<label for="col_nomina_sufijo">Sufijo</label>
				<input type="text" class="form-control" id="col_nomina_sufijo" placeholder="Ingrese Sufijo" value="" onchange="agregarSufijoNomina();" onKeyPress="return validateString(event)">
			</div>

			<div class="form-group">
				<label for="col_nomina_nombre">Nombre Configuraci&oacute;n</label>
				<input type="text" class="form-control" id="col_nomina_nombre" placeholder="Nombre" value="" readonly>
			</div>

			<div class="form-group">
				<label for="col_nomina_tipo">Tipo</label>
				<select id="col_nomina_tipo" onchange="habilitarDataAdicional()">
					<option value="NORMAL">NORMAL</option>
					<option value="AGRUPADA">AGRUPADA</option>
					<option value="FILTRO">FILTRO</option>
					<option value="JAVA">JAVA</option>
					<option value="" selected>SELECCIONE</option>
				</select>
			</div>

			<div class="form-group">
				<label for="col_nomina_entidad">C&oacute;digo Entidad</label>
				<input type="text" class="form-control" id="col_nomina_entidad" placeholder="Entidad" value="" readonly>
			</div>
			
			<div class="form-group">
				<label for="col_nomina_data_adicional">Data Adicional</label>
				<input type="text" class="form-control" id="col_nomina_data_adicional" placeholder="Ingrese Data Adicional" value="" onchange="">
			</div>

			<div class="form-check">
				<label >Formato de Salida</label><br/>
				<input type="checkbox" class="form-check-input" name="col_nomina_formato_salida" value="txt" checked> TXT </input>&nbsp;
				<input type="checkbox" class="form-check-input" name="col_nomina_formato_salida" value="csv" > CSV </input>&nbsp;
				<input type="checkbox" class="form-check-input" name="col_nomina_formato_salida" value="xls" > XLS </input>&nbsp;
				<input type="checkbox" class="form-check-input" name="col_nomina_formato_salida" value="pdf" > PDF </input>&nbsp;
				<input type="checkbox" class="form-check-input" name="col_nomina_formato_salida" value="car" > Carta </input>&nbsp;
			</div>


			<div class="form-group">
				<label for="col_nomina_formato">Formato N&oacute;mina</label>
				<select id="col_nomina_formato">
					<option value="" selected>SELECCIONE</option>
				</select>
			</div>

			<div class="form-group">
				<label for="col_opciones_adicionales">Opciones Adicionales</label><br/>
				<input type="radio" class="form-check-input" name="col_nomina_formato_agrupar_por" value="config.agrupaciones.rut" > Agrupar Por Rut y sumar Monto Cuota </input>&nbsp;
				<input type="radio" class="form-check-input" name="col_nomina_formato_agrupar_por" value="config.agrupaciones.nro.inscripcion" > Agrupar Por Rut y Nro. Inscripci&oacute;n y sumar Monto Cuota </input>&nbsp;
				
			</div>
			<div class="form-group">
				<label for="col_nomina_archivo_salida">Nombre Archivo Salida</label>
				<table>
					<tr>

						<td><select id="sel_tipo_dato" style="height:30px" onchange="cargarValoresDatos()"><option value="texto">Texto</option><option value="variable_sistema">Variable Sistema</option><option value="columna_bd">Columna BD</option><option selected>Seleccione Tipo Dato</option></select></td>
						<td>
							<input type="text" class="form-control valor_dato" id="texto_dato" placeholder="Ingrese Dato Salida" value="" onchange="" onKeyPress="return validateString(event)" style="display:none">
							<select id="sel_variables_disponibles" class="valor_dato" style="height:30px;display:none"></select>
							<select id="sel_columnas_disponibles" class="valor_dato" style="height:30px;display:none"></select>
						</td>
						

					</tr>
					<tr >
						<td colspan="2" style="padding-top:5px;">
						&nbsp;
							<button type='button' class='btn btn-danger btn-sm'  onclick="limpiarNombre()">
								<span class='glyphicon glyphicon-remove'></span> Limpiar Nombre Salida
							</button>
						&nbsp;
							<button type='button' class='btn btn-primary btn-sm'  onclick="agregarDato()">
								<span class='glyphicon glyphicon-ok'></span> Agregar
							</button>
						</td>
					</tr>
				</table>
				<input type="text" class="form-control" id="col_nomina_archivo_salida" placeholder="Ingrese Nombre Archivo Salida" value="" readonly>
			</div>
			
			<div style="width:100%;float:left;">
				<font id="informacion_nomina"></font>
			</div>
<br/>
        </div>
        <div class="modal-footer">
			

          	<button type='button' class='btn btn-default btn-sm' title='Guardar Cambios' onclick="ocultarModalNomina()">
				<span class='glyphicon glyphicon-back'></span> Cancelar
			</button> 
			
          	<button type='button' class='btn btn-primary btn-sm' title='Validar Nombre' onclick="validarNombreNomina()">
				<span class='glyphicon glyphicon-refresh'></span> Validar Nombre
			</button> 

          	<button id="btnAgregarNomina" type='button' class='btn btn-success btn-sm' style="display:none" title='Guardar Cambios' onclick="agregarNomina()">
				<span class='glyphicon glyphicon-plus'></span> Crear Configuraci&oacute;n
			</button> 

        </div>
      </div>
      
    </div>
  </div>
 

<div class="modal fade" id="modalBuscarEntidades" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title">Buscar Entidad</h4>
        </div>
        <div class="modal-body">
          
		

			<div class="form-group">
				<label for="col_nombre_entidad">Nombre Entidad</label>
				<input type="text" class="form-control" id="col_nombre_entidad" placeholder="Ingrese Nombre Entidad" value="" onchange="buscarEntidades();">
			</div>

			<div class="form-check">
				<label >Tipo de Entidad</label><br/>
				<input type="radio" class="form-radio-input" name="col_tipo_entidad" value="empresa_publica" checked> Empresa P&uacute;blica </input>&nbsp;
				<input type="radio" class="form-radio-input" name="col_tipo_entidad" value="empresa_privada" > Empresa Privada </input>&nbsp;
<input type="radio" class="form-radio-input" name="col_tipo_entidad" value="holding" > Holding </input>&nbsp;
			</div>

<div class="form-group">
	<br/>
<button id="btnBuscarEntidades" type='button' class='btn btn-success btn-sm' onclick="buscarEntidades()">
				<span class='glyphicon glyphicon-search'></span> Buscar Entidades
			</button> 
			</div>

			<div class="form-group">
			  <h4>Entidades Encontradas</h4>         
			  <table class="table table-bordered">
				<thead  class="header_tabla">
				  <tr>
					<th width="100px">C&oacute;digo Entidad</th>
					<th width="100px">Nombre Entidad</th>
					<th width="100px">Seleccionar</th>
				  </tr>
				</thead>
				<tbody id="body_entidades">
				  
				  <tr class="sin_resultados">
					<td colspan="3" style="padding-left:20px;">Sin Resultados</td>
				  </tr>
					
				  <tr class="cargando" style="display:none">
					<td colspan="3" style="padding-left:20px;">Buscando, por favor espere...<img src="img/loading.gif" width="20px"></img></td>
				  </tr>
				</tbody>
			  </table>
			</div>
		
			<div style="width:100%;float:left;">
				<font id="informacion_entidades"> Se muestran m&aacute;ximo 10 Entidades, si la entidad que busca no aparece, refine la b&uacute;squeda.</font>
			</div>
<br/>
        </div>
        <div class="modal-footer">
			

          	<button type='button' class='btn btn-default btn-sm' title='Guardar Cambios' onclick="ocultarModalEntidades()">
				<span class='glyphicon glyphicon-back'></span> Cancelar
			</button> 
			

        </div>
      </div>
      
    </div>
  </div>
 


<div class="modal fade" id="modalVerEmpresasAsociadas" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title">Ver Empresas Asociadas</h4>
        </div>
        <div class="modal-body">

			<div class="form-group">       
			  <table class="table table-bordered">
				<thead  class="header_tabla">
				  <tr>
					<th width="100px">Rut Empresa</th>
					<th width="100px">Nombre Entidad</th>
				  </tr>
				</thead>
				<tbody id="body_empresas">
				  
				  <tr class="sin_resultados">
					<td colspan="3" style="padding-left:20px;">Sin Resultados</td>
				  </tr>
					<tr class="cargando" style="display:none">
					<td colspan="3" style="padding-left:20px;">Buscando, por favor espere...<img src="img/loading.gif" width="20px"></img></td>
				  </tr>
				</tbody>
			  </table>
			</div>
		
			
        </div>
        <div class="modal-footer">
			

          	<button type='button' class='btn btn-default btn-sm' title='Guardar Cambios' onclick="ocultarModalEmpresasAsociadas()">
				<span class='glyphicon glyphicon-back'></span> Cancelar
			</button> 
			

        </div>
      </div>
      
    </div>
  </div>
 


<div class="modal fade" id="modalHoldingConfigurado" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title">Holding Configurado</h4>
        </div>
        <div class="modal-body">

			Esta empresa pertenece a un Holding [<font id="codigo_holding_asociado"></font>] ya configurado.<br/> La configuraci&oacute;n de la n&oacute;mina para la empresa [<font id="codigo_empresa_buscada"></font>] ser&aacute; <b>ignorada</b> en el proceso masivo de Generaci&oacute;n de N&oacute;minas, sin embargo, &eacute;sta podr&aacute; ser ejecutada de forma manual.
		
			
        </div>
        <div class="modal-footer">
			

          	<button type='button' class='btn btn-default btn-sm'  onclick="ocultarModalHoldingConfigurado()">
				<span class='glyphicon glyphicon-back'></span> Cancelar
			</button> 
			

          	<button type='button' class='btn btn-primary btn-sm'  onclick="cargarConfiguracionHoldingAsociado()">
				<span class='glyphicon glyphicon-back'></span> Ir a la Configuraci&oacute;n del Holding
			</button> 
			

          	<button type='button' class='btn btn-success btn-sm'  onclick="continuarConfigurandoEmpresa()">
				<span class='glyphicon glyphicon-back'></span> Continuar de todas formas
			</button> 
			

        </div>
      </div>
      
    </div>
  </div>
 

<div class="modal fade" id="modalConfiguracionEnvio" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title">Configuraci&oacute;n de Env&iacute;o</h4>
        </div>
        <div class="modal-body">

		<div class="form-group">
			<label for="txt_envio_codigo_entidad">Entidad Relacionada: </label>
			<font id="txt_envio_codigo_entidad"></font>
		</div>

		<div class="form-group">
				<label for="txt_envio_tipo">Tipo Env&iacute;o</label><br/>
				&nbsp;&nbsp;<input type="radio" class="form-check-input" name="txt_envio_tipo" value="mail" checked onclick="seleccionarEnvio('mail')"> MAIL </input>&nbsp;
				<input type="radio" class="form-check-input" name="txt_envio_tipo" value="ftp" onclick="seleccionarEnvio('ftp')"> FTP </input>&nbsp;
			</div>
        </div>

		<div class="form-group" style="padding-left:20px;padding-right:20px;">
			<div class=" envio envio_mail" style="display:none">
				<label for="col_envio_mails">Correo Electr&oacute;nico</label>
				<table>
					<tr>
						<td><input type="text" class="form-control" id="txt_mail" placeholder="Ingrese Mail" value="" onchange=""></td>
						

					</tr>
					<tr >
						<td colspan="2">
						&nbsp;
							<button type='button' class='btn btn-danger btn-sm'  onclick="limpiarMails()">
								<span class='glyphicon glyphicon-remove'></span> Limpiar Mails
							</button>
						&nbsp;
							<button type='button' class='btn btn-primary btn-sm'  onclick="agregarMail()">
								<span class='glyphicon glyphicon-ok'></span> Agregar
							</button>
						</td>
					</tr>
				</table>
			</div>		

			<div class=" envio envio_ftp" style="display:none">
				<label for="col_envio_mails">Configuraci&oacute;n FTP</label>
				<table>
					<tr height="25px">
						<td width="100px">
							Servidor
						</td>
						<td>
							<input type="text"  id="txt_envio_ftp_server" placeholder="Ingrese Server" value="" onchange="" onclick="limpiaInfo(this)">
							</td><td id="info_txt_envio_ftp_server"></td>
						</td>
					</tr>
					<tr height="25px">
						<td width="100px">
							Puerto
						</td>
						<td>
							<input type="text"  id="txt_envio_ftp_puerto" placeholder="Ingrese Puerto" value="21" onchange=""  onclick="limpiaInfo(this)">
							</td><td id="info_txt_envio_ftp_puerto"></td>
						</td>
					</tr>
					<tr height="25px">
						<td width="100px">
							Usuario
						</td>
						<td>
							<input type="text"  id="txt_envio_ftp_usuario" placeholder="Ingrese Usuario" value="" onchange="" onclick="limpiaInfo(this)">
							</td><td id="info_txt_envio_ftp_usuario"></td>
						</td>
					</tr>
					<tr height="25px">
						<td width="100px">
							Password
						</td>
						<td>
							<input type="text"  id="txt_envio_ftp_password" placeholder="Ingrese Password" value="" onchange="" onclick="limpiaInfo(this)">
							</td><td id="info_txt_envio_ftp_password"></td>
						</td>
					</tr>

					<tr height="25px">
						<td width="100px">
							Directorio
						</td>
						<td>
							<input type="text"  id="txt_envio_ftp_directorio" placeholder="Ingrese Directorio" value="/" onchange="" onclick="limpiaInfo(this)">
							</td><td id="info_txt_envio_ftp_directorio"></td>
						</td>
					</tr>
					<tr >
						<td colspan="2">
						&nbsp;
							<button type='button' class='btn btn-danger btn-sm'  onclick="limpiarDatosFTP()">
								<span class='glyphicon glyphicon-remove'></span> Limpiar 
							</button>
						&nbsp;
							<button type='button' class='btn btn-primary btn-sm'  onclick="refrescarDatosFTP()">
								<span class='glyphicon glyphicon-ok'></span> Asignar
							</button>
						</td>
					</tr>
				</table>
			</div>		
			
			<input type="text" class="form-control" id="txt_envio_data_adicional" placeholder="" value="" readonly>
		</div>

    <div class="modal-footer">
			
			<button type='button' class='btn btn-success btn-sm' title='Guardar Cambios' onclick="guardarConfiguracionEnvio()">
				<span class='glyphicon glyphicon-back'></span> Guardar Configuraci&oacute;n
			</button> 
			

        </div>
      </div>
      
    </div>
  </div>
 



</body>
</html>


<script>

var url_busqueda = "/NRP/request?formato_salida=json&id_req=buscarEmpresaHolding&token="+mi_token+"&codigo_a_buscar=";
var url_add_formato = "/NRP/request?formato_salida=json&id_req=agregarFormato&token="+mi_token+"&";
var url_del_formato = "/NRP/request?formato_salida=json&id_req=eliminarFormato&token="+mi_token+"&";
var url_add_nomina = "/NRP/request?formato_salida=json&id_req=agregarNomina&token="+mi_token+"&";
var url_del_config = "/NRP/request?formato_salida=json&id_req=eliminaNomina&token="+mi_token+"&";
var url_add_envio = "/NRP/request?formato_salida=json&id_req=AgregarEnvio&token="+mi_token+"&codigo_entidad=";
var url_del_envio = "/NRP/request?formato_salida=json&id_req=eliminarEnvio&token="+mi_token+"&";
var url_buscar_entidades = "/NRP/request?formato_salida=json&id_req=buscarEntidades&token="+mi_token+"&codigo_entidad=";
var url_validar_nombre_formato =  "/NRP/request?formato_salida=json&id_req=validarNombre&token="+mi_token+"&tipo=formato&nombre=";
var url_validar_nombre_nomina =  "/NRP/request?formato_salida=json&id_req=validarNombre&token="+mi_token+"&tipo=nomina&nombre=";
var url_entidades_asociadas = "/NRP/request?formato_salida=json&id_req=buscarEmpresasAsociadas&token="+mi_token+"&codigo_entidad=";
var url_generar_nominas = "/NRP/request?formato_salida=json&id_req=generarNominas&token="+mi_token+"&codigo_entidad=";
var url_enviar_nominas = "/NRP/request?formato_salida=json&id_req=enviarNominas&token="+mi_token+"&codigo_entidad=";
$("#btn_buscar_info").click(function() {
		
buscarInformacion();	

});

$("#btn_seleccionar_entidad").click(function() {
		
	limpiarFormularioBuscarEntidades();
	$("#modalBuscarEntidades").modal();
});


function validateString(event){
	if(event.keyCode >= 65 && event.keyCode <= 90){
		return true;
	}
	else if(event.keyCode >= 97 && event.keyCode <= 122){
		return true;
	} 
	else if(event.keyCode === 95){
		return true;
	} 
	return false;
}


function buscarInformacion(){
		validaSiEsHolding();
		$("#btn_ver_nominas_periodo").show();
		$("#btn_add_formato").hide();
		$("#btn_add_nomina").hide();
		$("#btn_add_envio").hide();
		$("#btn_generar_nomina").hide();
		$("#btn_copy_formato").hide();
		$(".tr_formato").remove();
		$(".tr_config").remove();
		$(".tr_envio").remove();
		$("#body_formatos .sin_resultados").show();
		$("#body_nominas .sin_resultados").show();
		$("#body_envios .sin_resultados").show();

		if($("#codigo_entidad").val().length < 3){
			alert("ingrese un dato válido");
			return ;
		}
		console.log("buscando info...")
		mostrarCargando("#buscarInfo");
	
		url = url_busqueda + $("#codigo_entidad").val();
		ejecutarAJAX( url,null, function( data ) {
	
			if(data.item.error != null && data.item.error.length > 0){
				
				cargarMensajeInformativo(data.item.error, 3000, 'error');
				ocultarCargando("#buscarInfo");
				$("#codigo_entidad").val("")
				return;
			}
			if(data.item.holding != null){
				cargarHoldingConfigurado(data.item.holding);
			}

			$("#col_nomina_formato").html("<option>SELECCIONE</option>");
			for(var i=0; i< data.item.formatos.length; i++){
				var entidad = "";
				var opcion = "<td></td>";
				var info = "<td><a href='verConfiguracion.jsp?r="+getData(data.item.formatos[i],'nombre_nomina')+"&ce="+$("#codigo_entidad").val()+"'>"+getData(data.item.formatos[i],'nombre_nomina')+"</a></td>";
				if(getData(data.item.formatos[i],'rut_empresa') == null && getData(data.item.formatos[i],'codigo_holding')==null){
					entidad = "Gen&eacute;rico";
				}
				if(getData(data.item.formatos[i],'rut_empresa') != null && getData(data.item.formatos[i],'rut_empresa').length >0){
					entidad = "Empresa: "+ getData(data.item.formatos[i],'rut_empresa');
					opcion = "<td><span class='label label-danger clickeable'  onclick='eliminarFormato(\""+getData(data.item.formatos[i],'nombre_nomina')+"\");'><span class='glyphicon glyphicon-remove'></span> Eliminar </span></td>";
					info = "<td><a href='configurador_new.jsp?r="+getData(data.item.formatos[i],'nombre_nomina')+"&ce="+$("#codigo_entidad").val()+"'>"+getData(data.item.formatos[i],'nombre_nomina')+"</a></td>";
				}
				if(getData(data.item.formatos[i],'codigo_holding') !=null && getData(data.item.formatos[i],'codigo_holding').length>0){

					entidad = "Holding: "+ getData(data.item.formatos[i],'codigo_holding');
					opcion = "<td><span class='label label-danger clickeable'  onclick='eliminarFormato(\""+getData(data.item.formatos[i],'nombre_nomina')+"\");'><span class='glyphicon glyphicon-remove'></span> Eliminar </span></td>";
					info = "<td><a href='configurador_new.jsp?r="+getData(data.item.formatos[i],'nombre_nomina')+"&ce="+$("#codigo_entidad").val()+"'>"+getData(data.item.formatos[i],'nombre_nomina')+"</a></td>";
				}
				

				var tr = "<tr class='tr_formato'><td>"+entidad+"</td>"+
						info+
						"<td>"+getData(data.item.formatos[i],'descripcion')+"</td>"+
				opcion +

				"</tr>";
				$("#body_formatos").append(tr);

				$("#col_nomina_formato").append("<option value='"+getData(data.item.formatos[i],'nombre_nomina')+"'>"+getData(data.item.formatos[i],'nombre_nomina')+"</option>");
				
			}

			if(data.item.formatos.length > 0 ){
				$("#btn_add_formato").show();
			}

			var config_generica = false;
			for(var i=0; i< data.item.configuraciones.length; i++){
				var entidad = "";
				var opcion = "<td></td>";
				if(getData(data.item.configuraciones[i],'rut_empresa') == null && getData(data.item.configuraciones[i],'codigo_holding')==null){
					entidad = "Gen&eacute;rico";				
					config_generica = true;
				}
				if(getData(data.item.configuraciones[i],'rut_empresa') != null && getData(data.item.configuraciones[i],'rut_empresa').length > 0){
					entidad = "Empresa: "+ getData(data.item.configuraciones[i],'rut_empresa');
					opcion = "<td><span class='label label-danger clickeable'  onclick='eliminarConfigNomina(\""+getData(data.item.configuraciones[i],'nombre_config')+"\");'><span class='glyphicon glyphicon-remove'></span> Eliminar </span></td>";
				}
				if(getData(data.item.configuraciones[i],'codigo_holding') !=null && getData(data.item.configuraciones[i],'codigo_holding').length >0){
					entidad = "Holding: "+ getData(data.item.configuraciones[i],'codigo_holding');
					opcion = "<td><span class='label label-danger clickeable'  onclick='eliminarConfigNomina(\""+getData(data.item.configuraciones[i],'nombre_config')+"\");'><span class='glyphicon glyphicon-remove'></span> Eliminar </span></td>";
				}
				
				var data_adicional = getData(data.item.configuraciones[i],'data_adicional')

				/*if(data_adicional != null && data_adicional.length > 40 ){
					data_adicional = data_adicional.substr(0,40)+ " " + data_adicional.substr(40) ;
				}*/
		
				var tr = "<tr class='tr_config'><td>"+entidad+"</td>"+
											"<td>"+getData(data.item.configuraciones[i],'tipo')+"</td>"+
											"<td>"+data_adicional+"</td>"+
											"<td>"+getData(data.item.configuraciones[i],'formato_salida')+"</td>"+
											"<td>"+getData(data.item.configuraciones[i],'nombre_salida')+"</td>"+
											"<td><a href='verConfiguracion.jsp?r="+getData(data.item.configuraciones[i],'formato_nomina')+"'>"+getData(data.item.configuraciones[i],'formato_nomina')+"</a></td>"+
		

		opcion+
	
						"</tr>";
				$("#body_nominas").append(tr);
			}
			if(data.item.configuraciones.length > 0 ){
				$("#btn_add_nomina").show();
			}

			if(data.item.envio.length == 0 && config_generica == false){
				$("#btn_add_envio").show();
			}
			else{ 
				$("#btn_add_envio").hide();
			}

			if(config_generica){
				var tr = "<tr class='tr_envio'><td colspan='4'> S&oacute;lo existe n&oacute;mina gen&eacute;rica, no se puede configurar env&iacute;os </td>"
	
						"</tr>";
				
				$("#body_envios").append(tr);
		
			}
			else{
				for(var i=0; i< data.item.envio.length; i++){
					var entidad = "";
					var codigo_entidad = "";
					if(data.item.envio[i].rut_empresa == null && data.item.envio[i].codigo_holding==null){
						entidad = "Gen&eacute;rico";
					}
					if(data.item.envio[i].rut_empresa != null && data.item.envio[i].rut_empresa.length > 0){
						entidad = "Empresa: "+ data.item.envio[i].rut_empresa;
						codigo_entidad = getData(data.item.configuraciones[i],'rut_empresa')
					}
					if(data.item.envio[i].codigo_holding!=null && data.item.envio[i].codigo_holding.length >0){
						entidad = "Holding: "+ data.item.envio[i].codigo_holding;
						codigo_entidad = getData(data.item.configuraciones[i],'codigo_holding')
					}
				
					var tr = "<tr class='tr_envio'><td>"+entidad+"</td>"+
												"<td>"+getData(data.item.envio[i],"tipo")+"</td>"+
												"<td>"+getData(data.item.envio[i],"data_adicional")+"</td>"+
	"<td><span class='label label-danger clickeable'  onclick='eliminarEnvio();'><span class='glyphicon glyphicon-remove'></span> Eliminar </span> &nbsp; <span class='label label-success clickeable'  onclick='enviarAhora()'><span class='glyphicon glyphicon-share'></span> Enviar Ahora </span></td>"
	
							"</tr>";
				
					$("#btn_add_envio").hide();
					$("#body_envios").append(tr);
				}
			}
			$("#btn_generar_nomina").show();
			$("#btn_copy_formato").show();
			ocultarCargando("#buscarInfo");
		});


		$("#body_formatos .sin_resultados").hide();
		$("#body_nominas .sin_resultados").hide();
		$("#body_envios .sin_resultados").hide();



}



function generarNominas(nombre){

	var params = "codigo_entidad:"+$("#codigo_entidad").val()+";;";
	window.open("http://localhost:8080/NRP/Planillas?req=generar_todas&tipo=txt&reporte="+id_reporte+"&params="+params);
}

function agregarSufijo(){
	var prefijo = "NOM_"+$("#codigo_entidad").val()+"_";
	$("#col_formato_nombre").val(prefijo + $("#col_formato_sufijo").val() );
}

function agregarSufijoCopiado(){
	var prefijo = "NOM_"+$("#codigo_entidad").val()+"_";
	$("#col_copia_formato_nombre").val(prefijo + $("#col_copia_formato_sufijo").val() );
}

function validarNombreFormato(){
	if($("#col_formato_sufijo").val().length == 0){
		alert("Ingrese un Sufijo válido");
		return;
	}

	var url = 	url_validar_nombre_formato + $("#col_formato_nombre").val();

	ejecutarAJAX( url,null, function (data) {
		if(data.item.valido == true){
			$("#informacion_formato").html("Configuraci&oacute;n Correcta.");
			$("#btnAgregarFormato").show();
			$("#col_formato_sufijo").attr("readonly","readonly");
		}
		else{
			$("#informacion_formato").html("Nombre no válido.");
		}
	  
	});



}

function validarNombreFormatoCopiado(){
	if($("#col_copia_formato_sufijo").val().length == 0){
		alert("Ingrese un Sufijo válido");
		return;
	}

	var url = 	url_validar_nombre_formato + $("#col_copia_formato_nombre").val();

	ejecutarAJAX( url,null, function (data) {
		if(data.item.valido == true){
			$("#informacion_formato_copiado").html("Configuraci&oacute;n Correcta.");
			$("#btnAgregarFormatoCopiado").show();
			$("#col_copia_formato_sufijo").attr("readonly","readonly");
		}
		else{
			$("#informacion_formato_copiado").html("Nombre no válido.");
		}
	  
	});



}



function agregarFormatoNomina(){
	var rut_empresa = $("#codigo_entidad").val();
	var codigo_holding = "";
	
	if($("#codigo_entidad").val().toLowerCase().indexOf("h") ==0){
		rut_empresa = "";
		codigo_holding = $("#codigo_entidad").val();
	}
	console.log("agregando formato...")
	mostrarCargando("#addFormato");
	url = url_add_formato;
	var dataJSON = '{ "rut_empresa":"'+rut_empresa+'","codigo_holding":"'+codigo_holding+'","nombre_nomina":"'+$("#col_formato_nombre").val()+'","descripcion":"'+$("#col_formato_descripcion").val()+'"}';

	//console.log(dataJSON);

	ejecutarAJAX(url, { input_json :  dataJSON },function (data) {
		if(data.item.estado == true){
			cargarMensajeInformativo("El formato se ha creado correctamente ", 3000, 'success');
		}
		else{
			cargarMensajeInformativo("Ha ocurrido un error al crear el formato", 3000, 'error');
		}

		$("#sel_copia_formatos_disponibles").html();
		cargarSelectGenerico("/NRP/request?formato_salida=json&id_req=obtenerFormatosDisponibles","sel_copia_formatos_disponibles");
		ocultarModalFormato();
	})
	
}


function eliminarFormato(nombre){

	var fnOK = function(){
		mostrarCargando("#eliminarFormato");
		url = url_del_formato;
		var dataJSON = '{ "codigo_entidad":"'+$("#codigo_entidad").val()+'","nombre_nomina":"'+nombre+'"}';


		ejecutarAJAX(url, { input_json :  dataJSON },function (data) {
			if(data.item.estado == true){
				cargarMensajeInformativo("El formato se ha eliminado correctamente ", 3000, 'success');
			}
			else{
				cargarMensajeInformativo("Ha ocurrido un error al eliminar el formato", 3000, 'error');
			}
			
			$("#sel_copia_formatos_disponibles").html();
			cargarSelectGenerico("/NRP/request?formato_salida=json&id_req=obtenerFormatosDisponibles","sel_copia_formatos_disponibles");
			buscarInformacion();
		
		  
		});
	}


	agregarConfirm("¿Está Seguro que desea eliminar el formato ["+nombre+"]?",fnOK,function(){});
}




function agregarFormatoNominaCopiado(){
	var rut_empresa = $("#codigo_entidad").val();
	var codigo_holding = "";
	
	if($("#codigo_entidad").val().toLowerCase().indexOf("h") ==0){
		rut_empresa = "";
		codigo_holding = $("#codigo_entidad").val();
	}
	console.log("agregando formato...")
	mostrarCargando("#addFormato");
	url = url_add_formato;
	var dataJSON = '{ "rut_empresa":"'+rut_empresa+'","codigo_holding":"'+codigo_holding+'","nombre_nomina":"'+$("#col_copia_formato_nombre").val()+'","formato_copia":"'+$("#sel_copia_formatos_disponibles").val()+'","descripcion":"formato copiado"}';

	//console.log(dataJSON);

	ejecutarAJAX(url, { input_json :  dataJSON },function (data) {
		if(data.item.estado == true){
			cargarMensajeInformativo("El formato se ha creado correctamente ", 3000, 'success');
		}
		else{
			cargarMensajeInformativo("Ha ocurrido un error al crear el formato", 3000, 'error');
		}

		
		$("#sel_copia_formatos_disponibles").html();
		cargarSelectGenerico("/NRP/request?formato_salida=json&id_req=obtenerFormatosDisponibles","sel_copia_formatos_disponibles");
		
		ocultarModalFormatoCopiado();
	})
	
}



function eliminarEnvio(){
	var fnOK = function(){
		mostrarCargando("#eliminarFormato");
		url = url_del_envio + "codigo_entidad="+$("#codigo_entidad").val();
		ejecutarAJAX(url, null ,function (data) {
			if(data.item.estado == true){
				cargarMensajeInformativo("La Configuraci&oacute;n de Env&iacute;o se ha eliminado Correctamente ", 3000, 'success');
			}
			else{
				cargarMensajeInformativo("Ha ocurrido un error al eliminar la Configuraci&oacute;n de Env&iacute;o", 3000, 'error');
			}
			buscarInformacion();
		  
		});
	}

	agregarConfirm("¿Está Seguro que desea eliminar la configuraci&oacute;n de env&iacute;o ",fnOK,function(){});
}


$("#btn_add_formato").click(function() {
	$("#col_formato_nombre").val("NOM_"+$("#codigo_entidad").val()+"_");
	$("#col_formato_sufijo").val("");
	$("#col_formato_descripcion").val("");
	$("#col_formato_sufijo").removeAttr("readonly");
	$("#informacion_formato").html("");

	$("#modalAgregarFormato").modal();

	$("#btnAgregarFormato").hide();
});


$("#btn_copy_formato").click(function() {
	$("#col_copia_formato_nombre").val("NOM_"+$("#codigo_entidad").val()+"_");
	$("#col_copia_formato_sufijo").val("");
	$("#col_copia_formato_sufijo").removeAttr("readonly");
	$("#informacion_formato").html("");

	$("#modalCopiarFormato").modal();

});
$("#btn_generar_nomina").click(function() {
	mostrarCargando("#generar nomina");

		setTimeout( function (){
			url = url_generar_nominas;
			url = url + $("#codigo_entidad").val() +"&cantidad_hebras=1";
			$.getJSON( url, function( data ) {
				
				ocultarCargando();
				var tiempo = 2000;
				if(!data.item.status)
					tiempo = 5000;

				cargarMensajeInformativo(data.item.descripcion, tiempo, '');

			});
		}, 1000);
});



$("#btn_add_envio").click(function() {
	$("#modalConfiguracionEnvio").modal();
	validarComponentesEnvio();
});

function validarComponentesEnvio(){
	$("#txt_envio_data_adicional").val("")
	$(".envio").hide();
	var opciones = document.getElementsByName("txt_envio_tipo");
	for(var i=0; i<opciones.length; i++){
		if(opciones[i].checked){
			$(".envio_"+opciones[i].value).show();
		}
	}

}

function agregarMail(){

if(validacionesOBJ.validarEmail($("#txt_mail").val()) ){
	var mail = $("#txt_mail").val()+";";	
	var data = $("#txt_envio_data_adicional").val();
	data = data + mail;
	$("#txt_envio_data_adicional").val(data);
	$("#txt_mail").val("");	
}
else{
	alert("Formato de mail no válido");
}
}

function limpiarMails(){
$("#txt_mail").val("");
$("#txt_envio_data_adicional").val("");
}

function guardarConfiguracionEnvio(){


	if($("#txt_envio_data_adicional").val().trim().length == 0){
		alert("Debe especificar la data adicional");
		return;
	}

	var rut_empresa = $("#codigo_entidad").val();
	var codigo_holding = "";
	var tipo = "";

	var opciones = document.getElementsByName("txt_envio_tipo");
	for(var i=0; i<opciones.length; i++){
		if(opciones[i].checked){
			tipo = opciones[i].value;
		}
	}

		


	if($("#codigo_entidad").val().toLowerCase().indexOf("h") ==0){
		rut_empresa = "";
		codigo_holding = $("#codigo_entidad").val();
	}
	else{
		rut_empresa = $("#codigo_entidad").val();
		codigo_holding = "";

	}

	url = url_add_envio;
	var dataJSON = '{ "rut_empresa":"'+rut_empresa+'","codigo_holding":"'+codigo_holding+'","tipo":"'+tipo+'","data_adicional":"'+$("#txt_envio_data_adicional").val()+'"}';
	ocultarModalConfiguracionEnvio();
	//ocultarModalConfiguracionEnvio();
	//console.log(dataJSON);
	mostrarCargando("#addConfEnvio");
	ejecutarAJAX(url, { input_json :  dataJSON }, function (data) {
		if(data.item.estado == true){
			cargarMensajeInformativo("La configuraci&oacute;n se ha guardado correctamente ", 3000, 'success');
		}
		else{
			cargarMensajeInformativo("Ha ocurrido un error al guardar la configuraci&oacute;n", 3000, 'error');
		}
	  	buscarInformacion();
	});

	


}

$("#btn_add_nomina").click(function() {

	$("#col_nomina_nombre").val("CONF_"+$("#codigo_entidad").val()+"_");
	$("#col_nomina_sufijo").val("");	
	$("#col_nomina_entidad").val($("#codigo_entidad").val());
	$("#col_nomina_data_adicional").val("");
	$("#texto_dato").val("");
	$("#col_nomina_sufijo").removeAttr("readonly");
	$("#informacion_nomina").html("")
	$("#col_nomina_formato_agrupar_por").attr('checked', false);
	document.getElementsByName('col_nomina_formato_agrupar_por')[0].checked = false;
	document.getElementsByName('col_nomina_formato_agrupar_por')[1].checked = false;
	limpiarNombre();
	$("#modalAgregarNomina").modal();

});




function ocultarModalNomina(){
$("#modalAgregarNomina").modal('toggle');

buscarInformacion();
}


function ocultarModalFormato(){
$("#modalAgregarFormato").modal('toggle');
buscarInformacion();
}


function ocultarModalFormatoCopiado(){
$("#modalCopiarFormato").modal('toggle');
buscarInformacion();
}


function ocultarModalConfiguracionEnvio(){
$("#modalConfiguracionEnvio").modal('toggle');
}

function ocultarModalEntidades(){
$("#modalBuscarEntidades").modal('toggle');
limpiarFormularioBuscarEntidades();
}

function seleccionarEntidad(entidad){
$("#codigo_entidad").val(entidad);
$("#modalBuscarEntidades").modal('toggle');
buscarInformacion();
limpiarFormularioBuscarEntidades();
}


function limpiarFormularioBuscarEntidades(){
	$("#cod_nombre_entidad").val("");
	$("#body_entidades .tr_registros").remove();
	$("#body_entidades .sin_resultados").show();
	$("#informacion_entidades").hide();
}


function agregarSufijoNomina(){
	var prefijo = "CONF_"+$("#codigo_entidad").val()+"_";
	$("#col_nomina_nombre").val(prefijo + $("#col_nomina_sufijo").val() );
}

function validarNombreNomina(){
	if($("#col_nomina_sufijo").val().length == 0){
		alert("Ingrese un Sufijo válido");
		return;
	}
	var url = 	url_validar_nombre_nomina + $("#col_nomina_nombre").val();
	ejecutarAJAX(url, null , function (data) {
		if(data.item.valido == true){
			$("#informacion_nomina").html("Configuraci&oacute;n Correcta.");
			$("#btnAgregarNomina").show();
			$("#col_nomina_sufijo").attr("readonly","readonly");
		}
		else{
			$("#informacion_nomina").html("Nombre no válido.");
		}
	  
	});

}

function habilitarDataAdicional(){
	if($("#col_nomina_tipo").val().trim().length > 0){
		
		if($("#col_nomina_tipo").val().trim().toLowerCase() == "normal" ){
			$("#col_nomina_data_adicional").val("");
			$("#col_nomina_data_adicional").attr("readonly","readonly");
		}
		else{
			$("#col_nomina_data_adicional").val("");
			$("#col_nomina_data_adicional").removeAttr("readonly");

		}
	
	}
}


function agregarDato(){
	var valorActual = $("#col_nomina_archivo_salida").val();
	var texto = "";
	if($("#sel_tipo_dato").val()=="texto"){
		texto = $("#texto_dato").val();
	}
	else if($("#sel_tipo_dato").val()=="variable_sistema"){
		texto = "var."+$("#sel_variables_disponibles").val().split(":")[0]  +";";
	}
	else if($("#sel_tipo_dato").val()=="columna_bd"){
		texto = "db."+$("#sel_columnas_disponibles").val()+";";
	}
	$("#texto_dato").val("");
	$("#col_nomina_archivo_salida").val(valorActual + texto);
}

function limpiarNombre(){
	$("#col_nomina_archivo_salida").val("");
}

function agregarNomina(){

	if($("#col_nomina_sufijo").val().trim().length == 0){
		alert("Ingrese un sufijo");
		return;
	}
	if($("#col_nomina_tipo").val().trim().length==0){
		alert("Debe seleccionar un tipo de configuración");
		return;
	}
	if($("#col_nomina_tipo").val().trim().length > 0){

		if($("#col_nomina_tipo").val().trim().toLowerCase() == "agrupada" && $("#col_nomina_archivo_salida").val().indexOf("db.")<0){
			alert("Advertencia, se genera un archivo agrupado, pero el nombre de salida no es variable !");
			return;
		}



		if($("#col_nomina_tipo").val().trim().toLowerCase() != "normal" && $("#col_nomina_data_adicional").val().trim().length == 0){
			alert("El tipo ["+$("#col_nomina_tipo").val().trim()+"] requiere Data Adicional ");
			return;
		}
	
	}
	
	
	
	var rut_empresa = $("#codigo_entidad").val();
	var codigo_holding = "";
	
	if($("#codigo_entidad").val().toLowerCase().indexOf("h") ==0){
		rut_empresa = "";
		codigo_holding = $("#codigo_entidad").val();
	}


	var formato_salida = "";
	var opciones = document.getElementsByName("col_nomina_formato_salida");
	var seleccionados = false;
	for(var i=0; i< opciones.length; i++){
		if(opciones[i].checked){
			formato_salida=formato_salida+opciones[i].value+";";
			seleccionados = true;
		}
	}

	if(seleccionados == false ){
		alert("Debe seleccionar al menos un formato (txt, csv, xls, pdf, carta) ");
		return;
	}

	if($("#col_nomina_formato").val().trim() == "SELECCIONE"){
		alert("Debe seleccionar el Formato para la Nómina a Generar");
		return;
	
	}
	

	if($("#col_nomina_archivo_salida").val().trim().length ==0){
		alert("Debe ingresar un nombre para el archivo de salida");
		return;
	
	}

	var par_generacion = "";
	
	var opciones = document.getElementsByName('col_nomina_formato_agrupar_por');
	
	for(var i=0; i< opciones.length; i++){
		if(document.getElementsByName('col_nomina_formato_agrupar_por')[i].checked){
			par_generacion = document.getElementsByName('col_nomina_formato_agrupar_por')[i].value;
		}
	}
	
	url = url_add_nomina;
	var dataJSON = '{ '+
					'"rut_empresa":"'+rut_empresa+'"'+
					',"codigo_holding":"'+codigo_holding+'"'+
					',"tipo":"'+$( "#col_nomina_tipo option:selected" ).text()+'"'+
					',"data_adicional":"'+ $( "#col_nomina_data_adicional" ).val() +'"'+
					',"formato_salida":"'+ formato_salida +'"'+
					',"nombre_salida":"' + $( "#col_nomina_archivo_salida" ).val() +'"'+
					',"formato_nomina":"'+ $( "#col_nomina_formato" ).val() +'"'+
					',"nombre_config":"'+ $( "#col_nomina_nombre" ).val() +'"'+
					',"par_generacion":"'+ par_generacion +'"'+
					'}';

	console.log(dataJSON);
	
	ejecutarAJAX(url, { input_json :  dataJSON }, function (data) {
		//Do stuff with the JSON data
		if(data.item.estado == true){
			cargarMensajeInformativo("La Nómina se ha creado correctamente ", 3000, 'success');
		}
		else{
			cargarMensajeInformativo("Ha ocurrido un error al crear la nómina", 3000, 'error');
		}
		ocultarModalNomina();
	});
}


function eliminarConfigNomina(nombre){
	var fnOK = function(){
		mostrarCargando("#eliminarFormato");
		url = url_del_config;
		var dataJSON = '{ "codigo_entidad":"'+$("#codigo_entidad").val()+'","nombre_config":"'+nombre+'"}';

		ejecutarAJAX(url, { input_json :  dataJSON }, function (data) {
			if(data.item.estado == true){
				cargarMensajeInformativo("La Nómina se ha eliminado correctamente ", 3000, 'success');
			}
			else{
				cargarMensajeInformativo("Ha ocurrido un error al eliminar la nómina", 3000, 'error');
			}
			buscarInformacion();
		});
	}
	
	agregarConfirm("¿Está Seguro que desea eliminar la configuraci&oacute;n de n&oacute;minas ["+nombre+"]?",fnOK,function(){});
}


function buscarEntidades(){
	if($("#col_nombre_entidad").val().length==0){
		alert("Debe ingresar un Nombre válido");
		return;
	}

	var opciones = document.getElementsByName("col_tipo_entidad");
	var par_tipo_entidad = "&tipo_entidad=";
	for(var i=0; i<opciones.length; i++){
		if(opciones[i].checked){
			par_tipo_entidad = par_tipo_entidad + opciones[i].value;
		}
	}

	url = url_buscar_entidades+$("#col_nombre_entidad").val() + par_tipo_entidad;
	$("#body_entidades .tr_registros").remove();
	$("#body_entidades .cargando").show();
	$("#body_entidades .sin_resultados").hide();
	ejecutarAJAX(url, null , function (data) {
		
		if(data.item.entidades != null ){
			for(var i=0; i<data.item.entidades.length;i++){
				var tr = '<tr class="tr_registros"><td>'+data.item.entidades[i].CODIGO_ENTIDAD+'</td>'+
							'<td>'+data.item.entidades[i].NOMBRE_ENTIDAD+'</td>'+	
							'<td><button type="button" class="btn btn-primary btn-sm" onclick="seleccionarEntidad(\''+data.item.entidades[i].CODIGO_ENTIDAD+'\')"><span class="glyphicon glyphicon-ok"></span> Seleccionar Entidad</button></td>'+
						'</tr>';
					$("#body_entidades").append(tr);
					$("#body_entidades .sin_resultados").hide();
			}
			
			$("#informacion_entidades").hide();
			if(data.item.entidades.length == 10){
				$("#informacion_entidades").show();
			}
	  	}
		else{
			$("#body_entidades .sin_resultados").show();	
		}
		
		$("#body_entidades .cargando").hide();
	});

}

$("#btn_add_formato").hide();
$("#btn_add_nomina").hide();
$("#btn_add_envio").hide();
$("#btn_generar_nomina").hide();
$("#btn_copy_formato").hide();
$("#btn_ver_nominas_periodo").hide();





$("#btn_ver_nominas_periodo").click(function() {
	location.href = "/NRP/verNominasPorEntidad.jsp?ce="+$("#codigo_entidad").val();
});

function getData(data, key){
	var valor = data[key];
	if(valor == null ){
		valor = data[key.toLowerCase()];
	}
	if(valor == null ){
		valor = data[key.toUpperCase()];
	}
	return valor;
}


function verEmpresasAsociadas(){
	$("#modalVerEmpresasAsociadas").modal();

	url = url_entidades_asociadas +$("#codigo_entidad").val();
	$("#body_empresas .tr_registros").remove();
	$("#body_empresas .sin_resultados").hide();
	$("#body_empresas .cargando").show();
	ejecutarAJAX(url, null , function (data) {
		if(data.item.entidades != null){
			for(var i=0; i<data.item.entidades.length;i++){
				var tr = '<tr class="tr_registros"><td>'+data.item.entidades[i].CODIGO_ENTIDAD+'</td>'+
							'<td>'+data.item.entidades[i].NOMBRE_ENTIDAD+'</td>'+	
						'</tr>';
					$("#body_empresas").append(tr);
					$("#body_empresas .sin_resultados").hide();
					$("#body_empresas .cargando").hide();
			}
		}
		else{
				$("#body_empresas .sin_resultados").show();
				$("#body_empresas .cargando").hide();
		}
	});


}
function ocultarModalEmpresasAsociadas(){
$("#modalVerEmpresasAsociadas").modal('toggle');
}

$("#btn_ver_empresas_asociadas").click(function() {
	verEmpresasAsociadas();
});

function validaSiEsHolding(){
	
	if($("#codigo_entidad").val().toLowerCase().startsWith("h")){
		$("#btn_ver_empresas_asociadas").show();
	}
	else{
		$("#btn_ver_empresas_asociadas").hide();
	}

}


function cargarHoldingConfigurado(holding){
	if(holding.trim().length == 0) return;
	$("#codigo_holding_asociado").html( holding );
	$("#codigo_empresa_buscada").html($("#codigo_entidad").val());
	$("#modalHoldingConfigurado").modal();

}

function ocultarModalHoldingConfigurado(){
	$("#modalHoldingConfigurado").modal('toggle');
}
function cargarConfiguracionHoldingAsociado(){
	$("#modalHoldingConfigurado").modal('toggle');
	$("#codigo_entidad").val($("#codigo_holding_asociado").html().trim());
	buscarInformacion();
}

function continuarConfigurandoEmpresa(){
	$("#modalHoldingConfigurado").modal('toggle');
}


if ( '<%=request.getParameter("ce")%>' != null && '<%=request.getParameter("ce")%>' != "null"){
	$("#codigo_entidad").val('<%=request.getParameter("ce")%>');
	setTimeout( function(){
		validaSiEsHolding();
		buscarInformacion();
	}, 1000);
}

function seleccionarEnvio(tipo){
	$(".envio").hide();
	$(".envio_"+tipo).show()
}

$("#menu_configurador").addClass("active");



function cargarValoresDatos(){
	$(".valor_dato").hide();
	if( $("#sel_tipo_dato").val() == "texto"){
		$("#texto_dato").val("");
		$("#texto_dato").show();
	}	
	else if( $("#sel_tipo_dato").val() == "columna_bd"){
		$("#sel_columnas_disponibles").show();
	}	
	else if( $("#sel_tipo_dato").val() == "variable_sistema"){
		$("#sel_variables_disponibles").show();
	}	
}


function refrescarDatosFTP(){
var validaciones = true;
if( !validacionesOBJ.validarIP($("#txt_envio_ftp_server").val())){
	validaciones = false;
	$("#info_txt_envio_ftp_server").html("&nbsp;Ip servidor no v&aacute;lida")
	
}
if( !validacionesOBJ.validarInt($("#txt_envio_ftp_puerto").val())){
	validaciones = false;
	$("#info_txt_envio_ftp_puerto").html("&nbsp;Puerto no v&aacute;lido.")
	
}
if( $("#txt_envio_ftp_usuario").val().trim().length <2){
	validaciones = false;
	$("#info_txt_envio_ftp_usuario").html("&nbsp;Puerto no v&aacute;lido.")
	
}
if( $("#txt_envio_ftp_password").val().trim().length <2){
	validaciones = false;
	$("#info_txt_envio_ftp_password").html("&nbsp;Contrase&ntilde;a no v&aacute;lido.")
	
}
if( !validacionesOBJ.validarDirectorio($("#txt_envio_ftp_directorio").val())){
	validaciones = false;
	$("#info_txt_envio_ftp_directorio").html("&nbsp;Directorio no v&aacute;lido.")
	
}

if(validaciones){

var data =	$("#txt_envio_ftp_server").val()+";"+
						$("#txt_envio_ftp_usuario").val()+";"+
						$("#txt_envio_ftp_password").val()+";"+
						$("#txt_envio_ftp_puerto").val()+";"+
						$("#txt_envio_ftp_directorio").val();

	$("#txt_envio_data_adicional").val(data);
}



}
function limpiarDatosFTP(){
	$("#txt_envio_ftp_server").val("");
	$("#txt_envio_ftp_puerto").val("");
	$("#txt_envio_ftp_usuario").val("");
	$("#txt_envio_ftp_password").val("");
	$("#txt_envio_ftp_directorio").val("");
	$("#txt_envio_data_adicional").val("");
}

function limpiaInfo(obj){
	$("#info_"+obj.id).html("");
}

cargarSelectGenerico("/NRP/request?formato_salida=json&id_req=obtenerVariablesSistema","sel_variables_disponibles");
cargarSelectGenerico("/NRP/request?formato_salida=json&id_req=obtenerColumnasBDDisponibles","sel_columnas_disponibles");
cargarSelectGenerico("/NRP/request?formato_salida=json&id_req=obtenerFormatosDisponibles","sel_copia_formatos_disponibles");


function enviarAhora(){

		mostrarCargando();

		setTimeout( function (){
			url = url_enviar_nominas;
			url = url + $("#codigo_entidad").val();
			$.getJSON( url, function( data ) {
				ocultarCargando();
				cargarMensajeInformativo("Proceso ejecutado ", 1000, '');
			});
		}, 1000);

}

//agregarConfirm("¿Está Seguro que desea eliminar?",function(){ alert("ok"); },function(){ alert("nook"); });

</script>
