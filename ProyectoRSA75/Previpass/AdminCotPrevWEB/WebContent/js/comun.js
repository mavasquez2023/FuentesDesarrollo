/*
 * Funciones generales de validacion
 */
 if (parent.user && parent.user.location != null)
	parent.user.location.reload();

	//Esta funcion debe ponerse en el evento onSubmit del form: onsubmit="return onFormSubmit(this)"
	//Retorna el valor de verdad de la funcion validaFormulario(form), que debe estar definida en cada
	//formulario. Se le pasa como parametro el form que gatillo el evento.
	//validaFormulario(form) debe retornar true si no hubo errores de validacion y false en caso contrario. Ademas
	//debe mostrar los alert correspondientes al usuario.
	function onFormSubmit(formulario) 
	{
		if (bCancel == true)
			return true;
		return validaFormulario(formulario);
	}
	
	//Declaracion de variable para poder diferenciar correctamente html submit de html cancel
	var bCancel = false;

	//Retorna verdadero si el campo esta presente.
	function validaReq(field) 
	{
	 if (field!=null)
	 {
		with (field) 
		{
			if ((value == null) || (trim(value) == ""))
				return false;
			else
				return true;
		}
	  }
	  return false;
	}

	function trim(cadena)
	{
		for(i = 0; i < cadena.length; )
		{
			if(cadena.charAt(i) == " ")
				cadena = cadena.substring(i+1, cadena.length);
			else
				break;
		}
		for(i = cadena.length-1; i >= 0; i = cadena.length-1)
		{
			if(cadena.charAt(i) == " ")
				cadena=cadena.substring(0,i);
			else
				break;
		}
		return cadena;
	}

	//Retorna verdadero si el string sFecha contiene una fecha valida en formato "dd/mm/yyyy"
	function validaFecha(sFecha) 
	{
		var datos = sFecha.split("/");
		if (datos[0].length == 1)
			datos[0] = "0" + datos[0];
		if (datos[1].length == 1)
			datos[1] = "0" + datos[1];
		sFecha = datos[0] + "/" + datos[1] + "/" + datos[2];
		var regex = /^\d{2}\/\d{2}\/\d{4}$/
		if (!regex.test(sFecha))
			return false;

		var dia  = sFecha.split("/")[0];
		var mes  = sFecha.split("/")[1];
		var year = sFecha.split("/")[2];
		var anno = new Number(year);
		if (dia < 1 || dia > 31)
			return false;
		if (mes < 1 || mes > 12)
			return false;
		if (year < 1970 || year > 2050)
			return false;

		var dateObj = new Date(year, mes - 1, dia);

		if ((dateObj.getDate() != dia) || (dateObj.getMonth() + 1 != mes) || (dateObj.getFullYear() != year))
			return false;
		return true;
	}
	
	//Valida que el string sRut contenga un rut valido segun el formato: 99[.]999[.]999[-]9|k|K o rut sin formato
	function validaRut(sRut) 
	{
		if(sRut.length < 10) sRut = '0' + sRut;
		var regex = /^\d{1,3}\.?\d{3}\.?\d{3}-?[\dkK]$/
		if (regex.test(sRut))
			return true;
	}
	
	//Valida el digito verificador del rut en el string sRut
	function validaDV(sRut) 
	{
		if(sRut.length < 10) sRut = '0' + sRut;
		var rut = sRut.replace(/\./g, "");
		if (rut.indexOf("-") != -1) 
		{//Rut con guion		
			var num = rut.split("-")[0];
			var dig = rut.split("-")[1].toUpperCase();
		} else 
		{//Rut sin guion
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
		var regex = /[^A-Za-z0-9\., ]/
		return !regex.test(sTxt) && validaNoEspacios(sTxt);
	}
	
	//Valida que el string sTxt contenga solo caracteres validos para nombres
	function validaNombres(sTxt) 
	{
		var regex = new RegExp("/[^A-Z\.-' ]/");
		return !regex.test(sTxt) && validaNoEspacios(sTxt);
	}

	//Valida que el string sTxt no sea una secuencia de espacios
	function validaNoEspacios(sTxt) 
	{
		var regex = /^ +$/
		return !regex.test(sTxt);
	}	
	
	//Valida que el string sTxt pueda ser parseado como un entero sin signo
	function validaUInt(sTxt) 
	{
		var regex = /^\d+$/
		return regex.test(sTxt);
	}

	function imprimir()
	{
		window.print();
	}
		
	function poneFoco(id)
	{
		parent.BODY.document.getElementById(id).focus();
	}

function abrirDocImpresion(url) 
{
	target = "_blank";
	window.open(url, target, "height=600,width=650,toolbar=no,directories=no,"
		+ "scrollbars=yes,status=no,linemenubar=no,resizable=yes,modal=yes");
}