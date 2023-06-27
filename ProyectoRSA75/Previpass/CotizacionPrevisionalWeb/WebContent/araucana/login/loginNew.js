
	//Validacion de formulario
	function validaFormulario(f) 
	{
		var sMsje = "";
		if (!validaReq(document.getElementById("j_username")))
			sMsje += "* Debe ingresar el RUT del usuario.\n";

		if (sMsje == "" && !validaRut(document.getElementById("j_username").value))
				sMsje += "* Formato de RUT de usuario inválido.\n";

		if (sMsje == "" && !validaDV(document.getElementById("j_username").value))
				sMsje += "* Dígito verificador inválido para el RUT del usuario.\n";

		if (sMsje == "" && document.getElementById("j_password").value == "")
				sMsje += "* Debe ingresar su Password.\n";

		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			document.getElementById("j_username").focus();
			return false;
		}
		return true;
	}

	//Validacion de formulario
	function onFormSubmit(formulario) 
	{
		if (bCancel == true)
			return true;
		return validaFormulario(formulario);
	}
	
	//Retorna verdadero si el campo esta presente.
	function validaReq(field) 
	{
		with (field) 
		{
			if ((value == null) || (value == ""))
				return false;
			else
				return true;
		}
	}

	//Valida que el string sRut contenga un rut valido segun el formato: 99[.]999[.]999[-]9|k|K o rut sin formato
	function validaRut(sRut) 
	{
		if(sRut.length < 9) sRut = '00' + sRut;
		else if(sRut.length < 10) sRut = '0' + sRut;
		var regex = /^\d{1,3}\.?\d{3}\.?\d{3}\-[\dkK]$/
		if (regex.test(sRut))
			return true;
	}
	
	//Valida el digito verificador del rut en el string sRut
	function validaDV(sRut) 
	{
		var rut = sRut.replace(/\./g, "");
		if (rut.indexOf("-") != -1)
		 {
			//Rut con guion		
			var num = rut.split("-")[0];
			var dig = rut.split("-")[1].toUpperCase();
		} else 
		{
			//Rut sin guion
			var num = rut.substr(0, rut.length - 1);
			var dig = rut.substr(rut.length - 1).toUpperCase();
		}
		
		var s = 0;
		var m = 2;
		for (var i = num.length - 1; i >= 0; i--) 
		{
			s = s + m++*num.charAt(i);
			if (m == 8) m = 2;
		}

		var d = 11 - (s % 11);
		if (d == 10)
			d = "K";
		else if (d == 11)
			d = "0";

		if (dig != d)
			return false;

		return true;
	}
	
	//Valida que el string sTxt contenga solo caracteres validos para la base de datos de Araucana
	function validaChrs(sTxt) 
	{
		var regex = /[^A-Za-z0-9\.,]/
		return !regex.test(sTxt) && validaNoEspacios(sTxt);
	}

	//Valida que el string sTxt no sea una secuencia de espacios
	function validaNoEspacios(sTxt) 
	{
		var regex = /^ +$/
		return !regex.test(sTxt);
	}

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

