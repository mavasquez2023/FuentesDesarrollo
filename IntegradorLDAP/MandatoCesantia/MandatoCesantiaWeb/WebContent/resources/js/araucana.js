function doPrint() {
	document.all.item("noprint").style.visibility = 'hidden';
	$("#titulo").css("text-align", "center");
	window.print();
	document.all.item("noprint").style.visibility = 'visible';
	$("#titulo").css("text-align", "left");
}


function mostrarInfo(texto, tiempo) {
	$("#informacion").hide();
	$("#informacion_error").hide();
	$("#informacion_warning").hide();
	var fade_out = 3000;
	if (tiempo != null)
		fade_out = tiempo;
	$("#informacion").html(texto);
	$("#informacion").fadeIn(500);
	$("#informacion").fadeOut(fade_out);
}

function mostrarError(texto, tiempo) {
$("#informacion").hide();
$("#informacion_error").hide();
var fade_out = 3000;
if (tiempo != null)
	fade_out = tiempo;
$("#informacion_error").html(texto);
$("#informacion_error").fadeIn(500);
$("#informacion_error").fadeOut(fade_out);
}

function mostrarWarning(texto, tiempo) {
	$("#informacion").hide()
	$("#informacion_error").hide();
	$("#informacion_warning").hide();
	var fade_out = 4000;
	if (tiempo != null)
		fade_out = tiempo;
	$("#informacion_warning").html(texto);
	$("#informacion_warning").fadeIn(500);
	$("#informacion_warning").fadeOut(fade_out);
}