<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>Procesos Fonasa</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type='text/javascript' src='/CotizacionesFonasaWEB/dwr/util.js'></script>
<script type='text/javascript' src='/CotizacionesFonasaWEB/dwr/interface/ProcesoFonasaDWR.js'></script>
<script type='text/javascript' src='/CotizacionesFonasaWEB/dwr/engine.js'></script>
<script type="text/javascript">



function inicioProcesoAutomatico(){
	
	
	
	    ProcesoFonasaDWR.iniciaProcesoAutomaticoDWR("");
		alert("Se ha dado inicio a proceso automatico");
		
}

function inicioProcesoFecha(){
	
	    var periodo= document.getElementById('periodo').value;
	
	    ProcesoFonasaDWR.ejecutaProcesoDWR(periodo);
		alert("Se ha dado inicio a proceso de periodo: "+periodo);
		
}

</script>
</head>
<body>
<table >
<tr> <td> Inicio Proceso Automatico</td><td><input type="button" onclick="inicioProcesoAutomatico()" value="Iniciar" /> </td></tr>
<tr> <td> Inicio Proceso Periodo</td><td><input type="text" id="periodo" value="102013"/></td><td><input type="button" onclick="inicioProcesoFecha()" value="Iniciar" /> </td></tr>
</table>

</body>
</html>