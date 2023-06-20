var formatear_rut = function(rut) {
	if ($.trim(rut).length > 1) {
		var digito = rut.substring(rut.length - 1);
		var entero = rut.substring(0, rut.length - 1);
		return SepararMiles(entero) + "-" + digito;
	}
	return rut;
}
var valida_texto_numerico = function(event) {
	if ((event.which >= 48 && event.which <= 57) || event.which != 45
			|| event.which != 46 || event.which != 8)
		return true;
	return false;
}
function replaceAll(str, find, replace) {
  return str.replace(new RegExp(find, 'g'), replace);
}

function validarRutIngresado(campo, rut) {
	rut= rut.replace(/\./g, "");
		if(rut.length>0){
			var partes = rut.split("-");
			var digv = partes[1];
			var rut = partes[0];
			if (digv == 'K') {
				digv = 'k';
			}
			var digesto = dv(rut);
			if (digesto == digv) {
				return true;
			} else {
				//mostrarInfoError("El Rut ingresado no es v&aacute;lido");
				marcarCampoError(campo, "RUT" );
				return;
			}
		}
}

function dv(T) {
	var M = 0, S = 1;
	for (; T; T = Math.floor(T / 10)) {
		S = (S + T % 10 * (9 - M++ % 6)) % 11;
	}
	return S ? S - 1 : 'k';
}

function SepararMiles(numero) {
	var num = numero.replace(/\./g, "");
	if (!isNaN(num)) {
		num = num.toString().split("").reverse().join("").replace(
				/(?=\d*\.?)(\d{3})/g, "$1.");
		num = num.split("").reverse().join("").replace(/^[\.]/, "");
		numero = num;
	} else {
		num = num.replace(/[^\d\.]*/g, "");
		num = num.toString().split("").reverse().join("").replace(
				/(?=\d*\.?)(\d{3})/g, "$1.");
		num = num.split("").reverse().join("").replace(/^[\.]/, "");
		numero = num;

	}
	return numero;
}

function sysout(texto) {
	// if(texto.indexOf("info")>-1)
	//console.log(texto);
}