/*************************************
+
+		Aplicacion Admin
+
***************************************/

function validaFormato(objCampo,vals,cambio,minus)
{
	var texto = objCampo.value;
	var marca=-1;
	if (minus)
	{
		for (j = texto.length - 1; j >= 0; j--)
	    	if (texto.charAt(j).match(/[a-zñáéíóúÁÉÍÓÚ]/) && marca == -1)
	            marca = j + 1;
	  	texto = objCampo.value.toUpperCase();
	}
	
	var cuenta=0;
    for (i = texto.length - 1; i >= 0; i--)
    {
		if (cambio)
        	texto=texto.substr(0, i)+Reemplazo(texto.charAt(i))+texto.substr(i + 1, texto.length - 1);

         if (vals.indexOf(texto.charAt(i)) == -1)
         {
			texto = texto.substr(0, i) + texto.substr(i + 1, texto.length - 1);
            if (cuenta==0)
			marca = i;
         }
    }

	if (marca!=-1)
   	{
    	objCampo.value = texto;
        setSelecRange(objCampo,marca,marca);
   	}
}

function validaFormatoMinuscula(objCampo,vals,cambio,minus)
{
	var texto = objCampo.value;
	var marca=-1;
	if (minus)
	{
		for (j = texto.length - 1; j >= 0; j--)
	    	if (texto.charAt(j).match(/[a-zñáéíóúÁÉÍÓÚ]/) && marca == -1)
	            marca = j + 1;
	  	texto = objCampo.value;
	}
	
	var cuenta=0;
    for (i = texto.length - 1; i >= 0; i--)
    {
		if (cambio)
        	texto=texto.substr(0, i)+Reemplazo(texto.charAt(i))+texto.substr(i + 1, texto.length - 1);

         if (vals.indexOf(texto.charAt(i)) == -1)
         {
			texto = texto.substr(0, i) + texto.substr(i + 1, texto.length - 1);
            if (cuenta==0)
			marca = i;
         }
    }

	if (marca!=-1)
   	{
    	objCampo.value = texto;
        
   	}
}


function setSelecRange(input, selectionStart, selectionEnd)
{
	if (input.setSelectionRange) 
	{
	   	input.focus();
		input.setSelectionRange(selectionStart, selectionStart);
 	} else if (input.createTextRange) 
 	{
   		var range = input.createTextRange();
   		range.collapse(true);
   		range.moveEnd('character', selectionEnd);
   		range.moveStart('character', selectionStart);
   		range.select();
 	}
}

function Reemplazo(caracter)
{
	if (caracter == 'Á')
			caracter= 'A';
		else if (caracter == 'É')
			caracter= 'E';
		else if (caracter == 'Í')
			caracter= 'I';
		else if (caracter == 'Ó')
			caracter= 'O';
		else if (caracter == 'Ú')
			caracter= 'U';
	return caracter;
}

//llamar en cada onkeyup de cada caja de RUT de un formulario, 
//si se ingresa algo que no sea numero . - k => se elimina de la caja de texto
function soloRut(objCampo)
{
	var vals="1234567890kK-.";
	validaFormato(objCampo,vals,false,true);
}

//llamar en cada onkeyup de cada caja de monto de un formulario, 
//si se ingresa algo que no sea numero o '.' => se elimina de la caja de texto
//newVals agrega valores permitidos
function soloMonto(objCampo, newVals)
{
	var vals="1234567890." + newVals;
	validaFormato(objCampo,vals,false,true);
}

// llamar en cada onkeyup de caja de Nombre de trabajador.
function soloNombre(objCampo)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ. "
	validaFormato(objCampo,vals,false,true);
}


//llamar en cada onkeyup de cada caja de texto de un formulario, 
//si se ingresa algo que no sea letra, espacio o numero => se elimina de la caja de texto
//transforma acentos y ñ
//newVals agrega valores permitidos
function soloAlfanumerico(objCampo, newVals)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 " + newVals;
	validaFormato(objCampo,vals,true,true);
}
function soloAlfanumericoMun(objCampo, newVals)
{
	var vals = "abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 " + newVals;
	validaFormatoMinuscula(objCampo,vals,true,true);
}

function soloAviso(objCampo, newVals)
{
	var vals = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890 abcdefghijklmnñopqrstuvwxyz" + newVals;
	validaFormato(objCampo,vals,true,false);
}

//llamar en cada onkeyup de cada caja de texto de un formulario, 
//si se ingresa algo que no sea letra, espacio o numero => se elimina de la caja de texto
//transforma acentos y ñ
//newVals agrega valores permitidos
//PERMITE EL INGRESO DE MINUSCULAS
function soloAlfaNumMinusc(objCampo, newVals)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 " + newVals;
	validaFormato(objCampo,vals,true,false);
}

//restringe a 'largoMax' el contenido de un text area
function cuentaTxtArea(obj, largoMax)
{
	var valor = obj.value;
	if (valor.length > largoMax)
		obj.value = valor.substr(0, largoMax);
}


//llamar en cada onkeyup de cada caja de texto de un formulario, 
//si se ingresa algo que no sea letra, @, . => se elimina de la caja de texto
//transforma acentos y ñ
//newVals agrega valores permitidos
function soloEmail(objCampo)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZÑ\/.@0123456789_-";
	validaFormato(objCampo,vals,true,true);
}

