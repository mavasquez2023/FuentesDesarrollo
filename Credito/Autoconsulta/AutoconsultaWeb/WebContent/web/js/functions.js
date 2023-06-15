var RUTMINIMO = 10000;
var EDADMAXIMA = 150;
var PORCENTAJEMAXIMO = 100;



function ChequearRutDigito(ObjRut, ObjDigito) {
	
	var dvr = '0'
	suma = 0
	mult = 2

	strObjRut = ObjRut.value;
	strObjDig = ObjDigito.value;

	for ( i = strObjRut.length - 1 ; i >= 0 ; i -- ) {
		suma = suma + strObjRut.charAt(i) * mult;
		if ( mult == 7 )
			mult = 2;
		else
			mult ++;
	}
	res = suma % 11;
	if ( res == 1 )
		dvr = 'k';
	else {
		if ( res == 0 )
			dvr = '0';
		else {
			dvi = 11 - res;
			dvr = dvi + "";
		}
	}
	if ( dvr != strObjDig.toLowerCase() )
		return(false);
	return(true);
}


function ChequearRutDigito2(Rut, Digito) {
	var dvr = '0'
	suma = 0
	mult = 2

	strObjRut = Rut;
	strObjDig = Digito;
	for ( i = strObjRut.length - 1 ; i >= 0 ; i -- ) {
		suma = suma + strObjRut.charAt(i) * mult;
		if ( mult == 7 )
			mult = 2;
		else
			mult ++;
	}
	res = suma % 11;
	if ( res == 1 )
		dvr = 'k';
	else {
		if ( res == 0 )
			dvr = '0';
		else {
			dvi = 11 - res;
			dvr = dvi + "";
		}
	}
	if ( dvr != strObjDig.toLowerCase() )
		return(false);
	return(true);

}





function Ver_DV(Obj,formulario) {
	var idetar = eval("document."+ formulario + ".idetar");
	var dv = eval("document."+ formulario + ".dv");
	
	var digito = dv.value;
	digito = digito.toUpperCase();
	dv.value = digito;
	
	if (!SoloTipo(Obj.value,'9')&& Obj.value.toLowerCase()!='k'){
		alert("Dígito Verificador Inválido.");
		Obj.value="";
		idetar.value = '';
		dv.value = '';
		Obj.focus();
	}
	else
	{
		if (ChequearRutDigito(idetar,Obj))
			{
			}
	    else
			{
			alert('El Rut Ingresado es incorrecto');
			dv.value='';
			idetar.value='';
			idetar.focus();
			}
	}
}
function IsNumber(camp)
{
	if (camp.value!='') 
	{
		if (isInteger(camp.value)=="false")
			{
			alert("Cantidad debe ser Numérica");
			camp.value='';
			camp.focus();
			}
	}
}		
	
function isInteger(inputText)
{
	var inputLength = inputText.length;
	var isNumber = "true";
	aChar1=inputText.substring(0,1);
		     
	for (i = 0; i < inputLength; i++) {
	   var aChar = inputText.substring(i,i+1);
	   if (aChar < "0" || aChar > "9" ) {
		if (aChar == "ƒ") {
	           isNumber = "true";
	          }
	else {
	           isNumber = "false";
	}
	   }
	}
	if (inputLength == 0) {
	  isNumber = "false";
	}
	if (aChar1 == "-")
	{    isNumber = "false"; }
	return(isNumber);
}



function SoloTipo(InString, tipo){

		for (Cont=0; Cont < InString.length; Cont++) {
    		Char = InString.charAt(Cont);
    		if (tipo == '9') {
    			if(!esNumerico(Char))
    				return(false);
    		}
    		else if (tipo == 'A') {
    			if(!esAlfabetico(Char))
    				return(false);
    		}
    		else if (tipo == 'F') {
    			if(!esAlfaNumerico(Char))
    				return(false);
    		}
    		else if (tipo == 'N') {
    			if(!esNombreUsuario(Char))
    				return(false);
    		}				
    		else if (tipo == 'X') {
    			if(!esCodigo(Char))
    				return(false);
    		}
    		else if (tipo == 'H') {
    			if(!esHexa(Char))
    				return(false);
    		}
			else if (tipo == 'I') {
    			if(!esComa(Char))
    				return(false);
    		}
			else if (tipo == 'RUD') {
    			if(!esRud(Char))
    				return(false);
    		}
			else if (tipo == 'RIT') {
    			if(!esRit(Char))
    				return(false);
    		}
			else if (tipo == 'RUC') {
    			if(!esRuc(Char))
    				return(false);
    		}
			else if (tipo == 'LG') {
    			if(!esLetraGeneral(Char))
    				return(false);
    		}
			else if (tipo == 'DIR') {
    			if(!esDireccion(Char))
    				return(false);
    		}
			else if (tipo == 'TEL') {
    			if(!esTelefono(Char))
    				return(false);
    		}
    		else if (tipo == 'AL') {
    			if(!esAlfabeticoLetra(Char))
    				return(false);
    		}

		}
    	return(true);
 }

 
 function ValidaNum(campo,txt)
{
		if(isNaN(campo.value)){
			alert("El " + txt + " no es válido");
			campo.value="";	
			campo.focus();
			campo.select();
			return(false);		
		}			
}
function esAlfabetico(Char) {
    	if(Char.length!=1) 
    		return(false);
    	Char=Char.toLowerCase();
    	RefString='abcdefghijklmnñopqrstuvwxyz áéíóúü ';
    	if (RefString.indexOf (Char.toLowerCase(), 0) == -1) 
    		return(false);
    	return(true);
}

function esAlfaNumerico(Char) {
    	if(Char.length != 1) 
    		return(false);
    	Char=Char.toLowerCase();
    	RefString='1234567890abcdefghijklmnñopqrstuvwxyzáéíóúü ';
    	if (RefString.indexOf(Char,0) == -1) 
    		return(false);
    	return(true);
}

function esNombreUsuario(Char) {
    	if(Char.length != 1) 
    		return(false);
    	Char=Char.toLowerCase();
    	RefString='1234567890abcdefghijklmnñopqrstuvwxyzáéíóúü ';
    	if (RefString.indexOf(Char,0) == -1) 
    		return(false);
    	return(true);
}

function esCodigo(Char) {
    	if(Char.length != 1) 
    		return(false);
    	Char=Char.toLowerCase();
    	RefString='1234567890abcdefghijklmnñopqrstuvwxyzáéíóúü/-_. ';
    	if (RefString.indexOf(Char,0) == -1) 
    		return(false);
    	return(true);
}

function esNumerico(Char) {
    	if(Char.length!=1) 
    		return(false);// false;
    	RefString="1234567890";
    	if (RefString.indexOf(Char, 0) == -1) 
    		return(false);
    	return(true);
}
function esHexa(Char) {
    	if(Char.length!=1) 
    		return(false);
    	Char=Char.toLowerCase();
    	RefString="1234567890abcdef";
    	if (RefString.indexOf(Char, 0) == -1) 
    		return(false);
    	return(true);
}

function esComa(Char) {
    	if(Char.length!=1) 
    		return(false);
    	Char=Char.toLowerCase();
    	RefString=",";
    	if (RefString.indexOf(Char, 0) == -1) 
    		return(true);
    	return(false);
}

function esDireccion(Char) {
    	if(Char.length != 1) 
    		return(false);
    	Char=Char.toLowerCase();
    	//RefString='1234567890abcdefghijklmnñopqrstuvwxyzáéíóúü&.#-, ';
    	RefString='<>';
    	if (RefString.indexOf(Char,0)!=-1) 
    		return(false);
    	return(true);
}

function esTelefono(Char) {
    	if(Char.length != 1) 
    		return(false);
    	Char=Char.toLowerCase();
    	RefString='1234567890-';
    	if (RefString.indexOf(Char,0) == -1) 
    		return(false);
    	return(true);
}

function esAlfabeticoLetra(Char) {		
    	if(Char.length!=1) 
    		return(false);
    	Char=Char.toLowerCase();
    	RefString='abcdefghijklmnñopqrstuvwxyz';
    	if (RefString.indexOf (Char.toLowerCase(), 0) == -1) 
    		return(false);
    	return(true);
}


/*======================================================================================
Descripción		: Retorna un boolean que indica si el RUD es válido según la referencia definida
Entrada			: Char
Salida			: booleano
Autor			: malvarez - Soluziona Chile S.A.
Fecha			: 15-04-2004
*/

function esRud(Char) {
    	if(Char.length!=1) 
    		return(false);
    	Char=Char.toLowerCase();
    	RefString='1234567890abcdefghijklmnñopqrstuvwxyz-';
    	if (RefString.indexOf (Char.toLowerCase(), 0) == -1) 
    		return(false);
    	return(true);
}

/*======================================================================================
Descripción		: Retorna un boolean que indica si el RIT es válido según la referencia definida
Entrada			: Char
Salida			: booleano
Autor			: malvarez - Soluziona Chile S.A.
Fecha			: 20-04-2004
Modificación	: 21-04-2004 Según especificaciones del Depto de Informática de la DPP se comenta
*/

