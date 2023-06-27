<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="stylesheet" href="theme/Master.css" type="text/css">
<title>AdminHomogacion</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

</body><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Homologación Archivo Dirección del Trabajo</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href="common/css/estilo_formularios.css" rel="stylesheet" type="text/css" />
		<link href="common/css/collapsible_menu.css" rel="stylesheet" type="text/css" />
		<link href="common/css/grid_960.css" rel="stylesheet" type="text/css" />
		
		<style type="text/css">
			#scrolling{
			height: 200px;
			overflow-y:scroll;}
		</style>
	
	</head>

<body leftmargin="3" topmargin="3" marginwidth="3" marginheight="3">

	<script language="javascript" src="Includes/FuncionesJava.js"></script>
	<script language="javascript" src="Includes/jquery.js"></script>
	<script language="javascript" src="Includes/jquery.numeric.js"></script>
	
	

	<script type="text/javascript">
	
	document.getElementById("error").value ="asdasd";
	
		function guardaCampo(id, tipo)
		{
			var gift = "load_"+id;
			var campoCaja = "campo_"+id+"_caja";
			var campoDt = "campo_"+id+"_dt";
			var campoDescripcion = "campo_"+id+"_des";
			
			//if (valorCaja != document.getElementById(campoCaja).value || valorDT != document.getElementById(campoDt).value || descripcion != document.getElementById(campoDescripcion).value) {
				//carga gif espera
				$.ajax({     	             
	            url: '/Publicacion/AdminHomologacion', 
	            dataType: 'json',
	            cache: false,
	            data: { id: id, accion: "actualizar",valorCaja:document.getElementById(campoCaja).value,valorDt:document.getElementById(campoDt).value,descripcion:document.getElementById(campoDescripcion).value,tipo:tipo},
	            beforeSend: function(){
			        $("#"+gift).css({'display':'block'});
			    },        
	            success: function(json){
	            	if ("" != json[0]) {
						document.getElementById(campoCaja).value = json[1]; 
						document.getElementById(campoDt).value = json[2];
						document.getElementById(campoDescripcion).value = json[3];
						alert(json[0]); 
					} else {
						document.getElementById(campoCaja).value = json[1]; 
						document.getElementById(campoDt).value = json[2];
						document.getElementById(campoDescripcion).value = json[3];
					}
					$("#"+gift).css({'display':'none'});
	            	
	            },
	            complete: function(){
			        $("#"+gift).css({'display':'none'});
			    }
				
				}); 
			//}
		}
		
		function cargaCampos(tipo)
		{
				$.ajax({     	             
	            url: '/Publicacion/AdminHomologacion', 
	            dataType: 'json',
	            cache: false,
	            data: { tipo: tipo, accion: "desplegar"},
	            beforeSend: function(){
			        $("#load").css({'display':'block'});
			    },              
	            success: function(json){
	            	if ("" != json[0]) {
						alert(json[0]); 
					} else {
						document.getElementById("Listado").innerHTML = json[1]; 
					}
					$("#load").css({'display':'none'});
	            }
	            
	        }); 
	    }
	</script>
		<FORM id="form1">
			<input type="hidden" value="Homologación Archivo Dirección del Trabajo" name="_folder">
			<input id="holdingA" type="hidden" name="holding" value="" />
			<font class="titulo">Homologación Archivo Dirección del Trabajo<span style="color:#ccc"></span></font>
				<br/>
				<br/>
				<font face="Verdana, Arial, Helvetica, sans-serif" style="font-size: 3pt;">&nbsp;<br></font>
				<table width="650" border="0" cellspacing="5" cellpadding="0" id="tabla_cont">
					<tr>
						<td width="18%" height="29" align="left" valign="middle">Tipo :</td>
						<td width="27%" align="left" valign="middle"><strong>	
								<select name="TipoProceso" size="1" id="TipoProceso" class="campos" onchange="cargaCampos(this.value)">
									<option value="">Seleccionar</option>
									<option value="MovimientoPersonal">Movimiento Personal</option>
									<option value="AFP">AFP</option>	   
									<option value="APV">APV</option>                          
									<option value="SALUD">Salud</option>   
									<option value="CCAF">CCAF</option>
									<option value="MUTUAL">MUTUAL</option>
									<option value="PAGADORESSUBSIDIO">Pagadores de Subsidio</option>                                            												
					  			</select>
							</strong>
						</td>
						<td align="left" valign="middle" nowrap="nowrap"><div id='load' style='display:none'><img src='Images/load.gif'/></div></td>
			        	<td align="left" valign="middle" nowrap="nowrap"></td>
				</tr>
			</table>
			<div id="Listado">
			</div>
		</FORM>
</body>
</html>

</html>