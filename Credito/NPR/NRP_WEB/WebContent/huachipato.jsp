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
					<h2>Mantenci&oacute;n de datos para Huachipato</h2>
				

					<div id="div_busqueda">
						<font>Filtrar Registro Por:</font>
						<select id="campo">
							<option value="EMPRUT">RUT EMPRESA</option>
							<option value="AFIRUT">RUT AFILIADO</option>
							<option value="FICHA">FICHA</option>
						</select>
						<br/><br/>
						<div class="form-group">
						  <input type="texto" class="form-control" id="data_to_search" placeholder="Ingrese Filtro" value="" />

							<button type="button" class="btn btn-success btn-sm" style="margin-top:10px" id="btn_buscar_info">
								<span class="glyphicon glyphicon-search"></span> Buscar Resultados
							</button>
						</div>

						
						<div class="container" style="width:800px">
						  <h4>Registros Huachipato</h4>
						  <table class="table table-bordered">
							<thead  class="header_tabla">
							  <tr>
								<th width="150px">Rut Empresa</th>
								<th width="150px">Rut Afiliado</th>
								<th width="150px">Ficha</th>
								<th width="100px">Rol</th>
								<th width="100px">Opciones</th>
							  </tr>
							</thead>
							<tbody id="body_registros">
							  
							</tbody>
						  </table>
						       
							<button type='button' class='btn btn-success btn-primary btn-xs'  onclick="showAgregar()">
								<span class='glyphicon glyphicon-pluss'></span> Agregar
							</button> 
						
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<div class="modal fade" id="modalEdicion" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title" id="titulo_data"></h4>
        </div>
        <div class="modal-body">
			<div id="add_or_edit" style="display:none">
				<div class="form-group">
					<label for="col_emprut">Rut Empresa</label>
					<input type="text" class="form-control" id="col_emprut" placeholder="Ingrese Rut Empresa" value="" >
					
					<label id="val_emprut" style="font-weight:normal; font-size:10px; padding-left:10px;padding-top:2px;color:red"></label>
				</div>
				
				<div class="form-group">
					<label for="col_afirut">Rut Afiliado</label>
					<input type="text" class="form-control" id="col_afirut" placeholder="Ingrese Rut Afiliado" value="" >
					<label id="val_afirut" style="font-weight:normal; font-size:10px; padding-left:10px;padding-top:2px;color:red"></label>
				</div>
				<div class="form-group">
					<label for="col_ficha">Ficha</label>
					<input type="text" class="form-control" id="col_ficha" placeholder="Ingrese Ficha" value="" >
					<label id="val_ficha" style="font-weight:normal; font-size:10px; padding-left:10px;padding-top:2px;color:red"></label>
				</div>
				<div class="form-group">
					<label for="col_rol">Rol</label>
					<select id="col_rol">
						<option value="A">A</option>
						<option value="C">C</option>
					</select>
				</div>
			</div>
			<div id="delete" style="display:none">
				¿Est&aacute; seguro que desea eliminar ?
			</div>
        </div>
        <div class="modal-footer">
			

          	<button type='button' class='btn btn-default btn-sm'  onclick="ocultarModalEditar()" id="btnCancelar">
				<span class='glyphicon glyphicon-back'></span> Cancelar
			</button> 
			

          	<button type='button' class='btn btn-success btn-primary'  onclick="guardar()" id="btnGuardar">
				<span class='glyphicon glyphicon-pencil'></span> Guardar
			</button> 
			

        </div>
      </div>
      
    </div>
  </div>


</body>
</html>


<script>
var MODO_ADD = 1;
var MODO_EDIT = 2;
var MODO_DEL = 3;
var modo = MODO_ADD;

$("#btn_buscar_info").click(function() {
  init();
});

setTimeout( function(){
	init();
}, 1000);
function init(){

	mostrarCargando("#Buscando Fichas")
	var dataJSON = '{ "campo":"'+$("#campo").find('option:selected').val()+'", "data_to_search":"'+$("#data_to_search").val()+'"}';

	ejecutarAJAX( "/NRP/request?formato_salida=json&id_req=getFichasHuachipato&token="+mi_token+"&rand="+Math.random(),{ data : dataJSON },  function( data ) {
	loadData(data.item.fichas)

	ocultarCargando("#Buscando Fichas")
	
	
});
}