function esRit(Char) {
    	/*if(Char.length!=1) 
    		return(false);
    	Char=Char.toLowerCase();
    	RefString='1234567890e.-';
    	if (RefString.indexOf (Char.toLowerCase(), 0) == -1) 
    		return(false);*/
    	return(true);
}

/*======================================================================================
Descripción		: Retorna un boolean que indica si el RUC es válido según la referencia definida
Entrada			: Char
Salida			: booleano
Autor			: malvarez - Soluziona Chile S.A.
Fecha			: 20-04-2004
Modificación	: 21-04-2004 Según especificaciones del Depto de Informática de la DPP se comenta
*/

function esRuc(Char) {
    	/*if(Char.length!=1) 
    		return(false);
    	Char=Char.toLowerCase();
    	RefString='1234567890-.';
    	if (RefString.indexOf (Char.toLowerCase(), 0) == -1) 
    		return(false);*/
    	return(true);
}


/*======================================================================================
Descripción		: Retorna un boolean que indica si el caracter es solo letras.
Entrada			: Char
Salida			: booleano
Autor			: malvarez - Soluziona Chile S.A.
Fecha			: 15-04-2004
*/
function esLetraGeneral(Char) {
    	if(Char.length!=1) 
    		return(false);
    	Char=Char.toLowerCase();
    	RefString='abcdefghijklmnñopqrstuvwxyz';
    	if (RefString.indexOf (Char.toLowerCase(), 0) == -1) 
    		return(false);
    	return(true);
}


/*
function isDate(value) {

	fecha = value;
	
	dia = fecha.substring(0,2);
	mes = fecha.substring(3,5);

	if (dia=="00" || mes=="00")
	{
		return(false);
	}
    
    var pattern1=new RegExp("^(0[0-9]|[1-2][0-9]|30|31)-(0[13-9]|1[0-2])-[1-9][0-9][0-9][0-9]");
    var pattern2=new RegExp("^(0[0-9]|[1-2][0-9])-(0[0-9]|1[0-2])-[1-9][0-9][0-9][0-9]");

    if (value.match(pattern1) || value.match(pattern2)) {
        if (parseInt(value.substr(6,4))%4!=0 && parseInt(value.substr(3,2))==2 && parseInt(value.substr(0,2))==29) {
            return(false);
        } else return(true);
    } else return(false);

} */

/*// isDate: devuelve verdadero si value es una fecha valida en formato dd/mm/aaaa
function isDate(value) {

	fecha = value;	
	dia = fecha.substring(0,2);
	mes = fecha.substring(3,5);

	if (dia=="00" || mes=="00")
	{
		return false;
	}*/

    /*var pattern1=new RegExp("^(0[0-9]|[1-2][0-9]|30|31)/(0[13-9]|1[0-2])/[1-9][0-9][0-9][0-9]");
    var pattern2=new RegExp("^(0[0-9]|[1-2][0-9])/(0[0-9]|1[0-2])/[1-9][0-9][0-9][0-9]");*/
    
/*    var pattern1=new RegExp("^(0[0-9]|[1-2][0-9]|30|31)-(0[13-9]|1[0-2])-[1-9][0-9][0-9][0-9]");
    var pattern2=new RegExp("^(0[0-9]|[1-2][0-9])-(0[0-9]|1[0-2])-[1-9][0-9][0-9][0-9]");    

    if (value.match(pattern1) || value.match(pattern2)) {
        if (parseInt(value.substr(6,4))%4!=0 && parseInt(value.substr(3,2))==2 && parseInt(value.substr(0,2))==29) {
            return false;
        } else return true;
    } else return false;

}*/

// isDate: devuelve verdadero si value es una fecha valida en formato dd-mm-aaaa valida
function isDate(value) {

	fecha = value;	
	dia = fecha.substring(0,2);
	mes = fecha.substring(3,5);

	if (dia=="00" || mes=="00")
	{
		return false;
	}
    
    var pattern1=new RegExp("^(0[0-9]|[1-2][0-9]|30|31)-(0[13-9]|1[0-2])-[1-9][0-9][0-9][0-9]");
    var pattern2=new RegExp("^(0[0-9]|[1-2][0-9])-(0[0-9]|1[0-2])-[1-9][0-9][0-9][0-9]");    

    if (value.match(pattern1) || value.match(pattern2)) {
    
	numero = 0; 
	var diasMeses = new CrearArreglo(numero); 
	var diasMesesBisiesto = new CrearArreglo(numero);
	
	diasMeses[1] = 31;
	diasMeses[2]=28;
	diasMeses[3]=31;
	diasMeses[4]=30;
	diasMeses[5]=31;
	diasMeses[6]=30;
	diasMeses[7]=31;
	diasMeses[8]=31;
	diasMeses[9]=30;
	diasMeses[10]=31;
	diasMeses[11]=30;
	diasMeses[12]=31;
		
	diasMesesBisiesto[2]=29;
		    
		if (parseInt(value.substr(3,2))==2)
		{
			if (parseInt(value.substr(6,4))%4==0 && parseInt(value.substr(0,2))>diasMesesBisiesto[2])
			{
			return false;
			}
			else if (parseInt(value.substr(6,4))%4!=0 && parseInt(value.substr(0,2))>diasMeses[2])
			{
			return false;
			}
			else
			{
			return true;
			}

		}
		else if (parseInt(value.substr(0,2))>diasMeses[parseInt(value.substr(3,2))])
		{
		return false;
		}
		else
		{
		return true;
		}
		
    } 
	else
	{
	return false;
	}
	
}



//Retorna true si fecha1 <= fecha2
function isDateBefore(value1,value2) 
{

	var fecha1 = value1;
	var fecha2 = value2;
	
	var dia1 = fecha1.substring(0,2);
	var mes1 = fecha1.substring(3,5);
	var agno1 = fecha1.substring(6,10);
	
	var dia2 = fecha2.substring(0,2);
	var mes2 = fecha2.substring(3,5);
	var agno2 = fecha2.substring(6,10);
	
	if(agno1<agno2)
	{
		return true;
	}
	else if(agno1==agno2)
	{	
		if(mes1<mes2)
		{
					return true;
		}
		else if(mes1==mes2)
		{
			if(dia1<=dia2)
					return true;
		}			
	}
	
	return false;
	
}


function isDateBeforeHoy(value1) 
{	var fecha1 = value1;
	var fecha2,fecha_aux;

	fecha_aux = new Date();
	
	dia1 = fecha1.substring(0,2);
	mes1 = fecha1.substring(3,5);
	agno1 = fecha1.substring(6,10);

	dia2 = fecha_aux.getDate();
	mes2 = fecha_aux.getMonth() +1; //las fecha de JS van de 0 a 11
	agno2 = fecha_aux.getYear();
	
	fecha1 = new Date(agno1,mes1,dia1);
	fecha2 = new Date(agno2,mes2,dia2);
	
	if (fecha1 <= fecha2)
	{
		return true;
	}
	else
	{
		return false;
	}
}



//fecha futura
function isDateFutura(value1) 
{

	var fecha1 = value1;
	var fecha2,fecha_aux;

	fecha_aux = new Date();
	
	dia1 = fecha1.substring(0,2);
	mes1 = fecha1.substring(3,5);
	agno1 = fecha1.substring(6,10);

	dia2 = fecha_aux.getDate();
	mes2 = fecha_aux.getMonth() +1; //las fecha de JS van de 0 a 11
	agno2 = fecha_aux.getYear();

	fecha1 = new Date(agno1,mes1,dia1);
	fecha2 = new Date(agno2,mes2,dia2);
	
	

	if (fecha1 >= fecha2)
	{
	
		return true;
	}
	else
	{
	
		return false;
	}
	
}



//Retorna true si fecha1 >= fecha2
function isDateFuturaAux(value1,value2) 
{
	var fecha1 = value1;
	var fecha2 = value2;


	
	dia1 = fecha1.substring(0,2);
	mes1 = fecha1.substring(3,5);
	agno1 = fecha1.substring(6,10);

	dia2 = fecha2.substring(0,2);
	mes2 =  fecha2.substring(3,5); //las fecha de JS van de 0 a 11
	agno2 = fecha2.substring(6,10);



	fecha1 = new Date(agno1,(mes1-1),dia1 );
	fecha2 = new Date(agno2,(mes2-1),dia2 );

	
	if (fecha1 >= fecha2)
	{	return true;
	}
	else
	{	return false;
	}
}


//Función General que compara las fechas según el signo que se envía
function comparaFechas(value1, value2, signo) 
{
	var fecha1 = value1;
	var fecha2 = value2;
	sino = new Boolean(false); 
	
	dia1 = fecha1.substring(0,2);
	mes1 = fecha1.substring(3,5);
	agno1 = fecha1.substring(6,10);

	dia2 = fecha2.substring(0,2);
	mes2 =  fecha2.substring(3,5); //las fecha de JS van de 0 a 11
	agno2 = fecha2.substring(6,10);

	fecha1 = new Date(agno1,mes1,dia1);
	fecha2 = new Date(agno2,mes2,dia2);
	
	switch (signo)  { 
	   case ">=" : 
		   	if (fecha1 >= fecha2) 	{	
				sino = true;		
			}	
			else 	{	
				sino = false; 	
			}
		  break; 
	   case "<=" : 
		   if (fecha1 <= fecha2) 	{	
				sino = true;		
			}	
			else 	{	
				sino = false; 	
			}
		  break; 
	   case ">" : 
		  if (fecha1 > fecha2) 	{	
				sino = true;		
			}	
			else 	{	
				sino = false; 	
			}
		  break; 
	   case "<" : 
		  if (fecha1 < fecha2) 	{	
				sino = true;		
			}	
			else 	{	
				sino = false; 	
			}
		  break; 
	   case "==" : 
		  if (fecha1 == fecha2) 	{	
				sino = true;		
			}	
			else 	{	
				sino = false; 	
			}
		  break; 
	   default : 
		  //nada
	} 

	if (sino) {
		return true;
	}
	else {
		return false;
	}
	
}







