var nombre_dia = new Array("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado") 
var nombre_mes = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre") 
var de = " de "; 

var fechita = fechactual;
var hoy_es = new Date(fechita); 
dia_mes = hoy_es.getDate(); 
dia_semana = hoy_es.getDay(); 
mes = hoy_es.getMonth() + 1; 
anyo = hoy_es.getYear(); 

if (anyo < 100) anyo = '19' + anyo 
else if ( (anyo>100) && (anyo<999) ) {var cadena_anyo=new String(anyo); anyo='20' + cadena_anyo.substring(1,3) } 
document.write(nombre_dia[dia_semana] + ", " + dia_mes + de + nombre_mes[mes - 1] + " de " + anyo) 