dojo.require('dijit.Dialog');
dojo.require('dijit.form.TextBox');
dojo.require('dijit.form.CheckBox');
dojo.require('dijit.form.Form');

function cargarAjax(div, action, formulario) {

	var splash="<table align='center' class='texto' width='100%' height='320'><tr><td align='center'><img src='Images/cargando.gif'></td></tr></table>";
	var datos = document.getElementById(div);
	datos.innerHTML = splash;
	var splash = document.getElementById('splash');
	//if(splash != null){
	//splash.style.display ='';
	//}
	if(!formulario){
	alert('error form');
	}
	
	var xhrArgs = {
		
		form: dojo.byId(formulario),
		url: action,
		error: function(response, ioArgs){
		alert("El sistema encontró una excepción o la sesión expiró");
		},
		load: function(response, ioArgs){
		document.getElementById(div).innerHTML = response;
	    },
	   handleAs: "text" 
	};

	var deferred = dojo.xhrPost(xhrArgs);
	if(splash != null){
	splash.style.display ='none';
	}   
} 
