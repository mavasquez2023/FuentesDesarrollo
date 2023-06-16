//Abre una ventana de windows sin menú, con los parámetros definidos, y alto y ancho especificos
function openWindow(url, name,  ancho, alto) {
	var protocolo="http://";
	if(url.indexOf(protocolo)==-1){
		url= protocolo + url;
	}
	var opciones="toolbar=no,location=no, directories=no, status=yes, menubar=no, scrollbars=yes, resizable=yes, "
	+ "width=" + (ancho-10) + ", height=" + (alto-58) + ", "
	+ "top=" + (screen.availHeight - alto)/2  +", left=" + (screen.availWidth - ancho)/2;
	
	return window.open(url, name, opciones);
}

//Abre una ventana de windows al alto y ancho de la página, con los parámetrpos definidos 
function openFullWindow(url, name) {
	var protocolo="http://";
	if(url.indexOf(protocolo)==-1){
		url= protocolo + url;
	}
	return window.open(
            url,
            name,
            "top=0,left=0,resizable=yes,scrollbars=yes,status=yes,"
                    + "location=yes,toolbar=yes,"
                    + "width=" + screen.availWidth + ","
                    + "height=" + screen.availHeight);
}

//Valida si el DV del rut es correcto
//como parametro se debe ingrsar el valor del campo Rut con formato
//retorna true o false
function validaRut(rut){
  var sRut = quitaFormato(rut, '.-');
  var sDV = new String(sRut.charAt(sRut.length-1));
  sRut = sRut.substring(0, sRut.length-1);
  if(sDV.toUpperCase() == getDV(sRut)){
    return true;
  }
  else{
    return false;
  }
}

//Calcula el DV de un rut
//como parámetro se debe ingresar la parte numerica del rut
//retorna el DV del rut
function getDV(rut) {
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
    return dv;
}

//Permite formatear rut a medida que se ingresa, agregando los puntos y guión automáticamente
//como parámetro se debe ingresar el campo donde está el rut a formatear
//no retorna nada, automáticamente formatea campo ingresado como parámetro
function formateaRut(campoRut){
  var sRut = new String(campoRut.value);
  var sRutFormateado = '';
  sRut = quitaFormato(sRut, '.-');
  var sDV = sRut.charAt(sRut.length-1);
  sRut = sRut.substring(0, sRut.length-1);

  while( sRut.length > 3 ){
    sRutFormateado = "." + sRut.substr(sRut.length - 3) + sRutFormateado;
    sRut = sRut.substring(0, sRut.length - 3);
  }
  sRutFormateado = sRut + sRutFormateado;
  if(sRutFormateado != "") sRutFormateado += "-";
  sRutFormateado += sDV.toUpperCase();
  if(campoRut.value!=sRutFormateado) 
    campoRut.value = sRutFormateado;

}

//Permite formatear montos a medida que se ingresa, agregando los puntos  automáticamente
//como parámetro se debe ingresar el campo donde está el numero a formatear
//no retorna nada, automáticamente formatea campo ingresado como parámetro
function formateaNumber(campoMonto){
  var sMonto = new String(campoMonto.value);
  var sMontoFormateado = '';
  sMonto = quitaFormato(sMonto, '.');

  while( sMonto.length > 3 ){
    sMontoFormateado = "." + sMonto.substr(sMonto.length - 3) + sMontoFormateado;
    sMonto = sMonto.substring(0, sMonto.length - 3);
  }
  sMontoFormateado = sMonto + sMontoFormateado;
  if(campoMonto.value!=sMontoFormateado) 
    campoMonto.value = sMontoFormateado;

}
function getFormatNumber(campoMonto){
  var sMonto = new String(campoMonto);
  var sMontoFormateado = '';
  sMonto = quitaFormato(sMonto, '.');

  while( sMonto.length > 3 ){
    sMontoFormateado = "." + sMonto.substr(sMonto.length - 3) + sMontoFormateado;
    sMonto = sMonto.substring(0, sMonto.length - 3);
  }
  sMontoFormateado = sMonto + sMontoFormateado;
  return sMontoFormateado;

}

