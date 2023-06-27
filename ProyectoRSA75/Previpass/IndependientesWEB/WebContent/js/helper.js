//--- Constructores de Objetos ---
function ObjAgrupacion (codigo, glosa, rut, dv)
{
	this.codigo = codigo;
	this.glosa = glosa;
	this.rut = rut;
	this.dv = dv;
}

function ObjParametro (codigo, glosa)
{
	this.codigo = codigo;
	this.glosa = glosa;
}

//--- Manejo de Cadenas ---
function lTrim(sStr)
{
	if((sStr.length) == 0)
		return sStr;

	while (sStr.charAt(0) == " ") 
      	sStr = sStr.substr(1, sStr.length - 1); 
    
    return sStr; 
} 

function rTrim(sStr)
{ 
	if((sStr.length) == 0)
		return sStr;

	while (sStr.charAt(sStr.length - 1) == " ") 
    	sStr = sStr.substr(0, sStr.length - 1); 
    
    return sStr;
} 

function Trim(sStr)
{ 
	if((sStr.length) == 0)
		return sStr;

	return rTrim(lTrim(sStr)); 
}

/*function Upper(campo,tipo)
{
	if (tipo=="L") // Letras + (/ y &) - Quitados 38 y 47
  	{
    	if(( event.keyCode >= 97 && event.keyCode <= 122)||(event.keyCode >=65 && event.keyCode <=90)||
        	(event.keyCode == 32) ||(event.keyCode == 46)||
         	(event.keyCode == 209)||(event.keyCode == 241)||(event.keyCode == 164)||(event.keyCode == 165))
    	{
     		campo.value=campo.value.toUpperCase();
    	}
    	else event.keyCode=0;
  	}
 	if (tipo=="N") // Numero
 	{ 
  		if(( event.keyCode < 48 || event.keyCode > 57))
  		{
   			event.keyCode=0;
  		}
	}
 	if (tipo=="A") // Ambos (Numeros y Letras) - Quitados 38 y 47
  	{
    	if(( event.keyCode >= 97 && event.keyCode <= 122)||(event.keyCode >=65 && event.keyCode <=90)||
        	(event.keyCode == 32)||( event.keyCode >= 48 && event.keyCode <= 57)||
        	(event.keyCode == 209)||(event.keyCode == 241)||(event.keyCode == 164)||(event.keyCode == 165)||(event.keyCode == 45))
      
    	{
     		campo.value=campo.value.toUpperCase()
    	}
    	else event.keyCode=0;
  	}
 	if (tipo=="T") // Todos los caracteres menos #,: y ?. 
  	{
    	if(( event.keyCode == 35)||(event.keyCode ==58)||(event.keyCode == 63)||
         	(event.keyCode == 209)||(event.keyCode == 241)||(event.keyCode == 164)||(event.keyCode == 165))
    	{
    		event.keyCode=0;
    	}
  	}
  	if (tipo=="M") // Para Correo Electronico - Agregados _ (95), @ (64), .(46)
  	{
    	if(( event.keyCode >= 97 && event.keyCode <= 122)||(event.keyCode >=65 && event.keyCode <=90)||
        	(event.keyCode == 32)||( event.keyCode >= 48 && event.keyCode <= 57)||
        	(event.keyCode == 209)||(event.keyCode == 241)||(event.keyCode == 164)||(event.keyCode == 165)||(event.keyCode == 45)
        	||(event.keyCode == 95) ||(event.keyCode == 64) ||(event.keyCode == 46))
    	{
     		campo.value=campo.value.toUpperCase()
    	}
    	else event.keyCode=0;
  	}
  	if (tipo=="D") // Para Digito verificador (Numero + K)
  	{
    	if(( event.keyCode >= 48 && event.keyCode <= 57) || event.keyCode == 107 || event.keyCode == 75)
  		{
     		campo.value=campo.value.toUpperCase()
    	}
    	else event.keyCode=0;
  	}
}*/