function  validaDate(valor)
{

 if (valor.value!="")
  {
   if (!isDate(valor.value))
     {
     alert("Ingrese una fecha válida");
     valor.value="";
     return(false);
     }
  }   
    
   return(true); 

}


/*======================================================================================
Entrega la cantidad de caracteres de la izquierda de un string
InString: String a evaluar.
cantidad: Numero de caracteres desde la izquierda
*/
function left(InString, cantidad)
{
	if (cantidad > InString.length){
		return InString
	}
	InString = InString.substring(0, cantidad);
	return InString
}
/*======================================================================================
Entrega la cantidad de caracteres de la derecha de un string
InString: String a evaluar.
cantidad: Numero de caracteres desde la derecha
*/
function right(InString, cantidad)
{
	if (cantidad > InString.length){
		return InString
	}
	InString = InString.substring(InString.length - cantidad, InString.length);
	return InString
}



/*======================================================================================
Funcion que elimina los blancos a la izquierda de un string.
Obj: Objeto que contiene Strin a eliminar blancos de la izquierda.
Retorna :Un Objeto
*/
function ltrim(obj) {
	
	InString = obj.value;
	n = i = 0;
	while (i < InString.length) {
		if (InString.charAt(i) != " ") {
			n = i
			break
		}
		i++
	}
	if (n == 0 && i == InString.length)
		obj.value = ''
	else
		obj.value = InString.substring(n, InString.length)
	return obj;
}

/*======================================================================================
Elimina los blancos a la derecha de un string.
obj:	objeto que contiene string a eliminar blancos de la derecha
Retorna: Un Objeto	
*/
function rtrim(obj) {
	InString = obj.value;
	n = i = InString.length
	while (i > 0) {
		if (InString.charAt(i - 1) != " ") {
			n = i
			break
		}
		i--
	}
	if (n == InString.length && i == 0)
		obj.value = ''
	else
		obj.value = InString.substring(0, n);
	return obj;
}

/*======================================================================================
Elimina los blancos de los extremos de un string.
Obj:	objeto que contiene string a eliminar blancos de los extremos
Retorna: Un Objeto	
*/

function trim(Obj) {
	return rtrim(ltrim(Obj));
	//rtrim(Obj);
	//ltrim(Obj);
	//return Obj;
}



