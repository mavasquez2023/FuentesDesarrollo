<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Parametros de Sistema</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/CotizacionesFonasaWEB/css/estilo.css" rel="stylesheet"
	type="text/css" />
<script type='text/javascript' src='/CotizacionesFonasaWEB/dwr/util.js'></script>
<script type='text/javascript' src='/CotizacionesFonasaWEB/dwr/interface/ParametrosDWR.js'></script>
<script type='text/javascript' src='/CotizacionesFonasaWEB/dwr/engine.js'></script>

<script type="text/javascript">
function validarEmail( email ) {
    expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if ( !expr.test(email) ) return false;
    else return true;
        
}
function volver()
{

var id='0124397104979702914097210947917049710947917907109470199127401243971049797029140972109479170497109479179071094701991274'
	    
	     var form = document.createElement("form");
	     var input = document.createElement("input");
	
	    form.action = "/CotizacionesFonasaWEB/bitacoraProcesos.jsp";
	    form.method = "post"
	
	    input.name = "id";
	    input.value = id;
	    input.type="hidden";
	    form.appendChild(input);
	
	    document.body.appendChild(form);
	    form.submit();


}

function guardaParametros()
{

    
    
             var correoAdmin =document.getElementById('correoAdmin').value;
             var correoUsuario = document.getElementById('correoUsuario').value ;
			 var directorioEntrada = document.getElementById('directorioEntrada').value;
			 var directorioSalida = document.getElementById('directorioSalida').value ;
			 var idParametro = document.getElementById('idparametro').value ;

                    
                    if( ! (validarEmail(correoAdmin)))
                    {
                      alert("Debe Ingresar Correo de Administrador Valido!");
                      return false;
                    
                    }
                    
                    if( ! (validarEmail(correoUsuario)))
                    {
                      alert("Debe Ingresar Correo de Usuario Valido!");
                      return false;
                    
                    }
                    
                    
                    
                    if(correoAdmin == "" || correoUsuario == "" || directorioEntrada =="" || directorioSalida =="")
                    {
                       alert("Todos los Datos son Obligatorios!");
                       return false;
                    }
                    
                    // ubicamos la ultima posicion de ruta de entrada
                    var largoLast = (directorioEntrada.length-1);
                    var lastEntrada = directorioEntrada.charAt(largoLast);
                    //ubicamos primera posicion
                    
                    var firstEntrada = directorioEntrada.charAt(0);
                    
                    if(!(firstEntrada == "/" && lastEntrada =="/"))
                    {
                    
                    alert("Debe Ingresar caracter / al prinicipio y final de ruta de Entrada!");
                    return false;
                    }  
                    
                    // ubicamos la ultima posicion de ruta de entrada
                    var largoLast2 = (directorioSalida.length-1);
                    var lastSalida = directorioSalida.charAt(largoLast2);
                    //ubicamos primera posicion
                    
                    var firstSalida = directorioSalida.charAt(0);
                    
                    if(!(firstSalida == "/" && lastSalida =="/"))
                    {
                    
                    alert("Debe Ingresar caracter / al prinicipio y final de ruta de Salida!");
                    return false;
                    }  
                    
                    
                    
                    
                    ParametrosDWR.verificaDirectorioDWR(directorioEntrada, function(data){
		
		             var resp = data;
		               if(data == 0){
		               alert("Directorio Entrada no Existe!");
		               flag=false;
		               return false;
		               
		               }else
		               {
		                  ParametrosDWR.verificaDirectorioDWR(directorioSalida, function(data){
		
				             var resp = data;
				               if(data == 0){
				               alert("Directorio Salida no Existe!");
				               return false;
		                    }else
		                    {
		                      	ParametrosDWR.guardaParametrosDWR(correoAdmin, correoUsuario,directorioEntrada,directorioSalida,idParametro, function(data){
			
					             var resp = data;
					               if(data == 1){
					               alert("Parametros Guardados Correctamente");
					               getParametros();
					               }else
					               {
					                alert("Parametros No fueron Guardados");
					               }
						
								});
		                    
		                    }
			
					       });
		               
		               }
			
					});

                    
					
                    
	             		
					
					

}

function getParametros(){

		ParametrosDWR.getParametrosDWR("", function(data){
		
			var parametros = null;
			parametros = data;
			var i;
			
			
			
			if(parametros.length > 0)
			{
			 var correoAdmin =parametros[0].correoAdmin;
			 var correoUsuario=parametros[0].correoUsuario;
			 var directorioEntrada=parametros[0].directorioEntrada;
			 var directorioSalida= parametros[0].directorioSalida;
			 var idParametro= parametros[0].idParametro;
			
			 document.getElementById('idparametro').value = idParametro;
			 document.getElementById('correoAdmin').value = correoAdmin;
			 document.getElementById('correoUsuario').value = correoUsuario;
			 document.getElementById('directorioEntrada').value = directorioEntrada;
			 document.getElementById('directorioSalida').value = directorioSalida;
			  
			   
			 
			
			}else document.getElementById('idparametro').value =-1;
			
			
		});
		
}

</script>

</head>
<body >
<div id="caja_registro">
		<table>	
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
	  </table>
<b style="font-family: sans-serif"> Parametros </b><br></br>
<input type="text" id="idparametro" style="visibility: hidden"/>
<table width="84%" height="40%" border="0" style="font-family: sans-serif">
<tr><td><b>Casilla Correo Admin * </b> </td><td> <input type="text" id="correoAdmin" size="30"/> </td></tr>
<tr><td><b>Casilla Correo Usuario *</b></td><td><input type="text" id="correoUsuario" size="30"/> </td></tr>
<tr><td> <b>Directorio Entrada * </b></td><td><input type="text" id="directorioEntrada" size="30"/> </td></tr>
<tr><td> <b>Directorio Salida *</b></td><td><input type="text" id="directorioSalida" size="30"/> </td></tr>
<tr><td> <b>* : Campos Obligatorios</b></td><td></td></tr>
<tr> <td align="right"><input type="button" class="btn_limp" onclick="guardaParametros();" value="Grabar" />     <input type="button" class="btn_limp" onclick="volver()" value="Volver" />  </td></tr>

<tr>

</tr>

</table>

</div>
</body>

<script type="text/javascript">
getParametros();
</script>

</html>