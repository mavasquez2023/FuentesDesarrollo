//[Configuracion Dialog Mantenedores 'LM' y 'SIL'].----------------------------------------------
	
//Pop Up Formulario
	function obtenerPeriodoMantenedor(plano){
		var p=$("#periodoInfoMantenedor_"+plano).text();
		return p.replace("-","");
	}
	
	function openMantenedor(plano,periodo) {//recibe plano('LM' o 'SIL') y periodo marcado AAAAMM.
		$("#dialog_mantenedor_"+plano).dialog("open");
		$(".textMsj").hide();
		//asignar a campo periodo el periodo (formato AAAA-MM)
		$("#periodoInfoMantenedor_"+plano).text(periodo);
		cargarListado(plano);
		
		//aqui-> ajax
	}
	

//[apertura de mantenedor]-------------------------------------------	
//validacion de un solo mes seleccionado
	//Botón LOG ERRORES */
	function checkMesMantencion(){		
		var ahno = $("#selAnno").val();
		var radio = $("input:radio[name='rd_Trim']:checked").val();		
		var meses = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10","11", "12"];
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
            		//$("#periodoInfo_Log").text(ahno+'-'+mes);            		
            		//$("#dialog_logError_"+plano).dialog("open");
            		$("#periodoInfoMantenedor_"+plano).text(ahno+'-'+mes);            		
            		//$("#periodoInfo").text(periodo);
            		cargarListado(plano);
            	}
        	}else{
        		alert('Debe seleccionar a lo menos un campo, y únicamente uno.');
        	}
    	}
    }
	
	$(document).ready(function(){//asigna propiedades de pop up
    	$("#dialog_mantenedor_LM").dialog({
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
    	$("#dialog_mantenedor_SIL").dialog({
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
//[Configuracion Dialog Formularios].----------------------------------------------
	
	function openInsertar(plano) {//recibe 'LM' o 'SIL'
		//limpiarFormulario(plano);
		$("#dialog_form_"+plano).dialog('option', 'title', 'Ingreso de registro '+plano);		
		var buttons = {};
		buttons["Insertar"] = function() { insertar(plano);};
		buttons["Cancelar"] = function() { $("#dialog_form_"+plano).dialog( "close" );};		
		$("#dialog_form_"+plano).dialog({buttons:buttons});
		
		mostrar_FormVacio(plano);
		$("#dialog_form_"+plano).dialog("open");
		//insertar accion de metodo.
	}
	
	/*openBorrar LM, mostrara el registro y deshabilitara los campos en el formulario
		para aceptar su eliminacion*/
	$(document).on('click', '.del_LM', function() {	    
	    var tr = $(this).closest("tr");
	    var del_f =tr.find(".var_f").text();
	    var del_r =tr.find(".var_r").text();
	    var del_ftr=tr.find(".var_ftr").text();
	    var del_c=tr.find(".var_c").text();
	    var b_periodo=obtenerPeriodoMantenedor("LM");
		$("#form_mantenedor_LM").find("input[name='folio']").val(del_f);
		$("#form_mantenedor_LM").find("input[name='ruttrabaj']").val(del_r);
		$("#form_mantenedor_LM").find("input[name='fecproceso']").val(b_periodo);
		$("#form_mantenedor_LM").find("input[name='fecterrepo']").val(del_ftr);
		$("#form_mantenedor_LM").find("input[name='correlativ']").val(del_c);
		
		$("#dialog_form_LM").dialog('option', 'title', 'Eliminación registro LM.');
		mostrar('LM','eliminar');
	});	
//open Actualizar LM, mostrara el registro en formulario y habilitara edicion.
	$(document).on('click', '.up_LM', function() {
		
	    var tr = $(this).closest("tr");
	    var del_f =tr.find(".var_f").text();
	    var del_r =tr.find(".var_r").text();
	    var del_ftr=tr.find(".var_ftr").text();
	    var del_c=tr.find(".var_c").text();
	    var b_periodo=obtenerPeriodoMantenedor("LM");
	    
		$("#form_mantenedor_LM").find("input[name='folio']").val(del_f);
		$("#form_mantenedor_LM").find("input[name='ruttrabaj']").val(del_r);
		$("#form_mantenedor_LM").find("input[name='fecproceso']").val(b_periodo);		
		$("#form_mantenedor_LM").find("input[name='fecterrepo']").val(del_ftr);
		$("#form_mantenedor_LM").find("input[name='correlativ']").val(del_c);
		
		$("#dialog_form_LM").dialog('option', 'title', 'Actualización registro LM.');
		
		mostrar('LM','actualizar');
	});

	/*openBorrar SIL, mostrara el registro y deshabilitara los campos en el formulario
	para aceptar su eliminacion*/
	$(document).on('click', '.del_SIL', function() {	    
	    var tr = $(this).closest("tr");
	    var del_f =tr.find(".var_f").text();
	    var del_r =tr.find(".var_r").text();
	    var del_pf=tr.find(".var_pf").text();
	    var del_ftr=tr.find(".var_ftr").text();
	    var del_c=tr.find(".var_c").text();
	    $("#form_mantenedor_SIL").find("input[name='perpag']").val(obtenerPeriodoMantenedor("SIL"));	    
		$("#form_mantenedor_SIL").find("input[name='ruttrabaj']").val(del_r);
		$("#form_mantenedor_SIL").find("input[name='nrofol']").val(del_f);		
		$("#form_mantenedor_SIL").find("input[name='pagfol']").val(del_pf);	
		$("#form_mantenedor_SIL").find("input[name='lichasfec']").val(del_ftr);		
		$("#form_mantenedor_SIL").find("input[name='correlativ']").val(del_c);
		
		$("#dialog_form_SIL").dialog('option', 'title', 'Eliminación registro SIL.');		
		mostrar('SIL','eliminar');
	});
//open Actualizar SIL, mostrara el registro en formulario y habilitara edicion.
	$(document).on('click', '.up_SIL', function() {
		
		var tr = $(this).closest("tr");
	    var del_f =tr.find(".var_f").text();
	    var del_r =tr.find(".var_r").text();
	    var del_pf=tr.find(".var_pf").text();
	    var del_ftr=tr.find(".var_ftr").text();
	    var del_c=tr.find(".var_c").text();
	   
		$("#form_mantenedor_SIL").find("input[name='perpag']").val(obtenerPeriodoMantenedor("SIL"));	    
		$("#form_mantenedor_SIL").find("input[name='ruttrabaj']").val(del_r);
		$("#form_mantenedor_SIL").find("input[name='nrofol']").val(del_f);		
		$("#form_mantenedor_SIL").find("input[name='pagfol']").val(del_pf);	
		$("#form_mantenedor_SIL").find("input[name='lichasfec']").val(del_ftr);
		$("#form_mantenedor_SIL").find("input[name='correlativ']").val(del_c);
		$("#dialog_form_SIL").dialog('option', 'title', 'Actualización registro SIL.');
		
		mostrar('SIL','actualizar');
	});
	
	
	
    $(document).ready(function(){
    	$("#dialog_form_LM").dialog({
            resizable: false,
            width: 850,
            modal: true,
            autoOpen:false
    	});
    });
    $(document).ready(function(){
    	$("#dialog_form_SIL").dialog({
            resizable: false,
            width: 850,
            modal: true,
            autoOpen:false
    	});
    });
    $(document).ready(function(){
    	$("#dialog_form_busqueda").dialog({
            resizable: false,
            width: 200,
            modal: true,
            autoOpen:false
    	});
    });
    
    
    
//[configuracion botones para mantenedores]--------------------------------------------------
       
	function cargarListado(plano) {		 
		 $("#form_filtro_"+plano).find("input[name='op']").val("unspecified");
		 
		 if(plano=="SIL"){
			 $("#form_filtro_"+plano).find("input[name='perpag']").val(obtenerPeriodoMantenedor(plano));
		 }else if(plano=="LM"){
			 $("#form_filtro_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoMantenedor(plano));
		 }
		 //alert("ser: "+$("#form_filtro_"+plano).serialize());
		 $.ajax({
            type: "POST",
            url: "./mantenedor_"+plano+".do",
            data: $("#form_filtro_"+plano).serialize(),
            beforeSend: function(){
            	$("#dialog_mantenedor_"+plano).dialog("open");
            	$("#form_filtro_"+plano).find("#lbl_msgBusqueda").text('');
            	$(".loadMenuMantenedor").show();
            	
  	      	},
            success: function (data) {
            
           	 $("#contenedor-tabla_"+plano).empty();
           	 $("#contenedor-tabla_"+plano).append(data);
           	downLoad();
            },
            complete: function (){ 
            	$(".loadMenuMantenedor").hide();
            	$(".classLoading").hide();
            },
            error: function (xhr, ajaxOptions, thrownError) {
           	 //mostrar mensaje de error
                alert(xhr.status);
                alert(thrownError);
            }
		 });        
        return false; // required to block normal submit since you used ajax
    }
	
	function insertar(plano) {
		$("#txt_msj_ok").text("  "); $("#txt_msj_nok").text("  ");
		 $("#dialog_mantenedor_"+plano).dialog("open");
		 $("#form_mantenedor_"+plano).find("input[name='op']").val("insertar_"+plano);
		 if(plano=="SIL"){
			 $("#form_mantenedor_"+plano).find("input[name='perpag']").val(obtenerPeriodoMantenedor(plano));
		 }else if(plano=="LM"){
			 $("#form_mantenedor_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoMantenedor(plano));
		 }
		 if(validarForm(plano,"form_mantenedor_"+plano)){
			 $.ajax({
		        type: "POST",
		        url: "./mantenedor_"+plano+".do",
		        data: $("#form_mantenedor_"+plano).serialize(),
		        beforeSend: function(){
		      	   $("#contenedor-form_"+plano).empty();
		       	  	$(".loadMenu").show();
		      	},
		        success: function (data) {
		        	$("#contenedor-form_"+plano).empty();
		          	$("#contenedor-form_"+plano).append(data);
		        },
		        complete: function(){
		 			//$("#dialog_form_"+plano).dialog( "close" );
		        	$("#txt_msj_ok").text("Se ha realizado la insercción del registro de manera satisfactoriamente.");
		    		$("#txt_msj_nok").text("No se ha podido realizar la insercción del nuevo registro debido a " +
					    "que existe otro registro con los mismos datos (Datos clave son número " +
			    	  	"de folio, rut trabajador, fecha término licencia).");
		        	$(".loadMenu").hide();
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
    }//
		
	function eliminar(plano) {		
		
		$("#txt_msj_ok").text("  "); $("#txt_msj_nok").text("  ");
		 $("#form_mantenedor_"+plano).find("input[name='op']").val("eliminar_"+plano);
		 
		 if(plano=="SIL"){
			 $("#form_mantenedor_"+plano).find("input[name='perpag']").val(obtenerPeriodoMantenedor(plano));
		 }else if(plano=="LM"){
			 $("#form_mantenedor_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoMantenedor(plano));
		 }
		 $("#form_mantenedor_"+plano).find(".txt_campo").removeAttr("disabled");
		 //$("#form_mantenedor_"+plano).find(".txt_campo").attr("disabled", "disabled");
		// alert("ser: "+$("#form_mantenedor_"+plano).serialize());
		 $.ajax({
            type: "POST",
            url: "./mantenedor_"+plano+".do",
            data: $("#form_mantenedor_"+plano).serialize(),
            beforeSend: function(){
         	   $("#contenedor-form_"+plano).empty();
          	  $(".loadMenu").show();
  	      	},
            success: function (data) {
            	$("#contenedor-form_"+plano).empty();
              	$("#contenedor-form_"+plano).append(data);
            },
            complete: function(){
     			//$("#dialog_form_"+plano).dialog( "close" );
            	$("#txt_msj_ok").text("Se ha eliminado el registro de manera satisfactoria."); 
            	$("#txt_msj_nok").text("No se ha podido eliminar el registro.");
            	$(".loadMenu").hide();
  			},
            error: function (xhr, ajaxOptions, thrownError) {
           	 //mostrar mensaje de error
                alert(xhr.status);
                alert(thrownError);
            }
		 });        
        return false; // required to block normal submit since you used ajax
    }
	
	function actualizar(plano) {
		$(".textMsj").hide();
		$("#txt_msj_ok").text("  "); $("#txt_msj_nok").text("  ");
		 //$("#dialog_mantenedor_"+plano).dialog("open");
		 $("#form_mantenedor_"+plano).find("input[name='op']").val("actualizar_"+plano);
		 if(plano=="SIL"){
			 $("#form_mantenedor_"+plano).find("input[name='perpag']").val(obtenerPeriodoMantenedor(plano));
		 }else if(plano=="LM"){
			 $("#form_mantenedor_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoMantenedor(plano));
		 }
		 $("#form_mantenedor_"+plano).find(".txt_campo").removeAttr('disabled');
		 //alert("}SER: "+$("#form_mantenedor_"+plano).serialize());
		 if(validarForm(plano,"form_mantenedor_"+plano)){	
			 $.ajax({
	           type: "POST",
	           url: "./mantenedor_"+plano+".do",
	           data: $("#form_mantenedor_"+plano).serialize(),
	           beforeSend: function(){
	        	   $("#contenedor-form_"+plano).empty();
	         	  $(".loadMenu").show();
	 	      },
	           success: function (data) {        	      	   
	        	 $("#contenedor-form_"+plano).empty();
	           	 $("#contenedor-form_"+plano).append(data);           	 
	           },
	           complete: function(){
	    			//$("#dialog_form_"+plano).dialog( "close" );
	        	   $("#txt_msj_ok").text("Se ha actualizado el registro de manera satisfactoria."); 
	        	   $("#txt_msj_nok").text("No se ha podido actualizar el registro.");
	        	   $(".loadMenu").hide(); 
	        	   $(".textMsj").show();
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
	
	function buscar(plano){
				
		var r=null;
		var f=null;
		$("#form_filtro_"+plano).find("input[name='op']").val("buscar_"+plano);
		if(plano=="SIL"){
			 $("#form_filtro_"+plano).find("input[name='perpag']").val(obtenerPeriodoMantenedor(plano));
			 r=$("#form_filtro_"+plano).find("input[name='ruttrabaj']").val().length;
			 f=$("#form_filtro_"+plano).find("input[name='nrofol']").val().length;
		 }else if(plano=="LM"){
			$("#form_filtro_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoMantenedor(plano));
			r=$("#form_filtro_"+plano).find("input[name='ruttrabaj']").val().length;
			f=$("#form_filtro_"+plano).find("input[name='folio']").val().length;
		 }
		
		if(r>0 || f>0){			
			$.ajax({
		           type: "POST",
		           url: "./mantenedor_"+plano+".do",
		           data: $("#form_filtro_"+plano).serialize(),
		           async : true,
		           beforeSend: function(){		        	   
		        	   $("#formLoadMant_"+plano).find("#loadMenuTabla_"+plano).show();
		           },
		           success: function (data) {
		          	 $("#contenedor-tabla_"+plano).empty();
		          	 $("#contenedor-tabla_"+plano).append(data);
		           },
		           complete: function(){
		        	   $("#formLoadMant_"+plano).find("#loadMenuTabla_"+plano).hide();
		        	   //$("#loadingSpinner").hide();
		        	  //$(".loadMenuMantenedor").hide();
		        	   $("#form_filtro_"+plano).find(".txt_campo").val('');
		        	   $("#form_filtro_"+plano).find("#lbl_msgBusqueda").text('');
		           },
		           error: function (xhr, ajaxOptions, thrownError) {
		          	 //mostrar mensaje de error
		               alert(xhr.status);
		               alert(thrownError);
		           }
				 }); 
		}else{			
			$("#form_filtro_"+plano).find("#lbl_msgBusqueda").text("Debe ingresar al menos un dato para la búsqueda.");
			//cambio de color
		}     
       return false; // required to block normal submit since you used ajax
	}
	
	function mostrar(plano,accion){ 
		 //alert("en doAjaxPost: "+id);
		$(".textMsj").hide();
		
		$("#form_mantenedor_"+plano).find("input[name='op']").val("mostrar_"+plano);	  	
  	  	$("#form_mantenedor_"+plano).find(".txt_campo").removeAttr('disabled');
		$.ajax({
          type: "POST",
          url: "./mantenedor_"+plano+".do",
          async : true,
          data: $("#form_mantenedor_"+plano).serialize(),
          beforeSend: function(){
        	  $("#dialog_form_"+plano).dialog("open");
        	  $("#contenedor-form_"+plano).empty();
        	  $(".loadMenu").show();
	      },
          success: function (data) {
         	 $("#contenedor-form_"+plano).empty();
         	 $("#contenedor-form_"+plano).append(data);
          },
          complete: function(){  
        	  $(".textMsj").hide();
        	  $(".loadMenu").hide();
   			//alert("ajax: terminado");
        	  if(accion=="eliminar"){        		  
        		  	$("#form_mantenedor_"+plano).find(".txt_campo").attr("disabled", "disabled");        		  	
        		  	var buttons = {};
      				buttons["Eliminar"] = function() { eliminar(plano);};
      				buttons["Cancelar"] = function() { $("#dialog_form_"+plano).dialog( "close" );};
      				$("#dialog_form_"+plano).dialog({buttons:buttons});
        	  }else if(accion=="actualizar"){
        		  /*
        		  if(plano=='LM'){
        		  	$("#form_mantenedor_"+plano).find("input[name='ruttrabaj']").attr("disabled", "disabled");
           	   		$("#form_mantenedor_"+plano).find("input[name='folio']").attr("disabled", "disabled");
           	   		$("#form_mantenedor_"+plano).find("input[name='fecterrepo']").attr("disabled", "disabled");
        		  }else if(plano=='SIL'){
          		  	$("#form_mantenedor_"+plano).find("input[name='ruttrabaj']").attr("disabled", "disabled");
           	   		$("#form_mantenedor_"+plano).find("input[name='nrofol']").attr("disabled", "disabled");
           	   		$("#form_mantenedor_"+plano).find("input[name='lichasfec']").attr("disabled", "disabled");
           	   		$("#form_mantenedor_"+plano).find("input[name='pagfol']").attr("disabled", "disabled");
        		  }
        		  */
        		  	var buttons = {};
        			buttons["Actualizar"] = function() { actualizar(plano);};
        			buttons["Cancelar"] = function() { $("#dialog_form_"+plano).dialog( "close" );};
        			$("#dialog_form_"+plano).dialog({buttons:buttons});
        	  }
        	  //$("#dialog_form_"+plano).dialog("open");
          },
          error: function (xhr, ajaxOptions, thrownError) {
         	 //mostrar mensaje de error
              alert(xhr.status);
              alert(thrownError);
          }
		 });        
      return false; // required to block normal submit since you used ajax
	}
	
	function mostrar_FormVacio(plano){ 
		 //alert("en doAjaxPost: "+id);
		
		$("#form_mantenedor_"+plano).find("input[name='op']").val("mostrar_Insertar_"+plano);
		 $.ajax({
         type: "POST",
         url: "./mantenedor_"+plano+".do",
         async : true,
         data: $("#form_mantenedor_"+plano).serialize(),         
         success: function (data) {
        	 $("#contenedor-form_"+plano).empty();
        	 $("#contenedor-form_"+plano).append(data);
         },
         error: function (xhr, ajaxOptions, thrownError) {
        	 //mostrar mensaje de error
             alert(xhr.status);
             alert(thrownError);
         }
		 });        
     return false; // required to block normal submit since you used ajax
	}
	
	function primeraPagina(plano) {
	   	$("#form_filtro_"+plano).find("input[name='op']").val("paginarInicio_"+plano);
   		if(plano=="SIL"){
			 $("#form_mantenedor_"+plano).find("input[name='perpag']").val(obtenerPeriodoMantenedor(plano));
		 }else if(plano=="LM"){
			 $("#form_mantenedor_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoMantenedor(plano));
		 }
   		
		 $.ajax({
          type: "POST",
          url: "./mantenedor_"+plano+".do",
          data: $("#form_filtro_"+plano).serialize(),
          async: true,
          beforeSend: function(){		        	   
       	   $("#formLoadMant_"+plano).find("#loadMenuTabla_"+plano).show();
          },
          success: function (data) {
         	 $("#contenedor-tabla_"+plano).empty();
         	 $("#contenedor-tabla_"+plano).append(data);
          },
          complete: function (){
        	  //downLoad();
        	  //$(".classLoading").hide();
        	  $("#formLoadMant_"+plano).find("#loadMenuTabla_"+plano).hide();
        	  $("#dialog_mantenedor_"+plano).dialog("open");
          },
          error: function (xhr, ajaxOptions, thrownError) {
         	 //mostrar mensaje de error
              alert(xhr.status);
              alert(thrownError);
          }
		 });        
      return false; // required to block normal submit since you used ajax
  }
	
    function avancePagina(plano) {	
    	 $(".classLoading").show();
    	 
    	$("#form_filtro_"+plano).find("input[name='op']").val("paginarAvance_"+plano);
    	if(plano=="SIL"){
			 $("#form_mantenedor_"+plano).find("input[name='perpag']").val(obtenerPeriodoMantenedor(plano));
		 }else if(plano=="LM"){
			 $("#form_mantenedor_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoMantenedor(plano));
		 }
    	
		 $.ajax({
           type: "POST",
           url: "./mantenedor_"+plano+".do",
           data: $("#form_filtro_"+plano).serialize(),
           async: true,
           beforeSend: function(){		        	   
           	   $("#formLoadMant_"+plano).find("#loadMenuTabla_"+plano).show();
           },
           success: function (data) {
          	 $("#contenedor-tabla_"+plano).empty();
          	 $("#contenedor-tabla_"+plano).append(data);
           },
           complete: function (){
        	   //downLoad();
        	   //$(".classLoading").hide();
        	   $("#formLoadMant_"+plano).find("#loadMenuTabla_"+plano).hide();
        	   $("#dialog_mantenedor_"+plano).dialog("open");
           },
           error: function (xhr, ajaxOptions, thrownError) {
          	 //mostrar mensaje de error
               alert(xhr.status);
               alert(thrownError);
           }
		 });        
       return false; // required to block normal submit since you used ajax
   }
    
   function retrocesoPagina(plano) {
	   	
   		$("#form_filtro_"+plano).find("input[name='op']").val("paginarRetroceso_"+plano);
   		if(plano=="SIL"){
			 $("#form_mantenedor_"+plano).find("input[name='perpag']").val(obtenerPeriodoMantenedor(plano));
		 }else if(plano=="LM"){
			 $("#form_mantenedor_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoMantenedor(plano));
		 }
   		
		 $.ajax({
          type: "POST",
          url: "./mantenedor_"+plano+".do",
          data: $("#form_filtro_"+plano).serialize(),
          async: true,
          beforeSend: function(){		        	   
          	   $("#formLoadMant_"+plano).find("#loadMenuTabla_"+plano).show();
          },
          success: function (data) {
         	 $("#contenedor-tabla_"+plano).empty();
         	 $("#contenedor-tabla_"+plano).append(data);
          },
          complete: function (){
        	  //downLoad();
        	  //$(".classLoading").hide();
        	  $("#formLoadMant_"+plano).find("#loadMenuTabla_"+plano).hide();
        	  $("#dialog_mantenedor_"+plano).dialog("open");
          },
          error: function (xhr, ajaxOptions, thrownError) {
         	 //mostrar mensaje de error
              alert(xhr.status);
              alert(thrownError);
          }
		 });        
      return false; // required to block normal submit since you used ajax
  }
   
   function ultimaPagina(plano) {
	   	
  		$("#form_filtro_"+plano).find("input[name='op']").val("paginarUltimo_"+plano);
  		if(plano=="SIL"){
			 $("#form_mantenedor_"+plano).find("input[name='perpag']").val(obtenerPeriodoMantenedor(plano));
		 }else if(plano=="LM"){
			 $("#form_mantenedor_"+plano).find("input[name='fecproceso']").val(obtenerPeriodoMantenedor(plano));
		 }
  		
		 $.ajax({
         type: "POST",
         url: "./mantenedor_"+plano+".do",
         data: $("#form_filtro_"+plano).serialize(),
         async: true,
         beforeSend: function(){		        	   
    	 	$("#formLoadMant_"+plano).find("#loadMenuTabla_"+plano).show();
         },
         success: function (data) {
        	 $("#contenedor-tabla_"+plano).empty();
        	 $("#contenedor-tabla_"+plano).append(data);
         },
         complete: function (){
       	  //downLoad();
       	  //$(".classLoading").hide();
        	 $("#formLoadMant_"+plano).find("#loadMenuTabla_"+plano).hide();
        	 $("#dialog_mantenedor_"+plano).dialog("open");
         },
         error: function (xhr, ajaxOptions, thrownError) {
        	 //mostrar mensaje de error
             alert(xhr.status);
             alert(thrownError);
         }
		 });        
     return false; // required to block normal submit since you used ajax
 }
   
   function limpiarFormulario(plano){
   		$("#form_mantenedor_"+plano).find(".txt_campo").removeAttr('disabled');
		$("#form_mantenedor_"+plano).find(".txt_campo").val('');
		$("#form_mantenedor_"+plano).find(".lbl_Error").text('');
		$("#form_mantenedor_"+plano).find(".lbl_Error").css({'background-color' : 'white'});
   }
    
    
    function upLoad(){
    	$("#loadingSpinner").show();
	}
	
	function downLoad(){
		$("#loadingSpinner").hide();
	}
	
	$(document).ready(function(){
		$("#loadingSpinner").hide();
	});
    
    