function loadData(data){
	$("#body_registros").html('<tr class="sin_resultados"><td colspan="5" style="padding-left:20px;">Sin Resultados</td></tr>');
	if(data != null && data.length > 0){

		$("#body_registros").html('');
		for(var i=0; i< data.length; i++){
			var boton = '<button type="button" class="btn btn-warning btn-xs" onclick="showEditar(\''+(getData(data[i],'EMPRUT')+"-"+getData(data[i],'EMPRUTDV'))+'\',\''+(getData(data[i],'AFIRUT')+"-"+getData(data[i],'AFIRUTDV'))+'\',\''+getData(data[i],'FICHA')+'\',\''+getData(data[i],'ROL')+'\')" >'+
							'<span class="glyphicon glyphicon-pencil"> </span> Editar'+
						'</button> &nbsp;'+
						 '<button type="button" class="btn btn-danger btn-xs" onclick="showEliminar(\''+(getData(data[i],'EMPRUT')+"-"+getData(data[i],'EMPRUTDV'))+'\',\''+(getData(data[i],'AFIRUT')+"-"+getData(data[i],'AFIRUTDV'))+'\',\''+getData(data[i],'FICHA')+'\',\''+getData(data[i],'ROL')+'\')" >'+
							'<span class="glyphicon glyphicon-remove"> </span> Eliminar'+
						'</button> &nbsp;';
			var tr = 
					"<tr>"+
						"<td>"+getData(data[i],'EMPRUT')+"-"+getData(data[i],'EMPRUTDV')+"</td>"+
						"<td>"+getData(data[i],'AFIRUT')+"-"+getData(data[i],'AFIRUTDV')+"</td>"+
						"<td>"+getData(data[i],'FICHA')+"</td>"+
						"<td>"+getData(data[i],'ROL')+"</td>"+
						"<td>"+boton+"</td>"+
					"</tr>";
			
			$("#body_registros").append(tr);
		}
}
}

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

function showEditar(emprut,afirut, ficha, rol){
	modo = MODO_EDIT;
	$("#add_or_edit").hide();
	$("#delete").hide();
	$("#titulo_data").html("Editar Registro");
	$("#col_afirut").val(afirut);
	$("#col_afirut").prop("disabled",true)
	$("#col_emprut").val(emprut);
	$("#col_emprut").prop("disabled",true)
	$("#col_ficha").val(ficha);
	$("#col_rol").val(rol);
	$("#add_or_edit").show();
	
	$("#modalEdicion").modal();
}

function showAgregar(){
	modo = MODO_ADD;
	modoEdicion = false;
	$("#add_or_edit").hide();
	$("#delete").hide();
	$("#titulo_data").html("Agregar Registro");
	$("#col_afirut").val("");
	$("#col_afirut").prop("disabled",false)
	$("#col_emprut").val("");
	$("#col_emprut").prop("disabled",false)
	$("#col_ficha").val("");
	$("#add_or_edit").show();
	
	$("#modalEdicion").modal();
}

function showEliminar( emprut,afirut, ficha, rol){
	modo = MODO_DEL
	$("#add_or_edit").hide();
	$("#delete").hide();
	$("#titulo_data").html("Eliminar Registro");
	$("#col_afirut").val(afirut);
	$("#col_afirut").prop("disabled",true)
	$("#col_emprut").val(emprut);
	$("#col_emprut").prop("disabled",true)
	$("#col_ficha").val(ficha);
	$("#col_rol").val(rol);
	$("#delete").show();
	
	$("#modalEdicion").modal();
}

function validar(){
	console.log("validando data -> ******************** ")
	console.log(parseInt($("#col_ficha").val()) === 0)
	$("#val_emprut").html("");
	$("#val_afirut").html("");
	$("#val_ficha").html("");
	var result = true;
	if($("#col_emprut").val().trim().length == 0  || !validaRut($("#col_emprut").val())  ){
		$("#val_emprut").html("* Rut no válido");
		result = false;
		console.log("validación fallida emprut")
	}
	if($("#col_afirut").val().trim().length == 0 || !validaRut($("#col_afirut").val()) ){
		$("#val_afirut").html("* Rut no válido");
		result = false;
		console.log("validación fallida afirut")
	}
	if($("#col_ficha").val().trim().length == 0 || isNaN($("#col_ficha").val()) || parseInt($("#col_ficha").val()) === 0  ){
		console.log("valor ficha no valida...********************************************")
		$("#val_ficha").html("* Ficha no válida");
		$("#col_ficha").val("")
		result = false;
		console.log("validación fallida ficha")
	}
	
	return result;
}

