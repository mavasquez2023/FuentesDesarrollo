function descarga() {
	// alert('2');
	document.forms["simulador-solicitar"].submit();

}

function activarSeguro() {
	// alert('2');
	var e = document.getElementById("tipo_afiliado");
	var d = document.getElementById("seguro-cesantia-div");
	var tipo_afiliado = e.options[e.selectedIndex].value;
	if (tipo_afiliado == 1) {
		document.getElementById("seguro-cesantia").disabled = false;
		d.style.display = "block";
	} else {
		d.style.display = "none";
		 document.getElementById("seguro-cesantia").disabled = true;
		 document.getElementById("seguro-cesantia").checked = false;
	}

}