function replaceAll(sValor)
{
var valor = sValor.replace(/'/g,"");
return(valor);
}



function esEmail(Obj) {
	
	Obj = trim(Obj);
	band = true;
	InString = Obj.value;
	RefString = 'abcdefghijklmnopqrstuvwxyz-_.@1234567890';
	cont=puntos=0;
	for (i=0;i < InString.length; i++){
		car = InString.charAt(i);
		car = car.toLowerCase();
		if (RefString.indexOf(car, 0) == -1) 
    		band=false;
    	if ( car == '@'){
			cont++;
		}
		if (cont > 0){
			puntos++;
		}
	}
	if (InString.charAt(InString.length - 1) == '.' || cont > 1 || puntos < 1 || InString.indexOf('@.') != -1 || InString.indexOf('.@') != -1 || band == false){
		alert("Error \""+InString+"\" no es una dirección válida de mail.")
		InString='';
		Obj.focus();
		band=false;
	}
	Obj.value = InString;	
	return band;
	
}

//obtiene la marca de la tarjeta

function ObtieneMarca(tarjeta)
	{
		//alert ("tarjeta="+tarjeta);
		var tarjeta1=tarjeta.substring(0,1);
		var tarjeta2=tarjeta.substring(0,2);
		//alert ("tarjeta1="+tarjeta1);
		//alert ("tarjeta2="+tarjeta2);
		var marca="Desconocida";
		
		if(tarjeta=="")
			marca="";
		
		if(tarjeta1=="4")
			marca="VISA";
		if(tarjeta1=="5")
			marca="MASTERCARD";
		if(tarjeta2=="34" || tarjeta2=="37")
			marca="AMERICAN EXPRESS";
		else if (tarjeta1=="3")
			marca="DINERS";
			
		return marca;	
		}

		
//Valida tarjetas
function valida_tarjeta(obj)
{
var tarjeta=obj.value;
tarjeta= rellena_izq(tarjeta,19,'0');

var smak = '1212121212121212121';
var sres = '';
var n ;
var i ;
var tc;

tc=tarjeta;

for(i=0;i<=17;i++)
{
	n=parseInt(smak.charAt(i)) * parseInt(tc.charAt(i));
	sres = sres + n;
	n=parseInt(n);

}
n=0;

for(i=0;i<=(sres.length - 1);i++)
{
	
	n=n + parseInt(sres.charAt(i));
	
}
n=parseInt(n);
if(((10-n%10) == tc.charAt(18) || ((n%10)==0 && tc.charAt(18) == '0')))
	return (true);
else
	return (false);
}


function es_tarjeta(obj)
{
if (!valida_tarjeta(trim(obj)))
 {
 alert("Ingrese una tarjeta válida");
 obj.value='';
 }
}		

//**********
function rellena_izq(valor,largo,caracter)
{
var resultado;
var prefijo='';
var n=(largo - valor.length);
for(i=1;i<=n;i++)
	prefijo=prefijo + caracter;

resultado=prefijo+valor;
return (resultado);
}
//***************

/*======================================================================================
Funcion que crea un vector 
Input   :  indice del vector
Retorna :  Vector(indice)
*/

function CrearArreglo(numero)
 {
	this.length = numero;
	for (var i = 0; i < numero; i++)
		this[i] = 0;
	return this;
 }


/*=====================================================================================
Funcion que abre un ventana o popup
*/


function NewWindow(mypage, myname, w, h, scroll) 
{
	var winl = (screen.width - w) / 2;
	var wint = (screen.height - h) / 2;
	winprops = 'height='+h+',width='+w+',top='+wint+',left='+winl+',scrollbars='+scroll+',resizable=no'
	win = window.open(mypage, myname, winprops)
	if (parseInt(navigator.appVersion) >= 4) { win.window.focus(); }
}

function NewWindow2(mypage, myname, w, h, otros_parametros) 
{
	var winl = (screen.width - w) / 2;
	var wint = (screen.height - h) / 2;
	winprops = 'height='+h+',width='+w+',top='+wint+',left='+winl+',scrollbars='+ otros_parametros
	win = window.open(mypage, myname, winprops)
	if (parseInt(navigator.appVersion) >= 4) { win.window.focus(); }
}


/*=====================================================================================
Funcion que que se usa para el combo de TipoGestion
*/

function LLTipoGestion(obj1,obj2,gest)
{
	IdGrupoSeleccionado=obj1.options[obj1.selectedIndex].value;
	obj2.length=cantidadTipoGestiones;
 	obj2.scrollleft='10';

 	if (gest==0)
 		{
 	/*	obj2.options[0].value='';
		obj2.options[0].text='xxx';
		obj2.length=obj2.length + 1;*/
		s=0;
		}    
	else
		{
		s=0;	
		}	
    
    numero=0;
    for (var j=0;j<(cantidadTipoGestiones);j++)
    	{
    	
		if (IdGrupoSeleccionado==grupoTipoGestionId[j])
			{
			if (gest==tipoGestionId[j])
				{
				numero=s;
				}
			obj2.options[s].value=tipoGestionId[j];
			obj2.options[s].text=nomTipoGestion[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero;
}	

/*=====================================================================================
Funcion que que se usa para el combo de TipoGestion de la Agenda
*/

function LLTipoGestionAgenda(obj1,obj2,gest)
{
	IdGrupoSeleccionado=obj1.options[obj1.selectedIndex].value;
	obj2.length=cantidadTipoGestiones;
 	obj2.scrollleft='10';
 	
 	if (gest==0) 	
 		{
 		obj2.options[0].value='';
		obj2.options[0].text='Seleccione';
		obj2.length=obj2.length + 1;
		s=1;
		}    

	else
		{
		s=0;	
		}	
    
    numero=0;
    for (var j=0;j<(cantidadTipoGestiones);j++)
    	{
    	
		if (IdGrupoSeleccionado==grupoTipoGestionId[j])
			{
			if (gest==tipoGestionId[j])
				{
				numero=s;
				}
			obj2.options[s].value=tipoGestionId[j];
			obj2.options[s].text=nomTipoGestion[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero;
}	


/*=====================================================================================
Funcion que que se usa para el combo de SubGrupoDelito
*/

function LLSubGrupoDelito(obj1,obj2,grupo)
{
	IdSubGrupoDelitoSeleccionado = obj1.options[obj1.selectedIndex].value;
	obj2.length=cantidadSubGrupoDelito;
 	obj2.scrollleft='10';
 	
 	if (grupo==0)
 	
 		{
 		obj2.options[0].value='';
		obj2.options[0].text='Seleccione Sub Grupo Delito';
		obj2.length=obj2.length + 1;
		s=1;
		}    
	else
		{
		s=0;	
		}	
    
    numero=0;
    for (var j=0;j<(cantidadSubGrupoDelito);j++)
    	{
    	
		if (IdSubGrupoDelitoSeleccionado==grupoDelitoId[j])
			{
			if (grupo==tipoGestionId[j])
				{
				numero=s;
				}
			obj2.options[s].value=subGrupoDelitoId[j];
			obj2.options[s].text=nombreSubGrupoDelito[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero;
}	

/*=====================================================================================
Funcion que que se usa para el combo de FormaTermino
*/

function LLFormaTermino(obj1,obj2,grupo)
{
	var IdTerminoSeleccionado = obj1.options[obj1.selectedIndex].value;
	var arregloId;

	//Modificación para el contexto de la forma de término(pena, acuerdo rep., suspens. cond.) 
	/*
	Ya no se usa, por mod de las penas (julio)
	if (IdTerminoSeleccionado.indexOf("&")>=0)
	{	arregloId = IdTerminoSeleccionado.split("&");
		IdTerminoSeleccionado = arregloId[0];
	}*/

	obj2.length=cantidadFormaTermino;
 	obj2.scrollleft='10';
 	
 	if (grupo==0)
 	
 		{
 		obj2.options[0].value='';
		obj2.options[0].text='Seleccione Forma Termino';
		obj2.length=obj2.length + 1;
		s=1;
		}    
	else
		{
		s=0;	
		}	
    
    numero=0;	
    for (var j=0;j<(cantidadFormaTermino);j++)
    	{
    	
		if (IdTerminoSeleccionado==columnaFormaTerminoId[j])
			{
			if (grupo==formaTerminoId[j])
				{
				numero=s;
				}
			obj2.options[s].value=formaTerminoId[j];
			obj2.options[s].text=nombreFormaTermino[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero;
}	


/*=====================================================================================
Funcion que que se usa para el combo de Comunas
*/

function LLComunas(obj1,obj2,comu)
{
	IdRegionSeleccionada = obj1.options[obj1.selectedIndex].value;
	obj2.length = cantidadComunas;
 	obj2.scrollleft='10';
 	
 	if (comu==0)
 	{	obj2.options[0].value='';
		obj2.options[0].text='Seleccione Comuna';
		obj2.length=obj2.length + 1;
		s=1;
	}    
	else
	{	s=0;	
	}	
    
    numero = 0;

    for (var j=0;j<(cantidadComunas);j++)
    	{
    	
		if (IdRegionSeleccionada==comunaIdRegion[j])
			{
			if (comu==comunaId[j])
				{
				numero=s;
				}
			obj2.options[s].value=comunaId[j];
			obj2.options[s].text=comunaNombre[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero;
}	


/*=====================================================================================
Funcion que que se usa para el combo de Resultados Gestion
*/

function LLResultadoGestion(obj1,obj2,gest)
{
	IdTipoGestionSeleccionado = obj1.options[obj1.selectedIndex].value;
	obj2.length = cantidadResultadoGestiones;
 	obj2.scrollleft='10';
 	
 	if (gest==0) 	
 		{
 	/*	obj2.options[0].value='';
		obj2.options[0].text='xxx';
		obj2.length=obj2.length + 1;*/
		s=0;
		}    
	else
		{
		s=0;	
		}	
    
    numero=0;
    for (var j=0;j<(cantidadResultadoGestiones);j++)
    	{
  		  	
		if (IdTipoGestionSeleccionado==tipoGestionId2[j])
			{
			if (gest==resultadoGestionId[j])
				{
				numero=s;
				}
			obj2.options[s].value=resultadoGestionId[j];
			obj2.options[s].text=resultadoGestion[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero;
}	


/*=====================================================================================
Funcion que que se usa para el combo de Delito
*/

function LLDelito(obj1,obj2,delito)
{
	IdGrupoDelitoSeleccionado = obj1.options[obj1.selectedIndex].value;
	obj2.length =  cantidadDelitos;
 	obj2.scrollleft = '10';
 	
 	if (delito==0)
 	{	obj2.options[0].value='';
		obj2.options[0].text='Seleccione Delito';
		obj2.length=obj2.length + 1;
		s=1;
	}    
	else
	{	s=0;	
	}	
    
    numero=0;
  
    for (var j=0;j<(cantidadDelitos);j++)
    {
    	if (IdGrupoDelitoSeleccionado==ArrGrupoDelitoId[j])
		{
			if (delito == ArrGrupoDelitoId[j])
			{	numero=s;
			}
			
			obj2.options[s].value=ArrDelitoId[j];
			obj2.options[s].text=ArrDelito[j];  
			s++;
		}
		else
		{	obj2.length=obj2.length-1;			
		}
			
	}
	
	obj2.selectedIndex=numero;
	
}	


/*=====================================================================================
Funcion que que se usa para el combo de Tipos de Usuarios
*/
		
function LLTipoUsuarios(obj1,obj2,tipo)
{
	idGrupoSeleccionado = obj1.options[obj1.selectedIndex].value;
	obj2.length = cantTipos;
 	obj2.scrollleft='10';
 	
 	if (tipo==0)
 	{	obj2.options[0].value='';
		obj2.options[0].text='Seleccione';
		obj2.length=obj2.length + 1;
		s=1;
	}    
	else
	{	s=0;	
	}	
    
    numero = 0;

    for (var j=0;j<(cantTipos);j++)
    	{
    	
		if (idGrupoSeleccionado==ArrGrupoUsuarioId[j])
			{
			if (tipo==ArrTipoUsuarioId[j])
				{
				numero=s;
				}
			obj2.options[s].value=ArrTipoUsuarioId[j];
			obj2.options[s].text=ArrTipoUsuario[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero;
}	



/*=====================================================================================
Funcion que que se usa para el combo de Defensoria Usuarios
*/
		
function LLDefensoriaUsu(obj1,obj2,tipo)
{
	idRegionSeleccionada = obj1.options[obj1.selectedIndex].value;
	obj2.length = cantDefensorias;
 	obj2.scrollleft='10';
 	
 	if (tipo==0)
 	{	obj2.options[0].value='';
		obj2.options[0].text='Seleccione';
		obj2.length=obj2.length + 1;
		s=1;
	}    
	else
	{	s=0;	
	}	
    
    numero = 0;

    for (var j=0;j<(cantDefensorias);j++)
    	{
    	
		if (idRegionSeleccionada==ArrRegionId[j])
			{
			if (tipo==ArrDefensoriaId[j])
				{
				numero=s;
				}
			obj2.options[s].value=ArrDefensoriaId[j];
			obj2.options[s].text=ArrDefensoria[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero;
}	



/*=====================================================================================
Funcion que que se usa para el combo de Defensoria Usuarios con respecto a Licitaciones
*/
		
	
		
function LLPropuestaLic(obj1,obj2,tipo)
{
	idLicitacionSel = obj1.options[obj1.selectedIndex].value;
	obj2.length = cantDefensoriasProp;
 	obj2.scrollleft='10';
 	
 	if (tipo==0)
 	{	obj2.options[0].value='';
		obj2.options[0].text='Seleccione';
		obj2.length=obj2.length + 1;
		s=1;
	}    
	else
	{	s=0;	
	}	
    
    numero = 0;

    for (var j=0;j<(cantDefensoriasProp);j++)
    	{
    	
		if (idLicitacionSel==ArrLicitacionPropId[j])
			{
			if (tipo==ArrPropuestaId[j])
				{
				numero=s;
				}
			obj2.options[s].value=ArrPropuestaId[j];
			obj2.options[s].text=ArrNroPropuesta[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero;
}

/* copia function LLPropuestaLic(obj1,obj2,tipo)
{
	idLicitacionSel = obj1.options[obj1.selectedIndex].value;
	obj2.length = cantDefensoriasProp;
 	obj2.scrollleft='10';
 	
 	if (tipo==0)
 	{	obj2.options[0].value='';
		obj2.options[0].text='Seleccione';
		obj2.length=obj2.length + 1;
		s=1;
	}    
	else
	{	s=0;	
	}	
    
    numero = 0;

    for (var j=0;j<(cantDefensoriasProp);j++)
    	{
    	
		if (idLicitacionSel==ArrLicitacionId[j])
			{
			if (tipo==ArrDefensoriaPropId[j])
				{
				numero=s;
				}
			obj2.options[s].value=ArrDefensoriaPropId[j];
			obj2.options[s].text=ArrDefensoriaProp[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero;
} */





/*=====================================================================================
Funcion que que se usa para el combo de Licitacion con respecto a las regiones
*/
		
function LLLicitacionUsu(obj1,obj2,tipo)
{
	idRegionSelLicitacion = obj1.options[obj1.selectedIndex].value;
	obj2.length = cantLicitaciones;
 	obj2.scrollleft='10';
 	
 	if (tipo==0)
 	{	obj2.options[0].value='';
		obj2.options[0].text='Seleccione';
		obj2.length=obj2.length + 1;
		s=1;
	}    
	else
	{	s=0;	
	}	
    
    numero = 0;
  
    for (var j=0;j<(cantLicitaciones);j++)
   	{   if (idRegionSelLicitacion==ArrRegionIdLic[j])
		{	if (tipo==ArrLicitacionId[j])
			{	numero=s;
			}
			obj2.options[s].value=ArrLicitacionId[j];
			obj2.options[s].text=ArrLicitacion[j];			
			s++;
		}
		else
		{	obj2.length=obj2.length-1;
		}	
	}
	obj2.selectedIndex=numero;
}	


/*=====================================================================================
Funcion que desde un combo setea un text y otro hidden
(se usa para el combo de Defensoría con respecto a una Propuesta)
*/
		
function LLDefensoriaProp(obj1,obj2,obj3)
{
	idPropuestaSel = obj1.options[obj1.selectedIndex].value;
     
    for (var j=0;j<(cantDefensoriasProp);j++)
   	{   if (idPropuestaSel==ArrPropuestaId[j])
		{	obj2.value = ArrDefensoriaId2[j]; 
		    obj3.value = ArrDefensoria2[j]; 
		}
		
	}
	
}


/*=====================================================================================
Funcion que que se usa para generar cualquier combo dinamico
*/


function LLRelacionForanea(obj1,obj2,comu)
{
/* Arreglo base = idBase, nombreBase
  Arreglo foranea = foraneaid, foraneanombre, foraneaidBase
*/    
	IdBaseSeleccionada = obj1.options[obj1.selectedIndex].value;
	obj2.length = cantidadForaneas;
 	obj2.scrollleft='10';
 	if (comu==0)
 	{	obj2.options[0].value='0';
		obj2.options[0].text='Seleccione';
		obj2.length=obj2.length + 1;
		s=1;
	}    
	else
	{	s=0;	
	}	
    
    numero = 0;

    for (var j=0;j<(cantidadForaneas);j++)
    	{
    	
		if (IdBaseSeleccionada==foraneaIdBase[j])
			{
			if (comu==foraneaId[j])
				{
				numero=s;
				}
			obj2.options[s].value=foraneaId[j];
			obj2.options[s].text=foraneaNombre[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero;
}	



/*=====================================================================================
Funcion que que se usa para generar cualquier combo dinamico, que se aplica en combos extendidos
*/


function LLRelacionForaneaForCM(obj1,obj2,comu)
{
	IdBaseSeleccionada = obj1.options[obj1.selectedIndex].value;
	obj2.length = cantidadForaneas;
 	obj2.scrollleft='10';
 	
 	if (comu==0)
 	{	/* obj2.options[0].value='0';
		obj2.options[0].text='Seleccione'; */
		//obj2.length=obj2.length + 1;
		s=0; 
	}	    
	else
	{	s=0;		
	}	
    
    numero = 0;

    for (var j=0;j<(cantidadForaneas);j++)
    {    	
		if (IdBaseSeleccionada==foraneaIdBase[j])
		{
			if (comu==foraneaId[j])
			{	numero=s;
			}
			obj2.options[s].value=foraneaId[j];
			obj2.options[s].text=foraneaNombre[j];  
			s++;
		}
		else
		{
			obj2.length=obj2.length-1;
		}	
	}
	obj2.selectedIndex=numero;
}


/*=====================================================================================
Funcion que que se usa para generar cualquier combo dinamico
*/

function LLRelacionForanea2(obj1,obj2,comu)
{
/* Arreglo base = idBase, nombreBase
  Arreglo foranea = foraneaid, foraneanombre, foraneaidBase
*/    
	IdBaseSeleccionada2 = obj1.options[obj1.selectedIndex].value;
	obj2.length = cantidadForaneas2;
 	obj2.scrollleft='10';
 	if (comu==0)
 	{	obj2.options[0].value='0';
		obj2.options[0].text='Seleccione';
		obj2.length=obj2.length + 1;
		s=1;
	}    
	else
	{	s=0;	
	}	
    
    numero2 = 0;

    for (var j=0;j<(cantidadForaneas2);j++)
    	{
    	
		if (IdBaseSeleccionada2==foraneaIdBase2[j])
			{
			if (comu==foraneaId2[j])
				{
				numero2=s;
				}
			obj2.options[s].value=foraneaId2[j];
			obj2.options[s].text=foraneaNombre2[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero2;
}	

/*=====================================================================================
Funcion que que se usa para generar cualquier combo dinamico para arreglo 3
*/


function LLRelacionForaneaToda3(obj1,obj2,comu)
{
/* Arreglo base = idBase, nombreBase
  Arreglo foranea3 = foraneaid, foraneanombre, foraneaidBase
*/    
	IdBaseSeleccionada = obj1.options[obj1.selectedIndex].value;
	obj2.length = cantidadForaneas3;
 	obj2.scrollleft='10';
 	if (comu==0)
 	{	obj2.options[0].value='0';
		obj2.options[0].text='Seleccione';
		obj2.length=obj2.length + 1;
		s=1;
	}    
	else
	{	s=0;	
	}	
    
    numero3 = 0;

    for (var j=0;j<(cantidadForaneas3);j++)
    	{

		if (IdBaseSeleccionada=="-1")
			{
			if (comu==foraneaId3[j])
				{
				numero3=s;
				}
			obj2.options[s].value=foraneaId3[j];
			obj2.options[s].text=foraneaNombre3[j];  
			s++;
			}
		else if (IdBaseSeleccionada==foraneaIdBase3[j])
			{
			if (comu==foraneaId3[j])
				{
				numero3=s;
				}
			obj2.options[s].value=foraneaId3[j];
			obj2.options[s].text=foraneaNombre3[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero3;
}	


/*=====================================================================================
Funcion que que se usa para generar cualquier combo dinamico
*/


function LLRelacionForaneaToda(obj1,obj2,comu)
{
/* Arreglo base = idBase, nombreBase
  Arreglo foranea = foraneaid, foraneanombre, foraneaidBase
*/    
	IdBaseSeleccionada = obj1.options[obj1.selectedIndex].value;
	obj2.length = cantidadForaneas;
 	obj2.scrollleft='10';
 	if (comu==0)
 	{	obj2.options[0].value='0';
		obj2.options[0].text='Seleccione';
		obj2.length=obj2.length + 1;
		s=1;
	}    
	else
	{	s=0;	
	}	
    
    numero = 0;

    for (var j=0;j<(cantidadForaneas);j++)
    	{

		if (IdBaseSeleccionada=="-1")
			{
			if (comu==foraneaId[j])
				{
				numero=s;
				}
			obj2.options[s].value=foraneaId[j];
			obj2.options[s].text=foraneaNombre[j];  
			s++;
			}
		else if (IdBaseSeleccionada==foraneaIdBase[j])
			{
			if (comu==foraneaId[j])
				{
				numero=s;
				}
			obj2.options[s].value=foraneaId[j];
			obj2.options[s].text=foraneaNombre[j];  
			s++;
			}
		else
			{
			obj2.length=obj2.length-1;
			}	
		}
	obj2.selectedIndex=numero;
}	


function isHourBefore(value1,value2) 
{
	
	var hora1 = value1;
	var hora2 = value2;
	if(hora1 <= hora2)
	{
		return true;
	}
	else 
	{	
		return false;
	}
	
}



/*****************************************************************/
/************    IMPUTADO                            *************/
/*****************************************************************/

/*======================================================================================
Descripción		: Javascript que calcula el Monto Ajustado que le corresponde al imputado,
				  según una fórmula dada.
Entrada			: obj (objeto desde el cual se invoca la function javascript)
Salida			: Ninguno, sólo actualiza el objeto "tasaCopago" del formulario
*/

	function jsCalculaIIA(obj, frm)
	{	var ing = '';
		var divid = '';
		var ctramo1 = '';
		var ctramo2 = '';
		var ctramo3 = '';
		var iia;
		
		if (validaNumero(obj))
		{	ing = parseInt(validaVacio(frm.ingresoTotalMensualImp.value));	
			divid = parseInt(validaVacio(frm.montodividendoarriendoImp.value));
			ctramo1 = parseInt(validaVacio(frm.cargasTramo1Imp.value)) * 0.3;
			ctramo2 = parseInt(validaVacio(frm.cargasTramo2Imp.value)) * 0.4;
			ctramo3 = parseInt(validaVacio(frm.cargasTramo3Imp.value)) * 0.8;				
			
			//iia	= new Number(Math.round((ing - (divid / (1.2 + ctramo1 + ctramo2 + ctramo3)))));
			iia	= new Number(Math.round((ing - divid) / (1.2 + ctramo1 + ctramo2 + ctramo3)));

			//validar si el valor es negativo
			if (iia<=0) {
				frm.ingresoIndAjustado.value = 0;
				frm.tasaCopago.value = 0;
			}
			else {
				frm.ingresoIndAjustado.value = iia;
				frm.tasaCopago.value = jsCalculaTasa(frm.ingresoIndAjustado.value);
			}
		}
	}
	
	

/*======================================================================================
Descripción		: Javascript que calcula el Monto Ajustado que le corresponde al imputado,
				  según una fórmula dada.
Entrada			: valor (valor a buscar en la tabla de montos ingreso proporcionada por
				  la Defensoría, esta tabla se guarda en la BD)
Salida			: Tasa o porcentaje que corresponde al monto.
*/

	function jsCalculaTasa(valor)
	{	var tasa2 = '';
		valor = Number(valor);
		
		for(var i=0; i<cantidadtasas; i++)
		{	var montoIni = Number(montoInicial[i]);
			var montoFin = Number(montoFinal[i]);

			if (valor >= montoIni)	
			{	if (montoFin!=-1) {
					if (valor <=montoFin) {
						tasa2=tasa[i];
						break;
					}
				}
				else {
					tasa2=tasa[cantidadtasas-1];					
					break;
				}
			}
		}		
		return(tasa2);		
	}

	function jsCalculaTasa_old(valor)
	{	var tasa2 = '';
		valor = Number(valor);
		
		for(var i=0; i<cantidadtasas; i++)
		{	var montoIni = Number(montoInicial[i]);
			var montoFin = Number(montoFinal[i]);
						
			if (valor > montoIni && valor < montoFin)	
			{	tasa2=tasa[i];
			}
		}		
		return(tasa2);		
	}





/*======================================================================================
Descripción		: Valida si un valor es vacío o null
Entrada			: valor (valor a buscar a evaluar)
Salida			: cadena (cadena formateada)
*/
	function validaVacio(valor)
	{	var cadena='';
		
		if (valor == "" || valor== " ")
		{	cadena=0; }
		else
		{	cadena=valor; }
		return(cadena);
	}


/*======================================================================================
Descripción		: Valida si un valor es numérico
Entrada			: objeto
Salida			: booleano
*/

	function validaNumero(obj)
	{ 	if (!SoloTipo(obj.value,'9'))
		{	alert('Debe Ingresar solo números');
			obj.focus();
			obj.value='';
			return(false);
		}
		return(true);
	}


/*======================================================================================
Descripción		: Valida si un valor es numérico, pero no retorna el alert, solo el boolean
Entrada			: objeto
Salida			: booleano
*/

	function validaNro(obj) { 	
	if (!SoloTipo(obj.value,'9'))
		{	return(false);
		}
		return(true);
	}




/*======================================================================================
Descripción		: Valida si un valor es alfabetico
Entrada			: objeto
Salida			: booleano
*/

	function validaAlfabetico(obj)
	{ 	if (!SoloTipo(obj.value,'A'))
		{	alert('Texto no válido');
			obj.focus();
			obj.value='';
			return(false);
		}
		return(true);
	}


/*======================================================================================
Descripción		: Valida si un valor es Direccion
Entrada			: objeto
Salida			: booleano
*/

	function validaDireccion(obj)
	{ 	if (!SoloTipo(obj.value,'DIR'))
		{	alert('Dirección no válida');
			obj.focus();
			obj.value='';
			return(false);
		}
		return(true);
	}

/*======================================================================================
Descripción		: Valida si un valor es Telefono
Entrada			: objeto
Salida			: booleano
*/
	function validaTelefono(obj)
	{ 	if (!SoloTipo(obj.value,'TEL'))
		{	alert('Teléfono no válido');
			obj.focus();
			obj.value='';
			return(false);
		}
		return(true);
	}


/*======================================================================================
Descripción		: Valida si un valor es Rut
Entrada			: objeto Rut y Digito verificador
Salida			: booleano
*/
	function validaRut(objRut,objDv)
	{ 	
		if (!ChequearRutDigito(objRut,objDv))
		{	alert("Debe ingresar un rut válido");
			objRut.value='';
			objDv.value='';
			objRut.focus();
			return false;
		}
		return(true);
	}



	//Modificaciones a antecedentes Socio-Economicos

	//validar Tiene Vivienda
	function validaTieneVivienda(f, obj) {
		if (trim(obj).value=='' || trim(obj).value=='0') {
			setFoco(f.tieneViviendaImp, 'r');
		}
		else {
			f.tieneViviendaImp.checked = false;
		}
	}

	//Setear que trabaja (esta funcion no se implementa porque un imputado puede no trabajar y recibir un ingreso, es decir no hay realacion entre las dos variables.)
	function jsValidaIngreso(f, obj) {		
		if (validaNumero(obj))
		{	if (obj.value>0 && obj.value!='') {
				setFoco(f.trabajaActualmenteImp[0], 'r');				
			}
			else {
				setFoco(f.trabajaActualmenteImp[1], 'r');
			}
		}		
	}

	//Setear que trabaja (esta funcion no se implementa porque un imputado puede no trabajar y recibir un ingreso, es decir no hay realacion entre las dos variables.)
	function jsValidaIngreso2(f, obj) {		
		if (validaNumero(obj))
		{	if (obj.value>0 && obj.value!='') {
				setFoco(f.trabajaactualmenteImp[0], 'r');				
			}
			else {
				setFoco(f.trabajaactualmenteImp[1], 'r');
			}
		}		
	}


	//Set es No es Dependiente en caso de que no trabaje e ingreso en cero
	function validaNotrabaja(f, obj) {
	 	if (obj.checked) {
			setFoco(f.esDependienteImp[1], 'r');
			//una persona sin trabajar puede tener ingresos, por ejem: un jubilado
			//f.ingresoTotalMensualImp.value = '0'; 
		}
	}


	//validar montos
	function jsValidaMontosIngresoArriendo(f, objIng, objArr) {				

		if (f.trabajaactualmenteImp[0].checked) {
			if (validaNumero(objIng) && validaNumero(objArr)) {
				var ing = new Number(objIng.value);
				var arr = new Number(objArr.value);
				if (ing<arr) {
					alert('El ingreso destinado al pago de dividendos o arriendos debe ser menor al Total Mensual de Ingreso.');
					objArr.focus();
					return(false);
				}
				return(true);
			}
		}
	}


	function jsValidaMontosIngresoArriendo2(f, objIng, objArr) {				

		if (f.trabajaactualmente[0].checked) {
			if (validaNumero(objIng) && validaNumero(objArr)) {
				var ing = new Number(objIng.value);
				var arr = new Number(objArr.value);
				if (ing<arr) {
					alert('El ingreso destinado al pago de dividendos o arriendos debe ser menor al Total Mensual de Ingreso.');
					objArr.focus();
					return(false);
				}
				return(true);
			}
		}
	}
	
	
	//En caso de que se trate de un docto diferente del RUT, dejar los text de RUT y dv como vacíos.
	function validaTipoDocumento(f, objTipoDocu, objRut, objDv) {
		
	 	if (objTipoDocu.value=='I' || objTipoDocu.value=='P' || objTipoDocu.value=='O') {
			objRut.value = '';
			objDv.value = '';					
		}
		
		if (objTipoDocu.value=='R' || objTipoDocu.value=='I') {
			f.nroIdentificacion.value = '';
		}
		
		
	}




function esEmail2(Obj) 
{	
	Obj = trim(Obj);
	band = true;
	InString = Obj.value;
	RefString = 'abcdefghijklmnopqrstuvwxyz-_.@';
	cont=puntos=0;
	for (i=0;i < InString.length; i++){
		car = InString.charAt(i);
		car = car.toLowerCase();
		if (RefString.indexOf(car, 0) == -1) 
    		band=false;
    	if ( car == '@'){
			cont++;
		}
		if (cont > 0){
			puntos++;
		}
	}
	
	if (InString.charAt(InString.length - 1) == '.' || cont > 1 || puntos < 1 || InString.indexOf('@.') != -1 || InString.indexOf('.@') != -1 || band == false) {
		band=false;
	}
	Obj.value = InString;	
	return band;
	
}



/*======================================================================================
Descripción		: Valida si un valor es Mail
Entrada			: objeto
Salida			: booleano
*/

	function validaMail(obj)
	{ 	if (!esMail(obj))
		{	return(false);
		}
		return(true);
	}



/*======================================================================================
Descripción		: Se encarga de contar los caracteres ingresados a un objeto
Entrada			: objeto, maximo de caracteres permitidos
Salida			: Ninguna
Autor			: malvarez - Soluziona Chile S.A.
*/
function cuentaCaracteres(obj,maximo)
{ var diferencia = maximo - obj.value.length;
  if (diferencia < 0)
  { obj.value = obj.value.substring(0,maximo);
    diferencia = maximo - obj.value.length;
  }  
}

/*======================================================================================
Descripción		: Se encarga de verificar si se llega al límite de caracteres permitidos
				  de un objeto.
Entrada			: objeto, maximo de caracteres permitidos
Salida			: booleano
Autor			: malvarez - Soluziona Chile S.A.
*/
function checkLimite(obj,maximo)
{ var result = true;
  if (obj.value.length >= maximo)
  {	result = false;
	alert('No puede ingresar más caracteres');
  }
  if (window.event)
    window.event.returnValue = result;
  return result;
}


/*======================================================================================
Descripción		: Función que dice si el checkbox está chequeado o no
Entrada			: objeto y nombre del objeto en el formulario
Salida			: booleano
Autor			: malvarez - Soluziona Chile S.A.
*/

function checkBoxValidar(obj, stringObj) {
	var total = 0;
	var max = obj.length;
	
	if (isNaN(max)) {		
		if (obj.checked) {
			total += 1;
		}
	}
	else {	
		for (var idx = 0; idx < max; idx++) 
		{	if (eval(stringObj + "[" + idx + "].checked") == true) {
				total += 1;
			}
		}
	}
	if (total==0)
		return(false);
	else
		return(true); 
}


/*======================================================================================
Descripción		: Función que chequea de una sola vez todos los CheckBox de un Objeto
Entrada			: objeto en el formulario
Salida			: String
Autor			: malvarez - Soluziona Chile S.A.
*/

var checkflag = "false";
function checkAll(field) {
	if (checkflag == "false") {
		for (i = 0; i < field.length; i++) {
			field[i].checked = true;
		}
		checkflag = "true";
		return "Uncheck All"; 
	}
	else {
		for (i = 0; i < field.length; i++) {
			field[i].checked = false; 
		}
		checkflag = "false";
		return "Check All"; 
	}
}


/*======================================================================================
Descripción		: Función que valida en forma específica todo el formulario de Acusación
				del Ministerio Público en el cual se eligen los imputados y los delitos
				a acusar.
Entrada			: formulario, objeto Check imputado
Salida			: boleano
Autor			: malvarez - Soluziona Chile S.A.
*/
function validateImputAcusacionMP(f,obj)
{	var i = 0;
	var flag = 0;

	var total = 0;
	var arregloImput;
	var cadenaId; 
	var cadenaText;
	var imputadoIdAux;
	var imputadoTextAux;

	var nroImputados = obj.length;
	
	if (isNaN(nroImputados)) {		
		arregloImput = f.imputado0.value.split("#");
		if (obj.checked && f.cadenaIdDelito0.value=="") {
			alert("Debe elegir al menos un delito para el imputado " + arregloImput[0]);
			return(false);
		}

		if ((obj.checked == false) && (f.cadenaIdDelito0.value != "")) {
			alert("Debe seleccionar el imputado " + arregloImput[0]);
			return(false);
		}

		if (obj.checked) {
			total += 1;			
		}

		cadenaId = f.cadenaIdDelito0;
		cadenaText = f.cadenaTextDelito0;
		imputadoIdAux = f.imputadoId0;
		imputadoTextAux = f.imputado0;
		
		f.cadenaTextImputado.value = imputadoIdAux.value + '#' + imputadoTextAux.value;					
		
		if (cadenaId.value!='' && cadenaText.value!='')	
		{	f.cadenaIdDelito.value = imputadoIdAux.value + ',' + cadenaId.value;
			f.cadenaTextDelito.value = imputadoIdAux.value + '#' + cadenaText.value;
		}

	}
	else {	
		for (var idx = 0; idx < nroImputados; idx++) 
		{	arregloImput = eval("document.gestionForm.imputado" + idx + ".value").split("#");
			if (eval("document.gestionForm.checkImputado" + "[" + idx + "].checked") == true &&
				eval("document.gestionForm.cadenaIdDelito" + idx + ".value") == "") {				
				alert("Debe elegir al menos un delito para el imputado " + arregloImput[0]);
				return(false);
			}

			if (eval("document.gestionForm.checkImputado" + "[" + idx + "].checked") == false &&
				eval("document.gestionForm.cadenaIdDelito" + idx + ".value") != "") {
				alert("Debe seleccionar el imputado " + arregloImput[0]);
				return(false);
			}

			if (eval("document.gestionForm.checkImputado" + "[" + idx + "].checked") == true) {
				total += 1;			
			}

			cadenaId = eval('document.gestionForm.cadenaIdDelito'+ idx);
			cadenaText = eval('document.gestionForm.cadenaTextDelito'+ idx);
			imputadoIdAux = eval('document.gestionForm.imputadoId'+ idx);
			imputadoTextAux = eval('document.gestionForm.imputado'+ idx);
			
			if (idx==0)
			{	f.cadenaTextImputado.value = imputadoIdAux.value + '#' + imputadoTextAux.value;					
			}
			else
			{	f.cadenaTextImputado.value = f.cadenaTextImputado.value + "++" + imputadoIdAux.value + '#' + imputadoTextAux.value;
			}
			
			if (cadenaId.value!='' && cadenaText.value!='')	
			{	if (i==0)
				{	f.cadenaIdDelito.value = imputadoIdAux.value + ',' + cadenaId.value;
					f.cadenaTextDelito.value = imputadoIdAux.value + '#' + cadenaText.value;							
				}
				else
				{	f.cadenaIdDelito.value = f.cadenaIdDelito.value + "++" + imputadoIdAux.value + ',' + cadenaId.value;
					f.cadenaTextDelito.value = f.cadenaTextDelito.value + "++" + imputadoIdAux.value + '#' + cadenaText.value;					
				}
			}
		}
	}

	if (total==0) {
		alert("Para realizar la Acusación debe seleccionar al menos un imputado");
		return(false);
	}
	
	return(true);

}



/*======================================================================================
Descripción		: Deja el focus o chequea (según sea el caso) en el objeto que corresponde.
Entrada			: objeto
Salida			: Ninguno
Autor			: malvarez - Soluziona Chile S.A.
Fecha			: 19-04-2004
Modificacion	:
*/

function setFoco(obj, tipoObj) {

	myString1 = new String(tipoObj);
	var myString2 = myString1.toUpperCase();

	if (myString2 == 'T') {
		obj.focus();
	}
	
	if (myString2 == 'R') {
		obj.checked = true; 
	}
	
	return(true);
}




/*=====================================================================================
Funcion que que se usa para dar formato a un numero con decimales, limita la cantidad de decimales
*/

function format_number(pnumber,decimals)
{ 
  if (isNaN(pnumber)) { return 0}; 
  if (pnumber=='') { return 0}; 
  
  var IsNegative=(parseInt(pnumber)<0);
  if(IsNegative)pnumber=-pnumber;

  var snum = new String(pnumber); 
  var sec = snum.split('.'); 
  var whole = parseInt(sec[0]); 
  var result = ''; 
  if(sec.length > 1){ 
    var dec = new String(sec[1]); 
    dec = parseInt(dec)/Math.pow(10,parseInt(dec.length-decimals-1));
	Math.round(dec);
	dec = parseInt(dec)/10;
	
	if(IsNegative)
	{
	  var x = 0-dec;
      x = Math.round(x);
	  dec = - x;
	}
	else
	{
      dec = Math.round(dec);
	}

	/*
	 * If the number was rounded up from 9 to 10, and it was for 1 'decimal'
	 * then we need to add 1 to the 'whole' and set the dec to 0.
	 */
	if(decimals==1 && dec==10)
	{
	  whole+=1;
	  dec="0";
	}

    dec = String(whole) + "." + String(dec); 
    var dot = dec.indexOf('.'); 
    if(dot == -1){ 
      dec += '.'; 
      dot = dec.indexOf('.'); 
    }
	var l=parseInt(dot)+parseInt(decimals);
    while(dec.length <= l) { dec += '0'; } 
    result = dec; 
  } else{ 
    var dot; 
    var dec = new String(whole); 
    dec += '.'; 
    dot = dec.indexOf('.'); 
	var l=parseInt(dot)+parseInt(decimals);
    while(dec.length <= l) { dec += '0'; } 
    result = dec; 
  } 
  if(IsNegative)result="-"+result;
  return result; 
} 





function compara_fecha(fecha1,fecha2)
{

	dia1 = fecha1.substring(0,2);
	mes1 = fecha1.substring(3,5);
	ano1 = fecha1.substring(6,10);
		
	dia2 = fecha2.substring(0,2);
	mes2 = fecha2.substring(3,5);
	ano2 = fecha2.substring(6,10);
		
	fecha1 = new Date(ano1,mes1,dia1);
	fecha2 = new Date(ano2,mes2,dia2);
	
	if (fecha1 <= fecha2)
	{
		return true;
	}
	else
	{
		return false;
	}
}



/*======================================================================================
Descripción		: Valida que el año del objeto date sea menor o igual a la constante agno inicio defensoria
Entrada			: objeto (formato:  mm-dd-aaaa)
Salida			: boolean
Autor			: malvarez - Soluziona Chile S.A.
Fecha			: 30-04-2004
Modificacion	:
*/ 
function validaAgnoInicioDef(objFecha, constAgnoDef) {

	var agno = new String(objFecha.value);
	var nroAgno = new Number(agno.substring(6,agno.length));	

	if (nroAgno < constAgnoDef) {
		return(false);
	}
	return(true);
}


/*======================================================================================
Descripción		: Valida que el año de nacimiento no sea mayor a la EDADMAXIMA
Entrada			: objetos date (formato:  mm-dd-aaaa)
Salida			: boolean
Autor			: malvarez - Soluziona Chile S.A.
Fecha			: 29-06-2004
Modificacion	:  
*/

function validaAgnoFechaNac(fechaHoy, fechaNac) {

	agnoFechaHoy = new Number(fechaHoy.substring(6,fechaHoy.length));	
	agnoFechaNac = new Number(fechaNac.substring(6,fechaNac.length));	
	
	if ((agnoFechaHoy - agnoFechaNac)<=EDADMAXIMA)  {
		return(true);
	}
	else {
		return(false);
	}

}



/*======================================================================================
Descripción	: Valida que la suma total de los items no sea superior a 100, en el módulo de Indicadores de Gestión.
Entrada			: objeto
Salida			: boolean
Autor				: malvarez - Soluziona Chile S.A.
Fecha			: 29-06-2004
Modificacion	:  
*/
function validaSumaPorcent(obj, objSuma, accion) {
	if (validaNumero(obj)) {
		valorSuma = new Number(objSuma.value);	
		valorObj = new Number(obj.value);	
		
		if (valorObj==0) {
			alert("No puede asignar un valor igual a Cero");
			return(false);
		}

		if (accion="add") 	{
				if ((valorSuma + valorObj)<=PORCENTAJEMAXIMO)  {
					return(true);
				}
				else {
					valorTope = PORCENTAJEMAXIMO - valorSuma;
					alert("No puede asignar un valor mayor a " + valorTope);
					return(false);
				}
		}

	}
}




function jsImprimir() 
{	bV = parseInt(navigator.appVersion);
	if (bV >= 4) 
		window.print();
}

function jsVolver() 
{	history.back();
}


/*======================================================================================
Descripción		: Retorna boolean si el rut es válido
Entrada			: rut, dv
Salida			: boolean
Autor			: malvarez - Soluziona Chile S.A.
Fecha			: 30-04-2004
Modificacion	:
*/

function validarRutImputado(objRut, objDig) {
	if (trim(objRut).value=="" || trim(objDig).value=="")  {    	 
		 return(false); 
	}	
	if (!ChequearRutDigito(objRut,objDig)) {
		return(false); 
	}
	return(true);
}



/*======================================================================================
Descripción		: Seteo de hiddens que contienen los nombres de algunos items de consulta
Entrada			: formulario
Salida			: Ninguna
Autor			: malvarez - Soluziona Chile S.A.
========================================================================================*/

function setParamInformes(f) {

	if (f.regiones[f.regiones.selectedIndex].value!= -1) {
		f.region.value = f.regiones[f.regiones.selectedIndex].text;
	}
	
	if (f.tribunales[f.tribunales.selectedIndex].value!= -1) {
		f.tribunal.value = f.tribunales[f.tribunales.selectedIndex].text;	
	}
	
	if (f.defensorias[f.defensorias.selectedIndex].value!= -1) {
		f.defensoria.value = f.defensorias[f.defensorias.selectedIndex].text;
	}
	
	if (f.defensores[f.defensores.selectedIndex].value!= -1) {
		f.defensor.value = f.defensores[f.defensores.selectedIndex].text;
	}

	if (f.defensores[f.defensores.selectedIndex].value!= -1 && f.defensorias[f.defensorias.selectedIndex].value== -1)  {
			idDefensoria = getCadenaDesdeVector(cantidadForaneas, 'foraneaId', 'foraneaIdBase',f.defensores[f.defensores.selectedIndex].value);
			nombDefensoria = getCadenaDesdeVector(cantidadDefensorias, 'defensoriaId', 'defensoriaNombre',idDefensoria);

			f.defensor.value = f.defensor.value + " ( Defensoria:  " + nombDefensoria + ")";
	}
		
}


/*======================================================================================
Descripción		: Extrae de un vector el valor deseado
Entrada			: largoVector, campos conocidos, id a buscar
Salida			: String
Autor			: malvarez - Soluziona Chile S.A.
========================================================================================*/
function getCadenaDesdeVector(largoVector, campoIndice, campoBuscar, idBuscado) {
		var cadena = "";
		largo = new Number(largoVector);

		for(var c=0; c<new Number(largo); c++) {
				idCampoIndice = new Number(eval(campoIndice + "[" + c  + "]"));
				if (idCampoIndice == idBuscado) {
					cadena = eval(campoBuscar + "[" + c  + "]");
					break;
				}
		}

		return (cadena);
}


/*======================================================================================
Descripción		: Limpia en forma conjunta varios objetos de los informes
Entrada			: formulario
Salida			: Ninguna
Autor			: malvarez - Soluziona Chile S.A.
========================================================================================*/

function cleanParamInformes(f) {
	f.region.value = "";
	f.tribunal.value = "";	
	f.defensoria.value = "";
	f.defensor.value = "";
}



/*======================================================================================
Descripción		: Asigna a un valor determinado a un objeto
Entrada			: objeto y valor
Salida			: Ninguna
Autor			: malvarez - Soluziona Chile S.A.
========================================================================================*/

function jsSetValor(obj, valor) {
	obj.value = valor;
}


/*======================================================================================
Descripción		: Da el formato de RUD Defensoria Penal a un string válido
Entrada				: objeto
Salida				: obj formateado
Autor					: malvarez - Soluziona Chile S.A.
========================================================================================*/

function formatearRud(objRud){
	strRud = objRud.value;
	myString = new String(strRud);
	var strRudSinGuion = myString.replace(/-/g,"");
	var guion = "-";

	strAuxArray = new Array(0,0,0);
	if (strRudSinGuion.length == 10) {	
		strAuxArray[0]=strRudSinGuion.substring(strRudSinGuion.length-2,strRudSinGuion.length);
		strAuxArray[1]=strRudSinGuion.substring(strRudSinGuion.length-7,strRudSinGuion.length-2);
		strAuxArray[2]=strRudSinGuion.substring(0,strRudSinGuion.length-7); 
		
		if (SoloTipo(new String(strAuxArray[0]),'9') && SoloTipo(new String(strAuxArray[1]),'9') && SoloTipo(new String(strAuxArray[2]),'AL')) {
			objRud.value = strAuxArray[2] + guion + strAuxArray[1] + guion + strAuxArray[0];
		}
	}
}

/*======================================================================================
Descripción		: Asigna a la barra de estado un mensaje
Entrada				: objeto
Salida				: ninguno
Autor					: malvarez - Soluziona Chile S.A.
========================================================================================*/
function jsTextoBarraEstado(texto) {
	 cadena = new String(texto);
	 window.status=cadena;

	 /*texto = texto.substring(1, texto.length)  + texto.substring(0, 1);
     setTimeout("jsTextoBarraEstado(document.causaForm.paso)", 150); */
}

//Funcion que sirve para desplegar texto de un select en la Barra de Estado
function jsVerTextoSelect(fbox) {
		for (var i=0; i<fbox.options.length; i++) 	{
			if (fbox.options[i].selected && fbox.options[i].value != "")  {	
					jsTextoBarraEstado(fbox.options[i].text);
			}
		}
}



function IsRut(theRut,dvr)
{
	if(ChequearRutDigito(theRut,dvr)){
		return true;
	}else{
		alert("Ingrese un rut válido")
		theRut.value="";
		dvr.value="";
		theRut.focus();
		return false;
	}
}


function calculaDigitoVerif(obj_rut,obj_dv){

  var rut = obj_rut.value;  
  var dvr = '0'
  var tmpstr="";
  var suma = 0
  var mul  = 2
  
  if ( rut.length < 2 )
  {
    alert("Debe ingresar un Rut del Socio completo.")
    obj_rut.select();
    obj_dv.value = "";
    obj_rut.focus();
    return false;
  } 

  for ( i=0; i < rut.length ; i++ )
	    if ( rut.charAt(i) != ' ' && rut.charAt(i) != '.' && rut.charAt(i) != '-' )
	      tmpstr = tmpstr + rut.charAt(i);
	  
  for (i= tmpstr.length-1  ; i >= 0; i--)
  {
    suma = suma + tmpstr.charAt(i) * mul
    if (mul == 7)
      mul = 2
    else    
      mul++
  }


  res = suma % 11
  if (res==1)
    dvr = 'k'
  else if (res==0)
    dvr = '0'
  else
  {
    dvi = 11-res
    dvr = dvi + ""
  }

  var texto = "";
  texto = tmpstr;	
    
  largo = texto.length;


  var invertido = "";

  for ( i=(largo-1),j=0; i>=0; i--,j++ )
    invertido = invertido + texto.charAt(i);

  var dtexto = "";

  cnt = 0;

for ( i=0,j=2; i<largo; i++,j++ )
  {
    if ( cnt == 3 )
    {
     // dtexto = dtexto + '.';
      j++;
	  dtexto = dtexto + invertido.charAt(i);
      cnt = 1;
    }
    else
    { 
      dtexto = dtexto + invertido.charAt(i);
      cnt++;
    }
  }

  invertido = "";

  for ( i=(dtexto.length-1),j=0; i>=0; i--,j++ ){
		invertido = invertido + dtexto.charAt(i);
		obj_rut.value = invertido;  
	}

  obj_rut.value = invertido;  

  obj_dv.value=dvr.toUpperCase();

}


var nav4 = window.Event ? true : false;
function acceptNum(evt){	
	//TEST
	// NOTE: Backspace = 8, Enter = 13, '0' = 48, '9' = 57	
	var key = nav4 ? evt.which : evt.keyCode;	
	return (key <= 13 || (key >= 48 && key <= 57));
}