function replace(texto, find, textoToReplace){
	if(texto.indexOf(find>=0)){
		texto = texto.substr(0,texto.indexOf(find))+textoToReplace+ texto.substr(texto.indexOf(find)+1);
	}
	return texto;
}

function replaceAll(texto,find,textoToReplace){
	while(texto.indexOf(find)>=0){
		texto = replace(texto,find,textoToReplace)
	}
	return texto;
}

function validaRut(rut){
  var sRut = replaceAll(rut,".","");
  
  var parteRut = sRut.split("-")[0];
  var parteDV = sRut.split("-")[1];
  console.log("rut-> "+ parteRut +" , dv-> "+ parteDV)
  if(parteDV.toUpperCase() == getDV(parteRut) +"".toUpperCase()){
	  console.log()
	  return true;
  }
  else{
	  console.log("return false")
	  return false;
  }

}

function getDV(rut) {
	console.log("validando rut:"+ rut)
    var sum, factor, i, rest, dv;

    sum = 0;
    factor  = 2;

    for (i = rut.length - 1; i >= 0; i--) {
        sum = sum + rut.charAt(i) * factor;
        
        if (factor == 7) {
            factor = 2;
        } else {
            factor++;
        }
    }

    rest = sum % 11;
  
    if (rest == 1) {
        dv = 'K';
    } else if (rest == 0) {
        dv = '0';
    } else {
        dv = (11 - rest) + "";
    }
    console.log("dv retornado ["+rut+"]->"+dv)
    return dv;
}

function ocultarModalEditar(){
	$("#modalEdicion").modal('toggle');
}

function guardar(){
	$("#btnGuardar").prop("disabled",true)
	$("#btnCancelar").prop("disabled",true)
	mostrarCargando("#Buscando Fichas")
	if(validar()){
		//$("#campo").find('option:selected').val()
		
		console.log("col_emprut->"+$("#col_emprut").val());
		console.log("col_afirut->"+$("#col_afirut").val());
		console.log("col_ficha->"+$("#col_ficha").val());
		
		var emprut =$("#col_emprut").val().trim().split("-")[0];
		var emprutdv = $("#col_emprut").val().trim().split("-")[1];
		var afirut = $("#col_afirut").val().trim().split("-")[0];
		var afirutdv = $("#col_afirut").val().trim().split("-")[1];
		var ficha = $("#col_ficha").val().trim();
		var rol = $("#col_rol").find('option:selected').val().trim();
		
		emprut = replaceAll(emprut, ".","")
		afirut = replaceAll(afirut, ".","")
		
		var dataJSON = '{ '+
							'  "emprut":"'+emprut+'",'+
							'  "emprutdv":"'+emprutdv+'",'+
							'  "afirut":"'+afirut+'",'+
							'  "afirutdv":"'+afirutdv+'", '+
							'  "ficha":"'+ficha+'", '+
							'  "rol":"'+rol+'", '+
							
					'}';
		console.log("dataJSON: "+ dataJSON)
		
		var msgOK = "El registro se ha creado correctamente ";
		var msgErr = "Ha ocurrido al agregar el registro";
		var action = "addFichaHuachipato";
		if(modo == MODO_EDIT){
			action = "editFichaHuachipato";
			msgOK = "El registro se ha actualizado correctamente ";
			msgErr = "Ha ocurrido al actualizar el registro";
		}
		else if(modo == MODO_DEL){
			action = "removeFichaHuachipato";
			msgOK = "El registro se ha eliminado correctamente ";
			msgErr = "Ha ocurrido al eliminar el registro";
		}
		
		ejecutarAJAX( "/NRP/request?formato_salida=json&id_req="+action+"&token="+mi_token+"&rand="+Math.random(),
				{ data : dataJSON },  
				function( data ) {
					
					if(data != null && data.item.status){
						cargarMensajeInformativo(msgOK, 1000, 'success');
					}
					else{
						cargarMensajeInformativo(msgErr, 1000, 'error');
					}
					
					
					ocultarCargando("#Buscando Fichas")
					$("#btnGuardar").prop("disabled",false)
					$("#btnCancelar").prop("disabled",false)
					
					$("#modalEdicion").modal('toggle');
					setTimeout(function(){init();},2000)
					
				});
		
		
	}
	else{
		ocultarCargando("#Buscando Fichas")
		$("#btnGuardar").prop("disabled",false)
		$("#btnCancelar").prop("disabled",false)
	}
}
</script>
