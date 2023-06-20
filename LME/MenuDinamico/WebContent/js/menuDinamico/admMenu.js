/**
 * Código javascript y jquerry utilizado para manejo de interfaz de 
 * administración menu
 */

//[start: dialogs]----------	
	$(document).ready(function(){
    	
		$("#dialog_form_Menu").dialog({
            resizable: false,
            width: 450,
            modal: true,
            autoOpen:false
    	});
		
    });
	
	$(document).ready(function(){
		$(".loadGif").hide();   	
    });
	
	 $(document).ready(function(){
		 $("#form_filtro_Usuario").keypress(
			function(e) {
				if (e.which == 13) {					
					return false;
				}
			}
		);	
		
		$("#form_filtro_Menu").keypress(
			function(e) {					
				if (e.which == 13) {						
					return false;
				}
			}
		);
	 });
	
	 //Limitar tamaño texto	
	 $(document).ready(function(){	 
		 ajusteStringMenu();
	 });
	 
//[end: dialogs]----------

	
//[start: acciones]----------	

	//Abre interfaz de administración de menu.
	function openAdm_Menu(){
		$("#formAdmMenu").submit();
	}
	
	//Dummy carga ventana administración de ítems de menú.
	function openAdmMenu(){
		$("#formMantAdmItems").submit();
	}
	
	//Realiza búsqueda de menu.
	function buscar_Menu(){
		
		$("#form_filtro_Menu").find("input[name='op']").val("buscarMenu");		
//		 //alert("ser: "+$("#form_filtro_"+plano).serialize());
		var etq=null;
		etq=$("#form_filtro_Menu").find("input[name='etiqueta']").val();
		if(etq!="" && etq!=null){			
			$.ajax({
	          type: "POST",
	          url: "./administracionMenu.do",
	          data: $("#form_filtro_Menu").serialize(),
	          beforeSend: function(){           	
	       	   $(".loadGif").show();   
		      	},
	          success: function (data) {           
	         	 $("#contenedor-tabla_Menu").empty();
	         	 $("#contenedor-tabla_Menu").append(data);
	          },
	          complete: function (){ 
	       	   $(".loadGif").hide();
	       	   $("#form_filtro_Menu").find("#etiqueta_filtro_error").text("");
	          },
	          error: function (xhr, ajaxOptions, thrownError) {
	         	 //mostrar mensaje de error
	              alert(xhr.status);
	              alert(thrownError);
	          }
			 });        
			return false; // required to block normal submit since you used ajax
		}else{
			$("#form_filtro_Menu").find("#etiqueta_filtro_error").text("*");
		}
	}
	
	//Carga listado completo en interfaz administración menu.
	function cargarListado_Menu(){
		$("#form_filtro_Menu").find("input[name='op']").val("cargarListado_Menu");		
//		 //alert("ser: "+$("#form_filtro_"+plano).serialize());
		 $.ajax({
           type: "POST",
           url: "./administracionMenu.do",
           data: $("#form_filtro_Menu").serialize(),
           beforeSend: function(){           	
        	   $(".loadGif").show();   
 	      	},
           success: function (data) {           
          	 $("#contenedor-tabla_Menu").empty();
          	 $("#contenedor-tabla_Menu").append(data);
           },
           complete: function (){ 
        	   $(".loadGif").hide();
           },
           error: function (xhr, ajaxOptions, thrownError) {
          	 //mostrar mensaje de error
               alert(xhr.status);
               alert(thrownError);
           }
		 });        
       return false; // required to block normal submit since you used ajax
	}//end cargarListado_Menu
	
	
	

	function openInsertar_Menu(){
		$("#form_Mentenedor_Menu").find("input[name='op']").val("mostrarFormMenu");		
//		 //alert("ser: "+$("#form_filtro_"+plano).serialize());
		 $.ajax({
         type: "POST",
         url: "./administracionMenu.do",
         data: $("#form_Mentenedor_Menu").serialize(),
         beforeSend: function(){
        	 $("#form_Mentenedor_Menu").find(".loadGif").show();
    	 	 $("#dialog_form_Menu").dialog('option', 'title', 'Insertar Menú');
    	 	 $("#dialog_form_Menu").dialog("open");
    	 	 var buttons = {};
    	 	 buttons["Insertar"] = function() { insertar_Menu();};
    	 	 buttons["Cerrar"] = function() { $("#dialog_form_Menu").dialog( "close" );};		
    	 	 $("#dialog_form_Menu").dialog({buttons:buttons});
	     },
         success: function (data) {           
        	 $("#contenedor-form_Menu").empty();
        	 $("#contenedor-form_Menu").append(data);
         },
         complete: function (){        	 
      	   $(".loadGif").hide();      	   
         },
         error: function (xhr, ajaxOptions, thrownError) {
        	 //mostrar mensaje de error
             alert(xhr.status);
             alert(thrownError);
         }
		 });        
     return false; // required to block normal submit since you used ajax
	}
	
	//Ingresar nuevo registro para menu.
	function insertar_Menu(){
		$("#form_Mentenedor_Menu").find("input[name='op']").val("insertarMenu");		
//		 //alert("ser: "+$("#form_filtro_"+plano).serialize());
		
		var etq = $("#form_Mentenedor_Menu").find("input[name='etiqueta']").val();
		var url = $("#form_Mentenedor_Menu").find("input[name='enlace']").val();
		var nodo = $("#form_Mentenedor_Menu").find("input[name='nodoPadre']").val();
		var nivel = $("#form_Mentenedor_Menu").find("input[name='nivel']").val();
		var hj = $("#flgHoja").val();
		var seg = $("#seguridad").val();
		
		
		if(etq="" || etq.length==0){
			alert('El campo de etiqueta está vacio.');
		}else if(url="" || url.length==0){
			alert('El campo de url está vacio.');
		}else if(nodo="" || nodo.length==0){
			alert('El campo de nodo está vacio.');
		}else if(nivel="" || nivel.length==0 || isText(nivel)){
			alert('El campo de nivel está vacio o contiene letras.');
		}else if(hj="" || hj.length==0){
			alert('El campo de hoja está vacio.');
		}else if(seg="" || seg.length==0){
			alert('El campo de seguridad está vacio.');
		}else{
			$.ajax({
		        type: "POST",
		        url: "./administracionMenu.do",
		        data: $("#form_Mentenedor_Menu").serialize(),
		        beforeSend: function(){           	
		        	$("#form_Mentenedor_Menu").find(".loadGif").show();
		     	   
			    },
		        success: function (data) {           
		       	 $("#contenedor-form_Menu").empty();
		       	 $("#contenedor-form_Menu").append(data);
		        },
		        complete: function (){        	 
		     	   $(".loadGif").hide();
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
	

	function openActualizar_Menu(codMenu){
		$("#form_Mentenedor_Menu").find("input[name='op']").val("buscarMenu_form");
		$("#form_Mentenedor_Menu").find("input[name='codMenu']").val(codMenu);
		//alert("cod: "+codMenu);
//		 //alert("ser: "+$("#form_filtro_"+plano).serialize());
		
		
		$.ajax({
			type: "POST",
			url: "./administracionMenu.do",
			data: $("#form_Mentenedor_Menu").serialize(),
			beforeSend: function(){
				$("#form_Mentenedor_Menu").find(".loadGif").show();
				$("#dialog_form_Menu").dialog('option', 'title', 'Actualizar Menú');
				$("#dialog_form_Menu").dialog("open");
				var buttons = {};
				buttons["Actualizar"] = function() { actualizar_Menu();};
				buttons["Cerrar"] = function() { $("#dialog_form_Menu").dialog( "close" );};		
				$("#dialog_form_Menu").dialog({buttons:buttons});
			},
			success: function (data) {           
				$("#contenedor-form_Menu").empty();
				$("#contenedor-form_Menu").append(data);
			},
			complete: function (){        	 
				$(".loadGif").hide();      	   
			},
			error: function (xhr, ajaxOptions, thrownError) {
				//mostrar mensaje de error
				alert(xhr.status);
				alert(thrownError);
			}
		});        
		return false; // required to block normal submit since you used ajax
	}
	
	//Actualizar registro para menu.
	function actualizar_Menu(){
		$("#form_Mentenedor_Menu").find("input[name='op']").val("actualizarMenu");		
//		 //alert("ser: "+$("#form_filtro_"+plano).serialize());
		 
		var etq = $("#form_Mentenedor_Menu").find("input[name='etiqueta']").val();
		var url = $("#form_Mentenedor_Menu").find("input[name='enlace']").val();
		var nodo = $("#form_Mentenedor_Menu").find("input[name='nodoPadre']").val();
		var nivel = $("#form_Mentenedor_Menu").find("input[name='nivel']").val();
		var hj = $("#flgHoja").val();
		var seg = $("#seguridad").val();
		
		
		if(etq="" || etq.length==0){
			alert('El campo de etiqueta está vacio.');
		}else if(url="" || url.length==0){
			alert('El campo de url está vacio.');
		}else if(nodo="" || nodo.length==0){
			alert('El campo de nodo está vacio.');
		}else if(nivel="" || nivel.length==0){
			alert('El campo de nivel está vacio.');
		}else if(hj="" || hj.length==0){
			alert('El campo de hoja está vacio.');
		}else if(seg="" || seg.length==0){
			alert('El campo de seguridad está vacio.');
		}else{
		$.ajax({
		       type: "POST",
		       url: "./administracionMenu.do",
		       data: $("#form_Mentenedor_Menu").serialize(),
		       beforeSend: function(){           	
		       	$("#form_Mentenedor_Menu").find(".loadGif").show();
		    	   
			    },
		       success: function (data) {           
		      	 $("#contenedor-form_Menu").empty();
		      	 $("#contenedor-form_Menu").append(data);
		       },
		       complete: function (){        	 
		    	   $(".loadGif").hide();
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
	

	function openEliminar_Menu(codMenu){
		$("#form_Mentenedor_Menu").find("input[name='op']").val("buscarMenu_form");
		$("#form_Mentenedor_Menu").find("input[name='codMenu']").val(codMenu);
//		 //alert("ser: "+$("#form_filtro_"+plano).serialize());

		$.ajax({
			type: "POST",
			url: "./administracionMenu.do",
			data: $("#form_Mentenedor_Menu").serialize(),
			beforeSend: function(){
				$("#form_Mentenedor_Menu").find(".loadGif").show();
				$("#dialog_form_Menu").dialog('option', 'title', 'Eliminar Menú');
				//$("#dialog_form_Menu").find(".txt_campo").attr("disabled", "disabled");
				
				$("#dialog_form_Menu").dialog("open");
				var buttons = {};
				buttons["Eliminar"] = function() { eliminar_Menu();};
				buttons["Cerrar"] = function() { $("#dialog_form_Menu").dialog( "close" );};		
				$("#dialog_form_Menu").dialog({buttons:buttons});
			},
			success: function (data) {           
				$("#contenedor-form_Menu").empty();
				$("#contenedor-form_Menu").append(data);
			},
			complete: function (){        	 
				$(".loadGif").hide(); 
				$("#dialog_form_Menu").find(".txt_campo").attr("disabled", "disabled");
			},
			error: function (xhr, ajaxOptions, thrownError) {
				//mostrar mensaje de error
				alert(xhr.status);
				alert(thrownError);
			}
		});        
		return false; // required to block normal submit since you used ajax
	}
	$('#dialogoFormulario').dialog({
		// Indica si la ventana se abre de forma automática
		autoOpen : false,
		// Indica si la ventana es modal
		modal : true,
		// Largo
		width : 350,
		// Alto
		height : 'auto',

		resizable : false,

		buttons : {
			Continuar : function() {
				ejecutaAjax();
				// Cerramos el diálogo
				$(this).dialog("close");
			},
			Cancelar : function() {
				// Cerramos el diálogo
				$(this).dialog("close");
			}
		}
	});
	//Eliminar registro para menu.
	function eliminar_Menu(){
	$('#dialogoFormulario').dialog({
			// Indica si la ventana se abre de forma automática
			autoOpen : false,
			// Indica si la ventana es modal
			modal : true,
			// Largo
			width : 350,
			// Alto
			height : 'auto',
			resizable : false,
			buttons : {
				Continuar : function() {
					ejecutaAjax();
					// Cerramos el diálogo
					$(this).dialog("close");
				},
				Cancelar : function() {
					// Cerramos el diálogo
					$(this).dialog("close");
				}
			}
		});		
		$("#form_Mentenedor_Menu").find("input[name='op']").val("eliminarMenu");		
		$("#form_Mentenedor_Menu").find(".txt_campo").removeAttr("disabled");
		$('#dialogoFormulario').dialog('open');
//		 //alert("ser: "+$("#form_filtro_"+plano).serialize());			      
		return false; // required to block normal submit since you used ajax	
	}
	function ejecutaAjax(){
		$.ajax({
			type: "POST",
			url: "./administracionMenu.do",
			data: $("#form_Mentenedor_Menu").serialize(),
			beforeSend: function(){ 
				$("#form_Mentenedor_Menu").find(".loadGif").show();
			},
			success: function (data) {           
				$("#contenedor-form_Menu").empty();
				$("#contenedor-form_Menu").append(data);
			},
			complete: function (){        	 
				$(".loadGif").hide();
			},
			error: function (xhr, ajaxOptions, thrownError) {
				//mostrar mensaje de error
				alert(xhr.status);
				alert(thrownError);
			}
		});
		
	}
	
	function ajusteStringMenu(){
		 var cantRegis = $("#cantRegis").val(); 
		   var index=0;
		   for(index = 0; index < cantRegis; ++index){
			   //var longitud=$("#url_jstl_"+index).width(); 
			   var longitud=50;
			   var texto=$("#url_jstl_"+index).text();
			   var aux="";
			   try {
				    aux=$.trim($("#url_jstl_"+index).text());
				} catch(err) {
				    aux=texto.trim();
				} 
		     if(aux.length > longitud){  
		    	 aux=aux.substring(0,longitud)+"...";  
		         $("#url_jstl_"+index).text(aux); 
		     };	  
		   };
	}
	
//[end: acciones]----------tro para menu.
	
	function isNumerico(valor){
		var key=false;
		if (/^([0-9])*$/.test(valor)){
			key=true;
	   	}
		return key;
	}
	
	function isText(valor){
		var key=false;
		if (/^[a-zA-Z]*$/.test(valor)){
			key=true;
	   	}
		return key;
	}