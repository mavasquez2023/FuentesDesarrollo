
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

function cambiaDiv(id) 
{
	if (tabActual != id)
		tabActual = id;
	for (i = 0; i < listaDivs.length; i++)
	{
		if (listaDivs[i] == id)
		{
			document.getElementById(id).style.display = 'block';
			document.getElementById(id + 'Td').style.color = '#ff8a00';
		} else
		{
			document.getElementById(listaDivs[i]).style.display = 'none';
			document.getElementById(listaDivs[i] + 'Td').style.color = '#000000';
		}
	}
}

function limpiaFecha(idCajaFecha)
{
	document.getElementById(idCajaFecha).value = "";
}

function validaDataBasicaForm()
{
//campos formulario:
	//newRutTrabajador
	//texto
		//newNombre
		//apellidoPat
		//apellidoMat
	//lista
		//idGenero
		//idSucursal
	var msg = '';
	if (document.getElementById("newRutTrabajador").value == '')
		msg += " - Debe ingresar RUT Trabajador.\n";	
	else if (!validaRut(document.getElementById("newRutTrabajador").value) || !validaDV(document.getElementById("newRutTrabajador").value))
		msg += " - RUT inválido.\n";

	if (document.getElementById("newNombre").value == '')
		msg += " - Debe ingresar un nombre.\n";
	else if (!validaSoloNombre(document.getElementById("newNombre").value))
		msg += " - Nombre contiene caracteres inválidos.\n";

	if (document.getElementById("apellidoPat").value == '')
		msg += " - Debe ingresar un Apellido Paterno.\n";
	else if (!validaSoloTexto(document.getElementById("apellidoPat").value))
		msg += " - Apellido Paterno contiene caracteres inválidos.\n";

	/*if (document.getElementById("apellidoMat").value == '')
		msg += " - Debe ingresar un Apellido Materno.\n";
	else if (!validaSoloTexto(document.getElementById("apellidoMat").value))*/
	if (!validaSoloNombre(document.getElementById("apellidoMat").value))
		msg += " - Apellido Materno contiene caracteres inválidos.\n";

	if (document.getElementById('idSucursal').value == -1)
		msg += " - Debe seleccionar una Sucursal. \n";

	if (document.getElementById('idGeneroReal').value == -1)
		msg += " - Debe seleccionar un Género. \n";
	
	return msg;
}
//validaciones para afiliacion voluntario: trabajador dependiente y voluntario
function validaDataBasicaFormAV()
{
//campos formulario:
	//newRutTrabajador
	//newRutTrabajadorDep
	//texto
		//newNombre
		//apellidoPat
		//apellidoMat
		//newNombreDep
		//apellidoPatDep
		//apellidoMatDep
	//lista
		//idGenero
		//idSucursal
	var msg = '';
	if (document.getElementById("newRutTrabajador").value == '')
		msg += " - Debe ingresar RUT Afiliado Voluntario.\n";	
	else if (!validaRut(document.getElementById("newRutTrabajador").value) || !validaDV(document.getElementById("newRutTrabajador").value))
		msg += " - RUT Afiliado Voluntario inválido.\n";

	if (document.getElementById("newNombre").value == '')
		msg += " - Debe ingresar un Nombre para Afiliado Voluntario.\n";
	else if (!validaSoloTexto(document.getElementById("newNombre").value))
		msg += " - Nombre de Afiliado Voluntario contiene caracteres inválidos.\n";

	if (document.getElementById("apellidoPat").value == '')
		msg += " - Debe ingresar un Apellido Paterno para Afiliado Voluntario.\n";
	else if (!validaSoloTexto(document.getElementById("apellidoPat").value))
		msg += " - Apellido Paterno de Afiliado Voluntario contiene caracteres inválidos.\n";

	if (document.getElementById("apellidoMat").value == '')
		msg += " - Debe ingresar un Apellido Materno para Afiliado Voluntario.\n";
	else if (!validaSoloTexto(document.getElementById("apellidoMat").value))
		msg += " - Apellido Materno de Afiliado Voluntario contiene caracteres inválidos.\n";

//dependiente
	if (document.getElementById("newRutTrabajadorDep").value == '')
		msg += " - Debe ingresar RUT Trabajador Dependiente.\n";	
	else if (!validaRut(document.getElementById("newRutTrabajadorDep").value) || !validaDV(document.getElementById("newRutTrabajadorDep").value))
		msg += " - RUT Afiliado Voluntario inválido.\n";

	if (document.getElementById("newNombreDep").value == '')
		msg += " - Debe ingresar un Nombre para Trabajador Dependiente.\n";
	else if (!validaSoloTexto(document.getElementById("newNombreDep").value))
		msg += " - Nombre de Trabajador Dependiente contiene caracteres inválidos.\n";

	if (document.getElementById("apellidoPatDep").value == '')
		msg += " - Debe ingresar un Apellido Paterno para Trabajador Dependiente.\n";
	else if (!validaSoloTexto(document.getElementById("apellidoPatDep").value))
		msg += " - Apellido Paterno de Trabajador Dependiente contiene caracteres inválidos.\n";

	/*if (document.getElementById("apellidoMatDep").value == '')
		msg += " - Debe ingresar un Apellido Materno para Trabajador Dependiente.\n";
	else if (!validaSoloTexto(document.getElementById("apellidoMatDep").value))*/
	if (!validaSoloNombre(document.getElementById("apellidoMatDep").value))
		msg += " - Apellido Materno de Trabajador Dependiente contiene caracteres inválidos.\n";

	if (document.getElementById('idSucursal').value == -1)
		msg += " - Debe seleccionar una Sucursal. \n";

	if (document.getElementById('idGenero').value == -1)
		msg += " - Debe seleccionar un Género. \n";
	return msg;
}

