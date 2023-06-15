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
<title>Gestor de NRP - La Araucana</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">

<script src="bootstrap-3.3.7-dist/js/jquery.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/comun.js?<%=Math.random()%>"></script>
<style>

#content_liv{
width:800px;
height:600px;
background-color:#FFF;
}

.menu{
width:300px;
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

.menu_proceso{
padding:2px;
border: 1px solid #DDD;
margin:2px;
font-size:14px;
padding-bottom:10px;
padding-top: 5px;
padding-left:8px;
}

.opciones_proceso{
padding-top:10px;
padding-left:10px;
}

.estado_menu{
font-size:13px;
height:20px;
}

.hide{
display:none;
}

.cursor{
cursor:hand;
cursor:pointer;
}
.estado_actual{
font-weight:bold;
color:#00F;
font-size:12px;
}


.content_titulo_proceso{
height:40px;
color:#005D8B;
font-weight:bold;
}
.content_titulo_etapa_proceso{
height:30px;
}
.titulo_detalle_proceso{
color:#005D8B;
font-weight:bold;
}
.data_detalle_proceso{
color:#333;

}
.clase_bloqueo{
color:#F00;
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

.data_detalle_proceso{
font-size:12px;
}
</style>

</head>
<body class="corp">


<div id="content" class="container_12">
  <div class="grid_12">
  <img src="images/cabecera_claves.jpg" />
	
    <h1>Recuperaci&oacute;n de Password</h1>
    
	<div id="content_liv">
		
	
	

		<div id="main_content"> 
			
	
<form id="formulario" class="form-horizontal" action="RecuperarPasswordServlet">
  <div class="form-group">
    <label for="usuario" class="col-sm-2 control-label">E-Mail &nbsp;</label>
    <div class="input-group col-sm-5">
    	<span class="input-group-addon">@</span>
    	<input type="text" class="form-control" id="codigo" name="codigo" aria-describedby="inputGroupSuccess4Status" placeholder="Ingrese su Correo Electr&oacute;nico">
  	</div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
		<button type="button" class="btn btn-default btn-sm" onclick="volver()">
          <span class="glyphicon glyphicon-chevron-left"></span> Volver
        </button>
		<button type="button" class="btn btn-success btn-sm" onclick="recuperar()">
          <span class="glyphicon glyphicon-share"></span> Recuperar
        </button>
    </div>
  </div>
</form>

		</div>

	</div>


</div>
</div>



<script>

function recuperar(){

if($("#codigo").val().length <= 3 ){
	alert("Ingrese un usuario válido")
	return;
}


document.getElementById("formulario").submit();

}

function volver(){
	location.href="login.jsp";
}

var error = '<%=request.getParameter("e")%>';
if(error == "ti"){
	cargarMensajeInformativo("Sesi&oacute;n inv&aacute;lida.",2000,"danger");
}
else if(error == "le"){
	cargarMensajeInformativo("Autenticaci&oacute;n Incorrecta.",2000,"danger");
}

limpiar();
</script>

</body>
</html>
