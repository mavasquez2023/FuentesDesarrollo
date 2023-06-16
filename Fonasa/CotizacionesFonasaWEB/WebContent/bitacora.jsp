<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<html>
<head>
<title>Bitacora Procesos</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/CotizacionesFonasaWEB/css/estilo.css" rel="stylesheet"
	type="text/css" />
<script type='text/javascript' src='/CotizacionesFonasaWEB/dwr/util.js'></script>
<script type='text/javascript' src='/CotizacionesFonasaWEB/dwr/interface/AdminProcesosDWR.js'></script>
<script type='text/javascript' src='/CotizacionesFonasaWEB/dwr/interface/ProcesoFonasaDWR.js'></script>
<script type='text/javascript' src='/CotizacionesFonasaWEB/dwr/engine.js'></script>
<script type="text/javascript">



function cargaAnos()
{


   AdminProcesosDWR.cargaAnosDWR("", function(data){
		var anos = null;
			anos = data;
			
			 var header = "<b>Años</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <select id='ano'>";
    		 var footer="</select>";
   			 var content = "<option value='0'>Seleccionar</option>";
			
			if(anos != null)
			{
			  
			 
			    for(var i=0;i < anos.length ; i++)
			    {
			        content = content + "<option value='"+anos[i].valorStr+"'>"+anos[i].valorStr+"</option>";
			        
			    }
		       
			
			}else{
			   alert("No hay periodos disponibles para consultar!");
			   
			}
			
			document.getElementById('comboAnos').innerHTML = header +  content + footer;
		});

}



function reprocesa(periodo)
{
        
        ProcesoFonasaDWR.consultaProcesoDWR(periodo, function(data){
		
			var logs = null;
			logs = data;
			var i;
			
			
			if(logs == 0)
			{
			
			  ProcesoFonasaDWR.reprocesaProcesoDWR(periodo);
		      alert("Se reprocesara Periodo "+periodo);
		      
		      consultaLogAno();
				
			
			}else
			    alert("No se puede reprocesar debido a que existe un proceso que se esta Procesando!");
		});
        
        
        
        

       

}



function cargaCombos()
{

    var header = "<b>Años</b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <select id='ano'>";
    var footer="</select>";
    var content = "<option value='0'>Seleccionar</option>";
    

    document.getElementById('comboAnos').innerHTML = header +  content + footer;
 
}

function consultaLogAno()
{

var ano = document.getElementById('ano').value;



var header="<table width='84%' height='60%' border='1'>"+
           "<tr><td align='left' colspan='7'><b> Resultados </b></td></tr>"+
           "<tr><td> <b> Fecha Inicio Proceso </b></td> <td><b>Fecha Termino Proceso</b></td> <td><b>Periodo</b> </td> <td><b>Archivo Entrada</b></td><td><b>Estado </b></td><td><b>Archivo Salida</b> </td> </tr>";

var footer="</table>";
var content="";

     if(ano == 0){
     alert("Debe Seleccionar Año");
     return false;
     }else{


   AdminProcesosDWR.consultaLogAnoDWR(ano, function(data){
		
			var logs = null;
			logs = data;
			var i;
			
			if(logs.length > 0)
			{
			
				for (i = 0; i < logs.length; i++){
				
				 
				 var estado = logs[i].estado;
				 var state ="";
				 var flecha="&nbsp;";
				 var archivoSalidaImg="&nbsp;";
				 var archivoEntrada="&nbsp;";
				 var archivoSalida="&nbsp;";
				 var fechaTerminoProceso="&nbsp;";
				 
				 
				 fechaTerminoProceso = logs[i].fechaTerminoProceso;
				 
				 if(estado == 1){
				 	state="OK";
				 	archivoSalidaImg = "<img src='img/ok-file.png' />";
				 	flecha = "<a href='javascript:reprocesa(\""+logs[i].periodo+"\")' > <img src='img/arrow.png'  /></a>";
				 }else{
				 	
				 	if(estado == 0 || estado ==4)
				 	{
				 	state="ERROR";
				    archivoSalidaImg = "<img src='img/error-file.png' />";
				    flecha = "<a href='javascript:reprocesa(\""+logs[i].periodo+"\")' > <img src='img/arrow.png'  /></a>";
				    }
				    else
				    if(estado ==2)
				    {
				      state="PROCESANDO";
				      archivoSalidaImg = "&nbsp;";
				      fechaTerminoProceso ="&nbsp;";
				    }
				    
				    
				 }
				 
				 //denota cualquier otro estado, 0=error archivo entrada,1=OK,2=proceso
				 if(estado==4){
				     archivoEntrada= logs[i].archivoEntrada;
				     archivoSalida= logs[i].archivoSalida;
				 }else{
				     archivoEntrada ="<a href='download?id="+ logs[i].archivoEntrada + "'>"+logs[i].nombreArchivoEntrada+"</a>";
				     archivoSalida= "<a href='download?id="+logs[i].archivoSalida+"'>"+archivoSalidaImg+"</a>";
				  }
				 
				  content = content + "<tr><td>" + logs[i].fechaInicioProceso +"</td><td>"+fechaTerminoProceso + "</td><td>"+
				            logs[i].periodoPrint + "</td><td>"+archivoEntrada+"</td><td>"+state+"</td><td>"+archivoSalida+flecha+"</td></tr>" ;
				  
				 
				}
				
				document.getElementById('resultado').innerHTML = header + content + footer;
			
			}else
			    alert("No se encontraron resultados para el año consultado!");
		});

      }

}

</script>


</head>
<body>
	<div id="caja_registro">
		<table>	
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
		<tr><td>&nbsp;</td></tr>
	  </table>

<table width="85%"  style="font-family: sans-serif">
<tr><td align="right"> <a href="http://146.83.1.144:9080/SivegamWEB/pages/menuFonasaSivegam.jsp" > <b>Volver</b>&nbsp; &nbsp; </a><a href="parametros.jsp" > <b>Parametros</b> </a></td> </tr>

</table>
  
<table width="85%" height="40%" border="0" style="font-family: sans-serif">
<tr><td align="left"> <b> Bitacora de Procesos </b> </td>
</tr>
<tr><td align="center"><div id="comboAnos" > </div></td> </tr>
<tr> <td align="center"><input type="button" onclick='consultaLogAno();' id="boton"  class="btn_limp" value="Buscar" />  </td></tr>

<tr>

</tr>

</table>

<div id='resultado' style="font-family: sans-serif"> </div>
</div>
</body>
<script type="text/javascript">

cargaAnos();
</script>
</html>