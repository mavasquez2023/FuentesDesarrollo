/**
 * Código javascript y jquerry utilizado para manejo de interfaz 
 * de administración usuarios.
 */

//[start: dialogs]----------	

    $(document).ready(function(){
    	$("#dialog_form_Usuario").dialog({
    		dialogClass: "no-close",
    		open: function() { $(".ui-dialog-titlebar-close").hide(); },
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
   
    $('#dialogoFormularioUsuario').dialog({
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
				eliminar_Usuario();
				// Cerramos el diálogo
				$(this).dialog("close");
			},
			Cancelar : function() {
				// Cerramos el diálogo
				$(this).dialog("close");
			}
		}
	});
    
//[end: dialogs]----------


//[start: acciones]----------	

	//Abre interfaz de administración de usuario.
	function openAdm_Usuario(){
		$("#formAdmUsuario").submit();
	}
	
	//Realiza búsqueda de usuario.
	function buscar_Usuario(){
		$("#form_filtro_Usuario").find("input[name='op']").val("buscarUsuario");		
		var etq=null;
		etq=$("#form_filtro_Usuario").find("input[name='rut_user']").val();
		if(etq!="" && etq!=null){

		 $.ajax({
         type: "POST",
         url: "./administracionUsuario.do",
         data: $("#form_filtro_Usuario").serialize(),
         beforeSend: function(){           	
      	   $(".loadGif").show();   
	      	},
         success: function (data) {           
        	 $("#contenedor-tabla_Usuario").empty();
        	 $("#contenedor-tabla_Usuario").append(data);
         },
         complete: function (){ 
      	   $(".loadGif").hide();
      	 $("#form_filtro_Usuario").find("#rut_user_filtro_error").text("");
         },
         error: function (xhr, ajaxOptions, thrownError) {
        	 //mostrar mensaje de error
             alert(xhr.status);
             alert(thrownError);
         }
		 });        
		 return false; // required to block normal submit since you used ajax
		}else{
			$("#form_filtro_Usuario").find("#rut_user_filtro_error").text("*");
		}
	}
	
	//Carga listado completo en interfaz administración usuario.
	function cargarListado_Usuarios(){
		$("#form_filtro_Usuario").find("input[name='op']").val("cargarListado_Usuario");		
		//alert("ser: "+$("#form_filtro_"+plano).serialize());
		$.ajax({
			type: "POST",
			url: "./administracionUsuario.do",
			data: $("#form_filtro_Usuario").serialize(),
			beforeSend: function(){           	
				$(".loadGif").show();   
	      	},
	      	success: function (data) {           
	      		$("#contenedor-tabla_Usuario").empty();
	      		$("#contenedor-tabla_Usuario").append(data);
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
	
	//Ingresar nuevo registro para usuario.
	function openInsertar_Usuario(){
		$("#form_Mentenedor_Usuario").find("input[name='op']").val("mostrarFormUsuario");		
		//alert("ser: "+$("#form_Mentenedor_Usuario").serialize());
		$.ajax({
			type: "POST",
			url: "./administracionUsuario.do",
			data: $("#form_Mentenedor_Usuario").serialize(),
			beforeSend: function(){
				$("#form_Mentenedor_Usuario").find(".loadGif").show();
				$("#dialog_form_Usuario").dialog('option', 'title', 'Registrar Usuario');
				$("#dialog_form_Usuario").dialog("open");
				var buttons = {};
				buttons["Insertar"] = function() { insertar_Usuario();};
				buttons["Cerrar"] = function() { $("#dialog_form_Usuario").dialog( "close" );};		
				$("#dialog_form_Usuario").dialog({buttons:buttons});
			},
			success: function (data) {           
				$("#contenedor-form_Usuario").empty();
				$("#contenedor-form_Usuario").append(data);
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
	
	function insertar_Usuario(){
		$("#form_Mentenedor_Usuario").find("input[name='op']").val("insertarUsuario");		
		//alert("ser: "+$("#form_Mentenedor_Usuario").serialize());
		
		
		if(validarForm_User("form_Mentenedor_Usuario")){
			//alert("valForm true");
			$.ajax({
				type: "POST",
				url: "./administracionUsuario.do",
				data: $("#form_Mentenedor_Usuario").serialize(),
				beforeSend: function(){           	
					$("#form_Mentenedor_Usuario").find(".loadGif").show();				
				},
				success: function (data) {           
					$("#contenedor-form_Usuario").empty();
					$("#contenedor-form_Usuario").append(data);
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
		
	//Actualizar registro para usuario.
	function openActualizar_Usuario(id_user){
		$("#form_Mentenedor_Usuario").find("input[name='op']").val("buscarUsuario_form");
		$("#form_Mentenedor_Usuario").find("input[name='id_user']").val(id_user);
		
		//alert("ser: "+$("#form_filtro_"+plano).serialize());
		$.ajax({
			type: "POST",
			url: "./administracionUsuario.do",
			data: $("#form_Mentenedor_Usuario").serialize(),
			beforeSend: function(){
				$("#form_Mentenedor_Usuario").find(".loadGif").show();
				$("#dialog_form_Usuario").dialog('option', 'title', 'Actualizar Usuario');
				$("#dialog_form_Usuario").dialog("open");
				var buttons = {};
				buttons["Actualizar"] = function() { actualizar_Usuario();};
				buttons["Cerrar"] = function() { $("#dialog_form_Usuario").dialog( "close" );};		
				$("#dialog_form_Usuario").dialog({buttons:buttons});
			},
			success: function (data) {           
				$("#contenedor-form_Usuario").empty();
				$("#contenedor-form_Usuario").append(data);
			},
			complete: function (){        	 
				$(".loadGif").hide();
				$("#form_Mentenedor_Usuario").find("#rut_user").attr("disabled", "disabled");
			},
			error: function (xhr, ajaxOptions, thrownError) {
				//mostrar mensaje de error
				alert(xhr.status);
				alert(thrownError);
			}
		});        
		return false; // required to block normal submit since you used ajax
	}
	
	function actualizar_Usuario(){
		$("#form_Mentenedor_Usuario").find("input[name='op']").val("actualizarUsuario");		
		//alert("ser: "+$("#form_filtro_"+plano).serialize());
		
		
		if(validarForm_User("form_Mentenedor_Usuario")){
			$("#form_Mentenedor_Usuario").find(".txt_campo").removeAttr("disabled");
			$.ajax({
				type: "POST",
				url: "./administracionUsuario.do",
				data: $("#form_Mentenedor_Usuario").serialize(),
				beforeSend: function(){           	
					$("#form_Mentenedor_Usuario").find(".loadGif").show();				
				},
				success: function (data) {           
					$("#contenedor-form_Usuario").empty();
					$("#contenedor-form_Usuario").append(data);
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
	
	//Eliminar registro para usuario.
	function openEliminar_Usuario(id_user){
	
		$("#form_Mentenedor_Usuario").find("input[name='op']").val("buscarUsuario_form");
		$("#form_Mentenedor_Usuario").find("input[name='id_user']").val(id_user);
		//eliminar_Usuario();
		//alert("ser: "+$("#form_filtro_"+plano).serialize());
		$.ajax({
			type: "POST",
			url: "./administracionUsuario.do",
			data: $("#form_Mentenedor_Usuario").serialize(),
			beforeSend: function(){
				$("#form_Mentenedor_Usuario").find(".loadGif").show();
				$("#dialog_form_Usuario").dialog('option', 'title', 'Eliminar Usuario');
				
				$("#dialog_form_Usuario").dialog("open");
				var buttons = {};
				buttons["Eliminar"] = function() { 
					
					 $('#dialogoFormularioUsuario').dialog({
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
									eliminar_Usuario();
									// Cerramos el diálogo
									$(this).dialog("close");
								},
								Cancelar : function() {
									// Cerramos el diálogo
									$(this).dialog("close");
								}
							}
						});
					
					$('#dialogoFormularioUsuario').dialog('open');
				};
				buttons["Cerrar"] = function() { $("#dialog_form_Usuario").dialog( "close" );};		
				$("#dialog_form_Usuario").dialog({buttons:buttons});
			},
			success: function (data) {           
				$("#contenedor-form_Usuario").empty();
				$("#contenedor-form_Usuario").append(data);
			},
			complete: function (){        	 
				$(".loadGif").hide();
				$("#form_Mentenedor_Usuario").find(".txt_campo").attr("disabled", "disabled");
			},
			error: function (xhr, ajaxOptions, thrownError) {
				//mostrar mensaje de error
				alert(xhr.status);
				alert(thrownError);
			}
		});        
		return false; // required to block normal submit since you used ajax
	}
		
	function eliminar_Usuario(){
		$("#form_Mentenedor_Usuario").find("input[name='op']").val("eliminarUsuario");		
		//alert("ser: "+$("#form_filtro_"+plano).serialize());
		$("#form_Mentenedor_Usuario").find(".txt_campo").removeAttr("disabled");
		$.ajax({
			type: "POST",
			url: "./administracionUsuario.do",
			data: $("#form_Mentenedor_Usuario").serialize(),
			beforeSend: function(){           	
				$("#form_Mentenedor_Usuario").find(".loadGif").show();				
			},
			success: function (data) {           
				$("#contenedor-form_Usuario").empty();
				$("#contenedor-form_Usuario").append(data);
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
//[end: acciones]----------
	
//[START: gif]----------
	
//[end: gif]----------
	