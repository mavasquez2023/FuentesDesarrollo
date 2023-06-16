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