function Upper(campo,tipo)
{
	if (tipo=="L") // Letras + (/ y &) - Quitados 38 y 47
  	{
    	if(( event.keyCode >= 97 && event.keyCode <= 122)||(event.keyCode >=65 && event.keyCode <=90)||
        	(event.keyCode == 32) ||(event.keyCode == 46)||
         	(event.keyCode == 209)||(event.keyCode == 241)||(event.keyCode == 164)||(event.keyCode == 165))
    	{
     		setTimeout(function() {forceupper(campo);},50);
    	}
    	else event.keyCode=0;
  	}
 	if (tipo=="N") // Numero
 	{
 		if(( event.keyCode < 48 || event.keyCode > 57))
  		{
  			event.keyCode=0;
  		}
	}
 	if (tipo=="A") // Ambos (Numeros y Letras) - Quitados 38 y 47
  	{
    	if(( event.keyCode >= 97 && event.keyCode <= 122)||(event.keyCode >=65 && event.keyCode <=90)||
        	(event.keyCode == 32)||( event.keyCode >= 48 && event.keyCode <= 57)||
        	(event.keyCode == 209)||(event.keyCode == 241)||(event.keyCode == 164)||(event.keyCode == 165)||(event.keyCode == 45))
      
    	{
     		setTimeout(function() {forceupper(campo);},50);
    	}
    	else event.keyCode=0;
  	}
 	if (tipo=="T") // Todos los caracteres menos #,: y ?. 
  	{
    	if(( event.keyCode == 35)||(event.keyCode ==58)||(event.keyCode == 63)||
         	(event.keyCode == 209)||(event.keyCode == 241)||(event.keyCode == 164)||(event.keyCode == 165))
    	{
    		event.keyCode=0;
    	}
    	setTimeout(function() {forceupper(campo);},50);
  	}
  	if (tipo=="M") // Para Correo Electronico - Agregados _ (95), @ (64), .(46) --- Quitado el " " (32)
  	{
    	if(( event.keyCode >= 97 && event.keyCode <= 122)||(event.keyCode >=65 && event.keyCode <=90)||
        	( event.keyCode >= 48 && event.keyCode <= 57)||
        	(event.keyCode == 209)||(event.keyCode == 241)||(event.keyCode == 164)||(event.keyCode == 165)||(event.keyCode == 45)
        	||(event.keyCode == 95) ||(event.keyCode == 64) ||(event.keyCode == 46))
    	{
     		setTimeout(function() {forceupper(campo);},50);
    	}else event.keyCode=0;
  	}
  	if (tipo=="D") // Para Digito verificador (Numero + K)
  	{
    	if(( event.keyCode >= 48 && event.keyCode <= 57) || event.keyCode == 107 || event.keyCode == 75)
  		{
     		setTimeout(function() {forceupper(campo);},50);
    	}
    	else event.keyCode=0;
  	}
  	
  	if (tipo=="TG") // Creado para Mantenedores, ocupado en glosa de Ingreso de Tablas globales
  	{
    	if(( event.keyCode >= 97 && event.keyCode <= 122)||(event.keyCode >=64 && event.keyCode <=90)||
        	(event.keyCode == 32) ||(event.keyCode >= 45 && event.keyCode <= 58) ||
         	(event.keyCode == 209)||(event.keyCode == 241)||(event.keyCode == 164)||(event.keyCode == 165))
    	{
     		setTimeout(function() {forceupper(campo);},50);
    	}
    	else event.keyCode=0;
  	}
}

function forceupper(o)
{
	var x = getCaretPosition(o);
	o.value=o.value.toUpperCase();
    setCaretPosition(o,x);
}

function getCaretPosition(ctrl) {
    var CaretPos = 0;    // IE Support
    if (document.selection) {
        ctrl.focus();
        var Sel = document.selection.createRange();
        Sel.moveStart('character', -ctrl.value.length);
        CaretPos = Sel.text.length;
    }
    // Firefox support
    else if (ctrl.selectionStart || ctrl.selectionStart == '0') {
        CaretPos = ctrl.selectionStart;
    }

    return CaretPos;
}

