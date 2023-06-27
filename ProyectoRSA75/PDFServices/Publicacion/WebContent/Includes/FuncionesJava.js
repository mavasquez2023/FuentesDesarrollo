function cantidaddias(anomes){
	
	hoy = new Date(anomes.substring(0, 4), anomes.substring(4, 6), 01);
	
	// Reemplazar (1970,01,01) por la fecha de inicio de tu sitio (aaaa,mm,dd)
	inicio = new Date(1970,01,01) 

	resta = hoy.getTime() - inicio.getTime();
	resultado = Math.floor(resta/(1000*60*60*24)) + 1;
	return resultado;
}

function CheckDate(THISDATE) {  //esta funcion chequea si una fecha es valida, 
	var err="no";          // formatos ddmmaaaa  dd/mm/aaaa dd-mm-aaaa
	err = "no";
	a=THISDATE.substring(0, 2) + THISDATE.substring(6, 8) + THISDATE.substring(2, 6);
	if (a.length == 10){
		d = a.substring(0, 2);   // dia
		c = a.substring(2, 3);   // '/'
		b = a.substring(3, 5);   // mes
		e = a.substring(5, 6);   // '/'
		f = a.substring(6, 10);  // ano
	}
	else{
		d = a.substring(0, 2);   // dia
		c = "";   // '/'
		b = a.substring(2, 4);   // mes
		e = "";   // '/'
		f = a.substring(4, 8);  // ano
	}

	//alert("dia: " + d + " Mes: " + b + " año: " + f);
	if (b<1 || b>12){
		err = "si";
	}
	if (d<1 || d>31){
		err = "si";
	}
	if (f < 1900){
		err = "si";
	}
	if (b==4 || b==6 || b==9 || b==11){
		if (d==31) {
			err = "si";
		}
	}
	if (b==2){
		var g=parseInt(f/4);
		if (isNaN(g)) {
			err = "si";
		}
		if (d > 29) {
			err = "si";
		}
		if (d==29 && ((f/4)!=parseInt(f/4))) {
			err = "si";
		}
	}	
	texto=d+b+f
	for (var i = 0; i < texto.length; i++) 
	{
		var ch = texto.substring(i, i + 1);
		if ((ch < "0" || "9" < ch) && ch != '.') 
		{
			err = "si";
		}
	}
	return err;
}



function JStrToFecha(Fecha){
	var Ciclo;
	var DMA;
	var valor,tmp;
	if (Fecha == ""){return;}
	A = 0;
	M = 0;
	D = 0;
	DMA = 0;
	tmp = "";
	floc = new String(Fecha);
	if (floc.indexOf("/") > 0 || floc.indexOf("-") > 0){
		for (Ciclo = 0 ; Ciclo <= floc.length; Ciclo++){
			if (floc.substr(Ciclo,1) == "/" || floc.substr(Ciclo,1) == "-"){
				switch(DMA){
					case 0:
						if(tmp.length == 1)
							D = "0" + tmp;
						else
							D = tmp;
						tmp = "";
						break;
					case 1:
						if(tmp.length == 1)
							M = "0" + tmp;
						else
							M = tmp;
						tmp = "";
						break;
				}
				DMA = DMA + 1;
			}
			else
				tmp = tmp + floc.substr(Ciclo,1);
		}
		if (tmp.length < 4)
			A = "20" + tmp;
		else
			A = tmp;
		valor = A + M + D;
	}
	else{
		valor = floc.substr(6,2) + "/";
		valor = valor + floc.substr(4,2) + "/";
		valor = valor + floc.substr(0,4);
	}
	return valor;
}

