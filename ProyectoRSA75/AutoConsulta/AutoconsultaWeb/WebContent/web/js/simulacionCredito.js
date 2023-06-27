/*	
var fechaHoy = new Date();
var dia=LZ(fechaHoy.getDate());
var mes= LZ(fechaHoy.getMonth()+1);
var agno= fechaHoy.getFullYear();

current_day=new Date(agno,1,dia);
var dia= current_day.getDate();
var fecha=  mes + "-" +  dia + "-" + agno;
cal1.addDisabledDates(fecha,null); 
*/
var nav4 = window.Event ? true : false;

function acceptNum(evt){	
	// NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57	
	var key = nav4 ? evt.which : evt.keyCode;	
	return (key <= 13 || (key >= 48 && key <= 57));
}
function seleccion(opcion){
	if(validaForm(document.PARAMForm)){
		document.PARAMForm.campo.value = opcion;
		document.PARAMForm.submit();
 	}
}

function formatNumber(obj)
{ 
var texto=obj.value;
 var tmpstr = "";
 var tmpstr2 = "";
 for (i=0; i < texto.length ; i++ )
	if ( texto.charAt(i) != ' ' && texto.charAt(i) != '.' && texto.charAt(i)!= '-' )
	   tmpstr = tmpstr + texto.charAt(i);
	
	texto = tmpstr;
	largo = texto.length;

  	var invertido = "";
  	for ( i=(largo-1),j=0; i>=0; i--,j++ )
    	invertido = invertido + texto.charAt(i);

  	var dtexto = "";
  	cnt = 0;

  for ( i=0,j=2; i<largo; i++,j++ )  {
    if ( cnt == 3 ) {
      dtexto = dtexto + '.';
      j++;
      dtexto = dtexto + invertido.charAt(i);
      cnt = 1;
    }
    else { 
      dtexto = dtexto + invertido.charAt(i);
      cnt++;
    }
  }

  invertido = "";
  for ( i=(dtexto.length-1),j=0; i>=0; i--,j++ )
    invertido = invertido + dtexto.charAt(i);
	obj.value=invertido

}
function validarFecha(obj,InputName){

	var Fecha= new String(obj.value)	// Crea un string
	var RealFecha = new Date()	// Para sacar la fecha de hoy
	// Cadena Año
	var Ano= new String(Fecha.substring(4,9))
	// Cadena Mes
	var Mes= new String(Fecha.substring(2,4))
	// Cadena Día
	var Dia= new String(Fecha.substring(0,2))
	// Valido el año
	
	if (isNaN(Ano) || Ano.length<4 || parseFloat(Ano)<1900){
       	alert('La fecha ingresada es inválida.')
		obj.value="";
		obj.focus();
		obj.select();
		return false
	}
	// Valido el Mes
	if (isNaN(Mes) || parseFloat(Mes)<1 || parseFloat(Mes)>12){
		alert('La fecha ingresada es inválida.')
		obj.value="";
		obj.focus();
		obj.select();		
		return false
	}
	// Valido el Dia
	if (isNaN(Dia) || parseFloat(Dia)<1 || parseFloat(Dia)>31){
		alert('La fecha ingresada es inválida.')
		obj.value="";
		obj.focus();
		obj.select();		
		return false
	}
	
	if (Mes==4 || Mes==6 || Mes==9 || Mes==11 || Mes==2) {
		if (Mes==2 && Dia > 28 || Dia>30) {
			alert('La fecha ingresada es inválida.')
			obj.value="";
			obj.focus();
			obj.select();
			return false
		}
	}

	// Cadena Año
	var AnoReal= RealFecha.getYear();
	// Cadena Mes
	var MesReal= RealFecha.getMonth()+1;
	// Cadena Día
	var DiaReal= RealFecha.getDate();

	if (AnoReal<Ano) {
		alert('Fecha Fuera de Rango.')
		obj.value="";
		obj.focus();
		obj.select();		
		return false
	}
	if( (AnoReal==Ano) && (MesReal<Mes) ){
		alert('La fecha ingresada es inválida.')
		obj.value="";
		obj.focus();	
		obj.select();	
		return false
	}
	if((AnoReal==parseFloat(Ano)) && (MesReal==parseFloat(Mes)) && (DiaReal<parseFloat(Dia)) ){
		alert('La fecha ingresada es inválida.')
		obj.value="";
		obj.focus();
		obj.select();		
		return false
	}
	obj.value=Dia + "-" + Mes+ "-" + Ano;
  	return true;	
}

function validarFechaCalendario(obj){
	if(!validarFecha(obj)){
			return;
	}
}