function setCaretPosition(ctrl, pos) {
    if (ctrl.setSelectionRange) {
        ctrl.focus();
        ctrl.setSelectionRange(pos,pos);
    }
    else if (ctrl.createTextRange) {
        var range = ctrl.createTextRange();
        range.collapse(true);
        range.moveEnd('character', pos);
        range.moveStart('character', pos);
        range.select();
    }
}

function validarEmail(valor) {

	if (Trim(valor) != "")
	{
	  	//RAC 19593 JLGN  02-04-2013
	  	if(/^\w+([\.-]?\w+[\.-]*)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(valor)){
	   		return true;
	  	} else {
	  		alert("La dirección de email '" + valor + "' es incorrecta.");
	  		return false;
	  	}
  	}
}

//--- Manejo de Objetos HTML ---
function bloqueaCampoIndiv(field){
	
	field.disabled = true;
}

function desBloqueaCampoIndiv(field){

	field.disabled = false;
}
//--- Gestion de geograficos ---
function cargarProvincias(box1, box2,valor,sel){
		
	box1.options.length = 0;
	box1.options[0] = new Option("Seleccione Region",0);
	box2.options.length = 0;
	box2.options[0] = new Option("Seleccione Provincia",0);		
	
	if(valor != 0){

		GeograficoDWR.cargarProvincias(valor, function(data){
		
			var resp = null;
			var aux = null;
		
			resp = data;
			
			box1.options[0] = new Option("Seleccione...",0);
			
			for(var a=0;a<resp.length;a++){
			
				aux = resp[a];
				box1.options[a+1] = new Option(aux.glosa,aux.codigo);
			
				if(aux.codigo == sel){
				
					box1.options[a+1].selected = true;
				}
			
			}
		});
	}
}

function cargarComunas(box1,valor,sel){
	
	box1.options.length = 0;
	box1.options[0] = new Option("Seleccione Provincia",0);
	
	if(valor != 0){

		GeograficoDWR.cargarComunas(valor, function(data){
		
			var resp = null;
			var aux = null;
		
			resp = data;
			
			box1.options[0] = new Option("Seleccione...",0);
			
			for(var a=0;a<resp.length;a++){
			
				aux = resp[a];
				box1.options[a+1] = new Option(aux.glosa,aux.codigo);
				
				if(aux.codigo == sel){
				
					box1.options[a+1].selected = true;
				}
			}
		});
	}
}

//--- Gestion de Motivos de Desafiliacion ---
function cargarDescMotivo(box1,valor,sel){
	
	box1.options.length = 0;
	box1.options[0] = new Option("Seleccione Descripción",0);
	
	if(valor != 0){

		GeograficoDWR.cargarDescMotivo(valor, function(data){
		
			var resp = null;
			var aux = null;
		
			resp = data;
			
			box1.options[0] = new Option("Seleccione...",0);
			
			for(var a=0;a<resp.length;a++){
			
				aux = resp[a];
				box1.options[a+1] = new Option(aux.glosa,aux.codigo);
				
				if(aux.codigo == sel){
				
					box1.options[a+1].selected = true;
				}
			}
		});
	}
}


//--- Validacion de RUT ---
function validaRut(rut,rut_dv)
{
	/*
	alert("RUT = " + rut);
	alert("DV = " + rut_dv);
	*/
	var sRut = new String(rut);
  	var sDV = new String(rut_dv);
  
  	if(Trim(rut) == "" && Trim(rut_dv) == "")
  	{
  		return true;
  	}
  	if(sDV.toUpperCase() == DigitoVerificadorRut(sRut))
  	{
    	return true;
  	}else
  	{
    	return false;
  	}
}

