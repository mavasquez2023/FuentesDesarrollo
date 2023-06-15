<%@include file="header_session.jsp" %>
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
color:#005D8B;

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


</style>

</head>
<body class="corp">


<div id="content" class="container_12">
  <div class="grid_12">
  <img src="images/cabecera_claves.jpg" />
		<%@include file="menu.jsp" %>
    <h1> Procesos</h1>
    
	<div id="content_liv">
		

		<div id="main_content"> 

		</div>

	</div>


</div>
</div>



<script>

var url_obtener_procesos_no_invalidantes = "/NRP/request?formato_salida=json&id_req=obtenerProcesosNoInvalidantes&token="+mi_token+"&rand="+Math.random();


function mostrarCargando(){
	$(".loading").show();
	$(".content_titulo_proceso").hide();
}
function ocultarCargando(){
	$(".loading").hide();
	$(".content_titulo_proceso").show();
}


$("#menu_opciones").addClass("active");


setTimeout( function (){
		url = url_obtener_procesos_no_invalidantes;
		$.getJSON( url, function( data ) {
			if(data.item.procesos!= null && data.item.procesos.length > 0){
				for(var i=0; i< data.item.procesos.length; i++){
					var item = data.item.procesos[i];
					//$("#tabla_procesos").append("<tr><td>"+item.nombre+"</td><td align='center'><input type='checkbox' name='proceso_no_invalidantes' value='"+item.codigo+"' /></td><td align='center'>"+item.estado+"</td><td align='center'>"+item.fecha_inicio+"</td><td align='center'>"+item.fecha_termino+"</td></tr>");
					var id_tabla = "tabla_"+item.codigo;
					var tabla = "<table id='"+id_tabla+"' style='border: 1px solid #CCC;'><thead><tr style='font-weight:bold;font-size:14px'><td colspan='4'>Proceso "+item.nombre+"</td></tr></thead></table><br/><br/>";
					
					$("#main_content").append(tabla);
					if(item.historico != null && item.historico.length > 0){
						$("#"+id_tabla).append("<tr style='width:250px;border: 1px solid #CCC;background-color:#DDD' style='font-weight:bold;font-size:14px'><td align='center'>Estado</td><td align='center' style='width:250px'>Fecha Inicio</td><td align='center' style='width:250px'>Fecha T&eacute;mino</td></tr>");
						for(var j=0; j<item.historico.length; j++ ){
							var it = item.historico[j];
							$("#"+id_tabla).append("<tr><td align='center' style='width:250px'>"+it.estado+"</td><td align='center' style='width:250px'>"+it.fecha_inicio+"</td><td align='center' style='width:250px'>"+it.fecha_termino+"</td></tr>");
						}
					}
					else {
						$("#"+id_tabla).append("<tr style='border: 1px solid #CCC' style='font-weight:bold;font-size:14px'><td align='center' colspan='4' width='750px;'>No Existen procesos ejecutados</td></tr>");
					}
					
					
				}
			}

		});
	}, 1000);

</script>

</body>
</html>
