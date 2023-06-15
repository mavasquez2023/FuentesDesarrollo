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

/*function gc ()
{
	<%=System.gc();%>
}*/

function TipoBeneficiarioVO(id_tipo_beneficiario,desc_tipo_beneficiario)
{
	this.id_tipo_beneficiario = id_tipo_beneficiario;
	this.desc_tipo_beneficiario = desc_tipo_beneficiario;
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

function Upper(campo,tipo)
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
}

function validarEmail(valor) {

	if (Trim(valor) != "")
	{
	  	if(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(valor)){
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

//--- Validacion de RUT ---
function validaRut(rut,rut_dv)
{
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
	var aux = -1;
	if((cadena.length)%3 == 0){
		aux = cadena.length/3;
		aux = aux - 1;
	}
	for(var i = cadena.length; i >= 1 ; i--){
		count++;
		
		if (count == 3){
			if((aux!=0)&&(cadena.charAt(i-2)!="-")){
				resultado = "." + cadena.charAt(i-1) + resultado;
				count = 0;
				aux = aux - 1;
			}else{
				resultado = cadena.charAt(i-1) + resultado;
			}
		}else{
			resultado = cadena.charAt(i-1) + resultado;
		}
		
	}
	
	if(resultado.charAt(0) == '.' && resultado.length > 0){
		resultado = resultado.substring(1, resultado.length);
	}
	
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

function completaValorAnio(anio){

	var salida = "";

	if(anio.length == 2)
	{
		salida = '20' + anio;
	}else{
		if(anio.length == 1){
			salida = '20' + anio;
		}	
	}

	return salida;
}


function completaValorMes(mes){
	
	var salida = "";
	
	if(mes < 10){
		salida = '0' + mes;
	}else{
		salida = mes;
	}
	
	return salida;	
		
}