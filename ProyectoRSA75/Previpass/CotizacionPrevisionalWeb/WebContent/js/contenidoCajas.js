/*************************************
+
+		Aplicacion Cliente
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

//llamar en cada onkeyup de cada caja de texto de un formulario, 
//si se ingresa algo que no sea letra, espacio => se elimina de la caja de texto
//transforma acentos y ñ
//newVals agrega valores permitidos
function soloTexto(objCampo, newVals)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ " + newVals;
	validaFormato(objCampo,vals,true,true);
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

function limpiaNumero(texto, newVals)    //---> Se usa???
{
	var vals="1234567890" + newVals;
	for (i = texto.length - 1; i >= 0; i--)
	{
		if (vals.indexOf(texto.charAt(i)) == -1)
			texto = texto.substr(0, i) + texto.substr(i + 1, texto.length - 1);
	}
	return texto;
}

//llamar en cada onkeyup de cada caja de texto de un formulario, 
//si se ingresa algo que no sea letra, @, . => se elimina de la caja de texto
//transforma acentos y ñ
//newVals agrega valores permitidos
function soloEmail(objCampo)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ.@0123456789_\-";
	validaFormato(objCampo,vals,true,true);
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
//	soloDescripcion(descripcion);
	return true;
}

function soloRazonSocial(objCampo)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.Ñ-_ &'";
	validaFormato(objCampo,vals,true,true);
}

function validaRazonSocial(razonSocial)
{
	//soloRazonSocial(razonSocial);
	return true;
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

function soloNombreEntidad(objCampo)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.Ñ-_ &'";
	validaFormato(objCampo,vals,true,true);
}

function validaNombreEntidad(NombreEnt)
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

function validaNumero(numero)
{
	//soloNumero(numero);
	return true;
}

//llamar en cada onkeyup de cada caja de numero real de un formulario, 
//si se ingresa algo que no sea numero o ',' => se elimina de la caja de texto
//newVals agrega valores permitidos
function soloReal(objCampo)
{
	var vals="1234567890,";
	validaFormato(objCampo,vals,false,true);
}

function validaReal(real)
{
	//soloReal(real);
	return true;
}

function valLimiteNumero(monto, limite, formatLimite, txt)
{
	if (monto > limite)
		return " - " + txt + " debe ser menor a " + formatLimite + ". \n";
	return "";
}

function valLimiteMonto(txt, monto)
{
	return valLimiteNumero(monto, 99999999, '99.999.999', txt);
}
function validaFormatoCelular(valor){
	if(valor.length>7 && valor.length<10)
		return true;
	else
		return false;
}
