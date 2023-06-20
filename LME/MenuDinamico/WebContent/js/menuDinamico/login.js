/**
 * Código javascript y jquerry utilizado para manejo de interfaz de ingreso 
 * y autentificación de usuarios al sistema a través de LDAP.
 */

	//Ingresar login.
	function validarLogin(){
		var login =  $("#claveInicial").val();
		var pass = $("#claveNueva").val();
		
		if(login=="" || login.length==0){
			alert("Debe de ingresar sus datos de acreditación de usuario.");
		}else if(pass=="" || pass.length==0){
			alert("Debe de ingresar sus datos de acreditación de usuario.");
		}else{
			$("#formLogin").submit();
		}
	}

	function closeSesion(){	
		$("#closeSesionForm").submit();
	}
	
	$(document).ready(function(){//asigna propiedades de pop up
    	$("#closeSesionContent").hide();
    });	
	
	function goBack(){
//		window.history.back();
		window.location.href = "./login.jsp";
	}