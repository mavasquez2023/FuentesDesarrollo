//[Configuraciones Calendario].------------------------------------------------------------------
$(document).ready(function(){
        $.datepicker.regional['es'] = {		
		closeText: 'Cerrar',
		prevText: '&#x3c;Ant',
		nextText: 'Sig&#x3e;',
		currentText: 'Hoy',
		monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
		'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
		monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun',
		'Jul','Ago','Sep','Oct','Nov','Dic'],
		dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
		dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
		dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
		weekHeader: 'Sm',
		dateFormat: 'yy-mm-dd',
		firstDay: 1,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: ''};
		$.datepicker.setDefaults($.datepicker.regional['es']);
            });
            
//[Dialog Campos Fecha].-----------------------------------------------
			function cargarFecha(tipoForm,tipoFecha,idInput){
		 		//asigno nombre del campo a completar
		 		$( "#idInput" ).val(idInput);
		 		//evaluar tipo de formato fecha requerido
		 		if(tipoFecha=="fechaPeriodo"){
            		$( "#fechaAux" ).datepicker({dateFormat: 'yymm'});
            	}
            	if(tipoFecha=="fechaISO"){            	
            		$( "#fechaAux" ).datepicker({dateFormat: 'yy-mm-dd'});
            	}
            	//completar tipo de formulario en donde se encuentra el campo.
				$( "#tipoForm" ).val(tipoForm);
                $("#Fecha_dialog").dialog("open");                
            }       
            $(document).ready(function(){
				$("#Fecha_dialog").dialog({
                        resizable: false,
                        width: 480,
                        modal: true,
                        autoOpen:false,
                        buttons: {
                             "Ok": function() {   
                             			var tf=$( "#tipoForm" ).val();
                             			var id=$( "#idInput" ).val();
                             			$("#"+tf).find("input[name='"+id+"']").val($( "#fechaAux" ).val());
                             			
                             	$( "#fechaAux" ).datepicker('destroy');
                             	$( "#fechaAux" ).val('');
                             	$( this ).dialog( "close" ); 
                             },
                             Cancelar: function() {
                             
                          		$( "#fechaAux" ).datepicker('destroy');                             		
                             	$( "#fechaAux" ).val('');
                                   $( this ).dialog( "close" );
                             }
                        }
                  });
            });