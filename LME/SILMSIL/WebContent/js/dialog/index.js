	
	
	//Carga al inicio de la pantalla.
    $(document).ready(function(){
    	$("#selAnno").change(function(){
    		refrescar(4);
    	});
    	Disabled();
    });
    
    $(document).ready(function(){//asigna propiedades de pop up
    	$("#closeSesionContent").hide();
    });	
    
    $(document).ready(function(){
		$(".loadMenuTabla").hide();	
	});
   
//[dialogo de estado de procesos seleccionados]----------------------------------------------- 
  //Manejo opciones menú hacia actions procesar.
    function asignarValor(a){
    	var radio = $("input:radio[name='rd_Trim']:checked").val();
    	var ahno = $("#selAnno").val();
    	
    	if(radio == undefined){
    		alert('Por favor, marque una opción de trimestre.');
    	}else if(ahno.length==0){
    		alert('Por favor, ingrese un año válido.');
    	}else{
    		$("#hd_Asignar").val(a);
    		
    		//$("#formCargar").submit();
    		if(a=='1'){
    			estadoEjecucion();  			
    		}
    		if(a=='5'){
    			estadoDescargar();
    		}
    	}
    }
    
    $(document).ready(function(){
    	$("#dialog_form_Ejecucion").dialog({
            resizable: false,
            width: 850,
            modal: true,
            autoOpen:false
    	});
    });
    
    function estadoEjecucion() {
    	if(validarlistaProceso()){
    		$("#formCargar").find("input[name='metodo']").val("asignar");
			$.ajax({
				type: "POST",
				url: "./asignando.do",
				data: $("#formCargar").serialize(),
				beforeSend: function(){
					$("#dialog_form_Ejecucion").dialog("open");
					$("#form_ejecucion").empty();
					$(".loadMenu").show();
				},
				success: function (data) {
					$("#contenedor-form_Ejecucion").empty();
					$("#contenedor-form_Ejecucion").append(data);
				},
				complete: function (){
					//$("#dialog_form_Ejecucion").dialog("open");
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
  } 
    
  function reprocesar(){	  
	  //obtencion de proceso_periodos para concatenar y enviar;	  
	  var $inputs = $('#form_ejecucion :input');
	  //var $inputs = $('.ejecucionInput:input[type="text"]');	  
	  var values = {};
	  var concat="";
	  $inputs.each(function() {
	      values[this.name] = $(this).val();	      
	      if(values[this.name]!=''){
	    	 concat+=values[this.name]+"#";
	      }
	  });
	  $("#form_ejecucion").find("input[name='op']").val("reprocesar");	  
	  $("#form_ejecucion").find("input[name='concat']").val(concat);
	  
	 // alert("CONCAT: "+$("#form_ejecucion").find("input[name='concat']").val());
	  // alert("SER: "+$("#form_ejecucion").serialize());
	  
	  $.ajax({
	      type: "POST",
	      url: "./reprocesar.do",
	      data: $("#form_ejecucion").serialize(),
	      async: true,
	      complete: function (){
	      	//$("#dialog_form_Ejecucion").dialog("close");
	      	refrescar(4);
	      },
	      error: function (xhr, ajaxOptions, thrownError) {
	     	 //mostrar mensaje de error
	          alert(xhr.status);
	          alert(thrownError);
	      }
		 });        
      return false; // required to block normal submit since you used ajax
  }  
  
  function revalidar(){	  
	  //obtencion de proceso_periodos para concatenar y enviar;	  
	  var $inputs = $('#form_ejecucion :input');
	  //var $inputs = $('.ejecucionInput:input[type="text"]');	  
	  var values = {};
	  var concat="";
	  $inputs.each(function() {
	      values[this.name] = $(this).val();	      
	      if(values[this.name]!=''){
	    	 concat+=values[this.name]+"#";
	      }
	  });	  
	  $("#form_ejecucion").find("input[name='op']").val("revalidar");	  
	  $("#form_ejecucion").find("input[name='concat']").val(concat);	    
	  $.ajax({
	      type: "POST",
	      url: "./revalidar.do",
	      data: $("#form_ejecucion").serialize(),
	      async: true,
	      complete: function (){
	      	//$("#dialog_form_Ejecucion").dialog("close");
	      	refrescar(4);
	      },
	      error: function (xhr, ajaxOptions, thrownError) {
	     	 //mostrar mensaje de error
	          alert(xhr.status);
	          alert(thrownError);
	      }
		 });        
      return false; // required to block normal submit since you used ajax
  }  
  

    //Botón REFRESCAR ESTADO.
    function refrescar(a){
    	$("#hd_Asignar").val(a);
    	$("#formCargar").submit();
    }
    //Deshabilita y habilita checkbox y radio button.
    function Disabled(){
    	var ahno = $("#selAnno").val();
    	var mes = $("#hd_Date").val();
    	var anno = $("#hd_Ahno").val();
    	var meses = ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"];
    	var index;
    			
    	for(index = 0; index < meses.length; ++index){
    		if(anno >= ahno){
    			//Habilitar y deshabilitar campos checkbox.	
	    		if(mes < ahno+meses[index]){		
					$("#chk_SIL"+meses[index]).attr("disabled", true);	
					$("#chk_LM"+meses[index]).attr("disabled", true);		
	    		}else{
	    			$("#chk_SIL"+meses[index]).attr("disabled", false);	
					$("#chk_LM"+meses[index]).attr("disabled", false);
	    		}
	    		//Habilitar y deshabilitar campos radio button.
	    		if(mes > (ahno+'03')){
	    			document.getElementsByName("rd_Trim")[0].disabled = false;
	    			document.getElementsByName("rd_Trim")[1].disabled = false;
	    			document.getElementsByName("rd_Trim")[2].disabled = false;
	    			document.getElementsByName("rd_Trim")[3].disabled = false;
	    		}
	    		if(mes <= (ahno+'03')){
	    			document.getElementsByName("rd_Trim")[0].disabled = false;
	    			document.getElementsByName("rd_Trim")[1].disabled = true;
	    			document.getElementsByName("rd_Trim")[2].disabled = true;
	    			document.getElementsByName("rd_Trim")[3].disabled = true;
	    		}
	    		if((mes > (ahno+'03'))&&(mes <= (ahno+'06'))){
	    			document.getElementsByName("rd_Trim")[0].disabled = false; 
	    			document.getElementsByName("rd_Trim")[1].disabled = false;
	    			document.getElementsByName("rd_Trim")[2].disabled = true;
	    			document.getElementsByName("rd_Trim")[3].disabled = true;
	    		}
	    		if((mes > (ahno+'06'))&&(mes <= (ahno+'09'))){
	    			document.getElementsByName("rd_Trim")[0].disabled = false;
	    			document.getElementsByName("rd_Trim")[1].disabled = false;
	    			document.getElementsByName("rd_Trim")[2].disabled = false;
	    			document.getElementsByName("rd_Trim")[3].disabled = true;
	    		}
	    		if((mes > (ahno+'09'))&&(mes <= (ahno+'12'))){
	    			document.getElementsByName("rd_Trim")[0].disabled = false;
	    			document.getElementsByName("rd_Trim")[1].disabled = false;
	    			document.getElementsByName("rd_Trim")[2].disabled = false;
	    			document.getElementsByName("rd_Trim")[3].disabled = true;
	    		}
    		}else if(anno < ahno){
    			//Desahilitar campos checkbox.
    			$("#chk_SIL"+meses[index]).attr("disabled", true);	
				$("#chk_LM"+meses[index]).attr("disabled", true);
				//Desahilitar campos radio button.
				document.getElementsByName("rd_Trim")[0].disabled = true;
				document.getElementsByName("rd_Trim")[1].disabled = true;
	    		document.getElementsByName("rd_Trim")[2].disabled = true;
	    		document.getElementsByName("rd_Trim")[3].disabled = true;
    		}
    	}
    }
    //Abre Pop-Up para proceso seleccionado.
    /*function goCorregir(){
 	   	var periodo = document.getElementsByName("hd_Periodo");
    	var index;
    	//Selecciona los campos seleccionados.
    	for(index = 0; index < periodo.length; index++){
    		if(periodo[index].checked){
    			alert('Checked ->' +index + ' período : ' + periodo[index].value);
    		}
    		alert(index + ' período : ' + periodo[index].value);
    	}
    }*/
    
    
//[funciones para activar bloqueo chk]-----------------------------------------------------------------------------------------------
  
    
    $(document).ready(function(){
    	$(".radioCHK").change(function(){
            //alert("ha cmabiado el radio: "+$("input[name='rd_Trim']:checked").val());
            chkTrimestre();
    	});
    });
    
    function chkTrimestre(){
    	//rd_Trim
    	var trim="";
    	var index=0;
    	var keyChk=0;    	
    	//alert("work trim [VAL]: "+$("input[name='rd_Trim']:checked").val());
    	//alert("work trim [IS ]: "+$("input[name='rd_Trim']").is(":checked"));
    	for(index = 1; index <= 12; index++){
    		if(index<10){
    			if($("input[name='chk_SIL0"+index+"']").is(":checked")){
    				keyChk+=1;
    			}
    			if($("input[name='chk_LM0"+index+"']").is(":checked")){
    				keyChk+=1;
    			}
    			
    		}else{
    			if($("input[name='chk_SIL"+index+"']").is(":checked")){
    				keyChk+=1;
    			}
    			if($("input[name='chk_LM"+index+"']").is(":checked")){
    				keyChk+=1;
    			}        			
    		}
    	} 		
    	
	    	if($("input[name='rd_Trim']:checked").val()){    		
	    		//bloqueamos todo si algun trimestre esta seleccionado.
	        	for(index = 1; index <= 12; index++){
	        		if(index<10){
	        			$("input[name='chk_SIL0"+index+"']").attr('checked', false);
	        			$("input[name='chk_SIL0"+index+"']").attr("disabled", true);
	        			$("input[name='chk_LM0"+index+"']").attr('checked', false);
	        			$("input[name='chk_LM0"+index+"']").attr("disabled", true);
	        		}else{
	        			$("input[name='chk_SIL"+index+"']").attr('checked', false);
	        			$("input[name='chk_SIL"+index+"']").attr("disabled", true);
	        			$("input[name='chk_LM"+index+"']").attr('checked', false);
	        			$("input[name='chk_LM"+index+"']").attr("disabled", true);        			
	        		}
	        	}    		
	        	//desbloqueamos solo los que corresponden al trimestre seleccionado.  
	    		trim=$("input[name='rd_Trim']:checked").val();  
	    		if(trim==1){
	    			$("input[name='chk_SIL01']").attr("disabled", false);
	    			//$("input[name='chk_SIL01']").attr('checked', true);
	    			$("input[name='chk_SIL02']").attr("disabled", false);
	    			//$("input[name='chk_SIL02']").attr('checked', true);
	    			$("input[name='chk_SIL03']").attr("disabled", false);
	    			//$("input[name='chk_SIL03']").attr('checked', true);
	    			
	    			$("input[name='chk_LM01']").attr("disabled", false);
	    			//$("input[name='chk_LM01']").attr('checked', true);
	    			$("input[name='chk_LM02']").attr("disabled", false);
	    			//$("input[name='chk_LM02']").attr('checked', true);
	    			$("input[name='chk_LM03']").attr("disabled", false);
	    			//$("input[name='chk_LM03']").attr('checked', true);
	    		}
	    		if(trim==2){
	    			$("input[name='chk_SIL04']").attr("disabled", false);
	    			//$("input[name='chk_SIL04']").attr('checked', true);
	    			$("input[name='chk_SIL05']").attr("disabled", false);
	    			//$("input[name='chk_SIL05']").attr('checked', true);
	    			$("input[name='chk_SIL06']").attr("disabled", false);
	    			//$("input[name='chk_SIL06']").attr('checked', true);
	    			
	    			$("input[name='chk_LM04']").attr("disabled", false);
	    			//$("input[name='chk_LM04']").attr('checked', true);
	    			$("input[name='chk_LM05']").attr("disabled", false);
	    			//$("input[name='chk_LM05']").attr('checked', true);
	    			$("input[name='chk_LM06']").attr("disabled", false);
	    			//$("input[name='chk_LM06']").attr('checked', true);
	    		}
	    		if(trim==3){
	    			$("input[name='chk_SIL07']").attr("disabled", false);
	    			//$("input[name='chk_SIL07']").attr('checked', true);
	    			$("input[name='chk_SIL08']").attr("disabled", false);
	    			//$("input[name='chk_SIL08']").attr('checked', true);
	    			$("input[name='chk_SIL09']").attr("disabled", false);
	    			//$("input[name='chk_SIL09']").attr('checked', true);
	    			
	    			$("input[name='chk_LM07']").attr("disabled", false);
	    			//$("input[name='chk_LM07']").attr('checked', true);
	    			$("input[name='chk_LM08']").attr("disabled", false);
	    			//$("input[name='chk_LM08']").attr('checked', true);
	    			$("input[name='chk_LM09']").attr("disabled", false);
	    			//$("input[name='chk_LM09']").attr('checked', true);
	    		}
	    		if(trim==4){
	    			$("input[name='chk_SIL10']").attr("disabled", false);
	    			//$("input[name='chk_SIL10']").attr('checked', true);
	    			$("input[name='chk_SIL11']").attr("disabled", false);
	    			//$("input[name='chk_SIL11']").attr('checked', true);
	    			$("input[name='chk_SIL12']").attr("disabled", false);
	    			//$("input[name='chk_SIL12']").attr('checked', true);
	    			
	    			$("input[name='chk_LM10']").attr("disabled", false);
	    			//$("input[name='chk_LM10']").attr('checked', true);
	    			$("input[name='chk_LM11']").attr("disabled", false);
	    			//$("input[name='chk_LM11']").attr('checked', true);
	    			$("input[name='chk_LM12']").attr("disabled", false);
	    			//$("input[name='chk_LM12']").attr('checked', true);
	    		}	
	    	}else{
	    		alert("Debe seleccionar a lo menos un trimestre.");    		
	    	}
    	
    }//end chkTrimestre

    
//[ajustes dialog descarga de archivos]------------------------------
    
    $(document).ready(function(){
    	$("#dialog_form_Descarga").dialog({
            resizable: false,
            width: 850,
            modal: true,
            autoOpen:false
    	});
    });
    function estadoDescargar(){		
		if(validarlistaProceso()){
			//alert("val: "+validarlistaProceso());
			$("#formCargar").find("input[name='metodo']").val("asignar");
			$("#formCargar").find("input[name='keyProcesoPeriodo']").val("");
			$("#form_descarga").empty();
			$.ajax({
				type: "POST",
				url: "./asignando.do",
				data: $("#formCargar").serialize(),
				beforeSend: function(){
					$("#errorDescarga").empty();
					$("#dialog_form_Descarga").dialog("open");
					$(".loadMenu").show();
				},
				success: function (data) {
					$("#contenedor-form_Descarga").empty();
					$("#contenedor-form_Descarga").append(data);
				},
				complete: function (){	    	
//		      		$("#dialog_form_Descarga").dialog("open");
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
	  }//end  estadoDescargar
    
    function estadoDescargarUno(opc){
		$("#formCargar").find("input[name='metodo']").val("asignar");
		$("#formCargar").find("input[name='hd_Asignar']").val('5');
		$("#formCargar").find("input[name='keyProcesoPeriodo']").val(opc);
		$("#form_descarga").empty();
		$("#dialog_form_Descarga").dialog("open");
		$.ajax({
			type: "POST",
			url: "./asignando.do",
			data: $("#formCargar").serialize(),
			beforeSend: function(){
				$("#errorDescarga").empty();
				$(".loadMenu").show();
			},
			success: function (data) {
				$("#contenedor-form_Descarga").empty();
				$("#contenedor-form_Descarga").append(data);
			},
			complete: function (){
				//$("#dialog_form_Descarga").dialog("open");
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
    
    function descargar(){
	  $("#form_descarga").find("input[name='op']").val("descargarZip");
	  //alert("ruta: "+$("#form_descarga").find("input[name='nombreZip']").val());
	  document["form_descarga"].submit();
    }  
    
    function validarlistaProceso(){		    	
    	var key=false;
    	var keyRdo=false;
    	var keyChk=0;
    	var index=0;
    	//alert("work trim [VAL]: "+$("input[name='rd_Trim']:checked").val());
    	//alert("work trim [IS ]: "+$("input[name='rd_Trim']").is(":checked"));    	  	
    	if($("input[name='rd_Trim']:checked").val()){
    		keyRdo=true;
    		//verificamos si existe un mes en checked
        	for(index = 1; index <= 12; index++){
        		if(index<10){
        			if($("input[name='chk_SIL0"+index+"']").is(":checked")){
        				keyChk+=1;
        			}
        			if($("input[name='chk_LM0"+index+"']").is(":checked")){
        				keyChk+=1;
        			}
        			
        		}else{
        			if($("input[name='chk_SIL"+index+"']").is(":checked")){
        				keyChk+=1;
        			}
        			if($("input[name='chk_LM"+index+"']").is(":checked")){
        				keyChk+=1;
        			}        			
        		}
        	}   
    	}
    	if(keyRdo && keyChk>0){
    		key=true;
    	}else{
    		if(keyRdo && keyChk==0){
    			alert("Debe seleccionar al menos un mes para procesar");
    		}else if(!keyRdo){
    			alert("Debe seleccionar un trimestre");
    		}
    	}
    	return key;
    }//end validarListaProceso
    
    function goOut(){
    	//alert('Cerrando sesión.');
    	window.open("", "_self");
    	window.close();
    	
    }
    
    function cerrar(){
    	window.close();
    }
    
