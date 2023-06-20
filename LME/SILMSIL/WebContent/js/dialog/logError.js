	
	$(document).ready(function(){//asigna propiedades de pop up
    	$("#dialog_logError_LM").dialog({
			resizable: false,
			width: 1000,
			modal: true,
			autoOpen:false,
			buttons:{
					"Cerrar": function() {
							$( this ).dialog( "close" );
                    	}
                	}
    	});
    });	
	
	$(document).ready(function(){//asigna propiedades de pop up
    	$("#dialog_logError_SIL").dialog({
			resizable: false,
			width: 1000,
			modal: true,
			autoOpen:false,
			buttons:{
				"Ok": function() {
					$( this ).dialog( "close" );
                }
            }
    	});
    });


	$(document).ready(function(){//asigna propiedades de pop up
    	$("#dialog_formL_LM").dialog({
			resizable: false,
			width: 1000,
			modal: true,
			autoOpen:false,
			buttons:{
				"Cerrar": function() {
					$( this ).dialog( "close" );
                }
            }
    	});
    });	
	
	
	$(document).ready(function(){//asigna propiedades de pop up
    	$("#dialog_formL_SIL").dialog({
			resizable: false,
			width: 1000,
			modal: true,
			autoOpen:false,
			buttons:{
				"Ok": function() {
					$( this ).dialog( "close" );
                }
            }
    	});
    });
	
	$(document).ready(function(){
    	$("#chk_agrupar_SIL").change(function(){
    		var chk = $(this);
    		chk.val(1);
    		cargarListadoLog("SIL");
    	});
    	
    	$("#chk_agrupar_LM").change(function(){
    		var chk = $(this);
    		chk.val(1);
    		cargarListadoLog("LM");
    	});
    });
	
	$(document).ready(function(){//asigna propiedades de pop up
    	$("#dialog_form_LogSIL").dialog({
			resizable: false,
			width: 1000,
			modal: true,
			autoOpen:false,
			buttons:{
				"Cerrar": function() {
					$( this ).dialog( "close" );
                }
            }
    	});
    });
    
	/* Obtener período sin guión. */
	function obtenerPeriodoLogError(){
		var p = $("#periodoInfo_Log").text();
		return p.replace("-","");
	}

	
	//Botón LOG ERRORES */
	function logError(){
		
		var ahno = $("#selAnno").val();
		var radio = $("input:radio[name='rd_Trim']:checked").val();
		
		var meses = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
		             "11", "12"];
    	var array = [];
    	var index;
    	
    	if(radio == undefined){
    		alert('Por favor, marque una opción de trimestre.');
    	}else if(ahno.length==0){
    		alert('Por favor, ingrese un año válido.');
    	}else{
    		//Cargado datos SIL al arreglo.
        	for(index = 0; index < meses.length; ++index){
        		if($("#chk_SIL"+meses[index]).is(":checked")== true){
        			array.push($("#chk_SIL"+meses[index]).val());
        		}
        	}
        	//Cargado datos LM al arreglo.
        	for(index = 0; index < meses.length; ++index){
        		if($("#chk_LM"+meses[index]).is(":checked")== true){
        			array.push($("#chk_LM"+meses[index]).val());
        		}
        	}
        	//Procesando arreglo creado.
        	if(array.length==1){
        		for(index = 0; index < array.length; ++index){
            		var proceso = array[index];
            		var plano;
            		var mes;
            		
            		if(array[index].length==9){
            			plano = proceso.substring(4, 7);
            			mes = proceso.substring(7);
            		}else{
            			plano = proceso.substring(4, 6);
            			mes = proceso.substring(6);
            		}
            		$("#periodoInfo_Log").text(ahno+'-'+mes);
            		
            		$("#dialog_logError_"+plano).dialog("open");
            		$("#periodoInfo").text(ahno+'-'+mes);
            		
            		cargarListadoLog(plano);
            	}
        	}else{
        		alert('Debe seleccionar a lo menos un campo, y únicamente uno.');
        	}
    	}
    }

	function cargarListadoLogUnico(opc) {
		//periodo: 201304
		$("#loadingLog").hide();
		var aux=[];
		aux=opc.split("_");		
		var plano=aux[0];
		var periodo=aux[1];
		if(plano=="SIL"){
			$("#form_filtro_Log_"+plano).find("input[name='perpag']").val(periodo);
		}else if(plano=="LM"){
			$("#form_filtro_Log_"+plano).find("input[name='fecproceso']").val(periodo);
		}
		$("#periodoInfo_Log").text(periodo.substring(0, 4)+"-"+periodo.substring(4, 6));		
		$("#dialog_logError_"+plano).dialog("open");
		$("#periodoInfo").text(periodo.substring(0, 4)+"-"+periodo.substring(4, 6));
		cargarListadoLog(plano);
	}
	/* carga listado de logerrores para LM y SIL, agrupa segun check "agrupar" val= 1*/
	function cargarListadoLog(plano) {
		$(".msjLog").hide();
		$("#form_filtro_Log_"+plano).find("input[name='op']").val("allList"+plano);
		$("#form_filtro_Log_"+plano).find("#lbl_msgBusqueda").text('');
		if(plano=="SIL"){
			$("#form_filtro_Log_"+plano).find("input[name='perpag']").val(obtenerPeriodoLogError());
		}else if(plano=="LM"){
			$("#form_filtro_Log_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoLogError());
		}
		
		$.ajax({
			type: "POST",
			url: "./logError_"+plano+".do",
			data: $("#form_filtro_Log_"+plano).serialize(),
			beforeSend: function(){
				$("#formLoadLog_"+plano).find("#loadMenuTablaLog_"+plano).show();
				$("#dialog_logError_"+plano).dialog("open");
		      },
			success: function (data) {
				$("#contenedor-tablaLog_"+plano).empty();
				$("#contenedor-tablaLog_"+plano).append(data);
			},
			complete: function (){
				$("#formLoadLog_"+plano).find("#loadMenuTablaLog_"+plano).hide();   
				$(".msjLog").show();
			},
			error: function (xhr, ajaxOptions, thrownError) {
				//Mostrar mensaje de error
				alert(xhr.status);
				alert(thrownError);
			}
		});        
		return false; // required to block normal submit since you used ajax.		
	}
	
	function primeraPaginaLog(plano){
		$("#form_filtro_Log_"+plano).find("input[name='op']").val("paginarInicio_Log"+plano);
		
    	if(plano=="SIL"){
			 $("#form_filtro_Log_"+plano).find("input[name='perpag']").val(obtenerPeriodoLogError());
		 }else if(plano=="LM"){
			 $("#form_filtro_Log_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoLogError());
		 }
		 $.ajax({
           type: "POST",
           url: "./logError_"+plano+".do",
           data: $("#form_filtro_Log_"+plano).serialize(),
           async: true,
           beforeSend: function(){
        	   $("#formLoadLog_"+plano).find("#loadMenuTablaLog_"+plano).show();
	      	},
           success: function (data) {
        	  $("#contenedor-tablaLog_"+plano).empty();
        	  $("#contenedor-tablaLog_"+plano).append(data);
           },
           complete: function (){
        	   	//downLoadLog();
        	   	//$(".classLoading").hide();
        	   $("#formLoadLog_"+plano).find("#loadMenuTablaLog_"+plano).hide();
           		$("#dialog_logError_"+plano).dialog("open");
           },
           error: function (xhr, ajaxOptions, thrownError) {
          	 //mostrar mensaje de error
               alert(xhr.status);
               alert(thrownError);
           }
		 });        
       return false; // required to block normal submit since you used ajax
	}
	
	function avancePaginaLog(plano){
		$("#form_filtro_Log_"+plano).find("input[name='op']").val("paginarAvance_Log"+plano);
		
    	if(plano=="SIL"){
			 $("#form_filtro_Log_"+plano).find("input[name='perpag']").val(obtenerPeriodoLogError());
		 }else if(plano=="LM"){
			 $("#form_filtro_Log_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoLogError());
		 }
		 $.ajax({
           type: "POST",
           url: "./logError_"+plano+".do",
           data: $("#form_filtro_Log_"+plano).serialize(),
           async: true,
           beforeSend: function(){
        	   $("#formLoadLog_"+plano).find("#loadMenuTablaLog_"+plano).show();
	      	},
           success: function (data) {
        	  $("#contenedor-tablaLog_"+plano).empty();
        	  $("#contenedor-tablaLog_"+plano).append(data);
           },
           complete: function (){
        	   	//downLoadLog();
        	   	//$(".classLoading").hide();
        	   $("#formLoadLog_"+plano).find("#loadMenuTablaLog_"+plano).hide();
           		$("#dialog_logError_"+plano).dialog("open");
           },
           error: function (xhr, ajaxOptions, thrownError) {
          	 //mostrar mensaje de error
               alert(xhr.status);
               alert(thrownError);
           }
		 });        
       return false; // required to block normal submit since you used ajax
	}
	
	
	function retrocesoPaginaLog(plano){
		$("#form_filtro_Log_"+plano).find("input[name='op']").val("paginarRetroceso_Log"+plano);
    	if(plano=="SIL"){
			 $("#form_filtro_Log_"+plano).find("input[name='perpag']").val(obtenerPeriodoLogError());
		 }else if(plano=="LM"){
			 $("#form_filtro_Log_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoLogError());
		 }
 	   	
		 $.ajax({
           type: "POST",
           url: "./logError_"+plano+".do",
           data: $("#form_filtro_Log_"+plano).serialize(),
           async: true,
           beforeSend: function(){
        	   //upLoadLog();
        	   $("#formLoadLog_"+plano).find("#loadMenuTablaLog_"+plano).show();
	      	},
           success: function (data) {
        	   $("#contenedor-tablaLog_"+plano).empty();
        	   $("#contenedor-tablaLog_"+plano).append(data);
           },
           complete: function (){
        	   $("#formLoadLog_"+plano).find("#loadMenuTablaLog_"+plano).hide();
        	   //downLoadLog();
        	   //$(".classLoading").hide();
           	$("#dialog_logError_"+plano).dialog("open");
           },
           error: function (xhr, ajaxOptions, thrownError) {
          	 //mostrar mensaje de error
               alert(xhr.status);
               alert(thrownError);
           }
		 });        
       return false; // required to block normal submit since you used ajax
	}
	
	
	function ultimaPaginaLog(plano){
		$("#form_filtro_Log_"+plano).find("input[name='op']").val("paginarUltimo_Log"+plano);
    	if(plano=="SIL"){
			 $("#form_filtro_Log_"+plano).find("input[name='perpag']").val(obtenerPeriodoLogError());
		 }else if(plano=="LM"){
			 $("#form_filtro_Log_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoLogError());
		 }
 	   	
		 $.ajax({
           type: "POST",
           url: "./logError_"+plano+".do",
           data: $("#form_filtro_Log_"+plano).serialize(),
           async: true,
           beforeSend: function(){
        	   $("#formLoadLog_"+plano).find("#loadMenuTablaLog_"+plano).show();
           },
           success: function (data) {
        	   $("#contenedor-tablaLog_"+plano).empty();
        	   $("#contenedor-tablaLog_"+plano).append(data);
           },
           complete: function (){
        	   //downLoadLog();
        	   //$(".classLoading").hide();
        	   $("#formLoadLog_"+plano).find("#loadMenuTablaLog_"+plano).hide();
        	   $("#dialog_logError_"+plano).dialog("open");
           },
           error: function (xhr, ajaxOptions, thrownError) {
          	 //mostrar mensaje de error
               alert(xhr.status);
               alert(thrownError);
           }
		 });        
       return false; // required to block normal submit since you used ajax
	}
	
	function buscarLog(plano){
		var r=null;
		var f=null;
		$("#form_filtro_Log_"+plano).find("input[name='op']").val("buscarLog_"+plano);
		if(plano=="SIL"){
			 $("#form_filtro_Log_"+plano).find("input[name='perpag']").val(obtenerPeriodoLogError());
			 r=$("#form_filtro_Log_"+plano).find("input[name='ruttrabaj']").val().length;
			 f=$("#form_filtro_Log_"+plano).find("input[name='nrofol']").val().length;
		 }else if(plano=="LM"){
			$("#form_filtro_Log_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoLogError());
			r=$("#form_filtro_Log_"+plano).find("input[name='ruttrabaj']").val().length;
			f=$("#form_filtro_Log_"+plano).find("input[name='folio']").val().length;
		 }
		
		if(r>0 || f>0){			
			$.ajax({
					type: "POST",
					url: "./logError_"+plano+".do",
					data: $("#form_filtro_Log_"+plano).serialize(),
					async : true,
					beforeSend: function(){
						$("#formLoadLog_"+plano).find("#loadMenuTablaLog_"+plano).show();
				 	},
					success: function (data) {
						$("#contenedor-tablaLog_"+plano).empty();
						$("#contenedor-tablaLog_"+plano).append(data);
					},
					complete: function(){
						$("#form_filtro_Log_"+plano).find(".txt_campo").val('');
						//$("#formLoad_"+plano).find("#loadingLog").hide();
						$("#formLoadLog_"+plano).find("#loadMenuTablaLog_"+plano).hide();
					},
					error: function (xhr, ajaxOptions, thrownError) {
						//mostrar mensaje de error
						alert(xhr.status);
						alert(thrownError);
					}
			}); 
		}else{			
			$("#form_filtro_Log_"+plano).find("#lbl_msgBusqueda").text("Debe ingresar al menos un dato para la búsqueda.");
			//cambio de color
		}     
       return false; // required to block normal submit since you used ajax
	}
	
	/* Acción ejecutada una vez se presiona el botón CORREGIR ERROR en Log Errores proceso SIL */
	$(document).on('click', '.correct_SIL', function() {
		
		var periodo = obtenerPeriodoLogError();
		
		var tr = $(this).closest("tr");
		var nro_fol = tr.find(".var_nrofol").text();
		var rut_trab = tr.find(".var_rut").text();
		var var_fecemi = tr.find(".var_fecemi").text();
		var var_lich = tr.find(".var_lichasfec").val();
		var var_pagfol = tr.find(".var_pagfol").val();
		var var_corr = tr.find(".var_correlab").val();
		var del_c=tr.find(".var_c").val();
		//alert('var_fecemi = ' + var_fecemi + ' var_lich = ' + var_lich + 'var_pagfol = ' + var_pagfol);
		
		$("#form_mantenedor_SIL").find("input[name='perpag']").val(periodo);
		$("#form_mantenedor_SIL").find("input[name='nrofol']").val(nro_fol);
		$("#form_mantenedor_SIL").find("input[name='ruttrabaj']").val(rut_trab);
		$("#form_mantenedor_SIL").find("input[name='fecemi']").val(var_fecemi);
		$("#form_mantenedor_SIL").find("input[name='lichasfec']").val(var_lich);
		$("#form_mantenedor_SIL").find("input[name='pagfol']").val(var_pagfol);
		$("#form_mantenedor_SIL").find("input[name='correlab']").val(var_corr);
		$("#form_mantenedor_SIL").find("input[name='correlativ']").val(del_c);
		$("#dialog_form_SIL").dialog('option', 'title', 'Correción de errores de registro en Log SIL.');
		
		//Abrir dialog para mostrar icono loading.
		$("#dialog_form_SIL").dialog("open");
		
		mostrarLog('SIL');
	});
	
	/* Acción ejecutada una vez se presiona el botón CORREGIR ERROR en Log Errores proceso LM */
	$(document).on('click', '.correct_LM', function() {
		var periodo = obtenerPeriodoLogError();
		
		var tr = $(this).closest("tr");
		var nro_fol = tr.find(".var_f").text();
		var rut_trab = tr.find(".var_r").text();
		var var_feter = tr.find(".var_ftr").text();
		var del_c=tr.find(".var_c").val();
		$("#form_mantenedor_LM").find("input[name='fecproceso']").val(periodo);
		$("#form_mantenedor_LM").find("input[name='folio']").val(nro_fol);
		$("#form_mantenedor_LM").find("input[name='ruttrabaj']").val(rut_trab);
		$("#form_mantenedor_LM").find("input[name='fecterrepo']").val(var_feter);
		$("#form_mantenedor_LM").find("input[name='correlativ']").val(del_c);
		$("#dialog_form_LM").dialog('option', 'title', 'Correción de errores de registro en Log LM.');
		
		//Abrir dialog para mostrar icono loading.
		$("#dialog_form_LM").dialog("open");
		
		mostrarLog('LM');
	});
	
	/* Muestra formulario para correción de errores. */
	function mostrarLog(plano){ 
		
		$("#form_mantenedor_"+plano).find("input[name='op']").val("mostrarLog_"+plano);	  	
		$("#form_mantenedor_"+plano).find(".txt_campo").removeAttr('disabled');
		
 	  	$.ajax({
 	  		type: "POST",
	           url: "./logError_"+plano+".do",
	           data: $("#form_mantenedor_"+plano).serialize(),
 	  		async : true,
 	  		beforeSend: function(){
 	  			upLoading();
 	  			$("#contenedor-form_"+plano).empty();
 	  			$("#dialog_form_"+plano).dialog("open");
 	  		},
 	  		success: function (data) {
 	  			$("#contenedor-form_"+plano).empty();
				$("#contenedor-form_"+plano).append(data);
			},
 	  		complete: function(){ 
       		  	var buttons = {};
     				buttons["Actualizar"] = function() {     					
     					actualizarLog(plano);};
     				buttons["Cancelar"] = function() {$("#dialog_form_"+plano).dialog( "close" );};
     				$("#dialog_form_"+plano).dialog({buttons:buttons});
     			
     				downLoading();
 	  		},
 	  		error: function (xhr, ajaxOptions, thrownError) {
 	  			//mostrar mensaje de error
 	  			alert(xhr.status);
 	  			alert(thrownError);
 	  		}
 	  	});        
 	  	return false; // required to block normal submit since you used ajax
	}
	
	/* Actualiza de acuerdo a lo ingresado en pantalla (formulario_SIL/LM.jsp). */
	function actualizarLog(plano) {
		$("#txt_msj_ok").text("  "); $("#txt_msj_nok").text("  ");
		$("#form_mantenedor_"+plano).find("input[name='op']").val("actualizarLog_"+plano);
		
		 if(plano=="SIL"){
			 $("#form_mantenedor_"+plano).find("input[name='perpag']").val(obtenerPeriodoLogError());
		 }else if(plano=="LM"){
			 $("#form_mantenedor_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoLogError());
		 }
		 //alert("ser: "+$("#form_mantenedor_"+plano).serialize());
		 if(validarForm(plano,"form_mantenedor_"+plano)){		
			 $.ajax({
		 	  	type: "POST",
			      url: "./logError_"+plano+".do",
			      data: $("#form_mantenedor_"+plano).serialize(),
			      beforeSend: function(){
		 	  			upLoading();
		 	  			$("#contenedor-form_"+plano).empty();
		 	  			$("#dialog_form_"+plano).dialog("open");
		 	  		},
			      success: function (data) {        	      	   
			    	 $("#contenedor-form_"+plano).empty();
			    	 $("#contenedor-form_"+plano).append(data);           	 
			      },
			      complete: function(){
			    	  //setTimeout(function(){$("#loadMenu").show()},10); 
			    	  $("#txt_msj_ok").text("Se ha realizado la actualización satisfactoriamente.");
		    		  $("#txt_msj_nok").text("No se ha podido actualizar el nuevo registro debido a " +
					    	"que existe otro registro con los mismos datos (Datos clave son número " +
			    	  		"de folio, rut trabajador y fecha proceso).");
			    	  $(".textMsj").show();
			    	  downLoading();
			    	 //$("#dialog_logError_"+plano).dialog("open");
					 //$("#dialog_form_"+plano).dialog("open");     	   
					},
			      error: function (xhr, ajaxOptions, thrownError) {
			     	 //mostrar mensaje de error
			          alert(xhr.status);
			          alert(thrownError);
			      }
			 });        
			 return false; // required to block normal submit since you used ajax
		 }else{
			 $("#txt_msj").text("No se ha podido insertar el registro por la existencia de errores.");
		 }
    }
	
	function upLoading(){
		$(".loadMenu").show();
	}
	
	function downLoading(){
		$(".loadMenu").hide();
	}
	
    function upLoadLog(){
    	$("#loadingLog").show();
	}
	
	function downLoadLog(){
		$("#loadingLog").hide();
	}
	
	$(document).ready(function(){
		downLoading();
		downLoadLog();
	});
	
	$(document).ready(function(){
		$("#loadingLog").hide();
	});