function JStrToHora(Hora){
	var Ciclo;
	var DMA;
	var valor,tmp;
	if (Hora == ""){return;}
	S = 0;
	M = 0;
	H = 0;
	DMA = 0;
	tmp = "";
	tmp = "";
	floc = new String(Hora);
	if (floc.indexOf(":") > 0){
		for (Ciclo = 0 ; Ciclo <= floc.length; Ciclo++){
			if (floc.substr(Ciclo,1) == ":"){
				switch(DMA){
					case 0:
						if(tmp.length == 1)
							H = "0" + tmp;
						else
							H = tmp;
						tmp = "";
						break;
					case 1:
						if(tmp.length == 1)
							M = "0" + tmp;
						else
							M = tmp;
						tmp = "";
						break;
				}
				DMA = DMA + 1;
			}
			else
				tmp = tmp + floc.substr(Ciclo,1);
		}
		if (tmp.length == 1)
			S = "0" + tmp;
		else
			S = tmp;
		valor = H + M + S;
	}
	else{
		valor = floc.substr(0,2) + ":";
		valor = valor + floc.substr(3,2) + ":";
		valor = valor + floc.substr(5,2);
	}
	return valor;
}

function Mes(M){
	var valor;
	switch(M){
		case 1:
			valor = "Enero";
			break;
		case 2:
			valor = "Febrero"
			break;
		case 3:
			valor = "Marzo";
			break;
		case 4:
			valor = "Abril";
			break;
		case 5:
			valor = "Mayo";
			break;
		case 6:
			valor = "Junio";
			break;
		case 7:
			valor = "Julio";
			break;
		case 8:
			valor = "Agosto";
			break;
		case 9:
			valor = "Septiembre";
			break;
		case 10:
			valor = "Octubre";
			break;
		case 11:
			valor = "Noviembre";
			break;
		case 12:
			valor = "Diciembre";
			break;
	}
}

function NewWindow(mypage, myname, alto, ancho){
  if (alto == 0){
	alto = (screen.height - 2) ;
  }else{
	alto = alto + 86 ;
  }

  if (ancho == 0){
	ancho = (screen.width - 2) ;
  }
  //else{
  //  ancho = ancho + 10;
  //}
	
 
  var winl = (screen.width - ancho) / 2;
  var wint = (screen.height - alto) / 2;
  winprops = 'height=' + alto + ',width=' + ancho + ',top='+wint+',left='+winl+',toolbar=no,scrollbars=no,resizable=no,menubar=no';
  win = window.open(mypage, myname, winprops);

  if (parseInt(navigator.appVersion) >= 4){
	 win.window.focus();
  }
  return win;
}

