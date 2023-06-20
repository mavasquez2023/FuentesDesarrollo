/* Inicialización en español para la extensión 'UI date picker' para jQuery. */
/* Traducido por Vester (xvester [en] gmail [punto] com). */
jQuery(function($){
   $.datepicker.regional['es'] = {
      closeText: 'Cerrar',
      prevText: '<Ant',
      nextText: 'Sig>',
      currentText: 'Hoy',
      monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'],
      monthNamesShort: ['Ene','Feb','Mar','Abr', 'May','Jun','Jul','Ago','Sep', 'Oct','Nov','Dic'],
      dayNames: ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'S\xE1bado'],
      dayNamesShort: ['Dom','Lun','Mar','Mié','Juv','Vie','Sáb'],
      dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','Sa'],
      weekHeader: 'Sm',
      dateFormat: 'dd-mm-yy',
      firstDay: 1,
      isRTL: false,
      showMonthAfterYear: false,
      yearSuffix: ''};
   $.datepicker.setDefaults($.datepicker.regional['es']);
}); 


$.validator.addMethod(
	    "fechaValida",
	    function(value, element) {
	        // put your own logic here, this is just a (crappy) example
	    	var fecha = element.value;
	    	var parts = fecha.split('-');
	    	fecha = parts[2] + "-" + parts[1] + "-" + parts[0];
	    	fecha = Date.parse(fecha);
	    	var currentTime = new Date();
	    	var nuevaFechaHoy = $.datepicker.formatDate('yy-mm-dd', currentTime);
	    	nuevaFechaHoy = Date.parse(nuevaFechaHoy);
	    	var finMes = new Date(currentTime.getFullYear(),currentTime.getMonth() + 1, 0);
	        return (fecha >= nuevaFechaHoy && fecha <= finMes);
	    },
	    "* Fecha fuera de rango."
	);

$.validator.addMethod(
	    "customDate",
	    function(value, element) {
	        // put your own logic here, this is just a (crappy) example
	    	return value.match(/^\d\d?\-\d\d?\-\d\d\d\d$/); //dd-mm-yyyy
	        //return value.match(/^\d\d?\/\d\d?\/\d\d\d\d$/); dd/mm/yyyy
	    },
	    "* Ingrese formato dd-mm-yyyy."
	);