function DigitoVerificadorRut( strRut ) 
{
	var rut = 0;
  	var s = 0;
  	var l_dv = "";
 	
  	rut = strRut;
  
  	for (i=2; i< 8; i++)
  	{
    	s = s + ( rut % 10 ) * i;
    	rut = (rut - ( rut % 10 )) / 10;
  	}
  
  	s = s + ( rut % 10 ) * 2;
  	rut = (rut - ( rut % 10 )) / 10;
  	s = s + ( rut % 10 ) * 3;
  	rut = (rut - ( rut % 10 )) / 10;
  	s = 11 - ( s % 11 );
  
  	if ( s == 10 )
     	l_dv = "K";
	else
     	if ( s == 11 )
        	l_dv = "0"
    else
        l_dv = s + "";
        
  	return( l_dv );
}

function ValidadorRUT(rut, dv)
{
	if(!validaRut(rut,dv))
	{
		alert("El RUT ingresado no es válido.");
		return false;
	}
	return true;
}

function separadorDeMiles(cadenaIngreso)
{
	var resultado = "";
	var count = 0;
	var cadena = cadenaIngreso + "";
	
	for(var i = cadena.length; i >= 1 ; i--){
		count++;
		
		if (count == 3){
			resultado = "." + cadena.charAt(i-1) + resultado;
			count = 0;
		}else{
			resultado = cadena.charAt(i-1) + resultado;
		}
		
	}
	
	if(resultado.charAt(0) == '.' && resultado.length > 0){
		resultado = resultado.substring(1, resultado.length);
	}
	
	return resultado;
}

function eliminaCerosIZQ(numeroIngreso)
{
	var resultado = 0;
	var count = 0;
	
	if(Trim(numeroIngreso) == "0"){
		return 0;
	}
	
	
	for (var i = 0; i < numeroIngreso.length; i++)
	{
		if(numeroIngreso.charAt(i) == 0){
			count++;
		}else{
			break;
		}
	}	
	
	resultado = numeroIngreso.substring(count, numeroIngreso.length);
	
	return resultado;
}

function obtenerDescripcion(lista, codigo)
{
	for(i =0; i < lista.length; i++){
		
		var aux;
		aux = lista[i];
		
		if(aux.codigo == codigo){
			
			return aux.glosa;
		}
	}
	return "";
}

function obtenerCodigo(lista, glosa)
{
	for(i =0; i < lista.length; i++){
		
		var aux;
		aux = lista[i];
		
		if(aux.glosa == glosa){
			
			return aux.codigo;
		}
	}
	return 0;
}

function obtenerAgrupacion(lista, codigo)
{
	for(i =0; i < lista.length; i++){
		var aux;
		aux = lista[i];
		if(aux.codigo == codigo){			
			return aux;
		}
	}
	return new ObjAgrupacion(0,"",0,"");
}


function validarPerfiles(perfiles, codigo)
{
    var lista = perfiles.split("|");
	for(var i = 0; i<lista.length; i++)
	{
 		if(lista[i] == codigo){
			return true;
		}
	}
	return false;
}

function obtenerRut(rut)
{
    var lista = rut.split("-");
	return lista[0].split(".").join("");
}

function obtenerDV(rut)
{
    var lista = rut.split("-");
	return lista[1];
}

function validarPerfilesFullAnalista(perfiles)
{
    if (validarPerfiles(perfiles, "1")){
    	if (validarPerfiles(perfiles, "2")){
    		return false;
    	}
    	else{    	
    		return true;
    	}
    }
    else{
    	return false;
    }
}

function copiaArreglo(obj) {
    if (Object.prototype.toString.call(obj) === '[object Array]') {
        var out = [], i = 0, len = obj.length;
        for ( ; i < len; i++ ) {
            out[i] = arguments.callee(obj[i]);
        }
        return out;
    }
    if (typeof obj === 'object') {
        var out = {}, i;
        for ( i in obj ) {
            out[i] = arguments.callee(obj[i]);
        }
        return out;
    }
    return obj;
}