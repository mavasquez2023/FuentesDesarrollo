/**
 * Código javascript y jquerry utilizado para manejo de interfaz de 
 * administración menus por usuarios admUsuarioMenu
 */

//[start: dialogs]----------	
		
	$(document).ready(function(){
		$(".loadGif").hide();   	
    });

//[end: dialogs]----------

//[start: acciones]----------	
	
	//Carga las tablas de usuarios y menu.
	function cargarListado_UsuarioMenu(){
		$("#form_adm_UsuarioMenu").find("input[name='op']").val("cargarListado_UsuarioMenu");		
//		 //alert("ser: "+$("#form_filtro_"+plano).serialize());
		 $.ajax({
           type: "POST",
           url: "./adm_UsuarioMenu.do",
           data: $("#form_adm_UsuarioMenu").serialize(),
           beforeSend: function(){           	
        	   $(".loadGif").show();   
 	      	},
           success: function (data) {           
          	 $("#contenedor-tabla_UsuarioMenu").empty();
          	 $("#contenedor-tabla_UsuarioMenu").append(data);
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
	
	
	function cargar_MenuPorUsuario(rutUser){
		//cargara la tabla de menus por usuarios.
		$("#form_adm_MenuPorUsuario").find("input[name='op']").val("listar_MenuPorUsuario");
		$("#form_adm_MenuPorUsuario").find("input[name='rut_user']").val(rutUser);
		//alert("ser: "+$("#form_filtro_"+plano).serialize());
		
		//deselect todos los checkbox del arbol de opciones, para una proxima seleccion limpia.
		var opciones = document.getElementsByName("menuArbol");
		for(var i=0; i<opciones.length;i++){
			opciones[i].checked= false;
		}
		
		$.ajax({
			type: "POST",
			url: "./adm_UsuarioMenu.do",
			data: $("#form_adm_MenuPorUsuario").serialize(),
			beforeSend: function(){           	
				$(".loadGif").show();   
	      	},
	      	success: function (data) {           
	      		$("#contenedor-tabla_menuPorUsuario").empty();
	      		$("#contenedor-tabla_menuPorUsuario").append(data);
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
	
	
	function asignar_UsuarioMenu(){
		
		//asignara menu selecionados al usuario.
		
		var $inputs = $('#form_adm_UsuarioMenu :checked');
		var values = {};
		var concat="";
		$inputs.each(function() {
			values[this.name] = $(this).val();
			if(values[this.name]!=''){
				concat+=values[this.name]+"#";
			}
		});
		
		//asigna menu a usuario, y recarga listado.
		$("#form_adm_MenuPorUsuario").find("input[name='op']").val("asignar_MenuPorUsuario");
		$("#form_adm_MenuPorUsuario").find("input[name='concat']").val(concat);
		//validamos seleccion de usuario
		var chkRd=$("#form_adm_UsuarioMenu").find("input[name='rd_User']:radio").is(':checked');
		//alert("chkRd: "+chkRd);
		if(chkRd){
			$.ajax({
				type: "POST",
				url: "./adm_UsuarioMenu.do",
				data: $("#form_adm_MenuPorUsuario").serialize(),
				beforeSend: function(){           	
					$(".loadGif").show();   
		      	},
		      	success: function (data) {           
		      		$("#contenedor-tabla_menuPorUsuario").empty();
		      		$("#contenedor-tabla_menuPorUsuario").append(data);
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
		}else{
			alert("seleccione un usuario");
		}
	}
	
	function quitar_UsuarioMenu(){
		//quitara menus al usuario.
		//asignara menu selecionados al usuario. 
		var $inputs = $('#form_adm_MenuPorUsuario :checked');
		var values = {};
		var concat="";
		$inputs.each(function() {
			values[this.name] = $(this).val();
			if(values[this.name]!=''){
				concat+=values[this.name]+"#";
			}
		});
		//recuperamos rut de usuario seleccionado
		var rutUser=$("input[name='rd_User']:checked").val();
		if(rutUser!= null || rutUser != ""){
			rutUser+="#"+concat;
			concat=rutUser;
			//asigna menu a usuario, y recarga listado.
			$("#form_adm_MenuPorUsuario").find("input[name='op']").val("quitar_MenuPorUsuario");
			$("#form_adm_MenuPorUsuario").find("input[name='concat']").val(concat);
			
			//alert("ser: "+$("#form_filtro_"+plano).serialize());
			$.ajax({
				type: "POST",
				url: "./adm_UsuarioMenu.do",
				data: $("#form_adm_MenuPorUsuario").serialize(),
				beforeSend: function(){           	
					$(".loadGif").show();   
		      	},
		      	success: function (data) {           
		      		$("#contenedor-tabla_menuPorUsuario").empty();
		      		$("#contenedor-tabla_menuPorUsuario").append(data);
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
		}else{
			alert("seleccione un usuario");
		}
	}
	
	
//[end: acciones]----------