// Verifica si (s) es una direccion de correo valida: cuenta@subdominio.dominio
//como parámetro se debe ingeesar el valor del campo
//retorna true o false
function validaEmail(s)
{
    var i = 1;
    var sLength = s.length;
    while ((i < sLength) && (s.charAt(i) != "@"))
    { i++
    }

    if ((i >= sLength) || (s.charAt(i) != "@")) return false;
    else i += 2;

    while ((i < sLength) && (s.charAt(i) != "."))
    { i++
    }

    if ((i >= sLength - 1) || (s.charAt(i) != ".")) 
    	return false;
    else 
    	return true;
}

// Verifica si (s) es un telefono válido.
//como parámetro se debe ingeesar el valor del campo
//retorna true o false
function validaTelefono(fono){
    var phoneChars = "()-+ ";
    var fonosf = quitaFormato( fono, phoneChars );
    if(isNaN(fonosf)){
    	return false;
    }
    var pos= fono.indexOf('-');
    if (pos>-1){
    	cod= fono.substr(0, pos); 
    	fononumber= fono.substr(pos+1);
        if(cod.length!=2 && cod.length!=1){
    		return false;
    	}
    	if(cod.length==2 && (fononumber.length > 7 || fononumber.length < 6)){
    		return false;
    	}
    	if(cod=='2' && fononumber.length!=7){
    		return false;
    	}
    	if(cod=='09' && fononumber.length!=8){
    		return false;
    	}
    }else{
    	if(fonosf.length<6 || fonosf.length>10){
    		return false;
    	}
    }
}

// Quita todos los caracteres que que estan en "bag" del string "s".
//como primer parámetro se debe ingresar el valor del campo y como segundo parámetro los caracteres a eliminar
//retorna string numérico sin formato
function quitaFormato (s, bag){   
    var returnString = "";

    // Buscar por el string, si el caracter no esta en "bag", 
    // agregarlo a returnString
    for (var i = 0; i < s.length; i++)
    {   var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }

    return returnString;
}

//valida si tecla presionada es número
function isKeyNum(){
	if (event.keyCode >= 48 && event.keyCode <= 57) return true;
}
//valida si tecla presionada es texto entre (A y Z) o (a y Z)
function isKeyText(){
	if((event.keyCode > 64 && event.keyCode<91) || (event.keyCode >96 && event.keyCode < 123)) return true;
}

//permite key pressed de rut, vale decir: números, k  K.
function keyRut(){
	//guion= event.keyCode!= 45
	if ( !isKeyNum() && (event.keyCode!= 75 && event.keyCode!=107 && event.keyCode!= 45)) event.returnValue = false;
}
//permite key presses de solo números.
function keyNum(){
	if (!isKeyNum()) event.returnValue = false;
}
//permite key pressed de abcedario, punto y espacio.
function keyText(){
	if (!isKeyText() && ( event.keyCode!= 46 && event.keyCode!= 32) ) event.returnValue = false;
}
//permite key pressed de telefono, vale decir: números, +  - ()
function keyFono(){
	if (!isKeyNum() && ( event.keyCode!= 40 && event.keyCode!= 41 && event.keyCode!= 43&& event.keyCode!= 45)) event.returnValue = false;
}
//permite key pressed de fecha, vale decir: números, slash y guión
function keyFecha(){
	if (!isKeyNum()  && ( event.keyCode!= 45 && event.keyCode!= 47)) event.returnValue = false;
}
//permite key pressed de email, numeros, texto y @ _ .
function keyEmail(){
	if (!isKeyNum() && !isKeyText() && (event.keyCode!= 46 && event.keyCode!= 64 && event.keyCode!= 95)) event.returnValue = false;
}

