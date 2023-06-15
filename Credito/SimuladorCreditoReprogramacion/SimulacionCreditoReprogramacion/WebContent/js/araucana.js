function doPrint() {
	document.all.item("noprint").style.visibility = 'hidden';
	$("#titulo").css("text-align", "center");
	window.print();
	document.all.item("noprint").style.visibility = 'visible';
	$("#titulo").css("text-align", "left");
}
function limpiar(){
	window.location="/SimulacionCreditoReprogramacion/simuladorReprogramacion.do";
	/*
	$("#rutConsulta").val("");
	$("#rutAfiliado").val("");
	$("#nombre").html("");
	$("#nombre").hide();
	$('#contrato')
	.empty()
    .append('<option selected="selected" value="">Seleccione</option>');
	$('#tipoAfiliado')
	.empty()
    .append('<option selected="selected" value="">Seleccione</option>');
	$('#anexos')
	.empty()
    .append('<option selected="selected" value="">Seleccione</option>');
	$('#productos')
	.empty()
    .append('<option selected="selected" value="">Seleccione</option>');
	$('#cuotas')
	.empty()
    .append('<option selected="selected" value="">Seleccione</option>');
	$('#mesesGracia')
	.empty()
    .append('<option selected="selected" value="0">Seleccione</option>');
	$('#segCesantia')
	.empty()
	.append('<option selected="selected" value="">Seleccione</option>');
	$('#segDesgravamen')
	.empty()
	.append('<option selected="selected" value="">Seleccione</option>');
	$("#insolvencia").val("");
	$("#cesantia").val("");
	$("#desgravamen").val("");
	$('#bt_calcular').hide();
	
	*/
}

function limpiarAcuerdos(){
	$("#rutConsulta").val("");
	$("#rutAfiliado").val("");
	$("#nombre").html("");
	$("#nombre").hide();
	$('#contrato')
	.empty()
    .append('<option selected="selected" value="">Seleccione</option>');
	$('#tipoAfiliado')
	.empty()
    .append('<option selected="selected" value="">Seleccione</option>');
	$("#mensajeCampanha").html("");
	$('#montoAbono')[0].removeAttribute("disabled");
}

function getCampanhasAP(){
	url = '/SimulacionCreditoReprogramacion/data_ap.do';
	o = new jObject();
	o.list['event']="getCampanhasAP";
	o.list['contrato']=$('#contrato').val();
	
	var set = function(data){
		//$('productos').innerHTML = data;
		// document.getElementById("productos") =  $('#productos')[0]
		$('#mensajeCampanha').html("");
		
		addCampanha(data, 'concepto', 'valor');
	};
	
	r = new XMLHttp();
	r.onError = function(r){alert(r.responseText);}; 
	r.get (url,o.toURLString(),set);
}

function getCabeceras(){
	url = '/SimulacionCreditoReprogramacion/data.do';
	o = new jObject();
	o.list['event']="getCabecerasCredito";
	o.list['contrato']=$('#contrato').val();
	
	var set = function(data){
		//$('productos').innerHTML = data;
		// document.getElementById("productos") =  $('#productos')[0]
		$('#insolvencia').val("");
		$('#cesantia').val("");
		$('#desgravamen').val("");
		
		addCabeceras(data, 'concepto', 'valor');
	};
	
	r = new XMLHttp();
	r.onError = function(r){alert(r.responseText);}; 
	r.get (url,o.toURLString(),set);
	getProductos();
}

function bloqueaRepro(){
	$('#bt_calcular').hide();
}

function getProductos(){
	url = '/SimulacionCreditoReprogramacion/data.do';
	o = new jObject();
	o.list['event']="getProductos";
	o.list['tipoAfiliado']=$('#tipoAfiliado').val();
	o.list['insolvencia']=$('#insolvencia').val();
	$('#productos_selected').val($('#productos').val());
	var set = function(data){
		//$('productos').innerHTML = data;
		// document.getElementById("productos") =  $('#productos')[0]
		$('#productos').empty();
		addOption($('#productos')[0], data, 'codigo', 'nombre');
		$('#productos').val($('#productos_selected').val());
	};
	
	r = new XMLHttp();
	r.onError = function(r){alert(r.responseText);}; 
	r.get (url,o.toURLString(),set);
}