function sacagatos(palabra){
	var pal="";
	pal = palabra;
	pal = pal.replace(/#/g,"Ñ");
	return pal
}

function JDigitoRut(numero){
  var suma=0;
  var mul=2;
  var i=0;
  for (i=numero.length-1;i>=0;i--){
    suma=suma+numero.charAt(i) * mul;
    mul= mul==7 ? 2 : mul+1;
  }
  var dvr = ''+(11 - suma % 11);
  if (dvr=='10') dvr = 'K';
  else if (dvr=='11') dvr = '0';
  return(dvr);
}

function JValidaRUT(valorut) {  //al ingresar el valor chequea digVer con el                                
  var digi ="";                 //ultimo valor del string que es el DV
  var i;
  valorut.select();
  if (valorut.value.length-1 != 0) {
	var DigVer = valorut.value.substring(valorut.value.length-1,valorut.value.length);
	var digitos = valorut.value.substring(0, valorut.value.length-1);
    for (i=0; i < digitos.length-1 ; i++ ){ 
	  if ( valorut.value.charAt(i) !="0" && valorut.value.charAt(i) != "1" && valorut.value.charAt(i) !="2" && valorut.value.charAt(i) != "3" && valorut.value.charAt(i) != "4" && valorut.value.charAt(i) !="5" && valorut.value.charAt(i) != "6" && valorut.value.charAt(i) != "7" && valorut.value.charAt(i) !="8" && valorut.value.charAt(i) != "9" ) 
	  {
	    alert("El valor ingresado debe ser Numérico.");
	    valorut.focus();
	    valorut.select();
	    return;
	  }
     }   
     digi = JDigitoRut(digitos);
     if (digi != DigVer) {
	    alert("El valor del Rut NO Válido o está mal ingresado.");
	    valorut.focus();
	    valorut.select();
	    return;
     }
     return true;
  }
}

function CheckHora(THISHORA) {  //esta funcion chequea si una Hora es valida, 
	var err="no";          // formatos hhmmss  hh:mm:ss
	err = "no";
	a=THISHORA;
	if (a.length == 8){
		d = a.substring(0, 2);   // Hora
		c = a.substring(2, 3);   // '/'
		b = a.substring(3, 5);   // Minuto
		e = a.substring(5, 6);   // '/'
		f = a.substring(6, 8);  // Segundo
	}
	else{
		d = a.substring(0, 2);   // Hora
		c = "";   // '/'
		b = a.substring(2, 4);   // Minuto
		e = "";   // '/'
		f = a.substring(4, 6);  // ano
	}
	if (d > 23) err = "si";
	if (b >59) err = "si";
	if(f > 59) err = "si";
	
	return err;
}

function CheckHora2(THISHORA) {  //esta funcion chequea si una Hora es valida, 
	var err="no";          // formatos hhmmss  hh:mm ó hmm h:mm
	err = "no";
	a=THISHORA;
	if (a.length == 3 || a.length == 3)
		a = "0" + a;
	if (a.length == 5){
		d = a.substring(0, 2);   // Hora
		b = a.substring(3, 5);   // Minuto
	}
	else{
		d = a.substring(0, 2);   // Hora
		b = a.substring(2, 4);   // Minuto
	}
	if (d > 23) err = "si";
	if (b >59) err = "si";

	return err;
}

function JValidaNumero(a) {
	var i;
	for (i=0; i < a.value.length ; i++ )
	{ 
	  if ( a.value.charAt(i) !="0" && a.value.charAt(i) != "1" && a.value.charAt(i) !="2" && a.value.charAt(i) != "3" && a.value.charAt(i) != "4" && a.value.charAt(i) !="5" && a.value.charAt(i) != "6" && a.value.charAt(i) != "7" && a.value.charAt(i) !="8" && a.value.charAt(i) != "9" ) 
	  {
	    alert("El valor ingresado debe ser Numérico.");
	    a.focus();
	    return false;
	  }
	}
	return true;
}

function JMayusculas(Objeto){
	var a = "";
	if (Objeto.value != ""){
		a = Objeto.value;
		Objeto.value = a.toUpperCase( );
	}
}

function JOnFocoFecha(Obj){
	var a=0;
	a = Obj.value;
	if (a.length == 10){
		aux = Obj.value;
		Obj.value = aux.substring(0,2) + aux.substring(3,5) + aux.substring(6,10);
	}
	Obj.select();
}

function JOnDejaFecha(Obj){
	var EsError,a;
	if (Obj.value == ""){
		return;}
	aux = Obj.value;
	if (aux.length != 8){
		alert("Fecha Inválida");
		if (!document.all){
			Obj.value = "";
		}
		Obj.focus();
		Obj.select();
		return;
	}
	EsError=CheckDate(Obj.value)
	if (EsError == "si"){
		alert ("Fecha Inválida");
		if (!document.all){
			Obj.value = "";
		}
		Obj.focus();
		Obj.select();
		return;
	}
	else{
		Obj.value = aux.substring(0,2) + "/" + aux.substring(2,4) +"/" + aux.substring(4,8);
	}
}

function ValidaPatente(Patente)
{
	If (!EsPatente(Patente))
	{
		alert("Ingrese una patente válida.");
		return false;
	}
	return true;
}

function EsPatente(Patente)
{
	if ((Patente.length=6)||(Patente.length=0))
	{
		if (EsNumero(Patente.substr(2,1)))
		{
			var texto = Patente.substr(0,2);
			var num = Patente.substr(2,4);
			if ((EsNumero(num))&&(EsTexto(texto)))
			{
				return true;
			}
			return false;
		}
		if (EsTexto(Patente.substr(2,1)))
		{
			var texto = Patente.substr(0,3);
			var num = Patente.substr(3,3);
			if ((EsNumero(num))&&(EsTexto(texto)))
			{
				return true;
			}
			return false;
		}
		return false;
	}
	else
	{
		return false;
	}
}

function EsTexto(texto)
{
	// Valida que el campo contenga solo caracteres a-z o A-Z
	{
		for (var i = 0; i < texto.length; i++) 
		{
			var ch = texto.substring(i, i + 1);
			if (((ch < "a" || "z" < ch) && (ch < "A" || "Z" < ch))) 
			{
				return false;
			}
		}
	}
    return true;
}
	
function EsNumero(texto)
{
	// Valida que el campo sea un número
	for (var i = 0; i < texto.length; i++) 
	{
		var ch = texto.substring(i, i + 1);
		if ((ch < "0" || "9" < ch) && ch != '.') 
		{
			return false;
		}
	}
	return true;
}

function Jsubstr(str,ini){
  var v='';
  for(i=ini;i<str.length;i++) v+=str.charAt(i);
  return v;
}

function Jsubstring(str,ini,fin){
  var v='';
  var bound=(fin>str.length) ? str.length : fin;
  for(i=ini;i<bound;i++) v+=str.charAt(i);
  return v;
}

function JTrim(str){
  var ini=0;
  var fin=str.length-1;
  while(ini<=fin && str.charAt(ini)==" ") ini++;
  if (ini<=fin){
    while(str.charAt(fin)==" ") fin--;
    if (fin<str.length-1) {
	fin++;
	return Jsubstring(str,ini,fin);
    } else return Jsubstr(str,ini);
  } else return '';
}


function JUpper(txt){
	txt = txt.toUpperCase();
	return (txt);
}

function JLen(txt){
	a = txt.length;
	return (a);
}

function JBlurFecha(Fecha,Inicio,c1,Termino,c2){
// valida que la fecha recibida en formato ddmmaaaa se encuentre entre los rangos recibidos en aaaammdd,
// además de devolver la fecha formateada en dd/mm/aaaa
	var EsError,a;
	if (Fecha.value == ""){
		return;}
	aux = Fecha.value;
	if (JLen(Fecha.value) != 8){
		alert("Fecha Inválida");
		if (!document.all){
			Fecha.value = "";
		}
		Fecha.focus();
		Fecha.select();
		return;
	}
	EsError=CheckDate(Fecha.value)
	if (EsError == "si"){
		alert ("Fecha Inválida");
		if (!document.all){
			Fecha.value = "";
		}
		Fecha.focus();
		Fecha.select();
		return;
	}
	else{
		Fecha.value = aux.substring(0,2) + "/" + aux.substring(2,4) +"/" + aux.substring(4,8);
		if (JTrim(Inicio) != "" || JTrim(Termino) != ""){
			if (JTrim(c1) == ""){
				if (JStrToFecha(Fecha.value) < JTrim(Inicio) && JTrim(Inicio) != ""){
					alert("Fecha debe ser mayor o igual al " + JStrToFecha(Inicio));

					if (!document.all){
						Fecha.value = "";
					}
					Fecha.focus()
					Fecha.select();
					return;
				}
			}else{
				if (JStrToFecha(Fecha.value) <= JTrim(Inicio) && JTrim(Inicio) != ""){
					alert("Fecha debe ser mayor al " + JStrToFecha(Inicio));
					if (!document.all){
						Fecha.value = "";
					}

					Fecha.focus()
					Fecha.select();
					return;
				}

			}
			if (JTrim(c2) == ""){
				if (JStrToFecha(Fecha.value) > JTrim(Termino) && JTrim(Termino) != ""){
					alert("Fecha debe ser menor o igual al " + JStrToFecha(Termino));

					if (!document.all){
						Fecha.value = "";
					}

					Fecha.focus()
					Fecha.select();
					return;
				}
			}else{
				if (JStrToFecha(Fecha.value) >= JTrim(Termino) && JTrim(Termino) != ""){
					alert("Fecha debe ser menor al " + JStrToFecha(Termino));
					if (!document.all){
						Fecha.value = "";
					}


					Fecha.focus()
					Fecha.select();
					return;
				}

			}
		}
	}
}

function JEsp(txt){
	var aux = "";
	aux = txt;
	aux = aux.replace(/ /g,"%20");
	return aux;
}

function JValidaCaracterEnter(Tipo, Adicional, Enter){
		var strNumeros ="0123456789";
		var Minusculas = "abcdefghijklmnñopqrstuvwxyz";
		var Mayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		var strTexto = Minusculas + Mayusculas + " ";
		var strAlfanumerico = strTexto + strNumeros + "/-_,;";
		
		var strMail = Minusculas + Mayusculas + strNumeros + "@_-.";
		
		var TextoTotal = new String();
		TextoTotal = Adicional;
		
		switch(Tipo){
			case "Numerico":{
				TextoTotal += strNumeros;
			break;	
			}
			case "Texto":{
				TextoTotal += strTexto;
			break;	
			}
			case "Alfanumerico":{
				TextoTotal += strAlfanumerico;
			break;	
			}
			case "Email":{
				TextoTotal += strMail;
			break;	
			}
		}
		
		
		strCaracter = new String();
		strCaracter = String.fromCharCode(window.event.keyCode);

		if (Enter) {
			if(window.event.keyCode==13) {
				return true;
			}
		}
		var Pos = TextoTotal.indexOf (strCaracter);
		if(Pos > -1){
			return true;
		}else{
			window.event.keyCode = 0;
			return false;			
		}
			
		
	}



function JValidaCaracter(Tipo, Adicional){
		var strNumeros ="0123456789";
		var Minusculas = "abcdefghijklmnñopqrstuvwxyz";
		var Mayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		var strTexto = Minusculas + Mayusculas + " ";
		var strAlfanumerico = strTexto + strNumeros + "/-_,;";
		
		var strMail = Minusculas + Mayusculas + strNumeros + "@_-.";
		
		var TextoTotal = new String();
		TextoTotal = Adicional;
		
		switch(Tipo){
			case "Numerico":{
				TextoTotal += strNumeros;
			break;	
			}
			case "Texto":{
				TextoTotal += strTexto;
			break;	
			}
			case "Alfanumerico":{
				TextoTotal += strAlfanumerico;
			break;	
			}
			case "Email":{
				TextoTotal += strMail;
			break;	
			}
		}
		
		
				
		strCaracter = new String();
		strCaracter = String.fromCharCode(window.event.keyCode);
		var Pos = TextoTotal.indexOf (strCaracter);
		if(Pos > -1){
			return true;
		}else{
			window.event.keyCode = 0;
			return false;			
		}
			
		
	}

function JValidaCaracter2(Tipo, Adicional, Caracter){
		var strNumeros ="0123456789";
		var Minusculas = "abcdefghijklmnñopqrstuvwxyz";
		var Mayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		var strTexto = Minusculas + Mayusculas + " ";
		var strAlfanumerico = strTexto + strNumeros + "/-_,;";
		
		var strMail = Minusculas + Mayusculas + strNumeros + "@_-.";
		
		var TextoTotal = new String();
		TextoTotal = Adicional;
		
		switch(Tipo){
			case "Numerico":{
				TextoTotal += strNumeros;
			break;	
			}
			case "Texto":{
				TextoTotal += strTexto;
			break;	
			}
			case "Alfanumerico":{
				TextoTotal += strAlfanumerico;
			break;	
			}
			case "Email":{
				TextoTotal += strMail;
			break;
			}
		}
		strCaracter = new String();
		strCaracter = String.fromCharCode(Caracter);
		var Pos = TextoTotal.indexOf (strCaracter);
		if(Pos > -1){
			return true;
		}else{
			//window.event.keyCode = 0;
			return false;			
		}
	}
	

function valiTexto(e) {
	key = "";
	key = e.which;
	tipo = e.id;
	if (	JValidaCaracter2('Texto','',key)  || key == 8) {
		return true;
	}else{
		return false;
	}
}

function valiNumero(e) {
	key = "";
	key = e.which;
	tipo = e.id;
	if (	JValidaCaracter2('Numerico','',key)  || key == 8) {
		return true;
	}else{
		return false;
	}
}

function valiDigito(e) {
	key = "";
	key = e.which;
	tipo = e.id;
	if (	JValidaCaracter2('Numerico','kK',key) || key == 8) {
		return true;
	}else{
		return false;
	}
}

function valiAlfanumerico(e) {
	key = "";
	key = e.which;
	tipo = e.id;
	if (	JValidaCaracter2('Alfanumerico','',key) || key == 8) {
		return true;
	}else{
		return false;
	}
}
 
function valiEmail(e) {
	key = "";
	key = e.which;
	tipo = e.id;
	if (	JValidaCaracter2('Email','',key) || key == 8) {
		return true;
	}else{
		return false;
	}
}

function Captura(Tipo){
	if (navigator.appName == 'Netscape') {
		if (Tipo == "T"){
			window.captureEvents(Event.KEYPRESS);
			window.onKeyPress = valiTexto;
		}
		if (Tipo == "N"){
			window.captureEvents(Event.KEYPRESS);
			window.onKeyPress = valiNumero;
		}
		if (Tipo == "A"){
			window.captureEvents(Event.KEYPRESS);
			window.onKeyPress = valiAlfanumerico;
		}
		if (Tipo == "E"){
			window.captureEvents(Event.KEYPRESS);
			window.onKeyPress = valiEmail;
		}
		if (Tipo == "D"){
			window.captureEvents(Event.KEYPRESS);
			window.onKeyPress = valiDigito;
		}
	}
}

function CambiaImagen( objImagen, Imagen) {
	objImagen.src = Imagen;
}

function OcultarVentana(Ventana) {
	document.all[Ventana].style.display="none";
}

function VerVentana(Ventana) {
	document.all[Ventana].style.display="";
}

function SacaFecha(){
	var co = RSExecute( serverURL , 'GetFec_asp');

	if (co.status == -1) {
		alert('Ocurrió un error al intentar recuperar la fecha del Servidor');
		FechaHoy = "";
	}else{
		FechaHoy = JTrim(co.return_value);
	}
	return FechaHoy;
}

function Fila(valor) {
	if ( Math.floor(valor / 2) == (valor / 2)) {
		return 'FilaPar';
	}
	else {
		return 'FilaImPar';
	}
}

function Alinear(Dato) {
	switch(typeof(Dato.value)) {
		case 'date':
			return 'center';
		case 'number':
			return 'right';
		default:
			return 'left';
	}
}

function Resolucion(){
	//devuelve la resulucion
	//si devuelve 600, entonces 800 x 600
	//si devuelve 768, entonces 1024 x 768
	var two = navigator.appName;
	res = 0;
	if (two != "Microsoft Internet Explorer"){
		res = 600; //si no es explorer, por defecto asume 800 x 600
	}else{
		var res = screen.height;
		res = parseInt(res); // one is the resolution height, if the res is 800 x 600 then one would equal 600
	}
	return res;
}

function FormateaNro(Nro){ // devuelve un numero entero separado por puntos ej: si recibe 999999999 devuelve 999.999.999
	if (isNaN(Nro)){
		return "0";
	}
	if (Nro == 0){
		return "0";
	}
	var elNo = new String();
	elNo = Nro.toString();
	nvoNro = "";
	dig = 1;
	for (un = JLen(elNo)-1; un >= 0; un--){
		nvoNro = elNo.substr(un,1) + nvoNro;
		if (dig == 3 && un !=0){
			nvoNro = "." + nvoNro;
			dig = 1;
		}else{
			dig++;
		}	
	}
	return nvoNro;
}

function rellena(lcDato,lcLargo,lcChar,lcPos){
	//rellena "lcDato" con el caracter "lcChar" para dejarlo de "lcLargo", si lcPos = 1 ==> dato = dato + lcchar
	locData = lcDato;
	if (JTrim(lcDato) != ""){
		for (ig = 1; ig <= (lcLargo - JLen(lcDato)); ig++){
			if (lcPos == 1){
				locData = locData + lcChar;
			}else{
				locData = lcChar + locData;
			}
		}
		return locData;
	}
	return;
}