//saca los espacios al inicio y al final, e incluso los espacios repetidos entre medio dejando uno solo.
//como parametro se debe ingresar el valor del campo
//como salida retorna el texto limpiado
function trim(texto){
  i=0;
  anterior=" ";
  var textoout="";
  while (i<=texto.length) { 
    caracter= texto.charAt(i);
    if (!(caracter == anterior && caracter==" ")){
	textoout= textoout.concat(caracter)
    }
    i=i +1;
    anterior=caracter;
  }
  if (textoout.charAt(textoout.length-1)==" "){
    textoout= textoout.substring(0, textoout.length - 1);
  }
return textoout;
}

//verifica si (y) es año bisiesto
function isBisiesto(y){
	var r = (y/4) - parseInt(y/4);
	if(r > 0){
		return false;
	}
	return true;
}

// Verifica si (datObj) es una fecha valida: dd-mm-aaaa o dd/mm/aaaa
function validaFecha(fecha){
	var diasmes = new Array('31','29','31','30','31','30','31','31','30','31','30','31');
	var doc = document.forms[0];
	
	  if(fecha.length != 10){
		return false;
	}
	var dd = fecha.substr(0, 2);
	var mm = fecha.substr(3, 2);
	var aa = fecha.substr(6, 4);
	if (isNaN(aa) || aa < 1900 || aa > 9999){
		return false;
	}
	if(isNaN(mm)  || mm < 1 || mm > 12){
		return false;
	}
	if(mm == 2){
		if((isBisiesto(aa) && dd > 29) || (!isBisiesto(aa) && dd > 28)){
			return false;
		}
	}
	if(isNaN(dd) || dd < 1 || dd>diasmes[mm-1]){
		return false;
	}
	
	return true;

}
//Permite formatear fecha a medida que se ingresa, agregando los "/"
//como parámetro se debe ingresar el campo donde está la fecha a formatear
//no retorna nada, automáticamente formatea campo ingresado como parámetro
function formateaFecha(campoFecha){
  var vFecha = new String(campoFecha.value);
  var sFechaFormateado = '';
  sFecha = quitaFormato(vFecha, '/-');
  //var sDV = sFecha.charAt(sFecha.length-1);
  //sFecha = sFecha.substring(0, sFecha.length-1);

  while( sFecha.length >= 2 && vFecha.length<6){
    sFechaFormateado += sFecha.substr(0, 2) + "/" ;
    sFecha = sFecha.substring(2);
  }
   sFechaFormateado = sFechaFormateado + sFecha ;
   if(vFecha.length>=6){
   	sFechaFormateado= vFecha;
   }
   campoFecha.value = sFechaFormateado;
}

function validaEnter(){
	if(window.event.keyCode==13) {
		window.event.keyCode = 0;
		return false;
	}
}

function textSelectedIndex(campo){
	var lista= campo.options;
	texto	 = lista[lista.selectedIndex].text;
	return texto;
}

//Dado un checkbox "Seleccionar Todos" permite seleccionar o deseleccionar 
//todos los checkbox de una lista que tiene el mismo nombre que parámetro 'campo'
function selectAll(campo, estado){
if(campo== null) return;
var nroseleccionadas= campo.length;
	if (isNaN(nroseleccionadas)){
		campo.checked= estado;
	}
	else{
		for (var i = 0; i < nroseleccionadas; i++) {
			campo[i].checked= estado;
		}
	}
}

//Si un elemento de la lista de checkbox es deseleccionada también lo hace 
//el checkbox principal "Seleccionar Todos".
function unSelect(campoTodas, estado){
	if (!estado){
		campoTodas.checked= false;
	}
}

//Retorna el número de checkbox seleccionados de una lista
function numSelected(campo){
	var sels=0;
	nroseleccionadas= campo.length;
	if (isNaN(nroseleccionadas)){
		if(campo.checked== true){
			sels++;
		}
	}else{
		for (var i = 0; i < nroseleccionadas; i++) {
			if (campo[i].checked==true)
				sels++;
		}
	}
	return sels;
}

function disabledField(campo, estado){
	campo.disabled= estado;
}

function hideField(campo, estado){
	if (estado== true){
		campo.style.visibility= 'hidden';
	}else if (estado== false){
		campo.style.visibility= 'visible';
	}
}