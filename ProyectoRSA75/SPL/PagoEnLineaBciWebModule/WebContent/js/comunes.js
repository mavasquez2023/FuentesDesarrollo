/**
 * Selecciona, en el objeto de tipo combobox, el option que tiene el valor 'value'
 **/
function setValueSelect(select, value) {
	for (var i = 0; i < select.options.length; i++) {
		if (select.options[i].value == value) {
			select.selectedIndex = i;
			return;
		}
	}
}

/**
 * Dado el arreglo con los objetos de tipo radio
 * (salido de form['nombreradio'] por ejemplo)
 * encuentra el que esta seleccionado.
 **/
function getValueRadio(radio) {
	for (var i = 0; i < radio.length; i++) {
		if (radio[i].checked || radio[i].selected) {
			return radio[i].value;
		}
	}
	return;
}


function fechaMenorIgual(dDesde, dHasta) {
	return dDesde.getTime() <= dHasta.getTime();
}


/**
 * Parsea una fecha con formato dd/mm/aaaa
 **/
function parseFecha(sFecha) {
	if (sFecha) {
		var dia = sFecha.substring(0, 2);
		var mes = sFecha.substring(3, 5);
		var anno = sFecha.substring(6, 10);
		var fecha = new Date(anno, mes, dia);
		return fecha;
	}
}

function characterCount(field, maxchars) {
	if (field.value.length > maxchars) {
		alert('El largo máximo del campo es ' + maxchars + ' caracteres.');
		field.value = field.value.substring(0, maxchars);		
	}
}

function winopen(url, name, width, height) {
    width = width || 550;
    height = height || 550;
    name = name || 'Ventana';
    var w = window.open(url, name, 'width='+width+',height='+height+',left=10,top=10,resizable=yes,scrollbars=yes');
    if (w)
      w.focus();
    return false;
}

function setupCalendar(idCampo, patronCalendario, idImagen) {
	if (document.getElementById(idCampo) && document.getElementById(idImagen)) {
		Calendar.setup({
			inputField     :    idCampo,     // id of the input field
			ifFormat       :    patronCalendario,      // format of the input field
			button         :    idImagen,  // trigger for the calendar (button ID)
			align          :    "Br",           // alignment (defaults to "Bl")
			singleClick    :    true,
			weekNumbers    :    false
		});
	}
}