function getCuotas(){
	if($('#productos').val()!=""){
		url = '/SimulacionCreditoReprogramacion/data.do';
		o = new jObject();
		o.list['event']="getCuotas";
		o.list['tipoProducto']=$('#productos').val();
		o.list['edad']=$('#edad').val();
		$('#cuotas_selected').val($('#cuotas').val()); 

		var set = function(data){
			$('#cuotas').empty();
			addOption($('#cuotas')[0], data, 'codigo', 'nombre');
			$('#cuotas').val($('#cuotas_selected').val());
		};

		r = new XMLHttp();
		r.onError = function(r){alert(r.responseText);}; 
		r.get (url,o.toURLString(),set);
	}
}

function getCuotasAcuerdo(){

		url = '/SimulacionCreditoReprogramacion/data_ap.do';
		o = new jObject();
		o.list['event']="getCuotas";
		o.list['tipoProducto']='CA_ACUERDO_CASTIGO';
		o.list['edad']='0';
		$('#cuotas_selected').val($('#cuotas').val()); 

		var set = function(data){
			$('#cuotas').empty();
			addOption($('#cuotas')[0], data, 'codigo', 'nombre');
			$('#cuotas').val($('#cuotas_selected').val());
		};

		r = new XMLHttp();
		r.onError = function(r){alert(r.responseText);}; 
		r.get (url,o.toURLString(),set);

}

function getMesesGracia(){
	if($('#productos').val() != "" && $('#tipoAfiliado').val()!=""){
		url = '/SimulacionCreditoReprogramacion/data.do';
		o = new jObject();
		o.list['event']="getMesesGracia";
		o.list['tipoProducto']=$('#productos').val();
		o.list['tipoAfiliado']=$('#tipoAfiliado').val();
		$('#mesesGracia_selected').val($('#mesesGracia').val());
		var set = function(data){
			//$('productos').innerHTML = data;
			// document.getElementById("productos") =  $('#productos')[0]
			$('#mesesGracia').empty();
			addOption($('#mesesGracia')[0], data, 'codigo', 'nombre');
			//var mesesGracia_old= $('#mesesGracia_selected').val();
			//alert("mesesGracia_old=" + mesesGracia_old + ".");
			//if(mesesGracia_old==null || mesesGracia_old==""){
			//	alert("se asigna mesesGracia_old=0")
			//	mesesGracia_old=0;
			//}
			//$('#mesesGracia').val(mesesGracia_old);
			$('#mesesGracia')[0].options[0].selected=true;
		};

		r = new XMLHttp();
		r.onError = function(r){alert(r.responseText);}; 
		r.get (url,o.toURLString(),set);
	}
}

function getCesantia(){
	
	if( $('#tipoAfiliado').val()!=""){
		url = '/SimulacionCreditoReprogramacion/data.do';
		o = new jObject();
		o.list['event']="getCesantia";
		o.list['tipoAfiliado']=$('#tipoAfiliado').val();

		var set = function(data){
			//$('productos').innerHTML = data;
			// document.getElementById("productos") =  $('#productos')[0]
			$('#segCesantia').empty();
			$('#segCesantia').val(data[0].codigo);
			//addOption($('#segCesantia')[0], data, 'codigo', 'nombre');
		};

		r = new XMLHttp();
		r.onError = function(r){alert(r.responseText);}; 
		r.get (url,o.toURLString(),set);
	}
}

function getDesgravamen(){
	//if($('#productos').val() != ""){
		url = '/SimulacionCreditoReprogramacion/data.do';
		o = new jObject();
		o.list['event']="getDesgravamen";

		var set = function(data){
			//$('productos').innerHTML = data;
			// document.getElementById("productos") =  $('#productos')[0]
			$('#segDesgravamen').empty();
			$('#segDesgravamen').val(data[0].codigo);
			//addOption_Disabled($('#segDesgravamen')[0], data, 'codigo', 'nombre');
		};

		r = new XMLHttp();
		r.onError = function(r){alert(r.responseText);}; 
		r.get (url,o.toURLString(),set);
	//}
}

function validaMontoAbono(){
	if($('#cuotas').val() == 2){
		$('#montoAbono').val("");
		$('#montoAbono')[0].setAttribute("disabled", "disabled");
	}else{
		$('#montoAbono')[0].removeAttribute("disabled");
	}
}

function validaMontoAbonoxCesante(){
	if($('#productos').val() == "CA_REPRO_CESANTE"){
		$('#montoAbono').val("");
		$('#montoAbono')[0].setAttribute("disabled", "disabled");
	}else{
		$('#montoAbono')[0].removeAttribute("disabled");
	}
}