//llamar en cada onkeyup de cada caja de RUT de un formulario, 
//si se ingresa algo que no sea numero . - k => se elimina de la caja de texto
function soloRut(objCampo)
{
	var vals="1234567890kK-.";
	validaFormato(objCampo,vals,false,true);
}

//llamar en cada onkeyup de cada caja de numero entero de un formulario, 
//si se ingresa algo que no sea numero => se elimina de la caja de texto
//newVals agrega valores permitidos
function soloNumero(objCampo, newVals)
{	
	var vals="1234567890";
	validaFormato(objCampo,vals,false,true);
}

//llamar en cada onkeyup de cada caja de numero real de un formulario, 
//si se ingresa algo que no sea numero o ',' => se elimina de la caja de texto
//newVals agrega valores permitidos
function soloReal(objCampo, newVals)
{
	var vals="1234567890,";
	validaFormato(objCampo,vals,false,true);
}

//llamar en cada onkeyup de cada caja de monto de un formulario, 
//si se ingresa algo que no sea numero o '.' => se elimina de la caja de texto
//newVals agrega valores permitidos
function soloMonto(objCampo, newVals)
{
	var vals="1234567890" + newVals;
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
//si se ingresa algo que no sea letra, espacio => se elimina de la caja de texto
//transforma acentos y ñ
//newVals agrega valores permitidos
function soloNombre(objCampo)
{
	var vals = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ. ";
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

function muestraCalendar(id1, id2, showMore, periodo)
{
	var formato = "%d-%m-%Y";
	var mes = periodo.substring(4, 6);
	var anno = periodo.substring(0, 4);
	showCalendar(id1, id2, formato, showMore, mes, anno);
}

//Valida que el string sTxt sea un numero (con puntos y comas)
function validaNumero(sTxt) 
{
	var regex = /[\d\.\, ]/
	return regex.test(sTxt);
}

function limpiaNumero(texto, newVals)
{
	var vals="1234567890" + newVals;
	for (i = texto.length - 1; i >= 0; i--)
	{
		if (vals.indexOf(texto.charAt(i)) == -1)
			texto = texto.substr(0, i) + texto.substr(i + 1, texto.length - 1);
	}
	return texto;
}

function calcula(monto, porcentaje)
{
	return Math.round(monto * porcentaje);
}

function formatNumero(valor)
{
	var tmp = '';
	var strTmp = '';
	var cero = true;
	var str = new String(valor);
	var negativo = 0;
	if (str.charAt(0) == '-')
	{
		negativo = 1;
		str = str.substring(1, str.length);
	}
	for (i = 0; i < str.length; i++)
	{
		if (str.charAt(i) != '0')
		{
			cero = false;
			tmpStr = str.substring(i,str.length);
			str = tmpStr;
			i = str.length;
		}
	}
	if(cero)
		str = '0';
	count = 0;
	for (i = str.length - 1; i >= 0; i--)
	{
		count++;
		if ((count - 1) % 3 == 0 && (tmp.length > 0))
			tmp = '.' + tmp;
		tmp = str.charAt(i) + tmp;
	}
	if (negativo == 1)
		tmp = '-' + tmp;
	return tmp;
}

function cambiaExCaja()
{
	document.getElementById("tasaCotINP").innerHTML = "0.0 %";
	document.getElementById("prevObligatorioINP").value = 0;
	document.getElementById("idRegimenImp").value = -1;
	document.getElementById("prevObligatorioAFP").value = '0';
	document.getElementById('idTasaTraPesa').value = '-1';
	document.getElementById('nombreTrabPesado').value = '';
	document.getElementById('montoTrabPesado').value = 0;
	if (document.getElementById("idEntExCaja").value != "-1")
	{
		document.getElementById('tipoPrevision').value = 2;//INP
		document.getElementById("opExCaja").value = "cambiaExCaja";
		document.getElementById("operacion").value = "";
		document.forms[0].submit();
	}
}

function cambiaCondINP()
{
	var id = document.getElementById("idRegimenImp").value;
	if (id != "-1")
	{
		var tasa = parseFloat(document.getElementById("codReg-" + id).value);
		document.getElementById("tasaCotINP").innerHTML = document.getElementById("codReg-" + id).value + " %";
		document.getElementById("prevObligatorioINP").value = formatNumero(Math.round(tasa * creaNumero(document.getElementById("remImpPension").value) / 100));
	} else
	{
		document.getElementById("tasaCotINP").innerHTML = "0.0 %";
		document.getElementById("prevObligatorioINP").value = "0";
	}

	recalculaTipoPrevision();
	recalculaTotalINP();
}

function recalculaTrabajo(idRenta)
{
	if (document.getElementById('idTasaTraPesa').value == -1)
	{
		document.getElementById('nombreTrabPesado').value = '';
		document.getElementById('montoTrabPesado').value = 0;
	} else
	{
		var renta = new Number(limpiaNumero(document.getElementById(idRenta).value, ''));
		document.getElementById('montoTrabPesado').value = formatNumero(Math.round(renta * (document.getElementById('idTasaTraPesa').value / 100)));
	}
	recalculaTotalAFP();
}

function limpiaAFP()
{
	if (document.getElementById('idEntPensionReal').value == idSinAFP || document.getElementById('idEntPensionReal').value == idAFPNinguna)
	{//sin AFP, o AFP 'ninguna'
		document.getElementById('prevObligatorioAFP').value = 0;
		document.getElementById('afpAhorro').value = 0;
		document.getElementById('segCesRemImp').value = 0;
		document.getElementById('segCesTrabajador').value = 0;
		document.getElementById('segCesEmpresa').value = 0;
		document.getElementById('idTasaTraPesa').value = '-1';
		document.getElementById('nombreTrabPesado').value = '';
		document.getElementById('montoTrabPesado').value = 0;
	}
}

function limpiaINP() {
	document.getElementById('remImpPension').value = 0;
	document.getElementById('prevObligatorioINP').value = 0;
	document.getElementById('inpDesahucio').value = 0;
	document.getElementById('inpDesahucio').value = 0;
}

function creaNumero(monto)
{
	return new Number(limpiaNumero(monto, ''));
}

function creaDecimal(monto)
{
	return new Number(limpiaNumero(monto, '.'));
}

function valLimiteNumero(monto, limite, formatLimite, txt)
{
	if (monto > limite)
		return " - " + txt + " debe ser menor a " + formatLimite + ". \n";
	return "";
}

function valLimiteMonto(txt, monto)
{
	return valLimiteNumero(monto, 99999999, '$99.999.999', txt);
}

function soloNomTrab(objCampo)
{
	var vals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ., Ñ ";
	validaFormato(objCampo,vals,true,true);
}

function validarFecha(sFecha) 
{
	var regex = /^\d{2}\-\d{2}\-\d{4}$/
	if (!regex.test(sFecha))
		return false;

	var dia  = sFecha.split("-")[0];
	var mes  = sFecha.split("-")[1];
	var year = sFecha.split("-")[2];
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

function limpiaConCursor(objCampo)
{
	var vals = "1234567890";
	var texto = objCampo.value;
	var marca = -1;

	for (i = texto.length - 1; i >= 0; i--)
  	{
    	if (vals.indexOf(texto.charAt(i)) == -1)
    	{
			texto = texto.substr(0, i) + texto.substr(i + 1, texto.length - 1);
      		marca = i;
    	}
  	}

	if (marca != -1)
  	{
  		objCampo.value = texto;
    	setSelecRange(objCampo,marca,marca);
  	}
}

function conFocoCampoNumerico(objCampo)
{
	soloNumero(objCampo);
}

function sinFocoCampoNumerico(objCampo)
{
	objCampo.value = formatNumero(objCampo.value);
}

function limpiaFecha(idCajaFecha)
{
	document.getElementById(idCajaFecha).value = "";
}
var temp_fecha="";
function restaurarFecha(e) 
{
	e.value = temp_fecha;
	temp_fecha = "";
}

function recalculaTipoPrevision()
{
	var tipoPrevision = 0;
	if (document.getElementById('idEntExCaja').value != -1 && document.getElementById('idRegimenImp').value != -1)
		tipoPrevision = 2;
	else if (document.getElementById('idEntPensionReal').value != idAFPNinguna && document.getElementById('idEntPensionReal').value != idSinAFP)
		tipoPrevision = 1;
	document.getElementById('tipoPrevision').value = tipoPrevision;
}


