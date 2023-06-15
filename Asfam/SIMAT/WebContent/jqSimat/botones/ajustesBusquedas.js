//[ajustes tab para buscadores].------------------------------------------------

  $(function() {
    $( "#tabsBusqueda" ).tabs();
  });


//[Busquedas Especificas].------------------------------------------------------------------------------------------
		function BuscarRutBenef(){
				$("#rutBenefMarca").text("");
				$("#rutBenef").css({'background-color' : 'white'});
				if(RutMod11($("#rutBenef").val())){
					document["filtroRutBenef"].submit();
				}else{
					$("#rutBenefMarca").text("*");
					$("#rutBenef").css({'background-color' : '#fef1ec'});
				}
        }
        
function BuscarNumDoc(){
				document["filtroNumDoc"].submit();        		
}

/*para tabla11 homologacion*/
function BuscarClasificacion(){
	
	var aux=$("#filtroBuscar").find("input[name='clasificacion']").val();
	marcarCampo("filtroBuscar","clasificacion",true);	
	if(!valNumericoBusqueda(aux) && aux!='' && aux!=null){
		marcarCampo("filtroBuscar","clasificacion",true);
		document["filtroBuscar"].submit();        		
	}else{
		marcarCampo("filtroBuscar","clasificacion",false);
	}
}
        
function BuscarEstadoDoc(){
	/*
	var aux=$("#filtroEstadoDoc").find("input[name='estadoDoc']").val();
	if(aux==null || aux==""){
		$("#rutBenefMarca").text("*");
		$("#rutBenef").css({'background-color' : '#fef1ec'});
	}else{
		$("#formAvance").find("input[name='keyEstadoDoc']").val($("#filtroEstadoDoc").find("input[name='estadoDoc']").val());
	document["filtroEstadoDoc"].submit();   
	}	     		
	*/
	$("#formAvance").find("input[name='keyEstadoDoc']").val($("#filtroEstadoDoc").find("input[name='estadoDoc']").val());
	document["filtroEstadoDoc"].submit();   
}

//[Valiaciones para busquedas].------------------------------

	function valFormatoRutBusqueda(valor){
		//retorna true si el valor no tiene formato rut, 999999999-9
		var key=true;
		
		if (/^\b\d{7,9}\-[K|k|0-9]$/.test(valor)){
				key=false;
		   	}
		return key;
	}
	
	function valNumericoBusqueda(valor){
	//retorna true si el valor contiene letras.
		var key=false;
		if (!/^([0-9])*$/.test(valor)){
			key=true;
   		}
		return key;
	}
	
function marcarCampo(tipoForm,campo,key){
	if(key){
		$("#"+tipoForm).find("#"+campo).css({'background-color' : 'white'});
		$("#"+tipoForm).find("#"+campo+"Marca").text("");
	}else{
		$("#"+tipoForm).find("#"+campo+"Marca").text("*");
		$("#"+tipoForm).find("#"+campo).val("");		
		$("#"+tipoForm).find("#"+campo).css({'background-color' : '#fef1ec'});
	}
	return true;
}
	