function soloEmailMinuscula(objCampo)
{
	var vals = "abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZÑ\/.@0123456789_-";
	validaFormatoMinuscula(objCampo,vals,true,true);
}


function validaMail(valor)
{
	re = /^([a-zA-Z0-9_\-])+([a-zA-Z0-9_\.\-])*\@([a-zA-Z0-9_\-])+([a-zA-Z0-9_\.\-])+$/;
	return re.test(valor);
}

function soloTelefonoFax(objCampo)
{
	var vals="1234567890()-";
	validaFormato(objCampo,vals,false,true);
}

function validaTelefonoFax(valor)
{
	re = /\([0-9]+\)[0-9]{6}/;  
	return re.test(valor);
}

function soloDireccion(objCampo)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.-()Ñ ";
	validaFormato(objCampo,vals,true,true);
}

function validaDireccion(direccion)
{
	return true;
}

function soloNombres(objCampo)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ.'Ñ -";
	validaFormato(objCampo,vals,true,true);
}
function soloNombresMun(objCampo)
{
	var vals = "abcdefghijklmnopqrstuvwxyzñABCDEFGHIJKLMNOPQRSTUVWXYZ.'Ñ -";
	validaFormatoMinuscula(objCampo,vals,true,true);
}

function validaNombres(nomApe)
{
	return true;
}

function soloDescripcion(objCampo)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.Ñ-_ '&";
	validaFormato(objCampo,vals,true,true);
}

function validaDescripcion(descripcion)
{
	return true;
}

function soloRazonSocial(objCampo)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.Ñ-_ &'";
	validaFormato(objCampo,vals,true,true);
}

function validaRazonSocial(razonSocial)
{
	var regex = new RegExp("/[^A-Z1-9\.-_&' ]/");
	var regex2 = /^ +$/
	try
  	{
		return !regex.test(razonSocial) && !regex2.test(razonSocial);
	} catch (err)
	{
		alert(":" + err.description + "::");
	}
}

function soloNombreSuc(objCampo)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.Ñ-_ &'";
	validaFormato(objCampo,vals,true,true);
}

function validaNombreSuc(NombreSuc)
{
	return true;
}

//llamar en cada onkeyup de cada caja de numero entero de un formulario, 
//si se ingresa algo que no sea numero => se elimina de la caja de texto
//newVals agrega valores permitidos
function soloNumero(objCampo)
{
	var vals="1234567890";
	validaFormato(objCampo,vals,false,true);
}
function soloEntero(objCampo)
{
	var vals="1234567890-";
	validaFormato(objCampo,vals,false,true);
}

function validaNumero(numero)
{
	for (j = numero.length - 1; j >= 0; j--)
		if (!numero.charAt(j).match(/[0-9]/))
			return false;
	return true;
}

function validaReal(real)
{
	return true;
}

function soloDecimales(objCampo)
{
	var vals="1234567890,";
	validaFormato(objCampo,vals,false,true);
}

function soloFecha(objCampo)
{
	var vals="1234567890/";
 	validaFormato(objCampo,vals,false,true);
}

function soloHora(objCampo)
{
	var vals="1234567890: ";
	validaFormato(objCampo,vals,false,true);
}

function soloHoraFecha(objCampo)
{
	var vals="1234567890/: ";
	validaFormato(objCampo,vals,false,true);
}

function validaFormatoCelular(valor)
{
	if(valor.length > 7 && valor.length < 10)
		return true;
	else
		return false;
}

function soloFechaG(objCampo)
{
	var vals="1234567890-";
	validaFormato(objCampo,vals,false,true);
}

function valida_Rut(Objeto)
{
	var tmpstr = "";
	var intlargo = Objeto.value
	if (intlargo.length > 0)
	{
		crut = Objeto.value
		largo = crut.length;
		if (largo < 2)
		{
			Objeto.focus()
			return false;
		}
		for (i = 0; i < crut.length; i++)
			if ( crut.charAt(i) != ' ' && crut.charAt(i) != '.' && crut.charAt(i) != '-' )
				tmpstr = tmpstr + crut.charAt(i);
		rut = tmpstr;
		crut = tmpstr;
		largo = crut.length;
	
		if (largo > 2)
			rut = crut.substring(0, largo - 1);
		else
			rut = crut.charAt(0);
	
		dv = crut.charAt(largo-1);
	
		if ( rut == null || dv == null )
			return 0;
	
		var dvr = '0';
		suma = 0;
		mul  = 2;
	
		for (i= rut.length-1 ; i>= 0; i--)
		{
			suma = suma + rut.charAt(i) * mul;
			if (mul == 7)
				mul = 2;
			else
				mul++;
		}
	
		res = suma % 11;
		if (res==1)
			dvr = 'k';
		else if (res==0)
			dvr = '0';
		else
		{
			dvi = 11-res;
			dvr = dvi + "";
		}
	
		if ( dvr != dv.toLowerCase() )
			return false;
		return true;
	}
}

function limpiaRutIncremental(texto)
{
	var vals="1234567890";
	for (i = texto.length - 1; i >= 0; i--)
	{
		if (vals.indexOf(texto.charAt(i)) == -1)
			texto = texto.substr(0, i) + texto.substr(i + 1, texto.length - 1);
	}
	return texto;
}


function soloCtaCte(objCampo)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.-()Ñ ";
	validaFormato(objCampo,vals,true,true);
}

